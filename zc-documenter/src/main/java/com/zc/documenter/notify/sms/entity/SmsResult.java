package com.zc.documenter.notify.sms.entity;

import lombok.Data;

@Data
public class SmsResult {
    private boolean success;
    private String message;
    private String bizId;

    public static SmsResult success(String message, String bizId) {
        SmsResult result = new SmsResult();
        result.setSuccess(true);
        result.setMessage(message);
        result.setBizId(bizId);
        return result;
    }

    public static SmsResult fail(String message) {
        SmsResult result = new SmsResult();
        result.setSuccess(false);
        result.setMessage(message);
        result.setBizId(null);
        return result;
    }
}