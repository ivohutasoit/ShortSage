package com.softhaxi.shortsage.v1.components;

import javax.swing.JPanel;

public abstract class CPanel extends JPanel {
    public CPanel() {
      super();
      initComponents();
      initData();
    }
    
    public abstract void initComponents();
    public abstract void initData();
}
