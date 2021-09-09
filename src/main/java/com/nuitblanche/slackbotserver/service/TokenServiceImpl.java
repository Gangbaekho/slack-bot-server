package com.nuitblanche.slackbotserver.service;

import com.nuitblanche.slackbotserver.component.SlackRestTemplate;
import com.nuitblanche.slackbotserver.domain.Token;
import com.nuitblanche.slackbotserver.domain.TokenRepository;
import com.nuitblanche.slackbotserver.dto.TokenSaveRequestDto;
import com.nuitblanche.slackbotserver.response.CommonResult;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService{

    @Value("${slack.clientId}")
    private String clientId;

    @Value(("${slack.clientSecret}"))
    private String clientSecret;

    @Value("${slack.baseurl}")
    private String baseUrl;

    private final TokenRepository tokenRepository;
    private final SlackRestTemplate restTemplate;

    public List<Token> findAllTokens(){

        return tokenRepository.findAll();
    }

    @Transactional
    @Override
    public Token saveToken(TokenSaveRequestDto requestDto) {

        String requestUrl = baseUrl + "/oauth.v2.access";

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("client_id", clientId);
        parameters.add("client_secret",clientSecret);
        parameters.add("code",requestDto.getCode());

        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-type", "application/x-www-form-urlencoded");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> formEntity = new HttpEntity<>(parameters, headers);

        ResponseEntity<Map> responseEntity = restTemplate
                .exchange(requestUrl,HttpMethod.POST, formEntity, Map.class);

        String stringToken = (String) responseEntity.getBody().get("access_token");
        String botUserId = (String) responseEntity.getBody().get("bot_user_id");

        if(Objects.nonNull(stringToken) && Objects.nonNull(botUserId)){
            Token token = Token.builder()
                    .token(stringToken)
                    .botUserId(botUserId)
                    .build();

            tokenRepository.save(token);
            return token;
        }

        return null;
    }

    @Override
    public Token findByBotUserId(String botUserId) {

        return tokenRepository.findByBotUserId(botUserId);
    }
}
