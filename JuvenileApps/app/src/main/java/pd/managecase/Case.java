/*
 * Created on Jun 22, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.managecase;

import java.util.Date;

import mojo.km.persistence.PersistentObject;


/**
 * @author mpatino
 *
 * Tester entity class for VSAM mapping - do not delete.
 */
public class Case extends PersistentObject {
	private String caseNumber; 
	private String courtDivision;
	private String court;
	private String bondAmount;
	private Date filingDate;
	private String filingTime;
	private String probOfficer;
	private String caseStatus;
	private String defStatus;
	private Date completionDate;
	private String disposition;
	private Date nextSettingDate;
	private String offenseCode;
	private String sequenceNumber;
	private String instrumentType;
	private String caseLocInd;
	private String daSequenceNumber;
	private String daPosition;
	private String jpDismInd;
	private String pendingHoldAction;
	private Date initialBondSetDate;
	private String nextSettingCode;
	private String jpInitial;
	private String jpNumber;
	private String localOffenseCode;
	private String caseFolderLoc;

	/**
	 * @return bondAmount
	 */
	public String getBondAmount() {
		return bondAmount;
	}

	/**
	 * @return caseNumber
	 */
	public String getCaseNumber() {
		return caseNumber;
	}

	/**
	 * @return court
	 */
	public String getCourt() {
		return court;
	}

	/**
	 * @return courtDivision
	 */
	public String getCourtDivision() {
		return courtDivision;
	}

	/**
	 * @return filingDate
	 */
	public Date getFilingDate() {
		return filingDate;
	}

	/**
	 * @param string
	 */
	public void setBondAmount(String name) {
		bondAmount = name;
	}

	/**
	 * @param string
	 */
	public void setCaseNumber(String name) {
		caseNumber = name;
	}

	/**
	 * @param string
	 */
	public void setCourt(String name) {
		court = name;
	}

	/**
	 * @param string
	 */
	public void setCourtDivision(String name) {
		courtDivision = name;
	}

	/**
	 * @param string
	 */
	public void setFilingDate(Date name) {
		filingDate = name;
	}

	/**
	 * @return caseStatus
	 */
	public String getCaseStatus() {
		return caseStatus;
	}

	/**
	 * @return completionDate
	 */
	public Date getCompletionDate() {
		return completionDate;
	}

	/**
	 * @return defStatus
	 */
	public String getDefStatus() {
		return defStatus;
	}

	/**
	 * @return disposition
	 */
	public String getDisposition() {
		return disposition;
	}

	/**
	 * @return filingTime
	 */ 
	public String getFilingTime() {
		return filingTime;
	}

	/**
	 * @return nextSettingDate
	 */
	public Date getNextSettingDate() {
		return nextSettingDate;
	}

	/**
	 * @return probOfficer
	 */
	public String getProbOfficer() {
		return probOfficer;
	}

	/**
	 * @param string
	 */
	public void setCaseStatus(String string) {
		caseStatus = string;
	}

	/**
	 * @param string
	 */
	public void setCompletionDate(Date string) {
		completionDate = string;
	}

	/**
	 * @param string
	 */
	public void setDefStatus(String string) {
		defStatus = string;
	}

	/**
	 * @param string
	 */
	public void setDisposition(String string) {
		disposition = string;
	}

	/**
	 * @param string
	 */
	public void setFilingTime(String string) {
		filingTime = string;
	}

	/**
	 * @param string
	 */
	public void setNextSettingDate(Date string) {
		nextSettingDate = string;
	}

	/**
	 * @param string
	 */
	public void setProbOfficer(String string) {
		probOfficer = string;
	}

	/**
	 * @return offenseCode
	 */
	public String getOffenseCode() {
		return offenseCode;
	}

	/**
	 * @param string
	 */
	public void setOffenseCode(String string) {
		offenseCode = string;
	}

	/**
	 * @return sequenceNumber
	 */
	public String getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * @param string
	 */
	public void setSequenceNumber(String seqNum) {
		sequenceNumber = seqNum;
	}

	/**
	 * @return instrumentType
	 */
	public String getInstrumentType()
	{
		return instrumentType;
	}

	/**
	 * @param string
	 */
	public void setInstrumentType(String string)
	{
		instrumentType = string;
	}

	/**
	 * @return caseLocInd
	 */
	public String getCaseLocInd()
	{
		return caseLocInd;
	}

	/**
	 * @param string
	 */
	public void setCaseLocInd(String string)
	{
		caseLocInd = string;
	}

	/**
	 * @return caseFolderLoc
	 */
	public String getCaseFolderLoc()
	{
		return caseFolderLoc;
	}

	/**
	 * @return daPosition
	 */
	public String getDaPosition()
	{
		return daPosition;
	}

	/**
	 * @return daSequenceNumber
	 */
	public String getDaSequenceNumber()
	{
		return daSequenceNumber;
	}

	/**
	 * @return initialBondSetDate
	 */
	public Date getInitialBondSetDate()
	{
		return initialBondSetDate;
	}

	/**
	 * @return jpDismInd
	 */
	public String getJpDismInd()
	{
		return jpDismInd;
	}

	/**
	 * @return jpInitial
	 */
	public String getJpInitial()
	{
		return jpInitial;
	}

	/**
	 * @return jpNumber
	 */
	public String getJpNumber()
	{
		return jpNumber;
	}

	/**
	 * @return localOffenseCode
	 */
	public String getLocalOffenseCode()
	{
		return localOffenseCode;
	}

	/**
	 * @return nextSettingCode
	 */
	public String getNextSettingCode()
	{
		return nextSettingCode;
	}

	/**
	 * @return pendingHoldAction
	 */
	public String getPendingHoldAction()
	{
		return pendingHoldAction;
	}

	/**
	 * @param string
	 */
	public void setCaseFolderLoc(String string)
	{
		caseFolderLoc = string;
	}

	/**
	 * @param string
	 */
	public void setDaPosition(String string)
	{
		daPosition = string;
	}

	/**
	 * @param string
	 */
	public void setDaSequenceNumber(String string)
	{
		daSequenceNumber = string;
	}

	/**
	 * @param string
	 */
	public void setInitialBondSetDate(Date string)
	{
		initialBondSetDate = string;
	}

	/**
	 * @param string
	 */
	public void setJpDismInd(String string)
	{
		jpDismInd = string;
	}

	/**
	 * @param string
	 */
	public void setJpInitial(String string)
	{
		jpInitial = string;
	}

	/**
	 * @param string
	 */
	public void setJpNumber(String string)
	{
		jpNumber = string;
	}

	/**
	 * @param string
	 */
	public void setLocalOffenseCode(String string)
	{
		localOffenseCode = string;
	}

	/**
	 * @param string
	 */
	public void setNextSettingCode(String string)
	{
		nextSettingCode = string;
	}

	/**
	 * @param string
	 */
	public void setPendingHoldAction(String string)
	{
		pendingHoldAction = string;
	}

}


