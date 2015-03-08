package com.softhaxi.shortsage.v1.dummies;

import com.softhaxi.shortsage.v1.entities.SystemMenu;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Hutasoit
 */
public class MenuDummy {
    /**
     * Dummy Menu for Workspace
     * @return 
     */
    public static DefaultMutableTreeNode getNodeWorkpace() {
        DefaultMutableTreeNode workspace = new DefaultMutableTreeNode("Workspace");
        
        DefaultMutableTreeNode mainMenu = new DefaultMutableTreeNode("Main Menu");
        
        SystemMenu dashboard = new SystemMenu();
        dashboard.setKey("G0001");
        dashboard.setTitle("G0001");
        dashboard.setName("Dashboard");
        dashboard.setProgram("com.softhaxi.shortsage.v1.pages.DashboardPage");
        dashboard.setIcon("images/ic_dashboard.png");
        dashboard.setKeyShow(true);
        mainMenu.add(new DefaultMutableTreeNode(dashboard));
        
        SystemMenu importData = new SystemMenu();
        importData.setKey("G0002");
        importData.setTitle("G0002");
        importData.setName("Import Data");
        importData.setProgram("com.softhaxi.shortsage.v1.pages.ImportDataPage");
        importData.setIcon("images/ic_download.png");
        importData.setKeyShow(true);
        mainMenu.add(new DefaultMutableTreeNode(importData));
        
        DefaultMutableTreeNode extMenu = new DefaultMutableTreeNode("Extension [Underconstruction]");
        DefaultMutableTreeNode setupMenu = new DefaultMutableTreeNode("Setup [Underconstruction]");
        
        workspace.add(mainMenu);
        workspace.add(extMenu);
        workspace.add(setupMenu);
        return workspace;
    }
    
    /**
     * 
     * @return 
     */
    public static DefaultMutableTreeNode getNodeModem() {
        DefaultMutableTreeNode modem = new DefaultMutableTreeNode("Modem");
        
        DefaultMutableTreeNode mainMenu = new DefaultMutableTreeNode("Main Menu");
        
        SystemMenu inbox = new SystemMenu();
        inbox.setKey("M0001");
        inbox.setTitle("M0001");
        inbox.setName("Inbox Folder");
        inbox.setProgram("com.softhaxi.shortsage.v1.pages.InboxPage");
        inbox.setIcon("images/ic_inbox.png");
        inbox.setKeyShow(true);
        mainMenu.add(new DefaultMutableTreeNode(inbox));
        
        SystemMenu outbox = new SystemMenu();
        outbox.setKey("M0002");
        outbox.setTitle("M0002");
        outbox.setName("Outbox Folder");
        outbox.setProgram("com.softhaxi.shortsage.v1.pages.OutboxPage");
        outbox.setIcon("images/ic_outbox.png");
        outbox.setKeyShow(true);
        mainMenu.add(new DefaultMutableTreeNode(outbox));
        
        DefaultMutableTreeNode entMenu = new DefaultMutableTreeNode("Entities");
        SystemMenu group = new SystemMenu();
        group.setKey("M0101");
        group.setTitle("M0101");
        group.setName("Contact Group [Underconstruction]");
        group.setIcon("images/ic_group.png");
        group.setKeyShow(true);
        entMenu.add(new DefaultMutableTreeNode(group));
        
        entMenu.add(new DefaultMutableTreeNode(new SystemMenu("M112", "M11002", "Contact Person [Underconstruction]", null, true)));
        entMenu.add(new DefaultMutableTreeNode(new SystemMenu("M113", "M11003", "Phone Fields [Underconstruction]", null, true)));
        entMenu.add(new DefaultMutableTreeNode(new SystemMenu("M114", "M11004", "Message Template [Underconstruction]", null, true)));
        entMenu.add(new DefaultMutableTreeNode(new SystemMenu("M115", "M110025", "Bulk Message [Underconstruction]", null, true)));
        
        DefaultMutableTreeNode extMenu = new DefaultMutableTreeNode("Extension [Underconstruction]");
        
        modem.add(mainMenu);
        modem.add(entMenu);
        modem.add(extMenu);
        return modem;
    }
}
