package com.softhaxi.shortsage.v1.modem.impl;


public class GatewayStatusNotification implements IGatewayStatusNotification {
    public void process(AGateway gateway, GatewayStatuses oldStatus, GatewayStatuses newStatus)
    {
      System.out.println(">>> Gateway Status change for " + gateway.getGatewayId() + ", OLD: " + oldStatus + " -> NEW: " + newStatus);
    }
}
