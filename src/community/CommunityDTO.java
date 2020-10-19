package community;

import java.util.Date;

public class CommunityDTO {
	   int num;
	   int boardId;
	   String userId;
	   String name;
	   String content;
	   Date regdate;
	   int readcount;
	   int pwd;
	   String filename;
	   String filesize;
	   public int getNum() {
	      return num;
	   }
	   public void setNum(int num) {
	      this.num = num;
	   }
	   public int getBoardId() {
	      return boardId;
	   }
	   public void setBoardId(int boardId) {
	      this.boardId = boardId;
	   }
	   public String getUserId() {
	      return userId;
	   }
	   public void setUserId(String userId) {
	      this.userId = userId;
	   }
	   public String getName() {
	      return name;
	   }
	   public void setName(String name) {
	      this.name = name;
	   }
	   public String getContent() {
	      return content;
	   }
	   public void setContent(String content) {
	      this.content = content;
	   }
	   public Date getRegdate() {
	      return regdate;
	   }
	   public void setRegdate(Date regdate) {
	      this.regdate = regdate;
	   }
	   public int getReadcount() {
	      return readcount;
	   }
	   public void setReadcount(int readcount) {
	      this.readcount = readcount;
	   }
	   public int getPwd() {
	      return pwd;
	   }
	   public void setPwd(int pwd) {
	      this.pwd = pwd;
	   }
	   public String getFilename() {
	      return filename;
	   }
	   public void setFilename(String filename) {
	      this.filename = filename;
	   }
	   public String getFilesize() {
	      return filesize;
	   }
	   public void setFilesize(String filesize) {
	      this.filesize = filesize;
	   }
	   @Override
	   public String toString() {
	      return "CommunityDTO [num=" + num + ", boardId=" + boardId + ", userId=" + userId + ", name=" + name
	            + ", content=" + content + ", regdate=" + regdate + ", readcount=" + readcount + ", pwd=" + pwd
	            + ", filename=" + filename + ", filesize=" + filesize + "]";
	   }

	   
	}