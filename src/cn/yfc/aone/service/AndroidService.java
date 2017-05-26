package cn.yfc.aone.service;

import java.util.List;
import java.util.Map;

public interface AndroidService {

	/**
	 * 获得在外员工信息
	 * @param username
	 * @return
	 * @throws Exception 
	 */
	List<Map<String, Object>> getZwygInfo(String username) throws Exception;

	/**
	 * 添加在外员工信息
	 * @param account
	 * @param affair
	 * @param locale
	 * @param starttime
	 * @param endtime
	 * @param manager
	 * @param username 
	 * @return
	 */
	int addData(String account, String affair, String locale, String starttime, String endtime, String manager, String username);

	/**
	 * 获得个人信息
	 * @param username
	 * @return
	 * @throws Exception 
	 */
	List<Map<String, Object>> getGrzwxxInfo(String username) throws Exception;

	/**
	 * 添加日志信息
	 * @param log
	 * @param log_account
	 * @param log_affair
	 * @param username
	 * @return
	 */
	int addRzInfo(String log, String log_account, String log_affair, String username);

	/**
	 * 添加经纬度
	 * @param lontitude
	 * @param latitude
	 * @param time
	 * @param username
	 */
	void addMapInfo(String lontitude, String latitude, String time, String username);

}
