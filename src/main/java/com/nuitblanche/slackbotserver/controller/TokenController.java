package com.nuitblanche.slackbotserver.controller;

import com.nuitblanche.slackbotserver.dto.TokenSaveRequestDto;
import com.nuitblanche.slackbotserver.response.CommonResult;
import com.nuitblanche.slackbotserver.service.ResponseService;
import com.nuitblanche.slackbotserver.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenController {

    private final ResponseService responseService;
    private final TokenService tokenService;

    @PostMapping("/api/v1/tokens")
    public CommonResult saveToken(@RequestBody TokenSaveRequestDto requestDto){

        tokenService.saveToken(requestDto);
        return responseService.getSuccessResult();
    }
}
