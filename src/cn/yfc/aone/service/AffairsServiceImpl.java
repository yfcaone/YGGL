package cn.yfc.aone.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.org.apache.bcel.internal.classfile.Code;

import cn.yfc.aone.dao.AffairsDao;

@Service
public class AffairsServiceImpl implements AffairsService {

	String username;

	@Autowired
	private AffairsDao affairsDao;

	@Override
	public List<Map<String, Object>> selectAll(String lname,int page, int start, int limit) {
		int begin = (page - 1) * limit + 1;
		int end = page * limit;
		String condition = "";
		if (lname.length() > 0) {
			condition = condition + " and a.account = '" + lname + "'";
		}
		List<Map<String, Object>> list = affairsDao.selectAll(condition,begin,end);
		return list;
	}

	@Override
	public Map<String, Object> getMapjw(String cname) {
		Map<String, Object> map = affairsDao.getMapjw(cname);
		return map;
	}

	@Override
	public List<Map<String, Object>> getLogContent(String name, String affair) {
		List<Map<String, Object>> list = affairsDao.getLogContent(name, affair);
		return list;
	}

	@Override
	public List<Map<String, Object>> getYpzaygInfo(String lname) {
		String condition = "";
		if (lname.length() > 0) {
			condition = condition + " and a.account = '" + lname + "'";
		}
		List<Map<String, Object>> list = affairsDao.getYpzaygInfo(condition);
		return list;
	}

	@Override
	public void addPcInfo(Map<String, Object> map, String eaccount, String eaffair) {
		int kqpc = (int) map.get("kqpc-inputEl");
		int rqpc = (int) map.get("rzpc-inputEl");
		int jgpc = (int) map.get("jgpc-inputEl");
		int ave = (kqpc + rqpc + jgpc) / 3;
		System.out.println("-=-=-=-=====-=-"+username);
		affairsDao.addPcInfo(map, eaccount, eaffair, ave, username);
	}

	@Override
	public void getUsername(String username) {
		this.username = username;
	}

	@Override
	public List<Map<String, Object>> getUserInfo(String date, String vname) {
		String condition = "";
		String data = date.replaceAll("T", " ");
		if (vname.length() > 0) {
			condition = condition + " and v.vname = '" + vname + "'";
		}
		if (date != null && date != "") {
			condition = condition + " and v.vdate > (to_date('" + data.substring(0, 19)
					+ "','yyyy-mm-dd HH24:mi:ss')) ";
		}
		List<Map<String, Object>> list = affairsDao.getUserInfo(condition);
		return list;
	}

	@Override
	public void createUser(Map<String, Object> map) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String str = (String) map.get("account");
		List<Map<String, Object>> map1 = new ArrayList<>();
		List<String> list = new ArrayList<>();
		String[] strings = str.split("，");
		System.out.println("strings"+strings);
		for (String str1 : strings) {
			list.add(str1);
			System.out.println("list"+list);
		}
		for (int j = 0; j < strings.length; j++) {
			StringBuffer ssss = new StringBuffer(6);
			for (int i = 0; i < 5; i++) {
				ssss.append(String.valueOf(chars.charAt((int) (Math.random() * 26))));
			}
			
			Map<String, Object> map2 = new HashMap<>();
			map2.put("account", ssss);
			map2.put("number", 111111);
			map2.put("name", list.get(j));
			map2.put("gender", map.get("Gender"));
			map2.put("department_name", map.get("department_name"));
			map2.put("post_name", map.get("post_name"));
			System.out.println("map2"+map2);
			map1.add(map2);
		}
		affairsDao.createUser(map1);
	}

	@Override
	public void updateInfo(String iSCOMPLETE,String ID,String ACCOUNT,String AFFAIR) {
		System.out.println("结果是"+iSCOMPLETE+ID+ACCOUNT +AFFAIR );
		affairsDao.updateInfo(iSCOMPLETE,ID,ACCOUNT,AFFAIR);
		
	}

	@Override
	public List<Map<String, Object>> selectAllInfo(String lname) {
		String condition = "";
		if (lname.length() > 0) {
			condition = condition + " and a.account = '" + lname + "'";
		}
		List<Map<String, Object>> list = affairsDao.selectAllInfo(condition);
		return list;
	}

	@Override
	public List<Map<String, Object>> getYpzaygAllInfo(String lname, int page, int start, int limit) {
		int begin = (page - 1) * limit + 1;
		int end = page * limit;
		String condition = "";
		if (lname.length() > 0) {
			condition = condition + " and a.account = '" + lname + "'";
		}
		List<Map<String, Object>> list = affairsDao.getYpzaygAllInfo(condition,begin,end);
		return list;
	}

}
