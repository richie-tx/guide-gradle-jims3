//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerviolationreport\\TreatmentHistory.java

package pd.supervision.administercompliance;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class TreatmentHistory extends Treatment
{
    public void setTreatmentHistory(){
    	this.setBeginDate(super.getBeginDate());
    	this.setExitDate(super.getExitDate());
    	this.setNcResponseId(super.getNcResponseId());
    	this.setReasonForDischargeId(super.getReasonForDischargeId());
    	this.setReferralType(super.getReferralType());
    	this.setServiceProviderName(super.getServiceProviderName());
    }
}
