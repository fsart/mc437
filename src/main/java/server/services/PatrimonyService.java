package server.services;

import server.models.Patrimony;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/patrimonies")
@Produces(MediaType.APPLICATION_JSON)
public class PatrimonyService {

    @GET
    public Patrimony list () {
        return new Patrimony();
    }

    @GET
    @Path("{id}")
    public Patrimony details (@PathParam("id") String id) {
        return new Patrimony();
    }

}

