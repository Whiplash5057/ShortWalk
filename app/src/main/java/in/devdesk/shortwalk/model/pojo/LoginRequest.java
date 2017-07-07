package in.devdesk.shortwalk.model.pojo;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by richardandrews on 07/07/17.
 */

public class LoginRequest implements Serializable{
    @Expose
    private String username;
    @Expose
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }



    public String getPassword() {
        return password;
    }

}

/**
 {
 "message": "success",
 "status": "success",
 "response": {
 "authToken": "$2a$10$X0CrBk6UHy8w8jfvkMkur.jA3LhJTA2Ggpqh30FyWnQRqL0PGVVYO595f9e9daff25a49e35f8987"
 }
 }
 */
