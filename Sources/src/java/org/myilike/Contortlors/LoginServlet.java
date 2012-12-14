/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.myilike.Contortlors;

import org.myilike.Beans.*;
import org.myilike.Moudles.UserActions;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Akers
 */
public class LoginServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse respose) throws ServletException, IOException 
    {
        User loginUser = new User();
        //取得输入帐号、密码
        loginUser.setUserID(request.getParameter("adminID"));
        loginUser.setUserPWD(request.getParameter("adminPswd"));

        //登录
        int loginRst = UserActions.userLogin(loginUser);
        IlikeMessages msg = new IlikeMessages(request, respose);
        if(loginRst == 0)//正常登录
        {
            request.getSession().setAttribute("adminName", loginUser.getUserName());
            msg.sendMsg("sucMsg", "您已经成功登录！！", "index.jsp", "系统主页", 3);
        }
        else
            msg.sendMsg("errMsg", "登录失败，请核实您的帐号密码再重新尝试登录！！", "login", "登录页面", 3);
    }
    
    
}
