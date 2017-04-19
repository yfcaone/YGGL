package cn.yfc.aone.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<Map<String, Object>> getZwygInfo(String username) throws Exception {
		String cname = new String(username.getBytes("ISO8859-1"), "UTF-8");
		System.out.println("==========================="+cname);
		String sql = "SELECT a.id,a.account,a.affair,a.lacale,to_char(a.startime,'yyyy-mm-dd')starttime,"
				+ "to_char(a.endtime,'yyyy-mm-dd')endtime,a.manager,a.detail,a.score,a.isdeal,a.iscomplete "
				+ "FROM AFFAIRS a where a.iscomplete='未完成'and a.account<>?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,cname);
		return list;
	}
	@Override
	public List<Map<String, Object>> getSelfInfo(String username) throws Exception {
		String cname = new String(username.getBytes("ISO8859-1"), "UTF-8");
		System.out.println("==========================="+cname);
		String sql = "SELECT a.id,a.account,a.affair,a.lacale,to_char(a.startime,'yyyy-mm-dd')starttime,"
				+ "to_char(a.endtime,'yyyy-mm-dd')endtime,a.manager,a.detail,a.score,a.isdeal,a.iscomplete "
				+ "FROM AFFAIRS a where  a.account=?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,cname);
		return list;
	}

}
