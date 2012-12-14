/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.myilike.Beans;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Akers
 */
public class IlikeMessages implements Externalizable
{
    private String msgType;
    private String msgText;
    private String callBackURL;
    private String callBackName;
    private int delayTime;
    private HttpServletRequest request;
    private HttpServletResponse respose;
    
    //构造方法：
    public IlikeMessages()
    {this(null, null, "", "", "", "", 0);}
    
    public IlikeMessages(HttpServletRequest req, HttpServletResponse resp)
    {this(req, resp, "", "", "", "", 0);}
    
    public IlikeMessages(String msgType, String msgText, String callBackURL, String callBackName, int delayTime)
    {this(null, null, msgType, msgText, callBackURL, callBackName, delayTime);}
    
    public IlikeMessages(HttpServletRequest req, HttpServletResponse resp, String msgType, String msgText, String callBackURL, String callBackName, int delayTime)
    {
        this.request = req; 
        this.respose = resp;
        this.setMsgText(msgText);
        this.setCallBackName(callBackName);
        this.setDelayTime(delayTime);
        this.setCallBackURL(callBackURL);
        this.setMsgType(msgType);
    }
    
    //实现序列化方法
    @Override//实现序列化数据读取
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        msgType = (String)in.readObject();
        msgText = (String)in.readObject();
        callBackURL = (String)in.readObject();
        callBackName = (String)in.readObject();
        delayTime = in.readInt();
    }

    @Override//实现序列化数据写入
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(msgType);
        out.writeObject(msgText);
        out.writeObject(callBackURL);
        out.writeObject(callBackName);
        out.writeInt(delayTime);
    }

    //Actions：
    //设置消息属性
    public void setMessage(String msgType, String msgText, String callBackURL, String callBackName, int delayTime)
    {
        this.setMsgText(msgText);
        this.setCallBackName(callBackName);
        this.setDelayTime(delayTime);
        this.setCallBackURL(callBackURL);
        this.setMsgType(msgType);
    }
    
    //发送通用消息模板格式化的消息
    public void sendMsg(HttpServletRequest req, HttpServletResponse resp, String msgType, String msgText, String callBackURL, String callBackName, int delayTime) throws ServletException, IOException 
    {
        this.setMessage(msgType, msgText, callBackURL, callBackName, delayTime);
        req.getSession().setAttribute("msg", this);
        resp.setCharacterEncoding("utf-8");
        resp.sendRedirect("msg.jsp");
    }
    public void sendMsg(String msgType, String msgText, String callBackURL, String callBackName, int delayTime) throws ServletException, IOException 
    {
        this.setMessage(msgType, msgText, callBackURL, callBackName, delayTime);
        this.request.getSession().setAttribute("msg", this);
        this.respose.setCharacterEncoding("utf-8");
        this.respose.sendRedirect("msg.jsp");
    }
    
    //发送非法访问消息：
    public void illegalRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
    {
        this.setMessage("sysMsg", "非法访问，可能是您未登录或者登录错误，请返回登录页面正常登录！！", "login.jsp", "登录页面", 3);
        req.getSession().setAttribute("msg", this);
        resp.setCharacterEncoding("utf-8");
        resp.sendRedirect("msg.jsp");
    }
    public void illegalRequest() throws ServletException, IOException 
    {
        this.setMessage("sysMsg", "非法访问，可能是您未登录或者登录错误，请返回登录页面正常登录！！", "login.jsp", "登录页面", 3);
        this.request.getSession().setAttribute("msg", this);
        this.respose.setCharacterEncoding("utf-8");
        this.respose.sendRedirect("msg.jsp");
    }
    
    
    //getter and setters
    public String getMsgType() {
        return msgType;
    }

    public String getMsgText() {
        return msgText;
    }

    public String getCallBackURL() {
        return callBackURL;
    }

    public String getCallBackName() {
        return callBackName;
    }

    public int getDelayTime() {
        return delayTime;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public void setCallBackURL(String callBackURL) {
        this.callBackURL = callBackURL;
    }

    public void setCallBackName(String callBackName) {
        this.callBackName = callBackName;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

  
    
    
    
    
}
