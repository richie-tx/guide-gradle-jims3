/*
 * Created on Jul 22, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.addressValidation;

import mojo.km.messaging.RequestEvent;

/**
 * @author Rcooper
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddressValidationEvent extends RequestEvent
{
private String streetName;
private int streetNum;
private int zipCode;
private String streetNumStr;
private String zipCodeStr;
 
/**
 * @return
 */
public String getStreetName()
{
	return streetName;
}

/**
 * @return
 */
public int getStreetNum()
{
	return streetNum;
}

/**
 * @return
 */
public int getZipCode()
{
	return zipCode;
}

/**
 * @param string
 */
public void setStreetName(String string)
{
	streetName = string;
}

/**
 * @param i
 */
public void setStreetNum(int i)
{
	streetNum = i;
}

/**
 * @param i
 */
public void setZipCode(int i)
{
	zipCode = i;
}

public String getStreetNumStr() {
	return streetNumStr;
}

public void setStreetNumStr(String streetNumStr) {
	this.streetNumStr = streetNumStr;
}

public String getZipCodeStr() {
	return zipCodeStr;
}

public void setZipCodeStr(String zipCodeStr) {
	this.zipCodeStr = zipCodeStr;
}

}
