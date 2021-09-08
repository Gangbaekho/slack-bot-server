package com.nuitblanche.slackbotserver.service;

import com.nuitblanche.slackbotserver.component.CustomRestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class SlackBotServiceImpl implements SlackBotService{

    private final CustomRestTemplate restTemplate;

    @Override
    public Map<String, Object> sendMessageToWorkSpaces(String test) {

        String postUrl = "/api/v1/projects";

        HttpHeaders postHeaders = new HttpHeaders();
        postHeaders.setContentType(MediaType.APPLICATION_JSON);
        postHeaders.set("Authorization","Bearer "+ "token");
        HttpEntity<Map<String,Object>> postRequestEntity = new HttpEntity<>(null,postHeaders);

        ResponseEntity<Map> postResponseEntityOne = restTemplate.exchange(postUrl, HttpMethod.POST,postRequestEntity,Map.class);

        return null;
    }

    @Override
    public Map<String,Object> getAllWorkSpaces(){

        return null;
    }

    @Override
    public Map<String,Object> getSingleWorkSpace(String workspace){

        return null;
    }
}
