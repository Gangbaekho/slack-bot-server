package com.nuitblanche.slackbotserver.service;

import com.nuitblanche.slackbotserver.domain.Token;
import com.nuitblanche.slackbotserver.dto.TokenSaveRequestDto;
import com.nuitblanche.slackbotserver.response.CommonResult;

import java.util.List;

public interface TokenService {

    List<Token> findAllTokens();

    Token saveToken(TokenSaveRequestDto requestDto);

    Token findByBotUserId(String botUserId);
}
