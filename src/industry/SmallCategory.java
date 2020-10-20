package industry;

public class SmallCategory {
	private String middlecategory_code;
	private String smallcategory_code;
	private String smallcategory_name;

	public String getMiddlecategory_code() {
		return middlecategory_code;
	}

	public void setMiddlecategory_code(String middlecategory_code) {
		this.middlecategory_code = middlecategory_code;
	}

	public String getSmallcategory_code() {
		return smallcategory_code;
	}

	public void setSmallcategory_code(String smallcategory_code) {
		this.smallcategory_code = smallcategory_code;
	}

	public String getSmallcategory_name() {
		return smallcategory_name;
	}

	public void setSmallcategory_name(String smallcategory_name) {
		this.smallcategory_name = smallcategory_name;
	}

	@Override
	public String toString() {
		return "SmallCategory [middlecategory_code=" + middlecategory_code + ", smallcategory_code="
				+ smallcategory_code + ", smallcategory_name=" + smallcategory_name + "]";
	}

}
