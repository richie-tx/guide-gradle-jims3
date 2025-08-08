/*
 * Created on Jan 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.common;

import messaging.codetable.GetOffenseCodeEvent;
import messaging.codetable.GetOffenseCodesEvent;
import messaging.codetable.criminal.reply.OffenseCodeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;

import ui.common.form.OffenseSearchForm;

/**
 * @author jjose
 * 
 */
public class OffenseHelper {

	public static CompositeResponse retrieveOffenseCode(
			GetOffenseCodeEvent requestEvent) {
		IDispatch dispatch = EventManager
				.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch
				.getReply();
		MessageUtil.processReturnException(compositeResponse);
		return compositeResponse;
	}
	
	public static CompositeResponse retrieveOffenseCodes(GetOffenseCodesEvent requestEvent) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch
				.getReply();
		MessageUtil.processReturnException(compositeResponse);
		return compositeResponse;
	}

	public static CompositeResponse retrieveOffenseCodes(OffenseSearchForm myForm) {
		GetOffenseCodesEvent request = (GetOffenseCodesEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.GETOFFENSECODES);

		// set the criteria from the form
		request.setOffenseCode(myForm.getOffenseCode());
		request.setOffenseDegree(myForm.getDegreeId());
		request.setOffenseLevel(myForm.getLevelId());
		request.setOffenseLiteral(myForm.getOffenseLiteral());
		request.setStateOffenseCode(myForm.getStateOffenseCode());
		request.setPenalCode(myForm.getPenalCode());

		CompositeResponse compositeResponse = OffenseHelper.retrieveOffenseCodes(request);
		return compositeResponse;

	}

	public static OffenseCodeResponseEvent retrieveOffenseCode(String offenseId) {
		GetOffenseCodeEvent request = (GetOffenseCodeEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.GETOFFENSECODE);

		request.setOffenseCode(offenseId);

		CompositeResponse compositeResponse = OffenseHelper
				.retrieveOffenseCode(request);
		OffenseCodeResponseEvent ocre = (OffenseCodeResponseEvent) MessageUtil
				.filterComposite(compositeResponse,
						OffenseCodeResponseEvent.class);
		return ocre;

	}

	public String getOffense(String offenseId) {

		offenseId = UIUtil.stripZeroes(offenseId);
		String myOffense = CodeHelper.getCodeDescriptionByCode(CodeHelper
				.getOffenseCodes(true), offenseId);
		return myOffense;
	}

}// END CLASS
