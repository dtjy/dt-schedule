/**
 * @author dtjy
 * 2018-09-20 19:57
 */
package com.dtjy.core;

/**
 * @author dtjy
 * 2018-09-20 19:57
 */
public class Result {
	
	private Object data;
	
	private String message;
	//状态码
	private String code;
	
	public Result() {
		super();
	}

	public Result(Object data) {
		super();
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
