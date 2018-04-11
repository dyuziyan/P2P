package com.wx.carloadmessage.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Convert
{
  public static int strToInt(String str, int defaultValue)
  {
    defaultValue = defaultValue;
    try
    {
      defaultValue = Integer.parseInt(str);
    }
    catch (Exception localException)
    {
    }
    return defaultValue;
  }

  public static long strToLong(String str, long defaultValue)
  {
    long l = defaultValue;
    try
    {
      l = Long.parseLong(str);
    }
    catch (Exception localException)
    {
    }
    return l;
  }

  public static float strToFloat(String str, float defaultValue)
  {
    defaultValue = defaultValue;
    try
    {
      defaultValue = Float.parseFloat(str);
    }
    catch (Exception localException)
    {
    }
    return defaultValue;
  }

  public static double strToDouble(String str, double defaultValue)
  {
    double d = defaultValue;
    try
    {
      d = Double.parseDouble(str);
    }
    catch (Exception localException)
    {
    }
    return d;
  }

  public static boolean strToBoolean(String str, boolean defaultValue)
  {
    defaultValue = defaultValue;
    try
    {
      defaultValue = Boolean.parseBoolean(str);
    }
    catch (Exception localException)
    {
    }
    return defaultValue;
  }

  public static java.util.Date strToDate(String str, java.util.Date defaultValue)
  {
    return strToDate(str, "yyyy-MM-dd HH:mm:ss", defaultValue);
  }

  public static java.util.Date strToDate(String str, String format, java.util.Date defaultValue)
  {
    defaultValue = defaultValue;
    SimpleDateFormat sdFormat = new SimpleDateFormat(format);
    try
    {
      defaultValue = sdFormat.parse(str);
    }
    catch (Exception localException)
    {
    }
    return defaultValue;
  }

  public static String dateToStr(java.util.Date date, String defaultValue)
  {
    return dateToStr(date, "yyyy-MM-dd HH:mm:ss", defaultValue);
  }

  public static String dateToStr(java.util.Date date, String format, String defaultValue)
  {
    defaultValue = defaultValue;
    SimpleDateFormat sdFormat = new SimpleDateFormat(format);
    try
    {
      defaultValue = sdFormat.format(date);
    }
    catch (Exception localException)
    {
    }
    return defaultValue;
  }

  public static String strToStr(String str, String defaultValue)
  {
    defaultValue = defaultValue;
    if ((str != null) && (!str.isEmpty()))
      defaultValue = str;
    return defaultValue;
  }

  public static java.sql.Date dateToSqlDate(java.util.Date date)
  {
    return new java.sql.Date(date.getTime());
  }

  public static java.util.Date sqlDateToDate(java.sql.Date date)
  {
    return new java.util.Date(date.getTime());
  }

  public static Timestamp dateToSqlTimestamp(java.util.Date date)
  {
    return new Timestamp(date.getTime());
  }

  public static java.util.Date qlTimestampToDate(Timestamp date)
  {
    return new java.util.Date(date.getTime());
  }

  public static int strtoAsc(String st)
  {
	  
    return (st.getBytes())[0];
  }

  public static char intToChar(int backnum)
  {
    return (char)backnum;
  }
}

