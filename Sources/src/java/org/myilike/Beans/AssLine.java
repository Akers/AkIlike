package org.myilike.Beans;

import java.io.Serializable;

/**
 * 现金流数据对象模型
 * @author Akers
 * 
 */
public class AssLine implements Serializable
{
    private int bankAccountAssLineID;
    private String bankAccountAssLineNum;
    private double amount;
    private boolean isAccounted;
    private boolean isTransactionType;
    private Remark bankAccountAssLineRemark;
    private String bankAccountAssLIneDate;
    private String customerName;
    private String supplierName;
    private String bankAccountName;
    private String bankAccount;
    private int customerID;
    private int supplierID;
    private int bankAccountID;

    public int getBankAccountAssLineID() {
        return bankAccountAssLineID;
    }

    public String getBankAccountAssLineNum() {
        return bankAccountAssLineNum;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isIsAccounted() {
        return isAccounted;
    }

    public boolean isIsTransactionType() {
        return isTransactionType;
    }

    public Remark getBankAccountAssLineRemark() {
        return bankAccountAssLineRemark;
    }

    public String getBankAccountAssLIneDate() {
        return bankAccountAssLIneDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public int getBankAccountID() {
        return bankAccountID;
    }

    public void setBankAccountAssLineID(int bankAccountAssLineID) {
        this.bankAccountAssLineID = bankAccountAssLineID;
    }

    public void setBankAccountAssLineNum(String bankAccountAssLineNum) {
        this.bankAccountAssLineNum = bankAccountAssLineNum;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setIsAccounted(boolean isAccounted) {
        this.isAccounted = isAccounted;
    }

    public void setIsTransactionType(boolean isTransactionType) {
        this.isTransactionType = isTransactionType;
    }

    public void setBankAccountAssLineRemark(Remark bankAccountAssLineRemark) {
        this.bankAccountAssLineRemark = bankAccountAssLineRemark;
    }

    public void setBankAccountAssLIneDate(String bankAccountAssLIneDate) {
        this.bankAccountAssLIneDate = bankAccountAssLIneDate;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public void setBankAccountID(int bankAccountID) {
        this.bankAccountID = bankAccountID;
    }

    public String getBankAccount()
    {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount)
    {
        this.bankAccount = bankAccount;
    }


    
}
