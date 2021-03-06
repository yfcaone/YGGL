package cn.yfc.aone.service;

import java.util.List;
import java.util.Map;

public interface AffairsService {

	/**
	 * 获得所有员工信息
	 * 
	 * @param lname
	 * @param limit 
	 * @param start 
	 * @param page 
	 * 
	 * @return
	 */
	List<Map<String, Object>> selectAll(String lname, int page, int start, int limit);

	/**
	 * 获取地图经纬度并先死到地图上
	 * 
	 * @param cname
	 */
	Map<String, Object> getMapjw(String cname);

	/**
	 * 获得日志内容
	 * 
	 * @param affair
	 * @param name
	 * @return
	 */
	List<Map<String, Object>> getLogContent(String name, String affair);

	/**
	 * /** 获取已评在外员工信息
	 * 
	 * @param lname
	 * 
	 * @return
	 */

	List<Map<String, Object>> getYpzaygInfo(String lname);

	/**
	 * 给员工评分
	 * 
	 * @param map
	 * @param eaffair
	 * @param eaccount
	 */
	void addPcInfo(Map<String, Object> map, String eaccount, String eaffair);

	/**
	 * 获得用户名
	 * 
	 * @param username
	 */
	void getUsername(String username);

	/**
	 * 创建用户并查询
	 * 
	 * @param date
	 * @param vname
	 * @return
	 */
	List<Map<String, Object>> getUserInfo(String date, String vname);

	/**
	 * 创建用户
	 * 
	 * @param map
	 */
	void createUser(Map<String, Object> map);

	/**
	 * 更新项目是否完成
	 * @param iSCOMPLETE
	 * @param aFFAIR 
	 * @param aCCOUNT 
	 * @param iD 
	 */
	void updateInfo(String iSCOMPLETE, String iD, String aCCOUNT, String aFFAIR);

	/**
	 * 获得未评价人所有数据
	 * @param lname
	 * @return
	 */
	List<Map<String, Object>> selectAllInfo(String lname);

	/**
	 * 获得所有已评员工信息
	 * @param lname
	 * @param page
	 * @param start
	 * @param limit
	 * @return
	 */
	List<Map<String, Object>> getYpzaygAllInfo(String lname, int page, int start, int limit);
}
