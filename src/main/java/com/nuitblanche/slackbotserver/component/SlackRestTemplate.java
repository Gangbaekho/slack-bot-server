package com.nuitblanche.slackbotserver.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component
public class SlackRestTemplate extends RestTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlackRestTemplate.class);

    @Value("${slack.baseurl}")
    private String slackBaseUrl;

//    @Value("${slack.token}")
//    private String slackToken;

    public Map<String, Object> getRequest(String requestUri, String slackToken) {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(slackBaseUrl + requestUri);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","Bearer " + slackToken);
        headers.set("Content-type", "application/json; charset=utf-8");
        HttpEntity<Object> requestEntity = new HttpEntity<>(null,headers);

        ResponseEntity<Map> responseEntity = exchange(uriComponentsBuilder.build().toUriString(), HttpMethod.GET, requestEntity, Map.class);

        Map<String,Object> body = responseEntity.getBody();

        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            LOGGER.error("requestUri : {}", requestUri);
//            throw new IllegalArgumentException("잘못된 요청입니다.");
        }
        return body;
    }

    public Map<String, Object> postRequest(String requestUri, Map<String,Object> params, String slackToken) {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(slackBaseUrl + requestUri);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","Bearer " + slackToken);
        headers.set("Content-Type", "application/json; charset=utf-8");
        HttpEntity<Map> requestEntity = new HttpEntity<>(params,headers);

        ResponseEntity<Map> responseEntity = exchange(uriComponentsBuilder.build().toUriString(), HttpMethod.POST, requestEntity, Map.class);

        Map<String,Object> body = responseEntity.getBody();

        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return body;
        } else {
            LOGGER.error("requestUri : {}", requestUri);
            LOGGER.error("{}", body.get("ok"));
            LOGGER.error("{}", body.get("error"));

            throw new IllegalArgumentException("잘못된 요청입니다.");
        }
    }

    public Map<String, Object> getRequestWithParameters(String requestUri, Map<String,Object> params, String slackToken) {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(slackBaseUrl + requestUri);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","Bearer " + slackToken);
        headers.set("Content-Type", "application/json; charset=utf-8");

        HttpEntity<Map> requestEntity = new HttpEntity<>(params,headers);

        ResponseEntity<Map> responseEntity = exchange(uriComponentsBuilder.build().toUriString(), HttpMethod.GET, requestEntity, Map.class);

        Map<String,Object> body = responseEntity.getBody();

        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return body;
        } else {
            LOGGER.error("requestUri : {}", requestUri);
            LOGGER.error("{}", body.get("ok"));
            LOGGER.error("{}", body.get("error"));

            throw new IllegalArgumentException("잘못된 요청입니다.");
        }
    }
}
