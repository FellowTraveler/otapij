/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger.main;

import com.ot.app.moneychanger.main.helpers.AppdataDirectory;
import com.ot.app.moneychanger.main.helpers.OSType;
import com.ot.app.moneychanger.main.helpers.OSType.typeOS;
import java.io.File;
import java.util.prefs.Preferences;

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
    private final static String KEY_CONFIG_UPDATED = "ConfigUpdated";
//----------------------------------------------------------------------------
//  Instance Data and Constructors
//----------------------------------------------------------------------------
    Preferences _prefs = Preferences.userNodeForPackage(this.getClass());

//----------------------------------------------------------------------------
//  Accessors
//----------------------------------------------------------------------------
    //TimeOUt
    public String timeOutInfo() {
        return "Time to try connecting to the server before giving up.";
    }

    public String timeOutRegex() {
        return "[0-9]*";
    }

    public String getTimeOut() {
        return _prefs.get(KEY_TIMEOUT, null);
    }

    public void setTimeOut(String name) {
        _prefs.put(KEY_TIMEOUT, name);
    }

    //OTPath
    public String OTPathInfo() {
        return "The directory that contains 'libotapi-java'.";
    }

    public String OTPathRegex() {
        return "";
    }

    public String getOTPath() {
        return _prefs.get(KEY_OTAPI_PATH, null);
    }

    public void setOTPath(String name) {
        _prefs.put(KEY_OTAPI_PATH, name);
    }

    //ZMQ PAth
    public String ZMQPathInfo() {
        return "The directory that contains 'libzmq'; for windows only.";
    }

    public String ZMQPathRegex() {
        return "[0-9a-zA-Z///.]*";
    }

    public String getZMQPath() {
        return _prefs.get(KEY_ZMQ_PATH, null);
    }

    public void setZMQPath(String name) {
        _prefs.put(KEY_ZMQ_PATH, name);
    }

    //UserDataPath
    public String userDataPathInfo() {
        return "The directory that contains the client data.";
    }

    public String userDataPathRegex() {
        return "(.)*";
    }

    public String getUserDataPath() {
        return _prefs.get(KEY_USERDATA_PATH, null);
    }

    public void setUserDataPath(String name) {
        _prefs.put(KEY_USERDATA_PATH, name);
    }

    //WalletFilename
    public String walletFilenameInfo() {
        return "The name of the wallet file.";
    }

    public String walletFilenameRegex() {
        return "[0-9a-zA-Z/.]*";
    }

    public String getWalletFilename() {
        return _prefs.get(KEY_WALLET_FILENAME, null);
    }

    public void setWalletFilename(String name) {
        _prefs.put(KEY_WALLET_FILENAME, name);
    }

    //ConfigUpdated
    public String configUpdatedInfo() {
        return "The name of the wallet file.";
    }

    public Boolean getConfigUpdated() {
        if (_prefs.get(KEY_CONFIG_UPDATED, null).equalsIgnoreCase("True")) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public void setConfigUpdated(Boolean name) {
        if (name) {
            _prefs.put(KEY_CONFIG_UPDATED, "True");
        } else {
            _prefs.put(KEY_CONFIG_UPDATED, "False");
        }
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

        if (getTimeOut() == null)
        {
            return false;
        }
        if (getUserDataPath() == null)
        {
            return false;
        }
        
        if (getTimeOut().isEmpty()) {
            return false;
        }
//        if (getOTPath().isEmpty()) {
//            return false;
//        }
        if (getUserDataPath().isEmpty()) {
            return false;
        }
        if (getTimeOut().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void resetConfig() {
        setOTPath("");
        setTimeOut("");
        setUserDataPath("");
        setWalletFilename("");
        setConfigUpdated(Boolean.TRUE);
    }
}
