/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger.main;

import com.wrapper.ui.MainPage;
import javax.swing.JOptionPane;

/**
 *
 * @author Cameron Garnham
 */
public class Start {

    private Concierge _concierge;
    private Thread t;

    public Start(Concierge concierge) {
        _concierge = concierge;

        t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    new Load(_concierge).attempt();
                    new MainPage().setVisible(true);
                } catch (Load.LoadMoneyChangerException ex) {
                    _concierge.getConfig().setConfigUpdated(Boolean.FALSE);
                    JOptionPane.showMessageDialog(_concierge.getDialogOwner(), ex, "Configuation Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception exp) {
                    JOptionPane.showMessageDialog(_concierge.getDialogOwner(), exp, "Configuation Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public Thread getThread() {
        return t;
    }
}
