package com.hillpark.hillpark.server.Response.balance;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "Array")
public class BalanceResponseArray {
    @ElementList(name = "Value", inline = true)
    public List<BalanceResponseValue> cardValueList = new ArrayList();

    public List<BalanceResponseValue> getBalanceValueList(){
        return cardValueList;
    }
}
