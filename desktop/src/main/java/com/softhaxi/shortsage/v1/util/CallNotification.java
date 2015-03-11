package com.softhaxi.shortsage.v1.modem.impl;

public class CallNotification implements ICallNotification {
    public void process(AGateway gateway, String callerId)
    {
      System.out.println(">>> New call detected from Gateway: " + gateway.getGatewayId() + " : " + callerId);
    }
}
