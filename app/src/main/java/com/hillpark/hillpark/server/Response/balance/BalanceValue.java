package com.hillpark.hillpark.server.Response.balance;

import com.hillpark.hillpark.server.Response.person.PersonAdate;
import com.hillpark.hillpark.server.Response.person.PersonOptional;
import com.hillpark.hillpark.server.Response.person.PersonUl;
import com.hillpark.hillpark.server.Response.person.StructDecimal;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Value")
public class BalanceValue {

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

    @Element(name = "Time", required = false)
    public BalanceTime time = new BalanceTime();

    @Element(name = "int64_t", required = false)
    public String int64_t = "";

    @Element(name = "int", required = false)
    public String Int = "";

    @Element(name = "base64", required = false)
    public String base64 = "";

    @Element(name = "Array", required = false)
    public BalanceArray balanceArray = new BalanceArray();

    @Element(name = "Structure", required = false)
    public BalanceResponseStructure structure = new BalanceResponseStructure();

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

    public BalanceTime getTime(){
        return time;
    }

    public String getBase64(){
        return base64;
    }

    public BalanceResponseStructure getStructure(){
        return structure;
    }
}
