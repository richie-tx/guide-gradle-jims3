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
public class CSReferralForm extends PersistentObject 
{
	/*********** Member Variables **************/
	private String referralFormId;
	private String formTitle;
	private String versionNum;
	private boolean isCurrent;
	private String referralTypeCode;
	
	
	static public CSReferralForm find(String oid)
	{
		IHome home = new Home();

		CSReferralForm csreferralForm = (CSReferralForm) home.find(oid, CSReferralForm.class);
		return csreferralForm;
	}//end of find()
    
   
	static public Iterator findAll()
	{
	    IHome home = new Home();
	    
		Iterator iter = home.findAll(CSReferralForm.class);
		return iter;
	}//end of findAll()
	
    
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, CSReferralForm.class);
	}//end of findAll()

    
	static public Iterator findAll(String attrName, String attrValue)
	{
	    IHome home = new Home();
		return home.findAll(attrName, attrValue, CSReferralForm.class);
	}

	
	/**************** Getters & Setters  ****************/
	
	
	/**
	 * @return the referralFormId
	 */
	public String getReferralFormId() {
		fetch();
		return referralFormId;
	}
	/**
	 * @param referralFormId the referralFormId to set
	 */
	public void setReferralFormId(String referralFormId) {
		if (this.referralFormId == null || !this.referralFormId.equals(referralFormId))
		{
			markModified();
		}
		this.referralFormId = referralFormId;
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
	 * @return the versionNum
	 */
	public String getVersionNum() {
		fetch();
		return versionNum;
	}
	/**
	 * @param versionNum the versionNum to set
	 */
	public void setVersionNum(String versionNum) {
		if (this.versionNum == null || !this.versionNum.equals(versionNum))
		{
			markModified();
		}
		this.versionNum = versionNum;
	}
	/**
	 * @return the isCurrent
	 */
	public boolean isCurrent() {
		fetch();
		return isCurrent;
	}
	/**
	 * @param isCurrent the isCurrent to set
	 */
	public void setCurrent(boolean isCurrent) {
		if (this.isCurrent != isCurrent)
		{
			markModified();
		}
		this.isCurrent = isCurrent;
	}
	/**
	 * @return the referralTypeCode
	 */
	public String getReferralTypeCode() {
		fetch();
		return referralTypeCode;
	}
	/**
	 * @param referralTypeCode the referralTypeCode to set
	 */
	public void setReferralTypeCode(String referralTypeCode) {
		if (this.referralTypeCode == null || !this.referralTypeCode.equals(referralTypeCode))
		{
			markModified();
		}
		this.referralTypeCode = referralTypeCode;
	}
	
}//end of CSReferralForm class
