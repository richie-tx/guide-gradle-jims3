/*
 * Project: JIMS
 * Class:   ui.juvenilecase.form.JuvenileProfileForm
 *
 * Date:    2005-06-29
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package ui.juvenilecase.form;

/**
 * 
 * @author athorat
 */
public class JuvenileProfileForm extends org.apache.struts.action.ActionForm
{

	// ------------------------------------------------------------------------
	// --- fields ---
	// ------------------------------------------------------------------------

	private String juvenileName;
	
	private String preferredFirstName;

	private String age;

	private String status;

	private String juvenileNum;

	private String detentionFacility;

	private String detentionFacilityId;

	private String detentionStatus;

	private String detentionStatusId;

	private boolean hasCasefile = false;
	
	private boolean juvUnder21 = false;
	
	
	//Changes for US 31029
	private boolean restrictedAccess;
	
	private String action;
	
	//added for US 40492
	private String jpoOfRecID;
	
	private String jpoOfRecord;
	
	//added for US 53368
	private String dateOfBirth ;
	private String facilityreleaseDate;
	
	private String traitTypeId;
	private String traitTypeDescription;

	// ------------------------------------------------------------------------
	// --- methods ---
	// ------------------------------------------------------------------------

	

	public void clear()
	{
		juvenileName = null;
		age = null;
		status = null;
		juvenileNum = null;
		preferredFirstName = null;
	} // end of ui.juvenilecase.form.JuvenileProfileForm.clear

	/**
	 * 
	 * @return The age.
	 */
	public String getAge()
	{
		return age;
	} // end of ui.juvenilecase.form.JuvenileProfileForm.getAge

	/**
	 * 
	 * @return The juvenile name.
	 */
	public String getJuvenileName()
	{
		return juvenileName;
	} // end of ui.juvenilecase.form.JuvenileProfileForm.getJuvenileName

	/**
	 * 
	 * @return The status.
	 */
	public String getStatus()
	{
		// temporary soloution: we need to fix this later
		if (this.status != null)
		{
			if (this.status.equals("A") || this.status.equals("CA") || this.status.equals("CP")
					|| this.status.equals("CS"))
			{
				this.status = "ACTIVE";
			}
			else if (this.status.equals("P"))
			{
				this.status = "PENDING";
			}
			else if (this.status.equals("C"))
			{
				this.status = "CLOSED";
			}
		}
		return this.status;
	} // end of ui.juvenilecase.form.JuvenileProfileForm.getStatus

	/**
	 * 
	 * @param string
	 *            The age.
	 */
	public void setAge(String string)
	{
		age = string;
	} // end of ui.juvenilecase.form.JuvenileProfileForm.setAge

	/**
	 * 
	 * @param string
	 *            The juvenile name.
	 */
	public void setJuvenileName(String string)
	{
		juvenileName = string;
	} // end of ui.juvenilecase.form.JuvenileProfileForm.setJuvenileName

	/**
	 * 
	 * @param string
	 *            The status.
	 */
	public void setStatus(String string)
	{
		this.status = string;
	} // end of ui.juvenilecase.form.JuvenileProfileForm.setStatus

	/**
	 * 
	 * @return The juvenile num.
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	} // end of ui.juvenilecase.form.JuvenileProfileForm.getJuvenileNum

	/**
	 * 
	 * @param string
	 *            The juvenile num.
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	} // end of ui.juvenilecase.form.JuvenileProfileForm.setJuvenileNum

	/**
	 * @return Returns the detentionFacility.
	 */
	public String getDetentionFacility()
	{
		return detentionFacility;
	}

	/**
	 * @param detentionFacility
	 *            The detentionFacility to set.
	 */
	public void setDetentionFacility(String detentionFacility)
	{
		this.detentionFacility = detentionFacility;
	}

	/**
	 * @return Returns the detentionFacilityId.
	 */
	public String getDetentionFacilityId()
	{
		return detentionFacilityId;
	}

	/**
	 * @param detentionFacilityId
	 *            The detentionFacilityId to set.
	 */
	public void setDetentionFacilityId(String detentionFacilityId)
	{
		this.detentionFacilityId = detentionFacilityId;
	}

	/**
	 * @return Returns the detentionStatus.
	 */
	public String getDetentionStatus()
	{
		return detentionStatus;
	}

	/**
	 * @param detentionStatus
	 *            The detentionStatus to set.
	 */
	public void setDetentionStatus(String detentionStatus)
	{
		this.detentionStatus = detentionStatus;
	}

	/**
	 * @return Returns the detentionStatusId.
	 */
	public String getDetentionStatusId()
	{
		return detentionStatusId;
	}

	/**
	 * @param detentionStatusId
	 *            The detentionStatusId to set.
	 */
	public void setDetentionStatusId(String detentionStatusId)
	{
		this.detentionStatusId = detentionStatusId;
	}

	public boolean isHasCasefile()
	{
		return hasCasefile;
	}

	/**
	 * @param hasCasefile
	 *            The hasCasefile to set.
	 */
	public void setHasCasefile(boolean hasCasefile)
	{
		this.hasCasefile = hasCasefile;
	}

	/**
	 * @return the juvUnder21
	 */
	public boolean isJuvUnder21() {
		return juvUnder21;
	}

	/**
	 * @param juvUnder21 the juvUnder21 to set
	 */
	public void setJuvUnder21(boolean juvUnder21) {
		this.juvUnder21 = juvUnder21;
	}

	/**
	 * @return the restrictedAccess
	 */
	public boolean isRestrictedAccess() {
		return restrictedAccess;
	}

	/**
	 * @param restrictedAccess the restrictedAccess to set
	 */
	public void setRestrictedAccess(boolean restrictedAccess) {
		this.restrictedAccess = restrictedAccess;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	/**
	 * @return the jpoOfRecID
	 */
	public String getJpoOfRecID() {
		return jpoOfRecID;
	}

	/**
	 * @param jpoOfRecID the jpoOfRecID to set
	 */
	public void setJpoOfRecID(String jpoOfRecID) {
		this.jpoOfRecID = jpoOfRecID;
	}

	/**
	 * @return the jpoOfRecord
	 */
	public String getJpoOfRecord() {
		return jpoOfRecord;
	}

	/**
	 * @param jpoOfRecord the jpoOfRecord to set
	 */
	public void setJpoOfRecord(String jpoOfRecord) {
		this.jpoOfRecord = jpoOfRecord;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPreferredFirstName()
	{
	    return preferredFirstName;
	}

	public void setPreferredFirstName(String preferredFirstName)
	{
	    this.preferredFirstName = preferredFirstName;
	}
	public String getFacilityreleaseDate()
	{
	    return facilityreleaseDate;
	}

	public void setFacilityreleaseDate(String facilityreleaseDate)
	{
	    this.facilityreleaseDate = facilityreleaseDate;
	}
	
	public String getTraitTypeId()
	{
	    return this.traitTypeId;
	}

	public void setTraitTypeId(String traittypeId)
	{
	        this.traitTypeId = traittypeId;
	}
	
	public String getTraitTypeDescription()
	{
	    return this.traitTypeDescription;
	}

	public void setTraitTypeDescription(String traitTypeDesc)
	{
	        this.traitTypeDescription = traitTypeDesc;
	}


	
} // end JuvenileProfileForm
