package com.patrimony.services;

import java.util.List;

import javax.ws.rs.core.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;
import com.sun.jersey.api.JResponse;
import com.sun.jersey.multipart.FormDataParam;

import org.bson.types.ObjectId;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;

import com.patrimony.DB;
import com.patrimony.models.PlaceChange;

@Path("placechange")
public class PlaceChangeService {

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public List<PlaceChange> list() {
        Query<PlaceChange> query = DB.getDatastore().createQuery(PlaceChange.class);

        return query.asList();
    }

    @POST
    @Consumes("application/json")
    public JResponse upload(
        @FormDataParam("patrimonyId") ObjectId patrimonyId,
        @FormDataParam("building") String building,
        @FormDataParam("floor") String floor,
        @FormDataParam("complement") String complement
    ) throws Exception {
        PlaceChange placeChange = new PlaceChange();
        placeChange.patrimonyId = patrimonyId;
        placeChange.building = building;
        placeChange.floor = floor;
        placeChange.complement = complement;
        DB.getDatastore().save(placeChange);

        return JResponse.ok().status(200).build();
    }
}
