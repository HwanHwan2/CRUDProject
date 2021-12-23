package dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Board {
	private int no;
	private String nickname;
	private String title;
	private String content;
	private Date b_date;
	private int hits;
	private String realFileName;
	private String saveFileName;
	private long fileSize;
	private int type;
	private int commentCnt;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public Date getB_date() {
		return b_date;
	}
	public void setB_date(Date b_date) {
		this.b_date = b_date;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getRealFileName() {
		return realFileName;
	}
	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getCommentCnt() {
		return commentCnt;
	}
	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}
	@Override
	public String toString() {
		return "Board [no=" + no + ", nickname=" + nickname + ", title=" + title + ", content=" + content + ", b_date="
				+ b_date + ", hits=" + hits + ", realFileName=" + realFileName + ", saveFileName=" + saveFileName
				+ ", fileSize=" + fileSize + ", type=" + type + "commentCnt =" + commentCnt + "]";
	}
}
