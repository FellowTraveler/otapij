/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger;

import com.ot.app.moneychanger.main.LoadMoneyChanger;
import com.ot.app.moneychanger.controlers.PrefsController;
import com.ot.app.moneychanger.main.Concierge;
import com.ot.app.moneychanger.main.ConfigBean;
import com.ot.app.moneychanger.main.Load;
import com.ot.app.moneychanger.main.MainFrameController;
import com.wrapper.ui.MainPage;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author cameron
 */
public class Main {

    
    static Concierge concierge;
    public static void main(String[] argv) throws Exception {
        final LoadMoneyChanger loadMoneyChanger = new LoadMoneyChanger() {

            @Override
            public void load() {
                try {
                    Thread t = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                new Load(concierge).attempt();
                                new MainPage().setVisible(true);
                            } catch (Load.LoadMoneyChangerException ex) {
                                concierge.getConfig().setConfigUpdated(Boolean.FALSE);
                                JOptionPane.showMessageDialog(concierge.getDialogOwner(), ex, "Configuation Error", JOptionPane.ERROR_MESSAGE);
                            } catch (Exception exp) {
                                JOptionPane.showMessageDialog(concierge.getDialogOwner(), exp, "Configuation Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                   t.start();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.runFinalization();
                    System.exit(1);
                }
            }
        };
        
        concierge = new Concierge(new ConfigBean(),loadMoneyChanger);

        try {
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {

                    new MainFrameController(concierge).buildAndShow();

                    if (!concierge.getConfig().isConfigComplete()) {
                        new PrefsController(concierge).show();
                    }
                }
            });





        } catch (Exception e) {
            e.printStackTrace();
            System.runFinalization();
            System.exit(1);
        }
    }
}
