package com.zc.documenter.notify.dingding.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 该工具类用于生成钉钉签名。
 */
public class DingTalkSignUtil {

    /**
     * 根据时间戳和密钥生成签名。
     *
     * @param timestamp 时间戳，单位为毫秒
     * @param secret    钉钉机器人的密钥
     * @return 生成的签名
     * @throws Exception 当加密过程中出现异常时抛出
     */
    public static String generateSign(long timestamp, String secret) throws Exception {
        // 拼接待签名的字符串，格式为 时间戳 + 换行符 + 密钥
        String stringToSign = timestamp + "\n" + secret;
        // 获取 HmacSHA256 算法的 Mac 实例
        Mac mac = Mac.getInstance("HmacSHA256");
        // 使用密钥初始化 Mac 实例
        mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        // 对拼接后的字符串进行签名
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        // 将签名结果进行 Base64 编码并返回
        return Base64.getEncoder().encodeToString(signData);
    }
}