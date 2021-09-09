package com.nuitblanche.slackbotserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WorkSpaceResponseDto {

    private String id;
    private String name;
    private String botUserId;

    @Builder
    public WorkSpaceResponseDto(String id, String name, String botUserId) {
        this.id = id;
        this.name = name;
        this.botUserId = botUserId;
    }
}
