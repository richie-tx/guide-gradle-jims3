/*
 * Created on Aug 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenilecase.reply;

import java.util.Date;

import messaging.contact.domintf.IName;
import mojo.km.messaging.ResponseEvent;

/**
 * @author jjose
 *
  */
public class AssociatedJuvenilesResponseEvent extends ResponseEvent {
	private String  famMemberId;
	private String  juvId;
	private String  constellationId;
	private IName	juvName;
	private String juvFullName;  //for display purposes
	private boolean deceased;
	private boolean guardian;
	private String relationTypeId;
	private String relationType;
	private boolean activeConstellation;
	private String raceCd;
	private String raceDesc; 	 //for display purposes
	private String ethnicityCd;
	private String ethnicityDesc;    //for display purposes
	private String sexCd;
	private String sexDesc; 	 //for display purposes
	private Date dateOfBirth;
	
	/**
	 * @return Returns the activeConstellation.
	 */
	public boolean isActiveConstellation() {
		return activeConstellation;
	}
	/**
	 * @param activeConstellation The activeConstellation to set.
	 */
	public void setActiveConstellation(boolean activeConstellation) {
		this.activeConstellation = activeConstellation;
	}
	
	/**
	 * @return Returns the constellationId.
	 */
	public String getConstellationId() {
		return constellationId;
	}
	/**
	 * @param constellationId The constellationId to set.
	 */
	public void setConstellationId(String constellationId) {
		this.constellationId = constellationId;
	}
	/**
	 * @return Returns the deceased.
	 */
	public boolean isDeceased() {
		return deceased;
	}
	/**
	 * @param deceased The deceased to set.
	 */
	public void setDeceased(boolean deceased) {
		this.deceased = deceased;
	}
	/**
	 * @return Returns the famMemberId.
	 */
	public String getFamMemberId() {
		return famMemberId;
	}
	/**
	 * @param famMemberId The famMemberId to set.
	 */
	public void setFamMemberId(String famMemberId) {
		this.famMemberId = famMemberId;
	}
	/**
	 * @return Returns the guardian.
	 */
	public boolean isGuardian() {
		return guardian;
	}
	/**
	 * @param guardian The guardian to set.
	 */
	public void setGuardian(boolean guardian) {
		this.guardian = guardian;
	}
	/**
	 * @return Returns the juvId.
	 */
	public String getJuvId() {
		return juvId;
	}
	/**
	 * @param juvId The juvId to set.
	 */
	public void setJuvId(String juvId) {
		this.juvId = juvId;
	}
	/**
	 * @return the juvFullName
	 */
	public String getJuvFullName() {
		return juvFullName;
	}
	/**
	 * @param juvFullName the juvFullName to set
	 */
	public void setJuvFullName(String juvFullName) {
		this.juvFullName = juvFullName;
	}
	/**
	 * @return Returns the juvName.
	 */
	public IName getJuvName() {
		return juvName;
	}
	/**
	 * @param juvName The juvName to set.
	 */
	public void setJuvName(IName juvName) {
		this.juvName = juvName;
	}
	/**
	 * @return Returns the relationType.
	 */
	public String getRelationType() {
		return relationType;
	}
	/**
	 * @param relationType The relationType to set.
	 */
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	/**
	 * @return Returns the relationTypeId.
	 */
	public String getRelationTypeId() {
		return relationTypeId;
	}
	/**
	 * @param relationTypeId The relationTypeId to set.
	 */
	public void setRelationTypeId(String relationTypeId) {
		this.relationTypeId = relationTypeId;
	}
	public String getRaceCd() {
		return raceCd;
	}
	public void setRaceCd(String raceCd) {
		this.raceCd = raceCd;
	}
	public String getRaceDesc() {
		return raceDesc;
	}
	public void setRaceDesc(String raceDesc) {
		this.raceDesc = raceDesc;
	}
	public String getEthnicityCd() {
		return ethnicityCd;
	}
	public void setEthnicityCd(String ethnicityCd) {
		this.ethnicityCd = ethnicityCd;
	}
	public String getEthnicityDesc() {
		return ethnicityDesc;
	}
	public void setEthnicityDesc(String ethnicityDesc) {
		this.ethnicityDesc = ethnicityDesc;
	}
	public String getSexCd() {
		return sexCd;
	}
	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}
	public String getSexDesc() {
		return sexDesc;
	}
	public void setSexDesc(String sexDesc) {
		this.sexDesc = sexDesc;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
}
