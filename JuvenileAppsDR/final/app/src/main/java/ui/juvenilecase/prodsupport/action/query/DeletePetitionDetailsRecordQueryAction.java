package ui.juvenilecase.prodsupport.action.query;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.productionsupport.DeleteProductionSupportPetitionEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.form.ProdSupportForm;

public class DeletePetitionDetailsRecordQueryAction extends LookupDispatchAction
{
    private Logger log = Logger.getLogger("UpdatePetitionDetailsQueryAction");

    protected Map<String, String> getKeyMethodMap()
    {
	Map<String, String> keyMap = new HashMap<String, String>();
	keyMap.put("button.submit", "deletePetitionDetail");
	keyMap.put("button.deleteRecord", "deletePetition");


	return keyMap;
    }

    /**
     * 
     */
    public ActionForward deletePetition(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	//record deletion
	ProdSupportForm regform = (ProdSupportForm) form;
	String petitionOID = request.getParameter("petOID");
	String returnVal = "deletesuccess";
	//
	if(petitionOID!=null&&!petitionOID.isEmpty())
	{
        	/*JJSPetition petition = JJSPetition.findById(petitionOID);
        	if (petition != null){
        	    petition.delete();
        	    new Home().bind(petition);
        	}*/
	    	CompositeResponse composite = null;
	    	DeleteProductionSupportPetitionEvent deletepetEvent = (DeleteProductionSupportPetitionEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTPETITION);
	    	deletepetEvent.setPetitionId(petitionOID);

		composite = MessageUtil.postRequest(deletepetEvent);
		ErrorResponseEvent errorEvt = (ErrorResponseEvent) MessageUtil.filterComposite(composite,
		        ErrorResponseEvent.class);
		if(errorEvt!=null){
			if(errorEvt.getMessage()!=null) {
			    	ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("Error on deleting the petition"));
				saveErrors(request, errors); 
				return mapping.findForward("error");
			} 
		}
	}
	else
	    returnVal = "error";
	//

	return mapping.findForward(returnVal);

    }
    /**
     * @param form
     * @throws Exception
     */
    public ActionForward deletePetitionDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	ProdSupportForm psform = (ProdSupportForm) form;
	psform.setMsg("");
	String returnVal = "success";
	boolean anyError = false;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	String petID = request.getParameter("altOID");
	PetitionResponseEvent curretpet=null;
	//JJSPetition petition = JJSPetition.findById(petID);	
	//PetitionResponseEvent originalPetition = (PetitionResponseEvent) petition;
	Iterator iter = psform.getPetitionDetails().iterator();
	while(iter.hasNext())
        {
        	PetitionResponseEvent petitionResponseEvent = (PetitionResponseEvent)iter.next();
    		if(petitionResponseEvent!=null && petitionResponseEvent.getPetitionNum()!=null && petitionResponseEvent.getOID().equalsIgnoreCase(petID))
    		{
    		    curretpet = petitionResponseEvent;
    		    psform.setPetitionDetail(curretpet);
    		    break;
    		}
        }
	if (curretpet==null)
	    returnVal="error";
	return mapping.findForward(returnVal);

    }

    public boolean isDefined(String value)
    {
	return (value != null && value.length() > 0);
    }

    

}
