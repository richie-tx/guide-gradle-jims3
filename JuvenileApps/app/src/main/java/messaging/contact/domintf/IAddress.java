/*
 * Created on Feb 24, 2006
 *
 */
package messaging.contact.domintf;

import java.util.Date;

/**
 * @author Jim Fisher
 *
 */
public interface IAddress
{
	public String getAdditionalZipCode();
	public String getAddress2();
	public String getAddressId();
	public String getAddressStatus();
	public String getAddressTypeCode();
	public String getAptNum();
	public String getCity();
	public String getCountry();
	public String getCountryCode();
	public String getCountyCode();
	public String getCurrentAddressInd();
	public String getKeymap();
	public String getLatitude();
	public String getLongitude();
	public String getStateCode();
	public String getStreetName();
	public String getStreetNum();
	public String getStreetNumSuffix();
	public String getStreetNumSuffixCode();
	public String getStreetTypeCode();
	public String getValidated();
	public String getZipCode();
	public Date getCreateDate();
	public void setAdditionalZipCode(String string);
	public void setAddress2(String string);
	public void setAddressId(String string);
	public void setAddressStatus(String string);
	public void setAddressTypeCode(String string);
	public void setAptNum(String string);
	public void setCity(String string);
	public void setCountryCode(String string);
	public void setCountyCode(String string);
	public void setCurrentAddressInd(String string);
	public void setKeymap(String string);
	public void setLatitude(String string);
	public void setLongitude(String string);
	public void setStateCode(String string);
	public void setStreetName(String string);
	public void setStreetNum(String string);
	public void setStreetNumSuffixCode(String string);
	public void setStreetTypeCode(String string);
	public void setValidated(String string);
	public void setZipCode(String string);
	public void setCreateDate(Date aDate);
}
