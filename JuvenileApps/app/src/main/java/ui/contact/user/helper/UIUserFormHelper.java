/*
 * Created on Mar 8, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.contact.user.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import messaging.authentication.GetJIMS2AccountByJIMSLogonIdEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.domintf.contact.user.IUserProfile;
import messaging.officer.ValidateOfficerProfileEvent;
import messaging.security.authentication.reply.JIMS2AccountResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.LogonControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.PDCodeTableConstants;

import org.apache.commons.lang.time.FastDateFormat;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SocialSecurity;
import ui.common.UIUtil;
import ui.contact.user.form.UserForm;
import ui.contact.user.form.UserProfileForm;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class UIUserFormHelper
{
	
	private UIUserFormHelper() {}
	
	/**
	 * @param date
	 * @return Date
	 * @throws ParseException
	 */
	public static Date toDate(String date)
	{

		Date toDate = null;
		if (date.length() > 1)
		{
			String formatDt = "MM/dd/yyyy";
			int slashIndex = date.indexOf("/");
			if (slashIndex < 0)
			{
				formatDt = "MMddyyyy";
			}
			toDate = DateUtil.stringToDate(date, formatDt);
		}
		return toDate;

	}
	
	/**
	 * This need to function is re-written since only lastName is reqd for the search 
	 * firstname and middlename are optional
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @return String
	 */
	public static String formatFullName(String firstName, String middleName, String lastName)
	{
		if (lastName == null)
		{
			return "Not Available";
		}
		StringBuffer full = new StringBuffer();
		full.append(lastName);
		if (firstName != null && !firstName.equals(""))
		{
			full.append(", " + firstName);
		}
		if (middleName != null && !middleName.equals(""))
		{
			full.append(" " + middleName);
		}
		return full.toString();
	}

	/**
	 * @param userForm
	 */
	public static void formatUserForm(UserForm userForm)
	{

		// set name
		userForm.setName(
			formatFullName(userForm.getFirstName(), userForm.getMiddleName(), userForm.getLastName()));

		/** Begin Format SSN & Phone Name For Display */
		SocialSecurity ss = new SocialSecurity(userForm.getSsn1(), userForm.getSsn2(), userForm.getSsn3());
		if (!ss.isValid())
		{
			ss.setSSN(userForm.getSsn());
		}
		userForm.setSsn(ss.getSSN());
		userForm.setSsn1(ss.getSSN1());
		userForm.setSsn2(ss.getSSN2());
		userForm.setSsn3(ss.getSSN3());

		PhoneNumber ph =
			new PhoneNumber(userForm.getPhoneNo1(), userForm.getPhoneNo2(), userForm.getPhoneNo3());
		if (!ph.isValid())
		{
			ph.setPhoneNumber(userForm.getPhoneNum());
		}
		userForm.setPhoneNum(ph.getPhoneNumber());
		userForm.setPhoneNo1(ph.getAreaCode());
		userForm.setPhoneNo2(ph.getPrefix());
		userForm.setPhoneNo3(ph.getLast4Digit());

		userForm.setCreatorName(
			UIUtil.formatFullName(userForm.getCreatorFirstName(), userForm.getCreatorLastName()));
		userForm.setRequestorName(
			UIUtil.formatFullName(userForm.getRequestorFirstName(), userForm.getRequestorLastName()));

		String dateOfBirth = userForm.getDateOfBirth();
		if (dateOfBirth != null && !dateOfBirth.trim().equals(""))
		{
			String dateFormat = "MM/dd/yyyy";
			if (!isDateValid(dateOfBirth, dateFormat, true))
				// if dateOfBirth in MM/dd/yyyy don't format it
			{
				FastDateFormat FULL_DATE_FAST_FORMAT = FastDateFormat.getInstance(dateFormat);
				userForm.setDateOfBirth ( FULL_DATE_FAST_FORMAT.format(userForm.getDateOfBirth()));
	            
	           

			//	SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			//	userForm.setDateOfBirth(sdf.format(userForm.getDateOfBirth()));

			}
		}

	}

	/**
	 * @param value
	 * @param datePattern
	 * @param strict
	 * @return boolean
	 */
	public static boolean isDateValid(String value, String datePattern, boolean strict)
	{

		if (value == null || datePattern == null || datePattern.length() <= 0)
		{

			return false;
		}
		//DateFormat formatter = DateFormat.getInstance(datePattern);
		 
		SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
		 
		formatter.setLenient(false);

		try
		{
			formatter.parse(value);
		 
		}
		catch (ParseException e)
		{
			return false;
		}

		if (strict && (datePattern.length() != value.length()))
		{
			return false;
		}

		return true;
	}

	public static void setUserProfileFormValues(UserProfileForm userProfileForm, IUserProfile userResponse)
	{
		userProfileForm.setLogonId(userResponse.getLogonId());
		setCodeDescriptions(userProfileForm,(UserResponseEvent)userResponse);
		Name nameObj = new Name(userResponse.getFirstName(), userResponse.getMiddleName(), userResponse.getLastName());
		userProfileForm.setUserName(nameObj);
		StringBuffer tempName = null;
		userProfileForm.setActivatedBy(new Name(userResponse.getActivatedByFirstName(),"",userResponse.getActivatedByLastName()));
		
		Date tempDate = userResponse.getActivationDate();
				
		if (tempDate != null)
		{
			userProfileForm.setActivationDate(DateUtil.dateToString(tempDate,"MM/dd/yyyy"));
			userProfileForm.setActivationTime(userResponse.getActivationTime());
		}
		
		tempDate = userResponse.getDateOfBirth();
		if (tempDate != null)
		{
			userProfileForm.setDateOfBirth(DateUtil.dateToString(tempDate,"MM/dd/yyyy"));
		}
		userProfileForm.setSsn(new SocialSecurity(userResponse.getSsn()));
		//userProfileForm.setGenericUserTypeId(userResponse.getGenericUserType());
		
		tempDate = userResponse.getCreateDate();
		if (tempDate != null)
		{
			userProfileForm.setCreateDate(DateUtil.dateToString(tempDate,"MM/dd/yyyy"));
			userProfileForm.setCreateTime(userResponse.getCreateTime());
		}
		tempDate = userResponse.getInactivationDate();
		if (tempDate != null)
		{
			userProfileForm.setInactivationDate(DateUtil.dateToString(tempDate,"MM/dd/yyyy"));
			userProfileForm.setInactivationTime(userResponse.getInactivationTimeId());
			userProfileForm.setInactivationTimeId(getWorkDayCode(userResponse.getInactivationTimeId()));
		}
	
		userProfileForm.setAgencyId(userResponse.getAgencyId());
		userProfileForm.setAgencyName(userResponse.getAgencyName());
		userProfileForm.setDepartmentId(userResponse.getDepartmentId());
		userProfileForm.setDepartmentName(userResponse.getDepartmentName());
		userProfileForm.setOPID(userResponse.getOperatorId());
		userProfileForm.setOrgCode(userResponse.getOrgCode());
		PhoneNumber phone = new PhoneNumber(userResponse.getPhoneNum());
		phone.setExt(userResponse.getPhoneExt());
		userProfileForm.setPhoneNum(phone);
		userProfileForm.setEmail(userResponse.getEmail());
//		userProfileForm.setPublicInd(userResponse.getPublicInd());
			
		nameObj = new Name(userResponse.getCreatedByFirstName(), "", userResponse.getCreatedByLastName());
		userProfileForm.setCreatorName(nameObj);
		userProfileForm.setInactivatedBy(new Name(userResponse.getInactivatedByFirstName(),"",userResponse.getInactivatedByLastName()));
		
		tempDate = userResponse.getTransferDate();
		if (tempDate != null)
		{
			userProfileForm.setTransferDate(DateUtil.dateToString(tempDate,"MM/dd/yyyy"));
			userProfileForm.setTransferTime(userResponse.getTransferTime());
		}
		String requestorFName = userResponse.getRequestorFirstName();
		String requestorLName = userResponse.getRequestorLastName();
		if((requestorFName == null && requestorLName == null) || (requestorFName.equals("") && requestorLName.equals("")))
		{
			ISecurityManager securityManager = (ISecurityManager)ContextManager.getSession().get("ISecurityManager");		
			IUserInfo userInfo = securityManager.getIUserInfo();	
			userProfileForm.setRequestorName(new Name(userInfo.getFirstName(),userInfo.getMiddleName(),userInfo.getLastName()));
		}
		else
			userProfileForm.setRequestorName(new Name(userResponse.getRequestorFirstName(), "",userResponse.getRequestorLastName()));
		
		userProfileForm.setTrainingInd(userResponse.getTrainingInd());
		userProfileForm.setComments(userResponse.getComments());
	}
	
	public static void setUserProfileDetailValues(UserProfileForm.UserProfileDetail userProfileDetail, IUserProfile userResponse)
	{
		userProfileDetail.setLogonId(userResponse.getLogonId());
		Name nameObj = new Name(userResponse.getFirstName(), userResponse.getMiddleName(), userResponse.getLastName());
		userProfileDetail.setUserName(nameObj);
		StringBuffer tempName = null;
		userProfileDetail.setActivatedBy(new Name(userResponse.getActivatedByFirstName(),"",userResponse.getActivatedByLastName()));

		Date tempDate = userResponse.getActivationDate();
		
		if (tempDate != null)
		{
			userProfileDetail.setActivationDate(DateUtil.dateToString(tempDate,"MM/dd/yyyy"));
			userProfileDetail.setActivationTime(userResponse.getActivationTime());
		}

		tempDate = userResponse.getDateOfBirth();
		if (tempDate != null)
		{
			userProfileDetail.setDateOfBirth(tempDate);
		}
		userProfileDetail.setSsn(new SocialSecurity(userResponse.getSsn()));
		
		

		tempDate = userResponse.getCreateDate();
		if (tempDate != null)
		{
			userProfileDetail.setCreateDate(DateUtil.dateToString(tempDate,"MM/dd/yyyy"));
			userProfileDetail.setCreateTime(userResponse.getCreateTime());
		}
		tempDate = userResponse.getInactivationDate();
		if (tempDate != null)
		{
			userProfileDetail.setInactivationDate(DateUtil.dateToString(tempDate,"MM/dd/yyyy"));
			userProfileDetail.setInactivationTime(userResponse.getInactivationTimeId());
			
		}
		tempDate = userResponse.getInactivationRequestDate();
		if (tempDate != null)
		{
			userProfileDetail.setInactivationReqDate(DateUtil.dateToString(tempDate,"MM/dd/yyyy"));
			userProfileDetail.setInactivationReqTime(userResponse.getInactivationRequestTimeId());
	
		}
	
		userProfileDetail.setAgencyId(userResponse.getAgencyId());
		userProfileDetail.setAgencyName(userResponse.getAgencyName());
		userProfileDetail.setDepartmentId(userResponse.getDepartmentId());
		userProfileDetail.setDepartmentName(userResponse.getDepartmentName());
		userProfileDetail.setOPID(userResponse.getOperatorId());
		userProfileDetail.setOrgCode(userResponse.getOrgCode());
		PhoneNumber phone = new PhoneNumber(userResponse.getPhoneNum());
		phone.setExt(userResponse.getPhoneExt());
		userProfileDetail.setPhoneNum(phone);
		userProfileDetail.setEmail(userResponse.getEmail());
	
		nameObj = new Name(userResponse.getCreatedByFirstName(), "", userResponse.getCreatedByLastName());
		userProfileDetail.setCreatedBy(nameObj.getFormattedName());
		userProfileDetail.setInactivatedBy(new Name(userResponse.getInactivatedByFirstName(),"",userResponse.getInactivatedByLastName()));

		tempDate = userResponse.getDeptTransferRequestDate();
		if (tempDate != null)
		{
			userProfileDetail.setDeptTransferReqDate(DateUtil.dateToString(tempDate,"MM/dd/yyyy"));
			userProfileDetail.setDeptTransferReqTime(userResponse.getDeptTransferRequestTime());
		}
		tempDate = userResponse.getTransferDate();
		if (tempDate != null)
		{
			userProfileDetail.setTransferDate(DateUtil.dateToString(tempDate,"MM/dd/yyyy"));
			userProfileDetail.setTransferTime(userResponse.getTransferTime());
		}
		userProfileDetail.setRequestorName(new Name(userResponse.getRequestorFirstName(), "",userResponse.getRequestorLastName()));

		userProfileDetail.setTrainingInd(userResponse.getTrainingInd());
		userProfileDetail.setComments(userResponse.getComments());
	}
	
	public static OfficerProfileResponseEvent getUserOfficerProfile(String userId)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		ValidateOfficerProfileEvent officerProfile =
			(ValidateOfficerProfileEvent) EventFactory.getInstance(
				OfficerProfileControllerServiceNames.VALIDATEOFFICERPROFILE);
	
		officerProfile.setLogonId(userId);
		dispatch.postEvent(officerProfile);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		OfficerProfileResponseEvent officerResponse=(OfficerProfileResponseEvent)MessageUtil.filterComposite(compositeResponse, OfficerProfileResponseEvent.class);
		return officerResponse;
	}
	
	public static JIMS2AccountResponseEvent getJIMS2Account(String userId)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetJIMS2AccountByJIMSLogonIdEvent jims2Account =
			(GetJIMS2AccountByJIMSLogonIdEvent) EventFactory.getInstance(
				LogonControllerServiceNames.GETJIMS2ACCOUNTBYJIMSLOGONID);

		jims2Account.setLogonId(userId);
		dispatch.postEvent(jims2Account);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		JIMS2AccountResponseEvent jims2Response=(JIMS2AccountResponseEvent)MessageUtil.filterComposite(compositeResponse, JIMS2AccountResponseEvent.class);
		return jims2Response;
	}
	private static void setCodeDescriptions(UserProfileForm profileForm,UserResponseEvent userResponse)
	 {
		Iterator iter;
		if(userResponse.getUserTypeId()==null || userResponse.getUserTypeId().equals(""))
			profileForm.setUserType("BASIC");
		else
		{
			iter = CodeHelper.getCodes(PDCodeTableConstants.USER_TYPE).iterator();
			while(iter.hasNext())
			{
				CodeResponseEvent codeResp = (CodeResponseEvent) iter.next();
				if(codeResp.getCode().equals(userResponse.getUserTypeId()))
				{
					profileForm.setUserType(codeResp.getDescription());
					
				}
			}
		}
		if(userResponse.getGenericUserType()==null || userResponse.getGenericUserType().equals(""))
		{
			profileForm.setGenericUserType("Non-Generic");
			profileForm.setGenericUserTypeId("N");
		}
		else
		{
			iter = CodeHelper.getCodes(PDCodeTableConstants.GENERIC_USER_TYPE).iterator();
			while(iter.hasNext())
			{
				CodeResponseEvent codeResp = (CodeResponseEvent) iter.next();
				if(codeResp.getCode().equals(userResponse.getGenericUserType()))
				{
					profileForm.setGenericUserType(codeResp.getDescription());
					profileForm.setGenericUserTypeId(codeResp.getCode());
				}
			}
		}
		if(userResponse.getUserStatus().equals("PC"))
		{
			profileForm.setUserStatus("PENDING CHANGE");
			profileForm.setUserStatusId("A");
		}
		else if(userResponse.getUserStatus().equals("PA"))
		{
			profileForm.setUserStatus("PENDING ADD");
			profileForm.setUserStatusId("A");
		}
		else if(userResponse.getUserStatus().equals("PD"))
		{
			profileForm.setUserStatus("PENDING DELETE");
			profileForm.setUserStatusId("I");
		}
		else
		{
			iter = CodeHelper.getCodes(PDCodeTableConstants.AGENCY_STATUS).iterator();
			while(iter.hasNext())
			{
				CodeResponseEvent codeResp = (CodeResponseEvent) iter.next();
				if(codeResp.getCode().equals(userResponse.getUserStatus()))
				{
					profileForm.setUserStatus(codeResp.getDescription());
					profileForm.setUserStatusId(codeResp.getCode());
				
				}
			}
		}
//		if(userResponse.getPublicInd().equals("Y"))
//			profileForm.setPublicIndString("YES");
//		else
//			profileForm.setPublicIndString("NO");	
			
		return;
	 }
	 private static String getWorkDayCode(String inactivationTime)
	 {
	 	Collection workDays = CodeHelper.getWorkDayCodes();
		Iterator i = workDays.iterator();
		while(i.hasNext())
		{
			CodeResponseEvent r = (CodeResponseEvent) i.next();
			if(r.getDescription().equals(inactivationTime))
				return r.getCode();
		}
		return "";
	 }
	 public static String getPasswordQuestionDescription(String passwdId)
	 {
	 	Collection questions = CodeHelper.getPasswordQuestionCodes();
		Iterator i = questions.iterator();
		while(i.hasNext())
		{
			CodeResponseEvent r = (CodeResponseEvent) i.next();
			if(r.getCode().equals(passwdId))
				return r.getDescription();
		}
		
		return "";
	 	
	 }
	// no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
	/* public static Collection getSortedHistoryByDate(Collection userHistory)
	 {
	 	Iterator iter = userHistory.iterator();
		SortedMap map = new TreeMap();
		UserHistoryResponseEvent item = null;
	   while(iter.hasNext()){
		  item = (UserHistoryResponseEvent) iter.next();
		  map.put(item.getTransactionDate(),item);
	   }
	   return new ArrayList(map.values());
	 }*/
	
}
