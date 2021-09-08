package com.nuitblanche.slackbotserver.service;

import com.nuitblanche.slackbotserver.component.SlackRestTemplate;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class SlackBotServiceImpl implements SlackBotService{

    private final SlackRestTemplate slackRestTemplate;

    @Override
    public Map<String, Object> sendMessageToWorkSpaces(String test) {

        String requestUrl = "/chat.postMessage";

        return slackRestTemplate.getRequest(requestUrl);
    }

    @Override
    public Map<String,Object> getAllWorkSpaces(){

        String requestUrl = "/conversations.list";

        return slackRestTemplate.getRequest(requestUrl);
    }

    @Override
    public Map<String,Object> getSingleWorkSpace(String workspace){

        return null;
    }
}
