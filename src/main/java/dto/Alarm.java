package dto;

import java.util.Date;

public class Alarm {
	private int no;
	private String sentNickname;
	private String recvNickname;
	private String title; 
	private String content;
	private String href;
	private int read;
	private Date date;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSentNickname() {
		return sentNickname;
	}
	public void setSentNickname(String sentNickname) {
		this.sentNickname = sentNickname;
	}
	public String getRecvNickname() {
		return recvNickname;
	}
	public void setRecvNickname(String recvNickname) {
		this.recvNickname = recvNickname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public int getRead() {
		return read;
	}
	public void setRead(int read) {
		this.read = read;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Alarm [no=" + no + ", sentNickname=" + sentNickname + ", recvNickname=" + recvNickname + ", title="
				+ title + ", content=" + content + ", href=" + href + ", read=" + read + ", date=" + date + "]";
	}
	
	
	
}
