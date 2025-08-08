/*
 * Created on Jan 28, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.codetable.supervision;

import java.util.Iterator;

import pd.codetable.ICodetable;

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
public class CSProgramReferralType extends PersistentObject implements ICodetable
{	
    private String referralTypeId;
    private String referralTypeCode;
    private String programGroupCode;
    private String programTypeCode;
    private String ctsCode;
    private String pfsCode;
    private String designator;
    private Boolean cjp;
    
    /**
     * @return Returns the programGroupCode.
     */
    public String getProgramGroupCode() {
    	fetch();
        return programGroupCode;
    }
    /**
     * @param programGroupCode The programGroupCode to set.
     */
    public void setProgramGroupCode(String programGroupCode) {
    	if (this.programGroupCode == null || !this.programGroupCode.equals(programGroupCode))
		{
			markModified();
		}
        this.programGroupCode = programGroupCode;
    }
    /**
     * @return Returns the programTypeCode.
     */
    public String getProgramTypeCode() {
    	fetch();
        return programTypeCode;
    }
    /**
     * @param programTypeCode The programTypeCode to set.
     */
    public void setProgramTypeCode(String programTypeCode) {
    	if (this.programTypeCode == null || !this.programTypeCode.equals(programTypeCode))
		{
			markModified();
		}
        this.programTypeCode = programTypeCode;
    }
    /**
     * @return Returns the referralTypeCode.
     */
    public String getReferralTypeCode() {
    	fetch();
        return referralTypeCode;
    }
    /**
     * @param referralTypeCode The referralTypeCode to set.
     */
    public void setReferralTypeCode(String referralTypeCode) {
    	if (this.referralTypeCode == null || !this.referralTypeCode.equals(referralTypeCode))
		{
			markModified();
		}
        this.referralTypeCode = referralTypeCode;
    }
    /**
     * @return Returns the referralTypeId.
     */
    public String getReferralTypeId() {
    	fetch();
        return referralTypeId;
    }
    /**
	 * @return the ctsCode
	 */
	public String getCtsCode() {
		fetch();
		return ctsCode;
	}
	/**
	 * @param ctsCode the ctsCode to set
	 */
	public void setCtsCode(String ctsCode) {
		if (this.ctsCode == null || !this.ctsCode.equals(ctsCode))
		{
			markModified();
		}
		this.ctsCode = ctsCode;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPfsCode() {
		
		fetch();
		return pfsCode;
	}
	
	/**
	 * 
	 * @param pfsCode
	 */
	public void setPfsCode(String pfsCode) {
		
		if (this.pfsCode == null || !this.pfsCode.equals(pfsCode))
		{
			markModified();
		}
		this.pfsCode = pfsCode;
	}
	/**
	 * @return the designator
	 */
	public String getDesignator() {
		fetch();
		return designator;
	}
	/**
	 * @param designator the designator to set
	 */
	public void setDesignator(String designator) {
		if (this.designator == null || !this.designator.equals(designator))
		{
			markModified();
		}
		this.designator = designator;
	}

	/**
	 * 
	 * @return
	 */
	public Boolean getCjp() {
		fetch();
		return cjp;
	}
	
	/**
	 * 
	 * @param cjp
	 */
	public void setCjp(Boolean cjp) {
		if (this.cjp == null || !this.cjp.equals(cjp))
		{
			markModified();
		}
		this.cjp = cjp;
	}
	
	

    /************ CSProgramReferralType Lookup Methods ***********************/

	/**
     * Find CSProgramReferralType by OID
     */
	static public CSProgramReferralType find(String refTypeId)
	{
	    	//initialize lookup objects
	    CSProgramReferralType refType = null;
		IHome home = new Home();

			//use delegate to locate given cs service provider entity
		refType = (CSProgramReferralType) home.find(refTypeId, CSProgramReferralType.class);
		return refType;
	}//end of find()

	
    /**
     * Find all CSProgramReferralType objects matching the given event attributes
     */
	static public Iterator findAll(IEvent event)
	{
	    	//initialize lookup objects
		IHome home = new Home();
		
			//use delegate to lookup program referral types matching the given event values
		return home.findAll(event, CSProgramReferralType.class);
	}//end of findAll()

    /**
     * Find all CSProgramReferralType objects matching the given event attributes
     */	
	static public Iterator findAll(String attrName, String attrValue) {
    		
	    	//initialize lookup objects
	    IHome home = new Home();
		Iterator refTypes = null;
		
			//use delegate to lookup service provider programs with the given attribute/value matches
		refTypes = home.findAll(attrName, attrValue, CSProgramReferralType.class);
		return refTypes;
	}
	
	
	public Iterator findAll() {
		IHome home = new Home();
		return home.findAll(CSProgramReferralType.class);
		
	}
	
	/**
	 * 
	 */
	public void inActivate() {
		
	}
    
}//end of CSProgramReferralType
