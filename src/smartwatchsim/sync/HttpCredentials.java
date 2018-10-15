package smartwatchsim.sync;

public class HttpCredentials {
        private String username;
        private String password;

        public HttpCredentials(String username, String password) {
            this.username = username;
            this.password = password;
        }
        
        public String getAuth() {
            String userPassword = username + ":" + password;
            String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
            return "Basic " + encoding;
        }
    }