package com.nuitblanche.slackbotserver.service;

import com.nuitblanche.slackbotserver.dto.*;

import java.util.List;
import java.util.Map;

public interface SlackBotService {

    Map<String,Object> openChannelWithUsers(ChannelOpenRequestDto requestDto);

    List<WorkSpaceResponseDto> getAllWorkSpaces();

    List<UserResponseDto> getAllUsersInWorkSpace(UserGetRequestDto requestDto);

    Map<String,Object> sendMessageToChannel(MessageSendRequestDto requestDto);
}
