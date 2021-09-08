package com.nuitblanche.slackbotserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageSendRequestDto {

    private String channel;
    private String text;

    public MessageSendRequestDto(String channel, String text) {
        this.channel = channel;
        this.text = text;
    }
}
