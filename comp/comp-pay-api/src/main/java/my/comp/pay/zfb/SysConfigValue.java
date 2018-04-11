package my.comp.pay.zfb;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * @ClassName: SysConfigValue
 * @Desc: 系统加载初始化信息
 */
public class SysConfigValue
{

    public static Logger logger = Logger.getLogger(SysConfigValue.class.getName());

    /**
     * sysConfigValue
     */
    public static SysConfigValue sysConfigValue;

    /****************** 支付宝 支付相关信息 *******************************/

    /**
     * 支付宝支付
     */
    public static String ZFB_SERVICE;
    /**
     * 参数编码字符集
     */
    public static String ZFB_INPUT_CHARSET;
    /**
     * 签名方式 RSA2
     */
    public static String ZFB_SIGN_TYPE;
    /**
     * 支付接口 服务器异步通知页面路径
     */
    public static String ZFB_NOTIFY_UR;
    /**
     *  卖家支付宝账号
     */
    public static String ZFB_SELLER_ID;
    /**
     * 支付宝appId
     */
    public static String ZFB_PID; 

    /**
     * 商户的私钥 PKCS8 转码后的密钥
     */
    public static String SH_PRIVATE_KEY;
    /**
     * 支付宝的公钥，无需修改该值
     */
    public static String ALI_PUBLIC_KEY ;
    
    /**
     * 测试开关：1测试，0正式
     */
    public static int testSwitch = 1;

    /**
     * 初始化实例，并加载数据
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static SysConfigValue getInstance()
    {
        if (null == sysConfigValue)
        {
            sysConfigValue = new SysConfigValue();
            sysConfigValue.init();
        }
        return sysConfigValue;
    }
    

    /**
     * 描述： 初始化加载系统配置参数
     */
    private void init()
    {
        InputStream inStream = null;
        Properties props = new Properties();
        try
		{
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			//后期有多个支付宝账号的时候，新增多个properties文件，用你同实例来读取不同支付配置的properties
			Resource resource = resolver.getResource("mzcf_zfb.properties");
			InputStream in = new BufferedInputStream(resource.getInputStream());
			props.load(in);
			ZFB_SERVICE = props.getProperty("zfb_service");
			ZFB_INPUT_CHARSET = props.getProperty("zfb_input_charset");
			ZFB_SIGN_TYPE = props.getProperty("zfb_sign_type");
			ZFB_NOTIFY_UR = props.getProperty("zfb_notify_ur");
			ZFB_SELLER_ID = props.getProperty("zfb_seller_id");
			SH_PRIVATE_KEY = props.getProperty("sh_private_key");
			ALI_PUBLIC_KEY = props.getProperty("ali_public_key");
			ZFB_PID = props.getProperty("zfb_pid");
		}
        catch (Exception e)
        {
            logger.error(" system file init form IO is error ");
        }
        finally
        {
            if (null != inStream)
            {
                try
                {
                    inStream.close();
                }
                catch (IOException e)
                {
                    logger.error(" close InputStream is error ");
                }
            }
        }
    }
    
    
}
