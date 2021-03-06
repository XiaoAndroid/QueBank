package com.liusiyang.question.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liusiyang.question.entity.Pager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class Data2Jsp {
	/**
	 * 
	 * @Description: 分页json
	 * @param pager
	 * @param pages
	 * @return
	 * @return String
	 * @throws @author zhoushuoxi
	 * @date 2015-7-26
	 */
	@SuppressWarnings("deprecation")
	public static String Json2Jsp(Pager<?> pager) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		StringBuffer bf = new StringBuffer();
		int total = pager.getTotalCount();
		List<?> list = pager.getDatas();
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		bf.append("{\"total\":").append(total + ",");
		bf.append("\"rows\":");
		bf.append(json.toString());
		bf.append("}");
		return bf.toString();
	}

	public static String Json2Jsp(Object obj, int total) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		StringBuffer bf = new StringBuffer();
		JSONArray json = JSONArray.fromObject(obj, jsonConfig);
		bf.append("{\"total\":").append(total + ",");
		bf.append("\"rows\":");
		bf.append(json.toString());
		bf.append("}");
		return bf.toString();
	}

	public static String errPage2Json() {
		StringBuffer bf = new StringBuffer();
		int recc = 0;
		bf.append("{\"total\":").append(recc + ",");
		bf.append("\"rows\":[]}");
		return bf.toString();
	}

	/**
	 * 
	 * @Description: 获得开始页及一页有多少条数据
	 * @param @param map
	 * @param @return
	 * @return Map<String,Integer>
	 * @throws @author
	 * @date 2015-7-17
	 */
	public static Map<String, Integer> getFristAndPageSize(int page, int rows) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		int page_size = 10;
		@SuppressWarnings("unused")
		int pages = 10;
		int first_page = 1;
		if (page > 1) {
			first_page = page;
		}
		if (rows > 10) {
			page_size = rows;
		}
		first_page = (first_page - 1) * page_size;
		pages = page_size;
		result.put("first_page", first_page);
		result.put("page_size", page_size);
		return result;
	}

	public static String listToJson(List<?> list) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		JSONArray json = JSONArray.fromObject(list, jsonConfig);
		return json.toString();
	}

	public static String mapToJson(Object obj) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		JSONObject json = JSONObject.fromObject(obj, jsonConfig);
		return json.toString();
	}
}
