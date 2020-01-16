package org.taotao.common.result;

/**
 * 所有 Result的父类
 * 
 * @author hydra
 *
 */
public abstract class Result<T> {

	/**
	 * 响应业务状态
	 * 
	 */
	private int status;

	/**
	 * 响应消息
	 */
	private String message;

	/**
	 * 响应中的数据
	 */
	private T data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

//	public abstract Result<T> success();
//
//	public abstract Result<T> failure();

}
