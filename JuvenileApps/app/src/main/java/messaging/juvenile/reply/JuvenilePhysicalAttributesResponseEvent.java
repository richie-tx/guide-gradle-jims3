/*
 * Created on Jun 15, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenile.reply;

import java.util.Collection;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.DateUtil;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenilePhysicalAttributesResponseEvent extends ResponseEvent implements Comparable
{
	private Collection tattoos;
	private String tattoosDesc;
	private String juvenileNum;
	private String weight;
	private String eyeColor;
	private String hairColor;
	private Date entryDate;
	private String build;
	private String complexion;
	private String heightFeet;
	private String heightInch;
	private String physicalAttributesId;
	private String entryDateAsString;
	private Collection otherTattooComments;
	private String otherTattooCommentsAsString;

	/**
	 * @return
	 */
	public String getBuild()
	{
		return build;
	}

	/**
	 * @return the complexion
	 */
	public String getComplexion() {
		return complexion;
	}

	/**
	 * @return
	 */
	public Date getEntryDate()
	{
		return entryDate;
	}

	public long getEntryDateAsLong()
		{
			if(entryDate!=null)
				return entryDate.getTime();
			else
				return 0;
		}

	/**
	 * @return
	 */
	public String getEntryDateAsString()
	{
		String dateStr = "";
		if(this.entryDate != null){
			dateStr = DateUtil.dateToString(entryDate, "MM/dd/yyyy HH:mm");
		}
		return dateStr;
	}

	/**
	 * @return
	 */
	public String getEyeColor()
	{
		return eyeColor;
	}

	/**
	 * @return
	 */
	public String getHairColor()
	{
		return hairColor;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @return
	 */
	public String getWeight()
	{
		return weight;
	}

	/**
	 * @param string
	 */
	public void setBuild(String string)
	{
		build = string;
	}

	/**
	 * @param complexion the complexion to set
	 */
	public void setComplexion(String complexion) {
		this.complexion = complexion;
	}

	/**
	 * @param date
	 */
	public void setEntryDate(Date date)
	{
		entryDate = date;
	}

	/**
	 * @param string
	 */
	public void setEyeColor(String string)
	{
		eyeColor = string;
	}

	/**
	 * @param string
	 */
	public void setHairColor(String string)
	{
		hairColor = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @param string
	 */
	public void setWeight(String string)
	{
		weight = string;
	}

	/**
	 * @return
	 */
	public String getHeightFeet()
	{
		return heightFeet;
	}

	/**
	 * @return
	 */
	public String getHeightInch()
	{
		return heightInch;
	}

	/**
	 * @param string
	 */
	public void setHeightFeet(String string)
	{
		heightFeet = string;
	}

	/**
	 * @param string
	 */
	public void setHeightInch(String string)
	{
		heightInch = string;
	}

	public int compareTo(Object obj) throws ClassCastException
	{
		if(obj==null)
			return -1;
		if(this.entryDate==null)
			return 1;
		JuvenilePhysicalAttributesResponseEvent evt = (JuvenilePhysicalAttributesResponseEvent) obj;
		if(evt.getEntryDate() == null)
			return -1;
		return evt.getEntryDate().compareTo(entryDate);
		
	}
	/**
	 * @return
	 */
	public String getPhysicalAttributesId()
	{
		return physicalAttributesId;
	}

	/**
	 * @param string
	 */
	public void setPhysicalAttributesId(String string)
	{
		physicalAttributesId = string;
	}

	/**
		 * @return
		 */
	public Collection getTattoos()
	{
		return tattoos;
	}

	/**
	 * @param collection
	 */
	public void setTattoos(Collection collection)
	{
		tattoos = collection;
	}
	
	public void setTattoosDescription(String desc) {
		tattoosDesc = desc;
	}

	/**
	 * @return String Tattoos description
	 */
	public String getTattoosDescription()
	{
		return tattoosDesc;

	}
	/**
	 * @return
	 */
	public Collection getOtherTattooComments()
	{
		return otherTattooComments;
	}

	/**
	 * @param string
	 */
	public void setOtherTattooComments(Collection string)
	{
		otherTattooComments = string;
	}

	public String getOtherTattooCommentsAsString() {
		return otherTattooCommentsAsString;
	}

	public void setOtherTattooCommentsAsString(String otherTattooCommentsAsString) {
		this.otherTattooCommentsAsString = otherTattooCommentsAsString;
	}

}