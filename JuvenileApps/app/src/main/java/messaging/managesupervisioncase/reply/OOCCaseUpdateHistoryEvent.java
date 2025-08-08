package messaging.managesupervisioncase.reply;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * @author jmcnabb
 *
 */
public class OOCCaseUpdateHistoryEvent extends ResponseEvent
{
	private Collection updateHistory = new ArrayList();
	

	/**
	 * @return Returns the updateHistory.
	 */
	public Collection getUpdateHistory()
	{
		return updateHistory;
	}
	
	/**
	 * @param updateHistory The updateHistory to set.
	 */
	public void setUpdateHistory(Collection updateHistory)
	{
		this.updateHistory = updateHistory;
	}
}
