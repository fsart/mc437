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

    public Patrimony() {
    }
}
