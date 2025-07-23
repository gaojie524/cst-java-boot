package com.zc.documenter.notify.weChat.controller;


import com.zc.documenter.notify.weChat.entity.WeChatResponse;
import com.zc.documenter.notify.weChat.entity.WeChatTemplateMessage;
import com.zc.documenter.notify.weChat.service.WeChatNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 微信公众号通知控制器
 * 对外暴露发送通知的 HTTP 接口
 */
@RestController
@RequestMapping("/wechat/notification")
public class WeChatNotificationController {
    @Autowired
    private WeChatNotificationService notificationService;

    /**
     * 发送微信模板消息接口
     * @param request 包含 openid 和模板数据的请求体
     * @return 微信接口响应
     */
    @PostMapping("/send")
    public WeChatResponse sendTemplateMessage(@RequestBody WeChatTemplateMessage request) {
        try {
            return notificationService.sendTemplateMessage(request.getTouser(), request);
        } catch (IOException e) {
            // 实际生产可封装统一异常处理，返回更友好的错误信息
            WeChatResponse response = new WeChatResponse();
            response.setErrcode(500);
            response.setErrmsg("发送通知失败：" + e.getMessage());
            return response;
        }
    }
}