package com.softhaxi.shortsage.v1.component;

import javax.swing.JPanel;

public abstract class CPanel extends JPanel {
    private IHost host;
    
    public CPanel() {
      super();
      initComponents();
      initData();
    }
    
    public abstract void initComponents();
    public abstract void initData();
}
