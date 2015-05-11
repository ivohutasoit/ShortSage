package com.softhaxi.shortsage.v1.model.table;

import com.softhaxi.shortsage.v1.dto.ContactPerson;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class SearchContactPersonTableModel extends AbstractTableModel {

    private final static String[] COLUMN_NAMES = {
        "Name", "Phone Number"
    };

    private List<ContactPerson> data;
    private HashMap<String, String> columns;

    public SearchContactPersonTableModel() {

    }

    public SearchContactPersonTableModel(List<ContactPerson> data) {
        this.data = data;
    }

    public List<ContactPerson> getData() {
        return this.data;
    }

    public void setData(List<ContactPerson> data) {
        this.data = data;
        fireTableDataChanged();
    }

    @Override
    public int getColumnCount() {
        if (columns == null) {
            return COLUMN_NAMES.length;
        }
        return columns.size();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        ContactPerson person = data.get(row);
        switch (col) {
            case 0:
                return person.getName();
            case 1:
                return person.getPhone();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
