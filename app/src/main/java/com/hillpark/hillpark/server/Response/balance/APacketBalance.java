package com.hillpark.hillpark.server.Response.balance;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

public class APacketBalance {
    @ElementList(name = "string", required = false, inline = true)
    public List<String> stringList;
    @Element(name = "int", required = false)
    public String intList = "";
    @ElementList(name = "Value", inline = true, required = false)
    public List<BalanceResponseValue> valueList;
    @Element(name = "list", required = false)
    public BalanceList list;

    public List<String> getStringList(){
        return stringList;
    }

    public String getIntList(){
        return intList;
    }

    public List<BalanceResponseValue> getValueList(){
        return valueList;
    }
}
