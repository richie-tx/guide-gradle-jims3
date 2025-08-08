/*
 * Created on Jan 30, 2007
 *
 */
package messaging.codetable.reply;



import java.util.Iterator;
import java.util.List;
import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 */
public class CodetableCompoundListResponseEvent extends ResponseEvent implements Comparable
{
	private String codetableIdentifier;
	private List list;
	private List activeList;
	
	
	/**
	 * @return Returns the codetableIdentifier.
	 */
	public String getCodetableIdentifier() {
		return codetableIdentifier;
	}
	/**
	 * @param codetableIdentifier The codetableIdentifier to set.
	 */
	public void setCodetableIdentifier(String codetableIdentifier) {
		this.codetableIdentifier = codetableIdentifier;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * @return Returns the list.
	 */
	public List getList() {
		return list;
	}
	/**
	 * @param list The list to set.
	 */
	public void setList(List list) {
		this.list = list;
	}
	
	
	
	public List getActiveList()
	{
	    return activeList;
	}
	public void setActiveList(List activeList)
	{
	    this.activeList = activeList;
	}
	
	
	public String getDescriptionFromCode(String codeId)
	{
		for(Iterator iter = this.list.iterator();iter.hasNext();)
		{
			CodeResponseEvent cre = (CodeResponseEvent)iter.next();
			if(codeId.equalsIgnoreCase(cre.getCodeId()))
			{
				return cre.getDescription();
			}
		}
		return "";
	}
	
}
