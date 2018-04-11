package my.comp.dao.mybatis.result;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
/**
 * 
 * @author wy
 * mybatis return map
 * @param <K> Map 键类型 
 * @param <V> Map 值类型
 */
public class MapConvertResultHandler<K,V> implements ResultHandler {
	Map<K,V> result = new HashMap<K,V>();
	
	private String k,v;

	public void setK(String k) {
		this.k = k;
	}
	public void setV(String v) {
		this.v = v;
	}
	public MapConvertResultHandler(String k,String v) {
		super();
		setK(k);
		setV(v);
	}
	public Map<K, V> getResult() {
		return result;
	}
	@Override
	@SuppressWarnings("unchecked")
	public void handleResult(ResultContext rc) {
	    Map<String,Object> m = (Map<String,Object>)rc.getResultObject();
	    result.put((K)m.get(k),(V)m.get(v));
	}
	
	/*	example
	TestDao
	void test(ResultHandler handler);

	MapConvertResultHandler<String, Integer> handler = new MapConvertResultHandler<String, Integer>("localAlias", "num");
	testDao.test(handler);
	Map<String, Integer> map = handler.getResult();*/

}
