/*
 * Created on March 16, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.supplementaldocuments.transactions;

import java.util.Iterator;

import pd.supervision.supplementaldocuments.SupplementalDocuments;
import messaging.supplementaldocuments.GetSupplementalDocumentsEvent;
import messaging.supplementaldocuments.reply.SupplementalDocumentsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author RCapestani
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetSupplementalDocumentsCommand implements ICommand
{
	/* (non-Javadoc)
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
	public void execute(IEvent anEvent)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		GetSupplementalDocumentsEvent requestEvent = (GetSupplementalDocumentsEvent) anEvent;
		Iterator reportIter = SupplementalDocuments.findAll();
	    SupplementalDocumentsResponseEvent reportRespEvent = null;
	    while( reportIter.hasNext() )
	    {
	    	SupplementalDocuments repCatalog = (SupplementalDocuments) reportIter.next();
	        reportRespEvent = this.buildResponseEvent(repCatalog);
	    	dispatch.postEvent(reportRespEvent);
	    }
	}

    private SupplementalDocumentsResponseEvent buildResponseEvent(SupplementalDocuments report)
    {
    	SupplementalDocumentsResponseEvent reportRespEvent = new SupplementalDocumentsResponseEvent();
        reportRespEvent.setFormCatId(report.getFormCatId());
        reportRespEvent.setFormGroup(report.getFormGroup());
        reportRespEvent.setFormTitle(report.getFormTitle());
        reportRespEvent.setUrl(report.getUrl());
        reportRespEvent.setUserParm(report.getUserParm());
        reportRespEvent.setPassWordParm(report.getPassWordParm());
        return reportRespEvent;  
    }
    
	public void onRegister(IEvent anEvent)
	{
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}
}
