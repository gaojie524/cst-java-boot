package com.zc.documenter.notify.dingding.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 该工具类用于通过 HTTP 请求向钉钉机器人发送消息。
 */
public class DingTalkHttpUtil {

    // 日志记录器
    private static final Logger log = LoggerFactory.getLogger(DingTalkHttpUtil.class);

    /**
     * 向钉钉机器人发送消息。
     *
     * @param webhook  钉钉机器人的 Webhook 地址
     * @param secret   钉钉机器人的密钥
     * @param jsonBody 要发送的消息的 JSON 格式字符串
     * @return 消息发送是否成功
     */
    public static boolean sendMessage(String webhook, String secret, String jsonBody) {
        try {
            // 构建带签名的 Webhook 地址
            String signedWebhook = buildSignedWebhook(webhook, secret);
            // 创建 URL 对象
            URL url = new URL(signedWebhook);
            // 打开 HTTP 连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置请求方法为 POST
            connection.setRequestMethod("POST");
            // 设置请求头的 Content-Type 为 application/json;charset=utf-8
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            // 允许向服务器输出内容
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                // 将 JSON 格式的消息内容转换为字节数组
                byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
                // 将字节数组写入输出流
                os.write(input, 0, input.length);
            }

            // 获取服务器的响应状态码
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 若响应状态码为 200，表示发送成功，记录日志并返回 true
                log.info("钉钉消息发送成功");
                return true;
            } else {
                // 若响应状态码不为 200，表示发送失败，记录错误日志并返回 false
                log.error("钉钉消息发送失败，HTTP状态码: {}", responseCode);
                return false;
            }
        } catch (Exception e) {
            // 若发送过程中出现异常，记录异常日志并返回 false
            log.error("钉钉消息发送异常: {}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 构建带签名的 Webhook 地址。
     *
     * @param webhook 钉钉机器人的 Webhook 地址
     * @param secret  钉钉机器人的密钥
     * @return 带签名的 Webhook 地址
     * @throws Exception 当生成签名过程中出现异常时抛出
     */
    private static String buildSignedWebhook(String webhook, String secret) throws Exception {
        if (secret == null || secret.isEmpty()) {
            // 若密钥为空，则直接返回原始 Webhook 地址
            return webhook;
        }
        // 获取当前时间戳
        long timestamp = System.currentTimeMillis();
        // 调用 DingTalkSignUtil 生成签名
        String sign = DingTalkSignUtil.generateSign(timestamp, secret);
        // 对签名进行 URL 编码
        sign = URLEncoder.encode(sign, StandardCharsets.UTF_8);
        // 拼接带签名的 Webhook 地址
        return webhook + "&timestamp=" + timestamp + "&sign=" + sign;
    }
}