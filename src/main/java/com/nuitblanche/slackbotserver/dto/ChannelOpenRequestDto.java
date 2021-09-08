package com.nuitblanche.slackbotserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ChannelOpenRequestDto {

    private List<String> users;
}
