package com.hillpark.hillpark.server.Response.balance;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Value")
public class BalanceResponseValue {
    @Element(name = "string", required = false)
    public String structureString = "";
    @Element(name = "Structure", required = false)
    public BalanceResponseStructure structure = new BalanceResponseStructure();
    @Element(name = "Array", required = false)
    public BalanceResponseArray array = new BalanceResponseArray();

    public BalanceResponseStructure getStructure(){
        return structure;
    }

    public BalanceResponseArray getArray(){
        return array;
    }
}