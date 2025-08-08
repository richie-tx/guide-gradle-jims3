package messaging.productionsupport;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class SaveProductionSupportJuvenileSchoolHistoryEvent extends ResponseEvent 
{
   
   /**
    * @roseuid 42BC4E0203B4
    */
   public SaveProductionSupportJuvenileSchoolHistoryEvent() 
   {
    
	   
   }
   
	private String schoolHistoryId;
	private Date lastAttendedDate;
	private String exitTypeCode;
	private String gradeLevelCode;
	private String instructionType;
	private String appropriateLevelCode;
	private String gradesRepeatedCode;
	public String juvenileNum;
	public String getSchoolHistoryId() {
		return schoolHistoryId;
	}
	public void setSchoolHistoryId(String schoolHistoryId) {
		this.schoolHistoryId = schoolHistoryId;
	}
	public Date getLastAttendedDate() {
		return lastAttendedDate;
	}
	public void setLastAttendedDate(Date lastAttendedDate) {
		this.lastAttendedDate = lastAttendedDate;
	}
	public String getExitTypeCode() {
		return exitTypeCode;
	}
	public void setExitTypeCode(String exitTypeCode) {
		this.exitTypeCode = exitTypeCode;
	}
	public String getGradeLevelCode() {
		return gradeLevelCode;
	}
	public void setGradeLevelCode(String gradeLevelCode) {
		this.gradeLevelCode = gradeLevelCode;
	}
	public String getInstructionType() {
		return instructionType;
	}
	public void setInstructionType(String instructionType) {
		this.instructionType = instructionType;
	}
	public String getAppropriateLevelCode() {
		return appropriateLevelCode;
	}
	public void setAppropriateLevelCode(String appropriateLevelCode) {
		this.appropriateLevelCode = appropriateLevelCode;
	}
	public String getGradesRepeatedCode() {
		return gradesRepeatedCode;
	}
	public void setGradesRepeatedCode(String gradesRepeatedCode) {
		this.gradesRepeatedCode = gradesRepeatedCode;
	}
	public void setJuvenileNum(String juvenileNum)
	{
		this.juvenileNum = juvenileNum;
	}
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	
	
}
