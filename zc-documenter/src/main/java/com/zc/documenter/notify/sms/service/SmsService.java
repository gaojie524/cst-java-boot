package com.zc.documenter.notify.sms.service;

import com.zc.documenter.notify.sms.entity.SmsResult;

public interface SmsService {
    SmsResult send(String phone, String[] params);
    SmsResult send(String phone, String signName, String templateCode, String[] params);
}