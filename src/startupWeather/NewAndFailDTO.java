package startupWeather;

import java.io.Serializable;

public class NewAndFailDTO implements Serializable {
	private String guName;
	private String guCode;
	private long newRate;
	private long failRate;
	
	public String getGuName() {
		return guName;
	}
	public void setGuName(String guName) {
		this.guName = guName;
	}
	public String getGuCode() {
		return guCode;
	}
	public void setGuCode(String guCode) {
		this.guCode = guCode;
	}
	public long getNewRate() {
		return newRate;
	}
	public void setNewRate(long newRate) {
		this.newRate = newRate;
	}
	public long getFailRate() {
		return failRate;
	}
	public void setFailRate(long failRate) {
		this.failRate = failRate;
	}
	@Override
	public String toString() {
		return "NewAndFailDTO [guName=" + guName + ", guCode=" + guCode + ", newRate=" + newRate + ", failRate="
				+ failRate + "]";
	}
}
