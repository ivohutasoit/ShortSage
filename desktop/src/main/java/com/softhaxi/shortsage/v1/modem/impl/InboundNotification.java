package com.softhaxi.shortsage.v1.modem.impl;

import com.softhaxi.shortsage.v1.dto.InboxMessage;
import com.softhaxi.shortsage.v1.dto.OutboxMessage;
import com.softhaxi.shortsage.v1.modem.ModemCallback;
import org.hibernate.Query;
import org.smslib.AGateway;
import org.smslib.IInboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Message.MessageTypes;
import org.smslib.OutboundMessage;

/**
 *
 * Reference <a href="https://github.com/smslib/smslib-v3/">Github SMSLib 3</a>
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class InboundNotification extends ModemCallback
        implements IInboundMessageNotification {

    @Override
    public void process(AGateway gateway, MessageTypes msgType, InboundMessage msg) {
        if (msgType == MessageTypes.INBOUND) {
            System.out.println(">>> New Inbound message detected from Gateway: " + gateway.getGatewayId());
        } else if (msgType == MessageTypes.STATUSREPORT) {
            System.out.println(">>> New Inbound Status Report message detected from Gateway: " + gateway.getGatewayId());
        }
        System.out.println(msg);

        InboxMessage message = null;
        try {
            openSession();
            message = new InboxMessage();
            message.setDate(msg.getDate());
            message.setRefId(msg.getUuid());
            message.setGatewayId(msg.getGatewayId());
            message.setContact(msg.getOriginator());
            message.setText(msg.getText());
            message.setCenter(msg.getSmscNumber());

            session.getTransaction().begin();
            session.saveOrUpdate(message);
            session.getTransaction().commit();
            
            gateway.deleteMessage(msg);
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        } finally {
            closeSession();
        }
    }
}
