package com.patrimony.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Property;

import org.bson.types.ObjectId;

import com.patrimony.DB;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(value="users", noClassnameStored=true)
public class User {
    @Id
    public String _id = new ObjectId().toString();

    @Property("username")
    @XmlElement(name = "username")
    public String username;

    @Property("password")
    @XmlElement(name = "password")
    public String password;

    @Property("type")
    @XmlElement(name = "type")
    public String type;

    public User () {
    
    }
}

