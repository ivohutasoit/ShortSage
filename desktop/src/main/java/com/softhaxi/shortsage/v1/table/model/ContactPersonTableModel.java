package com.softhaxi.shortsage.v1.table.model;

import com.softhaxi.shortsage.v1.dto.ContactPerson;
import com.softhaxi.shortsage.v1.table.RowTableModel;

public class ContactPersonTableModel extends RowTableModel<ContactPerson> {

    public ContactPersonTableModel() {
        super(ContactPerson.class);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
