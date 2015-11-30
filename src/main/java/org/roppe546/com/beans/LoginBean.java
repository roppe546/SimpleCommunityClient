package org.roppe546.com.beans;

import org.eclipse.persistence.sessions.Login;
import org.roppe546.com.viewmodels.LoginViewModel;
import org.roppe546.com.viewmodels.ReturnCodeViewModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;

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
        HttpSession session = SessionBean.getSession();

        if (session != null) {
            String user = (String) session.getAttribute("username");

            if (user != null) {
                return true;
            }
        }

        // Not logged in
        try {
            // Check if not on login page, if true, redirect to login page
            FacesContext fc = FacesContext.getCurrentInstance();
            String viewId = fc.getViewRoot().getViewId();

            if (!viewId.equals("/login.xhtml") && !viewId.equals("/register.xhtml")) {
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect(ec.getRequestContextPath() + "/login.xhtml");
            }
        }
        catch (IOException e) {
            System.err.println("Couldn't redirect to login page.");
            e.printStackTrace();
        }

        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String login() {

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target("http://130.237.84.200:8080/community/webapi/users/login");
        LoginViewModel loginUser = new LoginViewModel(this.username, this.password);

        Response postResponse = target.request().post(Entity.json(loginUser));

        int userId = Integer.parseInt(postResponse.readEntity(ReturnCodeViewModel.class).toString());

        if (userId > 0) {
            // Set attributes in session
            HttpSession session = SessionBean.getSession();
            session.setAttribute("userId", userId);
            session.setAttribute("username", username);

            setLoggedIn(true);
            return "timeline";
        }
        else {
            setLoggedIn(false);
            return "index";
        }
    }

    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        this.loggedIn = false;
        return "login";
    }
}
