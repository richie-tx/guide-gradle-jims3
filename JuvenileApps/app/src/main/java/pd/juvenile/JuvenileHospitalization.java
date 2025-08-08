package pd.juvenile;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import java.util.Date;
import java.util.Iterator;

import pd.codetable.Code;

/**
 * @roseuid 462D05690218
 */
public class JuvenileHospitalization extends PersistentObject
{
	private Date entryDate;
	private String hospitalizationId;
	private Date admissionDate;
	private String facilityName;
	private String hospitalizationReason;
	private Date releaseDate;
	private String physicianName;
	private String physicianPhoneNum;
	private String juvenileNum;
	private JuvenileHospitalization[] hospitalizations;
	/**
	 * Properties for admissionType
	 */
	private Code admissionType = null;
	private String admissionTypeId;
	private String admitYear;
	private String lengthOfStay;

	/**
	 * @roseuid 462D05690218
	 */
	public JuvenileHospitalization()
	{
	}

	/**
	 * @param event
	 * @return
	 * @roseuid 45AF7A0A0190
	 */
	static public JuvenileHospitalization find(String oid)
	{
		IHome home = new Home();
		JuvenileHospitalization juvenileHospitalization = (JuvenileHospitalization) home.find(oid, JuvenileHospitalization.class);
		return juvenileHospitalization;
	}
	/**
	 * @param event
	 * @return
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JuvenileHospitalization.class);
	}
	
	/**
	 *  @roseuid 45AF7A0A0190
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setAdmissionTypeId(String admissionTypeId)
	{
		if (this.admissionTypeId == null || !this.admissionTypeId.equals(admissionTypeId))
		{
			markModified();
		}
		admissionType = null;
		this.admissionTypeId = admissionTypeId;
	}

	/**
	 *  @roseuid 45AF7A0A0190
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getAdmissionTypeId()
	{
		fetch();
		return admissionTypeId;
	}

	/**
	 *  @roseuid 45AF7A0A0190
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initAdmissionType()
	{
		if (admissionType == null)
		{
			admissionType = (Code) new mojo.km.persistence.Reference(admissionTypeId,
					Code.class).getObject();
		}
	}

	/**
	 *  @roseuid 45AF7A0A0190
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getAdmissionType()
	{
		initAdmissionType();
		return admissionType;
	}

	/**
	 *  @roseuid 45AF7A0A0190
	 * set the type reference for class member admissionType
	 */
	public void setAdmissionType(Code admissionType)
	{
		if (this.admissionType == null || !this.admissionType.equals(admissionType))
		{
			markModified();
		}
		if (admissionType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(admissionType);
		}
		setAdmissionTypeId("" + admissionType.getOID());
		this.admissionType = (Code) new mojo.km.persistence.Reference(admissionType).getObject();
	}
	
	/**
	 *  @roseuid 45AF7A0A0190
	 * @return Returns the physicianPhoneNum.
	 */
	public String getPhysicianPhoneNum()
	{
		fetch();
		return physicianPhoneNum;
	}

	/**
	 *  @roseuid 45AF7A0A0190
	 * @param physicianPhoneNum The physicianPhoneNum to set.
	 */
	public void setPhysicianPhoneNum(String physicianPhoneNum)
	{
		if (this.physicianPhoneNum == null || !this.physicianPhoneNum.equals(physicianPhoneNum))
		{
			markModified();
		}
		this.physicianPhoneNum = physicianPhoneNum;
	}
	
	/**
	 *  @roseuid 45AF7A0A0190
	 * @return Returns the entryDate.
	 */
	public Date getEntryDate() {
		fetch();
		return entryDate;
	}
	/**
	 *  @roseuid 45AF7A0A0190
	 * @param entryDate The entryDate to set.
	 */
	public void setEntryDate(Date entryDate) {
		if (this.entryDate == null || !this.entryDate.equals(entryDate))
		{
			markModified();
		}
		this.entryDate = entryDate;
	}
	
	/**
	 *  @roseuid 45AF7A0A0190
	 * @return Returns the admissionDate.
	 */
	public Date getAdmissionDate(){
		fetch();
		return admissionDate;
	}
	/**
	 *  @roseuid 45AF7A0A0190
	 * @param entryDate The admissionDate to set.
	 */
	public void setAdmissionDate(Date admissionDate) {
		if (this.admissionDate == null || !this.admissionDate.equals(admissionDate))
		{
			markModified();
		}
		this.admissionDate = admissionDate;
	}
	
	/**
	 *  @roseuid 45AF7A0A0190
	 * @return Returns the releaseDate.
	 */
	public Date getReleaseDate(){
		fetch();
		return releaseDate;
	}
	/**
	 *  @roseuid 45AF7A0A0190
	 * @param entryDate The releaseDate to set.
	 */
	public void setReleaseDate(Date releaseDate) {
		if (this.releaseDate == null || !this.releaseDate.equals(releaseDate))
		{
			markModified();
		}
		this.releaseDate = releaseDate;
	}
	
	/**
	 *  @roseuid 45AF7A0A0190
	 * @return Returns the physicianName.
	 */
	public String getPhysicianName()
	{
		fetch();
		return physicianName;
	}

	/**
	 *  @roseuid 45AF7A0A0190
	 * @param physicianFirstName The physicianName to set.
	 */
	public void setPhysicianName(String physicianName)
	{
		if (this.physicianName == null || !this.physicianName.equals(physicianName))
		{
			markModified();
		}
		this.physicianName = physicianName;
	}
	
	/**
	 * @roseuid 45AF7A0A0190
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum()
	{
		fetch();
		return juvenileNum;
	}

	/**
	 * @roseuid 45AF7A0A0190
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum)
	{
		if (this.juvenileNum == null || !this.juvenileNum.equals(juvenileNum))
		{
			markModified();
		}
		this.juvenileNum = juvenileNum;
	}
	
	/**
	 * @roseuid 45AF7A0A0190
	 * @return Returns the hospitalizationId.
	 */
	public String getHospitalizationId() {
		fetch();
		return hospitalizationId;
	}
	/**
	 * @roseuid 45AF7A0A0190
	 * @param hospitalizationId The hospitalizationId to set.
	 */
	public void setHospitalizationId(String hospitalizationId) {
		if (this.hospitalizationId == null || !this.hospitalizationId.equals(hospitalizationId))
		{
			markModified();
		}
		this.hospitalizationId = hospitalizationId;
	}
	
	/**
	 * @roseuid 45AF7A0A0190
	 * @return Returns the hospitalizationReason.
	 */
	public String getHospitalizationReason()
	{
		fetch();
		return hospitalizationReason;
	}

	/**
	 * @roseuid 45AF7A0A0190
	 * @param hospitalizationReason The hospitalizationReason to set.
	 */
	public void setHospitalizationReason(String hospitalizationReason)
	{
		if (this.hospitalizationReason == null || !this.hospitalizationReason.equals(hospitalizationReason))
		{
			markModified();
		}
		this.hospitalizationReason = hospitalizationReason;
	}
	/**
	 * @roseuid 45AF7A0A0190
	 * @return Returns the facilityName.
	 */
	public String getFacilityName()
	{
		fetch();
		return facilityName;
	}

	/**
	 * @roseuid 45AF7A0A0190
	 * @param facilityName The facilityName to set.
	 */
	public void setFacilityName(String facilityName)
	{
		if (this.facilityName == null || !this.facilityName.equals(facilityName))
		{
			markModified();
		}
		this.facilityName = facilityName;
	}

	public String getAdmitYear()
	{
	    return admitYear;
	}

	public void setAdmitYear(String admitYear)
	{
	    this.admitYear = admitYear;
	}

	public String getLengthOfStay()
	{
	    return lengthOfStay;
	}

	public void setLengthOfStay(String lengthOfStay)
	{
	    this.lengthOfStay = lengthOfStay;
	}

	
	
	
}
