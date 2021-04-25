package com.nxcloud.sdk;

import com.nxcloud.sdk.enums.VoiceTypeCode;
import com.nxcloud.sdk.utils.NxHttpClient;

import java.util.Map;

public class NxVoiceClient extends NxHttpClient {

    public NxVoiceClient(String api, String appkey, String secretkey) {
        super();
        this.api = api;
        param("appkey", appkey).param("secretkey", secretkey);
    }

    public Map<String, String> uploadFile(String fileName, String content) throws Exception {
        setNonEmptyFields(new String[]{
                "appkey", "secretkey", "filename", "content"
        });
        param("filename", fileName)
                .param("content", content)
        ;
        return this.executeFormJson();
    }

    public NxVoiceClient(VoiceTypeCode voiceTypeCode, String appkey, String secretkey) {
        super();
        this.api = voiceTypeCode.getApiUrl();
        param("appkey", appkey).param("secretkey", secretkey);
    }

    public Map<String, String> gpsend(String showPhone, String countryCode, String phone, String url) throws Exception {
        setNonEmptyFields(VoiceTypeCode.VOICE_GROUP.getNonEmptyFields());
        param("show_phone", showPhone)
                .param("country_code", countryCode)
                .param("phone", phone)
                .param("url", url)
        ;
        return this.executeFormJson();
    }

    public Map<String, String> versend(String showPhone, String countryCode, String phone, String content, String lang) throws Exception {
        setNonEmptyFields(VoiceTypeCode.VOICE_VERIFY.getNonEmptyFields());
        param("show_phone", showPhone)
                .param("country_code", countryCode)
                .param("phone", phone)
                .param("content", content)
                .param("lang", lang)
        ;
        return this.executeFormJson();
    }

    public Map<String, String> notsend(String showPhone, String countryCode, String phone, String content, String lang) throws Exception {
        setNonEmptyFields(VoiceTypeCode.VOICE_NOTICE.getNonEmptyFields());
        param("show_phone", showPhone)
                .param("country_code", countryCode)
                .param("phone", phone)
                .param("content", content)
                .param("lang", lang)
        ;
        return this.executeFormJson();
    }
}
