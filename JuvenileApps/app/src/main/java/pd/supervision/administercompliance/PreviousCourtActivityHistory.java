//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerviolationreport\\PreviousCourtActivityHistory.java

package pd.supervision.administercompliance;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PreviousCourtActivityHistory extends PreviousCourtActivity 
{
    public void setPreviousCourtActivityHistory(){
    	this.setActivity(super.getActivity());
    	this.setDisposition(super.getDisposition());
    	this.setNcResponseId(super.getNcResponseId());
    	this.setOccurenceDate(super.getOccurenceDate());
    	this.setSummaryOfCourtActivity(super.getSummaryOfCourtActivity());
    	this.setType(super.getType());
    }
}
