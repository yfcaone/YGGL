package cn.yfc.aone.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sun.script.javascript.JSAdapter;

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
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, city);
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
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, department);
		System.out.println(list);
		return list;
	}

	@Override
	public void addTravelInfo(String city, String affair_name, String go_date, String post_name, String job_number,
			String username) {

		String sql = "INSERT INTO TRAVEL_INFO VALUES (TRAVEL_INFO_S.NEXTVAL,?," + "TO_DATE(?,'YYYY-MM-DD'),?,"
				+ "(select i.dpost from department_post_info i where i.dpost_code = ? ),"
				+ "(select c.city from citys c where c.ci_code = ?),?,"
				+ "SYSDATE,(select s.sname from  staffs s where s.job_number = ? ))";
		jdbcTemplate.update(sql, affair_name, go_date.substring(0, 10), job_number, post_name, city, username,
				job_number);

	}

	@Override
	public List<Map<String, Object>> getTravelInfo() {
		String sql = "select t.tid, t.taffair,t.job_number,t.tname,t.dpost,"
				+ "to_char(t.tdate,'yyyy-mm-dd')tdate from travel_info t";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public void addLeaveInfo(String log_number, String start_date, String end_date, String leave_cause) {
		String sql = "INSERT INTO LEAVE VALUES (LEAVE_S.NEXTVAL,?,"
				+ "(select s.sname from  staffs s where s.job_number = ? ),"
				+ "TO_DATE(?,'YYYY-MM-DD'),TO_DATE(?,'YYYY-MM-DD'),?)";
		jdbcTemplate.update(sql, log_number, log_number, start_date.substring(0, 10), end_date.substring(0, 10),
				leave_cause);

	}

	@Override
	public List<Map<String, Object>> getLeaveInfo() {
		String sql = "select l.lid,l.log_number,l.lname,to_char(l.start_time,'yyyy-mm-dd')start_time,"
				+ "to_char(l.end_time,'yyyy-mm-dd')end_time,(l.end_time-l.start_time+1)days from leave l";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public void addReimbursementInfo(String bx_log_number, String bx_invoice, String bx_maney, String username) {
		String sql = "INSERT INTO REIMBURSEMENT VALUES (REIMBURSEMENT_S.NEXTVAL,?,"
				+ "(select s.sname from  staffs s where s.job_number = ? ),?,?,SYSDATE,?)";
		jdbcTemplate.update(sql, bx_log_number, bx_log_number, bx_invoice, bx_maney, username);
		
	}

	@Override
	public List<Map<String, Object>> getReimbursementInfo() {
		String sql = "select R.RID,R.LOG_NUMBER,R.RNAME,R.INVOICE_TYPE,R.RMONEY,TO_CHAR(R.RDATE,'YYYY-MM-DD')RDATE from REIMBURSEMENT R";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		System.out.println("---------------成功2-----------------");
		return list;
	}

	@Override
	public void addLoanInfo(String jk_log_numbers, String jk_loan, String jk_money, String username) {
		String sql = "INSERT INTO LOAN VALUES (LOAN_S.NEXTVAL,?,"
				+ "(select s.sname from  staffs s where s.job_number = ? ),?,?,SYSDATE,'未还款',?)";
		jdbcTemplate.update(sql, jk_log_numbers, jk_log_numbers, jk_loan, jk_money, username);
		
	}

	@Override
	public List<Map<String, Object>> getloanInfo() {
		String sql = "SELECT L.LID,L.LOG_NUMBER,L.LNAME,L.LOAN_REASON,L.LOAN_MONEY,TO_CHAR(L.LOAN_DATE,'YYYY-MM-DD')LOAN_DATE,L.ISREPAYMENT FROM LOAN L";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		System.out.println("---------------成功2-----------------");
		return list ;
	}

}
