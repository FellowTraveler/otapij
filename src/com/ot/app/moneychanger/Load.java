/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger;

import com.ot.app.moneychanger.main.Concierge;
import com.ot.app.moneychanger.main.helpers.OSType;
import com.wrapper.core.jni.JavaCallback;
import com.wrapper.core.jni.OTCallback;
import com.wrapper.core.jni.OTCaller;
import com.wrapper.core.jni.otapi;
import com.wrapper.core.util.Configuration;
import com.wrapper.core.util.Utility;
import java.io.IOException;

/**
 *
 * @author Cameron Garnham
 */
public class Load {

    private static Concierge _concierge;

    public Load(Concierge concierge) {
        _concierge = concierge;
    }

    public void attempt() throws LoadMoneyChangerException {
        try {
            Load.loadOTAPI(_concierge.getConfig().getOTPath());
            Load.loadAppData(_concierge.getConfig().getUserDataPath(), _concierge.getConfig().getWalletFilename());
            Load.setTimeout(_concierge.getConfig().getTimeOut());
            Utility.setSettingsObj(this);
        } catch (ApiNotLoadedException e) {
            StringBuilder error = new StringBuilder();
            error.append("Unable to load your Java Path!");
            error.append(System.getProperty("line.separator"));
            error.append(e.getError());
            throw new LoadMoneyChangerException(error.toString());
        } catch (AppDataNotLoadedException e) {
            StringBuilder error = new StringBuilder();
            error.append("Loading your MoneyChanger user data failed; Please Choose the correct location:");
            error.append(e.getError().replace(":", System.getProperty("line.separator")));
            throw new LoadMoneyChangerException(error.toString());
        } catch (InvalidTimeOutException e) {
            StringBuilder error = new StringBuilder();
            error.append("Auto-Timout is invalid; you should never see this message: please contact us for support!");
            error.append(e.getError());
            throw new LoadMoneyChangerException(error.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static class LoadMoneyChangerException extends Exception {

        private String error;

        public LoadMoneyChangerException() {
            error = "none";
        }

        public LoadMoneyChangerException(String err) {
            super(err);     // call super class constructor
            error = err;  // save message
        }

        public String getError() {
            return error;
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Load API">

    public static void loadOTAPI(String paths) throws ApiNotLoadedException {
//                try {
//            
//        } catch (java.lang.UnsatisfiedLinkError e) {
//            StringBuilder errorMessage = new StringBuilder();
//            errorMessage.append(e.toString());
//            errorMessage.append(System.getProperty("line.separator"));
//            errorMessage.append("libzmq not in LD path:");
//            errorMessage.append(System.getProperty("line.separator"));
//            errorMessage.append(System.getProperty("java.library.path").replace(";", System.getProperty("line.separator")));
//            throw new ApiNotLoadedException(errorMessage.toString());
//        }
        try {
            Utility.addDirToRuntime(paths);
            if (OSType.getOS() == OSType.typeOS.WIN) {
                System.loadLibrary("libzmq");
                System.loadLibrary("otapi");
            }
            if (OSType.getOS() == OSType.typeOS.LINUX) {
                System.loadLibrary("otapi-java");
            }
            if (OSType.getOS() == OSType.typeOS.MAC) {
                System.loadLibrary("otapi-java");
            }
        } catch (java.lang.UnsatisfiedLinkError e) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append(e.toString());
            errorMessage.append(System.getProperty("line.separator"));
            errorMessage.append("libotapi-java not in LD path:");
            errorMessage.append(System.getProperty("line.separator"));
            errorMessage.append(System.getProperty("java.library.path").replace(";", System.getProperty("line.separator")));
            throw new ApiNotLoadedException(errorMessage.toString());
        } catch (IOException e) {
            throw new ApiNotLoadedException("IO Error");
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Load AppData">
    public static void loadAppData(String appDataLocation, String walletLocation) throws AppDataNotLoadedException {

        System.out.println(appDataLocation);

        if (otapi.OT_API_Init(appDataLocation) != 1) {
            // throw new AppDataNotLoadedException("Wrong Data Directory!");
        }

        OTCaller g_theCaller = new OTCaller();
        OTCallback g_theCallback = new JavaCallback();
        g_theCaller.setCallback(g_theCallback);
        otapi.OT_API_Set_PasswordCallback(g_theCaller);
        Utility.setG_theCallback(g_theCallback);
        Utility.setG_theCaller(g_theCaller);

        if (otapi.OT_API_LoadWallet(walletLocation) != 1) {
            throw new AppDataNotLoadedException("Unable To Load Wallet, Maybe Wrong Password?");
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Load setTimeOut">
    public static void setTimeout(String waitTimeTxt) throws InvalidTimeOutException {
        long waitTime;
        try {
            waitTime = Long.parseLong(waitTimeTxt);
        } catch (NumberFormatException nfe) {
            throw new InvalidTimeOutException("Please enter valid timeout; timeout not a number!");
        }
        if (waitTime < 1) {
            throw new InvalidTimeOutException("Please enter valid timeout; is less than 1");
        }
        Configuration.setWaitTime(waitTime);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Exceptions">
    static class ApiNotLoadedException extends Exception {

        private String locationsChecked;

        public ApiNotLoadedException() {
            super();             // call superclass constructor
            locationsChecked = "none";
        }

        public ApiNotLoadedException(String err) {
            super(err);     // call super class constructor
            locationsChecked = err;  // save message
        }

        public String getError() {
            return locationsChecked;
        }
    }

    static class AppDataNotLoadedException extends Exception {

        private String locationsChecked;

        public AppDataNotLoadedException() {
            super();             // call superclass constructor
            locationsChecked = "none";
        }

        public AppDataNotLoadedException(String err) {
            super(err);     // call super class constructor
            locationsChecked = err;  // save message
        }

        public String getError() {
            return locationsChecked;
        }
    }

    static class InvalidTimeOutException extends Exception {

        private String error;

        public InvalidTimeOutException() {
            error = "none";
        }

        public InvalidTimeOutException(String err) {
            super(err);     // call super class constructor
            error = err;  // save message
        }

        public String getError() {
            return error;
        }
    }
    // </editor-fold>
}
