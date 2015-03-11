package com.softhaxi.shortsage.v1.modem.impl;

import org.smslib.AGateway;
import org.smslib.ICallNotification;

/**
 * 
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class CallNotification implements ICallNotification {

    @Override
    public void process(AGateway gateway, String callerId) {
        System.out.println(">>> New call detected from Gateway: " + gateway.getGatewayId() + " : " + callerId);
    }
}
