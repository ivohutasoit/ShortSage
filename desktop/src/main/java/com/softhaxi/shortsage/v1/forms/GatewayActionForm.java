package com.softhaxi.shortsage.v1.forms;

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
    private void initComponents() {
    }
    
    @Override
    private void initState() {
    }
    
    @Override
    private void initData() {
    }
    
    public boolean save() {
      return false;
    }
}
