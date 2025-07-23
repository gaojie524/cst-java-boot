package com.zc.documenter.notify.sms.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.zc.documenter.notify.sms.entity.AliyunSmsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AliyunSmsClient {

    private final AliyunSmsConfig aliyunSmsConfig;

    public SendSmsResponse send(String phone, String signName, String templateCode, String templateParam) throws ClientException {
        IClientProfile profile = DefaultProfile.getProfile(
                "cn-hangzhou",
                aliyunSmsConfig.getAccessKeyId(),
                aliyunSmsConfig.getAccessKeySecret()
        );
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
        IAcsClient client = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phone);
        request.setSignName(signName != null ? signName : aliyunSmsConfig.getSignName());
        request.setTemplateCode(templateCode != null ? templateCode : aliyunSmsConfig.getTemplateCode());
        request.setTemplateParam(templateParam);

        return client.getAcsResponse(request);
    }
}