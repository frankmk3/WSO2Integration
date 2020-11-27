package com.wso2integration.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * "roles": [
 * {
 * "type": "default",
 * "value": "Internal/everyone"
 * }
 * ]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WS02UserRole {

    private String value;

    private String type;
}
