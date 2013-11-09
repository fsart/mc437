package com.patrimony.models;

import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Property;

import org.bson.types.ObjectId;

import com.patrimony.DB;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(value="place_changes", noClassnameStored=true)
public class PlaceChange {

    @XmlElement(name = "_id")
    public ObjectId _id;

    @XmlElement(name = "patrimonyId")
    public String patrimonyId;

    @Property("building")
    @XmlElement(name = "building")
    public String building;

    @Property("floor")
    @XmlElement(name = "floor")
    public String floor;

    @Property("complement")
    @XmlElement(name = "complement")
    public String complement;

    public PlaceChange () {

    }
}

