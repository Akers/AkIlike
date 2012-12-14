/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.myilike.Adapters.DB;

import java.sql.*;
import org.myilike.Moudles.Conf;
import org.myilike.Moudles.DB.MySqlDB;
import org.myilike.Moudles.DB.OdbcDB;

/**
 *
 * @author Akers
 */
public abstract class DataBase 
{

    public static DataBase getDB()
    {
        DataBase db = null;
        Conf conf = new Conf();
        if(conf.getDbType().equals("mysql"))
            db = new MySqlDB(conf);
        if(conf.getDbType().equals("odbc"))
            db = new OdbcDB(conf);
        return db;
    }
    
	//执行查询
	public abstract ResultSet query(String sql);
    
	//执行update delete操作
	public abstract int update(String sql);
    
    //注销数据库连接
	public abstract void close();
}
