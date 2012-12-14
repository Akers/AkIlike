/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.myilike.Contortlors;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.myilike.Beans.*;
import org.myilike.Moudles.BankAccountActions;
import org.myilike.Moudles.UserActions;

/**
 *
 * @author Akers
 */
public class BankAccountInfoessServlet extends HttpServlet 
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //判断是否正常访问
        if(req.getSession().getAttribute("adminName") == null || req.getParameter("aid") == null)
            this.illegalRequest(req, resp);
        else
            this.showAccountInfo(req, resp);
    }

    
    
    
    private void showAccountInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        BankAccountActions baa = new BankAccountActions();
        BankAccount ba = baa.getAccount(Integer.parseInt(req.getParameter("aid")));
        IlikeMessages msg = new IlikeMessages();
        
        if(ba == null)
        {
            msg.setMessage("sysMsg", "数据获取失败，请与系统管理员联系或返回帐号管理页面...", "bankaccounts", "登录页面", 3);
            req.getSession().setAttribute("msg", msg);
            resp.sendRedirect("msg.jsp");
        }
        
        List<User> employeeNames = UserActions.getUserNameList(UserActions.GROUP_EMPLOYEE);
        
        req.getSession().setAttribute("account", ba);
        req.getSession().setAttribute("employees", employeeNames);
        resp.setCharacterEncoding("utf8");
        resp.sendRedirect("myilike/fms/bankaccount/bankAccount_edit.jsp");
        
    }
    
    private void illegalRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        IlikeMessages msg = new IlikeMessages();
        msg.setMessage("sysMsg", "非法访问，可能是您未登录或者登录错误，请返回登录页面正常登录！！", "login.jsp", "登录页面", 3);
        req.getSession().setAttribute("msg", msg);
        resp.sendRedirect("msg.jsp");
    }
    
}
