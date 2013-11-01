package com.patrimony.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.MediaType;

import org.bson.types.ObjectId;

import com.sun.jersey.api.JResponse;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

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
import com.patrimony.utils.*;

@Path("patrimonies")
@Consumes("application/json")
public class PatrimonyService {

    private Datastore getDatastore() {
        try {
            MongoClientURI uri = new MongoClientURI("mongodb://heroku:16eb1d52483d3cf12a776bd8785af2c5@paulo.mongohq.com:10079/app18759691");
            MongoClient client = new MongoClient(uri);
            return new Morphia().createDatastore(client, "app18759691");
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

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@FormDataParam("file") InputStream uploadedInputStream, @FormDataParam("file") FormDataContentDisposition fileDetail) {
        File tempFile = Xlsx.createTempFile(uploadedInputStream);

        Xlsx x = new Xlsx();

		x.parse(tempFile);

		for(String key : x.sheets().keySet()) {
		    String[][] mat;
			mat = x.sheets().get(key);

			for(int i = 1; i < mat.length; i++) {
			    Patrimony patrimony = new Patrimony(mat[i]);
			    getDatastore().save(patrimony);
			}
		}

        return Response.status(200).build();
    }
}
