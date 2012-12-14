/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.myilike.Beans;

/**
 * 客户数据对象
 * @author Akers
 */
public class Customer
{
    //customerID 	customerName
    private int customerID;
    private String customerName;

    public int getCustomerID()
    {
        return customerID;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerID(int customerID)
    {
        this.customerID = customerID;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }
    
}
