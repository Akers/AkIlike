/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.myilike.Contortlors;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.myilike.Beans.AssLine;
import org.myilike.Beans.BankAccount;
import org.myilike.Beans.IlikeMessages;
import org.myilike.Beans.Remark;
import org.myilike.Moudles.AssLineActions;
import org.myilike.Moudles.BankAccountActions;

/**
 *
 * @author
 * Administrator
 */
public class AssLineSeverlet extends HttpServlet
{
    private IlikeMessages msg;
    private int recordsInPage;
    private int recordsInGroup;

    @Override
    public void init(ServletConfig config) throws ServletException
    {
        this.recordsInPage = config.getInitParameter("recordsInPage") == null ? 5 : Integer.parseInt(config.getInitParameter("recordsInPage"));
        this.recordsInGroup = config.getInitParameter("recordsInGroup") == null ? 20 : Integer.parseInt(config.getInitParameter("recordsInGroup"));
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        msg = new IlikeMessages(req, resp);
        
        
        //判断是否正常访问
        if(req.getSession().getAttribute("adminName") == null)
            this.msg.illegalRequest();
//            this.illegalRequest(req, resp);
        else if(req.getParameter("m") == null || req.getParameter("m").equals("list"))
            this.assLineMain(req, resp);
        //在帐号管理页面的“帐号明细”访问，根据参数aid取得帐号明细
        else if(req.getParameter("m").equals("baabbaid"))
            this.showAssLineInfo(req, resp);
        else if(req.getParameter("m").equals("add"))//增加
            this.addAssLine(req, resp);
        else if(req.getParameter("m").equals("del"))//删除
            this.deleteAssLine(req, resp);
    }
    
    //帐号现金流管理主页面
    private void assLineMain(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        AssLineActions ala = new AssLineActions();
        BankAccountActions baa = new BankAccountActions();
        HttpSession session = req.getSession();
        //getTotalBalance getNewestDate
        String beginDate = req.getParameter("beginDate");//筛选条件，时间段开始
        String endDate = req.getParameter("endDate");//筛选条件，时间段结束
        int baid = req.getParameter("baid") == null ? 0 : Integer.parseInt(req.getParameter("baid"));//筛选条件，银行帐号id
        int curPage = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));
        int curGropNum = (int)((recordsInPage*curPage-1)/recordsInGroup)+1;//当前数据段，每段数据量等于recordsInGroup
        //最老记录日期，显示在beginDate输入框
        String oldestDate = (beginDate == null ?ala.getOldestDate() : beginDate);
        //最新记录日期，显示在endDate输入框
        String newestDate = (endDate == null ? ala.getNewestDate() : endDate);
        
        //如果session中没有totalPage、查询条件变更，重新获取数据
        Object tpFromSession = session.getAttribute("totalPage");
        Object odFSession = session.getAttribute("oldestDate") == null ? null : session.getAttribute("oldestDate");
        Object ndFSession = session.getAttribute("newestDate") == null ? null : session.getAttribute("newestDate");
        int baidFromSession = session.getAttribute("baid") == null ? -1 : (Integer)session.getAttribute("baid");
        boolean isRefresh = false;
        if(!oldestDate.equals(odFSession))
            isRefresh = true;
        else if(!newestDate.equals(ndFSession))
            isRefresh = true;
        else if(baid != baidFromSession)
            isRefresh = true;
        else if(tpFromSession == null)
            isRefresh = true;
        //如果请求页数超出当前分段，重新获取数据：
        else if(session.getAttribute("curGropNum") == null || curGropNum != (Integer)session.getAttribute("curGropNum"))
            isRefresh=true;
        //获取现金流对象列表
        ArrayList<AssLine> als;
        if(session.getAttribute("alInfo") == null || isRefresh)
            als = ala.getAssLines(baid, beginDate, endDate, recordsInGroup, curGropNum);
        else
            als = (ArrayList<AssLine>)session.getAttribute("alInfo");
        //取得单页数据
        int pCurRec = (this.recordsInPage*(curPage-1))%this.recordsInGroup;//当前记录的起始指针
        List<AssLine> list = als.subList(pCurRec, (als.size()-pCurRec > this.recordsInPage ? pCurRec+this.recordsInPage : als.size()));
        
        //计算总页数
        int totalPage = 0;
        //!oldestDate.equals(req.getSession().getAttribute("oldestDate")) || !newestDate.equals(req.getSession().getAttribute("newestDate")) || baid>0 || tpFromSession == null
        if(isRefresh)
        {
            int tCount = ala.getTotalRecondCounts();
            if(tCount%recordsInPage == 0)//如果总记录数是每页记录数的整数倍的话，总页数=总记录数/每页记录数
                totalPage = tCount/recordsInPage;
            else//否则，总页数=总记录数/每页记录数取整后加一
                totalPage = (int)(tCount/recordsInPage) +1;
            
            //保存总页数到session中
            session.setAttribute("totalPage", totalPage);
        }
        isRefresh = false;
        tpFromSession = null;
        
        
        
        //取得可用帐号列表
        List<BankAccount> abas = ala.getAvailableBankAccoutSmsg();
        
        //保存属性到session
        session.setAttribute("condition", "beginDate="+oldestDate+"&endDate="+newestDate+"&baid="+baid+"["+curPage);
        session.setAttribute("abas", abas);//可用账户
        session.setAttribute("selectID", baid);
        session.setAttribute("curGropNum", curGropNum);
        session.setAttribute("oldestDate", oldestDate);
        session.setAttribute("newestDate", newestDate);
        session.setAttribute("alInfo", als);
        req.getSession().setAttribute("list", list);
        //跳转
        resp.sendRedirect("myilike/fms/bankaccount/bankAccountAssLine_m.jsp");
    }
    
    //根据aid取得帐号明细
    private void showAssLineInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        String aid = req.getParameter("aid") == null ? "-1" : req.getParameter("aid");
        AssLineActions ala = new AssLineActions();
        BankAccountActions baa = new BankAccountActions();
        //getTotalBalance getNewestDate
        String beginDate = req.getParameter("beginDate");
        String endDate = req.getParameter("endDate");

        ArrayList<AssLine> als = ala.getAssLines(Integer.parseInt(aid), beginDate, endDate, 0, 1);

        req.getSession().setAttribute("account", baa.getAccount(Integer.parseInt(aid)));
        req.getSession().setAttribute("totalBalance", baa.getTotalBalance(Integer.parseInt(aid)));
        req.getSession().setAttribute("totalCash", baa.getTotalCashBalance(Integer.parseInt(aid)));
        req.getSession().setAttribute("totalIncome", ala.getTotalIncome(Integer.parseInt(aid)));
        req.getSession().setAttribute("totalOut", ala.getTotalAmountOut(Integer.parseInt(aid)));
        req.getSession().setAttribute("oldestDate", (beginDate == null ?ala.getOldestDate(Integer.parseInt(aid)) : beginDate));
        req.getSession().setAttribute("newestDate", (endDate == null ? ala.getNewestDate(Integer.parseInt(aid)) : endDate));
        
        req.getSession().setAttribute("alInfo", als);
        resp.sendRedirect("myilike/fms/bankaccount/bankAccountAssLineByBankAccountID_br.jsp");
    }
    
    private void addAssLine(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        AssLine as = new AssLine();
        AssLineActions ala = new AssLineActions();
        as.setBankAccountAssLineNum(req.getParameter("bankAccountAssLineNum"));
        as.setAmount(Double.parseDouble(req.getParameter("amount")));
        String isAccounted = req.getParameter("isAccounted");
        as.setIsAccounted(isAccounted.equals("0") ? false : true);
        String isTTStr = req.getParameter("isTransactionType");
        as.setIsTransactionType(isTTStr.equals("0") ? false : true);
        as.setBankAccountAssLineRemark(new Remark(req.getParameter("bankAccountAssLineRemark")));
        as.setBankAccountAssLIneDate(req.getParameter("bankAccountAssLineDate"));
        as.setCustomerID(Integer.parseInt(req.getParameter("customerID")));
        as.setSupplierID(Integer.parseInt(req.getParameter("supplierID")));
        as.setBankAccountID(Integer.parseInt(req.getParameter("bankAccountID")));
        
        int resCode = ala.addAssLine(as);
        //String msg = "";
        IlikeMessages msg = new IlikeMessages(req, resp);
        
        if(resCode == 0)
            msg.sendMsg("sucMsg", "添加账户明细成功！！", "assline", "账户明细管理页面", 3);
//            msg = "添加帐号成功！！点击确定返回管理页面...";
        else
        {
            String errMsg = "";
            switch(resCode)
            {
                case 101:
                    errMsg = "需创建的账户明细记录已经存在，请先删除原有帐号再重新新建";
                    break;
                case 102:
                    errMsg = "数据写入失败！可能是数据库服务器出现故障或系统正在维护，请稍后再试或联系系统管理员";
                    break;
                case 200:
                    errMsg = "未知错误，请联系系统管理员";
                    break;
            }
            msg.sendMsg("errMsg", "添加账户明细失败，错误信息："+errMsg, "assline", "账户明细管理页面", 3);
        }
    }
    
    /**
     * 删除银行帐号
     * @author Akers
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException 
     */
    private void deleteAssLine(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        AssLineActions aa = new AssLineActions();
        boolean flag = false;
        
        flag = aa.delAssLine(Integer.parseInt(req.getParameter("alid")));
        
        //输出操作结果
        IlikeMessages msg = new IlikeMessages(req, resp);
        if(flag)//删除成功
            msg.sendMsg("sucMsg", "银行帐号明细删除成功！！", "assline", "账户明细管理页面", 3);
        else//删除失败
            msg.sendMsg("errMsg", "银行帐号明细删除失败！！", "assline", "账户明细管理页面", 3);
    }
}
