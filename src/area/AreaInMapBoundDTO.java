package area;

import java.io.Serializable;

// 동 클래스
public class AreaInMapBoundDTO implements Serializable {
   private String code;
   private String name;
   // 위도 경도
   private String latitude;
   private String longitude;
   private int storeNum;

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

   public int getStoreNum() {
      return storeNum;
   }

   public void setStoreNum(int storeNum) {
      this.storeNum = storeNum;
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
      return "AreaInMapBoundDTO [code=" + code + ", name=" + name + ", latitude=" + latitude + ", longitude="
            + longitude + ", storeNum=" + storeNum + "]";
   }

}