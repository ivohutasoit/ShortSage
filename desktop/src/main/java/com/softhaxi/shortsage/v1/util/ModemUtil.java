package com.softhaxi.shortsage.v1.util;

import com.softhaxi.shortsage.v1.modem.impl.CallNotification;
import com.softhaxi.shortsage.v1.modem.impl.InboundNotification;
import com.softhaxi.shortsage.v1.modem.impl.OrphanedMessageNotification;
import com.softhaxi.shortsage.v1.modem.impl.OutboundNotification;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.Service.ServiceStatus;
import org.smslib.modem.SerialModemGateway;

public class ModemUtil {

    private static SerialModemGateway gateway = null;

    static {
        gateway = new SerialModemGateway("modem.wavecom", "COM7", 115200, "Wavecom", "");
    }

    /**
     *
     * @return status that service modem has been started
     */
    public static boolean start() {
        try {
            Service service = Service.getInstance();
            if (service.getServiceStatus() == ServiceStatus.STARTED
                    || service.getServiceStatus() == ServiceStatus.STARTING) {
                return false;
            }

            //Session session = HibernateUtil.getSessionFactory().openSession();
            //Query query = session.session.createQuery("from " + Gateway.class.getName());
            gateway.setInbound(true);
            gateway.setOutbound(true);
            service.setOutboundMessageNotification(new OutboundNotification());
            service.setInboundMessageNotification(new InboundNotification());
            service.setCallNotification(new CallNotification());
            service.setOrphanedMessageNotification(new OrphanedMessageNotification());
            service.addGateway(gateway);
            service.startService();
            if (service.getServiceStatus() == ServiceStatus.STARTED) {
                return true;
            } else {
                return false;
            }
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
                    || service.getServiceStatus() == ServiceStatus.STOPPING) {
                return false;
            }

            service.stopService();
            service.removeGateway(gateway);
            if (service.getServiceStatus() == ServiceStatus.STOPPED) {
                return true;
            } else {
                return false;
            }
        } catch (SMSLibException | IOException | InterruptedException ex) {
            Logger.getLogger(ModemUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
