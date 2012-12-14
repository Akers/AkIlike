/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.myilike.Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Akers
 */
public class User implements Serializable 
{
    private String userID;
    private String userPWD;
    private String userName;
    
    public User()
    {
        
    }
    
    
    public static User getUser(String userID)
    {
        //从数据库中取得用户ID为传入参数的用户
        return new User();
    }
    
    public static List getUsers()
    {
        //从数据库中取得所有的管理员信息，存在一个ArrayList中
        List users = new ArrayList();
        return users;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the userPWD
     */
    public String getUserPWD() {
        return userPWD;
    }

    /**
     * @param userPWD the userPWD to set
     */
    public void setUserPWD(String userPWD) {
        this.userPWD = userPWD;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
