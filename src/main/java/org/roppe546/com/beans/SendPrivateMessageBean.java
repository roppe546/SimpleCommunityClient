package org.roppe546.com.beans;

import org.roppe546.com.viewmodels.PrivateMessageViewModel;
import org.roppe546.com.viewmodels.SubmitNewLogViewModel;
import org.roppe546.com.viewmodels.SubmitNewPrivateMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Peonsson on 19/11/15.
 */

@ManagedBean(name="SendPrivateMessage")
@RequestScoped
public class SendPrivateMessageBean {

    private String subject;
    private String message;
    private String sender;
    private String receiver;

    public SendPrivateMessageBean() {
    }

    public String submit(String sender, String receiver) {

        Client client = ClientBuilder.newClient();

        PrivateMessageViewModel newMessage = new PrivateMessageViewModel(sender, receiver, this.message, this.subject);

        System.out.println("sender: " + sender);
        System.out.println("receiver: " + receiver);
        System.out.println("mess: " + this.message);
        System.out.println("subj: " + this.subject);

        Response response = client.target("http://130.237.84.200:8080/community/webapi/PrivateMessages")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(newMessage));

        return "profile.xhtml";
    }

    public String sendPm(String toUserId) {
        this.receiver = toUserId;
        return "sendpm.xhtml";
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

}
