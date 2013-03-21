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
*To change this template, choose Tools | Templates
*and open the template in the editor.
 */

package com.moneychanger.ui.test;

/*
 * Christopher Deckers (chrriis@nextencia.net)
 * http://www.nextencia.net
 *
 * See the file "readme.txt" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */


import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import chrriis.common.UIUtils;
import chrriis.common.Utils;
import chrriis.common.WebServer;
import chrriis.common.WebServer.HTTPRequest;
import chrriis.common.WebServer.WebServerContent;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationParameters;

/**
 * @author Christopher Deckers
 */
public class NavigationParameters extends JPanel {

  public NavigationParameters() {
    super(new BorderLayout());
    JPanel buttonPanel = new JPanel(new GridBagLayout());
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    buttonPanel.add(new JLabel("Custom Header:"), new GridBagConstraints(0, 0, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    final JTextField testHeaderKeyTextField = new JTextField("Custom-header", 10);
    buttonPanel.add(testHeaderKeyTextField, new GridBagConstraints(1, 0, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 0), 0, 0));
    final JTextField testHeaderValueTextField = new JTextField("My value", 10);
    buttonPanel.add(testHeaderValueTextField, new GridBagConstraints(2, 0, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 0), 0, 0));
    JButton testHeaderButton = new JButton("Test");
    buttonPanel.add(testHeaderButton, new GridBagConstraints(3, 0, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 0), 0, 0));
    buttonPanel.add(new JLabel("BugZilla Search:"), new GridBagConstraints(0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    final JTextField testPostDataTextField = new JTextField("Browser", 20);
    buttonPanel.add(testPostDataTextField, new GridBagConstraints(1, 1, 2, 1, 0, GridBagConstraints.WEST, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 0), 0, 0));
    JButton testPostDataButton = new JButton("Go!");
    buttonPanel.add(testPostDataButton, new GridBagConstraints(3, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 0), 0, 0));
    add(buttonPanel, BorderLayout.NORTH);
    final JPanel webBrowserPanel = new JPanel(new BorderLayout());
    webBrowserPanel.setBorder(BorderFactory.createTitledBorder("Native Web Browser component"));
    add(webBrowserPanel, BorderLayout.CENTER);
    // Add the listeners
    testHeaderButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        webBrowserPanel.removeAll();
        JWebBrowser webBrowser = new JWebBrowser();
        webBrowser.setBarsVisible(false);
        WebBrowserNavigationParameters parameters = new WebBrowserNavigationParameters();
        Map<String, String> headersMap = new HashMap<String, String>();
        headersMap.put("User-agent", "Native Swing Browser");
        headersMap.put(testHeaderKeyTextField.getText(), testHeaderValueTextField.getText());
        parameters.setHeaders(headersMap);
        // Let's generate the page with the resulting HTTP headers dynamically.
        webBrowser.navigate(WebServer.getDefaultWebServer().getDynamicContentURL(NavigationParameters.this.getClass().getName(), "header-viewer.html"), parameters);
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        webBrowserPanel.revalidate();
        webBrowserPanel.repaint();
      }
    });
    testPostDataButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        webBrowserPanel.removeAll();
        JWebBrowser webBrowser = new JWebBrowser();
        webBrowser.setBarsVisible(false);
        WebBrowserNavigationParameters parameters = new WebBrowserNavigationParameters();
        Map<String, String> postDataMap = new HashMap<String, String>();
        postDataMap.put("short_desc_type", "allwordssubstr");
        postDataMap.put("short_desc", testPostDataTextField.getText());
        postDataMap.put("bug_status", "NEW");
        postDataMap.put("product", "Platform");
        postDataMap.put("component", "SWT");
        parameters.setPostData(postDataMap);
        webBrowser.navigate("https://bugs.eclipse.org/bugs/buglist.cgi", parameters);
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        webBrowserPanel.revalidate();
        webBrowserPanel.repaint();
      }
    });
  }

  protected static WebServerContent getWebServerContent(final HTTPRequest httpRequest) {
    // We dynamically generate the page using the embedded web server.
    if("header-viewer.html".equals(httpRequest.getResourcePath())) {
      return new WebServerContent() {
        @Override
        public InputStream getInputStream() {
          StringBuilder sb = new StringBuilder();
          sb.append("<html><body><h1>HTTP Headers</h1><table border=\"1\">");
          Map<String, String> headerMap = httpRequest.getHeaderMap();
          String[] keys = headerMap.keySet().toArray(new String[0]);
          Arrays.sort(keys, String.CASE_INSENSITIVE_ORDER);
          for(String key: keys) {
            sb.append("<tr><td>");
            sb.append(Utils.escapeXML(key));
            sb.append("</td><td>");
            sb.append(Utils.escapeXML(headerMap.get(key)));
            sb.append("</td></tr>");
          }
          sb.append("</table></body></html>");
          return getInputStream(sb.toString());
        }
      };
    }
    return null;
  }

  /* Standard main method to try that test as a standalone application. */
  public static void main(String[] args) {
    UIUtils.setPreferredLookAndFeel();
    NativeInterface.open();
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame frame = new JFrame("DJ Native Swing Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new NavigationParameters(), BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
      }
    });
    NativeInterface.runEventPump();
  }

}
