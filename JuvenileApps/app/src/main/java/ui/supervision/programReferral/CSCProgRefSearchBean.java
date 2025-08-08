/*
 * Created on Apr 22, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.programReferral;

import java.util.Date;

import naming.CSAdministerProgramReferralsConstants;

import mojo.km.utilities.DateUtil;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSCProgRefSearchBean implements Comparable
{
	 private String referralId;
	
	 private String spn;
	 private String criminalCaseId;	
	 private String caseNum;
	 
	 private String referralTypeCd;
	 private String referralTypeDesc;
	 private boolean autoReferral;
	 
	 private String programId;
	 private String locationId;
	 
	 private boolean userEnteredSP=false;
	 
	 private String serviceProvidername;
	 private String programIdentifier;
	 
	 private Date referralDate;
	 private String referralDateAsStr;
	 private Date beginDate;
	 private String beginDateAsStr;
	 private Date endDate;
	 private String endDateAsStr;
	 
	 private String referralStatusCd;
	 private boolean initiated=false;
	 private boolean open=false;
	 private boolean exited=false;
	
	 private boolean sentToState = false;
	 
	 
	 public void calculateStatus(){
	 	/* if(referralDate!=null)
	 	{
	 		if(endDate!=null)
	 		{
	 			exited = true;
	 		}
	 		else
	 		{
	 			if(beginDate!=null)
	 			{
	 				open=true;
	 			}
	 			else
	 			{
	 				initiated = true;
	 			}
	 		}
	 	}*/
		 if (CSAdministerProgramReferralsConstants.INITIATED_REFERRAL_STATUS.equals(referralStatusCd)){
			 initiated = true;
		 } else if (CSAdministerProgramReferralsConstants.OPEN_REFERRAL_STATUS.equals(referralStatusCd)){
			 open = true;
		 } else if (CSAdministerProgramReferralsConstants.EXITED_REFERRAL_STATUS.equals(referralStatusCd)){
			 exited = true;
		 }
	 }
	
	 
	 
	/**
	 * @return the criminalCaseId
	 */
	public String getCriminalCaseId() {
		return criminalCaseId;
	}

	/**
	 * @param criminalCaseId the criminalCaseId to set
	 */
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}

	/**
	 * @return Returns the autoReferral.
	 */
	public boolean isAutoReferral() {
		return autoReferral;
	}
	/**
	 * @param autoReferral The autoReferral to set.
	 */
	public void setAutoReferral(boolean autoReferral) {
		this.autoReferral = autoReferral;
	}
	/**
	 * @return Returns the beginDate.
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	/**
	 * @param beginDate The beginDate to set.
	 */
	public void setBeginDate(Date beginDate)
	{
		this.beginDate = beginDate;
		this.beginDateAsStr = "";
		
		if(beginDate != null)
		{
			try
			{
				this.beginDateAsStr = DateUtil.dateToString(beginDate, DateUtil.DATE_FMT_1);
			}
			catch(Exception ex)
			{
				this.beginDateAsStr = "";
			}
		}
	}
	/**
	 * @return Returns the beginDateAsStr.
	 */
	public String getBeginDateAsStr() {
		return beginDateAsStr;
	}
	/**
	 * @param beginDateAsStr The beginDateAsStr to set.
	 */
	public void setBeginDateAsStr(String beginDateAsStr) {
		this.beginDateAsStr = beginDateAsStr;
	}
	/**
	 * @return Returns the caseNum.
	 */
	public String getCaseNum() {
		return caseNum;
	}
	/**
	 * @param caseNum The caseNum to set.
	 */
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	/**
	 * @return Returns the endDate.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
		this.endDateAsStr = "";
		
		if(endDate != null)
		{
			try
			{
				this.endDateAsStr = DateUtil.dateToString(endDate, DateUtil.DATE_FMT_1);
			}
			catch(Exception ex)
			{
				this.endDateAsStr = "";
			}
		}
	}
	/**
	 * @return Returns the endDateAsStr.
	 */
	public String getEndDateAsStr() {
		return endDateAsStr;
	}
	/**
	 * @param endDateAsStr The endDateAsStr to set.
	 */
	public void setEndDateAsStr(String endDateAsStr) {
		this.endDateAsStr = endDateAsStr;
	}
	/**
	 * @return Returns the programIdentifier.
	 */
	public String getProgramIdentifier() {
		return programIdentifier;
	}
	/**
	 * @param programIdentifier The programIdentifier to set.
	 */
	public void setProgramIdentifier(String programIdentifier) {
		this.programIdentifier = programIdentifier;
	}
	/**
	 * @return Returns the referralDate.
	 */
	public Date getReferralDate() {
		return referralDate;
	}
	/**
	 * @param referralDate The referralDate to set.
	 */
	public void setReferralDate(Date referralDate) 
	{
		this.referralDate = referralDate;
		this.referralDateAsStr = "";
		
		if(referralDate != null)
		{
			try
			{
				this.referralDateAsStr = DateUtil.dateToString(referralDate, DateUtil.DATE_FMT_1);
			}
			catch(Exception ex)
			{
				this.referralDateAsStr = "";
			}
		}
	}
	/**
	 * @return Returns the referralDateAsStr.
	 */
	public String getReferralDateAsStr() {
		return referralDateAsStr;
	}
	/**
	 * @param referralDateAsStr The referralDateAsStr to set.
	 */
	public void setReferralDateAsStr(String referralDateAsStr) {
		this.referralDateAsStr = referralDateAsStr;
	}
	/**
	 * @return Returns the referralId.
	 */
	public String getReferralId() {
		return referralId;
	}
	/**
	 * @param referralId The referralId to set.
	 */
	public void setReferralId(String referralId) {
		this.referralId = referralId;
	}
	
	/**
	 * @return Returns the referralTypeDesc.
	 */
	public String getReferralTypeDesc() {
		return referralTypeDesc;
	}
	/**
	 * @param referralTypeDesc The referralTypeDesc to set.
	 */
	public void setReferralTypeDesc(String referralTypeDesc) {
		this.referralTypeDesc = referralTypeDesc;
	}
	
	/**
	 * @return the referralTypeCd
	 */
	public String getReferralTypeCd() {
		return referralTypeCd;
	}

	/**
	 * @param referralTypeCd the referralTypeCd to set
	 */
	public void setReferralTypeCd(String referralTypeCd) {
		this.referralTypeCd = referralTypeCd;
	}

	/**
	 * @return Returns the serviceProvidername.
	 */
	public String getServiceProvidername() {
		return serviceProvidername;
	}
	/**
	 * @param serviceProvidername The serviceProvidername to set.
	 */
	public void setServiceProvidername(String serviceProvidername) {
		this.serviceProvidername = serviceProvidername;
	}
	/**
	 * @return Returns the spn.
	 */
	public String getSpn() {
		return spn;
	}
	/**
	 * @param spn The spn to set.
	 */
	public void setSpn(String spn) {
		this.spn = spn;
	}
	/**
	 * @return Returns the exited.
	 */
	public boolean isExited() {
		return exited;
	}
	/**
	 * @param exited The exited to set.
	 */
	public void setExited(boolean exited) {
		this.exited = exited;
	}
	/**
	 * @return Returns the initiated.
	 */
	public boolean isInitiated() {
		return initiated;
	}
	/**
	 * @param initiated The initiated to set.
	 */
	public void setInitiated(boolean initiated) {
		this.initiated = initiated;
	}
	/**
	 * @return Returns the open.
	 */
	public boolean isOpen() {
		return open;
	}
	/**
	 * @param open The open to set.
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}

	/**
	 * @return the programId
	 */
	public String getProgramId() {
		return programId;
	}
	/**
	 * @param programId the programId to set
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}

	/**
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	/**
	 * @return the sentToState
	 */
	public boolean isSentToState() {
		return sentToState;
	}
	/**
	 * @param sentToState the sentToState to set
	 */
	public void setSentToState(boolean sentToState) {
		this.sentToState = sentToState;
	}
	/**
	 * @return the userEnteredSP
	 */
	public boolean isUserEnteredSP() {
		return userEnteredSP;
	}
	/**
	 * @param userEnteredSP the userEnteredSP to set
	 */
	public void setUserEnteredSP(boolean userEnteredSP) {
		this.userEnteredSP = userEnteredSP;
	}
	public String getReferralStatusCd() {
			return referralStatusCd;
		}

	public void setReferralStatusCd(String referralStatusCd) {
			this.referralStatusCd = referralStatusCd;
		}

	/**
	 * 
	 */
	public int compareTo(Object o)
	{
		if(o==null || !(o instanceof CSCProgRefSearchBean))
			return 1;
		
		CSCProgRefSearchBean progSearchBean = (CSCProgRefSearchBean)o;
		if(this.initiated)
		{
			if(progSearchBean.initiated)
			{
				int result = DateUtil.compare(this.referralDate, progSearchBean.referralDate, DateUtil.DATE_FMT_1);
				if(result>0)
					return -1;
				else if(result<0)
					return 1;
				else
					return 0;
			}
			else
			{
				return -1;
			}
		}
		else
		if(this.open)
		{
			if(progSearchBean.open)
			{
				int result = DateUtil.compare(this.referralDate, progSearchBean.referralDate, DateUtil.DATE_FMT_1);
				if(result>0)
					return -1;
				else if(result<0)
					return 1;
				else
					return 0;
			}
			else if(progSearchBean.initiated)
				return 1;
			else if(progSearchBean.exited)
				return -1;
		}
		if(this.exited)
		{
			if(progSearchBean.exited)
			{
				int result = DateUtil.compare(this.referralDate, progSearchBean.referralDate, DateUtil.DATE_FMT_1);
				if(result>0)
					return -1;
				else if(result<0)
					return 1;
				else
					return 0;
			}
			else 
				return 1;
		}
		
		return 1;
	}
	
}// END Class
