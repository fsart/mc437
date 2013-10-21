package com.patrimony.models;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Patrimony {

    private final int year;
    private final int month;
    private final int day;
    private final int hour;
    private final int minute;
    private final int second;

    public Patrimony() {

    }

    public int getTest1() {
        return 1;
    }

    public int getTest2() {
        return 2;
    }
}
