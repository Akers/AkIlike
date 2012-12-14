/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.myilike.Moudles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.myilike.Adapters.DB.DataBase;
import org.myilike.Beans.*;

/**
 *
 * @author Akers
 */
public class CustomerActions
{
    private DataBase db;
    
    
    public CustomerActions()
    {
       db = DataBase.getDB();
    }
    
    public List<Customer> getIntro()
    {
        ArrayList<Customer> cuss = new ArrayList<Customer>();
        Customer cus = null;
        String sql = "SELECT `customerID` , `customerName` FROM `customer_tb`";
        ResultSet rs = db.query(sql);
        try
        {
            while(rs.next())
            {
                cus = new Customer();
                cus.setCustomerID(rs.getInt("customerID"));
                cus.setCustomerName(rs.getString("customerName"));
                cuss.add(cus);
            }
            rs.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CustomerActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cuss;
    }
}
