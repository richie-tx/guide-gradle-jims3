/*
 * Created on Nov 28, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.casefile.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.interviewinfo.to.ExcludedTO;
import messaging.interviewinfo.to.FamilyInformationTO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.UIJuvenileLoadCodeTables;
import ui.juvenilecase.form.JuvenileFamilyForm;

/**
 * @author jjose To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CommonAppForm extends ActionForm
{
	private static Collection	emptyColl = new ArrayList();

	// Default Form variables
	private boolean	listsSet = false;
	private String	action = "";
	private String	secondaryAction = "";
	private boolean	update = false;
	private boolean	delete = false;
	private String	selectedValue = "";
	private String	juvenileNum = "";
	private boolean	exitReportFilled;
	private boolean	dsmDiagnosisFilled;
	private boolean	dsmDiagnosisExists;
	private boolean	placementHistoryExists;
	private boolean	financialHistoryExits;
	private boolean	courtOrderExists;

	private String	closingInfoId = "";
	private String prevClosingInfoId ="";
	private String casefileId=""; //#bug fix 35750 
	private String	reportType = "CAER";
	private String	selectedDisposition = "";
	private Collection	dispositions = emptyColl;
	private Collection	abuses = emptyColl;
	private Collection	schoolHistory = emptyColl;
	private JuvenileFamilyForm.Constellation	currentConstellation = null;
	private Collection	exitReportQuestions = null;
	private Collection	dsmResults = null;

	// drop down lists
	protected static Collection	recommendedCourtDispositionTypeList	= emptyColl;
	protected static Collection	abuseLevelList = emptyColl;
	
	public static Collection	informationBasisLevels = emptyColl;
	
	protected static Collection	relationshipToJuvList = emptyColl;
	protected static Collection	hearingTypeList = emptyColl;

	// Information for Guardian Financial Info for Printing Common App Report
	private boolean		  isEligibleForMedicaid = false;
	private boolean		  isReceivingMedicaid = false;
	private Collection	guardianFinancialInfo = emptyColl;

	// new tabs
	private CourtOrder	courtOrder = new CourtOrder();
	//changes for user story 11026
	private Placement placement = new Placement();
	private Collection	detentionFacilities = new ArrayList();
	private Collection	placementFacilities = new ArrayList();
	private Collection	juvenileCourts = new ArrayList();
	private Collection	currentOffenses = new ArrayList();
	private Collection	mostSeriousOffenses = new ArrayList();
	private Collection	numericTime = new ArrayList();
	private Collection	guardianList = new ArrayList();
	
	private Collection	controllingReferrals = new ArrayList() ;
	private String			selectedControllingReferral = "" ;
	private String			controllingReferralFromClosing = "" ;
	private String			offenseDescription = "" ;

	private String	selectedExitPlanId;
	private List		exitPlanList;
	private String	caseStatus = "";

	private boolean	fromCommonApp;
	private boolean casefilePostAdjCommOrRes = false ; // pre-adjudication, community or residential
	//Common App ER  11046 changes starts
	private String previousTab;
	private String previousReport="CAER";
	private String errorPage;
	private Collection previousExitReportQuestions = new ArrayList();;
	private Map cummulativeExitReportQuestions = new HashMap();
	//Common App ER  11046 changes ends

	/*
	 * 
	 */
	public boolean isFromCommonApp()
	{
		return fromCommonApp;
	}

	public void setFromCommonApp( boolean fromCommonApp )
	{
		this.fromCommonApp = fromCommonApp;
	}

	public CommonAppForm()
	{
		clear() ;
	}
	
	public void clear() 
	{
		/*
		 * Default Initialization
		 * The line that follows is done strictly to force loading of code tables
		 * for proper use during the common App form and need only be done once.
		 */

		emptyColl = new ArrayList() ;
		listsSet = false ;
		action = "" ;
		secondaryAction = "" ;
		update = false ;
		delete = false ;
		selectedValue = "" ;
		reportType="CAER"; //Common App ER  11046 changes
		UIJuvenileLoadCodeTables.getInstance().setCommonAppForm( this ) ;
		// Form specific initialization
		selectedDisposition = null ;
		dispositions = new ArrayList() ;
		abuses = new ArrayList() ;
		schoolHistory = new ArrayList() ;
		exitReportQuestions = null ;
		isEligibleForMedicaid = false ;
		isReceivingMedicaid = false ;
		guardianFinancialInfo = new ArrayList() ;
		mostSeriousOffenses = emptyColl ;
		currentOffenses = emptyColl ;
		detentionFacilities = emptyColl ;
		exitPlanList = new ArrayList() ;
		controllingReferrals = new ArrayList() ;
		selectedExitPlanId = "" ;
		casefilePostAdjCommOrRes = false ;
		selectedControllingReferral = "" ;
		controllingReferralFromClosing = "" ;
		offenseDescription = "" ;
	}

	// Will be called at every submit
	public void reset( ActionMapping aMapping, HttpServletRequest aRequest )
	{
		List listToReset = null;
		aMapping.getPath();
		Object obj = aRequest.getParameter( "clearDetentionFacilitiesCheckBoxes" );
		if( obj != null )
		{
			clearDetentionFacilitiesCheckBoxes( false );
		}

		if( (obj = aRequest.getParameter( "clearEmploymentCheckBoxes" )) != null )
		{
			String pagerOffset = (String)aRequest.getParameter( "pager.offset" );
			if( guardianList != null && ! guardianList.isEmpty() )
			{
				for( Iterator<FamilyInformationTO> iter = guardianList.iterator(); 
						iter.hasNext(); /*empty*/)
				{
					FamilyInformationTO to = iter.next();
					listToReset = to.getEmploymentHistory();
					// set excluded = true (means NOT included initially)
					resetList( listToReset, true, pagerOffset );
				}
			}
		}
	}

	/*
	 * 
	 */
	private void resetList( List list, boolean value, String pagerOffset )
	{
		if( list != null && ! list.isEmpty() )
		{
			list = currentlyViewedList( list, pagerOffset );
			for(Iterator<ExcludedTO> iter = list.iterator(); 
					iter.hasNext(); /*empty*/)
			{
				ExcludedTO excludedTO = iter.next();
				excludedTO.setExcluded( value );
			}
		}
	}

	/*
	 * 
	 */
	private List currentlyViewedList( List list, String pagerOffset )
	{
		if( pagerOffset != null && pagerOffset.length() > 0 )
		{
			try 
			{
				int from = Integer.parseInt( pagerOffset );
				int to = Integer.parseInt( pagerOffset ) + 10;
				if( to > list.size() )
				{
					to = list.size();
				}

				return list.subList( from, to );
			}
			catch( NumberFormatException nfe )
			{ /*empty*/
			}
		}
		
		return list;
	}

	/*
	 * 
	 */
	public void clearDetentionFacilitiesCheckBoxes( boolean val )
	{
		if( courtOrder != null && detentionFacilities != null )
		{
			Placement place = null;

			Iterator<Placement> iter = detentionFacilities.iterator();
			while(iter.hasNext())
			{
				place = iter.next();
				place.setStayed( val );
			}
		}
	}

	/*
	 * 
	 */
	public static class SchoolHistory
	{
		private String	schoolHistoryId;
		private String	gradeLevel;
		private String	gradeLevelId;
		private String	schoolDistrictId;
		private String	schoolDistrict;
		private String	schoolId;
		private String	school;
		private String	appropriateLevelId;
		private String	appropriateLevel;
		private String	exitTypeId;
		private String	exitType;
		private Date		lastAttendedDate;
		private String	TEASchoolNumber;
		private String	programAttending;
		private String	trauncyHistory;
		//Bug Fix : 11239 starts
		private String schoolAddress;
		private String schoolCity;
		private String schoolState;
		private String schoolZip;
		//Bug Fix : 11239 ends

		/**
		 * @return Returns the programAttending.
		 */
		public String getProgramAttending()
		{
			return programAttending;
		}

		/**
		 * @param programAttending
		 *          The programAttending to set.
		 */
		public void setProgramAttending( String programAttending )
		{
			this.programAttending = programAttending;
		}

		/**
		 * @return Returns the trauncyHistory.
		 */
		public String getTrauncyHistory()
		{
			return trauncyHistory;
		}

		/**
		 * @param trauncyHistory
		 *          The trauncyHistory to set.
		 */
		public void setTrauncyHistory( String trauncyHistory )
		{
			this.trauncyHistory = trauncyHistory;
		}

		/**
		 * @param string
		 */
		public void setAppropriateLevelId( String string )
		{
			appropriateLevelId = string;
		}

		/**
		 * @param string
		 */
		public void setExitTypeId( String string )
		{
			exitTypeId = string;
		}

		/**
		 * @param string
		 */
		public void setGradeLevelId( String string )
		{
			gradeLevelId = string;
		}

		/**
		 * @param string
		 */
		public void setSchoolDistrictId( String string )
		{
			schoolDistrictId = string;
		}

		/**
		 * @param string
		 */
		public void setSchoolHistoryId( String string )
		{
			schoolHistoryId = string;
		}

		/**
		 * @param string
		 */
		public void setSchoolId( String string )
		{
			schoolId = string;
		}

		/**
		 * @return
		 */
		public String getAppropriateLevel()
		{
			return appropriateLevel;
		}

		/**
		 * @return
		 */
		public String getAppropriateLevelId()
		{
			return appropriateLevelId;
		}

		/**
		 * @return
		 */
		public String getExitType()
		{
			return exitType;
		}

		/**
		 * @return
		 */
		public String getExitTypeId()
		{
			return exitTypeId;
		}

		/**
		 * @return
		 */
		public String getGradeLevel()
		{
			return gradeLevel;
		}

		/**
		 * @return
		 */
		public String getGradeLevelId()
		{
			return gradeLevelId;
		}

		/**
		 * @return
		 */
		public Date getLastAttendedDate()
		{
			return lastAttendedDate;
		}

		/**
		 * @return
		 */
		public String getSchool()
		{
			return school;
		}

		/**
		 * @return
		 */
		public String getSchoolDistrict()
		{
			return schoolDistrict;
		}

		/**
		 * @return
		 */
		public String getSchoolDistrictId()
		{
			return schoolDistrictId;
		}

		/**
		 * @return
		 */
		public String getSchoolHistoryId()
		{
			return schoolHistoryId;
		}

		/**
		 * @return
		 */
		public String getSchoolId()
		{
			return schoolId;
		}

		/**
		 * @return
		 */
		public String getTEASchoolNumber()
		{
			return TEASchoolNumber;
		}

		/**
		 * @param string
		 */
		public void setAppropriateLevel( String string )
		{
			appropriateLevel = string;
		}

		/**
		 * @param string
		 */
		public void setExitType( String string )
		{
			exitType = string;
		}

		/**
		 * @param string
		 */
		public void setGradeLevel( String string )
		{
			gradeLevel = string;
		}

		/**
		 * @param date
		 */
		public void setLastAttendedDate( Date date )
		{
			lastAttendedDate = date;
		}

		/**
		 * @param string
		 */
		public void setSchool( String string )
		{
			school = string;
		}

		/**
		 * @param string
		 */
		public void setSchoolDistrict( String string )
		{
			schoolDistrict = string;
		}

		/**
		 * @param string
		 */
		public void setTEASchoolNumber( String string )
		{
			TEASchoolNumber = string;
		}

		public String getSchoolAddress() {
			return schoolAddress;
		}

		public void setSchoolAddress(String schoolAddress) {
			this.schoolAddress = schoolAddress;
		}

		public String getSchoolCity() {
			return schoolCity;
		}

		public void setSchoolCity(String schoolCity) {
			this.schoolCity = schoolCity;
		}

		public String getSchoolState() {
			return schoolState;
		}

		public void setSchoolState(String schoolState) {
			this.schoolState = schoolState;
		}

		public String getSchoolZip() {
			return schoolZip;
		}

		public void setSchoolZip(String schoolZip) {
			this.schoolZip = schoolZip;
		}
		
		
		
		

	}// END Class SCHOOL HISTORY

	public static class Abuse
	{
		private String	abuseId;
		private Date		entryDate;
		private Name		perpetratorName;
		private String	relationshipToJuvenile;
		private String	relationshipToJuvenileId;
		private String	abuseLevel;
		private String	abuseLevelId;
		private String	traitType;
		private String	traitTypeId;
		private String	abuseTreatment;
		private String	informationBasis;
		private String	informationBasisId;

		public String getInformationBasis()
		{
			return informationBasis;
		}

		/**
		 * @param informationBasis
		 *          The informationBasis to set.
		 */
		public void setInformationBasis( String informationBasis )
		{
			this.informationBasis = informationBasis;
		}

		/**
		 * @return Returns the informationBasisId.
		 */
		public String getInformationBasisId()
		{

			return informationBasisId;
		}

		/**
		 * @param informationBasisId
		 *          The informationBasisId to set.
		 */
		public void setInformationBasisId( String aInformationBasisId )
		{
			this.informationBasisId = aInformationBasisId ;
			
			if( aInformationBasisId == null || aInformationBasisId.length() == 0 )
			{
				informationBasis = "" ;
			}
			else if( CommonAppForm.getInformationBasisLevels() != null && 
						! CommonAppForm.getInformationBasisLevels().isEmpty() )
			{
				this.informationBasis = CodeHelper.getCodeDescriptionByCode( 
						CommonAppForm.getInformationBasisLevels(), this.informationBasisId );
			}
		}

		/**
		 * @return
		 */
		public String getAbuseId()
		{
			return abuseId;
		}

		/**
		 * @return
		 */
		public String getAbuseLevel()
		{
			return abuseLevel;
		}

		/**
		 * @return
		 */
		public String getAbuseLevelId()
		{
			return abuseLevelId;
		}

		/**
		 * @return
		 */
		public String getAbuseTreatment()
		{
			return abuseTreatment;
		}

		/**
		 * @return
		 */
		public String getTraitType()
		{
			return traitType;
		}

		/**
		 * @return
		 */
		public String getTraitTypeId()
		{
			return traitTypeId;
		}

		/**
		 * @return
		 */
		public Date getEntryDate()
		{
			return entryDate;
		}

		/**
		 * @return
		 */
		public Name getPerpetratorName()
		{
			return perpetratorName;
		}

		/**
		 * @return
		 */
		public String getRelationshipToJuvenile()
		{
			return relationshipToJuvenile;
		}

		/**
		 * @return
		 */
		public String getRelationshipToJuvenileId()
		{
			return relationshipToJuvenileId;
		}

		/**
		 * @param string
		 */
		public void setAbuseId( String string )
		{
			abuseId = string;
		}

		/**
		 * @param string
		 */
		public void setAbuseLevel( String string )
		{
			abuseLevel = string;
		}

		/**
		 * @param string
		 */
		public void setAbuseLevelId( String string )
		{
			abuseLevelId = string;
			if( abuseLevelId == null || abuseLevelId.length() == 0 )
			{
				return;
			}

			if( CommonAppForm.abuseLevelList != null && 
					! CommonAppForm.abuseLevelList.isEmpty() )
			{
				abuseLevel = CodeHelper.getCodeDescriptionByCode( 
						CommonAppForm.abuseLevelList, abuseLevelId );
			}
		}

		/**
		 * @param string
		 */
		public void setAbuseTreatment( String string )
		{
			abuseTreatment = string;
		}

		/**
		 * @param string
		 */
		public void setTraitType( String string )
		{
			traitType = string;
		}

		/**
		 * @param string
		 */
		public void setTraitTypeId( String string )
		{
			traitTypeId = string;
		}

		/**
		 * @param date
		 */
		public void setEntryDate( Date date )
		{
			entryDate = date;
		}

		/**
		 * @param name
		 */
		public void setPerpetratorName( Name name )
		{
			perpetratorName = name;
		}

		/**
		 * @param string
		 */
		public void setRelationshipToJuvenile( String string )
		{
			relationshipToJuvenile = string;
		}

		/**
		 * @param string
		 */
		public void setRelationshipToJuvenileId( String string )
		{
			relationshipToJuvenileId = string;
			if( relationshipToJuvenileId == null || 
					relationshipToJuvenileId.length() == 0 )
			{
				return;
			}

			if( CommonAppForm.relationshipToJuvList != null && 
					! CommonAppForm.relationshipToJuvList.isEmpty() )
			{
				relationshipToJuvenile = CodeHelper.getCodeDescriptionByCode( 
						CommonAppForm.relationshipToJuvList, relationshipToJuvenileId );
			}
		}

	}// END CLASS ABUSE

	public static class Disposition
	{
		private String	dispositionNumber	= "";
		private String	petititionNumber	= "";
		private String	courtDate_Time		= "";
		private String	courtNumber				= "";
		private String	dispositionDate		= "";
		private String	hearingType				= "";
		private String	hearingTypeId			= "";
		private String	judgementDate			= "";

		public String getCourtDate_Time()
		{
			return courtDate_Time;
		}

		public String getCourtNumber()
		{
			return courtNumber;
		}

		public String getDispositionDate()
		{
			return dispositionDate;
		}

		public String getHearingType()
		{
			return hearingType;
		}

		public String getJudgementDate()
		{
			return judgementDate;
		}

		public String getPetititionNumber()
		{
			return petititionNumber;
		}

		public void setCourtDate_Time( String string )
		{
			courtDate_Time = string;
		}

		public void setCourtNumber( String string )
		{
			courtNumber = string;
		}

		public void setDispositionDate( String string )
		{
			dispositionDate = string;
		}

		public void setHearingType( String string )
		{
			hearingType = string;
		}

		public void setJudgementDate( String string )
		{
			judgementDate = string;
		}

		public void setPetititionNumber( String string )
		{
			petititionNumber = string;
		}

		public String getDispositionNumber()
		{
			return dispositionNumber;
		}

		public void setDispositionNumber( String string )
		{
			dispositionNumber = string;
		}

		/**
		 * @return
		 */
		public String getHearingTypeId()
		{
			return hearingTypeId;
		}

		/**
		 * @param string
		 */
		public void setHearingTypeId( String string )
		{
			hearingTypeId = string;
			if( hearingTypeId == null || hearingTypeId.length() == 0 )
			{
				return;
			}

			if( CommonAppForm.hearingTypeList != null && 
					! CommonAppForm.hearingTypeList.isEmpty() )
			{
				hearingType = CodeHelper.getCodeDescription( 
						CommonAppForm.hearingTypeList, hearingTypeId );
			}
		}

	}// END Disposition Class

	public String getAction()
	{
		return action;
	}

	public boolean isDelete()
	{
		return delete;
	}

	public Collection getDispositions()
	{
		return dispositions;
	}

	/**
	 * @return Returns the exitReportFilled.
	 */
	public boolean isExitReportFilled()
	{
		return exitReportFilled;
	}

	/**
	 * @param exitReportFilled
	 *          The exitReportFilled to set.
	 */
	public void setExitReportFilled( boolean exitReportFilled )
	{
		this.exitReportFilled = exitReportFilled;
	}

	/**
	 * @return
	 */

	public boolean isListsSet()
	{
		return listsSet;
	}

	public Collection getRecommendedCourtDispositionTypeList()
	{
		return recommendedCourtDispositionTypeList;
	}

	public String getSecondaryAction()
	{
		return secondaryAction;
	}

	public String getSelectedValue()
	{
		return selectedValue;
	}

	public boolean isUpdate()
	{
		return update;
	}

	public void setAction( String string )
	{
		action = string;
	}

	public void setDelete( boolean b )
	{
		delete = b;
	}

	public void setDispositions( Collection collection )
	{
		dispositions = collection;
	}

	public void setListsSet( boolean b )
	{
		listsSet = b;
	}

	public void setRecommendedCourtDispositionTypeList( Collection collection )
	{
		recommendedCourtDispositionTypeList = collection;
	}

	public void setSecondaryAction( String string )
	{
		secondaryAction = string;
	}

	public void setSelectedValue( String string )
	{
		selectedValue = string;
	}

	public void setUpdate( boolean b )
	{
		update = b;
	}

	public String getSelectedDisposition()
	{
		return selectedDisposition;
	}

	public void setSelectedDisposition( String disposition )
	{
		selectedDisposition = disposition;
	}

	/**
	 * @return Returns the courtOrderExists.
	 */
	public boolean isCourtOrderExists()
	{
		return courtOrderExists;
	}

	/**
	 * @param courtOrderExists
	 *          The courtOrderExists to set.
	 */
	public void setCourtOrderExists( boolean courtOrderExists )
	{
		this.courtOrderExists = courtOrderExists;
	}

	/**
	 * @return Returns the dsmDiagnosisFilled.
	 */
	public boolean isDsmDiagnosisFilled()
	{
		return dsmDiagnosisFilled;
	}

	/**
	 * @param dsmDiagnosisFilled
	 *          The dsmDiagnosisFilled to set.
	 */
	public void setDsmDiagnosisFilled( boolean dsmDiagnosisFilled )
	{
		this.dsmDiagnosisFilled = dsmDiagnosisFilled;
	}

	/**
	 * @return Returns the financialHistoryExits.
	 */
	public boolean isFinancialHistoryExits()
	{
		return financialHistoryExits;
	}

	/**
	 * @param financialHistoryExits
	 *          The financialHistoryExits to set.
	 */
	public void setFinancialHistoryExits( boolean financialHistoryExits )
	{
		this.financialHistoryExits = financialHistoryExits;
	}

	/**
	 * @return Returns the placementHistoryExists.
	 */
	public boolean isPlacementHistoryExists()
	{
		return placementHistoryExists;
	}

	/**
	 * @param placementHistoryExists
	 *          The placementHistoryExists to set.
	 */
	public void setPlacementHistoryExists( boolean placementHistoryExists )
	{
		this.placementHistoryExists = placementHistoryExists;
	}

	/**
	 * @return Returns the dsmDiagnosisExists.
	 */
	public boolean isDsmDiagnosisExists()
	{
		return dsmDiagnosisExists;
	}

	/**
	 * @param dsmDiagnosisExists
	 *          The dsmDiagnosisExists to set.
	 */
	public void setDsmDiagnosisExists( boolean dsmDiagnosisExists )
	{
		this.dsmDiagnosisExists = dsmDiagnosisExists;
	}

	/**
	 * @return
	 */
	public Collection getExitReportQuestions()
	{
		return exitReportQuestions;
	}

	/**
	 * @param collection
	 */
	public void setExitReportQuestions( Collection collection )
	{
		exitReportQuestions = collection;
	}

	/**
	 * @return Returns the dsmResults.
	 */
	public Collection getDsmResults()
	{
		return dsmResults;
	}

	/**
	 * @param dsmResults
	 *          The dsmResults to set.
	 */
	public void setDsmResults( Collection dsmResults )
	{
		this.dsmResults = dsmResults;
	}

	/**
	 * @return
	 */
	public static Collection getAbuseLevelList()
	{
		return abuseLevelList;
	}

	/**
	 * @return
	 */
	public static Collection getRelationshipToJuvList()
	{
		return relationshipToJuvList;
	}

	/**
	 * @param collection
	 */
	public static void setAbuseLevelList( Collection collection )
	{
		abuseLevelList = collection;
	}

	/**
	 * @param collection
	 */
	public static void setRelationshipToJuvList( Collection collection )
	{
		relationshipToJuvList = collection;
	}

	/**
	 * @return
	 */
	public Collection getAbuses()
	{
		return abuses;
	}

	/**
	 * @return
	 */
	public JuvenileFamilyForm.Constellation getCurrentConstellation()
	{
		return currentConstellation;
	}

	/**
	 * @param collection
	 */
	public void setAbuses( Collection collection )
	{
		abuses = collection;
	}

	/**
	 * @param constellation
	 */
	public void setCurrentConstellation( JuvenileFamilyForm.Constellation constellation )
	{
		currentConstellation = constellation;
	}

	/**
	 * @return
	 */
	public Collection getSchoolHistory()
	{
		return schoolHistory;
	}

	/**
	 * @param collection
	 */
	public void setSchoolHistory( Collection collection )
	{
		schoolHistory = collection;
	}

	/**
	 * @return
	 */
	public String getReportType()
	{
		return reportType;
	}

	/**
	 * @param string
	 */
	public void setReportType( String string )
	{
		reportType = string;
	}

	/**
	 * @return
	 */
	public static Collection getHearingTypeList()
	{
		return hearingTypeList;
	}

	/**
	 * @param collection
	 */
	public static void setHearingTypeList( Collection collection )
	{
		hearingTypeList = collection;
	}

	/**
	 * @return
	 */
	public String getClosingInfoId()
	{
		return closingInfoId;
	}

	/**
	 * @param string
	 */
	public void setClosingInfoId( String string )
	{
		closingInfoId = string;
	}

	/**
	 * @return Returns the isEligibleForMedicaid.
	 */
	public boolean isEligibleForMedicaid()
	{
		return isEligibleForMedicaid;
	}

	/**
	 * @return Returns the isReceivingMedicaid.
	 */
	public boolean isReceivingMedicaid()
	{
		return isReceivingMedicaid;
	}

	/**
	 * @param isEligibleForMedicaid
	 *          The isEligibleForMedicaid to set.
	 */
	public void setEligibleForMedicaid( boolean isEligibleForMedicaid )
	{
		this.isEligibleForMedicaid = isEligibleForMedicaid;
	}

	/**
	 * @param isReceivingMedicaid
	 *          The isReceivingMedicaid to set.
	 */
	public void setReceivingMedicaid( boolean isReceivingMedicaid )
	{
		this.isReceivingMedicaid = isReceivingMedicaid;
	}

	public static class GuardianFinancialInformation
	{
		private String	firstName;
		private String	middleName;
		private String	lastName;
		private boolean	isDisabled;
		private String	occupation;
		private String	employerName;
		private String	salary;
		private String	salaryRate;
		private int			employerAddressId;
		private double	otherIncomeAmount;
		private double	annualNetIncome;
		private double	foodStamps;
		private double	intangibleProperty;
		private double	monthlyLifeInsurancePremium;
		private double	propertyValue;
		private double	tanf;
		private double	ssi;
		private String	healthInsuranceTypeCD;

		/**
		 * @return Returns the annualNetIncome.
		 */
		public double getAnnualNetIncome()
		{
			return annualNetIncome;
		}

		/**
		 * @return Returns the employerAddressId.
		 */
		public int getEmployerAddressId()
		{
			return employerAddressId;
		}

		/**
		 * @return Returns the employerName.
		 */
		public String getEmployerName()
		{
			return employerName;
		}

		/**
		 * @return Returns the firstName.
		 */
		public String getFirstName()
		{
			return firstName;
		}

		/**
		 * @return Returns the foodStamps.
		 */
		public double isFoodStamps()
		{
			return foodStamps;
		}

		/**
		 * @return Returns the healthInsuranceTypeCD.
		 */
		public String getHealthInsuranceTypeCD()
		{
			return healthInsuranceTypeCD;
		}

		/**
		 * @return Returns the intangibleProperty.
		 */
		public double getIntangibleProperty()
		{
			return intangibleProperty;
		}

		/**
		 * @return Returns the isDisabled.
		 */
		public boolean isDisabled()
		{
			return isDisabled;
		}

		/**
		 * @return Returns the lastName.
		 */
		public String getLastName()
		{
			return lastName;
		}

		/**
		 * @return Returns the middleName.
		 */
		public String getMiddleName()
		{
			return middleName;
		}

		/**
		 * @return Returns the monthlyLifeInsurancePremium.
		 */
		public double getMonthlyLifeInsurancePremium()
		{
			return monthlyLifeInsurancePremium;
		}

		/**
		 * @return Returns the occupation.
		 */
		public String getOccupation()
		{
			return occupation;
		}

		/**
		 * @return Returns the otherIncomeAmount.
		 */
		public double getOtherIncomeAmount()
		{
			return otherIncomeAmount;
		}

		/**
		 * @return Returns the propertyValue.
		 */
		public double getPropertyValue()
		{
			return propertyValue;
		}

		/**
		 * @return Returns the salary.
		 */
		public String getSalary()
		{
			return salary;
		}

		/**
		 * @return Returns the salaryRate.
		 */
		public String getSalaryRate()
		{
			return salaryRate;
		}

		/**
		 * @return Returns the tanf.
		 */
		public double getTanf()
		{
			return tanf;
		}

		/**
		 * @param annualNetIncome
		 *          The annualNetIncome to set.
		 */
		public void setAnnualNetIncome( double annualNetIncome )
		{
			this.annualNetIncome = annualNetIncome;
		}

		/**
		 * @param employerAddressId
		 *          The employerAddressId to set.
		 */
		public void setEmployerAddressId( int employerAddressId )
		{
			this.employerAddressId = employerAddressId;
		}

		/**
		 * @param employerName
		 *          The employerName to set.
		 */
		public void setEmployerName( String employerName )
		{
			this.employerName = employerName;
		}

		/**
		 * @param firstName
		 *          The firstName to set.
		 */
		public void setFirstName( String firstName )
		{
			this.firstName = firstName;
		}

		/**
		 * @param foodStamps
		 *          The foodStamps to set.
		 */
		public void setFoodStamps( double foodStamps )
		{
			this.foodStamps = foodStamps;
		}

		/**
		 * @param healthInsuranceTypeCD
		 *          The healthInsuranceTypeCD to set.
		 */
		public void setHealthInsuranceTypeCD( String healthInsuranceTypeCD )
		{
			this.healthInsuranceTypeCD = healthInsuranceTypeCD;
		}

		/**
		 * @param intangibleProperty
		 *          The intangibleProperty to set.
		 */
		public void setIntangibleProperty( double intangibleProperty )
		{
			this.intangibleProperty = intangibleProperty;
		}

		/**
		 * @param isDisabled
		 *          The isDisabled to set.
		 */
		public void setDisabled( boolean isDisabled )
		{
			this.isDisabled = isDisabled;
		}

		/**
		 * @param lastName
		 *          The lastName to set.
		 */
		public void setLastName( String lastName )
		{
			this.lastName = lastName;
		}

		/**
		 * @param middleName
		 *          The middleName to set.
		 */
		public void setMiddleName( String middleName )
		{
			this.middleName = middleName;
		}

		/**
		 * @param monthlyLifeInsurancePremium
		 *          The monthlyLifeInsurancePremium to set.
		 */
		public void setMonthlyLifeInsurancePremium( double monthlyLifeInsurancePremium )
		{
			this.monthlyLifeInsurancePremium = monthlyLifeInsurancePremium;
		}

		/**
		 * @param occupation
		 *          The occupation to set.
		 */
		public void setOccupation( String occupation )
		{
			this.occupation = occupation;
		}

		/**
		 * @param otherIncomeAmount
		 *          The otherIncomeAmount to set.
		 */
		public void setOtherIncomeAmount( double otherIncomeAmount )
		{
			this.otherIncomeAmount = otherIncomeAmount;
		}

		/**
		 * @param propertyValue
		 *          The propertyValue to set.
		 */
		public void setPropertyValue( double propertyValue )
		{
			this.propertyValue = propertyValue;
		}

		/**
		 * @param salary
		 *          The salary to set.
		 */
		public void setSalary( String salary )
		{
			this.salary = salary;
		}

		/**
		 * @param salaryRate
		 *          The salaryRate to set.
		 */
		public void setSalaryRate( String salaryRate )
		{
			this.salaryRate = salaryRate;
		}

		/**
		 * @param tanf
		 *          The tanf to set.
		 */
		public void setTanf( double tanf )
		{
			this.tanf = tanf;
		}

		/**
		 * @return Returns the ssi.
		 */
		public double getSsi()
		{
			return ssi;
		}

		/**
		 * @param ssi
		 *          The ssi to set.
		 */
		public void setSsi( double ssi )
		{
			this.ssi = ssi;
		}
	}

	/**
	 * @return Returns the guardianFinancialInfo.
	 */
	public Collection getGuardianFinancialInfo()
	{
		return guardianFinancialInfo;
	}

	/**
	 * @param guardianFinancialInfo
	 *          The guardianFinancialInfo to set.
	 */
	public void setGuardianFinancialInfo( Collection guardianFinancialInfo )
	{
		this.guardianFinancialInfo = guardianFinancialInfo;
	}

	/**
	 * @return Returns the informationBasisLevels.
	 */
	public static Collection getInformationBasisLevels()
	{
		return informationBasisLevels;
	}

	/**
	 * @param informationBasisLevels
	 *          The informationBasisLevels to set.
	 */
	public static void setInformationBasisLevels( Collection informationBasisLevels )
	{
		CommonAppForm.informationBasisLevels = informationBasisLevels;
	}

	/**
	 * @return Returns the courtOrder.
	 */
	public CourtOrder getCourtOrder()
	{
		return courtOrder;
	}

	/**
	 * @param courtOrder
	 *          The courtOrder to set.
	 */
	public void setCourtOrder( CourtOrder courtOrder )
	{
		this.courtOrder = courtOrder;
	}

	/**
	 * @return Returns the detentionFacilities.
	 */
	public Collection getDetentionFacilities()
	{
		return detentionFacilities;
	}

	/**
	 * @param detentionFacilities
	 *          The detentionFacilities to set.
	 */
	public void setDetentionFacilities( Collection detentionFacilities )
	{
		this.detentionFacilities = detentionFacilities;
	}

	/**
	 * @return Returns the placementFacilities.
	 */
	public Collection getPlacementFacilities()
	{
		return placementFacilities;
	}

	/**
	 * @param placementFacilities
	 *          The placementFacilities to set.
	 */
	public void setPlacementFacilities( Collection placementFacilities )
	{
		this.placementFacilities = placementFacilities;
	}

	/**
	 * @return Returns the juvenileCourts.
	 */
	public Collection getJuvenileCourts()
	{
		return juvenileCourts;
	}

	/**
	 * @param juvenileCourts
	 *          The juvenileCourts to set.
	 */
	public void setJuvenileCourts( Collection juvenileCourts )
	{
		this.juvenileCourts = juvenileCourts;
	}

	/**
	 * @return Returns the mostSeriousOffenses.
	 */
	public Collection getMostSeriousOffenses()
	{
		return mostSeriousOffenses;
	}

	/**
	 * @param mostSeriousOffenses
	 *          The mostSeriousOffenses to set.
	 */
	public void setMostSeriousOffenses( Collection mostSeriousOffenses )
	{
		this.mostSeriousOffenses = mostSeriousOffenses;
	}

	/**
	 * @return Returns the numericTime.
	 */
	public Collection getNumericTime()
	{
		return numericTime;
	}

	/**
	 * @param numericTime
	 *          The numericTime to set.
	 */
	public void setNumericTime( Collection numericTime )
	{
		this.numericTime = numericTime;
	}

	/**
	 * @return Returns the currentOffenses.
	 */
	public Collection getCurrentOffenses()
	{
		return currentOffenses;
	}

	/**
	 * @param currentOffenses
	 *          The currentOffenses to set.
	 */
	public void setCurrentOffenses( Collection currentOffenses )
	{
		this.currentOffenses = currentOffenses;
	}

	/*
	 * 
	 */
	public static class CourtOrder
	{
		private String			commitmentDate;
		private String			courtNameId							= "";
		private String			courtName								= "";
		private String			judgeName								= "";
		private String			causeNumber							= "";
		private String			prosecutingAttorneyName	= "";
		private String			typeOfCommitmentId			= "";
		private String			typeOfCommitment				= "";
		private boolean			probationFailure;
		private String			mostSeriousOffenseId		= "";
		private String			mostSeriousOffense			= "";
		private String			failureReason						= "";
		private String			offenseCode							= "";

		private String			categoryDescription			= "";
		
		private String			currentOffenseId				= "";
		private String			currentOffense					= "";
		private String			offenseDescription			= "" ;
		private String			weaponUsedId						= "";
		private String			weaponUsed							= "";
		private String 			weaponType						=""; //added for user-story11449
		private String 			weaponTypeId					=""; //added for user-story11449
		private String			gangRelated							= "";
		private String			offenseLevelId					= "";
		private String			offenseLevel						= "";
		private String			selectedOffense = "" ;
		private boolean			determinateSentence;
		private String			timeStr									= "";
		private String			priorTYCCommitmentDate;
		private String[]		locationId;
		private String			timeNumericId						= "";
		private String			detentionPeriodId				= "";
		private String			detentionTime						= "";

		private Collection	selectedFacilities			= new ArrayList();
		private Collection	selectedPlacement				= new ArrayList();
		
		//user-story 11030
		private String priorTJJDCommitmentDate="";

		/**
		 * @return Returns the causeNumber.
		 */

		public void clearCourtOrder()
		{
			this.weaponUsed = "";
			this.weaponUsedId = "";
			this.mostSeriousOffenseId = "";
			this.typeOfCommitmentId = "";
			this.courtNameId = "";
			this.causeNumber = "";
			this.prosecutingAttorneyName = "";
			this.probationFailure = false;
			this.currentOffenseId = "";
			this.offenseDescription = "" ;
			this.offenseLevelId = "";
			this.determinateSentence = false;
			this.timeNumericId = "";
			this.detentionPeriodId = "";
			this.timeStr = "";
			this.courtName = "";
			this.judgeName = "";
			this.selectedFacilities = new ArrayList();
			this.selectedPlacement = new ArrayList();
			this.categoryDescription= "";
			this.selectedOffense = "" ;
			this.priorTJJDCommitmentDate="";
			this.commitmentDate="";
		}

		public String getCauseNumber()
		{
			return causeNumber;
		}

		/**
		 * @param causeNumber
		 *          The causeNumber to set.
		 */
		public void setCauseNumber( String causeNumber )
		{
			this.causeNumber = causeNumber;
		}

		/**
		 * @return Returns the courtNameId.
		 */
		public String getCourtNameId()
		{
			return courtNameId;
		}

		/**
		 * @param courtNameId
		 *          The courtNameId to set.
		 */
		public void setCourtNameId( String courtNameId )
		{
			this.courtNameId = courtNameId;
		}

		/**
		 * @return Returns the courtName.
		 */
		public String getCourtName()
		{
			return courtName;
		}

		/**
		 * @param courtName
		 *          The courtName to set.
		 */
		public void setCourtName( String courtName )
		{
			this.courtName = courtName;
		}

		/**
		 * @return Returns the currentOffenseId.
		 */
		public String getCurrentOffenseId()
		{
			return currentOffenseId;
		}

		/**
		 * @param currentOffenseId
		 *          The currentOffenseId to set.
		 */
		public void setCurrentOffenseId( String currentOffenseId )
		{
			this.currentOffenseId = currentOffenseId;
		}

		/**
		 * @return Returns the currentOffense.
		 */
		public String getCurrentOffense()
		{
			return currentOffense;
		}

		/**
		 * @param currentOffense
		 *          The currentOffense to set.
		 */
		public void setCurrentOffense( String currentOffense )
		{
			this.currentOffense = currentOffense;
		}

		/**
		 * @return Returns the commitmentDate.
		 */
		public String getCommitmentDate()
		{
			return commitmentDate;
		}

		/**
		 * @param commitmentDate
		 *          The commitmentDate to set.
		 */
		public void setCommitmentDate( String commitmentDate )
		{
			this.commitmentDate = commitmentDate;
		}

		/**
		 * @return Returns the detentionPeriodId.
		 */
		public String getDetentionPeriodId()
		{
			return detentionPeriodId;
		}

		/**
		 * @param detentionPeriodId
		 *          The detentionPeriodId to set.
		 */
		public void setDetentionPeriodId( String detentionPeriodId )
		{
			this.detentionPeriodId = detentionPeriodId;
			
			if( detentionPeriodId != null && detentionPeriodId.length() > 0 )
			{
				detentionTime = SimpleCodeTableHelper.getDescrByCode( 
						"DETENTION_PERIOD", detentionPeriodId );
			}
		}

		/**
		 * @return Returns the detentionTime.
		 */
		public String getDetentionTime()
		{
			return detentionTime;
		}

		/**
		 * @param detentionTime
		 *          The detentionTime to set.
		 */
		public void setDetentionTime( String detentionTime )
		{
			this.detentionTime = detentionTime;
		}

		/**
		 * @return Returns the determinateSentence.
		 */
		public boolean isDeterminateSentence()
		{
			return determinateSentence;
		}

		/**
		 * @param determinateSentence
		 *          The determinateSentence to set.
		 */
		public void setDeterminateSentence( boolean determinateSentence )
		{
			this.determinateSentence = determinateSentence;
		}

		/**
		 * @return Returns the priorTYCCommitmentDate.
		 */
		public String getPriorTYCCommitmentDate()
		{
			return priorTYCCommitmentDate;
		}

		/**
		 * @param priorTYCCommitmentDate
		 *          The priorTYCCommitmentDate to set.
		 */
		public void setPriorTYCCommitmentDate( String priorTYCCommitmentDate )
		{
			this.priorTYCCommitmentDate = priorTYCCommitmentDate;
		}

		/**
		 * @return Returns the failureReason.
		 */
		public String getFailureReason()
		{
			return failureReason;
		}

		/**
		 * @param failureReason
		 *          The failureReason to set.
		 */
		public void setFailureReason( String failureReason )
		{
			this.failureReason = failureReason;
		}

		/**
		 * @return Returns the gangRelated.
		 */
		public String getGangRelated()
		{
			return gangRelated;
		}

		/**
		 * @param gangRelated
		 *          The gangRelated to set.
		 */
		public void setGangRelated( String gangRelated )
		{
			this.gangRelated = gangRelated;
		}

		/**
		 * @return Returns the judgeName.
		 */
		public String getJudgeName()
		{
			return judgeName;
		}

		/**
		 * @param judgeName
		 *          The judgeName to set.
		 */
		public void setJudgeName( String judgeName )
		{
			this.judgeName = judgeName;
		}

		/**
		 * @return Returns the locationId.
		 */
		public String[] getLocationId()
		{
			return locationId;
		}

		/**
		 * @param locationId
		 *          The locationId to set.
		 */
		public void setLocationId( String[] locationId )
		{
			this.locationId = locationId;
		}

		/**
		 * @return Returns the mostSeriousOffenseId.
		 */
		public String getMostSeriousOffenseId()
		{
			return mostSeriousOffenseId;
		}

		/**
		 * @param mostSeriousOffenseId
		 *          The mostSeriousOffenseId to set.
		 */
		public void setMostSeriousOffenseId( String mostSeriousOffenseId )
		{
			this.mostSeriousOffenseId = mostSeriousOffenseId;
		}

		/**
		 * @return Returns the mostSeriousOffense.
		 */
		public String getMostSeriousOffense()
		{
			return mostSeriousOffense;
		}

		/**
		 * @param mostSeriousOffense
		 *          The mostSeriousOffense to set.
		 */
		public void setMostSeriousOffense( String mostSeriousOffense )
		{
			this.mostSeriousOffense = mostSeriousOffense;
		}

		/**
		 * @return Returns the offenseCode.
		 */
		public String getOffenseCode()
		{
			return offenseCode;
		}

		/**
		 * @param offenseCode
		 *          The offenseCode to set.
		 */
		public void setOffenseCode( String offenseCode )
		{
			this.offenseCode = offenseCode;
		}

		/**
		 * @return Returns the offenseLevelId.
		 */
		public String getOffenseLevelId()
		{
			return offenseLevelId;
		}

		/**
		 * @param offenseLevelId
		 *          The offenseLevelId to set.
		 */
		public void setOffenseLevelId( String offenseLevelId )
		{
			this.offenseLevelId = offenseLevelId;
		}

		/**
		 * @return Returns the offenseLevel.
		 */
		public String getOffenseLevel()
		{
			return offenseLevel;
		}

		/**
		 * @param offenseLevel
		 *          The offenseLevel to set.
		 */
		public void setOffenseLevel( String offenseLevel )
		{
			this.offenseLevel = offenseLevel;
		}

		/**
		 * @return Returns the probationFailure.
		 */
		public boolean isProbationFailure()
		{
			return probationFailure;
		}

		/**
		 * @param probationFailure
		 *          The probationFailure to set.
		 */
		public void setProbationFailure( boolean probationFailure )
		{
			this.probationFailure = probationFailure;
		}

		/**
		 * @return Returns the prosecutingAttorneyName.
		 */
		public String getProsecutingAttorneyName()
		{
			return prosecutingAttorneyName;
		}

		/**
		 * @param prosecutingAttorneyName
		 *          The prosecutingAttorneyName to set.
		 */
		public void setProsecutingAttorneyName( String prosecutingAttorneyName )
		{
			this.prosecutingAttorneyName = prosecutingAttorneyName;
		}

		/**
		 * @return Returns the selectedFacilities.
		 */
		public Collection getSelectedFacilities()
		{
			return selectedFacilities;
		}

		/**
		 * @param selectedFacilities
		 *          The selectedFacilities to set.
		 */
		public void setSelectedFacilities( Collection selectedFacilities )
		{
			this.selectedFacilities = selectedFacilities;
		}

		/**
		 * @return Returns the timeStr.
		 */
		public String getTimeStr()
		{
			return timeStr;
		}

		/**
		 * @param timeStr
		 *          The timeStr to set.
		 */
		public void setTimeStr( String timeStr )
		{
			this.timeStr = timeStr;
		}

		/**
		 * @return Returns the typeOfCommitment.
		 */
		public String getTypeOfCommitment()
		{
			return typeOfCommitment;
		}

		/**
		 * @param typeOfCommitment
		 *          The typeOfCommitment to set.
		 */
		public void setTypeOfCommitment( String typeOfCommitment )
		{
			this.typeOfCommitment = typeOfCommitment;
		}

		/**
		 * @return Returns the typeOfCommitmentId.
		 */
		public String getTypeOfCommitmentId()
		{
			return typeOfCommitmentId;
		}

		/**
		 * @param typeOfCommitmentId
		 *          The typeOfCommitmentId to set.
		 */
		public void setTypeOfCommitmentId( String typeOfCommitmentId )
		{
			this.typeOfCommitmentId = typeOfCommitmentId;
			
			if( typeOfCommitmentId != null && typeOfCommitmentId.length() > 0 )
			{
				typeOfCommitment = SimpleCodeTableHelper.getDescrByCode( 
						"TYPE_OF_COMMITMENT", typeOfCommitmentId );
			}
		}

		/**
		 * @return Returns the weaponUsedId.
		 */
		public String getWeaponUsedId()
		{
			return weaponUsedId;
		}

		/**
		 * @return Returns the weaponUsed.
		 */
		public String getWeaponUsed()
		{
			return weaponUsed;
		}

		/**
		 * @param weaponUsed
		 *          The weaponUsed to set.
		 */
		public void setWeaponUsed( String weaponUsed )
		{
			this.weaponUsed = weaponUsed;
		}

		/**
		 * @param weaponUsedId
		 *          The weaponUsedId to set.
		 */
		public void setWeaponUsedId( String weaponUsedId )
		{
			this.weaponUsedId = weaponUsedId;
			if( weaponUsedId != null && weaponUsedId.length() > 0 )
			{
				weaponUsed = SimpleCodeTableHelper.getDescrByCode( "WEAPON_USED", weaponUsedId );
			}
		}

		/**
		 * @return Returns the timeNumericId.
		 */
		public String getTimeNumericId()
		{
			return timeNumericId;
		}

		/**
		 * @param timeNumericId
		 *          The timeNumericId to set.
		 */
		public void setTimeNumericId( String timeNumericId )
		{
			this.timeNumericId = timeNumericId;
		}

		/**
		 * @return Returns the selectedPlacement.
		 */
		public Collection getSelectedPlacement()
		{
			return selectedPlacement;
		}

		/**
		 * @param selectedPlacement
		 *          The selectedPlacement to set.
		 */
		public void setSelectedPlacement( Collection selectedPlacement )
		{
			this.selectedPlacement = selectedPlacement;
		}

		public String getOffenseDescription()
		{
			return offenseDescription;
		}

		public void setOffenseDescription( String offenseDescription )
		{
			this.offenseDescription = offenseDescription;
		}

		public String getCategoryDescription()
		{
			return categoryDescription;
		}

		public void setCategoryDescription( String categoryDescription )
		{
			this.categoryDescription = categoryDescription;
		}

		public String getSelectedOffense()
		{
			return selectedOffense;
		}

		public void setSelectedOffense( String selectedOffense )
		{
			this.selectedOffense = selectedOffense;
		}

		/**
		 * @param priorTJJDCommitmentDate the priorTJJDCommitmentDate to set
		 */
		public void setPriorTJJDCommitmentDate(String priorTJJDCommitmentDate) {
			this.priorTJJDCommitmentDate = priorTJJDCommitmentDate;
		}

		/**
		 * @return the priorTJJDCommitmentDate
		 */
		public String getPriorTJJDCommitmentDate() {
			return priorTJJDCommitmentDate;
		}

		public String getWeaponType() {
			return weaponType;
		}

		public void setWeaponType(String weaponType) {
			this.weaponType = weaponType;
		}

		public String getWeaponTypeId() {
			return weaponTypeId;
		}

		public void setWeaponTypeId(String weaponTypeId) {
			this.weaponTypeId = weaponTypeId;
		}
	}

	/*
	 * 
	 */
	public static class Placement
	{
		private String admitReason = "";
		private String facilityName	= "";
		private String referralNumber = "";
		private String admitDate;
		private String admitTime = "";
		private String releaseDate = "";
		private String releaseTime;
		private String totalTime = "";
		private long detTime;
		private boolean	stayed;
		private boolean	continuedStay;
		//changes for user story 11026
		private String outcome;
		private String recentLevelOfCare;
		private String recentReasonForDischarge;

		private JuvenileFacilityResponseEvent	facilityInfo;

		/**
		 * @return Returns the admitReason.
		 */
		public String getAdmitReason()
		{
			return admitReason;
		}

		/**
		 * @param admitReason
		 *          The admitReason to set.
		 */
		public void setAdmitReason( String admitReason )
		{
			this.admitReason = admitReason;
		}

		/**
		 * @return Returns the facilityName.
		 */
		public String getFacilityName()
		{
			return facilityName;
		}

		/**
		 * @param facilityName
		 *          The facilityName to set.
		 */
		public void setFacilityName( String facilityName )
		{
			this.facilityName = facilityName;
		}

		/**
		 * @return Returns the admitDate.
		 */
		public String getAdmitDate()
		{
			return admitDate;
		}

		/**
		 * @param admitDate
		 *          The admitDate to set.
		 */
		public void setAdmitDate( String admitDate )
		{
			this.admitDate = admitDate;
		}

		/**
		 * @return Returns the admitTime.
		 */
		public String getAdmitTime()
		{
			return admitTime;
		}

		/**
		 * @param admitTime
		 *          The admitTime to set.
		 */
		public void setAdmitTime( String admitTime )
		{
			this.admitTime = admitTime;
		}

		/**
		 * @return Returns the referralNumber.
		 */
		public String getReferralNumber()
		{
			return referralNumber;
		}

		/**
		 * @param referralNumber
		 *          The referralNumber to set.
		 */
		public void setReferralNumber( String referralNumber )
		{
			this.referralNumber = referralNumber;
		}

		/**
		 * @return Returns the releaseDate.
		 */
		public String getReleaseDate()
		{
			return releaseDate;
		}

		/**
		 * @param releaseDate
		 *          The releaseDate to set.
		 */
		public void setReleaseDate( String releaseDate )
		{
			this.releaseDate = releaseDate;
		}

		/**
		 * @return Returns the releaseTime.
		 */
		public String getReleaseTime()
		{
			return releaseTime;
		}

		/**
		 * @param releaseTime
		 *          The releaseTime to set.
		 */
		public void setReleaseTime( String releaseTime )
		{
			this.releaseTime = releaseTime;
		}

		/**
		 * @return Returns the totalTime.
		 */
		public String getTotalTime()
		{
			return totalTime;
		}

		/**
		 * @param totalTime
		 *          The totalTime to set.
		 */
		public void setTotalTime( String totalTime )
		{
			this.totalTime = totalTime;
		}

		/**
		 * @return Returns the stayed.
		 */
		public boolean isStayed()
		{
			return stayed;
		}

		/**
		 * @param stayed
		 *          The stayed to set.
		 */
		public void setStayed( boolean stayed )
		{
			this.stayed = stayed;
		}

		/**
		 * @return Returns the continuedStay.
		 */
		public boolean isContinuedStay()
		{
			return continuedStay;
		}

		/**
		 * @param continuedStay
		 *          The continuedStay to set.
		 */
		public void setContinuedStay( boolean continuedStay )
		{
			this.continuedStay = continuedStay;
		}

		/**
		 * @return Returns the detTime.
		 */
		public long getDetTime()
		{
			return detTime;
		}

		/**
		 * @param detTime
		 *          The detTime to set.
		 */
		public void setDetTime( long detTime )
		{
			this.detTime = detTime;
		}

		/*
		 * 
		 */
		public JuvenileFacilityResponseEvent getFacilityInfo()
		{
			return facilityInfo;
		}

		public void setFacilityInfo( JuvenileFacilityResponseEvent facilityInfo )
		{
			this.facilityInfo = facilityInfo;
		}

		/**
		 * @param outcome the outcome to set
		 */
		public void setOutcome(String outcome) {
			this.outcome = outcome;
		}

		/**
		 * @return the outcome
		 */
		public String getOutcome() {
			return outcome;
		}

		/**
		 * @param recentLevelOfCare the recentLevelOfCare to set
		 */
		public void setRecentLevelOfCare(String recentLevelOfCare) {
			this.recentLevelOfCare = recentLevelOfCare;
		}

		/**
		 * @return the recentLevelOfCare
		 */
		public String getRecentLevelOfCare() {
			return recentLevelOfCare;
		}

		/**
		 * @param recentReasonForDischarge the recentReasonForDischarge to set
		 */
		public void setRecentReasonForDischarge(String recentReasonForDischarge) {
			this.recentReasonForDischarge = recentReasonForDischarge;
		}

		/**
		 * @return the recentReasonForDischarge
		 */
		public String getRecentReasonForDischarge() {
			return recentReasonForDischarge;
		}

		

	
	}

	/*
	 * 
	 */
	public static class Diagnosis
	{
		private String			testId;
		private Collection	diagnosisResults;
		private boolean			dsmDiagnosisFound;

		/**
		 * @return Returns the diagnosisResults.
		 */
		public Collection getDiagnosisResults()
		{
			return diagnosisResults;
		}

		/**
		 * @param diagnosisResults
		 *          The diagnosisResults to set.
		 */
		public void setDiagnosisResults( Collection diagnosisResults )
		{
			this.diagnosisResults = diagnosisResults;
		}

		/**
		 * @return Returns the dsmDiagnosisFound.
		 */
		public boolean isDsmDiagnosisFound()
		{
			return dsmDiagnosisFound;
		}

		/**
		 * @param dsmDiagnosisFound
		 *          The dsmDiagnosisFound to set.
		 */
		public void setDsmDiagnosisFound( boolean dsmDiagnosisFound )
		{
			this.dsmDiagnosisFound = dsmDiagnosisFound;
		}

		/**
		 * @return Returns the testId.
		 */
		public String getTestId()
		{
			return testId;
		}

		/**
		 * @param testId
		 *          The testId to set.
		 */
		public void setTestId( String testId )
		{
			this.testId = testId;
		}
	}

	/**
	 * @return Returns the guardianList.
	 */
	public Collection getGuardianList()
	{
		return guardianList;
	}

	/**
	 * @param guardianList
	 *          The guardianList to set.
	 */
	public void setGuardianList( Collection guardianList )
	{
		this.guardianList = guardianList;
	}

	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param juvenileNum
	 *          The juvenileNum to set.
	 */
	public void setJuvenileNum( String juvenileNum )
	{
		this.juvenileNum = juvenileNum;
	}

	public List getExitPlanList()
	{
		return exitPlanList;
	}

	public void setExitPlanList( List exitPlanList )
	{
		this.exitPlanList = exitPlanList;
	}

	public String getSelectedExitPlanId()
	{
		return selectedExitPlanId;
	}

	public void setSelectedExitPlanId( String selectedExitPlanId )
	{
		this.selectedExitPlanId = selectedExitPlanId;
	}

	/**
	 * @return Returns the caseStatus.
	 */
	public String getCaseStatus()
	{
		return caseStatus;
	}

	/**
	 * @param caseStatus
	 *          The caseStatus to set.
	 */
	public void setCaseStatus( String caseStatus )
	{
		this.caseStatus = caseStatus;
	}

	public boolean isCasefilePostAdjCommOrRes()
	{
		return casefilePostAdjCommOrRes;
	}

	public void setCasefilePostAdjCommOrRes( boolean casefilePostAdjCommOrRes )
	{
		this.casefilePostAdjCommOrRes = casefilePostAdjCommOrRes;
	}

	public Collection getControllingReferrals()
	{
		return controllingReferrals;
	}

	public void setControllingReferrals( Collection controllingReferrals )
	{
		this.controllingReferrals = controllingReferrals;
	}

	public String getControllingReferralFromClosing()
	{
		return controllingReferralFromClosing;
	}

	public void setControllingReferralFromClosing( String controllingReferralFromClosing )
	{
		this.controllingReferralFromClosing = controllingReferralFromClosing;
	}

	public String getSelectedControllingReferral()
	{
		return selectedControllingReferral;
	}

	public void setSelectedControllingReferral( String selectedControllingReferral )
	{
		this.selectedControllingReferral = selectedControllingReferral;
	}

	public String getOffenseDescription()
	{
		return offenseDescription;
	}

	public void setOffenseDescription( String offenseDescription )
	{
		this.offenseDescription = offenseDescription;
	}

	/**
	 * @param placement the placement to set
	 */
	public void setPlacement(Placement placement) {
		this.placement = placement;
	}

	/**
	 * @return the placement
	 */
	public Placement getPlacement() {
		return placement;
	}

	public String getPreviousTab() {
		return previousTab;
	}

	public void setPreviousTab(String previousTab) {
		this.previousTab = previousTab;
	}

	public String getPreviousReport() {
		return previousReport;
	}

	public void setPreviousReport(String previousReport) {
		this.previousReport = previousReport;
	}

	public Collection getPreviousExitReportQuestions() {
		return previousExitReportQuestions;
	}

	public void setPreviousExitReportQuestions(
			Collection previousExitReportQuestions) {
		this.previousExitReportQuestions = previousExitReportQuestions;
	}

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	public Map getCummulativeExitReportQuestions() {
		return cummulativeExitReportQuestions;
	}

	public void setCummulativeExitReportQuestions(
			Map cummulativeExitReportQuestions) {
		this.cummulativeExitReportQuestions = cummulativeExitReportQuestions;
	}

	/**
	 * @return the casefileId
	 */
	public String getCasefileId() {
		return casefileId;
	}

	/**
	 * @param casefileId the casefileId to set
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}

	/**
	 * @return the prevClosingInfoId
	 */
	public String getPrevClosingInfoId() {
		return prevClosingInfoId;
	}

	/**
	 * @param prevClosingInfoId the prevClosingInfoId to set
	 */
	public void setPrevClosingInfoId(String prevClosingInfoId) {
		this.prevClosingInfoId = prevClosingInfoId;
	}
}// END CLASS
