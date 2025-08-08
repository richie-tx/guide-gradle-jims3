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

/**
 * 
 * @author cc_bjangay
 *
 */
public class CSReferralFormFieldData extends CSReferralFormField 
{
	private String programReferralId;
	
	private String refFormFieldId;
	private String refFormFieldValue; //stored optionId if the field has options
	
	private boolean isOptionSelected; //used only in case of multi-checkbox
	private String userEnteredOptionName;
	
	private String formTitle;
	private String referralTypeCd;
	
	
	
	public static CSReferralFormFieldData find(String oid)
	{
		IHome home = new Home();

		CSReferralFormFieldData csrefFormFieldData = (CSReferralFormFieldData) home.find(oid, CSReferralFormFieldData.class);
		return csrefFormFieldData;
	}//end of find()
    
   
	public static Iterator findAll()
	{
	    IHome home = new Home();
	    
		Iterator iter = home.findAll(CSReferralFormFieldData.class);
		return iter;
	}//end of findAll()
	
    
	public static Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, CSReferralFormFieldData.class);
	}//end of findAll()

    
	static public Iterator findAll(String attrName, String attrValue)
	{
	    IHome home = new Home();
		return home.findAll(attrName, attrValue, CSReferralFormFieldData.class);
	}
	
	
	 public void bind()
    {
        IHome home = new Home();
        home.bind(this);
        
    }//end of bind()
	

	
	/**
	 * @return the programReferralId
	 */
	public String getProgramReferralId() {
		fetch();
		return programReferralId;
	}
	/**
	 * @param programReferralId the programReferralId to set
	 */
	public void setProgramReferralId(String programReferralId) {
		if (this.programReferralId == null || !this.programReferralId.equals(programReferralId))
		{
			markModified();
		}
		this.programReferralId = programReferralId;
	}
	/**
	 * @return the refFormFieldId
	 */
	public String getRefFormFieldId() {
		fetch();
		return refFormFieldId;
	}
	/**
	 * @param refFormFieldId the refFormFieldId to set
	 */
	public void setRefFormFieldId(String refFormFieldId) {
		if (this.refFormFieldId == null || !this.refFormFieldId.equals(refFormFieldId))
		{
			markModified();
		}
		this.refFormFieldId = refFormFieldId;
	}
	/**
	 * @return the refFormFieldValue
	 */
	public String getRefFormFieldValue() {
		fetch();
		return refFormFieldValue;
	}
	/**
	 * @param refFormFieldValue the refFormFieldValue to set
	 */
	public void setRefFormFieldValue(String refFormFieldValue) {
		if (this.refFormFieldValue == null || !this.refFormFieldValue.equals(refFormFieldValue))
		{
			markModified();
		}
		this.refFormFieldValue = refFormFieldValue;
	}
	/**
	 * @return the formTitle
	 */
	public String getFormTitle() {
		fetch();
		return formTitle;
	}
	/**
	 * @param formTitle the formTitle to set
	 */
	public void setFormTitle(String formTitle) {
		if (this.formTitle == null || !this.formTitle.equals(formTitle))
		{
			markModified();
		}
		this.formTitle = formTitle;
	}
	/**
	 * @return the referralTypeCd
	 */
	public String getReferralTypeCd() {
		fetch();
		return referralTypeCd;
	}
	/**
	 * @param referralTypeCd the referralTypeCd to set
	 */
	public void setReferralTypeCd(String referralTypeCd) {
		if (this.referralTypeCd == null || !this.referralTypeCd.equals(referralTypeCd))
		{
			markModified();
		}
		this.referralTypeCd = referralTypeCd;
	}
	/**
	 * @return the userEnteredOptionName
	 */
	public String getUserEnteredOptionName() {
		fetch();
		return userEnteredOptionName;
	}
	/**
	 * @param userEnteredOptionName the userEnteredOptionName to set
	 */
	public void setUserEnteredOptionName(String userEnteredOptionName) {
		if (this.userEnteredOptionName == null || !this.userEnteredOptionName.equals(userEnteredOptionName))
		{
			markModified();
		}
		this.userEnteredOptionName = userEnteredOptionName;
	}
	/**
	 * @return the isOptionSelected
	 */
	public boolean getIsOptionSelected() {
		fetch();
		return isOptionSelected;
	}


	/**
	 * @param isOptionSelected the isOptionSelected to set
	 */
	public void setIsOptionSelected(boolean isOptionSelected) {
		if (this.isOptionSelected != isOptionSelected)
		{
			markModified();
		}
		this.isOptionSelected = isOptionSelected;
	}
	
}
