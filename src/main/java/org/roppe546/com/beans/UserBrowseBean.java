package org.roppe546.com.beans;

import org.roppe546.com.viewmodels.UserViewModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by robin on 13/11/15.
 */
@ManagedBean(name="UserBrowse")
@SessionScoped
public class UserBrowseBean {

    private List<UserViewModel> users;

    public UserBrowseBean() {
    }

    public List<UserViewModel> getUsers() {
//
//        Client client = ClientBuilder.newClient();
//
//        List list = client.target("http://130.237.84.200:8080/community/webapi/users")
//                .request(MediaType.APPLICATION_JSON)
//                .get(new GenericType<List<UserViewModel>>() { });

        return this.users;
    }

    public void setUsers(List<UserViewModel> users) {

        Client client = ClientBuilder.newClient();

        List list = client.target("http://130.237.84.200:8080/community/webapi/users")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<UserViewModel>>() { });

        this.users = list;
    }
}

