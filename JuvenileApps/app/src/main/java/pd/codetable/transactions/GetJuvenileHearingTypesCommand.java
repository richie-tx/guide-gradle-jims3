package pd.codetable.transactions;

import java.util.Iterator;

import messaging.codetable.GetJuvenileHearingTypesEvent;
import messaging.codetable.criminal.reply.JuvenileHearingTypeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.criminal.JuvenileHearingTypeCode;

public class GetJuvenileHearingTypesCommand implements ICommand
{
    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event) throws Exception
    {
	GetJuvenileHearingTypesEvent evt = (GetJuvenileHearingTypesEvent)event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	IHome home = new Home();
	Iterator<JuvenileHearingTypeCode> hearingTypeCodes = home.findAll(evt, JuvenileHearingTypeCode.class);

	while (hearingTypeCodes.hasNext())
	{
	    JuvenileHearingTypeCode hearingTypeCode = hearingTypeCodes.next();
	    JuvenileHearingTypeResponseEvent hearingRespEvt = new JuvenileHearingTypeResponseEvent();
	    if (hearingTypeCode!=null && hearingTypeCode.getInactiveInd()!=null && !hearingTypeCode.getInactiveInd().equalsIgnoreCase("Y"))
	    {
		//added for court action
		if( "ANCILLARY".equalsIgnoreCase(evt.getDocketType()) ){
		    if( "A".equalsIgnoreCase(hearingTypeCode.getCategoryInd())|| ( "B".equalsIgnoreCase(hearingTypeCode.getCategoryInd())) ){
			hearingRespEvt.setCategoryInd(hearingTypeCode.getCategoryInd());
			hearingRespEvt.setCode(hearingTypeCode.getCode());
			hearingRespEvt.setDescription(hearingTypeCode.getDescription());
			hearingRespEvt.setInactiveInd(hearingTypeCode.getInactiveInd());
			hearingRespEvt.setIssueInd(hearingTypeCode.getIssueInd());
			hearingRespEvt.setDescriptionWithCode(hearingTypeCode.getCode() + " - " + hearingTypeCode.getDescription());
			 dispatch.postEvent(hearingRespEvt);
		    }
		}else if( "COURT".equalsIgnoreCase(evt.getDocketType()) ){
		    if( "N".equalsIgnoreCase(hearingTypeCode.getCategoryInd())|| ( "B".equalsIgnoreCase(hearingTypeCode.getCategoryInd())) ){
			hearingRespEvt.setCategoryInd(hearingTypeCode.getCategoryInd());
			hearingRespEvt.setCode(hearingTypeCode.getCode());
			hearingRespEvt.setDescription(hearingTypeCode.getDescription());
			hearingRespEvt.setInactiveInd(hearingTypeCode.getInactiveInd());
			hearingRespEvt.setIssueInd(hearingTypeCode.getIssueInd());
			hearingRespEvt.setDescriptionWithCode(hearingTypeCode.getCode() + " - " + hearingTypeCode.getDescription());
			 dispatch.postEvent(hearingRespEvt);
		    }
		}else{
		    hearingRespEvt.setCategoryInd(hearingTypeCode.getCategoryInd());
		    hearingRespEvt.setCode(hearingTypeCode.getCode());
		    hearingRespEvt.setDescription(hearingTypeCode.getDescription());
		    hearingRespEvt.setInactiveInd(hearingTypeCode.getInactiveInd());
		    hearingRespEvt.setIssueInd(hearingTypeCode.getIssueInd());
		    hearingRespEvt.setDescriptionWithCode(hearingTypeCode.getCode() + " - " + hearingTypeCode.getDescription());
		    dispatch.postEvent(hearingRespEvt);
		}
	    }
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event)
    {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event)
    {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject)
    {
	// TODO Auto-generated method stub

    }

}
