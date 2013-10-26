package com.patrimony.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Property;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(value="patrimonies", noClassnameStored=true)
public class Patrimony {

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
/*
    public Patrimony(String id, String description, String department, String mark, String model, String serialNumber, StringacquisitionDate, String closingDate, String value, String process, String document, String building, String floor, String complement, String situation) {
		this.id = id;
		this.description = description;
		this.department = department;
		this.mark = mark;
		this.model = model;
		this.serialNumber = serialNumber;
		this.acquisitionDate = acquisitionDate;
		this.closingDate = closingDate;
		this.value = value;
		this.process = process;
		this.document = document;
		this.building = building;
		this.floor = floor;
		this.complement = complement;
		this.situation = situation;
    }

    public Patrimony(String[] patrimony) {
		this.id = patrimony[0];
		this.description = patrimony[1];
		this.department = patrimony[2];
		this.mark = patrimony[3];
		this.model = patrimony[4];
		this.serialNumber = patrimony[5];
		this.acquisitionDate = patrimony[6];
		this.closingDate = patrimony[7];
		this.value = patrimony[8];
		this.process = patrimony[9];
		this.document = patrimony[10];
		this.building = patrimony[11];
		this.floor = patrimony[12];
		this.complement = patrimony[13];
		this.situation = patrimony[14];
    }
    */
}

