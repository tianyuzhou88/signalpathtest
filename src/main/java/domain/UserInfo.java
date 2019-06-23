package domain;

public class UserInfo {
    private String twittername;
    private String password;
    private String username;

    public UserInfo(String twittername, String password, String username) {
        this.twittername = twittername;
        this.password = password;
        this.username = username;
    }

    public String getTwittername() {
        return twittername;
    }


    public String getPassword() {
        return password;
    }


    public String getUsername() { return username; }

}
