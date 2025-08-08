package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.mentalhealth.reply.MAYSIDetailsResponseEvent;
import messaging.productionsupport.DeleteProductionSupportFacilityDetentionEvent;
import messaging.productionsupport.DeleteProductionSupportMaysiDetailEvent;
import messaging.productionsupport.GetJJSCLAncillaryCourtByPetitionNumAndHearingDateEvent;
import messaging.productionsupport.GetProductionSupportMaysiDetailEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 * 
 * 	Perform a delete for a Facility Detention record
 */

public class PerformDeleteFacilityDetentionAction extends Action {

	private Logger log = Logger.getLogger("PerformDeleteFacilityDetentionAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		String detentionId   = regform.getDetentionId();
		
		if ( detentionId != null
			&& detentionId.length() > 0) {
		    DeleteProductionSupportFacilityDetentionEvent deleteFacilityDetentionEvent = (DeleteProductionSupportFacilityDetentionEvent)
			    										EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTFACILITYDETENTION);
		    deleteFacilityDetentionEvent.setDetentionId(detentionId);
		    deleteFacilityDetentionEvent.setLastSequenceNumber(regform.getLastSeqNum());
		    deleteFacilityDetentionEvent.setNewLastSequenceNumber(regform.getNewLastSeqNum());
		    dispatch.postEvent(deleteFacilityDetentionEvent);
		}
		
		
		
		return mapping.findForward("success");

		
	}
	
	
	
}
