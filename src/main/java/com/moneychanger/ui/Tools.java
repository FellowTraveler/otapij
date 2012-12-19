/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moneychanger.ui;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cameron Garnham
 */
public class Tools {
    
    static Collection<String> appendPathToRuntime(final String strPath) throws IllegalAccessException, NoSuchFieldException {
        Set<String> pathSet = new HashSet<String>();


        Field field = ClassLoader.class.getDeclaredField("usr_paths");
        field.setAccessible(true);

        pathSet.addAll(Arrays.asList((String[]) field.get(null)));
        pathSet.addAll(Arrays.asList(strPath.split(File.pathSeparator)));

        if (pathSet.contains(".")) {
            pathSet.remove(".");
        }
        if (pathSet.contains("")) {
            pathSet.remove("");
        }
        if (pathSet.contains(" ")) {
            pathSet.remove(" ");
        }
        if (pathSet.contains(File.pathSeparator)) {
            pathSet.remove(File.pathSeparator);
        }

        // now we have just a set of all the paths (including the new one).

        StringBuilder pathsString = new StringBuilder();
        Iterator<String> setIterator = pathSet.iterator();
        while (setIterator.hasNext()) {
            String next = setIterator.next();
            //System.out.println("Path:  " + next);
            pathsString.append(next);
            if (setIterator.hasNext()) {
                pathsString.append(File.pathSeparator);
            }
        }

        field.set(null, pathSet.toArray(new String[0]));
        System.setProperty("java.library.path", pathsString.toString());
        //System.out.println("Utility.addDirToRuntime: Setting java.library.path: " + pathsString.toString());

        return pathSet;
    }
    
    static String getDefaultLibPath(final typeOS os) {
        if (os.equals(typeOS.WINDOWS)) {
            if (System.getProperty("os.arch").contentEquals("x86")) {
                return ".\\lib\\Win32\\Release";
            }

            System.out.println(System.getProperty("os.arch"));
            if (System.getProperty("os.arch").contentEquals("amd64")) {
                return ".\\lib\\x64\\Release";
            } else {
                return "";
            }
        } else {
            // I don't know if we can have a default place on non-windows oses.
            return "";
        }
    }
    
    static enum typeOS {
        WINDOWS, UNIX
    }
    
    static void loadNative(final typeOS os) throws UnsatisfiedLinkError {
        
        final Logger l = Logger.getLogger(Load.class.getName());

        // If windows, load up the dependancy libaries:

        if (os.equals(typeOS.WINDOWS)) {
            
            l.log(Level.INFO, "Atempting to load libzmq");
            System.loadLibrary("libzmq");
            l.log(Level.INFO, "Success loading libzmq");

            l.log(Level.INFO, "Atempting to load chaiscript");
            System.loadLibrary("chaiscript");
            l.log(Level.INFO, "Success loading chaiscript");

            l.log(Level.INFO, "Atempting to load otlib");
            System.loadLibrary("otlib");
            l.log(Level.INFO, "Success loading otlib");

            l.log(Level.INFO, "Atempting to load otapi");
            System.loadLibrary("otapi");
            l.log(Level.INFO, "Success loading otapi");
        }

        // all
        {
            l.log(Level.INFO, "Atempting to load otapi-java");
            System.loadLibrary("otapi-java");
            l.log(Level.INFO, "Success loading otapi-java");
        }
    }
    
    static typeOS getOS() {
        String OS = System.getProperty("os.name").toUpperCase();
        if (OS.contains("WIN")) {
            return typeOS.WINDOWS;
        }
        else return typeOS.UNIX;
    }
}
