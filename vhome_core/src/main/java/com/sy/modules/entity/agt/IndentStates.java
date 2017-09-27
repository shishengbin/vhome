package com.sy.modules.entity.agt;

public enum IndentStates {
	审核中(0),已通过(1),已撤销(2),未通过(3);
	
	private int states;
	private IndentStates(int states){
		this.states = states;
	}
	
	public int getStates() {
		return states;
	}

	public void setStates(int states) {
		this.states = states;
	}
	
	public static int getIntStates(String is){
		IndentStates indents = null;
		try {
			IndentStates.valueOf(is);
		} catch (IllegalArgumentException e) {
			return -1;
		}
		return indents.getStates();
	}
	public static IndentStates getStringStates(int ii){
		switch(ii){
		case 0:
			return IndentStates.审核中;
		case 1:
			return IndentStates.已通过;
		case 2:
			return IndentStates.已撤销;
		default:
			return IndentStates.未通过;
		}
	}
}
