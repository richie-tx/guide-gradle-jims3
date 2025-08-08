//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\cscdstaffposition\\VerifyCjadNumEvent.java

package messaging.cscdstaffposition;

import mojo.km.messaging.RequestEvent;

public class VerifyCjadNumEvent extends RequestEvent 
{
	public String getUserProfileId() {
		return userProfileId;
	}
	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}
	private String agencyId;
	private	String cjadNum;
	private	String staffPositionId;
	private String userProfileId;//needed for comparison if user is already assigned to a position.
	/**
    * @roseuid 460BDCFE02F9
    */
   public VerifyCjadNumEvent() 
   {
    
   }
    /**
     * @return Returns the agencyId.
     */
    public String getAgencyId() {
        return agencyId;
    }
	/**
	 * @return Returns the cjadNum.
	 */
	public String getCjadNum() {
		return cjadNum;
	}
	/**
	 * @return Returns the staffPositionId.
	 */
	public String getStaffPositionId() {
		return staffPositionId;
	}
    /**
     * @param agencyId The agencyId to set.
     */
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
	/**
	 * @param cjadNum The cjadNum to set.
	 */
	public void setCjadNum(String cjadNum) {
		this.cjadNum = cjadNum;
	}
	/**
	 * @param staffPositionId The staffPositionId to set.
	 */
	public void setStaffPositionId(String staffPositionId) {
		this.staffPositionId = staffPositionId;
	}
}
