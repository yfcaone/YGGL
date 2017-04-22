package cn.yfc.aone.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TravelDaoImpl implements TravelDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<Map<String, Object>> getCityGrade() {
		String sql = "select C.T_CLEVEL,C.T_CLEVEL_CODE from city_grade C";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		System.out.println(list);
		return list;
	}
	@Override
	public List<Map<String, Object>> getCity(String city) {
		String sql = "select C.CITY,C.CI_CODE from citys C where C.cl_code =? ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,city);
		return list;
	}
	@Override
	public List<Map<String, Object>> getDepartment() {
		String sql = "select d.department,d.department_code from department d";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		System.out.println(list);
		return list;
	}
	@Override
	public List<Map<String, Object>> getPost(String department) {
		String sql = "select p.dpost,p.dpost_code from department_post_info p where p.department_code=? ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,department);
		System.out.println(list);
		return list;
	}
	@Override
	public void addTravelInfo(String city, String affair_name, String go_date,
			String post_name, String job_number,String username) {
		
		String sql = "INSERT INTO TRAVEL_INFO VALUES (TRAVEL_INFO_S.NEXTVAL,?,"
				+ "TO_DATE(?,'YYYY-MM-DD'),?,"
				+ "(select i.dpost from department_post_info i where i.dpost_code = ? ),"
				+ "(select c.city from citys c where c.ci_code = ?),?,"
				+ "SYSDATE,(select s.sname from  staffs s where s.job_number = ? ))";
		jdbcTemplate.update(sql,affair_name,go_date.substring(0,10),job_number,post_name,city,username,job_number);
		System.out.println("---------------成功-----------------");
	}
	@Override
	public List<Map<String, Object>> getTravelInfo() {
		String sql = "select t.tid, t.taffair,t.job_number,t.tname,t.dpost,to_char(t.tdate,'yyyy-mm-dd')tdate from travel_info t";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

}
