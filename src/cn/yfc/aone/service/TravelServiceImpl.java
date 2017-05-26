package cn.yfc.aone.service;

import java.math.BigDecimal;
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
	public void addTravelInfo(String city, String affair_name, String go_date, String post_name, String job_number,
			String p_number) {
		travelDao.addTravelInfo(city, affair_name, go_date, post_name, job_number, username, p_number);
	}

	@Override
	public void addTravelInfos(String p_number, String p_city, String go_date, String post_name, String job_number,
			String p_name) {
		travelDao.addTravelInfos(p_number, p_city, go_date, post_name, job_number, p_name, username);
	}

	@Override
	public void getUsername(String cname) {
		this.username = cname;
	}

	@Override
	public List<Map<String, Object>> getTravelInfo(String P_NUMBER, int page, int start, int limit) {
		int begin = (page - 1) * limit + 1;
		int end = page * limit;
		String condition = "";
		if (P_NUMBER.length() > 0) {
			condition = condition + " where t.p_number = '" + P_NUMBER + "'";
		}
		List<Map<String, Object>> list = travelDao.getTravelInfo(condition, begin, end);
		return list;
	}

	@Override
	public void addLeaveInfo(String log_number, String start_date, String end_date, String leave_cause,
			String p_number) {
		travelDao.addLeaveInfo(log_number, start_date, end_date, leave_cause, p_number);
	}

	@Override
	public List<Map<String, Object>> getLeaveInfo(int page, int start, int limit, String p_NUMBER) {
		int begin = (page - 1) * limit + 1;
		int end = page * limit;
		String condition = "";
		if (p_NUMBER.length() > 0) {
			condition = condition + " and l.p_number = '" + p_NUMBER + "'";
		}
		List<Map<String, Object>> list = travelDao.getLeaveInfo(begin, end, condition);
		return list;
	}

	@Override
	public void addReimbursementInfo(String bx_log_number, String bx_invoice, String bx_maney) {
		travelDao.addReimbursementInfo(bx_log_number, bx_invoice, bx_maney, username);

	}

	@Override
	public List<Map<String, Object>> getReimbursementInfo(int page, int start, int limit) {
		int begin = (page - 1) * limit + 1;
		int end = page * limit;
		List<Map<String, Object>> list = travelDao.getReimbursementInfo(begin, end);
		return list;
	}

	@Override
	public void addLoanInfo(String jk_log_numbers, String jk_loan, String jk_money) {
		travelDao.addLoanInfo(jk_log_numbers, jk_loan, jk_money, username);
	}

	@Override
	public List<Map<String, Object>> getloanInfo(int page, int start, int limit) {
		int begin = (page - 1) * limit + 1;
		int end = page * limit;
		List<Map<String, Object>> list = travelDao.getloanInfo(begin, end);
		return list;
	}

	@Override
	public List<Map<String, Object>> getAllInfo(String project_number) {
		Map<String, Object> map = travelDao.getProjectInfo(project_number);
		List<Map<String, Object>> list = new ArrayList<>();
		if (map != null && map.size() > 0) {
			Map<String, Object> map1 = travelDao.getSubsidyInfo(map);
			map1.put("P_NAME", map.get("P_NAME"));
			map1.put("P_DATE", map.get("P_DATE"));
			map1.put("P_NUMBER", map.get("P_NUMBER"));
			list.add(map1);
			return list;

		} else {
			return null;
		}

	}

	@Override
	public List<Map<String, Object>> getWageInfo(String affair_name, String city_name, String start_date,
			String end_date, String stay_subsidy, String food_subsidy, String traffic_subsidy, String p_number) {
		List<Map<String, Object>> datas = new ArrayList<>();
		String start_date_i = "";
		String end_date_i = "";
		// 请假开始时间
		int leave_starttime_int;
		// 请假结束时间
		int leave_endtime_int;
		int travel_number;
		int leave_number;
		String[] s1 = start_date.split("-");
		String[] s2 = end_date.split("-");
		for (String string : s1) {
			start_date_i = start_date_i + string;
		}
		for (String string : s2) {
			end_date_i = end_date_i + string;
		}
		String end_date_ii = end_date_i.substring(0, 8);
		// 发放开始时间
		int start_date_int = Integer.parseInt(start_date_i);
		// 发放截至时间
		int end_date_int = Integer.parseInt(end_date_ii);
		List<Map<String, Object>> travel_info = travelDao.getTravel_Info(p_number);
		List<Map<String, Object>> leave_info = travelDao.getLeave_Info(p_number);
		for (Map<String, Object> map : travel_info) {
			Integer i = new Integer(0);
			BigDecimal travel_number_b = (BigDecimal) map.get("JOB_NUMBER");
			travel_number = travel_number_b.intValue();
			if (leave_info.size() > 0) {
				for (Map<String, Object> map1 : leave_info) {
					int number_int = travel_number;
					BigDecimal leave_number_b = (BigDecimal) map1.get("LOG_NUMBER");

					leave_number = leave_number_b.intValue();
					String leave_starttime = (String) map1.get("START_TIME");
					String leave_starttime_i = "";
					String[] s3 = leave_starttime.split("-");
					for (String string : s3) {
						leave_starttime_i = leave_starttime_i + string;
					}
					leave_starttime_int = Integer.parseInt(leave_starttime_i);
					String leave_endtime = (String) map1.get("END_TIME");
					String leave_endtime_i = "";
					String[] s4 = leave_endtime.split("-");
					for (String string : s4) {
						leave_endtime_i = leave_endtime_i + string;
					}
					leave_endtime_int = Integer.parseInt(leave_endtime_i);
					int leave_number_int = leave_number;
					if (number_int == leave_number_int) {
						if (start_date_int < leave_starttime_int) {
							if (end_date_int >= leave_endtime_int) {
								map.put("DAYS", (leave_endtime_int - leave_starttime_int + 1));
								datas.add(map);
							} else if (end_date_int < leave_starttime_int) {
								map.put("DAYS", 0);
								datas.add(map);
							} else {
								map.put("DAYS", (end_date_int - leave_starttime_int + 1));
								datas.add(map);
							}
						} else if (start_date_int >= leave_starttime_int && start_date_int <= leave_endtime_int) {
							if (end_date_int <= leave_endtime_int) {
								map.put("DAYS", (end_date_int - start_date_int + 1));
								datas.add(map);
							} else {
								map.put("DAYS", (leave_endtime_int - start_date_int + 1));
								datas.add(map);
							}
						} else {
							map.put("DAYS", 0);
							datas.add(map);
						}
					} else {
						i = i + 1;
						if (i == leave_info.size()) {
							map.put("DAYS", 0);
							datas.add(map);
						}
					}
				}
			} else {
				map.put("DAYS", 0);
				datas.add(map);
			}

		}
		moneys = Integer.parseInt(stay_subsidy) + Integer.parseInt(food_subsidy) + Integer.parseInt(traffic_subsidy);
		List<Map<String, Object>> list = travelDao.getWageInfo(affair_name, city_name, start_date, end_date,
				stay_subsidy, food_subsidy, traffic_subsidy, p_number, moneys);
		travelDao.addAllWageInfo(datas, start_date, end_date);
		System.out.println("搜有数据是" + datas);
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

	@Override
	public List<Map<String, Object>> addCreateTravelInfo(String add_city, String add_affair_name, String add_go_date) {
		int number = (int) ((Math.random() * 9 + 1) * 100000);
		List<Map<String, Object>> list = travelDao.addCreateTravelInfo(add_city, add_affair_name, add_go_date, number,
				username);
		return list;
	}

	@Override
	public void delTravelInfo(String tID) {
		travelDao.delTravelInfo(tID);

	}

	@Override
	public List<Map<String, Object>> getNumber(String post_name) {
		List<Map<String, Object>> list = travelDao.getNumber(post_name);
		return list;
	}

	@Override
	public void resetPwd(String VACCOUNT) {
		travelDao.resetPwd(VACCOUNT);

	}

	@Override
	public List<Map<String, Object>> getloanCount() {
		List<Map<String, Object>> list = travelDao.getloanCount();
		return list;
	}

	@Override
	public List<Map<String, Object>> getReimbursementCount() {
		List<Map<String, Object>> list = travelDao.getReimbursementCount();
		return list;
	}

	@Override
	public List<Map<String, Object>> getXmygxxInfo() {
		List<Map<String, Object>> list = travelDao.getXmygxxInfo();
		return list;
	}

	@Override
	public void addTravelYgclInfo(String p_NUMBER, String jOB_NUMBER, String p_EVACUATE_DATE) {
		travelDao.addTravelYgclInfo(p_NUMBER, jOB_NUMBER, p_EVACUATE_DATE);

	}

	@Override
	public List<Map<String, Object>> getTravelInfos(String p_NUMBERS) {
		List<Map<String, Object>> list = travelDao.getTravelInfos(p_NUMBERS);
		return list;
	}

	@Override
	public List<Map<String, Object>> getAllTravelInfo(String p_NUMBER) {
		String condition = "";
		if (p_NUMBER.length() > 0) {
			condition = condition + " where t.p_number = '" + p_NUMBER + "'";
		}
		List<Map<String, Object>> list = travelDao.getAllTravelInfo(condition);
		return list;
	}

	@Override
	public List<Map<String, Object>> getXmbh() {
		List<Map<String, Object>> list = travelDao.getXmbh();
		return list;
	}

	@Override
	public List<Map<String, Object>> getGhData(String p_number) {
		List<Map<String, Object>> list = travelDao.getGhData(p_number);
		return list;
	}

	@Override
	public List<Map<String, Object>> getAllLeave(String p_NUMBER) {
		String condition = "";
		if (p_NUMBER.length() > 0) {
			condition = condition + " and l.p_number = '" + p_NUMBER + "'";
		}
		List<Map<String, Object>> list = travelDao.getAllLeave(condition);
		return list;
	}

}
