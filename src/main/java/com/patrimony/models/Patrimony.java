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
@Entity(value="patrimonies", noClassnameStored=true)
public class Patrimony {

    @Id
    public String _id = new ObjectId().toString();

    @Property("id")
    @XmlElement(name = "id")
    public String id;

    @Property("description")
    @XmlElement(name = "description")
    public String description;

    @Property("department")
    @XmlElement(name = "department")
    public String department;

    @Property("mark")
    @XmlElement(name = "mark")
    public String mark;

    @Property("model")
    @XmlElement(name = "model")
    public String model;

    @Property("serialNumber")
    @XmlElement(name = "serialNumber")
    public String serialNumber;

    @Property("acquisitionDate")
    @XmlElement(name = "acquisitionDate")
    public String acquisitionDate;

    @Property("closingDate")
    @XmlElement(name = "closingDate")
    public String closingDate;

    @Property("value")
    @XmlElement(name = "value")
    public String value;

    @Property("process")
    @XmlElement(name = "process")
    public String process;

    @Property("document")
    @XmlElement(name = "document")
    public String document;

    @Property("building")
    @XmlElement(name = "building")
    public String building;

    @Property("floor")
    @XmlElement(name = "floor")
    public String floor;

    @Property("complement")
    @XmlElement(name = "complement")
    public String complement;

    @Property("situation")
    @XmlElement(name = "situation")
    public String situation;

    public Patrimony () {

    }
}

