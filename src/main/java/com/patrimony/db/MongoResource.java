package com.patrimony.db;

import java.net.UnknownHostException;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public enum MongoResource {
    public Datastore getDatastore() {
        try {
            MongoClient client = new MongoClient(new MongoClientURI(System.getenv("MONGOHQ_URI")));
            return new Morphia().createDatastore(client);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
