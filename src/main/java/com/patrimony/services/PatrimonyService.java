package com.mobjoy.gameverse.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;
import com.mobjoy.gameverse.db.MongoResource;
import com.mobjoy.gameverse.model.game.normalizedTf.GameNormalizedTFs;
import com.mobjoy.gameverse.model.game.similarities.GameSimilarities;
import com.sun.jersey.api.JResponse;

@Path("/users")
@Consumes("application/json")
public class UserResources {

    @GET
    @Path("/patrimonies")
    @Produces("application/json")
    public Patrimony list() {
        return new Patrimony
    }
}
