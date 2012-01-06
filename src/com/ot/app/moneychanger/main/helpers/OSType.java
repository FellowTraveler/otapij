/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger.main.helpers;

/**
 *
 * @author cameron
 */
public class OSType {
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
}
