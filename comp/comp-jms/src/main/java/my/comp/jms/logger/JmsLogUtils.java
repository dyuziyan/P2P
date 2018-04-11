package my.comp.jms.logger;

import java.util.Date;

import my.comp.jms.DestType;
import my.comp.sn.SerialNumberable;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JmsLogUtils {
	public static final JmsLog create(DestType destType, String destName, SerialNumberable logmsg) {
		JmsLog jmslog = new JmsLog();
		jmslog.setSn(logmsg.getSn());
		jmslog.setDestType(destType);
		jmslog.setDestName(destName);
		jmslog.setMsgClass(logmsg.getClass().getName());
		jmslog.setMsgData(createMsgData(logmsg));
		jmslog.setState(JmsLog.State.SENDED);
		jmslog.setSendCount(0);
		jmslog.setConsumeCount(0);
		jmslog.setCreatedDate(new Date());
		jmslog.setLastModifiedDate(new Date());
		return jmslog;

	}

	public static final String createMsgData(SerialNumberable logmsg) {
		return JSONObject.toJSONString(logmsg, SerializerFeature.WriteClassName);
	}
	
}
