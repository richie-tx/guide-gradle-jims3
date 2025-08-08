package pd.juvenile;

import java.util.Date;
import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class JuvenileJIS extends PersistentObject 
{
	
	private String juvenileNum;
	
	private Date entryDate;
	 
	private String agency;
	 
	private String otherAgency;
	 
	private String comments;
	
	private String jisId;
	/**
	 * @roseuid 42B062E7022B
	 */
	public JuvenileJIS()
	{
	}


	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		if (this.juvenileNum == null || !this.juvenileNum.equals(juvenileNum))
		{
			markModified();
		}
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		fetch();
		return juvenileNum;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setEntryDate(Date entryDate) {
		if (this.entryDate == null || !this.entryDate.equals(entryDate))
		{
			markModified();
		}
		this.entryDate = entryDate;
	}
	
	/**
	 * @return the entryDate
	 */
	public Date getEntryDate() {
		fetch();
		return entryDate;
	}
	public String getAgency() {
		fetch();
		return agency;
	}


	public void setAgency(String agency) {
		if(this.agency==null || !this.agency.equals(agency))
		{
			markModified();
		}
		this.agency = agency;
	}


	public String getOtherAgency() {
		fetch();
		return otherAgency;
	}


	public void setOtherAgency(String otherAgency) {
		if(this.otherAgency == null || !this.otherAgency.equals(otherAgency))
		{
			markModified();
		}
		this.otherAgency = otherAgency;
	}


	public String getComments() {
		fetch();
		return comments;
	}


	public void setComments(String comments) {
		if (this.comments == null || !this.comments.equals(comments))
		{
			markModified();
		}
		this.comments = comments;
	}


	/**
	 * @return the jisId
	 */
	public String getJisId() {
		return jisId;
	}


	/**
	 * @param jisId the jisId to set
	 */
	public void setJisId(String jisId) {
		this.jisId = jisId;
	}
	
	/**
	 * Finds all jis records by an attribute value
	 * 
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		Iterator benefits = home.findAll(attributeName, attributeValue, JuvenileJIS.class);
		return benefits;
	}
	
	/**
	 * @return JuvenileJIS
	 * @param jisId
	 */
	static public JuvenileJIS find(String jisId)
	{
		IHome home = new Home();
		JuvenileJIS jisInfo = (JuvenileJIS) home.find(jisId, JuvenileJIS.class);
		return jisInfo;
	}

}	