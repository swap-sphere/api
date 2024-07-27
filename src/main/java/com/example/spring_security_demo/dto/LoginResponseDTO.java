package com.example.spring_security_demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponseDTO {
    @JsonProperty
    private String access_token;
    @JsonProperty
    private AccountDetails accountDetails;
    public LoginResponseDTO(String access_token,AccountDetails accountDetails) {
        this.access_token = access_token;
        this.accountDetails = accountDetails;
    }
    public static class AccountDetails{
        @JsonProperty
        private String username;
        @JsonProperty
        private String name;

        public AccountDetails(String username, String name) {
            this.username = username;
            this.name = name;
        }
    }
}


