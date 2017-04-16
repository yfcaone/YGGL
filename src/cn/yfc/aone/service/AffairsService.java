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
}
