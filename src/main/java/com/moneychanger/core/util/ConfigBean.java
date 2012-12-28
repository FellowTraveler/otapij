/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moneychanger.core.util;

import java.util.EnumMap;
import java.util.Map;
import java.util.prefs.Preferences;
import org.opentransactions.otjavalib.util.Utility;

/**
 *
 * @author Cameron Garnham
 */
public class ConfigBean {

    private Preferences _prefs = Preferences.userNodeForPackage(this.getClass());

    public static enum Keys {

        JavaPath, JavaPathSet, LoomPasswordID, MarketMaxDepth, NbrTransactionCount, PasswordImageSaved, RipplePasswordID, RippleUsernameID, RetryCount, Timeout, UserDataPath, WaitTime, WalletFilename, LastLoadSuccessfull, LastLoad;
    }

    public static class Static {

        public static enum Keys {

            PasswordImagePath, LoomPasswordID, MarketMaxDepth, RipplePasswordID, RippleUsernameID, NbrTransactionCount, RetryCount, Timeout, WalletFilename, WaitTime
        }
        private static final Map<Keys, String> _StaticMap = new EnumMap<Keys, String>(Keys.class);
        private static final Map<Keys, String> _DefaultsMap = new EnumMap<Keys, String>(Keys.class);

        static {
            setDefaultValues();
            _StaticMap.putAll(_DefaultsMap);
        }

        private static void setDefaultValues() {
            _DefaultsMap.put(Keys.LoomPasswordID, "passphrase");
            _DefaultsMap.put(Keys.MarketMaxDepth,"100");
            _DefaultsMap.put(Keys.RipplePasswordID, "password");
            _DefaultsMap.put(Keys.RippleUsernameID, "username");
            _DefaultsMap.put(Keys.NbrTransactionCount, Integer.toString(10));
            _DefaultsMap.put(Keys.RetryCount, Integer.toString(1));
            _DefaultsMap.put(Keys.Timeout, Integer.toString(1));
            _DefaultsMap.put(Keys.WalletFilename, "wallet.xml");
            _DefaultsMap.put(Keys.WaitTime, Integer.toString(1));
        }

        public static void setKey(Keys key, String value) {
            _StaticMap.put(key, value);
        }

        public static String getKey(Keys key) {
            if (!(_StaticMap.containsKey(key))) {
                return null;
            } else {
                return _StaticMap.get(key);
            }
        }

        public static String getKey(String key) {
            try {
                Keys k = Keys.valueOf(key);
                return _StaticMap.get(k);
            }
            catch (IllegalArgumentException e)
            {
                return "";
            }
        }
        // Overrides

        public static <T extends Object> void setGenericKey(Keys key, T value) {
            setKey(key, value.toString());
        }

        public static Integer getKeyAsInteger(Keys key) {
            return Integer.parseInt(getKey(key));
        }

        public static Long getKeyAsLong(Keys key) {
            return Long.parseLong(getKey(key));
        }

        public static Boolean getKeyAsBoolean(Keys key) {
            return Boolean.parseBoolean(getKey(key));
        }
    }

    public ConfigBean() {
        if (!configurationExist()) {
            resetConfig();
        }
    }

    public String getConfig(Keys key) {
        String ret = _prefs.get(key.name(), null);
        if (!Utility.VerifyStringVal(ret)) {
            return "";
        }
        return ret;
    }

    public void setConfig(Keys key, String value) {
        if (!Utility.VerifyStringVal(value)) {
            value = "";
        }
        _prefs.put(key.name(), value);
    }

    public final Boolean configurationExist() {
        return Boolean.parseBoolean(_prefs.get("ConfigurationExist", null));
    }

    public final void resetConfig() {
        for (Keys key : Keys.values()) {
            String value = Static.getKey(key.name());

            if (Utility.VerifyStringVal(value)) {
                setConfig(key, value);
            } else {
                setConfig(key, "");
            }
        }
        _prefs.put("ConfigurationExist", Boolean.TRUE.toString());
    }
}
