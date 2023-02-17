package org.example;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.ArrayList;

public class Region implements Serializable {
    private String name;
    private String code;
    private ArrayList<Estado> states;


    public String getName() {
        return name;
    }

    @XmlAttribute(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    @XmlAttribute(name = "Code")
    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Estado> getStates() {
        return states;
    }

    @XmlElement(name = "State")
    public void setStates(ArrayList<Estado> states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "Region{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
