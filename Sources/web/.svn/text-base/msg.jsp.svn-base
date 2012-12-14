<%-- 
    Document   : msg.jsp，用于格式化输出各种页面信息
    Created on : 2011-11-20, 20:20:28
    Author     : Akers
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <script src="js/jquery-1.6.2.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            var time = ${sessionScope.msg.delayTime == 0 ? 10 : sessionScope.msg.delayTime};
            $(document).ready(function() {  
                $("#timerText").html(time);
                function jump(count) {  
                    window.setTimeout(function(){  
                        count--;  
                        if(count > 0) {  
//                            $('#num').attr('innerHTML', count);
                            $("#timerText").html(count);
                            jump(count);  
                        } else {  
                            location.href="${sessionScope.msg.callBackURL}";  
                        }  
                    }, 1000);  
                }  
                jump(time);  
            });  
//            function redirect(){ 
//                window.location = "${sessionScope.msg.callBackURL}"; 
//            } 
//            var i = 0; 
//            function dis(){ 
//                $("#timerText").html(time - i);
//                i++; 
//            } 
//            timer=setInterval('dis()', 1000);//显示时间 
//            timer=setTimeout('redirect()',time * 1000); //跳转 
        </script>
        <link type="text/css" href="css/base.css" rel="stylesheet" />
        <title>System Messages</title>
    </head>
    <body>
         <div id="msgMain">
            <div id="msgBox">
                <div id="msgTitle">
                    <span>
                    <c:if test="${sessionScope.msg.msgType == 'sysMsg'}">
                        系统消息：
                    </c:if>
                    <c:if test="${sessionScope.msg.msgType == 'sucMsg'}">
                        操作成功！！
                    </c:if>
                    <c:if test="${sessionScope.msg.msgType == 'errMsg'}">
                        出错啦！！
                    </c:if>
                    </span>
                </div>
                <div id="msgText">
                    <c:out value="${sessionScope.msg.msgText}" escapeXml="false" />
                    <br />
                    将于<span id="timerText"></span>秒后返回
                    <a href="${sessionScope.msg.callBackURL}"><c:out value="${sessionScope.msg.callBackName}" escapeXml="false" /></a>
                </div>
            </div>
        </div>
    </body>
</html>
