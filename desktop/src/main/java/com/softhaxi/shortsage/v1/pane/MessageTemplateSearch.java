package com.softhaxi.shortsage.v1.pane;

public class MessageTemplateSearch extends JPanel
      implements ActionListener {
      
  private final static ResourcesBundle RES_GLOBAL = ResourcesBundle.getBundle("global");
  
  private final static String[] COLUMN_NAMES = {
    "Name",
    "Text",
    "Created Date"
  };
  
  private JXSearchField sfKeyword;
  private JXTable ttData;
  
  private DefaultTableModel tModel;
  private List<MessageTemplate> data;
  private MessageTemplate object;
  
  private JButton bOk, bCancel, bClear;
  
  public MessageTemplateSearch() {
    initComponents();
    initListeners();
  }
  
  private void initComponents() {
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(600, 400));
    
    initSearchPanel();
    initTablePanel();
    initButtonPanel();
  }
  
  private void initSearchPanel() {
    JPanel pKeyword = new JPanel();
    DesignGridLayout layout = new DesignGridLayout(pKeyword);
    
    sfKeyword = new JXSearchField(RES_GLOBAL.getString("label.search.item"));
    layout.row().grid(new JLabel(RES_GLOBAL.getString("label.search"))).add(sfKeyword);
    
    add(pKeyword, BorderLayout.NORTH);
  }
  
  private void initTablePanel() {
    ttData = new JXTable();
    ttData.setEditable(false);

    mData = new DefaultTableModel(COLUMN_NAMES, 0);
    ttData.setModel(mData);
    ttData.setSelectionMode(JTable.SINGLE_SELECTION);
    ttData.setShowGrid(false);
    ttData.setIntercellSpacing(new Dimension(0, 0));
    ttData.getTableHeader().setDefaultRenderer(new TableHeaderCenterRender(ttData));
    ttData.getColumnModel().getColumn(0).setPreferredWidth(100);
    ttData.getColumnModel().getColumn(1).setPreferredWidth(450);
    ttData.getColumnModel().getColumn(2).setPreferredWidth(50);

    JScrollPane sPane = new JScrollPane(ttData);
    HNumberedTable rowTable = new HNumberedTable(ttData);
    sPane.setRowHeaderView(rowTable);
    sPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,
            rowTable.getTableHeader());

    add(sPane, BorderLayout.CENTER);
  }
  
  private void initButtonPanel() {
    bOk = new JButton(RES_GLOBAL.getString("label.oke"));
    bCancel = new JButton(RES_GLOBAL.getString("label.cancel"));
    bClear = new JButton(RES_GLOBAL.getString("label.clear.value"))
  
    //Lay out the buttons from left to right.
    JPanel pButton = new JPanel();
    pButton.setLayout(new BoxLayout(pButton, BoxLayout.LINE_AXIS));
    pButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
    pButton.add(Box.createHorizontalGlue());
    pButton.add(bOk);
    pButton.add(Box.createRigidArea(new Dimension(5, 0)));
    pButton.add(bCancel);
    pButton.add(Box.createRigidArea(new Dimension(5, 0)));
    pButton.add(bClear);
    
    
    add(pButton, BorderLayout.PAGE_END);
  }
  
  private void initListeners() {
    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentShown(ComponentEvent e) {
          loadData();
      }
    });
    sfKeyword.addActionListener(this);
    bOk.addActionListener(this);
    bCancel.addActionListener(this);
    bClear.addActionListener(this);
  }
  
  private void initTableModel() {
    mData = new DefaultTableModel(COLUMN_NAMES, 0);
    Object[] obj = null;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    for (MessageTemplate template : data) {
        obj = new Object[3];
        obj[0] = template.getName();
        obj[1] = template.getText();
        obj[2] = sdf.format(template.getCreatedDate());
    
        mData.addRow(obj);
    }
    ttData.setModel(mData);
    
    if(data.size() > 0) {
      ttData.setSelectionInterval(0,0);
    }
  }
  
  private void loadData() {
    private HWaitDialog dialog = new HWaitDialog("Load Data");
    final SwingWorker<Boolean, Void> t1 = new SwingWorker<Boolean, Void>() {
       @Override
       protected Boolean doInBackground() {
            try {
                Session hSession = HibernateUtil.getSessionFactory().openSession();
                hSession.getTransaction().begin();
                Query query = hSession.getNamedQuery("MessageTemplate.All");
                data = query.list();
                hSession.getTransaction().commit();
                hSession.close();
                return true;
            } catch (Exception ex) {
                Logger.getLogger(MessageTemplateSearch.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
       }
       
        @Override
        protected void done() {
            if (!isCancelled()) {
                initTableModel();
            }
        }
    };
    
    t1.addPropertyChangeListener(new PropertyChangeListener() {
       @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if ("state".equals(evt.getPropertyName())
             && SwingWorker.StateValue.DONE == evt.getNewValue()) {
                try {
                    if(t1.get() == true) {
                      dialog.setVisible(false);
                      dialog.dispose();
                    }
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(MessageTemplateSearch.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
        }
    });
    t1.execute();
    dialog.setVisible(true);
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() instanceof JButton) {
      JButton bb = (JButton) e.getSource();
      if(bb = bOk) {
        if(ttData.getSelectedRow() != -1) {
          object = data.get(ttData.getSeletedRow());
        }
        firePropertyChange("select", false, true);
      } else if(bb == bCancel) {
        firePropertyChange("cancel", false, true);
      } else if(bb == bClear) {
        firePropertyChange("clear", false, true);
      }
    }
  }
}
