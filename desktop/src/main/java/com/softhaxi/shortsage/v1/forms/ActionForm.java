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
      
    }
    
    protected void initState() {
      if(page == null) {
        
      }
    }
    
    public abstract void initData(); 
}
