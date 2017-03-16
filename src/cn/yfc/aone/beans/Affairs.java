package cn.yfc.aone.beans;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Affairs {
    private BigDecimal id;

    private String account;

    private String affair;

    private String lacale;

    private String startime;

    private String endtime;

    private String manager;

    private String detail;

    private String score;
    
    private String isdeal;

    public String getIsdeal() {
		return isdeal;
	}

	public void setIsdeal(String isdeal) {
		this.isdeal = isdeal;
	}

	public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getAffair() {
        return affair;
    }

    public void setAffair(String affair) {
        this.affair = affair == null ? null : affair.trim();
    }

    public String getLacale() {
        return lacale;
    }

    public void setLacale(String lacale) {
        this.lacale = lacale == null ? null : lacale.trim();
    }

    public String getStartime() {
        return startime;
    }

    public void setStartime(Date startime) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date=new java.util.Date();
        String strstartime=sdf.format(startime);
        this.startime = strstartime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date=new java.util.Date();
        String stredntime=sdf.format(endtime);
        this.endtime = stredntime;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

	@Override
	public String toString() {
		return "[id=" + id + ", account=" + account + ", affair=" + affair + ", lacale=" + lacale
				+ ", startime=" + startime + ", endtime=" + endtime + ", manager=" + manager + ", detail=" + detail
				+ ", score=" + score + "]";
	}
    
    
}