package com.wso2integration.demo.controller;

import com.wso2integration.demo.dto.Response;
import com.wso2integration.demo.dto.WS02User;
import com.wso2integration.demo.service.WS02Service;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final WS02Service ws02Services;

    @Autowired
    public UserController(WS02Service ws02Services) {
        this.ws02Services = ws02Services;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody WS02User ws02User) {
        try {
            return new ResponseEntity<>(ws02Services.register(ws02User), HttpStatus.CREATED);
        } catch (RestClientException e) {
            return new ResponseEntity<>(
                    Response.builder()
                            .message(e.getMessage())
                            .status(HttpStatus.BAD_REQUEST.value())
                            .build()
                    , HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/check")
    public ResponseEntity<?> checkToken() {
        return new ResponseEntity<>(
                Response.builder()
                        .message("The token in valid")
                        .status(HttpStatus.OK.value())
                        .build()
                , HttpStatus.OK);
    }

}
