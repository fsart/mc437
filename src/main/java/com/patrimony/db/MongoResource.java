package com.mobjoy.gameverse.db;

import java.net.UnknownHostException;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public enum MongoResource {
    INSTANCE;
    private MongoClient mongoClient;
    private Datastore ds;
    private DB db;

    private MongoResource() {
        try {
            if (mongoClient == null) {
                mongoClient = getClient();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Nullable
    private MongoClient getClient() {
        try {
            return new MongoClient(new MongoClientURI(System.getenv("MONGOHQ_URI")));
        } catch (UnknownHostException uh) {
            uh.printStackTrace();
        }
        return null;
    }

    //@Nullable
    //public Datastore getDatastore(@NotNull String dbName) {
    public Datastore getDatastore() {
        if(ds!=null) {
            return ds;
        }

        ds = new Morphia().createDatastore(mongoClient, System.getenv("DB_NAME"));
        return ds;
    }
}
