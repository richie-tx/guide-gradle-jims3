/*
 * Created on Dec 13, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.codetable.transactions;

import messaging.codetable.GetRiskAnalysisControlCodesEvent;

import java.util.Iterator;

import pd.codetable.riskanalysis.RiskAnalysisControlCode;
import messaging.codetable.riskanalysis.reply.RiskAnalysisControlCodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;


/**
 *
 */
public class GetRiskAnalysisControlCodesCommand implements ICommand
{

    /**
     *  
     */
    public GetRiskAnalysisControlCodesCommand()
    {
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event)
    {
        IHome home = new Home();
        Iterator iter = home.findAll(RiskAnalysisControlCode.class);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        while (iter.hasNext())
        {
            RiskAnalysisControlCode riskAnalysisControlCode = (RiskAnalysisControlCode) iter.next();
            RiskAnalysisControlCodeResponseEvent resp = new RiskAnalysisControlCodeResponseEvent();
            resp.setRiskAnalysisControlCodeId(riskAnalysisControlCode.getOID());
            resp.setCode(riskAnalysisControlCode.getCode());
            resp.setAnswerSource(riskAnalysisControlCode.getAnswerSource());
            resp.setDescription(riskAnalysisControlCode.getDescription());
            resp.setName(riskAnalysisControlCode.getName());
            dispatch.postEvent(resp);
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
