package pd.supervision.supervisionstaff.cscdstaffposition;

import java.util.Iterator;

import mojo.km.persistence.Home;
import pd.supervision.Court;

public class CSCDStaffPositionCourt extends mojo.km.persistence.PersistentObject
{
	private Court court;
	private CSCDStaffPosition staffPosition;
	private String courtId;
	private String staffPositionId;

	/**
	 * Set the reference value to class :: pd.supervision.Court
	 */
	public void setCourtId(String childId)
	{
		if (this.courtId == null || !this.courtId.equals(childId))
		{
			markModified();
		}
		court = null;
		this.courtId = childId;
	}

	/**
	 * Get the reference value to class :: pd.supervision.Court
	 */
	public String getCourtId()
	{
		fetch();
		return courtId;
	}

	/**
	 * Initialize class relationship to class pd.supervision.Court
	 */
	private void initCourt()
	{
		if (court == null)
		{
			court = (Court) new mojo.km.persistence.Reference(courtId, Court.class)
					.getObject();
		}
	}

	/**
	 * Gets referenced type pd.supervision.Court
	 */
	public Court getCourt()
	{
		fetch();
		initCourt();
		return court;
	}

	/**
	 * set the type reference for class member child
	 */
	public void setCourt(Court child)
	{
		if (this.court == null || !this.court.equals(child))
		{
			markModified();
		}
		if (child.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(child);
		}
		setCourtId("" + child.getOID());
		this.court = (Court) new mojo.km.persistence.Reference(child).getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	public void setStaffPositionId(String parentId)
	{
		if (this.staffPositionId == null || !this.staffPositionId.equals(parentId))
		{
			markModified();
		}
		staffPosition = null;
		this.staffPositionId = parentId;
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
					staffPositionId, CSCDStaffPosition.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition
	 */
	public CSCDStaffPosition getStaffPosition()
	{
		fetch();
		return staffPosition;
	}

	/**
	 * set the type reference for class member parent
	 */
	public void setStaffPosition(CSCDStaffPosition parent)
	{
		if (this.staffPosition == null || !this.staffPosition.equals(parent))
		{
			markModified();
		}
		if (parent.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(parent);
		}
		setStaffPositionId("" + parent.getOID());
		this.staffPosition = (CSCDStaffPosition) new mojo.km.persistence.Reference(
				parent).getObject();
	}

	public static Iterator findAll(String attributeName, String attributeValue) {
		return new Home().findAll(attributeName, attributeValue, CSCDStaffPositionCourt.class);
	}
}
