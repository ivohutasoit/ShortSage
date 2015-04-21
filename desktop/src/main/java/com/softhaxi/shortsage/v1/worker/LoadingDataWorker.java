package com.softhaxi.shortsage.v1.worker;

import java.util.List;
import javax.swing.SwingWorker;

/**
 * 
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 * @param <T> 
 */
public class LoadingDataWorker<T> extends SwingWorker<List<T>, Void> {
    
    private List<T> data;
    private String query;
    
    public LoadingDataWorker(String query) {
        this.query = query;
    }
    
    
    @Override
    protected List<T> doInBackground() throws Exception {
        
        
        return data;
    }
    
}
