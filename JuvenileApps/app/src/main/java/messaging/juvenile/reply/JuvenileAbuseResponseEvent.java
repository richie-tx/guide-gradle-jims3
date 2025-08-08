/*
 * Created on Jun 17, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenile.reply;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import pd.juvenile.JuvenileAbusePerpatrator;

import mojo.km.messaging.ResponseEvent;

/**
 * @author jfisher
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileAbuseResponseEvent extends ResponseEvent
{
	private final String DATE_FORMAT_1 = "M/d/yyyy";
	private String abuseId;
	private String traitTypeId;
	private String traitTypeName;
	private String informationBasisId;
	private String abuseLevelCode;
	private String abuseLevelDescription;
	private String treatment;
	private Date entryDate;
	private String relationshipToJuvenileCode;
	private String allegedAbuserRelationship;
	
	private String relationshipToJuvenileDescription;
	private String namewithRelationship;
	
	private String lastName;
	private String firstName;
	private String middleName;
	private boolean cpsInvolvement;
	private boolean cpsCustody;
	
	private String abuseDetails;
	private String abuseEvent;
	private String memberId;
	private String contactId;
	
	private String abusePerpId;
	
	private String abuseInformationBasisCode;
	private String abuseInformationBasisDescription;
	private Collection<JuvenileAbusePerpatrator> abusePrps;
	
	//added for US 40635
	private String informationSrcCd; 
	private String informationSrcDesc;
	//
	private String juvenileNumber;
	private String cpsCaseNumber;
	
	

	/**
	 * @return
	 */
	public Date getEntryDate()
	{
		return entryDate;
	}

	public String getEntryDateString()
	{
		String dateString = null;
		if (entryDate != null)
		{
			SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT_1);
			dateString = fmt.format(entryDate);
		}
		return dateString;
	}

	/**
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @param date
	 */
	public void setEntryDate(Date date)
	{
		entryDate = date;
	}

	/**
	 * @param string
	 */
	public void setFirstName(String string)
	{
		firstName = string;
	}

	/**
	 * @param string
	 */
	public void setLastName(String string)
	{
		lastName = string;
	}

	/**
	 * @param string
	 */
	public void setMiddleName(String string)
	{
		middleName = string;
	}

	/**
	 * @return
	 */
	public String getAbuseLevelCode()
	{
		return abuseLevelCode;
	}

	/**
	 * @return
	 */
	public String getAbuseLevelDescription()
	{
		return abuseLevelDescription;
	}

	/**
	 * @return
	 */
	public String getTreatment()
	{
		return treatment;
	}



	/**
	 * @return
	 */
//	public String getAbuseTypeDescription()
//	{
//		return abuseTypeDescription;
//	}

	/**
	 * @param string
	 */
	public void setAbuseLevelCode(String string)
	{
		abuseLevelCode = string;
	}

	/**
	 * @param string
	 */
	public void setAbuseLevelDescription(String string)
	{
		abuseLevelDescription = string;
	}

	/**
	 * @param string
	 */
	public void setTreatment(String string)
	{
		treatment = string;
	}



	/**
	 * @param string
	 */
//	public void setAbuseTypeDescription(String string)
//	{
//		abuseTypeDescription = string;
//	}

	/**
	 * @return
	 */
	public String getRelationshipToJuvenileCode()
	{
		return relationshipToJuvenileCode;
	}

	/**
	 * @return
	 */
	public String getRelationshipToJuvenileDescription()
	{
		return relationshipToJuvenileDescription;
	}

	/**
	 * @param string
	 */
	public void setRelationshipToJuvenileCode(String string)
	{
		relationshipToJuvenileCode = string;
	}

	/**
	 * @param string
	 */
	public void setRelationshipToJuvenileDescription(String string)
	{
		relationshipToJuvenileDescription = string;
	}

	/**
	 * @param object
	 */
	public void setAbuseId(String abuseId)
	{
		this.abuseId = abuseId;

	}

	/**
	 * @return
	 */
	public String getAbuseId()
	{
		return this.abuseId;
	}

	/**
	 * @return
	 */
	public String getAbuseDetails()
	{
		return abuseDetails;
	}

	/**
	 * @return
	 */
	public String getAbuseEvent()
	{
		return abuseEvent;
	}

	/**
	 * @return
	 */
	public boolean isCpsInvolvement()
	{
		return cpsInvolvement;
	}

	/**
	 * @param string
	 */
	public void setAbuseDetails(String string)
	{
		abuseDetails = string;
	}

	/**
	 * @param string
	 */
	public void setAbuseEvent(String string)
	{
		abuseEvent = string;
	}

	/**
	 * @param b
	 */
	public void setCpsInvolvement(boolean b)
	{
		cpsInvolvement = b;
	}

	/**
	 * @return
	 */





	/**
	 * @return
	 */
	public String getTraitTypeId()
	{
		return traitTypeId;
	}

	/**
	 * @return
	 */
	public String getTraitTypeName()
	{
		return traitTypeName;
	}



	/**
	 * @param string
	 */
	public void setTraitTypeId(String string)
	{
		traitTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setTraitTypeName(String string)
	{
		traitTypeName = string;
	}

	/**
	 * @return Returns the informationBasisId.
	 */
	public String getInformationBasisId() {
		return informationBasisId;
	}
	/**
	 * @param informationBasisId The informationBasisId to set.
	 */
	public void setInformationBasisId(String informationBasisId) {
		this.informationBasisId = informationBasisId;
	}
	/**
	 * @return Returns the contactId.
	 */
	public String getContactId() {
		return contactId;
	}
	/**
	 * @param contactId The contactId to set.
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	/**
	 * @return Returns the memberId.
	 */
	public String getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId The memberId to set.
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public String perpId(){
		if(memberId!=null && !memberId.equals("")){
			return "M"+memberId;
		}
		else if(contactId!=null && !contactId.equals("")){
			return "C"+contactId;
		}
		else{
			return "";
		}
	}
	/**
	 * @return Returns the abuseInformationBasisCode.
	 */
	public String getAbuseInformationBasisCode() {
		return abuseInformationBasisCode;
	}
	/**
	 * @param abuseInformationBasisCode The abuseInformationBasisCode to set.
	 */
	public void setAbuseInformationBasisCode(String abuseInformationBasisCode) {
		this.abuseInformationBasisCode = abuseInformationBasisCode;
	}
	/**
	 * @return Returns the abuseInformationBasisDescription.
	 */
	public String getAbuseInformationBasisDescription() {
		return abuseInformationBasisDescription;
	}
	/**
	 * @param abuseInformationBasisDescription The abuseInformationBasisDescription to set.
	 */
	public void setAbuseInformationBasisDescription(String abuseInformationBasisDescription) {
		this.abuseInformationBasisDescription = abuseInformationBasisDescription;
	}

	public String getAbusePerpId() {
		return abusePerpId;
	}

	public void setAbusePerpId(String abusePerpId) {
		this.abusePerpId = abusePerpId;
	}

	public Collection<JuvenileAbusePerpatrator> getAbusePrps() {
		return abusePrps;
	}

	public void setAbusePrps(Collection<JuvenileAbusePerpatrator> abusePrps) {
		this.abusePrps = abusePrps;
	}

	public String getInformationSrcCd() {
		return informationSrcCd;
	}

	public void setInformationSrcCd(String informationSrcCd) {
		this.informationSrcCd = informationSrcCd;
	}

	public String getInformationSrcDesc() {
		return informationSrcDesc;
	}

	public void setInformationSrcDesc(String informationSrcDesc) {
		this.informationSrcDesc = informationSrcDesc;
	}
	public boolean isCpsCustody()
	{
	    return cpsCustody;
	}

	public void setCpsCustody(boolean cpsCustody)
	{
	    this.cpsCustody = cpsCustody;
	}
	public String getAllegedAbuserRelationship()
	{
	    return allegedAbuserRelationship;
	}

	public void setAllegedAbuserRelationship(String allegedAbuserRelationship)
	{
	    this.allegedAbuserRelationship = allegedAbuserRelationship;
	}
	public String getNamewithRelationship()
	{
	    return namewithRelationship;
	}

	public void setNamewithRelationship(String namewithRelationship)
	{
	    this.namewithRelationship = namewithRelationship;
	}
	public String getJuvenileNumber()
	{
	    return juvenileNumber;
	}

	public void setJuvenileNumber(String juvenileNumber)
	{
	    this.juvenileNumber = juvenileNumber;
	}

	public String getCpsCaseNumber()
	{
	    return cpsCaseNumber;
	}

	public void setCpsCaseNumber(String cpsCaseNumber)
	{
	    this.cpsCaseNumber = cpsCaseNumber;
	}
	
	
}
