/**  
* @Title: ConfigUtil.java
* @Package com.jushi.test
* @Description: TODO(用一句话描述该文件做什么)
* @author johnny
* @date 2017年5月15日 下午7:25:48
* @version V1.0  
*/
package com.wx.depotbank.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigUtil
{

    private static final String fileName = "bank.properties";

    public static byte[] getFileByte(String pro) throws IOException
    {
        byte[] b = null;
        InputStream in = null;
        try
        {
            String filepath = getPro(pro);
            if (null == filepath)
                throw new RuntimeException("没有找到配置信息" + pro);
            in = new FileInputStream(new File(filepath));
            b = new byte[20480];
            in.read(b);
        }
        finally
        {
            if (null != in)
                in.close();
        }
        return b;
    }

    public static String getKey(String filepath) throws Exception
    {
        BufferedReader br = null;
        try
        {
            FileInputStream  fis = new FileInputStream(filepath);
            br = new BufferedReader(new InputStreamReader(fis));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null)
            {
                if (readLine.charAt(0) == '-')
                {
                    continue;
                }
                else
                {
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            return sb.toString();
        }
        catch (IOException e)
        {
            throw new Exception("数据流读取错误");
        }
        catch (NullPointerException e)
        {
            throw new Exception("输入流为空");
        }
        finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                }
            }
        }
    }

    public static String getPro(String pro)
    {
        InputStream in = null;
        try
        {
            Properties prop = new Properties();
            in = ConfigUtil.class.getClassLoader().getResourceAsStream(fileName);
            if (null == in)
                throw new RuntimeException("没有找到配置文件" + fileName);
            prop.load(in);
            in.close();
            String str = prop.getProperty(pro).trim();
            return str;
        }
        catch (Exception ex)
        {
            RuntimeException rex = new RuntimeException();
            throw rex;
        }
        finally
        {
            if (null != in)
                try
                {
                    in.close();
                }
                catch (Exception ex)
                {
                    RuntimeException rex = new RuntimeException(ex.getMessage());
                    rex.setStackTrace(ex.getStackTrace());
                    throw rex;
                }
        }
    }
}