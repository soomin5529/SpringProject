package startupWeather;

import java.io.Serializable;

public class SalesAvgDTO implements Serializable {
	private String guCode;
	private String mainCode;
	private long avgSum;
	private long avgCount;
	
	public String getGuCode() {
		return guCode;
	}
	public void setGuCode(String guCode) {
		this.guCode = guCode;
	}
	public String getMainCode() {
		return mainCode;
	}
	public void setMainCode(String mainCode) {
		this.mainCode = mainCode;
	}
	public long getAvgSum() {
		return avgSum;
	}
	public void setAvgSum(long avgSum) {
		this.avgSum = avgSum;
	}
	public long getAvgCount() {
		return avgCount;
	}
	public void setAvgCount(long avgCount) {
		this.avgCount = avgCount;
	}
	@Override
	public String toString() {
		return "SalesAvgDTO [guCode=" + guCode + ", mainCode=" + mainCode + ", avgSum=" + avgSum + ", avgCount="
				+ avgCount + "]";
	}
	
	
}
