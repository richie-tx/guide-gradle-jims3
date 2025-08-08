package messaging.interviewinfo;

import java.util.ArrayList;
import java.util.List;

import mojo.km.messaging.reporting.ReportRequestEvent;


public class CreateHireAttorneyReportEvent extends ReportRequestEvent 
{
	private String casefileId;
	private String juvenileNum;
	private String victimsPhysicalInjuries;
	private ArrayList guardianInfo = new ArrayList();
	
	/**
	* 
	*/
	public CreateHireAttorneyReportEvent() 
	{
    
	}
	
	/**
	 * @return
	 */
	public String getCasefileId()
	{
		return casefileId;
	}

	/**
	 * @param string
	 */
	public void setCasefileId(String string)
	{
		casefileId = string;
	}

	public void addGuardianInfo( String guardianId, String contactMethod )
	{
		guardianInfo.add( new GuardianInfo( guardianId, contactMethod ) );
	}
	
	/**
	 * @return Returns the victimsPhysicalInjuries.
	 */
	public String getVictimsPhysicalInjuries() {
		return victimsPhysicalInjuries;
	}
	/**
	 * @param victimsPhysicalInjuries The victimsPhysicalInjuries to set.
	 */
	public void setVictimsPhysicalInjuries(String victimsPhysicalInjuries) {
		this.victimsPhysicalInjuries = victimsPhysicalInjuries;
	}
	
	public List getGuardianInfo()
	{
		return guardianInfo; 
	}
	
	/**
	 * 
	 */
	public static class GuardianInfo
	{
		private String guardianId;
		private String contactMethod;

		/**
		 * 
		 */
		public GuardianInfo( String aGuardianId, String aContactMethod )
		{
			guardianId = aGuardianId;
			contactMethod = aContactMethod;
		}
		
		/**
		 * @return Returns the contactMethod.
		 */
		public String getContactMethod() {
			return contactMethod;
		}
		/**
		 * @param contactMethod The contactMethod to set.
		 */
		public void setContactMethod(String contactMethod) {
			this.contactMethod = contactMethod;
		}
		/**
		 * @return Returns the guardianId.
		 */
		public String getGuardianId() {
			return guardianId;
		}
		/**
		 * @param guardianId The guardianId to set.
		 */
		public void setGuardianId(String guardianId) {
			this.guardianId = guardianId;
		}
	}
	public String getJuvenileNum() {
		return juvenileNum;
	}
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
}
