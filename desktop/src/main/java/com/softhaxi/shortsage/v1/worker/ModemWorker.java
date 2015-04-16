package com.softhaxi.shortsage.v1.worker;

import com.softhaxi.shortsage.v1.enums.ActionState;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import org.smslib.AGateway;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.Service.ServiceStatus;

/**
 * 
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class ModemWorker extends SwingWorker<Boolean, Integer> {

    private ActionState state;
    private List<AGateway> data;
    private AGateway modem;

    private Service service;

    public ModemWorker(ActionState state) {
        this(null, null, state);
    }

    public ModemWorker(AGateway modem, ActionState state) {
        this(null, modem, state);
    }

    public ModemWorker(List<AGateway> data, ActionState state) {
        this(data, null, state);
    }

    private ModemWorker(List<AGateway> data, AGateway modem, ActionState state) {
        this.data = data;
        this.modem = modem;
        this.state = state;

        service = Service.getInstance();
    }

    @Override
    protected Boolean doInBackground() {
        if (state == ActionState.CONNECT) {
            if (service.getServiceStatus() == ServiceStatus.STARTED
                    || service.getServiceStatus() == ServiceStatus.STARTING) {
                try {
                    service.stopService();
                    // how if i use this class instance????
                    Thread.sleep(8000);

                    if (service.getServiceStatus() == ServiceStatus.STOPPED) {
                        if (modem == null) {
                            for (AGateway gateway  : data) {
                                modem.setInbound(true);
                                modem.setOutbound(true);
                                service.addGateway(modem);
                            }
                        }
                        // add callback for service
                    }
                    service.startService();
                } catch (SMSLibException | IOException | InterruptedException ex) {
                    Logger.getLogger(ModemWorker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return service.getServiceStatus() == ServiceStatus.STARTED;
        } else if (state == ActionState.DISCONNECT) {
            if (service.getServiceStatus() == ServiceStatus.STARTED
                    || service.getServiceStatus() == ServiceStatus.STARTING) {
                try {
                    service.stopService();
                } catch (SMSLibException | IOException | InterruptedException ex) {
                    Logger.getLogger(ModemWorker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            return service.getServiceStatus() == ServiceStatus.STOPPED;
        }
        return false;
    }
}
