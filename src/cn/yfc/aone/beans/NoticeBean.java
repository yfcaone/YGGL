package cn.yfc.aone.beans;

public class NoticeBean {
	private String id;
	private String title;
	private String date;
	private String endDate;
	private String content;
	private String fileLocal;
	private String receiverDept;
	private String fileName;
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFileLocal() {
		return fileLocal;
	}
	public void setFileLocal(String fileLocal) {
		this.fileLocal = fileLocal;
	}
	public String getReceiverDept() {
		return receiverDept;
	}
	public void setReceiverDept(String receiverDept) {
		this.receiverDept = receiverDept;
	}
	@Override
	public String toString() {
		return "NoticeBean [id=" + id + ", title=" + title + ", date=" + date + ", endDate=" + endDate + ", content="
				+ content + ", fileLocal=" + fileLocal + ", receiverDept=" + receiverDept + ", fileName=" + fileName
				+ "]";
	}
	
	
	
}
