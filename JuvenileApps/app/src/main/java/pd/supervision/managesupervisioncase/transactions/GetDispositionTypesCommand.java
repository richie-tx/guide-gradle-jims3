//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\managesupervisioncase\\transactions\\GetDispositionTypesCommand.java

package pd.supervision.managesupervisioncase.transactions;

import java.util.Iterator;

import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDCodeTableConstants;
import pd.codetable.Code;
import pd.codetable.PDCodeHelper;

public class GetDispositionTypesCommand implements ICommand 
{
   
   /**
    */
   public GetDispositionTypesCommand() 
   {
    
   }
   
   /**
    * @param event
    */
   public void execute(IEvent event) 
   {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

	Iterator iter = PDCodeHelper.getInstance().getCodes(PDCodeTableConstants.DISP_TYPE).iterator();
	while (iter.hasNext())
	{
		Code code = (Code)iter.next();
		// TODO This if condition will go away when/if m204 program is modified to filter for Out of County Supervision Case.
		if (PDCodeTableConstants.DEFERRED_ADJUDICATION.equals(code.getCode()) ||
			PDCodeTableConstants.STRAIGHT_PROBATION.equals(code.getCode()) ||
			PDCodeTableConstants.PRETRIAL_INTERVENTION.equals(code.getCode()))
		{
			CodeResponseEvent codeReply = new CodeResponseEvent();
			codeReply.setCodeId(code.getCodeId());
			codeReply.setActiveDate(code.getActiveDate());
			codeReply.setCode(code.getCode());
			codeReply.setCodeTableName(code.getCodeTableName());
			codeReply.setDescription(code.getDescription());
			codeReply.setInactiveDate(code.getInactiveDate());
			codeReply.setInactiveEffectiveDate(code.getInactiveEffectiveDate());
			codeReply.setStatus(code.getStatus());
			dispatch.postEvent(codeReply);
		}
	}
   }
   
   /**
    * @param event
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param updateObject
    */
   public void update(Object updateObject) 
   {
    
   }
   
}
