package com.softhaxi.shortsage.v1.table.model;

import com.softhaxi.shortsage.v1.dto.SystemUser;
import com.softhaxi.shortsage.v1.table.RowTableModel;

public class SystemUserTableModel extends RowTableModel<SystemUser> {
    
    public SystemUserTableModel() {
        super(SystemUser.class);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
