package com.zc.documenter.notify.dingding.service;

import java.util.List;

public interface AlertService {

    /**
     * 发送磁盘告警
     */
    void sendDiskAlert(String serverIp, double usageRate);

    /**
     * 发送异常告警
     */
    void sendExceptionAlert(Exception ex, String context);

    /**
     * 发送自定义告警（带@人）
     */
    void sendCustomAlert(String title, String content, List<String> atMobiles);
}