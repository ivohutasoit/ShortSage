package com.softhaxi.shortsage.v1.modem.impl;

public class OutboundNotification implements IOutboundNotification {
    public void process(AGateway gateway, OutboundMessage msg)
    {
      System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());
      System.out.println(msg);
    }
}
