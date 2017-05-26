package cn.yfc.aone.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yfc.aone.dao.AndroidDao;
import cn.yfc.aone.dao.EmployeeDao;

@Service
public class AndroidServiceImpl implements AndroidService {

	@Autowired
	private AndroidDao androidDao;
	
	@Autowired
	private  EmployeeDao employeeDao;
	
	@Override
	public List<Map<String, Object>> getZwygInfo(String username) throws Exception {
		List<Map<String, Object>> list = employeeDao.getZwygInfo(username);
		return list;
	}

	@Override
	public int addData(String account, String affair, String locale, String starttime, String endtime, String manager,String username) {
		int number = (int) ((Math.random() * 9 + 1) * 100000);
		int i = androidDao.addData(account,affair,locale,starttime,endtime,manager, username, number);
		return i;
	}

	@Override
	public List<Map<String, Object>> getGrzwxxInfo(String username) throws Exception {
		List<Map<String, Object>> list = employeeDao.getSelfInfo(username);
		return list;
	}

	@Override
	public int addRzInfo(String log, String log_account, String log_affair, String username) {
		int i = androidDao.addRzInfo(log,log_account,log_affair,username);
		return i;
	}

	@Override
	public void addMapInfo(String lontitude, String latitude, String time, String username) {
		androidDao.addMapInfo(lontitude,latitude,time,username);
		
	}

}
