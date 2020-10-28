package board;

import java.io.Serializable;
import java.util.Date;

public class BoardDTO implements Serializable {

	private String userid;
	private int boardid;
	private String dong_code;
	private String writer;
	private String content;
	private String regDate;
	private String filename;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getBoardid() {
		return boardid;
	}

	public void setBoardid(int boardid) {
		this.boardid = boardid;
	}

	public String getDong_code() {
		return dong_code;
	}

	public void setDong_code(String dong_code) {
		this.dong_code = dong_code;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "BoardDTO [userid=" + userid + ", boardid=" + boardid + ", dong_code=" + dong_code + ", writer=" + writer
				+ ", content=" + content + ", regDate=" + regDate + ", filename=" + filename + "]";
	}

	

}
=======
package board;

import java.io.Serializable;
import java.util.Date;

public class BoardDTO implements Serializable {

	private String userid;
	private int boardid;
	private String dong_code;
	private String writer;
	private String content;
	private String regDate;
	private String filename;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getBoardid() {
		return boardid;
	}

	public void setBoardid(int boardid) {
		this.boardid = boardid;
	}

	public String getDong_code() {
		return dong_code;
	}

	public void setDong_code(String dong_code) {
		this.dong_code = dong_code;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "BoardDTO [userid=" + userid + ", boardid=" + boardid + ", dong_code=" + dong_code + ", writer=" + writer
				+ ", content=" + content + ", regdate=" + regDate + ", filename=" + filename + "]";
	}

}
>>>>>>> refs/remotes/origin/main
