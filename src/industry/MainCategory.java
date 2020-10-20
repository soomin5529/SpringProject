package industry;

public class MainCategory {
	private String maincategory_code;
	private String maincategory_name;

	public String getMaincategory_code() {
		return maincategory_code;
	}

	public void setMaincategory_code(String maincategory_code) {
		this.maincategory_code = maincategory_code;
	}

	public String getMaincategory_name() {
		return maincategory_name;
	}

	public void setMaincategory_name(String maincategory_name) {
		this.maincategory_name = maincategory_name;
	}

	@Override
	public String toString() {
		return "MainCategory [maincategory_code=" + maincategory_code + ", maincategory_name=" + maincategory_name
				+ "]";
	}

}
