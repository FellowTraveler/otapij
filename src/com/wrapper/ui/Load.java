/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrapper.ui;

import com.wrapper.core.jni.JavaCallback;
import com.wrapper.core.jni.OTCallback;
import com.wrapper.core.jni.OTCaller;
import com.wrapper.core.jni.otapi;
import com.wrapper.core.util.Configuration;
import com.wrapper.core.util.Utility;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;

/**
 *
 * @author cameron
 *
 * Cleanup: FT
 */
public class Load {
    // These can only happen once per run of the cpplication
    // -FT
    //

    private static boolean s_bLoadedZMQ = false; // So we don't load it multiple times in Windows.
    private static boolean s_bLoadedOTAPI = false; // So we don't load it multiple times.
    private static boolean s_bInitializedOTAPI = false; // So we don't initialize it multiple times.
    private static boolean s_bLoadedWallet = false; // So we don't load the wallet multiple times.
    // -------------------------------------
    private static OneTimeOnly s_OneTime = null;
    // -------------------------------------
    // Do NOT instantiate this until AFTER OTAPI library is loaded
    // and OT_API_INit() is called, but BEFORE LoadWallet() is called.
    //

    private static class OneTimeOnly {

        private OTCaller m_theCaller;
        private OTCallback m_theCallback;

        public OneTimeOnly() {
            m_theCaller = new OTCaller();
            m_theCallback = new JavaCallback();

            m_theCaller.setCallback(m_theCallback);

            otapi.OT_API_Set_PasswordCallback(m_theCaller);

            Utility.setG_theCallback(m_theCallback);
            Utility.setG_theCaller(m_theCaller);
        }
    }
    // -------------------------------------

    public static void setupPasswordCallback() {
        // This only happens if the above OT_API_Init() was successful, and it
        // only happens once.
        if (null == Load.s_OneTime) {
            Load.s_OneTime = new OneTimeOnly(); // Password callbacks are in here.
            System.out.println("Load.setupPasswordCallback: Password callback successfully set up.");
        } else {
            System.out.println("Load.setupPasswordCallback: Password callback was already set. (Skipping.)");
        }
    }
    // -------------------------------------

    private static void loadImage() throws ImageNotLoadedException {

        System.out.println("Load.loadImage(): ");
        String strImagePath = Utility.getImagePath();

        if (null == strImagePath) {
            throw new ImageNotLoadedException("Load.loadImage(): Unable To Load Image, Maybe First Run, Image Not Set?");
        }
        System.out.println("Utility.getImagePath(): " + strImagePath);

        Configuration.setImagePath(strImagePath);
    }
    // -------------------------------------

    public static void loadAppData(String appDataLocation, String walletLocation) throws AppDataNotLoadedException {

        if (!Load.s_bLoadedOTAPI) // We haven't loaded the jnilib yet.
        {
            String strError = "Load.loadAppData: otapi library isn't loaded yet. (Should thus never call this function at this point.)";
            System.out.println(strError);
            throw new AppDataNotLoadedException(strError);
        }
        // -----------------------------------------

        String strLocation = new String(appDataLocation.trim());

        if (Load.s_bInitializedOTAPI) {
            System.out.println("Load.loadAppData: (OT_API_Init() was already completed in the past. (Skipping.)");
        } else // We haven't initialized yet OT yet.
        {
            if (1 == otapi.OT_API_Init(strLocation)) {
                Load.s_bInitializedOTAPI = true;
                System.out.println("Load.loadAppData: Success invoking OT_API_Init().");
            } else // Failed in OT_API_Init().
            {
                String strErrorMsg = "Load.loadAppData: OT_API_Init() call failed(). (Has it already been called?) Location is " + appDataLocation + " (End of location.)";
                throw new AppDataNotLoadedException(strErrorMsg);
            }
        }
        // --------------------------------------------

        // This has internal logic so that it only actually is called once.
        //
        Load.setupPasswordCallback();

        // --------------------------------------------
        // Password dialog -- security image 
        try {
            Load.loadImage();
        } catch (ImageNotLoadedException ex) {
            throw new AppDataNotLoadedException("Load.loadAppData: Unable To Load Wallet; password security image not loaded.");
        }
        // --------------------------------------------
        // Load the wallet, if not already loaded.
        //
        if (Load.s_bLoadedWallet) // Already loaded, therefore, use SwitchWallet() instead of LoadWallet().
        {
            if (1 == otapi.OT_API_SwitchWallet(strLocation, walletLocation)) {
                Load.s_bLoadedWallet = true;
                System.out.println("Load.loadAppData: OT_API_SwitchWallet() completed successfully.");
            } else {
                throw new AppDataNotLoadedException("Load.loadAppData: Unable To Switch Wallet, Maybe Wrong Password?");
            }
        } else // Wallet has not ever been loaded yet successfully.
        {
            if (1 == otapi.OT_API_LoadWallet(walletLocation)) {
                Load.s_bLoadedWallet = true;
                System.out.println("Load.loadAppData: OT_API_LoadWallet() completed successfully.");
            } else {
                throw new AppDataNotLoadedException("Load.loadAppData: Unable To Load Wallet, Maybe Wrong Password?");
            }
        }
    }
    // -------------------------------------

    // <editor-fold defaultstate="collapsed" desc="Load API">
    public static void loadOTAPI() throws ApiNotLoadedException {

        loadZMQ();

        if (!s_bLoadedOTAPI) {
            try {
                System.loadLibrary("otapi-java");
                s_bLoadedOTAPI = true;
                System.out.println("Load.loadOTAPI: Success loading otapi.java.jnilib");
            } catch (java.lang.UnsatisfiedLinkError e) {
                StringBuilder errorMessage = new StringBuilder();
                errorMessage.append(System.getProperty("line.separator"));
                errorMessage.append("libotapi-java not in LD path:");
                errorMessage.append(System.getProperty("line.separator"));
                errorMessage.append(System.getProperty("java.library.path").replace(":", System.getProperty("line.separator")));
                throw new ApiNotLoadedException(errorMessage.toString());
            }
        } else {
            System.out.println("Load.loadOTAPI (1): (otapi-java is already loaded. Skipping.)");
        }
    }

    public static void loadOTAPI(JavaPaths paths) throws ApiNotLoadedException {

        loadZMQ();

        if (!s_bLoadedOTAPI) {
            try {
                Utility.addDirToRuntime(paths.toString());
                System.loadLibrary("otapi-java");
                s_bLoadedOTAPI = true;
                System.out.println("Load.loadOTAPI: Success loading otapi-java.jnilib");
            } catch (java.lang.UnsatisfiedLinkError e) {
                StringBuilder errorMessage = new StringBuilder();
                errorMessage.append(System.getProperty("line.separator"));
                errorMessage.append("libotapi-java not in LD path:");
                errorMessage.append(System.getProperty("line.separator"));
                errorMessage.append(System.getProperty("java.library.path").replace(":", System.getProperty("line.separator")));
                throw new ApiNotLoadedException(errorMessage.toString());
            } catch (IOException e) {
                throw new ApiNotLoadedException("IO Error");
            }
        } else {
            System.out.println("Load.loadOTAPI (2): (otapi-java is already loaded. Skipping.)");
        }
    }
    // </editor-fold>

    // -------------------------------------
    public static void loadZMQ() throws ApiNotLoadedException {
        try {
            if (getOS() == typeOS.WIN) {
                if (!s_bLoadedZMQ) {
                    System.loadLibrary("libzmq");
                    s_bLoadedZMQ = true; // (Assuming the above loadLibrary throws in event of failure.)
                } else {
                    System.out.println("Load.loadZMQ: (libzmq is already loaded. Skipping.)");
                }
            }
        } catch (java.lang.UnsatisfiedLinkError e) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append(System.getProperty("line.separator"));
            errorMessage.append("libzmq not in LD path:");
            errorMessage.append(System.getProperty("line.separator"));
            errorMessage.append(System.getProperty("java.library.path").replace(File.pathSeparator, System.getProperty("line.separator")));
            throw new ApiNotLoadedException(errorMessage.toString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Load AppData">
    public static void loadAppData() throws AppDataNotLoadedException {
        StringBuffer strOSVers = new StringBuffer("~"); // My appdataDirectory has a SPACE in it (/Users/au/Library/Application Support/.ot)
//      StringBuffer strOSVers      = new StringBuffer(appdataDirectory(getOS())); // Therefore I did this for now, so I could run the wallet again!
        String strAppend = strOSVers.append("/.ot/client_data").toString();
//      String strSubstitute        = strAppend.replaceAll(" ", "\\ "); // Oh well, I tried.
        loadAppData(strAppend, "wallet.xml");
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Load setTimeOut">
    public static void setTimeout() throws InvalidTimeOutException {
        long waitTime = 1;
        Configuration.setWaitTime(waitTime);
    }

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
    // <editor-fold defaultstate="collapsed" desc="Helpers">

    public static class JavaPaths extends AbstractListModel {

        private List<String> _paths = new ArrayList<String>();

        @Override
        public int getSize() {
            return _paths.size();
        }

        @Override
        public Object getElementAt(int index) {
            return _paths.get(index);
        }

        @Override
        public String toString() {
            StringBuilder pathsString = new StringBuilder();
            Iterator<String> setIterator = _paths.iterator();
            while (setIterator.hasNext()) {
                pathsString.append(setIterator.next());
                if (setIterator.hasNext()) {
                    pathsString.append(File.pathSeparator);
                }
            }
            return pathsString.toString();
        }

        public JavaPaths() {
        }

        public JavaPaths(List<String> paths) {
            _paths.addAll(paths);
        }

        public void addDefultPath(typeOS os) {
            switch (os) {
                case WIN: {
                    break;
                }
                case LINUX: {
                    addPath("/usr/local/lib");
                    break;
                }
                case MAC: {
                    addPath("/usr/local/lib");
                    break;
                }
                case UNIX: {
                    addPath("/usr/local/lib");
                    break;
                }
                case OTHER: {
                    addPath("/usr/local/lib");
                    break;
                }
            }
        }

        public void addPath(String path) {
            _paths.add(path); //.toLowerCase());
            fireContentsChanged(this, 0, this.getSize());
        }

        public List<String> getPaths() {
            return _paths;
        }
    }

    public enum typeOS {

        WIN, LINUX, MAC, UNIX, ALL, OTHER
    }

    public static typeOS getOS() {
        String OS = System.getProperty("os.name").toUpperCase();
        if (OS.contains("WIN")) {
            return typeOS.WIN;
        }
        if (OS.contains("LINUX")) {
            return typeOS.LINUX;
        }
        if (OS.contains("MAC")) {
            return typeOS.MAC;
        }
        if (OS.contains("MAC")) {
            return typeOS.UNIX;
        }
        return typeOS.OTHER;
    }

    public static StringBuilder appdataDirectory(typeOS os) {
        StringBuilder appdataDirectory = new StringBuilder();
        switch (os) {
            case WIN: {
                appdataDirectory.append(System.getenv("APPDATA"));
                return appdataDirectory;
            }
            case LINUX: {
                appdataDirectory.append(System.getProperty("user.home"));
                return appdataDirectory;
            }
            case MAC: {
                appdataDirectory.append(System.getProperty("user.home"));
                // FT: Been debugging on something here for hours and if this
                // turns out to be the fix, then I'll just keep it this way until
                // we have a well-tested version that says otherwise.
                //
//              appdataDirectory.append("/Library/Application ");
//              appdataDirectory.append("Support");
                return appdataDirectory;
            }
            case UNIX: {
                appdataDirectory.append(System.getProperty("user.home"));
                return appdataDirectory;
            }
        }
        appdataDirectory.append(System.getProperty("user.dir"));
        return appdataDirectory;
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

    static class ImageNotLoadedException extends Exception {

        private String locationsChecked;

        public ImageNotLoadedException() {
            super();             // call superclass constructor
            locationsChecked = "none";
        }

        public ImageNotLoadedException(String err) {
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
