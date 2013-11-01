package com.patrimony;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class DB {

    public static Datastore getDatastore() {
        try {
            MongoClientURI uri = new MongoClientURI("mongodb://heroku:XKYi77uAz_oMJkqnS4aTQVtLTkQYg7f1i6lXMabz8LJF4eCH3xgjQukwukoaIuLWCDoa1x3VG7N6BawRfA0BoA@paulo.mongohq.com:10079/app18759691");
            MongoClient client = new MongoClient(uri);
            return new Morphia().createDatastore(client, "app18759691");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
