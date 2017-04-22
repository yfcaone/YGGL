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
		travelDao.addTravelInfo(city,affair_name,go_date,post_name,job_number,username);
		
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

}
