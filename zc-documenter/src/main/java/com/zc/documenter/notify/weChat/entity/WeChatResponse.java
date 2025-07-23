package com.zc.documenter.notify.weChat.entity;

import lombok.Data;

/**
 * 微信接口通用响应实体类
 * 用于接收微信接口返回的结果（如发送模板消息后的响应）
 */
@Data
public class WeChatResponse {
    // 错误码（0 表示成功）
    private Integer errcode;
    // 错误信息
    private String errmsg;
    // 消息 ID（发送模板消息成功时返回）
    private String msgid;
}