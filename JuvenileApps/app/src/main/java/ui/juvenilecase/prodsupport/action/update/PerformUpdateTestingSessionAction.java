package ui.juvenilecase.prodsupport.action.update;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.productionsupport.UpdateProductionSupportTestingSessionInfoEvent;
import messaging.productionsupport.reply.ProductionSupportTestingSessionInfoResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.form.ProdSupportForm;

public class PerformUpdateTestingSessionAction extends JIMSBaseAction{
    
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.next","next");
	keyMap.put("button.updateRecord","update");
	keyMap.put("button.cancel","cancel");
	keyMap.put("button.back","back");
	
    }
    
    public ActionForward next(ActionMapping aMapping, ActionForm aForm,
  		HttpServletRequest aRequest, HttpServletResponse aResponse)
      {
	ProdSupportForm form = (ProdSupportForm)aForm;
	if ( form.getTestingSessionInfos() != null 
		&& form.getTestingSessionInfos().size() > 0  ) {
	    for ( ProductionSupportTestingSessionInfoResponseEvent testingSessionInfo : form.getTestingSessionInfos() ){
		if (form.getTestingSessionId().equals( testingSessionInfo.getTestingSessionId()  ) ){
		    form.setTestDate( DateUtil.dateToString(testingSessionInfo.getTestSessionDate(), DateUtil.DATE_FMT_1 ));
		    form.setOriginalTestDate( DateUtil.dateToString(testingSessionInfo.getTestSessionDate(), DateUtil.DATE_FMT_1 ) );
		}
	    }
	}
  	return aMapping.findForward("continue");
      }
    
    public ActionForward update (ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm)aForm;
	
	try {
    		UpdateProductionSupportTestingSessionInfoEvent updateEvent = (UpdateProductionSupportTestingSessionInfoEvent) 
    									EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTTESTINGSESSIONINFO);
    		updateEvent.setTestingSessionId( form.getTestingSessionId() );
    		updateEvent.setTestingSessionDate( form.getTestDate() );
    		MessageUtil.postRequest(updateEvent);
	} catch (Exception e){
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", e.toString() ));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward("error");
	}
	
	return aMapping.findForward("success");
    }
    
    public ActionForward back(ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward("back");
    }

}
