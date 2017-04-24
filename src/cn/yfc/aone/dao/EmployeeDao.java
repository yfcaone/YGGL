package cn.yfc.aone.dao;

import java.util.List;
import java.util.Map;

public interface EmployeeDao {

	/**
	 * 获取未完成员工信息
	 * 
	 * @param username
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getZwygInfo(String username) throws Exception;

	/**
	 * 获得个人在外信息
	 * 
	 * @param username
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getSelfInfo(String username) throws Exception;

	/**
	 * 添加日志
	 * 
	 * @param map
	 * @param eaccount
	 * @param eaffair
	 * @param username
	 */
	void addLogInfo(Map<String, Object> map, String eaccount, String eaffair, String username);

	/**
	 * 添加员工信息
	 * 
	 * @param map
	 * @param username
	 * @param number
	 */
	void addData(Map<String, Object> map, String username, int number);

}
