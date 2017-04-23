package cn.yfc.aone.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yfc.aone.dao.TravelDao;

@Service
public class TravelServiceImpl implements TravelService {

	String username;
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
		travelDao.addTravelInfo(city, affair_name, go_date, post_name, job_number, username);

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

}
