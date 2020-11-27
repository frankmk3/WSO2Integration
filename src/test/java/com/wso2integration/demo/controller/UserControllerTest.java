package com.wso2integration.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wso2integration.demo.dto.WS02User;
import com.wso2integration.demo.dto.WS02UserResponse;
import com.wso2integration.demo.service.WS02Service;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.opaqueToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    public static final WS02User USER = WS02User.builder()
            .userName("test_name")
            .build();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private WS02Service ws02Service;

    @Test
    @DisplayName("Create user endpoint should return data")
    public void createdUserEndpointShouldReturnData() throws Exception {
        long id = new Random().nextLong();
        Mockito.when(ws02Service.getUsers(USER.getUserName())).thenReturn(null);
        Mockito.when(ws02Service.register(USER)).thenReturn(
                WS02UserResponse.childBuilder()
                        .id(String.valueOf(id))
                        .meta(new HashMap<>())
                        .schemas(new ArrayList<>())
                        .build()
        );

        this.mockMvc.perform(
                post("/user")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER))
        ).andExpect(
                status().isCreated()
        )
                .andExpect(
                        content().string(containsString("userName"))
                );
    }

    @Test
    @DisplayName("Check user endpoint should throw Unauthorized if the authentication is not present")
    public void shouldCheckFailIfTokenAbsent() throws Exception {
        this.mockMvc.perform(
                post("/user/check")
        ).andExpect(
                status().isForbidden()
        );
    }

    @Test
    @DisplayName("Check user endpoint should return OK if the authentication is present")
    public void shouldCheckUserValidateToken() throws Exception {
        this.mockMvc.perform(
                post("/user/check")
                        .with(opaqueToken())
        )
                .andExpect(
                        status().isOk()
                );

    }

    @Test
    @DisplayName("Check user endpoint should be Unauthorized if the authentication is not valid")
    public void shouldFailCheckValidateToken() throws Exception {
        this.mockMvc.perform(
                post("/user/check")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer 7a8937bf-8c54-3d3f-970c-892dbbd45466")
        )
                .andExpect(
                        status().isUnauthorized()
                );

    }

}