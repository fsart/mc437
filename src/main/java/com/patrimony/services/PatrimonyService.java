package com.patrimony.services;

import java.util.List;

import javax.ws.rs.core.*;
import javax.ws.rs.*;
import javax.ws.rs.Consumes;

import com.sun.jersey.api.JResponse;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;

import com.patrimony.DB;
import com.patrimony.models.Patrimony;

@Path("patrimonies")
public class PatrimonyService {

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public List<Patrimony> list() {
        Query<Patrimony> query = DB.getDatastore().createQuery(Patrimony.class);

        return query.asList();
    }

    @POST
    public Response upload(@FormParam("file") String file) {
        return Response.status(200).build();
    }
}
