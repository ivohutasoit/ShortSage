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
      });
      
      if(state == ActionState.Create) 
          add(getPanelHeader(), BorderLayout.CENTER);
      else {
          JPanel pForm = new JPanel(new BorderLayout());
          
          pForm.add(getPanelHeader(), BorderLayout.NORTH);
          pForm.add(getPanelTable(), BorderLayout.CENTER);
      }
    }
    
    private JPanel getPanelHeader() {
        JPanel pHeader = new JPanel();
        pHeader.setPreferedSize(new Dimension(300, 400));
        
        DesignGridLayout layout = new DesignGridLayout(pHeader);
        layout.row().grid(label("Group :")).add(txId).empty().add(txName, 2);
        layout.row().grid(label("Description :")).add(txRemark);
        layout.row().grid(label("Status :")).add(cmStatus).empty(2);
        layout.row().grid(label("Handler :")).add(cmHandler).empty(2);
        
        return pHeader;
    }
    
    private JPanel getPanelTable() {
        JPanel pTable = new JPanel(new BorderLayout();
        JToolBar toContact = new JToolBar();
        toContact.setFloatable(false);
        toContact.setBorder(new CompoundBorder(new Empty(2,2,2,2)));
        
        bcNew = new JButton("New");
        bcImport = new JButton("Import Data");
        bcExport = new JButton("Export Data");
        bcDelete = new JButton("Delete");
        bcRefresh = new JButton("Refresh");
        
        toContact.add(bcNew);
        toContact.add(new JToolBar.Separator());
        toContact.add(bcImport);
        toContact.add(bcExport);
        toContact.add(new JToolBar.Separator());
        toContact.add(bcDelete);
        toContact.add(Box.createHorizontalGlue());
        toContact.add(bcRefresh);
        
        pTable.add(toContact, BorderLayout.NORTH);
        
        tbContact = new CZebraTable();
        pTable.add(tbContact, BorderLayout.CENTER);
        
        return pTable;
    }
    
    @Override
    public void initState() {
      super.initState();
    }
    
    @Override
    public void initData() {
    }
}
