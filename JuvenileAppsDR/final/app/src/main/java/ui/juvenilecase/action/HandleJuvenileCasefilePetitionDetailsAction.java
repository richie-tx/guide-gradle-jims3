//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\referral\\action\\HandleJuvenileCasefilePetitionDetailsAction.java

package ui.juvenilecase.action;

import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.juvenilewarrant.GetJuvenileCasefilePropertyLossDetailsEvent;
import messaging.juvenilewarrant.GetJuvenileCasefileVictimWitnessDetailsEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingChargeResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingComplainantResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingPropertyResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.common.PhoneNumber;
import ui.juvenilecase.form.PetitionDetailsForm;

public class HandleJuvenileCasefilePetitionDetailsAction extends JIMSBaseAction {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.viewVictim", "viewVictim");
		keyMap.put("button.viewPropertyLoss", "viewPropertyLoss");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
	}

	/**
	 * @roseuid 467FB5D0014C
	 */
	public HandleJuvenileCasefilePetitionDetailsAction() {

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 467FAF3A0004
	 */
	public ActionForward viewVictim(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		PetitionDetailsForm form = (PetitionDetailsForm) aForm;
		GetJuvenileCasefileVictimWitnessDetailsEvent comp = (GetJuvenileCasefileVictimWitnessDetailsEvent) EventFactory
				.getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILECASEFILEVICTIMWITNESSDETAILS);
		comp.setDaLogNum(form.getSelectedDALogNum());
		comp.setSequenceNum(form.getSelectedSeqNum());
		IDispatch dispatch = EventManager
				.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(comp);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		JuvenileOffenderTrackingComplainantResponseEvent resp = (JuvenileOffenderTrackingComplainantResponseEvent) MessageUtil
				.filterComposite(response,
						JuvenileOffenderTrackingComplainantResponseEvent.class);
		if (resp != null)
			fillVictimDetails(resp, form.getVictimWitnessRec());
		ActionForward forward = aMapping.findForward("victimSuccess");
		return forward;
	}

	private void fillVictimDetails(
			JuvenileOffenderTrackingComplainantResponseEvent comp,
			PetitionDetailsForm.VictimOrWitness vW) {
		vW.setAge(comp.getAge());
		vW.setName(comp.getName());
		vW.setAssociationType(comp.getAssociationType());
		vW.setSequenceNum(comp.getSequenceNum());
		vW.setRelationshipToJuvenile(comp.getRelationshipToJuvenile());
		vW.setTransactionNum(comp.getTransactionNum());
		vW.setDaLogNum(comp.getDaLogNum());
		vW.setDateOfBirth(comp.getDateOfBirth());
		vW.setAge(comp.getAge());
		vW.setDlNumber(comp.getDlNumber());
		vW.setDlState(comp.getDlState());
		vW.setSsn(comp.getSsn());
		vW.setOtherIDNumbers(comp.getOtherIDNumbers());
		vW.setStreetNum(comp.getStreetNum());
		vW.setStreetName(comp.getStreetName());
		vW.setAptNum(comp.getAptNum());
		vW.setCity(comp.getCity());
		vW.setZip(comp.getZip());
		if (comp.getPhone() != null)
			vW.setPhone(new PhoneNumber(comp.getPhone().toString()));
		else
			vW.setPhone(new PhoneNumber(""));
		vW.setState(comp.getState());
		vW.setEmployer(comp.getEmployer());
		vW.setOccupation(comp.getOccupation());
		vW.setOtherInd(comp.getOtherInd());
		vW.setOtherStreetName(comp.getOtherStreetName());
		vW.setOtherStreetNumber(comp.getOtherStreetNumber());
		vW.setOtherAptNum(comp.getOtherAptNum());
		vW.setOtherCity(comp.getOtherCity());
		vW.setOtherState(comp.getOtherState());
		vW.setOtherZip(comp.getOtherZip());
		if (comp.getOtherPhone() != null)
			vW.setOtherPhone(new PhoneNumber(comp.getOtherPhone().toString()));
		else
			vW.setOtherPhone(new PhoneNumber(""));
	}

	public ActionForward viewPropertyLoss(ActionMapping aMapping,ActionForm aForm, HttpServletRequest aRequest,HttpServletResponse aResponse) {
		PetitionDetailsForm form = (PetitionDetailsForm) aForm;
		GetJuvenileCasefilePropertyLossDetailsEvent loss = (GetJuvenileCasefilePropertyLossDetailsEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILECASEFILEPROPERTYLOSSDETAILS);
		Collection<JuvenileOffenderTrackingChargeResponseEvent> charges = form.getJotCharges();
		if (charges != null || (form.getDaLogNum() != null && !form.getDaLogNum().equals(""))) {
			if (form.getDaLogNum() == null) {
				if (charges != null) {
					for (JuvenileOffenderTrackingChargeResponseEvent chargeResp : charges) {
						if (chargeResp.getDaLogNum() != null) {
							loss.setDaLogNum(chargeResp.getDaLogNum());
							break;
						}
					}
				}
			} else {
				loss.setDaLogNum(form.getDaLogNum());
			}
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(loss);
			CompositeResponse response = (CompositeResponse) dispatch.getReply();
			Collection coll = MessageUtil.compositeToCollection(response,JuvenileOffenderTrackingPropertyResponseEvent.class);
			form.setPropertyLosses(coll);
		}
		ActionForward forward = aMapping.findForward("propertyLossSuccess");
		return forward;
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}
}
