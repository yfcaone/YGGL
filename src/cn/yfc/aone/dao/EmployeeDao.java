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

	/**
	 * 判断密码是否正确
	 * @param username
	 * @param old_pwd
	 * @return
	 */
	int getJudgmentPwd(String username, String old_pwd);

	/**
	 * 修改用户密码
	 * @param new_pwd
	 * @param old_pwd 
	 */
	void updatePwd(String new_pwd, String old_pwd);

	/**
	 * 获得用户名中文
	 * @param cname
	 * @return
	 */
	Map<String, Object> getName(String cname);

	

}
