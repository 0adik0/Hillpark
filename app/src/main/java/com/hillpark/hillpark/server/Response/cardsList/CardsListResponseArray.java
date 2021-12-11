package com.hillpark.hillpark.server.Response.cardsList;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "Array")
public class CardsListResponseArray {
    @ElementList(name = "Value", inline = true)
    public List<CardValue> cardValueList = new ArrayList();

    public List<CardValue> getCardValueList(){
        return cardValueList;
    }
}
