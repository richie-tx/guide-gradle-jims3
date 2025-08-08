//Source file: C:\\views\\dev\\app\\src\\messaging\\address\\ValidateAddressEvent.java

package messaging.address;

import mojo.km.messaging.RequestEvent;

public class ValidateAddressEvent extends RequestEvent 
{
   public String addressType;
   public String apartmentNum;
   public String city;
   public String keyMap;
   public String latitude;
   public String longitude;
   public String state;
   public String streetName;
   public String streetNum;
   public String streetSuffix;
   public String zipCode;
   
   /**
   @roseuid 4107C35402C7
    */
//   public ValidateAddressEvent() 
//   {
//    
//   }
   
   /**
   @param addressType
   @roseuid 40E54CEB0150
    */
   public void setAddressType(String addressType) 
   {
    	this.addressType = addressType; 
   }
   
   /**
   @return String
   @roseuid 40E54CEB015B
    */
   public String getAddressType() 
   {
    return addressType;
   }
   
   /**
   @param apartmentNum
   @roseuid 40E54CEB0164
    */
   public void setApartmentNum(String apartmentNum) 
   {
    this.apartmentNum = apartmentNum;
   }
   
   /**
   @return String
   @roseuid 40E54CEB0166
    */
   public String getApartmentNum() 
   {
    return apartmentNum;
   }
   
   /**
   @param city
   @roseuid 40E54CEB016E
    */
   public void setCity(String city) 
   {
    this.city = city;
   }
   
   /**
   @return String
   @roseuid 40E54CEB0170
    */
   public String getCity() 
   {
    return city;
   }
   
   /**
   @param keyMap
   @roseuid 40E54CEB0179
    */
   public void setKeyMap(String keyMap) 
   {
    this.keyMap = keyMap;
   }
   
   /**
   @return String
   @roseuid 40E54CEB018C
    */
   public String getKeyMap() 
   {
    return keyMap;
   }
   
   /**
   @param latitude
   @roseuid 40E54CEB0196
    */
   public void setLatitude(String latitude) 
   {
    this.latitude = latitude;
   }
   
   /**
   @return String
   @roseuid 40E54CEB01A0
    */
   public String getLatitude() 
   {
    return latitude;
   }
   
   /**
   @param longitude
   @roseuid 40E54CEB01A2
    */
   public void setLongitude(String longitude) 
   {
	this.longitude = longitude;
   }
   
   /**
   @return String
   @roseuid 40E54CEB01AB
    */
   public String getLongitude() 
   {
    return longitude;
   }
   
   /**
   @param state
   @roseuid 40E54CEB01B4
    */
   public void setState(String state) 
   {
	this.state = state;
   }
   
   /**
   @return String
   @roseuid 40E54CEB01B6
    */
   public String getState() 
   {
    return state;
   }
   
   /**
   @param streetName
   @roseuid 40E54CEB01BF
    */
   public void setStreetName(String streetName) 
   {
    this.streetName = streetName;
   }
   
   /**
   @return String
   @roseuid 40E54CEB01C8
    */
   public String getStreetName() 
   {
    return streetName;
   }
   
   /**
   @param streetNum
   @roseuid 40E54CEB01CA
    */
   public void setStreetNum(String streetNum) 
   {
    this.streetNum = streetNum;
   }
   
   /**
   @return String
   @roseuid 40E54CEB01D3
    */
   public String getStreetNum() 
   {
    return streetNum;
   }
   
   /**
   @param streetSuffix
   @roseuid 40E54CEB01DC
    */
   public void setStreetSuffix(String streetSuffix) 
   {
    this.streetSuffix = streetSuffix;
   }
   
   /**
   @return String
   @roseuid 40E54CEB01DE
    */
   public String getStreetSuffix() 
   {
    return streetSuffix;
   }
   
   /**
   @param zipCode
   @roseuid 40E54CEB01E6
    */
   public void setZipCode(String zipCode) 
   {
    this.zipCode = zipCode;
   }
   
   /**
   @return String
   @roseuid 40E54CEB01F0
    */
   public String getZipCode() 
   {
    return zipCode;
   }
}
