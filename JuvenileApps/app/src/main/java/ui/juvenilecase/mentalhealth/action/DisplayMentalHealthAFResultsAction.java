//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\mentalhealth\\action\\DisplayMentalHealthAFResultsAction.java

package ui.juvenilecase.mentalhealth.action;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import messaging.mentalhealth.GetMentalHealthAFResultsEvent;
import messaging.mentalhealth.reply.AFTestResponseEvent;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DisplayMentalHealthAFResultsAction extends JIMSBaseAction {

	/**
	 * @roseuid 45D4AEA4018B
	 */
	public DisplayMentalHealthAFResultsAction() {

	}

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
		GetMentalHealthAFResultsEvent afEvent = (GetMentalHealthAFResultsEvent) EventFactory
				.getInstance(JuvenileMentalHealthControllerServiceNames.GETMENTALHEALTHAFRESULTS);
		afEvent.setJuvenileNum(headerForm.getJuvenileNum());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(afEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Collection coll = MessageUtil.compositeToCollection(response,AFTestResponseEvent.class);
		Collections.sort((List) coll);
		// set the test name on each record
		Iterator iter = coll.iterator();
		while (iter.hasNext()) {
			AFTestResponseEvent aftResp = (AFTestResponseEvent) iter.next();
			String testNameId = aftResp.getTestName();
			aftResp.setTestName(SimpleCodeTableHelper.getDescrByCode("AF_TEST_NAME", testNameId));
		}
		sessForm.setAfResultsList(coll);
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
	}

	protected void addButtonMapping(Map keyMap) {
	}
}
