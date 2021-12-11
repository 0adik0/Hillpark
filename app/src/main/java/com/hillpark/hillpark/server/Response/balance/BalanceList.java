package com.hillpark.hillpark.server.Response.balance;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "list")
public class BalanceList {
    @Element(name = "Message", required = false)
    BalanceMessage mess;
}
