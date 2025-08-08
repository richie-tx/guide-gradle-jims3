package ui.juvenilecase.prodsupport.action.query;

import java.util.Collection;

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

public class DeleteDrugTestingQueryAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
	ProdSupportForm regform = (ProdSupportForm) form;
	String forward = "listSuccess";
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
	  } else {
	      getDrugTestingInfoEvent.setJuvenileId( regform.getJuvenileId() );
	  }
	  CompositeResponse replyEvent = MessageUtil.postRequest(getDrugTestingInfoEvent);
	  Collection<DrugTestingResponseEvent> drugTestingInfos = MessageUtil.compositeToCollection(replyEvent, DrugTestingResponseEvent.class);
	  if ( drugTestingInfos != null
		  && drugTestingInfos.size() > 0 ) {
	      regform.getDrugTestingInfos().addAll( drugTestingInfos );
	  }
	
	return mapping.findForward(forward);
    }

}

