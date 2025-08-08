/*
' * Created on Feb 7, 2005
 */
package messaging.codetable.criminal.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dnikolis
 */
public class JuvenileOffenseCodeResponseEvent extends ResponseEvent
{
	private String category; //2. AND Offense Catagory = CF, F1, F2, or F3
	private String reportGroup;
	private String shortDescription;
	private String dpsOffenseCode;
	private String numericCode;
	private String offenseCode;
	private String longDescription;
	private String citationCode;
	private String severity;        //3. or Severity code = 06, 07, 08, or 09, then Violent Felony = Yes
	private String offenseCategory; 
	private String severitySubtype; //1. Severity Subtype indicator =V 
	private String inactiveInd;
	private String citationSource; // added for user-story 32111
	private String discontCode; //U.S 58355
	private String ageRestrict;
	private String severityType;// US 184961
	
	

	/**
	 * @return
	 */
	public String getCategory()
	{
		return category;
	}

	/**
	 * @return
	 */
	public String getCitationCode()
	{
		return citationCode;
	}

	/**
	 * @return
	 */
	public String getDpsOffenseCode()
	{
		return dpsOffenseCode;
	}

	/**
	 * @return
	 */
	public String getLongDescription()
	{
		return longDescription;
	}

	/**
	 * @return
	 */
	public String getNumericCode()
	{
		return numericCode;
	}

	/**
	 * @return
	 */
	public String getOffenseCode()
	{
		return offenseCode;
	}

	/**
	 * @return
	 */
	public String getReportGroup()
	{
		return reportGroup;
	}

	/**
	 * @return
	 */
	public String getSeverity()
	{
		return severity;
	}

	/**
	 * @return
	 */
	public String getShortDescription()
	{
		return shortDescription;
	}

	/**
	 * @param category
	 */
	public void setCategory(String category)
	{
		this.category = category;
	}

	/**
	 * @param citationCode
	 */
	public void setCitationCode(String citationCode)
	{
		this.citationCode = citationCode;
	}

	/**
	 * @param dpsOffenseCode
	 */
	public void setDpsOffenseCode(String dpsOffenseCode)
	{
		this.dpsOffenseCode = dpsOffenseCode;
	}

	/**
	 * @param longDescription
	 */
	public void setLongDescription(String longDescription)
	{
		this.longDescription = longDescription;
	}

	/**
	 * @param numericCode
	 */
	public void setNumericCode(String numericCode)
	{
		this.numericCode = numericCode;
	}

	/**
	 * @param offenseCode
	 */
	public void setOffenseCode(String offenseCode)
	{
		this.offenseCode = offenseCode;
	}

	/**
	 * @param reportGroup
	 */
	public void setReportGroup(String reportGroup)
	{
		this.reportGroup = reportGroup;
	}

	/**
	 * @param severity
	 */
	public void setSeverity(String severity)
	{
		this.severity = severity;
	}

	/**
	 * @param shortDescription
	 */
	public void setShortDescription(String shortDescription)
	{
		this.shortDescription = shortDescription;
	}

	public void setOffenseCategory(String offenseCategory) {
		this.offenseCategory = offenseCategory;
	}

	public String getOffenseCategory() {
		return offenseCategory;
	}

	public void setSeveritySubtype(String severitySubtype) {
		this.severitySubtype = severitySubtype;
	}

	public String getSeveritySubtype() {
		return severitySubtype;
	}

	public String getInactiveInd() {
		return inactiveInd;
	}

	public void setInactiveInd(String inactiveInd) {
		this.inactiveInd = inactiveInd;
	}

	/**
	 * @return the citationSource
	 */
	public String getCitationSource() {
		return citationSource;
	}

	/**
	 * @param citationSource the citationSource to set
	 */
	public void setCitationSource(String citationSource) {
		this.citationSource = citationSource;
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

	public String getAgeRestrict()
	{
	    return ageRestrict;
	}

	public void setAgeRestrict(String ageRestrict)
	{
	    this.ageRestrict = ageRestrict;
	}
	public String getSeverityType()
	{
	    return severityType;
	}

	public void setSeverityType(String severityType)
	{
	    this.severityType = severityType;
	}
	
}