package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.dto.MessageTemplate;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.enums.PropertyChangeField;
import com.softhaxi.shortsage.v1.util.HibernateUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;
import net.java.dev.designgridlayout.DesignGridLayout;
import net.java.dev.designgridlayout.LabelAlignment;
import org.hibernate.Session;
import org.jdesktop.swingx.JXTextField;

public class MessageTemplateActionForm extends JPanel
        implements ActionListener {

    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private MessageTemplate object;
    private ActionState state;

    /**
     * Tool bar items
     */
    private JButton bNew, bEdit, bDelete;
    private JButton bSave, bSaveNew, bCancel;

    /**
     * Fields
     */
    private JXTextField tfName;
    private JTextArea tfText;
    private JComboBox cfStatus, cfHandler;

    /**
     * Database connection
     */
    private Session hSession;

    public MessageTemplateActionForm() {
        this(null, ActionState.CREATE);
    }

    public MessageTemplateActionForm(MessageTemplate object) {
        this(null, ActionState.SHOW);
    }

    public MessageTemplateActionForm(MessageTemplate object, ActionState state) {
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
        setPreferredSize(new Dimension(450, 250));

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
        //pToolbar.add(bSaveNew);
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
        tfName = new JXTextField(RES_GLOBAL.getString("label.template.name"));
        tfText = new JTextArea();
        tfText.setRows(3);
        tfText.setLineWrap(true);
        tfText.setFont(tfName.getFont());
        cfStatus = new JComboBox();
        cfHandler = new JComboBox();

        JPanel pForm = new JPanel();
        DesignGridLayout layout = new DesignGridLayout(pForm);
        layout.labelAlignment(LabelAlignment.RIGHT);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.template.name") + " :")).add(tfName).empty();
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.message.text") + " :")).add(new JScrollPane(tfText));
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.status") + " :")).add(cfStatus).empty(2);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.handler") + " :")).add(cfHandler).empty(2);

        add(pForm, BorderLayout.CENTER);
    }

    /**
     *
     */
    private void initListeners() {
        bSave.addActionListener(this);
    }

    /**
     *
     */
    private void initState() {
        if (state == ActionState.CREATE) {
            cfStatus.removeAllItems();
            cfStatus.addItem("CREATE");
            cfStatus.setEnabled(false);

            cfHandler.removeAllItems();
            cfHandler.addItem("CREATED");
            cfHandler.setEnabled(false);
            
            bNew.setVisible(false);
            bEdit.setVisible(false);
            bDelete.setVisible(false);
            bSaveNew.setVisible(false);
        } else if (state == ActionState.SHOW || state == ActionState.EDIT) {
            cfStatus.removeAllItems();
            cfStatus.addItem("DRAFT");
            cfStatus.addItem("ACTIVE");
            cfStatus.addItem("INACTIVE");

            cfHandler.removeAllItems();
            cfHandler.addItem("NO ACTION");
            cfHandler.addItem("ACTIVATED");
            cfHandler.addItem("DEACTIVATED");
            cfHandler.addItem("DELETED");

            if (state == ActionState.SHOW) {
                cfStatus.setEnabled(false);
                cfHandler.setEnabled(false);
            } else {
                cfStatus.setEnabled(false);
                cfStatus.setEnabled(true);
            }
        }
    }

    /**
     *
     */
    public void initData() {
        if (state == ActionState.SHOW || state == ActionState.EDIT) {
            if (object != null) {
                tfName.setText(object.getName());
                tfText.setText(object.getText());
                cfStatus.setSelectedIndex(object.getStatus());
                cfHandler.setSelectedIndex(0);
            }
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    /**
     * 
     * @return 
     */
    private boolean isValidModel() {
        if (tfName.getText().equals("")) {
            tfName.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            return false;
        }  
        
        if (tfText.getText().equals("")) {
            tfText.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            return false;
        }
        
        return true;
    }
    
    /**
     * 
     */
    private void create() {
        firePropertyChange(PropertyChangeField.SAVING.toString(), false, true);
        
        object = new MessageTemplate();
        object.setName(tfName.getText().trim());
        object.setText(tfText.getText().trim());
        
        final SwingWorker<Boolean, Void> t1 = new SwingWorker<Boolean, Void>() {

            /**
             *
             * @return @throws Exception
             */
            @Override
            protected Boolean doInBackground() throws Exception {
                boolean saved = false;
                try {
                    hSession = HibernateUtil.getSessionFactory().openSession();
                    hSession.getTransaction().begin();
                    hSession.save(object);
                    hSession.getTransaction().commit();
                    hSession.close();
                    saved = true;
                } catch (Exception ex) {
                    Logger.getLogger(MessageTemplateActionForm.class.getName()).log(Level.SEVERE, null, ex);
                    saved = false;
                }
                return saved;
            }
        };
        t1.addPropertyChangeListener(new PropertyChangeListener() {
            /**
             *
             * @param evt
             */
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("state".equals(evt.getPropertyName())
                         && SwingWorker.StateValue.DONE == evt.getNewValue()) {
                    try {
                        firePropertyChange(PropertyChangeField.SAVING.toString(), true, false);
                        if (t1.get() == true) {
                            JOptionPane.showMessageDialog(null, "Saving new template successfull", "New Message Template", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (InterruptedException | ExecutionException ex) {
                        Logger.getLogger(MessageTemplateActionForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 }
            }
        });
        t1.execute();
    }
    
    private void update() {
        
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ActionListener Implementation">
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
            JButton bb = (JButton) e.getSource();
            
            if(bb == bSave) {
                if(!isValidModel()) {
                    return;
                }
                
                create();
            }
        }
    }
    // </editor-fold>
}
