package com.hillpark.hillpark.server.Response.person;

import com.hillpark.hillpark.server.Response.cardsList.CardsListResponseValue;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

public class APacketCardsList {
    @ElementList(name = "string", required = false, inline = true)
    public List<String> stringList = new ArrayList();
    @Element(name = "int", required = false)
    public String intList = "";
    @ElementList(name = "Value", inline = true, required = false)
    public List<CardsListResponseValue> valueList = new ArrayList();

    public List<String> getStringList(){
        return stringList;
    }

    public String getIntList(){
        return intList;
    }

    public List<CardsListResponseValue> getValueList(){
        return valueList;
    }
}
