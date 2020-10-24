package area;

import java.io.Serializable;

// 동 클래스
public class DongDTO implements Serializable {
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
		return "Dong [code=" + code + ", name=" + name + "]";
	}

}
