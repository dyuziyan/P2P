package org.comp.vedio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class VedioUtil
{

    public static void main(String[] args)
    {
//        Date d = new Date();  
//        Calendar now = Calendar.getInstance();  
//        now.setTime(d);  
//        now.set(Calendar.HOUR, now.get(Calendar.HOUR) + 24);  
//        long a = now.getTimeInMillis();
//        String b = Long.toHexString(a);
       // System.out.println(getPushUrl("11212122","txrtmp", "11212122", 1469762325L));
    }

    private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

   /**
    * 计算推流地址
    * 描述：TODO
    * @author qingyan.wu 
    * @date 2018年3月26日上午11:20:27
    * @param key  推流防盗链 KEY
    * @param streamId 频道id
    * @param txTime 有效时间
    * @return
    */
    public static String getPushUrl(int bizid,String key, String streamId, long txTime)
    {
        String input = new StringBuilder().append(key).append(streamId).append(Long.toHexString(txTime).toUpperCase()).toString();

        String txSecret = null;//防盗链
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            txSecret = byteArrayToHexString(messageDigest.digest(input.getBytes("UTF-8")));
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
         txSecret = new StringBuilder().append("txSecret=").append(txSecret).append("&").append("txTime=").append(Long.toHexString(txTime).toUpperCase()).toString();
         return "rtmp://"+bizid+".livepush.myqcloud.com/live/"+streamId+"?"+txSecret;
    }

    private static String byteArrayToHexString(byte[] data)
    {
        char[] out = new char[data.length << 1];

        for (int i = 0, j = 0; i < data.length; i++)
        {
            out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS_LOWER[0x0F & data[i]];
        }
        return new String(out);
    }
    /**
     * 获取播放url 后续根据需求 目前就返回一种rtmp
     * 描述：TODO
     * @author qingyan.wu 
     * @date 2018年3月26日下午1:43:25
     * @param safeUrl
     * @return
     */
//    public static Map<String,String> getPlayUrl(int bizid, String streamId)
//    {
//        Map<String,String> playMap = new HashMap<String,String>();
//        String playUrl = bizid+".liveplay.myqcloud.com/live/"+streamId;  
//        playMap.put("rtmp", "rtmp://"+playUrl);
//        playMap.put("flv", "http://"+playUrl+".flv");
//        playMap.put("hls", "http://"+playUrl+".m3u8");
//        return playMap;
//    }
    public static String getPlayUrl(int bizid, String streamId)
    {
        String playUrl = bizid+".liveplay.myqcloud.com/live/"+streamId;        
        return "rtmp://"+playUrl;
    }
    /**
     * 发送get请求
     * 描述：TODO
     * @author qingyan.wu 
     * @date 2018年3月26日下午2:38:13
     * @param sendUrl
     * @param backEncodType
     * @return
     */
    public static String doAccessHTTPGet(String sendUrl, String backEncodType) {

        StringBuffer receive = new StringBuffer();
        BufferedReader in = null;
        try {
            if (backEncodType == null || backEncodType.equals("")) {
                backEncodType = "UTF-8";
            }

            URL url = new URL(sendUrl);
            HttpURLConnection URLConn = (HttpURLConnection) url
                    .openConnection();

            URLConn.setDoInput(true);
            URLConn.setDoOutput(true);
            URLConn.connect();
            URLConn.getOutputStream().flush();
            in = new BufferedReader(new InputStreamReader(URLConn
                    .getInputStream(), backEncodType));

            String line;
            while ((line = in.readLine()) != null) {
                receive.append(line).append("\r\n");
            }

        } catch (IOException e) {
            receive.append("访问产生了异常-->").append(e.getMessage());
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (java.io.IOException ex) {
                    ex.printStackTrace();
                }
                in = null;

            }
        }
        return receive.toString();
    }
    /**
     * 设置推流状态
     * 描述：TODO
     * @author qingyan.wu 
     * @date 2018年3月26日下午2:43:14
     * @param apiUrl
     * @param appid
     * @param interfacename
     * @param time
     * @param sign
     * @param channel_id
     * @param status
     * @return
     */
    public static String setStatus(String apiUrl,String appid,String interfacename,
        long time,String sign,String channel_id,int status){
        StringBuffer url = new StringBuffer();
        url.append(apiUrl);
        url.append("?appid="+appid);
        url.append("&interface="+interfacename);
        url.append("&Param.s.channel_id="+channel_id);
        url.append("&Param.n.status="+status);
        url.append("&t="+time+"&sign="+sign);
        return doAccessHTTPGet(url.toString(),"UTF-8");
    }
    /**
     * 查询录制文件列表/直播状态  根据interfacename
     * 描述：TODO
     * @author qingyan.wu 
     * @date 2018年3月26日下午2:51:21
     * @param apiUrl
     * @param appid
     * @param interfacename
     * @param time
     * @param sign
     * @param channel_id
     * @return
     */
    public static String getLiveShowInfo(String apiUrl,String appid,String interfacename,
        long time,String sign,String channel_id){
        StringBuffer url = new StringBuffer();
        url.append(apiUrl);
        url.append("?appid="+appid);
        url.append("&interface="+interfacename);
        url.append("&Param.s.channel_id="+channel_id);
        url.append("&t="+time+"&sign="+sign);
        return doAccessHTTPGet(url.toString(),"UTF-8");
    }
}