/*
 * Created on May 3, 2005
 *
 */
package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.struts.action.ActionForm;

/* 30 nov 2006 - mjt
 remove the import that follows after PD is done ... 
 look for: public Collection getReasonsNotDone() to fix that end
 */
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.mentalhealth.reply.MAYSIDetailsResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.Name;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;

/**
 * @author dapte
 * 
 *         This class currently holds the MAYSI related UI fields.
 */
public class MentalHealthForm extends ActionForm
{
	// constants to check against
	public final String SUBSEQUENT_NEEDED = "SUBSEQUENT NEEDED";
	public final String SUBSEQUENT_DONE = "SUBSEQUENT DONE";

	// Attributes of Casefile form that are used to query
	// the MAYSI system
	private String raceId;
	private String race;
	private String ethnicity;
	private String hispanic; //U.S 88526
	private String ethnicityHispanic;
	private String sexId;
	private String sex;
	private String detailRaceId;
	private String detailRace;
	private String detailSexId;
	private String detailSex;
	private String juvenileNum;
	private Date assessmentDate;
	private String assessmentTime;
	private Date juvenileDOB;
	private String juvenileName;
	private int juvenileAge;
	private String juvenileStatus;
	// the casefile id will be used to obtain a list of
	// referral numbers for that casefile.
	private String casefileId;
	private boolean subReferral;
	private boolean assessmentComplete;
	private boolean hasDetails = false;
	private boolean hasSubAssessment = false;

	// The collection of previous MAYSI results events
	private Collection previousMAYSIResults;
	private String maysiId = "";

	// temp variable for maysi text file contents
	private String maysiTextFileContents;
	private String maysAssesId;

	// Maysi Key
	private String assessmentId;
	private String subAssessId;
	private String maysiDetailId;

	// Subsequent assessment piece
	private String sequenceNum;
	private String assessmentOption;
	private Date assessmentReviewDate;
	private String assessmentReviewTime;
	private String assessmentOfficerName;
	private String assessmentOfficerID;
	private String subsAssessmentComments;
	private String wasSubsAssessmentCompleted; // this drives the setting of
	private String updatedMaysiComments;																						// assessmentOption to
																							// "Subsequent Completed"

	// Attributes that detail the MAYSI screening when user
	// selects one of the previous results
	// The above attributes for subsequent assessment will
	// be re-used to display the subsequent assessment piece
	// of the detail screen.
	private Date screenDate;
	private String referralNum;
	private boolean hasPreviousMAYSI;
	private String locationUnitId;
	private String locationUnit;
	private String lengthOfStayId;
	private String lengthOfStay;

	private String reasonNotDoneId; // added
	private String reasonNotDone;
	private String otherReasonNotDone;
	private String scheduledOffIntDateStr;

	private String facilityTypeId;
	private String facilityType;
	private String alcoholDrug;
	private boolean alcoholDrugCaution;
	private boolean alcoholDrugWarning;
	private String angryIrritable;
	private boolean angryIrritableCaution;
	private boolean angryIrritableWarning;
	private String depressionAnxiety;
	private boolean depressionAnxietyCaution;
	private boolean depressionAnxietyWarning;
	private String somaticComplaint;
	private boolean somaticComplaintCaution;
	private boolean somaticComplaintWarning;
	private String suicideIdeation;
	private boolean suicideIdeationCaution;
	private boolean suicideIdeationWarning;
	private String thoughtDisturbance;
	private boolean thoughtDisturbanceCaution;
	private boolean thoughtDisturbanceWarning;
	private String traumaticExpression;
	private boolean traumaticExpressionCaution;
	private boolean traumaticExpressionWarning;
	private String testAge = "";

	// For New Assessment, we will re-use the
	// referralNumber, location, lengthOfStay, facilityType and
	// previousMAYSI fields. All dropdowns except the referralNumber will come
	// from code tables
	// using ui.CodeHelper. So we do not need any Collection type attributes
	// for those.
	// The referralNumber dropdown will show all referralNumbers associated to
	// that casefile.
	private Collection referralNums;
	private Collection locations;
	Collection reasonsNotDone;

	// Added for ER 25992
	private boolean administer;

	private Collection providerReferredTypes;
	private String providerReferredTypeId;
	private String providerReferredType;

	private String subsAssessmentReferral;

	// added for PCC
	private String action = "";

	// Added to check whether to send the task or not.
	private boolean isTask;

	/**
	 * 
	 */
	public void clearForNewAssessment()
	{
		maysAssesId = "";
		administer = false;
		isTask = false;

		referralNum = "";

		locationUnitId = "";
		locationUnit = "";

		lengthOfStayId = "";
		lengthOfStay = "";
		
		otherReasonNotDone ="";
		reasonNotDoneId = "";
		reasonNotDone = "";
		
		scheduledOffIntDateStr = "";
		
		facilityTypeId = "";
		facilityType = "";

		hasPreviousMAYSI = false;
		providerReferredTypeId = "";
		juvenileDOB = null;
	}

	public void clearForSubAssessment()
	{
		subReferral = false;
		assessmentComplete = false;
		subsAssessmentComments = "";
		wasSubsAssessmentCompleted = "";
		subsAssessmentReferral = "";
		updatedMaysiComments = "";
	}

	/**
	 * @return
	 */
	public String getAlcoholDrug()
	{
		return alcoholDrug;
	}

	/**
	 * @return
	 */
	public String getAngryIrritable()
	{
		return angryIrritable;
	}

	/**
	 * @return
	 */
	public String getAssessmentOfficerID()
	{
		return assessmentOfficerID;
	}

	/**
	 * @return
	 */
	public String getAssessmentOfficerName()
	{
		return assessmentOfficerName;
	}

	/**
	 * @return Returns the maysiID.
	 */
	public String getMaysiId()
	{
		return maysiId;
	}

	/**
	 * @param maysiID
	 *          The maysiID to set.
	 */
	public void setMaysiId(String maysiID)
	{
		this.maysiId = maysiID;
	}

	/**
	 * @param locations
	 *          The locations to set.
	 */
	public void setLocations(Collection locations)
	{
		this.locations = locations;
	}

	/**
	 * @param providerReferredType
	 *          The providerReferredType to set.
	 */
	public void setProviderReferredType(String providerReferredType)
	{
		this.providerReferredType = providerReferredType;
	}

	/**
	 * @param reasonsNotDone
	 *          The reasonsNotDone to set.
	 */
	public void setReasonsNotDone(Collection reasonsNotDone)
	{
		this.reasonsNotDone = reasonsNotDone;
	}

	/**
	 * @return
	 */
	public String getAssessmentOption()
	{
		return assessmentOption;
	}

	/**
	 * @return
	 */
	public Date getAssessmentReviewDate()
	{
		return assessmentReviewDate;
	}

	/**
	 * @return
	 */
	public String getDepressionAnxiety()
	{
		return depressionAnxiety;
	}

	/**
	 * @return
	 */
	public String getFacilityType()
	{
		return facilityType;
	}

	/**
	 * @return
	 */
	public boolean isHasPreviousMAYSI()
	{
		return hasPreviousMAYSI;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @return String
	 */
	public String getReasonNotDone()
	{
		return reasonNotDone;
	}

	/**
	 * @return the scheduledOffIntDateStr
	 */
	public String getScheduledOffIntDateStr() {
		return scheduledOffIntDateStr;
	}

	/**
	 * @return
	 */
	public String getLengthOfStay()
	{
		return lengthOfStay;
	}

	/**
	 * @return
	 */
	public String getLocationUnit()
	{
		return locationUnit;
	}

	/**
	 * @return
	 */
	public Collection getPreviousMAYSIResults()
	{
		if( previousMAYSIResults == null )
		{
			previousMAYSIResults = new ArrayList();
		}

		return previousMAYSIResults;
	}

	/**
	 * @return
	 */
	public String getReferralNum()
	{
		return referralNum;
	}

	/**
	 * @return
	 */
	public Date getScreenDate()
	{
		return screenDate;
	}

	/**
	 * @return
	 */
	public String getSomaticComplaint()
	{
		return somaticComplaint;
	}

	/**
	 * @return
	 */
	public String getSubsAssessmentComments()
	{
		return subsAssessmentComments;
	}

	/**
	 * @return
	 */
	public String getSuicideIdeation()
	{
		return suicideIdeation;
	}

	/**
	 * @return
	 */
	public String getThoughtDisturbance()
	{
		return thoughtDisturbance;
	}

	/**
	 * @return
	 */
	public String getTraumaticExpression()
	{
		return traumaticExpression;
	}

	/**
	 * @return
	 */
	public String getWasSubsAssessmentCompleted()
	{
		return wasSubsAssessmentCompleted;
	}

	/**
	 * @param string
	 */
	public void setAlcoholDrug(String string)
	{
		alcoholDrug = string;
	}

	/**
	 * @param string
	 */
	public void setAngryIrritable(String string)
	{
		angryIrritable = string;
	}

	/**
	 * @param string
	 */
	public void setAssessmentOfficerID(String string)
	{
		assessmentOfficerID = string;
	}

	/**
	 * @param string
	 */
	public void setAssessmentOfficerName(String string)
	{
		assessmentOfficerName = string;
	}

	/**
	 * @param string
	 */
	public void setAssessmentOption(String string)
	{
		assessmentOption = string;
	}

	/**
	 * @param date
	 */
	public void setAssessmentReviewDate(Date date)
	{
		assessmentReviewDate = date;
	}

	/**
	 * @param string
	 */
	public void setDepressionAnxiety(String string)
	{
		depressionAnxiety = string;
	}

	/**
	 * @param string
	 */
	public void setFacilityType(String string)
	{
		facilityType = string;
	}

	/**
	 * @param b
	 */
	public void setHasPreviousMAYSI(boolean b)
	{
		hasPreviousMAYSI = b;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @param string
	 */
	public void setReasonNotDone(String val)
	{
		reasonNotDone = val;
	}

	/**
	 * @param scheduledOffIntDateStr the scheduledOffIntDateStr to set
	 */
	public void setScheduledOffIntDateStr(String scheduledOffIntDateStr) {
		this.scheduledOffIntDateStr = scheduledOffIntDateStr;
	}

	/**
	 * @param string
	 */
	public void setLengthOfStay(String string)
	{
		lengthOfStay = string;
	}

	/**
	 * @param string
	 */
	public void setLocationUnit(String string)
	{
		locationUnit = string;
	}

	/**
	 * @param collection
	 */
	public void setPreviousMAYSIResults(Collection collection)
	{
		previousMAYSIResults = collection;
	}

	/**
	 * @param string
	 */
	public void setReferralNum(String string)
	{
		referralNum = string;
	}

	/**
	 * @param date
	 */
	public void setScreenDate(Date date)
	{
		screenDate = date;
	}

	/**
	 * @param string
	 */
	public void setSomaticComplaint(String string)
	{
		somaticComplaint = string;
	}

	/**
	 * @param string
	 */
	public void setSubsAssessmentComments(String string)
	{
		subsAssessmentComments = string;
	}

	/**
	 * @param string
	 */
	public void setSuicideIdeation(String string)
	{
		suicideIdeation = string;
	}

	/**
	 * @param string
	 */
	public void setThoughtDisturbance(String string)
	{
		thoughtDisturbance = string;
	}

	/**
	 * @param string
	 */
	public void setTraumaticExpression(String string)
	{
		traumaticExpression = string;
	}

	/**
	 * @param b
	 */
	public void setWasSubsAssessmentCompleted(String b)
	{
		wasSubsAssessmentCompleted = b;
	}

	/**
	 * @return
	 */
	public Collection getReferralNums()
	{
		referralNums = UIJuvenileCaseworkHelper.fetchUniqueJuvenileCasefileReferralsList(casefileId, juvenileNum);
		return referralNums;
	}

	/**
	 * @param collection
	 */
	public void setReferralNums(Collection collection)
	{
		referralNums = collection;
	}

	public Collection getLocations()
	{
	    	List<LocationResponseEvent> filteredmaysilocations = new ArrayList<>();
	    	List<LocationResponseEvent> activeLocations = ComplexCodeTableHelper.getActiveJuvenileLocationUnits();
		//filter for maysi flag 1
		for (LocationResponseEvent location : activeLocations ) {
		    /*if(location.getMaysiFlg()!=null)
		    {*/
        		    if (  location.getMaysiFlg()==1 ) {
        			filteredmaysilocations.add(location);
        		    }
		    //}
		}
		locations = filteredmaysilocations;
		return locations;
		
	}

	public Collection getReasonsNotDone() /* */
	{ // 30 nov 2006 - mjt - i've dummied this call out until the PD portion is
		// completed
	// Collection reasonNotDone = new Vector() ; // return(
	// CodeHelper.getReasonsNotDone() ) ;
		//reasonsNotDone = CodeHelper.getReasonNotDoneCode(true);
		reasonsNotDone = UIJuvenileHelper.fetchMAYSIreasoncodes();
		return reasonsNotDone;
	}

	public Collection getLengthsOfStay()
	{
		return CodeHelper.getLengthsOfStay();
	}

	public Collection getPlacementTypes()
	{
	    List<CodeResponseEvent> maysiPlacementTypes = CodeHelper.getMAYSIPlacementTypes();
	    List<CodeResponseEvent> filterredMaysiPlacementTypes = new ArrayList<>();
	    if ( maysiPlacementTypes != null
		    && maysiPlacementTypes.size() > 0 ) {
		for (CodeResponseEvent placementType : maysiPlacementTypes ) {
		    if ( "ACTIVE".equals( placementType.getStatus().toUpperCase() ) ) {
			filterredMaysiPlacementTypes.add(placementType);
		    }
		}
	    }
		return filterredMaysiPlacementTypes ;
	}
	
	public Collection getMaysireasonCodes()
	{
	    List<CodeResponseEvent> maysireasoncodes=UIJuvenileHelper.fetchMAYSIreasoncodes();
	    List<CodeResponseEvent> filteredmaysireasoncodes = new ArrayList<>();
	    if ( maysireasoncodes != null
		    && maysireasoncodes.size() > 0 ) {
		for (CodeResponseEvent code : maysireasoncodes ) {
		    if ( "ACTIVE".equals( code.getStatus().toUpperCase() ) ) {
			filteredmaysireasoncodes.add(code);
		    }
		}
	    }
		return filteredmaysireasoncodes ;
	}

	/**
	 * @return
	 */
	public String getMaysiTextFileContents()
	{
		return maysiTextFileContents;
	}

	/**
	 * @param string
	 */
	public void setMaysiTextFileContents(String string)
	{
		maysiTextFileContents = string;
	}

	/**
	 * Populates the mental health with the contents of the incoming
	 * MAYSIDetailsResponseEvent.
	 * 
	 * @param MAYSIDetailsResponseEvent
	 */
	public void populateFromMAYSIDetailsEvent(MAYSIDetailsResponseEvent detail)
	{
		setReferralNum(String.valueOf(detail.getReferralNumber()));
		setMaysiId(detail.getAssessmentId());
		setSexId(detail.getSexId());
		setSex(detail.getSex());
		setRaceId(detail.getRaceId());
		setTestAge(detail.getTestAge());
		setRace(detail.getRace());
		setAssessmentDate(detail.getAssessmentDate());
		setAssessmentTime(detail.getAssessmentTime());

		Name officerName = new Name(detail.getAssessOfficerName().getFirstName(),
				detail.getAssessOfficerName().getMiddleName(), detail.getAssessOfficerName().getLastName());
		
		setAssessmentOfficerName(officerName.getFormattedName());
		setAssessmentOption(detail.getAssessmentOption());
		setHasPreviousMAYSI(detail.isHasPreviousMAYSI());
		setHispanic(detail.getHispanic());
		setAdminister(detail.isAdministered());
		setOtherReasonNotDone(detail.getOtherReasonNotDone());
		setReasonNotDone(detail.getReasonNotDone());
		setScheduledOffIntDateStr("");
		if (detail.getScheduledOffIntDate() != null){
			setScheduledOffIntDateStr(DateUtil.dateToString(detail.getScheduledOffIntDate(), DateUtil.DATE_FMT_1));
		} 	
		if( detail.getReasonNotDoneId() == null )
		{
			setReasonNotDoneId("");
		}

		String loc = detail.getLocationUnit();
		if( loc == null || loc.equals("") || loc.equals("null") )
		{
			setLocationUnit("");
		}
		else
		{
			setLocationUnit(loc);
		}

		setLengthOfStay(detail.getLengthOfStay());

		setFacilityType(detail.getFacilityType());
		setHasDetails(false);
		if( detail.haveMAYSIDetails() )
		{
			setHasDetails(true);
			setCautionsNWarnings(detail);
			setDetailSexId(detail.getDetailSexId());
			setDetailRaceId(detail.getDetailRaceId());

			setScreenDate(detail.getScreenDate());
			setAlcoholDrug(String.valueOf(detail.getAlcoholDrug()));
			setAngryIrritable(String.valueOf(detail.getAngryIrritable()));
			setDepressionAnxiety(String.valueOf(detail.getDepressionAnxiety()));
			setSomaticComplaint(String.valueOf(detail.getSomaticComplaint()));
			setSuicideIdeation(String.valueOf(detail.getSuicideIdetaion()));
			setThoughtDisturbance(String.valueOf(detail.getThoughtDisturbance()));
			setTraumaticExpression(String.valueOf(detail.getTraumaticExpression()));

		}
		setHasSubAssessment(false);
		if( detail.haveMAYSISubAssessDetails() )
		{
			setHasSubAssessment(true);
			setAssessmentReviewDate(detail.getAssessmentReviewdate());
			setAssessmentReviewTime(detail.getAssessmentReviewtime());
			setSubsAssessmentReferral(Boolean.toString(detail.isSubReferral()));
			setProviderReferredType(detail.getProviderType());
			setWasSubsAssessmentCompleted(Boolean.toString(detail.isAssessComplete()));
			setSubsAssessmentComments(detail.getReviewComments());
		}
	}

	/*
	 * 
	 */
	public void setCautionsNWarnings(MAYSIDetailsResponseEvent detail)
	{
		setAlcoholDrugCaution(false);
		setAlcoholDrugWarning(false);
		if( detail.getAlcoholDrug() >= 7 )
		{
			setAlcoholDrugWarning(true);
		}
		else if( detail.getAlcoholDrug() >= 4 )
		{
			setAlcoholDrugCaution(true);
		}
		setAngryIrritableCaution(false);
		setAngryIrritableWarning(false);
		if( detail.getAngryIrritable() >= 8 )
		{
			setAngryIrritableWarning(true);
		}
		else if( detail.getAngryIrritable() >= 5 )
		{
			setAngryIrritableCaution(true);
		}
		setDepressionAnxietyCaution(false);
		setDepressionAnxietyWarning(false);
		if( detail.getDepressionAnxiety() >= 6 )
		{
			setDepressionAnxietyWarning(true);
		}
		else if( detail.getDepressionAnxiety() >= 3 )
		{
			setDepressionAnxietyCaution(true);
		}
		setSomaticComplaintCaution(false);
		setSomaticComplaintWarning(false);
		if( detail.getSomaticComplaint() >= 6 )
		{
			setSomaticComplaintWarning(true);
		}
		else if( detail.getSomaticComplaint() >= 3 )
		{
			setSomaticComplaintCaution(true);
		}
		setThoughtDisturbanceCaution(false);
		setThoughtDisturbanceWarning(false);
		if( detail.getThoughtDisturbance() >= 2 )
		{
			setThoughtDisturbanceWarning(true);
		}
		else if( detail.getThoughtDisturbance() >= 1 )
		{
			setThoughtDisturbanceCaution(true);
		}
		//<KISHORE>JIMS200060170 : MJCW - MAYSI Isn't Displaying the Warnings Correctly
		// Add logic here for suicide/Ideation 
		// Once requirements document has been updated
		//maysiDetail.jsp has already been updated
		setSuicideIdeationCaution(false);
		setSuicideIdeationWarning(false);
		// 08/13/2013 #75944 revised values for Warning from >= 3 to > 1 and Caution from >= 2 to == 1 
		if( detail.getSuicideIdetaion() > 1 )
		{
			setSuicideIdeationWarning(true);
		}
		else if( detail.getSuicideIdetaion() == 1 )
		{
			setSuicideIdeationCaution(true);
		}
	}

	/**
	 * Sets the descriptions of the dropdown codes to be displayed on the summary 
	 */
	public void processCodeDescriptions()
	{
		if( locationUnitId != null  && locations != null )
		{
			String unitID = "" ;
			LocationResponseEvent tEvt ;
			Iterator iter = locations.iterator();
			while( iter.hasNext() )
			{
				tEvt = (LocationResponseEvent)iter.next();
				unitID = tEvt.getJuvLocationUnitId() ;
				if( unitID != null  && tEvt.getJuvLocationUnitId().equals(locationUnitId) )
				{
					setLocationUnit(tEvt.getLocationUnitName());
					break;
				}
			} // while
		}

		CodeResponseEvent evt;
		if( lengthOfStayId != null  &&  (lengthOfStayId.length() > 0) )
		{
			evt = UIUtil.findCodeResponseEvent(getLengthsOfStay().iterator(), lengthOfStayId);
			setLengthOfStay(evt.getDescription());
		}

		if( reasonNotDoneId != null &&  (reasonNotDoneId.length() > 0) )
		{
			evt = UIUtil.findCodeResponseEvent(getReasonsNotDone().iterator(), reasonNotDoneId);
			// 30 nov 2006 - mjt - i've commented out this call out until the PD
			// portion is completed
			setReasonNotDone(evt.getDescription()); // MentalHealthForm.java
		}

		if( facilityTypeId != null  &&  (facilityTypeId.length() > 0) )
		{
			evt = UIUtil.findCodeResponseEvent(getPlacementTypes().iterator(), facilityTypeId);
			setFacilityType(evt.getDescription());
		}
	}

	/**
	 * @return
	 */
	public String getFacilityTypeId()
	{
		return facilityTypeId;
	}

	/**
	 * @return String
	 */
	public String getReasonNotDoneId()
	{
		return reasonNotDoneId;
	}

	/**
	 * @return
	 */
	public String getLengthOfStayId()
	{
		return lengthOfStayId;
	}

	/**
	 * @return
	 */
	public String getLocationUnitId()
	{
		return locationUnitId;
	}

	/**
	 * @param string
	 */
	public void setFacilityTypeId(String string)
	{
		facilityTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setReasonNotDoneId(String reasonNotDoneId)
	{
		this.reasonNotDoneId = reasonNotDoneId;
		/*if( reasonNotDoneId != null && (reasonNotDoneId.trim().length() > 0) )
		{
			setReasonNotDone(CodeHelper.getCodeDescriptionByCode(CodeHelper.getCodes(
					"REASON_NOT_DONE", true), reasonNotDoneId));
			//change here too
		}*/
	}

	/**
	 * @param string
	 */
	public void setLengthOfStayId(String string)
	{
		lengthOfStayId = string;
	}

	/**
	 * @param string
	 */
	public void setLocationUnitId(String string)
	{
		locationUnitId = string;
	}

	/**
	 * @return
	 */
	public String getSequenceNum()
	{
		return sequenceNum;
	}

	/**
	 * @param string
	 */
	public void setSequenceNum(String string)
	{
		sequenceNum = string;
	}

	/**
	 * @return
	 */
	public String getCasefileId()
	{
		return casefileId;
	}

	/**
	 * @param string
	 */
	public void setCasefileId(String string)
	{
		casefileId = string;
	}

	/**
	 * @return
	 */
	public boolean isAdminister()
	{
		return administer;
	}

	/**
	 * @param b
	 */
	public void setAdminister(boolean b)
	{
		administer = b;
	}

	/**
	 * @return
	 */
	public String getProviderReferredTypeId()
	{
		return providerReferredTypeId;
	}

	/**
	 * @return
	 */
	public Collection getProviderReferredTypes()
	{
		if( providerReferredTypes == null || providerReferredTypes.size() < 1 )
		{
			providerReferredTypes = CodeHelper.getProviderTypeReferred();
		}

		return providerReferredTypes;
	}

	/**
	 * @param string
	 */
	public void setProviderReferredTypeId(String string)
	{
		providerReferredTypeId = string;
		providerReferredType = CodeHelper.getCodeDescriptionByCode(
				providerReferredTypes, providerReferredTypeId);
	}

	public String getProviderReferredType()
	{
		return providerReferredType;
	}

	/**
	 * @param collection
	 */
	public void setProviderReferredTypes(Collection collection)
	{
		providerReferredTypes = collection;
	}

	/**
	 * @return
	 */
	public String getSubsAssessmentReferral()
	{
		return subsAssessmentReferral;
	}

	/**
	 * @param b
	 */
	public void setSubsAssessmentReferral(String b)
	{
		subsAssessmentReferral = b;
	}

	/**
	 * @return Returns the maysAssesId.
	 */
	public String getMaysAssesId()
	{
		return maysAssesId;
	}

	/**
	 * @param maysAssesId
	 *          The maysAssesId to set.
	 */
	public void setMaysAssesId(String maysAssesId)
	{
		this.maysAssesId = maysAssesId;
	}

	/**
	 * @return Returns the assessmentComplete.
	 */
	public boolean isAssessmentComplete()
	{
		return assessmentComplete;
	}

	/**
	 * @param assessmentComplete
	 *          The assessmentComplete to set.
	 */
	public void setAssessmentComplete(boolean assessmentComplete)
	{
		this.assessmentComplete = assessmentComplete;
	}

	/**
	 * @return Returns the subReferral.
	 */
	public boolean isSubReferral()
	{
		return subReferral;
	}

	/**
	 * @param subReferral
	 *          The subReferral to set.
	 */
	public void setSubReferral(boolean subReferral)
	{
		this.subReferral = subReferral;
	}

	/**
	 * @return Returns the race.
	 */
	public String getRace()
	{
		return race;
	}

	/**
	 * @param race
	 *          The race to set.
	 */
	public void setRace(String race)
	{
		this.race = race;
	}

	/**
	 * @return Returns the raceId.
	 */
	public String getRaceId()
	{
		return raceId;
	}

	/**
	 * @param raceId
	 *          The raceId to set.
	 */
	public void setRaceId(String raceId)
	{
		this.raceId = raceId;
	}

	/**
	 * @return Returns the sex.
	 */
	public String getSex()
	{
		return sex;
	}

	/**
	 * @param sex
	 *          The sex to set.
	 */
	public void setSex(String sex)
	{
		this.sex = sex;
	}

	/**
	 * @return Returns the sexId.
	 */
	public String getSexId()
	{
		return sexId;
	}

	/**
	 * @param sexId
	 *          The sexId to set.
	 */
	public void setSexId(String sexId)
	{
		this.sexId = sexId;
	}

	/**
	 * @return Returns the detailRace.
	 */
	public String getDetailRace()
	{
		return detailRace;
	}

	/**
	 * @param detailRace
	 *          The detailRace to set.
	 */
	public void setDetailRace(String detailRace)
	{
		this.detailRace = detailRace;
	}

	/**
	 * @return Returns the detailRaceId.
	 */
	public String getDetailRaceId()
	{
		return detailRaceId;
	}

	/**
	 * @param detailRaceId
	 *          The detailRaceId to set.
	 */
	public void setDetailRaceId(String detailRaceId)
	{
		this.detailRaceId = detailRaceId;
	}

	/**
	 * @return Returns the detailSex.
	 */
	public String getDetailSex()
	{
		return detailSex;
	}

	/**
	 * @param detailSex
	 *          The detailSex to set.
	 */
	public void setDetailSex(String detailSex)
	{
		this.detailSex = detailSex;
	}

	/**
	 * @return Returns the detailSexId.
	 */
	public String getDetailSexId()
	{
		return detailSexId;
	}

	/**
	 * @param detailSexId
	 *          The detailSexId to set.
	 */
	public void setDetailSexId(String detailSexId)
	{
		this.detailSexId = detailSexId;
	}

	/**
	 * @return Returns the assessmentDate.
	 */
	public Date getAssessmentDate()
	{
		return assessmentDate;
	}

	/**
	 * @param assessmentDate
	 *          The assessmentDate to set.
	 */
	public void setAssessmentDate(Date assessmentDate)
	{
		this.assessmentDate = assessmentDate;
	}

	/**
	 * @return Returns the assessmentTime.
	 */
	public String getAssessmentTime()
	{
		return assessmentTime;
	}

	/**
	 * @param assessmentTime
	 *          The assessmentTime to set.
	 */
	public void setAssessmentTime(String assessmentTime)
	{
		this.assessmentTime = assessmentTime;
	}

	/**
	 * @return Returns the assessmentReviewTime.
	 */
	public String getAssessmentReviewTime()
	{
		return assessmentReviewTime;
	}

	/**
	 * @param assessmentReviewTime
	 *          The assessmentReviewTime to set.
	 */
	public void setAssessmentReviewTime(String assessmentReviewTime)
	{
		this.assessmentReviewTime = assessmentReviewTime;
	}

	/**
	 * @return Returns the testAge.
	 */
	public String getTestAge()
	{
		return testAge;
	}

	/**
	 * @param testAge
	 *          The testAge to set.
	 */
	public void setTestAge(String testAge)
	{
		this.testAge = testAge;
	}

	/**
	 * @return Returns the hasDetails.
	 */
	public boolean isHasDetails()
	{
		return hasDetails;
	}

	/**
	 * @param hasDetails
	 *          The hasDetails to set.
	 */
	public void setHasDetails(boolean hasDetails)
	{
		this.hasDetails = hasDetails;
	}

	/**
	 * @return Returns the hasSubAssessment.
	 */
	public boolean isHasSubAssessment()
	{
		return hasSubAssessment;
	}

	/**
	 * @param hasSubAssessment
	 *          The hasSubAssessment to set.
	 */
	public void setHasSubAssessment(boolean hasSubAssessment)
	{
		this.hasSubAssessment = hasSubAssessment;
	}

	/**
	 * @return Returns the alcoholDrugCaution.
	 */
	public boolean isAlcoholDrugCaution()
	{
		return alcoholDrugCaution;
	}

	/**
	 * @param alcoholDrugCaution
	 *          The alcoholDrugCaution to set.
	 */
	public void setAlcoholDrugCaution(boolean alcoholDrugCaution)
	{
		this.alcoholDrugCaution = alcoholDrugCaution;
	}

	/**
	 * @return Returns the alcoholDrugWarning.
	 */
	public boolean isAlcoholDrugWarning()
	{
		return alcoholDrugWarning;
	}

	/**
	 * @param alcoholDrugWarning
	 *          The alcoholDrugWarning to set.
	 */
	public void setAlcoholDrugWarning(boolean alcoholDrugWarning)
	{
		this.alcoholDrugWarning = alcoholDrugWarning;
	}

	/**
	 * @return Returns the angryIrritableCaution.
	 */
	public boolean isAngryIrritableCaution()
	{
		return angryIrritableCaution;
	}

	/**
	 * @param angryIrritableCaution
	 *          The angryIrritableCaution to set.
	 */
	public void setAngryIrritableCaution(boolean angryIrritableCaution)
	{
		this.angryIrritableCaution = angryIrritableCaution;
	}

	/**
	 * @return Returns the angryIrritableWarning.
	 */
	public boolean isAngryIrritableWarning()
	{
		return angryIrritableWarning;
	}

	/**
	 * @param angryIrritableWarning
	 *          The angryIrritableWarning to set.
	 */
	public void setAngryIrritableWarning(boolean angryIrritableWarning)
	{
		this.angryIrritableWarning = angryIrritableWarning;
	}

	/**
	 * @return Returns the depressionAnxietyCaution.
	 */
	public boolean isDepressionAnxietyCaution()
	{
		return depressionAnxietyCaution;
	}

	/**
	 * @param depressionAnxietyCaution
	 *          The depressionAnxietyCaution to set.
	 */
	public void setDepressionAnxietyCaution(boolean depressionAnxietyCaution)
	{
		this.depressionAnxietyCaution = depressionAnxietyCaution;
	}

	/**
	 * @return Returns the depressionAnxietyWarning.
	 */
	public boolean isDepressionAnxietyWarning()
	{
		return depressionAnxietyWarning;
	}

	/**
	 * @param depressionAnxietyWarning
	 *          The depressionAnxietyWarning to set.
	 */
	public void setDepressionAnxietyWarning(boolean depressionAnxietyWarning)
	{
		this.depressionAnxietyWarning = depressionAnxietyWarning;
	}

	/**
	 * @return Returns the somaticComplaintCaution.
	 */
	public boolean isSomaticComplaintCaution()
	{
		return somaticComplaintCaution;
	}

	/**
	 * @param somaticComplaintCaution
	 *          The somaticComplaintCaution to set.
	 */
	public void setSomaticComplaintCaution(boolean somaticComplaintCaution)
	{
		this.somaticComplaintCaution = somaticComplaintCaution;
	}

	/**
	 * @return Returns the somaticComplaintWarning.
	 */
	public boolean isSomaticComplaintWarning()
	{
		return somaticComplaintWarning;
	}

	/**
	 * @param somaticComplaintWarning
	 *          The somaticComplaintWarning to set.
	 */
	public void setSomaticComplaintWarning(boolean somaticComplaintWarning)
	{
		this.somaticComplaintWarning = somaticComplaintWarning;
	}

	/**
	 * @return Returns the thoughtDisturbanceCaution.
	 */
	public boolean isThoughtDisturbanceCaution()
	{
		return thoughtDisturbanceCaution;
	}

	/**
	 * @param thoughtDisturbanceCaution
	 *          The thoughtDisturbanceCaution to set.
	 */
	public void setThoughtDisturbanceCaution(boolean thoughtDisturbanceCaution)
	{
		this.thoughtDisturbanceCaution = thoughtDisturbanceCaution;
	}

	/**
	 * @return Returns the thoughtDisturbanceWarning.
	 */
	public boolean isThoughtDisturbanceWarning()
	{
		return thoughtDisturbanceWarning;
	}

	/**
	 * @param thoughtDisturbanceWarning
	 *          The thoughtDisturbanceWarning to set.
	 */
	public void setThoughtDisturbanceWarning(boolean thoughtDisturbanceWarning)
	{
		this.thoughtDisturbanceWarning = thoughtDisturbanceWarning;
	}

	/**
	 * @return Returns the action.
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @param action
	 *          The action to set.
	 */
	public void setAction(String action)
	{
		this.action = action;
	}

	/**
	 * @return Returns the isTask.
	 */
	public boolean isTask()
	{
		return isTask;
	}

	/**
	 * @param isTask
	 *          The isTask to set.
	 */
	public void setTask(boolean isTask)
	{
		this.isTask = isTask;
	}

	/**
	 * @return Returns the juvenileDOB.
	 */
	public Date getJuvenileDOB()
	{
		return juvenileDOB;
	}

	/**
	 * @param juvenileDOB
	 *          The juvenileDOB to set.
	 */
	public void setJuvenileDOB(Date juvenileDOB)
	{
		this.juvenileDOB = juvenileDOB;
	}

	/**
	 * @return assessmentId
	 */
	public String getAssessmentId()
	{
		return assessmentId;
	}

	/**
	 * @param assessmentId
	 */
	public void setAssessmentId(String assessmentId)
	{
		this.assessmentId = assessmentId;
	}

	/**
	 * @return maysiDetailId
	 */
	public String getMaysiDetailId()
	{
		return maysiDetailId;
	}

	/**
	 * @param maysiDetailId
	 */
	public void setMaysiDetailId(String maysiDetailId)
	{
		this.maysiDetailId = maysiDetailId;
	}

	/**
	 * @return subAssessId
	 */
	public String getSubAssessId()
	{
		return subAssessId;
	}

	/**
	 * @param subAssessId
	 */
	public void setSubAssessId(String subAssessId)
	{
		this.subAssessId = subAssessId;
	}

	

	/**
	 * @param hispanic
	 * //U.S 88526
	 */
	public void setHispanic(String hispanic)
	{
		this.hispanic = hispanic;
	}
	
	

	/**
	 * @return the hispanic
	 * //U.S 88526
	 */
	public String getHispanic()
	{
	    return hispanic;
	}

	/**
	 * @return ethnicity
	 */
	public String getEthnicity()
	{
		return CodeHelper.getFastCodeDescription(
				PDCodeTableConstants.JUVENILE_ETHNICITY, this.ethnicity);
	}

	/**
	 * @param ethnicity
	 */
	public void setEthnicity(String ethnicity)
	{
		this.ethnicity = ethnicity;
	}

	public String getEthnicityHispanic()
	{
		return ethnicityHispanic;
	}

	public void setEthnicityHispanic(String ethnicityHispanic)
	{
		this.ethnicityHispanic = ethnicityHispanic;
	}

	/**
	 * @return the suicideIdeationCaution
	 */
	public boolean isSuicideIdeationCaution() {
		return suicideIdeationCaution;
	}

	/**
	 * @param suicideIdeationCaution the suicideIdeationCaution to set
	 */
	public void setSuicideIdeationCaution(boolean suicideIdeationCaution) {
		this.suicideIdeationCaution = suicideIdeationCaution;
	}

	/**
	 * @return the suicideIdeationWarning
	 */
	public boolean isSuicideIdeationWarning() {
		return suicideIdeationWarning;
	}

	/**
	 * @param suicideIdeationWarning the suicideIdeationWarning to set
	 */
	public void setSuicideIdeationWarning(boolean suicideIdeationWarning) {
		this.suicideIdeationWarning = suicideIdeationWarning;
	}

	/**
	 * @return the traumaticExpressionCaution
	 */
	public boolean isTraumaticExpressionCaution() {
		return traumaticExpressionCaution;
	}

	/**
	 * @param traumaticExpressionCaution the traumaticExpressionCaution to set
	 */
	public void setTraumaticExpressionCaution(boolean traumaticExpressionCaution) {
		this.traumaticExpressionCaution = traumaticExpressionCaution;
	}

	/**
	 * @return the traumaticExpressionWarning
	 */
	public boolean isTraumaticExpressionWarning() {
		return traumaticExpressionWarning;
	}

	/**
	 * @param traumaticExpressionWarning the traumaticExpressionWarning to set
	 */
	public void setTraumaticExpressionWarning(boolean traumaticExpressionWarning) {
		this.traumaticExpressionWarning = traumaticExpressionWarning;
	}

	public String getOtherReasonNotDone()
	{
	    return otherReasonNotDone;
	}

	public void setOtherReasonNotDone(String otherReasonNotDone)
	{
	    this.otherReasonNotDone = otherReasonNotDone;
	}

	public String getUpdatedMaysiComments()
	{
	    return updatedMaysiComments;
	}

	public void setUpdatedMaysiComments(String updatedMaysiComments)
	{
	    this.updatedMaysiComments = updatedMaysiComments;
	}
	
	public String getJuvenileName()
	{
	    return this.juvenileName;
	}

	public void setJuvenileName(String juvName)
	{
	    this.juvenileName = juvName;
	}
	
	public String getJuvenileStatus()
	{
	    return this.juvenileStatus;
	}

	public void setJuvenileStatus(String juvStatus)
	{
	    this.juvenileStatus = juvStatus;
	}
	
	public int getJuvenileAge()
	{
	    return this.juvenileAge;
	}

	public void setJuvenileStatus(int juvAge)
	{
	    this.juvenileAge = juvAge;
	}
}
