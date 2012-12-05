/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moneychanger.ui;

import com.moneychanger.core.util.ConfigBean;
import com.moneychanger.core.util.JavaCallback;
import com.moneychanger.core.util.Utility;
import com.moneychanger.core.util.Utility.ReturnAction;
import com.moneychanger.ui.LoadState.OutOfOrderException;
import org.opentransactions.jni.core.OTCallback;
import org.opentransactions.jni.core.OTCaller;
import org.opentransactions.jni.core.otapi;
import org.opentransactions.jni.core.otapiJNI;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * Note... Everything in this outer class is Static
 *
 * @author Cameron Garnham
 */
public class Load {

    private static ConfigBean _configBean;

    //<editor-fold defaultstate="collapsed" desc="Init">
    public static void Init(ConfigBean configBean) throws OutOfOrderException {
        if (LoadState.getStage() == LoadState.Stages.Init) {
            _configBean = configBean;
            LoadState.setStageComplete();
        } else {
            throw new OutOfOrderException("Tried to init too late!");
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="AttemptLoad">
    public static void Attempt() throws LoadFailedException {

        System.out.println();
        System.out.println("Attempting to load Moneychanger...");

        try {
            System.out.println();
            // For some reason the current stage is not complete... Let's attempt to complete it.
            if (!LoadState.isStageComplete()) {

                System.out.println("Current stage not complete... attempting to run uncompleted stage.");

                switch (LoadState.getStage()) {
                    case Init:
                        throw new LoadFailedException("Must complete init before attempt.");

                    case Opt_InitSettings:
                    case Opt_LoadSettings:
                    case Opt_UpdateSettings:
                        throw new LoadFailedException("Must complete settings before attempting to load.");


                    case LoadNativeLibraries:
                        LoadNativeLibraries.Attempt();
                        break;
                    case InitOTAPI:
                        InitOTAPI.Attempt();
                        break;
                    case SetupPasswordImage:
                        SetupPasswordImage.Attempt();
                        break;
                    case SetupPasswordCallback:
                        SetupPasswordCallback.Attempt();
                        break;
                    case LoadWallet:
                        LoadWallet.Attempt();
                        break;
                }

                // Well that didn't fix it... lets throw a big error.
                if (LoadState.isStageComplete()) {
                    throw new LoadFailedException("Failed to complete step.");
                }
                System.out.println();
                System.out.println("That worked... stage now complete.");
            }
            System.out.println("Attempting to load the stages.");
            System.out.println();

            // Now that the stage we are on is complete... lets load the next step.
            switch (LoadState.getStage()) {


                case Opt_InitSettings:
                case Opt_LoadSettings:
                    throw new LoadFailedException("Must complete settings before attempting to load!");

                case Init:
                case Opt_UpdateSettings:
                    LoadNativeLibraries.Attempt();
                case LoadNativeLibraries:
                    InitOTAPI.Attempt();
                case InitOTAPI:
                    SetupPasswordImage.Attempt();
                case SetupPasswordImage:
                    SetupPasswordCallback.Attempt();
                case SetupPasswordCallback:
                    LoadWallet.Attempt();
            }

        } catch (Exception e) {
            System.err.println(e.toString());
            throw new LoadFailedException(e.toString());
        }
    }

    public static class LoadFailedException extends Exception {

        private String locationsChecked;

        public LoadFailedException() {
            super();             // call superclass constructor
            locationsChecked = "none";
        }

        public LoadFailedException(String err) {
            super(err);     // call super class constructor
            locationsChecked = err;  // save message
        }

        public String getError() {
            return locationsChecked;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="LoadNativeLibraries">
    public static class LoadNativeLibraries {

        private LoadNativeLibraries() {
        }

        public static void Attempt() throws LoadNativeLibrariesFailedException, OutOfOrderException {
            LoadState.Progress(LoadState.Stages.LoadNativeLibraries);

            setJavaPaths(_configBean);

            // If Running on windows... Lets load the windows DLL's
            if (getOS() == typeOS.WIN) {
                System.out.println("We are on Windows!");
                LoadWindowsDLLs();
            }
            // If Running on other than windows... Lets load the libs
            else
            {
                LoadLinuxLIBs();
            }


            LoadNativeOTAPI();

            LoadState.setStageComplete();
        }

        private static void LoadWindowsDLLs() throws LoadNativeLibrariesFailedException, OutOfOrderException {
            try {
                //System.loadLibrary("libeay32");
                //System.loadLibrary("libssl32");
                
                System.out.print("Loading libzmq:   ");
                System.loadLibrary("libzmq");
                System.out.println("Success!");

                System.out.print("Loading chaiscript:   ");
                System.loadLibrary("chaiscript");
                System.out.println("Success!");

                System.out.print("Loading otlib:   ");
                System.loadLibrary("otlib");
                System.out.println("Success!");

            } catch (java.lang.UnsatisfiedLinkError e) {
                System.out.println();
                System.err.println("ERROR:   Loading Windows DLL's Failed");
                e.printStackTrace();
                LoadState.setStageFailed();
                throw new LoadNativeLibrariesFailedException(e.toString());
            }
        }

        private static void LoadLinuxLIBs() throws LoadNativeLibrariesFailedException, OutOfOrderException {
            try {

            } catch (java.lang.UnsatisfiedLinkError e) {
                System.out.println();
                System.err.println("ERROR:   Loading Linux LIB's Failed");
                e.printStackTrace();
                LoadState.setStageFailed();
                throw new LoadNativeLibrariesFailedException(e.toString());
            }
        }

        private static void LoadNativeOTAPI() throws LoadNativeLibrariesFailedException, OutOfOrderException {
            try {

                //TODO:     Add Shutdown code here... otapi-java dosn't automaticly close
                //          so what happens is that Java keeps on running, even when the
                //          rest of the program has quit.

                System.out.print("Loading otapi-java:   ");
                System.loadLibrary("otapi-java");
                System.out.println("Success!");

            } catch (java.lang.UnsatisfiedLinkError e) {
                System.out.println();
                System.err.println("ERROR:   Loading OTAPI Native Failed");
                e.printStackTrace();

                LoadState.setStageFailed();
                throw new LoadNativeLibrariesFailedException(e.toString());
            }
        }
    }

    public static class LoadNativeLibrariesFailedException extends Exception {

        private String locationsChecked;

        public LoadNativeLibrariesFailedException() {
            super();             // call superclass constructor
            locationsChecked = "none";
        }

        public LoadNativeLibrariesFailedException(String err) {
            super(err);     // call super class constructor
            locationsChecked = err;  // save message
        }

        public String getError() {
            return locationsChecked;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="InitOTAPI">
    public static class InitOTAPI {

        private InitOTAPI() {
        }

        public static void Attempt() throws InitOTAPIFailedException, OutOfOrderException {
            LoadState.Progress(LoadState.Stages.InitOTAPI);

            API_Init();

            LoadState.setStageComplete();
        }

        private static void API_Init() throws InitOTAPIFailedException, OutOfOrderException {
            //String userDataPath = _configBean.getConfig(ConfigBean.Keys.UserDataPath);

            // --------------------------------------------
            // This has internal logic so that it only actually is called once.
            // Probably not necessary anymore since I moved the call here.
            // The problem was, this call was happening too early (it could
            // happen even if otapi init failed) and then it wouldn't allow
            // itself to trigger again.  This I think should fix that.
            //
            // --------------------------------------------
            boolean bSuccess = false;
            
            if (otapiJNI.OTAPI_Basic_AppStartup())  // Call once at startup. Sets up OpenSSL, signal handlers, etc.
            {
                bSuccess = otapiJNI.OTAPI_Basic_Init(); // Initialize OTAPI context. Loads config file, etc.
            }
            // -------------------------------------------------
            if (bSuccess)
            {
                System.out.println("Load.initOTAPI: SUCCESS invoking OTAPI_Basic_AppStartup and OTAPI_Basic_Init.");
            }
            else // Failed in OTAPI_Basic_AppStartup or OTAPI_Basic_Init.
            {
                String strErrorMsg = "Load.initOTAPI: Failed calling OTAPI_Basic_AppStartup or OTAPI_Basic_Init.";
                throw new InitOTAPIFailedException(strErrorMsg);
            }
        }
    }

    public static class InitOTAPIFailedException extends Exception {

        private String locationsChecked;

        public InitOTAPIFailedException() {
            super();             // call superclass constructor
            locationsChecked = "none";
        }

        public InitOTAPIFailedException(String err) {
            super(err);     // call super class constructor
            locationsChecked = err;  // save message
        }

        public String getError() {
            return locationsChecked;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="SetupPasswordImage">
    public static class SetupPasswordImage {

        public static void Attempt() throws SetupPasswordImageFailedException, OutOfOrderException {
            LoadState.Progress(LoadState.Stages.SetupPasswordImage);

            SetGetImagePath();

            LoadState.setStageComplete();
        }

        private static void SetGetImagePath() throws SetupPasswordImageFailedException {
            // Set In Config... Update Image...
            String path = ConfigBean.Static.getKey(ConfigBean.Static.Keys.PasswordImagePath);
            System.out.println(path);
            if (Utility.VerifyStringVal(path)) {
                Utility.saveImagePath(path);
            } // Try Loding pre-ezxisting path
            else {
                path = Utility.getImagePath();
                System.out.println(path);
                if (Utility.VerifyStringVal(path)) {
                    ConfigBean.Static.setKey(ConfigBean.Static.Keys.PasswordImagePath, path);
                } // Throw Eception... no Path
                else {
                    throw new SetupPasswordImageFailedException("No path available. \n Please set the image path on the settings dialog (to any gif on your computer.)");
                }
            }
        }
    }

    public static class SetupPasswordImageFailedException extends Exception {

        private String locationsChecked;

        public SetupPasswordImageFailedException() {
            super();             // call superclass constructor
            locationsChecked = "none";
        }

        public SetupPasswordImageFailedException(String err) {
            super(err);     // call super class constructor
            locationsChecked = err;  // save message
        }

        public String getError() {
            return locationsChecked;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="SetupPasswordCallback">
    public static class SetupPasswordCallback {

        private static OTCaller   s_theCaller   = null;
        private static OTCallback s_theCallback = null;

        public static void Attempt() throws SetupPasswordCallbackFailedException, OutOfOrderException {
            if (!LoadState.isThisStageComplete(LoadState.Stages.SetupPasswordCallback)) {

                LoadState.Progress(LoadState.Stages.SetupPasswordCallback);
                SetCallerAndCallback();
                LoadState.setStageComplete();
            }
        }

        private static void SetCallerAndCallback() throws SetupPasswordCallbackFailedException, OutOfOrderException {

            s_theCaller   = new OTCaller();
            s_theCallback = new JavaCallback();

            if ((null == s_theCaller) || (null == s_theCallback)) {
                s_theCaller   = null;
                s_theCallback = null;
                LoadState.setStageFailed();
                throw new SetupPasswordCallbackFailedException("OneTimeOnly.GiveItAShot(): ERROR: Failure instantiating caller or callback objects.");
            }

            s_theCaller.setCallback(s_theCallback);
            Boolean bSuccess = otapi.OT_API_Set_PasswordCallback(s_theCaller);

            if (!bSuccess) {
                s_theCaller   = null;
                s_theCallback = null;
                LoadState.setStageFailed();
                throw new SetupPasswordCallbackFailedException("OneTimeOnly.GiveItAShot(): ERROR: Failure instantiating caller or callback objects.");
            }

            System.out.println("OneTimeOnly.GiveItAShot(): SUCCESS setting the password callback.");
        }
    }

    public static class SetupPasswordCallbackFailedException extends Exception {

        private String locationsChecked;

        public SetupPasswordCallbackFailedException() {
            super();             // call superclass constructor
            locationsChecked = "none";
        }

        public SetupPasswordCallbackFailedException(String err) {
            super(err);     // call super class constructor
            locationsChecked = err;  // save message
        }

        public String getError() {
            return locationsChecked;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="LoadWallet">
    public static class LoadWallet {

        private LoadWallet() {
        }

        public static void Attempt() throws LoadWalletFailedException, OutOfOrderException {
            LoadState.Progress(LoadState.Stages.LoadWallet);

            API_LoadWallet();

            LoadState.setStageComplete();
        }

        private static void API_LoadWallet() throws LoadWalletFailedException, OutOfOrderException {
            String walletFilename = _configBean.getConfig(ConfigBean.Keys.WalletFilename);

            if (Utility.VerifyStringVal(walletFilename))
                otapiJNI.OTAPI_Basic_SetWallet(walletFilename);
            
            if (otapiJNI.OTAPI_Basic_LoadWallet()) {
                System.out.println("Load.loadOTWallet: OT_API_LoadWallet() completed successfully.");
            } else {
                LoadState.setStageFailed();
                throw new LoadWalletFailedException("Load.loadOTWallet: Unable To Load Wallet, Maybe Wrong Password?");
            }
        }
    }

    public static class LoadWalletFailedException extends Exception {

        private String locationsChecked;

        public LoadWalletFailedException() {
            super();             // call superclass constructor
            locationsChecked = "none";
        }

        public LoadWalletFailedException(String err) {
            super(err);     // call super class constructor
            locationsChecked = err;  // save message
        }

        public String getError() {
            return locationsChecked;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="SwitchWallet">
    public static class SwitchWallet {

        private SwitchWallet() {
        }

        public static void Attempt() throws SwitchWalletFailedException, OutOfOrderException {
            LoadState.Progress(LoadState.Stages.Opt_SwitchWallet);

            API_SwitchWallet();

            LoadState.setStageComplete();
        }

        private static void API_SwitchWallet() throws SwitchWalletFailedException, OutOfOrderException {
            String walletFilename = _configBean.getConfig(ConfigBean.Keys.WalletFilename);
            if (Utility.VerifyStringVal(walletFilename) && otapiJNI.OTAPI_Basic_SetWallet(walletFilename))
                if (otapiJNI.OTAPI_Basic_SwitchWallet()) {
                    System.out.println("Load.loadOTWallet: OT_API_SwitchWallet() completed successfully.");
                } else {
                    LoadState.setStageFailed();
                    throw new SwitchWalletFailedException("Load.loadOTWallet: Unable To Switch Wallet, Maybe Wrong Password?");
                }
        }
    }

    public static class SwitchWalletFailedException extends Exception {

        private String locationsChecked;

        public SwitchWalletFailedException() {
            super();             // call superclass constructor
            locationsChecked = "none";
        }

        public SwitchWalletFailedException(String err) {
            super(err);     // call super class constructor
            locationsChecked = err;  // save message
        }

        public String getError() {
            return locationsChecked;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Helpers">
    public static void setJavaPaths(ConfigBean configBean) {
        System.out.println();
        System.out.print("Updating JavaPaths:  ");

        String paths = configBean.getConfig(ConfigBean.Keys.JavaPath);
        if (Utility.VerifyStringVal(paths)) {
            System.out.println("Custom JavaPaths... adding now!");
            try {
                Utility.addDirToRuntime(paths);
                System.out.println("Updating JavaPaths...   Success!");
                System.out.println();

            } catch (IOException e) {
                System.err.println("Updating JavaPaths...     Failure!");
                System.err.println(e.toString());
            }
        } else {
            System.out.println("Skipping...  No custom Java Paths!");
        }
    }

    public static class JavaPaths {

        private static Set<String> _paths = new HashSet<String>();
        private static JavaPathsListModel _javaPathsListModel;
        private int _selectedIndex;
        private static ReturnAction _returnActionToSettings;
        private static ReturnAction _returnActionToPathDialog;

        private static class JavaPathsListModel extends AbstractListModel {

            public void fireContentsChanged() {
                if (_returnActionToPathDialog != null) {
                    if (_paths.isEmpty()) {
                        _returnActionToPathDialog.returnAction("Disabled");
                    } else {
                        _returnActionToPathDialog.returnAction("Enabled");
                    }
                }

                fireContentsChanged(this, 0, this.getSize());
            }

            @Override
            public int getSize() {
                return _paths.size();
            }

            @Override
            public Object getElementAt(int index) {
                return _paths.toArray(new String[getSize()])[index];
            }
        }

        public AbstractListModel getAbstractListModel() {
            return _javaPathsListModel;
        }

        public JavaPaths(ReturnAction returnAction) {
            this(returnAction, null);
        }

        public JavaPaths(ReturnAction returnAction, String paths) {
            _javaPathsListModel = new JavaPathsListModel();
            _returnActionToSettings = returnAction;
            if (Utility.VerifyStringVal(paths)
                    && !paths.isEmpty()) {
                addPaths(paths);
            }
            _javaPathsListModel.fireContentsChanged();
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

        public final void setSelectedElement(int index) {
            if (index < 1) {
                index = 0;
            }
            _selectedIndex = index;
        }

        public final String getSelectedElement() {
            if (_selectedIndex < 1) {
                _selectedIndex = 0;
            }
            Iterator<String> setIterator = _paths.iterator();
            Integer index = _selectedIndex;
            String ret = null;
            while (setIterator.hasNext()
                    && !(index < 0)) {
                ret = setIterator.next();
                index--;
            }
            return ret;
        }

        public final boolean isEmpty() {
            return _paths.isEmpty();
        }

        public final void addPaths(String paths) {
            final List<String> pathList = new CopyOnWriteArrayList<String>();
            pathList.addAll(Arrays.asList(paths.split(File.pathSeparator)));
            final Iterator<String> listIterator = pathList.iterator();
            String path;
            while (listIterator.hasNext()) {
                path = listIterator.next(); //.toLowerCase();
                if (!path.equalsIgnoreCase(".")) {
                    System.out.println("Load.JavaPaths: Adding path: " + path.toString());
                    _paths.add(path);
                }
            }
            _javaPathsListModel.fireContentsChanged();
            _returnActionToSettings.returnAction(this.toString());
        }

        public final void addPath(String path) {
            if (Utility.VerifyStringVal(path)
                    || path.isEmpty()) {
                addPaths(path);
            }
        }

        public final void remove(String path) {
            if (Utility.VerifyStringVal(path)
                    && !_paths.isEmpty()) {
                _paths.remove(path);
                _javaPathsListModel.fireContentsChanged();
                _returnActionToSettings.returnAction(this.toString());
            }
        }

        public final void removeSelected() {
            remove(getSelectedElement());
            setSelectedElement(_paths.toArray().length);
        }

        public final void setRemoveReturnAction(ReturnAction r) {
            _returnActionToPathDialog = r;
        }
    }

    public static String getUserAppDataLocation() {
        return appdataDirectory(getOS()).toString();
    }

    public static enum typeOS {

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
    //</editor-fold>
}
