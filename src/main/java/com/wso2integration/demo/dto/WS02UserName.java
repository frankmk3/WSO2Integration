package com.wso2integration.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * "name":{
 * "familyName":"jackson",
 * "givenName":"kim"
 * }
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WS02UserName {
    private String familyName;
    private String givenName;
}
