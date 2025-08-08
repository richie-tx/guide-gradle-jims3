//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administersupervisee\\EducationAssessment.java

package pd.supervision.administersupervisee;

import java.util.Date;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.supervision.administerserviceprovider.Service;

public class EducationAssessment extends PersistentObject 
{

   private Date assessmentDate;
   private String assessedLevel;
   private String gedVerifiedInd; //boolean
   private Date gedDate;
   private String highSchoolDiplomaVerifiedInd; // boolean
   private Date highSchoolDiplomaDate;
   private Code reportedLevel;
   private String reportedLevelId;
   private String defendantId;
   private String assessmentMethod;
   
   /**
    * @roseuid 484E80F7021C
    */
   public EducationAssessment() 
   {
    
   }
   
   /**
    * @roseuid 48494220026F
    */
   public void findAll() 
   {
    
   }
   
   /**
    * @roseuid 4849422002EC
    */
   public void find() 
   {
    
   }

	public Date getAssessmentDate() {
		return assessmentDate;
	}
	
	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}
	
	public Date getGedDate() {
		return gedDate;
	}
	
	public void setGedDate(Date gedDate) {
		this.gedDate = gedDate;
	}
	
	public String isGedVerifiedInd() {
		return gedVerifiedInd;
	}
	
	public void setGedVerifiedInd(String gedVerifiedInd) {
		this.gedVerifiedInd = gedVerifiedInd;
	}
	
	public Date getHighSchoolDiplomaDate() {
		return highSchoolDiplomaDate;
	}
	
	public void setHighSchoolDiplomaDate(Date highSchoolDiplomaDate) {
		this.highSchoolDiplomaDate = highSchoolDiplomaDate;
	}
	
	public String isHighSchoolDiplomaVerifiedInd() {
		return highSchoolDiplomaVerifiedInd;
	}
	
	public void setHighSchoolDiplomaVerifiedInd(String highSchoolDiplomaVerifiedInd) {
		this.highSchoolDiplomaVerifiedInd = highSchoolDiplomaVerifiedInd;
	}
	
	public Code getReportedLevel() {
		return reportedLevel;
	}
	
	public void setReportedLevel(Code reportedLevel) {
		this.reportedLevel = reportedLevel;
	}
	
	public String getAssessedLevel() {
		return assessedLevel;
	}
	
	public void setAssessedLevel(String assessedLevel) {
		this.assessedLevel = assessedLevel;
	}
	
	public String getReportedLevelId() {
		return reportedLevelId;
	}
	
	public void setReportedLevelId(String reportedLevelId) {
		this.reportedLevelId = reportedLevelId;
	}
	
	public String getDefendantId() {
		return defendantId;
	}
	
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	static public EducationAssessment find(String oid) {
		IHome home = new Home();
		return (EducationAssessment) home.find(oid, EducationAssessment.class);
	}

	public String getAssessmentMethod() {
		return assessmentMethod;
	}

	public void setAssessmentMethod(String assessmentMethod) {
		this.assessmentMethod = assessmentMethod;
	}


}
