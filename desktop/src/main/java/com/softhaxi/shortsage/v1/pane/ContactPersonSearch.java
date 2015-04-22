package com.softhaxi.shortsage.v1.pane;

import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.jdesktop.swingx.JXTable;

public class ContactPersonSearch extends JPanel {

    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private static final String[] COLUMN_NAMES = new String[]{
        ""
    };

    private JXTable ttData;
    private JButton bOK, bCancel, bClear;

    public ContactPersonSearch() {

        initComponents();
        initListeners();
    }

    private void initComponents() {

    }

    private void initListeners() {

    }
}
