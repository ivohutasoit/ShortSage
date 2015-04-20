package com.softhaxi.shortsage.v1.task;

import com.softhaxi.shortsage.v1.util.ModemUtil;
import com.softhaxi.shortsage.v1.enums.ServiceHandler;
import javax.swing.SwingWorker;

/**
 * 
 * @author Ivo Hutasoit
 * 
 */
public class ModemTask extends SwingWorker<Boolean, Void> {

    private final ServiceHandler handler;
    
    public ModemTask(ServiceHandler handler) {
      this.handler = handler;
    }
    
    @Override
    protected Boolean doInBackground() throws Exception {
      if(handler == ServiceHandler.START) {
          return ModemUtil.start();
      } else {
          return ModemUtil.stop();
      }
    }
}
