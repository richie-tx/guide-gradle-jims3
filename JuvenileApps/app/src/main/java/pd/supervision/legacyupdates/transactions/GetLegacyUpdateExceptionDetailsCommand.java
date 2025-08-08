package pd.supervision.legacyupdates.transactions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import messaging.legacyupdates.GetLegacyUpdateExceptionDetailsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import pd.supervision.legacyupdates.LegacyUpdateLog;

public class GetLegacyUpdateExceptionDetailsCommand implements ICommand {
	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) {
		GetLegacyUpdateExceptionDetailsEvent pEvent = (GetLegacyUpdateExceptionDetailsEvent) event;
		List legacyAssignments = LegacyUpdateLog.findAll(pEvent);
		SendEmailEvent email = new SendEmailEvent();
		StringBuffer exception = new StringBuffer("");
		if (legacyAssignments.size() <= 0) {
			exception.append("No exception to report");
		} else {
			exception.append("");
		}
		Map countRecType = new HashMap();
		for (int i = 0; i < legacyAssignments.size(); i++) {
			LegacyUpdateLog legacyUpdateLog = (LegacyUpdateLog) legacyAssignments
					.get(i);
			int j = i + 1;
			exception.append("EXCEPTION: " + j + "\n\tSOURCE ID : "
					+ legacyUpdateLog.getSourceId() + " \n\tUPDATED ON: "
					+ legacyUpdateLog.getUpdateTimestamp() + " \n\tSPN: "
					+ legacyUpdateLog.getSpn() + " \n\tCRIMINAL CASEID: "
					+ legacyUpdateLog.getCriminalCaseId() + "\n\tPROC MSG: "					
					+ legacyUpdateLog.getProcMessage() + "\n\tRECORD TYPE: "
					+ legacyUpdateLog.getRecordTypeId() + " \n\tRECORD DATA: "
					+ legacyUpdateLog.getRecordData() + "\n\n\n");
			Integer count = (Integer) countRecType.get(legacyUpdateLog
					.getRecordTypeId());
			if (count != null) {
				countRecType.put(legacyUpdateLog.getRecordTypeId(),
						new Integer(count.intValue() + 1));
			} else {
				countRecType.put(legacyUpdateLog.getRecordTypeId(),
						new Integer(1));
			}

		}
		Iterator keys = countRecType.entrySet().iterator();
		String valueStr = "";
		while (keys.hasNext()) {
			Entry key = (Entry) keys.next();
			valueStr = key.getKey() + " : " + key.getValue() + "\t" + valueStr;
		}

		email
				.setMessage("\t\t\t\t\t\t\t\t\t\t Legacy Update Log Exception Summary Report \n\nCount\n"
						+ valueStr + "\n\nDetails\n" + exception.toString());
		email.setSubject("Legacy Update Log Exception Summary Report");
		email.setFromAddress("LegacyUpdateException.Monitor@itc.hctx.net");
		email.addToAddress("raja.veerappan@itc.hctx.net");

		IDispatch requestDispatch = EventManager
				.getSharedInstance(EventManager.REQUEST);
		requestDispatch.postEvent(email);

	}
}
