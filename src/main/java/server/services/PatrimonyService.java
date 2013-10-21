package server.services;

import server.models.Patrimony;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
public class PatrimonyService {

    @GET
    @Path("/patrimonies")
    public Patrimony list() {
        return new Patrimony();
    }

    @GET
    @Path("/patrimonies/:id")
    public Patrimony list() {
        return new Patrimony();
    }

    @PUT
    @Path("/patrimonies/:id")
    public Patrimony list() {
        return new Patrimony();
    }

    @POST
    @Path("/patrimonies")
    public Patrimony import() {
        return null;
    }

}

