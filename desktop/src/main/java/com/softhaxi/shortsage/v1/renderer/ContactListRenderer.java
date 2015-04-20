package com.softhaxi.shortsage.v1.renderer;

import com.softhaxi.shortsage.v1.dto.Contact;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 * Render list of contacts Reference
 * <a href="http://stackoverflow.com/questions/22266506/how-to-add-image-in-jlist">List
 * With Image</a>
 * <a href="http://www.nguyenvanquan7826.com/2014/04/25/java-swing-tuy-bien-jlist-jlist-custom-renderer/?lang=en">Testing</a>
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class ContactListRenderer extends JPanel
        implements ListCellRenderer<Contact> {

    private JLabel lfIcon;
    private JLabel lfName;
    private JLabel lfPhone;

    /**
     *
     */
    public ContactListRenderer() {
        lfIcon = new JLabel();
        lfName = new JLabel();
        lfPhone = new JLabel();

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        setLayout(layout);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weighty = 1.0;
        layout.setConstraints(lfIcon, gbc);
        add(lfIcon);

        gbc.weighty = 0.0;                   //reset to the default
        gbc.gridwidth = GridBagConstraints.REMAINDER; //end of row
        gbc.gridheight = 1;                   //reset to the default
        layout.setConstraints(lfName, gbc);
        add(lfName);
        
        layout.setConstraints(lfPhone, gbc);
        add(lfPhone);
    }

    /**
     *
     * @param list
     * @param value
     * @param index
     * @param isSelected
     * @param cellHasFocus
     * @return
     */
    @Override
    public Component getListCellRendererComponent(JList<? extends Contact> list,
            Contact value, int index, boolean isSelected, boolean cellHasFocus) {
        return this;
    }

}
