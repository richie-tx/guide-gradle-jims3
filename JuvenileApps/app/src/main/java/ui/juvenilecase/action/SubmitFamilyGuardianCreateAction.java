/*
 * Created on Oct 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.family.SaveFamilyConstellationEvent;
import messaging.family.SaveFamilyConstellationMemberInfoEvent;
import messaging.family.SaveFamilyMemberFinancialEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileFamilyHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileFamilyForm;

/**
 * @author jjose
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SubmitFamilyGuardianCreateAction extends LookupDispatchAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.next", "next");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.finish", "finish");
		keyMap.put("button.returnToConstellation", "toConstellationList");
		return keyMap;
	}

	public ActionForward toConstellationList(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward("toConstellationList");
		return forward;
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}

	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		JuvenileFamilyForm myFamForm = (JuvenileFamilyForm) aForm;
		JuvenileFamilyForm.Constellation myConstellation = myFamForm.getCurrentConstellation();
		JuvenileFamilyForm.Guardian myGuardian = myFamForm.getCurrentGuardian();
		Collection guardians = myConstellation.getGuardiansList();
		Iterator iter;
		iter = guardians.iterator();
		int numOfGuardians = guardians.size();
		JuvenileFamilyForm.Guardian myListGuardian;
		// Reset all existing family member information that shoudl not exist
		boolean goToNextGuardian = false;
		while (iter.hasNext()) {
			myListGuardian = (JuvenileFamilyForm.Guardian) iter.next();
			if (myListGuardian.getMemberNumber().equalsIgnoreCase(myGuardian.getMemberNumber())) {
				if (iter.hasNext()) {
					myFamForm.setCurrentGuardian((JuvenileFamilyForm.Guardian) iter.next());
					goToNextGuardian = true;
					break;
				}
			}
		}
		ActionForward forward = null;
		if (goToNextGuardian)
			forward = aMapping.findForward("nextGuardian");
		else
			forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
	}

	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		JuvenileFamilyForm myFamForm = (JuvenileFamilyForm) aForm;
		JuvenileFamilyForm.Constellation myConstellation = myFamForm.getCurrentConstellation();
		JuvenileFamilyForm.Guardian myGuardian = myFamForm.getCurrentGuardian();
		boolean saveGuardian = true;
		if (myFamForm.getAction().equalsIgnoreCase(UIConstants.UPDATE)) {
			Collection guardians = myConstellation.getGuardiansList();
			if (guardians != null && guardians.size() > 0) {
				JuvenileFamilyForm.Guardian tempGuardian = (JuvenileFamilyForm.Guardian) (((ArrayList) guardians)
						.get(0));
				boolean isSame = UIJuvenileFamilyHelper.isGuardianTheSame(myGuardian, tempGuardian);
				saveGuardian = !isSame;
			}

		}
		if (saveGuardian) {
			if (myConstellation == null)
				return aMapping.findForward(UIConstants.FAILURE);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			// Set Juvenile Number in main event
			SaveFamilyConstellationEvent event = new SaveFamilyConstellationEvent();
			event.setJuvNum(myFamForm.getJuvenileNumber());
			event.setConstellationNum(myFamForm.getCurrentConstellation().getFamilyNumber());
			// Set Members
			Collection members = myConstellation.getMemberList();
			JuvenileFamilyForm.MemberList myConstMember;
			if (members != null) {
				Iterator iter = members.iterator();
				while (iter.hasNext()) {
					myConstMember = (JuvenileFamilyForm.MemberList) iter.next();
					if (myConstMember.getMemberNumber().equals(myGuardian.getMemberNumber())) {
						SaveFamilyConstellationMemberInfoEvent myFamMemEvent = UIJuvenileHelper
								.getSaveFamilyConstellationMemberInfoEvent(myConstMember);
						event.addRequest(myFamMemEvent);
						SaveFamilyMemberFinancialEvent myGuardianEvent = UIJuvenileHelper.getSaveFamilyFinancialEvent(
								myGuardian, myFamForm.getCurrentConstellation().getFamilyNumber(), myConstMember
										.getMemberNumber());
						myFamMemEvent.addRequest(myGuardianEvent);
					}
				}
			}
			dispatch.postEvent(event);
			// Getting PD Response Event
			CompositeResponse response = (CompositeResponse) dispatch.getReply();
			// Perform Error handling
			MessageUtil.processReturnException(response);
		}

		myFamForm.setSecondaryAction(UIConstants.CONFIRM);
		ActionForward forward = aMapping.findForward("updateGuardianSuccess");
		return forward;
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UIConstants.BACK);
		return forward;
	}

	/**
	 * @param aRequest
	 */
	private void sendToErrorPage(HttpServletRequest aRequest, String msg) {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}
}// END CLASS
