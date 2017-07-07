package in.devdesk.shortwalk.model.pojo;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by richardandrews on 07/07/17.
 */

public class Login implements Serializable {

    /**
     * {
     * "message": "success",
     * "response": {
     * "authToken": "$2a$10$gYMneGMbXCqnmvPlN6J5juWsJ4vkRNxozojcVkQFmxfrvq3B5SLPy595f68b91d31b03c9b26f459"
     * }
     * }
     */
    public class LoginResponse{

        @Expose
        private String message;
        @Expose
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }



        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


    public class LoginRequest{
        @Expose
        private String username;
        @Expose
        private String email;
        @Expose
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }




}
