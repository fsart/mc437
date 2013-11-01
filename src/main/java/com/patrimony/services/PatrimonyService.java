package com.patrimony.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ws.rs.core.*;
import javax.ws.rs.*;
import javax.ws.rs.Consumes;

import java.net.UnknownHostException;
import com.sun.jersey.api.JResponse;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import com.patrimony.models.Patrimony;

@Path("patrimonies")
public class PatrimonyService {

    private Datastore getDatastore() {
        try {
            MongoClientURI uri = new MongoClientURI("mongodb://heroku:XKYi77uAz_oMJkqnS4aTQVtLTkQYg7f1i6lXMabz8LJF4eCH3xgjQukwukoaIuLWCDoa1x3VG7N6BawRfA0BoA@paulo.mongohq.com:10079/app18759691");
            MongoClient client = new MongoClient(uri);
            return new Morphia().createDatastore(client, "app18759691");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public List<Patrimony> list() {
        Query<Patrimony> query = getDatastore().createQuery(Patrimony.class);

        return query.asList();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response upload(@FormParam("file") String file) {
        System.out.println(file);
        return Response.status(200).build();
    }
}
