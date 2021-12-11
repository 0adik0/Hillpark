package com.hillpark.hillpark.server.Response.person;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "ul")
public class PersonUl {
    @Element(name = "int64_t", required = false)
    public Long int64;

    public Long getInt64(){
        return int64;
    }
}
