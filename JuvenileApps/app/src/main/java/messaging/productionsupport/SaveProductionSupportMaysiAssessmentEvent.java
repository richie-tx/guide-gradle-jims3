package messaging.productionsupport;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SaveProductionSupportMaysiAssessmentEvent extends RequestEvent 
{
	
	public String maysiAssessmentId;
	public String assessmentOption;
	public String referralNumber;
	public Date assessmentDate;
	public String assessOfficerId;
	public boolean hasPreviousMaysi;
	public boolean isAdministered;
	public String reasonNotDone;
	public String otherReasonNotDone;

     
   /**
    * @roseuid 456F33E603CA
    */  
   public SaveProductionSupportMaysiAssessmentEvent() {}
   
   
   public String getMaysiAssessmentId() {
	   return maysiAssessmentId;
   }

   public void setMaysiAssessmentId(String maysiAssessmentId) {
	   this.maysiAssessmentId = maysiAssessmentId;
   }
	

   public String getReferralNumber() {
	   return referralNumber;
   }
	
   public void setReferralNumber(String referralNumber) {
	   this.referralNumber = referralNumber;
   }

   public String getAssessmentOption() {
	   return assessmentOption;
   }	
	
   public void setAssessmentOption(String assessmentOption) {
	   this.assessmentOption = assessmentOption;
   }


public Date getAssessmentDate() {
	return assessmentDate;
}


public void setAssessmentDate(Date assessmentDate) {
	this.assessmentDate = assessmentDate;
}

public String getAssessOfficerId() {
    return assessOfficerId;
}

public void setAssessOfficerId(String assessOfficerId) {
    this.assessOfficerId = assessOfficerId;
}

public boolean getHasPreviousMaysi() {
    return hasPreviousMaysi;
}

public void setHasPreviousMaysi(boolean hasPreviousMaysi) {
    this.hasPreviousMaysi = hasPreviousMaysi;
}

public boolean getIsAdministered() {
    return isAdministered;
}

public void setIsAdministered(boolean isAdministered) {
    this.isAdministered = isAdministered;
}

public String getReasonNotDone() {
    return reasonNotDone;
}

public void setReasonNotDone(String reasonNotDone) {
    this.reasonNotDone = reasonNotDone;
}

public String getOtherReasonNotDone() {
    return otherReasonNotDone;
}

public void setOtherReasonNotDone(String otherReasonNotDone) {
    this.otherReasonNotDone = otherReasonNotDone;
}
   
   
}
