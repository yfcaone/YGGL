package cn.yfc.aone.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yfc.aone.dao.EmployeeDao;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	String username;
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public List<Map<String, Object>> getZwygInfo() throws Exception {
		List<Map<String, Object>> list = employeeDao.getZwygInfo(username);
		return list;
	}

	@Override
	public void getUsername(String username) {
		this.username = username;

	}

	@Override
	public List<Map<String, Object>> getSelfInfo() throws Exception {
		List<Map<String, Object>> list = employeeDao.getSelfInfo(username);
		return list;
	}

	@Override
	public void addLogInfo(Map<String, Object> map, String eaccount, String eaffair) {
		employeeDao.addLogInfo(map, eaccount, eaffair, username);

	}

	@Override
	public void addData(Map<String, Object> map) {
		int number = (int) ((Math.random() * 9 + 1) * 100000);
		employeeDao.addData(map, username, number);

	}

}
