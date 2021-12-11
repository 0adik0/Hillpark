package com.hillpark.hillpark.server.Response.person;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "decimal")
public class StructDecimal {
    @Element(name = "string", required = false)
    public String decimalValue = "";

    public String getDecimalValue(){
        return decimalValue;
    }
}