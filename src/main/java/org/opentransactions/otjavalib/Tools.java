/************************************************************
-----BEGIN PGP SIGNED MESSAGE-----
Hash: SHA1

 *                 M O N E Y C H A N G E R
 *
 *  Open Transactions:
 *       Financial Cryptography and Digital Cash
 *       Library, Protocol, API, Server, CLI, GUI 
 *    
 *       -- Anonymous Numbered Accounts.
 *       -- Untraceable Digital Cash.
 *       -- Triple-Signed Receipts.
 *       -- Cheques, Vouchers, Transfers, Inboxes.
 *       -- Basket Currencies, Markets, Payment Plans.
 *       -- Signed, XML, Ricardian-style Contracts.
 *       -- Scripted smart contracts.
 *    
 *  Copyright (C) 2010-2013 by "Fellow Traveler" (A pseudonym)
 *
 *  EMAIL:
 *  FellowTraveler@rayservers.net --- SEE PGP KEY BELOW.
 *  F3llowTraveler@gmail.com --- (not preferred.)
 *  
 *  FINGERPRINT:
 *  9DD5 90EB 9292 4B48 0484  7910 0308 00ED F951 BB8E
 *
 *  BITCOIN:  1NtTPVVjDsUfDWybS4BwvHpG2pdS9RnYyQ
 *
 *  OFFICIAL PROJECT WIKI:
 *  https://github.com/FellowTraveler/Moneychanger
 *  https://github.com/FellowTraveler/Open-Transactions/wiki
 *
 *  WEBSITE:
 *  http://www.OpenTransactions.org/
 *    
 *  Components and licensing:
 *   -- Moneychanger..A Java client GUI.....LICENSE:.....GPLv3
 *   -- otlib.........A class library.......LICENSE:...LAGPLv3
 *   -- otapi.........A client API..........LICENSE:...LAGPLv3
 *   -- opentxs/ot....Command-line client...LICENSE:...LAGPLv3
 *   -- otserver......Server Application....LICENSE:....AGPLv3
 *  Github.com/FellowTraveler/Open-Transactions/wiki/Components
 *
 *  All of the above OT components were designed and written by
 *  Fellow Traveler, with the exception of Moneychanger, which
 *  was contracted out to Vicky C (bitcointrader4@gmail.com).
 *  The open-source community has since actively contributed.
 *
 *  -----------------------------------------------------
 *
 *   LICENSE:
 *   This program is free software: you can redistribute it
 *   and/or modify it under the terms of the GNU General
 *   Public License as published by the Free Software
 *   Foundation, either version 3 of the License, or (at your
 *   option) any later version.
 *
 *   You should have received a copy of the GNU General
 *   Public License along with this program.  If not, see:
 *   http://www.gnu.org/licenses/
 *
 *   If you would like to use this software outside of the free
 *   software license, please contact FellowTraveler.
 *   (Unfortunately many will run anonymously and untraceably,
 *   so who could really stop them?)
 *   
 *   DISCLAIMER:
 *   This program is distributed in the hope that it will be
 *   useful, but WITHOUT ANY WARRANTY; without even the implied
 *   warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 *   PURPOSE.  See the GNU General Public License for more
 *   details.

-----BEGIN PGP SIGNATURE-----
Version: GnuPG v1.4.9 (Darwin)

iQIcBAEBAgAGBQJRSsfMAAoJEAMIAO35UbuOCTQQAJWUR6l+IbUGYPfudumDBZep
XWu5aUVXPt+HTetWobTT3VrSEoQTV+t3Qk10EHzIIQNIvDNkA3cfQod5xMk3CZgm
MuA1PTDZdmrcm1lf92rVULaiB2tHYuPKYryFfkmNcidoWaJAI0ny/AE4gSdopKuU
MCLhB+fWpPv+nK9Qjb8duvRyFjYeLxrnwjfy05zNIY5Fj4HsCzmf6G6xqbUOZzBA
Zc4RjfKeg9MVJl4ObIKhDfpicCTVZkgFPVGeok/KtmiPRgV1HtaHqib4RiN9VMkr
YKbOUOb931pukRJQv+z5fT1EQkSVBDO5Th2q7Nls5idMgtR2BPXxVhs+e8OM4IJK
W+1V0WHWHuE+6SRKQrPU4hAmXrtmGRtu474TTmPlW6dCqFgvLWBuxeTRCPf4l29T
ImEOxdjFwlMVoxsazE3KE/YMbX7IiZqgLx/C4OTPlz7BVLhphzPRXA7KhhRFi1Df
jie5oRhJ4zbTFQI8SKsjbx0H/4VpB+Vtjx6fOxnLUpjZAE7G6ZL2zOEK8rtiGeiH
0AWK2rKWP8oOMnQwBMP838WRtxFmaOIhvVqAngjynSmVouf1RKKU7y7/YQ0iVDcN
WqAhaZhvszQ6UCDAEi11rfdC2qt29Jds9lS41YGqvYaan+b50lr5u59Uknz2LC94
HS4/gWtdVEVnXDda0wk9
=CbRB
-----END PGP SIGNATURE-----
 **************************************************************/

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
    private static boolean LOCAL = true;
    //private static boolean LOCAL = false;

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
    
    private static void deletePIDFile(){
           try{
            File pidFile = new File("C:\\Users\\waqqas\\AppData\\Roaming\\OpenTransactions\\client_data\\ot.pid");
            if(pidFile.exists()){
                System.out.println("Existing pid file deleted:"+pidFile.delete());
            }
        }catch(Exception e){}
    }
    
    static void loadNative(final typeOS os) throws UnsatisfiedLinkError {
        
        final Logger l = Logger.getLogger(Tools.class.getName());
        
        if(LOCAL)
            deletePIDFile();
        
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
