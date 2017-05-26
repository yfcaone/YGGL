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
		String sql = "SELECT a.id,a.account,a.affair,a.lacale,to_char(a.startime,'yyyy-mm-dd')starttime,"
				+ "to_char(a.endtime,'yyyy-mm-dd')endtime,a.manager,a.detail,a.score,a.isdeal,a.iscomplete "
				+ "FROM AFFAIRS a where a.iscomplete='未完成'and a.username<>?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, cname);
		return list;
	}

	@Override
	public List<Map<String, Object>> getSelfInfo(String username) throws Exception {
		String cname = new String(username.getBytes("ISO8859-1"), "UTF-8");
		String sql = "SELECT a.id,a.account,a.affair,a.lacale,to_char(a.startime,'yyyy-mm-dd')starttime,"
				+ "to_char(a.endtime,'yyyy-mm-dd')endtime,a.manager,a.detail,a.score,a.isdeal,a.iscomplete "
				+ "FROM AFFAIRS a where  a.username=?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, cname);
		return list;
	}

	@Override
	public void addLogInfo(Map<String, Object> map, String eaccount, String eaffair, String username) {
		String sql = "insert into log_content values(LOG_CONTENT_S.NEXTVAL,?,sysdate,'" + map.get("log") + "',?,?)";
		jdbcTemplate.update(sql, eaccount, eaffair, username);
	}

	@Override
	public void addData(Map<String, Object> map, String username, int number) {
		String sql = "insert into affairs (ID,Account,Affair,Lacale,Startime,Endtime,Manager,ISCOMPLETE,Username,Data,isdeal) values"
				+ " (AFFAIRS_S.NEXTVAL,'" + map.get("account") + "','" + map.get("affair") + "','" + map.get("lacale")
				+ "',to_date('" + map.get("starttime") + "','yyyy-mm-dd'),to_date('" + map.get("endtime")
				+ "','yyyy-mm-dd'),'" + map.get("manager") + "','未完成',?,SYSDATE,'0')";
		jdbcTemplate.update(sql, username);
		/*String sql1 = " insert into project_date VALUES(PROJECT_DATE_S.NEXTVAL,?,'" + map.get("affair") + "',to_date('"
				+ map.get("starttime") + "','yyyy-mm-dd'))";
		jdbcTemplate.update(sql1, number);*/
		/*String sql2 = "insert into wage (wid,w_project_number,w_project_name,w_starttime)values (WAGE_S.NEXTVAL,?,'"
				+ map.get("affair") + "',to_date('" + map.get("starttime	") + "','yyyy-mm-dd'))";
		jdbcTemplate.update(sql2, number);*/
	}

	@Override
	public int getJudgmentPwd(String username, String old_pwd) {
		String sql = "select upassword　from users where uaccount = ? ";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql,username);
		String pwd = (String) map.get("UPASSWORD");
		if (pwd.equals(old_pwd)) {
			return 1;
		}else {
			return 0;
		}
		
	}

	@Override
	public void updatePwd(String new_pwd, String old_pwd) {
		String sql = "update  users set upassword = ? where upassword = ?";
		jdbcTemplate.update(sql,new_pwd,old_pwd);
		
	}

	@Override
	public Map<String, Object> getName(String cname) {
		String sql = "SELECT UNAME FROM USERS WHERE UACCOUNT= ?";
		Map<String, Object>map = jdbcTemplate.queryForMap(sql,cname);
		return map;
	}



}
