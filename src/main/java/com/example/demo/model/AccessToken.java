package com.example.demo.model;

import lombok.Data;
import lombok.ToString;

/**
 * GitHub AccessToken
 *
 * @author SimonX
 */
@Data
@ToString
public class AccessToken {

    private String access_token;
    private String token_type;
    private String scope;

}
