package com.softhaxi.shortsage.v1.page;

import com.softhaxi.shortsage.v1.component.CDialog;
import com.softhaxi.shortsage.v1.component.CNumberedTable;
import com.softhaxi.shortsage.v1.component.CSearchField;
import com.softhaxi.shortsage.v1.component.CZebraTable;
import com.softhaxi.shortsage.v1.dto.Gateway;
import com.softhaxi.shortsage.v1.forms.GatewayActionForm;
import com.softhaxi.shortsage.v1.table.model.GatewayTableModel;
import com.softhaxi.shortsage.v1.util.HibernateUtil;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class GatewayPage extends JPanel
        implements ActionListener {

    private JPanel pDetail;

    private JButton bNew;
    private JButton bEdit;
    private JButton bDelete;
    private JButton bRefresh;

    /**
     *
     */
    public GatewayPage() {
        initComponents();
        initTable();
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 4));
        setBorder(new EmptyBorder(4, 4, 4, 4));

        JPanel pUtil = new JPanel(new GridLayout(1, 2));
        pUtil.add(new CSearchField() {

            @Override
            public void doSearch() {
                JOptionPane.showMessageDialog(null, "Search action was not implemented full!");
            }
        });

        add(pUtil, BorderLayout.NORTH);

        pDetail = new JPanel(new BorderLayout(0, 3));
        JToolBar tpDetail = new JToolBar();
        tpDetail.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(2, 2, 2, 2)));
        tpDetail.setFloatable(false);

        bNew = new JButton("New", new ImageIcon(getClass().getClassLoader().getResource("images/ic_new.png")));
        bNew.addActionListener(this);
        tpDetail.add(bNew);

        bEdit = new JButton("Edit", new ImageIcon(getClass().getClassLoader().getResource("images/ic_edit.png")));
        tpDetail.add(bEdit);
        tpDetail.add(new JToolBar.Separator());

        bDelete = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_delete.png")));
        tpDetail.add(bDelete);
        tpDetail.add(Box.createHorizontalGlue());

        bRefresh = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_refresh.png")));
        tpDetail.add(bRefresh);

        pDetail.add(tpDetail, BorderLayout.NORTH);
        add(pDetail, BorderLayout.CENTER);
    }

    private void initTable() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from " + Gateway.class.getName());
        CZebraTable table = new CZebraTable(new GatewayTableModel(q.list()));
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));

        CNumberedTable rowTable = new CNumberedTable(table);
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setRowHeaderView(rowTable);
        scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,
                rowTable.getTableHeader());

        //Add the scroll pane to this panel.
        pDetail.add(scrollPane, BorderLayout.CENTER);
        session.close();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton source = (JButton) e.getSource();

            if (source == bNew) {
                final GatewayActionForm form = new GatewayActionForm();
                CDialog dialog = new CDialog(null, form, "Create Gateway", true);
                try {
                    Toolkit kit = Toolkit.getDefaultToolkit();
                    dialog.setIconImage(kit.createImage(ClassLoader.getSystemResource("images/ic_logo.png")));
                } catch (Exception ex) {
                    System.err.printf(ex.getMessage());
                }

                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.out.println("Hello moto!");
                        initTable();
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        System.out.println("Hello moto!");
                        initTable();
                    }
                });
                dialog.setVisible(true);
            }
        }
    }
}