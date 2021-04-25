package com.nxcloud.sdk;

import com.nxcloud.sdk.utils.NxHttpClient;

import java.util.Map;

public class NxSmsClient extends NxHttpClient {

    public NxSmsClient() {
        super();
    }

    public NxSmsClient(String appkey, String secretkey) {
        super();
        param("appkey", appkey).param("secretkey", secretkey);
    }

    public Map<String, String> mtsend(String phone, String content, String... args) throws Exception {
        this.nonEmptyFields = new String[]{
                "appkey", "secretkey", "phone", "content"
        };

        if (args.length > 0) {
            this.api = args[0];
        } else {
            this.api = "http://api2.nxcloud.com/api/sms/mtsend";
        }
        param("phone", phone).param("content", content);

        return this.executeFormJson();
    }

    public String conversion(String messageId, String... args) throws Exception {
        this.nonEmptyFields = new String[]{
                "messageid"
        };
        this.api = "http://api2.nxcloud.com/api/smsdr/conversion";

        this.param("messageid", messageId);
        if (args.length > 0) {
            this.param("phone", args[0]);
        } else if (args.length > 1) {
            this.param("status", args[1]);
        }

        return this.executeFormString();
    }

}