<img align="center" src="http://ft.vm.to/blogimages/moneychanger-credits.jpg" alt="Jesus and the money-lenders" />

Moneychanger -- A wallet built on Open Transactions
====================================

## DESCRIPTION

Moneychanger is a simple Java client GUI built on top of [Open Transactions](https://github.com/FellowTraveler/Open-Transactions/wiki).

Moneychanger can be used to create pseudonyms, issue currencies, open accounts, withdraw cash, deposit cash, write and deposit cheques, and purchase vouchers ("cashier's cheques" aka "banker's cheques"). New: Markets and now Basket currencies are functional!

Bitcoin is now supported (via bitcoind). Coming soon: safe Bitcoin storage in OT (through voting pools.) 

[Open-Transactions wiki](https://github.com/FellowTraveler/Open-Transactions/wiki)

Video Walkthru:
[Part 1](http://vimeo.com/28141679)
[Part 2](http://vimeo.com/28142096)

[New Videos](http://open-transactions-tv.github.com/)

Radio interview about Open Transactions and Moneychanger:
[Part 1](http://agoristradio.com/?p=234)
[Part 2](http://agoristradio.com/?p=246)

THIS IS ONLY EXPERIMENTAL SOFTWARE AND IS NOT YET READY FOR PRIME TIME.

## INSTALLATION - (Bounty to creat MoneyChanger and OT .deb - https://propster.me/tipjar/0cfdnyi )

First, install Open-Transactions and its dependencies:
[Open-Transactions Install Instructions.](https://github.com/FellowTraveler/Open-Transactions/wiki/Install) Also: [Troubleshooting](https://github.com/FellowTraveler/Moneychanger/wiki/Troubleshooting)


Next, download the Moneychanger source code:

     $ cd ..
     $ git clone git://github.com/FellowTraveler/Moneychanger.git

[Pre-Built Jarfiles for Moneychanger are available here.](https://github.com/FellowTraveler/Moneychanger/downloads)

## TO RUN OPEN TRANSACTIONS SERVER

     $ otserver

## TO RUN MONEYCHANGER 

From a separate terminal:
 
     $ java -jar Moneychanger.jar

(Bounty to creat MoneyChanger and OT .deb - https://propster.me/tipjar/0cfdnyi )

--------------------------------------------------------

## CREATE PSEUDONYMS
<img src="http://ft.vm.to/blogimages/ot-nyms.png" alt="Pseudonyms aka Nyms screenshot" />

## ISSUE CURRENCIES
<img src="http://ft.vm.to/blogimages/ot-contracts.png" alt="Contracts screenshot" />

## OPEN ACCOUNTS
<img src="http://ft.vm.to/blogimages/ot-main.png" alt="Main Account screenshot" />

## GO CASH-ONLY
<img src="http://ft.vm.to/blogimages/ot-cash.gif" alt="Cash Purse screenshot" />

<img src="http://ft.vm.to/blogimages/ot-cash2.gif" alt="Export Tokens from Purse screenshot" />

## TRADE ON MARKETS
<img src="http://ft.vm.to/blogimages/ot-markets.png" alt="Markets screenshot" />

## BITCOIN INTEGRATION
<img src="http://ft.vm.to/blogimages/workingBTC.gif" alt="OT Bitcoin screenshot" />

(Of course, this is only a TEST GUI -- a real one would be a lot prettier.)

```xml

(See VERSION.txt for version information.) 

/************************************************************
-----BEGIN PGP SIGNED MESSAGE-----
Hash: SHA256

 *                 M O N E Y C H A N G E R
 *
 *  Open Transactions:
 *       Financial Cryptography and Digital Cash
 *       Library, Protocol, API, Server, and GUI 
 *    
 *       -- Anonymous Numbered Accounts.
 *       -- Untraceable Digital Cash.
 *       -- Triple-Signed Receipts.
 *       -- Cheques, Vouchers, Transfers, Inboxes.
 *       -- Basket Currencies, Markets, Payment Plans.
 *       -- Signed, XML, Ricardian-style Contracts.
 *    
 *  Copyright (C) 2010-2012 by "Fellow Traveler" (A pseudonym)
 *
 *  EMAIL:
 *  FellowTraveler@rayservers.net
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
Version: GnuPG v1.4.11 (Darwin)

iQIcBAEBCAAGBQJOj7gfAAoJEAMIAO35UbuOs3sP/2rrjSdYu/AsXcgLK9/9CP4a
lIJfw3KLvybKZjZW5r5j+4xUlCYIqPZSI66PGDChGPMPFcZQN6M4Ddpn9kbctymS
sdTXvbdFhpbV6k2wSa1Fz97ygfXJc/7MDTmHYbZ53hVV8AoUBrCHWtVttkQD31o3
Pn/qGmy+jOgTvjEXhjEpV66pDkMWze1SiI1MArHUziCYoxItuM45x0EfzwQIqlo3
ku2R7rRTtqm47Dgea12psWrjbPS5XRL1Q8Hs38Z1J0JdFlfn6cJYe52Iiluzof6M
kCLhy6FH8QfIADfrKkFP48EIhnVquDlkV9AlJ1r217K3cpK2jEjlZUnGBECMAMEo
pSXXk1BLNgxsa4yaXCgHY92/MhgtcdCMLkcCq6MWUTGZsLGiWIiQGmO9mwBfNIlY
SawlIviuS5DiE/D16A290Byxhha/5e144cIiKm27fSQra8eogUXNfZdZeuv6n69v
t8QjeBjoLhe5/KnRNoGLpSXhPphsWLRSJBru77ZU2msHfmkNfcP2UoEUCfNTfTbE
XpyRfeyRVowVKeKunV9KUSHgdD5wa6RUeyodAbaHvWrFpIpNkaFIP9OwhRULpjx0
arwVNYucbX1qb2I8HBm2u+IRWQTONp74TFFjU0/CVAXu2DeJKY5mL4zDej35c5j9
AK+ZirdWhhoHeWR1tAkN
=RcXP
-----END PGP SIGNATURE-----
 **************************************************************/

```


 
