package com.softhaxi.shortsage.v1.lookup;

import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.jdesktop.swingx.JXTable;

public class ContactSearch extends JPanel {

    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private JComboBox cfCategory;
    private JXSearchField sfKeyword;
    private JList ldLeft, ldRight;
    private JButton bOK, bCancel, bClear,
        bSelect, bDeselect;
    
    private DefaultListModel mdLeft, mdRight;

    /**
     * Constructor
     */
    public ContactPersonSearch() {
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
        JPanel pSearch = new JPanel(new GridLayout(2, 1, 5, 0));
        
        cfCategory = new JComboBox();
        cfCategory.addItem("Contact Group");
        cfCategory.addItem("Contact Person");
        cfCategory.setSelectedIndex(1);
        cfCategory.setEnabled(false);
        
        pSearch.add(cfCategory);
        
        add(pSearch, BorderLayout.NORTH);
    }
    
    private void initListPanel() {
        JPanel pList = new JPanel();
        DesignGridLayout layout = new DesignGridLayout(pList);
        
        String[] contacts = new String[] {
          "Andri Kulkia",
          "Heidi Lian",
          "Balman Tri"
        };
        
        for(String contact : contacts) {
            mdLeft.addElement(contact);
        }
        
        ldLeft = new JList(mdLeft);
        //ldLeft.setPreferredSize(new Dimension(140, ldLeft.getHeight()));
        ldLeft.setVisibleRowCount(10);
        ldLeft.setFixedCellHeight(20);
        ldLeft.setFixedCellWidth(140);
        ldLeft.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        JPanel pSelection = new JPanel(1, 6, 0, 4);
        //pSelection.setPreferredSize(new Dimension(40, pSelection.getHight()));
        pSelection.add(new JPanel());
        pSelection.add(new JPanel());
        
        bSelect = new JButton(">>");
        bDeselect = new JButton("<<");
        
        pSelection.add(bSelect);
        pSelection.add(bDeselect);
        
        pSelection.add(new JPanel());
        pSelection.add(new JPanel());
        
        ldRight = new JList(mdRight);
        //ldLeft.setPreferredSize(new Dimension(140, ldLeft.getHeight()));
        ldLeft.setVisibleRowCount(10);
        ldLeft.setFixedCellHeight(20);
        ldLeft.setFixedCellWidth(140);
        ldLeft.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        layout.row().grid().add(new JScrollPane(ldLeft), 4).add(pSelection).add(new JScrollPane(ldRight, 4));
        
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
    
    private void loadData() {
        
    }
}