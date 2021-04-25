package com.nxcloud.sdk;

import com.nxcloud.sdk.enums.VoiceTypeCode;
import com.nxcloud.sdk.utils.Constant;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.util.Map;

public class NxVoiceClientTest {

    /**
     * 文件上传接口示例
     */
    @Test
    public void testVoiceFileUpload() {
        NxVoiceClient nxVoiceClient = new NxVoiceClient("http://api2.nxcloud.com/api/voiceSms/uploadVoiceFile",
                "", "");

        Map<String, String> result = null;
        try {
            File file = new File("src/main/resources/file/59ab53092e7e5cec16756ce98fc9b2c8.mp3");
            byte[] readFileToByteArray = FileUtils.readFileToByteArray(file);
            String content = Base64.encodeBase64String(readFileToByteArray);
            //
            result = nxVoiceClient.uploadFile("hello.mp3", content);
        } catch (java.net.MalformedURLException mue) {
            System.out.println("api url not right, please try http://api2.nxcloud.com/api/voiceSms/uploadVoiceFile or contact us!!");
            mue.printStackTrace();
        } catch (java.net.SocketTimeoutException ste) {
            System.out.println("timeout when send sms, try nxVoiceClient.setTimeoutSeconds(30);\n please contact us or change network to try!!");
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
        if (code.equals("success")) {
            String info = result.get("info");
            System.out.println("upload voice file success, voice url: " + info);
        } else {
            System.out.println("upload fail, reason:" + Constant.ErrorCodes.get(code));
        }
    }

    public static void testVoiceGroup() {
        NxVoiceClient nxVoiceClient = new NxVoiceClient(VoiceTypeCode.VOICE_GROUP, "", "");

        Map<String, String> result = null;
        try {
            nxVoiceClient
                    .param("task_time", "") // 定时任务开始时间，不填等于立即发送
                    .param("task_zone", "") // 时区（8、9、10.....），如果task_time传值，time_zone就必填，否则task_time失效
                    .param("sched_hangup", "") // 定时接通后多少秒挂断(为0则不限制)
                    .param("sms_appkey", "") // 短信应用APPKEY
                    .param("msg_content", "") // 追加短信内容
            ; // https://github.com/nxtele/http-api-document/wiki/%E5%8F%91%E9%80%81%E8%AF%AD%E9%9F%B3%E7%BE%A4%E5%91%BC
            result = nxVoiceClient.gpsend("8615058876610", "86", "84934442648",
                    "https://nxcloudhk.oss-cn-hongkong.aliyuncs.com/voice_group/1605696584389.m4a");
        } catch (java.net.MalformedURLException mue) {
            System.out.println("api url not right, please try http://api.nxcloud.com/api/voiceSms/gpsend or contact us!!");
            mue.printStackTrace();
        } catch (java.net.SocketTimeoutException ste) {
            System.out.println("timeout when send sms, try nxVoiceClient.setTimeoutSeconds(30);\n please contact us or change network to try!!");
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

    public static void testVoiceVerify() {
        NxVoiceClient nxVoiceClient = new NxVoiceClient(VoiceTypeCode.VOICE_VERIFY, "", "");

        Map<String, String> result = null;
        try {
            result = nxVoiceClient.versend("84998996123", "84", "84934442648",
                    "hello today is Friday 1-2-3-4-5-6", "en");
        } catch (java.net.MalformedURLException mue) {
            System.out.println("api url not right, please try http://api.nxcloud.com/api/voiceSms/versend or contact us!!");
            mue.printStackTrace();
        } catch (java.net.SocketTimeoutException ste) {
            System.out.println("timeout when send sms, try nxVoiceClient.setTimeoutSeconds(30);\n please contact us or change network to try!!");
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

    public static void testVoiceNotice() {
        NxVoiceClient nxVoiceClient = new NxVoiceClient(VoiceTypeCode.VOICE_NOTICE, "", "");

        Map<String, String> result = null;
        try {
            result = nxVoiceClient.notsend("84998996123", "84", "84934442648",
                    "hello today is Friday.", "en");
        } catch (java.net.MalformedURLException mue) {
            System.out.println("api url not right, please try http://api.nxcloud.com/api/voiceSms/notsend or contact us!!");
            mue.printStackTrace();
        } catch (java.net.SocketTimeoutException ste) {
            System.out.println("timeout when send sms, try nxVoiceClient.setTimeoutSeconds(30);\n please contact us or change network to try!!");
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

    public static void main(String[] args) {
        testVoiceGroup();
        testVoiceVerify();
        testVoiceNotice();
    }
}
