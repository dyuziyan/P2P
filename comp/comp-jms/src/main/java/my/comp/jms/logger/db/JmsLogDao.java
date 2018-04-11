package my.comp.jms.logger.db;

import java.util.Date;

import my.comp.jms.logger.JmsLog;

import org.apache.ibatis.annotations.Param;

public interface JmsLogDao {

	JmsLog get(long id);

	JmsLog getByBusiId(@Param("busiType") String busiType, @Param("busiId") String busiId);

	JmsLog getBySn(@Param("sn") String sn);

	int create(JmsLog jmsLog);

	int logSendAfter(@Param("sn") String sn, @Param("systime") Date systime);

	int logConsumeBefore(@Param("sn") String sn, @Param("clientId") String clientId, @Param("systime") Date systime);

	int logConsumeAfter(@Param("sn") String sn, @Param("clientId") String clientId, @Param("systime") Date systime);

	int logReSend(Long id, @Param("systime") Date systime);

}
