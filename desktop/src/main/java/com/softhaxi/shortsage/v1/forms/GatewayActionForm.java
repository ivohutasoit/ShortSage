package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.dto.Gateway;
import com.softhaxi.shortsage.v1.enums.ActionState;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GatewayActionForm extends ActionForm<Gateway> {

    private JPanel pDetail;
    private JTextField tName;
    private JTextField tport;
    private JTextField tRate;
    private JComboBox cStatus;
    private JComboBox cHandle;
    private JTextArea tRemark;
    
    public GatewayActionForm() {
      super();
    }
    
    private GatewayActionForm(Gateway gateway) {
      super(gateway);
    }
    
    private GatewayActionForm(ActionState state, Gateway gateway) {
      super(state, gateway);
    }
    
    @Override
    public void initComponents() {
      super.initComponents();
    }
    
    @Override
    public void initState() {
      super.initState();
    }
    
    @Override
    public void initData() {
    }
}
