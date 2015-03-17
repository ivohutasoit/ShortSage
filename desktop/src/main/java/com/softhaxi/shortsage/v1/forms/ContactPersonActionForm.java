package com.softhaxi.shortsafge.v1.forms;

public class ContactPersonActionForm extends ActionForm<ContactPerson> {
    private JPanel pHeader;
    private JTextField tID;
    private JTextField tName;
    private JTextField tCompany;
    private JComboBox cStatus;
    private JComboBox cHeadler;
    private JTextArea tRemark;
    
    private JPanel pDetail;
    private CZebraTable zNumber;
    
    public ContactPersonActionForm() {
      super();
    }
    
    public ContactPersonActionForm(ContactPerson person) {
      super(person);
    }
    
    public ContactPersonActionForm(ActionState state, ContactPerson person) {
      super(state, person);
    }
    
    @Override
    private void initComponents() {
      super.initComponents();
    }
    
    @Override
    private void initState() {
      super.initState();
    }
    
    @Override
    private void initData() {
    }
    
}
