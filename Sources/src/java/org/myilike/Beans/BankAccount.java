/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.myilike.Beans;

import java.io.Serializable;

/**
 * 银行帐号数据bean
 * @author Akers
 */
public class BankAccount implements Serializable
{
    private int bankAccountID;
	private String bankAccount;
	private String bankName;
	private String bankAccountName;
	private double bankAccountBalance;
	private double bankAccountCashBalance;
	private int employeeID;
    private String employeeName;
	private int bankAccountCateID;
    private String 	bankAccountCateName;

    public String getBankAccount() {
        return bankAccount;
    }

    public double getBankAccountBalance() {
        return bankAccountBalance;
    }

    public double getBankAccountCashBalance() {
        return bankAccountCashBalance;
    }

    public int getBankAccountCateID() {
        return bankAccountCateID;
    }

    public String getBankAccountCateName() {
        return bankAccountCateName;
    }

    public int getBankAccountID() {
        return bankAccountID;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public String getBankName() {
        return bankName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setBankAccountBalance(double bankAccountBalance) {
        this.bankAccountBalance = bankAccountBalance;
    }

    public void setBankAccountCashBalance(double bankAccountCashBalance) {
        this.bankAccountCashBalance = bankAccountCashBalance;
    }

    public void setBankAccountCateID(int bankAccountCateID) {
        this.bankAccountCateID = bankAccountCateID;
    }

    public void setBankAccountCateName(String bankAccountCateName) {
        this.bankAccountCateName = bankAccountCateName;
    }

    public void setBankAccountID(int bankAccountID) {
        this.bankAccountID = bankAccountID;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

   

}
