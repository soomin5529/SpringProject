package industry;

public class MiddleCategory {
	private String maincategory_code;
	private String middlecategory_code;
	private String middlecategory_name;

	public String getMaincategory_code() {
		return maincategory_code;
	}

	public void setMaincategory_code(String maincategory_code) {
		this.maincategory_code = maincategory_code;
	}

	public String getMiddlecategory_code() {
		return middlecategory_code;
	}

	public void setMiddlecategory_code(String middlecategory_code) {
		this.middlecategory_code = middlecategory_code;
	}

	public String getMiddlecategory_name() {
		return middlecategory_name;
	}

	public void setMiddlecategory_name(String middlecategory_name) {
		this.middlecategory_name = middlecategory_name;
	}

	@Override
	public String toString() {
		return "MiddleCategory [maincategory_code=" + maincategory_code + ", middlecategory_code=" + middlecategory_code
				+ ", middlecategory_name=" + middlecategory_name + "]";
	}

}
