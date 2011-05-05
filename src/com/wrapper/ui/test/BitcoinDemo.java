/*
*To change this template, choose Tools | Templates
*and open the template in the editor.
 */

package com.wrapper.ui.test;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 *
 * @author waqqas
 */
public class BitcoinDemo {
   private static HttpSession session;

public static void main(String[] a) throws URISyntaxException, JSONException{
    BitcoinClient bcc= new BitcoinClient("127.0.0.1", "testuser", "testpassword");
System.out.println("bb:tt "+bcc.getBalance());
//bcc.listTransactions();
//
System.out.println("TTT:"+bcc.getAccount("1Hi69mrM3iYr8kktFzjz67X22Njp9Rreuq"));
//bcc.move("Label Testing", "", 25.04, 1, "Since account was created");
//bcc.setAccountForAddress("1Hi69mrM3iYr8kktFzjz67X22Njp9Rreuq", "")

}
}
