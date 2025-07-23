package com.zc.documenter.notify.dingding.service;


import com.zc.documenter.notify.dingding.entity.Message;

public interface NoticeService {

    /**
     * 发送系统升级通知
     */
    void sendSystemUpgradeNotice(String startTime, int duration);

    /**
     * 发送操作成功通知
     */
    void sendOperationSuccessNotice(String operation, String operator);

    /**
     * 发送自定义消息
     */
    void sendCustomMessage(Message message);
}