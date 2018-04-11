package my.comp.jms.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class InetAddressUtils {

	public static String getLocalHostAdress() {
		return getIp();
	}

	public static void main(String[] args) {
		System.out.print(getIp());
	}
	
	/**
	 * 多IP处理，可以得到最终ip
	 * @return
	 */
	private static String getIp() {
		String localip = null;// 本地IP，如果没有配置外网IP则返回它
		String netip = null;// 外网IP
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface
					.getNetworkInterfaces();
			InetAddress ip = null;
			boolean finded = false;// 是否找到外网IP
			while (netInterfaces.hasMoreElements() && !finded) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> address = ni.getInetAddresses();
				while (address.hasMoreElements()) {
					ip = address.nextElement();
					// System.out.println(ni.getName() + ";" + ip.getHostAddress()
					// + ";ip.isSiteLocalAddress()="
					// + ip.isSiteLocalAddress()
					// + ";ip.isLoopbackAddress()="
					// + ip.isLoopbackAddress());
					if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
							&& ip.getHostAddress().indexOf(":") == -1) {// 外网IP
						netip = ip.getHostAddress();
						finded = true;
						break;
					} else if (ip.isSiteLocalAddress()
							&& !ip.isLoopbackAddress()
							&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP
						localip = ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}
}
