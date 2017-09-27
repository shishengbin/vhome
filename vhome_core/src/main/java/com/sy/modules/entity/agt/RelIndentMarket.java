package com.sy.modules.entity.agt;


public class RelIndentMarket {
	private Long id;
	private Long indentId;
	private Long marketId;
	public RelIndentMarket(Long indentId,Long marketId){
		this.indentId = indentId;this.marketId = marketId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIndentId() {
		return indentId;
	}
	public void setIndentId(Long indentId) {
		this.indentId = indentId;
	}
	public Long getMarketId() {
		return marketId;
	}
	public void setMarketId(Long marketId) {
		this.marketId = marketId;
	}
	
}
