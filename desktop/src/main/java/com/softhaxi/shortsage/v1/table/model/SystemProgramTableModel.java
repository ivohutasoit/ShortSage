package com.softhaxi.shortsage.v1.table.model;

import com.softhaxi.shortsage.v1.dto.SystemProgram;
import com.softhaxi.shortsage.v1.table.RowTableModel;

public class SystemProgramTableModel extends RowTableModel<SystemProgram> {

    public SystemProgramTableModel() {
        super(SystemProgram.class);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
  
}
