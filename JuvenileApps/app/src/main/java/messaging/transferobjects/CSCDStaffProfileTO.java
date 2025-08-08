/*
 * Created on Aug 20, 2007
 *
 */
package messaging.transferobjects;



/**
 * @author cc_mdsouza
 *
 */
public class CSCDStaffProfileTO 
extends PersistentObjectTO
{

	private String cjadNum;
	private CSCDStaffPositionTO staffPosition = null;
	private String staffPositionId;

	public CSCDStaffProfileTO()
	{
	}

	
	
	
	/**
	 * @return Returns the cjadNum.
	 */
	public String getCjadNum() {
		return cjadNum;
	}
	/**
	 * @param cjadNum The cjadNum to set.
	 */
	public void setCjadNum(String cjadNum) {
		this.cjadNum = cjadNum;
	}
	/**
	 * @return Returns the staffPosition.
	 */
	public CSCDStaffPositionTO getStaffPosition() {
		return staffPosition;
	}
	/**
	 * @param staffPosition The staffPosition to set.
	 */
	public void setStaffPosition(CSCDStaffPositionTO staffPosition) {
		this.staffPosition = staffPosition;
	}
	/**
	 * @return Returns the staffPositionId.
	 */
	public String getStaffPositionId() {
		return staffPositionId;
	}
	/**
	 * @param staffPositionId The staffPositionId to set.
	 */
	public void setStaffPositionId(String staffPositionId) {
		this.staffPositionId = staffPositionId;
	}
}
