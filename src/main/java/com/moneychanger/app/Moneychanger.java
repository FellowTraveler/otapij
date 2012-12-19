/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moneychanger.app;

import com.moneychanger.core.util.ConfigBean;
import com.moneychanger.core.util.JavaCallback;
import com.moneychanger.core.util.Utility.ReturnAction;
import com.moneychanger.ui.Load;
import com.moneychanger.ui.Load.IJavaPath;
import com.moneychanger.ui.Load.IPasswordImage;
import com.moneychanger.ui.Load.LoadingOpenTransactionsFailure;
import com.moneychanger.ui.ProgressBar;
import com.moneychanger.ui.dialogs.GetJavaPath;
import com.moneychanger.ui.dialogs.GetPasswordImageDialog;
import java.awt.Dialog;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import org.jdesktop.application.SingleFrameApplication;
import org.opentransactions.jni.core.OTCallback;
import org.opentransactions.jni.core.OTCaller;
import org.opentransactions.jni.core.otapiJNI;

/**
 *
 * @author Cameron Garnham
 */
public class Moneychanger extends SingleFrameApplication {

    static ConfigBean configBean = new ConfigBean();  // Our Configuration!
    static private IJavaPath javaPath = null;
    static private IPasswordImage passwordImageMgmt = null;
    static private OTCaller javaPasswordCaller = null;
    static private OTCallback javaPasswordCallback = null;
    

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        try {



            final Runnable doMoneychanger = new Runnable() {
                @Override
                public void run() {
                    new ProgressBar().setVisible(true);
                }
            };

            Thread appThread = new Thread() {
                @Override
                public void run() {
                    try {
                        SwingUtilities.invokeAndWait(doMoneychanger);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Finished on " + Thread.currentThread());
                }
            };

            appThread.setDaemon(true);
            appThread.start();


        } catch (Exception ex) {
            Logger.getLogger(Moneychanger.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
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
    public static Moneychanger getApplication() {
        return Moneychanger.getInstance(Moneychanger.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) throws UnsupportedLookAndFeelException {

        Runtime.getRuntime().addShutdownHook( 
    		new Thread(
    			new Runnable() {
                            @Override
    				public void run() {
    					System.out.println( "Shutdown hook ran." );

                                        otapiJNI.OTAPI_Basic_AppShutdown();
    				}	
    			}
    		)
    	);
        
        Loader.Load();
        launch(Moneychanger.class, args);
        
    }

    static class Loader {

        static boolean Load() {
            try {
                javaPath = new JavaPathMgmt(configBean);
                Load.It().InitNative(javaPath, "");
                Load.It().Init();


                passwordImageMgmt = new PasswordImageMgmt();
                Load.It().SetupPasswordImage(passwordImageMgmt);

                javaPasswordCaller = new OTCaller();
                javaPasswordCallback = new JavaCallback();
                Load.It().SetupPasswordCallback(javaPasswordCaller,javaPasswordCallback);
                Load.It().LoadWallet();

                return true;
            } catch (LoadingOpenTransactionsFailure ex) {
                Logger.getLogger(Moneychanger.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
    }

    static class PasswordImageMgmt implements IPasswordImage {

        private ReturnAction returnAction = null;

        @Override
        public String GetPasswordImageFromUser(String string) {
            JDialog j = new GetPasswordImageDialog();
            j.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            j.setAlwaysOnTop(true);
            j.setVisible(true);
            return GetPasswordImageDialog.GetPasswordImagePath();
        }

        @Override
        public boolean SetPasswordImage(String string) {
            ConfigBean.Static.setKey(ConfigBean.Static.Keys.PasswordImagePath, string);
            return true;
        }
    }

    static class JavaPathMgmt implements IJavaPath {

        private ConfigBean configBean = null;

        public JavaPathMgmt(ConfigBean configBean) {
            this.configBean = configBean;
        }
        private boolean bHaveTriedConfigPath = false;

        @Override
        public String GetJavaPathFromUser(String message) {

            if (!bHaveTriedConfigPath) {
                bHaveTriedConfigPath = true;

                String pathFromConfig = configBean.getConfig(ConfigBean.Keys.JavaPath);
                if (!pathFromConfig.isEmpty()) {
                    return pathFromConfig;
                }
            }

            JDialog j = new GetJavaPath();
            j.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            j.setAlwaysOnTop(true);
            j.setVisible(true);

            String pathFromUser = GetJavaPath.GetJavaPath();

            configBean.setConfig(ConfigBean.Keys.JavaPath, pathFromUser);

            return pathFromUser;
        }
    }
}
