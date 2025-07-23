package com.zc.documenter.notify.dingding.service.impl;


import com.zc.documenter.notify.dingding.builder.MessageBuilder;
import com.zc.documenter.notify.dingding.entity.Message;
import com.zc.documenter.notify.dingding.service.NoticeService;
import com.zc.documenter.notify.dingding.utils.DingTalkRobotUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private DingTalkRobotUtil dingTalkRobotUtil;

    @Resource
    private MessageBuilder messageBuilder;

    @Override
    public void sendSystemUpgradeNotice(String startTime, int duration) {
        Message message = messageBuilder.buildSystemUpgradeNotice(startTime, duration);
        dingTalkRobotUtil.send(message);
    }

    @Override
    public void sendOperationSuccessNotice(String operation, String operator) {
        Message message = messageBuilder.buildOperationSuccessNotice(operation, operator);
        dingTalkRobotUtil.send(message);
    }

    @Override
    public void sendCustomMessage(Message message) {
        dingTalkRobotUtil.send(message);
    }
}