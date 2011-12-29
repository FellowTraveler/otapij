/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger;

import com.ot.app.moneychanger.controlers.PrefsController;
import com.ot.app.moneychanger.main.Concierge;
import com.ot.app.moneychanger.main.ConfigBean;
import com.ot.app.moneychanger.main.MainFrameController;
import javax.swing.SwingUtilities;

/**
 *
 * @author cameron
 */
public class Main {

    public static void main(String[] argv) throws Exception {
        final Concierge concierge = new Concierge(new ConfigBean());
        try {
            SwingUtilities.invokeAndWait(new Runnable() {

                public void run() {

                    new MainFrameController(concierge).buildAndShow();

                    if (!concierge.getConfig().isConfigComplete()) {
                        new PrefsController(concierge).show();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
