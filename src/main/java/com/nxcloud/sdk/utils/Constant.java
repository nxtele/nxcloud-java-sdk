package com.nxcloud.sdk.utils;

import java.util.HashMap;
import java.util.Map;

public class Constant {

    public static Map<String, String> ErrorCodes = new HashMap<String, String>() {{
        put("0", "SUCCESS");
        put("1", "APP_KEY_ERROR");
        put("2", "PARAMETER_ERROR");
        put("3", "INSUFFICIENT_BALANCE");
        put("4", "EMPTY_OR_ILLEGAL_CONTENT");
        put("5", "CONTENT_TOO_LONG");
        put("6", "PHONE_NUMBER_ERROR");
        put("7", "NUMBER_MORE_THAN_100");
        put("8", "SENDER_ERROR"); // SENDER BETWEEN (3, 15), AND only letters and numbers
        put("9", "IP_NOT_ALLOWED");
        put("10", "CONTENT_NEED_UTF8_ENCODE");
        put("11", "EMAIL_FORMAT_WORNG");
        put("12", "CUSTOMER_PRICE_ERROR");
        put("13", "CUSTOMER_APP_NOT_EXIST");
        put("14", "NO_VENDOR_PRICE");
        put("15", "NO_VENDOR");
        put("16", "VENDOR_ROUTE_ERROR");
        put("17", "VENDOR_ERROR");
        put("20", "URL_NOT_EXIST");
        put("21", "SHOW_NUMBER_ERROR");
        put("22", "NO_ROUTE");
        put("23", "COUNTRY_CODE_ERROR");
        put("24", "NO_CALLEE_NUMBER");
        put("26", "DUPLICATED_PHONE_CONTENT_IN_5_MINUTES");
        put("88", "REQUEST_ERROR");
        put("99", "SYSTEM_ERROR");
        put("101", "FEATURE_DISABLED");
        put("102", "ACCOUNT_DISABLED");
        put("103", "EMOJI_NOT_SUPPORTED");
    }};

}
