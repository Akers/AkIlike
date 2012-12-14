<%-- 
    Document   : bankAccount_edit
    Created on : 2011-12-6, 17:48:49
    Author     : Akers
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
<title>账户明细管理</title>
<link href="../../../css/styleform.css" rel="stylesheet" type="text/css" />
<link href="../../../css/stylemain.css" rel="stylesheet" type="text/css" />
<link href="../../../css/styletable.css" rel="stylesheet" type="text/css">
<link href="../../../css/styletext.css" rel="stylesheet" type="text/css">
<link href="../../../css/calendar.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript" src="../../../js/jquery-1.6.2.min.js"></script>
<script language="javascript" type="text/javascript" src="../../../js/calPicker.js"></script>
<script language="javascript" type="text/javascript" src="../../../js/PageChange.js"></script>
<script language="javascript" type="text/javascript" src="../../../js/editAssLine.js"></script>
<script language="javaScript" type="text/javascript">
$(function()
{
	$('.date-pick').simpleDatepicker();
});
</script>
</head>

<body topmargin="0">

<div align=center> 
  <p class="textTitle">账户明细管理</p> 
<table width="98%"  border="0" cellpadding="0" cellspacing="0" >
<tr>
    <td width="100%" >
	<div align="center" >      
        <form id="form3" name="form3" method="post" action="../../../assline" >
        <p align="right" class="text">
         
           日期:从 
           <input class="wdate date-pick" type="text" name="beginDate" value="${sessionScope.oldestDate}"/>
           至
           <input class="wdate date-pick" type="text" name="endDate" value="${sessionScope.newestDate}"/> 
            <label>账号：</label>
          <select name="baid">
              <option value="0" ${sessionScope.selectID > 0 ? "" : "selected"}>全部</option>
                <c:forEach var="row" items="${sessionScope.abas}">
                    <option value="${row.bankAccountID}" ${sessionScope.selectID == row.bankAccountID ? "selected" : ""}>${row.bankAccount}|${row.bankAccountName} </option>
                </c:forEach>
        </select>
           <input type="submit" name="Submit52" value="  查  询   " class="buttonCom" />
      </form>
        </div>	      
	  </td>
  </tr> 
  <tr valign="top">
    <td  valign="top">
     <form  method="post" action="">
    <table width="100%" height="98"  border="0" cellpadding="0" cellspacing="0"> 
        <tr height="5">
      <td height="5" align="right"><a href="bankAccountAssLine_add.html">添加账户明细</a></td>
   	 </tr>
      	<tr>
        <td  colspan="4" valign="top">
      
		<table width="100%" border="0" align="center" cellpadding="5" cellspacing="0"  style="border-collapse: collapse">
                  <tr> 
                    <th width="3%"valign="middle"  bgcolor="#eeeeee" class="th_brCom"><p>序号</p></th>
                    <th width="7%"valign="middle"  bgcolor="#eeeeee" class="th_brCom">日期</th>
                     <th width="5%"valign="middle"  bgcolor="#eeeeee" class="th_brCom">户名</th>
                    <th width="10%"valign="middle"  bgcolor="#eeeeee" class="th_brCom">账号</th>
                    <th width="10%"valign="middle"  bgcolor="#eeeeee" class="th_brCom"><div align="center">账户流水线</div></th>
                    <th  width="5%" height="30" valign="middle" bgcolor="#eeeeee" class="th_brCom"> 
                      <div align="center" >入账金额</div></th>
                    <th width="5%"valign="middle"  bgcolor="#eeeeee" class="th_brCom"><div align="center">支出金额</div></th>
                    <th width="5%"valign="middle"  bgcolor="#eeeeee" class="th_brCom">交易类型</th>
                   
                    <th width="15%"valign="middle"  bgcolor="#eeeeee" class="th_brCom">备注</th>
                    <th width="15%" height="30" valign="middle"   bgcolor="#eeeeee" class="th_brCom"><div align="center" >操作</div></th>
                  </tr>
                  <c:forEach var="row" items="${sessionScope.list}">
                      <tr>
                            <td valign="middle"  class="td_brCom bankAccountAssLineID"><div align="center"><c:out value="${row.bankAccountAssLineID}"/></div></td>
                            <td valign="middle"  class="td_brCom"><div align="center"><c:out value="${row.bankAccountAssLIneDate}" escapeXml="false" /></div></td>
                            <td valign="middle"  class="td_brCom"><div align="center"><c:out value="${row.bankAccountName}" escapeXml="false" /></div></td>
                            <td valign="middle"  class="td_brCom"><div align="center"><c:out value="${row.bankAccount}" escapeXml="false" /></div></td>
                            <td valign="middle"  class="td_brCom"><div align="center"><c:out value="${row.bankAccountAssLineNum}" escapeXml="false" /></td>                    
                            <td valign="middle"  class="td_brCom"><div align="center"><fmt:formatNumber value="${row.isAccounted == true ? row.amount : ''}" pattern="#,#00.00"/></div></td>
                            <td valign="middle"  class="td_brCom"><div align="center"><fmt:formatNumber value="${row.isAccounted == true ? '' : row.amount}" pattern="#,#00.00"/></td>
                            <td valign="middle"  class="td_brCom"><div align="center"><c:out value="${row.isTransactionType ? '现金' : '转账'}" escapeXml="false" /></div></td>

                            <td valign="middle"  class="td_brCom"><c:out value="${row.bankAccountAssLineRemark.remarkText}" escapeXml="false" />
                            <font color="blue" ><c:out value="${row.bankAccountAssLineRemark.remarkName}" escapeXml="false" />                  </font>
                            </td>
                            <td valign="middle"  class="td_brCom">
                                <font color="#000000">
                                    <a href="bankAccountAssLine_edit.html">修 改</a>
                                    <a href="#" class="delAssLineHref">删 除</a> 
                                    【<a href="bankAccountAssLineDetail_m.html">明细管理</a>】
                                </font>
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
    <input id="condition" type="hidden" value="${sessionScope.condition}"/>
    共有<font color="red" id="total">${sessionScope.totalPage}</font>页记录&nbsp;&nbsp;当前<font color="red" id="current">0</font>页
    &nbsp;&nbsp;
    <a href="javascript:function(0)" target="_self" id="goFrist">首页</a>&nbsp;&nbsp;<a href="javascript:function(0)" id="goPre">上一页</a> | <a  href="javascript:function(0)" id="goNext">下一页</a>&nbsp;&nbsp;<a href="javascript:function(0)" id="goLast">尾页</a>
    &nbsp;&nbsp;
    <select id="pageSelect">
        <c:forEach var="i" begin="1" end="${sessionScope.totalPage}" step="1">
            <option value="${i}">${i}</option>
        </c:forEach> 
    </select>
</td>
 
</tr>

    </table>
</div>
</body>
</html>