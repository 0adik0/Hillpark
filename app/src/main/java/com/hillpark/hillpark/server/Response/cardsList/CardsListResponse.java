package com.hillpark.hillpark.server.Response.cardsList;

import com.hillpark.hillpark.server.Response.person.APacketCardsList;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "xmlstream", strict = false)
public class CardsListResponse {
    @Element(name = "APacket")
    private APacketCardsList packetCards = new APacketCardsList();

    public APacketCardsList getPacketCards(){
        return packetCards;
    }
}
