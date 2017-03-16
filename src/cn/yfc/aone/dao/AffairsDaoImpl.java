package cn.yfc.aone.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.yfc.aone.beans.Affairs;

@Repository
public class AffairsDaoImpl implements AffairsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Affairs> selectAll() {
		String sql = "SELECT * FROM AFFAIRS";
		
		List<Map<String, Object>> data = jdbcTemplate.queryForList(sql);
		List<Affairs> list = new ArrayList<Affairs>();
		if (data != null && data.size() > 0) {
			for (int i = 0; i < data.size(); i++) {
				Map<String, Object> map = data.get(i);
				BigDecimal id =  (BigDecimal) map.get("id");
				String account = (String) map.get("account");
				String affair = (String) map.get("affair");
				String lacale = (String) map.get("lacale");
				Date startime = (Date) map.get("startime");
				Date endtime = (Date) map.get("endtime");
				String manager = (String) map.get("manager");
				String detail = (String) map.get("detail");
				String score = (String) map.get("score");
				String isdeal = (String) map.get("isdeal");
				Affairs b = new Affairs();
				b.setId(id);
				b.setAccount(account);
				b.setAffair(affair);
				b.setDetail(detail);
				b.setEndtime(endtime);
				b.setLacale(lacale);
				b.setManager(manager);
				b.setStartime(startime);
				b.setScore(score);
				b.setIsdeal(isdeal);
				list.add(b);
				
			}
		}
		
		return list;
	}

}
