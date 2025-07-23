package com.zc.documenter.notify.sms.controller;

import com.zc.documenter.notify.sms.entity.R;
import com.zc.documenter.notify.sms.entity.SmsResult;
import com.zc.documenter.notify.sms.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/sms")
@RequiredArgsConstructor
public class SmsController {

    private static final Logger logger = LoggerFactory.getLogger(SmsController.class);
    private static final Pattern PHONE_PATTERN = Pattern.compile("^1[3-9]\\d{9}$");
    private final SmsService smsService;

    @PostMapping("/process")
    public R<SmsResult> sendProcessSms(
            @RequestParam String phoneNumber,
            @RequestParam String processName,
            @RequestParam String status) {
        if (!isValidPhoneNumber(phoneNumber)) {
            logger.error("无效的手机号码: {}", phoneNumber);
            return R.fail("无效的手机号码");
        }
        try {
            SmsResult result = smsService.send(phoneNumber, new String[]{"processName", processName, "status", status});
            return R.ok(result);
        } catch (Exception e) {
            logger.error("发送流程短信时出错", e);
            return R.fail("发送流程短信时出错: " + e.getMessage());
        }
    }

    @PostMapping("/custom")
    public R<SmsResult> sendCustomSms(
            @RequestParam String phone,
            @RequestParam String signName,
            @RequestParam String templateCode,
            @RequestParam String[] params) {
        if (!isValidPhoneNumber(phone)) {
            logger.error("无效的手机号码: {}", phone);
            return R.fail("无效的手机号码");
        }
        if (signName == null || signName.isEmpty()) {
            logger.error("短信签名不能为空");
            return R.fail("短信签名不能为空");
        }
        if (templateCode == null || templateCode.isEmpty()) {
            logger.error("短信模板代码不能为空");
            return R.fail("短信模板代码不能为空");
        }
        try {
            SmsResult result = smsService.send(phone, signName, templateCode, params);
            return R.ok(result);
        } catch (Exception e) {
            logger.error("发送自定义短信时出错", e);
            return R.fail("发送自定义短信时出错: " + e.getMessage());
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return PHONE_PATTERN.matcher(phoneNumber).matches();
    }
}