package com.hillpark.hillpark.server.Response.person;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "ADate")
public class PersonAdate {
    @Element(name = "string", required = false)
    public String date = "";

    public String getDate(){
        return date;
    }
}
