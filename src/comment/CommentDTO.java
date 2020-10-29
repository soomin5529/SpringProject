package comment;

import java.io.Serializable;

public class CommentDTO implements Serializable{
	private String userid;
	private int boardid;
	private int commentid;
	private String content;
	private String name;
	private String regDate;

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	
	public int getCommentid() {
		return commentid;
	}

	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CommentDTO [userid=" + userid + ", boardid=" + boardid + ", commentid=" + commentid + ", content="
				+ content + ", name=" + name + ", regDate=" + regDate + "]";
	}

	

}
