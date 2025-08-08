/*
 * Created on Mar 1, 2006
 *
 */
package messaging.contact.domintf;

/**
 * @author Jim Fisher
 *
 */
public interface IPhoneNumber
{
	String getAreaCode();
	String getFourDigit();
	String getPrefix();
	String getExtension();
	String getExt();
	String getLast4Digit();
	String getPhoneId();
	String getFormattedPhoneNumber();
	String getPhoneNumber();
	boolean isValid();
	void setAreaCode(String string);
	void setFourDigit(String string);
	void setPrefix(String string);
	void setExtension(String string);	
	void setExt(String string);
	void setLast4Digit(String string);
	void setPhoneId(String string);
	
	
	
}
