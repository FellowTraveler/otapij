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


package com.wrapper.core.jni;

class otapiJNI {
  public final static native int OT_API_Init(String jarg1);
  public final static native String OT_API_Wallet_ImportNym(String jarg1, String jarg2);
  public final static native int OT_API_LoadWallet(String jarg1);
  public final static native int OT_API_SwitchWallet(String jarg1, String jarg2);
  public final static native int OT_API_GetMemlogSize();
  public final static native String OT_API_GetMemlogAtIndex(int jarg1);
  public final static native String OT_API_PeekMemlogFront();
  public final static native String OT_API_PeekMemlogBack();
  public final static native int OT_API_PopMemlogFront();
  public final static native int OT_API_PopMemlogBack();
  public final static native String OT_API_CreateNym();
  public final static native int OT_API_AddServerContract(String jarg1);
  public final static native int OT_API_AddAssetContract(String jarg1);
  public final static native int OT_API_GetServerCount();
  public final static native int OT_API_GetAssetTypeCount();
  public final static native int OT_API_GetAccountCount();
  public final static native int OT_API_GetNymCount();
  public final static native String OT_API_GetServer_ID(int jarg1);
  public final static native String OT_API_GetServer_Name(String jarg1);
  public final static native String OT_API_GetAssetType_ID(int jarg1);
  public final static native String OT_API_GetAssetType_Name(String jarg1);
  public final static native String OT_API_GetAccountWallet_ID(int jarg1);
  public final static native String OT_API_GetAccountWallet_Name(String jarg1);
  public final static native String OT_API_GetAccountWallet_Balance(String jarg1);
  public final static native String OT_API_GetAccountWallet_Type(String jarg1);
  public final static native String OT_API_GetAccountWallet_AssetTypeID(String jarg1);
  public final static native String OT_API_GetAccountWallet_ServerID(String jarg1);
  public final static native String OT_API_GetAccountWallet_NymID(String jarg1);
  public final static native int OT_API_VerifyAccountReceipt(String jarg1, String jarg2, String jarg3);
  public final static native int OT_API_GetNym_TransactionNumCount(String jarg1, String jarg2);
  public final static native String OT_API_GetNym_ID(int jarg1);
  public final static native String OT_API_GetNym_Name(String jarg1);
  public final static native String OT_API_GetNym_Stats(String jarg1);
  public final static native int OT_API_IsNym_RegisteredAtServer(String jarg1, String jarg2);
  public final static native int OT_API_GetNym_MailCount(String jarg1);
  public final static native String OT_API_GetNym_MailContentsByIndex(String jarg1, int jarg2);
  public final static native String OT_API_GetNym_MailSenderIDByIndex(String jarg1, int jarg2);
  public final static native String OT_API_GetNym_MailServerIDByIndex(String jarg1, int jarg2);
  public final static native int OT_API_Nym_RemoveMailByIndex(String jarg1, int jarg2);
  public final static native int OT_API_Nym_VerifyMailByIndex(String jarg1, int jarg2);
  public final static native int OT_API_GetNym_OutmailCount(String jarg1);
  public final static native String OT_API_GetNym_OutmailContentsByIndex(String jarg1, int jarg2);
  public final static native String OT_API_GetNym_OutmailRecipientIDByIndex(String jarg1, int jarg2);
  public final static native String OT_API_GetNym_OutmailServerIDByIndex(String jarg1, int jarg2);
  public final static native int OT_API_Nym_RemoveOutmailByIndex(String jarg1, int jarg2);
  public final static native int OT_API_Nym_VerifyOutmailByIndex(String jarg1, int jarg2);
  public final static native int OT_API_Wallet_CanRemoveServer(String jarg1);
  public final static native int OT_API_Wallet_RemoveServer(String jarg1);
  public final static native int OT_API_Wallet_CanRemoveAssetType(String jarg1);
  public final static native int OT_API_Wallet_RemoveAssetType(String jarg1);
  public final static native int OT_API_Wallet_CanRemoveNym(String jarg1);
  public final static native int OT_API_Wallet_RemoveNym(String jarg1);
  public final static native int OT_API_Wallet_CanRemoveAccount(String jarg1);
  public final static native int OT_API_Wallet_RemoveAccount(String jarg1);
  public final static native int OT_API_SetNym_Name(String jarg1, String jarg2, String jarg3);
  public final static native int OT_API_SetAccountWallet_Name(String jarg1, String jarg2, String jarg3);
  public final static native int OT_API_SetAssetType_Name(String jarg1, String jarg2);
  public final static native int OT_API_SetServer_Name(String jarg1, String jarg2);
  public final static native String OT_API_VerifyAndRetrieveXMLContents(String jarg1, String jarg2);
  public final static native String OT_API_WriteCheque(String jarg1, String jarg2, String jarg3, String jarg4, String jarg5, String jarg6, String jarg7, String jarg8);
  public final static native String OT_API_WritePaymentPlan(String jarg1, String jarg2, String jarg3, String jarg4, String jarg5, String jarg6, String jarg7, String jarg8, String jarg9, String jarg10, String jarg11, String jarg12, String jarg13, String jarg14, String jarg15);
  public final static native String OT_API_LoadUserPubkey(String jarg1);
  public final static native String OT_API_LoadPubkey(String jarg1);
  public final static native int OT_API_VerifyUserPrivateKey(String jarg1);
  public final static native String OT_API_LoadPurse(String jarg1, String jarg2, String jarg3);
  public final static native String OT_API_LoadMint(String jarg1, String jarg2);
  public final static native String OT_API_LoadAssetContract(String jarg1);
  public final static native String OT_API_LoadServerContract(String jarg1);
  public final static native int OT_API_IsBasketCurrency(String jarg1);
  public final static native int OT_API_Basket_GetMemberCount(String jarg1);
  public final static native String OT_API_Basket_GetMemberType(String jarg1, int jarg2);
  public final static native String OT_API_Basket_GetMinimumTransferAmount(String jarg1);
  public final static native String OT_API_Basket_GetMemberMinimumTransferAmount(String jarg1, int jarg2);
  public final static native String OT_API_LoadAssetAccount(String jarg1, String jarg2, String jarg3);
  public final static native String OT_API_LoadInbox(String jarg1, String jarg2, String jarg3);
  public final static native String OT_API_LoadOutbox(String jarg1, String jarg2, String jarg3);
  public final static native int OT_API_Ledger_GetCount(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native String OT_API_Ledger_CreateResponse(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native String OT_API_Ledger_GetTransactionByIndex(String jarg1, String jarg2, String jarg3, String jarg4, int jarg5);
  public final static native String OT_API_Ledger_GetTransactionByID(String jarg1, String jarg2, String jarg3, String jarg4, String jarg5);
  public final static native String OT_API_Ledger_GetTransactionIDByIndex(String jarg1, String jarg2, String jarg3, String jarg4, int jarg5);
  public final static native String OT_API_Ledger_AddTransaction(String jarg1, String jarg2, String jarg3, String jarg4, String jarg5);
  public final static native String OT_API_Transaction_CreateResponse(String jarg1, String jarg2, String jarg3, String jarg4, String jarg5, int jarg6);
  public final static native String OT_API_Ledger_FinalizeResponse(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native String OT_API_Transaction_GetType(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native String OT_API_Transaction_GetVoucher(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native int OT_API_Transaction_GetSuccess(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native String OT_API_Transaction_GetDateSigned(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native String OT_API_Transaction_GetAmount(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native String OT_API_Pending_GetNote(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native String OT_API_Transaction_GetSenderUserID(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native String OT_API_Transaction_GetSenderAcctID(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native String OT_API_Transaction_GetRecipientUserID(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native String OT_API_Transaction_GetRecipientAcctID(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native String OT_API_Transaction_GetDisplayReferenceToNum(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native String OT_API_CreatePurse(String jarg1, String jarg2, String jarg3);
  public final static native int OT_API_SavePurse(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native String OT_API_Purse_GetTotalValue(String jarg1, String jarg2, String jarg3);
  public final static native int OT_API_Purse_Count(String jarg1, String jarg2, String jarg3);
  public final static native String OT_API_Purse_Peek(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native String OT_API_Purse_Pop(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native String OT_API_Purse_Push(String jarg1, String jarg2, String jarg3, String jarg4, String jarg5);
  public final static native int OT_API_Wallet_ImportPurse(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native void OT_API_exchangePurse(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native String OT_API_Token_ChangeOwner(String jarg1, String jarg2, String jarg3, String jarg4, String jarg5);
  public final static native String OT_API_Token_GetID(String jarg1, String jarg2, String jarg3);
  public final static native String OT_API_Token_GetDenomination(String jarg1, String jarg2, String jarg3);
  public final static native int OT_API_Token_GetSeries(String jarg1, String jarg2, String jarg3);
  public final static native String OT_API_Token_GetValidFrom(String jarg1, String jarg2, String jarg3);
  public final static native String OT_API_Token_GetValidTo(String jarg1, String jarg2, String jarg3);
  public final static native String OT_API_Token_GetAssetID(String jarg1);
  public final static native String OT_API_Token_GetServerID(String jarg1);
  public final static native void OT_API_checkServerID(String jarg1, String jarg2);
  public final static native void OT_API_createUserAccount(String jarg1, String jarg2);
  public final static native void OT_API_checkUser(String jarg1, String jarg2, String jarg3);
  public final static native void OT_API_sendUserMessage(String jarg1, String jarg2, String jarg3, String jarg4, String jarg5);
  public final static native void OT_API_getRequest(String jarg1, String jarg2);
  public final static native void OT_API_getTransactionNumber(String jarg1, String jarg2);
  public final static native void OT_API_issueAssetType(String jarg1, String jarg2, String jarg3);
  public final static native void OT_API_getContract(String jarg1, String jarg2, String jarg3);
  public final static native void OT_API_getMint(String jarg1, String jarg2, String jarg3);
  public final static native void OT_API_createAssetAccount(String jarg1, String jarg2, String jarg3);
  public final static native void OT_API_getAccount(String jarg1, String jarg2, String jarg3);
  public final static native String OT_API_GenerateBasketCreation(String jarg1, String jarg2);
  public final static native String OT_API_AddBasketCreationItem(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native void OT_API_issueBasket(String jarg1, String jarg2, String jarg3);
  public final static native String OT_API_GenerateBasketExchange(String jarg1, String jarg2, String jarg3, String jarg4, int jarg5);
  public final static native String OT_API_AddBasketExchangeItem(String jarg1, String jarg2, String jarg3, String jarg4, String jarg5);
  public final static native void OT_API_exchangeBasket(String jarg1, String jarg2, String jarg3, String jarg4, int jarg5);
  public final static native void OT_API_notarizeWithdrawal(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native void OT_API_notarizeDeposit(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native void OT_API_notarizeTransfer(String jarg1, String jarg2, String jarg3, String jarg4, String jarg5, String jarg6);
  public final static native void OT_API_getInbox(String jarg1, String jarg2, String jarg3);
  public final static native void OT_API_getOutbox(String jarg1, String jarg2, String jarg3);
  public final static native void OT_API_getNymbox(String jarg1, String jarg2);
  public final static native String OT_API_LoadNymbox(String jarg1, String jarg2);
  public final static native void OT_API_processInbox(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native void OT_API_processNymbox(String jarg1, String jarg2, String jarg3);
  public final static native void OT_API_withdrawVoucher(String jarg1, String jarg2, String jarg3, String jarg4, String jarg5, String jarg6);
  public final static native void OT_API_depositCheque(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native void OT_API_depositPaymentPlan(String jarg1, String jarg2, String jarg3);
  public final static native void OT_API_issueMarketOffer(String jarg1, String jarg2, String jarg3, String jarg4, String jarg5, String jarg6, String jarg7, String jarg8, String jarg9, String jarg10, int jarg11);
  public final static native String OT_API_PopMessageBuffer();
  public final static native void OT_API_FlushMessageBuffer();
  public final static native String OT_API_Message_GetCommand(String jarg1);
  public final static native int OT_API_Message_GetSuccess(String jarg1);
  public final static native int OT_API_Message_GetTransactionSuccess(String jarg1, String jarg2, String jarg3, String jarg4);
  public final static native String OT_API_Message_GetLedger(String jarg1);
  public final static native String OT_API_Message_GetNewAssetTypeID(String jarg1);
  public final static native String OT_API_Message_GetNewIssuerAcctID(String jarg1);
  public final static native String OT_API_Message_GetNewAcctID(String jarg1);
  public final static native int OT_API_ConnectServer(String jarg1, String jarg2, String jarg3, String jarg4, String jarg5);
  public final static native int OT_API_ProcessSockets();
}
