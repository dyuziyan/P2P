package my.comp.jms.logger.db;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import my.comp.dao.mybatis.BaseDaoImpl;
import my.comp.jms.logger.JmsLog;

public class JmsLogDaoImpl extends BaseDaoImpl<JmsLog> implements JmsLogDao {

	@Override
	public JmsLog getByBusiId(String busiType, String busiId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("busiType", busiType);
		params.put("busiId", busiId);

		return getSqlSession().selectOne(statement("getByBusiId"), params);

	}

	@Override
	public int logSendAfter(String sn, Date systime) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sn", sn);
		params.put("systime", systime);

		return getSqlSession().update(statement("logSendAfter"), params);
	}

	@Override
	public int logConsumeBefore(String sn, String clientId, Date systime) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sn", sn);
		params.put("clientId", clientId);
		params.put("systime", systime);

		return getSqlSession().update(statement("logConsumeBefore"), params);
	}

	@Override
	public int logConsumeAfter(String sn, String clientId, Date systime) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sn", sn);
		params.put("clientId", clientId);
		params.put("systime", systime);

		return getSqlSession().update(statement("logConsumeAfter"), params);
	}

	@Override
	public int logReSend(Long id, Date systime) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("systime", systime);

		return getSqlSession().update(statement("logReSend"), params);
	}

	private String statement(String statementId) {
		return JmsLogDao.class.getName() + "." + statementId;
	}

	@Override
	public JmsLog getBySn(String sn) {
		
		return getSqlSession().selectOne(statement("getBySn"), sn);
	}

}
