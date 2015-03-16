package com.softhaxi.shortsage.v1.forms;

public abstract class ActionForm<T> extends JPanel {
    
    protected JPanel page;
    protected ActionState state;
    protected T object;
    
    protected JToolbar tBar;
    protected JButton bNew;
    protected JButton bEdit;
    protected JButton bDelete;
    protected JButton bSave;
    protected JButton bSaveNew;
    protected JButton bCancel;
    protected JBUtton bRefresh;
    
    protected ActionForm() {
      this(null, ActionState.CREATE, null);
    }

    protected ActionForm(JPanel page, T object) {
      this(page, ActionState.SHOW, object);
    }
    
    protected ActionForm(JPanel page, ActionState state, T object) {
      this.page = page;
      this.state = state;
      this.object = object;
      
      initComponents();
      initState();
    }
    
    protected void initComponents() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(2, 2, 2, 2));
        
        tBar = new JToolBar();
        tBar.setFloatable(false);
        tBar.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(2, 2, 2, 2)));
        add(ftBar, BorderLayout.NORTH);
        
        bNew = new JButton("New", new ImageIcon(getClass().getClassLoader().getResource("images/ic_new.png")));
        bEdit = new JButton("Edit", new ImageIcon(getClass().getClassLoader().getResource("images/ic_edit.png")));
        bSave = new JButton("Save", new ImageIcon(getClass().getClassLoader().getResource("images/ic_save.png")));
        bSaveNew = new JButton("Save and New", new ImageIcon(getClass().getClassLoader().getResource("images/ic_save_as.png")));
        bDelete = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_delete.png")));
        bCancel = new JButton("Cancel", new ImageIcon(getClass().getClassLoader().getResource("images/ic_cancel.png")));
        
        
    }
    
    protected void initState() {
      if(page == null) {
        if (state == ActionState.CREATE || state == ActionState.EDIT) {
            bNew.setVisible(false);
            bEdit.setVisible(false);
            bDelete.setVisible(false);
            cStatus.setEnabled(false);
        }
      }
    }
    
    public abstract void initData(); 
}
