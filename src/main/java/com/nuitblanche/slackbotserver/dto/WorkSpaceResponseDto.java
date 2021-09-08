package com.nuitblanche.slackbotserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WorkSpaceResponseDto {

    private String id;
    private String name;

    @Builder
    public WorkSpaceResponseDto(String id, String name) {
        this.id = id;
        this.name = name;
    }
}