package com.sy.modules.entity.agt;
/**
 * 订单类型
 * @describe  
 * @author LiuCheng
 * 2013年10月16日 下午1:52:05
 * @version v1.0
 */
public enum IndentType {
	主订单(0),附加订单(1);
	
	public static int getIntType(String ind) {
		IndentType in = null;
		try {
			in = IndentType.valueOf(ind);
		} catch (IllegalArgumentException e) {
			return -1;
		}
		return in.getIt();
	}
	
	public static IndentType getType(int ii) {
		if (ii == 0)
			return IndentType.主订单;
		else
			return IndentType.附加订单;
	}

	private int it;

	private IndentType(int i) {
		this.it = i;
	}

	public int getIt() {
		return it;
	}

	public void setIt(int it) {
		this.it = it;
	}
}
