/*
 * Created on Nov 27, 2006
 *
 */
package naming;

/**
 * @author dgibler
 *
 */
public class CasenoteConstants {
    //Parameters used in tasks 
    public static String PARAM_CASENOTE_ID = "OID";
    public static String PARAM_SUPERVISEE_ID = "superviseeId";
    public static String PARAM_SUPERVISION_PERIOD_ID = "supervisionPeriodId";
    public static String CASENOTE_ID = "casenoteId";
    
    //Task topics
    public static String TASKDEF_UPDATE_DRAFT_CASENOTE = "CS.ADMINISTERCASENOTES.UPDATEDRAFT";
    

    //Severity Levels
    public static Integer STATUS_LOW_SEVERITY = new Integer(0);
    
    public static String UPDATE_CASENOTE_CONDITIONS_DAO_LOCATOR = "pd.supervision.administercasenotes.UpdateCasenoteConditionsDAO";
	public static final String GET_CASENOTE_CONDITIONS_DAO_LOCATOR = "pd.supervision.administercasenotes.GetConditionCasenotesDAO";

	//Status
	public static final String STATUS_DRAFT = "D";
	public static final String STATUS_INACTIVE = "I";
	public static final String STATUS_COMPLETE = "C";

}
