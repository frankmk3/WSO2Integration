package com.wso2integration.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * {
 * "emails": [
 * {
 * "type": "work",
 * "value": "kim_j@wso2.com"
 * },
 * {
 * "type": "home",
 * "value": "kim.jackson@gmail.com"
 * }
 * ],
 * "meta": {
 * "created": "2020-11-15T21:18:49.089337Z",
 * "location": "https://localhost:9443/scim2/Users/5497e796-9f00-465b-9e5f-df6c9c505b56",
 * "lastModified": "2020-11-15T21:18:49.089337Z",
 * "resourceType": "User"
 * },
 * "schemas": [
 * "urn:ietf:params:scim:schemas:core:2.0:User",
 * "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User"
 * ],
 * "roles": [
 * {
 * "type": "default",
 * "value": "Internal/everyone"
 * }
 * ],
 * "name": {
 * "givenName": "kim",
 * "familyName": "jackson"
 * },
 * "id": "5497e796-9f00-465b-9e5f-df6c9c505b56",
 * "userName": "kim2"
 * }
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class WS02UserListResponse extends WS02User {

    private long totalResults;

    private int startIndex;

    private int itemsPerPage;

    private List<String> schemas;

    @JsonProperty("Resources")
    private List<WS02UserResponse> resources;

}
