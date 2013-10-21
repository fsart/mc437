package com.patrimony.services;

import com.patrimony.models.Patrimony;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/patrimonies")
@Produces(MediaType.APPLICATION_JSON)
public class TimeService {

    @GET
    public Patrimony get() {
        return new Patrimony();
    }

}

