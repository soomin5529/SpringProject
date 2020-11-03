package dashboard;

import java.io.Serializable;

public class IndustryCountDTO implements Serializable {
	private int industryCount;
	private String industryName;
	
	public int getIndustryCount() {
		return industryCount;
	}
	public void setIndustryCount(int industryCount) {
		this.industryCount = industryCount;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	
	@Override
	public String toString() {
		return "IndustryCountDTO [industryCount=" + industryCount + ", industryName=" + industryName + "]";
	}
}
