package com.nuitblanche.slackbotserver.controller;

import com.nuitblanche.slackbotserver.dto.*;
import com.nuitblanche.slackbotserver.response.CommonResult;
import com.nuitblanche.slackbotserver.response.ListResult;
import com.nuitblanche.slackbotserver.service.ResponseService;
import com.nuitblanche.slackbotserver.service.SlackBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class SlackBotController {

    private final ResponseService responseService;
    private final SlackBotService slackBotService;

    @PostMapping("/api/v1/channels")
    public Map<String,Object> openChannelWithUsers(@RequestBody SingleOpenChannelRequestDto requestDto){

        return slackBotService.openChannelWithUsers(requestDto);
    }

    @GetMapping("/api/v1/workspaces")
    public ListResult<WorkSpaceResponseDto> getWorkSpaces(){

        return responseService.getListReulst(slackBotService.getAllWorkSpaces());
    }

    @PostMapping("/api/v1/users")
    public ListResult<UserResponseDto> getAllUsersInWorkSpace(@RequestBody UserGetRequestDto requestDto){

        return responseService.getListReulst(slackBotService.getAllUsersInWorkSpace(requestDto));
    }

    @PostMapping("/api/v1/messages/single")
    public Map<String,Object> singleChannelOpenAndSendMessage(@RequestBody SingleOpenChannelRequestDto requestDto){

        return slackBotService.singleOpenChannelAndSendMessage(requestDto);
    }

    @PostMapping("/api/v1/messages/multiple")
    public CommonResult multipleChannelsOpenAndSendMessages(@RequestBody MultipleOpenChannelRequestDto requestDto){

        return slackBotService.multipleOpenChannelsAndSendMessages(requestDto);
    }
}
