package org.roppe546.com.beans;

import org.roppe546.com.viewmodels.LogViewModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * Created by Peonsson on 13/11/15.
 */
@ManagedBean(name = "UserProfile")
@SessionScoped
public class UserProfileBean {

    private String id;
    private String username;
    private String firstname;
    private String lastname;
    private String country;
    private String city;
    private List<LogViewModel> logs;

    public String getProfile(String id) {

        this.id = id;
        System.out.println("user id = " + id);

//        User user = UserHandler.getUser(Integer.parseInt(id));

//        this.username = user.getUsername();
//        this.firstname = user.getFirstname();
//        this.lastname = user.getLastname();
//        this.country = user.getCountry();
//        this.city = user.getCity();

//        logs = LogHandler.fetchLogs(Integer.parseInt(id));

        return "profile.xhtml";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<LogViewModel> getLogs() {
        return logs;
    }

    public void setLogs(List<LogViewModel> logs) {
        this.logs = logs;
    }
}