package cn.yfc.aone.dao;

import java.util.List;
import java.util.Map;

import cn.yfc.aone.beans.Affairs;

public interface AffairsDao {
	
	/**
	 * 获得所有员工信息
	 * @return
	 */
	List<Affairs> selectAll();

	/**
	 * 获取地图经纬度并先死到地图上
	 * @param cname
	 * @return
	 */
	Map<String, Object> getMapjw(String cname);

	/**
	 * 获得日志内容
	 * @param name
	 * @param affair
	 * @return
	 */
	List<Map<String, Object>> getLogContent(String name, String affair);

	/**
	 * 获取已评在外员工信息
	 * @return
	 */
	List<Map<String, Object>> getYpzaygInfo();

	/**
	 * 给员工评测
	 * @param map
	 * @param eaffair 
	 * @param eaccount 
	 * @param ave 
	 */
	void addPcInfo(Map<String, Object> map, String eaccount, String eaffair, int ave);

}
