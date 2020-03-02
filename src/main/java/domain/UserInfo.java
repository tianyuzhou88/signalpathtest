package domain;

public class UserInfo {
    private String password;
    private String username;

    public UserInfo(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }


    public String getUsername() { return username; }

}
