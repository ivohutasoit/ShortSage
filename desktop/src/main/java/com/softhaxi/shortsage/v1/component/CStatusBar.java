package com.softhaxi.shortsage.v1.component;

public class CStatusBar extends JToolBar {

    ResourceBundle resGlobal = ResourceBundle.getBundle("global");
    ResourceBundle resLocal;

    private JLabel lProgress;
    private JLabel lCompany;
    private JLabel lVersion;
    private JLabel lDate;
    private JProgress pProgress;
    
    public CStatusBar() {
      this("");
    }
    
    public CStatusBar(String filename) {
      if(!filename.equals("") || !filename != null) {
        FileInputStream fis = new FileInputStream(filename);
        resLocal.load(fis);
        fis.close()
      }
      initComponents();
    }
    
    private void initComponents() {
      setPreferredSize(new Dimension(getWidth(), 23));
      
      lProgress = new JLabel(resGlobal.getString("label.ready"));
      add(lProgress);
      add(Box.createHorizontalGlue());
      
      pProgress = new JProgress(0);
      pProgress.setVisible(false);
      add(pProgress);
      add(JToolBar.Separator());
      
      lCompany = new JLabel(resLocal != null ? 
            resLocal.getString("company.name") : resGlobal.getString("copyright.company.name"));
      add(lCompany);
      add(JToolBar.Separator());
      
      SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy")
      lDate = new JLabel(sdf.format(new Date()));
      add(lDate);
    }
}
