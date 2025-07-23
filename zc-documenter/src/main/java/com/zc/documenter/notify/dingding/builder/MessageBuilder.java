package com.zc.documenter.notify.dingding.builder;

import com.alibaba.fastjson2.JSONObject;

import com.zc.documenter.notify.dingding.entity.Message;
import com.zc.documenter.notify.dingding.entity.MessageType;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class MessageBuilder {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 构建消息体的公共方法
    public static String buildMessageBody(Message message) {
        JSONObject msg = new JSONObject();
        setMessageTypeAndContent(msg, message);
        setAtInfo(msg, message);
        return msg.toString();
    }

    // 设置消息类型和内容
    private static void setMessageTypeAndContent(JSONObject msg, Message message) {
        if (message.getType() == MessageType.TEXT) {
            setTextMessage(msg, message);
        } else if (message.getType() == MessageType.MARKDOWN) {
            setMarkdownMessage(msg, message);
        }
    }

    // 设置文本消息
    private static void setTextMessage(JSONObject msg, Message message) {
        msg.put("msgtype", "text");
        JSONObject text = new JSONObject();
        text.put("content", message.getContent());
        msg.put("text", text);
    }

    // 设置 Markdown 消息
    private static void setMarkdownMessage(JSONObject msg, Message message) {
        msg.put("msgtype", "markdown");
        JSONObject markdown = new JSONObject();
        markdown.put("title", message.getTitle());
        markdown.put("text", message.getContent());
        msg.put("markdown", markdown);
    }

    // 设置 @ 人信息
    private static void setAtInfo(JSONObject msg, Message message) {
        JSONObject at = new JSONObject();
        if (message.getAtMobiles() != null && !message.getAtMobiles().isEmpty()) {
            at.put("atMobiles", message.getAtMobiles());
        }
        at.put("isAtAll", message.isAtAll());
        msg.put("at", at);
    }

    // 初始化 Markdown 消息的公共方法
    private Message initMarkdownMessage(String title, List<String> atMobiles) {
        Message message = new Message();
        message.setType(MessageType.MARKDOWN);
        message.setTitle(title);
        message.setAtMobiles(atMobiles);
        return message;
    }

    // 格式化日期的公共方法
    private String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    public Message buildSystemUpgradeNotice(String startTime, int duration) {
        Message message = initMarkdownMessage("系统升级通知", null);
        StringBuilder content = buildBaseContent("系统升级通知");
        content.append("**升级时间**：").append(startTime).append("\n\n");
        content.append("**预计时长**：").append(duration).append("分钟\n\n");
        content.append("请各位用户提前做好准备，避免影响正常业务。\n\n");
        content.append("> 如有疑问，请联系技术支持。");
        message.setContent(content.toString());
        return message;
    }

    public Message buildOperationSuccessNotice(String operation, String operator) {
        Message message = initMarkdownMessage("操作成功通知", null);
        StringBuilder content = buildBaseContent("操作成功通知");
        content.append("**操作类型**：").append(operation).append("\n\n");
        content.append("**操作人**：").append(operator).append("\n\n");
        content.append("**操作时间**：").append(formatDate(new Date())).append("\n\n");
        content.append("> 此操作已成功完成。");
        message.setContent(content.toString());
        return message;
    }

    public Message buildDiskAlert(String serverIp, double usageRate, List<String> atMobiles) {
        Message message = initMarkdownMessage("磁盘空间告警", atMobiles);
        StringBuilder content = buildBaseContent("严重告警：磁盘空间不足");
        content.append("**服务器IP**：").append(serverIp).append("\n\n");
        content.append("**磁盘使用率**：").append(String.format("%.2f", usageRate)).append("%\n\n");
        content.append("**告警时间**：").append(formatDate(new Date())).append("\n\n");
        content.append("磁盘空间使用率已超过阈值，请及时处理！");
        message.setContent(content.toString());
        return message;
    }

    public Message buildExceptionAlert(Exception ex, String context, List<String> atMobiles) {
        Message message = initMarkdownMessage("系统异常告警", atMobiles);
        StringBuilder content = buildBaseContent("系统异常告警");
        content.append("**异常场景**：").append(context).append("\n\n");
        content.append("**异常类型**：").append(ex.getClass().getName()).append("\n\n");
        content.append("**异常信息**：").append(ex.getMessage()).append("\n\n");
        content.append("**异常时间**：").append(formatDate(new Date())).append("\n\n");
        content.append("**堆栈信息**：\n```java\n");
        addStackTrace(content, ex);
        content.append("```\n\n请相关人员及时处理！");
        message.setContent(content.toString());
        return message;
    }

    public Message buildCustomAlert(String title, String content, List<String> atMobiles) {
        Message message = initMarkdownMessage(title, atMobiles);
        message.setContent(content);
        return message;
    }

    // 构建消息基础内容
    private StringBuilder buildBaseContent(String header) {
        StringBuilder content = new StringBuilder();
        content.append("### ").append(header).append("\n\n");
        return content;
    }

    // 添加堆栈信息
    private void addStackTrace(StringBuilder content, Exception ex) {
        StackTraceElement[] stackTrace = ex.getStackTrace();
        int maxLines = Math.min(5, stackTrace.length);
        for (int i = 0; i < maxLines; i++) {
            content.append(stackTrace[i].toString()).append("\n");
        }
    }
}