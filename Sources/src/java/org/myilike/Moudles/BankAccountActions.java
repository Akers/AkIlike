/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.myilike.Moudles;

import org.myilike.Adapters.DB.DataBase;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.myilike.Beans.BankAccount;
import org.myilike.Adapters.DB.DBAdapter;

/**
 * BankAccountActions
 * 银行帐号操作模型
 * @author Akers
 */
public class BankAccountActions 
{
    private DataBase db;
    
    
    public BankAccountActions()
    {
       db = DataBase.getDB();
    }
    
    /**
     * 列出所有帐号的信息
     * @return ArrayList<BankAccount> 
     */
    public ArrayList<BankAccount> getAccountsList()
    {
        ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
        String sql = "select ba.bankAccountID, ba.bankAccount, ba.bankName, "
                          + "ba.bankAccountName, ba.bankAccountBalance, ba.bankAccountCashBalance, "
                          + "ba.employeeID, ba.bankAccountCateID, bc.bankAccountCateName,"
                          + "e.employeeName "
                          + "from ((bankaccount_tb as ba "
                          + "left join bankaccountcate_tb as bc on ba.bankAccountCateID = bc.bankAccountCateID) "
                          + "left join employee_tb as e on ba.employeeID = e.employeeID)" ;
 
        ResultSet rs = db.query(sql);
        try 
        {
            while(rs.next())
            {
                BankAccount ba = new BankAccount();
                ba.setBankAccountID(rs.getInt("bankAccountID"));
                ba.setBankAccount(rs.getString("bankAccount"));
                ba.setBankAccountName(rs.getString("bankAccountName"));
                ba.setBankAccountBalance(rs.getDouble("bankAccountBalance"));
                ba.setBankAccountCashBalance(rs.getDouble("bankAccountCashBalance"));
                ba.setBankAccountCateID(rs.getInt("bankAccountCateID"));
                ba.setBankAccountCateName(rs.getString("bankAccountCateName"));
                ba.setBankName(rs.getString("bankName"));
                ba.setEmployeeID(rs.getInt("employeeID"));
                ba.setEmployeeName(rs.getString("employeeName"));
                accounts.add(ba);
            }
            rs.close();
        }
        catch (NullPointerException ex) 
        {
            ex.printStackTrace();
        }
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return accounts;
    }
    
    /**
     * 根据帐号id获取帐号信息
     * @param aid 帐号id
     * @return BankAccount对象
     */
    public BankAccount getAccount(int aid)
    {
        BankAccount ba = null;
        String sql = "select ba.bankAccountID, ba.bankAccount, ba.bankName, "
                          + "ba.bankAccountName, ba.bankAccountBalance, ba.bankAccountCashBalance, "
                          + "ba.employeeID, ba.bankAccountCateID, bc.bankAccountCateName,"
                          + "e.employeeName "
                          + "from ((bankaccount_tb as ba "
                          + "left join bankaccountcate_tb as bc on ba.bankAccountCateID=bc.bankAccountCateID) "
                          + "left join employee_tb as e on ba.employeeID=e.employeeID) "
                          + "where bankAccountID="+aid ;
        ResultSet rs = db.query(sql);
        try 
        {
            if(rs.next())
            {
                ba = new BankAccount();
                ba.setBankAccountID(rs.getInt("bankAccountID"));
                ba.setBankAccount(rs.getString("bankAccount"));
                ba.setBankAccountName(rs.getString("bankAccountName"));
                ba.setBankAccountBalance(rs.getDouble("bankAccountBalance"));
                ba.setBankAccountCashBalance(rs.getDouble("bankAccountCashBalance"));
                ba.setBankAccountCateID(rs.getInt("bankAccountCateID"));
                ba.setBankAccountCateName(rs.getString("bankAccountCateName"));
                ba.setBankName(rs.getString("bankName"));
                ba.setEmployeeID(rs.getInt("employeeID"));
                ba.setEmployeeName(rs.getString("employeeName"));
            }
            rs.close();
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        }
        return ba;
    }
    
    /**
     * 取得所有帐号的总余额
     * @return 
     */
    public double getTotalBalance(){return getTotalBalance(-1);}
    public double getTotalBalance(int bid)
    {
        String sql = "SELECT SUM(bankAccountBalance) as sbab FROM bankaccount_tb"+(bid>0?" WHERE bankAccountID="+bid:"");
        ResultSet rs = db.query(sql);
        try 
        {
            if(rs.next())
                return rs.getDouble("sbab");
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BankAccountActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0;
    }
    
    /**
     * 取得所有帐号的总现金余额
     * @return 
     */
    public double getTotalCashBalance(){return getTotalCashBalance(-1);}
    public double getTotalCashBalance(int bid)
    {
        String sql = "SELECT SUM(bankAccountCashBalance) as sacb FROM bankaccount_tb"+(bid>0?" WHERE bankAccountID="+bid:"");
        ResultSet rs = db.query(sql);
        try 
        {
            if(rs.next())
                return rs.getDouble("sacb");
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BankAccountActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0;
    }
    
    /**
     * 添加一个银行帐号
     * @param ba
     * @return 返回0表示无错误，否则返回大于零的错误代码
     * 错误代码列表：
     * 101记录已存在
     * 102数据库写入失败
     * 200未知错误
     */
    public int addBankAccount(BankAccount ba)
    {
        if(new Double(ba.getBankAccountCateID()).isNaN())
        {
            ba.setBankAccountCateID(-1);
        }
        String sql = "SELECT * FROM bankaccount_tb WHERE bankAccountID=" + ba.getBankAccountID();
        ResultSet rs = this.db.query(sql);
        //bankAccountID 	bankAccount 	bankName 	bankAccountName 	bankAccountBalance 	bankAccountCashBalance 	employeeID 	bankAccountCateID
        try 
        {
            if(rs.next())
            {
                rs.close();
                return 101;//帐号已经存在
            }
            rs.close();
            sql = "INSERT INTO bankaccount_tb(bankAccount,bankName,bankAccountName,bankAccountBalance,bankAccountCashBalance,employeeID,bankAccountCateID)"
                    + " VALUES('"+ba.getBankAccount()+"','"+ba.getBankName()+"','"+ba.getBankAccountName()+"',"+ba.getBankAccountBalance()+","+ba.getBankAccountCashBalance()+","+ba.getEmployeeID()+","+ba.getBankAccountCateID()+");";
            int n = this.db.update(sql);
            if(n == 1)
                return 0;
            else
                return 102;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BankAccountActions.class.getName()).log(Level.WARNING, null, ex);
            return 200;//数据库异常
        }
    }
    
    /**
     * 删除id为aid的帐号
     * @param aid
     * @return 
     */
    public boolean delBankAccount(int aid)
    {
        if(aid <= 0)
            return false;
        
        String sql = "DELETE FROM bankaccount_tb WHERE bankAccountID="+aid;
        if(this.db.update(sql) > 0)
            return true;
        else
            return false;
    }
    
    /**
     * 修改银行帐号
     * @param ba
     * @return 返回0表示无错误，否则返回大于零的错误代码
     * 错误代码列表：
     * 101记录不存在
     * 102数据库写入失败
     * 200未知错误
     */
    public int updateBankAccount(BankAccount ba)
    {
        if(new Double(ba.getBankAccountCateID()).isNaN())
        {
            ba.setBankAccountCateID(-1);
        }

        String sql = "SELECT * FROM bankaccount_tb WHERE bankAccountID=" + ba.getBankAccountID();
        ResultSet rs = this.db.query(sql);
        //bankAccountID 	bankAccount 	bankName 	bankAccountName 	bankAccountBalance 	bankAccountCashBalance 	employeeID 	bankAccountCateID
        try 
        {
            if(rs.next())
            {
                //bankAccount,bankName,bankAccountName,bankAccountBalance,bankAccountCashBalance,employeeID,bankAccountCateID
                sql = "UPDATE bankaccount_tb SET "
                        + "bankAccount='"+ba.getBankAccount()+"',"
                        + "bankName='"+ba.getBankName()+"',"
                        + "bankAccountName='"+ba.getBankAccountName()+"',"
                        + "bankAccountBalance="+ba.getBankAccountBalance()+","
                        + "bankAccountCashBalance="+ba.getBankAccountCashBalance()+","
                        + "employeeID="+ba.getEmployeeID()+","
                        + "bankAccountCateID="+ba.getBankAccountCateID()+""
                        + " where bankAccountID="+ba.getBankAccountID()+";";
                int n = this.db.update(sql);
                if(n == 1)
                    return 0;
                else
                    return 102;
                
            }
            else
            {
                rs.close();
                return 101;//帐号不存在
            }
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(BankAccountActions.class.getName()).log(Level.WARNING, null, ex);
            return 200;//数据库异常
        }
    }
    
}
