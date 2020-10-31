package board;

import java.io.Serializable;

public class BoardLikeDTO implements Serializable {
	private String userid;
	private int boardid;

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

	@Override
	public String toString() {
		return "BoardLikeDTO [userid=" + userid + ", boardid=" + boardid + "]";
	}

}
