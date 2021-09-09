package com.nuitblanche.slackbotserver.service;

import com.nuitblanche.slackbotserver.dto.*;
import com.nuitblanche.slackbotserver.response.CommonResult;

import java.util.List;
import java.util.Map;

public interface SlackBotService {

    Map<String,Object> openChannelWithUsers(SingleOpenChannelRequestDto requestDto);

    List<WorkSpaceResponseDto> getAllWorkSpaces();

    List<UserResponseDto> getAllUsersInWorkSpace(UserGetRequestDto requestDto);

    Map<String,Object> singleOpenChannelAndSendMessage(SingleOpenChannelRequestDto requestDto);

    CommonResult multipleOpenChannelsAndSendMessages(MultipleOpenChannelRequestDto requestDto);

}
