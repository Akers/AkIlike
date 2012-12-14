<%@ page contentType="text/html; charset=utf8" %>
<html>
<head>

<title>登陆</title>
<style type="text/css">
<!--
.STYLE1 {
	color: #FFCC33;
	font-size: 14px;
}
-->
</style>
<link href="Css/style.css" rel="stylesheet" type="text/css">
<link href="Css/styleleft.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"><style type="text/css">
<!--
body {
	margin-top: 200px;
}
-->
</style></head>
<body>
<form name="form" method="post" action="login">
    <table width="500" border="0" align="center"  cellpadding="0" cellspacing="0" bgcolor="#F5f7f7">
        <tr align="center">
            <td height="27" colspan="2" bgcolor="#21536A" class="STYLE1"><strong><font size="+3"> 员工登陆</font></strong></td>
        </tr>
        <tr>
            <td width="200" height="22" align="center" bgcolor="#F5F7F7">用&nbsp;户&nbsp;名：</td>
            <td width="300" bgcolor="#F5F7F7">
                <input  type="text" name="adminID" />
            </td>
        </tr>
        <tr>
            <td height="22" align="center" bgcolor="#F5F7F7">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
            <td bgcolor="#F5F7F7"><input type="password" name="adminPswd"></td>
        </tr>
        <tr align="center">
            <td height="35" colspan="2" bgcolor="#F5F7F7">
            <input type="submit" name="Submit" value="登录" />
            &nbsp;&nbsp;
            <input type="reset" name="Reset" value="重置" /></td>
        </tr>
        <tr>
            <td height="22" align="center" bgcolor="#F5F7F7"></td>
            <td bgcolor="#F5F7F7"><font color="red" bold>  
            <%//验证反馈信息
            int errno = request.getParameter("errno") == null ? 0 : Integer.parseInt(request.getParameter("errno"));
            if(errno == 201)
            out.print("用户不存在！！");
            if(errno == 101)
            out.print("用户密码错误！！");
            %>
            </font></td>
        </tr>
    </table>
</form>
</body>
</html>
