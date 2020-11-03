package industry;

public class IndustryRankDTO {
	private String code;
	private String name;
	private int sum;
	private int rank;

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

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "IndustryRankDTO [code=" + code + ", name=" + name + ", sum=" + sum + ", rank=" + rank + "]";
	}

}
