/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.myilike.Beans;

/**
 * 供应商数据对象
 * @author Akers
 */
public class Supplier
{
    //完整内容 	supplierID 	supplierName
    private int supplierID;
    private String supplierName;

    public int getSupplierID()
    {
        return supplierID;
    }

    public String getSupplierName()
    {
        return supplierName;
    }

    public void setSupplierID(int supplierID)
    {
        this.supplierID = supplierID;
    }

    public void setSupplierName(String supplierName)
    {
        this.supplierName = supplierName;
    }
    
    
}
