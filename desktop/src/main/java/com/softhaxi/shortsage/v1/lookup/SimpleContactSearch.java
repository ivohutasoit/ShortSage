package com.softhaxi.shortsage.v1.lookup;

import com.softhaxi.shortsage.v1.dto.Contact;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import org.jdesktop.swingx.JXSearchField;

/**
 * http://www.macs.hw.ac.uk/cs/java-swing-guidebook/?name=JList&page=3
 * 
 * http://stackoverflow.com/questions/13620894/gridbag-layout-problems
 * 
 * http://stackoverflow.com/questions/26063639/jlist-control-size-changes-while-running-the-jar-file%5C
 * 
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class SimpleContactSearch extends JPanel {

    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private JComboBox cfCategory;
    private JXSearchField sfKeyword;
    private JList ldLeft, ldRight;
    private JButton bOK, bCancel, bClear,
        bSelect, bDeselect;
    
    private DefaultListModel mdLeft, mdRight;
    
    private List<Contact> aContacts, sContacts;

    /**
     * Constructor
     */
    public SimpleContactSearch() {
        setVisible(false);
        
        mdLeft = new DefaultListModel();
        mdRight = new DefaultListModel();
        
        initComponents();
        initListeners();
    }

    /**
     * 
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 400));
        
        initSearchPanel();
        initListPanel();
        initButtonPanel();
    }
    
    /**
     * 
     */
    private void initSearchPanel() {
        JPanel pSearch = new JPanel(new GridLayout(1, 2, 5, 0));
        pSearch.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        cfCategory = new JComboBox();
        cfCategory.addItem("Contact Group");
        cfCategory.addItem("Contact Person");
        cfCategory.addItem("Search Result");
        cfCategory.setSelectedIndex(1);
        cfCategory.setEnabled(false);
        
        sfKeyword = new JXSearchField("Contact or Group Name");
        
        pSearch.add(cfCategory);
        pSearch.add(sfKeyword);
        
        add(pSearch, BorderLayout.NORTH);
    }
    
    /**
     * 
     */
    private void initListPanel() {
        JPanel pList = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        pList.setLayout(layout);
        pList.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 0, 0, 2);
        pList.add(new JLabel("Avaliable Contacts"), gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 2, 0, 0);
        pList.add(new JLabel("Selected Contacts"), gbc);
       
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 0, 2);
        ldLeft = new JList();
        JScrollPane slLeft = new JScrollPane(ldLeft);
        slLeft.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        
        pList.add(slLeft, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 1;
        JPanel pSelect = new JPanel(new GridLayout(2, 1, 2, 2));
        bSelect = new JButton(">>");
        bDeselect = new JButton("<<");
        pSelect.add(bSelect);
        pSelect.add(bDeselect);
        pList.add(pSelect, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 2, 0, 0);
        ldRight = new JList();
        JScrollPane slRight = new JScrollPane(ldRight);
        slRight.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        pList.add(slRight, gbc);
        
        add(pList, BorderLayout.CENTER);
    }
    
    
    /**
     * 
     */
    private void initButtonPanel() {
        bOK = new JButton(RES_GLOBAL.getString("label.ok"));
        bCancel = new JButton(RES_GLOBAL.getString("label.cancel"));
        bClear = new JButton(RES_GLOBAL.getString("label.clear.value"));
        
        bOK.setPreferredSize(bClear.getPreferredSize());
        bCancel.setPreferredSize(bClear.getPreferredSize());

        //Lay out the buttons from left to right.
        JPanel pButton = new JPanel();
        pButton.setLayout(new BoxLayout(pButton, BoxLayout.LINE_AXIS));
        pButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pButton.add(Box.createHorizontalGlue());
        pButton.add(bOK);
        pButton.add(Box.createRigidArea(new Dimension(5, 0)));
        pButton.add(bCancel);
        pButton.add(Box.createRigidArea(new Dimension(5, 0)));
        pButton.add(bClear);

        add(pButton, BorderLayout.PAGE_END);
    }

    /**
     * 
     */
    private void initListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                loadData();
            }
        });
    }
    
    /**
     * 
     * @return 
     */
    public List<Contact> getUserData() {
        return new ArrayList<Contact>();
    }
    
    /**
     * 
     */
    private void loadData() {
        
    }
}
