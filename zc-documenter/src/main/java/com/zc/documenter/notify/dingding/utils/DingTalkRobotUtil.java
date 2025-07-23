package com.zc.documenter.notify.dingding.utils;


import com.zc.documenter.notify.dingding.builder.MessageBuilder;
import com.zc.documenter.notify.dingding.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 该工具类用于向钉钉机器人发送消息。
 */
@Component
public class DingTalkRobotUtil {

    // 日志记录器
    private static final Logger log = LoggerFactory.getLogger(DingTalkRobotUtil.class);

    // 从配置文件中获取钉钉机器人的 Webhook 地址
    @Value("${dingtalk.webhook:}")
    private String webhook;

    // 从配置文件中获取钉钉机器人的密钥
    @Value("${dingtalk.secret:}")
    private String secret;

    // 从配置文件中获取钉钉消息的关键词，默认为 "生产工序测试"
    @Value("${dingtalk.keyword:生产工序测试}")
    private String keyword;

    /**
     * 向钉钉机器人发送消息。
     *
     * @param message 要发送的消息对象
     */
    public void send(Message message) {
        try {
            // 确保消息内容包含关键词
            ensureKeyword(message);
            // 使用消息构建器构建消息的 JSON 格式字符串
            String jsonBody = MessageBuilder.buildMessageBody(message);
            // 调用 DingTalkHttpUtil 发送消息，并获取发送结果
            boolean result = DingTalkHttpUtil.sendMessage(webhook, secret, jsonBody);
            if (!result) {
                // 若发送失败，记录错误日志
                log.error("钉钉消息发送失败，内容: {}", jsonBody);
            }
        } catch (Exception e) {
            // 若发送过程中出现异常，记录异常日志
            log.error("钉钉消息发送异常", e);
        }
    }

    /**
     * 确保消息内容包含关键词。
     *
     * @param message 要检查的消息对象
     */
    private void ensureKeyword(Message message) {
        if (keyword != null && !keyword.isEmpty()) {
            // 获取消息的内容
            String content = message.getContent();
            if (content == null || !content.contains(keyword)) {
                // 若内容为空或不包含关键词，则在内容前添加关键词
                message.setContent(keyword + " - " + (content == null ? "" : content));
            }
        }
    }
}