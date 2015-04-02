package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.component.CActionForm;
import com.softhaxi.shortsage.v1.component.CZebraTable;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.model.ContactGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class ContactGroupActionForm extends CActionForm<ContactGroup> {
    private JTextField txId, txName;
    private JTextArea txRemark;
    private JComboBox cmStatus, cmHandler;
    
    private CZebraTable tbContact;
    private JButton bcNew, bcDelete, bcExport, bcImport, bcRefresh;
    
    public ContactGroupActionForm() {
      super();
    }
    
    public ContactGroupActionForm(ContactGroup group) {
      super(group);
    }
    
    public ContactGroupActionForm(ActionState state, ContactGroup group) {
      super(state, group);
    }
    
    @Override
    public void initComponents() {
      super.initComponents();
      
      txId = new JTextField();
      txName = new JTextField();
      txRemark = new JTextArea(5);
      
      cmStatus = new JComboBox(new String[] {
        "Active",
        "Deactive"  
      });
      
      cmHandler = new JComboBox(new String[] {
        "No Action",
        "Activated",
        "Deactivated",
        "Deleted"
      })
      
      if(state == ActionState.Create) 
        add(initCreate(), BorderLayout.CENTER);
    }
    
    private JPanel initCreate() {
        JPanel pForm = new JPanel();
        pForm.setPreferedSize(new Dimension(300, 400));
        pForm.setBorder(new EmptyBorder(4,4,4,4));
        
        DesignGridLayout layout = new DesignGridLayout(pForm);
        layout.row().grid(label("Group :")).add(txId).empty().add(txName, 2);
        layout.row().grid(label("Description :")).add(txRemark);
        layout.row().grid(label("Status :")).add(cmStatus).empty(2);
        layout.row().grid(label("Handler :")).add(cmHandler).empty(2);
        cmStatus.setEnabled(false);
        cmHandler.setEnabled(false);
        
        return panel;
    }
    
    @Override
    public void initState() {
      super.initState();
    }
    
    @Override
    public void initData() {
    }
}
