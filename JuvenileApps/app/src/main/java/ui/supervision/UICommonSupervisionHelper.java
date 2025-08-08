/*
 * Created on Oct 3, 2006
 *
 */
package ui.supervision;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import messaging.administerassessments.GetAssessmentsSummaryEvent;
import messaging.administerassessments.reply.AssessmentSummaryResponseEvent;
import messaging.administercaseload.GetSuperviseeHeaderInfoEvent;
import messaging.administercaseload.reply.SuperviseeInfoResponseEvent;
import messaging.administercasenotes.GetSpnFromCaseFileIdEvent;
import messaging.administercasenotes.GetSuperviseeInSupervisionPeriodEvent;
import messaging.administercasenotes.reply.SpnFromCaseFileIdResponseEvent;
import messaging.administercasenotes.reply.SupervisionPeriodNotFoundEvent;
import messaging.administercasenotes.to.CasenoteCaseTO;
import messaging.administercompliance.reply.ComplianceConditionResponseEvent;
import messaging.administersupervisee.GetSuperviseeDataEvent;
import messaging.administersupervisee.reply.SuperviseeDetailResponseEvent;
import messaging.codetable.criminal.reply.OffenseCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.domintf.IAddress;
import messaging.contact.domintf.IPhoneNumber;
import messaging.contact.domintf.ISocialSecurity;
import messaging.cscdstaffposition.GetCSCDSupervisionStaffEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.domintf.contact.party.ISupervisee;
import messaging.supervisionorder.GetDefendantSupervisionOrdersEvent;
import messaging.supervisionorder.GetLightSupervisionOrdersEvent;
import messaging.supervisionorder.GetSuperviseeComplianceIndicatorEvent;
import messaging.supervisionorder.reply.SuperviseeResponseEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.AssessmentControllerServiceNames;
import naming.CaseloadControllerServiceNames;
import naming.CasenoteControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.SupervisionConstants;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;
import ui.common.Address;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.OffenseHelper;
import ui.common.PhoneNumber;
import ui.common.SocialSecurity;
import ui.common.UIUtil;
import ui.common.form.OffenseSearchForm;
import ui.security.SecurityUIHelper;
import ui.supervision.administercasenotes.UICasenoteHelper;
import ui.supervision.administercasenotes.form.CasenoteJournalForm;
import ui.supervision.administercasenotes.form.CasenoteSearchForm;
import ui.supervision.administercasenotes.form.SuperviseeInfoHeaderForm;
import ui.supervision.administercasenotes.form.SuperviseeSearchForm;
import ui.supervision.managesupervisioncase.form.OutOfCountyCaseForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;

/**
 * @author jjose
 *  
 */
public class UICommonSupervisionHelper {

	public final static String CASENOTE_JOURNAL_FORM = "casenoteJournalForm";

	public final static String SUPERVISEE_INFO_HEADER_FORM = "superviseeInfoHeaderForm";

	public final static String CASENOTE_SEARCH_FORM = "casenoteSearchForm";

	public final static String OOC_FORM = "outOfCountyCaseForm";

	public final static String OFFENSE_SEARCH_FORM = "offenseSearchForm";

	public final static String SUPERVISION_ORDER_FORM = "supervisionOrderForm";
	
	public final static String DNA_COMPLIANCE_EFFECTIVE_DATE = "09/01/2009"; // business rule value implemented 2013 RJC
	
	public final static String FELONY_CDI = "003"; // business rule value implemented 2013 RJC
	
	public static void populateSuperviseeHeaderForm(SuperviseeHeaderForm myHeaderForm){
//		 fetch the party based on the given criteria
		  String supId=myHeaderForm.getSuperviseeId();
		  GetSuperviseeHeaderInfoEvent getEvent = (GetSuperviseeHeaderInfoEvent) EventFactory
	          .getInstance(CaseloadControllerServiceNames.GETSUPERVISEEHEADERINFO);
	      getEvent.setDefendantId(supId);
	      SuperviseeInfoResponseEvent sprResponse = (SuperviseeInfoResponseEvent) MessageUtil.postRequest(getEvent,
	      SuperviseeInfoResponseEvent.class);
          //	 clear any previous search results
		  myHeaderForm.clear();
		  myHeaderForm.setSuperviseeId(supId);
	  if (sprResponse != null) {
		  myHeaderForm.setSuperviseeFound(sprResponse.isSuperviseeFound());
	      myHeaderForm.setLOSDesc(sprResponse.getSupervisionLevel());
	  	  myHeaderForm.setOfficerNameDesc(sprResponse.getOfficerName());
	  	  myHeaderForm.setProgramUnitDesc(sprResponse.getProgramUnit());
	  	  myHeaderForm.setSuperviseeNameDesc(sprResponse.getDefendantName());
	  	  myHeaderForm.setSuperviseeSpn(supId);
	  	  myHeaderForm.setOfficerPositionId(sprResponse.getPositionId());
	  	  myHeaderForm.setCompliant(isSuperviseeCompliant(myHeaderForm.getSuperviseeId()));
	  	  
	  	GetSuperviseeDataEvent anEvent = new GetSuperviseeDataEvent();
		anEvent.setSuperviseeId(supId);
		CompositeResponse response = MessageUtil.postRequest(anEvent);
		
		SuperviseeDetailResponseEvent superviseeDetailResponseEvent = (SuperviseeDetailResponseEvent) MessageUtil.filterComposite(response,
				SuperviseeDetailResponseEvent.class);
		if(superviseeDetailResponseEvent != null){
	  	  myHeaderForm.setDnaFlagInd(superviseeDetailResponseEvent.isDnaFlagInd());
		}  
	  	  
	  	GetDefendantSupervisionOrdersEvent getDefEvent = (GetDefendantSupervisionOrdersEvent)EventFactory
		     .getInstance(SupervisionOrderControllerServiceNames.GETDEFENDANTSUPERVISIONORDERS);
	  	getDefEvent.setDefendantId(supId);
	  	
	  	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	  	dispatch.postEvent(getDefEvent);
		CompositeResponse replies = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException( replies );
	  	List<SupervisionOrderDetailResponseEvent> soResponseEvents = MessageUtil.compositeToList(replies, SupervisionOrderDetailResponseEvent.class);
	  	    if(soResponseEvents != null){
	  	      Collections.sort(soResponseEvents, SupervisionOrderDetailResponseEvent.OrderStatusProbationStartDateComparator);
	  	  	  Iterator supOrderDetails = soResponseEvents.iterator();
	  	  	  // br: determine DNA indicator values if cdi=003, disposition=PROB, Order is Active, and Probation Start Date greater than 9/1/2009
	  	  	  // br: otherwise set dna indicators to not show up
	  	  	  myHeaderForm.setOverNinetyDays(false);
	  	  	  myHeaderForm.setDnaFlagInd(true);
	  	   	  while (supOrderDetails.hasNext()){
	  	   		SupervisionOrderDetailResponseEvent supOrderDetail = (SupervisionOrderDetailResponseEvent) supOrderDetails.next();
	  	   		String offenseLevel = supOrderDetail.getOffenseLevel();
	  	   		String orderStatusId = supOrderDetail.getOrderStatusId();
	  		   	String dispositionType = supOrderDetail.getDispositionTypeId();
	  		   	String cdi = supOrderDetail.getCdi();
	  	 		Date probationStartDate = new Date();
	  		   	Date dnaComplianceDate = new Date();
	  		   	probationStartDate = DateUtil.stringToDate(supOrderDetail.getProbationStartDate(), DateUtil.DATE_FMT_1);
	  		   	dnaComplianceDate = DateUtil.stringToDate(DNA_COMPLIANCE_EFFECTIVE_DATE, DateUtil.DATE_FMT_1);	
	  	   		if ( (dispositionType.equalsIgnoreCase("PROB")) && (orderStatusId.equals("A")) && 
	  	   			(cdi != null && cdi.equalsIgnoreCase(FELONY_CDI)) && 
	  	   			(offenseLevel != null && offenseLevel.equalsIgnoreCase("F")) && 
	  	   			(DateUtil.compare(dnaComplianceDate, probationStartDate, DateUtil.DATE_FMT_1) <= 0)) {	  	   				
		  	   			myHeaderForm.setDisposition(supOrderDetail.getDispositionTypeId());
		  	   			myHeaderForm.setOffenseLevel(supOrderDetail.getOffenseLevel());	  	   			
		  	   			myHeaderForm.setProbationStartDate(supOrderDetail.getProbationStartDate());		
		  	   			// determine dna indicator values overNinetyDays, and isDnaFlagInd flags for supervisee header based on busines rules
		  	   			determineDnaComplianceIndicators(myHeaderForm, supOrderDetail, superviseeDetailResponseEvent.isDnaFlagInd());
		  	   			break; 	   					  	   		
		  	   	}
	  	      }
	  	    }	  	 
	     }
	     return;
	  }
	
	/**
	 * method to calculate and determine dna compliance and to set ninetyDay and isDNAFlagInd based on results
	 * @param myHeaderForm
	 * @param supOrderDetail
	 * @return
	 */
	private static void determineDnaComplianceIndicators(SuperviseeHeaderForm myHeaderForm, 
			SupervisionOrderDetailResponseEvent supOrderDetail, boolean isSuperviseeDnaIndicator){
 		Date probationStartDate = new Date();
	   	probationStartDate = DateUtil.stringToDate(supOrderDetail.getProbationStartDate(), DateUtil.DATE_FMT_1);		   	
	    Calendar cal = Calendar.getInstance();
	    Date currentDate = new Date();
	    cal.setTime(currentDate);
	    Date expirationDate = cal.getTime();	    
	    cal.setTime(probationStartDate);
	    cal.add(Calendar.DATE, 90);
	    Date probationNinetyDaysDate = cal.getTime();
	    int isGreaterThanNinetyDays = DateUtil.compare(expirationDate, probationNinetyDaysDate, DateUtil.DATE_FMT_1);
	    if (isGreaterThanNinetyDays > 0 && !isSuperviseeDnaIndicator){
	    	myHeaderForm.setOverNinetyDays(true);
	    } else{ 
	    	myHeaderForm.setOverNinetyDays(false);	  	   		    
	    }
	    myHeaderForm.setDnaFlagInd(isSuperviseeDnaIndicator);
	}

	
	/**
	 * find the given user's staff position response event
	 */
	public static CSCDSupervisionStaffResponseEvent getUserStaffPosition(String anAgencyId, String aLogonId){
		if(anAgencyId==null){
			anAgencyId=SecurityUIHelper.getUserAgencyId();
		}
		if(aLogonId==null){
			aLogonId=SecurityUIHelper.getLogonId();
		}
		GetCSCDSupervisionStaffEvent myEvent=new GetCSCDSupervisionStaffEvent();
		myEvent.setUseStaffLogonId(true);
		myEvent.setAgencyId(anAgencyId);
		myEvent.setStaffLogonId(aLogonId);
		CSCDSupervisionStaffResponseEvent sprResponse = (CSCDSupervisionStaffResponseEvent) MessageUtil.postRequest(myEvent,
				CSCDSupervisionStaffResponseEvent.class);
		return sprResponse;
	}
	
	public static SupervisionOrderForm getSupervisionOrderForm(
			HttpServletRequest aRequest) {
		HttpSession session = aRequest.getSession();
		SupervisionOrderForm myForm = null;
		myForm = (SupervisionOrderForm) session
				.getAttribute(SUPERVISION_ORDER_FORM);
		return myForm;
	}

	public static SupervisionOrderForm getSupervisionOrderForm(
			HttpServletRequest aRequest, boolean isCreate) {

		SupervisionOrderForm myForm = getSupervisionOrderForm(aRequest);
		if (myForm == null) {
			HttpSession session = aRequest.getSession();
			myForm = new SupervisionOrderForm();
			session.setAttribute(SUPERVISION_ORDER_FORM, myForm);
		}
		return myForm;
	}

	public static OffenseSearchForm getOffenseSearchForm(
			HttpServletRequest aRequest) {
		HttpSession session = aRequest.getSession();
		OffenseSearchForm myForm = null;
		myForm = (OffenseSearchForm) session.getAttribute(OFFENSE_SEARCH_FORM);
		return myForm;
	}

	public static OffenseSearchForm getOffenseSearchForm(
			HttpServletRequest aRequest, boolean isCreate) {

		OffenseSearchForm myForm = getOffenseSearchForm(aRequest);
		if (myForm == null) {
			HttpSession session = aRequest.getSession();
			myForm = new OffenseSearchForm();
			session.setAttribute(OFFENSE_SEARCH_FORM, myForm);
		}
		return myForm;
	}

	public static OutOfCountyCaseForm getOOCForm(HttpServletRequest aRequest) {
		HttpSession session = aRequest.getSession();
		OutOfCountyCaseForm myForm = null;
		myForm = (OutOfCountyCaseForm) session.getAttribute(OOC_FORM);
		return myForm;
	}

	public static OutOfCountyCaseForm getOOCForm(HttpServletRequest aRequest,
			boolean isCreate) {

		OutOfCountyCaseForm myForm = getOOCForm(aRequest);
		if (myForm == null) {
			HttpSession session = aRequest.getSession();
			myForm = new OutOfCountyCaseForm();
			session.setAttribute(OOC_FORM, myForm);
		}
		return myForm;
	}

	public static CasenoteJournalForm getCasenoteJournalForm(
			HttpServletRequest aRequest) {
		HttpSession session = aRequest.getSession();
		CasenoteJournalForm casefileForm = null;
		casefileForm = (CasenoteJournalForm) session
				.getAttribute(CASENOTE_JOURNAL_FORM);
		return casefileForm;
	}

	public static String getSpn(String aSpn) {
		String spn = aSpn;
		if (spn != null && !spn.trim().equals("")) {
			while (spn.length() < 8) {
				spn = "0" + spn;
			}
		}
		return spn;
	}

	public static void setSuperviseeHeader(ISupervisee aSupervisee,
			HttpServletRequest aRequest,String aSupervisionPeriodId) {
		SuperviseeInfoHeaderForm mySuperviseeHeaderForm = UICommonSupervisionHelper
				.getSuperviseeInfoHeaderForm(aRequest, true);
		mySuperviseeHeaderForm.clearAll();
		if (aSupervisee != null) {
			UICommonSupervisionHelper.populateSupverviseeInfoHeaderForm(
					mySuperviseeHeaderForm, aSupervisee, aSupervisionPeriodId);
		}
	}

	/**
	 * @param spn
	 */
	public static CompositeResponse getSuperviseeInfo(String userAgencyId,
			String spn, boolean getNotesForActiveSupervisionPeriod,
			String supPeriodId) {
		IDispatch dispatch = EventManager
				.getSharedInstance(EventManager.REQUEST);

		GetSuperviseeInSupervisionPeriodEvent mySuperviseeEvent = (GetSuperviseeInSupervisionPeriodEvent) EventFactory
				.getInstance(CasenoteControllerServiceNames.GETSUPERVISEEINSUPERVISIONPERIOD);
		mySuperviseeEvent.setSpn(spn);
		mySuperviseeEvent.setUserAgencyId(userAgencyId);
		mySuperviseeEvent
				.setActiveSupervisionPeriod(getNotesForActiveSupervisionPeriod);
		if (!getNotesForActiveSupervisionPeriod) {
			mySuperviseeEvent.setSupervisionPeriodId(supPeriodId);
		}
		dispatch.postEvent(mySuperviseeEvent);
		CompositeResponse response = (CompositeResponse) EventManager
				.getSharedInstance(EventManager.REQUEST).getReply();
		// check for errors
		MessageUtil.processReturnException(response);
		return response;

	}

	public static String getSpnFromCaseFileId(String caseid) {		
		GetSpnFromCaseFileIdEvent event = (GetSpnFromCaseFileIdEvent) EventFactory
				.getInstance(CasenoteControllerServiceNames.GETSPNFROMCASEFILEID);
		event.setCaseFileId(caseid);		
		SpnFromCaseFileIdResponseEvent response_event = 
			(SpnFromCaseFileIdResponseEvent)MessageUtil.postRequest(event, SpnFromCaseFileIdResponseEvent.class);		
		return response_event.getSpn();
	}

	
	public static CasenoteSearchForm getCasenoteSearchForm(
			HttpServletRequest aRequest) {
		HttpSession session = aRequest.getSession();
		CasenoteSearchForm myForm = null;
		myForm = (CasenoteSearchForm) session
				.getAttribute(CASENOTE_SEARCH_FORM);
		return myForm;
	}

	public static CasenoteSearchForm getCasenoteSearchForm(
			HttpServletRequest aRequest, boolean isCreate) {

		CasenoteSearchForm myForm = getCasenoteSearchForm(aRequest);
		if (myForm == null) {
			HttpSession session = aRequest.getSession();
			myForm = new CasenoteSearchForm();
			session.setAttribute(CASENOTE_SEARCH_FORM, myForm);
		}
		return myForm;
	}

	public static CasenoteJournalForm getCasenoteJournalForm(
			HttpServletRequest aRequest, boolean isCreate) {

		CasenoteJournalForm myForm = getCasenoteJournalForm(aRequest);
		if (myForm == null) {
			HttpSession session = aRequest.getSession();
			myForm = new CasenoteJournalForm();
			session.setAttribute(CASENOTE_JOURNAL_FORM, myForm);
		}
		return myForm;
	}

	public static SuperviseeInfoHeaderForm getSuperviseeInfoHeaderForm(
			HttpServletRequest aRequest) {
		HttpSession session = aRequest.getSession();
		SuperviseeInfoHeaderForm myForm = (SuperviseeInfoHeaderForm) session
				.getAttribute(SUPERVISEE_INFO_HEADER_FORM);
		return myForm;
	}

	public static SuperviseeInfoHeaderForm getSuperviseeInfoHeaderForm(
			HttpServletRequest aRequest, boolean isCreate) {

		SuperviseeInfoHeaderForm myForm = getSuperviseeInfoHeaderForm(aRequest);
		if (myForm == null) {
			HttpSession session = aRequest.getSession();
			myForm = new SuperviseeInfoHeaderForm();
			session.setAttribute(SUPERVISEE_INFO_HEADER_FORM, myForm);
		}
		return myForm;
	}
	
	public static String loadSupHeaderInfoFromSupSearchForm(HttpServletRequest aRequest,
			SuperviseeSearchForm aSupSearchForm, BasicSupervisee aMySupervisee, String aSelectSupPeriodId) {
		SuperviseeInfoHeaderForm mySuperviseeHeaderForm = UICommonSupervisionHelper
		.getSuperviseeInfoHeaderForm(aRequest, true);
		UICommonSupervisionHelper.populateSupverviseeInfoHeaderForm(mySuperviseeHeaderForm, aMySupervisee, aSelectSupPeriodId);
		return null;
	}
	

	public static String loadSupHeaderInfoFromSupSearchForm(
			SuperviseeSearchForm aSupSearchForm, String aSelectSupPeriodId,
			HttpServletRequest aRequest) {
		CompositeResponse response = null;
		boolean invalidSupervisionPeriod = false;
		Collection supervisees = null;
		response = UICommonSupervisionHelper.getSuperviseeInfo(SecurityUIHelper
				.getUserAgencyId(), aSupSearchForm.getSpn(), aSupSearchForm
				.getSupervisionPeriodAsBool(), aSelectSupPeriodId);
		if (MessageUtil.filterComposite(response,
				SupervisionPeriodNotFoundEvent.class) != null) {
			invalidSupervisionPeriod = true;
			// RETURN WITH ERROR PAGE
			return "error.invalidSupPeriod";
		} else {
			supervisees = MessageUtil.compositeToCollection(response,
					SuperviseeResponseEvent.class);
		}
		ArrayList searchResultList = new ArrayList();
		aSupSearchForm.setSearchResults(searchResultList);
		ISupervisee superviseeInfo = null;
		if (supervisees != null && !supervisees.isEmpty()) {
			superviseeInfo = (ISupervisee) supervisees.iterator().next();
			searchResultList.add(UICasenoteHelper
					.getBasicSupervisee(superviseeInfo));
		} else { // No Search results on spn found
			return "error.no.search.results.found";
		}
		UICommonSupervisionHelper.setSuperviseeHeader(superviseeInfo, aRequest, aSelectSupPeriodId);
		return null;
	}

	  public static String padSpn(String aSpn)
	    {
	        String spn = aSpn;
	        if (spn != null && !spn.trim().equals(""))
	        {
	            while (spn.length() < 8)
	            {
	                spn = "0" + spn;
	            }
	        }
	        return spn;
	    }
	  
	public static void populateSupverviseeInfoHeaderForm(
			SuperviseeInfoHeaderForm aHeaderForm, ISupervisee aResponseEvent, String aSupervisionPeriodId) {
		if (aResponseEvent == null || aHeaderForm == null) {
			return;
		}
		aHeaderForm.setCon(aResponseEvent.getConnectionId());
		aHeaderForm.setNextContactDate(aResponseEvent.getNextContactDate());
//		aHeaderForm.setNextContactTime(aResponseEvent.getNextContactTime());
		aHeaderForm.setUnit(aResponseEvent.getUnit());
		aHeaderForm.setContactMethodId(aResponseEvent.getContactMethod());
		aHeaderForm.setContactReason(aResponseEvent.getContactReason());	
		
		aHeaderForm.setDateOfBirth(aResponseEvent.getDateOfBirth());
		StringBuffer padFBI = null;
		padFBI = new StringBuffer(aResponseEvent.getFbiNum());
	    if (padFBI.length() < 9){
	    	while (padFBI.length() < 9){
	    		padFBI.insert(0, "0");
	    	}
	    }
		aHeaderForm.setFBI(padFBI.toString());
		aHeaderForm.setRaceId(aResponseEvent.getRaceId());
		aHeaderForm.setSexId(aResponseEvent.getSexId());
		StringBuffer padSID = null;
		padSID = new StringBuffer(aResponseEvent.getSidNum());
	    if (padSID.length() < 8){
	    	while (padSID.length() < 8){
	    		padSID.insert(0, "0");
	    	}
	    }
		aHeaderForm.setSID(padSID.toString());
		StringBuffer padSpn = null;
		padSpn = new StringBuffer(aResponseEvent.getSpn());
	    if (padSpn.length() < 10){
	    	while (padSpn.length() < 10){
	    		padSpn.insert(0, "0");
	    	}
	    }
		aHeaderForm.setSpn(padSpn.toString());
		aHeaderForm.setInComplianceInd(aResponseEvent.isInComplianceInd());	
		ISocialSecurity ssn = new SocialSecurity(aResponseEvent.getSsn());
		aHeaderForm.setSsn(ssn);
		aHeaderForm.setSuperviseeId(aResponseEvent.getOID());
		aHeaderForm.setSuperviseeName(new Name(aResponseEvent.getFirstName(),
				aResponseEvent.getMiddleName(), aResponseEvent.getLastName()));
		aHeaderForm.setOfficerName(UIUtil.getNameFromString(aResponseEvent.getOfficerName()));
		IAddress myAddress = new Address();
		myAddress.setAddressId(aResponseEvent.getAddressId());
		myAddress.setCity(aResponseEvent.getCurrentAddressCity());
		myAddress.setStateCode(aResponseEvent.getCurrentAddressStateId());
		myAddress.setStreetName(aResponseEvent.getCurrentAddressStreetName());
		myAddress.setStreetNum(aResponseEvent.getCurrentAddressStreetNum());
		myAddress.setZipCode(aResponseEvent.getCurrentAddressZipCode());
		aHeaderForm.setAddress(myAddress);
		aHeaderForm.setSupervisionPeriodId(aSupervisionPeriodId);
		IPhoneNumber myHomePhone = new PhoneNumber("");
		if (aResponseEvent.getHomePhoneNum() != null) {
			myHomePhone = new PhoneNumber(aResponseEvent.getHomePhoneNum());
		}
		aHeaderForm.setHomePhone(myHomePhone);
		SuperviseeInfoHeaderForm.BasicEmplymentInfo myEmploymentInfo = new SuperviseeInfoHeaderForm.BasicEmplymentInfo();
		myEmploymentInfo.setEmployerName(aResponseEvent.getEmployerName());
		//	myEmploymentInfo.setEmploymentId(aResponseEvent.getEmploy)
		myEmploymentInfo.setTitle(aResponseEvent.getTitle());
		IPhoneNumber myWorkPhone = new PhoneNumber("");
		if (aResponseEvent.getWorkPhoneNum() != null) {
			myWorkPhone = new PhoneNumber(aResponseEvent.getWorkPhoneNum());
		}
		myEmploymentInfo.setPhone(myWorkPhone);
		aHeaderForm.setEmploymentInfo(myEmploymentInfo);
		if (aResponseEvent.getCases() != null 
				&& aResponseEvent.getCases().size() > 0) {
			ArrayList myCasesList = new ArrayList();
			aHeaderForm.setCases(myCasesList);
			Iterator iter = aResponseEvent.getCases().iterator();
			while (iter.hasNext()) {
				CasenoteCaseTO myCaseRespEvt = (CasenoteCaseTO) iter.next();
				if(myCaseRespEvt.getSupervisionPeriodId()!=null && myCaseRespEvt.getSupervisionPeriodId().equals(aSupervisionPeriodId)){
					CaseInfo myCase = new CaseInfo();
					StringBuffer padCase = null;
					padCase = new StringBuffer(myCaseRespEvt.getCaseNum());
				    if (padCase.length() < 12){
				    	while (padCase.length() < 12){
				    		padCase.insert(0, "0");
				    	}
				    	myCaseRespEvt.setCaseNum(padCase.toString());
				    }
					myCase.setCaseNum(myCaseRespEvt.getCaseNum());
					StringBuffer padCdi = null;
					padCdi = new StringBuffer(myCaseRespEvt.getCdi());
				    if (padCdi.length() < 3){
				    	while (padCdi.length() < 3){
				    		padCdi.insert(0, "0");
				    	}
				    	myCaseRespEvt.setCourtNum(padCdi.toString());
				    }
					myCase.setCdi(myCaseRespEvt.getCdi());
					StringBuffer padCourt = null;
					padCourt = new StringBuffer(myCaseRespEvt.getCourtNum());
				    if (padCourt.length() < 3){
				    	while (padCourt.length() < 3){
				    		padCourt.insert(0, "0");
				    	}
				    	myCaseRespEvt.setCourtNum(padCourt.toString());
				    }
					myCase.setCourt(myCaseRespEvt.getCourtNum());
					myCase.setCaseSupPeriodBeginDate(myCaseRespEvt
							.getCaseSupervisionPeriodBeginDate());
					myCase.setCaseSupPeriodEndDate(myCaseRespEvt
							.getCaseSupervisionPeriodEndDate());
					myCase.setSupPeriodId(myCaseRespEvt.getSupervisionPeriodId());
					myCasesList.add(myCase);
				}
			}
			Collections.sort((List) myCasesList);
		} else {
			aHeaderForm.setCases(new ArrayList());
		}
		return;
	}
	
	public static void populateSupverviseeInfoHeaderForm(
			SuperviseeInfoHeaderForm aHeaderForm, BasicSupervisee aMySuperviseeInfo, String aSupervisionPeriodId) {
		if (aMySuperviseeInfo == null || aHeaderForm == null) {
			return;
		}
		aHeaderForm.clearAll();
		aHeaderForm.setCon(aMySuperviseeInfo.getCon());
		aHeaderForm.setNextContactDate(aMySuperviseeInfo.getNextContactDate());
		aHeaderForm.setUnit(aMySuperviseeInfo.getUnit());
		aHeaderForm.setContactMethodId(aMySuperviseeInfo.getContactMethod());
		aHeaderForm.setContactReason(aMySuperviseeInfo.getContactReason());		
		aHeaderForm.setInComplianceInd(isSuperviseeCompliant(aMySuperviseeInfo.getSpn()));			
		aHeaderForm.setDateOfBirth(aMySuperviseeInfo.getDateOfBirth());
		aHeaderForm.setFBI(aMySuperviseeInfo.getFBI());
		aHeaderForm.setRaceId(aMySuperviseeInfo.getRaceId());
		aHeaderForm.setSexId(aMySuperviseeInfo.getSexId());
		aHeaderForm.setSID(aMySuperviseeInfo.getSID());
		aHeaderForm.setSpn(aMySuperviseeInfo.getSpn());
		aHeaderForm.setSsn(aMySuperviseeInfo.getSsn());
		aHeaderForm.setSuperviseeId(aMySuperviseeInfo.getSuperviseeId());
		aHeaderForm.setSuperviseeNamePtr(aMySuperviseeInfo.getSuperviseeNamePtr());
		aHeaderForm.setSuperviseeName(aMySuperviseeInfo.getSuperviseeName());
		aHeaderForm.setOfficerName(aMySuperviseeInfo.getOfficerName());
		aHeaderForm.setProbationOfficerId(aMySuperviseeInfo.getProbationOfficerId());
		aHeaderForm.setAddress(aMySuperviseeInfo.getAddress());
		aHeaderForm.setHomePhone(aMySuperviseeInfo.getHomePhone());
		aHeaderForm.setEmploymentInfo(aMySuperviseeInfo.getEmploymentInfo());
		aHeaderForm.setSupervisionPeriodId(aSupervisionPeriodId);
		aHeaderForm.setOfficerPosition(aMySuperviseeInfo.getOfficerPosition());
		
		String supId= aMySuperviseeInfo.getSpn();
		GetSuperviseeHeaderInfoEvent getEvent = (GetSuperviseeHeaderInfoEvent) EventFactory
	          .getInstance(CaseloadControllerServiceNames.GETSUPERVISEEHEADERINFO);
	    getEvent.setDefendantId(supId);
	    SuperviseeInfoResponseEvent sprResponse = (SuperviseeInfoResponseEvent) MessageUtil.postRequest(getEvent,
	    SuperviseeInfoResponseEvent.class);
	    aHeaderForm.setLos(sprResponse.getSupervisionLevel());
	    
	    GetAssessmentsSummaryEvent assessmentsSummaryEvent = (GetAssessmentsSummaryEvent) EventFactory.getInstance(AssessmentControllerServiceNames.GETASSESSMENTSSUMMARY);
	    assessmentsSummaryEvent.setDefendantId(supId);
		assessmentsSummaryEvent.setSearchOnActiveSupervisionPeriod(true);
		List assessmentList = MessageUtil.postRequestListFilter( assessmentsSummaryEvent, AssessmentSummaryResponseEvent.class);
		
		int listSize = assessmentList.size();
		ArrayList newList = new ArrayList();
		if (assessmentList != null && listSize > 0) {
			for (int s=0; s<listSize; s++){
				AssessmentSummaryResponseEvent assessment = (AssessmentSummaryResponseEvent) assessmentList.get(s);
				String assessmentTypeId = assessment.getAssessmentTypeId();
				String assessmentStatusCd = assessment.getAssessmentStatusCd();
				Date assessmentDate = assessment.getAssessmentDate();
				if( StringUtils.isNotEmpty(assessmentStatusCd) && assessmentStatusCd.equals("C") 
						&& assessmentDate != null && StringUtils.isNotEmpty(assessmentTypeId)) {
					if( assessmentTypeId.equals("W") || assessmentTypeId.equals("L") ) {
						newList.add(assessment);
					}
				}
			}
		}
		listSize = newList.size();
		if(newList != null && listSize > 0){
			//sort assessments by date ascending order
			Collections.sort(newList, UICommonSupervisionHelper.assessmentComparator );
			
			AssessmentSummaryResponseEvent lastAssessment = (AssessmentSummaryResponseEvent) newList.get(listSize - 1);
			aHeaderForm.setLastAssessmentDate(lastAssessment.getAssessmentDate());
		}
		
		if (aMySuperviseeInfo.getCases() != null 
				&& aMySuperviseeInfo.getCases().size() > 0) {
			ArrayList myCasesList = new ArrayList();
			aHeaderForm.setCases(myCasesList);
			Iterator iter = aMySuperviseeInfo.getCases().iterator();
			List supOrders=new ArrayList();
		    GetLightSupervisionOrdersEvent requestEvent =
				(GetLightSupervisionOrdersEvent) EventFactory.getInstance(
					SupervisionOrderControllerServiceNames.GETLIGHTSUPERVISIONORDERS);
			requestEvent.setSpn(aHeaderForm.getSpn());
			requestEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());		
			supOrders = MessageUtil.postRequestListFilter(requestEvent, SupervisionOrderDetailResponseEvent.class);
			
			while (iter.hasNext()) {
				CaseInfo myCaseInfo = (CaseInfo) iter.next();
					CaseInfo myCase = new CaseInfo();
					myCase.setCaseNum(myCaseInfo.getCaseNum());
					myCase.setCdi(myCaseInfo.getCdi());
					myCase.setCourt(myCaseInfo.getCourt());
					myCase.setCaseSupPeriodBeginDate(myCaseInfo
							.getCaseSupPeriodBeginDate());
					myCase.setCaseSupPeriodEndDate(myCaseInfo
							.getCaseSupPeriodEndDate());
					myCase.setSupPeriodId(myCaseInfo.getSupPeriodId());
					myCase.setSuperviseeName(myCaseInfo.getSuperviseeName());
					aHeaderForm.setSuperviseeNamePtr(myCaseInfo.getSuperviseeName());
					myCase.setOrderId(myCaseInfo.getOrderId());
					myCasesList.add(myCase);	
					int size = supOrders.size();
					for(int s=0; s<size; s++){
						SupervisionOrderDetailResponseEvent anOrder = (SupervisionOrderDetailResponseEvent) supOrders.get(s);
						String myCaseNum = myCase.getCaseNum();
						String anOrderNum = anOrder.getCaseNum();
						String orderStatus = anOrder.getOrderStatusId();
						if(StringUtils.isNotEmpty(myCaseNum) && StringUtils.isNotEmpty(anOrderNum) 
								&& myCaseNum.equals(anOrderNum) && orderStatus.equalsIgnoreCase("A")){
							if (StringUtils.isNotEmpty(anOrder.getPrintedOffenseDesc())){
								myCase.setOffense(anOrder.getPrintedOffenseDesc());
							} else if(StringUtils.isNotEmpty(myCaseInfo.getOffenseCodeId())){
								String tempOffenseId = UIUtil.stripZeroes(myCaseInfo.getOffenseCodeId());
							    if (StringUtils.isNotEmpty(tempOffenseId)){
							    	String descr = CodeHelper.getOffenseCodeDescription(tempOffenseId);
									myCase.setOffense(descr);
							    }
							}
						}
					}
			}
			Collections.sort((List) myCasesList);
		} else {
			aHeaderForm.setCases(new ArrayList());
		}
		return;
	}
	
	

	/**
	 * @param spn
	 * @return
	 */
	public static boolean isSuperviseeCompliant(String spn) {
		GetSuperviseeComplianceIndicatorEvent myCompIndEvent = (GetSuperviseeComplianceIndicatorEvent) EventFactory
	       .getInstance(SupervisionOrderControllerServiceNames.GETSUPERVISEECOMPLIANCEINDICATOR);
	    myCompIndEvent.setDefendantId(spn);
	    CompositeResponse response = MessageUtil.postRequest(myCompIndEvent);
	    ComplianceConditionResponseEvent resp = (ComplianceConditionResponseEvent) MessageUtil.filterComposite(response, ComplianceConditionResponseEvent.class);
	    if(resp != null){	    
		    return false;
		}
	    return true;
	}
	
//	public static getCaseOrderInfo(String spn)
//	{
//		SupervisionOrderSearchForm orderSearchForm = (SupervisionOrderSearchForm) aRequest
//		.getSession().getAttribute("supervisionOrderSearchForm");
//	}
	
	public static String computeCSMultiDeptForward(String aIncomingForward,
			String anAgency) {
		if (UIConstants.CS_ENABLE_MULTI_DEPT_PAGES) {
			if (UIConstants.JUV.equalsIgnoreCase(anAgency)) {
				return aIncomingForward + UIConstants.JUV;
			} else if (UIConstants.PTR.equalsIgnoreCase(anAgency)) {
				return aIncomingForward + UIConstants.PTR;
			} else {
				return aIncomingForward + UIConstants.CSC;
			}
		} else {
			return aIncomingForward;
		}
	}

	public static String validateOffense(String offenseId, String courtId, boolean isCDIMessage) {
		String message = null;
		Map aMap = new HashMap();
		OffenseCodeResponseEvent offense = OffenseHelper
				.retrieveOffenseCode(offenseId);
		message = UICommonSupervisionHelper.validateOffense(offense, courtId,isCDIMessage);
		return message;
	}

	public static String validateOffense(String offenseId, Map offenseMap,
			String courtId) {
		String theMsg = null;

		OffenseCodeResponseEvent offense = (OffenseCodeResponseEvent) offenseMap
				.get(offenseId);
		theMsg = validateOffense(offense, courtId, false);
		return theMsg;
	}

	public static String validateOffense(OffenseCodeResponseEvent offense,
			String courtId, boolean isCDIMsg) {
		String theMsg = null;
		if (offense == null || courtId == null || courtId.trim().equals("")) {
			return "error.managesupervisioncase.invalidoffensecode";
		} else {
			String courtOffenseLevel = UICommonSupervisionHelper
					.getCourtOffenseType(courtId);
			if (courtOffenseLevel.equals(offense.getLevel())
					|| courtOffenseLevel.equals(SupervisionConstants.BOTH)) {
				theMsg = null;
			} else {
				if(isCDIMsg){
					theMsg="error.managesupervisioncase.offensecodecdiinvalid";
				}
				else
					theMsg = "error.managesupervisioncase.offensecodelevelinvalid";
			}
		}
		return theMsg;
	}
	
	public static boolean isUserCSO(CSCDSupervisionStaffResponseEvent myEvent){
		boolean userIsCSO=false;
			if(myEvent!=null && myEvent.getPositionTypeId()!=null){
				if(myEvent.getPositionTypeId().equals(PDCodeTableConstants.STAFF_POSITION_TYPE_OFFICER)){
					if(myEvent.getJobTitleId()!=null){
						if(myEvent.getJobTitleId().equals(PDCodeTableConstants.STAFF_JOB_TITLE_CLO) || myEvent.getJobTitleId().equals(PDCodeTableConstants.STAFF_JOB_TITLE_CLOFLOATER)){
							userIsCSO=false;
						}
						else{
							userIsCSO=true;
						}
					}
					else{
						userIsCSO=true;
					}
				}
			}
		
		return userIsCSO;
	}
	
	public static boolean isUserCLO(CSCDSupervisionStaffResponseEvent myEvent){
		boolean userIsCLO=false;
			if(myEvent!=null && myEvent.getJobTitleId()!=null){
				if(myEvent.getJobTitleId().equals(PDCodeTableConstants.STAFF_JOB_TITLE_CLO) || myEvent.getJobTitleId().equals(PDCodeTableConstants.STAFF_JOB_TITLE_CLOFLOATER)){
					userIsCLO=true;
				}
			}
		
		return userIsCLO;
	}

	public static String getCourtOffenseType(String courtId) {
		String courtCategory = "";
		String courtNum = "";
		if (courtId != null) {
			courtCategory = courtId.substring(0, 3);
			courtCategory = courtCategory.trim();
			courtNum = courtId.substring(3);
		}
		String courtOffenseType = "";
		if (courtCategory != null && !courtCategory.equals("")) {
			if (courtCategory
					.equalsIgnoreCase(SupervisionConstants.COUNTY_CRIMINAL_COURT)) {
				courtOffenseType = SupervisionConstants.MISDEMEANOR;
			} else if (courtCategory
					.equalsIgnoreCase(SupervisionConstants.CRIMINAL_DISTRICT_COURT)) {
				courtOffenseType = SupervisionConstants.BOTH;
			} else if (courtCategory
					.equalsIgnoreCase(SupervisionConstants.FAMILY_COURT)
					|| courtCategory
							.equals(SupervisionConstants.CIVIL_DISTRICT_COURT)) {
				courtOffenseType = "";
			} else if (courtCategory
					.equalsIgnoreCase(SupervisionConstants.JUSTICE_OF_PEACE_COURT)) {
				courtOffenseType = SupervisionConstants.MISDEMEANOR;
			} else if (courtCategory
					.equalsIgnoreCase(SupervisionConstants.JUVENILE_COURT)) {
				courtOffenseType = SupervisionConstants.BOTH;
			} else if (courtCategory
					.equalsIgnoreCase(SupervisionConstants.OUT_OF_COUNTY_COURT)
					&& courtNum != null && !courtNum.equals("")) {
				if (courtNum.substring(courtNum.length() - 1).toUpperCase()
						.equals(SupervisionConstants.FELONY)) {
					courtOffenseType = SupervisionConstants.BOTH;
				} else {
					courtOffenseType = SupervisionConstants.MISDEMEANOR;
				}
			}
		}
		return courtOffenseType;
	}
	private static final String DAYS_TO_ADD = "DAYS_TO_ADD";
	/**
	 * @param codeValues (DAYS_TO_ADD codetable)
	 * @return List sorted by code instead of description
	 */
	public static List sortDaysToAddCodes(List codeList, String codeTableName){
		String xCode = "";
		Iterator iter = codeList.iterator();
		SortedMap map = new TreeMap();
		while(iter.hasNext()){
			CodeResponseEvent cre = (CodeResponseEvent) iter.next();
			if (codeTableName.equals(DAYS_TO_ADD)){
				xCode = "";
				if (cre.getCode() != null){
					xCode = cre.getCode();
					if (xCode.length() == 1)
						xCode = "0" + xCode;
				}
				map.put(xCode + " " , cre);
			}	
		}
		return new ArrayList(map.values());
	} 	
	
	public static Comparator assessmentComparator = new Comparator() {
		
		public int compare( Object firstAssessment, Object nextAssessment ) {
		  int result = 0;
		  
		  Date assessmentDate = ((AssessmentSummaryResponseEvent)firstAssessment).getAssessmentDate();
		  Date otherAssessmentDate = ((AssessmentSummaryResponseEvent)nextAssessment).getAssessmentDate();
		  if ((assessmentDate == null) && (otherAssessmentDate == null))
			  return 0;
		  else
			  if (assessmentDate == null)
				  return -1;
			  else
				  if (otherAssessmentDate == null)
					  return 1;				  
		  else	 
		  result = assessmentDate.compareTo(otherAssessmentDate);
		  
		  return result;
		}	
	};
}// END CLASS
