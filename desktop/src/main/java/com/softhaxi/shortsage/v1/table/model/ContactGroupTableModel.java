package com.softhaxi.shortsage.v1.table.model;

import com.softhaxi.shortsage.v1.model.ContactGroup;
import com.softhaxi.shortsage.v1.table.RowTableModel;

public class ContactGroupTableModel extends RowTableModel<ContactGroup> {
    
    public ContactGroupTableModel() {
        super(ContactGroup.class);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
