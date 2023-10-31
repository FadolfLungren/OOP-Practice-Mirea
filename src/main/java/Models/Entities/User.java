package Models.Entities;

public class User {
    private int id;
    private String userName;
    private String login;
    private String password;
    private int accessLvl;


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

    public int getAccessLvl(){return accessLvl;}

    public void setAccessLvl(int accessLvl){this.accessLvl = accessLvl;}
    @Override
    public String toString() {
        return "{" +
                "\"id\":" + "\"" + id + "\"" +","+
                "\"accessLvl\":" + "\"" + accessLvl + "\"" +","+
                "\"userName\":" + "\"" + userName +"\"" +","+
                "\"login\":" + "\"" + login + "\"" +","+
                "\"password\":" + "\"" + password  + "\"" +
                "}";
    }
}
