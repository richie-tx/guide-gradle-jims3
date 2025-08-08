/*
 * Created on Oct 7, 2005
 *
 */
package pd.codetable.transactions;

import java.util.Iterator;
import java.util.List;

import naming.PDConstants;

import pd.codetable.criminal.OffenseCode;
import messaging.codetable.GetOffenseCodesEvent;
import messaging.codetable.criminal.reply.OffenseCodeResponseEvent;
import messaging.info.reply.CountInfoMessage;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;

/**
 * @author dgibler
 *
 */
public class GetOffenseCodesCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetOffenseCodesEvent getOffenseCodesEvent = (GetOffenseCodesEvent) event;

		boolean searchLimitExceeded = false;
		
		if (getOffenseCodesEvent.getLimitSearch() == null 
				|| (!getOffenseCodesEvent.getLimitSearch().equals(PDConstants.NO) 
						&& !getOffenseCodesEvent.getLimitSearch().equals(PDConstants.BLANK))){
			MetaDataResponseEvent mdre = OffenseCode.findMeta(getOffenseCodesEvent);
			if (mdre.getCount() > PDConstants.SEARCH_LIMIT){
				CountInfoMessage infoEvent = new CountInfoMessage();
				infoEvent.setCount(mdre.getCount());  
				MessageUtil.postReply(infoEvent);
				searchLimitExceeded = true;
			}
		}
		if (!searchLimitExceeded){
            Iterator iter = OffenseCode.findAll(getOffenseCodesEvent);
            List aList = CollectionUtil.iteratorToList(iter);
            OffenseCode offenseCode = null;
            OffenseCodeResponseEvent ocre = null;
            
            for (int i = 0; i < aList.size(); i++)
			{
            	offenseCode = (OffenseCode) aList.get(i);
            	if (offenseCode != null)
            		{
            			ocre = offenseCode.getResponseEvent();
            			MessageUtil.postReply(ocre);
            	}
            }
		}
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
	}

}
