/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrapper.ui;

/**
 *
 * @author cameron
 */
public class LoadExceptions {

    class NoApiException extends Exception {

        String locationsChecked;

        public NoApiException() {
            super();             // call superclass constructor
            locationsChecked = "none";
        }

        public NoApiException(String err) {
            super(err);     // call super class constructor
            locationsChecked = err;  // save message
        }

        public String getError() {
            return locationsChecked;
        }
    }

    class NoAppDataException extends Exception {

        String locationsChecked;

        public NoAppDataException() {
            super();             // call superclass constructor
            locationsChecked = "none";
        }

        public NoAppDataException(String err) {
            super(err);     // call super class constructor
            locationsChecked = err;  // save message
        }

        public String getError() {
            return locationsChecked;
        }
    }
}
