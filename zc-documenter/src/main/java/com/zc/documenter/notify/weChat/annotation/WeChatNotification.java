package com.zc.documenter.notify.weChat.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解示例：标记微信公众号通知相关的接口/方法
 * 可用于后续 AOP 切面处理（如日志、鉴权等）
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WeChatNotification {
    String desc() default "微信公众号通知";
}