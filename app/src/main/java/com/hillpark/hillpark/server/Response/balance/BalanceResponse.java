package com.hillpark.hillpark.server.Response.balance;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "xmlstream", strict = false)
public class BalanceResponse {
    @Element(name = "APacket")
    private APacketBalance packetBalance;

    public APacketBalance getPacketBalance(){
        return packetBalance;
    }
}
