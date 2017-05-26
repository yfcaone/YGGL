package cn.yfc.aone.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AndroidDaoImpl implements AndroidDao{

	@Autowired
	JdbcTemplate JdbcTemplate;
	@SuppressWarnings("unused")
	@Override
	public int addData(String account,String affair, String locale, String starttime, String endtime, String manager, String username,
			int number) {
		int i = 0;
		String sTime = starttime.substring(0,10);
		String eTime = endtime.substring(0,10);
			String sql = "insert into affairs (ID,Account,Affair,Lacale,Startime,Endtime,Manager,ISCOMPLETE,Username,Data,isdeal) values"
					+ " (AFFAIRS_S.NEXTVAL,?,?,?"
					+ ",to_date(?,'yyyy-mm-dd'),to_date(?"
					+ ",'yyyy-mm-dd'),?,'未完成',?,SYSDATE,'0')";
			JdbcTemplate.update(sql, account,affair,locale,sTime,eTime,manager,username);
			/*String sql1 = " insert into project_date VALUES(PROJECT_DATE_S.NEXTVAL,?,'" + map.get("affair") + "',to_date('"
					+ map.get("starttime") + "','yyyy-mm-dd'))";
			jdbcTemplate.update(sql1, number);*/
			String sql2 = "insert into wage (wid,w_project_number,w_project_name,w_starttime)values (WAGE_S.NEXTVAL,?,?"
					+ ",to_date(?,'yyyy-mm-dd'))";
			JdbcTemplate.update(sql2, number,affair,sTime);
			i = 1;
			return i;
		
		
		
	}
	@Override
	public int addRzInfo(String log, String log_account, String log_affair, String username) {
		int i = 0;
		String sql = "insert into log_content values(LOG_CONTENT_S.NEXTVAL,?,sysdate,?,?,?)";
		JdbcTemplate.update(sql, log_account,log, log_affair, username);
		i = 1;
		return i;
		
		
	}
	@Override
	public void addMapInfo(String lontitude, String latitude, String time, String username) {
		String times = time.substring(0,10);
		System.out.println(times);
		String sql = "insert into COORDINATE values (COORDINATE_S.Nextval,?,?,(select uname from users where uaccount = ?),to_date(?,'yyyy-mm-dd HH24:mi:ss'))";
		JdbcTemplate.update(sql,lontitude,latitude,username,time);
		System.out.println("====================================================================================");
	}

}
