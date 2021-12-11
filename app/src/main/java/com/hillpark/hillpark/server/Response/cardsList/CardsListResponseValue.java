package com.hillpark.hillpark.server.Response.cardsList;

import com.hillpark.hillpark.server.Response.person.PersonResponseStructure;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Value")
public class CardsListResponseValue {
    @Element(name = "string", required = false)
    public String structureString = "";
    @Element(name = "Structure", required = false)
    public PersonResponseStructure structure = new PersonResponseStructure();
    @Element(name = "Array", required = false)
    public CardsListResponseArray array = new CardsListResponseArray();

    public PersonResponseStructure getStructure(){
        return structure;
    }
    public CardsListResponseArray getArray(){
        return array;
    }
}

