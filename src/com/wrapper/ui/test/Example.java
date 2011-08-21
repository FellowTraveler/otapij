/************************************************************
 -----BEGIN PGP SIGNED MESSAGE-----
 Hash: SHA256
 
 *                 M O N E Y C H A N G E R
 *
 *   http://wiki.github.com/FellowTraveler/Moneychanger/wiki
 *
 *  Open Transactions:
 *       Financial Cryptography and Digital Cash
 *       Library, Protocol, API, Server, and GUI 
 *    
 *    	 -- Anonymous Numbered Accounts.
 *    	 -- Untraceable Digital Cash.
 *    	 -- Triple-Signed Receipts.
 *    	 -- Cheques, Vouchers, Transfers, Inboxes.
 *    	 -- Basket Currencies, Markets, Payment Plans.
 *    	 -- Signed, XML, Ricardian-style Contracts.
 *    
 *  Copyright (C) 2010-2011 by "Fellow Traveler" (A pseudonym)
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
 *  http://wiki.github.com/FellowTraveler/Open-Transactions/wiki 
 *
 *  WEBSITE:
 *  http://www.OpenTransactions.org/
 *    
 *  Components and licensing:
 *   -- Moneychanger..A Java client GUI.....LICENSE:.....GPLv3
 *   -- OTLib.........A class library.......LICENSE:...LAGPLv3 
 *   -- OT-API........A client API..........LICENSE:...LAGPLv3
 *   -- testwallet....Command-line client...LICENSE:...LAGPLv3
 *   -- OT-Server.....Server Application....LICENSE:....AGPLv3
 *  Github.com/FellowTraveler/Open-Transactions/wiki/Components
 *
 *  All of the above OT components were designed and written by
 *  Fellow Traveler, with the exception of Moneychanger, which
 *  was contracted out to Vicky C (livewire_3001@yahoo.com).
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
 wsFVAwUBTbFZUwMIAO35UbuOAQjDRBAAmIUJBi5/WC1KpI4TNAWdQNh6g59qYS6w
 SI6mTMbnP0DUVOrmJdNR7/n1sRlnWzyjKLcKkRtXwRWGC+jE16jijxek9Ome5Qid
 bDqjHSuFvqnsD3+0tbENf+kVrbAReU3YvWk+xFvVc6I2NpS+lEIdjHIWm85jSmew
 Ydx+4KpELkO59thkcKgSYsTSyTP3l9GOTtJlq45XiamoEvso4jFUC1y5KMQsz1KH
 DTE32m5FPZqJqUw9loAmrni3dIMpXKC5yLhdqSMXHK0MAPEIexsuaZjrjKJQSjwV
 eDjwJcMn2WZVvcIr9IEoKEU/2j9wHNZv5Xuj78A/78AkjqEUwrY1M9ht0r/QbusW
 ZT7MlxNCq4DFstrjyKi03yZQGR+m8eJFHE7GvF8Vzg/ap0/CUJzeoXg5wACXGfJj
 k6y8ZBriQO08JECki2sy6oTitDoi7FmzgAIxPGB1qA4HMur/LuzrxAj2V7XkZQlk
 VfAda6Ff9bmStNut+zbsQ0pnIeL/URwWifI8Wq81c7DEIvA5SH/bU9Hws1FMO8PU
 BcDmzadU+syJBTxoP/mHZcLfwHDhcZyBeHX7sHfpHweEunzWjcHjqVCutQMO4dii
 yrsc64WTfAqd4s12SfKMgVFLeL/FUYH7MNqpfgjgwX5co817m9VvCntU6njIuYtV
 6+G/TuSViH8=
 =/jIC
 -----END PGP SIGNATURE-----
 **************************************************************/


/*
*To change this template, choose Tools | Templates
*and open the template in the editor.
 */

package com.wrapper.ui.test;

import java.awt.*;
 	import java.awt.event.*;
 	import java.applet.*;
 	import java.awt.datatransfer.*;


 	public class Example extends Applet implements ClipboardOwner {

 	  private TextField sourceText = new TextField(40);
 	  private TextField destinationText = new TextField(40);
 	  private Button cmdCopy = new Button();
 	  private Button cmdPaste = new Button();
 	  private Label labelSource = new Label();
 	  private Label labelDestination = new Label();
 	  private Clipboard clipboard;


 	  public Example() {
 	  }


 	  public void init() {

 	    this.setBackground (Color.lightGray);
 	    clipboard = getToolkit ().getSystemClipboard ();
 	    cmdCopy.setLabel("Copy source to clipboard");
 	    cmdPaste.setLabel ("Paste to the destination field");
 	    labelSource.setAlignment(Label.RIGHT);
 	    labelSource.setText("Source text");
 	    labelDestination.setAlignment(Label.RIGHT);
 	    labelDestination.setText("Copy to the clipboard");
 	    this.add(labelSource);
 	    this.add(sourceText);
 	    this.add(labelDestination);
 	    this.add(destinationText);
 	    this.add(cmdCopy);
 	    this.add (cmdPaste);

 	    cmdCopy.addActionListener (new cmdCopyActionListener ());
 	    cmdPaste.addActionListener (new cmdPasteActionListener ());

 	  }

 		public void lostOwnership (Clipboard parClipboard, Transferable parTransferable) {
 	  	System.out.println ("Lost ownership");
 	  }

 		class cmdCopyActionListener implements ActionListener {

 			public void actionPerformed (ActionEvent event) {
 	  		StringSelection fieldContent = new StringSelection (sourceText.getText ());
 	     	clipboard.setContents (fieldContent, Example.this);
 	  	}
 		}

 		class cmdPasteActionListener implements ActionListener {

 			public void actionPerformed (ActionEvent event) {

   		Transferable clipboardContent = clipboard.getContents (this);

 				if ((clipboardContent != null) &&
 	        	(clipboardContent.isDataFlavorSupported (DataFlavor.stringFlavor))) {
 					try {
 	           	String tempString;
 						tempString = (String) clipboardContent.getTransferData
 	              													(DataFlavor.stringFlavor);
 	              destinationText.setText (tempString);
 	           }
 	           catch (Exception e) {
 	           	e.printStackTrace ();
 	           }
 	        }
 			}
 		}
 }