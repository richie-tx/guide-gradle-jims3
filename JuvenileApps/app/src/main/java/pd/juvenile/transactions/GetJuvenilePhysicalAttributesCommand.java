package pd.juvenile.transactions;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.FastArrayList;
import org.apache.commons.collections.FastHashMap;

import pd.codetable.person.ScarsMarksTattoosCode;
import pd.juvenile.JuvenilePhysicalAttributes;
import pd.juvenile.JuvenilePhysicalAttributesTattoosScarsMarksTattoosCode;

import messaging.codetable.GetCodesEvent;
import messaging.juvenile.GetJuvenilePhysicalAttributesEvent;
import messaging.juvenile.reply.JuvenilePhysicalAttributesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

public class GetJuvenilePhysicalAttributesCommand implements ICommand
{

    /**
     * @roseuid 42B18DC50232
     */
    public GetJuvenilePhysicalAttributesCommand()
    {

    }

    /**
     * @param event
     * @roseuid 42B1830603AA
     */
    public void execute(IEvent event)
    {
        // TODO Refactor to use proper helper abstraction

        GetJuvenilePhysicalAttributesEvent requestEvent = (GetJuvenilePhysicalAttributesEvent) event;
        Iterator i = JuvenilePhysicalAttributes.findAll(requestEvent);

        List responses = new FastArrayList();
        
        
        while (i.hasNext())
        {
            JuvenilePhysicalAttributes physicalAttribs = (JuvenilePhysicalAttributes) i.next();
            JuvenilePhysicalAttributesResponseEvent physicalAttribute = physicalAttribs
                    .getJuvenilePhysicalAttributesResponse();
            responses.add(physicalAttribute);            
        }

            
           int len= responses.size();

            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
            
            for(int m=0;m<len;m++)
            {                
                JuvenilePhysicalAttributesResponseEvent physicalAttribute = (JuvenilePhysicalAttributesResponseEvent) responses.get(m);
                dispatch.postEvent(physicalAttribute);
            }
//        }                

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
