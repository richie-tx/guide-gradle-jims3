package pd.supervision.supervisionstaff.cscdstaffposition;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

import pd.supervision.supervisionstaff.Organization;
import pd.contact.agency.Agency;

/**
 * @author dgibler
 */
public class StaffPositionOrganizationHistoryView extends StaffPositionOrganizationHistory
{
	/**
	 * Properties for agency
	 */
	private Agency agency = null;
	private String agencyId;
	/**
	 * Properties for programSection
	 */
	private Organization programSection = null;
	private String programSectionId;
	/**
	 * Properties for programUnit
	 */
	private Organization programUnit = null;
	private String programUnitId;
	private String jobTitleCode;
	private String jobTitleDesc;
	private String positionTypeDesc;
	private String positionStatusDesc;
	private String officerTypeDesc;
	private String positionTypeCode;
	private String positionStatusCode;

	/**
	 * Gets referenced type pd.contact.agency.Agency
	 */
	public Agency getAgency()
	{
		initAgency();
		return agency;
	}

	/**
	 * Get the reference value to class :: pd.contact.agency.Agency
	 */
	public String getAgencyId()
	{
		fetch();
		return agencyId;
	}

	/**
	 * Gets referenced type pd.supervision.supervisionstaff.Organization
	 */
	public Organization getProgramSection()
	{
		initProgramSection();
		return programSection;
	}

	/**
	 * Get the reference value to class :: pd.supervision.supervisionstaff.Organization
	 */
	public String getProgramSectionId()
	{
		fetch();
		return programSectionId;
	}

	/**
	 * Gets referenced type pd.supervision.supervisionstaff.Organization
	 */
	public Organization getProgramUnit()
	{
		initProgramUnit();
		return programUnit;
	}

	/**
	 * Get the reference value to class :: pd.supervision.supervisionstaff.Organization
	 */
	public String getProgramUnitId()
	{
		fetch();
		return programUnitId;
	}

	/**
	 * Initialize class relationship to class pd.contact.agency.Agency
	 */
	private void initAgency()
	{
		if (agency == null)
		{
			agency = (Agency) new mojo.km.persistence.Reference(agencyId,
					Agency.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.supervisionstaff.Organization
	 */
	private void initProgramSection()
	{
		if (programSection == null)
		{
			programSection = (Organization) new mojo.km.persistence.Reference(
					programSectionId, Organization.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.supervisionstaff.Organization
	 */
	private void initProgramUnit()
	{
		if (programUnit == null)
		{
			programUnit = (Organization) new mojo.km.persistence.Reference(
					programUnitId, Organization.class).getObject();
		}
	}

	/**
	 * set the type reference for class member agency
	 */
	public void setAgency(Agency agency)
	{
		if (this.agency == null || !this.agency.equals(agency))
		{
			markModified();
		}
		if (agency.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(agency);
		}
		setAgencyId("" + agency.getOID());
		this.agency = (Agency) new mojo.km.persistence.Reference(agency).getObject();
	}

	/**
	 * Set the reference value to class :: pd.contact.agency.Agency
	 */
	public void setAgencyId(String agencyId)
	{
		if (this.agencyId == null || !this.agencyId.equals(agencyId))
		{
			markModified();
		}
		agency = null;
		this.agencyId = agencyId;
	}

	/**
	 * set the type reference for class member programSection
	 */
	public void setProgramSection(Organization programSection)
	{
		if (this.programSection == null || !this.programSection.equals(programSection))
		{
			markModified();
		}
		if (programSection.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(programSection);
		}
		setProgramSectionId("" + programSection.getOID());
		this.programSection = (Organization) new mojo.km.persistence.Reference(
				programSection).getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.supervisionstaff.Organization
	 */
	public void setProgramSectionId(String programSectionId)
	{
		if (this.programSectionId == null || !this.programSectionId.equals(programSectionId))
		{
			markModified();
		}
		programSection = null;
		this.programSectionId = programSectionId;
	}

	/**
	 * set the type reference for class member programUnit
	 */
	public void setProgramUnit(Organization programUnit)
	{
		if (this.programUnit == null || !this.programUnit.equals(programUnit))
		{
			markModified();
		}
		if (programUnit.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(programUnit);
		}
		setProgramUnitId("" + programUnit.getOID());
		this.programUnit = (Organization) new mojo.km.persistence.Reference(programUnit)
				.getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.supervisionstaff.Organization
	 */
	public void setProgramUnitId(String programUnitId)
	{
		if (this.programUnitId == null || !this.programUnitId.equals(programUnitId))
		{
			markModified();
		}
		programUnit = null;
		this.programUnitId = programUnitId;
	}
	/* (non-Javadoc)
	 * @see pd.supervision.supervisionstaff.cscdstaffposition.StaffPositionOrganizationHistory#findAll(mojo.km.messaging.IEvent)
	 */
	public static Iterator findAll(IEvent event){
	    IHome home = new Home();
	    return home.findAll(event, StaffPositionOrganizationHistoryView.class);
	}

	public String getJobTitleCode()
	{
		return jobTitleCode;
	}

	public void setJobTitleCode(String jobTitleCode)
	{
		this.jobTitleCode = jobTitleCode;
	}

	public String getJobTitleDesc()
	{
		return jobTitleDesc;
	}

	public void setJobTitleDesc(String jobTitleDesc)
	{
		this.jobTitleDesc = jobTitleDesc;
	}

	public String getOfficerTypeDesc()
	{
		return officerTypeDesc;
	}

	public void setOfficerTypeDesc(String officerTypeDesc)
	{
		this.officerTypeDesc = officerTypeDesc;
	}

	public String getPositionStatusCode()
	{
		return positionStatusCode;
	}

	public void setPositionStatusCode(String positionStatusCode)
	{
		this.positionStatusCode = positionStatusCode;
	}

	public String getPositionStatusDesc()
	{
		return positionStatusDesc;
	}

	public void setPositionStatusDesc(String positionStatusDesc)
	{
		this.positionStatusDesc = positionStatusDesc;
	}

	public String getPositionTypeCode()
	{
		return positionTypeCode;
	}

	public void setPositionTypeCode(String positionTypeCode)
	{
		this.positionTypeCode = positionTypeCode;
	}

	public String getPositionTypeDesc()
	{
		return positionTypeDesc;
	}

	public void setPositionTypeDesc(String positionTypeDesc)
	{
		this.positionTypeDesc = positionTypeDesc;
	}
}
