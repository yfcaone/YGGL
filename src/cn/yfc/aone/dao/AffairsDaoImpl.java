package cn.yfc.aone.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AffairsDaoImpl implements AffairsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> selectAll() {
		String sql = "SELECT a.id,a.account,a.affair,a.lacale,to_char(a.startime,'yyyy-mm-dd')starttime,"
				+ "to_char(a.endtime,'yyyy-mm-dd')endtime,a.manager,a.detail,a.score,a.isdeal,a.iscomplete "
				+ "FROM AFFAIRS a where a.isdeal ='0'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

		return list;
	}

	@Override
	public Map<String, Object> getMapjw(String cname) {
		String sql = "select c.longitude,c.latitude from coordinate c where c.cname =?";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql, cname);

		return map;
	}

	@Override
	public List<Map<String, Object>> getLogContent(String name, String affair) {
		String sql = "select c.lid,c.lname,c.laffair,to_char(c.ldate,'yyyy-mm-dd')ldate,"
				+ "c.log from log_content c where c.lname= ? and c.laffair=? ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, name, affair);
		return list;
	}

	@Override
	public List<Map<String, Object>> getYpzaygInfo() {
		String sql = "SELECT a.id,a.account,a.affair,a.lacale,to_char(a.startime,'yyyy-mm-dd')starttime,"
				+ "to_char(a.endtime,'yyyy-mm-dd')endtime,a.manager,a.detail,a.score,a.isdeal,a.iscomplete "
				+ "FROM AFFAIRS a where a.isdeal ='1'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public void addPcInfo(Map<String, Object> map, String eaccount, String eaffair, int ave, String username) {
		String sql = " insert into evaluat values(EVALUAT_S.NEXTVAL,?,?,'" + map.get("kqpc-inputEl") + "'," + "'"
				+ map.get("rzpc-inputEl") + "','" + map.get("jgpc-inputEl") + "',?,SYSDATE)";
		jdbcTemplate.update(sql, eaccount, eaffair, username);
		String sql2 = "update affairs a set a.score=?, a.isdeal='1' where a.account=?and a.affair=? ";
		jdbcTemplate.update(sql2, ave, eaccount, eaffair);

	}

}
