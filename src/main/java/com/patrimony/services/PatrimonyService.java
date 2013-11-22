package com.patrimony.services;

import java.util.List;

import javax.ws.rs.core.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;
import com.sun.jersey.api.JResponse;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;

import com.patrimony.DB;
import com.patrimony.models.Patrimony;

@Path("/patrimonies")
public class PatrimonyService {

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Patrimony details(@PathParam("id") String id) {
        Query<Patrimony> query = DB.getDatastore().createQuery(Patrimony.class).field("_id").equal(id);

        return query.get();
    }

    @GET
    @Produces("application/json")
    public List<Patrimony> list() {
        Query<Patrimony> query = DB.getDatastore().createQuery(Patrimony.class);

        return query.asList();
    }

    /*
    @POST
    @Path("patrimonies")
    @Consumes("multipart/form-data")
    public List<String> upload(File file) throws Exception {
        boolean hasConflict = false;
        ArrayList<String> conflicts = new ArrayList<String>();
        Xlsx xlsx = new Xlsx();
        xlsx.read(file);
        return conflicts;
    }
    */
}
