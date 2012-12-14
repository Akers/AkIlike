<%-- 
    Document   : bankAccount_edit
    Created on : 2011-12-6, 17:48:49
    Author     : Akers
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title>修改银行账户</title>
    <link href="../../../css/styleform.css" rel="stylesheet" type="text/css" />
    <link href="../../../css/stylemain.css" rel="stylesheet" type="text/css" />
    <link href="../../../css/styletable.css" rel="stylesheet" type="text/css" />
    <link href="../../../css/styletext.css" rel="stylesheet" type="text/css" />
    <script src="../../../js/jquery-1.6.2.min.js" type="text/javascript"></script>
    <script src="../../../js/edit.js" type="text/javascript"></script>
</head>

<body topmargin="20">

<div align=center class="text">   
<table width="600" height="369"  border="0" cellpadding="0" cellspacing="0" >

  <tr>
    <td width="100%" >
	<div align="center" >
          <p class="textTitle">修改银行账户</p> 
        </div>	 
     <hr color="#6FB7B7">	  </td>
  </tr>
  
  <tr>
    <td height="345"  valign="top">
<form action="../../../bankaccounts" name="form1" method="post">
    <input type="hidden" name="m" value="up" />
    <input type="hidden" name="aid" value="${sessionScope.account.bankAccountID}"/>
          <table width="500"  border="0" cellpadding="2" cellspacing="0" bordercolor="#CCCCCC">           
           <tr> 
              <td height="32"><div align="right">银行:</div></td>
              <td> 
                  
                  <input class="inputBox" name="bankName" type="text" size="30" id="txt_bankName" value="${sessionScope.account.bankName}"/>
                  <div class="errMsgBox" style="display: none; "><img src="../../../images/img_error.jpg" /><span></span></div>
                  <div class="curMsgBox" style="display: none; "><img src="../../../images/img_currect.jpg" /></div>
              </td>
            </tr>
            
              <tr>
              <td height="32"><div align="right">户名:</div></td>
              <td>
                <input class="inputBox" name="bankAccountName" type="text"  size="30" id="txt_accountName" value="${sessionScope.account.bankAccountName}" />
                <div class="errMsgBox" style="display: none; "><img src="../../../images/img_error.jpg" /><span></span></div>
                <div class="curMsgBox" style="display: none; "><img src="../../../images/img_currect.jpg" /></div>
              </td>
            </tr>
            
            <tr> 
              <td height="32"><div align="right">账号:</div></td>
              <td>
                <input class="inputBox" name="bankAccount" type="text"  size="45" id="txt_bankAccount" value="${sessionScope.account.bankAccount}" />
                <div class="errMsgBox" style="display: none; "><img src="../../../images/img_error.jpg" /><span></span></div>
                <div class="curMsgBox" style="display: none; "><img src="../../../images/img_currect.jpg" /></div>
              </td>
            </tr>
            
            
              <tr> 
              <td height="32"><div align="right">账户余额:</div></td>
              <td>
                  <input class="inputBox" name="bankAccountBalance" type="text"  size="30" id="txt_bankBalance" value="${sessionScope.account.bankAccountBalance}" />
                  <div class="errMsgBox" style="display: none; "><img src="../../../images/img_error.jpg" /><span></span></div>
                  <div class="curMsgBox" style="display: none; "><img src="../../../images/img_currect.jpg" /></div>
              </td>
            </tr>

              <tr> 
              <td height="32"><div align="right">现金余额:</div></td>
              <td>
                <input  class="inputBox" name="bankAccountCashBalance" type="text"  size="30"  id="txt_bankAccountCashBalance" value="${sessionScope.account.bankAccountCashBalance}" />
                 <div class="errMsgBox" style="display: none; "><img src="../../../images/img_error.jpg" /><span></span></div>
                 <div class="curMsgBox" style="display: none; "><img src="../../../images/img_currect.jpg" /></div>
              </td>
            </tr>
            
            
            
            <tr> 
              <td height="32"><div align="right">使用者:</div></td>
              <td> <select name="employeeID" class="list">           		
                      <c:forEach var="employee" items="${sessionScope.employees}">
                          <option value="${employee.userID}" ${employee.userID == sessionScope.account.employeeID ? "selected='selected'" : ""}><c:out value="${employee.userName}"/></option>
                      </c:forEach>
                </select>
              </td>
            </tr>
            <tr> 
              <td height="61" colspan="2"><label> <div align="center"> 
                  <input name="Submit" type="submit" class="buttonCom" value="  保  存  " onClick="return checkForm();" />
				  <a href="bankAccounts_m.html" class="buttonCom">  返    回   </a>
                </div></label>
                <label></label></td>
            </tr>
          </table>
</form>	  </td>
    </tr>

  </table>

   

<blockquote>&nbsp;</blockquote>
</div>
</body>
</html>

