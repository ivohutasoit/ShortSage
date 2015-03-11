package com.softhaxi.shortsage.v1.modem.impl;

import org.smslib.AGateway;
import org.smslib.IInboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Message.MessageTypes;

/**
 *
 * Reference <a href="https://github.com/smslib/smslib-v3/">Github SMSLib 3</a>
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class InboundNotification implements IInboundMessageNotification {

    @Override
    public void process(AGateway gateway, MessageTypes msgType, InboundMessage msg) {
        if (msgType == MessageTypes.INBOUND) {
            System.out.println(">>> New Inbound message detected from Gateway: " + gateway.getGatewayId());
        } else if (msgType == MessageTypes.STATUSREPORT) {
            System.out.println(">>> New Inbound Status Report message detected from Gateway: " + gateway.getGatewayId());
        }
        System.out.println(msg);
    }
}
