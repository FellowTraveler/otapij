/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opentransactions.otjavalib.util;

/**
 *
 * @author Cameron Garnham and FellowTraveler
 */
public class Utility {

    public static class OTBool {

        private boolean value = false;

        public OTBool(boolean value) {
            this.value = value;
        }

        public boolean getBooleanValue() {
            return value;
        }

        public void setBooleanValue(boolean value) {
            this.value = value;
        }
    }

    public static class OTInteger {

        private int value = 0;

        public OTInteger(int value) {
            this.value = value;
        }

        public int getIntegerValue() {
            return value;
        }

        public void setIntegerValue(int value) {
            this.value = value;
        }
    }

    public static boolean VerifyStringVal(String strValue) {
        if ((null == strValue) || strValue.isEmpty()) {
            return false;
        }
        return true;
    }

    
    // a little handy java interface used to make call backs
    // (since java dosn't have delegates).
    
    public interface ReturnAction {

        public String getAction();

        public void returnAction(String returnValue);
    }
}
