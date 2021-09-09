package com.nuitblanche.slackbotserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChannelOpenResponseDto {

    private String id;

    @Builder
    public ChannelOpenResponseDto(String id) {
        this.id = id;
    }
}
