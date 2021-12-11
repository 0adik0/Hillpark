package com.hillpark.hillpark.server.Response.balance;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "Structure")
public class BalanceResponseStructure {
    @ElementList(name = "string", inline = true)
    public List<String> string = new ArrayList();
    @ElementList(name = "Value", inline = true)
    public List<BalanceValue> balanceValue = new ArrayList();

    public List<String> getString(){
        return string;
    }

    public List<BalanceValue> getBalanceValue(){
        return balanceValue;
    }
}
