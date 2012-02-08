package com.wrapper.ui;

import app.ApplicationLauncher;
import chrriis.dj.nativeswing.swtimpl.EventDispatchUtils;
import com.wrapper.core.util.Utility;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.SubstanceProgressBarUI;
import org.jvnet.substance.skin.SubstanceModerateLookAndFeel;

public class ProgressBar
        extends JFrame implements Runnable {

    private static JProgressBar progress;
    private JLabel label1;
    private JPanel topPanel;
    public static boolean stop = false;
    private static int MAX = 100;
    private static int SLEEP = 1000;
    private static int counter = 1;

    public ProgressBar() {
        /*try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.setLookAndFeel("javax.swing.MultiUIDefaults");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
        }
*/

        setResizable(false);

        setAlwaysOnTop(true);

        setTitle("Loading in progress");
        setSize(340, 80);

        topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(340, 80));
        getContentPane().add(topPanel);

        // Create a label and progress bar
        label1 = new JLabel("Moneychanger is loading wallet. Please wait...");
        label1.setPreferredSize(new Dimension(300, 24));
        topPanel.add(label1);

        progress = new JProgressBar();
        progress.setPreferredSize(new Dimension(300, 20));
        progress.setMinimum(0);
        progress.setMaximum(MAX);
 
        progress.setBounds(20, 35, 260, 20);
        topPanel.add(progress);

        progress.setValue(0);
        final Rectangle progressRect = progress.getBounds();
        progressRect.x = 0;
        progressRect.y = 0;
        progress.setIndeterminate(true);


        repaint();

        setLocation(Utility.getLocation(this.getSize()));

        ((JFrame) Utility.getSettingsObj()).dispose();


        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    //progress.paintImmediately(progressRect);
                    repaint();
                    MainPage mainPage = new MainPage();
                    stop = true;
                    completeAndClose();
                    mainPage.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });


    }

    public void completeAndClose() {
        progress.setValue(MAX);
        Rectangle progressRect = progress.getBounds();
        progressRect.x = 0;
        progressRect.y = 0;
        progress.paintImmediately(progressRect);
        progress.setIndeterminate(false);

    }

    public static void main(String args[]) {
        // Create an instance of the test application
        ProgressBar mainFrame = new ProgressBar();
        //new Thread(mainFrame).start();

        mainFrame.setVisible(true);
        mainFrame.pack();
    }

    @Override
    public void run() {

        System.out.println("In run");

        try {
            do {
                progress.setValue(counter);
                Rectangle progressRect = progress.getBounds();
                progressRect.x = 0;
                progressRect.y = 0;
                progress.paintImmediately(progressRect);
                //counter++;
                /*{
               Thread.sleep(SLEEP);
               } catch (Exception ex) {
            Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
        }*/
            

            } while (!stop);
        } catch (Exception ex) {
            Logger.getLogger(ProgressBar.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
        (new BackgroundTask()).execute();
        }
        } );*/


    }

    class BackgroundTask extends SwingWorker<String, Object> {

        @Override
        public String doInBackground() {
            someTimeConsumingMethod();
            return null;
        }

        @Override
        protected void done() {
            System.out.println("Done");
        }

        private void someTimeConsumingMethod() {
        }
    }
}
