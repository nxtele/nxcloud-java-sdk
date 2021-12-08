package com.nxcloud.sdk.utils;

import com.nxcloud.sdk.utils.encode.GSMEncoder;

import java.util.HashMap;
import java.util.Map;

public class SmsUtils {
    public static final int GSM_MAX_BYTE_SIZE = 160;//GSM编码一条短信最长字节数量160
    public static final int UCS2_MAX_BYTE_SIZE = 140;//UCS2编码一条短信最长字节数量140

    public static Map<String, Integer> INVISIBLE_CHAR_CNT = new HashMap<String, Integer>() {{
        put("60", 7); // 马拉西亚，运营商 7 个隐形字符
    }};

    public static int length(String content) throws Exception {
        String encode = GSMEncoder.canRepresent(content) ? "GSM":"UCS2";

        return encode.equals("UCS2") ? content.getBytes("UTF-16LE").length :
                new GSMEncoder().encode(content).length;
    }

    public static int size(String content, String countryCode) throws Exception {
        String encode = GSMEncoder.canRepresent(content) ? "GSM":"UCS2";

        int size = 0;
        int len = encode.equals("UCS2") ? content.getBytes("UTF-16LE").length :
                new GSMEncoder().encode(content).length;
        int invisibleLen = 0;
        if (INVISIBLE_CHAR_CNT.containsKey(countryCode)) {
            invisibleLen = INVISIBLE_CHAR_CNT.get(countryCode);
        }
        if (encode.contains("GSM")) {
            len = len + invisibleLen;
            if (len > GSM_MAX_BYTE_SIZE) {
                size = len % (GSM_MAX_BYTE_SIZE - 6) == 0 ? len / (GSM_MAX_BYTE_SIZE - 6) : len / (GSM_MAX_BYTE_SIZE - 6) + 1;
            } else {
                size = 1;
            }
        } else {
            len = len + invisibleLen * 2;
            if (len > UCS2_MAX_BYTE_SIZE) {
                size = len % (UCS2_MAX_BYTE_SIZE - 6) == 0 ? len / (UCS2_MAX_BYTE_SIZE - 6) : len / (UCS2_MAX_BYTE_SIZE - 6) + 1;
            } else {
                size = 1;
            }
        }

        return size;
    }

}
