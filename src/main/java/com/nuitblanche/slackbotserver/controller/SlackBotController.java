package com.nuitblanche.slackbotserver.controller;

import com.nuitblanche.slackbotserver.service.SlackBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class SlackBotController {

    private final SlackBotService slackBotService;

    @PostMapping("/api/v1/messages")
    public Map<String,Object> sendMessageToWorkSpaces(){

        return null;
    }

    @GetMapping("/api/v1/workspaces")
    public Map<String,Object> getWorkSpaces(){

        return slackBotService.getAllWorkSpaces();
    }

}
