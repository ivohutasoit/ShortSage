package com.softhaxi.shortsage.v1.forms;

public class MessageTemplateActionForm extends ActionForm<MessageTemplate> {
  private JTextField tID;
  private JTextField tName;
  private JComboBox cStatus;
  private JComboBox cHandler;
  private JTextArea tText;
  
  public MessageTemplateActionForm() {
    super();
  }
  
  public MessageTemplateActionForm(MessageTemplate template) {
    super(template);
  }
  
  public MessageTemplateActionForm(ActionState state, MessageTemplate template) {
    super(state, template);
  }
  
  @Override
  public void initComponents() {
    super.initComponents()
  }
  
  @Override
  public void initState() {
    super.initState();
  }
  
  @Override
  private void initData() {
  }
}
