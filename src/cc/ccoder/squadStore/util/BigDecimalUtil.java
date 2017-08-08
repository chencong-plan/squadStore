package cc.ccoder.squadStore.util;

import java.math.BigDecimal;

/**
 * 
 * @author chencong
 * @Time 2017年8月7日 上午8:59:25
 * @TODO 封装double浮点计算
 */

public class BigDecimalUtil {

	/**
	 * 私有化构造器，不允许外部实例化对象
	 */
	private BigDecimalUtil() {

	}

	/**
	 * 加法运算，参数为double
	 * 
	 * @param v1
	 *            加数
	 * @param v2
	 *            被加数
	 * @return 返回v1+v2
	 */
	public static BigDecimal add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2);
	}

}
