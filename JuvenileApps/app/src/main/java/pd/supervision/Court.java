package pd.supervision;

import java.util.Iterator;

import pd.criminalcase.CriminalCase;

import naming.PDConstants;

import messaging.supervisionoptions.reply.CourtResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.Name;

/**
* @roseuid 42F7C38F000F
*/
public class Court extends PersistentObject
{
	private String courtNumber;
	private String courtCategory;
	private String judgeFirstName;
	private String judgeLastName;
	private String description;
	private String address;

	/**
	* @return Court
	* @param courtId
	* @roseuid 4107B06D01B5
	*/
	static public Court find(String courtId)
	{
		IHome home = new Home();
		Court court = (Court) home.find(courtId, Court.class);
		return court;
	}
	/**
	* @return Court
	* @param attrName
	* @param attrValue
	* @roseuid 4107B06D01B5
	*/
	static public Court find(String attrName, String attrValue)
	{
		IHome home = new Home();
		Court court = (Court) home.find(attrName, attrValue, Court.class);
		return court;
	}

	/**
	* @roseuid 42F7C38F000F
	*/
	public Court()
	{
	}
	/**
	* Access method for the courtNumber property.
	* @return the current value of the courtNumber property
	*/
	public String getCourtNumber()
	{
		fetch();
		return courtNumber;
	}
	/**
	* Sets the value of the courtNumber property.
	* @param aCourtNumber the new value of the courtNumber property
	*/
	public void setCourtNumber(String aCourtNumber)
	{
		if (this.courtNumber == null || !this.courtNumber.equals(aCourtNumber))
		{
			markModified();
		}
		courtNumber = aCourtNumber;
	}
	/**
	* Access method for the judgeLastName property.
	* @return the current value of the judgeLastName property
	*/
	public String getJudgeLastName()
	{
		fetch();
		return judgeLastName;
	}
	/**
	* Sets the value of the judgeLastName property.
	* @param aJudgeLastName the new value of the judgeLastName property
	*/
	public void setJudgeLastName(String aJudgeLastName)
	{
		if (this.judgeLastName == null || !this.judgeLastName.equals(aJudgeLastName))
		{
			markModified();
		}
		judgeLastName = aJudgeLastName;
	}
	/**
	* Access method for the judgeFirstName property.
	* @return the current value of the judgeFirstName property
	*/
	public String getJudgeFirstName()
	{
		fetch();
		return judgeFirstName;
	}
	/**
	* Sets the value of the judgeFirstName property.
	* @param aJudgeFirstName the new value of the judgeFirstName property
	*/
	public void setJudgeFirstName(String aJudgeFirstName)
	{
		if (this.judgeFirstName == null || !this.judgeFirstName.equals(aJudgeFirstName))
		{
			markModified();
		}
		judgeFirstName = aJudgeFirstName;
	}
	/**
	*/
	public void setCourtCategory(String aCategory)
	{
		if (this.courtCategory == null || !this.courtCategory.equals(aCategory))
		{
			markModified();
		}
		this.courtCategory = aCategory;
	}
	/**
	*/
	public String getCourtCategory()
	{
		fetch();
		return courtCategory;
	}
	/**
	*/
	static public Iterator findAll()
	{
		IHome home = new Home();
		return home.findAll(Court.class);

		//		ArrayList list = new ArrayList();
		//		Court tmp;
		//		tmp = new Court();
		//		tmp.setCourtNumber("001");
		//		tmp.setCourtCategory("CC");
		//		tmp.setJudgeFirstName("REAGAN C.");
		//		tmp.setJudgeLastName("HELM");
		//		list.add(tmp);
		//		tmp = new Court();
		//		tmp.setCourtNumber("002");
		//		tmp.setCourtCategory("CC");
		//		tmp.setJudgeFirstName("MICHAEL A.");
		//		tmp.setJudgeLastName("PETERS");
		//		list.add(tmp);
		//		tmp = new Court();
		//		tmp.setCourtNumber("003");
		//		tmp.setCourtCategory("CC");
		//		tmp.setJudgeFirstName("DONALD W.");
		//		tmp.setJudgeLastName("JACKSON");
		//		list.add(tmp);
		//		tmp = new Court();
		//		tmp.setCourtNumber("174");
		//		tmp.setCourtCategory("CD");
		//		tmp.setJudgeFirstName("DONALD W.");
		//		tmp.setJudgeLastName("JACKSON");
		//		list.add(tmp);
		//		tmp = new Court();
		//		tmp.setCourtNumber("176");
		//		tmp.setCourtCategory("CD");
		//		tmp.setJudgeFirstName("BRIAN");
		//		tmp.setJudgeLastName("RAINS");
		//		list.add(tmp);
		//		tmp = new Court();
		//		tmp.setCourtNumber("177");
		//		tmp.setCourtCategory("CD");
		//		tmp.setJudgeFirstName("DEVON");
		//		tmp.setJudgeLastName("ANDERSON");
		//		list.add(tmp);
		//		tmp = new Court();
		//		tmp.setCourtNumber("INM");
		//		tmp.setCourtCategory("CR");
		//		tmp.setJudgeFirstName("INTERSTATE MISDEMEANOR");
		//		tmp.setJudgeLastName("");
		//		list.add(tmp);
		//		tmp = new Court();
		//		tmp.setCourtNumber("INF");
		//		tmp.setCourtCategory("CR");
		//		tmp.setJudgeFirstName("INTERSTATE FELONY");
		//		tmp.setJudgeLastName("");
		//		list.add(tmp);
		//		return list.iterator();
	}
	
	/**
	 * @return
	 * @param event
	 */
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, Court.class);
	}
	
	/**
	* @return 
	*/
	public String getDescription()
	{
		fetch();
		return description;
	}
	/**
	* @param string
	*/
	public void setDescription(String string)
	{
		if (this.description == null || !this.description.equals(string))
		{
			markModified();
		}
		description = string;
	}
	/**
	 * Creates response event object
	 * @return
	 */
	public CourtResponseEvent getResponseEvent()
	{
		CourtResponseEvent cre = new CourtResponseEvent();
		cre.setCourtCategory(this.getCourtCategory());
		cre.setCourtNumber(this.getCourtNumber());
		cre.setDescription(this.getDescription());
		cre.setJudgeFirstName(this.getJudgeFirstName());
		cre.setJudgeLastName(this.getJudgeLastName());
		return cre;
	}
	/**
	 * @return
	 */
	public String getFormattedJudgeName()
	{
		String fullName = PDConstants.BLANK;
		Name name = new Name(this.getJudgeFirstName(), PDConstants.BLANK, this.getJudgeLastName());
		if (name != null)
		{
			fullName = name.getFullNameFirst();
		}
		return fullName;
	}
	
	public String getAddress() {
		fetch();
		return address;
	}
	
	public void setAddress(String aAddress) {
		if (this.address == null || !this.address.equals(aAddress))
		{
			markModified();
		}
		this.address = aAddress;
	}
}
