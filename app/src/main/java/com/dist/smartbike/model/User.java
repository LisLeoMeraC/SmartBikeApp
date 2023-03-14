package com.dist.smartbike.model;

public class User {
    private String names;
    private String last_names;
    private String email;
    private String code_session;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLast_names() {
        return last_names;
    }

    public void setLast_names(String last_names) {
        this.last_names = last_names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode_session() {
        return code_session;
    }

    public void setCode_session(String code_session) {
        this.code_session = code_session;
    }
}
