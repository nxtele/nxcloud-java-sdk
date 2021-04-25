package com.nxcloud.sdk;

import com.nxcloud.sdk.utils.Constant;
import com.nxcloud.sdk.utils.SmsUtils;

import java.util.Map;

public class NxSmsClientTest {

    public static void testSms() {
        String appkey = "1";
        String secretkey = "2";

        // 这个类不是线程安全的，请每次调用，都重新初始化一个！
        NxSmsClient nxSmsClient = new NxSmsClient(appkey, secretkey);

        Map<String, String> result = null;
        try {
            nxSmsClient
                    .param("source_address", "") // sender，发件人，请勿随意填写！
//                    .param("country_code", "91")
//                    .param("task_time", "2021-04-02 12:59:59") // 定时发送
//                    .param("sys_messageid", "") // 可以选择自己生成消息ID
//                    .param("short_link", "") // 短链
//                    .param("linkVerbose", "") // 是否追踪短链点击的手机号码（可能会降低SMS短信成功率）
//                    .param("opt_entity_id", "") // 印度 dlt，91 方向实体编号
//                    .param("opt_template_id", "") // 印度 dlt，91 方向模板编号
//                    .param("opt_header_id", "") // 印度 dlt，91 方向发件人编号
            ;
            result = nxSmsClient.mtsend("620111111111111", "hello this is a test sms");
        } catch (java.net.MalformedURLException mue) {
            System.out.println("api url not right, please try http://api2.nxcloud.com/api/sms/mtsend or contact us!!");
            mue.printStackTrace();
        } catch (java.net.SocketTimeoutException ste) {
            System.out.println("timeout when send sms, try nxSmsClient.setTimeoutSeconds(30);\n please contact us or change network to try!!");
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
            String messageId = result.get("messageid");
            System.out.println("send success, msgid:" + messageId);
        } else {
            System.out.println("send fail, reason:" + Constant.ErrorCodes.get(code));
        }
    }

    public static void testConversion() {
        // ... 等待客户输入验证码，登陆系统之后，向我们上报用户回填事件
        // 这个类不是线程安全的，请每次调用，都重新初始化一个！
        NxSmsClient nxSmsClient = new NxSmsClient();
        try {
            String conversionResult = nxSmsClient.conversion("abcdefg");
            System.out.println("report conversion success, :" + conversionResult);
        } catch (Exception e) {
            System.out.println("report conversion fail");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        testSms();
//        testConversion();
        System.out.println("计费条数：" + SmsUtils.size("[1234]", "62"));
        System.out.println("计费长度：" + SmsUtils.length("["));
    }
}
