package cn.yfc.aone.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TravelDaoImpl implements TravelDao {

	List<Map<String, Object>> createList;
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
	public List<Map<String, Object>> getNumber(String post_name) {
		String sql = "select s.job_number from staffs s where s.spost ="
				+ " (select p.dpost from department_post_info p where p.dpost_code = ?) ORDER BY JOB_NUMBER";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, post_name);
		return list;
	}

	@Override
	public List<Map<String, Object>> addCreateTravelInfo(String add_city, String add_affair_name, String add_go_date,
			int number, String username) {
		String sql = " insert into project_date VALUES(PROJECT_DATE_S.NEXTVAL,?,?,to_date(?,'yyyy-mm-dd'),?,sysdate,(SELECT CITY FROM CITYS WHERE CI_CODE = ?))";
		jdbcTemplate.update(sql, number, add_affair_name, add_go_date.substring(0, 10), username, add_city);
		String sql2 = "insert into project (PID,P_NUMBER,P_NAME,P_CITY,P_DATE_GO,P_USERNAME,P_CREATE_DATE) VALUES"
				+ "(PROJECT_S.NEXTVAL,?,?,(SELECT CITY FROM CITYS WHERE CI_CODE = ?),TO_DATE(?,'YYYY-MM-DD'),?,sysdate)";
		jdbcTemplate.update(sql2, number, add_affair_name, add_city, add_go_date.substring(0, 10), username);
		String sql3 = "insert into wage (wid,w_project_number,w_project_name,w_starttime)values (WAGE_S.NEXTVAL,?,?,TO_DATE(?,'YYYY-MM-DD'))";
		jdbcTemplate.update(sql3, number, add_affair_name, add_go_date.substring(0, 10));
		String sql1 = "select  pd.p_number from PROJECT_DATE pd where pd.p_name = ? and pd.p_city = (SELECT CITY FROM CITYS WHERE CI_CODE = ?)";
		createList = jdbcTemplate.queryForList(sql1, add_affair_name, add_city);
		return createList;
	}

	@Override
	public void addTravelInfo(String city, String affair_name, String go_date, String post_name, String job_number,
			String username, String p_number) {
		String sql = "INSERT INTO TRAVEL_INFO (TID,TAFFAIR,TDATE,JOB_NUMBER,DPOST,TCITY,TUSERNAME,TCREATE_DATE,TNAME,P_NUMBER)"
				+ " VALUES  (TRAVEL_INFO_S.NEXTVAL,?," + "TO_DATE(?,'YYYY-MM-DD'),?,"
				+ "(select i.dpost from department_post_info i where i.dpost_code = ? ),"
				+ "(select c.city from citys c where c.ci_code = ?),?,"
				+ "SYSDATE,(select s.sname from  staffs s where s.job_number = ? ),?)";
		BigDecimal nb = (BigDecimal) createList.get(0).get("P_NUMBER");
		String number = nb.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
		jdbcTemplate.update(sql, affair_name, go_date.substring(0, 10), job_number, post_name, city, username,
				job_number, number);
	}

	@Override
	public List<Map<String, Object>> getTravelInfo(String condition, int begin, int end) {
		String sql = "SELECT * FROM(SELECT ROWNUM RN,g.* FROM"
				+ " (select t.tid, t.taffair,t.job_number,t.tname,t.dpost,to_char(t.tdate,'yyyy-mm-dd')tdate,"
				+ "t.p_number,to_char(t.p_evacuate_date,'yyyy-mm-dd')p_evacuate_date from travel_info t" + condition
				+ "  group by t.tid, t.taffair,t.job_number,t.tname,t.dpost,t.tdate,t.p_number,t.p_evacuate_date"
				+ " order by  t.taffair,t.job_number desc )g)where RN>=? and RN<=?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, begin, end);
		return list;
	}

	@Override
	public void addLeaveInfo(String log_number, String start_date, String end_date, String leave_cause,
			String p_number) {
		Integer i = new Integer(0);
		String sql1 = "select * from leave where p_number = (select p_number from project where pid = ?)";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql1, p_number);
		for (Map<String, Object> map : list) {
			String lname = (String) map.get("LNAME");
			if (log_number.equals(lname)) {
				System.out.println("是不是相等");
				String sql3 = "update leave set start_time = to_date(?,'yyyy-mm-dd') , end_time =to_date( ?,'yyyy-mm-dd') where p_number = (select p_number from project where pid = ?) and lname = ?";
				jdbcTemplate.update(sql3, start_date.substring(0, 10), end_date.substring(0, 10), p_number, log_number);
			} else {
				i = i + 1;
			}
		}
		if (i == list.size()) {
			String sql = "INSERT INTO LEAVE VALUES (LEAVE_S.NEXTVAL,(select job_number from staffs where sname = ?),?,"
					+ "TO_DATE(?,'YYYY-MM-DD'),TO_DATE(?,'YYYY-MM-DD'),?,(select p_number from project where pid = ?))";
			jdbcTemplate.update(sql, log_number, log_number, start_date.substring(0, 10), end_date.substring(0, 10),
					leave_cause, p_number);
		}
	}

	@Override
	public List<Map<String, Object>> getLeaveInfo(int begin, int end, String condition) {
		String sql = "SELECT * FROM(SELECT ROWNUM RN,g.* FROM"
				+ " (select l.lid,l.log_number,l.lname,to_char(l.start_time,'yyyy-mm-dd')start_time,"
				+ "to_char(l.end_time,'yyyy-mm-dd')end_time,(l.end_time-l.start_time+1)days,l.p_number"
				+ " from leave l where (l.end_time-l.start_time+1)<800 " + condition
				+ "group by l.lid,l.log_number,l.lname,l.start_time,l.end_time,l.p_number"
				+ " order by l.p_number,l.log_number desc )g )where RN>=? and RN<=?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, begin, end);
		return list;
	}

	@Override
	public void addReimbursementInfo(String bx_log_number, String bx_invoice, String bx_maney, String username) {
		String sql = "INSERT INTO REIMBURSEMENT VALUES (REIMBURSEMENT_S.NEXTVAL,?,"
				+ "(select s.sname from  staffs s where s.job_number = ? ),?,?,SYSDATE,?)";
		jdbcTemplate.update(sql, bx_log_number, bx_log_number, bx_invoice, bx_maney, username);

	}

	@Override
	public List<Map<String, Object>> getReimbursementInfo(int begin, int end) {
		String sql = "SELECT * FROM(SELECT ROWNUM RN,g.* FROM "
				+ "(select R.RID,R.LOG_NUMBER,R.RNAME,R.INVOICE_TYPE,R.RMONEY,"
				+ "TO_CHAR(R.RDATE,'YYYY-MM-DD')RDATE from REIMBURSEMENT R"
				+ " GROUP BY R.RID,R.LOG_NUMBER,R.RNAME,R.INVOICE_TYPE,R.RMONEY,R.RDATE order by R.Rdate desc )g"
				+ " )where RN>=? and RN<=?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, begin, end);
		return list;
	}

	@Override
	public void addLoanInfo(String jk_log_numbers, String jk_loan, String jk_money, String username) {
		String sql = "INSERT INTO LOAN VALUES (LOAN_S.NEXTVAL,?,"
				+ "(select s.sname from  staffs s where s.job_number = ? ),?,?,SYSDATE,'未还款',?)";
		jdbcTemplate.update(sql, jk_log_numbers, jk_log_numbers, jk_loan, jk_money, username);

	}

	@Override
	public List<Map<String, Object>> getloanInfo(int begin, int end) {
		String sql = "SELECT * FROM(SELECT ROWNUM RN,g.* FROM "
				+ "(SELECT L.LID,L.LOG_NUMBER,L.LNAME,L.LOAN_REASON,L.LOAN_MONEY,TO_CHAR(L.LOAN_DATE,'YYYY-MM-DD')LOAN_DATE,"
				+ "L.ISREPAYMENT FROM LOAN L  group by "
				+ "l.lid,log_number,l.lname,l.loan_reason,l.loan_money,l.loan_date,L.ISREPAYMENT order by l.loan_date desc )g )"
				+ "where RN>=? and RN<=?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, begin, end);
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
		String sql2 = "update wage set w_endtime = to_date(?,'yyyy-mm-dd')+1,w_money = ? where w_project_number = ? and w_starttime =to_date(?,'yyyy-mm-dd')";
		jdbcTemplate.update(sql2, end_date, moneys, p_number, start_date);
		// 添加工资表数据
		String sql3 = "insert into wage (wid,w_project_number,w_project_name,w_starttime)values "
				+ "(WAGE_S.NEXTVAL,?,?,to_date(?,'yyyy-mm-dd')+1)";
		jdbcTemplate.update(sql3, p_number, affair_name, end_date);

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

	@Override
	public void delTravelInfo(String tID) {
		String sql = "delete from TRAVEL_INFO  where tid = ?";
		jdbcTemplate.update(sql, tID);
	}

	@Override
	public void resetPwd(String VACCOUNT) {
		String sql = "update  users set upassword = '111111' where uaccount = ?";
		jdbcTemplate.update(sql, VACCOUNT);

	}

	@Override
	public List<Map<String, Object>> getloanCount() {
		String sql = "select L.LID,L.LOG_NUMBER,L.LNAME,L.LOAN_REASON,L.LOAN_MONEY,"
				+ "TO_CHAR(L.LOAN_DATE,'YYYY-MM-DD')LOAN_DATE,L.ISREPAYMENT from LOAN L ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getReimbursementCount() {
		String sql = "select R.RID,R.LOG_NUMBER,R.RNAME,R.INVOICE_TYPE,R.RMONEY,"
				+ "TO_CHAR(R.RDATE,'YYYY-MM-DD')RDATE from REIMBURSEMENT R";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getTravel_Info(String p_number) {
		String sql = "SELECT T.TID,T.TAFFAIR,TO_CHAR(T.TDATE,'YYYY-MM-DD')TDATE,T.JOB_NUMBER,T.DPOST,T.TCITY,T.TNAME,"
				+ "T.P_NUMBER FROM TRAVEL_INFO T WHERE P_NUMBER = ?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, p_number);
		return list;
	}

	@Override
	public List<Map<String, Object>> getLeave_Info(String p_number) {
		String sql = "SELECT L.LOG_NUMBER,L.LNAME,TO_CHAR(L.START_TIME,'YYYY-MM-DD')START_TIME,TO_CHAR(L.END_TIME,'YYYY-MM-DD')END_TIME,"
				+ "P_NUMBER FROM LEAVE L WHERE P_NUMBER = ?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, p_number);
		return list;
	}

	@Override
	public void addAllWageInfo(List<Map<String, Object>> datas, String start_date1, String end_date1) {
		String start_date = start_date1.substring(0, 10);
		String end_date = end_date1.substring(0, 10);
		for (Map<String, Object> map : datas) {
			String sql = "INSERT INTO RELEASE_WAGE VALUES (RELEASE_WAGE_S.NEXTVAL,'" + map.get("JOB_NUMBER") + "','"
					+ map.get("DPOST") + "',to_date(?,'yyyy-mm-dd')-to_date(?,'yyyy-mm-dd')+1-'" + map.get("DAYS")
					+ "',to_date(?,'yyyy-mm-dd'),SYSDATE,'" + map.get("TNAME") + "','" + map.get("P_NUMBER") + "')";
			jdbcTemplate.update(sql, end_date, start_date, end_date);
		}

	}

	@Override
	public List<Map<String, Object>> getXmygxxInfo() {
		String sql = "SELECT P.P_NUMBER,P.P_NAME,P.P_CITY,TO_CHAR(P.P_DATE_GO,'YYYY-MM-DD')P_DATE,P.P_USERNAME,"
				+ "TO_CHAR(P_CREATE_DATE,'YYYY-MM-DD')P_CREATE_DATE FROM PROJECT P";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public void addTravelInfos(String p_number, String p_city, String go_date, String post_name, String job_number,
			String p_name, String username) {
		String go_dates = go_date.substring(0, 10);
		// 添加在外员工信息
		String sql = "INSERT INTO TRAVEL_INFO (TID,TAFFAIR,TDATE,JOB_NUMBER,DPOST,TCITY,TUSERNAME,TCREATE_DATE,TNAME,P_NUMBER) VALUES "
				+ " (TRAVEL_INFO_S.NEXTVAL,?," + "TO_DATE(?,'YYYY-MM-DD'),?,"
				+ " (select i.dpost from department_post_info i where i.dpost_code = ? ),"
				+ "?,?, SYSDATE,(select s.sname from  staffs s where s.job_number = ? ),?)";
		jdbcTemplate.update(sql, p_name, go_dates, job_number, post_name, p_city, username, job_number, p_number);
		// 请假时间

		String sql3 = "insert into leave (lid,log_number,lname,start_time,end_time,leave_cause,p_number)"
				+ "values(LEAVE_S.NEXTVAL,?,(select s.sname from  staffs s where s.job_number = ? ),"
				+ "to_date(?,'yyyy-mm-dd')-10000,to_date(?,'yyyy-mm-dd')-1,'添加',?)";
		jdbcTemplate.update(sql3, job_number, job_number, go_dates, go_dates, p_number);
	}

	@Override
	public void addTravelYgclInfo(String p_NUMBER, String jOB_NUMBER, String p_EVACUATE_DATE) {
		Integer i = new Integer(0);
		String date_cl = p_EVACUATE_DATE.substring(0, 10);
		// 添加撤离时间
		String sql = "update travel_info set p_evacuate_date = to_date(?,'yyyy-mm-dd')  where job_number = ? and p_number = ?";
		jdbcTemplate.update(sql, date_cl, jOB_NUMBER, p_NUMBER);
		// 请假时间
		String sql2 = "select * from leave where p_number = ?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql2, p_NUMBER);
		for (Map<String, Object> map : list) {
			BigDecimal log_number = (BigDecimal) map.get("LOG_NUMBER");
			int number = log_number.intValue();
			String lnumber = Integer.toString(number);
			if (jOB_NUMBER.equals(lnumber)) {
				String sql3 = "update leave set start_time = to_date(?,'yyyy-mm-dd'),end_time = to_date(?,'yyyy-mm-dd')+10000  where p_number = ? and log_number = ?";
				jdbcTemplate.update(sql3, date_cl, date_cl, p_NUMBER, jOB_NUMBER);
			} else {
				i = i + 1;
			}
		}
		if (i == list.size()) {
			String sql1 = "insert into leave (lid,log_number,lname,start_time,end_time,leave_cause,p_number)"
					+ "values (LEAVE_S.NEXTVAL,?,(select s.sname from  staffs s where s.job_number = ? ),"
					+ "to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd')+10000,'撤离',?)";
			jdbcTemplate.update(sql1, jOB_NUMBER, jOB_NUMBER, date_cl, date_cl, p_NUMBER);
		}
	}

	@Override
	public List<Map<String, Object>> getTravelInfos(String p_NUMBERS) {
		String sql = "select t.tid, t.taffair,t.job_number,t.tname,t.dpost,"
				+ "to_char(t.tdate,'yyyy-mm-dd')tdate,t.p_number,to_char(t.p_evacuate_date,'yyyy-mm-dd')p_evacuate_date"
				+ " from travel_info t where t.p_number = ?  order by t.job_number desc ";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, p_NUMBERS);
		return list;
	}

	@Override
	public List<Map<String, Object>> getAllTravelInfo(String condition) {
		String sql = "select t.tid, t.taffair,t.job_number,t.tname,t.dpost,"
				+ "to_char(t.tdate,'yyyy-mm-dd')tdate,t.p_number,to_char(t.p_evacuate_date,'yyyy-mm-dd')p_evacuate_date"
				+ " from travel_info t " + condition;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getXmbh() {
		String sql = "select p_number,pid from project";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getGhData(String p_number) {
		String sql = "select job_number,tname from travel_info where p_number = (select p_number from project where pid =? )";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, p_number);
		return list;
	}

	@Override
	public List<Map<String, Object>> getAllLeave(String condition) {
		String sql = "select l.lid,l.log_number,l.lname,to_char(l.start_time,'yyyy-mm-dd')start_time,"
				+ "to_char(l.end_time,'yyyy-mm-dd')end_time,(l.end_time-l.start_time+1)days,"
				+ "l.p_number from leave l where (l.end_time-l.start_time+1)<800 " + condition
				+ "order by l.p_number,l.log_number desc";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

}
