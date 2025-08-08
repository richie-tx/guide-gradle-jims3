package pd.supervision.supervisionstaff.cscdstaffposition;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @roseuid 460BDC6603B4
 */
public class CSCDStaffProfile extends PersistentObject
{
	private String cjadNum;
	/**
	 * Properties for staffPosition
	 */
	private CSCDStaffPosition staffPosition = null;
	private String staffPositionId;

	/**
	 * @roseuid 460BDC6603B4
	 */
	public CSCDStaffProfile()
	{
	}

	public static Iterator findAll(IEvent anEvent){
	    IHome home = new Home();
	    return home.findAll(anEvent, CSCDStaffProfile.class);
	}
	public static Iterator findAll(String attrName, String attrValue){
	    IHome home = new Home();
	    return home.findAll(attrName, attrValue, CSCDStaffProfile.class);
	}
	public CSCDStaffProfile bind(){
		IHome home = new Home();
		home.bind(this);
		return this;
	}
	/**
	 * 
	 * @return Returns the cjadNum.
	 */
	public String getCjadNum()
	{
		fetch();
		return cjadNum;
	}

	/**
	 * 
	 * @param cjadNum The cjadNum to set.
	 */
	public void setCjadNum(String cjadNum)
	{
		if (this.cjadNum == null || !this.cjadNum.equals(cjadNum))
		{
			markModified();
		}
		this.cjadNum = cjadNum;
	}

	/**
	 * Set the reference value to class :: pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	public void setStaffPositionId(String staffPositionId)
	{
		if (this.staffPositionId == null || !this.staffPositionId.equals(staffPositionId))
		{
			markModified();
		}
		staffPosition = null;
		this.staffPositionId = staffPositionId;
	}

	/**
	 * Get the reference value to class :: pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	public String getStaffPositionId()
	{
		fetch();
		return staffPositionId;
	}

	/**
	 * Initialize class relationship to class pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	private void initStaffPosition()
	{
		if (staffPosition == null)
		{
			staffPosition = (CSCDStaffPosition) new mojo.km.persistence.Reference(
					staffPositionId, CSCDStaffPosition.class)
					.getObject();
		}
	}

	/**
	 * Gets referenced type pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	public CSCDStaffPosition getStaffPosition()
	{
		initStaffPosition();
		return staffPosition;
	}

	/**
	 * set the type reference for class member staffPosition
	 */
	public void setStaffPosition(CSCDStaffPosition staffPosition)
	{
		if (this.staffPosition == null || !this.staffPosition.equals(staffPosition))
		{
			markModified();
		}
		if (staffPosition.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(staffPosition);
		}
		setStaffPositionId("" + staffPosition.getOID());
		this.staffPosition = (CSCDStaffPosition) new mojo.km.persistence.Reference(
				staffPosition).getObject();
	}

}
