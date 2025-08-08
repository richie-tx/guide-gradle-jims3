/*
 * Created on Oct 19, 2004
 */
package naming;

/**
 * @author dgibler
 */
public class PDJuvenileWarrantConstants
{	
	public static  final String FATHER_ASSOCIATE_NUM = "-2";
	public static  final String MOTHER_ASSOCIATE_NUM = "-1";
	public static  final String OTHER_ASSOCIATE_NUM = "-3";
	public static final String ALTER_ASSOCIATE_NUM = "-4";
	
	public static final String WARRANT_NUM = "warrantNum";
	public static final String JUVENILE_NUM = "juvenileNum";
	public static final String ASSOCIATE_NUM = "associateNum";
	
	// Error Event Topics
	public static final String ERROR_ACTIVE_WARRANT_EXISTS_TOPIC = "ActiveWarrantExists";
	public static final String ERROR_INVALID_WARRANT_TYPE_TOPIC = "InvalidWarrantType";
	public static final String ERROR_ACKNOWLEDGED_WARRANT_TOPIC = "AcknowledgedWarrant";
	
	// Event Topics
	public static final String JUVENILE_WARRANT_EVENT_TOPIC = "JUVENILE_WARRANT";
	public static final String JUVENILE_ASSOCIATE_EVENT_TOPIC =	"JUVENILE_ASSOCIATE";
	public static final String JUVENILE_ASSOCIATE_ADDRESS_EVENT_TOPIC = "JUVENILE_ASSOCIATE_ADDRESS";
	public static final String JUVENILE_WARRANT_CAUTION_EVENT_TOPIC = "JUVENILE_WARRANT_CAUTION";
	public static final String JUVENILE_WARRANT_SCARS_MARKS_EVENT_TOPIC = "JUVENILE_WARRANT_SCARS_MARKS";
	public static final String JUVENILE_WARRANT_TATTOOS_EVENT_TOPIC = "JUVENILE_WARRANT_TATTOOS";
	public static final String CHARGE_EVENT_TOPIC = "CHARGE";
	public static final String JUVENILE_ADDRESS_EVENT_TOPIC = "JUVENILE_ADDRESS";
	public static final String MOTHER_ADDRESS_EVENT_TOPIC = "MOTHER_ADDRESS";
	public static final String FATHER_ADDRESS_EVENT_TOPIC = "FATHER_ADDRESS";
	public static final String OTHER_ADDRESS_EVENT_TOPIC = "OTHER_ADDRESS";
	public static final String ALTER_ADDRESS_EVENT_TOPIC = "ALTER_ADDRESS";
	public static final String JUVENILE_ASSOCIATE_MOTHER_EVENT_TOPIC = "MOTHER_JUVENILE_ASSOCIATE";
	public static final String JUVENILE_ASSOCIATE_FATHER_EVENT_TOPIC = "FATHER_JUVENILE_ASSOCIATE";
	public static final String JUVENILE_ASSOCIATE_OTHER_EVENT_TOPIC = "OTHER_JUVENILE_ASSOCIATE";
	public static final String JUVENILE_ASSOCIATE_ALTER_EVENT_TOPIC = "ALTER_JUVENILE_ASSOCIATE";
	public static final String JUVENILE_OFFENDER_TRACKING_CHARGE_EVENT_TOPIC = "JUVENILE_OFFENDER_TRACKING_CHARGE";
	public static final String SUMMARY_OF_FACTS_EVENT_TOPIC = "SUMMARY_OF_FACTS";
	public static final String JUVENILE_OFFENDER_TRACKING_EVENT_TOPIC = "JUVENILE_OFFENDER_TRACKING";
	public static final String JUVENILE_WARRANT_SERVICE_EVENT_TOPIC = "JUVENILE_WARRANT_SERVICE";
	public static final String JUVENILE_WARRANT_PROCESS_SERVICE_EVENT_TOPIC = "JUVENILE_WARRANT_PROCESS_SERVICE";

	
	
	/**
	 * Juvenile Justice System Constants.
	 */
	public static final String PETITION_NUM = "PETITION_NUM";
	public static final String JJS_RESULTS_EVENT_TOPIC = "JUVENILE_JUSTICE_SYSTEM_SEARCH_RESULT";
	public static final String JUVENILE_COURT_EVENT_TOPIC = "JUVENILE_COURT";
	public static final String JUVENILE_JUSTICE_SYSTEM_EVENT_TOPIC = "JUVENILE_JUSTICE_SYSTEM";
	public static final String JJS_CHARGE_EVENT_TOPIC = "JJS_CHARGE";
	public static final String PETITION_EVENT_TOPIC = "PETITION";
	
	public static final String WARRANT_TYPE_DTA = "DTA";
	public static final String WARRANT_TYPE_VOP = "VOP";
	public static final String WARRANT_TYPE_OIC = "OIC";
	public static final String WARRANT_TYPE_ARR = "ARR";
	public static final String WARRANT_TYPE_PCW = "PC";

	public static final String WARRANT_RETURN = "R";
	public static final String WARRANT_RETURN_DESCRIPTION = "RETURN";
	public static final String WARRANT_SIGNED = "S";
	public static final String WARRANT_SIGN_DESCRIPTION = "SIGN";
	
	public static final String WARRANT_NOT_SIGNED = "N";
	public static final String WARRANT_NOT_SIGNED_DESCRIPTION = "NOT SIGNED";
	
	public static final String WARRANT_UNSEND = "US";
	public static String WARRANT_UNSEND_DESCRIPTION = "UNSEND";
	
	public static final String WARRANT_ACTIVATION_ACTIVE = "AC";
	public static final String WARRANT_ACTIVATION_INACTIVE = "IN";
	public static final String WARRANT_ACTIVATION_NOT_ACTIVE = "NA";
	public static final String WARRANT_ACTIVATION_REJECTED = "RJ";
	public static final String WARRANT_ACTIVATION_UNSEND = "US";
	public static final String WARRANT_ACKNOWLEDGE_PRINTED = "PR";
	public static final String WARRANT_ACKNOWLEDGE_NOT_PRINTED = "NP";
	public static final String WARRANT_STATUS_RECALL = "R";
	public static final String WARRANT_STATUS_OPEN = "O";
	public static final String WARRANT_STATUS_PENDING = "P";
	public static final String WARRANT_STATUS_EXECUTED = "E";
	
	public static final String WARRANT_STATUS_UPDATE = "U";

	
	public static final String JW_CLASSNAME = "pd.juvenilewarrant.JuvenileWarrant";
	public static final String DTA_CLASSNAME = "pd.juvenilewarrant.DirectiveToApprehendWarrant";
	public static final String VOP_CLASSNAME = "pd.juvenilewarrant.ViolationOfProbationWarrant";
	public static final String OIC_CLASSNAME = "pd.juvenilewarrant.OrderOfImmediateCustodyWarrant";
	public static final String ARR_CLASSNAME = "pd.juvenilewarrant.JuvenileArrestWarrant";
	public static final String PC_CLASSNAME = "pd.juvenilewarrant.JuvenileProbableCauseWarrant";

	public static final String WARRANT_SERVICE_SUCCESSFUL = "S";
	public static final String WARRANT_SERVICE_SUCCESSFUL_DESC = "SUCCESSFUL";
	
	public static final String WARRANT_SERVICE_UNSUCCESSFUL = "U";
	
	public static final String SERVICE_RETURN_SIGN_STATUS_RETURNED = "R";
	public static final String SERVICE_RETURN_SIGN_STATUS_FILED = "F";

	public static final String SERVICE_RETURN_GEN_STATUS_PRINTED = "P";
	public static final String SERVICE_RETURN_GEN_STATUS_NOTPRINTED = "N";
	
	public static final String RELEASE_DECISION_TO_PERSON = "RTP";
	public static final String RELEASE_DECISION_TO_JUVENILE_PROBATION = "RJP";
	
	public static final String RELEASE_TO_YES = "YES";

	// Transfer Location	
	public static final String TRANSFER_LOCATION_ARROWHEAD_VILLAGE   = "ARROWHEAD VILLAGE";
	public static final String TRANSFER_LOCATION_BURNETT_BAYLAND     = "BURNETT-BAYLAND";
	public static final String TRANSFER_LOCATION_BBRC_MHMRA_UNIT     = "BBRC MHMRA UNIT";
	public static final String TRANSFER_LOCATION_CHIMNEY_ROCK_CENTER = "CHIMENY ROCK CENTER";
	public static final String TRANSFER_LOCATION_COLORADO_BOOT_CAMP  = "COLORADO BOOT CAMP";
	public static final String TRANSFER_LOCATION_DELTA_BOOT_CAMP     = "DELTA BOOT CAMP";
	public static final String TRANSFER_LOCATION_DETENTION_CENTER    = "DETENTION CENTER";
	public static final String TRANSFER_LOCATION_HC_YOUTH_VILLAGE    = "H.C. YOUTH VILLAGE";
	
	public static final String PETITION_JOT = "JOT";
	public static final String PETITION_JJS = "JJS";
	
	// Associate Relationships
	public static final String BIRTH_MOTHER = "BM";
	public static final String BIRTH_FATHER = "BF";
	public static final String OTHER_RELATION = "OR";    
	
	//JOT Offender Tracking constants
	public static final String DM_ARREST_IND="ARRESTED DURING COURSE OF OFFENSE";
	public static final String DM_COMP_KNOWS_DEF = "COMPLAINANT KNOWS DEF";
	public static final String DM_BROUGHT_BACK = "BROUGHT BACK TO SCENE AND IDENTIFIED BY WITNESS";
	public static final String DM_AFIS_NCIC_ID = "AFIS/NCIC CHECK";
	public static final String DM_LINEUP_IND = "LINEUP";
	public static final String DM_ORAL_CONFESS = "ORAL CONFESSION LEADING TO EVIDENCE";
	public static final String DM_PHOTO_ARRAY_ID = "PHOTO ARRAY";
	public static final String DM_WRITTEN_CONFESS = "WRITTEN CONFESSION";
	public static final String DM_WINTNESS_KNOWS_DEF = "EYEWITNESS KNOWS JUVNILE";
	public static final String DM_OTHER = "OTHER";
	public static final String DM_CASE_TYPE_GROUP_FGC = "FGC";
	public static final String DM_CASE_TYPE_GROUP_GC = "GC";
	public static final String DM_CASE_TYPE_GROUP_GG = "GG";
	
	//JOT Offender Tracking Complainant constants
	public static final String DM_ASSOCIATION_TYPE_C="VICTIM";
	public static final String DM_ASSOCIATION_TYPE_W="WITNESS";
	
	//JOT Offender Tracking Disposition constants
	public static final String DM_DISP_IND_Y="Y";
	public static final String DM_DISP_IND_M="M";
	public static final String DM_DISP_IND_D="D";
	
	//JJS Petition constants
	public static final String MS_PETITION_STATUS_A = "ACTIVE";
	public static final String MS_PETITION_STATUS_D = "DISPOSED";
	public static final String MS_PETITION_STATUS_F = "PROBATION";
	public static final String MS_PETITION_STATUS_G = "CASE ON APPEAL";
	public static final String MS_PETITION_STATUS_M = "PROBATION MODIFIED";
	public static final String MS_PETITION_STATUS_N = "NEW TRIAL FILED";
	public static final String MS_PETITION_STATUS_R = "RE-OPEN";
	public static final String MS_PETITION_STATUS_T = "PROBATION TERMINATED";
	public static final String MS_PETITION_STATUS_V = "PROBATION VIOLATION";
	
	
}
