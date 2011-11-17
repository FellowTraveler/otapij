package com.wrapper.ui;

import com.wrapper.core.jni.JavaCallback;
import com.wrapper.core.jni.OTCallback;
import com.wrapper.core.jni.OTCaller;
import com.wrapper.core.jni.otapi;
import com.wrapper.core.util.Configuration;
import com.wrapper.core.util.Utility;
import java.io.IOException;
import java.util.*;
import javax.swing.AbstractListModel;

/**
 *
 * @author cameron
 */
public class Load {
    // <editor-fold defaultstate="collapsed" desc="Load">

    public static void Load() throws LoadFailedException {
        Load(new LoadData(), true);
    }

    public static void Load(LoadData loadData, boolean autoload) throws LoadFailedException {
        try {
            Load.loadOTAPI(loadData.GetJavaPaths());
            Load.loadAppData(loadData.GetDataFolder(), loadData.GetWalletFilename());
            Load.setTimeout(loadData.GetTimeOut());
        } catch (Load.ApiNotLoadedException e) {
            StringBuilder error = new StringBuilder();
            if (autoload) {
                error.append("Autoload: ");
            }
            error.append("The Java Path failed!: ");
            error.append(System.getProperty("line.separator"));
            error.append(e.getError());
            error.append(System.getProperty("line.separator"));
            System.out.println(error.toString());
            throw new LoadFailedException(error.toString(), autoload);
        } catch (Load.AppDataNotLoadedException e) {
            StringBuilder error = new StringBuilder();
            if (autoload) {
                error.append("Autoload: ");
            }
            error.append("Your MoneyChanger user data failed; Choose the location here:");
            error.append(e.getError().replace(":", System.getProperty("line.separator")));
            System.out.println(error.toString());
            throw new LoadFailedException(error.toString(), autoload);
        } catch (Load.InvalidTimeOutException e) {
            StringBuilder error = new StringBuilder();
            error.append("Auto-Timout is invalid; you should never see this message: please contact us for support!");
            if (autoload) {
                error.append("Autoload: ");
            }
            error.append(e.getError());
            System.out.println(error.toString());
            throw new LoadFailedException(error.toString(), autoload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Load API">

    public static void loadOTAPI() throws ApiNotLoadedException {
        loadOTAPI(new JavaPaths());
    }

    public static void loadOTAPI(JavaPaths paths) throws ApiNotLoadedException {
        try {
            if (getOS() == typeOS.WIN) {
                System.loadLibrary("libzmq");
            }
        } catch (java.lang.UnsatisfiedLinkError e) {
            throw new ApiNotLoadedException(buildPathErrorMessage("Unable load libzmq from:"));
        }
        try {
            if (!paths.isEmpty()) {
                Utility.addDirToRuntime(paths);
            }
            System.loadLibrary("otapi-java");
        } catch (java.lang.UnsatisfiedLinkError e) {
            throw new ApiNotLoadedException(buildPathErrorMessage("Unable to load libotapi-java from:"));
        } catch (IOException e) {
            throw new ApiNotLoadedException("IO Error");
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Load AppData">

    public static void loadAppData() throws AppDataNotLoadedException {
        loadAppData(appdataDirectory(getOS()).append("/.ot/client_data").toString(), "wallet.xml");
    }

    public static void loadAppData(String appDataLocation, String walletLocation) throws AppDataNotLoadedException {

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

    private static String buildPathErrorMessage(String message) {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append(System.getProperty("line.separator"));
        errorMessage.append(message);
        errorMessage.append(System.getProperty("line.separator"));
        errorMessage.append(System.getProperty("java.library.path").replace(":", System.getProperty("line.separator")));
        return errorMessage.toString();
    }

    public static class JavaPaths extends AbstractListModel {

        private Collection<String> _paths = new HashSet<String>();

        @Override
        public int getSize() {
            return _paths.size();
        }

        @Override
        public Object getElementAt(int index) {
            return _paths.toArray()[index];
        }

        @Override
        public String toString() {
            StringBuilder pathList = new StringBuilder();
            for (String path : _paths) {
                pathList.append(path);
                pathList.append(";");
            }
            return pathList.toString();
        }

        public boolean isEmpty() {
            return _paths.isEmpty();
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
            _paths.add(path.toLowerCase());
            fireContentsChanged(this, 0, this.getSize());
        }
      
        
        public void remove(String path){
            _paths.remove(path.toLowerCase());
            fireContentsChanged(this, 0, this.getSize());
        }
        
        public void remove(int index){
            _paths.remove(index);
                        fireContentsChanged(this, 0, this.getSize());
        }
        
        public Collection<String> getPaths(){
            return _paths;
        }
    }

    public static class LoadData {

        private JavaPaths javaPaths_;
        private String dataFolder_;
        private String walletFilename_;
        private String timeOut_;

        public LoadData() {
            javaPaths_ = new JavaPaths();
        }

        public LoadData(JavaPaths javaPaths, String dataFolder, String walletFilename, String timeOut) {
            this.javaPaths_ = javaPaths;
            this.dataFolder_ = dataFolder;
            this.walletFilename_ = walletFilename;
            this.timeOut_ = timeOut;
        }

        public void SetJavaPaths(JavaPaths javaPaths) {
            this.javaPaths_ = javaPaths;
        }

        public JavaPaths GetJavaPaths() {
            return this.javaPaths_;
        }

        public void SetDataFolder(String dataFolder) {
            this.dataFolder_ = dataFolder;

        }

        public String GetDataFolder() {
            return this.dataFolder_;
        }

        public void SetWalletFilename(String walletFilename) {
            this.walletFilename_ = walletFilename;

        }

        public String GetWalletFilename() {
            return this.walletFilename_;
        }

        public void SetTimeOut(String timeOut) {
            this.timeOut_ = timeOut;

        }

        public String GetTimeOut() {
            return this.timeOut_;
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
                appdataDirectory.append("/Library/Application ");
                appdataDirectory.append("Support");
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

    static class LoadFailedException extends Exception {

        private Exception e;
        private String error_;
        private boolean auto_;

        public LoadFailedException() {
            this.e = this;
            this.error_ = "none";
            this.auto_ = false;
        }

        public LoadFailedException(Exception e) {
            super(e);
            this.e = e;
            this.error_ = "none";
            this.auto_ = false;
        }

        public LoadFailedException(String err, boolean auto) {
            super(err);     // call super class constructor
            this.error_ = err;  // save message
            this.auto_ = auto;
        }

        public LoadFailedException(Exception e, String err, boolean auto) {
            super(e);     // call super class constructor
            this.e = e;
            this.error_ = err;  // save message
            this.auto_ = auto;
        }

        public Exception getException() {
            return this.e;
        }

        public String getError() {
            return this.error_;
        }

        public boolean getAuto() {
            return auto_;
        }
    }

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
