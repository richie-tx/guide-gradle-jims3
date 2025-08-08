/*
 * Created on July 9th, 2007
 */
package messaging.juvenilewarrant.reply;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import mojo.km.messaging.ResponseEvent;

/**
 * @author ugopinath
 */
public class JuvenileOffenderTrackingCoActorResponseEvent extends ResponseEvent
{
	private String daLogNum;
	private String seqNum;
	private Map coActors;
	private String name;
	

	public JuvenileOffenderTrackingCoActorResponseEvent()
	{
		this.coActors = new TreeMap();
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
		
	/**
	 * @return Returns the daLogNum.
	 */
	public String getDaLogNum() {
		return daLogNum;
	}
	/**
	 * @param daLogNum The daLogNum to set.
	 */
	public void setDaLogNum(String daLogNum) {
		this.daLogNum = daLogNum;
	}
	/**
	 * @return Returns the seqNum.
	 */
	public String getSeqNum() {
		return seqNum;
	}
	/**
	 * @param seqNum The seqNum to set.
	 */
	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}
	/**
	 * @param list
	 */
	public void addCoActors(String seqNum, String name)
	{
		try
		{
			Integer seq = new Integer(seqNum);
			coActors.put(seq, name);
		}
		catch (NumberFormatException e)
		{
			// TODO Handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * @return
	 */
	public List getCoActors()
	{
		List actors;
		if (coActors != null)
		{
			actors = new ArrayList(this.coActors.values());
		}
		else
		{
			actors = new ArrayList();
		}
		return actors;
	}


}