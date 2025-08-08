/*
 * Created on Jan 20, 2005
 *
 */
package pd.codetable.transactions;

import java.util.Collection;
import java.util.Iterator;

import naming.PDCodeTableConstants;
import pd.codetable.Code;
import messaging.codetable.GetCodesEvent;
import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.ReadOnlyTransactional;

/**
 * @author glyons Retrieves and posts Code response events.
 */
public class GetCodesCommand implements ICommand, ReadOnlyTransactional
{
    /**
     * @param event
     * @precondition ((GetCodeTableEvent)event).getCodeTableId() != null)
     */
    public void execute(IEvent event) throws Exception
    {
        GetCodesEvent codeTableEvent = (GetCodesEvent) event;

        if (codeTableEvent.getCodes() == null)
        {
            this.sendAllCodes(codeTableEvent);
        }
        else
        {
            this.sendSpecificCodes(codeTableEvent);
        }
    }

    /**
     * @param codes
     */
    private void sendSpecificCodes(GetCodesEvent codeTableEvent)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        String[] codes = codeTableEvent.getCodes();
        String codeTableName = codeTableEvent.getCodeTableName();
        for (int i = 0; i < codes.length; i++)
        {
            Code code = Code.find(codeTableName, codes[i]);
            CodeResponseEvent codeResponse = code.valueObject(codeTableEvent.isThin());
            codeResponse.setTopic(PDCodeTableConstants.getCodeTableTopic(codeTableName));
            dispatch.postEvent(codeResponse);
        }
    }

    private void sendAllCodes(GetCodesEvent codeTableEvent)
    {
        String codeTableName = codeTableEvent.getCodeTableName();
        
        if (codeTableName != null)
        {
            Collection codes = Code.findAll(codeTableName);

            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

            Iterator i = codes.iterator();

            while (i.hasNext())
            {
                Code code = (Code) i.next();
                CodeResponseEvent codeReply = code.valueObject(codeTableEvent.isThin());
                codeReply.setTopic(PDCodeTableConstants.getCodeTableTopic(codeTableName));                

                dispatch.postEvent(codeReply);
            }
        }
    }

    /**
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event)
    {
    }

    /**
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event)
    {
    }

    /**
     * @see mojo.km.context.ICommand#update(Object)
     */
    public void update(Object updateObject)
    {
    }
}
