package com.zc.documenter.notify.weChat.entity;

import lombok.Data;
import java.util.Map;

/**
 * 微信公众号模板消息实体类
 * 对应微信模板消息格式：https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Template_Message_Interface.html
 */
@Data
public class WeChatTemplateMessage {
    // 接收者 openid
    private String touser;
    // 模板 ID
    private String template_id;
    // 模板跳转链接（可选）
    private String url;
    // 模板数据（对应模板里的关键词）
    private Map<String, WeChatTemplateData> data;

    /**
     * 模板数据内部类（如 first、keyword1、remark 等）
     */
    @Data
    public static class WeChatTemplateData {
        // 关键词值
        private String value;
        // 关键词颜色（可选，默认 #173177）
        private String color = "#173177";

        public WeChatTemplateData(String value) {
            this.value = value;
        }
    }
}