//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\cscdstaffposition\\GetOrganizationsEvent.java

package messaging.cscdstaffposition;

import mojo.km.messaging.RequestEvent;

public class GetOrganizationHierarchyEvent extends RequestEvent 
{
	private String agencyId;
	private String divisionId;
	private String programUnitId;
	
   /**
    * @roseuid 460BDCC70105
    */
   public GetOrganizationHierarchyEvent() 
   {
    
   }
	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		return agencyId;
	}
    /**
     * @return Returns the divisionId.
     */
    public String getDivisionId() {
        return divisionId;
    }
    /**
     * @return Returns the programUnitId.
     */
    public String getProgramUnitId() {
        return programUnitId;
    }
	/**
	 * @param agencyId The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
    /**
     * @param divisionId The divisionId to set.
     */
    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }
    /**
     * @param programUnitId The programUnitId to set.
     */
    public void setProgramUnitId(String programUnitId) {
        this.programUnitId = programUnitId;
    }
}
