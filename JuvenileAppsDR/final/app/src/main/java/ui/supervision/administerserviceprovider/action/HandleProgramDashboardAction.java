// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerserviceprovider\\action\\SubmitJuvCreateServiceProviderAction.java

package ui.supervision.administerserviceprovider.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.administercontract.GetServiceContractsEvent;
import messaging.administerserviceprovider.administercontract.reply.ContractResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.supervision.administerserviceprovider.form.ServiceProviderForm;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;

public class HandleProgramDashboardAction extends LookupDispatchAction {

	/**
	 * @roseuid 447351D50126
	 */
	public HandleProgramDashboardAction() {

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 446A2E440317
	 */
	public ActionForward returnSP(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ServiceProviderForm sp = (ServiceProviderForm) aForm;
		sp.clearProgram();
		Collection programs = UIServiceProviderHelper.getPrograms(sp, sp.getProviderId());
		sp.setPrograms(UIServiceProviderHelper.sortResults(programs, "P"));
		if (sp.getActionType().equalsIgnoreCase("updateProgram"))
			sp.setConfirmMessage("Program successfully updated.");
		else if (sp.getActionType().equalsIgnoreCase("createProgram"))
			sp.setConfirmMessage("Program successfully created.");
		else
			sp.setConfirmMessage("");
		return aMapping.findForward("returnToSPSuccess");
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.CANCEL);
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward add(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ServiceProviderForm providerForm = (ServiceProviderForm) aForm;
		providerForm.getCurrentProgram().setProgramService(new ServiceProviderForm.Program.Service());
		providerForm.setServiceEvents(new ArrayList());
		//ServiceProviderForm.Program.Service svc =
		// providerForm.getCurrentProgram().getProgramService();
		//svc = new ServiceProviderForm.Program.Service();

		//providerForm.getCurrentProgram().clearServices();

		Collection locationResponses = UIServiceProviderHelper.setServiceLocations(providerForm);
		providerForm.setContracts(null);
		//Collections.sort((ArrayList)locationResponses);
		providerForm.setServiceLocationNames(locationResponses);
		return aMapping.findForward("addUpdateSuccess");
	}

	public ActionForward update(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ServiceProviderForm providerForm = (ServiceProviderForm) aForm;
		if (providerForm.getServiceLocationNames().size() == 0)
			providerForm.setServiceLocationNames(UIServiceProviderHelper.setServiceLocations(providerForm));
		providerForm.getCurrentProgram().getProgramService().setServiceId(providerForm.getSelectedServiceValue());
		ServiceResponseEvent serv = UIServiceProviderHelper.getServiceById(providerForm.getSelectedServiceValue());
		if (serv != null) {
			UIServiceProviderHelper.fillServiceDetails(providerForm, serv);
		}
		return aMapping.findForward("addUpdateSuccess");
	}

	public ActionForward view(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ServiceProviderForm providerForm = (ServiceProviderForm) aForm;
		String selectedVal = providerForm.getSelectedValue();
		ServiceResponseEvent serv = UIServiceProviderHelper.getServiceById(selectedVal);
		if (serv != null) {
			UIServiceProviderHelper.fillServiceDetails(providerForm, serv);
		}
		/* TO Display Contract Funding Information along service details. */
		if (serv != null && serv.getServiceId() != null && !serv.getServiceId().equals("")) {
			GetServiceContractsEvent sEvent = new GetServiceContractsEvent();
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			sEvent.setServiceId(serv.getServiceId());
			dispatch.postEvent(sEvent);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);

			Collection currentContracts = MessageUtil.compositeToCollection(compositeResponse,
					ContractResponseEvent.class);
			if (currentContracts != null && !currentContracts.isEmpty()) {
				providerForm.setContracts(currentContracts);
			}
			else
			{
				providerForm.setContracts(null);
			}
		}
		/* End */
		providerForm.setConfirmMessage("");
		return aMapping.findForward("viewServiceSuccess");

	}

	public ActionForward delete(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ServiceProviderForm providerForm = (ServiceProviderForm) aForm;
		Collection coll = providerForm.getCurrentProgram().getServices();
		return aMapping.findForward(UIConstants.DELETE_SUCCESS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.returnToServiceProvider", "returnSP");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.add", "add");
		keyMap.put("button.update", "update");
		keyMap.put("button.delete", "delete");
		keyMap.put("button.view", "view");
		return keyMap;
	}
}
