package com.softhaxi.shortsage.v1.table.model;

/**
 * Outbox Model was defined for showing message on table.
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class OutboxModelTable extends RowModelTable<Message> {
    
    private static final String[] COLUMN_OUTBOX = {
        "Contact",
        "Message",
        "Date",
        "Status",
    };
    
    public OutboxModelTable() {
        super(Arrays.asList(COLUMN_OUTBOX));
        setRowClass(Message.class);
        setModelEditable(false);
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        Message message = getRow(row);
        
        switch(column) {
          case 0:
            return message.getContact();
          case 1:
            return message.getText();
          case 2:
            return message.getDate();
          case 3:
            return message.getStatus();
          default:
            return null;
        }
    }
    
    public void setValueAt(Object value, int row, int column) {
        Message message = getRow(row);
        
        switch(column) {
            case 0:
              message.setContact((String) value);
              break;
            case 1:
              message.setText((String) value);
              break;
            case 2:
              message.setDate((Date) value);
              break;
            case 3:
              message.setStatus((String) value);
              break;
        }
        fireTableCellUpdated(row, column);
    }
}
