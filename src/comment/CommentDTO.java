package comment;

public class CommentDTO {
	private String userid;
	private String boardid;
	private String commentid;
	private String content;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getBoardid() {
		return boardid;
	}

	public void setBoardid(String boardid) {
		this.boardid = boardid;
	}

	public String getCommentid() {
		return commentid;
	}

	public void setCommentid(String commentid) {
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
				+ content + "]";
	}

}
