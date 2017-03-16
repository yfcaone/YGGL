package cn.yfc.aone.service;

import java.util.List;
import java.util.Map;

import cn.yfc.aone.beans.Affairs;

public interface AffairsService {

	/**
	 * 查询所有员工信息
	 * @return
	 */
	List<Affairs> selectAll();
}
