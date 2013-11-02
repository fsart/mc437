package com.patrimony.models;

import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Property;

import com.patrimony.DB;

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

    public Patrimony (String[] patrimony) {
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

    public ArrayList<String> conflicts () {
        ArrayList<String> conflicts = new ArrayList<String>();
        Patrimony patrimony = DB.getDatastore().createQuery(Patrimony.class).field("id").equal(this.id).get();

        if (patrimony == null) {
            conflicts.add("O item " + this.id + " não existe");
        } else {
            if (patrimony.description != this.description) conflicts.add("O item" + this.id + "possui descrição diferente do valor no banco de dados");
            if (patrimony.department != this.department) conflicts.add("O item" + this.id + "possui departamento diferente do valor no banco de dados");
            if (patrimony.mark != this.mark) conflicts.add("O item" + this.id + "possui marca diferente do valor no banco de dados");
            if (patrimony.model != this.model) conflicts.add("O item" + this.id + "possui modelo diferente do valor no banco de dados");
            if (patrimony.serialNumber != this.serialNumber) conflicts.add("O item" + this.id + "possui numero serial diferente do valor no banco de dados");
            if (patrimony.acquisitionDate != this.acquisitionDate) conflicts.add("O item" + this.id + "possui data de aquisição diferente do valor no banco de dados");
            if (patrimony.closingDate != this.closingDate) conflicts.add("O item" + this.id + "possui data de fechamento diferente do valor no banco de dados");
            if (patrimony.value != this.value) conflicts.add("O item" + this.id + "possui valor diferente do valor no banco de dados");
            if (patrimony.process != this.process) conflicts.add("O item" + this.id + "possui processo diferente do valor no banco de dados");
            if (patrimony.document != this.document) conflicts.add("O item" + this.id + "possui documento diferente do valor no banco de dados");
            if (patrimony.building != this.building) conflicts.add("O item" + this.id + "possui prédio diferente do valor no banco de dados");
            if (patrimony.floor != this.floor) conflicts.add("O item" + this.id + "possui andar diferente do valor no banco de dados");
            if (patrimony.complement != this.complement) conflicts.add("O item" + this.id + "possui complemento diferente do valor no banco de dados");
            if (patrimony.situation != this.situation) conflicts.add("O item" + this.id + "possui situação diferente do valor no banco de dados");
        }

        return conflicts;
    }
}

