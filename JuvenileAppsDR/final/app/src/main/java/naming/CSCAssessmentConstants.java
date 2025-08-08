/*
 * Created on Feb 1, 2008
 *
 */
package naming;

/**
 * @author cc_bjangay
 *
 */
public class CSCAssessmentConstants
{
    public static final String CSTASK_TOPIC_NOTIFY_CSTS_CHANGE= "CS.NOTIFY.CSTS.CHANGE";
    public static final String CS_CASELOAD_BY_PROG_REFER_PROV= "CS.CASELOAD.BY.PROG.REFER.PROV";
    public static final String PAGEFLOW_SCENERIO = "caseloadByProgReferProvPageFlow";
   
    public static final String ASSESSMENT_COMPLETE_FORCE_FIELD_PAGEFLOW="completeforcefieldpageflow";
    public static final String ASSESSMENT_COMPLETE_SCS_INTERVIEW_PAGEFLOW="completescsinterviewpageflow";
    public static final String ASSESSMENT_COMPLETE_SCS_INVENTORY_PAGEFLOW="completescsinventorypageflow"; 
    public static final String ASSESSMENT_COMPLETE_WISCONSIN_PAGEFLOW="completewisconsinpageflow";
    
    public static final String ASSESSMENT_SUPERVISION_PRD_ACTV="ACTIVE";
    public static final String ASSESSMENT_SUPERVISION_PRD_INACTV="INACTIVE";
    
    public static final String ASSESSMENT_EXIST="EXIST";
    public static final String ASSESSMENT_NOT_EXIST="NOT_EXIST";
    
    public static final String ASSESSMENT_INITIAL_ASSESSMENT="INITIAL_ASSESSMENT";
    public static final String ASSESSMENT_REASSESSMENT="REASSESSMENT";
    
    public static final String ASSESSMENT_WISCONSIN_INITAIL_RISK_QUEST_GRP_ID="WISCONSIN_INITIAL_G1";
    public static final String ASSESSMENT_WISCONSIN_INITIAL_NEEDS_QUEST_GRP_ID="WISCONSIN_INITIAL_G2";
    
    public static final String ASSESSMENT_WISCONSIN_REASSESS_RISK_QUEST_GRP_ID="WISCREAS_G1";
    public static final String ASSESSMENT_WISCONSIN_REASSESS_NEEDS_QUEST_GRP_ID="WISCREAS_G2";
    
    public static final String ASSESSMENT_SCS_SCREEN_ONE_QUEST_GRP_ID="SCS_G1";
    public static final String ASSESSMENT_SCS_SCREEN_TWO_QUEST_GRP_ID="SCS_G2";
    public static final String ASSESSMENT_SCS_SCREEN_THREE_QUEST_GRP_ID="SCS_G3";
    public static final String ASSESSMENT_SCS_SCREEN_FOUR_QUEST_GRP_ID="SCS_G4";
    
    public static final String ASSESSMENT_WISCONSIN_SCREEN_RISK="risk";
    public static final String ASSESSMENT_WISCONSIN_SCREEN_NEEDS="needs";
    
    public static final String ASSESSMENT_LSIR_SCREEN="lsir";
    
    public static final String ASSESSMENT_SCS_SCREEN_ONE="screenOne";
    public static final String ASSESSMENT_SCS_SCREEN_TWO="screenTwo";
    public static final String ASSESSMENT_SCS_SCREEN_THREE="screenThree";
    public static final String ASSESSMENT_SCS_SCREEN_FOUR="screenFour";
    public static final String ASSESSMENT_SCS_CLASSIFICATION_TIE="classificationTie";
    
    public static final String ASSESSMENT_FORCE_FIELD_SCREEN="forceField";
    
    public static final int ASSESSMENT_SCS_CLASSIFICATION_INDEX_CC=0;
    public static final int ASSESSMENT_SCS_CLASSIFICATION_INDEX_ES=1;
    public static final int ASSESSMENT_SCS_CLASSIFICATION_INDEX_LS=2;
    public static final int ASSESSMENT_SCS_CLASSIFICATION_INDEX_SI=3;
    
    public static final String ASSESSMENT_SCS_INTRW_SCREEN_ONE_QUEST_GRP_ID="SCS_INTERVIEW_G1";
    public static final String ASSESSMENT_SCS_INTRW_SCREEN_TWO_QUEST_GRP_ID="SCS_INTERVIEW_G2";
    public static final String ASSESSMENT_SCS_INTRW_SCREEN_THREE_QUEST_GRP_ID="SCS_INTERVIEW_G3";
    public static final String ASSESSMENT_SCS_INTRW_SCREEN_FOUR_QUEST_GRP_ID="SCS_INTERVIEW_G4";
    public static final String ASSESSMENT_SCS_INTRW_SCREEN_FIVE_QUEST_GRP_ID="SCS_INTERVIEW_G5";
    public static final String ASSESSMENT_SCS_INTRW_SCREEN_SIX_QUEST_GRP_ID="SCS_INTERVIEW_G6";
    
    public static final String ASSESSMENT_SCS_INTERVIEW_SCREEN_ONE="screenOne";
    public static final String ASSESSMENT_SCS_INTERVIEW_SCREEN_TWO="screenTwo";
    public static final String ASSESSMENT_SCS_INTERVIEW_SCREEN_THREE="screenThree";
    public static final String ASSESSMENT_SCS_INTERVIEW_SCREEN_FOUR="screenFour";
    public static final String ASSESSMENT_SCS_INTERVIEW_SCREEN_FIVE="screenFive";
    public static final String ASSESSMENT_SCS_INTERVIEW_SCREEN_SIX="screenSix";
    
    public static final int ASSESSMENT_INITIAL_ASSESSMENT_LIST=0;
    public static final int ASSESSMENT_REASSESSMENT_LIST=1;
    public static final int ASSESSMENT_SCS_ASSESSMENT_LIST=2;
    
    public static final String ASSESSMENT_LOS_MAXIMUM = "Maximum";
    public static final String ASSESSMENT_LOS_MEDIUM = "Medium";
    public static final String ASSESSMENT_LOS_MINIMUM = "Minimum";
    
    public static final int ASSESSMENT_LOS_MAXIMUM_CD = 2;
    public static final int ASSESSMENT_LOS_MEDIUM_CD = 3;
    public static final int ASSESSMENT_LOS_MINIMUM_CD = 4;
    
    public static String CASENOTE_JOURNAL="casenotejournal";
	public static String CASENOTE_SUPERVISEE_SEARCH="casenotesuperviseesearch";
	
    /* Assessment Type Codes */
    public static String ASSESSMENTTYPE_WISCONSIN = "W";
    public static String ASSESSMENTTYPE_SCS = "S";
    public static final String ASSESSMENTTYPE_SCS_INTERVIEW = "I";
    public static String ASSESSMENTTYPE_FORCEFIELD = "F";
    public static String ASSESSMENTTYPE_LSIR = "L";
    
    /* Code tables */
    public static String ASSESSMENT_FILENAME = "ASSESSMENT_FILENAME";
    public static String ASSESSMENT_STATUS = "ASSESSMENT_STATUS";
    
    
    /* Assessment Status Codes */
    public static String ASSESSMENT_INCOMPLETE = "I";
    public static String ASSESSMENT_COMPLETE = "C";
    
    public static String ASSESSMENT_INCOMPLETE_DESC = "Incomplete";
    public static String ASSESSMENT_COMPLETE_DESC = "Complete";
    
    /* xml */
	public static final String COLUMN_SEQUENCE = "columnSequence";
	public static final String COLUMN_WIDTH = "columnWidth";
    public static final String DISPLAY_TEXT_ALIGN = "displayTextAlign";
	public static final String DISPLAY_TEXT = "displayText";
	public static final String FORMAT_KEY = "formatKey";
    public static final String ID = "id";
	public static final String IS_DEFAULT = "isDefault";
    public static final String IS_DISPLAY_TEXT_DETAIL_HEADER = "isDisplayTextDetailHeader";
	public static final String IS_EACH_RESPONSE_NEW_LINE = "isEachResponseNewLine";
    public static final String IS_RENDER_QUES_NUM = "isRenderQuesNum";
	public static final String IS_REQUIRED = "isRequired";
	public static final String IS_REQUIRED_IMAGE_SHOWN = "isRequiredImageShown";
	public static final String IS_RESPONSE_NEW_LINE = "isResponseNewLine";
	public static final String QUESTION_ALIGNMENT="questionAlignment";
	public static final String NAME = "name";
	public static final String QUESTION_COLUMN_WIDTH = "questionColumnWidth";
	public static final String QUESTION_ID = "questionId";
	public static final String RESPONSE_DATA_TYPE = "responseDataType";
	public static final String RESPONSE_DEPENDENCY = "responseDependancy";
	public static final String RESPONSE_UICONTROL_TYPE = "responseUIControlType";
	public static final String RESPONSE_VALUE = "responseValue";
	public static final String ROW_SEQUENCE = "rowSequence";
	public static final String SELECTED_RESPONSE_ID = "selectedResponseId";
	public static final String SELECTED_RESPONSE_TEXT = "selectedResponseText";
	public static final String SEQUENCE = "sequence";
	public static final String SUMMARY_COL_SEQUENCE = "summaryColSeq";
	public static final String SUMMARY_RESP_COLUMN_WIDTH = "summaryrespColumnWidth";
	public static final String SUMMARY_ROW_SEQUENCE = "summaryRowSeq";
	public static final String TEXT = "text";
	public static final String TEXT_LENGTH="textLength";
	public static final String TYPE = "type";
	public static final String UICONTROL_SIZE = "uiControlSize";
	public static final String VALIDATION_DESCRIPTION = "validationDescription";
	public static final String MIN_VALUE = "minValue";
	public static final String MAX_VALUE = "maxValue";
	public static final String ASSESMENTID = "assessmentId";
}
