package messaging.domintf.contact.user;

import java.util.Date;
/**
 * @author jmcnabb
 *
 */
public interface IUserProfileUpdate
{
	String getLogonId();
	String getLastName();
	String getFirstName();
	String getMiddleName();
	Date getDateOfBirth();
	String getEmail();
	Date getInactivationDate();
	String getInactivationTime();
	String getGenericUserType();
	String getGenericUserTypeId();
	String getPublicInd();
	String getRequestorFirstName();
	String getRequestorLastName();
	String getSsn();
	String getUserStatus();
	String getPhoneNum();
	String getPhoneExt();
	String getDepartmentId();
	Date getDeptTransferRequestDate();
	String getDeptTransferRequestTime();
	String getBadgeNum();
	String getOtherIdNum();
	String getOrgCode();
	String getComments();
	String getCustomCodeGeneration();
	boolean getUvCodeGeneration();
	void setLastName(String lastName);
	void setFirstName(String firstName);
	void setMiddleName(String middleName);
	void setDateOfBirth(Date dateOfBirth);
	void setEmail(String email);
	void setInactivationDate(Date inactivationDate);
	void setInactivationTime(String inactivationTime);
	void setBadgeNum(String badgeNum);
	void setOtherIdNum(String otherIdNum);
	void setPublicInd(String ind);
	void setRequestorFirstName(String requestorFirstName);
	void setRequestorLastName(String requestorLastName);
	void setSsn(String ssn);
	void setLogonId(String logonId);
	void setUserStatus(String userStatus);
	void setGenericUserType(String genericUserType);
	void setGenericUserTypeId(String genericUserTypeId);
	void setPhoneNum(String phoneNum);
	void setPhoneExt(String phoneExt);
	void setDepartmentId(String departmentId);
	void setDeptTransferRequestDate(Date reqDate);
	void setDeptTransferRequestTime(String reqTime);
	void setOrgCode(String orgCode);
	void setComments(String comments);
	void setCustomCodeGeneration(String customCodeGeneration);
	void setUvCodeGeneration(boolean uvCodeGeneration);
	boolean isCreate();
}
