package com.softhaxi.shortsage.v1.component;

import static com.softhaxi.shortsage.v1.MainApp.main;
import java.awt.Component;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 * Reference
 * <a href="https://tips4java.wordpress.com/2008/11/18/row-number-table/">Numbered
 * Row Table</a>
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class CNumberedTable extends JTable
        implements ChangeListener, PropertyChangeListener, TableModelListener {

    private JTable mainTable;

    public CNumberedTable(JTable mainTable) {
        this.mainTable = mainTable;
        mainTable.addPropertyChangeListener(this);
        mainTable.getModel().addTableModelListener(this);

        setFocusable(false);
        setAutoCreateColumnsFromModel(false);
        setSelectionModel(mainTable.getSelectionModel());

        TableColumn column = new TableColumn();
        column.setHeaderValue("");
        addColumn(column);
        column.setCellRenderer(new CNumberedRenderer());

        getColumnModel().getColumn(0).setPreferredWidth(24);
        setPreferredScrollableViewportSize(getPreferredSize());
    }

    @Override
    public void addNotify() {
        super.addNotify();

        // Keep scrolling of the row table
        if (getParent() instanceof JViewport) {
            JViewport viewport = (JViewport) getParent();
            viewport.addChangeListener(this);
        }
    }

    @Override
    public int getRowCount() {
        return mainTable.getRowCount();
    }

    @Override
    public int getRowHeight(int row) {
        int rowHeight = mainTable.getRowHeight(row);

        if (rowHeight != super.getRowHeight(row)) {
            super.setRowHeight(row, rowHeight);
        }

        return rowHeight;
    }

    @Override
    public Object getValueAt(int row, int column) {
        return Integer.toString(row + 1);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // Keep the scrolling of the row table in sync with main table

        JViewport viewport = (JViewport) e.getSource();
        JScrollPane scrollPane = (JScrollPane) viewport.getParent();
        scrollPane.getVerticalScrollBar().setValue(viewport.getViewPosition().y);
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        // Keep the row table in sync with the main table

        if ("selectionModel".equals(e.getPropertyName())) {
            setSelectionModel(mainTable.getSelectionModel());
        }

        if ("rowHeight".equals(e.getPropertyName())) {
            repaint();
        }

        if ("model".equals(e.getPropertyName())) {
            mainTable.getModel().addTableModelListener(this);
            revalidate();
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        revalidate();
    }

    private static class CNumberedRenderer extends DefaultTableCellRenderer {

        public CNumberedRenderer() {
            setHorizontalAlignment(JLabel.RIGHT);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            if (table != null) {
                JTableHeader header = table.getTableHeader();
                if (header != null) {
                    setForeground(header.getForeground());
                    setBackground(header.getBackground());
                    setFont(header.getFont());
                }
            }

            if (isSelected) {
                setFont(getFont().deriveFont(Font.BOLD));
            }
            setText((value == null) ? "" : value.toString());
            setBorder(UIManager.getBorder("TableHeader.cellBorder"));

            return this;
        }
    }
}
