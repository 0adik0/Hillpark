package com.hillpark.hillpark.server.Response.person;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Value")
public class PersonResponseValue {
    @Element(name = "string", required = false)
    public String structureString = "";
    @Element(name = "Structure", required = false)
    public PersonResponseStructure structure;

    public PersonResponseStructure getStructure(){
        return structure;
    }
}
