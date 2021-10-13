package models;

public class User {
    private String fName;
    private String lName;
    private String username;
    private String password;
    private String role;

    public User(String fName, String lName, String username, String password, String role) {
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}
}