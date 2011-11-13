/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrapper.ui;

import com.wrapper.core.jni.otapi;
import com.wrapper.core.util.Configuration;
import java.io.IOException;
import java.util.*;
import javax.swing.AbstractListModel;

/**
 *
 * @author cameron
 */
public class Load {

    public static void loadOTAPI() throws ApiNotLoadedException {
        try {
            if (System.getProperty("os.name") != null
                    && (System.getProperty("os.name").startsWith("windows")
                    || System.getProperty("os.name").startsWith("Windows"))) {
                System.loadLibrary("libzmq");
            }
        } catch (java.lang.UnsatisfiedLinkError e) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append(System.getProperty("line.separator"));
            errorMessage.append("libzmq not in LD path:");
            errorMessage.append(System.getProperty("line.separator"));
            errorMessage.append(System.getProperty("java.library.path").replace(":", System.getProperty("line.separator")));
            throw new ApiNotLoadedException(errorMessage.toString());
        }
        try {
            System.loadLibrary("libotapi-java");
        } catch (java.lang.UnsatisfiedLinkError e) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append(System.getProperty("line.separator"));
            errorMessage.append("libotapi-java not in LD path:");
            errorMessage.append(System.getProperty("line.separator"));
            errorMessage.append(System.getProperty("java.library.path").replace(":", System.getProperty("line.separator")));
            throw new ApiNotLoadedException(errorMessage.toString());
        }
    }

    public static void loadOTAPI(JavaPaths paths) throws ApiNotLoadedException {
        try {
            for (String path : paths.getPaths()) {
            }
        } catch (java.lang.UnsatisfiedLinkError e) {
            throw new ApiNotLoadedException("Not In LD Path");
        }
    }

    public static void loadAppData() throws AppDataNotLoadedException {
        otapi.OT_API_Init(appdataDirectory(getOS()).append("/.ot/client_data").toString());
        otapi.OT_API_LoadWallet("wallet.xml");
    }

    public static void loadAppData(String appDataLocation) throws AppDataNotLoadedException {
        otapi.OT_API_Init(appDataLocation);
        otapi.OT_API_LoadWallet("wallet.xml");
    }

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

    // <editor-fold desc="Helpers> defaultstate="collapsed">
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
            StringBuilder pathList = new StringBuilder();
            for (String path : _paths) {
                pathList.append(path);
                pathList.append(";");
            }
            return pathList.toString();
        }

        public JavaPaths() {
        }

        public JavaPaths(List<String> paths) {
            _paths.addAll(paths);
        }

        public void addDefultPath(typeOS os) {
            switch (os) {
                case WIN: {
                    addPath("windows");
                    break;
                }
                case LINUX: {
                    addPath("linux");
                    break;
                }
                case MAC: {
                    addPath("mac-os");
                    break;
                }
                case UNIX: {
                    addPath("unix");
                    break;
                }
                case OTHER: {
                    addPath("other");
                    break;
                }
            }
        }

        public void addPath(String path) {
            _paths.add(path.toLowerCase());
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
    // <editor-fold desc="Exceptions"> defaultstate="collapsed">
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
