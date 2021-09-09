package com.nuitblanche.slackbotserver.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuitblanche.slackbotserver.dto.MultipleOpenChannelRequestDto;
import com.nuitblanche.slackbotserver.dto.SingleOpenChannelRequestDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertObjectUtils {

    public static Map<String,Object> convertObjectToMap(Object object){

        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> params = mapper.convertValue(object,Map.class);

        return params;
    }

    public static Map<String,Object> convertSingleOpenChannelRequestToParams(SingleOpenChannelRequestDto requestDto){

        Map<String,Object> params = new HashMap<>();
        params.put("users",requestDto.getUsers());

        return params;
    }

    public static List<Map<String,Object>> convertMultipleOpenChannelRequesttoParams(MultipleOpenChannelRequestDto requestDto){

        List<Map<String,Object>> list = new ArrayList<>();
        for(String userId : requestDto.getUsers()){
            Map<String,Object> param = new HashMap<>();
            param.put("users",userId);

            list.add(param);
        }

        return list;
    }
}
