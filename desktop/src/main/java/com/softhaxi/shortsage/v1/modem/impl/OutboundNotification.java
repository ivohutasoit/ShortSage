package com.softhaxi.shortsage.v1.modem.impl;

import com.softhaxi.shortsage.v1.dto.OutboxMessage;
import com.softhaxi.shortsage.v1.modem.ModemCallback;
import org.hibernate.Query;
import org.smslib.AGateway;
import org.smslib.IOutboundMessageNotification;
import org.smslib.OutboundMessage;

public class OutboundNotification extends ModemCallback
        implements IOutboundMessageNotification {

    @Override
    public void process(AGateway gateway, OutboundMessage msg) {
        System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());
        System.out.println(msg);

        OutboxMessage message = null;
        try {
            openSession();
            Query query = session.getNamedQuery("Message.ByRefId");
            query.setParameter("uuid", msg.getUuid());

            message = (OutboxMessage) query.uniqueResult();

            // Only use for scheduler messages
            if (message != null) {
                message.setStatus(msg.getMessageStatus() == OutboundMessage.MessageStatuses.UNSENT ? 1
                        : msg.getMessageStatus() == OutboundMessage.MessageStatuses.SENT ? 2 : 3);
                message.setErrorMessage(msg.getErrorMessage());
                message.setFailureCause(msg.getFailureCause().toString());
                message.setDate(msg.getDispatchDate());
                
                
                    session.getTransaction().begin();
                    session.saveOrUpdate(message);
                    session.getTransaction().commit();
            } 
            //else {
            //    message = new OutboxMessage();
            //    message.setRefId(msg.getUuid());
            //    message.setContact(msg.getRecipient());
            //    message.setGatewayId(msg.getGatewayId());
            //    message.setText(msg.getText());
            //    message.setDate(msg.getDate());
            //    message.setFailureCause(msg.getFailureCause().toString());
            //    message.setRetryCount(msg.getRetryCount());
            //    message.setErrorMessage(msg.getErrorMessage());
            //    message.setStatus(msg.getMessageStatus() == OutboundMessage.MessageStatuses.UNSENT ? 1
            //            : msg.getMessageStatus() == OutboundMessage.MessageStatuses.SENT ? 2 : 3);
            //}
        } catch (Exception ex) {
            session.getTransaction().rollback();
            throw new RuntimeException(ex);
        } finally {
            closeSession();
        }

//        openSession();
//        session.getTransaction().begin();
//        session.save(message);
//        session.getTransaction().commit();
    }
}
