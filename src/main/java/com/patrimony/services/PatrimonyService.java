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

import java.net.UnknownHostException;
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

import com.patrimony.models.Patrimony;

@Path("patrimonies")
@Consumes("application/json")
public class PatrimonyService {

    private Datastore getDatastore() {
        try {
            MongoClient client = new MongoClient(new MongoClientURI(System.getenv("MONGOHQ_URI")));
            return new Morphia().createDatastore(client, 'app18759691');
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GET
    @Produces("application/json")
    public List<Patrimony> list() {
        Query<Patrimony> query = getDatastore().createQuery(Patrimony.class);

        return query.asList();
    }
}
