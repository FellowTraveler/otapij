<img align="center" src="http://ft.vm.to/blogimages/moneychanger-credits.jpg" alt="Jesus and the money-lenders" />

Moneychanger -- A wallet built on Open Transactions
====================================

## DESCRIPTION

Moneychanger is a simple Java client GUI built on top of [Open Transactions](https://github.com/FellowTraveler/Open-Transactions/wiki).

Moneychanger can be used to create pseudonyms, issue currencies, open accounts, withdraw cash, deposit cash, write and deposit cheques, and purchase vouchers ("cashier's cheques" aka "banker's cheques").

Soon, Moneychanger will also be able to use basket currencies, set up payment plans, and trade on markets. (The Open Transactions library already supports these features.)

Bitcoin integration is also coming soon.

See wikis for more screenshots and diagrams.

[Moneychanger wiki](https://github.com/FellowTraveler/Moneychanger/wiki)

[Open-Transactions wiki](https://github.com/FellowTraveler/Open-Transactions/wiki)

Radio interview about Open Transactions, Moneychanger (and Bitcoin):
[Part 1](http://agoristradio.com/?p=234)
[Part 2](http://agoristradio.com/?p=246)

THIS IS ONLY EXPERIMENTAL SOFTWARE AND IS NOT YET READY FOR PRIME TIME.

## INSTALLATION

[(Open-Transactions Install Instructions)](https://github.com/FellowTraveler/Open-Transactions/wiki/Install)

[Troubleshooting](https://github.com/FellowTraveler/Moneychanger/wiki/Troubleshooting)

First, [download Open-Transactions](https://github.com/FellowTraveler/Open-Transactions/):

     $ git clone git://github.com/FellowTraveler/Open-Transactions.git
     $ cd Open-Transactions

[Get OpenSSL 1.0.0](https://github.com/FellowTraveler/Open-Transactions/wiki/Install-OpenSSL) if you don't have it already. You don't have to install it system-wide, but you still need to download it and build it. (If you are using 64-bit Linux, MAKE SURE you configure OpenSSL explicitly for this before building, as it will not build that way by default.)

(Probably already done for you) Do a search-and-replace on one file:
* Open this file: Open-Transactions/testwallet/OTAPI_java.c
* Search and replace. Search for this string: Java_otapiJNI
* Replace (entire file): Java_com_wrapper_core_jni_otapiJNI

Build Open-Transactions for the Java API:

     $ make java

If you have a special, local version of OpenSSL 1.0.0 just for OT, then name its folder "openssl" (all lowercase) and place it inside the Open-Transactions folder. (As in, "Open-Transactions/openssl" is where it will be.) Then:

     $ make DSP=1 java

The DSP option causes Open Transactions to look for an 'openssl' folder inside the Open-Transactions main folder.

Download Moneychanger source code:

     $ cd ..
     $ git clone git://github.com/FellowTraveler/Moneychanger.git

The jar, lib, and src are posted at:

     http://ft.vm.to/files/moneychanger/src.zip
     http://ft.vm.to/files/moneychanger/lib.zip
     http://ft.vm.to/files/moneychanger/JavaWrapper.jar

To download those versions, you can do this:

     $ mkdir Moneychanger (ONLY DO THIS LINE IF YOU DIDN'T GIT.)
     $ cd Moneychanger 
     $ wget http://ft.vm.to/files/moneychanger/lib.zip
     $ wget http://ft.vm.to/files/moneychanger/JavaWrapper.jar

If wget fails with "Command not found", try this:

     $ curl http://ft.vm.to/files/moneychanger/lib.zip >lib.zip
     $ curl http://ft.vm.to/files/moneychanger/JavaWrapper.jar >JavaWrapper.jar

From within the Moneychanger folder, unzip the lib.zip, then copy it and JavaWrapper.jar to the Open-Transactions/testwallet directory:

     $ unzip lib.zip
     $ cp -r lib ../Open-Transactions/testwallet
     $ cp JavaWrapper.jar Open-Transactions/testwallet

## TO RUN OPEN TRANSACTIONS SERVER

     $ cd Open-Transactions/transaction
     $ ./transaction.exe test <full path>/Open-Transactions/transaction/data_folder

## TO RUN MONEYCHANGER 

From a separate terminal:
 
     $ cd Open-Transactions/testwallet
     $ java -jar JavaWrapper.jar

--------------------------------------------------------

<img src="http://ft.vm.to/blogimages/ot-cash.gif" alt="Cash Purse screenshot" />

<img src="http://ft.vm.to/blogimages/ot-cash2.gif" alt="Export Tokens from Purse screenshot" />


```xml

Moneychanger v0.02 (running pretty smooth...)

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

```


 
