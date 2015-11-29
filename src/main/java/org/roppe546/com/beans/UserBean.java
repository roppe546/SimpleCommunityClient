package org.roppe546.com.beans;

import org.roppe546.com.models.CreateUser;
import org.roppe546.com.viewmodels.UserViewModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by robin on 9/11/15.
 */
@ManagedBean(name = "User")
@SessionScoped
public class UserBean {
    private int userId;
    private String email;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String country;
    private String city;

    public UserBean() {
        super();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void register() {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://130.237.84.200:8080/community/webapi/users/register");

        CreateUser newUser = new CreateUser(this.email, this.password, this.username, this.firstname, this.lastname, this.country, this.city);

        System.out.println(newUser);

        Response postResponse = target.request().post(Entity.json(newUser));

        if(postResponse.getStatus() != 201) {
            System.err.println("register status: " + postResponse.getStatus());
        }

        System.out.println(postResponse.toString());

    }
}
