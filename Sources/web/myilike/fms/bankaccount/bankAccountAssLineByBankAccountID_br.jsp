<%-- 
    Document   : bankAccountAssLineByBankAccountID_br
    Created on : 2011-12-10, 0:23:06
    Author     : Administrator
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8">

<title>账户明细</title>

<link href="../../../css/styleform.css" rel="stylesheet" type="text/css" />
<link href="../../../css/stylemain.css" rel="stylesheet" type="text/css" />
<link href="../../../css/styletable.css" rel="stylesheet" type="text/css">
<link href="../../../css/styletext.css" rel="stylesheet" type="text/css">
<link href="../../../css/calendar.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript" src="../../../js/jquery-1.6.2.min.js"></script>
<script language="javascript" type="text/javascript" src="../../../js/calPicker.js"></script>
<script language="javaScript" type="text/javascript">
$(function()
{
	$('.date-pick').simpleDatepicker();
		
});
</script>

</head>

<body topmargin="0">

<div align=center> 
    <p class="textTitle">【<c:out value="${sessionScope.account.bankAccountName}" escapeXml="false" />|<c:out value="${sessionScope.account.bankAccount}" escapeXml="false" />】账户明细</p> 
<table width="98%"  border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td width="100%" >
	<div align="center" >      
      <form id="form3" name="form3" method="post" action="../../../assline?m=baabbaid" >        
        <p align="right" class="text">
            <input type="hidden" name="aid" value="${sessionScope.account.bankAccountID}"/>
             日期:从 
             <input class="wdate date-pick" type="text" name="beginDate" value="${sessionScope.oldestDate}"/>
             至
           <input class="wdate date-pick" type="text" name="endDate" value="${sessionScope.newestDate}"/>           
           <input type="submit" name="Submit52" value="  查  询   " class="buttonCom" />
      </form>
        </div>	      
	  </td>
  </tr> 
   <tr>
      <td> <div align="right">入账总额：
      	<span class="textStress">
            <c:out value="${sessionScope.totalIncome}" />
      	</span>
      	，出账总额：<span class="textStress">
            <c:out value="${sessionScope.totalOut}" />	
		</span>
		，账户余额：<span class="textStress"><c:out value="${sessionScope.totalBalance}" />	
		</span>
		，现金余额：<span class="textStress">
      <c:out value="${sessionScope.totalCash}" />			
		</span>
        </div></td>
    </tr>
  <tr valign="top">
    <td  valign="top">
     <form  method="post" action="">
    <table width="100%" height="98"  border="0" cellpadding="0" cellspacing="0"> 
       <tr>
        <td  colspan="4" valign="top">
      
		<table width="100%" border="0" align="center" cellpadding="5" cellspacing="0"  style="border-collapse: collapse">
            
            <tr> 
                <th width="3%"valign="middle"  bgcolor="#eeeeee" class="th_brCom"><p>序号</p></th>
                <th width="7%"valign="middle"  bgcolor="#eeeeee" class="th_brCom">日期</th>                   
                <th width="10%"valign="middle"  bgcolor="#eeeeee" class="th_brCom"><div align="center">账户流水线</div></th>
                <th  width="5%" height="30" valign="middle" bgcolor="#eeeeee" class="th_brCom"> 
                    <div align="center" >入账金额</div></th>
                <th width="5%"valign="middle"  bgcolor="#eeeeee" class="th_brCom"><div align="center">支出金额</div></th>
                    <th width="5%"valign="middle"  bgcolor="#eeeeee" class="th_brCom"><div align="center">现金余额</div></th>
                <th width="5%"valign="middle"  bgcolor="#eeeeee" class="th_brCom">交易类型</th>                   
                <th width="15%"valign="middle"  bgcolor="#eeeeee" class="th_brCom">备注</th>                
            </tr>
            
            <c:forEach var="row" items="${sessionScope.alInfo}">
                  <tr > 
                    <td valign="middle"  class="td_brCom"><div align="center"><c:out value="${row.bankAccountAssLineID}"/></div></td>
                    <td valign="middle"  class="td_brCom"><div align="center"><c:out value="${row.bankAccountAssLIneDate}" escapeXml="false" /></div></td> 
                    <td valign="middle"  class="td_brCom"><div align="center"><c:out value="${row.bankAccountAssLineNum}" escapeXml="false" /></td>                    
                    <td valign="middle"  class="td_brCom"><div align="center"><fmt:formatNumber value="${row.isAccounted == true ? row.amount : ''}" pattern="#,#00.00"/></div></td>
                    <td valign="middle"  class="td_brCom"><div align="center"><fmt:formatNumber value="${row.isAccounted == true ? '' : row.amount}" pattern="#,#00.00"/></td>
                    <td valign="middle"  class="td_brCom"><div align="center"><c:out value="${sessionScope.totalCash}" escapeXml="false" /></td>
                    <td valign="middle"  class="td_brCom"><c:out value="${row.isTransactionType ? '现金' : '转账'}" escapeXml="false" /></td>
                    <td valign="middle"  class="td_brCom"><c:out value="${row.bankAccountAssLineRemark.remarkText}" escapeXml="false" />
                    <font color="blue" ><c:out value="${row.bankAccountAssLineRemark.remarkName}" escapeXml="false" />                  </font>
                    </td>   
                  </tr>
			</c:forEach>	  
                </table>
		</td>
      </tr>
	   </table>
        </form>
		</td>
        
      </tr>
    
	<tr>

<td class="left_text" align="right"  >

</td>

</tr>
    </table>

   
</div>
</body>
</html>
