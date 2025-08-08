/*
 * Created on Nov 4, 2004
 */
package naming;

/**
 * @author dnikolis
 */
public class PDNotificationConstants
{			
	/* Generic Warrant Notification Constants */
	public static final int CREATE_WARRANT = 101;
	public static final int UPDATE_WARRANT = 102;
	public static final String NOTIFICATION_EMAILFROM = "Notification_EmailFrom";
	
	/* Juvenile Warrant - Directive to Apprehend Notification Constants */
	public static final int DTA_FILED = 11;
	public static final int DTA_FILED_ACKNOWLEDGEMENT_FAILURE = 12;
	public static final int DTA_REQUEST_ACTIVATION = 13;	
	public static final int DTA_ACTIVATION_FAILURE = 14;
	public static final int DTA_WANTED = 15;	
	
	public static final String NOTIFICATION_DTA_FILED_EMAILTO = "Notification_DTA_Filed_EmailTo";
	public static final String NOTIFICATION_DTA_WANTED_EMAILTO = "Notification_DTA_Wanted_EmailTo";
	public static final String NOTIFICATION_DTA_ACTIVATION_EMAILTO = "Notification_DTA_Activation_EmailTo";
	public static final String NOTIFICATION_DTA_ACTIVATION_FAILURE_EMAILTO = "Notification_DTA_Activation_Failure_EmailTo";
	public static final String NOTIFICATION_ARR_NEEDS_ACTIVATION_EMAILTO = "Notification_ARR_Needs_Activation_EmailTo";
	public static final String NOTIFICATION_DTA_FILED_ACKNOWLEDGEMENT_FAILURE_EMAILTO = "Notification_DTA_Filed_Acknowledgement_Failure_EmailTo";
	public static final String NOTIFICATION_FAILURE_TO_ENTER_RELEASE_DETAILS_EMAILTO = "Notification_Failure_To_Enter_Release_Details_EmailTo";
	public static final String NOTIFICATION_RELEASE_DECISION_EMAILTO = "Notification_Release_Deicision";	

	/* Juvenile Warrant -Probable Cause notification event*/
	public static final String NOTIFICATION_PC_WANTED_EMAILTO="Notification_PC_Wanted_EmailTo";
	
	/* Juvenile Warrant - Order of Immediate Custody Notification Constants */
	public static final int OIC_NEEDS_TO_BE_SIGNED = 21;
	public static final int OIC_WAS_NOT_SIGNED = 22;
	public static final int OIC_JUVENILE_IS_WANTED = 23;
	public static final int OIC_DO_NOT_ISSUE = 24;
	public static final int OIC_UPDATES_REQUIRED = 25;

	/* Juvenile Arrest Warrant Notifications */
	public static final int ARR_FILED = 31;
	public static final int ARR_FILED_ACKNOWLEDGEMENT_FAILURE = 32;
	public static final int ARR_ACTIVATE = 33;	
	public static final int ARR_ACTIVATION_FAILURE = 34;
	public static final int ARR_WANTED = 35;
	public static final int ARR_NEEDS_ACTIVATION = 36;
	public static final String NOTIFICATION_ARR_FILED_EMAILTO = "Notification_ARR_Filed_EmailTo";
	public static final String NOTIFICATION_ARR_WANTED_EMAILTO = "Notification_ARR_Wanted_EmailTo";
	public static final String NOTIFICATION_ARR_ACTIVATION_EMAILTO = "Notification_ARR_Activation_EmailTo";
	public static final String NOTIFICATION_ARR_ACTIVATION_FAILURE_EMAILTO = "Notification_ARR_Activation_Failure_EmailTo";
	public static final String NOTIFICATION_ARR_FILED_ACKNOWLEDGEMENT_FAILURE_EMAILTO = "Notification_ARR_Filed_Acknowledgement_Failure_EmailTo";

	/* Probable Cause Notifications */
	public static final int PC_FILED = 41;
	public static final int PC_FILED_ACKNOWLEDGEMENT_FAILURE = 42;
	public static final int PC_ACTIVATE = 43;
	public static final int PC_ACTIVATION_FAILURE = 44;
	public static final int PC_WANTED = 45;
	
	/* Service Juvenile Warrant Notification */
	public static final int SERVICE_INVALID_ADDRESS = 51;
	public static final int SERVICE_SUCCESSFUL = 52;
	public static final int PROCESS_RETURN_OF_SERVICE = 53;
	public static final int REMINDER_RETURN_NOT_SIGNED = 54;
	public static final int EXPIRED_RETUNRED_NOT_SIGNED = 55;
	public static final String NOTIFICATION_UPDATE_SERVICE_ARR = "Notification_ARR_Update_Service_EmailTo";
	public static final String NOTIFICATION_UPDATE_SERVICE_DTA = "Notification_DTA_Update_Service_EmailTo";
	public static final String NOTIFICATION_UPDATE_SERVICE_PC = "Notification_PC_Update_Service_EmailTo";
	public static final String NOTIFICATION_UPDATE_SERVICE_OIC = "Notification_OIC_Update_Service_EmailTo";
	public static final String NOTIFICATION_UPDATE_SERVICE_VOP = "Notification_VOP_Update_Service_EmailTo";	
	
	/* Violation of Probation Notifications */
	public static final int VOP_FILED = 61;
	public static final int VOP_ACTIVATION = 62;
	public static final int VOP_REJECTION = 63;
	public static final int VOP_UNSEND = 64;
	public static final String NOTIFICATION_VOP_FILED_EMAILTO = "Notification_VOP_Filed_EmailTo";
	public static final String NOTIFICATION_VOP_ACTIVATION_EMAILTO = "Notification_VOP_Activation_EmailTo";
	public static final String NOTIFICATION_VOP_REJECTION_EMAILTO = "Notification_VOP_Rejection_EmailTo";
	
	/* Release Juvenile Notification */
	public static final int NOTIFICATION_RELEASE_DECISION = 71;
	public static final int NOTIFICATION_RELEASE_DECISION_TIMER_EXPIRED = 72;
	public static final int NOTIFICATION_FAILURE_TO_ENTER_RELEASE_DETAILS = 73;
	
	// Juvenile case work notifications
	public static final int NOTIFICATION_NEW_JUVENILECASEFILE = 11;
	public static final int NOTIFICATION_UPDATE_JUVENILECASEFILE = 12;
	public static final int NOTIFICATION_CONDUCT_MAYSI = 21;
}
