/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger.models.viewmodel;

import com.ot.app.moneychanger.main.Concierge;
import javax.swing.JDialog;
import javax.swing.JPanel;
import net.sf.swinglib.SwingUtil;
import net.sf.swinglib.UIHelper;

/**
 *
 * @author cameron
 */
public abstract class AbstractDialog implements IDialog  {

        private Concierge _concierge;
        private JDialog _dialog;
        private JPanel _panel;

        public AbstractDialog(JPanel panel,Concierge concierge) {
            _panel = panel;
            _concierge = concierge;
            buildDialog();
        }
        public void show() {
            _dialog.pack();
            SwingUtil.centerAndShow(_dialog, _concierge.getDialogOwner());
        }
        public void close() {
            _dialog.dispose();
        }
        private void buildDialog() {
            _dialog = UIHelper.newDialog(_concierge.getDialogOwner(), "Preferences", _panel);
        }
}
