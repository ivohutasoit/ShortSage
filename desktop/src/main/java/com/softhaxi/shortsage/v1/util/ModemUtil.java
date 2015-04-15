package com.softhaxi.shortsage.v1.util;

import com.softhaxi.shortsage.v1.dto.Gateway;
import com.softhaxi.shortsage.v1.modem.impl.CallNotification;
import com.softhaxi.shortsage.v1.modem.impl.InboundNotification;
import com.softhaxi.shortsage.v1.modem.impl.OrphanedMessageNotification;
import com.softhaxi.shortsage.v1.modem.impl.OutboundNotification;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.smslib.AGateway;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.Service.ServiceStatus;
import org.smslib.modem.SerialModemGateway;

public class ModemUtil {

    private static SerialModemGateway modem = null;

//    static {
//        gateway = new SerialModemGateway("modem.com7", "COM7", 115200, "Wavecom", "");
//    }
    /**
     *
     * @return status that service modem has been started
     */
    public static boolean start() {
        try {
            Service service = Service.getInstance();
            if (service.getServiceStatus() == ServiceStatus.STARTED
                    || service.getServiceStatus() == ServiceStatus.STARTING
                    || service.getServiceStatus() == ServiceStatus.STOPPING) {
                return false;
            }

            Session hSession = HibernateUtil.getSessionFactory().openSession();
            hSession.getTransaction().begin();
            Query query = hSession.createQuery("from Gateway");
            List<Gateway> dGateway = query.list();
            hSession.getTransaction().commit();
            hSession.close();

            service.setOutboundMessageNotification(new OutboundNotification());
            service.setInboundMessageNotification(new InboundNotification());
            service.setCallNotification(new CallNotification());
            service.setOrphanedMessageNotification(new OrphanedMessageNotification());

            for (Gateway gateway : dGateway) {
                if (service.getGateway(gateway.getId()) == null) {
                    modem = new SerialModemGateway(gateway.getId(), gateway.getPort(), gateway.getBaudRate(),
                            "", "");
                    modem.setInbound(true);
                    modem.setOutbound(true);
                    service.addGateway(modem);
                }
            }

            //Session session = HibernateUtil.getSessionFactory().openSession();
            //Query query = session.session.createQuery("from " + Gateway.class.getName());
            service.startService();
            return service.getServiceStatus() == ServiceStatus.STARTED;
            //for(Gateway gateway : query.list()) {
            //}
        } catch (SMSLibException | IOException | InterruptedException ex) {
            Logger.getLogger(ModemUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     *
     * @return Status that service modem has been stopped
     */
    public static boolean stop() {
        try {
            Service service = Service.getInstance();
            if (service.getServiceStatus() == ServiceStatus.STOPPED
                    || service.getServiceStatus() == ServiceStatus.STOPPING
                    || service.getServiceStatus() == ServiceStatus.STARTING) {
                return true;
            }
            List<AGateway> gateways = (List<AGateway>) service.getGateways();
            
            service.stopService();
            
            for (AGateway gateway : gateways) {
                service.removeGateway(gateway);
                System.out.println(gateway.getStatus().toString());
            }
            return service.getServiceStatus() == ServiceStatus.STOPPED;
        } catch (SMSLibException | IOException | InterruptedException ex) {
            Logger.getLogger(ModemUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
