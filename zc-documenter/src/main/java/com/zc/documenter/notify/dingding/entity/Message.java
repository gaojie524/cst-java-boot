package com.zc.documenter.notify.dingding.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private MessageType type;
    private String title;
    private String content;
    private List<String> atMobiles;
    private boolean atAll;
}    