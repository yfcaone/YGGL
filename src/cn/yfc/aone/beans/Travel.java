package cn.yfc.aone.beans;


public class Travel {
	
private String TID;
	private String TAFFAIR;
	private String JOB_NUMBER;
	private String TNAME;
	private String DPOST;
	private String TDATE;

	
	
	
	public Travel( String tID,String tAFFAIR, String jOB_NUMBER, String tNAME, String dPOST,String tDATE) {
		super();
		TID = tID;
		TAFFAIR = tAFFAIR;
		setJOB_NUMBER(jOB_NUMBER);
		TNAME = tNAME;
		DPOST = dPOST;
		setTDATE(tDATE);
	
	}

	public String getTAFFAIR() {
		return TAFFAIR;
	}
	public void setTAFFAIR(String tAFFAIR) {
		TAFFAIR = tAFFAIR;
	}
	public String getTNAME() {
		return TNAME;
	}
	public void setTNAME(String tNAME) {
		TNAME = tNAME;
	}
	public String getDPOST() {
		return DPOST;
	}
	public void setDPOST(String dPOST) {
		DPOST = dPOST;
	}

	public String getTID() {
		return TID;
	}

	public void setTID(String tID) {
		TID = tID;
	}

	public String getJOB_NUMBER() {
		return JOB_NUMBER;
	}

	public void setJOB_NUMBER(String jOB_NUMBER) {
		JOB_NUMBER = jOB_NUMBER;
	}

	public String getTDATE() {
		return TDATE;
	}

	public void setTDATE(String tDATE) {
		TDATE = tDATE;
	}


	
}
