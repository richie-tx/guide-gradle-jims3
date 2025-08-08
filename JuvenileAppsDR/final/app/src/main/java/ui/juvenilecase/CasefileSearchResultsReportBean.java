/**
 * 
 */
package ui.juvenilecase;

import java.util.Collection;

/**
 * @author ugopinath
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CasefileSearchResultsReportBean {

	private int numberOfCasefiles;
	
	private Collection casefileSearchResults;
	
	private String statusSearchType;
	
	//added for  US 153691
	private String officerLastName;
	private String officerFirstName;
	private String officerMiddleName;
	

	/**
	 * @return the numberOfCasefiles
	 */
	public int getNumberOfCasefiles() {
		return numberOfCasefiles;
	}

	/**
	 * @param numberOfCasefiles the numberOfCasefiles to set
	 */
	public void setNumberOfCasefiles(int numberOfCasefiles) {
		this.numberOfCasefiles = numberOfCasefiles;
	}

	/**
	 * @return the casefileSearchResults
	 */
	public Collection getCasefileSearchResults() {
		return casefileSearchResults;
	}

	/**
	 * @param casefileSearchResults the casefileSearchResults to set
	 */
	public void setCasefileSearchResults(Collection casefileSearchResults) {
		this.casefileSearchResults = casefileSearchResults;
	}

	/**
	 * @return the statusSearchType
	 */
	public String getStatusSearchType() {
		return statusSearchType;
	}

	/**
	 * @param statusSearchType the statusSearchType to set
	 */
	public void setStatusSearchType(String statusSearchType) {
		this.statusSearchType = statusSearchType;
	}

	public String getOfficerLastName()
	{
	    return officerLastName;
	}

	public void setOfficerLastName(String officerLastName)
	{
	    this.officerLastName = officerLastName;
	}

	public String getOfficerFirstName()
	{
	    return officerFirstName;
	}

	public void setOfficerFirstName(String officerFirstName)
	{
	    this.officerFirstName = officerFirstName;
	}

	public String getOfficerMiddleName()
	{
	    return officerMiddleName;
	}

	public void setOfficerMiddleName(String officerMiddleName)
	{
	    this.officerMiddleName = officerMiddleName;
	}

}
