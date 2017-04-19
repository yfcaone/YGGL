package cn.yfc.aone.service;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

	/**
	 * 获取未完成员工信息
	 * @return
	 * @throws Exception 
	 */
	List<Map<String, Object>> getZwygInfo() throws Exception;

	/**
	 * 获得登录者用户名
	 * @param username
	 */
	void getUsername(String username);

	/**
	 * 获得个人在外信息
	 * @return
	 * @throws Exception 
	 */
	List<Map<String, Object>> getSelfInfo() throws Exception;

}
