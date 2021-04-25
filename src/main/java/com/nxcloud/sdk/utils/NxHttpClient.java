package com.nxcloud.sdk.utils;

import com.nxcloud.sdk.utils.json.JsonObject;
import com.nxcloud.sdk.utils.json.Jsoner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class NxHttpClient {

    protected String api = "";
    protected Map<String, Object> params;
    protected String[] nonEmptyFields = new String[0];
    protected int timeoutSeconds = 10;

    public NxHttpClient() {
        params = new HashMap<>();
    }

    public void setNonEmptyFields(String[] nonEmptyFields) {
        this.nonEmptyFields = nonEmptyFields;
    }

    public void setTimeoutSeconds(int timeoutSeconds) {
        this.timeoutSeconds = timeoutSeconds;
    }

    public NxHttpClient param(String name, Object value) {
        params.put(name, value);
        return this;
    }

    private void checkParams() throws Exception {
        for (String param : nonEmptyFields) {
            if (!params.containsKey(param) || null == params.get(param) || String.valueOf(params.get(param)).length() == 0) {
                throw new Exception(param + " must not be empty!!");
            }
        }
    }

    private Map<String, String> json2Map(String result) throws Exception {
        JsonObject jsonObject = (JsonObject) Jsoner.deserialize(result);
        Map<String, String> resultMap = new HashMap<>();
        for (String key : jsonObject.keySet()) {
            resultMap.put(key, String.valueOf(jsonObject.get(key)));
        }
        return resultMap;
    }

    private HttpURLConnection getConnection() throws Exception {
        URL url = new URL(api);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        connection.setConnectTimeout(timeoutSeconds * 1000);
        connection.setReadTimeout(timeoutSeconds * 1000);
        connection.setUseCaches(false);

        return connection;
    }

    public Map<String, String> executeJson() throws Exception {
        checkParams();

        HttpURLConnection connection = getConnection();
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.connect();

        try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8)) {
            JsonObject reqBody = new JsonObject(params);
            out.write(reqBody.toJson());
            out.flush();
        }

        String result = "";
        // 获取服务端响应，通过输入流来读取URL的响应
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            result = response.toString();
        }

        connection.disconnect();
        return json2Map(result);
    }

    public String executeFormString() throws Exception {
        checkParams();

        HttpURLConnection connection = getConnection();
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        connection.connect();

        try (PrintWriter out = new PrintWriter(connection.getOutputStream())) {
            String postBody = params.entrySet().stream().map(x -> {
                try {
                    return x.getKey() + "=" + URLEncoder.encode(String.valueOf(x.getValue()), "UTF-8");
                } catch (Exception e) {
                    return "";
                }
            }).collect(Collectors.joining("&"));
            out.print(postBody);
        }

        String result = "";
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        }

        return result;
    }

    public Map<String, String> executeFormJson() throws Exception {
        return json2Map(executeFormString());
    }
}
