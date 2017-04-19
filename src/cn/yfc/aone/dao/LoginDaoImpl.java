package cn.yfc.aone.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public Map<String, Object> getLogContent(String username, String password, String role) {
		String sql = "select * from users u where u.uaccount =?and u.upassword=?and u.uroot = ? ";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql,username,password,role);
		return map;
	}

	
}
