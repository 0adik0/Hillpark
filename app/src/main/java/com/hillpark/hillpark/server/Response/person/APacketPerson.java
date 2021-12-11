package com.hillpark.hillpark.server.Response.person;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

public class APacketPerson {
    @ElementList(name = "string", required = false, inline = true)
    public List<String> stringList;
    @Element(name = "int", required = false)
    public String intList;
    @ElementList(name = "Value", inline = true, required = false)
    public List<PersonResponseValue> valueList;

    public List<String> getStringList(){
        return stringList;
    }

    public String getIntList(){
        return intList;
    }

    public List<PersonResponseValue> getValueList(){
        return valueList;
    }
}
