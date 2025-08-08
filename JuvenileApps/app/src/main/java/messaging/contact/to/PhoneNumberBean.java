/*
 * Created on Mar 1, 2006
 *
 */
package messaging.contact.to;

import messaging.contact.domintf.IPhoneNumber;

/**
 * @author Jim Fisher
 * 
 */
public class PhoneNumberBean implements IPhoneNumber {
	private String areaCode;

	private String prefix;

	private String fourDigit;

	private String extension;

	private boolean valid;

	private String phoneId;

	public PhoneNumberBean() {
	}

	public PhoneNumberBean(String aPhoneNumber) {
		if ((aPhoneNumber != null) && aPhoneNumber.length() > 9) {
			// set the individual fields too
			// check if input is formated
			String formatedValue = aPhoneNumber.substring(3, 8);
			if (formatedValue.startsWith("-") && formatedValue.endsWith("-")) {
				this.areaCode = aPhoneNumber.substring(0, 3);
				this.prefix = aPhoneNumber.substring(4, 7);
				this.fourDigit = aPhoneNumber.substring(8, 12);
				this.valid = true;
			} else {
				this.areaCode = aPhoneNumber.substring(0, 3);
				this.prefix = aPhoneNumber.substring(3, 6);
				this.fourDigit = aPhoneNumber.substring(6, 10);
				this.valid = true;
			}

		}
	}

	public PhoneNumberBean(String areaCode, String prefix, String fourDigit) {
		this.areaCode = areaCode;
		this.prefix = prefix;
		this.fourDigit = fourDigit;
	}

	/**
	 * @return
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * @return
	 */
	public String getFourDigit() {
		return fourDigit;
	}

	/**
	 * @return
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param string
	 */
	public void setAreaCode(String string) {
		areaCode = string;
	}

	/**
	 * @param string
	 */
	public void setFourDigit(String string) {
		fourDigit = string;
	}

	/**
	 * @param string
	 */
	public void setPrefix(String string) {
		prefix = string;
	}

	public String toString() {
		return this.areaCode + this.prefix + this.fourDigit;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String string) {
		this.extension = string;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see messaging.contact.domintf.IPhoneNumber#getExt()
	 */
	public String getExt() {
		// TODO Auto-generated method stub
		return this.extension;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see messaging.contact.domintf.IPhoneNumber#getFormattedPhoneNumber()
	 */
	public String getFormattedPhoneNumber() {
		StringBuffer phNumber = new StringBuffer();
		phNumber = phNumber.append(areaCode).append("-").append(prefix).append(
				"-").append(this.fourDigit);
		String ph = phNumber.toString();
		if (ph.equals("--") || ph.equalsIgnoreCase("null-null-null")) {
			ph = "";
		}
		return ph;
	}

	public String getPhoneNumberFormat() {
		StringBuffer phNumber = new StringBuffer();
		phNumber = phNumber.append("(").append(areaCode).append(")").append(
				prefix).append("-").append(this.fourDigit);
		String ph = phNumber.toString();
		if (ph.equals("--") || ph.equalsIgnoreCase("(null)null-null")) {
			ph = "";
		}
		return ph;
	}

	/**
	 * @return String
	 */
	public String getPhoneNumber() {
		StringBuffer phNumber = new StringBuffer();
		phNumber = phNumber.append(areaCode).append(prefix).append(fourDigit);
		return phNumber.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see messaging.contact.domintf.IPhoneNumber#getLast4Digit()
	 */
	public String getLast4Digit() {
		return this.fourDigit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see messaging.contact.domintf.IPhoneNumber#getPhoneId()
	 */
	public String getPhoneId() {
		// TODO Auto-generated method stub
		return this.phoneId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see messaging.contact.domintf.IPhoneNumber#isValid()
	 */
	public boolean isValid() {
		// TODO Auto-generated method stub
		return this.valid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see messaging.contact.domintf.IPhoneNumber#setExt(java.lang.String)
	 */
	public void setExt(String anExtension) {
		this.extension = anExtension;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * messaging.contact.domintf.IPhoneNumber#setLast4Digit(java.lang.String)
	 */
	public void setLast4Digit(String aFourDigit) {
		this.fourDigit = aFourDigit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see messaging.contact.domintf.IPhoneNumber#setPhoneId(java.lang.String)
	 */
	public void setPhoneId(String aPhoneId) {
		this.phoneId = aPhoneId;
	}

}
