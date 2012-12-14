package org.myilike.Contortlors;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.io.IOException;
import java.util.List;
import java.text.NumberFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.myilike.Beans.*;
import org.myilike.Moudles.BankAccountActions;

/**
 *
 * @author Akers
 */
public class BankAccountsServlet extends HttpServlet
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
        if(req.getSession().getAttribute("adminName") == null)
            new IlikeMessages(req, resp).illegalRequest();
        
        else if(req.getParameter("m") == null)
            this.listAccounts(req, resp);
        
        //m=add参数，新增银行帐号，添加成功跳转到BankAccounts_m.jsp
        else if(req.getParameter("m").equals("add"))
            this.addBankAccount(req, resp);
        
        //m=del参数，删除银行帐号，删除成功跳转到BankAccounts_m.jsp
        else if(req.getParameter("m").equals("del"))
            this.deleteAccount(req, resp);
        
        //m=up参数，修改银行帐号，修改成功跳转到BankAccounts_m.jsp
        else if(req.getParameter("m").equals("up"))
            this.updateAccount(req, resp);
        
        else if(req.getParameter("m").equals("edit"))
            this.updateAccount(req, resp);
    }
    
    /* function addBankAccount
     * @author Akers
     * 添加一个银行帐号到数据库
     */
    private void addBankAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        BankAccount ba = new BankAccount();
        BankAccountActions baa = new BankAccountActions();
        req.setCharacterEncoding("gbk");
        ba.setBankName(req.getParameter("bankName"));
        ba.setBankAccount(req.getParameter("bankAccount"));
        ba.setBankAccountName(req.getParameter("bankAccountName"));
        ba.setBankAccountBalance(Double.parseDouble(req.getParameter("bankAccountBalance")));
        ba.setBankAccountCashBalance(Double.parseDouble(req.getParameter("bankAccountCashBalance")));
        ba.setEmployeeID(Integer.parseInt(req.getParameter("employeeID")));
        
        int resCode = baa.addBankAccount(ba);
        //String msg = "";
        IlikeMessages msg = new IlikeMessages(req, resp);
        
        if(resCode == 0)
            msg.sendMsg("sucMsg", "创建银行帐号成功！！", "bankaccounts", "管理页面", 3);
//            msg = "添加帐号成功！！点击确定返回管理页面...";
        else
        {
            String errMsg = "";
            switch(resCode)
            {
                case 101:
                    errMsg = "需创建的帐号已经存在，请先删除原有帐号再重新新建";
                    break;
                case 102:
                    errMsg = "数据写入失败！可能是数据库服务器出现故障或系统正在维护，请稍后再试或联系系统管理员";
                    break;
                case 200:
                    errMsg = "未知错误，请联系系统管理员";
                    break;
            }
            msg.sendMsg("errMsg", "添加银行帐号失败，错误信息："+errMsg, "bankaccounts", "管理页面", 3);
        }
    }
    
    /* function listAccounts
     * @author Akers
     * 取得所有银行帐号列表
     */
    private void listAccounts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        BankAccountActions baa = new BankAccountActions();
        String totalBalance = "";//格式化后的货币字符串
        String totalCashBalance = "";//格式化后的货币字符串
        List<BankAccount> bankAccounts = baa.getAccountsList();

        //格式化数字输出：
        NumberFormat formater = NumberFormat.getNumberInstance();
        formater.setMaximumFractionDigits(2);//格式化小数为总是保留2位
        formater.setMinimumFractionDigits(2);

        totalBalance = formater.format(baa.getTotalBalance());
        totalCashBalance = formater.format(baa.getTotalCashBalance());
        req.getSession().setAttribute("accounts", bankAccounts);
        req.getSession().setAttribute("totalBalance", totalBalance);
        req.getSession().setAttribute("totalCashBalance", totalCashBalance);
        resp.setCharacterEncoding("utf-8");
        resp.sendRedirect("myilike/fms/bankaccount/bankAccounts_m.jsp");
    }
    
    /**
     * 删除银行帐号
     * @author Akers
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    private void deleteAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        resp.setCharacterEncoding("utf8");
        req.setCharacterEncoding("utf8");
        BankAccountActions baa = new BankAccountActions();
        boolean flag = false;
        
        flag = baa.delBankAccount(Integer.parseInt(req.getParameter("aid")));
        
        //输出操作结果
        IlikeMessages msg = new IlikeMessages(req, resp);
        if(flag)//删除成功
            msg.sendMsg("sucMsg", "银行帐号删除成功！！", "bankaccounts", "管理页面", 3);
        else//删除失败
            msg.sendMsg("errMsg", "银行帐号删除失败！！", "bankaccounts", "管理页面", 3);
    }
    
    /**
     * 修改银行帐号
     * @author Akers
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    private void updateAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        BankAccount ba = new BankAccount();
        BankAccountActions baa = new BankAccountActions();
        req.setCharacterEncoding("gbk");
        ba.setBankName(req.getParameter("bankName"));
        ba.setBankAccount(req.getParameter("bankAccount"));
        ba.setBankAccountName(req.getParameter("bankAccountName"));
        ba.setBankAccountBalance(Double.parseDouble(req.getParameter("bankAccountBalance")));
        ba.setBankAccountCashBalance(Double.parseDouble(req.getParameter("bankAccountCashBalance")));
        ba.setEmployeeID(Integer.parseInt(req.getParameter("employeeID")));
        ba.setBankAccountID(Integer.parseInt(req.getParameter("aid")));
        
        int resCode = baa.updateBankAccount(ba);
        //String msg = "";
        IlikeMessages msg = new IlikeMessages(req,resp);
        
        if(resCode == 0)
            msg.setMessage("sucMsg", "修改银行帐号成功！！", "bankaccounts", "管理页面", 3);
//            msg = "添加帐号成功！！点击确定返回管理页面...";
        else
        {
            String errMsg = "";
            switch(resCode)
            {
                case 101:
                    errMsg = "需修改的账户不存在";
                    break;
                case 102:
                    errMsg = "数据写入失败！可能是数据库服务器出现故障或系统正在维护，请稍后再试或联系系统管理员";
                    break;
                case 200:
                    errMsg = "未知错误，请联系系统管理员";
                    break;
            }
            msg.sendMsg("errMsg", "修改银行帐号失败，错误信息："+errMsg, "bankaccounts", "管理页面", 3);
        }
    }
}
