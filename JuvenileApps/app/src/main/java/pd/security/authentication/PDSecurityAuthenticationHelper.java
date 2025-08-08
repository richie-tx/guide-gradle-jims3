/*
 * Created on Dec 19, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.security.authentication;

import pd.contact.agency.Department;
import pd.contact.agency.DepartmentContact;
import pd.contact.user.UserProfile;
import pd.km.util.Phone;

/**
 * @author dnikolis
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PDSecurityAuthenticationHelper
{
	public static String getInactiveDepartmentMessage(Department dept)
	{
		StringBuffer message = new StringBuffer();
		String agencyTypeId = dept.getAgencyTypeId();
		if (agencyTypeId.equals("S"))
		{
			message =
				message.append(
					"Department Inactive.  Please contact the Subscriber Access Coordinator at 713-755-7815 or via email to dcsa@dco.hctx.net.");
			return message.toString();
		}
		else
		{
			String contactMessage = PDSecurityAuthenticationHelper.getDepartmentPrimaryContactInfo(dept);
			message =
				message
					.append("Department Inactive.  Please contact the Harris County ITC Help Desk at 713-755-6624 or your department security administrator: ")
					.append(contactMessage)
					.append(".");
		}
		return message.toString();
	}

	public static String getInactiveUserMessage(UserProfile user)
	{
		StringBuffer message = new StringBuffer();
		String agencyTypeId = "N";
		Department dept = user.getDepartment();
		if (dept != null)
		{
			agencyTypeId = dept.getAgencyTypeId();
		}
		if (agencyTypeId.equals("S"))
		{
			message =
				message.append(
					"User Inactive.  Please contact the Subscriber Access Coordinator at 713-755-7815 or via email to dcsa@dco.hctx.net.");
			return message.toString();
		}
		else
		{
			String contactMessage = PDSecurityAuthenticationHelper.getDepartmentPrimaryContactInfo(dept);
			message =
				message
					.append("User Inactive.  Please contact the Harris County ITC help desk at 713-755-6624 or your department security administrator. ")
					.append(contactMessage);
		}
		return message.toString();
	}

	public static String getNoFeaturesMessage(String deptId)
	{
		StringBuffer message = new StringBuffer();
		Department dept = Department.find(deptId);
		if (dept != null)
		{
			String contactMessage = PDSecurityAuthenticationHelper.getDepartmentPrimaryContactInfo(dept);
			message =
				message
					.append("You are not cleared for any JIMS2 applications at this time.  To request clearance please contact your department security administrator: ")
					.append(contactMessage);
		}
		return message.toString();
	}

	public static String getDepartmentPrimaryContactInfo(Department dept)
	{
		DepartmentContact deptContact = dept.getPrimaryContact();
		StringBuffer message = new StringBuffer();
		if (deptContact != null)
		{
			String phone = deptContact.getPhoneNum();
			String extension = deptContact.getPhoneExt();
			Phone phoneNumber = new Phone(phone);
			String formattedPhone = phoneNumber.getFormattedPhoneNumber();
			if ((extension != null) && (!(extension.equals(""))))
			{
				message =
					message
						.append(deptContact.getFirstName())
						.append(" ")
						.append(deptContact.getLastName())
						.append(" at ")
						.append(formattedPhone)
						.append(" ext. ")
						.append(extension);
			}
			else
			{
				message =
					message.append(deptContact.getFirstName()).append(" ").append(deptContact.getLastName()).append(
						" at ").append(
						formattedPhone);
			}
		}
		return message.toString();
	}

}