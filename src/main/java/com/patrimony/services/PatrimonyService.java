package com.patrimony.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.bson.types.ObjectId;

import com.sun.jersey.api.JResponse;


import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import com.patrimony.models.Patrimony;

@Path("patrimonies")
@Consumes("application/json")
public class PatrimonyService {

    @GET
    @Produces("application/json")
    public Patrimony list() {
        try {
            Datastore ds = new Morphia().createDatastore(new MongoClient(new MongoClientURI(System.getenv("MONGOHQ_URI"))), System.getenv("DB_NAME"));
            Query<Patrimony> query = ds.createQuery(Patrimony.class);

            return query.get();
        } catch (UnknownHostException uh) {
            uh.printStackTrace();
        }
    }
}
