package com.company.insta.instaapp;

/**
 * Created by LENOVO on 4/29/2019.
 */

public class User {
    int id;
    String email, username, image;

    public User(int id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
