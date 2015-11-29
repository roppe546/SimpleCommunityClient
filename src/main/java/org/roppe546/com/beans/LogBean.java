package org.roppe546.com.beans;

import org.roppe546.com.SessionBean;
import org.roppe546.com.models.CreateUser;
import org.roppe546.com.viewmodels.LogViewModel;
import org.roppe546.com.viewmodels.SubmitNewLogViewModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peonsson on 11/11/15.
 */
@ManagedBean(name="Log")
@SessionScoped
public class LogBean {

    private String subject;
    private String message;
    private int userId;
    private List<LogViewModel> logs = new ArrayList<LogViewModel>();

    public LogBean() {
        super();
    }

    public void submit() {
        Client client = ClientBuilder.newClient();

//        int userId = (Integer) SessionBean.getSession().getAttribute("userId");
//      TODO: MAKE DYNAMIC USER_ID
        int USER_ID = 4;
        SubmitNewLogViewModel newLog = new SubmitNewLogViewModel(USER_ID, "Hello from Emma!", "Hello World!");

        Response reponse = client.target("http://130.237.84.200:8080/community/webapi/logs")
                .request()
                .post(Entity.json(newLog));
    }

    public void setLogs(List<LogViewModel> logs) {

        Client client = ClientBuilder.newClient();

        List list = client.target("http://130.237.84.200:8080/community/webapi/logs")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<LogViewModel>>() { });

        this.logs = list;
    }

    public List<LogViewModel> getLogs() {
        return logs;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
