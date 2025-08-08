/*
 * Created on Dec 2, 2004
 */
package messaging.codetable.criminal.reply;

import messaging.codetable.reply.ICode;
import mojo.km.messaging.ResponseEvent;
import java.util.Comparator;


public class JuvenileFacilityResponseEvent extends ResponseEvent implements Comparable, ICode
{
	private String department;
	private String juvTJPCPlacementType; //added for JIMS200077404
	private String code;
	private String description;
	private String address;
	private String city;
	private String zip;
	private String levelOfCare;
	//<KISHORE>JIMS200059759 : MJCW - Common App Section 4 phone and LOC are missing
	private String facilityPhone;
	private String contactPerson; // added for U.S #26457
	private String juvPlacementType; // added for U.S #26457
	
	//Added for Facility Report
	private String inactiveInd;
	
	//added for facility admit
	private String descriptionWithCode;
	private String codeWithJuvTJPCPlacementType;
	private String refereeCourt;
	private String locationOneLabel;
	private String locationTwoLabel;
	private String locationThreeLabel;
	
	private String secureStatus;
	private String rectype;
	private String avgCostPerDay;
	private String serviceType;
	private String vendor;
	private int tjjdFacilityId;
	private String tjjdFundingSrc;
	private String riskAnalysisId;
	
	/**
	 * @return Returns the address.
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address The address to set.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code The code to set.
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return Returns the department.
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department The department to set.
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the juvTJPCPlacementType
	 */
	public String getJuvTJPCPlacementType() {
		return juvTJPCPlacementType;
	}
	/**
	 * @param juvTJPCPlacementType the juvTJPCPlacementType to set
	 */
	public void setJuvTJPCPlacementType(String juvTJPCPlacementType) {
		this.juvTJPCPlacementType = juvTJPCPlacementType;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the zip.
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * @param zip The zip to set.
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	public int compareTo(Object obj) throws ClassCastException {
		JuvenileFacilityResponseEvent evt = (JuvenileFacilityResponseEvent)obj;
		return description.compareTo(evt.getDescription());
	}
	
	public String getLevelOfCare() {
		return levelOfCare;
	}
	public void setLevelOfCare(String levelOfCare) {
		this.levelOfCare = levelOfCare;
	}
	/**
	 * @return the facilityPhone
	 */
	public String getFacilityPhone() {
		return facilityPhone;
	}
	/**
	 * @param facilityPhone the facilityPhone to set
	 */
	public void setFacilityPhone(String facilityPhone) {
		this.facilityPhone = facilityPhone;
	}
	
	/**
	 * @param inactiveInd The inactiveInd to set.
	 */
	public void setInactiveInd(String inactiveInd) {
		this.inactiveInd = inactiveInd;
	}
	/**
	 * @return Returns the inactiveInd.
	 */
	public String getInactiveInd() {
		return inactiveInd;
	}
	
	/**
	 * @return Returns the descriptionWithCode.
	 */
	public String getDescriptionWithCode() {
		return descriptionWithCode;
	}
	/**
	 * @param descriptionWithCode The descriptionWithCode to set.
	 */
	public void setDescriptionWithCode(String descriptionWithCode) {
		this.descriptionWithCode = descriptionWithCode;
	}
	public void setCodeWithJuvTJPCPlacementType(
			String codeWithJuvTJPCPlacementType) {
		this.codeWithJuvTJPCPlacementType = codeWithJuvTJPCPlacementType;
	}
	public String getCodeWithJuvTJPCPlacementType() {
		return codeWithJuvTJPCPlacementType;
	}
	public void setRefereeCourt(String refereeCourt) {
		this.refereeCourt = refereeCourt;
	}
	public String getRefereeCourt() {
		return refereeCourt;
	}

	public void setLocationTwoLabel(String locationTwoLabel) {
		this.locationTwoLabel = locationTwoLabel;
	}
	public String getLocationTwoLabel() {
		return locationTwoLabel;
	}
	public void setLocationThreeLabel(String locationThreeLabel) {
		this.locationThreeLabel = locationThreeLabel;
	}
	public String getLocationThreeLabel() {
		return locationThreeLabel;
	}
	public void setLocationOneLabel(String locationOneLabel) {
		this.locationOneLabel = locationOneLabel;
	}
	public String getLocationOneLabel() {
		return locationOneLabel;
	}
	public String getSecureStatus() {
		return secureStatus;
	}
	public void setSecureStatus(String secureStatus) {
		this.secureStatus = secureStatus;
	}
	/**
	 * @return the contactPerson
	 */
	public String getContactPerson() {
		return contactPerson;
	}
	/**
	 * @param contactPerson the contactPerson to set
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	/**
	 * @return the juvPlacementType
	 */
	public String getJuvPlacementType() {
		return juvPlacementType;
	}
	/**
	 * @param juvPlacementType the juvPlacementType to set
	 */
	public void setJuvPlacementType(String juvPlacementType) {
		this.juvPlacementType = juvPlacementType;
	}
	
	
	
	public String getRectype()
	{
	    return rectype;
	}
	public void setRectype(String rectype)
	{
	    this.rectype = rectype;
	}
	public String getAvgCostPerDay()
	{
	    return avgCostPerDay;
	}
	public void setAvgCostPerDay(String avgCostPerDay)
	{
	    this.avgCostPerDay = avgCostPerDay;
	}
	public String getServiceType()
	{
	    return serviceType;
	}
	public void setServiceType(String serviceType)
	{
	    this.serviceType = serviceType;
	}
	public String getVendor()
	{
	    return vendor;
	}
	public void setVendor(String vendor)
	{
	    this.vendor = vendor;
	}
	public int getTjjdFacilityId()
	{
	    return tjjdFacilityId;
	}
	public void setTjjdFacilityId(int tjjdFacilityId)
	{
	    this.tjjdFacilityId = tjjdFacilityId;
	}
	public String getTjjdFundingSrc()
	{
	    return tjjdFundingSrc;
	}
	public void setTjjdFundingSrc(String tjjdFundingSrc)
	{
	    this.tjjdFundingSrc = tjjdFundingSrc;
	}
	
	public String getRiskAnalysisId()
	{
	    return riskAnalysisId;
	}
	public void setRiskAnalysisId(String riskAnalysisId)
	{
	    this.riskAnalysisId = riskAnalysisId;
	}



	public static Comparator CodeComparator = new Comparator(){
		public int compare(Object obj1, Object obj2){
			
			if(obj1==null || !(obj1 instanceof JuvenileFacilityResponseEvent))
				return 0;
			if(obj2==null || !(obj2 instanceof JuvenileFacilityResponseEvent))
				return 0;
			
			String code1= ((JuvenileFacilityResponseEvent)obj1).getDescription();
			String code2= ((JuvenileFacilityResponseEvent)obj2).getDescription();
			if(code1==null) return 1;
			if(code2==null) return -1;
			
			return code1.compareTo(code2);
			
		}
	};

}
