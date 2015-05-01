package com.softhaxi.shortsage.v1.lookup;

import com.softhaxi.shortsage.v1.dto.Contact;
import java.awt.BorderLayout;
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
    
    private List<Contact> contacts;

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

    private void initComponents() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 400));
        
        initSearchPanel();
        initListPanel();
        initButtonPanel();
    }
    
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
    
    private void initListPanel() {
        JPanel pList = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        pList.setLayout(layout);
        
        JLabel lLeft = new JLabel("Avaliable Contacts");
        JLabel lRight = new JLabel("Selected Contacts");
//        pList.setLayout(new BoxLayout(pList, BoxLayout.LINE_AXIS));
//        DesignGridLayout layout = new DesignGridLayout(pList);
        
        String[] contacts = new String[] {
          "Andri Kulkia",
          "Heidi Lian",
          "Balman Tri"
        };
        
        for(String contact : contacts) {
            mdLeft.addElement(contact);
        }
        
        ldLeft = new JList(mdLeft);
//        ldLeft.setPreferredSize(new Dimension(140, ldLeft.getHeight()));
        ldLeft.setVisibleRowCount(10);
        ldLeft.setFixedCellHeight(20);
        ldLeft.setFixedCellWidth(140);
        ldLeft.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        
        JPanel pSelection = new JPanel(new GridLayout(6, 1, 0, 4));
//        pSelection.setPreferredSize(new Dimension(40, pSelection.getHeight()));
        pSelection.add(new JPanel());
        pSelection.add(new JPanel());
        
        bSelect = new JButton(">>");
        bDeselect = new JButton("<<");
        
        pSelection.add(bSelect);
        pSelection.add(bDeselect);
        
        pSelection.add(new JPanel());
        pSelection.add(new JPanel());
        
        ldRight = new JList(mdRight);
//        ldLeft.setPreferredSize(new Dimension(140, ldLeft.getHeight()));
        ldLeft.setVisibleRowCount(10);
        ldLeft.setFixedCellHeight(20);
        ldLeft.setFixedCellWidth(140);
        ldLeft.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
//        pList.add(Box.createRigidArea(new Dimension(10,0)));
//        pList.add(new JScrollPane(ldLeft));
//        pList.add(Box.createRigidArea(new Dimension(5,0)));
//        pList.add(pSelection);
//        pList.add(Box.createRigidArea(new Dimension(5,0)));
//        pList.add(new JScrollPane(ldRight));
//        pList.add(Box.createRigidArea(new Dimension(10,0)));
        
//        layout.row()
//                .grid().add(new JScrollPane(ldLeft), 3)
//                .grid().add(pSelection)
//                .grid().add(new JScrollPane(ldRight), 3);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        pList.add(lLeft, gbc);
        gbc.gridx = 2;
        pList.add(lRight, gbc);
        
        gbc.weighty = 1.0;
        gbc.weightx = 1.0;
        gbc.gridheight = 2;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pList.add(new JScrollPane(ldLeft), gbc);
        
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.weightx = 0;
        pList.add(bSelect, gbc);
        gbc.gridy = 2;
        pList.add(bDeselect, gbc);

        gbc.gridheight = 2;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;

        pList.add(new JScrollPane(ldRight), gbc);
        
        add(pList, BorderLayout.CENTER);
    }
    
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
    
    private void loadData() {
        
    }
}
