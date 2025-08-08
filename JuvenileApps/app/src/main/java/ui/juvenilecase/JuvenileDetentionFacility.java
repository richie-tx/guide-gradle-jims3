/*
 * Created on Nov 29, 2007
 */
package ui.juvenilecase;

import java.util.Date;
import naming.PDCodeTableConstants;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;


/**
 * @author awidjaja
 * This object is the UI representation of 
 * JuvenileDetentionFacilitiesResponseEvent.
 * It has added functionality e.g. getting facility name. 
 * 
 */
public class JuvenileDetentionFacility {
	private String admitReason;
	private Date admitDate;
	private String facilityCode;	
	private String referralNumber;
	private Date originalAdmitDate;
	private String releaseBy;
	private Date releaseDate;
	private String releaseTo;
	private String releaseTime;
	private String admitTime;
	
	public JuvenileDetentionFacility(JuvenileDetentionFacilitiesResponseEvent re) {
		admitReason = re.getAdmitReason();
		admitDate = re.getAdmitDate();
		facilityCode = re.getDetainedFacility();
		referralNumber = re.getReferralNumber();
		originalAdmitDate = re.getOriginalAdmitDate();
		releaseBy = re.getReleaseBy();
		releaseDate = re.getReleaseDate();
		releaseTo = re.getReleaseTo();
		releaseTime = re.getReleaseTime();
		admitTime = re.getAdmitTime();		
	}
	
	public Date getAdmitDate() {
		return admitDate;
	}
	public void setAdmitDate(Date admitDate) {
		this.admitDate = admitDate;
	}
	public String getAdmitReason() {
		return admitReason;
	}
	public void setAdmitReason(String admitReason) {
		this.admitReason = admitReason;
	}
	public String getAdmitTime() {
		return admitTime;
	}
	public void setAdmitTime(String admitTime) {
		this.admitTime = admitTime;
	}
	public String getFacilityCode() {
		return facilityCode;
	}
	public void setFacilityCode(String facilityCode) {
		this.facilityCode = facilityCode;
	}
	public Date getOriginalAdmitDate() {
		return originalAdmitDate;
	}
	public void setOriginalAdmitDate(Date originalAdmitDate) {
		this.originalAdmitDate = originalAdmitDate;
	}
	public String getReferralNumber() {
		return referralNumber;
	}
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}
	public String getReleaseBy() {
		return releaseBy;
	}
	public void setReleaseBy(String releaseBy) {
		this.releaseBy = releaseBy;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getReleaseTo() {
		return releaseTo;
	}
	public void setReleaseTo(String releaseTo) {
		this.releaseTo = releaseTo;
	}
	
	public String getFacilityName() {
		return SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY, facilityCode);		
	}
	
	public String getAdmitReasonDesc() {
		return SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.DETENTION_REASON, admitReason);
	}
	
	public String getReleaseToDesc() {
		return ComplexCodeTableHelper.getDescrByCode(ComplexCodeTableHelper.getActiveReleasedFromDetentionCodes(), releaseTo);
	}
	
}
