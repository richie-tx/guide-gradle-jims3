package pd.juvenilecase.mentalhealth;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import java.util.Date;
import java.util.Iterator;

import pd.codetable.Code;


/**
 * Properties for hospitalization
 */
public class Hospitalization extends PersistentObject
{
	/**
	 * Properties for admissionType
	 * @detailerDoNotGenerate false
	 * @referencedType pd.codetable.Code
	 * @contextKey ADMISSION_TYPE
	 */
	//private Code admissionType = null;
	private String admissionType;
	private String admissionTypeId;
	/**
	 * Properties for hospitalization
	 */
	private Date admitDate;
	private String admittingPhysicianFirstName;
	private String admittingPhysicianLastName;
	private String admittingPhysicianMiddleName;
	private String facilityName;
	private String hospitalizationReason;
	private String juvenileNum;
	private String physicianPhone;
	private Date releaseDate;
	private Hospitalization[] mentalHealthHospitalizations;
	private String admitPhysicianName;
	
	/**
	 * @return Returns the hospitalizationId.
	 */
	public String getHospitalizationId() {
		return hospitalizationId;
	}
	/**
	 * @param hospitalizationId The hospitalizationId to set.
	 */
	public void setHospitalizationId(String hospitalizationId) {
		this.hospitalizationId = hospitalizationId;
	}
	private String hospitalizationId;
	/**
	 * @return Returns the admitPhysicianName.
	 */
	public String getAdmitPhysicianName() {
		return admitPhysicianName;
	}
	/**
	 * @param admitPhysicianName The admitPhysicianName to set.
	 */
	public void setAdmitPhysicianName(String admitPhysicianName) {
		this.admitPhysicianName = admitPhysicianName;
	}
	/**
	 * 
	 * @roseuid 45B11D2401F8
	 */
	public Hospitalization()
	{
	}

	/**
	 * 
	 * @roseuid 45AF7A0A0189
	 * @param attrName
	 * @param attrValue
	 * @return java.util.Iterator
	 * @methodInvocation fetch
	 */
	public void findAll()
	{
		fetch();
	}

	/**
	* @return Hospitalization
	* @param event
	* @roseuid 45AF7A0A0190
	*/
	static public Hospitalization find(String hospitalID)
	{
		Hospitalization hospitalizationDetails = null;
		IHome home = new Home();
		hospitalizationDetails = (Hospitalization) home.find(hospitalID, Hospitalization.class);
		return hospitalizationDetails;
	}	
	
	/** 
	 * @roseuid 45AF7A0A0192
	 * @return Iterator of Hospitalization
	* @param event
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, Hospitalization.class);
	}
	
	/**
	* @return java.util.Iterator
	* @param attrName
	* @param attrValue
	* @return Iterator of Hospitalization
	* @roseuid 4236ED950316
	*/
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		return (Iterator) home.findAll(attrName, attrValue, Hospitalization.class);
	}

	/**
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
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getAdmissionTypeId()
	{
		fetch();
		return admissionTypeId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	/*private void initAdmissionType()
	{
		if (admissionType == null)
		{
			admissionType = (pd.codetable.Code) new mojo.km.persistence.Reference(admissionTypeId,
					pd.codetable.Code.class, "ADMISSION_TYPE").getObject();
		}
	}*/

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	/*public pd.codetable.Code getAdmissionType()
	{
		fetch();
		initAdmissionType();
		return admissionType;
	}*/
	public String getAdmissionType()
	{
		fetch();
		return admissionType;
	}

	/**
	 * set the type reference for class member admissionType
	 */
	/*public void setAdmissionType(pd.codetable.Code admissionType)
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
		admissionType.setContext("ADMISSION_TYPE");
		this.admissionType = (pd.codetable.Code) new mojo.km.persistence.Reference(admissionType).getObject();
	}*/
	public void setAdmissionType(String admissionType){
		if (this.admissionType == null
				|| !this.admissionType.equals(admissionType))
		{
			markModified();
		}	
	this.admissionType = admissionType;
	}
	/**
	 * 
	 * @return Returns the admitDate.
	 */
	public Date getAdmitDate()
	{
		fetch();
		return admitDate;
	}

	/**
	 * 
	 * @param admitDate The admitDate to set.
	 */
	public void setAdmitDate(Date admitDate)
	{
		if (this.admitDate == null || !this.admitDate.equals(admitDate))
		{
			markModified();
		}
		this.admitDate = admitDate;
	}

	/**
	 * 
	 * @return Returns the admittingPhysicianFirstName.
	 */
	public String getAdmittingPhysicianFirstName()
	{
		fetch();
		return admittingPhysicianFirstName;
	}

	/**
	 * 
	 * @param admittingPhysicianFirstName The admittingPhysicianFirstName to set.
	 */
	public void setAdmittingPhysicianFirstName(String admittingPhysicianFirstName)
	{
		if (this.admittingPhysicianFirstName == null
				|| !this.admittingPhysicianFirstName.equals(admittingPhysicianFirstName))
		{
			markModified();
		}
		this.admittingPhysicianFirstName = admittingPhysicianFirstName;
	}

	/**
	 * 
	 * @return Returns the admittingPhysicianLastName.
	 */
	public String getAdmittingPhysicianLastName()
	{
		fetch();
		return admittingPhysicianLastName;
	}

	/**
	 * 
	 * @param admittingPhysicianLastName The admittingPhysicianLastName to set.
	 */
	public void setAdmittingPhysicianLastName(String admittingPhysicianLastName)
	{
		if (this.admittingPhysicianLastName == null
				|| !this.admittingPhysicianLastName.equals(admittingPhysicianLastName))
		{
			markModified();
		}
		this.admittingPhysicianLastName = admittingPhysicianLastName;
	}

	/**
	 * 
	 * @return Returns the admittingPhysicianMiddleName.
	 */
	public String getAdmittingPhysicianMiddleName()
	{
		fetch();
		return admittingPhysicianMiddleName;
	}

	/**
	 * 
	 * @param admittingPhysicianMiddleName The admittingPhysicianMiddleName to set.
	 */
	public void setAdmittingPhysicianMiddleName(String admittingPhysicianMiddleName)
	{
		if (this.admittingPhysicianMiddleName == null
				|| !this.admittingPhysicianMiddleName.equals(admittingPhysicianMiddleName))
		{
			markModified();
		}
		this.admittingPhysicianMiddleName = admittingPhysicianMiddleName;
	}

	/**
	 * 
	 * @return Returns the facilityName.
	 */
	public String getFacilityName()
	{
		fetch();
		return facilityName;
	}

	/**
	 * 
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

	/**
	 * 
	 * @return Returns the hospitalizationReason.
	 */
	public String getHospitalizationReason()
	{
		fetch();
		return hospitalizationReason;
	}

	/**
	 * 
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
	 * 
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum()
	{
		fetch();
		return juvenileNum;
	}

	/**
	 * 
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
	 * 
	 * @return Returns the mentalHealthHospitalizations.
	 */
	public Hospitalization[] getMentalHealthHospitalizations()
	{
		fetch();
		return mentalHealthHospitalizations;
	}

	/**
	 * 
	 * @param mentalHealthHospitalizations The mentalHealthHospitalizations to set.
	 */
	public void setMentalHealthHospitalizations(
			Hospitalization[] mentalHealthHospitalizations)
	{
		if (this.mentalHealthHospitalizations == null
				|| !this.mentalHealthHospitalizations.equals(mentalHealthHospitalizations))
		{
			markModified();
		}
		this.mentalHealthHospitalizations = mentalHealthHospitalizations;
	}

	/**
	 * 
	 * @return Returns the physicianPhone.
	 */
	public String getPhysicianPhone()
	{
		fetch();
		return physicianPhone;
	}

	/**
	 * 
	 * @param physicianPhone The physicianPhone to set.
	 */
	public void setPhysicianPhone(String physicianPhone)
	{
		if (this.physicianPhone == null || !this.physicianPhone.equals(physicianPhone))
		{
			markModified();
		}
		this.physicianPhone = physicianPhone;
	}

	/**
	 * 
	 * @return Returns the releaseDate.
	 */
	public Date getReleaseDate()
	{
		fetch();
		return releaseDate;
	}

	/**
	 * 
	 * @param releaseDate The releaseDate to set.
	 */
	public void setReleaseDate(Date releaseDate)
	{
		if (this.releaseDate == null || !this.releaseDate.equals(releaseDate))
		{
			markModified();
		}
		this.releaseDate = releaseDate;
	}
}
