package com.zc.documenter.notify.sms.utils;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class SmsParamUtils {
    public static String buildParamJson(String[] params) {
        if (params == null || params.length % 2 != 0) {
            return "{}";
        }
        Map<String, String> paramMap = new HashMap<>();
        for (int i = 0; i < params.length; i += 2) {
            paramMap.put(params[i], params[i + 1]);
        }
        Gson gson = new Gson();
        return gson.toJson(paramMap);
    }
}