/*
 * Created on Feb 23, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.casefile.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import ui.common.CodeHelper;
import ui.common.UIUtil;
// import ui.juvenilecase.UIJuvenileLoadCodeTables;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CasefileActivationForm extends ActionForm
{
	private static Collection emptyColl = new ArrayList();
//	Default field
	private boolean listsSet = false;
	private String	action = "";
	private String	secondaryAction = "";
	private boolean update = false;
	private boolean delete = false;
	private String	selectedValue = "";

//	Casefile Activation Related fields
	private String	juvMasterStatusId = "";
	private String	juvMasterStatus = "";
	private Date	dateOfBirth = null;
	private String	maysi = "";
	private String	riskAnalysis = "";
	private String	titelIVcompleted = "";
	private String	casefileID = "";
	private boolean casefileAlreadyActivated = false;
	private Date supervisionEndDate = null ;
	private String supervisionEndDateStr = "";
	private String courtOrderedProbationStartDateStr = "";
	
	private String supervisionTypeId = "";
	private Collection referrals = emptyColl;
	private String controllingReferral = "";
	
	/**
	 * @return Returns the supervisionTypeId.
	 */
	public String getSupervisionTypeId() {
		return supervisionTypeId;
	}
	/**
	 * @param supervisionTypeId The supervisionTypeId to set.
	 */
	public void setSupervisionTypeId(String supervisionTypeId) {
		this.supervisionTypeId = supervisionTypeId;
	}


	public CasefileActivationForm()
	{
		clearAll();
	}
	
	public void clearAll()
	{
		juvMasterStatusId = "";
		juvMasterStatus = "";
		dateOfBirth = null;
		maysi = "";
		riskAnalysis = "";
		titelIVcompleted = "";
		casefileAlreadyActivated = false;
		supervisionEndDate = null ;
		supervisionTypeId = null;
		supervisionEndDateStr = "";
		courtOrderedProbationStartDateStr = "";
		referrals = null;
		controllingReferral = "";
	}
	
	public static class Refferal implements Comparable 
	{
		private String referralNumber = "";
		private String referralSeverity = "";
		private String finalDisposition = "";
		private String refSequenceNum= "";
		
		/**
		 * @return
		 */
		public String getReferralNumber()
		{
			return referralNumber;
		}
		/**
		 * @param string
		 */
		public void setReferralNumber(String string)
		{
			referralNumber = string;
		}
						
		/**
		 * @return the referralSeverity
		 */
		public String getReferralSeverity() {
			return referralSeverity;
		}
		/**
		 * @param referralSeverity the referralSeverity to set
		 */
		public void setReferralSeverity(String referralSeverity) {
			this.referralSeverity = referralSeverity;
		}
		/**
		 * @param string
		 */
		public void setFinalDisposition(String string)
		{
			finalDisposition = string;
		}
		
		/**
		 * @return
		 */
		public String getFinalDisposition()
		{
			return finalDisposition;
		}
		
		

		public String getRefSequenceNum()
		{
		    return refSequenceNum;
		}
		public void setRefSequenceNum(String refSequenceNum)
		{
		    this.refSequenceNum = refSequenceNum;
		}
		
		public int compareTo(Object obj) {
			Refferal evt = (Refferal) obj;
			
			String ref1 = referralNumber.trim();
			String ref2 = evt.getReferralNumber().trim();		
			return ref1.compareToIgnoreCase(ref2);
		}
	}// end public static class Refferal

	/**
	 * @return Returns the supervisionEndDateStr.
	 */
	public String getSupervisionEndDateStr() {
		return supervisionEndDateStr;
	}
	/**
	 * @param supervisionEndDateStr The supervisionEndDateStr to set.
	 */
	public void setSupervisionEndDateStr(String supervisionEndDateStr) {
		this.supervisionEndDateStr = supervisionEndDateStr;
		
		if(supervisionEndDateStr != null && supervisionEndDateStr.length() > 0)
		{
			supervisionEndDate = UIUtil.getDateFromString(supervisionEndDateStr, UIConstants.DATE_FMT_1);
			
		}
	}
	
	/**
	 * @return
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @return
	 */
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	/**
	 * @return
	 */
	public boolean isDelete()
	{
		return delete;
	}

	/**
	 * @return
	 */
	public String getJuvMasterStatus()
	{
		return juvMasterStatus;
	}

	/**
	 * @return
	 */
	public String getJuvMasterStatusId()
	{
		return juvMasterStatusId;
	}

	/**
	 * @return
	 */
	public boolean isListsSet()
	{
		return listsSet;
	}

	/**
	 * @return
	 */
	public String getMaysi()
	{
		return maysi;
	}

	/**
	 * @return
	 */
	public String getRiskAnalysis()
	{
		return riskAnalysis;
	}

	/**
	 * @return
	 */
	public String getSecondaryAction()
	{
		return secondaryAction;
	}

	/**
	 * @return
	 */
	public String getSelectedValue()
	{
		return selectedValue;
	}

	/**
	 * @return
	 */
	public String getTitelIVcompleted()
	{
		return titelIVcompleted;
	}

	/**
	 * @return
	 */
	public boolean isUpdate()
	{
		return update;
	}

	/**
	 * @param string
	 */
	public void setAction(String string)
	{
		action = string;
	}

	/**
	 * @param string
	 */
	public void setDateOfBirth(Date doB)
	{
		dateOfBirth = doB;
	}

	/**
	 * @param b
	 */
	public void setDelete(boolean b)
	{
		delete = b;
	}

	/**
	 * @param string
	 */
	public void setJuvMasterStatus(String string)
	{
		juvMasterStatus = string;
	}

	/**
	 * @param string
	 */
	public void setJuvMasterStatusId(String aId)
	{
		if( aId != null && !(aId.trim().equals("")) )
		{
			juvMasterStatus = CodeHelper.getCodeDescriptionByCode(CodeHelper.getJuvenileCaseStatuses(), aId);
		}
		else
		{
			juvMasterStatus = "";
		}
	
		juvMasterStatusId = aId;
	}

	/**
	 * @param b
	 */
	public void setListsSet(boolean b)
	{
		listsSet = b;
	}

	/**
	 * @param string
	 */
	public void setMaysi(String string)
	{
		maysi = string;
	}

	/**
	 * @param string
	 */
	public void setRiskAnalysis(String string)
	{
		riskAnalysis = string;
	}

	/**
	 * @param string
	 */
	public void setSecondaryAction(String string)
	{
		secondaryAction = string;
	}

	/**
	 * @param string
	 */
	public void setSelectedValue(String string)
	{
		selectedValue = string;
	}

	/**
	 * @param string
	 */
	public void setTitelIVcompleted(String string)
	{
		titelIVcompleted = string;
	}

	/**
	 * @param b
	 */
	public void setUpdate(boolean b)
	{
		update = b;
	}

	/**
	 * @return
	 */
	public String getCasefileID()
	{
		return casefileID;
	}

	/**
	 * @param string
	 */
	public void setCasefileID(String string)
	{
		casefileID = string;
	}

	/**
	 * @return
	 */
	public boolean isCasefileAlreadyActivated()
	{
		return casefileAlreadyActivated;
	}

	/**
	 * @param b
	 */
	public void setCasefileAlreadyActivated(boolean b)
	{
		casefileAlreadyActivated = b;
	}

	/**
	 * @return
	 */
	public Date getSupervisionEndDate()
	{
		return supervisionEndDate;
	}

	/**
	 * @param date
	 */
	public void setSupervisionEndDate(Date date)
	{
		supervisionEndDate = date;
	}
	
	/**
	 * @return
	 */
	public Collection getReferrals()
	{
		return referrals;
	}
	
	/**
	 * @param collection
	 */
	public void setReferrals(Collection collection)
	{
		referrals = collection;
	}

	/**
	 * @return
	 */
	public String getControllingReferral()
	{
		return controllingReferral;
	}

	/**
	 * @param string
	 */
	public void setControllingReferral(String string)
	{
		controllingReferral = string;
	}

	/**
	 * @return the courtOrderedProbationStartDateStr
	 */
	public String getCourtOrderedProbationStartDateStr() {
		return courtOrderedProbationStartDateStr;
	}
	
	/**
	 * @param courtOrderedProbationStartDateStr the courtOrderedProbationStartDateStr to set
	 */
	public void setCourtOrderedProbationStartDateStr(String string) {
		courtOrderedProbationStartDateStr = string;
	}
	
}// End Class
