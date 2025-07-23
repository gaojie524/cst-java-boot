package com.zc.documenter.notify.dingding.service.impl;



import com.zc.documenter.notify.dingding.builder.MessageBuilder;
import com.zc.documenter.notify.dingding.entity.Message;
import com.zc.documenter.notify.dingding.service.AlertService;
import com.zc.documenter.notify.dingding.utils.DingTalkRobotUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class AlertServiceImpl implements AlertService {

    @Resource
    private DingTalkRobotUtil dingTalkRobotUtil;

    @Resource
    private MessageBuilder messageBuilder;

    /**
     * 运维人员手机号列表
     */
    private static final List<String> OPS_MOBILES = Arrays.asList(
            "13800001111", "13900002222"
    );

    @Override
    public void sendDiskAlert(String serverIp, double usageRate) {
        Message message = messageBuilder.buildDiskAlert(serverIp, usageRate, OPS_MOBILES);
        dingTalkRobotUtil.send(message);
    }

    @Override
    public void sendExceptionAlert(Exception ex, String context) {
        Message message = messageBuilder.buildExceptionAlert(ex, context, OPS_MOBILES);
        dingTalkRobotUtil.send(message);
    }

    @Override
    public void sendCustomAlert(String title, String content, List<String> atMobiles) {
        Message message = messageBuilder.buildCustomAlert(title, content, atMobiles);
        dingTalkRobotUtil.send(message);
    }
}    