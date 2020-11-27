package com.wso2integration.demo.service;

import com.wso2integration.demo.dto.WS02User;
import com.wso2integration.demo.dto.WS02UserListResponse;
import com.wso2integration.demo.dto.WS02UserResponse;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Slf4j
public class WS02Service extends BaseRestService {

    @Autowired
    @Setter
    private RestTemplate restTemplate;

    @Value("${security.creation.url}")
    private String securityCreationUrl;

    public static HttpEntity<Object> getHttpEntityFromRequest(WS02User ws02User, Map<String, String> customHeader) {
        HttpHeaders headers = new HttpHeaders();
        customHeader.keySet().forEach(key -> headers.set(key, customHeader.get(key)));
        return new HttpEntity<>(ws02User, headers);
    }

    public WS02UserResponse register(WS02User ws02User) {
        WS02UserListResponse users = getUsers(ws02User.getUserName());
        if (users.getTotalResults() == 0) {
            ResponseEntity<WS02UserResponse> response = executeRequest(ws02User);
            return response.getBody();
        } else {
            return users.getResources().stream().findFirst().orElseGet(null);
        }
    }

    public WS02UserListResponse getUsers(String userName) {
        return getUsersByUserName(userName).getBody();
    }

    private ResponseEntity<WS02UserResponse> executeRequest(WS02User ws02User) {
        Map<String, String> customHeader = getRequestSecureHeader();
        HttpEntity<Object> entity = getHttpEntityFromRequest(ws02User, customHeader);
        return restTemplate.exchange(securityCreationUrl, HttpMethod.POST, entity, WS02UserResponse.class);
    }

    private ResponseEntity<WS02UserListResponse> getUsersByUserName(String userName) {
        Map<String, String> customHeader = getRequestSecureHeader();
        HttpEntity<Object> entity = getHttpEntityFromRequest(null, customHeader);
        return restTemplate.exchange(String.format("%s?filter=userName+eq+%s", securityCreationUrl, userName), HttpMethod.GET, entity, WS02UserListResponse.class, customHeader);
    }

}
