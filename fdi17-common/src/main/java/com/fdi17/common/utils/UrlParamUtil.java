package com.fdi17.common.utils;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class UrlParamUtil {

	/**
	 * 
	 * 获取Url参数
	 */
	public static Map<String, String> getUrlParam(String url) {
		Map<String, String> urlParams = new HashMap<>();
		url = url.replace("?", ";");
		if (!url.contains(";")) {
			return urlParams;
		}
		if (url.split(";").length > 0) {
			String[] arr = url.split(";")[1].split("&");
			for (String s : arr) {
				String key = s.split("=", -1)[0];
				String value = s.split("=", -1)[1];
				urlParams.put(key, value);
			}
			return urlParams;

		} else {
			return urlParams;
		}
	}
}