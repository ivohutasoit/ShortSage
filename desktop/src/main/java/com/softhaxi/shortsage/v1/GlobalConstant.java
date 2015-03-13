package com.softhaxi.shortsage.v1;

public class GlobalConstant {
    private static GlobalConstant instance = null;

    private Map<String, AGateway> gateways;
    private SystemUser user;
    
    private GlobalConstant() {
        gateways = new HashMap<>();
        user = new SystemUser();
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
