/*
 * Created on May 1, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerprogramreferrals.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReferralTypeResponseEvent extends ResponseEvent 
{
    private String referralTypeId;
    private String referralTypeCode;
    private String programGroupCode;
    private String programTypeCode;
    private String programGroupDesc;
    private String programTypeDesc;
    
    
    /**
     * @return Returns the programGroupCode.
     */
    public String getProgramGroupCode() {
        return programGroupCode;
    }
    /**
     * @param programGroupCode The programGroupCode to set.
     */
    public void setProgramGroupCode(String programGroupCode) {
        this.programGroupCode = programGroupCode;
    }
    /**
     * @return Returns the programTypeCode.
     */
    public String getProgramTypeCode() {
        return programTypeCode;
    }
    /**
     * @param programTypeCode The programTypeCode to set.
     */
    public void setProgramTypeCode(String programTypeCode) {
        this.programTypeCode = programTypeCode;
    }
    /**
     * @return Returns the referralTypeCode.
     */
    public String getReferralTypeCode() {
        return referralTypeCode;
    }
    /**
     * @param referralTypeCode The referralTypeCode to set.
     */
    public void setReferralTypeCode(String referralTypeCode) {
        this.referralTypeCode = referralTypeCode;
    }
    /**
     * @return Returns the referralTypeId.
     */
    public String getReferralTypeId() {
        return referralTypeId;
    }
    /**
     * @param referralTypeId The referralTypeId to set.
     */
    public void setReferralTypeId(String referralTypeId) {
        this.referralTypeId = referralTypeId;
    }

	/**
	 * @return Returns the programGroupDesc.
	 */
	public String getProgramGroupDesc() {
		return programGroupDesc;
	}
	/**
	 * @param programGroupDesc The programGroupDesc to set.
	 */
	public void setProgramGroupDesc(String programGroupDesc) {
		this.programGroupDesc = programGroupDesc;
	}
	/**
	 * @return Returns the programTypeDesc.
	 */
	public String getProgramTypeDesc() {
		return programTypeDesc;
	}
	/**
	 * @param programTypeDesc The programTypeDesc to set.
	 */
	public void setProgramTypeDesc(String programTypeDesc) {
		this.programTypeDesc = programTypeDesc;
	}
}
