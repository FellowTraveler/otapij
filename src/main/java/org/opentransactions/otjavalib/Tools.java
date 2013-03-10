/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opentransactions.otjavalib;

import org.opentransactions.otjavalib.util.WinRegistry;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
                 

            
                try {
                    String value = WinRegistry.readString(WinRegistry.HKEY_LOCAL_MACHINE, "SOFTWARE\\Open-Transactions", "Path");
                    if (null == value) {
                        return "";
                    }
                    Logger.getLogger(Tools.class.getName()).log(Level.INFO, "Found OT path in registry:{0}", value);
                    
                    
                    return value;

                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        return "";
    }
    
    static String getJavaPathUserMessage(final typeOS os)
    {
        if (os.equals(typeOS.WINDOWS)) {
            if (System.getProperty("os.arch").contentEquals("x86")) {
                Logger.getLogger(Tools.class.getName()).log(Level.INFO, "We are on x32 Java");
                return "Windows x32 Java: You need to select the x32 Opentransctions runrimes.";
            }

            if (System.getProperty("os.arch").contentEquals("amd64")) {
                Logger.getLogger(Tools.class.getName()).log(Level.INFO, "We are on x32 Java");
                return "Windows x64 Java: You need to select the x64 Opentransctions runrimes.";
            }
        }
        
        if (os.equals(typeOS.UNIX)) {
                Logger.getLogger(Tools.class.getName()).log(Level.INFO, "We are on UNIX");
                return "We are running on UNIX, please select the correct lib folder.";
        }
        
        return "";
    }
    
    
    static enum typeOS {
        WINDOWS, UNIX
    }
    
    static void loadNative(final typeOS os) throws UnsatisfiedLinkError {
        
        final Logger l = Logger.getLogger(Tools.class.getName());

        // If windows, load up the dependancy libaries:

        l.log(Level.INFO, "Loading Native OpenTransactions...");
        
        if (os.equals(typeOS.WINDOWS)) {
            
            l.log(Level.FINE, "Atempting to load libzmq");
            System.loadLibrary("libzmq");
            l.log(Level.FINE, "Success loading libzmq");

            l.log(Level.FINE, "Atempting to load chaiscript");
            System.loadLibrary("chaiscript");
            l.log(Level.FINE, "Success loading chaiscript");

            l.log(Level.FINE, "Atempting to load otlib");
            System.loadLibrary("otlib");
            l.log(Level.FINE, "Success loading otlib");

            l.log(Level.FINE, "Atempting to load otapi");
            System.loadLibrary("otapi");
            l.log(Level.FINE, "Success loading otapi");
        }

        // all
        {
            l.log(Level.FINE, "Atempting to load otapi-java");
            System.loadLibrary("otapi-java");
            l.log(Level.FINE, "Success loading otapi-java");
        }
        
        l.log(Level.INFO, "Success Loading Native OpenTransactions!");
    }
    
    static typeOS getOS() {
        String OS = System.getProperty("os.name").toUpperCase();
        if (OS.contains("WIN")) {
            return typeOS.WINDOWS;
        }
        else return typeOS.UNIX;
    }
}
