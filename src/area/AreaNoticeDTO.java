package area;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AreaNoticeDTO implements Serializable {
	private String userid;
	private String dongCode;
	private int boardid;
	private String board_regdate;
	private int readed;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public int getBoardid() {
		return boardid;
	}

	public void setBoardid(int boardid) {
		this.boardid = boardid;
	}

	public String getBoard_regdate() {
		return board_regdate;
	}

	public void setBoard_regdate(String board_regdate) {
		this.board_regdate = board_regdate;
	}

	public int getReaded() {
		return readed;
	}

	public void setReaded(int readed) {
		this.readed = readed;
	}

	@Override
	public String toString() {
		return "AreaNoticeDTO [userid=" + userid + ", dongCode=" + dongCode + ", boardid=" + boardid
				+ ", board_regdate=" + board_regdate + ", readed=" + readed + "]";
	}

}
