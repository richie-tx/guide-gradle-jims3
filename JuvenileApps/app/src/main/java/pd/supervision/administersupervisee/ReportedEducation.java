//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administersupervisee\\ReportedEducation.java

package pd.supervision.administersupervisee;

import java.util.Date;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

public class ReportedEducation extends PersistentObject 
{
   private Date intakeDate;
   private String educationLevel; //highest Grade completed
   private String highSchoolDiplomaInd; //boolean
   private String gedInd;//boolean
   private String advancedDegree;
   
   /**
    * @roseuid 484E80F702E7
    */
   public ReportedEducation() 
   {
    
   }
   
   /**
    * @roseuid 48494222004C
    */
   public void findAll() 
   {
    
   }
   
   /**
    * @roseuid 48494222004D
    */
   public void find() 
   {
    
   }

public String getAdvancedDegree() {
	return advancedDegree;
}

public void setAdvancedDegree(String advancedDegree) {
	this.advancedDegree = advancedDegree;
}

public String isGedInd() {
	return gedInd;
}

public void setGedInd(String gedInd) {
	this.gedInd = gedInd;
}

public String isHighSchoolDiplomaInd() {
	return highSchoolDiplomaInd;
}

public void setHighSchoolDiplomaInd(String highSchoolDiplomaInd) {
	this.highSchoolDiplomaInd = highSchoolDiplomaInd;
}

public Date getIntakeDate() {
	return intakeDate;
}

public void setIntakeDate(Date intakeDate) {
	this.intakeDate = intakeDate;
}

public String getEducationLevel() {
	return educationLevel;
}

public void setEducationLevel(String educationLevel) {
	this.educationLevel = educationLevel;
}
static public ReportedEducation find(String oid) {
	IHome home = new Home();
	return (ReportedEducation) home.find(oid, ReportedEducation.class);
}

}
