package com.nuitblanche.slackbotserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuitblanche.slackbotserver.component.SlackRestTemplate;
import com.nuitblanche.slackbotserver.dto.*;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.stereotype.Service;

import java.awt.image.RescaleOp;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class SlackBotServiceImpl implements SlackBotService{

    private final SlackRestTemplate slackRestTemplate;

    @Override
    public Map<String, Object> openChannelWithUsers(ChannelOpenRequestDto requestDto) {

        String requestUrl = "/conversations.open";

        Map<String,Object> params = convertObjectToMap(requestDto);

        return slackRestTemplate.postRequest(requestUrl, params);
    }

    @Override
    public List<WorkSpaceResponseDto> getAllWorkSpaces(){

        String requestUrl = "/auth.teams.list";

        Map<String, Object> result = slackRestTemplate.getRequest(requestUrl);
        ObjectMapper mapper = new ObjectMapper();
        List<WorkSpaceResponseDto> workSpaces = mapper.convertValue(result.get("teams"),List.class);

        return workSpaces;
    }

    @Override
    public List<UserResponseDto> getAllUsersInWorkSpace(UserGetRequestDto requestDto){

        String requestUrl = "/users.list";

        Map<String,Object> params = convertObjectToMap(requestDto);
        Map<String, Object> result = slackRestTemplate.getRequestWithParameters(requestUrl,params);

        ObjectMapper mapper = new ObjectMapper();
        List<UserResponseDto> users = mapper.convertValue(result.get("members"),List.class);

        return users;
    }

    @Override
    public Map<String, Object> sendMessageToChannel(MessageSendRequestDto requestDto) {

        String requestUrl = "/chat.postMessage";

        Map<String,Object> params = convertObjectToMap(requestDto);

        return slackRestTemplate.postRequest(requestUrl,params);
    }

    private Map<String,Object> convertObjectToMap(Object object){

        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> params = mapper.convertValue(object,Map.class);

        return params;
    }
}
