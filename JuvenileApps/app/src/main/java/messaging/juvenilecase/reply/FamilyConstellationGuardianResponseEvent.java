/*
 * Created on jan 27,2006
 *
 */
package messaging.juvenilecase.reply;

import java.sql.Timestamp;
import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 */
public class FamilyConstellationGuardianResponseEvent extends ResponseEvent
{
	private int memberNum;
    private boolean guardian;
    private boolean active;
    private String juvenileId;
    private Timestamp entryDate;
    private String firstName;
    private String lastName;
    private String middleName;
    private String juvRelation;
    private String familyNum;
    private int financialId;
    private String homePhoneNumber;
    private String mobilePhoneNumber;
    private String workPhoneNumber;
    private Collection emailAddresses;
 
	/**
	 * @return
	 */
	public boolean isActive()
	{
		return active;
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
	public boolean isGuardian()
	{
		return guardian;
	}

	/**
	 * @return
	 */
	public String getJuvenileId()
	{
		return juvenileId;
	}

	/**
	 * @return
	 */
	public String getJuvRelation()
	{
		return juvRelation;
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
	public int getMemberNum()
	{
		return memberNum;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @param b
	 */
	public void setActive(boolean b)
	{
		active = b;
	}

	/**
	 * @param string
	 */
	public void setFirstName(String string)
	{
		firstName = string;
	}

	/**
	 * @param i
	 */
	public void setGuardian(boolean b)
	{
		guardian = b;
	}

	/**
	 * @param string
	 */
	public void setJuvenileId(String string)
	{
		juvenileId = string;
	}

	/**
	 * @param string
	 */
	public void setJuvRelation(String string)
	{
		juvRelation = string;
	}

	/**
	 * @param string
	 */
	public void setLastName(String string)
	{
		lastName = string;
	}

	/**
	 * @param i
	 */
	public void setMemberNum(int i)
	{
		memberNum = i;
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
	public Timestamp getEntryDate()
	{
		return entryDate;
	}

	/**
	 * @param timestamp
	 */
	public void setEntryDate(Timestamp timestamp)
	{
		entryDate = timestamp;
	}

	/**
	 * @return
	 */
	public String getFamilyNum()
	{
		return familyNum;
	}

	/**
	 * @param string
	 */
	public void setFamilyNum(String string)
	{
		familyNum = string;
	}

	/**
	 * @param i
	 */
	public void setFinancialId(int i)
	{
		financialId = i;
	}

	/**
	 * @return
	 */
	public int getFinancialId()
	{
		return financialId;
	}

	/**
	 * @return Returns the homePhoneNumber.
	 */
	public String getHomePhoneNumber() {
		return homePhoneNumber;
	}
	/**
	 * @param homePhoneNumber The homePhoneNumber to set.
	 */
	public void setHomePhoneNumber(String homePhoneNumber) {
		this.homePhoneNumber = homePhoneNumber;
	}
	/**
	 * @return Returns the workPhoneNumber.
	 */
	public String getWorkPhoneNumber() {
		return workPhoneNumber;
	}
	/**
	 * @param workPhoneNumber The workPhoneNumber to set.
	 */
	public void setWorkPhoneNumber(String workPhoneNumber) {
		this.workPhoneNumber = workPhoneNumber;
	}
	/**
	 * @return Returns the mobilePhoneNumber.
	 */
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}
	/**
	 * @param mobilePhoneNumber The mobilePhoneNumber to set.
	 */
	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}
	/**
	 * @return Returns the emailAddresses.
	 */
	public Collection getEmailAddresses() {
		return emailAddresses;
	}
	/**
	 * @param emailAddresses The emailAddresses to set.
	 */
	public void setEmailAddresses(Collection emailAddresses) {
		this.emailAddresses = emailAddresses;
	}
}
