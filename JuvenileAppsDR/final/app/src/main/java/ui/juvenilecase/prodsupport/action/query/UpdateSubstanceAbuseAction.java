package ui.juvenilecase.prodsupport.action.query;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.productionsupport.GetProductionSupportSubstanceAbuseInfoEvent;
import messaging.productionsupport.reply.ProductionSupportSubstanceAbuseInfoResponseEvent;
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

public class UpdateSubstanceAbuseAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {

	ProdSupportForm regform = (ProdSupportForm) form;
	/** Check for initial load of this page **/
	String clrChk = request.getParameter("clr");
	if (clrChk!=null && clrChk.equalsIgnoreCase("Y"))
	{
		regform.clearAllResults();
		regform.setMsg("");
		return mapping.findForward("error");		
	}
	
	GetProductionSupportSubstanceAbuseInfoEvent getSubstanceAbuseEvent = (GetProductionSupportSubstanceAbuseInfoEvent)
										EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTSUBSTANCEABUSEINFO);
	
	getSubstanceAbuseEvent.setJuvenileId( regform.getJuvenileId() );
	getSubstanceAbuseEvent.setSubstanceAbuseId( regform.getSubstanceAbuseId() );
	CompositeResponse substanceAbuseRsp = MessageUtil.postRequest(getSubstanceAbuseEvent);
	
	List<ProductionSupportSubstanceAbuseInfoResponseEvent>substanceAbuseInfos = MessageUtil.compositeToList(substanceAbuseRsp, ProductionSupportSubstanceAbuseInfoResponseEvent.class);
	if ( substanceAbuseInfos.size() > 0 ) {
	    
	    Collections.sort((List<ProductionSupportSubstanceAbuseInfoResponseEvent>)substanceAbuseInfos,Collections.reverseOrder(new Comparator<ProductionSupportSubstanceAbuseInfoResponseEvent>() {
		@Override
		public int compare(ProductionSupportSubstanceAbuseInfoResponseEvent sbInfo1, ProductionSupportSubstanceAbuseInfoResponseEvent sbInfo2) {
		    return  Integer.parseInt( sbInfo1.getSubstanceAbuseId()) - Integer.parseInt( sbInfo2.getSubstanceAbuseId());
		}
	    }));
	    
	    regform.setSubstanceAbuseInfos(substanceAbuseInfos);
	    regform.setJuvenileId(substanceAbuseInfos.get(0).getJuvenileId());
	    regform.setJuvenileName(substanceAbuseInfos.get(0).getFullName());
	}
	regform.setMsg("");
	return mapping.findForward("success");
    }

}
