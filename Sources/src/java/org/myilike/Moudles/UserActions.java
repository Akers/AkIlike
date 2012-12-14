/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.myilike.Moudles;

import org.myilike.Adapters.DB.DataBase;
import org.myilike.Beans.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.myilike.Adapters.DB.DBAdapter;

/**
 *
 * @author Akers
 */
public class UserActions 
{
    public static final int GROUP_EMPLOYEE = 11;
    
    //0 登录成功 大于0为已知错误代码
    public static int userLogin(User user)
    {
        DataBase db = DataBase.getDB();
        String sqlStr = "select * from admin_tb where adminID = '" + user.getUserID() + "'";
        ResultSet rs = db.query(sqlStr);
        try {
            if(rs.next())//账户存在
            {	
                String pwd = rs.getString("adminPswd");
                String adminName = rs.getString("adminName");
                rs.close();
                if(pwd.equals(user.getUserPWD()))
                {
                    //正常登录
                    user.setUserName(adminName);
                    return 0;
                }
                else
                {
                    //密码错误
                    return 101;
                }
            }else{//帐号不存在
                rs.close();
                return 201;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static ArrayList<User> getUserNameList(int userGruop)
    {
        DataBase db = DBAdapter.getDB();
        ArrayList<User> users = new ArrayList<User>();
        ResultSet rs = null;
        if(userGruop == GROUP_EMPLOYEE)
        {
            String sql = "SELECT employeeID, employeeName FROM employee_tb";
            rs = db.query(sql);
            try 
            {
                while(rs.next())
                {
                    User user = new User();
                    user.setUserID(rs.getString("employeeID"));
                    user.setUserName(rs.getString("employeeName"));
                    users.add(user);
                }
                rs.close();
                db.close();
                return users;
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(UserActions.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        try 
        {
            rs.close();
            db.close();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(UserActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
}
