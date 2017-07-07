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
 * {
 * "message": "success",
 * "response": {
 * "authToken": "$2a$10$gYMneGMbXCqnmvPlN6J5juWsJ4vkRNxozojcVkQFmxfrvq3B5SLPy595f68b91d31b03c9b26f459"
 * }
 * }
 */
