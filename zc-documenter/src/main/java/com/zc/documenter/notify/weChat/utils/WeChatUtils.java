package com.zc.documenter.notify.weChat.utils;

import com.alibaba.fastjson2.JSON;

import com.zc.documenter.notify.weChat.entity.WeChatResponse;
import com.zc.documenter.notify.weChat.entity.WeChatTemplateMessage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 微信公众号工具类
 * 封装：获取 AccessToken、发送模板消息 等通用逻辑
 */
@Component
public class WeChatUtils {
//    @Value("${wechat.app-id}")
    private String appId;
//    @Value("${wechat.app-secret}")
    private String appSecret;
//    @Value("${wechat.api-url}")
    private String apiUrl;

    // 缓存 AccessToken，避免频繁调用接口（实际生产可优化为带过期时间的缓存）
    private String accessToken;

    /**
     * 获取微信 AccessToken
     * 文档：https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_access_token.html
     */
    public String getAccessToken() throws IOException {
        if (accessToken == null) {
            String url = apiUrl + "token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            try {
                String result = EntityUtils.toString(response.getEntity());
                accessToken = JSON.parseObject(result).getString("access_token");
            } finally {
                response.close();
            }
        }
        return accessToken;
    }

    /**
     * 发送微信模板消息
     * @param message 模板消息实体
     * @return 微信接口响应
     */
    public WeChatResponse sendTemplateMessage(WeChatTemplateMessage message) throws IOException {
        String url = apiUrl + "message/template/send?access_token=" + getAccessToken();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        // 设置请求体为 JSON
        httpPost.setEntity(new StringEntity(JSON.toJSONString(message), "UTF-8"));
        httpPost.setHeader("Content-Type", "application/json");

        CloseableHttpResponse response = httpClient.execute(httpPost);
        try {
            String result = EntityUtils.toString(response.getEntity());
            return JSON.parseObject(result, WeChatResponse.class);
        } finally {
            response.close();
        }
    }
}