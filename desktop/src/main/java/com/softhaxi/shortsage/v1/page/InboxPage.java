package com.softhaxi.shortsage.v1.page;

import com.softhaxi.shortsage.v1.component.CNumberedTable;
import com.softhaxi.shortsage.v1.component.CSearchField;
import com.softhaxi.shortsage.v1.component.CZebraTable;
import com.softhaxi.shortsage.v1.dto.Message;
import com.softhaxi.shortsage.v1.dummies.MessageDummy;
import com.softhaxi.shortsage.v1.table.model.InboxTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import org.smslib.InboundMessage;
import org.smslib.InboundMessage.MessageClasses;
import org.smslib.Service;

/**
 * Reference <a href="https://github.com/oliverwatkins/swing_library">Swing Library</a>
 * 
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class InboxPage extends CPanel {
    
    private JXSearchField fSearch;
    private CZebraTable tData;
    
    private JPanel pDetail;
    private List<Message> dbMessages;
    private List<InboundMessage> mdMessages;

    public InboxPage() {
        super();
    }

    @Override
    private void initComponents() {
        CSearchField pSearch = new CSearchField() {

            @Override
            public void doSearch() {
                JOptionPane.showMessageDialog(null, "Search action was not implemented full!");
            }
        };

        add(pSearch, BorderLayout.NORTH);

        pDetail = new JPanel(new BorderLayout(0, 3));
        JToolBar tpDetail = new JToolBar();
        tpDetail.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(2, 2, 2, 2)));
        tpDetail.setFloatable(false);

        tpDetail.add(new JButton("New", new ImageIcon(getClass().getClassLoader().getResource("images/ic_new.png"))));
        tpDetail.add(new JButton("Edit", new ImageIcon(getClass().getClassLoader().getResource("images/ic_edit.png"))));
        tpDetail.add(new JToolBar.Separator());
        tpDetail.add(new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_delete.png"))));
        tpDetail.add(Box.createHorizontalGlue());
        tpDetail.add(new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_refresh.png"))));

        pDetail.add(tpDetail, BorderLayout.NORTH);
        add(pDetail, BorderLayout.CENTER);
        
        initTable();
    }

    private void initTable() {
        CZebraTable table = new CZebraTable(new InboxTableModel(MessageDummy.getInbox()));
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(700);

        CNumberedTable rowTable = new CNumberedTable(table);
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setRowHeaderView(rowTable);
        scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,
                rowTable.getTableHeader());

        //Add the scroll pane to this panel.
        pDetail.add(scrollPane, BorderLayout.CENTER);
    }
    
    @Override
    public void initData() {
           
    }

    private class LoadMessageTask extends SwingWorker<Boolean, Void> {

        @Override
        protected Boolean doInBackground() throws Exception {
            mdMessages = new ArrayList<InboundMessage>();
            Service.getInstance().readMessages(mdMessages, MessageClasses.ALL);
            Message message = null;
            for (InboundMessage msg : mdMessages) {
                System.out.println(msg);
            }
            return true;
        }

    }
}
