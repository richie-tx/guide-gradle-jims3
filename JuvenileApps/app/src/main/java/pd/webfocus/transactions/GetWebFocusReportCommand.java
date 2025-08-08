/*
 * Created on Feb 21, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.webfocus.transactions;

import java.util.Iterator;

import pd.webfocus.WebFocusReportCatalog;
import messaging.webfocus.GetWebFocusReportEvent;
import messaging.webfocus.reply.WebFocusReportCatalogResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author RYoung
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetWebFocusReportCommand implements ICommand
{

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event) throws Exception
    {

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        GetWebFocusReportEvent requestEvent = (GetWebFocusReportEvent) event;
		
       // WebFocusReportCatalog report = WebFocusReportCatalog.find(requestEvent.getWebFocusCatId());
        Iterator reportIter = WebFocusReportCatalog.findAll("webFocusName",requestEvent.getWebFocusName());
        WebFocusReportCatalogResponseEvent reportRespEvent = null;
        while(reportIter.hasNext()){
            
            WebFocusReportCatalog repCatalog = (WebFocusReportCatalog) reportIter.next();
            reportRespEvent = this.buildResponseEvent(repCatalog);
    	    dispatch.postEvent(reportRespEvent);
        }
         
    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject)
    {
        // TODO Auto-generated method stub
        
    }
    
    private WebFocusReportCatalogResponseEvent buildResponseEvent(WebFocusReportCatalog report){
        
        WebFocusReportCatalogResponseEvent reportRespEvent = new WebFocusReportCatalogResponseEvent();
        
        reportRespEvent.setPasswordParm(report.getPasswordParm());
        reportRespEvent.setReportDesc(report.getReportDesc());
        reportRespEvent.setReportId(report.getReportId());
        reportRespEvent.setUrl(report.getUrl());
        reportRespEvent.setUserParm(report.getUserParm());
        reportRespEvent.setWebFocusName(report.getWebFocusName());
        
        return reportRespEvent;
        
        
    }

}
