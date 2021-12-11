package com.hillpark.hillpark.server.Response.balance;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Time")
public class BalanceTime {
    @Element(name = "string", required = false)
    public String timeString = "";

    public String getTimeString(){
        return timeString;
    }
}
