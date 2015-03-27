package com.softhaxi.shortsage.v1.component;

import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public abstract class CPanel extends JPanel {
    //protected IHost host;
    
    public CPanel() {
      super();
      initComponents();
      
        addAncestorListener(new AncestorListener() {

          @Override
          public void ancestorAdded(AncestorEvent event) {
              initData();
          }

          @Override
          public void ancestorRemoved(AncestorEvent event) {
          
          }

          @Override
          public void ancestorMoved(AncestorEvent event) {
          
          }
      });
    }
    
    public abstract void initComponents();
    public abstract void initData();
}
