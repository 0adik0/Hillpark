package com.hillpark.hillpark.server.Response.balance;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "Array")
public class BalanceArray {
    @ElementList(name = "Value", required = false, inline = true)
    public List<BalanceValue> balanceValues = new ArrayList<BalanceValue>();

    public List<BalanceValue> getBalanceValues() {
        return balanceValues;
    }
}
