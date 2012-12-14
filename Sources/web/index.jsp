<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" %>

 <c:if test="${empty sessionScope.adminName}">
    <c:redirect url="/login.jsp" />
</c:if>
<html>
<head>

<title>ILIKE</title>
</head>

<frameset name="head_main" rows="60,18,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="top.html" name="top" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
<frame border=0 name="head_bar" marginWidth=0 borderColor=#e7e7e7 marginHeight=0 src="head_bar.jsp" frameBorder=0 noResize scrolling=no>
 <frameset name="menu_main" cols=200,8,* bordercolor="#3164FE">
    <frame src="left.html" name="left" scrolling="yes" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frame border=0 name="bar" marginWidth=0 borderColor=#e7e7e7 marginHeight=0 src="left_bar.htm" frameBorder=0 noResize scrolling=no>
    <frame src="mainMessage.htm" name="main" id="mainFrame" title="" />
  </frameset>
</frameset>
<noframes>
    <body>
    </body>
</noframes>
</html>
