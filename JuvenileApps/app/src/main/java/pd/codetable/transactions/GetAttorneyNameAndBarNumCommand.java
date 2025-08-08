package pd.codetable.transactions;

import java.util.Iterator;

import messaging.codetable.GetAttorneyNameAndBarNumEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.reply.AttorneyNameAndAddressResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import naming.PDConstants;
import pd.codetable.criminal.AttorneyNameAndBarNum;

/**
 * 
 * @author sthyagarajan
 *
 */
public class GetAttorneyNameAndBarNumCommand implements ICommand {

	/**
	 * 
	 */
	public GetAttorneyNameAndBarNumCommand()
	{

	}
	public void execute(IEvent event) throws Exception {
	    GetAttorneyNameAndBarNumEvent attorneyEvt = (GetAttorneyNameAndBarNumEvent) event;
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		IHome home = new Home();
		MetaDataResponseEvent metaData = (MetaDataResponseEvent) home.findMeta(event, AttorneyNameAndBarNum.class);
		 
		int totalRecords = metaData.getCount();
		 
		if (totalRecords > PDConstants.SEARCH_LIMIT){
			ErrorResponseEvent errorResp = new ErrorResponseEvent();
			errorResp.setMessage("error.max.limit.exceeded");			
			//IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(errorResp);
		}else{
		    if(attorneyEvt.getBarNum()!=null && !attorneyEvt.getBarNum().isEmpty()){
			Iterator<AttorneyNameAndBarNum> attorneyRec = AttorneyNameAndBarNum.findAll("barNum",attorneyEvt.getBarNum());
        		if (attorneyRec != null)
        		{
        		    if(attorneyRec.hasNext()){
        			AttorneyNameAndBarNum attorneyNameAndBarNum = (AttorneyNameAndBarNum) attorneyRec.next();
                		AttorneyNameAndAddressResponseEvent replyEvent = new AttorneyNameAndAddressResponseEvent();
                		replyEvent.setAttyName(attorneyNameAndBarNum.getAttyName());
                		replyEvent.setBarNum(attorneyNameAndBarNum.getBarNum());
                		replyEvent.setAttyNameHistory(attorneyNameAndBarNum.getAttyNameHistory());
                		dispatch.postEvent(replyEvent);
        		    }
        		}
        	    }else{
        		if(attorneyEvt.getAttorneyName()!=null && !attorneyEvt.getAttorneyName().isEmpty())
        		{
        		    Iterator<AttorneyNameAndBarNum> attorneyRec = AttorneyNameAndBarNum.findAll(attorneyEvt);
        		    if (attorneyRec != null)
        		    {
        			while (attorneyRec.hasNext())
        			{
        				AttorneyNameAndBarNum attorneyNameAndBarNum = (AttorneyNameAndBarNum) attorneyRec.next();
        				 AttorneyNameAndAddressResponseEvent replyEvent = new AttorneyNameAndAddressResponseEvent();
        				 replyEvent.setAttyName(attorneyNameAndBarNum.getAttyName());
        				 replyEvent.setBarNum(attorneyNameAndBarNum.getBarNum());
        				 replyEvent.setAttyNameHistory(attorneyNameAndBarNum.getAttyNameHistory());
        				 dispatch.postEvent(replyEvent);
        			 }
        		    }	
        		}
        	    }
		} //end of main else.
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
