/*
 * Created on Mar 25, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerprogramreferrals;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSReferralFormFieldOption extends PersistentObject 
{
	private String referralFormFieldId;
	private String optionLabel;
	private String optionValue;
	private String displaySequence;
	private boolean isDefault;
	private boolean isOptionEditable;
	
	public static CSReferralFormFieldOption find(String oid)
	{
		IHome home = new Home();

		CSReferralFormFieldOption csrefFormFieldOption = (CSReferralFormFieldOption) home.find(oid, CSReferralFormFieldOption.class);
		return csrefFormFieldOption;
	}//end of find()
    
   
	public static Iterator findAll()
	{
	    IHome home = new Home();
	    
		Iterator iter = home.findAll(CSReferralFormFieldOption.class);
		return iter;
	}//end of findAll()
	
    
	public static Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, CSReferralFormFieldOption.class);
	}//end of findAll()

    
	static public Iterator findAll(String attrName, String attrValue)
	{
	    IHome home = new Home();
		return home.findAll(attrName, attrValue, CSReferralFormFieldOption.class);
	}

	
	
	/**
	 * @return the referralFormFieldId
	 */
	public String getReferralFormFieldId() {
		fetch();
		return referralFormFieldId;
	}
	/**
	 * @param referralFormFieldId the referralFormFieldId to set
	 */
	public void setReferralFormFieldId(String referralFormFieldId) {
		if (this.referralFormFieldId == null || !this.referralFormFieldId.equals(referralFormFieldId))
		{
			markModified();
		}
		this.referralFormFieldId = referralFormFieldId;
	}
	/**
	 * @return the optionLabel
	 */
	public String getOptionLabel() {
		fetch();
		return optionLabel;
	}
	/**
	 * @param optionLabel the optionLabel to set
	 */
	public void setOptionLabel(String optionLabel) {
		if (this.optionLabel == null || !this.optionLabel.equals(optionLabel))
		{
			markModified();
		}
		this.optionLabel = optionLabel;
	}
	/**
	 * @return the optionValue
	 */
	public String getOptionValue() {
		fetch();
		return optionValue;
	}
	/**
	 * @param optionValue the optionValue to set
	 */
	public void setOptionValue(String optionValue) {
		if (this.optionValue == null || !this.optionValue.equals(optionValue))
		{
			markModified();
		}
		this.optionValue = optionValue;
	}
	/**
	 * @return the displaySequence
	 */
	public String getDisplaySequence() {
		fetch();
		return displaySequence;
	}
	/**
	 * @param displaySequence the displaySequence to set
	 */
	public void setDisplaySequence(String displaySequence) {
		if (this.displaySequence == null || !this.displaySequence.equals(displaySequence))
		{
			markModified();
		}
		this.displaySequence = displaySequence;
	}

	/**
	 * @return the isDefault
	 */
	public boolean getIsDefault() {
		fetch();
		return isDefault;
	}

	/**
	 * @param isDefault the isDefault to set
	 */
	public void setIsDefault(boolean isDefault) {
		if (this.isDefault != isDefault)
		{
			markModified();
		}
		this.isDefault = isDefault;
	}


	/**
	 * @return the isOptionEditable
	 */
	public boolean getIsOptionEditable() {
		return isOptionEditable;
	}


	/**
	 * @param isOptionEditable the isOptionEditable to set
	 */
	public void setIsOptionEditable(boolean isOptionEditable) {
		if (this.isOptionEditable != isOptionEditable)
		{
			markModified();
		}
		this.isOptionEditable = isOptionEditable;
	}
	
	
}//end of CSReferralFormFieldOption class
