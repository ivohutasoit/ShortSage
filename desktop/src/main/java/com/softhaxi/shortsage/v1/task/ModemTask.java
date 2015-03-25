package com.softhaxi.shortsage.v1.task;

public class ModemTask extends SwingWorker<Boolean, Void> {

    private ServiceHandler handler;
    private JLabel label;
    private JProgressBar progress,
    
    public ModemTask(ServiceHandler handler, JLabel label, JProgressBar progress) {
      this.handler = handler;
      this.label = label;
      this.progress = progress;
      
      if(handler == ServiceHandler.START) 
        label.setText("Starting Service Modem...");
      else
        label.setText("Stopping Service Modem...");
      
      progress.setVisible(true);
      progress.setIndeterminate(false);
    }
    
    @Override
    protected Boolean doInBackground() throws Exception {
      if(handler == ServiceHandler.START) {
          ModemUtil.start();
      } else {
          ModemUtil.stop();
      }
    }
    
    @Override
    protected void done() {
      if(!isCancelled()) {
          label.setText("Ready");
      } else {
          label.setText("Action has been cancelled by user.");
          try {
              Thread.sleep(1000);
              label.setText("Action has been cancelled by user.");
          } catch (InterruptedException e) {
              return;
          }
      }
    }
}
