package pd.juvenilecase;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class JJSChainNumControl extends PersistentObject{
	
	private String nextChainNum;
	private Date lcDate;
	private String chainNum;
	private Date lcTime;
	private String lcUser;
	
	/**
	 * @roseuid 43BA9F090142
	 */
	public JJSChainNumControl()
	{
		
	}

	public String getNextChainNum() {
		fetch();
		return nextChainNum;
	}

	public void setNextChainNum(String nextChainNum) {
		if (this.nextChainNum == null || !this.nextChainNum.equals(nextChainNum))
		{
			markModified();
		}
		this.nextChainNum = nextChainNum;
	}

	public Date getLcDate() {
		fetch();
		return lcDate;
	}

	public void setLcDate(Date lcDate) {
		if (this.lcDate == null || !this.lcDate.equals(lcDate))
		{
			markModified();
		}
		this.lcDate = lcDate;
	}

	public String getChainNum() {
		return chainNum;
	}

	public void setChainNum(String chainNum) {
		if (this.chainNum == null || !this.chainNum.equals(chainNum))
		{
			markModified();
		}
		this.chainNum = chainNum;
	}
	
	/**
	* @param chainNum
	* @roseuid 427136D900BB
	*/
	static public JJSChainNumControl find(String chainNum)
	{
		IHome home = new Home();
		return (JJSChainNumControl) home.find("chainNum", chainNum, JJSChainNumControl.class);
	}
	/**
	* @return 
	* @param event
	*/
	static public Iterator findAll()
	{
		IHome home = new Home();
		return home.findAll(JJSChainNumControl.class);
	}

	public Date getLcTime() {
		fetch();
		return lcTime;
	}

	public void setLcTime(Date lcTime) {
		if (this.lcTime == null || !this.lcTime.equals(lcTime))
		{
			markModified();
		}
		this.lcTime = lcTime;
	}

	public String getLcUser() {
		fetch();
		return lcUser;
	}

	public void setLcUser(String lcUser) {
		if (this.lcUser == null || !this.lcUser.equals(lcUser))
		{
			markModified();
		}
		this.lcUser = lcUser;
	}

	
}
