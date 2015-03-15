package com.softhaxi.shortsage.v1.table.model;

import com.softhaxi.shortsage.v1.dto.Gateway;
import com.softhaxi.shortsage.v1.table.RowTableModel;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Hutasoit
 */
public class GatewayTableModel extends RowTableModel<Gateway> {

    private static final String[] COLUMN_NAME = {
        "Gateway Name",
        "Interface",
        "Baud Rate",
        "Manufacture",
        "Model",
        "Status",};
    
    public GatewayTableModel() {
        super(Arrays.asList(COLUMN_NAME));
        
        setRowClass(Gateway.class);
        setModelEditable(false);
    }

    public GatewayTableModel(List<Gateway> gateways) {
        super(gateways, Arrays.asList(COLUMN_NAME));
        setRowClass(Gateway.class);
        setModelEditable(false);
    }

    @Override
    public Object getValueAt(int row, int column) {
        Gateway obj = getRow(row);

        switch (column) {
            case 0:
                return obj.getName();
            case 1:
                return obj.getPort();
            case 2:
                return obj.getBaudRate();
            case 3:
                return obj.getManufacture();
            case 4:
                return obj.getModel();
            case 5:
                switch (obj.getStatus()) {
                    case 1:
                        return "Started";
                    case 2:
                        return "Stoped";
                    default:
                        return "Deleted";
                }
            default:
                return null;
        }
    }

}
