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
		String sql = "SELECT * FROM AFFAIRS a where a.isdeal ='0' ";
		
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

	@Override
	public Map<String, Object> getMapjw(String cname) {
		String sql = "select c.longitude,c.latitude from coordinate c where c.cname =?";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql,cname);
		
		return map;
	}

	@Override
	public List<Map<String, Object>> getLogContent(String name, String affair) {
		String sql = "select c.lid,c.lname,c.laffair,to_char(c.ldate,'yyyy-mm-dd')ldate,c.log from log_content c where c.lname= ? and c.laffair=? ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,name,affair);
		return list;
	}

	@Override
	public List<Map<String, Object>> getYpzaygInfo() {
		String sql = "SELECT a.id,a.account,a.affair,a.lacale,to_char(a.startime,'yyyy-mm-dd')starttime,"
				+ "to_char(a.endtime,'yyyy-mm-dd')endtime,a.manager,a.detail,a.score,a.isdeal "
				+ "FROM AFFAIRS a where a.isdeal ='1'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public void addPcInfo(Map<String, Object> map,String eaccount, String eaffair,int ave) {
		String sql = " insert into evaluat values(EVALUAT_S.NEXTVAL,?,?,'"+map.get("kqpc-inputEl")+"',"
				+ "'"+map.get("rzpc-inputEl")+"','"+map.get("jgpc-inputEl")+"')";
		jdbcTemplate.update(sql,eaccount,eaffair);
		String sql2 = "update affairs a set a.score=?, a.isdeal='1' where a.account=?and a.affair=? ";
		jdbcTemplate.update(sql2,ave,eaccount,eaffair);
		
	}

}
