package cn.yfc.aone.dao;

import java.util.List;
import java.util.Map;

public interface AffairsDao {

	/**
	 * 获得所有员工信息
	 * 
	 * @param condition
	 * @return
	 */
	List<Map<String, Object>> selectAll(String condition);

	/**
	 * 获取地图经纬度并先死到地图上
	 * 
	 * @param cname
	 * @return
	 */
	Map<String, Object> getMapjw(String cname);

	/**
	 * 获得日志内容
	 * 
	 * @param name
	 * @param affair
	 * @return
	 */
	List<Map<String, Object>> getLogContent(String name, String affair);

	/**
	 * 获取已评在外员工信息
	 * 
	 * @param condition
	 * @return
	 */
	List<Map<String, Object>> getYpzaygInfo(String condition);

	/**
	 * 给员工评测
	 * 
	 * @param map
	 * @param eaffair
	 * @param eaccount
	 * @param ave
	 * @param username
	 */
	void addPcInfo(Map<String, Object> map, String eaccount, String eaffair, int ave, String username);

	/**
	 * 查询用户并查询
	 * 
	 * @param condition
	 * @return
	 */
	List<Map<String, Object>> getUserInfo(String condition);

	/**
	 * 创建用户
	 * 
	 * @param map1
	 */
	void createUser(List<Map<String, Object>> map1);

	/**
	 * 更新项目是否完成
	 * @param iSCOMPLETE
	 * @param aFFAIR 
	 * @param aCCOUNT 
	 * @param iD 
	 */
	void updateInfo(String iSCOMPLETE, String iD, String aCCOUNT, String aFFAIR);

}
