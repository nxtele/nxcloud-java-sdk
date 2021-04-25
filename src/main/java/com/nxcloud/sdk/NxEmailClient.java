package com.nxcloud.sdk;

import com.nxcloud.sdk.utils.NxHttpClient;

import java.util.Map;

public class NxEmailClient extends NxHttpClient {
    public NxEmailClient(String appKey, String secretKey) {
        super();
        param("appKey", appKey).param("secretKey", secretKey);
    }

    public NxEmailClient(String api, String appKey, String secretKey) {
        super();
        this.api = api;
        param("appKey", appKey).param("secretKey", secretKey);
    }

    public Map<String, String> send(String from, String to, String templateName) throws Exception {
        this.nonEmptyFields = new String[]{
                "appKey", "secretKey", "from", "to", "templateName"
        };
        this.api = "http://api2.nxcloud.com/api/email/otp";
        
        param("from", from)
                .param("to", to)
                .param("templateName", templateName);

        return this.executeJson();
    }
}
