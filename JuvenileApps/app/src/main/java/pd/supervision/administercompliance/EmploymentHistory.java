//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerviolationreport\\EmploymentHistory.java

package pd.supervision.administercompliance;



public class EmploymentHistory extends Employment 
{
    public void setEmploymentHistory(){
    	this.setEmployerName(super.getEmployerName());
    	this.setJobTitle(super.getJobTitle());
    	this.setNcResponseId(super.getNcResponseId());
    	this.setStatusId(super.getStatusId());
    }
}
