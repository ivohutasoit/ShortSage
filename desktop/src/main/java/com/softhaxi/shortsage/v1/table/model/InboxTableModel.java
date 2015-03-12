package com.softhaxi.shortsage.v1.table.model;

import com.softhaxi.shortsage.v1.model.Message;
import com.softhaxi.shortsage.v1.table.RowTableModel;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Hutasoit
 */
public class InboxTableModel extends RowTableModel<Message> {

    private static String[] COLUMN_INBOX
            = {
                "Contact",
                "Message",
                "Date"
            };

    public InboxTableModel(int type) {
        super(Arrays.asList(COLUMN_INBOX));

        setRowClass(Message.class);
        setModelEditable(false);
    }

    @Override
    public Object getValueAt(int row, int column) {
        Message message = getRow(row);

        switch (column) {
            case 0:
                return message.getReciever();
            case 1:
                return message.getMessage();
            case 2:
                return message.getDate();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        Message message = getRow(row);

        switch (column) {
            case 0:
                message.setReciever((String) value);
                break;
            case 1:
                message.setMessage((String) value);
                break;
            case 2:
                message.setDate((Date) value);
                break;
        }

        fireTableCellUpdated(row, column);
    }
}
