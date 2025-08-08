package pd.juvenilecase.caseplan;

import mojo.km.persistence.PersistentObject;
import java.util.Date;
import pd.codetable.Code;
import pd.codetable.criminal.JuvenileFacility;

/**
 * @roseuid 45351329027F
 */
public class Placement extends PersistentObject
{
	private Date entryDate;
	private Date expectedReleaseDate;
	private String specialnotes;
	private String placementQuestionsLocation;
	private String supervisionNumber;
	private boolean closestFacilityAvailable;
	private boolean leaseRestrEnvAvailable;
	private boolean proximityToSchoolDist;
	private String reasonForPlacement;
	private String specificServices;
	private String whyOutsideTexas;
	/**
	 * Properties for facility
	 */
	public JuvenileFacility facility = null;
	/**
	 * Properties for facilityReleaseReason
	 */
	public Code facilityReleaseReason = null;
	/**
	 * Properties for levelOfCare
	 */
	public Code levelOfCare = null;
	/**
	 * Properties for permanencyPlan
	 */
	public Code permanencyPlan = null;
	private String facilityId;
	private String facilityReleaseReasonId;
	private String levelOfCareId;
	private String permanencyPlanId;
	/**
	 * Properties for caseplan
	 * @referencedType pd.juvenilecase.caseplan.CasePlan
	 */
	private CasePlan caseplan = null;
	private String caseplanId;

	/**
	 * @roseuid 45351329027F
	 */
	public Placement()
	{
	}

	/**
	 * @roseuid 4534FE3F0024
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public void find()
	{
		fetch();
	}

	/**
	 * @roseuid 4534FE3F0025
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void bind()
	{
		markModified();
	}

	/**
	 * @roseuid 4534FE3F0038
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void None()
	{
		markModified();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setFacilityId(String facilityId)
	{
		if (this.facilityId == null || !this.facilityId.equals(facilityId))
		{
			markModified();
		}
		facility = null;
		this.facilityId = facilityId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getFacilityId()
	{
		fetch();
		return facilityId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initFacility()
	{
		if (facility == null)
		{
		    JuvenileFacility juvFacility = (JuvenileFacility) JuvenileFacility.find("code", facilityId );
	    	
        	    	if(juvFacility != null)
        	    	{     
        	                  facility = juvFacility ;
        	    	}	 
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation initFacility
	 * @methodInvocation initFacility
	 * @methodInvocation initFacility
	 */
	public JuvenileFacility getFacility()
	{
		fetch();
		initFacility();
		return facility;
	}

	/**
	 * set the type reference for class member facility
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setFacilityId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setFacilityId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setFacilityId
	 */
	public void setFacility(JuvenileFacility facility)
	{
		if (this.facility == null || !this.facility.equals(facility))
		{
			markModified();
		}
		if (facility.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(facility);
		}
		setFacilityId("" + facility.getOID());
		this.facility = (JuvenileFacility) new mojo.km.persistence.Reference(facility).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setFacilityReleaseReasonId(String facilityReleaseReasonId)
	{
		if (this.facilityReleaseReasonId == null || !this.facilityReleaseReasonId.equals(facilityReleaseReasonId))
		{
			markModified();
		}
		facilityReleaseReason = null;
		this.facilityReleaseReasonId = facilityReleaseReasonId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getFacilityReleaseReasonId()
	{
		fetch();
		return facilityReleaseReasonId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initFacilityReleaseReason()
	{
		if (facilityReleaseReason == null)
		{
			facilityReleaseReason = (Code) new mojo.km.persistence.Reference(facilityReleaseReasonId,
					Code.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation initFacilityReleaseReason
	 * @methodInvocation initFacilityReleaseReason
	 * @methodInvocation initFacilityReleaseReason
	 */
	public Code getFacilityReleaseReason()
	{
		fetch();
		initFacilityReleaseReason();
		return facilityReleaseReason;
	}

	/**
	 * set the type reference for class member facilityReleaseReason
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setFacilityReleaseReasonId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setFacilityReleaseReasonId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setFacilityReleaseReasonId
	 */
	public void setFacilityReleaseReason(Code facilityReleaseReason)
	{
		if (this.facilityReleaseReason == null || !this.facilityReleaseReason.equals(facilityReleaseReason))
		{
			markModified();
		}
		if (facilityReleaseReason.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(facilityReleaseReason);
		}
		setFacilityReleaseReasonId("" + facilityReleaseReason.getOID());
		this.facilityReleaseReason = (Code) new mojo.km.persistence.Reference(facilityReleaseReason)
				.getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setLevelOfCareId(String levelOfCareId)
	{
		if (this.levelOfCareId == null || !this.levelOfCareId.equals(levelOfCareId))
		{
			markModified();
		}
		levelOfCare = null;
		this.levelOfCareId = levelOfCareId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getLevelOfCareId()
	{
		fetch();
		return levelOfCareId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initLevelOfCare()
	{
		if (levelOfCare == null)
		{
			levelOfCare = (Code) new mojo.km.persistence.Reference(levelOfCareId, Code.class)
					.getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation initLevelOfCare
	 * @methodInvocation initLevelOfCare
	 * @methodInvocation initLevelOfCare
	 */
	public Code getLevelOfCare()
	{
		fetch();
		initLevelOfCare();
		return levelOfCare;
	}

	/**
	 * set the type reference for class member levelOfCare
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setLevelOfCareId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setLevelOfCareId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setLevelOfCareId
	 */
	public void setLevelOfCare(Code levelOfCare)
	{
		if (this.levelOfCare == null || !this.levelOfCare.equals(levelOfCare))
		{
			markModified();
		}
		if (levelOfCare.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(levelOfCare);
		}
		setLevelOfCareId("" + levelOfCare.getOID());
		this.levelOfCare = (Code) new mojo.km.persistence.Reference(levelOfCare).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setPermanencyPlanId(String permanencyPlanId)
	{
		if (this.permanencyPlanId == null || !this.permanencyPlanId.equals(permanencyPlanId))
		{
			markModified();
		}
		permanencyPlan = null;
		this.permanencyPlanId = permanencyPlanId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getPermanencyPlanId()
	{
		fetch();
		return permanencyPlanId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initPermanencyPlan()
	{
		if (permanencyPlan == null)
		{
			permanencyPlan = (Code) new mojo.km.persistence.Reference(permanencyPlanId,
					Code.class, "PERMANENCY_PLAN").getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation initPermanencyPlan
	 * @methodInvocation initPermanencyPlan
	 * @methodInvocation initPermanencyPlan
	 */
	public Code getPermanencyPlan()
	{
		fetch();
		initPermanencyPlan();
		return permanencyPlan;
	}

	/**
	 * set the type reference for class member permanencyPlan
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setPermanencyPlanId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setPermanencyPlanId
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setPermanencyPlanId
	 */
	public void setPermanencyPlan(Code permanencyPlan)
	{
		if (this.permanencyPlan == null || !this.permanencyPlan.equals(permanencyPlan))
		{
			markModified();
		}
		if (permanencyPlan.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(permanencyPlan);
		}
		setPermanencyPlanId("" + permanencyPlan.getOID());
		this.permanencyPlan = (Code) new mojo.km.persistence.Reference(permanencyPlan).getObject();
	}

	/**
	 * @return Returns the reasonForPlacement.
	 * @methodInvocation fetch
	 */
	public String getReasonForPlacement()
	{
		fetch();
		return reasonForPlacement;
	}

	/**
	 * @param reasonForPlacement The reasonForPlacement to set.
	 * @methodInvocation markModified
	 */
	public void setReasonForPlacement(String reasonForPlacement)
	{
		if (this.reasonForPlacement == null || !this.reasonForPlacement.equals(reasonForPlacement))
		{
			markModified();
		}
		this.reasonForPlacement = reasonForPlacement;
	}

	/**
	 * @return Returns the specialnotes.
	 * @methodInvocation fetch
	 */
	public String getSpecialnotes()
	{
		fetch();
		return specialnotes;
	}

	/**
	 * @param specialnotes The specialnotes to set.
	 * @methodInvocation markModified
	 */
	public void setSpecialnotes(String specialnotes)
	{
		if (this.specialnotes == null || !this.specialnotes.equals(specialnotes))
		{
			markModified();
		}
		this.specialnotes = specialnotes;
	}

	/**
	 * @return Returns the specificServices.
	 * @methodInvocation fetch
	 */
	public String getSpecificServices()
	{
		fetch();
		return specificServices;
	}

	/**
	 * @param specificServices The specificServices to set.
	 * @methodInvocation markModified
	 */
	public void setSpecificServices(String specificServices)
	{
		if (this.specificServices == null || !this.specificServices.equals(specificServices))
		{
			markModified();
		}
		this.specificServices = specificServices;
	}

	/**
	 * @return Returns the supervisionNumber.
	 * @methodInvocation fetch
	 */
	public String getSupervisionNumber()
	{
		fetch();
		return supervisionNumber;
	}

	/**
	 * @param supervisionNumber The supervisionNumber to set.
	 * @methodInvocation markModified
	 */
	public void setSupervisionNumber(String supervisionNumber)
	{
		if (this.supervisionNumber == null || !this.supervisionNumber.equals(supervisionNumber))
		{
			markModified();
		}
		this.supervisionNumber = supervisionNumber;
	}

	/**
	 * @return Returns the whyOutsideTexas.
	 * @methodInvocation fetch
	 */
	public String getWhyOutsideTexas()
	{
		fetch();
		return whyOutsideTexas;
	}

	/**
	 * @param whyOutsideTexas The whyOutsideTexas to set.
	 * @methodInvocation markModified
	 */
	public void setWhyOutsideTexas(String whyOutsideTexas)
	{
		if (this.whyOutsideTexas == null || !this.whyOutsideTexas.equals(whyOutsideTexas))
		{
			markModified();
		}
		this.whyOutsideTexas = whyOutsideTexas;
	}

	/**
	 * @return Returns the entryDate.
	 */
	public Date getEntryDate()
	{
		fetch();
		return entryDate;
	}

	/**
	 * @param entryDate The entryDate to set.
	 */
	public void setEntryDate(Date entryDate)
	{
		if (this.entryDate == null || !this.entryDate.equals(entryDate))
		{
			markModified();
		}
		this.entryDate = entryDate;
	}

	/**
	 * @return Returns the expectedReleaseDate.
	 */
	public Date getExpectedReleaseDate()
	{
		fetch();
		return expectedReleaseDate;
	}

	/**
	 * @param expectedReleaseDate The expectedReleaseDate to set.
	 */
	public void setExpectedReleaseDate(Date expectedReleaseDate)
	{
		if (this.expectedReleaseDate == null || !this.expectedReleaseDate.equals(expectedReleaseDate))
		{
			markModified();
		}
		this.expectedReleaseDate = expectedReleaseDate;
	}

	/**
	 * @return Returns the leaseRestrEnvAvailalbe.
	 */
	public boolean getLeaseRestrEnvAvailable()
	{
		fetch();
		return leaseRestrEnvAvailable;
	}

	/**
	 * @param leaseRestrEnvAvailalbe The leaseRestrEnvAvailalbe to set.
	 */
	public void setLeaseRestrEnvAvailable(boolean leaseRestrEnvAvailalbe)
	{
		if (this.leaseRestrEnvAvailable != leaseRestrEnvAvailalbe)
		{
			markModified();
		}
		this.leaseRestrEnvAvailable = leaseRestrEnvAvailalbe;
	}

	/**
	 * @return Returns the proximityToSchoolDist.
	 */
	public boolean getProximityToSchoolDist()
	{
		fetch();
		return proximityToSchoolDist;
	}

	/**
	 * @param proximityToSchoolDist The proximityToSchoolDist to set.
	 */
	public void setProximityToSchoolDist(boolean proximityToSchoolDist)
	{
		if (this.proximityToSchoolDist != proximityToSchoolDist)
		{
			markModified();
		}
		this.proximityToSchoolDist = proximityToSchoolDist;
	}

	/**
	 * @return Returns the closestFacilityAvailable.
	 */
	public boolean getClosestFacilityAvailable()
	{
		fetch();
		return closestFacilityAvailable;
	}

	/**
	 * @param closestFacilityAvailable The closestFacilityAvailable to set.
	 */
	public void setClosestFacilityAvailable(boolean closestFacilityAvailable)
	{
		if (this.closestFacilityAvailable != closestFacilityAvailable)
		{
			markModified();
		}
		this.closestFacilityAvailable = closestFacilityAvailable;
	}

	/**
	 * Set the reference value to class :: pd.juvenilecase.caseplan.CasePlan
	 */
	public void setCaseplanId(String caseplanId)
	{
		if (this.caseplanId == null || !this.caseplanId.equals(caseplanId))
		{
			markModified();
		}
		caseplan = null;
		this.caseplanId = caseplanId;
	}

	/**
	 * Get the reference value to class :: pd.juvenilecase.caseplan.CasePlan
	 */
	public String getCaseplanId()
	{
		fetch();
		return caseplanId;
	}

	/**
	 * Initialize class relationship to class pd.juvenilecase.caseplan.CasePlan
	 */
	private void initCaseplan()
	{
		if (caseplan == null)
		{
			caseplan = (CasePlan) new mojo.km.persistence.Reference(caseplanId,
					CasePlan.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.juvenilecase.caseplan.CasePlan
	 */
	public CasePlan getCaseplan()
	{
		initCaseplan();
		return caseplan;
	}

	/**
	 * set the type reference for class member caseplan
	 */
	public void setCaseplan(CasePlan caseplan)
	{
		if (this.caseplan == null || !this.caseplan.equals(caseplan))
		{
			markModified();
		}
		if (caseplan.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(caseplan);
		}
		setCaseplanId("" + caseplan.getOID());
		this.caseplan = (CasePlan) new mojo.km.persistence.Reference(caseplan).getObject();
	}
}

