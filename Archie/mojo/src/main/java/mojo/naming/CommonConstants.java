package mojo.naming;

/**
 * @author dnikolis
 *
 * This class contains all the constant names for the application.
 * 
 * Change History:
 *
 * Author          	Date        	Explanation
 * Dnikolis			12/03/03		Added Javadocs
 * 
 */
public class CommonConstants {
	// Application constants	 
	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";
	public static final String UPDATE_PASSWORD = "updatePassword";

	public static final String ACTIVE = "ACTIVE";
	public static final String EXPIRED = "EXPIRED";
	public static final String SUSPENDED = "SUSPENDED";
	
	public static final String LOGIN_PAGE = "/jsp/refApp_splash.jsp";

	public static final int INCORRECT_LOGIN_LIMIT = 10;
	
	// represents the JAAS security context for ACF2 authentication
	public static final String ACF2_CONTEXT = "acf2";
	
	// Application errors	
	public static final String INCORRECT_USER_ERROR = "error.incorrect.user";
	public static final String INCORRECT_PASSWORD_ERROR = "error.incorrect.password";
	public static final String HOUR_PASSWORD_ERROR = "error.hour.password";
	public static final String INVALID_PASSWORD_ERROR = "error.invalid.password";
	public static final String PROFILE_SUSPENDED_ERROR = "error.profile.suspended";
	public static final String GENERAL_PROFILE_ERROR = "error.profile.general";
	public static final String PROFILE_EXPIRED_ERROR = "error.profile.expired";
	public static final String UNKNOWN_LOGIN_ERROR = "error.unknown.login.error";
	public static final String PASSWORD_EXPIRED_ERROR = "error.password.expired";

	//JRIO constants
	public final static String PERSON = "person";
	public final static String CASE = "case";
	public final static String MAINFRAME = "mainframe";
	public final static String PM1_FIRST_SPN = "00000000";
	public final static String PM1_LAST_SPN = "00544977";
	public final static String PM2_FIRST_SPN = "00544978";
	public final static String PM2_LAST_SPN = "01046688";
	public final static String PM3_FIRST_SPN = "01046689";
	public final static String PM3_LAST_SPN = "01347310";
	public final static String PM4_FIRST_SPN = "01347311";
	public final static String PM4_LAST_SPN = "50000000";
	public final static String PM5_FIRST_SPN = "50000001";
	public final static String PM5_LAST_SPN = "99999999";
	
	public final static String CLASS_C_CDI = "001";
	public final static String MISDEMEANOR_CDI = "002";
	public final static String FELONY_CDI = "003";
	
	//M204 constants
	//driver supplied in the J204.jar file
	public final static String DRIVER = "com.cca.j204.J204Driver";
	// rclconnection is the ipaddress of the mainframe 
	//public final static String RCLCONNECTION = "jdbc:j204://10.5.22.104:2505/RCL/"; 
	public final static String RCLCONNECTION1 = "jdbc:j204://";
	public final static String RCLCONNECTION2 = ":";
	public final static String RCLCONNECTION3 = "/RCL/";
	//this tells 204 you are finished transferring data
	public final static String ENDofDATA = "eof;";
	//the ; must be used to imitate the enter key
	public final static String SEPARATOR = ";";
	//the = sign
 	public final static String EQUALSIGN = "=";
	//If there is a problem with 204, the first row begins with M204 Exception
	public final static String M204EXCEPTION = "M204_EXCEPTION:";
	//The first row of data coming back from 204 begins with START-OF-DATA
	public final static String STARTDATA = "START-OF-DATA";
	//The last row of data coming back from 204 begins with END-OF-DATA
	public final static String ENDDATA = "END-OF-DATA";
	//This is the package name for the M204 classes
	public final static String DATAACCESS204 = "km.dataaccess.m204.";
	//this logonid/password is used to sign on to 204
	public final static String LOGONID = "jib01";
    public final static String PASSWORD = "jib01";
    //M204 subsystem name
    public final static String SUBSYSTEM = "jprc1";
    
}

