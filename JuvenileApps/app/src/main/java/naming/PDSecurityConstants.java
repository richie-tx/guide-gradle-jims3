/*
 * Created on Aug 3, 2004
 *
 */
package naming;

/**
 * @author Jim Fisher
 *
 */
public class PDSecurityConstants
{
	// Event Topics
	public static final String ROLE_EVENT_TOPIC = "Role";
	public static final String SYSTEM_ACTIVITY_EVENT_TOPIC = "SystemActivity";
	public static final String ROLE_LISTITEM_EVENT_TOPIC = "Role.ListItem";
	public static final String ROLE_GROUP_EVENT_TOPIC = "RoleGroup";
	public static final String ROLE_GROUP_LISTITEM_EVENT_TOPIC = "RoleGroup.ListItem";
	public static final String USER_GROUP_EVENT_TOPIC = "UserGroup";	
	public static final String ROLEGROUP_ROLE_AVAILABLE_LISTITEM =	"RoleGroup.Role.Available.ListItem";
	public static final String ROLEGROUP_ROLE_ASSOCIATED_LISTITEM =	"RoleGroup.Role.Associated.ListItem";

	// Role Type Constants
	public static final String PUBLIC = "PUBLIC";
	public static final String PRIVATE = "PRIVATE";
	public static final String SHARED = "SHARED";

	// Status Constants
	public static final String INUSE = "IN USE";
	public static final String INACTIVE = "INACTIVE";
	public static final String UNUSED = "UNUSED";

	// Status Code Constants
	public static final String ACTIVE = "A";
	public static final String EXPIRED = "E";
	public static final String SUSPENDED = "S";
	public static final String PENDING_ADD = "P";
	public static final String SHORTEND_INACTIVE = "I";
	public static final String NORTH = "N";
	public static final String SOUTH = "S";
	public static final String EAST = "E";
	public static final String WEST = "W";
	
	// Security Type Constants (not to be confused with Role Type)
	public static final String ROLE = "ROLE";
	public static final String ROLE_GROUP = "ROLEGROUP";
	public static final String USER = "USER";
	public static final String ROLE_ROOT = "ROLEROOT";
	public static final String AGENCY = "AGENCY";
	public static final String FEATURE_RESPONSE_LOCATOR = "FEAT";
	public static final String USER_GROUP_RESPONSE_LOCATOR = "UGRP";
	public static final String USER_GROUP_SECURITY_RESPONSE_LOCATOR = "UGSI";
	public static final String SECURITY_ROLE_RESPONSE_LOCATOR = "SRIR";
	public static final String AGENCY_RESPONSE_LOCATOR = "AGEN";
	public static final String DEPARTMENT_RESPONSE_LOCATOR = "DEPT";
	public static final String ROLE_RESPONSE_LOCATOR = "ROLE";
	public static final String USER_RESPONSE_LOCATOR = "USER";
	public static final String AGENCY_SECURITY_RESPONSE_LOCATOR = "ASIR";
	public static final String SECURITY_USER_RESPONSE_LOCATOR = "SURC";
	public static final String USER_SECURITY_INFO_RESPONSE_LOCATOR = "USIR";
	public static final String FEATURE_SECURITY_INFO_RESPONSE_LOCATOR = "FSIR";
	
	//USER TYPES
	public static final String USER_TYPE_SA = "SA";
	public static final String USER_TYPE_ASA = "ASA";
	public static final String USER_TYPE_MA = "MA";
	public static final String USER_TYPE_LIASON = "LA";
	public static final String USER_TYPE_BASIC = "BA";

	//ROLE TYPES
	public static final String ROLE_TYPE_SA = "SA";

	//UserGroup Status Code Constants
	public static final String USER_GROUP_STATUS_ACTIVE = "A";
	public static final String USER_GROUP_STATUS_INACTIVE = "I";
	public static final String USER_GROUP_TYPE_SECURITY = "SECURITY";
	
	// manage department
	public static final String DEPARTMENT_MAILINGADDRESS_TYPE = "M";
	public static final String DEPARTMENT_BILLINGADDRESS_TYPE = "S";
	public static final String DEPARTMENT_PHYSICALADDRESS_TYPE = "P";
	public static final String DEAPRTMENT_SETCIC_CONTACTTYPE = "S";
	static public final String BILLING_ADDRESS_TOPIC = "billingAddress";
	static public final String MAILING_ADDRESS_TOPIC = "mailingAddress";
	static public final String PHYSICAL_ADDRESS_TOPIC = "physicalAddress";
	static public final String DEPARTMENT_EVENT_TOPIC = "departmentEventTopic";
	public static final String DEPARTMENT_CONTACT_LISTITEM_EVENT_TOPIC = "contactListItem";
	public static final String DEPARTMENT_SETCIC_CONTACT_LISTITEM_EVENT_TOPIC = "setCicContactListItem";
	public static final String DEPARTMENT_NAME = "departmentName";
	public static final String YES = "Yes";
	public static final String NO = "No";
	public static final String Y = "Y";

	//miscellaneous constants
	public static final String ISECURITYMANAGER = "ISecurityManager";
	public static final String UPDATE="update";
	public static final String JUVENILE_PREFIX = "UV";
	public static final String JIMS_PREFIX = "JI";

	
	// GENERIC USER PROFILE TYPES
	public static final String NON_GENERIC_USER = "N";
	public static final String OFFICER_PROFILE = "L";
	public static final String SERVICE_PROVIDER = "S";

/*
	// create service provider
	
	public static final String SERVICEPROVIDER_MAILINGADDRESS_TYPE = "M";
	public static final String SERVICEPROVIDER_BILLINGADDRESS_TYPE = "S";
	public static final String AGENCY_ID = "agencyId";
	public static final String DEPARTMENT_ID = "originatingDepartment";
	public static final String LOGON_ID = "logonId";
	public static final String EMPLOYEE_ID = "employeeId";
	public static final String PROGRAM_CODE = "programCode";
	public static final String SERVICE_CODE = "serviceCode";
*/	
}