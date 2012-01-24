/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger.main;

import com.wrapper.ui.MainPage;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.jdesktop.application.SingleFrameApplication;

/**
 *
 * @author Cameron Garnham
 */
public class Start extends SingleFrameApplication {

    private static Concierge _concierge;

    @Override
    protected void startup() {
        SwingUtilities.invokeLater(new Runnable() {

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

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     *
     * @return the instance of ApplicationLauncher
     */
    public static Start getApplication() {
        return Start.getInstance(Start.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args,Concierge concierge) {
        _concierge = concierge;
        launch(Start.class, args);
    }
}
