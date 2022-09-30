package dev.vnpay.model;

public class LogATGModel {
    private String TIME;
    private String USER;
    private String PAN;
    private String POSEntryMode;
    private String CVM;
    private String ErrorDescription;
    private String TransactionAmount;
    private String Track2;
    private String PINBlock;
    private String TransactionKSN;
    private String MID;
    private String TID;
    private String BankCode;
    private String CurrencyCode;
    private String CardHolderName;
    private String BillNumber;
    private String ExpireDate;
    private String AppName;
    private String AppID;
    private String TC;
    private String AppVersion;
    private String IsEasyPayment;
    private String RequestDateTime;
    private String RequestID;

    public LogATGModel() {
    }

    public LogATGModel(String TIME, String USER, String PAN, String POSEntryMode, String CVM, String errorDescription, String transactionAmount, String track2, String PINBlock, String transactionKSN, String MID, String TID, String bankCode, String currencyCode, String cardHolderName, String billNumber, String expireDate, String appName, String appID, String TC, String appVersion, String isEasyPayment, String requestDateTime, String requestID) {
        this.TIME = TIME;
        this.USER = USER;
        this.PAN = PAN;
        this.POSEntryMode = POSEntryMode;
        this.CVM = CVM;
        ErrorDescription = errorDescription;
        TransactionAmount = transactionAmount;
        Track2 = track2;
        this.PINBlock = PINBlock;
        TransactionKSN = transactionKSN;
        this.MID = MID;
        this.TID = TID;
        BankCode = bankCode;
        CurrencyCode = currencyCode;
        CardHolderName = cardHolderName;
        BillNumber = billNumber;
        ExpireDate = expireDate;
        AppName = appName;
        AppID = appID;
        this.TC = TC;
        AppVersion = appVersion;
        IsEasyPayment = isEasyPayment;
        RequestDateTime = requestDateTime;
        RequestID = requestID;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public String getPAN() {
        return PAN;
    }

    public void setPAN(String PAN) {
        this.PAN = PAN;
    }

    public String getPOSEntryMode() {
        return POSEntryMode;
    }

    public void setPOSEntryMode(String POSEntryMode) {
        this.POSEntryMode = POSEntryMode;
    }

    public String getCVM() {
        return CVM;
    }

    public void setCVM(String CVM) {
        this.CVM = CVM;
    }

    public String getErrorDescription() {
        return ErrorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        ErrorDescription = errorDescription;
    }

    public String getTransactionAmount() {
        return TransactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        TransactionAmount = transactionAmount;
    }

    public String getTrack2() {
        return Track2;
    }

    public void setTrack2(String track2) {
        Track2 = track2;
    }

    public String getPINBlock() {
        return PINBlock;
    }

    public void setPINBlock(String PINBlock) {
        this.PINBlock = PINBlock;
    }

    public String getTransactionKSN() {
        return TransactionKSN;
    }

    public void setTransactionKSN(String transactionKSN) {
        TransactionKSN = transactionKSN;
    }

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public String getBankCode() {
        return BankCode;
    }

    public void setBankCode(String bankCode) {
        BankCode = bankCode;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public String getCardHolderName() {
        return CardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        CardHolderName = cardHolderName;
    }

    public String getBillNumber() {
        return BillNumber;
    }

    public void setBillNumber(String billNumber) {
        BillNumber = billNumber;
    }

    public String getExpireDate() {
        return ExpireDate;
    }

    public void setExpireDate(String expireDate) {
        ExpireDate = expireDate;
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String appName) {
        AppName = appName;
    }

    public String getAppID() {
        return AppID;
    }

    public void setAppID(String appID) {
        AppID = appID;
    }

    public String getTC() {
        return TC;
    }

    public void setTC(String TC) {
        this.TC = TC;
    }

    public String getAppVersion() {
        return AppVersion;
    }

    public void setAppVersion(String appVersion) {
        AppVersion = appVersion;
    }

    public String getIsEasyPayment() {
        return IsEasyPayment;
    }

    public void setIsEasyPayment(String isEasyPayment) {
        IsEasyPayment = isEasyPayment;
    }

    public String getRequestDateTime() {
        return RequestDateTime;
    }

    public void setRequestDateTime(String requestDateTime) {
        RequestDateTime = requestDateTime;
    }

    public String getRequestID() {
        return RequestID;
    }

    public void setRequestID(String requestID) {
        RequestID = requestID;
    }

    @Override
    public String toString() {
        return "LogATGModel{" +
                "TIME='" + TIME + '\'' +
                ", USER='" + USER + '\'' +
                ", PAN='" + PAN + '\'' +
                ", POSEntryMode='" + POSEntryMode + '\'' +
                ", CVM='" + CVM + '\'' +
                ", ErrorDescription='" + ErrorDescription + '\'' +
                ", TransactionAmount='" + TransactionAmount + '\'' +
                ", Track2='" + Track2 + '\'' +
                ", PINBlock='" + PINBlock + '\'' +
                ", TransactionKSN='" + TransactionKSN + '\'' +
                ", MID='" + MID + '\'' +
                ", TID='" + TID + '\'' +
                ", BankCode='" + BankCode + '\'' +
                ", CurrencyCode='" + CurrencyCode + '\'' +
                ", CardHolderName='" + CardHolderName + '\'' +
                ", BillNumber='" + BillNumber + '\'' +
                ", ExpireDate='" + ExpireDate + '\'' +
                ", AppName='" + AppName + '\'' +
                ", AppID='" + AppID + '\'' +
                ", TC='" + TC + '\'' +
                ", AppVersion='" + AppVersion + '\'' +
                ", IsEasyPayment='" + IsEasyPayment + '\'' +
                ", RequestDateTime='" + RequestDateTime + '\'' +
                ", RequestID='" + RequestID + '\'' +
                '}';
    }
}
