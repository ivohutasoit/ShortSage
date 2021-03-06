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
        dashboard.setProgram("com.softhaxi.shortsage.v1.page.DashboardPage");
        dashboard.setIcon("images/ic_dashboard.png");
        dashboard.setKeyShow(true);
        mainMenu.add(new DefaultMutableTreeNode(dashboard));
        
        SystemMenu importData = new SystemMenu();
        importData.setKey("G0002");
        importData.setTitle("G0002");
        importData.setName("Import Data");
        importData.setProgram("com.softhaxi.shortsage.v1.page.ImportDataPage");
        importData.setIcon("images/ic_download.png");
        importData.setKeyShow(true);
//        mainMenu.add(new DefaultMutableTreeNode(importData));
        
        SystemMenu duplication = new SystemMenu();
        duplication.setKey("G0003");
        duplication.setTitle("G0003");
        duplication.setName("Duplicate Detection");
        duplication.setProgram("com.softhaxi.shortsage.v1.page.DuplicationDetectionPage");
        duplication.setIcon("images/ic_copy_16.png");
        duplication.setKeyShow(true);
//        mainMenu.add(new DefaultMutableTreeNode(duplication));
        
        DefaultMutableTreeNode extMenu = new DefaultMutableTreeNode("Extension [Underconstruction]");
        DefaultMutableTreeNode setupMenu = new DefaultMutableTreeNode("Setup [Underconstruction]");
        
        workspace.add(mainMenu);
//        workspace.add(extMenu);
//        workspace.add(setupMenu);
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
        inbox.setProgram("com.softhaxi.shortsage.v1.page.InboxPage");
        inbox.setIcon("images/ic_inbox.png");
        inbox.setKeyShow(true);
        mainMenu.add(new DefaultMutableTreeNode(inbox));
        
        SystemMenu outbox = new SystemMenu();
        outbox.setKey("M0002");
        outbox.setTitle("M0002");
        outbox.setName("Outbox Folder");
        outbox.setProgram("com.softhaxi.shortsage.v1.page.OutboxPage");
        outbox.setIcon("images/ic_outbox.png");
        outbox.setKeyShow(true);
        mainMenu.add(new DefaultMutableTreeNode(outbox));
        
        DefaultMutableTreeNode setupMenu = new DefaultMutableTreeNode("Setup");
        SystemMenu group = new SystemMenu();
        group.setKey("M0101");
        group.setTitle("M0101");
        group.setName("Phone Book");
        group.setProgram("com.softhaxi.shortsage.v1.page.PhoneBookPage");
        group.setIcon("images/ic_book_16.png");
        group.setKeyShow(true);
        setupMenu.add(new DefaultMutableTreeNode(group));
        
        SystemMenu person = new SystemMenu();
        person.setKey("M0102");
        person.setTitle("M0102");
        person.setName("Contact Person");
        person.setProgram("com.softhaxi.shortsage.v1.page.ContactPersonPage");
        person.setIcon("images/ic_contact.png");
        person.setKeyShow(true);
//        setupMenu.add(new DefaultMutableTreeNode(person));
        
        SystemMenu template = new SystemMenu();
        template.setKey("M0102");
        template.setTitle("M0102");
        template.setName("Message Template");
        template.setProgram("com.softhaxi.shortsage.v1.page.MessageTemplatePage");
        template.setIcon("images/ic_template_16.png");
        template.setKeyShow(true);
        setupMenu.add(new DefaultMutableTreeNode(template));
        
        SystemMenu bulk = new SystemMenu();
        bulk.setKey("M0103");
        bulk.setTitle("M0103");
        bulk.setName("Bulk Message");
        bulk.setProgram("com.softhaxi.shortsage.v1.page.BulkMessagePage");
        bulk.setIcon("images/ic_bulk.png");
        bulk.setKeyShow(true);
//        setupMenu.add(new DefaultMutableTreeNode(bulk));
        
        DefaultMutableTreeNode extMenu = new DefaultMutableTreeNode("Extension [Underconstruction]");
        
        modem.add(mainMenu);
        modem.add(setupMenu);
//        modem.add(extMenu);
        return modem;
    }
    
    /**
     * 
     * @return 
     */
    public static DefaultMutableTreeNode getNodeSetting() {
        DefaultMutableTreeNode setting = new DefaultMutableTreeNode("Setting");
        
        DefaultMutableTreeNode mainMenu = new DefaultMutableTreeNode("Main Menu");
        
        SystemMenu custom = new SystemMenu();
        custom.setKey("S0001");
        custom.setTitle("S0001");
        custom.setName("Administration");
        custom.setIcon("images/ic_clip.png");
        custom.setKeyShow(true);
        mainMenu.add(new DefaultMutableTreeNode(custom));
        
        SystemMenu busman = new SystemMenu();
        busman.setKey("S0002");
        busman.setTitle("S0002");
        busman.setName("Business Management");
        busman.setIcon("images/ic_clip.png");
        busman.setKeyShow(true);
        mainMenu.add(new DefaultMutableTreeNode(busman));
        
        // Setups
        // General Setup, Miscellanneous Setup, Customs Autonumber, Signature Maintanance
        DefaultMutableTreeNode setup = new DefaultMutableTreeNode("Setup");
        SystemMenu menu = new SystemMenu();
        menu.setKey("S1001");
        menu.setTitle("S1001");
        menu.setName("Menu Setup");
        menu.setProgram("com.softhaxi.shortsage.v1.page.MenuSetupPage");
        menu.setIcon("images/ic_clip.png");
        menu.setKeyShow(true);
//        setup.add(new DefaultMutableTreeNode(menu));
        
        SystemMenu modem = new SystemMenu();
        modem.setKey("S1002");
        modem.setTitle("S1002");
        modem.setName("Gateway Setup");
        modem.setProgram("com.softhaxi.shortsage.v1.page.GatewaySetupPage");
        modem.setIcon("images/ic_clip.png");
        modem.setKeyShow(true);
        setup.add(new DefaultMutableTreeNode(modem));
        
        // Extensions
        
//        setting.add(mainMenu);
        setting.add(setup);
        
        return setting;
    }
}
