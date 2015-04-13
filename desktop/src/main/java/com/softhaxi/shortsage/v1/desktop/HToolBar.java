package com.softhaxi.shortsage.v1.desktop;

import com.softhaxi.shortsage.v1.enums.PropertyChangeField;
import com.softhaxi.shortsage.v1.forms.NewMessageActionForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class HToolBar extends JToolBar 
    implements ActionListener {
    
    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private JButton biMessage;
    private JButton biLogout;

    public HToolBar() {
        setBorder(new EmptyBorder(0, 2, 0, 2));
        
        biMessage = new JButton(RES_GLOBAL.getString("label.new.message"),
                new ImageIcon(getClass().getClassLoader().getResource("images/ic_new.png")));
        add(biMessage);
        add(Box.createHorizontalGlue());

        biLogout = new JButton(RES_GLOBAL.getString("label.user.logout"),
                new ImageIcon(getClass().getClassLoader().getResource("images/ic_logout.png")));
        add(biLogout);
        
        initListeners();
    }
    
    private void initListeners() {
        biMessage.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
            JButton source = (JButton) e.getSource();
            if(source == biMessage) {
                final JDialog dialog = new JDialog();
                dialog.setModal(true);
                dialog.setTitle(RES_GLOBAL.getString("label.new") + " Message");
                NewMessageActionForm form = new NewMessageActionForm();
                form.addPropertyChangeListener(new PropertyChangeListener() {
                    /**
                     *
                     * @param evt
                     */
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());

                        if (evt.getPropertyName().equals(PropertyChangeField.SAVING.toString())) {
                            boolean value = (boolean) evt.getNewValue();
                            if (value == false) {
                                dialog.dispose();
                            }
                        }
                    }
                });
                dialog.add(form);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        }
    }
}
