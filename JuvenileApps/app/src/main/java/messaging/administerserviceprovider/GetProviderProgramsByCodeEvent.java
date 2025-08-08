//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetProviderProgramsByCodeEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class GetProviderProgramsByCodeEvent extends RequestEvent 
{
   //public String programName;
  // public Date endDateFrom;
  // public Date endDateTo;
   //public String statusId;
  // public String targetInterventionId;
  // public String stateProgramCode;
  // private String serviceProviderId;
  // private String serviveId;
   private String agencyId;
   private String programCode; // newly added class by Sruti
   
   /**
    * @roseuid 450ACF5A0085
    */
   public GetProviderProgramsByCodeEvent() 
   {
    
   }
	/**
	 * @return Returns the programCode.
	 */
	public String getProgramCode() {
		return programCode;
	}
	/**
	 * @param programCode The programCode to set.
	 */
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}
	

	
/**
 * @return Returns the agencyId.
 */
public String getAgencyId() {
	return agencyId;
}
/**
 * @param agencyId The agencyId to set.
 */
public void setAgencyId(String agencyId) {
	this.agencyId = agencyId;
}
}
