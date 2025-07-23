package com.zc.documenter.notify.dingding.controller;

import com.zc.common.core.domain.R;
import com.zc.documenter.notify.dingding.entity.Message;
import com.zc.documenter.notify.dingding.entity.MessageType;
import com.zc.documenter.notify.dingding.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Api(tags = "工序管理通知接口")
@RestController
@RequestMapping("/documenter/process")
public class ProcessNotifyController {

    @Resource
    private NoticeService noticeService;

    @ApiOperation("发送工序操作钉钉通知")
    @PostMapping("/notify")
    public R<String> sendProcessNotify(@RequestBody Map<String, Object> requestBody) {
        try {
            String operation = (String) requestBody.get("operation");
            String operator = (String) requestBody.get("operator");
            String title = (String) requestBody.get("title");
            String content = (String) requestBody.get("content");

            Message message = new Message();
            message.setType(MessageType.MARKDOWN);
            message.setTitle(title);
            message.setContent(content);

            noticeService.sendCustomMessage(message);
            return R.ok("工序操作通知已发送");
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("通知发送失败: " + e.getMessage());
        }
    }
}