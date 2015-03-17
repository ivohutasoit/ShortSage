package com.softhaxi.shortsage.v1.components;

import com.softhaxi.shortsage.v1.enums.ActionState;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public abstract class CActionForm<T> extends JPanel {

    protected ActionState state;
    protected T object;

    protected JToolBar tBar;
    protected JButton bNew;
    protected JButton bEdit;
    protected JButton bDelete;
    protected JButton bSave;
    protected JButton bSaveNew;
    protected JButton bCancel;
    protected JButton bRefresh;

    protected CActionForm() {
        this(ActionState.CREATE, null);
    }

    protected CActionForm(T object) {
        this(ActionState.SHOW, object);
    }

    protected CActionForm(ActionState state, T object) {
        this.state = state;
        this.object = object;

        initComponents();
        initData();

        addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                // Component added somewhere
                initState();
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
                // Component removed from container
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
                // Component container moved
            }
        });
    }

    /**
     *
     */
    public void initComponents() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(2, 2, 0, 1));

        tBar = new JToolBar();
        tBar.setFloatable(false);
//        tBar.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(2, 2, 2, 2)));

        bNew = new JButton("New", new ImageIcon(getClass().getClassLoader().getResource("images/ic_new.png")));
        tBar.add(bNew);

        bEdit = new JButton("Edit", new ImageIcon(getClass().getClassLoader().getResource("images/ic_edit.png")));
        tBar.add(bEdit);

        bSave = new JButton("Save", new ImageIcon(getClass().getClassLoader().getResource("images/ic_save.png")));
        tBar.add(bSave);

        bSaveNew = new JButton("Save and New", new ImageIcon(getClass().getClassLoader().getResource("images/ic_save_as.png")));
        tBar.add(bSaveNew);
        tBar.add(new JToolBar.Separator());

        bDelete = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_delete.png")));
        tBar.add(bDelete);

        bCancel = new JButton("Cancel", new ImageIcon(getClass().getClassLoader().getResource("images/ic_cancel.png")));
        tBar.add(bCancel);

        add(tBar, BorderLayout.NORTH);
    }

    /**
     *
     */
    public void initState() {
        System.out.println(getRootPane().getParent().toString());
        System.out.println(getParent().toString());
        CDialog dialog = null;

        if (getRootPane().getParent() instanceof CDialog) {
            dialog = (CDialog) getRootPane().getParent();
        }

        if (dialog != null) {
            if (state == ActionState.CREATE || state == ActionState.EDIT) {
                bNew.setVisible(false);
                bEdit.setVisible(false);
                bDelete.setVisible(false);
            }
        } else {
            bSave.setVisible(false);
            bSaveNew.setVisible(false);
            bCancel.setVisible(false);
        }
    }

    /**
     * Load data to panel
     */
    public abstract void initData();
}
