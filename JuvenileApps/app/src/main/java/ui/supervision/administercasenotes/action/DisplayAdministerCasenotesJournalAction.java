/*
 * Created on Aug 2, 2006
 *
 */
package ui.supervision.administercasenotes.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercasenotes.DeleteCasenoteEvent;
import messaging.administercasenotes.GetCasenotesEvent;
import messaging.administercasenotes.UpdateCasenoteStatusEvent;
import messaging.administercasenotes.domintf.ICasenote;
import messaging.administercasenotes.reply.CasenoteResponseEvent;
import messaging.contact.domintf.IName;
import messaging.cscdcalendar.GetMonthlyCSCalendarEvent;
import messaging.cscdcalendar.reply.MonthlyCSCalendarResponseEvent;
import messaging.report.GenericPrintRequestEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.CSEventControllerServiceNames;
import naming.CasenoteControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.Casenote;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administercasenotes.CasenotesJournalBean;
import ui.supervision.administercasenotes.UICasenoteHelper;
import ui.supervision.administercasenotes.form.CasenoteJournalForm;
import ui.supervision.administercasenotes.form.CasenoteSearchForm;
import ui.supervision.administercasenotes.form.SuperviseeInfoHeaderForm;
import ui.supervision.manageassociate.UIManageAssociateHelper;

/**
 * @author jjose
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class DisplayAdministerCasenotesJournalAction extends JIMSBaseAction {
	

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected void addButtonMapping(Map keyMap) {
        keyMap.put("button.delete", "deleteCasenote");
        keyMap.put("button.assessments", "assessments");
        keyMap.put("button.edit", "editCasenote");
        keyMap.put("button.createCasenote", "createCasenote");
        keyMap.put("button.viewAll", "viewAll");
        keyMap.put("button.search", "search");
        keyMap.put("button.linkCreate", "linkCreate");
        keyMap.put("button.link", "link");
        keyMap.put("button.advancedSearch", "advancedSearch");
        keyMap.put("button.basicSearch", "basicSearch");
        //keyMap.put("button.back", "back");
        //keyMap.put("button.cancel", "cancel");
        keyMap.put("button.refresh", "refresh");
        keyMap.put("button.print", "printCasenotes");
        keyMap.put("button.addAssociate", "addAssociate");
        keyMap.put("button.reset", "resetCasenoteToDraft");
        keyMap.put("button.saveAsDraft", "saveAsDraft");
		keyMap.put("button.finish", "finish");
    }
    
    public ActionForward assessments(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)throws GeneralFeedbackMessageException {
    	CasenoteJournalForm myForm = (CasenoteJournalForm) aForm;
		String myForward="toAssessments";
		String spn=myForm.getSuperviseeId();
		if(spn==null || spn.trim().equals("")){
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"A Supervisee must be available");
			myForward=UIConstants.FAILURE;
		}
		else{
			SuperviseeInfoHeaderForm supInfoHeaderForm = (SuperviseeInfoHeaderForm)getSessionForm(aMapping, aRequest, "superviseeInfoHeaderForm", true);	    
			supInfoHeaderForm.setSpn(spn);
		}
		return aMapping.findForward(myForward);
	}
    
    public ActionForward deleteCasenote(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        
    	CasenoteJournalForm myForm = (CasenoteJournalForm) aForm;
    	myForm.setSecondaryAction("");
        String casenoteId = myForm.getSelectedValue();
        myForm.setAction(UIConstants.DELETE_SUCCESS);
        DeleteCasenoteEvent deleteEvent = (DeleteCasenoteEvent) EventFactory
                .getInstance(CasenoteControllerServiceNames.DELETECASENOTE);
        deleteEvent.setCasenoteId(casenoteId);
        EventManager.getSharedInstance(EventManager.REQUEST).postEvent(deleteEvent);
        CompositeResponse compositeResponse = (CompositeResponse) EventManager.getSharedInstance(EventManager.REQUEST)
                .getReply();
        MessageUtil.processReturnException(compositeResponse);
        myForm.getCurrentCasenote().clearAll();
        
        Collection casenotes = myForm.getSearchCasenote().getCasenoteResults();
        if (casenotes != null && casenotes.size() > 0) {
            Iterator iterCaseNotes = casenotes.iterator();
            while (iterCaseNotes.hasNext()) {
                Casenote myCasenote = (Casenote) iterCaseNotes.next();
                if (myCasenote.getCasenoteId().equals(casenoteId)) {
                	iterCaseNotes.remove();
                	myForm.setSecondaryAction(UIConstants.DELETE_SUCCESS);
                    break;
                }
            }
        }

        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward editCasenote(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        CasenoteJournalForm myForm = (CasenoteJournalForm) aForm;
        myForm.setSecondaryAction("");
        String casenoteId = myForm.getSelectedValue();
        myForm.setAction(UIConstants.UPDATE);
        myForm.getCurrentCasenote().clearAll();
        myForm.getCurrentCasenote().setAction(UIConstants.UPDATE);
        Collection casenotes = myForm.getSearchCasenote().getCasenoteResults();
        if (casenotes != null && casenotes.size() > 0) {
            Iterator iterCaseNotes = casenotes.iterator();
            while (iterCaseNotes.hasNext()) {
                Casenote myCasenote = (Casenote) iterCaseNotes.next();
                if (myCasenote.getCasenoteId().equals(casenoteId)) {
                    UICasenoteHelper.setCasenoteForm(myForm.getCurrentCasenote(), myCasenote, myForm
                            .getSupervisionPeriodId());
                    myForm.getCurrentCasenote().setAction(UIConstants.UPDATE);
                    myForm.getCurrentCasenote().setAssociateList(UIManageAssociateHelper.fetchAssociatesListSortedOnDisplayName(myForm.getSuperviseeId()));
                    break;
                }
            }
        }
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward createCasenote(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        CasenoteJournalForm myForm = (CasenoteJournalForm) aForm;
        myForm.setAction(UIConstants.CREATE);
        myForm.setSecondaryAction("");
        myForm.getCurrentCasenote().clearAll();
        myForm.getCurrentCasenote().setAssociateList(UIManageAssociateHelper.fetchAssociatesListSortedOnDisplayName(myForm.getSuperviseeId()));
        myForm.getCurrentCasenote().setAction(UIConstants.CREATE);
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward viewAll(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        CasenoteJournalForm myForm = (CasenoteJournalForm) aForm;
        UICasenoteHelper.setAllJournalCaseNotes(myForm.getSupervisionPeriodId(), myForm);
        myForm.setSecondaryAction("");
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward search(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        CasenoteJournalForm myForm = (CasenoteJournalForm) aForm;
        CasenoteSearchForm searchForm = myForm.getSearchCasenote();
        myForm.setSecondaryAction("");
        String forward=UIConstants.SUCCESS;
        GetCasenotesEvent request =
         (GetCasenotesEvent)EventFactory.getInstance(CasenoteControllerServiceNames.GETCASENOTES);
        request.setSearchBy(UIUtil.blankToNull(searchForm.getSearchById()));
        request.setAll(false);
        request.setAssociateIds(UIUtil.convertStringArr2Coll(searchForm.getAssociateIds()));
        request.setBeginDate(searchForm.getCasenoteBeginDate());
		request.setCaseIds(UIUtil.convertStringArr2Coll(searchForm.getCaseIds()));
		request.setCasenoteTypeId(UIUtil.blankToNull(searchForm.getCasenoteTypeId()));
		request.setCollateralId(UIUtil.blankToNull(searchForm.getCollateralId()));
		request.setCourtNum(UIUtil.blankToNull(searchForm.getCourt()));
		request.setEndDate(searchForm.getCasenoteEndDate());
		request.setHowGeneratedId(UIUtil.blankToNull(searchForm.getGeneratedById()));
		request.setSubjects(UIUtil.convertStringArr2Coll(searchForm.getSubjectIds()));
		request.setSuperviseeId(myForm.getSuperviseeId());
		request.setSupervisionPeriodId(myForm.getSupervisionPeriodId());
		request.setAssociateIds(UIUtil.convertStringArr2Coll(searchForm.getAssociateIds()));
		request.setCreateDate(searchForm.getCreateDate());
		//send to PD search criteria, evaluate search results and return to proper place
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(request);
        
        CompositeResponse response = (CompositeResponse) dispatch.getReply();
        // check for errors
        MessageUtil.processReturnException(response);
        			      			
        // process results from lookup
        Collection casenotes = MessageUtil.compositeToCollection(response, CasenoteResponseEvent.class);		
        ArrayList myExistingCasenotes = new ArrayList();
        if (casenotes!=null && !casenotes.isEmpty()){
			Collection casenoteSubjectList = searchForm.getCasenoteSubjectList();
			Collection casenoteTypeList = searchForm.getCasenoteTypeList();
			Collection collateralList = searchForm.getCollateralList();
			Collection contactMethodList = searchForm.getContactMethodList();
			String currentUserId = UIUtil.getCurrentUserID();

        	Iterator iter = casenotes.iterator();
        	while (iter.hasNext()){
        		ICasenote casenote = (CasenoteResponseEvent)iter.next();
        		Casenote newCasenote = UICasenoteHelper.getCasenote(casenote, currentUserId);
				newCasenote.setCasenoteTypeId(casenote.getCasenoteTypeId(), casenoteTypeList);
				String[] associatesArr = UICasenoteHelper.getArrayFromCollection(casenote.getAssociates());
				newCasenote.setAssociateIds(associatesArr, collateralList);
				String[] cnSubjs = UICasenoteHelper.getArrayFromCollection(casenote.getSubjects());
				newCasenote.setSubjectIds(cnSubjs, casenoteSubjectList);
				newCasenote.setAssociateIds(associatesArr, collateralList);
				newCasenote.setContactMethodId(casenote.getContactMethodId(), contactMethodList);
				myExistingCasenotes.add(newCasenote);
        	}
        	UICasenoteHelper.resolveCreatorNames(myExistingCasenotes);
        	if (searchForm.getGeneratedById()!= null 
        			&& searchForm.getGeneratedById().equals("CB")
        			&& searchForm.getCreatedByName() != null 
        			&& !searchForm.getCreatedByName().getLastName().equals("")){
        			myExistingCasenotes = this.filterByName(myExistingCasenotes, searchForm.getCreatedByName());
        	}
        	
        	Collections.sort(myExistingCasenotes);
       	} else{
        	//sendToErrorPage(aRequest, "error.no.search.results.found");
        	forward=UIConstants.SUCCESS;
      	}
        			
        searchForm.setCasenoteResults(myExistingCasenotes);
        			
        return aMapping.findForward(forward);
    }

    private ArrayList filterByName(ArrayList myExistingCasenotes, IName filterByName) {
    	ArrayList filteredList = new ArrayList();
    	Casenote casenote = null;
		for (int i = 0; i < myExistingCasenotes.size(); i++) {
			casenote = (Casenote) myExistingCasenotes.get(i);
			IName caseNoteCreator = casenote.getCreatedByName();
			if (caseNoteCreator.getLastName().equals(filterByName.getLastName().toUpperCase())){
				if (filterByName.getFirstName() != null 
						&& !filterByName.getFirstName().equals("")
						&& caseNoteCreator.getFirstName().equals(filterByName.getFirstName().toUpperCase())){
					filteredList.add(casenote);
				} else {
					filteredList.add(casenote);
				}
			}
		}
		return filteredList;
	}

    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
	public ActionForward linkCreate(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        link(aMapping, aForm, aRequest, aResponse);
        return createCasenote(aMapping, aForm, aRequest, aResponse);
    }

	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
    public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        CasenoteJournalForm myForm = (CasenoteJournalForm) aForm;
        SuperviseeInfoHeaderForm mySupHeader = UICommonSupervisionHelper.getSuperviseeInfoHeaderForm(aRequest, true);
        myForm.setAction(UIConstants.VIEW);        
        
//Defect ID: JIMS200054516 Start		
		GetMonthlyCSCalendarEvent getMonthlyEvents = 
			(GetMonthlyCSCalendarEvent)EventFactory.getInstance(CSEventControllerServiceNames.GETMONTHLYCSCALENDAR);
		getMonthlyEvents.setCurrentContext("S");		
		getMonthlyEvents.setSuperviseeId(mySupHeader.getSuperviseeId());	
		getMonthlyEvents.setStartDatetime(Calendar.getInstance().getTime());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(getMonthlyEvents);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		Collection events = MessageUtil.compositeToCollection(compositeResponse, MonthlyCSCalendarResponseEvent.class);
		List eventsList = (List) events;
		if(eventsList != null && !eventsList.isEmpty()){
			// Sort list if has data
			Collections.sort(eventsList, MonthlyCSCalendarResponseEvent.CSCalendarDateComparator);
			Collections.reverse(eventsList);
			Iterator<MonthlyCSCalendarResponseEvent> calendarEvents = eventsList.iterator();
			while( calendarEvents.hasNext() ){
				MonthlyCSCalendarResponseEvent event = (MonthlyCSCalendarResponseEvent) calendarEvents.next();
//defect 65723 extra edit
				if (event.getStartDatetime().after(getMonthlyEvents.getStartDatetime()) ||
					( event.getStartTime() != null && !event.getStartTime().equals("") &&
					  event.getStartTime().after(getMonthlyEvents.getStartDatetime()) ) ) {
					if(event.getStatus().equals("O")) {
						if( mySupHeader.getNextContactDate() == null || mySupHeader.getNextContactDate().after(event.getStartDatetime()) ){
						
							mySupHeader.setNextContactDate(event.getStartDatetime());
						// if starttime present use it, as it contains date and time value.	
							if (event.getStartTime() != null && !event.getStartTime().equals("")){
								mySupHeader.setNextContactDate(event.getStartTime());
							}
							mySupHeader.setNextContactTime(event.getStartTime());
							mySupHeader.setContactMethodId(event.getCategoryCd());
							mySupHeader.setContactMethod(event.getCategoryDesc());
						}
					}
				} 
			}
		}
//Defect ID: JIMS200054516 End
        UICasenoteHelper.setAllJournalCaseNotes(myForm.getSupervisionPeriodId(), myForm);
        UICasenoteHelper.setCasesList(myForm, aRequest);
        myForm.clearCurrentCasenoteAction();
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward advancedSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        CasenoteJournalForm myForm = (CasenoteJournalForm) aForm;
        myForm.setSecondaryAction("");
        Collection searchResults=myForm.getSearchCasenote().getCasenoteResults();
        myForm.getSearchCasenote().clearSearchFields();
        //			 TODO: set defaults up
        myForm.getSearchCasenote().setCasenoteResults(searchResults);
        myForm.getSearchCasenote().setAction(UIConstants.ADVANCED);
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward basicSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        CasenoteJournalForm myForm = (CasenoteJournalForm) aForm;
        myForm.setSecondaryAction("");
        Collection searchResults=myForm.getSearchCasenote().getCasenoteResults();
        myForm.getSearchCasenote().clearSearchFields();
        //			 TODO: set defaults up
        myForm.getSearchCasenote().setCasenoteResults(searchResults);
        myForm.getSearchCasenote().setAction(UIConstants.BASIC);
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        CasenoteJournalForm myForm = (CasenoteJournalForm) aForm;
        myForm.setSecondaryAction("");
        String tempAction = myForm.getSearchCasenote().getAction();
        myForm.getSearchCasenote().clearSearchFields();
        // TODO: set defaults up
        myForm.getSearchCasenote().setAction(tempAction);
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        CasenoteJournalForm myForm = (CasenoteJournalForm) aForm;
        myForm.setSecondaryAction("");
        myForm.clearCurrentCasenoteAction();
        return aMapping.findForward(UIConstants.BACK);
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        CasenoteJournalForm myForm = (CasenoteJournalForm) aForm;
        myForm.setSecondaryAction("");
        myForm.clearCurrentCasenoteAction();
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }

    public ActionForward printCasenotes(ActionMapping aMapping, ActionForm aForm, 
    		HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception{
    	
    	CasenoteJournalForm casenoteJournalForm = (CasenoteJournalForm) aForm;
    	casenoteJournalForm.setSecondaryAction("");
    	
    	Collection casenotes = casenoteJournalForm.getSearchCasenote().getCasenoteResults();
    			// user want to print single casenote
    	    	if (casenoteJournalForm.getSelectedCasenoteId() != null){
    	            if (casenotes != null && casenotes.size() > 0) {
    	                Iterator iterCaseNotes = casenotes.iterator();
    	                while (iterCaseNotes.hasNext()) {
    	                    Casenote myCasenote = (Casenote) iterCaseNotes.next();
    	                    if (myCasenote.getCasenoteId().equals(casenoteJournalForm.getSelectedCasenoteId())) {
    	                    	casenoteJournalForm.getSearchCasenote().setCasenoteResults( new ArrayList() );
    	                    	List temp = new ArrayList();
    	                    	temp.add(myCasenote);
    	                    	casenoteJournalForm.getSearchCasenote().setCasenoteResults(temp);
    	                        break;
    	                    }
    	                }
    	            }    		
    	    	}   	
    	SuperviseeInfoHeaderForm supInfoHeaderForm = UICommonSupervisionHelper.getSuperviseeInfoHeaderForm(aRequest);
 			
	    GenericPrintRequestEvent casenotesPrintEvent = new GenericPrintRequestEvent();
	    casenotesPrintEvent.addDataObject(prepareBean(supInfoHeaderForm, casenoteJournalForm));   
	    casenotesPrintEvent.setReportName("REPORTING::CASENOTE");
	    	    
   	    ReportResponseEvent aRespEvent = (ReportResponseEvent) MessageUtil.postRequest(casenotesPrintEvent, ReportResponseEvent.class );
   	    
   	    if (aRespEvent == null) {
   	    	if (casenoteJournalForm.getSelectedCasenoteId() != null){
   	   	    	casenoteJournalForm.getSearchCasenote().setCasenoteResults( new ArrayList() );
   	   	    	casenoteJournalForm.getSearchCasenote().setCasenoteResults(casenotes);
   	   	    }
   	    	return aMapping.findForward("casenotePrintException");
	    } 
   	    aResponse.setContentType("application/x-file-download");
   	    aResponse.setHeader("Content-disposition", "attachment; filename="
   	    							+ aRespEvent.getFileName().substring(aRespEvent.getFileName().lastIndexOf("/") + 1) + ".pdf");   
   	    aResponse.setHeader("Cache-Control", "must-revalidate");   
   	    aResponse.setContentLength(aRespEvent.getContent().length);   
   	    aResponse.resetBuffer();   
   	    OutputStream os;
   	    os = aResponse.getOutputStream();
   	    os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);   
   	    os.flush();   
   	    os.close(); 
   	    // reload all casenotes after single print   	    
   	    if (casenoteJournalForm.getSelectedCasenoteId() != null){
   	    	casenoteJournalForm.getSearchCasenote().setCasenoteResults( new ArrayList() );
   	    	casenoteJournalForm.getSearchCasenote().setCasenoteResults(casenotes);
   	    }	   	    
        return null;
    }
    
    public ActionForward addAssociate(ActionMapping aMapping, ActionForm aForm,
			  HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException{
		
    	CasenoteJournalForm casenoteJournalForm = (CasenoteJournalForm)aForm;
    	
    	if (casenoteJournalForm.getSelectedValue().equals(UIConstants.ASSOCIATE_CREATE_SUCCESS)){
    		casenoteJournalForm.setSecondaryAction(casenoteJournalForm.getSelectedValue());
    		casenoteJournalForm.getCurrentCasenote().setAssociateList(UIManageAssociateHelper.fetchAssociatesListSortedOnDisplayName(casenoteJournalForm.getSuperviseeId()));
    	}
    	/*else{
    		if (casenoteJournalForm.getSelectedValue().equals(UIConstants.CANCEL_CASENOTES)){
    			//TODO: go where?
        	}
    	}*/
    	
    	return aMapping.findForward(UIConstants.SUCCESS);
    	
    }
    
    private CasenotesJournalBean prepareBean(SuperviseeInfoHeaderForm sForm, CasenoteJournalForm cjForm){
    	CasenotesJournalBean printDataBean = new CasenotesJournalBean();
    	
    	printDataBean.setSupInfoHeaderForm(sForm);
    	printDataBean.setCasenoteJournalForm(cjForm);
    	printDataBean.setSuperviseeName(sForm.getSuperviseeName().getFormattedName());   
    	printDataBean.setOfficerName(sForm.getOfficerName().getFormattedName());
    	return printDataBean;
	}
    public ActionForward resetCasenoteToDraft(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)throws GeneralFeedbackMessageException {
    	CasenoteJournalForm casenoteForm = (CasenoteJournalForm) aForm;
    	casenoteForm.setSecondaryAction("");
    	String casenoteId = casenoteForm.getSelectedValue();
    	UpdateCasenoteStatusEvent updateEvent = 
			(UpdateCasenoteStatusEvent) EventFactory.getInstance(CasenoteControllerServiceNames.UPDATECASENOTESTATUS);
		updateEvent.setCasenoteId(casenoteForm.getSelectedValue());
		updateEvent.setAutoSaveAsDraft(true);
		updateEvent.setStatusId("D");
		CasenoteResponseEvent response = (CasenoteResponseEvent) MessageUtil.postRequest( updateEvent , CasenoteResponseEvent.class );
		if (response == null) {
			Collection casenotes = casenoteForm.getSearchCasenote().getCasenoteResults();
	        if (casenotes != null && casenotes.size() > 0) {
	            Iterator iterCaseNotes = casenotes.iterator();
	            while (iterCaseNotes.hasNext()) {
	                Casenote myCasenote = (Casenote) iterCaseNotes.next();
	                if (myCasenote.getCasenoteId().equals(casenoteId)) {
	                	myCasenote.setCasenoteStatusId("D");
	                	myCasenote.setAllowEdit(true);
	                    break;
	                }
	            }
	        }
			System.out.println("Case note successfully reset to Draft.");
			casenoteForm.setSecondaryAction(UIConstants.RESET_SUCCESS);
			return aMapping.findForward(UIConstants.SUCCESS);
		} else {
			sendToErrorPage(aRequest, "error.generic", "Casenote save failed.");
			System.out.println("Case note save failed.");
			return aMapping.findForward(UIConstants.SUCCESS);
		}
	}
    public ActionForward saveAsDraft(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        return aMapping.findForward("saveAsDraft");
    }  
    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) {
        return aMapping.findForward(UIConstants.FINISH);
    } 

}// END CLASS