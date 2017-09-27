package com.sy.commons.entity;

import java.util.Map;
/*
 * The Result of DAO Service API execute
 */
public class HResult<T> {
	private Boolean result;
	private String value;
	private T objValue;

	private Map<String, Object> paramMap;

	
	public static HResult DEFAULT_OK = new HResult(true, "");

	public HResult(Boolean result, String value) {
		this.result = result;
		this.value = value == null ? "" : value;
	}

	public HResult(Boolean checkResult) {
		this.result = checkResult;
	}

	public HResult(Boolean result, String value, T objValue) {
		this.result = result;
		this.value = value == null ? "" : value;
		this.objValue = objValue;
	}

	public HResult(Boolean result, String value, T objValue,
			Map<String, Object> paramMap) {
		this.result = result;
		this.value = value == null ? "" : value;
		this.objValue = objValue;
		this.paramMap = paramMap;
	}

	public HResult(T objValue) {
		this.result = true;
		this.value = "";
		this.objValue = objValue;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public Boolean isOK() {
		return result;
	}

	public String getValue() {
		return value;
	}

	public T getObjValue() {
		return objValue;
	}

	public void setObjValue(T objValue) {
		this.objValue = objValue;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HResult [result=");
		builder.append(result);
		builder.append(", value=");
		builder.append(value);
		builder.append(", objValue=");
		builder.append(objValue);
		builder.append(", paramMap=");
		builder.append(paramMap);
		builder.append("]");
		return builder.toString();
	}
}
