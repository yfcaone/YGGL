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
			String username,int number) {
		String sql1 = " insert into project_date VALUES(PROJECT_DATE_S.NEXTVAL,?,?,to_date(?,'yyyy-mm-dd'))";
		jdbcTemplate.update(sql1,number,affair_name,go_date.substring(0, 10));
		String sql = "INSERT INTO TRAVEL_INFO VALUES (TRAVEL_INFO_S.NEXTVAL,?," + "TO_DATE(?,'YYYY-MM-DD'),?,"
				+ "(select i.dpost from department_post_info i where i.dpost_code = ? ),"
				+ "(select c.city from citys c where c.ci_code = ?),?,"
				+ "SYSDATE,(select s.sname from  staffs s where s.job_number = ? ),?)";
		jdbcTemplate.update(sql, affair_name, go_date.substring(0, 10), job_number, post_name, city, username,
				job_number,number);

	}

	@Override
	public List<Map<String, Object>> getTravelInfo() {
		String sql = "select t.tid, t.taffair,t.job_number,t.tname,t.dpost,"
				+ "to_char(t.tdate,'yyyy-mm-dd')tdate,t.p_number from travel_info t";
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
		String sql = "select R.RID,R.LOG_NUMBER,R.RNAME,R.INVOICE_TYPE,R.RMONEY,"
				+ "TO_CHAR(R.RDATE,'YYYY-MM-DD')RDATE from REIMBURSEMENT R";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
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
		String sql = "SELECT L.LID,L.LOG_NUMBER,L.LNAME,L.LOAN_REASON,L.LOAN_MONEY,"
				+ "TO_CHAR(L.LOAN_DATE,'YYYY-MM-DD')LOAN_DATE,L.ISREPAYMENT FROM LOAN L";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public Map<String, Object> getProjectInfo(String project_number) {
		String sql = "select pd.p_number,pd.p_name,to_char(pd.p_date,'yyyy-mm-dd')p_date from project_date pd"
				+ " where pd.p_number = ? ";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql, project_number);
		return map;
	}

	@Override
	public Map<String, Object> getSubsidyInfo(Map<String, Object> map) {
		String sql = "select s.s_stay,s.s_food,s.s_traffic,tc.tcity from subsidy s,"
				+ "(select distinct(t.tcity)tcity from TRAVEL_INFO  t where " + "t.taffair = '" + map.get("p_name")
				+ "')tc where s.s_level = (select c.clevel from citys c " + "where c.city =tc.tcity)";
		Map<String, Object> map2 = jdbcTemplate.queryForMap(sql);
		return map2;
	}

	@Override
	public List<Map<String, Object>> getWageInfo(String affair_name, String city_name, String start_date1,
			String end_date1, String stay_subsidy, String food_subsidy, String traffic_subsidy, String p_number,
			int moneys) {
		String start_date = start_date1.substring(0, 10);
		String end_date = end_date1.substring(0, 10);
		// 更新项目表时间语句
		String sql1 = " update project_date set  p_date = to_date(?,'yyyy-mm-dd')+1 where p_number = ?";
		jdbcTemplate.update(sql1, end_date, p_number);
		// 更新工资表语句
		String sql2 = "update wage set w_endtime = to_date(?,'yyyy-mm-dd')+1,w_money = ? where w_project_number = ?";
		jdbcTemplate.update(sql2, end_date, moneys, p_number);
		// 添加工资表数据
		String sql3 = "insert into wage (wid,w_project_number,w_project_name,w_starttime)values "
				+ "(WAGE_S.NEXTVAL,?,?,to_date(?,'yyyy-mm-dd')+1)";
		jdbcTemplate.update(sql3, p_number, affair_name, end_date);
		// 获得项目员工及休假天数
		String sql4 = "select s.tname,s.dpost,nvl(ss.log_number,s.job_number)job_number , nvl(ss.days,0)days"
				+ " from (select t.tname,t.dpost,t.job_number from travel_info t where t.taffair = ?)s"
				+ " full outer join (select l.log_number ,(l.end_time - l.start_time+1)days from LEAVE l "
				+ "where l.start_time>=to_date(?,'yyyy-mm-dd') and l.end_time <=to_date(?,'yyyy-mm-dd')) ss"
				+ " on s.job_number=ss.log_number";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql4, affair_name, start_date, end_date);
		for (Map<String, Object> map : list) {
			String sql = "INSERT INTO RELEASE_WAGE VALUES (RELEASE_WAGE_S.NEXTVAL,'" + map.get("JOB_NUMBER") + "','"
					+ map.get("DPOST") + "',to_date(?,'yyyy-mm-dd')-to_date(?,'yyyy-mm-dd')+1-'" + map.get("DAYS")
					+ "',to_date(?,'yyyy-mm-dd'),SYSDATE,'" + map.get("TNAME") + "')";
			jdbcTemplate.update(sql, end_date, start_date, end_date);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getAllMoneyInfo() {
		String sql = "select rw.rid,rw.r_job_number,rw.r_job_name,rw.r_post,rw.r_days,"
				+ "to_char(rw.r_endtime,'yyyy-mm-dd')r_endtime,to_char(rw.r_releasetime,'yyyy-mm-dd')r_releasetime"
				+ " from RELEASE_WAGE rw where rw.r_releasetime>=sysdate";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

}
