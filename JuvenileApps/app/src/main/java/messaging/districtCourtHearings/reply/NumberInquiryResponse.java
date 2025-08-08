package messaging.districtCourtHearings.reply;

import java.util.List;


public class NumberInquiryResponse {
	
	private String juvenileName;
    private String referralNumber;
	private String statusCd;
	private String statusDesc;
	private String supervisionNumber;
	private String supervisionCategory;
	private String jpoOfficer;
	private String officerName;
	private String juvenileNumber;
	private List<NumberInquiryResponse> historyList;
	
	
    public String getJuvenileNumber() {
		return juvenileNumber;
	}
	public void setJuvenileNumber(String juvenileNumber) {
		this.juvenileNumber = juvenileNumber;
	}
	public String getJuvenileName() {
		return juvenileName;
	}
	public void setJuvenileName(String juvenileName) {
		this.juvenileName = juvenileName;
	}
	public String getReferralNumber() {
		return referralNumber;
	}
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statudDesc) {
		this.statusDesc = statudDesc;
	}
	public String getJpoOfficer() {
		return jpoOfficer;
	}
	public void setJpoOfficer(String jpoOfficer) {
		this.jpoOfficer = jpoOfficer;
	}
	public String getOfficerName() {
		return officerName;
	}
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	public String getSupervisionCategory() {
		return supervisionCategory;
	}
	public void setSupervisionCategory(String supervisionCategory) {
		this.supervisionCategory = supervisionCategory;
	}
	public String getSupervisionNumber() {
		return supervisionNumber;
	}
	public void setSupervisionNumber(String supervisionNumber) {
		this.supervisionNumber = supervisionNumber;
	}
	public List<NumberInquiryResponse> getHistoryList() {
		return historyList;
	}
	public void setHistoryList(List<NumberInquiryResponse> historyList) {
		this.historyList = historyList;
	}
	
}
