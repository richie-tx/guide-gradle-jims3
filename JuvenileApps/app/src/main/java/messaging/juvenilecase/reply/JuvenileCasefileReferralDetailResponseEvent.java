package messaging.juvenilecase.reply;

import java.util.Comparator;

import mojo.km.messaging.ResponseEvent;

public class JuvenileCasefileReferralDetailResponseEvent extends ResponseEvent{
    
	private String caseFileId;
	private String supervisionTypeCd;
	private String supervisionTypeDesc;
	private String assignmentDate;
	private String juvenileId;
	private String assignOfficerId;
	private String officerLName;
	private String officerFName;
	private String officerMName;
	private String referralNumber;
	
	
	public String getCaseFileId() {
		return caseFileId;
	}
	public void setCaseFileId(String caseFileId) {
		this.caseFileId = caseFileId;
	}
	public String getSupervisionTypeCd() {
		return supervisionTypeCd;
	}
	public void setSupervisionTypeCd(String supervisionTypeCd) {
		this.supervisionTypeCd = supervisionTypeCd;
	}
	public String getSupervisionTypeDesc() {
		return supervisionTypeDesc;
	}
	public void setSupervisionTypeDesc(String supervisionTypeDesc) {
		this.supervisionTypeDesc = supervisionTypeDesc;
	}
	public String getJuvenileId() {
		return juvenileId;
	}
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
	public String getAssignOfficerId() {
		return assignOfficerId;
	}
	public void setAssignOfficerId(String assignOfficerId) {
		this.assignOfficerId = assignOfficerId;
	}
	public String getOfficerLName() {
		return officerLName;
	}
	public void setOfficerLName(String officerLName) {
		this.officerLName = officerLName;
	}
	public String getOfficerFName() {
		return officerFName;
	}
	public void setOfficerFName(String officerFName) {
		this.officerFName = officerFName;
	}
	public String getOfficerMName() {
		return officerMName;
	}
	public void setOfficerMName(String officerMName) {
		this.officerMName = officerMName;
	}
	public String getReferralNumber() {
		return referralNumber;
	}
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}
	public String getAssignmentDate() {
		return assignmentDate;
	}
	public void setAssignmentDate(String assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	public static Comparator DateRefComparator = new Comparator() {
		
		public int compare(Object responseEvent1, Object responseEvent2) {
		  
			String ref1 = ((JuvenileCasefileReferralDetailResponseEvent)responseEvent1).getReferralNumber();
    		String ref2 = ((JuvenileCasefileReferralDetailResponseEvent)responseEvent2).getReferralNumber();
		  
			Integer refInt1 = new Integer(ref1);
			Integer refInt2 = new Integer(ref2);
			
			int compReferrals = refInt2.compareTo(refInt1);
			if(compReferrals != 0 ){
				return compReferrals;
			}
			
		  String date1 = ((JuvenileCasefileReferralDetailResponseEvent)responseEvent1).getAssignmentDate();
		  String date2 = ((JuvenileCasefileReferralDetailResponseEvent)responseEvent2).getAssignmentDate();
		  
		  Integer dateInt1 = new Integer(date1);
		  Integer dateInt2 = new Integer(date2);
		  return dateInt2.compareTo(dateInt1);
		}	
	};

	public static Comparator ReferralComparator = new Comparator() {
		
		public int compare(Object responseEvent1, Object responseEvent2) {
		  String ref1 = ((JuvenileCasefileReferralDetailResponseEvent)responseEvent1).getReferralNumber();
		  String ref2 = ((JuvenileCasefileReferralDetailResponseEvent)responseEvent2).getReferralNumber();
		  
		  Integer refInt1 = new Integer(ref1);
		  Integer refInt2 = new Integer(ref2);
		  return refInt2.compareTo(refInt1);
		}	
	};
}
