package pd.supervision.supervisionstaff.legacycscdstaff;

import java.util.Date;
import java.util.Iterator;

import naming.PDConstants;

import messaging.cscdstaffposition.reply.NoLegacyProgramUnitOnOrganizationEvent;
import messaging.legacycscdstaff.UpdateLegacyStaffAssignmentEvent;
import messaging.supervision.reply.SupervisionStaffResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.Name;

/**
* @author dgibler
*/
public class LegacySupervisionStaff extends PersistentObject
{
    private static final String NO_CJAD = "00000000";
	/**
	 * @param anEvent
	 * @return
	 */
	public static Iterator findAll(IEvent anEvent)
	{
		IHome home = new Home();
		return home.findAll(anEvent, LegacySupervisionStaff.class);
	}
	public static LegacySupervisionStaff find(String poi){
		IHome home = new Home();
		return (LegacySupervisionStaff) home.find(poi, LegacySupervisionStaff.class);
	}
	/**
	 * 
	 */
	public void bind(){
	    IHome home = new Home();
	    home.bind(this);
	}
	/**
	 * @param reqEvent
	 */
	public static void update(UpdateLegacyStaffAssignmentEvent reqEvent){
	    if (reqEvent.getLegacyProgramUnit() != null && !PDConstants.BLANK.equals(reqEvent.getLegacyProgramUnit()) 
	            && reqEvent.getPoi() != null && !PDConstants.BLANK.equals(reqEvent.getPoi())){
	    	LegacySupervisionStaff lss = LegacySupervisionStaff.find(reqEvent.getPoi());
	        if (lss == null){
	        	lss = new LegacySupervisionStaff();
	        	lss.setOID(reqEvent.getPoi());
	        }
	        LegacySupervisionStaff.create(lss, reqEvent);
	        
	    } else if (reqEvent.getPoi() !=  null && !PDConstants.BLANK.equals(reqEvent.getPoi())){
	        MessageUtil.postReply(new NoLegacyProgramUnitOnOrganizationEvent());
	    }
	}
	/**
	 * @param lss
	 * @param reqEvent
	 */
	public static void create(LegacySupervisionStaff lss, UpdateLegacyStaffAssignmentEvent reqEvent){
        lss.setCourtNum(reqEvent.getCourtNum());
        lss.setFirstName(reqEvent.getOfficerName().getFirstName());
        lss.setMiddleName(reqEvent.getOfficerName().getMiddleName());
        lss.setLastName(reqEvent.getOfficerName().getLastName());
        lss.setPhoneNum(reqEvent.getPhoneNum());
        lss.setProbationOfficerInd(reqEvent.getPoi());
        lss.setUnit(reqEvent.getLegacyProgramUnit());
        lss.setEffectiveDate(reqEvent.getEffectiveDate());
        if (reqEvent.getCjadNum() == null) {
            //lss.setCjadNum(NO_CJAD);
            lss.setCjadNum(PDConstants.BLANK);
        } else {
            lss.setCjadNum(reqEvent.getCjadNum());
        }
        lss.setLocation(reqEvent.getLocation());
        lss.bind(); //Ensure that crm26 records are persisted in order.
	}
	/**
	 * @param reqEvent
	 */
	public static void create(UpdateLegacyStaffAssignmentEvent reqEvent){
	    if (reqEvent.getLegacyProgramUnit() != null && !PDConstants.BLANK.equals(reqEvent.getLegacyProgramUnit()) 
	            && reqEvent.getPoi() != null && !PDConstants.BLANK.equals(reqEvent.getPoi())){
	        LegacySupervisionStaff lss = new LegacySupervisionStaff();
	        LegacySupervisionStaff.create(lss, reqEvent);
	    } else if (reqEvent.getPoi() !=  null && !PDConstants.BLANK.equals(reqEvent.getPoi())){
	        MessageUtil.postReply(new NoLegacyProgramUnitOnOrganizationEvent());
	    }
	}

	private String cjadNum;
    private String courtNum;
	private Date effectiveDate;
	private String firstName;
	private String lastName;
	private String location;
	private String middleName;
	private String phoneNum;
	private String probationOfficerInd;
	private String unit;
    /**
     * @return Returns the cjadNum.
     */
    public String getCjadNum() {
        fetch();
        return cjadNum;
    }
	/**
	* @return 
	*/
	public String getCourtNum()
	{
		fetch();
		return courtNum;
	}
    /**
     * @return Returns the effectiveDate.
     */
    public Date getEffectiveDate() {
        fetch();
        return effectiveDate;
    }
	/**
	* @return 
	*/
	public String getFirstName()
	{
		fetch();
		return firstName;
	}
    /**
     * @param getFullName The getFullName to set.
     */
    public String getFullName() {
        fetch();
        Name name = new Name();
        name.setFirstName(this.firstName);
        name.setMiddleName(this.middleName);
        name.setLastName(this.lastName);

        String fullName = name.getFormattedName();
        if (fullName != null && fullName.length() > 0 && fullName.charAt(0) == ' '){
        	fullName = fullName.substring(1);
        }
        return fullName;
    }
	/**
	* @return 
	*/
	public String getLastName()
	{
		fetch();
		return lastName;
	}
    /**
     * @return Returns the location.
     */
    public String getLocation() {
        fetch();
        return location;
    }
	/**
	* @return 
	*/
	public String getMiddleName()
	{
		fetch();
		return middleName;
	}
	/**
	* @return 
	*/
	public String getPhoneNum()
	{
		fetch();
		return phoneNum;
	}
	/**
	* @return 
	*/
	public String getProbationOfficerInd()
	{
		fetch();
		return probationOfficerInd;
	}
	/**
	 * @return
	 */
	public SupervisionStaffResponseEvent getResponseEvent()
	{
		SupervisionStaffResponseEvent ssre = null;
		if (this != null)
		{
			ssre = new SupervisionStaffResponseEvent();
			ssre.setFirstName(this.getFirstName());
			ssre.setLastName(this.getLastName());
			ssre.setMiddleName(this.getMiddleName());
			ssre.setSupervisionStaffId(this.getProbationOfficerInd());
			ssre.setCourtNum(this.getCourtNum());
			ssre.setUnit(this.getUnit());
		}
		return ssre;
	}
	/**
	* @return 
	*/
	public String getUnit()
	{
		fetch();
		return unit;
	}
    /**
     * @param cjadNum The cjadNum to set.
     */
    public void setCjadNum(String cjadNum) {
		if (this.cjadNum == null || !this.cjadNum.equals(cjadNum))
		{
			markModified();
		}

        this.cjadNum = cjadNum;
    }
	/**
	* @param string
	*/
	public void setCourtNum(String string)
	{
		if (this.courtNum == null || !this.courtNum.equals(string))
		{
			markModified();
		}
		courtNum = string;
	}
    /**
     * @param effectiveDate The effectiveDate to set.
     */
    public void setEffectiveDate(Date effectiveDate) {
		if (this.effectiveDate == null || !this.effectiveDate.equals(effectiveDate))
		{
			markModified();
		}

        this.effectiveDate = effectiveDate;
    }
	/**
	* @param string
	*/
	public void setFirstName(String firstName)
	{
		if (this.firstName == null || !this.firstName.equals(firstName))
		{
			markModified();
		}
		this.firstName = firstName;
	}
	/**
	* @param string
	*/
	public void setLastName(String lastName)
	{
		if (this.lastName == null || !this.lastName.equals(lastName))
		{
			markModified();
		}
		this.lastName = lastName;
	}
    /**
     * @param location The location to set.
     */
    public void setLocation(String location) {
		if (this.location == null || !this.lastName.equals(location))
		{
			markModified();
		}

        this.location = location;
    }
	/**
	* @param string
	*/
	public void setMiddleName(String string)
	{
		if (this.middleName == null || !this.middleName.equals(string))
		{
			markModified();
		}
		middleName = string;
	}
	/**
	* @param string
	*/
	public void setPhoneNum(String string)
	{
		if (this.phoneNum == null || !this.phoneNum.equals(string))
		{
			markModified();
		}
		phoneNum = string;
	}
	/**
	* @param string
	*/
	public void setProbationOfficerInd(String string)
	{
		if (this.probationOfficerInd == null || !this.probationOfficerInd.equals(string))
		{
			markModified();
		}
		probationOfficerInd = string;
	}
	/**
	* @param string
	*/
	public void setUnit(String string)
	{
		if (this.unit == null || !this.unit.equals(string))
		{
			markModified();
		}
		unit = string;
	}
}
