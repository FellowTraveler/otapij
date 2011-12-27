/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger;

//import com.ot.app.moneychanger.controlers.PrefsDialogController;
import com.ot.app.moneychanger.Load.LoadMoneyChangerException;
import com.ot.app.moneychanger.controlers.PrefsController;
import com.ot.app.moneychanger.main.Concierge;
import com.ot.app.moneychanger.main.ConfigBean;
import com.ot.app.moneychanger.main.MainFrameController;
import com.wrapper.ui.MainPage;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author cameron
 */
public class Main {

    public static void main(String[] argv) throws Exception {
        final Concierge concierge = new Concierge(new ConfigBean());
        SwingUtilities.invokeAndWait(new Runnable() {

            public void run() {

                Boolean loaded;
                new MainFrameController(concierge).buildAndShow();

                if (!concierge.getConfig().isConfigComplete()) {
                    new PrefsController(concierge).show();
                }
                loaded = false;

                while (!loaded) {
                    new PrefsController(concierge).show();
                    if (!concierge.getConfig().getConfigUpdated()) break;
                    try {
                        concierge.getConfig().setConfigUpdated(Boolean.FALSE);
                        new Load(concierge).attempt();
                        new MainPage().setVisible(true);
                        loaded = true;
                    } catch (LoadMoneyChangerException e) {
                        JOptionPane.showMessageDialog(concierge.getDialogOwner(), e, "Initialization Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

//                if (StringUtils.isBlank(concierge.getConfig().getAmazonBucketName()))
//                    new BucketDialogController(concierge).show();
//                else
//                    new S3InitialLoadOp(concierge).start();
            }
        });

    }
}
