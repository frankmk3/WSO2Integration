package com.wso2integration.demo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


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
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class WS02UserResponse extends WS02User {

    private String id;

    private List<String> schemas;

    private Map<String, String> meta;

    @Builder(builderMethodName = "childBuilder")
    public WS02UserResponse(
            WS02UserName name,
            String userName,
            String password, List<String> emails,
            String id,
            List<String> schemas,
            Map<String, String> meta) {
        super(name, userName, password, emails);
        this.id = id;
        this.schemas = schemas;
        this.meta = meta;
    }

    public WS02UserResponse(String id) {
        this.id = id;
    }
}
