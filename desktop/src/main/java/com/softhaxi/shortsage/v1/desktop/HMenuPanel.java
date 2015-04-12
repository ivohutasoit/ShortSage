package com.softhaxi.shortsage.v1.desktop;

import com.softhaxi.shortsage.v1.dummies.MenuDummy;
import com.softhaxi.shortsage.v1.entities.SystemMenu;
import com.softhaxi.shortsage.v1.enums.PropertyChangeField;
import com.softhaxi.shortsage.v1.renderer.MenuTreeCellRender;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @verison 1.0.0
 */
public class HMenuPanel extends JPanel 
    implements ActionListener, TreeSelectionListener {
    
    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");
    
    private JLabel lTitle;
    private JButton bMinimize;
    
    private JPanel pTreemenu;
    private JPanel pHeadmenu;
    private JPanel pDetail;
    private JTree pTree;
    
    private DefaultMutableTreeNode nTop;
    private Object oldValue;
    
    public HMenuPanel() {
        initComponents();
        
        lTitle.setText(RES_GLOBAL.getString("label.workspace").toUpperCase());
        initTreeMenu(lTitle.getText());
        pTree.setSelectionRow(1);
    }

    // <editor-fold defaultstate="collapsed" desc="Region Inititalization">  
    /**
     *
     */
    private void initComponents() {
        setLayout(new BorderLayout(5, 5));
        setPreferredSize(new Dimension(240, getHeight()));
        setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(5, 2, 0, 2), new EtchedBorder()));
        
        JToolBar pHeader = new JToolBar();
        pHeader.setBorder(new EmptyBorder(0, 5, 0, 5));
        pHeader.setFloatable(false);
        
        lTitle = new JLabel(RES_GLOBAL.getString("label.workspace").toUpperCase());
        lTitle.setFont(lTitle.getFont().deriveFont(Font.BOLD));
        pHeader.add(lTitle);
        pHeader.add(Box.createHorizontalGlue());
        bMinimize = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_minus_12.png")));
        pHeader.add(bMinimize);
        add(pHeader, BorderLayout.NORTH);
        
        pTreemenu = new JPanel(new BorderLayout());
        pTreemenu.setBackground(Color.white);
        
        pDetail = new JPanel(new BorderLayout());
        pTreemenu.add(pDetail, BorderLayout.CENTER);
        
        add(pTreemenu, BorderLayout.CENTER);

        pHeadmenu = new JPanel(new GridLayout(3, 1, 5, 0));
        pHeadmenu.setPreferredSize(new Dimension(getWidth(), 90));
        JButton bWorkspace = new JButton(RES_GLOBAL.getString("label.workspace").toUpperCase());
        bWorkspace.setHorizontalAlignment(JButton.LEFT);
        bWorkspace.addActionListener(this);
        pHeadmenu.add(bWorkspace);

        JButton bModem = new JButton(RES_GLOBAL.getString("label.modem").toUpperCase());
        bModem.setHorizontalAlignment(JButton.LEFT);
        bModem.addActionListener(this);
        pHeadmenu.add(bModem);

        JButton bSetting = new JButton(RES_GLOBAL.getString("label.setting").toUpperCase());
        bSetting.setHorizontalAlignment(JButton.LEFT);
        bSetting.addActionListener(this);
        pHeadmenu.add(bSetting);
        add(pHeadmenu, BorderLayout.SOUTH);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
     /**
     *
     * @param header
     */
    private void initTreeMenu(String header) {
        lTitle.setText(header);
        pDetail.removeAll();
        if (header == null || header.equalsIgnoreCase(RES_GLOBAL.getString("label.workspace"))) {
            header = RES_GLOBAL.getString("label.workspace").toUpperCase();
            lTitle.setText(header);
            nTop = MenuDummy.getNodeWorkpace();
        } else if (header.equalsIgnoreCase(RES_GLOBAL.getString("label.modem"))) {
            nTop = MenuDummy.getNodeModem();
        } else if (header.equalsIgnoreCase(RES_GLOBAL.getString("label.setting"))) {
            nTop = MenuDummy.getNodeSetting();
        }
        pTree = new JTree(nTop);
        pTree.setBorder(new EmptyBorder(2, 5, 2, 5));
        pTree.removeTreeSelectionListener(this);
        pTree.addTreeSelectionListener(this);
        pTree.setRootVisible(false);
        pTree.setCellRenderer(new MenuTreeCellRender());

        pDetail.add(new JScrollPane(pTree), BorderLayout.CENTER);
        for (int i = 0; i < pTree.getRowCount(); i++) {
            pTree.expandRow(i);
        }
        updateIcons(pTree);
    }
    /**
     * 
     * @param icon
     * @param scaleFactor
     * @param tree
     * @return 
     */
    private Icon scale(Icon icon, double scaleFactor, JTree tree) {
        int width = icon.getIconWidth();
        int height = icon.getIconHeight();

        width = (int) Math.ceil(width * scaleFactor);
        height = (int) Math.ceil(height * scaleFactor);

        BufferedImage image
                = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = image.createGraphics();
        g.scale(scaleFactor, scaleFactor);
        icon.paintIcon(tree, g, 0, 0);
        g.dispose();

        return new ImageIcon(image);
    }

    /**
     * 
     * @param tree 
     */
    private void updateIcons(JTree tree) {
        Font defaultFont = UIManager.getFont("Tree.font");
        Font currentFont = tree.getFont();

        double newScale = (double) currentFont.getSize2D() / defaultFont.getSize2D();

        MenuTreeCellRender renderer
                = (MenuTreeCellRender) tree.getCellRenderer();
        renderer.setOpenIcon(
                scale(UIManager.getIcon("Tree.openIcon"), newScale, tree));
        renderer.setClosedIcon(
                scale(UIManager.getIcon("Tree.closedIcon"), newScale, tree));
        renderer.setLeafIcon(
                scale(UIManager.getIcon("Tree.leafIcon"), newScale, tree));

        Collection<Integer> iconSizes = Arrays.asList(
                renderer.getOpenIcon().getIconHeight(),
                renderer.getClosedIcon().getIconHeight(),
                renderer.getLeafIcon().getIconHeight());

        // Convert points to pixels
        Point2D p = new Point2D.Float(0, currentFont.getSize2D());
        FontRenderContext context
                = tree.getFontMetrics(currentFont).getFontRenderContext();
        context.getTransform().transform(p, p);
        int fontSizeInPixels = (int) Math.ceil(p.getY());

        tree.setRowHeight(
                Math.max(fontSizeInPixels, Collections.max(iconSizes) + 2));
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ActionListener Implementation">
    @Override
    public void actionPerformed(ActionEvent e) {
        lTitle.setText(((JButton) e.getSource()).getText());
        initTreeMenu(lTitle.getText());
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="TreeSelectionListener Implementation">
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        JTree tempTree = (JTree) e.getSource();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tempTree
                .getLastSelectedPathComponent();
        System.out.println(selectedNode.toString());
        Object newValue = selectedNode.getUserObject();
        if (selectedNode.isLeaf()) {
            if (newValue != null) {
                firePropertyChange(PropertyChangeField.TREEMENU.toString(), oldValue, newValue);
            }
        }
    }
    // </editor-fold>
}
