package com.nxcloud.sdk.enums;

/**
 *
 */
public enum EmailApiCode {

    /**
     *
     */
    SUCCESS(0, "请求成功"),
    ERROR_CUS_STATUS(601001, "账号状态异常"),
    ERROR_CUS_BALANCE(601002, "账号余额不足"),
    ERROR_APP_INFO(601101, "应用状态异常，appKey/secretKey不匹配"),
    ERROR_APP_NO_PRICE(601102, "应用无报价"),
    ERROR_APP_NO_DOMAIN(601103, "应用下无该域名配置"),
    ERROR_APP_DOMAIN_STATUS(601104, "域名未校验通过"),
    ERROR_APP_NO_SENDER(601105, "发件邮箱地址不存在"),
    ERROR_APP_NO_TEMPLATE(601106, "邮件模板不存在"),
    ERROR_APP_TEMPLATE_STATUS(601107, "邮件模板审核未通过"),
    ERROR_MSG_STATUS(601201, "提交失败"),
    ERROR_PAGE_SIZE_TOO_BIG(601301, "每页数量过大");

    private final int code;
    private final String msg;

    private EmailApiCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 获取code对应的信息
     *
     * @param code
     * @return
     */
    public static String getMsg(int code) {
        for (EmailApiCode value : EmailApiCode.values()) {
            if (value.getCode() == code) {
                return value.getMsg();
            }
        }
        return null;
    }

}
