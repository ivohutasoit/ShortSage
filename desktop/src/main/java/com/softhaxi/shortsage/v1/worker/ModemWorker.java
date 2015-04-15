package com.softhaxi.shortsage.v1.worker;

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
    switch(state) {
      case ActionState.CONNECT:
        if(service.getServiceStatus() == ServiceStatus.STARTED ||
            service.getServiceStatus() == ServiceStatus.STARTING) {
          service.stopService();
          // how if i use this class instance????
        }
        
        Thread.sleep(8000);
        
        if(service.getServiceStatus() == ServiceStatus.STOPPED) {
          if(modem == null) {
            for(modem : data) {
              modem.setInbound(true);
              modem.setOutbound(true);
              service.addGateway(modem);
            }
          }
          // add callback for service
        }
        service.startService();
        return service.getServiceStatus() == ServiceStatus.STARTED ? true : false;
      case ActionState.DISCONNECT:
        if(service.getServiceStatus() == ServiceStatus.STARTED ||
          service.getServiceStatus() == ServiceStatus.STARTING) 
          service.stopService();
        
        return service.getServiceStatus() == ServiceStatus.STOPPED ? true : false;
      }
      return false;
  }
}
