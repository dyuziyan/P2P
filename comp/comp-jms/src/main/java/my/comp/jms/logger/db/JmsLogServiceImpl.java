package my.comp.jms.logger.db;

import java.util.Date;

import my.comp.jms.DestType;
import my.comp.jms.JmsException;
import my.comp.jms.logger.JmsLog;
import my.comp.jms.logger.JmsLog.State;
import my.comp.jms.logger.JmsLogService;
import my.comp.jms.logger.JmsLogUtils;
import my.comp.jms.utils.InetAddressUtils;
import my.comp.sn.SerialNumberable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 将消息日志存储在数据库
 */
public class JmsLogServiceImpl implements JmsLogService {

	private final static Logger logger = LoggerFactory.getLogger(JmsLogServiceImpl.class);

	private JmsLogDao jmsLogDao;

	public void setJmsLogDao(JmsLogDao jmsLogDao) {
		this.jmsLogDao = jmsLogDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Throwable.class)
	public void logSendBefore(DestType destType, String destName, SerialNumberable logmsg) throws JmsException {
		JmsLog jmsLog = JmsLogUtils.create(destType, destName, logmsg);
		logger.debug("新增消息日志：destType={}，destName={}，sn={}", destType, destName, logmsg.getSn());
		jmsLogDao.create(jmsLog);

	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Throwable.class)
	public void logSendAfter(SerialNumberable logmsg) throws JmsException {
		jmsLogDao.logSendAfter(logmsg.getSn(), new Date());
	}

	@Override
	public void logConsumeBefore(SerialNumberable logmsg) throws JmsException {
		String clientId = InetAddressUtils.getLocalHostAdress();
		int count = jmsLogDao.logConsumeBefore(logmsg.getSn(), clientId, new Date());
		if (count == 0) throw new JmsException("该消息已在处理中：sn={0}", logmsg.getSn());
	}

	@Override
	public void logConsumeAfter(SerialNumberable logmsg) throws JmsException {
		String clientId = InetAddressUtils.getLocalHostAdress();
		jmsLogDao.logConsumeAfter(logmsg.getSn(), clientId, new Date());
	}

	@Override
	public JmsLog logReSend(long jmsLogId) throws JmsException {
		JmsLog jmslog = jmsLogDao.get(jmsLogId);
		if (jmslog == null) throw new JmsException("消息日志不存在：id={0}", jmsLogId);

		State state = jmslog.getState();
		if (state == State.SENDED) {
			int count = jmsLogDao.logReSend(jmslog.getId(), new Date());
			if (count == 0) throw new JmsException("消息日志[{0}]：重发失败。");

		} else if (state == State.RESENDED || state == State.CONSUMING) {
			throw new JmsException("消息日志[{0}]状态为{1}，请检查相关业务，并将状态置为{2}，再重新发送。", //
					jmslog.getId(), jmslog.getState(), State.SENDED);
		} else {
			throw new JmsException("消息日志[{0}]状态为{1}，不支持重新发送。", jmslog.getId(), jmslog.getState());
		}

		return jmslog;
	}

}
