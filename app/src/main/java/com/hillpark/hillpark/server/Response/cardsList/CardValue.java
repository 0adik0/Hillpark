package com.hillpark.hillpark.server.Response.cardsList;

import com.hillpark.hillpark.server.Response.person.PersonResponseStructure;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Value")
public class CardValue {
    @Element(name = "Structure")
    public PersonResponseStructure structure = new PersonResponseStructure();

    public PersonResponseStructure getStructure(){
        return structure;
    }
}
