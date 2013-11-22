package com.patrimony.services;

import javax.ws.rs.core.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;
import com.sun.jersey.api.JResponse;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;

import com.patrimony.DB;
import com.patrimony.models.User;

@Path("/users")
public class UserService {

    @GET
    @Produces("application/json")
    public User signin(@QueryParam("username") String username, @QueryParam("password") String password) {
        Query<User> query = DB.getDatastore().createQuery(User.class).field("username").equal(username).field("password").equal(password);

        return query.get();
    }

    @POST
    @Produces("application/json")
    public JResponse signup(User user) {
        DB.getDatastore().save(user);

        return JResponse.ok().status(200).build();
    }
}
