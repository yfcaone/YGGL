package cn.yfc.aone.dao;

import java.util.List;
import java.util.Map;

public interface TravelDao {

	/**
	 * 获得城市等级
	 * 
	 * @return
	 */
	List<Map<String, Object>> getCityGrade();

	/**
	 * 获得城市等级
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
	 * @param username
	 */
	void addTravelInfo(String city, String affair_name, String go_date, String post_name, String job_number,
			String username);

	/**
	 * 获得出差信息
	 * 
	 * @return
	 */
	List<Map<String, Object>> getTravelInfo();

	/**
	 * 录入请假信息
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
	void addReimbursementInfo(String bx_log_number, String bx_invoice, String bx_maney, String username);

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
	 * @param username
	 */
	void addLoanInfo(String jk_log_numbers, String jk_loan, String jk_money, String username);

	/**
	 * 获得借款信息
	 * 
	 * @return
	 */
	List<Map<String, Object>> getloanInfo();

	/**
	 * 根据项目编号获得项目名称和开始时间
	 * 
	 * @param project_number
	 * @return
	 */
	Map<String, Object> getProjectInfo(String project_number);

	/**
	 * 获得差补信息
	 * 
	 * @param map
	 * @return
	 */
	Map<String, Object> getSubsidyInfo(Map<String, Object> map);

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
	 * @param days
	 * @return
	 */
	List<Map<String, Object>> getWageInfo(String affair_name, String city_name, String start_date, String end_date,
			String stay_subsidy, String food_subsidy, String traffic_subsidy, String p_number, int moneys);

	/**
	 * 获得所有工资信息
	 * 
	 * @return
	 */
	List<Map<String, Object>> getAllMoneyInfo();

}
