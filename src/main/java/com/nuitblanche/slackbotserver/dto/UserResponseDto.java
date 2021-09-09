package com.nuitblanche.slackbotserver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
public class UserResponseDto {

    private String id;
    private String team_id;
    private String name;
    private boolean is_bot;

    @Builder
    public UserResponseDto(String id, String team_id, String name, boolean is_bot) {
        this.id = id;
        this.team_id = team_id;
        this.name = name;
        this.is_bot = is_bot;
    }

    public boolean getIs_bot(){
        return this.is_bot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponseDto that = (UserResponseDto) o;
        return is_bot == that.is_bot &&
                Objects.equals(id, that.id) &&
                Objects.equals(team_id, that.team_id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, team_id, name, is_bot);
    }
}
