/*
 * Created on June 12, 2013
 */
package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * @author CShimek
 */
public class TransferredOffenseForm extends ActionForm
{
	private String adjudicationDateStr;
	private String alphaCodeId;
	private String categoryId;
	private String confirmMsg;
	private String countyId;
	private String dpsCodeId;
	private String errMessage;
	private String fromPage;
	private String juvenileNumber;
	private String offenseId;
	private String offenseDateStr;
	private String offenseDesc;
	private String selectedTransfer;
	private String selectedOffense;
	private String selectedValue;
	private String shortDesc;
	private String validateMsg;
	private String personId;
	private String probationStartDateStr;
	private String probationEndDateStr;
	
	
	private List countiesList;
	private List existingTransferredOffensesList;
	private List availableTransferredOffenseReferralList;
	private List newTransferredOffensesList;
	private List offensesList;
	private List offenseResultList;
	
	private Collection codetableDataList;


	public void clear( )
	{
		this.adjudicationDateStr = null;
		this.probationStartDateStr = null;
		this.probationEndDateStr = null;
		this.selectedOffense="";
		this.confirmMsg = null;
		this.countyId = null;
		this.errMessage = "";
//		this.fromPage = null;
		this.offenseId = null;
		this.offenseDesc = "";
		this.offenseDateStr = null;
		this.selectedTransfer = null;
		this.validateMsg = "";
		this.newTransferredOffensesList = new ArrayList();
		this.personId="";
		this.countiesList = new ArrayList(); ////#33358 changes
	}

	/**
	 * @return the adjudicationDateStr
	 */
	public String getAdjudicationDateStr() {
		return adjudicationDateStr;
	}

	/**
	 * @param adjudicationDateStr the adjudicationDateStr to set
	 */
	public void setAdjudicationDateStr(String adjudicationDateStr) {
		this.adjudicationDateStr = adjudicationDateStr;
	}

	/**
	 * @return the alphaCodeId
	 */
	public String getAlphaCodeId() {
		return alphaCodeId;
	}

	/**
	 * @param alphaCodeId the alphaCodeId to set
	 */
	public void setAlphaCodeId(String alphaCodeId) {
		this.alphaCodeId = alphaCodeId;
	}

	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the confirmMsg
	 */
	public String getConfirmMsg() {
		return confirmMsg;
	}

	/**
	 * @param confirmMsg the confirmMsg to set
	 */
	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}

	/**
	 * @return the countyId
	 */
	public String getCountyId() {
		return countyId;
	}

	/**
	 * @param countyId the countyId to set
	 */
	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}

	/**
	 * @return the dpsCodeId
	 */
	public String getDpsCodeId() {
		return dpsCodeId;
	}

	/**
	 * @param dpsCodeId the dpsCodeId to set
	 */
	public void setDpsCodeId(String dpsCodeId) {
		this.dpsCodeId = dpsCodeId;
	}

	/**
	 * @return the errMessage
	 */
	public String getErrMessage() {
		return errMessage;
	}

	/**
	 * @param errMessage the errMessage to set
	 */
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	/**
	 * @return the fromPage
	 */
	public String getFromPage() {
		return fromPage;
	}

	/**
	 * @param fromPage the fromPage to set
	 */
	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}

	/**
	 * @return the juvenileNumber
	 */
	public String getJuvenileNumber() {
		return juvenileNumber;
	}

	/**
	 * @param juvenileNumber the juvenileNumber to set
	 */
	public void setJuvenileNumber(String juvenileNumber) {
		this.juvenileNumber = juvenileNumber;
	}

	/**
	 * @return the offenseId
	 */
	public String getOffenseId() {
		return offenseId;
	}

	/**
	 * @param offenseId the offenseId to set
	 */
	public void setOffenseId(String offenseId) {
		this.offenseId = offenseId;
	}

	/**
	 * @return the offenseDateStr
	 */
	public String getOffenseDateStr() {
		return offenseDateStr;
	}

	/**
	 * @param offenseDateStr the offenseDateStr to set
	 */
	public void setOffenseDateStr(String offenseDateStr) {
		this.offenseDateStr = offenseDateStr;
	}

	/**
	 * @return the offenseDesc
	 */
	public String getOffenseDesc() {
		return offenseDesc;
	}

	/**
	 * @param offenseDesc the offenseDesc to set
	 */
	public void setOffenseDesc(String offenseDesc) {
		this.offenseDesc = offenseDesc;
	}

	/**
	 * @return the selectedTransfer
	 */
	public String getSelectedTransfer() {
		return selectedTransfer;
	}

	/**
	 * @param selectedTransfer the selectedTransfer to set
	 */
	public void setSelectedTransfer(String selectedTransfer) {
		this.selectedTransfer = selectedTransfer;
	}

	/**
	 * @return the selectedValue
	 */
	public String getSelectedValue() {
		return selectedValue;
	}

	/**
	 * @param selectedValue the selectedValue to set
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	/**
	 * @return the shortDesc
	 */
	public String getShortDesc() {
		return shortDesc;
	}

	/**
	 * @param shortDesc the shortDesc to set
	 */
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	/**
	 * @return the validateMsg
	 */
	public String getValidateMsg() {
		return validateMsg;
	}

	/**
	 * @param validateMsg the validateMsg to set
	 */
	public void setValidateMsg(String validateMsg) {
		this.validateMsg = validateMsg;
	}

	/**
	 * @return the countiesList
	 */
	public List getCountiesList() {
		return countiesList;
	}

	/**
	 * @param countiesList the countiesList to set
	 */
	public void setCountiesList(List countiesList) {
		this.countiesList = countiesList;
	}

	/**
	 * @return the existingTransferredOffensesList
	 */
	public List getExistingTransferredOffensesList() {
		return existingTransferredOffensesList;
	}

	/**
	 * @param existingTransferredOffensesList the existingTransferredOffensesList to set
	 */
	public void setExistingTransferredOffensesList(	List existingTransferredOffensesList) {
		this.existingTransferredOffensesList = existingTransferredOffensesList;
	}

	/**
	 * @return the availableTransferredOffenseReferralList
	 */
	public List getAvailableTransferredOffenseReferralList() {
		return availableTransferredOffenseReferralList;
	}

	/**
	 * @param availableTransferredOffenseReferralList the availableTransferredOffenseReferralList to set
	 */
	public void setAvailableTransferredOffenseReferralList(	List availableTransferredOffenseReferralList) {
		this.availableTransferredOffenseReferralList = availableTransferredOffenseReferralList;
	}

	/**
	 * @return the juvenileTransferredOffensesList
	 *
	public List getJuvenileTransferredOffensesList() {
		return juvenileTransferredOffensesList;
	}

	/**
	 * @param juvenileTransferredOffensesList the juvenileTransferredOffensesList to set
	 *
	public void setJuvenileTransferredOffensesList(
			List juvenileTransferredOffensesList) {
		this.juvenileTransferredOffensesList = juvenileTransferredOffensesList;
	}

	/**
	 * @return the newTransferredOffensesList
	 */
	public List getNewTransferredOffensesList() {
		return newTransferredOffensesList;
	}

	/**
	 * @param newTransferredOffensesList the newTransferredOffensesList to set
	 */
	public void setNewTransferredOffensesList(List newTransferredOffensesList) {
		this.newTransferredOffensesList = newTransferredOffensesList;
	}

	/**
	 * @return the offensesList
	 */
	public List getOffensesList() {
		return offensesList;
	}

	/**
	 * @param offensesList the offensesList to set
	 */
	public void setOffensesList(List offensesList) {
		this.offensesList = offensesList;
	}

	/**
	 * @return the offenseResultList
	 */
	public List getOffenseResultList() {
		return offenseResultList;
	}

	/**
	 * @param offenseResultList the offenseResultList to set
	 */
	public void setOffenseResultList(List offenseResultList) {
		this.offenseResultList = offenseResultList;
	}

	/**
	 * @return the codetableDataList
	 */
	public Collection getCodetableDataList() {
		return codetableDataList;
	}

	/**
	 * @param codetableDataList the codetableDataList to set
	 */
	public void setCodetableDataList(Collection codetableDataList) {
		this.codetableDataList = codetableDataList;
	}
	
	/**
	 * @return
	 */
	public String getPersonId() {
		return personId;
	}

	/**
	 * @param personId
	 */
	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getSelectedOffense() {
		return selectedOffense;
	}

	public void setSelectedOffense(String selectedOffense) {
		this.selectedOffense = selectedOffense;
	}

	public String getProbationStartDateStr()
	{
	    return probationStartDateStr;
	}

	public void setProbationStartDateStr(String probationStartDateStr)
	{
	    this.probationStartDateStr = probationStartDateStr;
	}

	public String getProbationEndDateStr()
	{
	    return probationEndDateStr;
	}

	public void setProbationEndDateStr(String probationEndDateStr)
	{
	    this.probationEndDateStr = probationEndDateStr;
	}


}