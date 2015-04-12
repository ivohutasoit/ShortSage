package com.softhaxi.shortsage.v1.page;

import com.softhaxi.shortsage.v1.desktop.HNumberedTable;
import com.softhaxi.shortsage.v1.renderer.TableHeaderCenterRender;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ResourceBundle;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import net.java.dev.designgridlayout.DesignGridLayout;
import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class OutboxPage extends JPanel {
    
    private final static ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private final static String[] COLUMN_NAME = new String[]{
        "Contact",
        "Message",
        "Date",
        "Status"
    };

    private JPanel pNorth;
    private JPanel pCenter;
    private JPanel pSouth;

    private JXSearchField tfSearch;
    private JComboBox cfViews;
    private JXTable ttData;
    
    private DefaultTableModel mData;
    
    /**
     * Main Constructor
     */
    public OutboxPage() {
        setVisible(false);
        initComponents();
        initListeners();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Region Inititalization">  
    /**
     * Initialize components of the panel
     */
    private void initComponents() {
        setLayout(new BorderLayout());

        initNorthPanel();
        initCenterPanel();
        initSouthPanel();
    }

    /**
     *
     */
    private void initNorthPanel() {
        pNorth = new JPanel();
        tfSearch = new JXSearchField(RES_GLOBAL.getString("label.search.item"));
        tfSearch.setSearchMode(JXSearchField.SearchMode.REGULAR);
        cfViews = new JComboBox();
        cfViews.addItem("All Items");

        DesignGridLayout layout = new DesignGridLayout(pNorth);
        layout.margins(0.5, 0.5, 0.5, 0.5);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.search") + " :"))
                .add(tfSearch)
                .grid(new JLabel(RES_GLOBAL.getString("label.view") + " :"))
                .add(cfViews);

        add(pNorth, BorderLayout.NORTH);
    }

    /**
     *
     */
    private void initCenterPanel() {
        pCenter = new JPanel(new BorderLayout());
        pCenter.setBorder(new EmptyBorder(0, 4, 4, 4));
        JToolBar pToolbar = new JToolBar();
        pToolbar.setFloatable(false);
        pToolbar.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(2, 2, 2, 2)));

        pToolbar.add(new JButton(RES_GLOBAL.getString("label.new"), new ImageIcon(getClass().getClassLoader().getResource("images/ic_new.png"))));
        pToolbar.add(new JButton(RES_GLOBAL.getString("label.edit"), new ImageIcon(getClass().getClassLoader().getResource("images/ic_edit.png"))));
        pToolbar.addSeparator();
        pToolbar.add(new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_delete.png"))));
        pToolbar.add(Box.createHorizontalGlue());
        pToolbar.add(new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_refresh.png"))));

        pCenter.add(pToolbar, BorderLayout.NORTH);

        ttData = new JXTable();
        ttData.setEditable(false);
        
        mData = new DefaultTableModel(COLUMN_NAME, 0);
        ttData.setModel(mData);
        ttData.setShowGrid(false);
        ttData.setIntercellSpacing(new Dimension(0, 0));
        ttData.getTableHeader().setDefaultRenderer(new TableHeaderCenterRender(ttData));
        ttData.getColumnModel().getColumn(0).setPreferredWidth(100);
        ttData.getColumnModel().getColumn(1).setPreferredWidth(450);
        ttData.getColumnModel().getColumn(2).setPreferredWidth(80);
        
        JScrollPane sPane = new JScrollPane(ttData);
        HNumberedTable rowTable = new HNumberedTable(ttData);
        sPane.setRowHeaderView(rowTable);
        sPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,
                rowTable.getTableHeader());
        
        pCenter.add(sPane, BorderLayout.CENTER);

        add(pCenter, BorderLayout.CENTER);
    }

    /**
     *
     */
    private void initSouthPanel() {

    }

    /**
     * Initialize listeners for all components of frame
     */
    private void initListeners() {
//        addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentShown(ComponentEvent e) {
//                JOptionPane.showMessageDialog(null, "This is dialog from Import Page");
//            }
//        });
    }
    // </editor-fold>   
}
