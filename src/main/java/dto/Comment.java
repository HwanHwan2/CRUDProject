package dto;

import java.util.Date;

public class Comment {
	private int no;
	private String id;
	private Date c_date;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getC_date() {
		return c_date;
	}
	public void setC_date(Date c_date) {
		this.c_date = c_date;
	}
	@Override
	public String toString() {
		return "Comment [no=" + no + ", id=" + id + ", c_date=" + c_date + "]";
	}
}	
