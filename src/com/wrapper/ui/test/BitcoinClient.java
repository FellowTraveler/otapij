/*
*To change this template, choose Tools | Templates
*and open the template in the editor.
 */

package com.wrapper.ui.test;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
import ru.paradoxs.bitcoin.client.TransactionInfo;
import ru.paradoxs.bitcoin.client.ValidatedAddressInfo;
import ru.paradoxs.bitcoin.client.WorkInfo;
import ru.paradoxs.bitcoin.client.exceptions.BitcoinClientException;

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
    System.out.println(" parseTransactionInfoFromJson --- ");
    info.setAmount(jObject.getDouble("amount"));
    System.out.println("Account:"+jObject.getString("account"));
    if(!jObject.isNull("comment")){
    System.out.println("message1:"+jObject.getString("comment"));
    System.out.println("message1:"+jObject.get("comment"));
        }

    System.out.println("address:"+jObject.get("address"));
    System.out.println("time:"+jObject.get("time"));
    if (!(jObject.isNull("category"))) {
      info.setCategory(jObject.getString("category"));
    }

    if (!(jObject.isNull("fee"))) {
      info.setFee(jObject.getDouble("fee"));
    }

    if (!(jObject.isNull("message"))) {
      info.setMessage(jObject.getString("message"));
    }

    if (!(jObject.isNull("to"))) {
      info.setTo(jObject.getString("to"));
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

    return info;
  }

  public List<TransactionInfo> listTransactions(String account)
  {
    return listTransactions(account, 10);
  }

  public List<TransactionInfo> listTransactions()
  {
    return listTransactions("", 10);
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
