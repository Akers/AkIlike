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
import org.myilike.Beans.Supplier;

/**
 *
 * @author Akers
 */
public class SupplierActions
{
    private DataBase db;
    
    
    public SupplierActions()
    {
       db = DataBase.getDB();
    }
    
    public List<Supplier> getIntro()
    {
        ArrayList<Supplier> sps = new ArrayList<Supplier>();
        Supplier sp = null;
        String sql = "SELECT `supplierID`,`supplierName` FROM `supplier_tb`";
        ResultSet rs = db.query(sql);
        try
        {
            while(rs.next())
            {
                sp = new Supplier();
                sp.setSupplierID(rs.getInt("supplierID"));
                sp.setSupplierName(rs.getString("supplierName"));
                sps.add(sp);
            }
            rs.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CustomerActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sps;
    }
}
