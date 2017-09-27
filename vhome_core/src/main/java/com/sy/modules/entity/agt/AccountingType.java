package com.sy.modules.entity.agt;

public enum AccountingType {
	银行入账(1),订单出账(2),订单撤销(3);
	
	public static int getIntType(String type){
		AccountingType t = null;
		try {
			t = AccountingType.valueOf(type);
		} catch (IllegalArgumentException e) {
			return -1;
		}
		return t.getType();
	}
	
	public static AccountingType getStringType(int type){
		switch(type){
		case 1:
			return AccountingType.银行入账;
		case 2:
			return AccountingType.订单出账;
		default:
			return AccountingType.订单撤销;
		}
	}
	
	private int type;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	private AccountingType(int type){
		this.type = type;
	}
	
}
