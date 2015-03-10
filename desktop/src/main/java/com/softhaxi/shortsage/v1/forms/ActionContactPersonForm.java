package com.softhaxi.shortsage.v1.forms;

public class ActionContactPersonForm extends JPanel {
    
    private ActionSate state;
    private ContactPerson object;
    
    
    public ActionContactPersonForm() {
        this(ActionState.CREATE, null);  
    }
    
    public ActionContactPersonForm(ActionState state, ContactPerson object) {
        this.state = state;
        this.object = object;
        
        initComponents();
        initState();
        initData();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(5, 5));
        setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5)));
        
        JToolbar ftBar = new JToolbar();
        ftBar.add(new JButton("New", new ImageIcon(getClass().getResources("images/ic_new.png"))));
        ftBar.add(new JButton("Edit", new ImageIcon(getClass().getResources("images/ic_edit.png"))));
        
        add(ftBar, BorderLayout.NORTH);
        
        JPanel pForm = new JPanel();
        GroupLayout layout = new new GroupLayout(pForm);
        pForm.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        JLabel lPrimary = new JLabel("Primary Code: ");
        JTextField tPrimary = new JTextField(16);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(lPrimary))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(tPrimary))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lPrimary)
                .addComponent(tPrimary))
        );
    }
    
    private void initState() {
    
    }
    
    private void initData() {
      
    }
}
