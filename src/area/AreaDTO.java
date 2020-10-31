package area;

import java.io.Serializable;

// 시군구 클래스
public class AreaDTO implements Serializable {
	private String code;
	private String name;
	// 위도 경도
	private String latitude;
	private String longitude;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "AreaDTO[code=" + code + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
