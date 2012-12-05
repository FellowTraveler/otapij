/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moneychanger.core.util;

import org.opentransactions.jni.core.otapiJNI;

import com.moneychanger.core.util.Utility;

/**
 *
 * @author FT
 */

/*
 * FT:  I notice a lot of code duplication, when sending messages and transaction
 * requests. I could basically remove all that duplication, except there are a couple
 * of OT_API calls inside each one, that are different, and that take different
 * parameters.
 * Best way to get around that, is to just make an object that will do the appropriate
 * API call, and store the necessary parameters inside. (A "functor" aka function
 * object.) Then pass it in as a parameter and trigger it at the appropriate time.
 * (That's what this is.)
 */


public class OTAPI_Func {

    public enum FT {
        CREATE_USER_ACCT,
        DELETE_USER_ACCT,
        CHECK_USER,
        SEND_USER_MESSAGE,
        SEND_USER_INSTRUMENT,
        ISSUE_ASSET_TYPE,
        ISSUE_BASKET,
        CREATE_ASSET_ACCT,
        DELETE_ASSET_ACCT,
        EXCHANGE_BASKET,
        PROCESS_INBOX,
        DEPOSIT_CASH,
        EXCHANGE_CASH,
        DEPOSIT_CHEQUE,
        WITHDRAW_VOUCHER,
        WITHDRAW_CASH,
        GET_CONTRACT,
        SEND_TRANSFER,
        GET_MARKET_LIST,
        CREATE_MARKET_OFFER,
        CANCEL_MARKET_OFFER,
        CANCEL_PAYMENT_PLAN,
        DEPOSIT_PAYMENT_PLAN,
        GET_NYM_MARKET_OFFERS,
        GET_MARKET_OFFERS,
        GET_MARKET_RECENT_TRADES,
        GET_MINT,
        QUERY_ASSET_TYPES,
        GET_BOX_RECEIPT;  //; is optional
        }

    public FT funcType;
    // -------------------------
    public String   serverID;
    public String   nymID, nymID2;
    public String   assetID, assetID2;
    public String   accountID, accountID2;
    public String   basket;
    public String   strData, strData2, strData3, strData4;
    public boolean  bBool;
    public int      nData;
    public int      nTransNumsNeeded;

    public int      nRequestNum; // IF the message is successfully sent, this will contain the request number after that point. (For convenience.)

   // for market offers
   //
   public OTAPI_Func(FT theType,
           String serverID,
           String nymID,
           String assetTypeID,      String assetAccountID,
           String currencyTypeID,   String currencyAcctID,
           String scale, String minIncrement, String quantity, String price,
           boolean bSelling) { // 12 args

       if (!Utility.VerifyStringVal(serverID))
           System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as serverID");
       if (!Utility.VerifyStringVal(nymID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as nymID");
       if (!Utility.VerifyStringVal(assetTypeID))
           System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as assetTypeID");
       if (!Utility.VerifyStringVal(assetAccountID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as assetAccountID");
       if (!Utility.VerifyStringVal(currencyTypeID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as currencyTypeID");
       if (!Utility.VerifyStringVal(currencyAcctID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as currencyAcctID");
       if (!Utility.VerifyStringVal(scale))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as scale");
       if (!Utility.VerifyStringVal(minIncrement))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as minIncrement");
       if (!Utility.VerifyStringVal(quantity))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as quantity");
       if (!Utility.VerifyStringVal(price))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as price");


        this.funcType   = theType;
        this.serverID   = serverID;
        this.nymID      = nymID;
        this.assetID    = assetTypeID;
        this.accountID  = assetAccountID;
        this.assetID2   = currencyTypeID;
        this.accountID2 = currencyAcctID;

        this.strData    = scale;
        this.strData2   = minIncrement;
        this.strData3   = quantity;
        this.strData4   = price;

        this.bBool      = bSelling;

        this.nTransNumsNeeded = 3; // An opening transaction number, plus another for each asset account, total of 3.
        
        this.nRequestNum  = (-1);
    }


    public OTAPI_Func(FT theType, String serverID, String nymID, String assetID,
            String basket, String accountID, boolean bBool, int nTransNumsNeeded) { // 8 args

        if (!Utility.VerifyStringVal(serverID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as serverID");
        if (!Utility.VerifyStringVal(nymID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as nymID");
        if (!Utility.VerifyStringVal(assetID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as assetID");
        if (!Utility.VerifyStringVal(accountID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as accountID");
        if (!Utility.VerifyStringVal(basket))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as basket");

        this.funcType   = theType;
        this.serverID   = serverID;
        this.nymID      = nymID;
        this.nTransNumsNeeded = nTransNumsNeeded;
        this.bBool      = bBool;
        this.assetID    = assetID;
        this.basket     = basket;
        this.accountID  = accountID;
        
        this.nRequestNum  = (-1);
    }


    public OTAPI_Func(FT theType, String serverID, String nymID) { // 3 args

        if (!Utility.VerifyStringVal(serverID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as serverID");
        if (!Utility.VerifyStringVal(nymID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as nymID");

        this.funcType   = theType;
        this.serverID   = serverID;
        this.nymID      = nymID;
        this.nTransNumsNeeded = 1;
        this.bBool      = false;
        
        this.nRequestNum  = (-1);
    }

    public OTAPI_Func(FT theType, String serverID, String nymID, String strParam, String strData) { // 5 args
        if (!Utility.VerifyStringVal(serverID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as serverID");
        if (!Utility.VerifyStringVal(nymID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as nymID");

        if (!Utility.VerifyStringVal(strParam))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as strParam");
        if (!Utility.VerifyStringVal(strData))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as strData");

        this.funcType   = theType;
        this.serverID   = serverID;
        this.nymID      = nymID;
        this.nTransNumsNeeded = 1;
        this.bBool      = false;
        
        this.nRequestNum  = (-1);
        
        switch(theType)
        {
            case CANCEL_MARKET_OFFER:
            case CANCEL_PAYMENT_PLAN:
            case PROCESS_INBOX:
            case DEPOSIT_CASH:
            case DEPOSIT_CHEQUE:
            case WITHDRAW_CASH:
                this.accountID  = strParam;
                this.strData    = strData;
                break;
            case EXCHANGE_CASH:
                this.assetID    = strParam;
                this.strData    = strData;
                break;
            case GET_MARKET_OFFERS:
                this.strData    = strParam;
                this.strData2   = strData;
                break;
            default:
                System.out.print("ERROR! WRONG TYPE passed to OTAPI_Func.OTAPI_Func() ERROR!!!!!!");
                break;
        }
    }
    public OTAPI_Func(FT theType, String serverID, String nymID, String accountID, String strParam, String strData, String strData2) { // 7 args

        if (!Utility.VerifyStringVal(serverID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as serverID");
        if (!Utility.VerifyStringVal(nymID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as nymID");
        if (!Utility.VerifyStringVal(accountID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as accountID");

        if (!Utility.VerifyStringVal(strParam))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as strParam");
        if (!Utility.VerifyStringVal(strData))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as strData");
        if (!Utility.VerifyStringVal(strData2))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as strData2");

        this.funcType   = theType;
        this.serverID   = serverID;
        this.nymID      = nymID;
        this.nTransNumsNeeded = 1;
        this.bBool      = false;
        this.accountID  = accountID;
        this.strData    = strData;
        this.strData2   = strData2;

        this.nRequestNum  = (-1);

        switch(theType) {
            case WITHDRAW_VOUCHER:
                this.nymID2     = strParam;
                break;
            case SEND_TRANSFER:
                this.accountID2 = strParam;
                break;
            default:
                System.out.print("ERROR! WRONG TYPE passed to OTAPI_Func.OTAPI_Func() ERROR!!!!!!");
                break;
        }


    }


    public OTAPI_Func(FT theType, String serverID, String nymID, String strParam) { // 4 args
        if (!Utility.VerifyStringVal(serverID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as serverID");
        if (!Utility.VerifyStringVal(nymID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as nymID");

        if (!Utility.VerifyStringVal(strParam))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as strParam");

        this.funcType   = theType;
        this.serverID   = serverID;
        this.nymID      = nymID;
        this.nTransNumsNeeded = 1;
        this.bBool      = false;
        
        this.nRequestNum  = (-1);

        switch(theType) {
            case ISSUE_BASKET:
                this.basket     = strParam;
                break;
            case GET_MINT:
            case GET_CONTRACT:
            case CREATE_ASSET_ACCT:
                this.assetID    = strParam;
                break;
            case CHECK_USER:
                this.nymID2     = strParam;
                break;
            case DELETE_ASSET_ACCT:
                this.accountID  = strParam;
                break;
            case DEPOSIT_PAYMENT_PLAN:
            case ISSUE_ASSET_TYPE:
            case GET_MARKET_RECENT_TRADES:
            case QUERY_ASSET_TYPES:
                this.strData    = strParam;
                break;
            default:
                System.out.print("ERROR! WRONG TYPE passed to OTAPI_Func.OTAPI_Func() ERROR!!!!!!");
                break;
        }
    }

    public OTAPI_Func(FT theType, String serverID, String nymID, String nymID2, String strData, String strData2) { // 6 args with string
        if (!Utility.VerifyStringVal(serverID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as serverID");
        if (!Utility.VerifyStringVal(nymID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as nymID");
        if (!Utility.VerifyStringVal(nymID2))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as nymID2");

        if (!Utility.VerifyStringVal(strData))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as strData");
        if (!Utility.VerifyStringVal(strData2))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as strData2");

        this.funcType   = theType;
        this.serverID   = serverID;
        this.nymID      = nymID;
        this.nTransNumsNeeded = 1;
        this.bBool      = false;
        
        this.nRequestNum  = (-1);

        switch(theType) {
            case SEND_USER_MESSAGE:
                this.nymID2     = nymID2;
                this.strData    = strData;
                this.strData2   = strData2;
                break;
            case SEND_USER_INSTRUMENT:
                this.nymID2     = nymID2;
                this.strData    = strData;
                this.strData2   = strData2;
                break;
            default:
                System.out.print("ERROR! WRONG TYPE passed to OTAPI_Func.OTAPI_Func() ERROR!!!!!!");
                break;
        }
    }

    public OTAPI_Func(FT theType, String serverID, String nymID, String accountID, int nData, String strData) { // 6 args with long
        if (!Utility.VerifyStringVal(serverID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as serverID");
        if (!Utility.VerifyStringVal(nymID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as nymID");
        if (!Utility.VerifyStringVal(accountID))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as accountID");

        if (!Utility.VerifyStringVal(strData))
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as strData");

        this.funcType   = theType;
        this.serverID   = serverID;
        this.nymID      = nymID;
        this.nTransNumsNeeded = 1;
        this.bBool      = false;
        
        this.nRequestNum  = (-1);

        switch(theType) {
            case GET_BOX_RECEIPT:
                this.accountID = accountID;
                this.nData    = nData;
                this.strData  = strData;
                break;
            default:
                System.out.print("ERROR! WRONG TYPE passed to OTAPI_Func.OTAPI_Func() ERROR!!!!!!");
                break;
        }
    }

    // -1 means error, no message was sent.
    //  0 means NO error, yet still no message was sent.
    // >0 means (usually) the request number is being returned.
    private int Run() {
        int nRetVal = (-1);
        switch (this.funcType) {
            case CHECK_USER:
                nRetVal = otapiJNI.OTAPI_Basic_checkUser(this.serverID, this.nymID, this.nymID2);
                break;
            case CREATE_USER_ACCT:
                nRetVal = otapiJNI.OTAPI_Basic_createUserAccount(this.serverID, this.nymID);
                break;
            case DELETE_USER_ACCT:
                nRetVal = otapiJNI.OTAPI_Basic_deleteUserAccount(this.serverID, this.nymID);
                break;
            case SEND_USER_MESSAGE:
                nRetVal = otapiJNI.OTAPI_Basic_sendUserMessage(this.serverID, this.nymID, this.nymID2, this.strData, this.strData2);
                break;
            case SEND_USER_INSTRUMENT:
                nRetVal = otapiJNI.OTAPI_Basic_sendUserInstrument(this.serverID, this.nymID, this.nymID2, this.strData, this.strData2);
                break;
            case GET_NYM_MARKET_OFFERS:
                nRetVal = otapiJNI.OTAPI_Basic_getNym_MarketOffers(this.serverID, this.nymID);
                break;
            case CREATE_ASSET_ACCT:
                nRetVal = otapiJNI.OTAPI_Basic_createAssetAccount(this.serverID, this.nymID, this.assetID);
                break;
            case DELETE_ASSET_ACCT:
                nRetVal = otapiJNI.OTAPI_Basic_deleteAssetAccount(this.serverID, this.nymID, this.accountID);
                break;
            case EXCHANGE_BASKET:
                nRetVal = otapiJNI.OTAPI_Basic_exchangeBasket(this.serverID, this.nymID, this.assetID, this.basket, this.bBool);
                break;
            case GET_CONTRACT:
                nRetVal = otapiJNI.OTAPI_Basic_getContract(this.serverID, this.nymID, this.assetID);
                break;
            case QUERY_ASSET_TYPES:
                nRetVal = otapiJNI.OTAPI_Basic_queryAssetTypes(this.serverID, this.nymID, this.strData);
                break;
            case GET_MINT:
                nRetVal = otapiJNI.OTAPI_Basic_getMint(this.serverID, this.nymID, this.assetID);
                break;
            case ISSUE_ASSET_TYPE:
                nRetVal = otapiJNI.OTAPI_Basic_issueAssetType(this.serverID, this.nymID, this.strData);
                break;
            case ISSUE_BASKET:
                nRetVal = otapiJNI.OTAPI_Basic_issueBasket(this.serverID, this.nymID, this.basket);
                break;
            case EXCHANGE_CASH:
                nRetVal = otapiJNI.OTAPI_Basic_exchangePurse(this.serverID, this.assetID, this.nymID, this.strData);
                break;
            case CANCEL_MARKET_OFFER:
                nRetVal = otapiJNI.OTAPI_Basic_cancelMarketOffer(this.serverID, this.nymID, this.accountID, this.strData);
                break;
            case CANCEL_PAYMENT_PLAN:
                nRetVal = otapiJNI.OTAPI_Basic_cancelPaymentPlan(this.serverID, this.nymID, this.accountID, this.strData);
                break;
            case GET_BOX_RECEIPT:
                nRetVal = otapiJNI.OTAPI_Basic_getBoxReceipt(this.serverID, this.nymID, this.accountID, this.nData, this.strData);
                break;
            case PROCESS_INBOX:
                nRetVal = otapiJNI.OTAPI_Basic_processInbox(this.serverID, this.nymID, this.accountID, this.strData);
                break;
            case DEPOSIT_CASH:
                nRetVal = otapiJNI.OTAPI_Basic_notarizeDeposit(this.serverID, this.nymID, this.accountID, this.strData);
                break;
            case DEPOSIT_CHEQUE:
                nRetVal = otapiJNI.OTAPI_Basic_depositCheque(this.serverID, this.nymID, this.accountID, this.strData);
                break;
            case DEPOSIT_PAYMENT_PLAN:
                nRetVal = otapiJNI.OTAPI_Basic_depositPaymentPlan(this.serverID, this.nymID, this.strData);
                break;
            case WITHDRAW_CASH:
                nRetVal = otapiJNI.OTAPI_Basic_notarizeWithdrawal(this.serverID, this.nymID, this.accountID, this.strData);
                break;
            case WITHDRAW_VOUCHER:
                nRetVal = otapiJNI.OTAPI_Basic_withdrawVoucher(this.serverID, this.nymID, this.accountID, this.nymID2, this.strData, this.strData2);
                break;
            case SEND_TRANSFER:
                nRetVal = otapiJNI.OTAPI_Basic_notarizeTransfer(this.serverID, this.nymID, this.accountID, this.accountID2,   this.strData, this.strData2); // amount and note, for the last two.
                break;
            case GET_MARKET_LIST:
                nRetVal = otapiJNI.OTAPI_Basic_getMarketList(this.serverID, this.nymID);
                break;
            case GET_MARKET_OFFERS:
                nRetVal = otapiJNI.OTAPI_Basic_getMarketOffers(this.serverID, this.nymID, this.strData, this.strData2);
                break;
            case GET_MARKET_RECENT_TRADES:
                nRetVal = otapiJNI.OTAPI_Basic_getMarketRecentTrades(this.serverID, this.nymID, this.strData);
                break;
            case CREATE_MARKET_OFFER:
                nRetVal = otapiJNI.OTAPI_Basic_issueMarketOffer(this.serverID, this.nymID, this.assetID, this.accountID, this.assetID2, this.accountID2,
                        this.strData, this.strData2, this.strData3, this.strData4, this.bBool);
                break;
            default:
                System.out.println("ERROR! OTAPI_Func.Send() activated, with bad function type!!!! ERROR!");
                break;
        } // switch
        
        return nRetVal;
    }

    // Generic function for any server message.
    // If you are doing a transaction message, then use SendTransaction instead.
    // (It will call this one.)
    // Returns:  null if failure, or the server's response message if success.
    //
    /*
    public static String SendRequest_DEPRECATED(OTAPI_Func theFunction, String IN_FUNCTION)  {

        String strResponseMessage = null;

        otapiJNI.OTAPI_Basic_FlushMessageBuffer();
        int nRun1 = theFunction.Run(); // <===== FIRST ATTEMPT
        Utility.delay();
        strResponseMessage = otapiJNI.OTAPI_Basic_PopMessageBuffer();

//      System.out.println("DEBUGGING SendRequest " + strResponseMessage);

        if ((null == strResponseMessage) ||
            (strResponseMessage.length() < 10) ||
            (0 == otapiJNI.OTAPI_Basic_Message_GetSuccess(strResponseMessage))) {
            if (false == Utility.getRequestNumber(theFunction.serverID, theFunction.nymID)) {
                System.out.println("IN SendRequest (" + IN_FUNCTION + " ): After first failure, Utility.getRequestNumber() failed as well. (I give up.) ");
                return null;
            }
            // getRequestNumber succeeded, so we'll try the Function call again...
            else {
                int nRun2 = theFunction.Run(); // <===== SECOND ATTEMPT
                Utility.delay();
                strResponseMessage = otapiJNI.OTAPI_Basic_PopMessageBuffer();
                if ((null == strResponseMessage) || (strResponseMessage.length() < 10) ||
                    (0 == otapiJNI.OTAPI_Basic_Message_GetSuccess(strResponseMessage))) {
                    System.out.println("IN SendRequest (" + IN_FUNCTION + " ): Function retry failed, even after Utility.getRequestNumber() succeeded. (I give up.) ");
                    return null;
                }
            }
        }
//      System.out.println("DEBUGGING SendRequest " + strResponseMessage);

        return strResponseMessage;
    }
     */

    
    /*
     
How to handle this?

Whenever sending any server message, save the REQUEST NUMBER.

If message is null (apparently no reply) then re-download nymbox and receipts.

Then call a function to see if the replyNotice is there, based on REQUEST NUMBER.

If the notice is there, grab a copy of the server reply (as a string), and then process the Nymbox. 
(This will cause OT to process the server reply that I should have gotten in the first place.)
This will also leave me standing with a copy of the server's reply, which is what 
WOULD have happened if I had gotten the message properly in the first place.

If the notice is NOT there, the message never got through, or it failed. However, 
processing the Nymbox is the best way to insure it WILL work on a re-try. But probably then it will have different request number and different transactions numbers.

NEED a function in the API where I can pass such messages, in order to reclaim my transaction numbers.
BUT…  I often don't have the message itself, when it is originally sent -- so how
could I then leverage it to reclaim pieces of its data?

Instead I just have OTAPI_Func which automatically does re-tries when there are failures.
Maybe code OTAPI_Func to be smart enough to reclaim transaction numbers.

Problem is that OTAPI_Func doesn't have access to the messages themselves. It just
calls the API, which constructs the messages internally (separately each time.)
Each time OTAPI_Func calls exchangeBasket, it grabs a new transaction number each time. 
But if the message never got through the first time, that means the original 
transaction # is still good -- how will I harvest that, 

UNLESS I SAVE A COPY OF OUTGOING MESSAGES…!!!!!


     */
    // This function returns the REQUEST NUMBER of the sent message, if it was sent.
    // OTHERWISE it returns -1 for error, 0 for no error, 
    // This function returns a VALID REPLY MESSAGE (or null) in the output parameter.
    // The reply message may be SUCCESS or FAILURE, depending on whether
    // the server accepted (or rejected) the request.
    // The caller will have to check either way--that's his problem not ours.
    //
    public static int SendRequestLowLevel(OTAPI_Func theFunction, String IN_FUNCTION)  
    {
        otapiJNI.OTAPI_Basic_FlushMessageBuffer();
        // --------------------------------------------------------------------
        final int nRun = theFunction.Run(); // <===== ATTEMPT TO SEND THE MESSAGE HERE...
                
        if (nRun == (-1))  // if the requestNumber returned by the send-attempt is -1, that means it DIDN'T SEND (error)
        {
            System.out.println("OTAPI_Func.SendRequestLowLevel (" + IN_FUNCTION + " ): Failed to send message due to error.");
                    
            theFunction.nRequestNum  = (-1);
        }
        else if (nRun == 0) // if the requestNumber returned by the send-attempt is 0, it means no error, but nothing was sent. (Like processing an empty box.)
        {
            System.out.println("OTAPI_Func.SendRequestLowLevel (" + IN_FUNCTION + " ): Didn't send this message, but NO error occurred, either. (For example, a request to process an empty Nymbox will return 0, meaning, nothing was sent, but also no error occurred.)");
            
            theFunction.nRequestNum  = 0;
        }
        else if (nRun == (-2)) // -2 is also possible at some future date. (If the request number won't fit in an int, this is returned and then you can retrieve the actual number via a separate call.)
        {
            System.out.println("OTAPI_Func.SendRequestLowLevel (" + IN_FUNCTION + " ): ERROR, not supported. (-2 was returned.)");
            
            // TODO: Have an OT API call here, which retrieves the request number
            // (It must be too large to fit into an integer, which is why this happened.)
            
            theFunction.nRequestNum  = (-1);
        }
        else
        {
            theFunction.nRequestNum  = nRun;
        }
            
        // BY this point, we definitely have the request number, which means the
        // message was actually SENT. (At least.) This also means we can use nRun
        // later to query for a copy of that sent message (like if we need to clawback
        // the transaction numbers from it later, once we confirm whether the server
        // actually never got it.)
        //
        return theFunction.nRequestNum;
    }
    
    /*
     When calling the above and below functions:
      
     final String strReply;
     final int nRequestNum = OTAPI_Func.SendRequestLowLevel(theFunction, IN_FUNCTION);
     switch(nRequestNum)
     {
          // ----------------------------------------------
          // NO NEED TO LOG HERE (These are already logged in the above call.)
          case (-1):  // Error, nothing sent.         
          case (0):   // NO error, but nothing sent.  
              return; // (Reply is NOT expected.)
          // ----------------------------------------------
          case (1):   // Message sent successfully! (No request number returned except a generic '1'
                      // used by certain calls like checkServer, to indicate it sent successfully.)
          // NO break // ===> (Reply IS expected.)
          default:    // ALL other request numbers (SUCCESS sending.)
              strReply = OTAPI_Func.ReceiveReplyLowLevel(IN_FUNCTION);
              break;  // ===> (Reply IS expected.)
     }
     // ----------------------------------------------
     if (null == strReply)
     {
         // USE request num here, and retries, and resyncing, etc.
     }
      
     */
    
    // ---------------------------------------------------------
    
        /*
         * What's going on here?
         * 
         * -- Flush the message buffer, and then send the server message.
         * -- nRun1 contains the REQUEST NUMBER, if the message was actually
         *    sent. Otherwise it contains 0 if nothing happened (no error) or
         *    -1 if there was an error. 
         * -- Clearly in the above cases, unless the request number is actually
         *    returned from the first call, any other waiting / popping / re-trying / re-syncing
         *    is nothing more than guesswork, (which is why it worked mostly but then had
         *    speed and sync issues.)
         * -- IF the request number IS returned, then we need to pop the message buffer
         *    and see if there is a server reply.
         * 
         * -- HERE ARE THE OPTIONS WE MIGHT SEE, UPON POPPING THE SERVER REPLY:
         *    1. Returns null.
         *    2. Returns an empty string.
         *    3. Returns the proper message, failed.
         *    4. Returns the proper message, succeeded.
         * 
         * Additionally, if it's a transaction, "proper message succeeded" (step 4)
         * turns into THESE options:
         * 
         *    5. Returns the proper message, succeeded: BUT Balance Agreement failed!
         *    6. Returns the proper message, succeeded: AND Balance Agreement success, but TRANSACTION failed.
         *    7. Returns the proper message, succeeded: AND Balance Agreement success, AND TRANSACTION succeeded.
         * 
         */
 
/*
    public static String SendRequest(OTAPI_Func theFunction, String IN_FUNCTION) 
    {
        Utility.OTBool bCanRetryAfterThis = new Utility.OTBool(false);
        // -------------------------------------------------------
        String strResult = SendRequestOnce( theFunction,
                                            IN_FUNCTION,
                                            false,  // bIsTransaction      = false;
                                            true,   // bWillRetryAfterThis = true
                                            bCanRetryAfterThis);
        // -------------------------------------------------------
        
        if ((null == strResult) && 
                bCanRetryAfterThis.getBooleanValue())
            strResult = SendRequestOnce( theFunction,
                                         IN_FUNCTION,
                                         false,  // bIsTransaction      = false;
                                         false,  // bWillRetryAfterThis = false
                                         bCanRetryAfterThis);
        
        return strResult;
    }
    */
    
    
    public static String SendTransaction(OTAPI_Func theFunction, String IN_FUNCTION)
    {
        // We won't download here unless we can see the hash has changed.
        // If it HAS, and our download fails, then we have failed.
        // (Can't sign any balance agreements anyway without that download...)
        //
        if (false == Utility.getIntermediaryFiles(theFunction.serverID,
                theFunction.nymID, theFunction.accountID, false)) // bForceDownload=false
        {
            System.out.println(IN_FUNCTION + " getIntermediaryFiles returned false. (It couldn't download files that it needed.)");
            return null;
        }
        // -------------------------------------------------------

        
        // **********************************************************************
        // GET TRANSACTION NUMBERS HERE IF NECESSARY.
        //
        boolean bSure = true;

        if (otapiJNI.OTAPI_Basic_GetNym_TransactionNumCount(theFunction.serverID, theFunction.nymID)    // If the current trans# count is LESS than what's needed...
                <
                (theFunction.nTransNumsNeeded > Configuration.getNbrTransactionCount() ?        //theFunction needs more than my normal minimum in my configuration
                    theFunction.nTransNumsNeeded  : Configuration.getNbrTransactionCount()))    // Therefore I will grab that many, instead of however many I would normally grab.
        {
            System.out.println("OTAPI_Func.SendTransaction: " + IN_FUNCTION + ", I don't have enough transaction numbers to perform this transaction. Grabbing more now...");
            
            int configTxnCount = Configuration.getNbrTransactionCount();
            
            Configuration.setNbrTransactionCount((theFunction.nTransNumsNeeded > configTxnCount) ? theFunction.nTransNumsNeeded : configTxnCount);
            
            bSure = Utility.getTransactionNumbers(theFunction.serverID, theFunction.nymID); // <====================== getTransactionNumbers
            
            Configuration.setNbrTransactionCount(configTxnCount);
        }
        // -------------------------------------
        
        if (
//              !bSure || 
                (otapiJNI.OTAPI_Basic_GetNym_TransactionNumCount(theFunction.serverID, theFunction.nymID) < theFunction.nTransNumsNeeded))  // Try a second time.
        {
            System.out.println("In OTAPI_Func.SendTransaction, for: " + IN_FUNCTION + ", first failure:  Utility.getTransactionNumbers. (Trying again...)");
            
            bSure = Utility.getTransactionNumbers( theFunction.serverID, theFunction.nymID,    // Try a second time.        
                                                   false); // We tell getTransNumbers that it can skip the first call to getTransNumLowLevel        
        }
        // -------------------------------------
        if (
//              !bSure || 
                (otapiJNI.OTAPI_Basic_GetNym_TransactionNumCount(theFunction.serverID, theFunction.nymID) < theFunction.nTransNumsNeeded))  // Try a third time.
        {
            System.out.println("In OTAPI_Func.SendTransaction, for: " + IN_FUNCTION + ", second failure:  Utility.getTransactionNumbers. (Trying again...)");
            
            bSure = Utility.getTransactionNumbers( theFunction.serverID, theFunction.nymID, // Try a third time.   
                                                   false); // We tell getTransNumbers that it can skip the first call to getTransNumLowLevel        
        }
        // -------------------------------------
        // Giving up if still a failure...
        //
        if (
//              !bSure || 
                (otapiJNI.OTAPI_Basic_GetNym_TransactionNumCount(theFunction.serverID, theFunction.nymID) < theFunction.nTransNumsNeeded))
        {
            System.out.println("In OTAPI_Func.SendTransaction, for: " + IN_FUNCTION + ", third failure. bSure: " + bSure + ". OT_API_GetNym_TransactionNumCount: " +
                    otapiJNI.OTAPI_Basic_GetNym_TransactionNumCount(theFunction.serverID, theFunction.nymID));
            return null;
        }
        
        // **********************************************************************
        
        Utility.OTBool bCanRetryAfterThis = new Utility.OTBool(false);
        // -------------------------------------------------------
        
 
        String strResult = SendRequestOnce( theFunction,
                                            IN_FUNCTION,
                                            true,   // bIsTransaction      = true;
                                            true,   // bWillRetryAfterThis = true
                                            bCanRetryAfterThis); // output
        
        // -------------------------------------------------------
        
        if (Utility.VerifyStringVal(strResult)) // success.
        {
            if (false == Utility.getIntermediaryFiles(theFunction.serverID,
                    theFunction.nymID, theFunction.accountID, true)) // bForceDownload=true
            {
                System.out.println(IN_FUNCTION + " getIntermediaryFiles returned false. (After a success sending the transaction. Strange...)");
                return null;
            }
            // -----------------------------
            
            return strResult; // success!
        }
        
        
        //
        // Maybe we have an old Inbox or something.
        //
        
        
        // TODO!!  SECURITY:  This is where a GOOD CLIENT (vs. a test client)
        // will verify these intermediary files against your LAST SIGNED RECEIPT,
        // using OT_API_VerifySomethingorother().
        // See verifyFiles() at the bottom of this file.
        // Add some kind of warning Modal Dialog here, since it's actually
        // normal for a NEW account (never had any receipts yet.) But for
        // any other account, this should ALWAYS VERIFY!
        //
        // Another note: this should happen INSIDE the getIntermediaryFiles call itself,
        // and all similar calls.  You simply should not download those files,
        // without verifying them also. Otherwise you could end up signing
        // a future bad receipt, based on malicious, planted intermediary files.
        
        int nRetries = 2;
        
        while ((nRetries > 0) && (!Utility.VerifyStringVal(strResult)) && 
                bCanRetryAfterThis.getBooleanValue())
        {
            --nRetries;
            // -------------------------------
                Utility.OTBool bWasMsgSent = new Utility.OTBool(false);
                                
                // NOTE: LATEST CHANGES, DEBUGGING. COMMENTED THIS OUT DURING DEBUGGING PROCESS.
                //
//                final int nLast = Utility.getAndProcessNymbox(theFunction.serverID, theFunction.nymID, bWasMsgSent, true); // bForceDownload=true
                // ---------------------------
//                if ( (!bWasMsgSent.getBooleanValue() && (0 == nLast)) ||    // If the message was NOT sent, and Nymbox was empty,
//                      (bWasMsgSent.getBooleanValue() && (1 == nLast)) )     // or if the message WAS sent, and it was success, (either way is success.)
                    
                    strResult = SendRequestOnce( theFunction,
                                                 IN_FUNCTION,
                                                 true,   // bIsTransaction      = true;
                                                 ((nRetries == 0) || !bCanRetryAfterThis.getBooleanValue()) ? false : true,  // bWillRetryAfterThis = false
                                                 bCanRetryAfterThis);
                    // ---------------------------
                    // In case of failure, we want to get these before we re-try.
                    // But in case of success, we also want to get these, so we can
                    // see the results of our success. So we get these either way...
                    //
                    if (Utility.VerifyStringVal(strResult))
                    {
                        if (false == Utility.getIntermediaryFiles(theFunction.serverID,
                                theFunction.nymID, theFunction.accountID, true)) // bForceDownload=true
                        {
                            System.out.println(IN_FUNCTION + " getIntermediaryFiles (loop) returned false even after successfully sending the transaction.");
                            return null;
                        }
                        
                        break;
                    }
            // -------------------------------
        }
        
        return strResult;
    }
    
    // **********************************************************************
  
    
    public static String SendRequest(OTAPI_Func theFunction, String IN_FUNCTION) 
    {
        Utility.OTBool bCanRetryAfterThis = new Utility.OTBool(false);
        // -------------------------------------------------------
        String strResult = SendRequestOnce( theFunction,
                                            IN_FUNCTION,
                                            false,  // bIsTransaction      = false;
                                            true,   // bWillRetryAfterThis = true
                                            bCanRetryAfterThis);
        // -------------------------------------------------------
        
        if ((!Utility.VerifyStringVal(strResult)) && 
                bCanRetryAfterThis.getBooleanValue())
            strResult = SendRequestOnce( theFunction,
                                         IN_FUNCTION,
                                         false,  // bIsTransaction      = false;
                                         false,  // bWillRetryAfterThis = false
                                         bCanRetryAfterThis); // unused in this case, I suppose. It's still returned as output.
        
        return strResult;
    }
    
    // **********************************************************************
    
    public static String SendRequestOnce(OTAPI_Func theFunction,
                                        String     IN_FUNCTION,
                                        boolean    bIsTransaction,
                                        boolean    bWillRetryAfterThis,
                                        Utility.OTBool bCanRetryAfterThis)
    {
        if (null == bCanRetryAfterThis)
        {
            System.out.println("SendRequestOnce: Error: bCanRetryAfterThis was null.");
            return null;
        }
        bCanRetryAfterThis.setBooleanValue(false);
        // ----------------------------------------------
        String strReply = null;
        final int nRequestNum = OTAPI_Func.SendRequestLowLevel(theFunction, IN_FUNCTION);  // <========   FIRST ATTEMPT!!!!!!
        
        switch(nRequestNum)
        {
            // ----------------------------------------------
            // NO NEED TO LOG HERE (These are already logged in the above call.)
            case (-1):  // Error, nothing sent.         
            case ( 0):   // NO error, but also, still: nothing sent.  
            {
                return null; // (Reply is NOT expected from the server, for either of these.)
            }
            // ----------------------------------------------
            case (1):   // Message sent successfully! (No request number returned except a generic '1'
                        //  used by certain calls like checkServer, to indicate it sent successfully.)
            // NO break // ===> (Reply IS expected.)
            default:    // Message sent successfully! (ALL other request numbers.)
            {    
                if (nRequestNum < (-1))
                    return null;
                
                strReply = Utility.ReceiveReplyLowLevel(theFunction.serverID, theFunction.nymID, nRequestNum, IN_FUNCTION);  // <=============== Here we RECEIVE the REPLY...
            }
                break;  // ===> (Reply IS expected.)
        }
        // Below this point, we definitely have a request number.
        // (But not yet necessarily a reply...)
        //
        // ----------------------------------------------
        
        // nRequestNum is positive and contains the request number from sending.
        //
        // nReplySuccess contains status of the REPLY to that sent message.
        // nReplySuccess contains:
        //   0 == FAILURE reply msg from server,
        //   1 == SUCCESS reply msg from server (transaction could be success or fail.)
        //  -1 == (ERROR)
        //
        // strReply contains the reply itself (or null.)
        //
        final int     nReplySuccess      = Utility.getMessageSuccess(strReply); 
        // -----------------------------------------------------------------------------------------------------
        final boolean bMsgReplyError     = (!Utility.isValid(strReply) || (nReplySuccess   < 0));
        // -----------------------------------------------------------------------------------------------------
        final boolean bMsgReplySuccess   = (!bMsgReplyError && (nReplySuccess  > 0));
        final boolean bMsgReplyFailure   = (!bMsgReplyError && (nReplySuccess == 0));
        // -----------------------------------------------------------------------------------------------------
        // -----------------------------------------------------------------------------------------------------
        final boolean bMsgBalanceError;
        final boolean bMsgBalanceSuccess;
        final boolean bMsgBalanceFailure;
        // -----------------------------------------------------------------------------------------------------
        final boolean bMsgTransError;
        final boolean bMsgTransSuccess;
        final boolean bMsgTransFailure;
        // -----------------------------------------------------------------------------------------------------
        // -----------------------------------------------------------------------------------------------------
        final boolean bMsgAnyError;
        final boolean bMsgAnyFailure;
        // -----------------------------------------------------------------------------------------------------
        final boolean bMsgAllSuccess;
        // -----------------------------------------------------------------------------------------------------
        
        // If you EVER are in a situation where you have to harvest numbers
        // back, it will ONLY be for transactions, not normal messages. (Those
        // are the only ones that USE transaction numbers.)
        //
        final int nTransSuccess, nBalanceSuccess;
        //
        if (bIsTransaction) // This request contains a TRANSACTION...
        {
            if (bMsgReplySuccess) // If message was success, then let's see if the transaction was, too.
            {
                nBalanceSuccess = otapiJNI.OTAPI_Basic_Message_GetBalanceAgreementSuccess(theFunction.serverID, theFunction.nymID, theFunction.accountID, strReply); 

//                if (nBalanceSuccess < 0)
//                    nTransSuccess = (-1);
//                else if (0 == nBalanceSuccess) // balance agreement was explicit failure.
//                    nTransSuccess = 0; // NOTE: VERY RECENT CHANGE (SEE COMMENTED SECTION BELOW) SO MIGHT NEED TO CHANGE THIS BACK... DEBUGGING RIGHT NOW...
//                else
//                    nTransSuccess   = otapiJNI.OTAPI_Basic_Message_GetTransactionSuccess (theFunction.serverID, theFunction.nymID, theFunction.accountID, strReply); 
                
                if (nBalanceSuccess > 0)
                    nTransSuccess   = otapiJNI.OTAPI_Basic_Message_GetTransactionSuccess (theFunction.serverID, theFunction.nymID, theFunction.accountID, strReply); 
                else
                    nTransSuccess = (-1);
            }
            else
            {
                nBalanceSuccess = -1;
                nTransSuccess   = -1;
            }
            // All of these booleans (except "error") represent RECEIVED ANSWERS from the server.
            // In other words, "failure" does not mean "failed to find message."
            // Rather, it means "DEFINITELY got a reply, and that reply says status==failure."
            //
            // -----------------------------------------------------------------------------------------------------
            bMsgBalanceError   = (!Utility.isValid(strReply) || (nBalanceSuccess < 0));
            bMsgBalanceSuccess = (!bMsgReplyError && !bMsgBalanceError && (nBalanceSuccess  > 0));
            bMsgBalanceFailure = (!bMsgReplyError && !bMsgBalanceError && (nBalanceSuccess == 0));
            // -----------------------------------------------------------------------------------------------------
            bMsgTransError     = (!Utility.isValid(strReply) || (nTransSuccess   < 0));
            bMsgTransSuccess   = (!bMsgReplyError && !bMsgBalanceError && !bMsgTransError && (nTransSuccess  > 0));
            bMsgTransFailure   = (!bMsgReplyError && !bMsgBalanceError && !bMsgTransError && (nTransSuccess == 0));
            // -----------------------------------------------------------------------------------------------------
            // -----------------------------------------------------------------------------------------------------
            bMsgAnyError       = (bMsgReplyError   || bMsgBalanceError   || bMsgTransError);
            bMsgAnyFailure     = (bMsgReplyFailure || bMsgBalanceFailure || bMsgTransFailure);
            // -----------------------------------------------------------------------------------------------------
            bMsgAllSuccess     = (bMsgReplySuccess && bMsgBalanceSuccess && bMsgTransSuccess);
            // -----------------------------------------------------------------------------------------------------
        }
        else // it's NOT a transaction, but a normal message..
        {
            nBalanceSuccess = -1;
            nTransSuccess   = -1;
            // -----------------------------------------------------------------------------------------------------
            bMsgBalanceError   = false;
            bMsgBalanceSuccess = false;
            bMsgBalanceFailure = false;
            // -----------------------------------------------------------------------------------------------------
            bMsgTransError     = false;
            bMsgTransSuccess   = false;
            bMsgTransFailure   = false;
            // -----------------------------------------------------------------------------------------------------
            // -----------------------------------------------------------------------------------------------------
            bMsgAnyError       = (bMsgReplyError);
            bMsgAnyFailure     = (bMsgReplyFailure);
            // -----------------------------------------------------------------------------------------------------
            bMsgAllSuccess     = (bMsgReplySuccess);
            // -----------------------------------------------------------------------------------------------------            
        }
        // ****************************************************************************
        
        // We know the message SENT. The above logic is about figuring out whether the reply message,
        // the transaction inside it, and the balance agreement inside that transaction, whether
        // any of those three things is a definite error, a definite failure, or a definite success.
        // (Any one of those things could be true, OR NOT, and we can only act as if they are, if we
        // have definitive proof in any of those cases.)
        //
        // The below logic is about what sort of REPLY we may have gotten (if anything.)
        // Without a definite reply we cannot claw back. But the Nymbox can show us this answer,
        // either now, or later...
        //
        if (bMsgAllSuccess)
        {
            // the Msg was a complete success, including the message
            // AND the transaction AND the balance agreement.
            // Therefore, there's DEFINITELY nothing to clawback.
            //
            // (Thus I RemoveSentMessage for the message, since 
            // I'm totally done with it now. NO NEED TO HARVEST anything, either.)
            //
//            final int nRemoved = otapiJNI.OTAPI_Basic_RemoveSentMessage(Integer.toString(nRequestNum), theFunction.serverID, theFunction.nymID);
//            
            // NOTE: The above call is unnecessary, since a successful reply means
            // we already received the successful server reply, and OT's "ProcessServerReply"
            // already removed the sent message from the sent buffer (so no need to do that here.)
            
//          System.out.println("SendRequestOnce(): OT_API_RemoveSentMessage: " + nRemoved);
            
            return strReply;         
            
        }
        // In this case we want to grab the Nymbox and see if the replyNotice is there.
        // If it IS, then OT server DEFINITELY replied to it (the MESSAGE was a success,
        // whether the transaction inside of it was success or fail.) So we know bMsgReplySuccess
        // is true, if we find a replyNotice.
        // From there, we can also check for transaction success.
        //
        else if (bMsgAnyError || bMsgAnyFailure) // let's resync, and clawback whatever transaction numbers we might have used on the Request...
        {                
            Utility.OTBool bWasGetReqSent = new Utility.OTBool(false);
            
            final int nGetRequest = Utility.getRequestNumber(theFunction.serverID, theFunction.nymID, bWasGetReqSent);  // <==== RE-SYNC ATTEMPT...
            // ------------------------------------------
            // GET REQUEST WAS A SUCCESS.
            //
            if (bWasGetReqSent.getBooleanValue() && 
                (nGetRequest > 0)) // success
            {
                bCanRetryAfterThis.setBooleanValue(true);
                // ------------------------------------------------------------------------------
                // But--if it was a TRANSACTION, then we're not done syncing yet!
                //
                if (bIsTransaction) 
                {
                    bCanRetryAfterThis.setBooleanValue(false);
                    // -------------------------------------------

                    //
                    // Maybe we have an old Inbox or something.
                    // NEW CODE HERE FOR DEBUGGING (THIS BLOCK)
                    //
                    if (false == Utility.getIntermediaryFiles(theFunction.serverID,
                            theFunction.nymID, theFunction.accountID, true)) // bForceDownload=true
                    {
                        System.out.println(IN_FUNCTION + " getIntermediaryFiles returned false. (After a failure to send the transaction. Thus, I give up.)");
                        return null;
                    }
                    
                    
                    // -------------------------------------------
                    Utility.OTBool  bWasFound = new Utility.OTBool(false),
                                    bWasSent  = new Utility.OTBool(false);

                    final int nProcessNymboxResult = 
                            Utility.getAndProcessNymbox(theFunction.serverID, theFunction.nymID, bWasSent, false, // bForceDownload=false
                                          nRequestNum, bWasFound,
                                          bWillRetryAfterThis, // bHarvestingForRetry,    // bHarvestingForRetry is INPUT, in the case nRequestNumber needs to be harvested before a flush occurs.
                                          // --------------------
                                          bMsgReplySuccess,    // bMsgReplySuccess is INPUT, and is in case nRequestNumber needs to be HARVESTED before a FLUSH happens.
                                          bMsgReplyFailure,    // bMsgReplyFailure is INPUT, and is in case nRequestNumber needs to be HARVESTED before a FLUSH happens.
                                          bMsgTransSuccess,    // bMsgTransSuccess is INPUT, and is in case nRequestNumber needs to be HARVESTED before a FLUSH happens.
                                          bMsgTransFailure);   // Etc.
                    // -------------------------------------------
                    if ((bWasSent.getBooleanValue() && (1 == nProcessNymboxResult)) ||
                        !bWasSent.getBooleanValue() && (0 == nProcessNymboxResult))// success processing Nymbox.
                    {
                        bCanRetryAfterThis.setBooleanValue(true);
                    }
                    // -------------------------------------------
                    
                    // This means a request number was returned, since the processNymbox failed,
                    // and hasn't been properly harvested, so we either need to harvest it for a re-try,
                    // or flush it.
                    //
                    else if (bWasSent.getBooleanValue() && (nProcessNymboxResult > 1))
                    {
                        String strNymbox = otapiJNI.OTAPI_Basic_LoadNymboxNoVerify(theFunction.serverID, theFunction.nymID);      // FLUSH SENT MESSAGES!!!!  (AND HARVEST.)

                        // *******************************************************
                        if (Utility.isValid(strNymbox))
                             otapiJNI.OTAPI_Basic_FlushSentMessages(false, //harvesting for retry == false
                                                           theFunction.serverID,
                                                           theFunction.nymID,
                                                           strNymbox);                        
                    }
                } // if (bIsTransaction)
                // -----------------------------------------------------------------------                                    
            } // if getRequest was success.
            else
            {
                System.out.println("SendRequestOnce: Failure: Never got reply from server, so tried getRequest, and that failed too. (I give up.) ");
                
                // Note: cannot harvest transaction nums here because I do NOT know for sure
                // whether the server has replied to the message or not! (Not until I successfully
                // download my Nymbox.) Therefore, do NOT harvest or flush, but hold back and wait
                // until the next attempt does a successful getNymbox and THEN do a "flush sent" after
                // that. (That's the time we'll know for SURE what happened to the original reply.)
                //
                // (Therefore LEAVE the sent message in the sent queue.)
                
                return null;
            }
        } // else if (bMsgAnyError || bMsgAnyFailure)
        // --------------------------------------------------------

        return null;         
    }
        
    
    
    
    
    
    
    
        
        
//        if (null == strReply) // DROPPED REPLY? Probably was successful?
//        {                     // Possibly could have failed? Need to resync to make sure, then maybe re-try?
//            /*
//             * -- THE SERVER REPLY (potentially):
//             *    1. Returns null.
//             *    2. Returns an empty string.
//             * 
//             * (ReceiveReplyLowLevel handles that, and returns the string, or null on failure.)
//             * 
//             * ===> In these cases there probably WAS a server reply, but we didn't receive it.
//             *      (Network burp.) If the message was less important, we can just re-send it.
//             *      If the message was MORE important, then there's probably a copy of it in my Nymbox.
//             *      Therefore, OTAPI_Func should know the diff between important, Nymbox-level messages,
//             *      where it has to download the Nymbox first to make sure the reply's not already there,
//             *      versus less-important messages that can just be re-sent. 
//             *      THUS: get a list of NymboxNotice-dropping commands.
//             *      
//             *      Ah-ha: The first thing we do ANYWAY is get the request number, and THAT has a Nymbox hash.
//             *      And if the request number syncs, then I know the request number is not an issue.
//             *
//             *      ===> If the NymboxHash is changed from what I expected, then I need to re-download the
//             *      nymbox (and any box receipts I don't already have.)
//             * 
//             *      Then I need to process the Nymbox. But first, see if my missing server reply is in there.
//             *      If it is, then I have the server reply! (As if we had succeeded in the first place!!)
//             *      Next, process the Nymbox (which processes that reply) and then return strReply like normal.
//             * 
//             *      (Clearly this is just going to be a normal part of the getRequest syncronization.)
//             * 
//             *      By the time that much is done, I will KNOW the request number, the nymbox, the box receipts,
//             *      etc are ALL syncronized properly, and that I THEN processed the Nymbox successfully.
//             * 
//             * 
//             *      NOTICE: In this example I do NOT want to pull out my sent message from the outbuffer (using
//             *      the request number) and try to harvest all the transaction numbers. Why not? Because possibly the
//             *      server DID reply! And if I processed that reply properly, it would sync my transaction numbers
//             *      properly just from that! ===>
//             * 
//             *      ===> Therefore, I need to see FIRST if the old message has a reply WAITING in the Nymbox. THEN
//             *      I need to process the Nymbox. ONLY if the reply wasn't there, can I THEN pull out the message
//             *      from my outbuffer and harvest it. (Which I am reticent to do, until I am SURE the server
//             *      really never saw that message in the first place.)
//             * 
//             *      However, as long as my NymboxHash hasn't changed, then I'm safe! But if it HAS changed,
//             *      then I HAVE to A. download it B. SEE if the reply is there for the request number, then 
//             *      C. process it. ... If the reply wasn't there, THEN Harvest the transaction #s (for transaction
//             *      messages) and then re-try.
//             */
//            Utility.OTBool bWasFound = new Utility.OTBool(false), bWasSent = new Utility.OTBool(false);
//            
//            final int nMsg = Utility.getAndProcessNymbox(theFunction.serverID, theFunction.nymID, bWasSent, nRequestNum, bWasFound);
//            
//            if (nMsg < 1)
//            {
//                System.out.println("SendRequest: Failure: Utility.getAndProcessNymbox. Returned value: " + nMsg);
//                return null;
//            }
            // 
            //-------------------------------------------------
            //
            // NOTE: If the hashes MATCH, then 
            // -- I definitely do NOT need to download Nymbox, etc. (It already matches.)
            // -- Therefore, there is definitely NOT some dropped message that I NEED out 
            //    of my nymbox (or the hash would have changed by now.)

            // For example:
            /*
             *  Let's say I create a BASKET EXCHANGE, with trans#'s 5, 6, and 7.
             *  Next, I try to process the exchange transaction, using #8.
             *  No reply ever comes... So I send a getRequest, it works, so I
             *  check the Nymbox, and my hash still matches... This means the exchange
             *  basket transaction ITSELF *never* processed. Why not? Because I would
             *  have definitely gotten a receipt in my Nymbox success OR fail.
             *  But since the Nymbox hash matches, that means the Exchange Basket transaction
             *  definitely NEVER processed, meaning the #8 is definitely still good.
             * 
             *  Next, I re-send my exchange basket request. But the ACT of re-sending
             *  it, takes the same basket exchange object, with trans 5, 6, and 7, and
             *  RE-CALLS the "exchange basket" message in the API, which creates a NEW
             *  transaction request using #9 (instead of #8) other using the exact same
             *  exchangeBasket object. Then it submits it!
             *  
             *  So question: WHEN do I get #8 back on my records again?? Only
             *  when I clawback #8 from the first failed message! (And NOT 5, 6, 
             *  and 7, since clawing those back would then force me to recreate
             *  the entire basket exchange object all over again, calling various
             *  API functions to do so, before I could call exchangeBasket again.)
             * 
             *  Since I'm about to re-send the message, I need to clawback the #8
             *  here and now. Then re-call exchangeBasket with the existing data, and
             *  it should work.
             * 
             *  But when I call OT_API_Msg_HarvestTransactionNumbers, what will
             *  happen? Let's just make sure...
             *  
               bReplyWasSuccess,        // RECEIVED server reply message: success. (Explicit.)
               bReplyWasFailure,        // RECEIVED server reply message: failure. (Explicit.)
               bTransactionWasSuccess,  // MESSAGE success, Transaction success. (Explicit.)
               bTransactionWasFailure)  // MESSAGE success, Transaction failure. (Explicit.)
             * 
             * 
               bReplyWasSuccess false, since reply never received.
               bReplyWasFailure false, since reply never received.
               bTransactionWasSuccess--false for same reason.
               bTransactionWasFailure--false for same reason
             * 
             * So the QUESTION IS, if I pass ALL FALSE values, for exchangeBasket,
             * WILL OT_API_Msg_HarvestTransactionNumbers ONLY clawback the #8, and
             * LEAVE the 5,6,7 intact ?  Is that what will actually happen ?
             * 
             * CORRECTION: I know for a FACT that the message's transaction was never
             * processed, because my nymbox would have a receipt either way. But no receipt,
             * meaning the server either never got my message, or it failed processing that
             * message before any of the transaction numbers would have been impacted.
             * IF THOSE CONDITIONS ARE BASICLALY THE SAME (and they must be, for messages
             * to be repeatable at all) then I can basically intrerpret that as if bReplyWasFailure=true.
             * Right? So how does the Harvesting code act in that situation?
             * 
             * Well it definitely harvests the opener... and the closers too, which I
             * want to suppress in the event (like here) where we want to re-try a message.
             * 
             */
            

//            if (!strServerHash.equals(strLocalHash))
//            {
//                
//                final int nGetNymbox = Utility.getNymboxLowLevel(theFunction.serverID, theFunction.nymID);
//
//                if ((nGetNymbox <= 0) && ((-1) != nGetNymbox))
//                {
//                    System.out.println("SendRequest: Failure: Utility.getNymboxLowLevel returned unexpected value: " + nGetNymbox);
//                    return null;
//                }
//                else if ((-1) == nGetNymbox) // we'll try re-syncing the request number, then try again.
//                {
//                    System.out.println("SendRequest: Failure: Utility.getNymboxLowLevel returned -1, even after syncing the request number successfully. (Giving up.)");
//                    return null;
//                }
//                
////                public static int Utility.getAndProcessNymbox(String serverID, String nymID, final int nRequestNumber, OTBool bFoundNymboxItem) 
//                        
//                // A. download it 
//                
//                
//                
//                
//                // B. SEE if the reply is there for the request number, then 
//                
//                
//                
//                
//                // C. process it. ... If the reply wasn't there, 
//                
//                
//                
//                
//                // D. THEN Harvest the transaction #s (for transaction messages)
//                
//                
//                
//                
//                // E and then re-try the message.
//                
//                
//                
//                
//                
//            } // (!strServerHash.equals(strLocalHash))
//            // ------------------------------------------------------------
//            else    // The hashes DO ALREADY match.  (may remove the "else" and just have this block here below the one above, so it happens no matter what.)
//            {
//                // ******************************************************************
//                // It's a TRANSACTION -- Harvest any necessary transaction numbers before
//                // re-trying the message (since the getRequest succeeded and the nymbox
//                // doesn't need updating, then we can re-try it--we might be back in 
//                // sync now, if it was a requestNumber issue.)
//                //
//                if (theFunction.nTransNumsNeeded > 0) 
//                {
//                    final String strRequestNum = Integer.toString(nGetRequest);
//
//                    if (null == strRequestNum)
//                    {
//                        System.out.println("IN SendRequest (" + IN_FUNCTION + 
//                                " ): ERROR: should never happen, strRequestNum is null.");
//                        return null; 
//                    }
//                    // -------------------------------------------
//                    final String strOriginalMsg = otapiJNI.OTAPI_Basic_GetSentMessage(strRequestNum, theFunction.serverID, theFunction.nymID);
//
//                    if (null == strOriginalMsg)
//                    {
//                        System.out.println("IN SendRequest (" + IN_FUNCTION + 
//                                " ): ERROR: unable to find my cached copy of the original message sent, with request number: " + strRequestNum);
//                        return null; 
//                    }
//                    // ----------------------------------------
//                    //
//                    /*
//                     * NOTE: Since my Nymbox hash already matches the one on the @getRequest (server reply)
//                     * and since the @getRequest was a success, then I'm going to RE-TRY the message.
//                     * 
//                     * But there are cases where I need to harvest some transaction numbers first, before I
//                     * re-try it. (Assuming it's a transaction...) Such as exchangeBasket, WHICH TRIGGERED ALL
//                     * THIS CODE.. it can re-use the closing transaction numbers on the re-tried message, but
//                     * it has to use a NEW number each time for the main opening #. This means, if he does not
//                     * harvest the opening number from the LAST TRY, it will be lost! That's why this harvest
//                     * call is here.
//                     * 
//                     */
//                    //
//                    /*
//                            const char * OT_API_GetSentMessage(const char * REQUEST_NUMBER);
//                            int OT_API_RemoveSentMessage(const char * REQUEST_NUMBER); // actually returns OT_BOOL
//                            void OT_API_FlushSentMessages(void);
//                     */
//                    // true  == no errors.
//                    // false == errors.
//                    //
//                    int nBool = otapiJNI.OTAPI_Basic_Msg_HarvestTransactionNumbers(strOriginalMsg, 
//                                                                           theFunction.nymID,
//                                                                           1,   // bHarvestingForRetry == true
//                                                                           0,   // bReplyWasSuccess,       // RECEIVED server reply: explicit success.
//                                                                           0,   // bReplyWasFailure,       // RECEIVED server reply: explicit failure.
//                                                                           0,   // bTransactionWasSuccess, // MESSAGE success, Transaction success. (Explicit.)
//                                                                           0);  // bTransactionWasFailure  // MESSAGE success, Transaction failure. (Explicit.)
//                    switch (nBool)
//                    {
//                        case (1):
//                            //   (The harvesting process completed successfully.)
//                            break;
//                        default:
//                            System.out.println("IN SendRequest (" + IN_FUNCTION + 
//                                    " ): ERROR: " + nBool + " was returned (expected true) while harvesting message with request number: " + strRequestNum + " and original message contents:\n\n" + strOriginalMsg + "\n");
//                            return null;                             
//                    }
//
//                } // IF it's a transaction, we just harvested any transaction numbers needing harvesting.
//                // ******************************************************************
//
//                // Since the getRequest was a SUCCESS, and Hash already matches,
//                // ===> I can go ahead and re-try the message!
//                //
//                // ----------------------------------------------
//                final String strReply2;
//                final int nRequestNum2 = OTAPI_Func.SendRequestLowLevel(theFunction, IN_FUNCTION); // <=========== SECOND TRY!!!!!!!!!!!!
//                switch(nRequestNum2)
//                {
//                    // ----------------------------------------------
//                    // NO NEED TO LOG HERE (These are already logged in the above call.)
//                    case (-1):  // Error, nothing sent.         
//                    case (0):   // NO error, but also, still: nothing sent.  
//                        return null; // (Reply is NOT expected from the server, obviously, for either of these.)
//                    // ----------------------------------------------
//                    case (1):   // Message sent successfully! (No request number returned except a generic '1'
//                                //  used by certain calls like checkServer, to indicate it sent successfully.)
//                    // NO break // ===> (Reply IS expected.)
//
//                    default:    // Message sent successfully! (ALL other request numbers.)
//                        strReply2 = Utility.ReceiveReplyLowLevel(IN_FUNCTION); // <=== Here we RECEIVE the REPLY...
//                        break;  // ===> (Reply IS expected.)
//                }
//                // ----------------------------------------------
//
//                if (null == strReply2) // talk about full circle!
//                {
//                    System.out.println("OTAPI_Func.SendRequest(2): Error calling OTAPI_Func.ReceiveReplyLowLevel during message re-try, for request numbers: " + nRequestNum + " and " + nRequestNum2 + ". (Giving up.)");
//                    return null;
//                }
//     
//                // -------------------------------------------------------
//                // (strReply2 is valid string by this point.)
//
//                final int nReplyMsgSuccess = otapiJNI.OTAPI_Basic_Message_GetSuccess(strReply2);
//
//                if (1 == nReplyMsgSuccess)
//                {
//                    return strReply2;  // <============================================  *** SUCCESS ***
//                }
//                else if (( 0) == nReplyMsgSuccess)
//                {
//                    System.out.println("OTAPI_Func.SendRequest(2) (" + IN_FUNCTION + " ): Message success == FALSE. (FYI. It *was* a good message though. But the request in it was denied.)");
//                    return null;
//                }
//                else if ((-1) == nReplyMsgSuccess)
//                {
//                    System.out.println("OTAPI_Func.SendRequest(2): Error calling OT_API_Message_GetSuccess on message:\n\n" + strReply2);
//                    return null;
//                }
//
//                System.out.println("OTAPI_Func.SendRequest(2): ERROR (SHOULD NEVER HAPPEN): calling OT_API_Message_GetSuccess on message:\n\n" +
//                        strReply2);
//
//                return null;
//            } // if the hashes already match.
//            // -------------------------------------------------------
//        } // if null == strReply
//        // ----------------------------------------------
//        else // strReply is NOT null...
//        {
//            //
//            
//            
//            
//            
//        }
//        // ----------------------------------------------
//        
//        
//              
//        // --------------------------------------------------------------------
//        /*
//         * -- How do we handle these various options?
//         * 
//         * 
//         */
//        
//        if (!Utility.isValid(strResponseMessage)) // null or empty string.
//        {
//        } // if strResponseMessage NOT VALID (above)
//        else
//        {   // else, if IS VALID...
//            
//        }
//        
//        
//        
//        
//        else if (1)
//        {
//            Utility.setLastReplyReceived(strResponseMessage);
//
//        /*
//         *    3. Returns the proper message, failed.
//         * 
//         * ===> This is a much better situation: WE HAVE A REPLY! The message failed, for whatever reason, fine,
//         *      so if there was a transaction, it may need to be re-sent in another message. But the new code
//         *      will fail MANY messages due to NymboxHash alone, so we can just look at it in the reply, 
//         *      and if it is different, then we again do the whole process above. (Except no need to search
//         *      the nymbox for my "lost reply" since in this case, I DO have the reply.)
//         */
//            
//            // getRequest
//
//            
//            
//        }
//        else if ()
//        {
//            Utility.setLastReplyReceived(strResponseMessage);
//
//        /*
//         *    4. Returns the proper message, succeeded.
//         *    5. Returns the proper message, succeeded: BUT Balance Agreement failed!
//         *    6. Returns the proper message, succeeded: AND Balance Agreement success, but TRANSACTION failed.
//         *    7. Returns the proper message, succeeded: AND Balance Agreement success, AND TRANSACTION succeeded.
//         * 
//         */
//        }
//        else if ()
//        {
//            
//        }
//        else
//        {
//            
//        }
//
////      System.out.println("DEBUGGING SendRequest " + strResponseMessage);
//
//        if ((null == strResponseMessage) ||
//            (strResponseMessage.length() < 10) ||
//                
//            (0 == otapiJNI.OTAPI_Basic_Message_GetSuccess(strResponseMessage))) {
//            
//            
//            
//            if (false == Utility.getRequestNumber(theFunction.serverID, theFunction.nymID)) {
//                System.out.println("IN SendRequest (" + IN_FUNCTION + " ): After first failure, Utility.getRequestNumber() failed as well. (I give up.) ");
//                return null;
//            }
//            
//            
//            
//            // getRequestNumber succeeded, so we'll try the Function call again...
//            else {
//                final int nRun2 = theFunction.Run(); // <===== SECOND ATTEMPT                
//                Utility.delay();
//                strResponseMessage = otapiJNI.OTAPI_Basic_PopMessageBuffer();
//                if ((null == strResponseMessage) || (strResponseMessage.length() < 10) ||
//                    (0 == otapiJNI.OTAPI_Basic_Message_GetSuccess(strResponseMessage))) {
//                    System.out.println("IN SendRequest (" + IN_FUNCTION + " ): Function retry failed, even after Utility.getRequestNumber() succeeded. (I give up.) ");
//                    return null;
//                }
//            }
//        }
////      System.out.println("DEBUGGING SendRequest " + strResponseMessage);
//
//        return strResponseMessage;
//    }

    
    
    
    // ---------------------------------------------------------------------------------
    
    /*
    public static String SendTransaction(OTAPI_Func theFunction, String IN_FUNCTION)  {

        boolean bSure = true;

        if (otapiJNI.OTAPI_Basic_GetNym_TransactionNumCount(theFunction.serverID, theFunction.nymID)    // If the current trans# count is LESS than what's needed...
                <
                (theFunction.nTransNumsNeeded > Configuration.getNbrTransactionCount() ?        //theFunction needs more than my normal minimum in my configuration
                    theFunction.nTransNumsNeeded  : Configuration.getNbrTransactionCount())) {  // Therefore I will grab that many, instead of however many I would normally grab.
            System.out.println("OTAPI_Func.SendTransaction: I don't have enough transaction numbers to perform this transaction. Grabbing more now...");
            int configTxnCount = Configuration.getNbrTransactionCount();
            Configuration.setNbrTransactionCount((theFunction.nTransNumsNeeded > configTxnCount) ? theFunction.nTransNumsNeeded : configTxnCount);
            bSure = Utility.getTransactionNumbers(theFunction.serverID, theFunction.nymID);
            Configuration.setNbrTransactionCount(configTxnCount);
        }
        if (!bSure || otapiJNI.OTAPI_Basic_GetNym_TransactionNumCount(theFunction.serverID, theFunction.nymID) < theFunction.nTransNumsNeeded) {
            System.out.println("In " + IN_FUNCTION + " , failed to get transaction numbers, OT_API_GetNym_TransactionNumCount:" +
                    otapiJNI.OTAPI_Basic_GetNym_TransactionNumCount(theFunction.serverID, theFunction.nymID));
            return null;
        }
        // --------------------------------------
        // BY THIS POINT, I know that I have enough transaction numbers.
        // (Although I might NOT have checked my Nymbox yet, so sync issues are still possible.)
        // I also ran the function, and if it failed, then I successfully ran getRequestNumber(),
        // and then the function again -- and this time it worked (or I would have returned already.)
        // At least, the message was success. Now let's check the balance agreement, and the transaction itself.
        //
        String strResponseMessage = null;

        strResponseMessage = OTAPI_Func.SendRequest(theFunction, IN_FUNCTION);  //<============ FIRST ATTEMPT!!!!!

        if (null == strResponseMessage || strResponseMessage.length() < 10) {
            System.out.print("OTAPI_Func.SendTransaction: OTAPI_Func.SendRequest failed. (I give up.)");
            return null;
        }

        // *********************************************************************************
        // Was balance agreement successful?
        // (Just because the message was successful, doesn't mean the transaction
        //  was successful, or the balance agreement...)

        boolean bBalanceAgreement =
                (otapiJNI.OTAPI_Basic_Message_GetBalanceAgreementSuccess(
                    theFunction.serverID,
                    theFunction.nymID,
                    theFunction.accountID,
                    strResponseMessage) == 1) ? true : false;
        if (false == bBalanceAgreement) // Failure
        {
        // Hmm -- the message was a success, but the BALANCE AGREEMENT has failed.
        // Most likely culprit? There are probably nymbox notices regarding transaction
        // numbers I added, but missed a server reply confirming. Or, perhaps a market
        // offer expired, and one of my transaction #s was closed, but I don't know it
        // yet, because I haven't seen the nymbox notice.
        // EITHER WAY, this is the time to check the nymbox and process whatever is in
        // there. Then my balance agreement will probably start working again.

            //
            // Maybe we have an old Inbox or something.
            //
            if (false == Utility.getIntermediaryFiles(theFunction.serverID,
                    theFunction.nymID, theFunction.accountID))
            {
                System.out.println(IN_FUNCTION + " getIntermediaryFiles returned false. (I give up.)");
                return null;
            }




            // TODO!!  SECURITY:  This is where a GOOD CLIENT (vs. a test client)
            // will verify these intermediary files against your LAST SIGNED RECEIPT,
            // using OT_API_VerifySomethingorother().
            // See verifyFiles() at the bottom of this file.
            // Add some kind of warning Modal Dialog here, since it's actually
            // normal for a NEW account (never had any receipts yet.) But for
            // any other account, this should ALWAYS VERIFY!
            //
            // Another note: this should happen INSIDE the getIntermediaryFiles call itself,
            // and all similar calls.  You simply should not download those files,
            // without verifying them also. Otherwise you could end up signing
            // a future bad receipt, based on malicious, planted intermediary files.



            // In case any notices have come in about new transaction #s already accepted,
            // or about old transaction #s that expired lawfully.
            if (-1 == Utility.getAndProcessNymbox(theFunction.serverID, // This also grabs all the box receipts.
                    theFunction.nymID))
            {
                System.out.println(IN_FUNCTION + ", Utility.getAndProcessNymbox returned false. (I give up.)");
                return null;
            }
            // ----------------------------------------
            // Now we'll try again..
            //
            otapiJNI.OTAPI_Basic_FlushMessageBuffer();
            System.out.println("In " + IN_FUNCTION + ", serverID:"+theFunction.serverID+" nymID:"+theFunction.nymID);


            theFunction.Run();  // <====== TRYING AGAIN (THIRD TIME)


            Utility.delay();
            strResponseMessage = otapiJNI.OTAPI_Basic_PopMessageBuffer();

            // Balance agreement STILL FAILURE <=========
            //

            if (strResponseMessage == null || strResponseMessage.length() < 10) {
                System.out.println("In " + IN_FUNCTION + ", strResponseMessage is null after retry after balance agreement failure. (I give up.)");
                return null;
            } // NOTE: I could check the message here for failure, but the below call would return 0 in
              // that case as well anyway. Since we already went through the request number stuff above,
              // there's no point / need in repeating it.
              //
            else if (0 == otapiJNI.OTAPI_Basic_Message_GetBalanceAgreementSuccess( // Failure
                    theFunction.serverID,
                    theFunction.nymID,
                    theFunction.accountID,
                    strResponseMessage))
            {
                System.out.println(IN_FUNCTION + " strResponseMessage is still FAILURE after retry after balance agreement failure. (I give up.)");
                return null;
            }
            System.out.println(IN_FUNCTION + " after balance agreement retry, (success on balance agreement.)");
        }

        // *********************************************************************************
        // Was transaction successful?

        if (0 == otapiJNI.OTAPI_Basic_Message_GetTransactionSuccess( // Failure.
                theFunction.serverID,
                theFunction.nymID,
                theFunction.accountID,
                strResponseMessage))
        {
            System.out.println(IN_FUNCTION + " strResponseMessage the TRANSACTION reports FAILURE (though balance agreement was successful.) (I give up.)");
            return null;
        }

        return strResponseMessage;
    }
    
    */
    
    
    

} // class OTAPI_Func
