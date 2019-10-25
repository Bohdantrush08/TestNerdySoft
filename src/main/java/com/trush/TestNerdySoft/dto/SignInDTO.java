package com.trush.TestNerdySoft.dto;


public class SignInDTO {
    private static final long serialVersionUID = 5926468583005150707L;
    private String username;
    private String password;


    // need default constructor for JSON Parsing
    public SignInDTO() {
    }

    public SignInDTO(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
