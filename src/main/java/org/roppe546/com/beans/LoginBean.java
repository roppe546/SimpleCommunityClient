package org.roppe546.com.beans;

import org.roppe546.com.models.CreateUser;
import org.roppe546.com.viewmodels.LoginViewModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Created by robin on 13/11/15.
 */
@ManagedBean(name="Login")
@SessionScoped
public class LoginBean {
    private String username;
    private String password;
    private boolean loggedIn;

    public LoginBean() {
        super();
        loggedIn = false;
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

    public boolean isLoggedIn() {
//        HttpSession session = SessionBean.getSession();

//        if (session != null) {
//            System.out.println("session");
//            String user = (String) session.getAttribute("username");
//            if (user != null) {
//                System.out.println("user");
//                System.out.println("LoginBean: found session");
//                System.out.println("LoginBean: session username = " + session.getAttribute("username"));
//                return true;
//            }
//        }

//        System.out.println("LoginBean: didn't find session");
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String login() {

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target("http://130.237.84.200:8080/community/webapi/users/login");
        LoginViewModel loginUser = new LoginViewModel(this.username, this.password);
        System.out.println(loginUser);

        Response postResponse = target.request().post(Entity.json(loginUser));

        if(postResponse.getStatus() != 200) {
            System.err.println("login getStatus: " + postResponse.getStatus());
            return "index.xhtml";
        }

        setLoggedIn(true);
        return "timeline.xhtml";
//        if (userId > -1) {
////            HttpSession session = SessionBean.getSession();
////            session.setAttribute("userId", userId);
////            session.setAttribute("username", username);
//        }
//        return "index.xhtml";
    }

    public String logout() {
//        HttpSession session = SessionBean.getSession();
//        session.invalidate();
        this.loggedIn = false;
        return "login";
    }
}
