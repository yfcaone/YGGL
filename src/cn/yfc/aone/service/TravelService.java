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
	 * @param p_number
	 */
	void addTravelInfo(String city, String affair_name, String go_date, String post_name, String job_number,
			String p_number);

	/**
	 * 获取用户名
	 * 
	 * @param cname
	 */
	void getUsername(String cname);

	/**
	 * 获得出差信息
	 * 
	 * @param p_NUMBER
	 * @param limit
	 * @param start
	 * @param page
	 * 
	 * @return
	 */
	List<Map<String, Object>> getTravelInfo(String p_NUMBER, int page, int start, int limit);

	/**
	 * 添加请假信息
	 * 
	 * @param log_number
	 * @param start_date
	 * @param end_date
	 * @param leave_cause
	 * @param p_number
	 */
	void addLeaveInfo(String log_number, String start_date, String end_date, String leave_cause, String p_number);

	/**
	 * 获得请假信息
	 * 
	 * @param p_NUMBER
	 * @param limit
	 * @param start
	 * @param page
	 * 
	 * @return
	 */
	List<Map<String, Object>> getLeaveInfo(int page, int start, int limit, String p_NUMBER);

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
	 * @param limit
	 * @param start
	 * @param page
	 * 
	 * @return
	 */
	List<Map<String, Object>> getReimbursementInfo(int page, int start, int limit);

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
	 * @param limit
	 * @param start
	 * @param page
	 * 
	 * @return
	 */
	List<Map<String, Object>> getloanInfo(int page, int start, int limit);

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

	/**
	 * 创建项目编号
	 * 
	 * @param add_city
	 * @param add_affair_name
	 * @param add_go_date
	 * @return
	 */
	List<Map<String, Object>> addCreateTravelInfo(String add_city, String add_affair_name, String add_go_date);

	/**
	 * 删除在员工信息
	 * 
	 * @param tID
	 */
	void delTravelInfo(String tID);

	/**
	 * 获得工号
	 * 
	 * @param post_name
	 * @return
	 */
	List<Map<String, Object>> getNumber(String post_name);

	/**
	 * 重置用户名密码
	 * 
	 * @param VACCOUNT
	 */
	void resetPwd(String VACCOUNT);

	/**
	 * 获得所有借款信息
	 * 
	 * @return
	 */
	List<Map<String, Object>> getloanCount();

	/**
	 * 获得所有报销信息
	 * 
	 * @return
	 */
	List<Map<String, Object>> getReimbursementCount();

	/**
	 * 获得项目员工信息
	 * 
	 * @return
	 */
	List<Map<String, Object>> getXmygxxInfo();

	/**
	 * 添加新在外员工信息
	 * 
	 * @param p_number
	 * @param p_city
	 * @param go_date
	 * @param post_name
	 * @param job_number
	 * @param p_name
	 */
	void addTravelInfos(String p_number, String p_city, String go_date, String post_name, String job_number,
			String p_name);

	/**
	 * 添加在外员工撤离时间
	 * 
	 * @param p_NUMBER
	 * @param jOB_NUMBER
	 * @param p_EVACUATE_DATE
	 */
	void addTravelYgclInfo(String p_NUMBER, String jOB_NUMBER, String p_EVACUATE_DATE);

	/**
	 * 根据项目获得在外员工信息
	 * 
	 * @param p_NUMBERS
	 * @return
	 */
	List<Map<String, Object>> getTravelInfos(String p_NUMBERS);

	/**
	 * 获得所有在外员工数据
	 * 
	 * @param p_NUMBER
	 * @return
	 */
	List<Map<String, Object>> getAllTravelInfo(String p_NUMBER);

	/**
	 * 获得项目编号
	 * 
	 * @return
	 */
	List<Map<String, Object>> getXmbh();

	/**
	 * 获得工号
	 * 
	 * @param p_number
	 * @return
	 */
	List<Map<String, Object>> getGhData(String p_number);

	/**
	 * 获得所有请假信息
	 * 
	 * @param p_NUMBER
	 * @return
	 */
	List<Map<String, Object>> getAllLeave(String p_NUMBER);

}
