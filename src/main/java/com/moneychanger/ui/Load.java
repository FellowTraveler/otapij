/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moneychanger.ui;

import java.io.File;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opentransactions.jni.core.OTAPI_Basic;
import org.opentransactions.jni.core.OTCallback;
import org.opentransactions.jni.core.OTCaller;
import org.opentransactions.jni.core.Storable;
import org.opentransactions.jni.core.StoredObjectType;
import org.opentransactions.jni.core.StringMap;
import org.opentransactions.jni.core.otapi;
import org.opentransactions.jni.core.otapiJNI;

/**
 *
 * @author Cameron Garnham
 */
public class Load {

    private static Load ptrThis = null;

    public synchronized static Load It() {
        if (null == ptrThis) {
            ptrThis = new Load();
        }
        return ptrThis;
    }
    private boolean isNativeLoaded = false;
    private boolean isInitialized = false;
    private boolean isPasswordImageSet = false;
    private boolean isPasswordCallbackSet = false;
    private boolean isWalletLoaded = false;

    public interface IJavaPath {
        String GetJavaPathFromUser(String message);
    }
    
    public interface IPasswordImage {

        String GetPasswordImageFromUser(String message);

        boolean SetPasswordImage(String path);
    }
    
    
    

    // The code here will only ever happen once per instance
    private Load() {
    }

    boolean GetIsLoaded() {
        return isWalletLoaded;
    }

    public void InitNative(IJavaPath javaPathCallback, String optionalPath) throws LoadingOpenTransactionsFailure {
        final Logger l = Logger.getLogger(Load.class.getName());

        String extra_path = optionalPath.isEmpty() ? "" : optionalPath;

        if (isNativeLoaded) {
            throw new LoadingOpenTransactionsFailure("Native Already Loaded!");
        }

        boolean bFirstAttempt = true;
        boolean bUsingDefaultPath = false;
        
        for (;;) {

            try {
                Collection<String> pathsSet = null;

                if (extra_path.isEmpty()) {
                    pathsSet = Tools.appendPathToRuntime(Tools.getDefaultLibPath(Tools.getOS()));
                    bUsingDefaultPath = true;
                } else {
                    pathsSet = Tools.appendPathToRuntime(extra_path);
                }

                l.log(Level.INFO, null, pathsSet);

            } catch (IllegalAccessException ex) {
                l.log(Level.SEVERE, null, ex);
                System.exit(-1); // bad excetion
            } catch (NoSuchFieldException ex) {
                l.log(Level.SEVERE, null, ex);
                System.exit(-1); // bad excetion
            }

            try {
                Tools.loadNative(Tools.getOS());
            } catch (UnsatisfiedLinkError ex) {

                if (bFirstAttempt) {
                    l.log(Level.INFO, null, ex);
                    bFirstAttempt = false;
                }
                else {
                    l.log(Level.SEVERE, null, ex);
                }
                    
                extra_path = javaPathCallback.GetJavaPathFromUser("Failed To Find OT");
                continue;
            }
            break; //success
        }
        isNativeLoaded = true;
    }

    public void Init() throws LoadingOpenTransactionsFailure {
        final Logger l = Logger.getLogger(Load.class.getName());

        if (!isNativeLoaded) {
            throw new LoadingOpenTransactionsFailure("Native Libs Not Loaded!");
        }
        if (isInitialized) {
            throw new LoadingOpenTransactionsFailure("Is Already Initialized");
        }

        // --------------------------------------------
        // This has internal logic so that it only actually is called once.
        // Probably not necessary anymore since I moved the call here.
        // The problem was, this call was happening too early (it could
        // happen even if otapi init failed) and then it wouldn't allow
        // itself to trigger again.  This I think should fix that.
        //
        // --------------------------------------------

        boolean bSuccess = false;

        if (otapiJNI.OTAPI_Basic_AppStartup()) // Call once at startup. Sets up OpenSSL, signal handlers, etc.
        {
            bSuccess = otapiJNI.OTAPI_Basic_Init(); // Initialize OTAPI context. Loads config file, etc.
        }
        // -------------------------------------------------
        if (bSuccess) {
            System.out.println("Load.initOTAPI: SUCCESS invoking OTAPI_Basic_AppStartup and OTAPI_Basic_Init.");
        } else // Failed in OTAPI_Basic_AppStartup or OTAPI_Basic_Init.
        {
            String strErrorMsg = "Load.initOTAPI: Failed calling OTAPI_Basic_AppStartup or OTAPI_Basic_Init.";
            throw new LoadingOpenTransactionsFailure(strErrorMsg);
        }

        isInitialized = true;
    }

    public void SetupPasswordImage(IPasswordImage passwordImage) throws LoadingOpenTransactionsFailure {

        if (!isInitialized) {
            throw new LoadingOpenTransactionsFailure("Is Not Initialized");
        }
        if (isPasswordImageSet) {
            throw new LoadingOpenTransactionsFailure("Password Image Already Set!");
        }

        String imagePath = "";
        boolean bHaveImage = false;

        // First Lets Check if we already have a password image. (we don't need annother one)
        if (otapi.Exists("moneychanger", "settings.dat")) {

            Storable storable = null;
            StringMap stringMap = null;

            storable = otapi.QueryObject(StoredObjectType.STORED_OBJ_STRING_MAP, "moneychanger", "settings.dat");

            if (null != storable) {

                stringMap = StringMap.ot_dynamic_cast(storable);
                imagePath = stringMap.GetValue("ImagePath");

                File f = new File(imagePath);
                if (f.exists()) {
                    // Good we have a password Image
                    bHaveImage = true;
                }
            }
        }

        // We don't have an image... lets get it from the user, then save it.
        if (!bHaveImage) {

            for (;;) {
                imagePath = passwordImage.GetPasswordImageFromUser("passwordImage");

                File f = new File(imagePath);
                if (f.exists()) {
                    // Good we have a password Image
                    break;
                }
            }

            StringMap stringMap = null;  // we are about to create this object
            Storable storable = otapi.CreateObject(StoredObjectType.STORED_OBJ_STRING_MAP);

            if (storable != null) {
                stringMap = StringMap.ot_dynamic_cast(storable);
                System.out.println("stringMap:" + stringMap);

                if (stringMap != null) {
                    //stringMap.SetValue("ImagePath", "~/.ot/default.gif");
                    stringMap.SetValue("ImagePath", imagePath);
                    bHaveImage = otapi.StoreObject(stringMap, "moneychanger", "settings.dat");
                }
            }
        }

        if (bHaveImage) {
            passwordImage.SetPasswordImage(imagePath);
        } else {
            throw new LoadingOpenTransactionsFailure("Password image not Set!");
        }
        isPasswordImageSet = true;
    }

    public void SetupPasswordCallback(OTCaller passwordCaller, OTCallback passwordCallback) throws LoadingOpenTransactionsFailure {

        if (!isPasswordImageSet) {
            throw new LoadingOpenTransactionsFailure("Must Set Password Image First!");
        }
        if (isPasswordCallbackSet) {
            throw new LoadingOpenTransactionsFailure("Already Have Set Password Callback!");
        }

        if (null == passwordCallback) {
            throw new IllegalArgumentException("passwordCallback is null");
        }

        try {
            passwordCaller.setCallback(passwordCallback);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new LoadingOpenTransactionsFailure("Unable to Set Password Callback");
        }
        
        Boolean bSuccess = otapi.OT_API_Set_PasswordCallback(passwordCaller);
        if (!bSuccess)
        {
            passwordCaller = null;
            passwordCallback = null;
            throw new LoadingOpenTransactionsFailure("Unable to Set Password Callback");
        }
        
        isPasswordCallbackSet = true;
    }

    public void LoadWallet() throws LoadingOpenTransactionsFailure {

        if (!isPasswordCallbackSet) {
            throw new LoadingOpenTransactionsFailure("Must Set Password Callback First!");
        }
        if (isWalletLoaded) {
            throw new LoadingOpenTransactionsFailure("Already Loaded!");
        }

        if (!OTAPI_Basic.LoadWallet()) {
            throw new LoadingOpenTransactionsFailure("Unable to Load Wallet");
        }

        isWalletLoaded = true;
    }

    static public class LoadingOpenTransactionsFailure extends Exception {

        private final String _message;

        public LoadingOpenTransactionsFailure(final String message) {
            _message = message;
        }

        public final String getError() {
            return _message;
        }
    }
}
