package com.wx.market ;

import java.util.HashMap;
import java.util.Map;

import com.wx.account.dto.AccountDto;
import com.wx.product.dto.ProductDto;
import com.wx.trade.dto.OrderDto;

public class BusiContextImpl {

	private ThreadLocal<Map<String, Object>> context;
//	private static final String BASE = "_investor_base_info_";
	private static final String ACCOUNT = "_investor_account_info_";
	private static final String PRODUCT = "_borrow_pRODUCT_info_";
	private static final String ORDER = "_tender_order_info_";

	public BusiContextImpl() {
		context = new ThreadLocal<Map<String, Object>>();
	}

//	public void setBase(MemberDto base) {
//		getOrCreateVal().put(BASE, base);
//	}
//	public MemberDto getBase() {
//		return get(BASE, MemberDto.class);
//	}

	public void setAccount(AccountDto account) {
		getOrCreateVal().put(ACCOUNT, account);
	}
	public AccountDto getAccount() {
		return get(ACCOUNT, AccountDto.class);
	}
	
	public void setProduct(ProductDto product) {
		getOrCreateVal().put(PRODUCT, product);
	}
	public ProductDto getProduct() {
		return get(PRODUCT, ProductDto.class);
	}

	public void setOrder(OrderDto order) {
		getOrCreateVal().put(ORDER, order);
	}
	public OrderDto getOrder() {
		return get(ORDER, OrderDto.class);
	}

	public void set(String key, Object value) {
		getOrCreateVal().put(key, value);
	}

	protected Map<String, Object> getOrCreateVal() {
		if (context.get() == null) {
			context.set(new HashMap<String, Object>());
		}
		return context.get();
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String key, Class<T> clazz) {
		Map<String, Object> map = context.get();
		if (map == null) return null;
		return (T) map.get(key);
	}

	public void clear() {
		context.remove();
	}

	static BusiContextImpl impl = new BusiContextImpl();

	static class ContextThread extends Thread {

		private int id;

		public ContextThread(int id) {
			this.id = id;
		}

		public ContextThread() {

		}

		public void run() {
			impl.set("ID", id);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(id + "\t" + impl.get("ID", Integer.class));

		}
	}

}
