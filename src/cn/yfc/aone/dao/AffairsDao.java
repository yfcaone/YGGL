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

}
