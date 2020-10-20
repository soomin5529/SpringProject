package area;

// 시군구 클래스
public class Sigungu {
	private String code;
	private String name;

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

	@Override
	public String toString() {
		return "Sigungu [code=" + code + ", name=" + name + "]";
	}

}
