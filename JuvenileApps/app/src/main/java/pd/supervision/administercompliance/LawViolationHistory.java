//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerviolationreport\\LawViolationHistory.java

package pd.supervision.administercompliance;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LawViolationHistory extends LawViolation
{
    public void setLawViolationHistory(){
    	this.setCaseId(super.getCaseId());
    	this.setCrt(super.getCrt());
    	this.setNcResponseId(super.getNcResponseId());
    	this.setOffenseDate(super.getOffenseDate());
    	this.setOffenseDegree(super.getOffenseDegree());
    	this.setOffenseLevel(super.getOffenseLevel());
    	this.setOffenseLiteral(super.getOffenseLiteral());
    }
}
