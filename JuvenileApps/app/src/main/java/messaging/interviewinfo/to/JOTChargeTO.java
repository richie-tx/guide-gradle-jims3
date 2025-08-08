/*
 * Created on Nov 29, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.interviewinfo.to;

import java.util.Date;

import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingChargeResponseEvent;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JOTChargeTO {
	private Date offenseDate;
	private String chargeDescription;
	private String offenseLevel;
	private String offenseDegree;
	private String penalCode;
	private boolean weaponUsed = false;
	private String weaponType;
	private String unlistedWeapon;
	private String caseTypeGroup;
	private boolean alcoholInfluence = false;
	
	
	
	public String getOffenseLevelDegree() {
		StringBuffer sb = new StringBuffer();
		if(offenseLevel != null)
			sb.append(offenseLevel);
		if(offenseDegree != null)
			sb.append(offenseDegree);
		return sb.toString();
	}
	
	public boolean isInvolvedInGang() {
		if ( caseTypeGroup != null && 
				("FGC".equals(caseTypeGroup) || 
				"GC".equals(caseTypeGroup) || 
				"GG".equals(caseTypeGroup) )) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public boolean isAlcoholInfluence() {
		return alcoholInfluence;
	}
	public void setAlcoholInfluence(boolean alcoholInfluence) {
		this.alcoholInfluence = alcoholInfluence;
	}
	public String getCaseTypeGroup() {
		return caseTypeGroup;
	}
	public void setCaseTypeGroup(String caseTypeGroup) {
		this.caseTypeGroup = caseTypeGroup;
	}
	public String getChargeDescription() {
		return chargeDescription;
	}
	public void setChargeDescription(String chargeDescription) {
		this.chargeDescription = chargeDescription;
	}
	public Date getOffenseDate() {
		return offenseDate;
	}
	public void setOffenseDate(Date offenseDate) {
		this.offenseDate = offenseDate;
	}
	
	public String getOffenseDegree() {
		return offenseDegree;
	}
	public void setOffenseDegree(String offenseDegree) {
		this.offenseDegree = offenseDegree;
	}
	public String getOffenseLevel() {
		return offenseLevel;
	}
	public void setOffenseLevel(String offenseLevel) {
		this.offenseLevel = offenseLevel;
	}
	public String getPenalCode() {
		return penalCode;
	}
	public void setPenalCode(String penalCode) {
		this.penalCode = penalCode;
	}
	public String getUnlistedWeapon() {
		return unlistedWeapon;
	}
	public void setUnlistedWeapon(String unlistedWeapon) {
		this.unlistedWeapon = unlistedWeapon;
	}
	public String getWeaponType() {
		return weaponType;
	}
	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}
	public boolean isWeaponUsed() {
		return weaponUsed;
	}
	public void setWeaponUsed(boolean weaponUsed) {
		this.weaponUsed = weaponUsed;
	}
	
	public String getWeaponTypeUsed() {
		return null;
	}
	
}
