package pd.supervision.csts;

import java.util.Iterator;
import java.util.List;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.CollectionUtil;

/**
 * 
 * @author dgibler
 */
public class CSTSStatus extends PersistentObject
{
	public static List findAll(IEvent anEvent){
		IHome home = new Home();
		Iterator iter = home.findAll(anEvent, CSTSStatus.class);
		List aList = CollectionUtil.iteratorToList(iter);
		return aList;
		
	}
	private String criminalCaseId;
	private String recType;
	private String seqNum;
	private String spn;
	private String status;

	public String getCriminalCaseId() {
		return criminalCaseId;
	}

	/**
	 * 
	 * @return Returns the recType.
	 */
	public String getRecType()
	{
		fetch();
		return recType;
	}

	/**
	 * 
	 * @return Returns the seqNum.
	 */
	public String getSeqNum()
	{
		fetch();
		return seqNum;
	}

	/**
	 * 
	 * @return Returns the spn.
	 */
	public String getSpn()
	{
		fetch();
		return spn;
	}

	/**
	 * 
	 * @return Returns the status.
	 */
	public String getStatus()
	{
		fetch();
		return status;
	}

	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}

	/**
	 * 
	 * @param recType The recType to set.
	 */
	public void setRecType(String recType)
	{
		if (this.recType == null || !this.recType.equals(recType))
		{
			markModified();
		}
		this.recType = recType;
	}

	/**
	 * 
	 * @param seqNum The seqNum to set.
	 */
	public void setSeqNum(String seqNum)
	{
		if (this.seqNum == null || !this.seqNum.equals(seqNum))
		{
			markModified();
		}
		this.seqNum = seqNum;
	}

	/**
	 * 
	 * @param spn The spn to set.
	 */
	public void setSpn(String spn)
	{
		if (this.spn == null || !this.spn.equals(spn))
		{
			markModified();
		}
		this.spn = spn;
	}
	/**
	 * 
	 * @param status The status to set.
	 */
	public void setStatus(String status)
	{
		if (this.status == null || !this.status.equals(status))
		{
			markModified();
		}
		this.status = status;
	}
}
