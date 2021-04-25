package com.nxcloud.sdk;

import com.nxcloud.sdk.enums.EmailApiCode;

import java.util.HashMap;
import java.util.Map;

public class NxEmailClientTest {

    public static void testEmail() {
        NxEmailClient nxEmailClient = new NxEmailClient("http://test-2.nxtele.com/api/email/otp",
                "appKey1", "secretKey1");
        Map<String, String> data = new HashMap<>();
        data.put("vCode", "666666");
        nxEmailClient.param("templateData", data);
        // templateData
        Map<String, String> result = null;
        try {
            result = nxEmailClient.send("1990336062@demo.v2580.xyz", "1990336062@qq.com", "test001");
        } catch (java.net.MalformedURLException mue) {
            System.out.println("api url not right, please try http://api2.nxcloud.com/api/sms/mtsend or contact us!!");
            mue.printStackTrace();
        } catch (java.net.SocketTimeoutException ste) {
            System.out.println("timeout when send email, try nxEmailClient.setTimeoutSeconds(30);\n please contact us or change network to try!!");
            ste.printStackTrace();
        } catch (java.io.IOException ioe) {
            System.out.println("io exception, maybe poor network");
            ioe.printStackTrace();
        } catch (com.nxcloud.sdk.utils.json.JsonException jsone) {
            System.out.println("nxcloud internal error, please contact us!!");
            jsone.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String code = result.get("code");
        if (code.equals("0")) {
            // 发送成功，将 messageid 保存
            String messageId = result.get("result");
            System.out.println("send success, msgId:" + messageId);
        } else {
            System.out.println("send fail, reason:" + EmailApiCode.getMsg(Integer.parseInt(code)));
        }
    }

    public static void testEmailWithEmptyField() {
        NxEmailClient nxEmailClient = new NxEmailClient("http://test-2.nxtele.com/api/email/otp", "appKey", "secretKey");

        Map<String, String> result = null;
        try {
            Map<String, String> data = new HashMap<>();
            data.put("vCode", "666666");
            nxEmailClient.param("templateData", data);
            // templateData

            nxEmailClient
                    .param("from", "1990336062@demo.v2580.xyz")
                    .param("to", "1990336062@qq.com")
                    .param("templateName", "")
            ;
            result = nxEmailClient.executeJson();
        } catch (java.net.MalformedURLException mue) {
            System.out.println("api url not right, please try http://api2.nxcloud.com/api/sms/mtsend or contact us!!");
            mue.printStackTrace();
        } catch (java.net.SocketTimeoutException ste) {
            System.out.println("timeout when send email, try nxEmailClient.setTimeoutSeconds(30);\n please contact us or change network to try!!");
            ste.printStackTrace();
        } catch (java.io.IOException ioe) {
            System.out.println("io exception, maybe poor network");
            ioe.printStackTrace();
        } catch (com.nxcloud.sdk.utils.json.JsonException jsone) {
            System.out.println("nxcloud internal error, please contact us!!");
            jsone.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String code = result.get("code");
        if (code.equals("0")) {
            // 发送成功，将 messageid 保存
            String messageId = result.get("result");
            System.out.println("send success, msgId:" + messageId);
        } else {
            System.out.println("send fail, reason:" + EmailApiCode.getMsg(Integer.parseInt(code)));
        }
    }

    public static void main(String[] args) {
        testEmail();
        testEmailWithEmptyField();
    }
}
