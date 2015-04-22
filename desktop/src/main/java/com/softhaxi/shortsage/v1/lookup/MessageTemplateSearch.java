package com.softhaxi.shortsage.v1.lookup;

import com.softhaxi.shortsage.v1.desktop.HNumberedTable;
import com.softhaxi.shortsage.v1.desktop.HWaitDialog;
import com.softhaxi.shortsage.v1.dto.MessageTemplate;
import com.softhaxi.shortsage.v1.renderer.TableHeaderCenterRender;
import com.softhaxi.shortsage.v1.util.HibernateUtil;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import net.java.dev.designgridlayout.DesignGridLayout;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXTable;

public class MessageTemplateSearch extends JPanel
        implements ActionListener {

    private final static ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private final static String[] COLUMN_NAMES = {
        "Name",
        "Text",
        "Created Date"
    };

    private JXSearchField sfKeyword;
    private JXTable ttData;

    private DefaultTableModel mData;
    private List<MessageTemplate> data;
    private MessageTemplate object;

    private JButton bOK, bCancel, bClear;

    public MessageTemplateSearch() {
        initComponents();
        initListeners();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 400));

        initSearchPanel();
        initTablePanel();
        initButtonPanel();
    }

    private void initSearchPanel() {
        JPanel pKeyword = new JPanel();
        DesignGridLayout layout = new DesignGridLayout(pKeyword);

        sfKeyword = new JXSearchField(RES_GLOBAL.getString("label.search.item"));
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.search"))).add(sfKeyword);

        add(pKeyword, BorderLayout.NORTH);
    }

    private void initTablePanel() {
        ttData = new JXTable();
        ttData.setEditable(false);

        mData = new DefaultTableModel(COLUMN_NAMES, 0);
        ttData.setModel(mData);
        ttData.setShowGrid(false);
        ttData.setIntercellSpacing(new Dimension(0, 0));
        ttData.getTableHeader().setDefaultRenderer(new TableHeaderCenterRender(ttData));
        ttData.getColumnModel().getColumn(0).setPreferredWidth(100);
        ttData.getColumnModel().getColumn(1).setPreferredWidth(450);
        ttData.getColumnModel().getColumn(2).setPreferredWidth(50);

        JScrollPane sPane = new JScrollPane(ttData);
        HNumberedTable rowTable = new HNumberedTable(ttData);
        sPane.setRowHeaderView(rowTable);
        sPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,
                rowTable.getTableHeader());

        add(sPane, BorderLayout.CENTER);
    }

    private void initButtonPanel() {
        bOK = new JButton(RES_GLOBAL.getString("label.ok"));
        bCancel = new JButton(RES_GLOBAL.getString("label.cancel"));
        bClear = new JButton(RES_GLOBAL.getString("label.clear.value"));

        //Lay out the buttons from left to right.
        JPanel pButton = new JPanel();
        pButton.setLayout(new BoxLayout(pButton, BoxLayout.LINE_AXIS));
        pButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
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
        sfKeyword.addActionListener(this);
        bOK.addActionListener(this);
        bCancel.addActionListener(this);
        bClear.addActionListener(this);
    }

    private void initTableModel() {
        mData = new DefaultTableModel(COLUMN_NAMES, 0);
        Object[] obj = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        for (MessageTemplate template : data) {
            obj = new Object[3];
            obj[0] = template.getName();
            obj[1] = template.getText();
            obj[2] = sdf.format(template.getCreatedDate());

            mData.addRow(obj);
        }
        ttData.setModel(mData);

        if (data.size() > 0) {
            ttData.setRowSelectionInterval(0, 0);
        }
    }

    private void loadData() {
        final HWaitDialog dialog = new HWaitDialog("Load Data");
        final SwingWorker<Boolean, Void> t1 = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() {
                try {
                    Session hSession = HibernateUtil.getSessionFactory().openSession();
                    hSession.getTransaction().begin();
                    Query query = hSession.getNamedQuery("MessageTemplate.All");
                    data = query.list();
                    hSession.getTransaction().commit();
                    hSession.close();
                    return true;
                } catch (Exception ex) {
                    Logger.getLogger(MessageTemplateSearch.class.getName()).log(Level.SEVERE, null, ex);
                }
                return false;
            }

            @Override
            protected void done() {
                if (!isCancelled()) {
                    initTableModel();
                }
            }
        };

        t1.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("state".equals(evt.getPropertyName())
                        && SwingWorker.StateValue.DONE == evt.getNewValue()) {
                    try {
                        if (t1.get() == true) {
                            dialog.setVisible(false);
                            dialog.dispose();
                        }
                    } catch (InterruptedException | ExecutionException ex) {
                        Logger.getLogger(MessageTemplateSearch.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        t1.execute();
        dialog.setVisible(true);
    }

    public MessageTemplate getUserData() {
        return object;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton bb = (JButton) e.getSource();
            if (bb == bOK) {
                if (ttData.getSelectedRow() != -1) {
                    object = data.get(ttData.getSelectedRow());
                }
                firePropertyChange("select", false, true);
            } else if (bb == bCancel) {
                firePropertyChange("cancel", false, true);
            } else if (bb == bClear) {
                firePropertyChange("clear", false, true);
            }
        }
    }
}
