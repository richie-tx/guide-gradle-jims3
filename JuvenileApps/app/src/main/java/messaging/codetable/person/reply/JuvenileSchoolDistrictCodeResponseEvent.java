/*
 * Created on Feb 7, 2005
 */
package messaging.codetable.person.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dnikolis
 */
public class JuvenileSchoolDistrictCodeResponseEvent extends ResponseEvent implements Comparable
{
	private String teaCode;
	private String schoolDescription;
	private String inactiveInd;
	private String districtCode;
	private String districtDescription;
	private String schoolCode;
	private String oid;
	private String instructionType;
	private String charterType;
	private String streetName;
	private String city;
	private String state;
	private String zip;
	private String phoneNum;
	private String schoolDisplayLiteral;
	private String subGroupInd;
	/**
	 * @return
	 */
	public String getDistrictCode()
	{
		return districtCode;
	}

	/**
	 * @return
	 */
	public String getDistrictDescription()
	{
		return districtDescription;
	}

	/**
	 * @return
	 */
	public String getInactiveInd()
	{
		return inactiveInd;
	}

	/**
	 * @return
	 */
	public String getSchoolCode()
	{
		return schoolCode;
	}

	/**
	 * @return
	 */
	public String getSchoolDescription()
	{
		return schoolDescription;
	}

	/**
	 * @return
	 */
	public String getTeaCode()
	{
		return teaCode;
	}

	/**
	 * @param districtCode
	 */
	public void setDistrictCode(String districtCode)
	{
		this.districtCode = districtCode;
	}

	/**
	 * @param districtDescription
	 */
	public void setDistrictDescription(String districtDescription)
	{
		this.districtDescription = districtDescription;
	}

	/**
	 * @param inactiveInd
	 */
	public void setInactiveInd(String inactiveInd)
	{
		this.inactiveInd = inactiveInd;
	}

	/**
	 * @param schoolCode
	 */
	public void setSchoolCode(String schoolCode)
	{
		this.schoolCode = schoolCode;
	}

	/**
	 * @param schoolDescription
	 */
	public void setSchoolDescription(String schoolDescription)
	{		
		this.schoolDescription = schoolDescription;
	}

	/**
	 * @param teaCode
	 */
	public void setTeaCode(String teaCode)
	{
		this.teaCode = teaCode;
	}

	/**
	 * @return Returns the oid.
	 */
	public String getOid() {
		return oid;
	}
	/**
	 * @param oid The oid to set.
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}
	/** Sorts by school name
	 * @see Comparable#compareTo(Object)
	 */
	public int compareTo(Object schoolObj)
	{
		JuvenileSchoolDistrictCodeResponseEvent school = (JuvenileSchoolDistrictCodeResponseEvent) schoolObj;
		return this.getSchoolDescription().compareTo(school.getSchoolDescription());
	}

	public String getInstructionType() {
		return instructionType;
	}

	public void setInstructionType(String instructionType) {
		this.instructionType = instructionType;
	}

	public String getCharterType() {
		return charterType;
	}

	public void setCharterType(String charterType) {
		this.charterType = charterType;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public String getSchoolDisplayLiteral() {
		String instructType = this.getInstructionType();
		if (instructType != null && (!instructType.equals("")))
		{
			if(!instructType.equals("REGULAR INSTRUCTIONAL"))
			{
			    schoolDisplayLiteral = this.getSchoolDescription() + ":  " + this.getInstructionType();
			} else {
				schoolDisplayLiteral = this.getSchoolDescription();
			}
		} else {
			schoolDisplayLiteral = this.getSchoolDescription();
		}
		return schoolDisplayLiteral;	
	}
	
	public void setSchoolDisplayLiteral(String schoolDisplayLiteral) {
		this.schoolDisplayLiteral = schoolDisplayLiteral;
	}

	public void setSubGroupInd(String subGroupInd) {
		this.subGroupInd = subGroupInd;
	}

	public String getSubGroupInd() {
		return subGroupInd;
	}
}