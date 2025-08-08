/*
 * Created on Oct 18, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author palcocer
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CourtReferralPrefillResponseEvent extends ResponseEvent
{
	//Part I - Juvenile Information
	private String headquarterCounty; //Harris County
	private String juvnileName;
	private String pidNumber; //Juvenile Number
	private String ReferralNumber;
	private Date dateOfReferral;
	private Date dateOfBirth;
	private String juvSex;
	private String juvSexId;
	private int ageOfFirstReferral; 
	private boolean voilentFelony;
	private int totalReferralsToJuvenile;
	private String referralsInformation;
	

	
	public void setHeadquarterCounty(String headquarterCounty) {
		this.headquarterCounty = headquarterCounty;
	}

	public String getHeadquarterCounty() {
		return headquarterCounty;
	}

	public void setJuvnileName(String juvnileName) {
		this.juvnileName = juvnileName;
	}

	public String getJuvnileName() {
		return juvnileName;
	}

	public void setPidNumber(String pidNumber) {
		this.pidNumber = pidNumber;
	}

	public String getPidNumber() {
		return pidNumber;
	}

	public void setReferralNumber(String referralNumber) {
		ReferralNumber = referralNumber;
	}

	public String getReferralNumber() {
		return ReferralNumber;
	}

	public void setDateOfReferral(Date dateOfReferral) {
		this.dateOfReferral = dateOfReferral;
	}

	public Date getDateOfReferral() {
		return dateOfReferral;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @return Returns the juvSexId.
	 */
	public String getJuvSexId() {
		return juvSexId;
	}
	
	/**
	 * @param juvSexId The juvSexId to set.
	 */
	public void setJuvSexId(String juvSexId) {
		this.juvSexId = juvSexId;
	}
	
	/**
	 * @return
	 */
	public String getJuvSex()
	{
		return juvSex;
	}
	
	/**
	 * @param string
	 */
	public void setJuvSex(final String string)
	{
		juvSex = string;
	}
	
	/**
	 * @return
	 */
	public int getAgeOfFirstReferral()
	{
		return ageOfFirstReferral;
	}

	/**
	 * @param i
	 */
	public void setAgeOfFirstReferral(final int i)
	{
		ageOfFirstReferral = i;
	}

	public void setVoilentFelony(boolean voilentFelony) {
		this.voilentFelony = voilentFelony;
	}

	public boolean isVoilentFelony() {
		return voilentFelony;
	}

	public void setTotalReferralsToJuvenile(int totalReferralsToJuvenile) {
		this.totalReferralsToJuvenile = totalReferralsToJuvenile;
	}

	public int getTotalReferralsToJuvenile() {
		return totalReferralsToJuvenile;
	}

	public void setReferralsInformation(String referralsInformation) {
		this.referralsInformation = referralsInformation;
	}

	public String getReferralsInformation() {
		return referralsInformation;
	}

}
