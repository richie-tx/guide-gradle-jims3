//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionorder\\transactions\\GetImpactedOrdersCommand.java

package pd.supervision.supervisionorder.transactions;

import java.util.Iterator;

import messaging.supervisionorder.GetPrintTemplatesEvent;
import messaging.supervisionorder.reply.PrintTemplatesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.supervisionorder.SupervisionOrderPrintTemplate;

/**
 * @author dgibler
 *
 */
public class GetPrintTemplatesCommand implements ICommand 
{
   
   /**
    * @roseuid 43B2E77C03C8
    */
   public GetPrintTemplatesCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 43B2B6ED0244
    */
   public void execute(IEvent event) 
   {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetPrintTemplatesEvent reqEvent = (GetPrintTemplatesEvent)event;
		
		
		//Iterator iter = SupervisionOrderPrintTemplate.findAll();
		Iterator iter = SupervisionOrderPrintTemplate.findAll("agencyID",reqEvent.getAgencyId());
		
		if (iter != null )
		{
			while (iter.hasNext())
			{
			SupervisionOrderPrintTemplate soPrnTmpls = (SupervisionOrderPrintTemplate)iter.next();
			PrintTemplatesResponseEvent prnTmplRes = new PrintTemplatesResponseEvent();
			prnTmplRes.setTopic("OrderTitles");
			prnTmplRes.setAgencyID(soPrnTmpls.getAgencyID());
			prnTmplRes.setCourtCategory(soPrnTmpls.getCourtCategory());
			prnTmplRes.setCourtId(soPrnTmpls.getCourtId());
			
			
			if(soPrnTmpls.getOrderTitle() == null || soPrnTmpls.getOrderTitle().equals("") )
			continue;
			prnTmplRes.setOrderTitle(soPrnTmpls.getOrderTitle());
			prnTmplRes.setOrderType(soPrnTmpls.getOrderType());
			prnTmplRes.setPrintTemplateId(soPrnTmpls.getOID().toString());
			prnTmplRes.setTemplateLang(soPrnTmpls.getTemplateLang());
			prnTmplRes.setVersionDate(soPrnTmpls.getVersionDate());
			prnTmplRes.setVersionNumber(soPrnTmpls.getVersionNumber());
			prnTmplRes.setVersionType(soPrnTmpls.getVersionType());
			
			dispatch.postEvent(prnTmplRes);
			}
		}
		

   }
   
   /**
    * @param event
    * @roseuid 43B2B6ED0246
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 43B2B6ED0252
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param updateObject
    * @roseuid 43B2E77C03D8
    */
   public void update(Object updateObject) 
   {
    
   }
}
