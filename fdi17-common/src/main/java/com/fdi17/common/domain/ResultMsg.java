package com.fdi17.common.domain;

import com.fdi17.common.utils.StringUtils;

import java.util.HashMap;

/**
 * 操作消息提醒
 * 
 * @author wuhao01
 */
public class ResultMsg extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	/** 状态码 */
	public static final String CODE_TAG = "code";

	/** 返回内容 */
	public static final String MSG_TAG = "msg";

	/** 数据对象 */
	public static final String DATA_TAG = "data";

	/** 数据对象个数 */
	public static final String DATA_COUNT = "total";

	/**
	 * 初始化一个新创建的 ResultMsg 对象，使其表示一个空消息。
	 */
	public ResultMsg() {
	}

	/**
	 * 初始化一个新创建的 ResultMsg 对象
	 * 
	 * @param code 状态码
	 * @param msg  返回内容
	 */
	public ResultMsg(int code, String msg) {
		super.put(CODE_TAG, code);
		super.put(MSG_TAG, msg);
	}

	/**
	 * 初始化一个新创建的 ResultMsg 对象
	 * 
	 * @param code 状态码
	 * @param msg  返回内容
	 * @param data 数据对象
	 */
	public ResultMsg(int code, String msg, Object data) {
		super.put(CODE_TAG, code);
		super.put(MSG_TAG, msg);
		if (StringUtils.isNotNull(data)) {
			super.put(DATA_TAG, data);
		}
	}

	/**
	 * 初始化一个新创建的 ResultMsg 对象
	 *
	 * @param code 状态码
	 * @param msg  返回内容
	 * @param data 数据对象
	 */
	public ResultMsg(int code, String msg, Object data, String total) {
		super.put(CODE_TAG, code);
		super.put(MSG_TAG, msg);
		if (StringUtils.isNotNull(data)) {
			super.put(DATA_TAG, data);
		}
		if (StringUtils.isNotNull(total)) {
			super.put(DATA_COUNT, total);
		}
	}

	/**
	 * 返回成功消息
	 * 
	 * @return 成功消息
	 */
	public static ResultMsg success() {
		return ResultMsg.success("操作成功");
	}

	/**
	 * 返回成功数据
	 * 
	 * @return 成功消息
	 */
	public static ResultMsg success(Object data) {
		return new ResultMsg(HttpStatus.SUCCESS,"操作成功", data,null);
	}

	/**
	 * 返回成功数据
	 *
	 * @return 成功消息
	 */
	public static ResultMsg success(Object data,String total) {
		return new ResultMsg(HttpStatus.SUCCESS,"操作成功", data,total);
	}

	/**
	 * 返回成功消息
	 *
	 * @param msg 返回内容
	 * @return 成功消息
	 */
	public static ResultMsg success(String msg) {
		return new ResultMsg(HttpStatus.SUCCESS,msg);
	}

	/**
	 * 返回成功消息
	 *
	 * @param msg 返回内容
	 * @return 成功消息
	 */
	public static ResultMsg success(String msg,Object data) {
		return new ResultMsg(HttpStatus.SUCCESS,msg,data);
	}

	/**
	 * 返回成功消息
	 *
	 * @param msg 返回内容
	 * @return 成功消息
	 */
	public static ResultMsg success(String msg,Object data,String total) {
		return new ResultMsg(HttpStatus.SUCCESS,msg,data,total);
	}


	/**
	 * 返回错误消息
	 * 
	 * @return
	 */
	public static ResultMsg error() {
		return ResultMsg.error("操作失败");
	}

	/**
	 * 返回错误消息
	 * 
	 * @param msg 返回内容
	 * @return 警告消息
	 */
	public static ResultMsg error(String msg) {
		return ResultMsg.error(msg, null);
	}

	/**
	 * 返回错误消息
	 * 
	 * @param msg  返回内容
	 * @param data 数据对象
	 * @return 警告消息
	 */
	public static ResultMsg error(String msg, Object data) {
		return new ResultMsg(HttpStatus.ERROR, msg, data);
	}

	/**
	 * 返回错误消息
	 * 
	 * @param code 状态码
	 * @param msg  返回内容
	 * @return 警告消息
	 */
	public static ResultMsg error(int code, String msg) {
		return new ResultMsg(code, msg, null);
	}
}
