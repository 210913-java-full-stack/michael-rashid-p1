package models;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column
    private String fName;

    @Column
    private String lName;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Ticket> ticketList = new LinkedList<>();

    public User() {
    }

    public User(int user_id,String fName, String lName, String username, String password, String role) {
        this.user_id = user_id;
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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}