package com.patrimony.models;

import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@XmlRootElement
@Entity(value="patrimonies", noClassnameStored=true)
public class Patrimony {

    @Id
    @XmlElement(name = "id")
    public ObjectId id;

    @Property("description")
    @XmlElement(name = "description")
    public String description;

    public Patrimony() {
    }
}
