package member;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MemberDTO implements Serializable {
	private String userid;
	private String pwd;
	private String name;
	private String email;
	private String gender;
	private int birthdate;
	private String regdate;
	private int author;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(int birthdate) {
		this.birthdate = birthdate;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return "MemberDTO [userid=" + userid + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", gender="
				+ gender + ", birthdate=" + birthdate + ", regdate=" + regdate + ", author=" + author + "]";
	}
	
	
	
	
	
	
	

}
