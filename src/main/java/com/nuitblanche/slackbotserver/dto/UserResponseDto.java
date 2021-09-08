package com.nuitblanche.slackbotserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {

    private String id;
    private String teamId;
    private String name;

    @Builder
    public UserResponseDto(String id, String teamId, String name) {
        this.id = id;
        this.teamId = teamId;
        this.name = name;
    }
}
