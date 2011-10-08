/************************************************************
-----BEGIN PGP SIGNED MESSAGE-----
Hash: SHA256

 *                 M O N E Y C H A N G E R
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


package com.wrapper.core.helper.bitcoin;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.paradoxs.bitcoin.client.AccountInfo;
import ru.paradoxs.bitcoin.client.AddressInfo;
import ru.paradoxs.bitcoin.client.LabelInfo;
import ru.paradoxs.bitcoin.client.ServerInfo;
import ru.paradoxs.bitcoin.client.ValidatedAddressInfo;
import ru.paradoxs.bitcoin.client.WorkInfo;
import ru.paradoxs.bitcoin.client.exceptions.BitcoinClientException;
import ru.paradoxs.bitcoin.http.HttpSession;

public class BitcoinClient
{
  private HttpSession session;

  public BitcoinClient(String host, String login, String password, int port)
  {
    this.session = null;
    try
    {
      Credentials credentials = new UsernamePasswordCredentials(login, password);
      URI uri = new URI("http", null, host, port, null, null, null);
      this.session = new HttpSession(uri, credentials);
    } catch (URISyntaxException e) {
      throw new BitcoinClientException("This host probably doesn't have correct syntax: " + host, e);
    }
  }

  public BitcoinClient(String host, String login, String password)
  {
    this(host, login, password, 8332);
  }

  @Deprecated
  public List<String> getAddressesByLabel(String label)
  {
    try
    {
      JSONArray parameters = new JSONArray().put(label);
      JSONObject request = createRequest("getaddressesbylabel", parameters);
      JSONObject response = this.session.sendAndReceive(request);
      JSONArray result = (JSONArray)response.get("result");
      int size = result.length();

      List list = new ArrayList();

      for (int i = 0; i < size; ++i) {
        list.add(result.getString(i));
      }

      return list;
    } catch (JSONException e) {
      throw new BitcoinClientException("Got incorrect JSON for this label: " + label, e);
    }
  }

  public List getAccountList(){
      List accounts = new ArrayList();
    try
    {
      JSONObject request = createRequest("listaccounts");
      JSONObject response = this.session.sendAndReceive(request);
      System.out.println("Accc:"+response.getJSONObject("result"));

      JSONObject accountObject = response.getJSONObject("result");
      Iterator keys = accountObject.keys();
      while(keys.hasNext()){
          String data[] = new String[2];
          String key = (String)keys.next();
          data[0] = key;
          data[1] = String.valueOf((Double)accountObject.get(key));
          accounts.add(data);
      }
   
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting account list", e);
    }
      return accounts;
  }


  public List<String> getAddressesByAccount(String account)
  {
    if (account == null) {
      account = "";
    }
    try
    {
      JSONArray parameters = new JSONArray().put(account);
      JSONObject request = createRequest("getaddressesbyaccount", parameters);
      JSONObject response = this.session.sendAndReceive(request);
      JSONArray result = (JSONArray)response.get("result");
      int size = result.length();

      List list = new ArrayList();

      for (int i = 0; i < size; ++i) {
        list.add(result.getString(i));
      }

      return list;
    } catch (JSONException e) {
      throw new BitcoinClientException("Got incorrect JSON for this account: " + account, e);
    }
  }

  public double getBalance()
  {
    try
    {
      JSONObject request = createRequest("getbalance");
      JSONObject response = this.session.sendAndReceive(request);

      return response.getDouble("result");
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting balance", e);
    }
  }

  public double getBalance(String account)
  {
    if (account == null) {
      account = "";
    }
    try
    {
      JSONArray parameters = new JSONArray().put(account);
      JSONObject request = createRequest("getbalance", parameters);
      JSONObject response = this.session.sendAndReceive(request);

      return response.getDouble("result");
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting balance", e);
    }
  }

  public int getBlockCount()
  {
    try
    {
      JSONObject request = createRequest("getblockcount");
      JSONObject response = this.session.sendAndReceive(request);

      return response.getInt("result");
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting block count", e);
    }
  }

  public int getBlockNumber()
  {
    try
    {
      JSONObject request = createRequest("getblocknumber");
      JSONObject response = this.session.sendAndReceive(request);

      return response.getInt("result");
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting the block number", e);
    }
  }

  public int getConnectionCount()
  {
    try
    {
      JSONObject request = createRequest("getconnectioncount");
      JSONObject response = this.session.sendAndReceive(request);

      return response.getInt("result");
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting the number of connections", e);
    }
  }

  public long getHashesPerSecond()
  {
    try
    {
      JSONObject request = createRequest("gethashespersec");
      JSONObject response = this.session.sendAndReceive(request);

      return response.getLong("result");
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting the number of calculated hashes per second", e);
    }
  }

  public double getDifficulty()
  {
    try
    {
      JSONObject request = createRequest("getdifficulty");
      JSONObject response = this.session.sendAndReceive(request);

      return response.getDouble("result");
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting the difficulty", e);
    }
  }

  public boolean getGenerate()
  {
    try
    {
      JSONObject request = createRequest("getgenerate");
      JSONObject response = this.session.sendAndReceive(request);

      return response.getBoolean("result");
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting whether the server is generating coins or not", e);
    }
  }

  public void setGenerate(boolean isGenerate, int processorsCount)
  {
    try
    {
      JSONArray parameters = new JSONArray().put(isGenerate).put(processorsCount);
      JSONObject request = createRequest("setgenerate", parameters);
      this.session.sendAndReceive(request);
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when setting whether the server is generating coins or not", e);
    }
  }

  public ServerInfo getServerInfo()
  {
    try
    {
      JSONObject request = createRequest("getinfo");
      JSONObject response = this.session.sendAndReceive(request);
      JSONObject result = (JSONObject)response.get("result");

      ServerInfo info = new ServerInfo();
      info.setBalance(result.getDouble("balance"));
      info.setBlocks(result.getLong("blocks"));
      info.setConnections(result.getInt("connections"));
      info.setDifficulty(result.getDouble("difficulty"));
      info.setHashesPerSecond(result.getLong("hashespersec"));
      info.setIsGenerateCoins(result.getBoolean("generate"));
      info.setUsedCPUs(result.getInt("genproclimit"));
      info.setVersion(result.getString("version"));

      return info;
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting the server info", e);
    }
  }

  @Deprecated
  public String getLabel(String address)
  {
    try
    {
      JSONArray parameters = new JSONArray().put(address);
      JSONObject request = createRequest("getlabel", parameters);
      JSONObject response = this.session.sendAndReceive(request);

      return response.getString("result");
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting the label associated with this address: " + address, e);
    }
  }

  public String getAccount(String address)
  {
    try
    {
      JSONArray parameters = new JSONArray().put(address);
      JSONObject request = createRequest("getaccount", parameters);
      JSONObject response = this.session.sendAndReceive(request);

      return response.getString("result");
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting the account associated with this address: " + address, e);
    }
  }

  @Deprecated
  public void setLabelForAddress(String address, String label)
  {
    try
    {
      JSONArray parameters = new JSONArray().put(address).put(label);
      JSONObject request = createRequest("setlabel", parameters);
      this.session.sendAndReceive(request);
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when setting the label associated with a given address", e);
    }
  }

  public void setAccountForAddress(String address, String account)
  {
    try
    {
      JSONArray parameters = new JSONArray().put(address).put(account);
      JSONObject request = createRequest("setaccount", parameters);
      this.session.sendAndReceive(request);
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when setting the account associated with a given address", e);
    }
  }

  @Deprecated
  public String getNewAddress(String label)
  {
    try
    {
      JSONArray parameters = new JSONArray().put(label);
      JSONObject request = createRequest("getnewaddress", parameters);
      JSONObject response = this.session.sendAndReceive(request);

      return response.getString("result");
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting the new bitcoin address for receiving payments", e);
    }
  }

  public String getAccountAddress(String account)
  {
    if (account == null) {
      account = "";
    }
    try
    {
      JSONArray parameters = new JSONArray().put(account);
      JSONObject request = createRequest("getaccountaddress", parameters);
      JSONObject response = this.session.sendAndReceive(request);

      return response.getString("result");
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting the new bitcoin address for receiving payments", e);
    }
  }

  public double getReceivedByAddress(String address, long minimumConfirmations)
  {
    try
    {
      JSONArray parameters = new JSONArray().put(address).put(minimumConfirmations);
      JSONObject request = createRequest("getreceivedbyaddress", parameters);
      JSONObject response = this.session.sendAndReceive(request);

      return response.getDouble("result");
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting the total amount received by bitcoinaddress", e);
    }
  }

  @Deprecated
  public double getReceivedByLabel(String label, long minimumConfirmations)
  {
    try
    {
      JSONArray parameters = new JSONArray().put(label).put(minimumConfirmations);
      JSONObject request = createRequest("getreceivedbylabel", parameters);
      JSONObject response = this.session.sendAndReceive(request);

      return response.getDouble("result");
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting the total amount received with label: " + label, e);
    }
  }

  public double getReceivedByAccount(String account, long minimumConfirmations)
  {
    try
    {
      JSONArray parameters = new JSONArray().put(account).put(minimumConfirmations);
      JSONObject request = createRequest("getreceivedbyaccount", parameters);
      JSONObject response = this.session.sendAndReceive(request);

      return response.getDouble("result");
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting the total amount received for account: " + account, e);
    }
  }

  public String help(String command)
  {
    try
    {
      JSONArray parameters = new JSONArray().put(command);
      JSONObject request = createRequest("help", parameters);
      JSONObject response = this.session.sendAndReceive(request);

      return response.getString("result");
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting help for a command", e);
    }
  }

  public List<AddressInfo> listReceivedByAddress(long minimumConfirmations, boolean includeEmpty)
  {
    try
    {
      JSONArray parameters = new JSONArray().put(minimumConfirmations).put(includeEmpty);
      JSONObject request = createRequest("listreceivedbyaddress", parameters);
      JSONObject response = this.session.sendAndReceive(request);
      JSONArray result = response.getJSONArray("result");
      int size = result.length();
      List list = new ArrayList();

      for (int i = 0; i < size; ++i) {
        AddressInfo info = new AddressInfo();
        JSONObject jObject = result.getJSONObject(i);
        info.setAddress(jObject.getString("address"));
        info.setAccount(jObject.getString("account"));
        info.setAmount(jObject.getDouble("amount"));
        info.setConfirmations(jObject.getLong("confirmations"));
        list.add(info);
      }

      return list;
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting info about all received transactions by address", e);
    }
  }

  @Deprecated
  public List<LabelInfo> listReceivedByLabel(long minimumConfirmations, boolean includeEmpty)
  {
    try
    {
      JSONArray parameters = new JSONArray().put(minimumConfirmations).put(includeEmpty);
      JSONObject request = createRequest("listreceivedbylabel", parameters);
      JSONObject response = this.session.sendAndReceive(request);
      JSONArray result = response.getJSONArray("result");
      int size = result.length();

      List list = new ArrayList(size);

      for (int i = 0; i < size; ++i) {
        LabelInfo info = new LabelInfo();
        JSONObject jObject = result.getJSONObject(i);
        info.setLabel(jObject.getString("label"));
        info.setAmount(jObject.getDouble("amount"));
        info.setConfirmations(jObject.getLong("confirmations"));
        list.add(info);
      }

      return list;
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting the received amount by label", e);
    }
  }

  public List<AccountInfo> listReceivedByAccount(long minimumConfirmations, boolean includeEmpty)
  {
    try
    {
      JSONArray parameters = new JSONArray().put(minimumConfirmations).put(includeEmpty);
      JSONObject request = createRequest("listreceivedbyaccount", parameters);
      JSONObject response = this.session.sendAndReceive(request);
      JSONArray result = response.getJSONArray("result");
      int size = result.length();

      List list = new ArrayList(size);

      for (int i = 0; i < size; ++i) {
        AccountInfo info = new AccountInfo();
        JSONObject jObject = result.getJSONObject(i);
        info.setAccount(jObject.getString("account"));
        info.setAmount(jObject.getDouble("amount"));
        info.setConfirmations(jObject.getLong("confirmations"));
        list.add(info);
      }

      return list;
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting the received amount by account", e);
    }
  }

  public List<TransactionInfo> listTransactions(String account, int count)
  {
    if (account == null) {
      account = "";
    }

    if (count <= 0) {
      throw new BitcoinClientException("count must be > 0");
    }
    try
    {
      JSONArray parameters = new JSONArray().put(account).put(count);
      JSONObject request = createRequest("listtransactions", parameters);
      JSONObject response = this.session.sendAndReceive(request);
      JSONArray result = response.getJSONArray("result");
      int size = result.length();

      List list = new ArrayList(size);

      for (int i = 0; i < size; ++i) {
        JSONObject jObject = result.getJSONObject(i);
        TransactionInfo info = parseTransactionInfoFromJson(jObject);
        list.add(info);
      }

      return list;
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting transactions for account: " + account, e);
    }
  }

  public TransactionInfo getTransaction(String txId)
  {
    try
    {
      JSONArray parameters = new JSONArray().put(txId);
      JSONObject request = createRequest("gettransaction", parameters);
      JSONObject response = this.session.sendAndReceive(request);
      JSONObject result = (JSONObject)response.get("result");

      return parseTransactionInfoFromJson(result);
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting transaction info for this id: " + txId, e);
    }
  }

  private TransactionInfo parseTransactionInfoFromJson(JSONObject jObject) throws JSONException {
    TransactionInfo info = new TransactionInfo();

    if (!(jObject.isNull("amount"))) {
      info.setAmount(jObject.getDouble("amount"));
    }

    if (!(jObject.isNull("category"))) {
      info.setCategory(jObject.getString("category"));
    }

    if (!(jObject.isNull("fee"))) {
      info.setFee(jObject.getDouble("fee"));
    }

    if (!(jObject.isNull("message"))) {
      info.setMessage(jObject.getString("message"));
    }else if(!(jObject.isNull("comment"))) {
      info.setMessage(jObject.getString("comment"));
    }

    if (!(jObject.isNull("to"))) {
      info.setTo(jObject.getString("to"));
    }

    if (!(jObject.isNull("address"))) {
      info.setAddress(jObject.getString("address"));
    }

    if (!(jObject.isNull("confirmations"))) {
      info.setConfirmations(jObject.getLong("confirmations"));
    }

    if (!(jObject.isNull("txid"))) {
      info.setTxId(jObject.getString("txid"));
    }

    if (!(jObject.isNull("otheraccount"))) {
      info.setOtherAccount(jObject.getString("otheraccount"));
    }

    if (!(jObject.isNull("time"))) {
      info.setTimestamp(jObject.getLong("time"));
    }

    return info;
  }

  public List<TransactionInfo> listTransactions(String account)
  {
    return listTransactions(account, 1000000000);
  }

  public List<TransactionInfo> listTransactions()
  {
    return listTransactions("",1000000000);
  }

  public WorkInfo getWork()
  {
    try
    {
      JSONObject request = createRequest("getwork");
      JSONObject response = this.session.sendAndReceive(request);
      JSONObject result = (JSONObject)response.get("result");

      WorkInfo info = new WorkInfo();
      info.setMidstate(result.getString("midstate"));
      info.setData(result.getString("data"));
      info.setHash1(result.getString("hash1"));
      info.setTarget(result.getString("target"));

      return info;
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when getting work info", e);
    }
  }

  public boolean getWork(String block)
  {
    try
    {
      JSONArray parameters = new JSONArray().put(block);
      JSONObject request = createRequest("getwork", parameters);
      JSONObject response = this.session.sendAndReceive(request);

      return response.getBoolean("result");
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when trying to solve a block with getwork", e);
    }
  }

  public String sendToAddress(String bitcoinAddress, double amount, String comment, String commentTo)
  {
    amount = checkAndRound(amount);
    try
    {
      JSONArray parameters = new JSONArray().put(bitcoinAddress).put(amount).put(comment).put(commentTo);

      JSONObject request = createRequest("sendtoaddress", parameters);
      JSONObject response = this.session.sendAndReceive(request);

      return response.getString("result");
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when sending bitcoins", e);
    }
  }

  public String sendFrom(String account, String bitcoinAddress, double amount, int minimumConfirmations, String comment, String commentTo)
  {
    if (account == null) {
      account = "";
    }

    if (minimumConfirmations <= 0) {
      throw new BitcoinClientException("minimumConfirmations must be > 0");
    }

    amount = checkAndRound(amount);
    try
    {
      JSONArray parameters = new JSONArray().put(account).put(bitcoinAddress).put(amount).put(minimumConfirmations).put(comment).put(commentTo);

      JSONObject request = createRequest("sendfrom", parameters);
      JSONObject response = this.session.sendAndReceive(request);

      return response.getString("result");
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when sending bitcoins with sendFrom()", e);
    }
  }

  public boolean move(String fromAccount, String toAccount, double amount, int minimumConfirmations, String comment)
  {
    if (fromAccount == null) {
      fromAccount = "";
    }

    if (toAccount == null) {
      toAccount = "";
    }

    if (minimumConfirmations <= 0) {
      throw new BitcoinClientException("minimumConfirmations must be > 0");
    }

    amount = checkAndRound(amount);
    try
    {
      JSONArray parameters = new JSONArray().put(fromAccount).put(toAccount).put(amount).put(minimumConfirmations).put(comment);

      JSONObject request = createRequest("move", parameters);
      JSONObject response = this.session.sendAndReceive(request);

      return response.getBoolean("result");
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when moving " + amount + " bitcoins from account: '" + fromAccount + "' to account: '" + toAccount + "'", e);
    }
  }

  private double checkAndRound(double amount)
  {
    if (amount < 0.01D) {
      throw new BitcoinClientException("The current machinery doesn't support transactions of less than 0.01 Bitcoins");
    }

    if (amount > 21000000.0D) {
      throw new BitcoinClientException("Sorry dude, can't transfer that many Bitcoins");
    }

    amount = roundToTwoDecimals(amount);
    return amount;
  }

  public void stop()
  {
    try
    {
      JSONObject request = createRequest("stop");
      this.session.sendAndReceive(request);
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when stopping the bitcoin server", e);
    }
  }

  public ValidatedAddressInfo validateAddress(String address)
  {
    try
    {
      JSONArray parameters = new JSONArray().put(address);
      JSONObject request = createRequest("validateaddress", parameters);
      JSONObject response = this.session.sendAndReceive(request);
      JSONObject result = (JSONObject)response.get("result");

      ValidatedAddressInfo info = new ValidatedAddressInfo();
      info.setIsValid(result.getBoolean("isvalid"));

      if (info.getIsValid())
      {
        info.setIsMine(result.getBoolean("ismine"));
        info.setAddress(result.getString("address"));
      }

      return info;
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when validating an address", e);
    }
  }

  public void backupWallet(String destination)
  {
    try
    {
      JSONArray parameters = new JSONArray().put(destination);
      JSONObject request = createRequest("backupwallet", parameters);
      this.session.sendAndReceive(request);
    } catch (JSONException e) {
      throw new BitcoinClientException("Exception when backing up the wallet", e);
    }
  }

  protected static double roundToTwoDecimals(double amount)
  {
    BigDecimal amountTimes100 = new BigDecimal(amount * 100.0D + 0.5D);
    BigDecimal roundedAmountTimes100 = new BigDecimal(amountTimes100.intValue());
    BigDecimal roundedAmount = roundedAmountTimes100.divide(new BigDecimal(100.0D));

    return roundedAmount.doubleValue();
  }

  private JSONObject createRequest(String functionName, JSONArray parameters) throws JSONException {
    JSONObject request = new JSONObject();
    request.put("jsonrpc", "2.0");
    request.put("id", UUID.randomUUID().toString());
    request.put("method", functionName);
    request.put("params", parameters);

    return request;
  }

  private JSONObject createRequest(String functionName) throws JSONException {
    return createRequest(functionName, new JSONArray());
  }
}
