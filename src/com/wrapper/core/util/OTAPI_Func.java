/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrapper.core.util;
import com.wrapper.core.jni.otapi;

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
        GET_NYM_MARKET_OFFERS,
        GET_MARKET_OFFERS,
        GET_MARKET_RECENT_TRADES,
        GET_MINT,
        QUERY_ASSET_TYPES;  //; is optional
        }

    public FT funcType;
    // -------------------------
    public String   serverID;
    public String   nymID, nymID2;
    public String   assetID, assetID2;
    public String   accountID;
    public String   accountID2;
    public String   basket;
    public String   strData, strData2, strData3, strData4;
    public boolean  bBool;    
    public int      nData;
    public int      nTransNumsNeeded;
    
               
    public OTAPI_Func(FT theType, String serverID, String nymID, String assetTypeID, String assetAccountID, String currencyTypeID, String currencyAcctID,
            String scale, String minIncrement, String quantity, String price, boolean bSelling) {
        
       if (null == serverID)
           System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as serverID");
       if (null == nymID)	
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as nymID");	
       if (null == assetTypeID)	
           System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as assetTypeID"); 	 	
        if (null == assetAccountID)	 	
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as assetAccountID"); 	 	
        if (null == currencyTypeID) 	 	
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as currencyTypeID"); 	 	
        if (null == currencyAcctID) 	 	
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as currencyAcctID");	 	
      if (null == scale) 	 	
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as scale");	 	
       if (null == minIncrement)	 	
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as minIncrement");	 	
        if (null == quantity)	 	
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as quantity");	 	
        if (null == price)	 	
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
    }
        

    public OTAPI_Func(FT theType, String serverID, String nymID, String assetID, 
            String basket, String accountID, boolean bBool, int nTransNumsNeeded) {
        
        if (null == serverID)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as serverID");	
        if (null == nymID)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as nymID");
        if (null == assetID)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as assetID");
        if (null == accountID)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as accountID");
        if (null == basket)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as basket");
        
        this.funcType   = theType;
        this.serverID   = serverID;
        this.nymID      = nymID;
        this.nTransNumsNeeded = nTransNumsNeeded;
        this.bBool      = bBool;
        this.assetID    = assetID;
        this.basket     = basket;
        this.accountID  = accountID;
    }
    
    
    public OTAPI_Func(FT theType, String serverID, String nymID) {
        
        if (null == serverID)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as serverID");	
        if (null == nymID)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as nymID");

        this.funcType   = theType;
        this.serverID   = serverID;
        this.nymID      = nymID;
        this.nTransNumsNeeded = 1;
        this.bBool      = false;
    }

    public OTAPI_Func(FT theType, String serverID, String nymID, String strParam, String strData) {
        if (null == serverID)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as serverID");	
        if (null == nymID)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as nymID");

        if (null == strParam)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as strParam");	
        if (null == strData)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as strData");

        this.funcType   = theType;
        this.serverID   = serverID;
        this.nymID      = nymID;
        this.nTransNumsNeeded = 1;
        this.bBool      = false;
                
        switch(theType)
        {
            case CANCEL_MARKET_OFFER:
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
    public OTAPI_Func(FT theType, String serverID, String nymID, String accountID, String strParam, String strData, String strData2) {
    
        if (null == serverID)	
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as serverID");
        if (null == nymID)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as nymID");
        if (null == accountID)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as accountID");
	
        if (null == strParam)	
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as strParam");	
        if (null == strData)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as strData");	
        if (null == strData2)	
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as strData2");
	
        this.funcType   = theType;
        this.serverID   = serverID;
        this.nymID      = nymID;
        this.nTransNumsNeeded = 1;
        this.bBool      = false;
        this.accountID  = accountID;
        this.strData    = strData;
        this.strData2   = strData2;
        
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
    public OTAPI_Func(FT theType, String serverID, String nymID, String strParam) {
        if (null == serverID)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as serverID");	
        if (null == nymID)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as nymID");

        if (null == strParam)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as strParam");	

        this.funcType   = theType;
        this.serverID   = serverID;
        this.nymID      = nymID;
        this.nTransNumsNeeded = 1;
        this.bBool      = false;
        
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
    public OTAPI_Func(FT theType, String serverID, String nymID, String nymID2, String strData, String strData2) {
        if (null == serverID)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as serverID");	
        if (null == nymID)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as nymID");
        if (null == nymID2)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as nymID2");

        if (null == strData)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as strData");	
        if (null == strData2)
            System.out.print("ERROR! NULL passed to OTAPI_Func.OTAPI_Func() as strData2");	

        this.funcType   = theType;
        this.serverID   = serverID;
        this.nymID      = nymID;
        this.nymID2     = nymID2;
        this.nTransNumsNeeded = 1;
        this.bBool      = false;
        
        this.strData    = strData;
        this.strData2   = strData2;
        
        switch(theType) {
            case SEND_USER_MESSAGE:
                break;                
            default:
                System.out.print("ERROR! WRONG TYPE passed to OTAPI_Func.OTAPI_Func() ERROR!!!!!!");
                break;
        }
    }
    
    private void Run() {
        switch (this.funcType) {
            case CHECK_USER:
                otapi.OT_API_checkUser(this.serverID, this.nymID, this.nymID2);
                break;
            case CREATE_USER_ACCT:
                otapi.OT_API_createUserAccount(this.serverID, this.nymID);
                break;
            case DELETE_USER_ACCT:
                otapi.OT_API_deleteUserAccount(this.serverID, this.nymID);
                break;
            case SEND_USER_MESSAGE:
                otapi.OT_API_sendUserMessage(this.serverID, this.nymID, this.nymID2, this.strData, this.strData2);
                break;
            case GET_NYM_MARKET_OFFERS:
                otapi.OT_API_getNym_MarketOffers(this.serverID, this.nymID);
                break;
            case CREATE_ASSET_ACCT:
                otapi.OT_API_createAssetAccount(this.serverID, this.nymID, this.assetID);
                break;
            case DELETE_ASSET_ACCT:
                otapi.OT_API_deleteAssetAccount(this.serverID, this.nymID, this.accountID);
                break;
            case EXCHANGE_BASKET:
                otapi.OT_API_exchangeBasket(this.serverID, this.nymID, this.assetID, this.basket, this.bBool == true ? 1 : 0);
                break;
            case GET_CONTRACT:
                otapi.OT_API_getContract(this.serverID, this.nymID, this.assetID);
                break;
            case QUERY_ASSET_TYPES:
                otapi.OT_API_queryAssetTypes(this.serverID, this.nymID, this.strData);
                break;
            case GET_MINT:
                otapi.OT_API_getMint(this.serverID, this.nymID, this.assetID);
                break;
            case ISSUE_ASSET_TYPE:
                otapi.OT_API_issueAssetType(this.serverID, this.nymID, this.strData);
                break;
            case ISSUE_BASKET:
                otapi.OT_API_issueBasket(this.serverID, this.nymID, this.basket);
                break;
            case EXCHANGE_CASH:
                otapi.OT_API_exchangePurse(this.serverID, this.assetID, this.nymID, this.strData);
                break;                
            case CANCEL_MARKET_OFFER:
                otapi.OT_API_cancelMarketOffer(this.serverID, this.nymID, this.accountID, this.strData);
                break;
            case PROCESS_INBOX:
                otapi.OT_API_processInbox(this.serverID, this.nymID, this.accountID, this.strData);
                break;
            case DEPOSIT_CASH:
                otapi.OT_API_notarizeDeposit(this.serverID, this.nymID, this.accountID, this.strData);
                break;
            case DEPOSIT_CHEQUE:
                otapi.OT_API_depositCheque(this.serverID, this.nymID, this.accountID, this.strData);
                break;
            case WITHDRAW_CASH:
                otapi.OT_API_notarizeWithdrawal(this.serverID, this.nymID, this.accountID, this.strData);
                break;
            case WITHDRAW_VOUCHER:
                otapi.OT_API_withdrawVoucher(this.serverID, this.nymID, this.accountID, this.nymID2, this.strData, this.strData2);
                break;  
            case SEND_TRANSFER:
                otapi.OT_API_notarizeTransfer(this.serverID, this.nymID, this.accountID, this.accountID2,   this.strData, this.strData2); // amount and note, for the last two.
                break;
            case GET_MARKET_LIST:
                otapi.OT_API_getMarketList(this.serverID, this.nymID);
                break;
            case GET_MARKET_OFFERS:
                otapi.OT_API_getMarketOffers(this.serverID, this.nymID, this.strData, this.strData2);
                break;
            case GET_MARKET_RECENT_TRADES:
                otapi.OT_API_getMarketRecentTrades(this.serverID, this.nymID, this.strData);
                break;
            case CREATE_MARKET_OFFER:
                otapi.OT_API_issueMarketOffer(this.serverID, this.nymID, this.assetID, this.accountID, this.assetID2, this.accountID2, 
                        this.strData, this.strData2, this.strData3, this.strData4, this.bBool ? 1 : 0);
                break;
            default:
                System.out.println("ERROR! OTAPI_Func.Send() activated, with bad function type!!!! ERROR!");
                break;
        }
    }
    

    // Generic function for any server message.
    // If you are doing a transaction message, then use SendTransaction instead.
    // (It will call this one.)
    // Returns:  null if failure, or the server's response message if success.
    //
    public static String SendRequest(OTAPI_Func theFunction, String IN_FUNCTION)  {

        String strResponseMessage = null;
        
        otapi.OT_API_FlushMessageBuffer();
        theFunction.Run(); // <===== FIRST ATTEMPT            
        Utility.delay();
        strResponseMessage = otapi.OT_API_PopMessageBuffer();

//      System.out.println("DEBUGGING SendRequest " + strResponseMessage);

        if ((null == strResponseMessage) || 
            (strResponseMessage.length() < 10) ||
            (0 == otapi.OT_API_Message_GetSuccess(strResponseMessage))) {
            if (false == Utility.getRequestNumber(theFunction.serverID, theFunction.nymID)) {
                System.out.println("IN SendRequest (" + IN_FUNCTION + " ): After first failure, Utility.getRequestNumber() failed as well. (I give up.) ");
                return null;
            }
            // getRequestNumber succeeded, so we'll try the Function call again...
            else {
                theFunction.Run(); // <===== SECOND ATTEMPT
                Utility.delay();
                strResponseMessage = otapi.OT_API_PopMessageBuffer();
                if ((null == strResponseMessage) || (strResponseMessage.length() < 10) ||
                    (0 == otapi.OT_API_Message_GetSuccess(strResponseMessage))) {
                    System.out.println("IN SendRequest (" + IN_FUNCTION + " ): Function retry failed, even after Utility.getRequestNumber() succeeded. (I give up.) ");
                    return null;
                }
            }
        }
//      System.out.println("DEBUGGING SendRequest " + strResponseMessage);            

        return strResponseMessage;
    }
    
    public static String SendTransaction(OTAPI_Func theFunction, String IN_FUNCTION)  {
        
        String strResponseMessage = null;
        boolean bSure = true;
        
        if (otapi.OT_API_GetNym_TransactionNumCount(theFunction.serverID, theFunction.nymID)    // If the current trans# count is LESS than what's needed...
                <                                                                               
                (theFunction.nTransNumsNeeded > Configuration.getNbrTransactionCount() ?        //theFunction needs more than my normal minimum in my configuration
                    theFunction.nTransNumsNeeded  : Configuration.getNbrTransactionCount())) {  // Therefore I will grab that many, instead of however many I would normally grab.
            System.out.println("OTAPI_Func.SendTransaction: I don't have enough transaction numbers to perform this transaction. Grabbing more now...");
            int configTxnCount = Configuration.getNbrTransactionCount();
            Configuration.setNbrTransactionCount((theFunction.nTransNumsNeeded > configTxnCount) ? theFunction.nTransNumsNeeded : configTxnCount);
            bSure = Utility.getTransactionNumbers(theFunction.serverID, theFunction.nymID);
            Configuration.setNbrTransactionCount(configTxnCount);
        }
        if (!bSure || otapi.OT_API_GetNym_TransactionNumCount(theFunction.serverID, theFunction.nymID) < theFunction.nTransNumsNeeded) {
            System.out.println("IN " + IN_FUNCTION + " , failed to get transaction numbers, OT_API_GetNym_TransactionNumCount:" + 
                    otapi.OT_API_GetNym_TransactionNumCount(theFunction.serverID, theFunction.nymID));
            return null;
        }
        // --------------------------------------
        // BY THIS POINT, I know that I have enough transaction numbers. 
        // (Although I might NOT have checked my Nymbox yet, so sync issues are still possible.)
        // I also ran the function, and if it failed, then I successfully ran getRequestNumber(),
        // and then the function again -- and this time it worked (or I would have returned already.)
        // At least, the message was success. Now let's check the balance agreement, and the transaction itself.
        //
        strResponseMessage = OTAPI_Func.SendRequest(theFunction, IN_FUNCTION);

        if (null == strResponseMessage || strResponseMessage.length() < 10) {
            System.out.print("OTAPI_Func.SendTransaction: OTAPI_Func.SendRequest failed. (I give up.)");
            return null;
        }

        // *********************************************************************************
        // Was balance agreement successful?
        // (Just because the message was successful, doesn't mean the transaction
        //  was successful, or the balance agreement...)

        boolean bBalanceAgreement = 
                (otapi.OT_API_Message_GetBalanceAgreementSuccess(
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
            if (-1 == Utility.getAndProcessNymbox(theFunction.serverID,
                    theFunction.nymID)) 
            {
                System.out.println(IN_FUNCTION + ", Utility.getAndProcessNymbox returned false. (I give up.)");
                return null;
            }
            // ----------------------------------------
            // Now we'll try again..
            //
            otapi.OT_API_FlushMessageBuffer();
            System.out.println("In " + IN_FUNCTION + ", serverID:"+theFunction.serverID+" nymID:"+theFunction.nymID);
            theFunction.Run();  // <====== TRYING AGAIN (THIRD TIME)
            Utility.delay();
            strResponseMessage = otapi.OT_API_PopMessageBuffer();

            // Balance agreement STILL FAILURE <=========
            //

            if (strResponseMessage == null || strResponseMessage.length() < 10) {
                System.out.println("In " + IN_FUNCTION + ", strResponseMessage is null after retry after balance agreement failure. (I give up.)");
                return null;
            } // NOTE: I could check the message here for failure, but the below call would return 0 in
              // that case as well anyway. Since we already went through the request number stuff above,
              // there's no point / need in repeating it.
              //
            else if (0 == otapi.OT_API_Message_GetBalanceAgreementSuccess( // Failure
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

        if (0 == otapi.OT_API_Message_GetTransactionSuccess( // Failure.
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

} // class OTAPI_Func

