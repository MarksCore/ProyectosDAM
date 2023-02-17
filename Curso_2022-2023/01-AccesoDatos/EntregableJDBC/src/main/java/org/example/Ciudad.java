package org.example;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class Ciudad implements Serializable {
    private String name;
    private String code;

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

    @Override
    public String toString() {
        return "    Ciudad{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
