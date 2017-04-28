package cn.yfc.aone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yfc.aone.dao.TravelDao;

@Service
public class TravelServiceImpl implements TravelService {

	String username;
	int moneys;
	@Autowired
	private TravelDao travelDao;

	@Override
	public List<Map<String, Object>> getCityGrade() {
		List<Map<String, Object>> list = travelDao.getCityGrade();
		return list;
	}

	@Override
	public List<Map<String, Object>> getCity(String city) {
		List<Map<String, Object>> list = travelDao.getCity(city);
		return list;
	}

	@Override
	public List<Map<String, Object>> getDepartment() {
		List<Map<String, Object>> list = travelDao.getDepartment();
		return list;
	}

	@Override
	public List<Map<String, Object>> getPost(String department) {
		List<Map<String, Object>> list = travelDao.getPost(department);
		return list;
	}

	@Override
	public void addTravelInfo(String city, String affair_name, String go_date, String post_name, String job_number) {
		int number = (int) ((Math.random() * 9 + 1) * 100000);
		travelDao.addTravelInfo(city, affair_name, go_date, post_name, job_number, username,number);

	}

	@Override
	public void getUsername(String cname) {
		this.username = cname;

	}

	@Override
	public List<Map<String, Object>> getTravelInfo() {
		
		List<Map<String, Object>> list = travelDao.getTravelInfo();
		return list;
	}

	@Override
	public void addLeaveInfo(String log_number, String start_date, String end_date, String leave_cause) {
		travelDao.addLeaveInfo(log_number, start_date, end_date, leave_cause);

	}

	@Override
	public List<Map<String, Object>> getLeaveInfo() {
		List<Map<String, Object>> list = travelDao.getLeaveInfo();
		return list;
	}

	@Override
	public void addReimbursementInfo(String bx_log_number, String bx_invoice, String bx_maney) {
		travelDao.addReimbursementInfo(bx_log_number, bx_invoice, bx_maney, username);

	}

	@Override
	public List<Map<String, Object>> getReimbursementInfo() {
		List<Map<String, Object>> list = travelDao.getReimbursementInfo();
		return list;
	}

	@Override
	public void addLoanInfo(String jk_log_numbers, String jk_loan, String jk_money) {
		travelDao.addLoanInfo(jk_log_numbers, jk_loan, jk_money, username);

	}

	@Override
	public List<Map<String, Object>> getloanInfo() {
		List<Map<String, Object>> list = travelDao.getloanInfo();
		return list;
	}

	@Override
	public List<Map<String, Object>> getAllInfo(String project_number) {
		Map<String, Object> map = travelDao.getProjectInfo(project_number);
		Map<String, Object> map1 = travelDao.getSubsidyInfo(map);
		List<Map<String, Object>> list = new ArrayList<>();
		map1.put("P_NAME", map.get("P_NAME"));
		map1.put("P_DATE", map.get("P_DATE"));
		map1.put("P_NUMBER", map.get("P_NUMBER"));
		list.add(map1);
		return list;
	}

	@Override
	public List<Map<String, Object>> getWageInfo(String affair_name, String city_name, String start_date,
			String end_date, String stay_subsidy, String food_subsidy, String traffic_subsidy, String p_number) {
		moneys = Integer.parseInt(stay_subsidy) + Integer.parseInt(food_subsidy) + Integer.parseInt(traffic_subsidy);
		List<Map<String, Object>> list = travelDao.getWageInfo(affair_name, city_name, start_date, end_date,
				stay_subsidy, food_subsidy, traffic_subsidy, p_number, moneys);
		return list;
	}

	@Override
	public List<Map<String, Object>> getAllMoneyInfo() {
		List<Map<String, Object>> lists = new ArrayList<>();
		List<Map<String, Object>> list = travelDao.getAllMoneyInfo();
		for (Map<String, Object> map : list) {
			String ss = (String) map.get("R_DAYS");
			int ssss = Integer.parseInt(ss);
			int MONEYS = ssss * moneys;
			String moneyss = String.valueOf(MONEYS);
			map.put("MONEYS", moneyss);
			lists.add(map);
		}

		return lists;
	}

}
