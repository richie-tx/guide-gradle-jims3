/*
 * Created on Oct 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import java.util.Date;

import ui.common.Address;
import messaging.contact.to.PhoneNumberBean;


/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FamilyConstellationMemberListResponseEvent extends FamilyMemberListResponseEvent //implements Comparable
{

	// the oid of a member of constellation 
	private String famConstellationMemberNum;
	private String relationToJuvenileId;
	private String relationToJuvenile;
	private boolean isGuardian;
	private boolean primaryCareGiver; //ER changes 11063

	private boolean inHomeStatus;
	private String  involvmentLevelId;
	private boolean parentalRightsTerminated;
	private boolean detentionHearing;  
	private boolean detentionVisitation; 
	private boolean primaryContact;
	
	//added for facility
	private String phone; //latest home phone number
	private Address memberAddress;
	
	//added for User Story 27023
	private Date confirmedDate;
	
   	//added for User Story 43892
   	private boolean over21;

	/**
	 * 
	 */
	public FamilyConstellationMemberListResponseEvent()
	{
		super();
	}
	
	/*
    public int compareTo(Object o) {
		if(o==null){
			return 1;
		}
		if(o instanceof FamilyConstellationMemberListResponseEvent){
			FamilyConstellationMemberListResponseEvent myEvt=(FamilyConstellationMemberListResponseEvent)o;
			if(myEvt.getFullName()==null){
				return 1;
			}
			else if(this.getFullName()==null){
				return -1;
			}
			else{
				return this.getFullName().compareTo(myEvt.getFullName());
			}
		}
		else
			return 1;
	}*/
    
    
    public String getFullName(){
		if(getFirstName()!=null && !getFirstName().equals("")){
			return getLastName() + ", " + getFirstName();
		}
		else{
			return getLastName();
		}
	}

	/**
	 * @return
	 */
	public String getFamConstellationMemberNum()
	{
		return famConstellationMemberNum;
	}

	/**
	 * @return
	 */
	public boolean isGuardian()
	{
		return isGuardian;
	}

	/**
	 * @return
	 */
	public String getRelationToJuvenileId()
	{
		return relationToJuvenileId;
	}

	/**
	 * @param string
	 */
	public void setFamConstellationMemberNum(String string)
	{
		famConstellationMemberNum = string;
	}

	/**
	 * @param b
	 */
	public void setGuardian(boolean b)
	{
		isGuardian = b;
	}

	/**
	 * @param string
	 */
	public void setRelationToJuvenileId(String string)
	{
		relationToJuvenileId = string;
	}

	/**
	 * @return
	 */
	public boolean isInHomeStatus()
	{
		return inHomeStatus;
	}

	/**
	 * @return
	 */
	public String getInvolvmentLevelId()
	{
		return involvmentLevelId;
	}

	/**
	 * @param b
	 */
	public void setInHomeStatus(boolean b)
	{
		inHomeStatus = b;
	}

	/**
	 * @param string
	 */
	public void setInvolvmentLevelId(String string)
	{
		involvmentLevelId = string;
	}

	/**
	 * @return Returns the relationToJuvenile.
	 */
	public String getRelationToJuvenile() {
		return relationToJuvenile;
	}
	/**
	 * @param relationToJuvenile The relationToJuvenile to set.
	 */
	public void setRelationToJuvenile(String relationToJuvenile) {
		this.relationToJuvenile = relationToJuvenile;
	}

	/**
	 * @return the parentalRightsTerminated
	 */
	public boolean isParentalRightsTerminated() {
		return parentalRightsTerminated;
	}

	/**
	 * @param parentalRightsTerminated the parentalRightsTerminated to set
	 */
	public void setParentalRightsTerminated(boolean b) {
		parentalRightsTerminated = b;
	}
	

	/**
	 * @return the detentionHearing
	 */
	public boolean isDetentionHearing() {
		return detentionHearing;
	}

	/**
	 * @param detentionHearing the detentionHearing to set
	 */
	public void setDetentionHearing(boolean b) {
		detentionHearing = b;
	}
	

	/**
	 * @return the detentionVisitation
	 */
	public boolean isDetentionVisitation() {
		return detentionVisitation;
	}

	/**
	 * @param detentionVisitation the detentionVisitation to set
	 */
	public void setDetentionVisitation(boolean b) {
		detentionVisitation = b;
	}

	/**
	 * @return the primaryContact
	 */
	public boolean isPrimaryContact() {
		return primaryContact;
	}

	/**
	 * @param primaryContact the primaryContact to set
	 */
	public void setPrimaryContact(boolean primaryContact) {
		this.primaryContact = primaryContact;
	}

	public boolean isPrimaryCareGiver() {
		return primaryCareGiver;
	}

	public void setPrimaryCareGiver(boolean primaryCareGiver) {
		this.primaryCareGiver = primaryCareGiver;
	}

	public Address getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(Address memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the confirmedDate
	 */
	public Date getConfirmedDate() {
		return confirmedDate;
	}

	/**
	 * @param confirmedDate the confirmedDate to set
	 */
	public void setConfirmedDate(Date confirmedDate) {
		this.confirmedDate = confirmedDate;
	}

	public boolean isOver21() {
		return over21;
	}

	public void setOver21(boolean over21) {
		this.over21 = over21;
	}
}