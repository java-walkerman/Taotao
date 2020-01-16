package org.taotao.common.result;

import java.io.Serializable;

//import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonResult<T> extends Result<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean success = true;

	// 定义jackson对象
	// private final ObjectMapper objectMapper = new ObjectMapper();

	private JsonResult(int status, boolean success, String message) {
		this.success = success;
		this.setStatus(status);
		this.setMessage(message);
		this.setData(null);
	}

	private JsonResult(int status, boolean success, String message, T data) {
		this.success = success;
		this.setStatus(status);
		this.setMessage(message);
		this.setData(data);
	}

//	public static JsonResult<String> success() {
//		return new JsonResult<String>(200, true, "SUCCESS", null);
//	}

	public static JsonResult<String> success(String message) {
		return new JsonResult<String>(200, true, message, null);
	}

//	public static JsonResult<String> failure() {
//		return new JsonResult<String>(400, false, "FAILURE", null);
//	}

	public static JsonResult<String> failure(String message) {
		return new JsonResult<String>(400, false, message, null);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
