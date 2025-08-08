package pd.codetable.transactions;

import java.util.Iterator;

import naming.PDCodeTableConstants;

import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;

import messaging.codetable.GetSupervisionCodesEvent;
import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetSupervisionCodesCommand implements ICommand
{

    /**
     * @roseuid 43039DCD01A5
     */
    public GetSupervisionCodesCommand()
    {

    }

    /**
     * @param event
     * @roseuid 43039A40005E
     */
    public void execute(IEvent event)
    {
        GetSupervisionCodesEvent codeTableEvent = (GetSupervisionCodesEvent) event;

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        if (codeTableEvent.getCode() != null)
        {
            SupervisionCode code = PDSupervisionCodeHelper.getSupervisionCodeByValue(codeTableEvent.getAgencyId(),
                    codeTableEvent.getCodeTableName(), codeTableEvent.getCode());

            CodeResponseEvent codeReply = new CodeResponseEvent();

            codeReply.setTopic(PDCodeTableConstants.getCodeTableTopic(codeTableEvent.getCodeTableName()));

            // NOTE "code" is set to SupervisionCode.OID since it was initially coded this way
            // and UI development has already proceeded to depend on this "code" property.

            String codeOID = (String) code.getOID();
            
            codeReply.setCodeId(codeOID);
            codeReply.setCode(codeOID);
            //Had to create another to hold code.  Too many jsps are expecting the code to be the codeId.
            codeReply.setSupervisionCode(code.getCode());
            
            codeReply.setCodeTableName(code.getCodeTableName());
            codeReply.setDescription(code.getDescription());
            
            dispatch.postEvent(codeReply);
        }
        else if (codeTableEvent.getCodeTableName() != null)
        {
            Iterator codes = SupervisionCode.findAll(codeTableEvent);

            while (codes.hasNext())
            {
                CodeResponseEvent codeReply = new CodeResponseEvent();
                codeReply.setTopic(PDCodeTableConstants.getCodeTableTopic(codeTableEvent.getCodeTableName()));

                SupervisionCode code = (SupervisionCode) codes.next();

                // NOTE "code" is set to SupervisionCode.OID since it was initially coded this way
                // and UI development has already proceeded to depend on this "code" property.

                String codeOID = (String) code.getOID();
                
                codeReply.setCodeId(codeOID);
                codeReply.setCode(codeOID);
                codeReply.setSupervisionCode(code.getCode());
                
                codeReply.setCodeTableName(code.getCodeTableName());
                codeReply.setDescription(code.getDescription());
                dispatch.postEvent(codeReply);
            }
        }

    }

    /**
     * @param event
     * @roseuid 43039A400060
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 43039A400062
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 43039A40006E
     */
    public void update(Object anObject)
    {

    }

}
