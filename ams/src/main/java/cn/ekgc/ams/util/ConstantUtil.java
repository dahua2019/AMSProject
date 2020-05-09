package cn.ekgc.ams.util;

import java.security.PublicKey;
import java.util.Properties;

/**
 * <b>系统常量</b>
 * @author Wang
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConstantUtil {
	private static final Properties props = new Properties();

	static {
		try {
			props.load(ConstantUtil.class.getClassLoader().getResourceAsStream("page.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <b>分页信息：当前页码</b>
	 */
	public static final Integer PAGE_NO = Integer.parseInt(props.getProperty("page.no"));

	/**
	 * <b>分页信息：当前页码</b>
	 */
	public static final Integer PAGE_SIZE = Integer.parseInt(props.getProperty("page.size"));

}
