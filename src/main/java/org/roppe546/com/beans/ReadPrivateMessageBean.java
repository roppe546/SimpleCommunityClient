package org.roppe546.com.beans;

import org.roppe546.com.viewmodels.LogViewModel;
import org.roppe546.com.viewmodels.PrivateMessageViewModel;

import javax.faces.bean.ManagedBean;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by robin on 19/11/15.
 */
@ManagedBean(name = "ReadPrivateMessage")
public class ReadPrivateMessageBean {

    public List<PrivateMessageViewModel> messages;

    public ReadPrivateMessageBean() {
        super();
    }

    public List<PrivateMessageViewModel> getMessages() {

        //TODO DYNAMIC USER_ID
        String USER_ID = "/4";

        Client client = ClientBuilder.newClient();

        List list = client.target("http://130.237.84.200:8080/community/webapi/PrivateMessages")
                //TODO DYNAMIC USER_ID
                .path(USER_ID)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<PrivateMessageViewModel>>() { });

        return list;
    }

    public void setMessages(List<PrivateMessageViewModel> messages) {

        //TODO DYNAMIC USER_ID
        String USER_ID = "/4";

        Client client = ClientBuilder.newClient();

        List list = client.target("http://130.237.84.200:8080/community/webapi/privatemessages")
                //TODO DYNAMIC USER_ID
                .path(USER_ID)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<PrivateMessageViewModel>>() { });

        this.messages = list;
    }
}