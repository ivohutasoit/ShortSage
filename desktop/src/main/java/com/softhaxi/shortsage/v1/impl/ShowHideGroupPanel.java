package com.softhaxi.shortsage.v1.impl;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import net.java.dev.designgridlayout.RowGroup;

/**
 * 
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class ShowHideGroupPanel implements ItemListener {

        private RowGroup group;

        public ShowHideGroupPanel(RowGroup group) {
            this.group = group;
        }

        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                group.show();
            } else {
                group.hide();
            }
        }
    }
