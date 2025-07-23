package com.zc.documenter.notify.weChat.service.impl;

import com.zc.documenter.notify.weChat.entity.WeChatResponse;
import com.zc.documenter.notify.weChat.entity.WeChatTemplateMessage;
import com.zc.documenter.notify.weChat.service.WeChatNotificationService;
import com.zc.documenter.notify.weChat.utils.WeChatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 微信公众号通知服务实现类
 * 实现发送模板消息的具体逻辑
 */
@Service
public class WeChatNotificationServiceImpl implements WeChatNotificationService {
    @Autowired
    private WeChatUtils weChatUtils;

    // 修正为完整路径（匹配配置文件的层级）
//    @Value("${wechat.template.test-template-id}")
    private String templateId;

    @Override
    public WeChatResponse sendTemplateMessage(String openId, WeChatTemplateMessage templateData) throws IOException {
        // 设置模板 ID
        templateData.setTemplate_id(templateId);
        return weChatUtils.sendTemplateMessage(templateData);
    }
}