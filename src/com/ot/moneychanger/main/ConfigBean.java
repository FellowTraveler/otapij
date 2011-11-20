/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.moneychanger.main;

import java.util.prefs.Preferences;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author cameron
 */
/**
 *  Provides bean-style access to application configuration data, backed by the
 *  Preferences API. Each preference has the following methods:
 *  <ul>
 *  <li> a getter, with the name <code>getXXX()</code>
 *  <li> a setter, with the name <code>setXXX()</code>
 *  <li> a method that returns validation regex, <code>xxxRegex()</code> (may
 *       be null, in which case no validation occurs)
 *  <li> a method that returns a description of the preference, <code>xxxInfo()</code>,
 *       used for tooltip text
 *  <ul>
 */
public class ConfigBean {
    
    private final static String KEY_TIMEOUT = "TimeOut";
    private final static String KEY_OTAPI_PATH = "OTPath";
    private final static String KEY_ZMQ_PATH = "ZMQPath";
    private final static String KEY_USERDATA_PATH = "UserDataPath";
    private final static String KEY_WALLET_FILENAME = "WalletFilename";
//----------------------------------------------------------------------------
//  Instance Data and Constructors
//----------------------------------------------------------------------------
    Preferences _prefs = Preferences.userNodeForPackage(this.getClass());

//----------------------------------------------------------------------------
//  Accessors
//----------------------------------------------------------------------------

    //TimeOUt
    public static String timeOutInfo() {
        return "Time to try connecting to the server before giving up.";
    }

    public static String timeOutRegex() {
        return "[0-9]*";
    }

    public String getTimeOut() {
        return _prefs.get(KEY_TIMEOUT, null);
    }

    public void setTimeOut(String name) {
        _prefs.put(KEY_TIMEOUT, name);
    }
    
    //OTPath
    public static String OTPathInfo() {
        return "The directory that contains 'libotapi-java'.";
    }

    public static String OTPathRegex() {
        return "[0-9a-zA-Z///.]*";
    }

    public String getOTPath() {
        return _prefs.get(KEY_OTAPI_PATH, null);
    }

    public void setOTPath(String name) {
        _prefs.put(KEY_OTAPI_PATH, name);
    }
    
    //ZMQ PAth
    public static String ZMQPathInfo() {
        return "The directory that contains 'libzmq'; for windows only.";
    }

    public static String ZMQPathRegex() {
        return "[0-9a-zA-Z///.]*";
    }

    public String getZMQPath() {
        return _prefs.get(KEY_ZMQ_PATH, null);
    }

    public void setZMQPath(String name) {
        _prefs.put(KEY_ZMQ_PATH, name);
    }
    
    //UserDataPath
    public static String userDataPathInfo() {
        return "The directory that contains the client data.";
    }

    public static String userDataPathRegex() {
        return "[0-9a-zA-Z///.]*";
    }

    public String getUserDataPath() {
        return _prefs.get(KEY_USERDATA_PATH, null);
    }

    public void setUserDataPath(String name) {
        _prefs.put(KEY_USERDATA_PATH, name);
    }
    
    //WalletFilename
    public static String walletFilenameInfo() {
        return "The name of the wallet file.";
    }

    public static String walletFilenameRegex() {
        return "[0-9a-zA-Z/.]*";
    }

    public String getWalletFilename() {
        return _prefs.get(KEY_WALLET_FILENAME, null);
    }

    public void setWalletFilename(String name) {
        _prefs.put(KEY_WALLET_FILENAME, name);
    }

//----------------------------------------------------------------------------
//  Other Useful Functions
//----------------------------------------------------------------------------
    /**
     *  Determines whether we have sufficient prefs set to be able to
     *  do anything. This is used during application start, to decide
     *  whether to show the prefs window as the first action.
     */
    public boolean isConfigComplete() {
        
        return StringUtils.isNotEmpty(getUserDataPath())
                && StringUtils.isNotEmpty(getWalletFilename());
    }
}
