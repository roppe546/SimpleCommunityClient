package org.roppe546.com.beans;

import org.roppe546.com.viewmodels.CreateUserViewModel;
import org.roppe546.com.viewmodels.LogViewModel;
import org.roppe546.com.viewmodels.UserViewModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://130.237.84.200:8080/community/webapi/users/")
                .queryParam("id", id);

        UserViewModel user = target.request(MediaType.APPLICATION_JSON)
                .get(UserViewModel.class);

        this.username = user.getUsername();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.country = user.getCountry();
        this.city = user.getCity();

        WebTarget logsTarget = client.target("http://130.237.84.200:8080/community/webapi/logs")
                .queryParam("id", id);

        LogViewModel logs = logsTarget.request(MediaType.APPLICATION_JSON)
                .get(LogViewModel.class);

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