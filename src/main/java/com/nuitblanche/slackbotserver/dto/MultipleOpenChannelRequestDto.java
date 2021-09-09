package com.nuitblanche.slackbotserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MultipleOpenChannelRequestDto {

    private List<String> users;
    private String text;

    @Builder
    public MultipleOpenChannelRequestDto(List<String> users, String text) {
        this.users = users;
        this.text = text;
    }
}
