/*
 * Created on Oct 19, 2007
 *
 */
package ui.juvenilecase.interviewinfo.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ui.common.Name;

/**
 * @author awidjaja
 *
 */
public class RequestAppointedAttorneyPrintBean {

	private String offenseLevel;
		
	private Name juvenileName;
	private String juvenileNameString;
	private String juvenileNum;
	private String courtId;
	private String petitionNum;
	private Date pendingCourtDate;
	
	private List guardians;
	private List otherKnownParent;
	
	private double ssi;
	private double foodStamps;
	private double childSupport;
	private double tanf;
	
	
	private double totalAnnualIncome;
	private int numberInHousehold;
	
	private String youthCoveredByMedicaid;
	
	private String additionalComments;
	
	public RequestAppointedAttorneyPrintBean() {
		offenseLevel = "";
		juvenileName = new Name();
		juvenileNameString = "";
		juvenileNum = "";
		courtId = "";
		petitionNum = "";
		pendingCourtDate = null;
		guardians = new ArrayList();
		otherKnownParent = new ArrayList();
		totalAnnualIncome = 0;
		numberInHousehold = 0;
		youthCoveredByMedicaid = "NO";
		foodStamps = 0;
	}
	
	public String getAdditionalComments() {
		return additionalComments;
	}
	public void setAdditionalComments(String additionalComments) {
		this.additionalComments = additionalComments;
	}
	
	
	public String getCourtId() {
		return courtId;
	}
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
	public String getOffenseLevel() {
		return offenseLevel;
	}
	public void setOffenseLevel(String offenseLevel) {
		this.offenseLevel = offenseLevel;
	}
	public List getGuardians() {
		return guardians;
	}
	public void setGuardians(List guardians) {
		this.guardians = guardians;
	}
	public Name getJuvenileName() {
		return juvenileName;
	}
	public void setJuvenileName(Name juvenileName) {
		this.juvenileName = juvenileName;
	}	
	public String getJuvenileNameString() {
		return juvenileNameString;
	}
	public void setJuvenileNameString(String juvenileNameString) {
		this.juvenileNameString = juvenileNameString;
	}
	public String getJuvenileNum() {
		return juvenileNum;
	}
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	public List getOtherKnownParent() {
		return otherKnownParent;
	}
	public void setOtherKnownParent(List otherKnownParent) {
		this.otherKnownParent = otherKnownParent;
	}
	public Date getPendingCourtDate() {
		return pendingCourtDate;
	}
	public void setPendingCourtDate(Date pendingCourtDate) {
		this.pendingCourtDate = pendingCourtDate;
	}
	public String getPetitionNum() {
		return petitionNum;
	}
	public void setPetitionNum(String petitionNum) {
		this.petitionNum = petitionNum;
	}
	
	public int getNumberInHousehold() {
		return numberInHousehold;
	}
	public void setNumberInHousehold(int numberInHousehold) {
		this.numberInHousehold = numberInHousehold;
	}
	public double getTotalAnnualIncome() {
		return totalAnnualIncome;
	}
	public void setTotalAnnualIncome(double totalAnnualIncome) {
		this.totalAnnualIncome = totalAnnualIncome;
	}
	
	public String getYouthCoveredByMedicaid() {
		return youthCoveredByMedicaid;
	}
	public void setYouthCoveredByMedicaid(String youthCoveredByMedicaid) {
		this.youthCoveredByMedicaid = youthCoveredByMedicaid;
	}
	public double getChildSupport() {
		return childSupport;
	}
	public void setChildSupport(double childSupport) {
		this.childSupport = childSupport;
	}
	
	public double getFoodStamps() {
		return foodStamps;
	}
	public void setFoodStamps(double foodStamps) {
		this.foodStamps = foodStamps;
	}
	public double getSsi() {
		return ssi;
	}
	public void setSsi(double ssi) {
		this.ssi = ssi;
	}
	public double getTanf() {
		return tanf;
	}
	public void setTanf(double tanf) {
		this.tanf = tanf;
	}
}
