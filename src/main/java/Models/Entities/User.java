package Models.Entities;

public class User {
    private int id;
    private String userName;
    private String login;
    private String password;
    private ROLE role;

    public ROLE getRole() {
        return role;
    }

    public void setRole(String role) {
        if(role.equals("UNKNOWN")){
            this.role = ROLE.UNKNOWN;
        }
        if(role.equals("ADMIN")){
            this.role = ROLE.ADMIN;
        }
        if(role.equals("USER")){
            this.role = ROLE.USER;
        }
    }


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
                "\"role\":" + "\"" + role + "\"" +","+
                "\"userName\":" + "\"" + userName +"\"" +","+
                "\"login\":" + "\"" + login + "\"" +","+
                "\"password\":" + "\"" + password  + "\"" +
                "}";
    }

    public enum ROLE{
        USER("user"),
        ADMIN("admin"),
        UNKNOWN("unknown");

        private final String accesLvl;

        ROLE(String accesLvl) {
            this.accesLvl = accesLvl;
        }

        public String getAccesLvl() {
            return accesLvl;
        }
    }
}
