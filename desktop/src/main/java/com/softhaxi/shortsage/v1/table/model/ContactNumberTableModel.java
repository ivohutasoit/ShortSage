package com.softhaxi.shortsage.v1.table.model;

import com.softhaxi.shortsage.v1.dto.ContactNumber;
import com.softhaxi.shortsage.v1.table.RowTableModel;

public class ContactNumberTableModel extends RowTableModel<ContactNumber> {

    public ContactNumberTableModel() {
        super(ContactNumber.class);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
