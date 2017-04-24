package cn.yfc.aone.service;

import java.util.List;
import java.util.Map;

public interface TravelService {

	/**
	 * 获得城市等级
	 * 
	 * @return
	 */
	List<Map<String, Object>> getCityGrade();

	/**
	 * 获得城市
	 * 
	 * @param city
	 * @return
	 */
	List<Map<String, Object>> getCity(String city);

	/**
	 * 获得部门名称
	 * 
	 * @return
	 */
	List<Map<String, Object>> getDepartment();

	/**
	 * 获得职务
	 * 
	 * @return
	 */
	List<Map<String, Object>> getPost(String department);

	/**
	 * 录入出差信息
	 * 
	 * @param city
	 * @param affair_name
	 * @param go_date
	 * @param post_name
	 * @param job_number
	 */
	void addTravelInfo(String city, String affair_name, String go_date, String post_name, String job_number);

	/**
	 * 获取用户名
	 * 
	 * @param cname
	 */
	void getUsername(String cname);

	/**
	 * 获得出差信息
	 * 
	 * @return
	 */
	List<Map<String, Object>> getTravelInfo();

	/**
	 * 添加请假信息
	 * 
	 * @param log_number
	 * @param start_date
	 * @param end_date
	 * @param leave_cause
	 */
	void addLeaveInfo(String log_number, String start_date, String end_date, String leave_cause);

	/**
	 * 获得请假信息
	 * 
	 * @return
	 */
	List<Map<String, Object>> getLeaveInfo();

	/**
	 * 添加报销信息
	 * 
	 * @param bx_log_number
	 * @param bx_invoice
	 * @param bx_maney
	 */
	void addReimbursementInfo(String bx_log_number, String bx_invoice, String bx_maney);

	/**
	 * 获得报销信息
	 * 
	 * @return
	 */
	List<Map<String, Object>> getReimbursementInfo();

	/**
	 * 添加借款信息
	 * 
	 * @param jk_log_numbers
	 * @param jk_loan
	 * @param jk_money
	 */
	void addLoanInfo(String jk_log_numbers, String jk_loan, String jk_money);

	/**
	 * 获得借款信息
	 * 
	 * @return
	 */
	List<Map<String, Object>> getloanInfo();

	/**
	 * 获得所有需要的信息
	 * 
	 * @param project_number
	 * @return
	 */
	List<Map<String, Object>> getAllInfo(String project_number);

	/**
	 * 添加所有工资信息
	 * 
	 * @param affair_name
	 * @param city_name
	 * @param start_date
	 * @param end_date
	 * @param stay_subsidy
	 * @param food_subsidy
	 * @param traffic_subsidy
	 * @param p_number
	 * @return
	 */
	List<Map<String, Object>> getWageInfo(String affair_name, String city_name, String start_date, String end_date,
			String stay_subsidy, String food_subsidy, String traffic_subsidy, String p_number);

	/**
	 * 获得所有工资信息
	 * 
	 * @return
	 */
	List<Map<String, Object>> getAllMoneyInfo();

}
