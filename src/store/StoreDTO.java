package store;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StoreDTO implements Serializable {
	private String storeNum;
	private String storeName;
	private String branchName;
	// 업종코드
	private String mainCategoryCode;
	private String middleCategoryCode;
	private String smallCategoryCode;
	// 지역코드
	// private String sidoCode;
	// private String sigunguCode;
	private String dongCode;
	// 지번, 도로명 주소
	private String lotNumAddress;
	private String roadAddress;
	// 위도, 경도
	private String latitude;
	private String longitude;

	public String getStoreNum() {
		return storeNum;
	}

	public void setStoreNum(String storeNum) {
		this.storeNum = storeNum;
	}

	public String getStroreName() {
		return storeName;
	}

	public void setStroreName(String stroreName) {
		this.storeName = stroreName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getMainCategoryCode() {
		return mainCategoryCode;
	}

	public void setMainCategoryCode(String mainCategoryCode) {
		this.mainCategoryCode = mainCategoryCode;
	}

	public String getMiddleCategoryCode() {
		return middleCategoryCode;
	}

	public void setMiddleCategoryCode(String middleCategoryCode) {
		this.middleCategoryCode = middleCategoryCode;
	}

	public String getSmallCategoryCode() {
		return smallCategoryCode;
	}

	public void setSmallCategoryCode(String smallCategoryCode) {
		this.smallCategoryCode = smallCategoryCode;
	}

//	public String getSidoCode() {
//		return sidoCode;
//	}
//
//	public void setSidoCode(String sidoCode) {
//		this.sidoCode = sidoCode;
//	}
//
//	public String getSigunguCode() {
//		return sigunguCode;
//	}
//
//	public void setSigunguCode(String sigunguCode) {
//		this.sigunguCode = sigunguCode;
//	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getLotNumAddress() {
		return lotNumAddress;
	}

	public void setLotNumAddress(String lotNumAddress) {
		this.lotNumAddress = lotNumAddress;
	}

	public String getRoadAddress() {
		return roadAddress;
	}

	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
