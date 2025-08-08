package pd.supervision.administercompliance;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;

import naming.PDCodeTableConstants;

import messaging.administercompliance.GetNCResponsesEvent;
import messaging.administercompliance.UpdateNCResponseEvent;
import messaging.administercompliance.reply.NCPreviousCourtActivityResponseEvent;
import messaging.administercompliance.reply.NCResponseResponseEvent;
import mojo.km.messaging.ResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import pd.codetable.Code;
import pd.security.PDSecurityHelper;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * type of Report is either 'Violation Report' or 'Case Summary'
 */
public class ViolationReport extends PersistentObject
{
	private String statusId;
	private String caseId;
	private Timestamp filedDate;
	private int orderId;
	/**
	 * type of Report is either 'Violation Report' or 'Case Summary'
	 */
	private String reportType;
	private String courtId;
	private String presentedBy;
	private String presentedByPositionId;
	private String signedBy;
	private String signedByPositionId;
	private String courtActionSummary;
	private Timestamp lastContactDate;
	private String addressType;
	private String aptNumber;
	private String city;
	private String stateId;
	private String streetName;
	private String streetNumber;
	private String zipcode;
	private int hoursOrdered;
	private int hoursCompleted;
	private String totalSpecimenAnalyzed;
	private String typeOfCourtActionComment;
	private int taskId;
	
	private String managerApprovalUser;
	private Timestamp managerApprovalDate;
	private String submissionApprovedUser;
	private Timestamp submissionApprovedDate;
	private String filedUser;
	private Timestamp statusChangedDate;
	private Timestamp subMgrAppDate;
	
	private Code addressTypeCode = null;
	private Code state = null;
	private Code status = null;
	
	
	/**
	 * Properties for employments
	 * @referencedType pd.supervision.administercompliance.Employment
	 * @detailerDoNotGenerate true
	 */
	private Collection employments;
	/**
	 * Properties for delinquentFees
	 * @referencedType pd.supervision.administercompliance.DelinquentFee
	 * @detailerDoNotGenerate true
	 */
	private Collection delinquentFees;
	/**
	 * Properties for treatments
	 * @referencedType pd.supervision.administercompliance.Treatment
	 * @detailerDoNotGenerate true
	 */
	private Collection treatments;
	/**
	 * Properties for positiveUAs
	 * @referencedType pd.supervision.administercompliance.PositiveUA
	 * @detailerDoNotGenerate true
	 */
	private Collection positiveUAs;
	/**
	 * Properties for reportings
	 * @referencedType pd.supervision.administercompliance.Reporting
	 * @detailerDoNotGenerate true
	 */
	private Collection reportings;
	/**
	 * Properties for recommendedApprovedCourtActions
	 * @referencedType pd.supervision.administercompliance.RecommendedApprovedCourtAction
	 * @detailerDoNotGenerate true
	 */
	private Collection recommendedApprovedCourtActions;
	/**
	 * Properties for reasonForTransfers
	 * @referencedType pd.supervision.administercompliance.ReasonForTransfer
	 * @detailerDoNotGenerate true
	 */
	private Collection reasonForTransfers;
	/**
	 * Properties for previousCourtActivities
	 * @referencedType pd.supervision.administercompliance.PreviousCourtActivity
	 * @detailerDoNotGenerate true
	 */
	private Collection previousCourtActivities;
	/**
	 * Properties for lawViolations
	 * @referencedType pd.supervision.administercompliance.LawViolation
	 * @detailerDoNotGenerate true
	 */
	private Collection lawViolations;

	/**
	 * @return Returns the addressType.
	 */
	public String getAddressType()
	{
		fetch();
		return addressType;
	}

	/**
	 * @param addressType The addressType to set.
	 */
	public void setAddressType(String addressType)
	{
		if (this.addressType == null || !this.addressType.equals(addressType))
		{
			markModified();
		}
		this.addressType = addressType;
	}

	/**
	 * @return Returns the aptNumber.
	 */
	public String getAptNumber()
	{
		fetch();
		return aptNumber;
	}

	/**
	 * @param aptNumber The aptNumber to set.
	 */
	public void setAptNumber(String aptNumber)
	{
		if (this.aptNumber == null || !this.aptNumber.equals(aptNumber))
		{
			markModified();
		}
		this.aptNumber = aptNumber;
	}

	/**
	 * @return Returns the caseId.
	 */
	public String getCaseId()
	{
		fetch();
		return caseId;
	}

	/**
	 * @param caseId The caseId to set.
	 */
	public void setCaseId(String caseId)
	{
		if (this.caseId == null || !this.caseId.equals(caseId))
		{
			markModified();
		}
		this.caseId = caseId;
	}

	/**
	 * @return Returns the city.
	 */
	public String getCity()
	{
		fetch();
		return city;
	}

	/**
	 * @param city The city to set.
	 */
	public void setCity(String city)
	{
		if (this.city == null || !this.city.equals(city))
		{
			markModified();
		}
		this.city = city;
	}

	/**
	 * @return Returns the courtActionSummary.
	 */
	public String getCourtActionSummary()
	{
		fetch();
		return courtActionSummary;
	}

	/**
	 * @param courtActionSummary The courtActionSummary to set.
	 */
	public void setCourtActionSummary(String courtActionSummary)
	{
		if (this.courtActionSummary == null || !this.courtActionSummary.equals(courtActionSummary))
		{
			markModified();
		}
		this.courtActionSummary = courtActionSummary;
	}

	/**
	 * @return Returns the courtId.
	 */
	public String getCourtId()
	{
		fetch();
		return courtId;
	}

	/**
	 * @param courtId The courtId to set.
	 */
	public void setCourtId(String courtId)
	{
		if (this.courtId == null || !this.courtId.equals(courtId))
		{
			markModified();
		}
		this.courtId = courtId;
	}

	/**
	 * @return Returns the filedDate.
	 */
	public Timestamp getFiledDate()
	{
		fetch();
		return filedDate;
	}

	/**
	 * @param filedDate The filedDate to set.
	 */
	public void setFiledDate(Timestamp filedDate)
	{
		if (this.filedDate == null || !this.filedDate.equals(filedDate))
		{
			markModified();
		}
		this.filedDate = filedDate;
	}

	/**
	 * @return Returns the hoursCompleted.
	 */
	public int getHoursCompleted()
	{
		fetch();
		return hoursCompleted;
	}

	/**
	 * @param hoursCompleted The hoursCompleted to set.
	 */
	public void setHoursCompleted(int hoursCompleted)
	{
		if (this.hoursCompleted != hoursCompleted)
		{
			markModified();
		}
		this.hoursCompleted = hoursCompleted;
	}

	/**
	 * @return Returns the hoursOrdered.
	 */
	public int getHoursOrdered()
	{
		fetch();
		return hoursOrdered;
	}

	/**
	 * @param hoursOrdered The hoursOrdered to set.
	 */
	public void setHoursOrdered(int hoursOrdered)
	{
		if (this.hoursOrdered != hoursOrdered)
		{
			markModified();
		}
		this.hoursOrdered = hoursOrdered;
	}

	/**
	 * @return Returns the lastContactDate.
	 */
	public Timestamp getLastContactDate()
	{
		fetch();
		return lastContactDate;
	}

	/**
	 * @param lastContactDate The lastContactDate to set.
	 */
	public void setLastContactDate(Timestamp lastContactDate)
	{
		if (this.lastContactDate == null || !this.lastContactDate.equals(lastContactDate))
		{
			markModified();
		}
		this.lastContactDate = lastContactDate;
	}

	/**
	 * @return Returns the presentedBy.
	 */
	public String getPresentedBy()
	{
		fetch();
		return presentedBy;
	}

	/**
	 * @param presentedBy The presentedBy to set.
	 */
	public void setPresentedBy(String presentedBy)
	{
		if (this.presentedBy == null || !this.presentedBy.equals(presentedBy))
		{
			markModified();
		}
		this.presentedBy = presentedBy;
	}

	/**
	 * @return Returns the presentedByPositionId.
	 */
	public String getPresentedByPositionId()
	{
		fetch();
		return presentedByPositionId;
	}

	/**
	 * @param presentedByPositionId The presentedByPositionId to set.
	 */
	public void setPresentedByPositionId(String presentedByPositionId)
	{
		if (this.presentedByPositionId == null || !this.presentedByPositionId.equals(presentedByPositionId))
		{
			markModified();
		}
		this.presentedByPositionId = presentedByPositionId;
	}

	/**
	 * @return Returns the reportType.
	 */
	public String getReportType()
	{
		fetch();
		return reportType;
	}

	/**
	 * @param reportType The reportType to set.
	 */
	public void setReportType(String reportType)
	{
		if (this.reportType == null || !this.reportType.equals(reportType))
		{
			markModified();
		}
		this.reportType = reportType;
	}

	/**
	 * @return Returns the signedBy.
	 */
	public String getSignedBy()
	{
		fetch();
		return signedBy;
	}

	/**
	 * @param signedBy The signedBy to set.
	 */
	public void setSignedBy(String signedBy)
	{
		if (this.signedBy == null || !this.signedBy.equals(signedBy))
		{
			markModified();
		}
		this.signedBy = signedBy;
	}

	/**
	 * @return Returns the signedByPositionId.
	 */
	public String getSignedByPositionId()
	{
		fetch();
		return signedByPositionId;
	}

	/**
	 * @param signedByPositionId The signedByPositionId to set.
	 */
	public void setSignedByPositionId(String signedByPositionId)
	{
		if (this.signedByPositionId == null || !this.signedByPositionId.equals(signedByPositionId))
		{
			markModified();
		}
		this.signedByPositionId = signedByPositionId;
	}

	/**
	 * @return Returns the state.
	 */
	public String getStateId()
	{
		fetch();
		return stateId;
	}

	/**
	 * @param state The state to set.
	 */
	public void setStateId(String stateId)
	{
		if (this.stateId == null || !this.stateId.equals(stateId))
		{
			markModified();
		}
		this.stateId = stateId;
	}

	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId()
	{
		fetch();
		return statusId;
	}

	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId)
	{
		if (this.statusId == null || !this.statusId.equals(statusId))
		{
			markModified();
		}
		this.statusId = statusId;
	}

	/**
	 * @return Returns the streetName.
	 */
	public String getStreetName()
	{
		fetch();
		return streetName;
	}

	/**
	 * @param streetName The streetName to set.
	 */
	public void setStreetName(String streetName)
	{
		if (this.streetName == null || !this.streetName.equals(streetName))
		{
			markModified();
		}
		this.streetName = streetName;
	}

	/**
	 * @return Returns the streetNumber.
	 */
	public String getStreetNumber()
	{
		fetch();
		return streetNumber;
	}

	/**
	 * @param streetNumber The streetNumber to set.
	 */
	public void setStreetNumber(String streetNumber)
	{
		if (this.streetNumber == null || !this.streetNumber.equals(streetNumber))
		{
			markModified();
		}
		this.streetNumber = streetNumber;
	}
	/**
	 * @return Returns the typeOfCourtActionComment.
	 */
	public String getTypeOfCourtActionComment()
	{
		fetch();
		return typeOfCourtActionComment;
	}

	/**
	 * @param typeOfCourtActionComment The typeOfCourtActionComment to set.
	 */
	public void setTypeOfCourtActionComment(String typeOfCourtActionComment)
	{
		if (this.typeOfCourtActionComment == null || !this.typeOfCourtActionComment.equals(typeOfCourtActionComment))
		{
			markModified();
		}
		this.typeOfCourtActionComment = typeOfCourtActionComment;
	}
	/**
	 * @return Returns the totalSpecimenAnalyzed.
	 */
	public String getTotalSpecimenAnalyzed()
	{
		fetch();
		return totalSpecimenAnalyzed;
	}

	/**
	 * @param totalSpecimenAnalyzed The totalSpecimenAnalyzed to set.
	 */
	public void setTotalSpecimenAnalyzed(String totalSpecimenAnalyzed)
	{
		if (this.totalSpecimenAnalyzed == null || !this.totalSpecimenAnalyzed.equals(totalSpecimenAnalyzed))
		{
			markModified();
		}
		this.totalSpecimenAnalyzed = totalSpecimenAnalyzed;
	}

	/**
	 * @return Returns the zipcode.
	 */
	public String getZipcode()
	{
		fetch();
		return zipcode;
	}

	/**
	 * @param zipcode The zipcode to set.
	 */
	public void setZipcode(String zipcode)
	{
		if (this.zipcode == null || !this.zipcode.equals(zipcode))
		{
			markModified();
		}
		this.zipcode = zipcode;
	}

	/**
	 * Initialize class relationship implementation for pd.supervision.administercompliance.Employment
	 */
	private void initEmployments()
	{
		if (employments == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			employments = new mojo.km.persistence.ArrayList(Employment.class,
					"ncResponseId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.supervision.administercompliance.Employment
	 */
	public Collection getEmployments()
	{
		fetch();
		initEmployments();
		return employments;
	}

	/**
	 * insert a pd.supervision.administercompliance.Employment into class relationship collection.
	 */
	public void insertEmployments(Employment anObject)
	{
		initEmployments();
		employments.add(anObject);
	}

	/**
	 * Removes a pd.supervision.administercompliance.Employment from class relationship collection.
	 */
	public void removeEmployments(Employment anObject)
	{
		initEmployments();
		employments.remove(anObject);
	}

	/**
	 * Clears all pd.supervision.administercompliance.Employment from class relationship collection.
	 */
	public void clearEmployments()
	{
		initEmployments();
		employments.clear();
	}

	/**
	 * Initialize class relationship implementation for pd.supervision.administercompliance.DelinquentFee
	 */
	private void initDelinquentFees()
	{
		if (delinquentFees == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			delinquentFees = new mojo.km.persistence.ArrayList(DelinquentFee.class,
					"ncResponseId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.supervision.administercompliance.DelinquentFee
	 */
	public Collection getDelinquentFees()
	{
		fetch();
		initDelinquentFees();
		return delinquentFees;
	}

	/**
	 * insert a pd.supervision.administercompliance.DelinquentFee into class relationship collection.
	 */
	public void insertDelinquentFees(DelinquentFee anObject)
	{
		initDelinquentFees();
		delinquentFees.add(anObject);
	}

	/**
	 * Removes a pd.supervision.administercompliance.DelinquentFee from class relationship collection.
	 */
	public void removeDelinquentFees(DelinquentFee anObject)
	{
		initDelinquentFees();
		delinquentFees.remove(anObject);
	}

	/**
	 * Clears all pd.supervision.administercompliance.DelinquentFee from class relationship collection.
	 */
	public void clearDelinquentFees()
	{
		initDelinquentFees();
		delinquentFees.clear();
	}

	/**
	 * Initialize class relationship implementation for pd.supervision.administercompliance.Treatment
	 */
	private void initTreatments()
	{
		if (treatments == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			treatments = new mojo.km.persistence.ArrayList(Treatment.class,
					"ncResponseId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.supervision.administercompliance.Treatment
	 */
	public Collection getTreatments()
	{
		fetch();
		initTreatments();
		return treatments;
	}

	/**
	 * insert a pd.supervision.administercompliance.Treatment into class relationship collection.
	 */
	public void insertTreatments(Treatment anObject)
	{
		initTreatments();
		treatments.add(anObject);
	}

	/**
	 * Removes a pd.supervision.administercompliance.Treatment from class relationship collection.
	 */
	public void removeTreatments(Treatment anObject)
	{
		initTreatments();
		treatments.remove(anObject);
	}

	/**
	 * Clears all pd.supervision.administercompliance.Treatment from class relationship collection.
	 */
	public void clearTreatments()
	{
		initTreatments();
		treatments.clear();
	}

	/**
	 * Initialize class relationship implementation for pd.supervision.administercompliance.PositiveUA
	 */
	private void initPositiveUAs()
	{
		if (positiveUAs == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			positiveUAs = new mojo.km.persistence.ArrayList(PositiveUA.class,
					"ncResponseId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.supervision.administercompliance.PositiveUA
	 */
	public Collection getPositiveUAs()
	{
		fetch();
		initPositiveUAs();
		return positiveUAs;
	}

	/**
	 * insert a pd.supervision.administercompliance.PositiveUA into class relationship collection.
	 */
	public void insertPositiveUAs(PositiveUA anObject)
	{
		initPositiveUAs();
		positiveUAs.add(anObject);
	}

	/**
	 * Removes a pd.supervision.administercompliance.PositiveUA from class relationship collection.
	 */
	public void removePositiveUAs(PositiveUA anObject)
	{
		initPositiveUAs();
		positiveUAs.remove(anObject);
	}

	/**
	 * Clears all pd.supervision.administercompliance.PositiveUA from class relationship collection.
	 */
	public void clearPositiveUAs()
	{
		initPositiveUAs();
		positiveUAs.clear();
	}

	/**
	 * Initialize class relationship implementation for pd.supervision.administercompliance.Reporting
	 */
	private void initReportings()
	{
		if (reportings == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			reportings = new mojo.km.persistence.ArrayList(Reporting.class,
					"ncResponseId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.supervision.administercompliance.Reporting
	 */
	public Collection getReportings()
	{
		fetch();
		initReportings();
		return reportings;
	}

	/**
	 * insert a pd.supervision.administercompliance.Reporting into class relationship collection.
	 */
	public void insertReportings(Reporting anObject)
	{
		initReportings();
		reportings.add(anObject);
	}

	/**
	 * Removes a pd.supervision.administercompliance.Reporting from class relationship collection.
	 */
	public void removeReportings(Reporting anObject)
	{
		initReportings();
		reportings.remove(anObject);
	}

	/**
	 * Clears all pd.supervision.administercompliance.Reporting from class relationship collection.
	 */
	public void clearReportings()
	{
		initReportings();
		reportings.clear();
	}

	/**
	 * Initialize class relationship implementation for pd.supervision.administercompliance.RecommendedApprovedCourtAction
	 */
	private void initRecommendedApprovedCourtActions()
	{
		if (recommendedApprovedCourtActions == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			recommendedApprovedCourtActions = new mojo.km.persistence.ArrayList(
					RecommendedApprovedCourtAction.class, "ncResponseId", ""
							+ getOID());
		}
	}

	/**
	 * returns a collection of pd.supervision.administercompliance.RecommendedApprovedCourtAction
	 */
	public Collection getRecommendedApprovedCourtActions()
	{
		fetch();
		initRecommendedApprovedCourtActions();
		return recommendedApprovedCourtActions;
	}

	/**
	 * insert a pd.supervision.administercompliance.RecommendedApprovedCourtAction into class relationship collection.
	 */
	public void insertRecommendedApprovedCourtActions(
			RecommendedApprovedCourtAction anObject)
	{
		initRecommendedApprovedCourtActions();
		recommendedApprovedCourtActions.add(anObject);
	}

	/**
	 * Removes a pd.supervision.administercompliance.RecommendedApprovedCourtAction from class relationship collection.
	 */
	public void removeRecommendedApprovedCourtActions(
			RecommendedApprovedCourtAction anObject)
	{
		initRecommendedApprovedCourtActions();
		recommendedApprovedCourtActions.remove(anObject);
	}

	/**
	 * Clears all pd.supervision.administercompliance.RecommendedApprovedCourtAction from class relationship collection.
	 */
	public void clearRecommendedApprovedCourtActions()
	{
		initRecommendedApprovedCourtActions();
		recommendedApprovedCourtActions.clear();
	}

	/**
	 * Initialize class relationship implementation for pd.supervision.administercompliance.ReasonForTransfer
	 */
	private void initReasonForTransfers()
	{
		if (reasonForTransfers == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			reasonForTransfers = new mojo.km.persistence.ArrayList(
					ReasonForTransfer.class, "ncResponseId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.supervision.administercompliance.ReasonForTransfer
	 */
	public Collection getReasonForTransfers()
	{
		fetch();
		initReasonForTransfers();
		return reasonForTransfers;
	}

	/**
	 * insert a pd.supervision.administercompliance.ReasonForTransfer into class relationship collection.
	 */
	public void insertReasonForTransfers(ReasonForTransfer anObject)
	{
		initReasonForTransfers();
		reasonForTransfers.add(anObject);
	}

	/**
	 * Removes a pd.supervision.administercompliance.ReasonForTransfer from class relationship collection.
	 */
	public void removeReasonForTransfers(ReasonForTransfer anObject)
	{
		initReasonForTransfers();
		reasonForTransfers.remove(anObject);
	}

	/**
	 * Clears all pd.supervision.administercompliance.ReasonForTransfer from class relationship collection.
	 */
	public void clearReasonForTransfers()
	{
		initReasonForTransfers();
		reasonForTransfers.clear();
	}

	/**
	 * Initialize class relationship implementation for pd.supervision.administercompliance.PreviousCourtActivity
	 */
	private void initPreviousCourtActivities()
	{
		if (previousCourtActivities == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			previousCourtActivities = new mojo.km.persistence.ArrayList(
					PreviousCourtActivity.class, "ncResponseId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.supervision.administercompliance.PreviousCourtActivity
	 */
	public Collection getPreviousCourtActivities()
	{
		fetch();
		initPreviousCourtActivities();
		return previousCourtActivities;
	}

	/**
	 * insert a pd.supervision.administercompliance.PreviousCourtActivity into class relationship collection.
	 */
	public void insertPreviousCourtActivities(PreviousCourtActivity anObject)
	{
		initPreviousCourtActivities();
		previousCourtActivities.add(anObject);
	}

	/**
	 * Removes a pd.supervision.administercompliance.PreviousCourtActivity from class relationship collection.
	 */
	public void removePreviousCourtActivities(PreviousCourtActivity anObject)
	{
		initPreviousCourtActivities();
		previousCourtActivities.remove(anObject);
	}

	/**
	 * Clears all pd.supervision.administercompliance.PreviousCourtActivity from class relationship collection.
	 */
	public void clearPreviousCourtActivities()
	{
		initPreviousCourtActivities();
		previousCourtActivities.clear();
	}

	/**
	 * Initialize class relationship implementation for pd.supervision.administercompliance.LawViolation
	 */
	private void initLawViolations()
	{
		if (lawViolations == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			lawViolations = new mojo.km.persistence.ArrayList(LawViolation.class,
					"ncResponseId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.supervision.administercompliance.LawViolation
	 */
	public Collection getLawViolations()
	{
		initLawViolations();
		return lawViolations;
	}

	/**
	 * insert a pd.supervision.administercompliance.LawViolation into class relationship collection.
	 */
	public void insertLawViolations(LawViolation anObject)
	{
		initLawViolations();
		lawViolations.add(anObject);
	}

	/**
	 * Removes a pd.supervision.administercompliance.LawViolation from class relationship collection.
	 */
	public void removeLawViolations(LawViolation anObject)
	{
		initLawViolations();
		lawViolations.remove(anObject);
	}

	/**
	 * Clears all pd.supervision.administercompliance.LawViolation from class relationship collection.
	 */
	public void clearLawViolations()
	{
		initLawViolations();
		lawViolations.clear();
	}

	/**
	 * @param event
	 * @return
	 */
	public static Iterator findAll(GetNCResponsesEvent event) {
		return new Home().findAll(event, ViolationReport.class);
	}
	
	/**
	 * @param event
	 * @return
	 */
	public static Iterator findAll(String attrName, String attrVal) {
		return new Home().findAll(attrName, attrVal, ViolationReport.class);
	}
	
	/**
	 * @param event
	 * @return
	 */
	public static ViolationReport find(String ncResponseId) {
		return (ViolationReport) new Home().find(ncResponseId, ViolationReport.class);
	}

	/**
	 * @return NCResponseResponseEvent
	 */
	public NCResponseResponseEvent getResponseEvent() {
		NCResponseResponseEvent resp = new NCResponseResponseEvent();
    	resp.setCaseId(this.getCaseId());
    	resp.setCreateDate(this.getCreateTimestamp());
    	resp.setCreatedBy(this.getCreateUserID());
    	resp.setNcResponseId(this.getOID());
    	resp.setStatusId(this.getStatusId());
    	if(this.getStatusId() != null && !this.getStatusId().equals("")){
    		Code code = this.getStatus();
    		if(code != null){
    			resp.setStatus(code.getDescription());
    		}
    	}
    	resp.setStatusChangedDate(this.getStatusChangedDate());
    	resp.setSubMgrAppDate(this.getSubMgrAppDate());
    	resp.setFiledUser(this.getFiledUser());
    	resp.setFiledDate( this.getFiledDate() );
    	resp.setManagerApprovalDate(this.getManagerApprovalDate());
    	resp.setManagerApprovalUser(this.getManagerApprovalUser());
    	resp.setSubmissionApprovedDate(this.getSubmissionApprovedDate());
    	resp.setSubmissionApprovedUser(this.getSubmissionApprovedUser());
    	resp.setTaskId(String.valueOf(this.getTaskId()));
    	resp.setCourtActionSummary(this.getCourtActionSummary());
		return resp;
	}
	
	/**
	* Gets referenced type pd.codetable.Code
	* @return status
	*/
	public Code getStatus()
	{
		fetch();
		initStatus();
		return status;
	}
	
	
	private void initStatus() {
		if (status == null)
		{
			try
			{
				status =
					(Code) new mojo
						.km
						.persistence
						.Reference(statusId, Code.class, PDCodeTableConstants.VIOLATION_REPORT_STATUS)
						.getObject();
			}
			catch (Throwable t)
			{
				status = null;
			}
		}	
	}

	/**
	* Gets referenced type pd.codetable.Code
	* @return addressTypeCode
	*/
	public Code getAddressTypeCode()
	{
		fetch();
		initAddressType();
		return addressTypeCode;
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initAddressType()
	{
		if (addressTypeCode == null)
		{
			try
			{
				addressTypeCode =
					(Code) new mojo
						.km
						.persistence
						.Reference(addressType, Code.class, PDCodeTableConstants.ADDRESS_TYPE)
						.getObject();
			}
			catch (Throwable t)
			{
				addressType = null;
			}
		}
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initState()
	{
		if (state == null)
		{
			try
			{
				state =
					(Code) new mojo
						.km
						.persistence
						.Reference(stateId, Code.class, PDCodeTableConstants.STATE_ABBR)
						.getObject();
			}
			catch (Throwable t)
			{
				state = null;
			}
		}
	}
	
	/**
	* Gets referenced type pd.codetable.Code
	* @return Code
	*/
	public Code getState()
	{
		fetch();
		initState();
		return state;
	}

	/**
	 * @return
	 */
	public NCResponseResponseEvent getPositiveUAResponse() {
		NCResponseResponseEvent resp = new NCResponseResponseEvent();
		resp.setTotalSpecimenAnalyzed(this.getTotalSpecimenAnalyzed());	
		return resp;
	}

	/**
	 * @return
	 */
	public NCPreviousCourtActivityResponseEvent getCourtActivityResponseEvent(String type, String subType) {
		NCPreviousCourtActivityResponseEvent resp = new NCPreviousCourtActivityResponseEvent();
//		resp.setActivity(ViolationReportConstants.PREVIOUS_COURT_ACTIVITY_VIOLATION);
		resp.setTypeOfCourtActionComment(this.getTypeOfCourtActionComment());
		resp.setNcResponseId(this.getOID());
		resp.setOccurenceDate(this.getCreateTimestamp());
		resp.setSummaryOfCourtAction(this.getCourtActionSummary());		
		resp.setType(type);
		resp.setSubType(subType);
		return resp;
	}
	
	/**
	 * @return
	 */
	private NCResponseResponseEvent getDetailResponseEvent() {
		NCResponseResponseEvent resp = this.getResponseEvent();			
		resp.setAddressType(this.getAddressType());
		resp.setAptNumber(this.getAptNumber());
		resp.setCity(this.getCity());
		resp.setLastContactDate(this.getLastContactDate());
		resp.setState(this.getStateId());
		resp.setStreetName(this.getStreetName());
		resp.setStreetNumber(this.getStreetNumber());
		resp.setTotalSpecimenAnalyzed(this.getTotalSpecimenAnalyzed());
		resp.setZipcode(this.getZipcode());
		resp.setFiledDate(this.getFiledDate());
		resp.setPositionIdOfPresentedBy(this.getPresentedByPositionId());
		resp.setPositionIdOfSignedBy(this.getSignedByPositionId());
		resp.setPresentedBy(this.getPresentedBy());
		resp.setSignedBy(this.getSignedBy());
		resp.setTaskId(String.valueOf(this.getTaskId()));
		return resp;
	}

	/**
	 * @param reqEvent
	 */
	public void setLastKnownAddress(UpdateNCResponseEvent reqEvent) {
		this.setAddressType(reqEvent.getAddressTypeId());
		this.setLastContactDate(reqEvent.getLastContactDate());
		this.setCity(reqEvent.getCity());
		this.setStreetNumber(reqEvent.getStreetNumber());
		this.setStreetName(reqEvent.getStreetName());
		this.setStateId(reqEvent.getState());
		this.setZipcode(reqEvent.getZipcode());	
	}
	
	/**
	 * @param reqEvent
	 */
	public void setLastKnownAddress(String addressTypeId, Timestamp lastContactDate,String city,String streetNumber,String streetName,String stateId, String zipCode) {
		this.setAddressType(addressTypeId);
		this.setLastContactDate(lastContactDate);
		this.setCity(city);
		this.setStreetNumber(streetNumber);
		this.setStreetName(streetName);
		this.setStateId(stateId);
		this.setZipcode(zipcode);	
	}	
	
	public static void delete(String ncResponseId){
		ViolationReport vr = ViolationReport.find(ncResponseId);
		if(vr != null){
			vr.delete();
		}
	}
	
	public static NCResponseResponseEvent get(String ncResponseId){
		ViolationReport vr = ViolationReport.find(ncResponseId);
		return vr.getDetailResponseEvent();
	}
	
	public static void post(String ncResponseId){
		MessageUtil.postReply(get(ncResponseId));
	}
	
	public void post(){
		MessageUtil.postReply(get(this));
	}

	private ResponseEvent get(ViolationReport report) {
		return report.getDetailResponseEvent();
	}

	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		fetch();
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		if (this.orderId != orderId)
		{
			markModified();
		}
		this.orderId = orderId;
	}
	
	public void commit(){
		new Home().bind(this);
	}

	public String create(UpdateNCResponseEvent reqEvent) {
		this.setStatusId(reqEvent.getStatusId());
		this.setCaseId(reqEvent.getCaseId());
		this.setOrderId(reqEvent.getOrderId());
		this.setReportType(reqEvent.getReportType());
		this.setCreateUserID(PDSecurityHelper.getLogonId());	
		this.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
		commit();
		return this.getOID();
	}

	/**
	 * @return the filedUser
	 */
	public String getFiledUser() {
		fetch();
		return filedUser;
	}

	/**
	 * @return the managerApprovalDate
	 */
	public Timestamp getManagerApprovalDate() {
		fetch();
		return managerApprovalDate;
	}

	/**
	 * @return the managerApprovalUser
	 */
	public String getManagerApprovalUser() {
		fetch();
		return managerApprovalUser;
	}

	/**
	 * @return the submissionApprovedDate
	 */
	public Timestamp getSubmissionApprovedDate() {
		fetch();
		return submissionApprovedDate;
	}

	/**
	 * @return the submissionApprovedUser
	 */
	public String getSubmissionApprovedUser() {
		fetch();
		return submissionApprovedUser;
	}

	/**
	 * @param filedUser the filedUser to set
	 */
	public void setFiledUser(String filedUser) {
		if (this.filedUser == null || !this.filedUser.equals(filedUser)){
			markModified();
		}
		this.filedUser = filedUser;
	}

	/**
	 * @param managerApprovalDate the managerApprovalDate to set
	 */
	public void setManagerApprovalDate(Timestamp managerApprovalDate) {
		if (this.managerApprovalDate == null || !this.managerApprovalDate.equals(managerApprovalDate)){
			markModified();
		}
		this.managerApprovalDate = managerApprovalDate;
	}

	/**
	 * @param managerApprovalUser the managerApprovalUser to set
	 */
	public void setManagerApprovalUser(String managerApprovalUser) {
		if (this.managerApprovalUser == null || !this.managerApprovalUser.equals(managerApprovalUser)){
			markModified();
		}
		this.managerApprovalUser = managerApprovalUser;
	}

	/**
	 * @param submissionApprovedDate the submissionApprovedDate to set
	 */
	public void setSubmissionApprovedDate(Timestamp submissionApprovedDate) {
		if (this.submissionApprovedDate == null || !this.submissionApprovedDate.equals(submissionApprovedDate)){
			markModified();
		}
		this.submissionApprovedDate = submissionApprovedDate;
	}

	/**
	 * @param submissionApprovedUser the submissionApprovedUser to set
	 */
	public void setSubmissionApprovedUser(String submissionApprovedUser) {
		if (this.submissionApprovedUser == null || !this.submissionApprovedUser.equals(submissionApprovedUser)){
			markModified();
		}
		this.submissionApprovedUser = submissionApprovedUser;
	}

	/**
	 * @return the statusChangedDate
	 */
	public Timestamp getStatusChangedDate() {
		fetch();
		return statusChangedDate;
	}

	/**
	 * @param statusChangedDate the statusChangedDate to set
	 */
	public void setStatusChangedDate(Timestamp statusChangedDate) {
		if (this.statusChangedDate == null || !this.statusChangedDate.equals(statusChangedDate)){
			markModified();
		}
		this.statusChangedDate = statusChangedDate;
	}

	/**
	 * @return the subMgrAppDate
	 */
	public Timestamp getSubMgrAppDate() {
		fetch();
		return subMgrAppDate;
	}

	/**
	 * @param subMgrAppDate the subMgrAppDate to set
	 */
	public void setSubMgrAppDate(Timestamp subMgrAppDate) {
		if (this.subMgrAppDate == null || !this.subMgrAppDate.equals(subMgrAppDate)){
			markModified();
		}
		this.subMgrAppDate = subMgrAppDate;
	}

	/**
	 * @return the taskId
	 */
	public int getTaskId() {
		fetch();
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(int taskId) {
		if (this.taskId != taskId)
		{
			markModified();
		}
		this.taskId = taskId;
	}
	
}
