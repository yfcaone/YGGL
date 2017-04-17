package cn.yfc.aone.service;

import java.util.List;
import java.util.Map;

import cn.yfc.aone.beans.Affairs;

public interface AffairsService {

	/**
	 * 获得所有员工信息
	 * @return
	 */
	List<Affairs> selectAll();

	/**
	 * 获取地图经纬度并先死到地图上
	 * @param cname
	 */
	Map<String, Object > getMapjw(String cname);

	/**
	 *  获得日志内容
	 * @param affair 
	 * @param name 
	 * @return
	 */
	List<Map<String,Object>> getLogContent(String name, String affair);

	/**
	 * /**
	 * 获取已评在外员工信息
	 * @return
	 */
	
	List<Map<String, Object>> getYpzaygInfo();

	/**
	 * 给员工评分
	 * @param map
	 * @param eaffair 
	 * @param eaccount 
	 */
	void addPcInfo(Map<String, Object> map, String eaccount, String eaffair);
}
