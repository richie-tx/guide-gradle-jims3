package messaging.juvenilecase.reply;

import java.util.List;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.ResponseEvent;

import org.apache.commons.lang.StringUtils;

public class JuvenileCaseLoadRepsonseEvent extends ResponseEvent implements IAddressable{
	private String juvenileNum;

	private String juvenileFirstName;

	private String juvenileMiddleName;

	private String juvenileLastName;
	
	private String juvenileNameSuffix;

	private String probationOfficerId;

	private String probationOfficerLogonId;

	private String probationOfficerFirstName;

	private String probationOfficerMiddleName;

	private String probationOfficerLastName;

	private String masterStatus;

	private String detentionFacility;

	private String detentionFacilityId;

	private String detentionStatus;

	private String detentionStatusId;

	private int activeCasefilesCount;

	private int activeJuvenilesCount;

	private List casefileAssignments;
	
	//added for US 32107 - marker for Restricted Access
	private String restrictedAccess;
	
	private String juvRectype;
	
	private String preferredFirstName;
	

	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return the juvenileFirstName
	 */
	public String getJuvenileFirstName() {
		return juvenileFirstName;
	}

	/**
	 * @param juvenileFirstName the juvenileFirstName to set
	 */
	public void setJuvenileFirstName(String juvenileFirstName) {
		this.juvenileFirstName = juvenileFirstName;
	}

	/**
	 * @return the juvenileMiddleName
	 */
	public String getJuvenileMiddleName() {
		return juvenileMiddleName;
	}

	/**
	 * @param juvenileMiddleName the juvenileMiddleName to set
	 */
	public void setJuvenileMiddleName(String juvenileMiddleName) {
		this.juvenileMiddleName = juvenileMiddleName;
	}

	/**
	 * @return the juvenileLastName
	 */
	public String getJuvenileLastName() {
		return juvenileLastName;
	}

	/**
	 * @param juvenileLastName the juvenileLastName to set
	 */
	public void setJuvenileLastName(String juvenileLastName) {
		this.juvenileLastName = juvenileLastName;
	}

	/**
	 * @return the juvenileNameSuffix
	 */
	public String getJuvenileNameSuffix() {
		return juvenileNameSuffix;
	}

	/**
	 * @param juvenileNameSuffix the juvenileNameSuffix to set
	 */
	public void setJuvenileNameSuffix(String juvenileNameSuffix) {
		this.juvenileNameSuffix = juvenileNameSuffix;
	}

	/**
	 * @return the probationOfficeId
	 */
	public String getProbationOfficerId() {
		return probationOfficerId;
	}

	/**
	 * @param probationOfficeId the probationOfficeId to set
	 */
	public void setProbationOfficerId(String probationOfficerId) {
		this.probationOfficerId = probationOfficerId;
	}

	/**
	 * @return the probationOfficerLogonId
	 */
	public String getProbationOfficerLogonId() {
		return probationOfficerLogonId;
	}

	/**
	 * @param probationOfficerLogonId the probationOfficerLogonId to set
	 */
	public void setProbationOfficerLogonId(String probationOfficerLogonId) {
		this.probationOfficerLogonId = probationOfficerLogonId;
	}

	/**
	 * @return the probationOfficerFirstName
	 */
	public String getProbationOfficerFirstName() {
		return probationOfficerFirstName;
	}

	/**
	 * @param probationOfficerFirstName the probationOfficerFirstName to set
	 */
	public void setProbationOfficerFirstName(String probationOfficerFirstName) {
		this.probationOfficerFirstName = probationOfficerFirstName;
	}

	/**
	 * @return the probationOfficerMiddleName
	 */
	public String getProbationOfficerMiddleName() {
		return probationOfficerMiddleName;
	}

	/**
	 * @param probationOfficerMiddleName the probationOfficerMiddleName to set
	 */
	public void setProbationOfficerMiddleName(String probationOfficerMiddleName) {
		this.probationOfficerMiddleName = probationOfficerMiddleName;
	}

	/**
	 * @return the probationOfficerLastName
	 */
	public String getProbationOfficerLastName() {
		return probationOfficerLastName;
	}

	/**
	 * @param probationOfficerLastName the probationOfficerLastName to set
	 */
	public void setProbationOfficerLastName(String probationOfficerLastName) {
		this.probationOfficerLastName = probationOfficerLastName;
	}

	/**
	 * @return the masterStatus
	 */
	public String getMasterStatus() {
		return masterStatus;
	}

	/**
	 * @param masterStatus the masterStatus to set
	 */
	public void setMasterStatus(String masterStatus) {
		this.masterStatus = masterStatus;
	}

	/**
	 * @return the detentionFacility
	 */
	public String getDetentionFacility() {
		return detentionFacility;
	}

	/**
	 * @param detentionFacility the detentionFacility to set
	 */
	public void setDetentionFacility(String detentionFacility) {
		this.detentionFacility = detentionFacility;
	}

	/**
	 * @return the detentionFacilityId
	 */
	public String getDetentionFacilityId() {
		return detentionFacilityId;
	}

	/**
	 * @param detentionFacilityId the detentionFacilityId to set
	 */
	public void setDetentionFacilityId(String detentionFacilityId) {
		this.detentionFacilityId = detentionFacilityId;
	}

	/**
	 * @return the detentionStatus
	 */
	public String getDetentionStatus() {
		return detentionStatus;
	}

	/**
	 * @param detentionStatus the detentionStatus to set
	 */
	public void setDetentionStatus(String detentionStatus) {
		this.detentionStatus = detentionStatus;
	}

	/**
	 * @return the detentionStatusId
	 */
	public String getDetentionStatusId() {
		return detentionStatusId;
	}

	/**
	 * @param detentionStatusId the detentionStatusId to set
	 */
	public void setDetentionStatusId(String detentionStatusId) {
		this.detentionStatusId = detentionStatusId;
	}

	/**
	 * @return the activeCasefilesCount
	 */
	public int getActiveCasefilesCount() {
		return activeCasefilesCount;
	}

	/**
	 * @param activeCasefilesCount the activeCasefilesCount to set
	 */
	public void setActiveCasefilesCount(int activeCasefilesCount) {
		this.activeCasefilesCount = activeCasefilesCount;
	}

	/**
	 * @return the activeJuvenilesCount
	 */
	public int getActiveJuvenilesCount() {
		return activeJuvenilesCount;
	}

	/**
	 * @param activeJuvenilesCount the activeJuvenilesCount to set
	 */
	public void setActiveJuvenilesCount(int activeJuvenilesCount) {
		this.activeJuvenilesCount = activeJuvenilesCount;
	}

	/**
	 * @return the casefileAssignments
	 */
	public List getCasefileAssignments() {
		return casefileAssignments;
	}

	/**
	 * @param casefileAssignments the casefileAssignments to set
	 */
	public void setCasefileAssignments(List casefileAssignments) {
		this.casefileAssignments = casefileAssignments;
	}

	/**
	 * @return String formatted Juvenile Name
	 */
	public String getJuvenileName()
	{
		String name = null;
		StringBuffer full = new StringBuffer();
		if (StringUtils.isNotEmpty(juvenileLastName)) {
			full.append(juvenileLastName);
		}
		if (StringUtils.isNotEmpty(juvenileFirstName)) {
			full.append(", ");
			full.append(juvenileFirstName);
			if (StringUtils.isNotEmpty(juvenileMiddleName)) {
				full.append(" " + juvenileMiddleName);
			}
			if (StringUtils.isNotEmpty(juvenileNameSuffix)) {
				full.append(" " + juvenileNameSuffix);
			}
		}
		name = full.toString();

		return name;
	}

	public String getRestrictedAccess() {
		return restrictedAccess;
	}

	public void setRestrictedAccess(String restrictedAccess) {
		this.restrictedAccess = restrictedAccess;
	}
	public String getJuvRectype()
	{
	    return juvRectype;
	}

	public void setJuvRectype(String juvRectype)
	{
	    this.juvRectype = juvRectype;
	}

	public String getPreferredFirstName()
	{
	    return preferredFirstName;
	}

	public void setPreferredFirstName(String preferredFirstName)
	{
	    this.preferredFirstName = preferredFirstName;
	}
}
