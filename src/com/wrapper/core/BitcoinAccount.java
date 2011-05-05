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
package com.wrapper.core;

import com.wrapper.core.dataobjects.BitcoinDetails;
import com.wrapper.core.helper.bitcoin.BitcoinClient;
import com.wrapper.core.helper.bitcoin.TransactionInfo;
import com.wrapper.core.util.Configuration;
import com.wrapper.core.util.Utility;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.paradoxs.bitcoin.client.AccountInfo;
import ru.paradoxs.bitcoin.client.AddressInfo;
import ru.paradoxs.bitcoin.client.ValidatedAddressInfo;
import ru.paradoxs.bitcoin.client.exceptions.BitcoinClientException;

public class BitcoinAccount extends Account {

    BitcoinClient btcClient;

    public BitcoinAccount() {
        if (btcClient == null) {
            btcClient = new BitcoinClient(Configuration.getHostBitcoin(), Configuration.getUserBitcoin(), Configuration.getPwdBitcoin(), Configuration.getPortBitcoin());
        }
        type = "BitcoinAccount";
    }

    @Override
    public boolean createAccount() {
        return false;
    }

    @Override
    public boolean deleteAccount(String accountID) {
        return false;
    }

    @Override
    public void editAccount() {
    }

    public void getBitcoinAccountList() {
        System.out.println("getBitcoinAccountList strts ");
        try {
            key = "default";
            label = "Bitcoin Account";
            amount = String.valueOf(btcClient.getBalance());
            accountList.put(key, new String[]{label, amount, type, key});
            System.out.println("getBitcoinAccountList ends ");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadAccount(String assetID, String serverID, String nymID) {
        //accountList.put("Bitcoin1", new String[] {"bitcoinlabel1","2000","BitcoinAccount","Bitcoin1"});
        System.out.println("In Bit load");
        getBitcoinAccountList();
    }

    @Override
    public Object getAccountDetails(String accountID) {
        System.out.println(" getAccountDetails - Starts ");
        BitcoinDetails bitcoinDetails = new BitcoinDetails();
       System.out.println(" getAccountDetails - 1");
        bitcoinDetails.setAccount("");
        System.out.println(" getAccountDetails - 2");
        //btcClient.getServerInfo();
        System.out.println(" getServerInfo - 22");
        bitcoinDetails.setAddress(btcClient.getAccountAddress(""));
        System.out.println(" getAccountDetails - 3");
        bitcoinDetails.setName(btcClient.getLabel(bitcoinDetails.getAddress()));
        System.out.println(" getAccountDetails - 4");
        bitcoinDetails.setBalance(String.valueOf(btcClient.getBalance()));
        System.out.println(" getAccountDetails - 5");
        bitcoinDetails.setCurrentBlockNumber(btcClient.getBlockNumber());
        System.out.println(" getAccountDetails - 6");
        bitcoinDetails.setSentboxData(getSentBoxData(""));
        bitcoinDetails.setReceivedboxData(getReceivedBoxData(""));
        bitcoinDetails.setReceivedAddrboxData(getRecevingAddrBoxData(""));
        System.out.println("Block Count:" + btcClient.getBlockCount() + " btcClient.getBlockNumber():" + btcClient.getBlockNumber());
        return bitcoinDetails;
    }

    @Override
    public boolean editLabel(String accountID, String newLabel) {
        return false;
    }

    public double checkBalance(String account) {
        System.out.println("bal:" + btcClient.getBalance());
        return btcClient.getBalance();
    }

    public String createNewAddress(String label, String account) {
        String newAddress = btcClient.getNewAddress(label);
        System.out.println("newAddress:" + newAddress);
        if (newAddress != null) {
            btcClient.setAccountForAddress(newAddress, account);
        }
        return newAddress;

    }

    public void setLabelForAddress(String address, String newLabel) {
        btcClient.setLabelForAddress(address, newLabel);
    }

    public String sendFunds(String toAddress, double amount, String comment, String commentTo) {
        try {
            String result = btcClient.sendToAddress(toAddress, amount, comment, commentTo);
            System.out.println("result:" + result);

        } catch (BitcoinClientException btcException) {
            return btcException.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
        return "Success";
    }

    public boolean validateAddress(String address) {
        ValidatedAddressInfo addressInfo = btcClient.validateAddress(address);
        return addressInfo.getIsValid();
    }

    public boolean isMineAddress(String address) {
        ValidatedAddressInfo addressInfo = btcClient.validateAddress(address);
        return addressInfo.getIsMine();
    }

    public String getBlockNumber() {
        return String.valueOf(btcClient.getBlockNumber());
    }

    public Map getRecevingAddrBoxData(String account) {

        HashMap data = new HashMap();

        List listOfAddress = btcClient.listReceivedByAddress(0, true);
        
        for (int i = 0; i < listOfAddress.size(); i++) {
            AddressInfo addressInfo = (AddressInfo) listOfAddress.get(i);
            boolean addAddress = true;
            double amount = 0.0;
            List list = btcClient.listTransactions(addressInfo.getAccount());
            for(int j=0;j<list.size();j++){
                TransactionInfo info = (TransactionInfo) list.get(j);
                
                if(addressInfo.getAddress()!=null && addressInfo.getAddress().equals(info.getAddress()) && "send".equalsIgnoreCase(info.getCategory())){
                        addAddress = false;
                        break;
                }else if(addressInfo.getAddress()!=null && addressInfo.getAddress().equals(info.getAddress()) && "receive".equalsIgnoreCase(info.getCategory())){
                    amount += info.getAmount();
                }
            }
            if(addAddress){

                String[] row = new String[3];

                row[0] = btcClient.getLabel(addressInfo.getAddress());
                row[1] = addressInfo.getAddress();
                row[2] = String.valueOf(Utility.roundTwoDecimals(amount));

                data.put(row[1], row);
            }
     
        }
        return data;
    }


    public Map getSentBoxData(String account) {
        HashMap data = new HashMap();

        List listOfTransactions = btcClient.listTransactions();
        System.out.println(" listOfTransactions.size(): " + listOfTransactions.size());
        for (int i = 0; i < listOfTransactions.size(); i++) {
            TransactionInfo trnInfo = (TransactionInfo) listOfTransactions.get(i);
            /*System.out.println(" ------------------------------------ " + trnInfo);
            System.out.println("confirm:" + trnInfo.getConfirmations());
            System.out.println("amout:" + trnInfo.getAmount());
            System.out.println("Address:" + trnInfo.getAddress());
            System.out.println("mess:" + trnInfo.getMessage());
            System.out.println("cat:" + trnInfo.getCategory());
            System.out.println("other acct:" + trnInfo.getOtherAccount());
            System.out.println("fees:" + trnInfo.getFee());
            System.out.println("ID:" + trnInfo.getTxId());
            System.out.println("Date:" + trnInfo.getTimestamp());
            System.out.println("Formatted Date:" + new Date(trnInfo.getTimestamp() * 1000));*/

            if ("send".equalsIgnoreCase(trnInfo.getCategory())) {
                String[] row = new String[6];
                row[0] = String.valueOf(trnInfo.getConfirmations() + " Confirmations");
                row[1] = String.valueOf(new Date(trnInfo.getTimestamp() * 1000));
                row[2] = String.valueOf(trnInfo.getAmount() * -1);
                row[3] = String.valueOf(trnInfo.getAddress());
                row[4] = trnInfo.getMessage() == null ? "" : trnInfo.getMessage();
                row[5] = String.valueOf(trnInfo.getTxId());

                data.put(row[5], row);
            }
        }
        return data;
    }

    public Map getReceivedBoxData(String account) {
        HashMap data = new HashMap();

        List accountsList = btcClient.listReceivedByAccount(0, true);
        for (int j = 0; j < accountsList.size(); j++) {
            String accounts = ((AccountInfo)accountsList.get(j)).getAccount();
            List listOfTransactions = btcClient.listTransactions(accounts);
            System.out.println(" listOfTransactions.size(): " + listOfTransactions.size());
            for (int i = 0; i < listOfTransactions.size(); i++) {
                TransactionInfo trnInfo = (TransactionInfo) listOfTransactions.get(i);
                /*System.out.println(" ------------------------------------ " + trnInfo);
                System.out.println("confirm:" + trnInfo.getConfirmations());
                System.out.println("amout:" + trnInfo.getAmount());
                System.out.println("Address:" + trnInfo.getAddress());
                System.out.println("mess:" + trnInfo.getMessage());
                System.out.println("cat:" + trnInfo.getCategory());
                System.out.println("other acct:" + trnInfo.getOtherAccount());
                System.out.println("fees:" + trnInfo.getFee());
                System.out.println("ID:" + trnInfo.getTxId());
                System.out.println("Date:" + trnInfo.getTimestamp());
                System.out.println("Formatted Date:" + new Date(trnInfo.getTimestamp() * 1000));*/

                if ("receive".equalsIgnoreCase(trnInfo.getCategory())) {
                    String[] row = new String[6];
                    row[0] = String.valueOf(trnInfo.getConfirmations() + " Confirmations");
                    row[1] = String.valueOf(new Date(trnInfo.getTimestamp() * 1000));
                    row[2] = String.valueOf(trnInfo.getAmount());
                    row[3] = String.valueOf(trnInfo.getAddress());
                    row[4] = trnInfo.getMessage() == null ? "" : trnInfo.getMessage();
                    row[5] = String.valueOf(trnInfo.getTxId());

                    data.put(row[5], row);
                }
            }
        }
        return data;
    }

    public String getLabel(String address) {
        return btcClient.getLabel(address);
    }
}
