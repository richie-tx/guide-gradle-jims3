/*
 * Created on Dec 4, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.interviewinfo.to;

import java.util.Date;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MentalHealthTestResultTO implements Comparable{
	public static final String IQ_TEST = "IQ TEST";
	public static final String DMS_IV_TEST = "DSM IV TEST";
	public static final String ACHIEVEMENT_TEST = "ACHIEVEMENT TEST";
	public static final String ADAPTIVE_FUNCTIONING_TEST = "ADAPTIVE FUNCTIONING TEST";
	
	private String testName;
	private Date testDate;
	private String serviceProviderName;
	private String testType;
	
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (o == null)
            return 1; // this makes any null objects go to the bottom change this to -1 if you want
        // the top of the list
        if (this.testDate == null)
            return -1; // this makes any null objects go to the bottom change this to 1 if you want
        // the top of the list
        MentalHealthTestResultTO evt = (MentalHealthTestResultTO) o;
        return evt.getTestDate().compareTo(testDate);
	}
}
