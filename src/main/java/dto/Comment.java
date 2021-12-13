package dto;

import java.util.Date;

public class Comment {

	private int no;
	private int type;
	private int c_no;
	private String nickname;
	private String content;
	private Date c_date;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getC_date() {
		return c_date;
	}
	public void setC_date(Date c_date) {
		this.c_date = c_date;
	}
	@Override
	public String toString() {
		return "Comment [no=" + no + ", type=" + type + ", c_no=" + c_no + ", nickname=" + nickname + ", content="
				+ content + ", c_date=" + c_date + "]";
	}
		
	
	
}
