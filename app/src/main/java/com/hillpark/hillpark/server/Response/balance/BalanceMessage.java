package com.hillpark.hillpark.server.Response.balance;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Message")
public class BalanceMessage {
    @Element(name = "string", required = false)
    public String string;
    @Element(name = "int", required = false)
    public String Int;
}
