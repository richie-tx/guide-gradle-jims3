/*
 * Created on Jun 21, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenile;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileDrugRequestEvent extends RequestEvent
{
	private String supervisionNum;
	private String degree = null;
	private String juvenileNum;
	private String amountSpent = null;
	private String frequency = null;
	private String drugType = null;
	private String locationOfUse=null;
	private int onsetAge;
	private boolean toBeDeleted = false;
	private String temporaryId;
	private Date entryDate=null;
	
	// just display purpose fields for descriptions in the ui.
	private String degreeDesc;
	private String amountSpentDesc;
	private String frequencyDesc;
	private String drugTypeDesc;
	private String locationOfUseDesc;
	/**
	 * @return
	 */
	public String getDegree()
	{
		return degree;
	}

	/**
	 * @return
	 */
	public String getDrugType()
	{
		return drugType;
	}

	/**
	 * @return
	 */
	public String getFrequency()
	{
		return frequency;
	}

	/**
	 * @return
	 */
	public int getOnsetAge()
	{
		return onsetAge;
	}

	/**
	 * @return
	 */
	public String getSupervisionNum()
	{
		return supervisionNum;
	}

	/**
	 * @return
	 */
	public String getAmountSpent()
	{
		return amountSpent;
	}

	/**
	 * @param string
	 */
	public void setDegree(String string)
	{
		degree = string;
	}

	/**
	 * @param string
	 */
	public void setDrugType(String string)
	{
		drugType = string;
	}

	/**
	 * @param string
	 */
	public void setFrequency(String string)
	{
		frequency = string;
	}

	/**
	 * @param i
	 */
	public void setOnsetAge(int i)
	{
		onsetAge = i;
	}

	/**
	 * @param string
	 */
	public void setSupervisionNum(String string)
	{
		supervisionNum = string;
	}

	/**
	 * @param string
	 */
	public void setAmountSpent(String string)
	{
		amountSpent = string;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}
	
	public boolean isMarkedForDeletion() {
		return toBeDeleted;
	}
	
	public void markForDeletion() {
		toBeDeleted = true;
	}
	

	/**
	 * @return
	 */
	public String getTemporaryId()
	{
		return temporaryId;
	}

	/**
	 * @param string
	 */
	public void setTemporaryId(String string)
	{
		temporaryId = string;
	}

	/**
	 * @return
	 */
	public String getDegreeDesc()
	{
		return degreeDesc;
	}

	/**
	 * @return
	 */
	public String getDrugTypeDesc()
	{
		return drugTypeDesc;
	}

	/**
	 * @return
	 */
	public String getFrequencyDesc()
	{
		return frequencyDesc;
	}

	/**
	 * @return
	 */
	public String getAmountSpentDesc()
	{
		return amountSpentDesc;
	}

	/**
	 * @param string
	 */
	public void setDegreeDesc(String string)
	{
		degreeDesc = string;
	}

	/**
	 * @param string
	 */
	public void setDrugTypeDesc(String string)
	{
		drugTypeDesc = string;
	}

	/**
	 * @param string
	 */
	public void setFrequencyDesc(String string)
	{
		frequencyDesc = string;
	}

	/**
	 * @param string
	 */
	public void setAmountSpentDesc(String string)
	{
		amountSpentDesc = string;
	}

	/**
	 * @return
	 */
	public Date getEntryDate()
	{
		return entryDate;
	}

	/**
	 * @param date
	 */
	public void setEntryDate(Date date)
	{
		entryDate = date;
	}

	/**
	 * @return
	 */
	public String getLocationOfUse()
	{
		return locationOfUse;
	}

	/**
	 * @return
	 */
	public String getLocationOfUseDesc()
	{
		return locationOfUseDesc;
	}

	/**
	 * @param string
	 */
	public void setLocationOfUse(String string)
	{
		locationOfUse = string;
	}

	/**
	 * @param string
	 */
	public void setLocationOfUseDesc(String string)
	{
		locationOfUseDesc = string;
	}

}
