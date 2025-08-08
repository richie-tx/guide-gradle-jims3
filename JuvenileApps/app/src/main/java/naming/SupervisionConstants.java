/*
 * Created on Sep 28, 2005
 *
 */
package naming;

/**
 * @author dgibler
 *
 */
public class SupervisionConstants
{
	public final static String FELONY = "F";
	public final static String MISDEMEANOR = "M";
	public final static String BOTH = "B";
	
	public final static String CIVIL_DISTRICT_COURT = "CV";
	public final static String COUNTY_CRIMINAL_COURT = "CC";
	public final static String CRIMINAL_DISTRICT_COURT = "CR";
	public final static String FAMILY_COURT = "FAM";
    public final static String FELONY_CDI = "003";
	public final static String OUT_OF_COUNTY_COURT = "OC";
	public final static String JUSTICE_OF_PEACE_COURT = "JP";
	public final static String JUVENILE_COURT = "JUV";

	public static final String STANDARD_ONLY_CONDITION = "SO";
	public static final String NON_STANDARD_ONLY_CONDITION = "NSO";
	public static final String STANDARD_AND_NON_STANDARD_CONDITION = "SNS";
	public static final String STANDARD_ONLY_CONDITION_DESC = "S";
	public static final String NON_STANDARD_ONLY_CONDITION_DESC = "NS";
	
	//Supervision Order
	public static final String ORIGINAL = "O";
	public static final String AMMENDED = "A";
	public static final String MODIFIED = "M";
	public static final String ORDER_TITLE_MODIFIED = "18";
	public static final String PASO_WORKFLOWFROM = "orderPresentation";
	
	
	//Suggested Order ResponseEvent topics
	public static final String SUGGESTED_ORDER_EVENT_TOPIC = "SuggestedOrder";
	public static final String SUGGESTED_ORDER_CONDITION_EVENT_TOPIC = "SuggestedOrderCondition";
	public static final String SUGGESTED_ORDER_COURT_EVENT_TOPIC = "SuggestedOrderCourt";
	public static final String SUGGESTED_ORDER_OFFENSE_EVENT_TOPIC = "SuggestedOrderOffense";
	
	// Administer Casenotes
	public static final String HOW_GENERATED_CREATED_BY = "CB";
	public static final String HOW_GENERATED_BY_SYSTEM = "SG";
	public static final String FELONY_INDICATOR = "F";
	public static final String MISDEMEANOR_INDICATOR = "M";
	
	public static final String DEFENDANT_ID = "defendantId";
	public static final String ACTIVE_STATUS = "A";
	public static final String INACTIVE_STATUS = "I";
	public static final String ERRORCODE_TRCNOTES = "TRCNOTES";
	public static final String ERRORCODE_CONDCNOTES = "CONDCNOTES";
	public static final String ERRORCODE_NCCNOTES = "NCCNOTES";
	public static final String ERRORCODE_CASEASSIGN = "CASEASSIGN";
	public static final String ERRORCODE_PROGREFREL = "PROGREFREL";
	public static final String CRIMINALCASEID = "criminalCaseId";
	public static final String PROGRAMREFERRALID = "programReferralId";
	public static final String CASENOTEID = "casenoteId";
	public static final String NONCOMPLIANCEEVENTID = "nonComplianceEventId";
	public static final String SUPERVISIONORDERCONDITIONID = "sprOrderConditionId";
	public static final String LOCATORKEY_SPNORDHL = "SPNORDHL";
	public static final String LOCATORKEY_SPNCNTHL = "SPNCNTHL";	
	public static final String USERPROFILE_ID = "userProfileId";
	public static final String STAFFPOSITION_ID = "staffPositionId";
	public static final String AGENCY_ID = "agencyId";
	public static final String DIVISION_ID = "divisionId";
	public static final String SEX_OFFENDER = "SEX OFFENDER";
}
