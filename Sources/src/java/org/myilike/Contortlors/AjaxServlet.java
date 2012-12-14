/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.myilike.Contortlors;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.myilike.Beans.*;
import org.myilike.Moudles.*;
import org.myilike.utils.json.*;

/**
 *
 * @author Akers
 */
public class AjaxServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        //获取所有员工的姓名
        if(req.getParameter("m").equals("genl"))
            this.getEmplyeeNameList(req, resp);
        else if(req.getParameter("m").equals("gbail"))
            this.getBankAccountIntroList(req, resp);
        else if(req.getParameter("m").equals("gcnil"))//客户名称、ID列表
            this.getCusIntroList(req, resp);
        else if(req.getParameter("m").equals("gsnil"))//供应商名称、ID列表
            this.getSppIntroList(req, resp);
    }
    
    private void getEmplyeeNameList(HttpServletRequest req, HttpServletResponse resp)
    {
        List<User> users = UserActions.getUserNameList(UserActions.GROUP_EMPLOYEE);
        //构建JSON数组
        JSONArray jsonA = new JSONArray(users);
        
        resp.setCharacterEncoding("utf-8");
        try 
        {
            resp.getWriter().print(jsonA);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(AjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getBankAccountIntroList(HttpServletRequest req, HttpServletResponse resp)
    {
        List<BankAccount> accounts = new AssLineActions().getAvailableBankAccoutSmsg();
        //构建JSON数组
        JSONArray jsonA = new JSONArray(accounts);
        
        resp.setCharacterEncoding("utf-8");
        try 
        {
            resp.getWriter().print(jsonA);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(AjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getCusIntroList(HttpServletRequest req, HttpServletResponse resp)
    {
        List<Customer> accounts = new CustomerActions().getIntro();
        //构建JSON数组
        JSONArray jsonA = new JSONArray(accounts);
        
        resp.setCharacterEncoding("utf-8");
        try 
        {
            resp.getWriter().print(jsonA);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(AjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getSppIntroList(HttpServletRequest req, HttpServletResponse resp)
    {
        List<Supplier> accounts = new SupplierActions().getIntro();
        //构建JSON数组
        JSONArray jsonA = new JSONArray(accounts);
        
        resp.setCharacterEncoding("utf-8");
        try 
        {
            resp.getWriter().print(jsonA);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(AjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
