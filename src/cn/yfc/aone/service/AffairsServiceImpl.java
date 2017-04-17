package cn.yfc.aone.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yfc.aone.beans.Affairs;
import cn.yfc.aone.dao.AffairsDao;

@Service
public class AffairsServiceImpl implements AffairsService{

	@Autowired
	private AffairsDao affairsDao;
	
	@Override
	public List<Affairs> selectAll() {
		List<Affairs> list = affairsDao.selectAll();
		return list;
	}

	@Override
	public Map<String, Object > getMapjw(String cname) {
		Map<String, Object > map = affairsDao.getMapjw(cname);
		return map;
	}

	@Override
	public List<Map<String, Object>> getLogContent(String name, String affair) {
		List<Map<String, Object>> list = affairsDao.getLogContent(name,affair);
		return list;
	}

	@Override
	public List<Map<String, Object>> getYpzaygInfo() {
		List<Map<String,Object>> list = affairsDao.getYpzaygInfo();
		return list;
	}

	@Override
	public void addPcInfo(Map<String, Object> map,String eaccount, String eaffair) {
		int kqpc = (int) map.get("kqpc-inputEl");
		int rqpc = (int)map.get("rzpc-inputEl");
		int jgpc = (int)map.get("jgpc-inputEl");
		int ave = (kqpc+rqpc+jgpc)/3;
		affairsDao.addPcInfo(map,eaccount,eaffair,ave);	
	}

}
