package cn.yfc.aone.service;

import java.util.List;
import java.util.Map;

public interface TravelService {

	/**
	 * 获得城市等级
	 * @return
	 */
	List<Map<String, Object>> getCityGrade();

	/**
	 * 获得城市
	 * @param city
	 * @return
	 */
	List<Map<String, Object>> getCity(String city);

	/**
	 * 获得部门名称
	 * @return
	 */
	List<Map<String, Object>> getDepartment();

	/**
	 * 获得职务
	 * @return
	 */
	List<Map<String, Object>> getPost(String department);

	/**
	 * 录入出差信息
	 * @param city
	 * @param affair_name
	 * @param go_date
	 * @param post_name
	 * @param job_number
	 */
	void addTravelInfo(String city, String affair_name, String go_date, String post_name, String job_number);
	
	/**
	 * 获取用户名
	 * @param cname
	 */
	void getUsername(String cname);

	/**
	 * 获得出差信息
	 * @return
	 */
	List<Map<String, Object>> getTravelInfo();

}
