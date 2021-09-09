package com.nuitblanche.slackbotserver.dto;

import com.nuitblanche.slackbotserver.domain.Token;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenSaveRequestDto {

    private String code;

    @Builder
    public TokenSaveRequestDto(String code) {
        this.code = code;
    }
}
