package com.doyatama.university.payload;

public class UserSummary {
    private String id;
    private String username;
    private String name;
    private String roles;
    private String description;

    private String avatar;

    public UserSummary(String id, String username, String name, String roles, String description, String avatar) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.roles = roles;
        this.description = description;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
