package startupWeather;

import java.io.Serializable;

public class OperateAvgDTO implements Serializable{
	private String smallCode;
	private long year;
	
	public String getSmallCode() {
		return smallCode;
	}
	public void setSmallCode(String smallCode) {
		this.smallCode = smallCode;
	}
	public long getYear() {
		return year;
	}
	public void setYear(long year) {
		this.year = year;
	}
	
	@Override
	public String toString() {
		return "avgOperateDTO [smallCode=" + smallCode + ", year=" + year + "]";
	}
}
