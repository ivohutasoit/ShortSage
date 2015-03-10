package com.softhaxi.shortsage.v1.forms;

public class ActionMessageForm extends JPanel {
    
    private ActionState state;
    private Message object;
    
    private JLabel lDestination;
    private JLabel lTimeSend;
    private JLabel lSendDate;
    private JLabel lTemplate;
    private JLabel lMessage;
    
    private JTextField tDestination;
    private JRadioButton rNow;
    private JRadioButton rNext;
    private JTextField tSendDate;
    private JCombobox xTemplate;
    private JTextArea tMessage;
    
    private JButton bSave;
    private JButton bCancel;
    private JButton bReset;
    
    public ActionMessageForm() {
        this(ActionState.CREATE, null);
    }
    
    public ActionMessageForm(ActionState state, Message object) {
        this.state = state;
        this.object = object;
        
        initComponents();
        initState();
        initData();
    }
    
    private void initComponents() {
        lDestinantion = new JTextField(20);
        CTextPrompt pDest = new CTextPrompt("Person or Group", tDestination);
        pDest.setForeground(Color.RED);
        pDest.setAlpha(0.7f);
    }
    
    private void initState() {
    }
    
    private void initData() {
      
    }
}
