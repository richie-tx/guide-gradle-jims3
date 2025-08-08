/*
 * Created on Dec 07, 2007
 *
 */
package naming;

/**
 * @author mchowdhury
 *
 */
public class SupervisionOrderConditionConstants {
    public static final String SUPERVISION_ORDER_CONDITION_ID = "sprOrderConditionId";
	public static final String NONCOMPLIANCE_EVENT_ID = "nonComplianceEventId";
	public static final String NONCOMPLIANCE_DATEFORMAT = "yyyy-MM-dd hh:mm:ss";
	
	// compliance DAO
	public static final String RESOLVE_NONCOMPLIANCE_DAO_LOCATOR = "pd.supervision.administercompliance.transactions.daos.ResolveNonComplianceDAO";
	public static final String SET_TO_COMPLIANT_DAO_LOCATOR = "pd.supervision.administercompliance.transactions.daos.SetToNonCompliantDAO";
	public static final String REMOVE_NONCOMPLIANT_EVENT_DAO_LOCATOR = "pd.supervision.administercompliance.transactions.daos.RemoveNonCompliantEventDAO";

	public static final String DATA_ENTRY_ERROR = "DE";
	public static final String VALID_EXCUSE = "VE";
	public static final String NEWEVENTTYPECODE = "NEW";
	public static final String NEWEVENTTYPECODEDESC = "NEW EVENT TYPE";
	public static final String AGENCYID = "CSC";
	public static final String SYSTEMGENERATED = "SG";
	public static final String COMPLETE = "C";
	public static final String DRAFT = "D";
	public static final String CONTEXTTYPE = "CO";
	public static final String SUPERVISOR_FEATURE = "CS-DECREMENT";
	public static final String COMPLIANCE_FEATURE = "CS-COMPLIANCE";
}
