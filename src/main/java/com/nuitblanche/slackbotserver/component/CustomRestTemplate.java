package com.nuitblanche.slackbotserver.component;

import antlr.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
public class CustomRestTemplate extends RestTemplate {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomRestTemplate.class);

    @Value("${slack.apiUrl}")
    private String apiUrl;

    public Map<String, Object> request(String requestUri, Map<String, Object> params) {
        LOGGER.warn("{}", params);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl + requestUri);
        Map<String, Object> apiResult = postForObject(uriComponentsBuilder.build().toUriString(), params, Map.class);

        return null;
//        if (StringUtils.equals(apiResult.get("errCd").toString(), "200")) {
//            return (Map<String, Object>) apiResult.get("result");
//        } else {
//            LOGGER.error("requestUri : {}", requestUri);
////            LOGGER.error("params : {}", new Gson().toJson(params));
//            LOGGER.error("{}", apiResult.get("errCd"));
//            LOGGER.error("{}", apiResult.get("errMsg"));
//            return null;
//        }
    }

}
