package pd.supervision.supervisionstaff.cscdstaffposition;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

import pd.supervision.supervisionstaff.Organization;
import pd.contact.agency.Agency;

/**
 * @author dgibler
 */
public class CSCDOrgStaffWorkgroup extends CSCDStaffPosition
{
	/* (non-Javadoc)
	 * @see pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition#findAll(mojo.km.messaging.IEvent)
	 */
	public static Iterator findAll(IEvent event){
	    IHome home = new Home();
	    return home.findAll(event, CSCDOrgStaffWorkgroup.class);
	}
	/**
	 * Properties for agency
	 */
	private Agency agency = null;
	private String agencyId;
	private String cjadNum;
	private String organizationName;
	private String legacyProgramUnit;
	/**
	 * Properties for programUnit
	 */
	private Organization programUnit = null;
	private String programUnitId;
	/**
	 * Properties for section
	 */
	private Organization section = null;
	private String sectionId;
	private String sectionName;
	private String unitName;
	private String workGroupDescription;
	private String workGroupName;
	private String workgroupTypeCodeId;
	
	private Date effectiveDate;

	/**
	 * Gets referenced type pd.contact.agency.Agency
	 */
	public Agency getAgency()
	{
		fetch();
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
	 * 
	 * @return Returns the cjadNum.
	 */
	public String getCjadNum()
	{
		fetch();
		return cjadNum;
	}
    /**
     * @return Returns the organizationName.
     */
    public String getOrganizationName() {
        fetch();
        return organizationName;
    }

	/**
	 * Gets referenced type pd.supervision.supervisionstaff.Organization
	 */
	public Organization getProgramUnit()
	{
		fetch();
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
	 * Gets referenced type pd.supervision.supervisionstaff.Organization
	 */
	public Organization getSection()
	{
		fetch();
		initSection();
		return section;
	}

	/**
	 * Get the reference value to class :: pd.supervision.supervisionstaff.Organization
	 */
	public String getSectionId()
	{
		fetch();
		return sectionId;
	}
    /**
     * @return Returns the sectionName.
     */
    public String getSectionName() {
        fetch();
        return sectionName;
    }
    /**
     * @return Returns the unitName.
     */
    public String getUnitName() {
        fetch();
        return unitName;
    }
    /**
     * @return Returns the workGroupDescription.
     */
    public String getWorkGroupDescription() {
        fetch();
        return workGroupDescription;
    }
    /**
     * @return Returns the workGroupName.
     */
    public String getWorkGroupName() {
        fetch();
        return workGroupName;
    }
    /**
     * @return Returns the workgroupTypeCodeId.
     */
    public String getWorkgroupTypeCodeId() {
        fetch();
        return workgroupTypeCodeId;
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
	private void initProgramUnit()
	{
		if (programUnit == null)
		{
			programUnit = (Organization) new mojo.km.persistence.Reference(
					programUnitId, Organization.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.supervisionstaff.Organization
	 */
	private void initSection()
	{
		if (section == null)
		{
			section = (Organization) new mojo.km.persistence.Reference(sectionId,
					Organization.class).getObject();
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
	 * 
	 * @param cjadNum The cjadNum to set.
	 */
	public void setCjadNum(String cjadNum)
	{
		if (this.cjadNum == null || !this.cjadNum.equals(cjadNum))
		{
			markModified();
		}
		this.cjadNum = cjadNum;
	}
    /**
     * @param organizationName The organizationName to set.
     */
    public void setOrganizationName(String organizationName) {
		if (this.organizationName == null || !this.organizationName.equals(organizationName))
		{
			markModified();
		}

        this.organizationName = organizationName;
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

	/**
	 * set the type reference for class member section
	 */
	public void setSection(Organization sectionId)
	{
		if (this.section == null || !this.section.equals(sectionId))
		{
			markModified();
		}
		if (sectionId.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(sectionId);
		}
		setSectionId("" + sectionId.getOID());
		this.section = (Organization) new mojo.km.persistence.Reference(sectionId)
				.getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.supervisionstaff.Organization
	 */
	public void setSectionId(String sectionIdId)
	{
		if (this.sectionId == null || !this.sectionId.equals(sectionIdId))
		{
			markModified();
		}
		section = null;
		this.sectionId = sectionIdId;
	}
    /**
     * @param sectionName The sectionName to set.
     */
    public void setSectionName(String sectionName) {
		if (this.sectionName == null || !this.sectionName.equals(sectionName))
		{
			markModified();
		}
        this.sectionName = sectionName;
    }
    /**
     * @param unitName The unitName to set.
     */
    public void setUnitName(String unitName) {
		if (this.unitName == null || !this.unitName.equals(unitName))
		{
			markModified();
		}
        this.unitName = unitName;
    }
    /**
     * @param workGroupDescription The workGroupDescription to set.
     */
    public void setWorkGroupDescription(String workGroupDescription) {
		if (this.workGroupDescription == null || !this.workGroupDescription.equals(workGroupDescription))
		{
			markModified();
		}
        this.workGroupDescription = workGroupDescription;
    }
    /**
     * @param workGroupName The workGroupName to set.
     */
    public void setWorkGroupName(String workGroupName) {
		if (this.workGroupName == null || !this.workGroupName.equals(workGroupName))
		{
			markModified();
		}
        this.workGroupName = workGroupName;
    }
    /**
     * @param workgroupTypeCodeId The workgroupTypeCodeId to set.
     */
    public void setWorkgroupTypeCodeId(String workgroupTypeCodeId) {
		if (this.workgroupTypeCodeId == null || !this.workgroupTypeCodeId.equals(workgroupTypeCodeId))
		{
			markModified();
		}
        this.workgroupTypeCodeId = workgroupTypeCodeId;
    }

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getLegacyProgramUnit()
	{
		return legacyProgramUnit;
	}

	public void setLegacyProgramUnit(String legacyProgramUnit)
	{
		this.legacyProgramUnit = legacyProgramUnit;
	}

}
