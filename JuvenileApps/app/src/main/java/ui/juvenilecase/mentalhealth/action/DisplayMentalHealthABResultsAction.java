package ui.juvenilecase.mentalhealth.action;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import messaging.mentalhealth.GetMentalHealthABResultsEvent;
import messaging.mentalhealth.reply.ABTestResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileMentalHealthControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.mentalhealth.form.TestingSessionForm;

public class DisplayMentalHealthABResultsAction extends JIMSBaseAction {

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 45D36FDB01D5
	 */
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		TestingSessionForm sessForm = (TestingSessionForm) aForm;
		HttpSession session = aRequest.getSession();
		JuvenileProfileForm headerForm = (JuvenileProfileForm) session.getAttribute("juvenileProfileHeader");
		sessForm.setJuvNum(headerForm.getJuvenileNum());
		GetMentalHealthABResultsEvent abEvent = (GetMentalHealthABResultsEvent) EventFactory
				.getInstance(JuvenileMentalHealthControllerServiceNames.GETMENTALHEALTHABRESULTS);
		abEvent.setJuvenileNum(headerForm.getJuvenileNum());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(abEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Collection coll = MessageUtil.compositeToCollection(response, ABTestResponseEvent.class);
		Collections.sort((List) coll);
		// set the test name on each record
		Iterator iter = coll.iterator();
		while (iter.hasNext()) {
			ABTestResponseEvent abResp = (ABTestResponseEvent) iter.next();
			String testNameId = abResp.getTestName();
			abResp.setTestName(SimpleCodeTableHelper.getDescrByCode(
					"AB_TEST_NAME", testNameId));
		}
		sessForm.setAbResultsList(coll);
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
	}

	protected void addButtonMapping(Map keyMap) {

	}
}