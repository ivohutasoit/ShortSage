package com.softhaxi.shortsage.v1.global;

import com.softhaxi.shortsage.v1.dto.system.SystemUser;
import com.softhaxi.shortsage.v1.modem.impl.CallNotification;
import com.softhaxi.shortsage.v1.modem.impl.InboundNotification;
import com.softhaxi.shortsage.v1.modem.impl.OrphanedMessageNotification;
import com.softhaxi.shortsage.v1.modem.impl.OutboundNotification;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.smslib.AGateway;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.Service.ServiceStatus;

public class Constant {

    private static Constant instance = null;

    private Map<String, AGateway> gateways;
    private SystemUser user;

    private Constant() {
        gateways = new HashMap<>();
        user = new SystemUser();

        Service.getInstance().setOutboundMessageNotification(new OutboundNotification());
        Service.getInstance().setInboundMessageNotification(new InboundNotification());
        Service.getInstance().setCallNotification(new CallNotification());
        //Service.getInstance().setGatewayStatusNotification(new StatusNotification());
        Service.getInstance().setOrphanedMessageNotification(new OrphanedMessageNotification());
    }

    public static Constant getInstance() {
        if (instance == null) {
            instance = new Constant();
        }

        return instance;
    }

    public void addGateway(String name, AGateway gateway) {
        try {
            if (Service.getInstance().getServiceStatus() != ServiceStatus.STOPPED) {
                Service.getInstance().stopService();
            }

            gateways.put(name, gateway);
            Service.getInstance().addGateway(gateway);
            Service.getInstance().startService();
        } catch (SMSLibException | IOException | InterruptedException ex) {
            Logger.getLogger(Constant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
