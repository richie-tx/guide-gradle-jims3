//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerviolationreport\\ReportingHistory.java

package pd.supervision.administercompliance;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class ReportingHistory extends Reporting 
{   
	public void setReportingHistory(){
		this.setDetails(super.getDetails());
		this.setEventTypes(super.getEventTypes());
		this.setNcResponseId(super.getNcResponseId());
		this.setOccurenceDate(super.getOccurenceDate());
	}
}
