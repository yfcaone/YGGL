package cn.yfc.aone.service;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

	/**
	 * 获取未完成员工信息
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getZwygInfo() throws Exception;

	/**
	 * 获得登录者用户名
	 * 
	 * @param username
	 */
	void getUsername(String username);

	/**
	 * 获得个人在外信息
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getSelfInfo() throws Exception;

	/**
	 * 添加日志
	 * 
	 * @param map
	 * @param eaccount
	 * @param eaffair
	 */
	void addLogInfo(Map<String, Object> map, String eaccount, String eaffair);

	/**
	 * 添加在外员工信息
	 * 
	 * @param map
	 */
	void addData(Map<String, Object> map);

	/**
	 * 判断密码是否正确
	 * @param old_pwd
	 * @return
	 */
	int getJudgmentPwd(String old_pwd);

	/**
	 * 修改密码
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
