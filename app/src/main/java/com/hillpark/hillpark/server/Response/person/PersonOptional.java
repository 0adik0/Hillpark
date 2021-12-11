package com.hillpark.hillpark.server.Response.person;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "optional")
public class PersonOptional {
    @Element(name = "string", required = false)
    public String name = "";
    @Element(name = "int", required = false)
    public String int_def = "";
    @Element(name = "int64_t", required = false)
    public String int64 = "";

    public String getName(){
        return name;
    }
}
