/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.myilike.Moudles;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Akers
 */
public class Conf 
{
    private static final String DATABASE_TYPE_MYSQL = "mysql";
    
    private String charset;
    private String dbType;
    private String driver;
    private String dbURL;
    private String dbPort;
    private String dbName;
    private String dbUserName;
    private String dbPassword;
    
    public Conf()
    {
        Properties p = new Properties();
        try {
            p.load(Conf.class.getResourceAsStream("/confs/database.properties"));
            this.dbType = p.getProperty("dbType");
            this.driver = p.getProperty("driver");
            this.dbURL = p.getProperty("dbURL");
            this.dbPort = p.getProperty("dbPort");
            this.dbName = p.getProperty("dbName");
            this.dbUserName = p.getProperty("dbUserName");
            this.dbPassword = p.getProperty("dbPassword");
            this.charset = p.getProperty("charset");
        } catch (IOException ex) {
            Logger.getLogger(Conf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getDBConnentStr()
    { 
        return "jdbc:MySql://" + getDbURL() + ":" + getDbPort() + "/" + getDbName()+"?useUnicode=true&characterEncoding=UTF8";
    }

    public String getCharset() {
        return charset;
    }

    public String getDbType() {
        return dbType;
    }

    public String getDriver() {
        return driver;
    }

    public String getDbURL() {
        return dbURL;
    }

    public String getDbPort() {
        return dbPort;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
    
    
  
}
