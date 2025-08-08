/*
 * Created on May 15, 2007
 *
 */
package messaging.programreferral;

import messaging.programreferral.ProgramReferralRetrieverAttribute;

/**
 */
public final class ProgramReferralRetrieverAttributeImpl implements ProgramReferralRetrieverAttribute {
	private String attributeName;
	private String attributeValue;


	private ProgramReferralRetrieverAttributeImpl() {
		
	}
	/**
	 * 
	 */
	public ProgramReferralRetrieverAttributeImpl(String attributeName) {
		this.setAttributeName(attributeName);
	}
	/**
	 * @return Returns the attributeName.
	 */
	public String getAttributeName() {
		return attributeName;
	}
	/**
	 * @param attributeName The attributeName to set.
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	/**
	 * @return Returns the attributeValue.
	 */
	public String getAttributeValue() {
		return attributeValue;
	}
	/**
	 * @param attributeValue The attributeValue to set.
	 */
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
}
