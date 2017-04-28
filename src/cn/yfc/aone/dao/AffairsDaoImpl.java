package cn.yfc.aone.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sun.accessibility.internal.resources.accessibility;

@Repository
public class AffairsDaoImpl implements AffairsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> selectAll(String condition) {
		String sql = "SELECT a.id,a.account,a.affair,a.lacale,to_char(a.startime,'yyyy-mm-dd')starttime,"
				+ "to_char(a.endtime,'yyyy-mm-dd')endtime,a.manager,a.detail,a.score,a.isdeal,a.iscomplete "
				+ "FROM AFFAIRS a where a.isdeal ='0'" + condition;
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
	public List<Map<String, Object>> getYpzaygInfo(String condition) {
		String sql = "SELECT a.id,a.account,a.affair,a.lacale,to_char(a.startime,'yyyy-mm-dd')starttime,"
				+ "to_char(a.endtime,'yyyy-mm-dd')endtime,a.manager,a.detail,a.score,a.isdeal,a.iscomplete "
				+ "FROM AFFAIRS a where a.isdeal ='1'" + condition;
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

	@Override
	public List<Map<String, Object>> getUserInfo(String condition) {
		String sql = "select s.job_number as vid,v.vaccount,v.vpassword,v.vname,to_char(v.vdate,'yyyy-mm-dd HH24:mi:ss')vdate  from VIRTUAL_USER v,staffs s "
				+ " where v.vid<>0 and v.vname = s.sname  " + condition + " ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public void createUser(List<Map<String, Object>> map1) {

		for (Map<String, Object> map : map1) {
			String sql = "insert into VIRTUAL_USER VALUES(VIRTUAL_USER_S.NEXTVAL,'" + map.get("account") + "','"
					+ map.get("number") + "','员工','" + map.get("name") + "',SYSDATE)";
			jdbcTemplate.update(sql);
		}
		for (Map<String, Object> map : map1) {
			String sql = "insert into users values(USERS_S.NEXTVAL,'" + map.get("account") + "','" + map.get("number")
					+ "','员工','" + map.get("name") + "')";
			jdbcTemplate.update(sql);
		}
		for (Map<String, Object> map : map1) {
			String sql = "insert into staffs values(STAFFS_S.NEXTVAL,'"+ map.get("name") +"','"+
		map.get("gender")+"',(select distinct(d.department) from DEPARTMENT_POST_INFO d where d.department_code = '"+
					map.get("department_name")+"'),(select d.dpost from DEPARTMENT_POST_INFO d where d.dpost_code = '"+
		map.get("post_name")+"'),sysdate)";
			jdbcTemplate.update(sql);
		}

	}

	@Override
	public void updateInfo(String iSCOMPLETE,String ID,String ACCOUNT,String AFFAIR) {
		String sql = "update affairs set iscomplete = ? where ID=?";
		jdbcTemplate.update(sql,iSCOMPLETE,ID);
		
	}

}
