package com.nuitblanche.slackbotserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserGetRequestDto {

    private String team_id;
    private String botUserId;

    @Builder
    public UserGetRequestDto(String team_id, String botUserId) {
        this.team_id = team_id;
        this.botUserId = botUserId;
    }
}
