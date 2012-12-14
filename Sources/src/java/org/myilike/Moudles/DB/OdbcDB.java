/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.myilike.Moudles.DB;

import org.myilike.Adapters.DB.DataBase;
import java.sql.*;
import org.myilike.Moudles.Conf;
import org.myilike.utils.Convertors.StringConvertor;

/**
 *
 * @author Akers
 */
public class OdbcDB extends DataBase 
{
    private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String charset;
    
    public OdbcDB(){this(new Conf());}
    
    public OdbcDB(Conf conf)
    {
        try 
        {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection("jdbc:odbc:db_ilike");
            stmt = con.createStatement();
            charset = conf.getCharset();
		} 
        catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		 
        catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    
	
	
    @Override
	public ResultSet query(String sql)
    {
        //查询字符串重编码
        //sql = StringConvertor.getRecodeStr(sql, charset);
        //设置数据库客户端字符编码
        //this.setNames(charset);
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//close();
		return rs;
	}
	
    
    @Override
	public int update(String sql){
        //查询字符串重编码
        //sql = StringConvertor.getRecodeStr(sql, charset);
        //设置数据库客户端字符编码
        //this.setNames(charset);
		int n = 0;
		try {
			n = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
            
		}
		//close();
		return n;
		
	}
    
    @Override
	public void close()
    {
		try 
        {
            if(rs!=null)
                rs.close();
            if(stmt!=null)
                stmt.close();
            if(con!=null)
                con.close();
        } 
        catch (SQLException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
