<%-- 
    Document   : bankAccounts_m.jsp
    Created on : 2011-11-20, 20:20:28
    Author     : Akers
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>银行账户管理</title>
<link href="../../../css/styleform.css" rel="stylesheet" type="text/css" />
<link href="../../../css/stylemain.css" rel="stylesheet" type="text/css" />
<link href="../../../css/styletable.css" rel="stylesheet" type="text/css" />
<link href="../../../css/styletext.css" rel="stylesheet" type="text/css" />
<SCRIPT src="../../../js/edit.js"></SCRIPT>
</head>

<body topmargin="20">
<div align=center>  
<table width="95%"  border="0" cellpadding="0" cellspacing="0" >
    <tr>
        <td width="100%" >
            <div align="center" >
                <p class="textTitle">银行账户管理</p>      
            </div>	  
        </td>
    </tr>
  
    <tr> 
      <td height="10" colspan="2">
         <div align="right">                
            账户总余额：<span class="textStress"><c:out value="${sessionScope.totalBalance}"/></span>
            现金总余额：<span class="textStress"><c:out value="${sessionScope.totalCashBalance}"/></span>

        </div>
      </td>
    </tr>
  <tr>
    <td  valign="top">
    <table width="100%"  border="0" cellpadding="0" cellspacing="0">     
      	<tr>
        <td  colspan="4" valign="top">
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"  style="border-collapse: collapse">
                <tr> 
                    <th  width="4%" height="30" valign="middle" bgcolor="#eeeeee" class="th_brCom"> 
                      <div align="center" >序号</div></th>
                    <th width="6%"valign="middle"  bgcolor="#eeeeee" class="th_brCom"><div align="center" >银行</div></th>
                    <th width="14%"valign="middle"  bgcolor="#eeeeee" class="th_brCom">户名</th>
                    <th width="20%"valign="middle"  bgcolor="#eeeeee" class="th_brCom">账号 </th>
                    <th width="8%"valign="middle"  bgcolor="#eeeeee" class="th_brCom">账户余额</th>
                    <th width="8%"valign="middle"  bgcolor="#eeeeee" class="th_brCom">开户现金余额</th>
                    <th width="10%"valign="middle"  bgcolor="#eeeeee" class="th_brCom">使用者</th>
                    <th width="15%" height="30" valign="middle"   bgcolor="#eeeeee" class="th_brCom"><div align="center" >操作</div></th>
                </tr>
                <c:forEach items="${sessionScope.accounts}" var="row">
                    <tr>
                    <form name="formAccount_${row.bankAccountID}" method="post" action="/jee_class_design/bankaccounts">
                        <input name="m" type="hidden" value='del' />
                  		<td valign="middle" height="30"  class="td_brCom" style="text-align: center;">
                                ${row.bankAccountID} 
                                
                                <input name="aid" type="hidden" value=${row.bankAccountID} />
                        </td>            
                        <td height="30" valign="middle"  class="td_brCom"><div align="center">${row.bankName}</div></td>
                        <td valign="middle"  class="td_brCom"><div align="center">${row.bankAccountName}</div></td>
                        <td valign="middle"  class="td_brCom"><div align="right">${row.bankAccount}</div></td>
                        <td valign="middle"  class="td_brCom"><div align="right">${row.bankAccountBalance}</div></td>
                        <td valign="middle"  class="td_brCom"><div align="right">${row.bankAccountCashBalance}</div></td>
                        <td valign="middle"  class="td_brCom"><div align="center">${row.employeeName}</div></td>
                        <td align="center" height="30"  valign="middle"  class="td_brCom"> 
                            <a href="../../../accountinfoes?aid=${row.bankAccountID}">修  改</a>                     
                            <a href="javascript:void(0)" onClick="delAccount(${row.bankAccountID})">删  除</a> 
                            【<a href="../../../assline?m=baabbaid&aid=${row.bankAccountID}">查询明细</a>】                    
                        </td>
                        </form>
                  	</tr>
                </c:forEach>
<!--                  <tr> 
                    <th  width="4%" height="30" valign="middle" bgcolor="#eeeeee" class="th_brCom"> 
                      <div align="center" >序号</div></th>
                    <th width="6%"valign="middle"  bgcolor="#eeeeee" class="th_brCom"><div align="center" >银行</div></th>
                    <th width="14%"valign="middle"  bgcolor="#eeeeee" class="th_brCom">户名</th>
                    <th width="20%"valign="middle"  bgcolor="#eeeeee" class="th_brCom">账号 </th>
                    <th width="8%"valign="middle"  bgcolor="#eeeeee" class="th_brCom">账户余额</th>
                    <th width="8%"valign="middle"  bgcolor="#eeeeee" class="th_brCom">开户现金余额</th>
                    <th width="10%"valign="middle"  bgcolor="#eeeeee" class="th_brCom">使用者</th>
                    <th width="15%" height="30" valign="middle"   bgcolor="#eeeeee" class="th_brCom"><div align="center" >操作</div></th>
                  </tr>-->
				  
<!--                  <tr>   
                  		<td valign="middle" height="30"  class="td_brCom"><div align="center" > 
                      1
                        <input name="hidden" type="hidden" value=1>
                      </div></td>            
                    <td height="30" valign="middle"  class="td_brCom"><div align="center">农行</div></td>
                    <td valign="middle"  class="td_brCom"><div align="center">BOSS1</div></td>
                    <td valign="middle"  class="td_brCom"><div align="right">62226007100080032008</div></td>
                    <td valign="middle"  class="td_brCom"><div align="right">500000</div></td>
                    <td valign="middle"  class="td_brCom"><div align="right">1000</div></td>
                    <td valign="middle"  class="td_brCom"><div align="center">员工1</div></td>
                    <td align="center" height="30"  valign="middle"  class="td_brCom"> 
                      <a href="bankAccount_edit.html">修  改</a>                    
                           
                       <a href="#">删  除</a> 
                  【<a href="bankAccountAssLineByBankAccountID_br.html">查询明细</a>】                    
                     
                   </td>
                  	</tr>-->
					
<!--					  <tr>   
                  		<td valign="middle" height="30"  class="td_brCom"><div align="center" > 
                      2
                        <input name="hidden" type="hidden" value=2>
                      </div></td>            
                    <td height="30" valign="middle"  class="td_brCom"><div align="center">工商</div></td>
                    <td valign="middle"  class="td_brCom"><div align="center">BOSS2</div></td>
                    <td valign="middle"  class="td_brCom"><div align="right">62226007100080032008D</div></td>
                    <td valign="middle"  class="td_brCom"><div align="right">200000</div></td>
                    <td valign="middle"  class="td_brCom"><div align="right">0</div></td>
                    <td valign="middle"  class="td_brCom"><div align="center">员工2</div></td>
                    <td align="center" height="30"  valign="middle"  class="td_brCom"> 
                      <a href="bankAccount_edit.html">修  改</a> 
                       <a href="#">删  除</a> 
                  【<a href="bankAccount_br.html">查询明细</a>】  
                   </td>
                  	</tr>-->
					
<!--					  <tr>   
                  		<td valign="middle" height="30"  class="td_brCom"><div align="center" > 
                      3
                        <input name="hidden" type="hidden" value=3>
                      </div></td>            
                    <td height="30" valign="middle"  class="td_brCom"><div align="center">农行</div></td>
                    <td valign="middle"  class="td_brCom"><div align="center">员工1</div></td>
                    <td valign="middle"  class="td_brCom"><div align="right">622260071000800328123</div></td>
                    <td valign="middle"  class="td_brCom"><div align="right">1000</div></td>
                    <td valign="middle"  class="td_brCom"><div align="right">300</div></td>
                    <td valign="middle"  class="td_brCom"><div align="center">员工4</div></td>
                    <td align="center" height="30"  valign="middle"  class="td_brCom"> 
                      <a href="bankAccount_edit.html">修  改</a> 
                       <a href="#">删  除</a> 
                  【<a href="bankAccount_br.html">查询明细</a>】  
                   </td>
                  	</tr>-->

                </table>
        </td>
      </tr>
       <tr height="10">
      <td height="10" align="right"> <br/><a href="bankAccount_add.html">添加银行账户</a></td>
    </tr>
	 
	   </table>
    </td>
      </tr>
    </table> 
</div>
</body>
</html>
