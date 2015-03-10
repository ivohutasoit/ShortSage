package com.softhaxi.shortsage.v1.panels;

import com.softhaxi.shortsage.v1.dummies.MenuDummy;
import com.softhaxi.shortsage.v1.entities.SystemMenu;
import com.softhaxi.shortsage.v1.stages.MainWindow;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Hutasoit
 */
public class MainMenuPanel extends JPanel
        implements ActionListener, TreeSelectionListener {

    private final ResourceBundle global = ResourceBundle.getBundle("global");
    private static String hCurrent = null;

    private MainWindow host;

    private JPanel hPanel;
    private JPanel dPanel;
    private JPanel tPanel;

    private JLabel dlTitle;
    private JButton hbWorkspace;
    private JButton hbModem;
    private JButton hbSetting;

    private DefaultMutableTreeNode top;
    private JTree tree;
    private JScrollPane scroller;

    public MainMenuPanel(MainWindow host) {
        initComponents();
        initDetailPanel(hCurrent);
        this.host = host;
    }

    private void initComponents() {
        setPreferredSize(new Dimension(240, getHeight()));
        setLayout(new BorderLayout(5, 5));
//        Border border = new LineBorder(Color.GRAY, 1);
//        Border margin = new EmptyBorder(2,2,2,2);
//        setBorder(new CompoundBorder(border, margin));
        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 0, 5), new EtchedBorder()));

        dPanel = new JPanel();
        dPanel.setLayout(new BorderLayout());
        dPanel.setBorder(new EmptyBorder(4, 4, 0, 4));
        dlTitle = new JLabel();
        dlTitle.setFont(dlTitle.getFont().deriveFont(Font.BOLD));
        dPanel.add(dlTitle, BorderLayout.NORTH);

        tPanel = new JPanel(new BorderLayout());
        tPanel.setBackground(Color.white);
        top = new DefaultMutableTreeNode(dlTitle.getText());
        dPanel.add(tPanel, BorderLayout.CENTER);

        add(dPanel, BorderLayout.CENTER);

        hPanel = new JPanel(new GridLayout(3, 1, 5, 0));
        hPanel.setPreferredSize(new Dimension(getWidth(), 90));
        hbWorkspace = new JButton(global.getString("label.workspace").toUpperCase());
        hbWorkspace.setHorizontalAlignment(JButton.LEFT);
        hbWorkspace.addActionListener(this);
//        hbWorkspace.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
//        hbWorkspace.setBorderPainted(false);
//        hbWorkspace.setContentAreaFilled(false);
        hPanel.add(hbWorkspace);

        hbModem = new JButton(global.getString("label.modem").toUpperCase());
        hbModem.setHorizontalAlignment(JButton.LEFT);
        hbModem.addActionListener(this);
        hPanel.add(hbModem);

        hbSetting = new JButton(global.getString("label.setting").toUpperCase());
        hbSetting.setHorizontalAlignment(JButton.LEFT);
        hbSetting.addActionListener(this);
        hPanel.add(hbSetting);
        add(hPanel, BorderLayout.SOUTH);
    }

    /**
     *
     * @param header
     */
    private void initDetailPanel(String header) {
        dlTitle.setText(header);
        tPanel.removeAll();
        if (header == null || header.equalsIgnoreCase(global.getString("label.workspace"))) {
            header = global.getString("label.workspace").toUpperCase();
            dlTitle.setText(header);
            top = MenuDummy.getNodeWorkpace();
        } else if (header.equalsIgnoreCase(global.getString("label.modem"))) {
            top = MenuDummy.getNodeModem();
        } else if (header.equalsIgnoreCase(global.getString("label.setting"))) {
            top = MenuDummy.getNodeSetting();
        }
        tree = new JTree(top);
        tree.setBorder(new EmptyBorder(2, 5, 2, 5));
        tree.removeTreeSelectionListener(this);
        tree.addTreeSelectionListener(this);
        tree.setRootVisible(false);
        tree.setCellRenderer(new MenuTreeCellRender());

        JScrollPane treeView = new JScrollPane(tree);
        treeView.setBorder(new EmptyBorder(0, 0, 0, 0));
        tPanel.add(treeView, BorderLayout.CENTER);
        for (int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }
        updateIcons(tree);
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        dlTitle.setText(((JButton) e.getSource()).getText());
        initDetailPanel(dlTitle.getText());
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        JTree tempTree = (JTree) e.getSource();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tempTree
                .getLastSelectedPathComponent();
        String selectedNodeName = ((SystemMenu) selectedNode.getUserObject()).getProgram();
        if (selectedNode.isLeaf()) {
            if (selectedNodeName != null || !selectedNodeName.equals("")) {
                host.setCenterPanel(selectedNodeName);
            }
        }
    }

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

    class MenuTreeCellRender extends DefaultTreeCellRenderer {

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
            Object o = ((DefaultMutableTreeNode) value).getUserObject();
            if (leaf) {
                if (o instanceof SystemMenu) {
                    SystemMenu menu = (SystemMenu) o;
                    if (menu.getIcon() != null) {
                        setIcon(new ImageIcon(getClass().getClassLoader().getResource(menu.getIcon())));
                    } else {
                        setIcon(null);
                    }
                }
            } else {
                setIcon(null);
            }
            return this;
        }
    }
}
