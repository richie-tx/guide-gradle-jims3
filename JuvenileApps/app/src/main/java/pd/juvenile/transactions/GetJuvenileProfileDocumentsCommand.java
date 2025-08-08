package pd.juvenile.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.juvenile.GetJuvenileProfileDocumentsEvent;
import messaging.juvenile.reply.JuvenileProfileDocumentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

import org.apache.commons.collections.FastArrayList;

import pd.juvenile.JuvenileDocuments;

public class GetJuvenileProfileDocumentsCommand implements ICommand
{

    /**
     * @roseuid 42B18DC50232
     */
    public GetJuvenileProfileDocumentsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 42B1830603AA
     */
    public void execute(IEvent event)
    {
        GetJuvenileProfileDocumentsEvent requestEvent = (GetJuvenileProfileDocumentsEvent) event;
        Iterator i = JuvenileDocuments.findAll("juvenileId", requestEvent.getJuvenileNum());

        List responses = new FastArrayList();
        
        JuvenileProfileDocumentResponseEvent juvenileDocument = new JuvenileProfileDocumentResponseEvent();
        while (i.hasNext())
        {
            JuvenileDocuments juvenileDocuments = ( JuvenileDocuments) i.next();
            juvenileDocument = new JuvenileProfileDocumentResponseEvent();
            juvenileDocument.setDocumentId(juvenileDocuments.getOID());
            juvenileDocument.setDocumentTypeId(juvenileDocuments.getDocumentTypeCodeId());
            juvenileDocument.setEntryDate(juvenileDocuments.getEntryDate());
            juvenileDocument.setJuvenileNum(juvenileDocuments.getJuvenileId());
            juvenileDocument.setDocument(juvenileDocuments.getDocument());
            responses.add(juvenileDocument);            
        }
         
        int len= responses.size();

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
            
        for(int m=0; m<len; m++)
        {                
        	JuvenileProfileDocumentResponseEvent juvenileDoc = (JuvenileProfileDocumentResponseEvent) responses.get(m);
        	dispatch.postEvent(juvenileDoc);
        }

    }

    /**
     * @param event
     * @roseuid 42B1830603AC
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 42B1830603AE
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 42B1830603B0
     */
    public void update(Object anObject)
    {

    }

}
