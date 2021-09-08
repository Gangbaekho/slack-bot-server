package com.nuitblanche.slackbotserver.service;

import java.util.Map;

public interface SlackBotService {

    Map<String,Object> sendMessageToWorkSpaces(String test);

    Map<String,Object> getAllWorkSpaces();

    Map<String,Object> getSingleWorkSpace(String workspace);
}
