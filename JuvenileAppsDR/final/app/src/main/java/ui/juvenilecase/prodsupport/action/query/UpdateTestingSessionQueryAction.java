package ui.juvenilecase.prodsupport.action.query;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.productionsupport.GetProductionSupportTestingSessionInfoEvent;
import messaging.productionsupport.reply.ProductionSupportTestingSessionInfoResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;

public class UpdateTestingSessionQueryAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
				HttpServletRequest request, 
					HttpServletResponse response) throws Exception {

	ProdSupportForm regform = (ProdSupportForm) form;
	String forward = "success";
	 /** Check for initial load of this page **/
	String clrChk = request.getParameter("clr");
	if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
	    regform.clearAllResults();
	    regform.setMsg("");
	    return mapping.findForward("error");
	}
	
	GetProductionSupportTestingSessionInfoEvent getEvent = (GetProductionSupportTestingSessionInfoEvent) 
									EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTTESTINGSESSIONINFO);
	getEvent.setJuvenileId(regform.getJuvenileId());
	getEvent.setTestingSessionId(regform.getTestingSessionId());
	
	CompositeResponse testingSessionRsp = MessageUtil.postRequest(getEvent);
	List<ProductionSupportTestingSessionInfoResponseEvent>testingSessionInfos = MessageUtil.compositeToList(testingSessionRsp, ProductionSupportTestingSessionInfoResponseEvent.class);
	if ( testingSessionInfos.size() > 0  ){
	    regform.setTestingSessionInfos(testingSessionInfos);
	    regform.setJuvenileName( testingSessionInfos.get(0).getFullName() );
	    regform.setJuvenileId( testingSessionInfos.get(0).getJuvenileId() );
	} 
	
	return mapping.findForward(forward);
	
    }

}
