/*
 * Created on June 11, 2013
 */
package messaging.juvenilecase.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * Returns a basic populated version juvenile transferred offense info
 * 
 */
public class JuvenileCasefileOffenseCodeResponseEvent extends ResponseEvent implements Comparable
{
	private String alphaCode;
	private String category;  // displays as severity level
	private String dpsCode;
	private String longDescription;
	private String numericCode;
	private String reportGroup;
	private String severity;
	private String severitySubType;
	private String severityType;
	private String shortDescription;
	private String inactiveInd;
	private String discontCode;//U.S 58355
	private String ageRestrict;
	private String aggravated;
	private String weapons;
	private String ncicCode;
	/**
	 * @return the alphaCode
	 */
	public String getAlphaCode() {
		return alphaCode;
	}
	/**
	 * @param alphaCode the alphaCode to set
	 */
	public void setAlphaCode(String alphaCode) {
		this.alphaCode = alphaCode;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the dpsCode
	 */
	public String getDpsCode() {
		return dpsCode;
	}
	/**
	 * @param dpsCode the dpsCode to set
	 */
	public void setDpsCode(String dpsCode) {
		this.dpsCode = dpsCode;
	}
	/**
	 * @return the longDescription
	 */
	public String getLongDescription() {
		return longDescription;
	}
	/**
	 * @param longDescription the longDescription to set
	 */
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	/**
	 * @return the numericCode
	 */
	public String getNumericCode() {
		return numericCode;
	}
	/**
	 * @param numericCode the numericCode to set
	 */
	public void setNumericCode(String numericCode) {
		this.numericCode = numericCode;
	}
	/**
	 * @return the reportGroup
	 */
	public String getReportGroup() {
		return reportGroup;
	}
	/**
	 * @param reportGroup the reportGroup to set
	 */
	public void setReportGroup(String reportGroup) {
		this.reportGroup = reportGroup;
	}
	/**
	 * @return the severity
	 */
	public String getSeverity() {
		return severity;
	}
	/**
	 * @param severity the severity to set
	 */
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	/**
	 * @return the severitySubType
	 */
	public String getSeveritySubType() {
		return severitySubType;
	}
	/**
	 * @param severitySubType the severitySubType to set
	 */
	public void setSeveritySubType(String severitySubType) {
		this.severitySubType = severitySubType;
	}
	/**
	 * @return the severityType
	 */
	public String getSeverityType() {
		return severityType;
	}
	/**
	 * @param severityType the severityType to set
	 */
	public void setSeverityType(String severityType) {
		this.severityType = severityType;
	}
	/**
	 * @return the shortDescription
	 */
	public String getShortDescription() {
		return shortDescription;
	}
	/**
	 * @param shortDescription the shortDescription to set
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	/**
	 * @return the discontCode
	 */
	public String getDiscontCode()
	{
	    return discontCode;
	}
	/**
	 * @param discontCode the discontCode to set
	 */
	public void setDiscontCode(String discontCode)
	{
	    this.discontCode = discontCode;
	}
	public String getInactiveInd()
	{
	    return inactiveInd;
	}
	public void setInactiveInd(String inactiveInd)
	{
	    this.inactiveInd = inactiveInd;
	}
	public String getAgeRestrict()
	{
	    return ageRestrict;
	}
	public void setAgeRestrict(String ageRestrict)
	{
	    this.ageRestrict = ageRestrict;
	}
	public String getAggravated()
	{
	    return aggravated;
	}
	public void setAggravated(String aggravated)
	{
	    this.aggravated = aggravated;
	}
	public String getWeapons()
	{
	    return weapons;
	}
	public void setWeapons(String weapons)
	{
	    this.weapons = weapons;
	}
	public String getNcicCode()
	{
	    return ncicCode;
	}
	public void setNcicCode(String ncicCode)
	{
	    this.ncicCode = ncicCode;
	}
	@Override
	public int compareTo(Object o)
	{
	    // TODO Auto-generated method stub
	    return 0;
	}

}