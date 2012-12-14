/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.myilike.Beans;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 *
 * @author Administrator
 */
public class Remark implements Externalizable
{
    private String unFormatString;//没格式化的
    private String remarkName;//人名
    private String remarkText;//正文
    private String remarkSp = ",";//分隔符
    
    public Remark(){this("");}
    public Remark(String ufs)
    {
        this.unFormatString = ufs;
    }
    /**
     * 
     * @param format "n,t"表示字符串格式为"name,text"
     */
    public void formatRemark(String format)
    {
        String[] s = format.split(",");
        String[] splitedText = this.getUnFormatString().split(this.getRemarkSp());
        if(unFormatString.contains(","))
        {
            if(s[0].equals("n"))
            {
                this.setRemarkName(splitedText[0]);
                this.setRemarkText(splitedText[1]);
            }
            else if(s[1].equals("n"))
            {
                this.setRemarkName(splitedText[1]);
                this.setRemarkText(splitedText[0]);
            }
        }
        else
        {
            this.setRemarkName("");
            this.setRemarkText(unFormatString);
        }
    }

    public String getUnFormatString()
    {
        return unFormatString;
    }

    public String getRemarkName()
    {
        return remarkName;
    }

    public String getRemarkText()
    {
        return remarkText;
    }

    public String getRemarkSp()
    {
        return remarkSp;
    }

    public void setUnFormatString(String unFormatString)
    {
        this.unFormatString = unFormatString;
    }

    public void setRemarkName(String remarkName)
    {
        this.remarkName = remarkName;
    }

    public void setRemarkText(String remarkText)
    {
        this.remarkText = remarkText;
    }

    public void setRemarkSp(String remarkSp)
    {
        this.remarkSp = remarkSp;
    }
    
    
    //实现序列化方法
    @Override//实现序列化数据读取
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException 
    {
        unFormatString = (String)in.readObject();
        remarkName = (String)in.readObject();
        remarkText = (String)in.readObject();
        remarkSp = (String)in.readObject();
    }

    @Override//实现序列化数据写入
    public void writeExternal(ObjectOutput out) throws IOException 
    {
        out.writeObject(unFormatString);
        out.writeObject(remarkName);
        out.writeObject(remarkText);
        out.writeObject(remarkSp);
    }
}
