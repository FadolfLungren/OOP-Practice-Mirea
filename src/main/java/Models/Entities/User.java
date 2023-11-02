package Models.Entities;

public class User {
    private int id;
    private String userName;
    private String login;
    private String password;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "{" +
                "\"id\":" + "\"" + id + "\"" +","+
                "\"userName\":" + "\"" + userName +"\"" +","+
                "\"login\":" + "\"" + login + "\"" +","+
                "\"password\":" + "\"" + password  + "\"" +
                "}";
    }

    public enum ROLE{
        USER, ADMIN, UNKNOWN
    }
}
