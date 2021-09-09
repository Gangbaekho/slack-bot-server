package com.nuitblanche.slackbotserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SingleOpenChannelRequestDto {

    private String users;
    private String text;
    private String botUserId;

    @Builder
    public SingleOpenChannelRequestDto(String users, String text, String botUserId) {
        this.users = users;
        this.text = text;
        this.botUserId = botUserId;
    }
}
