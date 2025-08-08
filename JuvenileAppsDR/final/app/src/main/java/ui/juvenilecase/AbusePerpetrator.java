/*
 * Created on Jun 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase;

import ui.common.Name;

/**
 * @author jjose
 *
 * Class hold the abuse perpetrator
 */
public class AbusePerpetrator implements Comparable{

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if(o==null || !(o instanceof AbusePerpetrator))
			return -1;
		AbusePerpetrator incomingPerp=(AbusePerpetrator)o;
		if(incomingPerp.getDescription()==null)
			return -1;
		else if(this.getDescription()==null)
			return 1;
		else{
			return this.getDescription().compareTo(incomingPerp.getDescription());
		}
	}
	public static String CONTACT_PREFIX="C";
	public static String MEMBER_PREFIX="M";
	
	private String perpetratorId="";
	private String memberId="";
	private String contactId="";
	private Name name=new Name("","","");;
	private String relationshipToJuvId="";
	private String relationshipToJuv="";
	
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
		if(this.contactId!=null && !this.contactId.equals("")){
			perpetratorId=CONTACT_PREFIX + contactId;
		}
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
		if(this.memberId!=null && !this.memberId.equals("")){
			perpetratorId=MEMBER_PREFIX + memberId;
		}
	}
	/**
	 * @return Returns the name.
	 */
	public Name getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(Name name) {
		this.name = name;
	}
	/**
	 * @return Returns the perpetratorId.
	 */
	public String getPerpetratorId() {
		return perpetratorId;
	}
	/**
	 * @param perpetratorId The perpetratorId to set.
	 */
	public void setPerpetratorId(String perpetratorId) {
		this.perpetratorId = perpetratorId;
	}
	/**
	 * @return Returns the relationshipToJuv.
	 */
	public String getRelationshipToJuv() {
		return relationshipToJuv;
	}
	/**
	 * @param relationshipToJuv The relationshipToJuv to set.
	 */
	public void setRelationshipToJuv(String relationshipToJuv) {
		this.relationshipToJuv = relationshipToJuv;
	}
	/**
	 * @return Returns the relationshipToJuvId.
	 */
	public String getRelationshipToJuvId() {
		return relationshipToJuvId;
	}
	/**
	 * @param relationshipToJuvId The relationshipToJuvId to set.
	 */
	public void setRelationshipToJuvId(String relationshipToJuvId) {
		this.relationshipToJuvId = relationshipToJuvId;
	}
	
	public String getDescription(){
		if(this.name!=null){
			String relToJuv="";
			if(this.getRelationshipToJuv()!=null){
				relToJuv=" - " + this.getRelationshipToJuv();
			}
			return this.name.getFormattedName() + relToJuv;
		}
		else
			return "";
	}
}// END CLASS
