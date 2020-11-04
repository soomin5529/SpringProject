package startupWeather;

import java.io.Serializable;

public class PopulationDTO implements Serializable{
	private String code;
	private long density;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public long getDensity() {
		return density;
	}
	public void setDensity(long density) {
		this.density = density;
	}
	
	@Override
	public String toString() {
		return "PopulationDTO [code=" + code + ", density=" + density + "]";
	}
}
