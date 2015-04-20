package com.softhaxi.shortsage.v1.modem.impl;

import org.smslib.AGateway;
import org.smslib.IUSSDNotification;
import org.smslib.USSDResponse;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class USSDNotification implements IUSSDNotification {

    @Override
    public void process(AGateway gateway, USSDResponse response) {
        System.out.println("USSD handler called from Gateway: " + gateway.getGatewayId());
        System.out.println(response);
    }
}
