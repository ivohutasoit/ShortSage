package com.softhaxi.shortsage.v1;

public class ModemUtil {
    
    /**
     *
     * @return status that service modem has been started
     */
    public static boolean start() {
      Service service = Service.getInstance();
      if(service.getServiceStatus() == ServiceStatus.STARTED ||
          service.getServiceStatus() == ServiceStatus.STARTING) {
          return false;
      }
      
      //Session session = HibernateUtil.getSessionFactory().openSession();
      //Query query = session.session.createQuery("from " + Gateway.class.getName());
      
      SerialModemGateway gateway = new SerialModemGateway("modem.wavecom", "COM8", 115200, "Wavecom", "");
      gateway.setInbound(true);
      gateway.setOutbound(true);
      service.setOutboundMessageNotification(new OutboundNotification());
      service.setInboundMessageNotification(new InboundNotification());
      service.setCallNotification(new CallNotification());
      service.setGatewayStatusNotification(new StatusNotification());
      service.setOrphanedMessageNotification(new OrphanedMessageNotification());
      service.addGateway(gateway);
      service.startService();
      return true;
      //for(Gateway gateway : query.list()) {
      //}
    }
    
    /**
     *
     * @return Status that service modem has been stopped
     */
    public static boolean stop() {
      Service service = Service.getInstance();
      if(service.getServiceStatus() == ServiceStatus.STOPPED ||
          service.getServiceStatus() == ServiceStatus.STOPPING) {
          return false;
      }
      
      service.stopService();
      return true;
    }
}
