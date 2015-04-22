package com.softhaxi.shortsage.v1.pane;

public class ContactPersonSearch extends JPanel {
  private static final ResourceBundle RES_GLOBAL = ResourcesBundle.getBundle("global");
  
  private static final String[] COLUMN_NAMES = new String[] {
    ""
  };
  
  private JXTable ttData;
  private JButton bOK, bCancel, bClear;

  public ContactPersonSearch() {
  
    initComponents();
    initListeners();
  }
}
