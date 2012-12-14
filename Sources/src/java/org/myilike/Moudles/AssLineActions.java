/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.myilike.Moudles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.myilike.Adapters.DB.DataBase;
import org.myilike.Beans.AssLine;
import org.myilike.Beans.BankAccount;
import org.myilike.Beans.Remark;

/**
 * 
 * @author Akers
 */
public class AssLineActions 
{
    private DataBase db;
    private String countSqlStr;
    
    public AssLineActions()
    {
        db = DataBase.getDB();
        countSqlStr = "SELECT COUNT(*) FROM bankaccountassline_tb";
    }
    
    /**
     * 获取帐号资金流数据对象
     * @author Akers
     * @param bankAccountID 传入大于0的bankAccountID取得对应的现金流对象列表，传入小于等于0的任意整数取得所有记录
     * @param startDate
     * @param endDate
     * @param limit
     * 
     * @return AssLine
     */
    public ArrayList<AssLine> getAssLines(int bankAccountID){return getAssLines(bankAccountID, null, null, 0, 1);}
    public ArrayList<AssLine> getAssLines(int bankAccountID, String startDate, String endDate, int limit, int groupNum)
    {
        AssLine al;
        ArrayList<AssLine> als = new ArrayList<AssLine>();
        int startIndex = limit*(groupNum-1);//上次查询的记录指针
        String limitStr = limit == 0 ? "" : "LIMIT "+startIndex+","+limit;
        String orderDate = "";
        String where = "";
        
        if(startDate == null)
            startDate = "0000-00-00";
        if(endDate == null)
            endDate =  new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        
        if(bankAccountID > 0)
        {
            where = "where b.bankAccountID="+bankAccountID;
            orderDate = " AND ";
        }
        else
        {
            where = "where";
            orderDate = " ";
        }
        
        orderDate += "(b.bankAccountAssLineDate >= '" + startDate + "' AND b.bankAccountAssLineDate <= '"+endDate + "')";
        
        String sql = "SELECT b.*,ba.`bankAccountName`,ba.`bankAccount`,c.customerName,s.supplierName FROM bankaccountassline_tb as b left join bankaccount_tb as ba on b.bankAccountID=ba.bankAccountID left join customer_tb as c on b.customerID=c.customerID left join supplier_tb as s on b.supplierID=s.supplierID "+where+orderDate+limitStr;
        countSqlStr = "SELECT COUNT(*) FROM bankaccountassline_tb as b "+where+orderDate;
        ResultSet rs = db.query(sql);
        NumberFormat formater = NumberFormat.getNumberInstance();
        formater.setMaximumFractionDigits(2);//格式化小数为总是保留2位
        formater.setMinimumFractionDigits(2);
        try
        {
            while(rs.next())
            {
                al = new AssLine();
                al.setAmount(rs.getDouble("amount"));
                al.setBankAccountAssLIneDate(rs.getString("bankAccountAssLIneDate"));
                al.setBankAccountAssLineID(rs.getInt("bankAccountAssLineID"));
                al.setBankAccountAssLineNum(rs.getString("bankAccountAssLineNum"));
                
                //设置remark
                Remark r = new Remark();
                r.setUnFormatString(rs.getString("bankAccountAssLineRemark"));
                r.formatRemark("n,t");
                al.setBankAccountAssLineRemark(r);
                
                al.setBankAccountID(rs.getInt("bankAccountID"));
                al.setBankAccountName(rs.getString("bankAccountName"));
                al.setBankAccount(rs.getString("bankAccount"));
                al.setCustomerID(rs.getInt("customerID"));
                al.setCustomerName(rs.getString("customerName"));
                al.setIsAccounted(rs.getBoolean("isAccounted"));
                al.setIsTransactionType(rs.getBoolean("isTransactionType"));
                al.setSupplierID(rs.getInt("supplierID"));
                al.setSupplierName(rs.getString("supplierName"));
                als.add(al);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            //Logger.getLogger(AssLineActions.class.getName()).log(Level.WARNING, null, ex);
        }
        return als;
    }
    
    /**
     * 添加一个流水线明细
     * @param al
     * @return 返回0表示无错误，否则返回大于零的错误代码
     * 错误代码列表：
     * 101记录已存在
     * 102数据库写入失败
     * 200未知错误
     */
    public int addAssLine(AssLine al)
    {
        String sql = "SELECT * FROM bankaccountassline_tb WHERE bankAccountAssLineNum=" + al.getBankAccountAssLineNum();
        ResultSet rs = this.db.query(sql);
        try 
        {
            if(rs.next())
            {
                rs.close();
                return 101;//帐号已经存在
            }
            rs.close();
            sql = "INSERT INTO bankaccountassline_tb(`bankAccountAssLineNum`,`amount`,`isAccounted`,`isTransactionType`,`bankAccountAssLineRemark`,`bankAccountAssLineDate`,`customerID`,`supplierID`,`bankAccountID`)"
                    + " VALUES('"+al.getBankAccountAssLineNum()+"',"+al.getAmount()+","+al.isIsAccounted()+","+al.isIsTransactionType()+",'"+al.getBankAccountAssLineRemark().getUnFormatString()+"','"+al.getBankAccountAssLIneDate()+"',"+al.getCustomerID()+","+al.getSupplierID()+","+al.getBankAccountID()+");";
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
     * 删除bankAccountAssLineID为alid的流水线记录
     * @param alid
     * @return 
     */
    public boolean delAssLine(int alid)
    {
        if(alid <= 0)
            return false;
        
        String sql = "DELETE FROM bankaccountassline_tb WHERE bankAccountAssLineID="+alid;
        if(this.db.update(sql) > 0)
            return true;
        else
            return false;
    }
    
    //取得总记录数
    public int getTotalRecondCounts()
    {
        if(countSqlStr == null)
            return 0;
        ResultSet rs = db.query(countSqlStr);
        int rCount = 0;
        try
        {
            if(rs.next())
                rCount = rs.getInt(1);
            else
                rCount = 0;
            if(rs != null)
                rs.close();
        }
        catch(SQLException se)
        {
            System.out.println(se.getMessage());
        }
        return rCount;
    }
    
    
    public Double getTotalIncome(){return getTotalIncome(-1);}
    public Double getTotalIncome(int bankAccountID)
    {
        String sql = "SELECT SUM(amount) FROM bankaccountassline_tb WHERE isAccounted=TRUE"+(bankAccountID > 0 ? " AND bankAccountID="+bankAccountID : "");
        ResultSet rs = db.query(sql);
        Double rst = 0.00;
        try 
        {
            if(rs.next())
               rst = rs.getDouble(1); 
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(AssLineActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rst;
    }
    
    public Double getTotalAmountOut(){return getTotalAmountOut(-1);}
    public Double getTotalAmountOut(int bankAccountID)
    {
        String sql = "SELECT SUM(amount) FROM bankaccountassline_tb WHERE isAccounted=FALSE"+(bankAccountID > 0 ? " AND bankAccountID="+bankAccountID : "");
        ResultSet rs = db.query(sql);
        Double rst = 0.00;
        try 
        {
            if(rs.next())
               rst = rs.getDouble(1); 
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(AssLineActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rst;
    }
    
    /**
     * 取得可用的银行帐号bankAccountID,bankAccount,bankAccountName
     * P.S.可用即是在现金流表中出现过的银行帐号
     * 
     * @return List<BankAccount>
     */
    public List<BankAccount> getAvailableBankAccoutSmsg()
    {
        ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
        BankAccount ba = null;
        
        String sql = "SELECT ba.bankAccount,ba.bankAccountName,b.bankAccountID FROM bankAccountAssLine_tb b join bankaccount_tb ba on b.bankAccountID = ba.bankAccountID group by b.`bankAccountID`";
        ResultSet rs = db.query(sql);
        try
        {
            while(rs.next())
            {
                ba = new BankAccount();
                ba.setBankAccount(rs.getString("bankAccount"));
                ba.setBankAccountName(rs.getString("bankAccountName"));
                ba.setBankAccountID(rs.getInt("bankAccountID"));
                accounts.add(ba);
            }
            rs.close();
        }
        catch(SQLException sqe)
        {
            System.out.println(sqe.getMessage());
        }
        
        return accounts;
    }
    
    
    /**
     * 取得资金链记录中最老的时间记录，不传入参数或者传入小于0的参数则返回所有记录中最老的时间，否则，返回bankAccountID对应的记录中最老的记录时间
     * @author Akers
     * @return 
     */
    public String getOldestDate(){return this.getOldestDate(-1);}
    public String getOldestDate(int bankAccountID)
    {
        ResultSet rs;
        String rst = "";
        try
        {   
            String sql = "SELECT MIN(bankAccountAssLineDate) FROM bankAccountAssLine_tb "+ (bankAccountID >= 0 ? "WHERE bankAccountID="+bankAccountID : "");
            //取得最新日期：
            rs = db.query(sql);
            if(rs.next())
                rst = rs.getString(1);
            else
                rst = "2000-01-01";

        }
        catch (SQLException ex)
        {
            Logger.getLogger(AssLineActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rst;
    }
    
    /**
     * 取得资金链记录中最新的时间记录，不传入参数或者传入小于0的参数则返回所有记录中最新的时间，否则，返回bankAccountID对应的记录中最新的记录时间
     * @author Akers
     * @return 
     */
    public String getNewestDate(){return this.getNewestDate(-1);}
    public String getNewestDate(int bankAccountID)
    {
        ResultSet rs;
        String rst = "";
        try
        {   
            String sql = "SELECT MAX(bankAccountAssLineDate) FROM bankAccountAssLine_tb "+ (bankAccountID >= 0 ? "WHERE bankAccountID="+bankAccountID : "");
            SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-mm-dd");
            //取得最新日期：
            rs = db.query(sql);
            if(rs.next())
                rst = rs.getString(1);
            else
                rst = dateFormater.format(new Date());

        }
        catch (SQLException ex)
        {
            Logger.getLogger(AssLineActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rst;
    }
    
    
    //析构
    @Override
    protected void finalize() throws Throwable
    {
        super.finalize();
        this.clone();
        System.gc();
    }
}
