package com.nuitblanche.slackbotserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserGetRequestDto {

    private String team_id;

    @Builder
    public UserGetRequestDto(String team_id) {
        this.team_id = team_id;
    }
}
