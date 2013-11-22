package com.patrimony.services;

import java.util.List;

import javax.ws.rs.core.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;
import com.sun.jersey.api.JResponse;
import com.sun.jersey.multipart.FormDataParam;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;

import com.patrimony.DB;
import com.patrimony.models.PlaceChange;
import com.patrimony.models.Patrimony;

@Path("place-change")
public class PlaceChangeService {

    @GET
    @Produces("application/json")
    public List<PlaceChange> list() {
        Query<PlaceChange> query = DB.getDatastore().createQuery(PlaceChange.class);

        return query.asList();
    }

    @PUT
    @Path("/{id}/confirm")
    public JResponse confirm(@PathParam("id") String id) {
        PlaceChange placeChange = DB.getDatastore().createQuery(PlaceChange.class).field("_id").equal(id).get();
        Patrimony patrimony     = DB.getDatastore().createQuery(Patrimony.class).field("_id").equal(placeChange.patrimonyId).get();

        patrimony.building   = placeChange.building;
        patrimony.floor      = placeChange.floor;
        patrimony.complement = placeChange.complement;
        DB.getDatastore().save(patrimony);

        placeChange.status   = "confirmed";
        DB.getDatastore().save(placeChange);

        return JResponse.ok().status(200).build();
    }

    @PUT
    @Path("/{id}/cancel")
    public JResponse cancel(@PathParam("id") String id) {
        PlaceChange placeChange = DB.getDatastore().createQuery(PlaceChange.class).field("_id").equal(id).get();

        placeChange.status   = "cancel";
        DB.getDatastore().save(placeChange);

        return JResponse.ok().status(200).build();
    }

    @POST
    @Consumes("application/json")
    public JResponse create(PlaceChange placeChange) {
        placeChange.status = "pending";
        DB.getDatastore().save(placeChange);

        return JResponse.ok().status(200).build();
    }
}
