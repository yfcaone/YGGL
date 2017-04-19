package cn.yfc.aone.dao;

import java.util.Map;

public interface LoginDao {

	/**
	 * 根据用户名获得信息
	 * @param username 
	 * @param role 
	 * @param password 
	 * @param map
	 * @return 
	 */
	Map<String, Object> getLogContent(String username, String password, String role);

}
