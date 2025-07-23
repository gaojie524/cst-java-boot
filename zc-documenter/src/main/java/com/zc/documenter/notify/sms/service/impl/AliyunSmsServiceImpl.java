package com.zc.documenter.notify.sms.service.impl;


import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.zc.documenter.notify.sms.entity.SmsResult;
import com.zc.documenter.notify.sms.service.SmsService;
import com.zc.documenter.notify.sms.utils.AliyunSmsClient;
import com.zc.documenter.notify.sms.utils.SmsParamUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AliyunSmsServiceImpl implements SmsService {

    private final AliyunSmsClient aliyunSmsClient;

    @Override
    public SmsResult send(String phone, String[] params) {
        return send(phone, null, null, params);
    }

    @Override
    public SmsResult send(String phone, String signName, String templateCode, String[] params) {
        try {
            String templateParam = SmsParamUtils.buildParamJson(params);
            SendSmsResponse response = aliyunSmsClient.send(phone, signName, templateCode, templateParam);
            if ("OK".equals(response.getCode())) {
                return SmsResult.success("发送成功", response.getBizId());
            } else {
                return SmsResult.fail("阿里云返回失败：" + response.getCode() + " - " + response.getMessage());
            }
        } catch (ClientException e) {
            return SmsResult.fail("发送异常：" + e.getErrCode() + " - " + e.getErrMsg());
        }
    }
}