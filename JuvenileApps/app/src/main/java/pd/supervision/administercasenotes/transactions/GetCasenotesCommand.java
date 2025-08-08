// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercasenotes\\transactions\\GetCasenotesCommand.java

package pd.supervision.administercasenotes.transactions;

import java.util.Iterator;
import messaging.administercasenotes.GetCasenotesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import naming.PDCodeTableConstants;
import naming.PDCommonSupervisionConstants;
import pd.supervision.administercasenotes.CasenoteHelper;
import pd.supervision.administercasenotes.CasenoteSearchHelper;

public class GetCasenotesCommand implements ICommand {

	/**
	 * @roseuid 44F4632F0377
	 */
	public GetCasenotesCommand() {

	}

	/**
	 * @param inEvent
	 * @return
	 */
	private Iterator advancedSearch(GetCasenotesEvent inEvent) {
		return CasenoteSearchHelper.getCasenotesAdvancedSearch(inEvent);
	}

	/**
	 * @param inEvent
	 * @return
	 */
	private Iterator basicSearch(GetCasenotesEvent inEvent) {
		return CasenoteSearchHelper.getCasenotes(inEvent);
	}

	/**
	 * @param event
	 * @roseuid 44EE113903B6
	 */
	public void execute(IEvent event) {
		GetCasenotesEvent inEvent = (GetCasenotesEvent) event;

		String searchBy = inEvent.getSearchBy();

		Iterator casenotesIter = null;

		if (PDCommonSupervisionConstants.SEARCH_BY_SUBJECTS.equals(searchBy)) {
			casenotesIter = searchBySubject(inEvent);
		} else if (PDCommonSupervisionConstants.SEARCH_BY_COLLATERAL.equals(searchBy)){
		    casenotesIter = searchByCollateral(inEvent);
		} else if (PDCommonSupervisionConstants.SEARCH_BY_CASES.equals(searchBy)
				|| PDCommonSupervisionConstants.SEARCH_BY_COURT.equals(searchBy)) {
			casenotesIter = searchBySupervisionOrderValues(inEvent);
		} else if (searchBy == null) {
			casenotesIter = advancedSearch(inEvent);
		} else {
			casenotesIter = basicSearch(inEvent);
		}
		CasenoteHelper helper = CasenoteHelper.getInstance();
		helper.postCasenoteResponses(casenotesIter);
		
		casenotesIter = null;
		inEvent = null;
	}

	/**
	 * @param event
	 * @roseuid 44EE113903BF
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 44EE113903C1
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param searchEvent
	 * @return Collection
	 * @roseuid 450730B20176
	 */
	private Iterator searchBySubject(GetCasenotesEvent searchEvent) {
		return CasenoteSearchHelper.getCasenotesBySubject(searchEvent);
	}
	/**
	 * @param searchEvent
	 * @return Collection
	 * @roseuid 450730B20176
	 */
	private Iterator searchByCollateral(GetCasenotesEvent searchEvent) {
	    Iterator iter = null;
	    if (searchEvent.getCollateralId() != null && !searchEvent.getCollateralId().equals(""))
	        if (searchEvent.getCollateralId().equals(PDCodeTableConstants.CONTACT_WITH_ASSOCIATES)){
	            iter = CasenoteSearchHelper.getCasenotesByAssociate(searchEvent);
	        } else {
	            iter = CasenoteSearchHelper.getCasenotesBySupervisionOrderValues(searchEvent);
	        }
		return iter;
	}

	/**
	 * @param searchEvent
	 * @return Collection
	 * @roseuid 45096F5602C4
	 */
	public Iterator searchBySupervisionOrderValues(GetCasenotesEvent searchEvent) {
		return CasenoteSearchHelper.getCasenotesBySupervisionOrderValues(searchEvent);
	}

	/**
	 * @param updateObject
	 * @roseuid 44F4632F038B
	 */
	public void update(Object updateObject) {

	}
}
