package ui.juvenilecase.prodsupport.action.query;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.GetDrugTestingInfoEvent;
import messaging.juvenile.reply.DrugTestingResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;

public class UpdateDrugTestingQueryAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
	    				HttpServletRequest request, 
	    				HttpServletResponse response) throws Exception 
    {
	ProdSupportForm regform = (ProdSupportForm) form;
	String forward = "success";
	 /** Check for initial load of this page **/
	String clrChk = request.getParameter("clr");
	if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
	    regform.clearAllResults();
	    regform.setMsg("");
	    return mapping.findForward("error");
	}
	
	GetDrugTestingInfoEvent getDrugTestingInfoEvent = (GetDrugTestingInfoEvent)
		EventFactory.getInstance(JuvenileControllerServiceNames.GETDRUGTESTINGINFO);
	if ( regform.getDrugTestingId() != null
        	  && regform.getDrugTestingId().length() > 0 ){
	    	getDrugTestingInfoEvent.setDrugTestingId( regform.getDrugTestingId() );
	} 
		CompositeResponse replyEvent = MessageUtil.postRequest(getDrugTestingInfoEvent);		
		List<DrugTestingResponseEvent> drugTestingInfos = MessageUtil.compositeToList(replyEvent, DrugTestingResponseEvent.class);
		if ( drugTestingInfos != null
			&& drugTestingInfos.size() > 0 ) {
                  regform.setOriginalDrugTestingInfo(drugTestingInfos.get(0));
                  regform.setNewcasefileId( drugTestingInfos.get(0).getAssociateCasefile() );
                  regform.setTestDate( drugTestingInfos.get(0).getFormarttedTestDate());
                  regform.setTestTime( drugTestingInfos.get(0).getFormattedTestTime() );
		}
	
	return mapping.findForward(forward);
	
    }
}
