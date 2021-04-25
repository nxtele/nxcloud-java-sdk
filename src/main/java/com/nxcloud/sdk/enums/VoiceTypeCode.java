package com.nxcloud.sdk.enums;

/**
 *
 */
public enum VoiceTypeCode {

    /**
     *
     */
    VOICE_GROUP(0, "语音群呼", "http://api.nxcloud.com/api/voiceSms/gpsend",
            new String[]{"appkey", "secretkey", "country_code", "show_phone", "phone", "url"}),
    VOICE_VERIFY(1, "语音验证", "http://api.nxcloud.com/api/voiceSms/versend",
            new String[]{"appkey", "secretkey", "country_code", "show_phone", "phone", "content", "lang"}),
    VOICE_NOTICE(2, "语音通知", "http://api.nxcloud.com/api/voiceSms/notsend",
            new String[]{"appkey", "secretkey", "country_code", "show_phone", "phone", "content", "lang"});

    private final int code;
    private final String info;
    private final String apiUrl;
    private final String[] nonEmptyFields;

    VoiceTypeCode(int code, String info, String apiUrl, String[] nonEmptyFields) {
        this.code = code;
        this.info = info;
        this.apiUrl = apiUrl;
        this.nonEmptyFields = nonEmptyFields;
    }

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String[] getNonEmptyFields() {
        return nonEmptyFields;
    }

    /**
     * 获取code对应的信息
     *
     * @param code
     * @return
     */
    public static String getInfo(int code) {
        for (VoiceTypeCode value : VoiceTypeCode.values()) {
            if (value.getCode() == code) {
                return value.getInfo();
            }
        }
        return null;
    }

}
