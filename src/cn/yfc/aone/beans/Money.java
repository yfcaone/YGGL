package cn.yfc.aone.beans;

public class Money {
	private String RID;
	private String R_JOB_NUMBER;
	private String R_JOB_NAME;
	private String R_POST;
	private String R_DAYS;
	private String MONEYS;
	private String R_ENDTIME;
	private String R_RELEASETIME;
	
	
	
	public Money(String rID, String r_JOB_NUMBER, String r_JOB_NAME, String r_POST, String r_DAYS, String mONEYS,
			String r_ENDTIME, String r_RELEASETIME) {
		super();
		RID = rID;
		R_JOB_NUMBER = r_JOB_NUMBER;
		R_JOB_NAME = r_JOB_NAME;
		R_POST = r_POST;
		R_DAYS = r_DAYS;
		MONEYS = mONEYS;
		R_ENDTIME = r_ENDTIME;
		R_RELEASETIME = r_RELEASETIME;
	}
	public String getRID() {
		return RID;
	}
	public void setRID(String rID) {
		RID = rID;
	}
	public String getR_JOB_NUMBER() {
		return R_JOB_NUMBER;
	}
	public void setR_JOB_NUMBER(String r_JOB_NUMBER) {
		R_JOB_NUMBER = r_JOB_NUMBER;
	}
	public String getR_JOB_NAME() {
		return R_JOB_NAME;
	}
	public void setR_JOB_NAME(String r_JOB_NAME) {
		R_JOB_NAME = r_JOB_NAME;
	}
	public String getR_POST() {
		return R_POST;
	}
	public void setR_POST(String r_POST) {
		R_POST = r_POST;
	}
	public String getR_DAYS() {
		return R_DAYS;
	}
	public void setR_DAYS(String r_DAYS) {
		R_DAYS = r_DAYS;
	}
	public String getMONEYS() {
		return MONEYS;
	}
	public void setMONEYS(String mONEYS) {
		MONEYS = mONEYS;
	}
	public String getR_ENDTIME() {
		return R_ENDTIME;
	}
	public void setR_ENDTIME(String r_ENDTIME) {
		R_ENDTIME = r_ENDTIME;
	}
	public String getR_RELEASETIME() {
		return R_RELEASETIME;
	}
	public void setR_RELEASETIME(String r_RELEASETIME) {
		R_RELEASETIME = r_RELEASETIME;
	}
	
	

}
