package com.softhaxi.shortsage.v1.modem.impl;

import org.smslib.AGateway;
import org.smslib.IOutboundMessageNotification;
import org.smslib.OutboundMessage;

public class OutboundNotification implements IOutboundMessageNotification {
    @Override
    public void process(AGateway gateway, OutboundMessage msg)
    {
      System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());
      System.out.println(msg);
    }
}
