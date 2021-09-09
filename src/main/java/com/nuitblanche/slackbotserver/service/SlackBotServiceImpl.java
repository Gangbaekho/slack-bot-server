package com.nuitblanche.slackbotserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuitblanche.slackbotserver.component.SlackRestTemplate;
import com.nuitblanche.slackbotserver.domain.Token;
import com.nuitblanche.slackbotserver.dto.*;
import com.nuitblanche.slackbotserver.response.CommonResult;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.nuitblanche.slackbotserver.util.ConvertObjectUtils.*;

@RequiredArgsConstructor
@Service
public class SlackBotServiceImpl implements SlackBotService{

    private final ResponseService responseService;
    private final TokenService tokenService;
    private final SlackRestTemplate slackRestTemplate;


    @Override
    public Map<String, Object> openChannelWithUsers(SingleOpenChannelRequestDto requestDto) {

        String requestUrl = "/conversations.open";

        Map<String,Object> params = convertObjectToMap(requestDto);

        return slackRestTemplate.postRequest(requestUrl, params,"test");
    }

    @Override
    public List<WorkSpaceResponseDto> getAllWorkSpaces(){

        String requestUrl = "/auth.teams.list";
        List<Token> tokens = tokenService.findAllTokens();
        ObjectMapper mapper = new ObjectMapper();
        List<WorkSpaceResponseDto> workSpaces = new ArrayList<>();

        for(Token token : tokens){
            Map<String, Object> result = slackRestTemplate.getRequest(requestUrl,token.getToken());
            List<Map<String,Object>> teams = mapper.convertValue(result.get("teams"),List.class);

            WorkSpaceResponseDto workSpace = WorkSpaceResponseDto.builder()
                    .id((String)teams.get(0).get("id"))
                    .name((String) teams.get(0).get("name"))
                    .botUserId(token.getBotUserId())
                    .build();

            workSpaces.add(workSpace);
        }
//        Map<String, Object> result = slackRestTemplate.getRequest(requestUrl);

//        List<WorkSpaceResponseDto> workSpaces = mapper.convertValue(result.get("teams"),List.class);

        return workSpaces;
    }

    @Override
    public List<UserResponseDto> getAllUsersInWorkSpace(UserGetRequestDto requestDto){

        Token token = tokenService.findByBotUserId(requestDto.getBotUserId());

        String requestUrl = "/users.list";

        Map<String,Object> params = convertObjectToMap(requestDto);
        Map<String, Object> result = slackRestTemplate.getRequestWithParameters(requestUrl,params,token.getToken());

        ObjectMapper mapper = new ObjectMapper();
        List<UserResponseDto> users = mapper.convertValue(result.get("members"),List.class);

        return users;
    }


    @Override
    public Map<String, Object> singleOpenChannelAndSendMessage(SingleOpenChannelRequestDto requestDto) {

        String channelOpenRequestUrl = "/conversations.open";
        Map<String,Object> channelOpenRequestParams = convertSingleOpenChannelRequestToParams(requestDto);

        Token token = tokenService.findByBotUserId(requestDto.getBotUserId());

        Map<String,Object> channelOpenResponse = slackRestTemplate.postRequest(channelOpenRequestUrl, channelOpenRequestParams,token.getToken());
        ObjectMapper mapper = new ObjectMapper();
        ChannelOpenResponseDto channelOpenResponseDto = mapper.convertValue(channelOpenResponse.get("channel"),ChannelOpenResponseDto.class);

        String sendMessageRequestUrl = "/chat.postMessage";

        Map<String,Object> sendMessageRequestParams = new HashMap<>();
        sendMessageRequestParams.put("channel",channelOpenResponseDto.getId());
        sendMessageRequestParams.put("text",requestDto.getText());

        return slackRestTemplate.postRequest(sendMessageRequestUrl,sendMessageRequestParams,token.getToken());
    }

    @Override
    public CommonResult multipleOpenChannelsAndSendMessages(MultipleOpenChannelRequestDto requestDto) {

        String channelOpenRequestUrl = "/conversations.open";
        String sendMessageRequestUrl = "/chat.postMessage";

        Token token = tokenService.findByBotUserId(requestDto.getBotUserId());

        List<SingleOpenChannelRequestDto> requestDtos = new ArrayList<>();
        for(String userId : requestDto.getUsers()){
            requestDtos.add(new SingleOpenChannelRequestDto(userId,requestDto.getText(), requestDto.getBotUserId()));
        }

        for(SingleOpenChannelRequestDto dto : requestDtos){

            Map<String,Object> channelOpenRequestParams = convertSingleOpenChannelRequestToParams(dto);

            Map<String,Object> channelOpenResponse = slackRestTemplate.postRequest(channelOpenRequestUrl, channelOpenRequestParams, token.getToken());
            ObjectMapper mapper = new ObjectMapper();
            ChannelOpenResponseDto channelOpenResponseDto = mapper.convertValue(channelOpenResponse.get("channel"),ChannelOpenResponseDto.class);

            Map<String,Object> sendMessageRequestParams = new HashMap<>();
            sendMessageRequestParams.put("channel",channelOpenResponseDto.getId());
            sendMessageRequestParams.put("text",requestDto.getText());

            slackRestTemplate.postRequest(sendMessageRequestUrl,sendMessageRequestParams,token.getToken());
        }

        return responseService.getSuccessResult();
    }

}
