package com.softhaxi.shortsage.v1;

public class GlobalConstant {
    private static GlobalConstant instance = null;

    private Map<String, AGateway> gateways;
    private SystemUser user;
    
    private GlobalConstant() {
        gateways = new HashMap<>();
        user = new SystemUser();
        
        Service.getInstance().setOutboundMessageNotification(new OutboundNotification());
        Service.getInstance().setInboundMessageNotification(new InboundNotification());
        Service.getInstance().setCallNotification(new CallNotification());
        Service.getInstance().setGatewayStatusNotification(new StatusNotification());
        Service.getInstance().setOrphanedMessageNotification(new OrphanedMessageNotification());
    }
    
    public static GlobalConstant getInstance() {
        if(instance == null)
            instance = new GlobalConstant();
            
        return instance;
    }
    
    public void addGateway(String name, AGateway gateway) {
        if(Service.getInstance().getServiceStatus() != ServiceStatus.STOPPED) 
          Service.getInstance().stopService();
          
        gateways.add(name, gateway);
        Service.getInstance().add(gateway)
        Service.getInstance().startService();
    }
}
