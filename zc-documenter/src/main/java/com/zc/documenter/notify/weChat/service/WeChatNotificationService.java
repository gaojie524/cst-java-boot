package com.zc.documenter.notify.weChat.service;



import com.zc.documenter.notify.weChat.entity.WeChatResponse;
import com.zc.documenter.notify.weChat.entity.WeChatTemplateMessage;

import java.io.IOException;

/**
 * 微信公众号通知服务接口
 * 定义发送通知的规范方法
 */
public interface WeChatNotificationService {
    /**
     * 发送模板消息
     * @param openId 接收者 openid
     * @param templateData 模板数据（key: 模板关键词，value: 对应值）
     * @return 微信接口响应
     */
    WeChatResponse sendTemplateMessage(String openId, WeChatTemplateMessage templateData) throws IOException;
}