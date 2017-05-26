package cn.yfc.aone.dao;

public interface AndroidDao {

	/**
	 * 添加用户信息
	 * @param account
	 * @param affair
	 * @param locale
	 * @param starttime
	 * @param endtime
	 * @param manager
	 * @param username
	 * @param number
	 * @return
	 */
	int addData(String account,String affair, String locale, String starttime, String endtime, String manager, String username,
			 int number);

	/**
	 * 添加日志信息
	 * @param log
	 * @param log_account
	 * @param log_affair
	 * @param username
	 * @return
	 */
	int addRzInfo(String log, String log_account, String log_affair, String username);

	/**
	 * 添加经纬度
	 * @param lontitude
	 * @param latitude
	 * @param time
	 * @param username
	 */
	void addMapInfo(String lontitude, String latitude, String time, String username);

}
