package com.hillpark.hillpark.server.Response.person;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "xmlstream", strict = false)
public class PersonResponse {
    @Element(name = "APacket")
    private APacketPerson packetPerson;

    public APacketPerson getPacketPerson(){
        return packetPerson;
    }
}
