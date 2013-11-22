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
@Entity(value="place_changes", noClassnameStored=true)
public class PlaceChange {

    @Id
    public String _id = new ObjectId().toString();

    @XmlElement(name = "patrimonyId")
    public String patrimonyId;

    @Property("user")
    @XmlElement(name = "user")
    public String user;

    @Property("oldBuilding")
    @XmlElement(name = "oldBuilding")
    public String oldBuilding;

    @Property("oldFloor")
    @XmlElement(name = "oldFloor")
    public String oldFloor;

    @Property("oldComplement")
    @XmlElement(name = "oldComplement")
    public String oldComplement;

    @Property("building")
    @XmlElement(name = "building")
    public String building;

    @Property("floor")
    @XmlElement(name = "floor")
    public String floor;

    @Property("complement")
    @XmlElement(name = "complement")
    public String complement;

    @Property("status")
    @XmlElement(name = "status")
    public String status;

    public PlaceChange () {

    }
}

