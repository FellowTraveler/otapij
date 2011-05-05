/*
*To change this template, choose Tools | Templates
*and open the template in the editor.
 */

package com.wrapper.ui.test;

import com.wrapper.ui.dialogs.OTPasswordDialog;

/**
 *
 * @author waqqas
 */
public class Testing {
public static void main(String a[]){

       // Get this string from a modal.
       String strPassword = null;
       new OTPasswordDialog(null, true).setVisible(true);
       strPassword = OTPasswordDialog.getPassword();
       System.out.println("strPassword:"+strPassword);
}
}
