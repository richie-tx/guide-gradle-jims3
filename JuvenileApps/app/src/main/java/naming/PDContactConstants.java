/*
 * Created on Jul 30, 2004
 */
package naming;

/**
 * Constants that apply explicitly to the Contact Package
 * 
 * @author glyons
 *
 */
public class PDContactConstants {
	
	// Shared Topics
	public static final String LISTITEM_EVENT_TOPIC = ".ListItem";
	
	// User Topics
	public static final String USER_EVENT_TOPIC = "User";
	public static final String USER_HISTORY_EVENT_TOPIC = "UserHistory";
	public static final String USER_DUPLICATE_SSN_EVENT_TOPIC = "UserDuplicateSsn";
	public static final String USER_DUPLICATE_NAME_EVENT_TOPIC = "UserDuplicateName";
	public static final String USER_DUPLICATE = "UserDuplicate";
	public static final String ASSOCIATED_ROLES_EVENT_TOPIC = "associatedRoles";
	public static final String AVAILABLE_ROLES_EVENT_TOPIC = "availableRoles";
	
	//Agency Topics
	public static final String AGENCY_EVENT_TOPIC = "Agency";
	public static final String AGENCY_LISTITEM_EVENT_TOPIC = "Agency.ListItem";
	public static final String CONTACT_EVENT_TOPIC = "Contact";
	public static final String DIVISION_EVENT_TOPIC = "Division";
	public static final String DEPARTMENT_EVENT_TOPIC = "Department";	
	public static final String DEPARTMENT_LISTITEM_EVENT_TOPIC = "Department.ListItem";	
	public static final String ERROR_VALIDATE_AGENCY_EVENT_TOPIC = "InvalidAgencyError";
	public static final String ERROR_VALIDATE_AGENCY_DELETE_EVENT_TOPIC = "AgencyInUseError";
	
	// Department
	public static final String ERROR_VALIDATE_DEPARTMENT_EVENT_TOPIC = "InvalidDepartmentError";
	// Contact Action Types for Manage Contacts
	public static final String CONTACT_DELETE = "Delete";
	public static final String CONTACT_CREATE = "Create";
	public static final String CONTACT_UPDATE = "Update";
	
	// Department code constants
	public static final String JIMS = "JIM";
	public static final String JUVENILE = "JUV";
	
	// Status Code Constants
	public static final String ACTIVE = "A";
	public static final String SUSPENDED = "S";
	public static final String INACTIVE = "I";
	
	// Agency field name constants
	public static final String DEPARTMENT_ID = "departmentId";
	public static final String DEPARTMENT = "DEPARTMENT";
	
	// User field name constants
	public static final String AGENCY_ID = "agencyId";
	public static final String LOGON_ID = "logonId";
	
	public static final String NO = "N";
	
}
