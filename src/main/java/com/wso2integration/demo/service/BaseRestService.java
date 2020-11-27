package com.wso2integration.demo.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BaseRestService {

    @Autowired
    @Setter
    protected RestTemplate restTemplate;

    @Value("${security.verification.token.clientId}")
    protected String clientId;

    @Value("${security.verification.token.clientSecret}")
    protected String clientSecret;

    public static HttpEntity<Object> getHttpEntityFromRequest(Object entity, Map<String, String> customHeader) {
        HttpHeaders headers = new HttpHeaders();
        customHeader.keySet().forEach(key -> headers.set(key, customHeader.get(key)));
        return new HttpEntity<>(entity, headers);
    }

    protected Map<String, String> getRequestHeader() {
        Map<String, String> customHeader = new HashMap<>();
        customHeader.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        customHeader.put(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return customHeader;
    }

    protected Map<String, String> getRequestSecureHeader() {
        Map<String, String> customHeader = getRequestHeader();
        customHeader.put(HttpHeaders.AUTHORIZATION, getAuthorizationHeader(clientId, clientSecret));
        return customHeader;
    }

    protected String getAuthorizationHeader(String clientId, String clientSecret) {
        if (clientId == null || clientSecret == null) {
            log.warn("Null Client ID or Client Secret detected. Endpoint that requires authentication will reject request with 401 error.");
        }

        String creds = String.format("%s:%s", clientId, clientSecret);

        return "Basic " + new String(Base64.getEncoder().encode(creds.getBytes(StandardCharsets.UTF_8)));
    }
}
