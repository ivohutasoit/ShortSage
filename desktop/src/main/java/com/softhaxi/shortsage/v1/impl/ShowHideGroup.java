public class ShowHideGroup implements ItemListener {

        private RowGroup group;

        public ShowHideAction(RowGroup group) {
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
