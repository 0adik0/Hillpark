package com.hillpark.hillpark.server.Response.person;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "Structure")
public class PersonResponseStructure {
    @ElementList(name = "string", inline = true)
    public List<String> name = new ArrayList();
    @ElementList(name = "Value", inline = true)
    public List<PersonValue> personValue = new ArrayList();

    public List<String> getName(){
        return name;
    }

    public List<PersonValue> getPersonValue(){
        return personValue;
    }
}
