package com.softhaxi.shortsage.v1.modem.impl;

import org.smslib.AGateway;
import org.smslib.AGateway.GatewayStatuses;
import org.smslib.IGatewayStatusNotification;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class GatewayStatusNotification implements IGatewayStatusNotification {

    @Override
    public void process(AGateway gateway, GatewayStatuses oldStatus, GatewayStatuses newStatus) {
        System.out.println(">>> Gateway Status change for " + gateway.getGatewayId() + ", OLD: " + oldStatus + " -> NEW: " + newStatus);
    }
}
