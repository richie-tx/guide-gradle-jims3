package pd.juvenilewarrant;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileOffenderTrackingSummaryOfFacts extends PersistentObject
{
	private String daLogNum;
	private String text;
	private String seqNum;
	/**
	* @roseuid 417FB39A0195
	*/
	public JuvenileOffenderTrackingSummaryOfFacts()
	{
	}
	/**
	* Access method for the text property.
	* @return the current value of the text property
	*/
	public String getText()
	{
		fetch();
		return text;
	}
	/**
	* Sets the value of the text property.
	* @param aText the new value of the text property
	*/
	public void setText(String aText)
	{
		if (this.text == null || !this.text.equals(aText))
		{
			markModified();
		}
		text = aText;
	}
	/**
	* Access method for the daLogNum property.
	* @return the current value of the daLogNum property
	*/
	public String getDaLogNum()
	{
		fetch();
		return daLogNum;
	}
	/**
	* Sets the value of the daLogNum property.
	* @param aDaLogNum the new value of the daLogNum property
	*/
	public void setDaLogNum(String aDaLogNum)
	{
		if (this.daLogNum == null || !this.daLogNum.equals(aDaLogNum))
		{
			markModified();
		}
		daLogNum = aDaLogNum;
	}
	/**
	* Access method for the seqNum property.
	* @return the current value of the seqNum property
	*/
	public String getSeqNum()
	{
		fetch();
		return seqNum;
	}
	/**
	* Sets the value of the seqNum property.
	* @param aSeqNum the new value of the seqNum property
	*/
	public void setSeqNum(String aSeqNum)
	{
		if (this.seqNum == null || !this.seqNum.equals(aSeqNum))
		{
			markModified();
		}
		seqNum = aSeqNum;
	}
	/**
	* @roseuid 4107B06C01EB
	* @return Iterator event
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JuvenileOffenderTrackingSummaryOfFacts.class);
	}
}
