package com.softhaxi.shortsage.v1.forms;

public class NewMessageActionForm extends JPanel {
  
  private Message object;
  private ActionState state;
  
  /**
   * Tool bar items
   */
  private JButton bNew, bEdit, bDelete, bReply;
  private JButton bSave, bSaveNew, bCancel;
  
  /**
   * Fields
   */
  private JCheckBox cfScheduler;
  private JTextField tfContact;
  private JXDatePicker dfDate;
  private JTextArea tfText;
  private JComboBox cfTemplate, cfStatus, cfHandler;
  
  /**
   * Database connection
   */
  private Session hSession;
  
  /**
   *
   */
  public NewMessageActionForm() {
    this(null, ActionState.CREATE);
  }
  
  /**
   *
   */
  public NewMessageActionForm(Message object, ActionState state) {
    this.object = object;
    this.state = state;
    
    initComponents();
    initListeners();
    initState();
  }
  
  // <editor-fold defaultstate="collapsed" desc="Region Initialization">
  /**
   *
   */
  private void initComponents() {
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(450, 300));
    
    initToolbar();
    initFormPanel();
  }
  
  /**
   *
   */
  private void initToolbar() {
    JToolBar pToolbar = new JToolBar();
    pToolbar.setFloatable(false);

    bNew = new JButton(RES_GLOBAL.getString("label.new"),
            new ImageIcon(getClass().getClassLoader().getResource("images/ic_new.png")));
    pToolbar.add(bNew);

    bEdit = new JButton(RES_GLOBAL.getString("label.edit"),
            new ImageIcon(getClass().getClassLoader().getResource("images/ic_edit.png")));
    pToolbar.add(bEdit);

    bSave = new JButton(RES_GLOBAL.getString("label.save"),
            new ImageIcon(getClass().getClassLoader().getResource("images/ic_save.png")));
    pToolbar.add(bSave);

    bSaveNew = new JButton(RES_GLOBAL.getString("label.save.new"),
            new ImageIcon(getClass().getClassLoader().getResource("images/ic_save_as.png")));
    pToolbar.add(bSaveNew);

    bTest = new JButton(RES_GLOBAL.getString("label.test.gateway"),
            new ImageIcon(getClass().getClassLoader().getResource("images/ic_modem_connect_16.png")));
    pToolbar.add(bTest);
    pToolbar.addSeparator();

    bDelete = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_delete.png")));
    pToolbar.add(bDelete);

    bCancel = new JButton(RES_GLOBAL.getString("label.cancel"),
            new ImageIcon(getClass().getClassLoader().getResource("images/ic_cancel.png")));
    pToolbar.add(bCancel);
    
    add(pToolbar, BorderLayout.NORTH);
  }
  
  /**
   *
   */
  private void initFormPanel() {
    JPanel pForm = new JPanel();
    
    cfScheduler = new JCheckBox(RES_GLOBAL.getString("label.message.scheduler"));
    cfScheduler.setForeground(Color.Blue);
    tfContact = new JTextField();
    dfDate = new JXDatePicker();
    tftext = new TextArea();
    tfText.setRows(3);
    cfTemplate = new JComboBox();
    cfStatus = new JComboBox();
    cfHandler = new JCombobBox();
    
    DesignGridLayout layout = new DesignGridLayout(pForm);
    RowGroup rgScheduler = new RowGroup();
    rgScheduler.setName(cfScheduler.getText());
    cfScheduler.addItemListener(new ShowHideAction(rgScheduler));
    
    layout.row().left().add(cfScheduler, new JSeparator()).fill();
    layout.row().group(rgScheduler).grid(new JLabel(RES_GLOBAL.getString("label.message.date"))).add(dfDate).empty(2);
    layout.row().grid(new JLabel(RES_GLOBAL.getString("label.message.contact") + " :")).add(tfContact, 2).empty();
    layout.row().grid(new JLabel(RES_GLOBAL.getString("label.message.template") + " :")).add(cfTemplate, 2).empty();
    layout.row().grid(new JLabel(RES_GLOBAL.getString("label.message.text") + " :")).add(new JScrollPane(tfText));
    layout.row().grid(new JLabel(RES_GLOBAL.getString("label.status") + " :")).add(cfStatus).empty(3);
    layout.row().grid(new JLabel(RES_GLOBAL.getString("label.handler") + " :")).add(cfHandler).empty(3);
    
    add(pForm, BorderLayout.CENTER);
  }
  
  /**
   *
   */
  private void initListeners() {
  }
  
  /**
   *
   */
  private void initState() {
    
  }
  
  /**
   *
   */
  private void initData() {
  }
  // </editor-fold>
  
  class ShowHideAction implements ItemListener {
    private RowGroup group;
    
    public ShowHideAction(RowGroup group) {
      this.group = group;
    }
    
    @Override 
    public void itemStateChanged(ItemEvent event) {
      if (event.getStateChange() == ItemEvent.SELECTED) {
        group.show();
      } else {
        group.hide();
      }
    }
  }
}
