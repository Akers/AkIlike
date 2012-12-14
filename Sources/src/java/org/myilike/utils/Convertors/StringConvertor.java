/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.myilike.utils.Convertors;

/**
 *
 * @author Akers
 */
public class StringConvertor 
{
    //重编码字符串
    public static String getRecodeStr(String str, String charSetName) 
    { 
        try 
        { 
            return new String(str.getBytes("ISO8859-1"), charSetName);
        } 
        catch(Exception   e) 
        { 
            e.getMessage(); 
        } 
        return   "null"; 
    } 
}
