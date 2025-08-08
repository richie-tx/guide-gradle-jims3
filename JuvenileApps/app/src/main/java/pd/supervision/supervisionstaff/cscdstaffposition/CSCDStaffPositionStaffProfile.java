/*
 * Created on Apr 26, 2007
 *
 */
package pd.supervision.supervisionstaff.cscdstaffposition;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

/**
 * @author dgibler
 *
 */
public class CSCDStaffPositionStaffProfile extends CSCDStaffPosition {
    private String cjadNum;
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
	/* (non-Javadoc)
	 * @see pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionStaffProfile#findAll(mojo.km.messaging.IEvent)
	 */
	public static Iterator findAll(IEvent anEvent){
	    IHome home = new Home();
	    return home.findAll(anEvent, CSCDStaffPositionStaffProfile.class);
	}

}
