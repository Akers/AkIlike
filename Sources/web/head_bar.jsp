<%@ page contentType="text/html; charset=utf8" %>
<%--
 if(session.getAttribute("adminName")==null){
 	response.sendRedirect("login.jsp");
 }
 --%>
<html>
<head>
	<title>head</title>
<link href="css/stylemain.css" rel="stylesheet" type="text/css" />
	<style>
		.jd{
			font-family : Webdings;
			color : #FFFFFF;
			cursor : hand;
		}
	</style>
	<script language="JavaScript" type="text/javascript">
		var timeid = null;
		function showtime(){
			var now = new Date();
			var hours = now.getHours();
			var minutes = now.getMinutes();
			var seconds = now.getSeconds();
			timevalue=" " + ((hours>12)?hours-12:hours);
			timevalue += ((minutes<10)?":0":":") + minutes;
			timevalue += ((seconds<10)?":0":":") + seconds;
			timevalue = ((hours>12)?" 下午:":" 上午:") + timevalue;
			mytimer.innerHTML = "<font color=#FFFFFF><nobr>&nbsp;" + timevalue + "&nbsp;</nobr></font>";			
			timeid = setTimeout("showtime()",1000);
		}
	</script>
</head>
<body onLoad="showtime()" bgcolor="#669999">
<a title="全屏/恢复">
<table border=0 cellpadding=0 cellspacing=0 width="100%">
<tr>
	<td width="1%" onClick="return FullScr();"><div id="h" class="jd">1</div></td>
	<td width="97%" align="left" onClick="return FullScr();">
	<font color="white">
	<!--显示管理员姓名   -->
    ${sessionScope.adminName}&nbsp;&nbsp;&nbsp;&nbsp;欢迎您
	</font></td>
	<td width="1%" align="right"><img src="images/top/clock.gif" width="11" height="11" border="0"></td>
	<td width="1%" align="right"><div id="mytimer"></div></td>
</tr>
</table>
</a>
<SCRIPT language=JavaScript type=text/javascript>
	function FullScr(){
		var hc = h.innerHTML;
		if(hc == '1'){
			parent.head_main.rows = '0,18,*';
			h.innerHTML = '2';
		}else{
			parent.head_main.rows = '60,18,*';
			h.innerHTML = '1';
		}
		return false;
	}
</SCRIPT>
</body>
</html>
