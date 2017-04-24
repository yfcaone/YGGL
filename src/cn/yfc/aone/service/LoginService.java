package cn.yfc.aone.service;

import java.util.Map;

public interface LoginService {

	/**
	 * 登录判断
	 * 
	 * @param map
	 */
	Map<String, Object> getLogContent(String username, String password, String role);

}
