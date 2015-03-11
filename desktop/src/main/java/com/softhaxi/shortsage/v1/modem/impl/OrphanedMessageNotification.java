package com.softhaxi.shortsage.v1.modem.impl;

import org.smslib.AGateway;
import org.smslib.IOrphanedMessageNotification;
import org.smslib.InboundMessage;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class OrphanedMessageNotification implements IOrphanedMessageNotification {

    @Override
    public boolean process(AGateway gateway, InboundMessage msg) {
        System.out.println(">>> Orphaned message part detected from " + gateway.getGatewayId());
        System.out.println(msg);
        // Since we are just testing, return FALSE and keep the orphaned message part.
        return false;
    }
}
