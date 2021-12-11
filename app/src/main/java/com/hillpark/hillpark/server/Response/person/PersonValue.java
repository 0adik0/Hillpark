package com.hillpark.hillpark.server.Response.person;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Value")
public class PersonValue {

    @Element(name = "ul", required = false)
    public PersonUl personUl = new PersonUl();

    @Element(name = "optional", required = false)
    public PersonOptional personOptional = new PersonOptional();

    @Element(name = "ADate", required = false)
    public PersonAdate personAdate = new PersonAdate();

    @Element(name = "decimal", required = false)
    public StructDecimal decimal = new StructDecimal();

    @Element(name = "string", required = false)
    public String element = "";

    @Element(name = "none", required = false)
    public String none = "";

    @Element(name = "int64_t", required = false)
    public String int64_t = "";

    @Element(name = "Time", required = false)
    public String time = "";

    public PersonUl getPersonUl(){
        return personUl;
    }

    public PersonOptional getPersonOptional(){
        return personOptional;
    }

    public PersonAdate getPersonAdate(){
        return personAdate;
    }

    public String getElement(){
        return element;
    }

    public String getNone(){
        return none;
    }

    public String getTime(){
        return time;
    }
}
