//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\criminalcase\\SupervisoryFees.java

package pd.criminalcase;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class SupervisoryFees extends PersistentObject 
{
    private String criminalCaseId;
    private String spn;
    private String probationOfficerInd;
   /**
    * @roseuid 4761AB6E0156
    */
   public SupervisoryFees() 
   {
    
   }
   
   /**
    * @roseuid 475D9DBB02F6
    */
   public void create() 
   {
    
   }
   
   /**
    * @roseuid 475D9DBB02F7
    */
   public String update() 
   {
       IHome home = new Home();
       String msg = null;
       try {
        home.bind(this);
    } catch (Exception e) {
        msg = e.getMessage();
    }
       return msg;
   }
    /**
     * @return Returns the criminalCaseId.
     */
    public String getCriminalCaseId() {
        return criminalCaseId;
    }
    /**
     * @param criminalCaseId The criminalCaseId to set.
     */
    public void setCriminalCaseId(String criminalCaseId) {
        this.criminalCaseId = criminalCaseId;
    }
    /**
     * @return Returns the probationOfficerInd.
     */
    public String getProbationOfficerInd() {
        return probationOfficerInd;
    }
    /**
     * @param probationOfficerInd The probationOfficerInd to set.
     */
    public void setProbationOfficerInd(String probationOfficerInd) {
        this.probationOfficerInd = probationOfficerInd;
    }
    /**
     * @return Returns the spn.
     */
    public String getSpn() {
        return spn;
    }
    /**
     * @param spn The spn to set.
     */
    public void setSpn(String spn) {
        this.spn = spn;
    }
}
