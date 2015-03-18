package com.softhaxi.shortsage.v1.table.model;

import com.softhaxi.shortsage.v1.dto.Message;
import com.softhaxi.shortsage.v1.table.RowTableModel;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    
    public InboxTableModel(List<Message> messages) {
        super(Arrays.asList(COLUMN_INBOX));

        for (Message message : messages) {
            addRow(message);
        }
        setRowClass(Message.class);
        setModelEditable(false);
    }

    @Override
    public Object getValueAt(int row, int column) {
        Message message = getRow(row);

        switch (column) {
            case 0:
                return message.getContact();
            case 1:
                return message.getText();
            case 2:
                return sdf.format(message.getDate());
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        Message message = getRow(row);

        switch (column) {
            case 0:
                message.setContact((String) value);
                break;
            case 1:
                message.setText((String) value);
                break;
            case 2:
                message.setDate((Date) value);
                break;
        }

        fireTableCellUpdated(row, column);
    }
}
