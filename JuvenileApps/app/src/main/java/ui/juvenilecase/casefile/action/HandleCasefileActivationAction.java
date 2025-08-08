/*
 * Created on Feb 23, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.casefile.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import messaging.casefile.GetCasefileForActivationEvent;
import messaging.casefile.SubmitCasefileForActivationEvent;
import messaging.casefile.UpdateJuvenileCasefileEvent;
import messaging.casefile.reply.CasefileForActivationResponseEvent;
import messaging.codetable.GetJuvenileDispositionCodeEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.SaveJuvJPOOfRecEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenile.reply.JuvenileJISResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetActiveCasefileReferralsEvent;
import messaging.juvenilecase.GetCasefileReferralsEvent;
import messaging.juvenilecase.GetJuvenileCasefilePetitionsEvent;
import messaging.juvenilecase.GetJuvenileCasefileReferralsEvent;
import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.JuvenileFamilyMemberViewResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilecase.reply.SearchResultsCountResponseEvent;
import messaging.juvenilewarrant.reply.JJSChargeResponseEvent;
import messaging.task.CreateTaskEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.PDJuvenileWarrantConstants;
import naming.PDTaskConstants;
import naming.TaskControllerServiceNames;
import naming.UIConstants;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.referral.JJSOffense;
import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileFamilyHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.JuvenileReferralHelper;
import ui.juvenilecase.casefile.form.CasefileActivationForm;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.JuvenilePhotoForm;
import ui.juvenilecase.helper.JuvenileCaseworkAlertsHelper;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class HandleCasefileActivationAction extends JIMSBaseAction
{

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	public void addButtonMapping(Map buttonMap)
	{
		buttonMap.put("button.link","link");
		buttonMap.put("button.activate","activate");
		buttonMap.put("button.taskList","taskList");
		buttonMap.put("button.cancel","cancel");
		buttonMap.put("button.next","next");
		buttonMap.put("button.back","back");
		buttonMap.put("button.casefileDetails", "casefileDetails");
	}

	public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
			  {
				CasefileActivationForm myForm=(CasefileActivationForm)aForm;
				myForm.clearAll();
				myForm.setAction(UIConstants.UPDATE);
				
				JuvenileCasefileForm casefileForm=UIJuvenileHelper.getJuvenileCasefileForm(aRequest,true);
				// Retrieve passed in casefile Number set in selectedValue
				try{
			//		  JuvenileCasefileForm casefileForm=UIJuvenileHelper.getJuvenileCasefileForm(aRequest,true);
						casefileForm.populateJuvenileCasefileForm(myForm.getCasefileID()); // load and populate the casefile header
						if(casefileForm==null || casefileForm.getCaseStatusId()==null){
							ActionMessage myError=new ActionMessage("error.casefileUnavailable",myForm.getCasefileID());
							ArrayList coll=new ArrayList();
							coll.add(myError);
							sendToErrorPage(aRequest,coll);
							return aMapping.findForward(UIConstants.CANCEL);
						}
						if(casefileForm!=null && !(casefileForm.getCaseStatusId().equalsIgnoreCase(UIConstants.CASEFILE_CASE_STATUS_PENDING)))
							myForm.setCasefileAlreadyActivated(true);
						else
							myForm.setCasefileAlreadyActivated(false);
						GetCasefileForActivationEvent myEvent=new GetCasefileForActivationEvent();
						
						myEvent.setSupervisionNumber(myForm.getCasefileID());
						// fire the event that retrieves the activation data
						// set activation data to the form
						// display page
						IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
						dispatch.postEvent(myEvent);

						CompositeResponse compositeResponse = (CompositeResponse) 
						dispatch.getReply();
		
						MessageUtil.processReturnException(compositeResponse);
						CasefileForActivationResponseEvent respEvent = (CasefileForActivationResponseEvent) MessageUtil.filterComposite(compositeResponse, CasefileForActivationResponseEvent.class);
						if(respEvent==null){// should never happen but if it does tell user and go back to original page
							//ActionMessage myError=new ActionMessage("error.commonWithMsg","Casefile was unable to be retrieved");
							ActionMessage myError=new ActionMessage("error.common");
							ActionMessage myError2=new ActionMessage("error.casefileMayNotBeActivated");
							ArrayList coll=new ArrayList();
							coll.add(myError);
							coll.add(myError2);
							sendToErrorPage(aRequest,coll);
							myForm.setAction(UIConstants.VIEW);
							return aMapping.findForward(UIConstants.CANCEL);
						}
						
						// SET THE RESPONSE EVENT TO THE FORM
						myForm.setDateOfBirth(respEvent.getDateOfBirth());	
						myForm.setJuvMasterStatusId(respEvent.getJuvenileMasterStatusId());
						myForm.setMaysi(UIUtil.getYesNo(respEvent.isMAYSINeeded(),true));
						
						if(casefileForm.getSupervisionCategoryId() != null && !casefileForm.getSupervisionCategoryId().equals("") &&
							    casefileForm.getSupervisionCategoryId().equalsIgnoreCase("AR")){ //US 106778
						    myForm.setRiskAnalysis(UIUtil.getYesNo(false,true));						    
						} else {
						    myForm.setRiskAnalysis(UIUtil.getYesNo(respEvent.isRiskAnalysis(),true));
						}
						
						myForm.setTitelIVcompleted(UIUtil.getYesNo(respEvent.isTitle4eNeeded(),true));
						myForm.setSupervisionEndDate(respEvent.getSupervisionEndDate());
						if(respEvent.getSupervisionEndDate() != null)
						{
							myForm.setSupervisionEndDateStr(
								DateUtil.dateToString(respEvent.getSupervisionEndDate(), UIConstants.DATE_FMT_1));
						}
						
						myForm.setSupervisionTypeId(respEvent.getSupervisionTypeId());
						String juvNumber=casefileForm.getJuvenileNum();
						loadJuvPic(aRequest,juvNumber);
						
				        GetJuvenileCasefileReferralsEvent casefileReferrelsEvent = new GetJuvenileCasefileReferralsEvent();
						casefileReferrelsEvent.setSupervisionNum( casefileForm.getSupervisionNum() );
						casefileReferrelsEvent.setJuvenileNum( casefileForm.getJuvenileNum() );

						dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
						dispatch.postEvent( casefileReferrelsEvent );

						CompositeResponse compResponse = (CompositeResponse)dispatch.getReply();
						MessageUtil.processReturnException( compResponse );
						Map dataMap = MessageUtil.groupByTopic( compResponse );
						if( dataMap != null )
						{
							myForm.setReferrals( new ArrayList() );
							Collection myReferralsList = myForm.getReferrals();
							List<String> filteredReferrals = new ArrayList<>();
							Collection<JuvenileCasefileReferralsResponseEvent> referrals = (Collection)
									dataMap.get( PDJuvenileCaseConstants.JUVENILE_CASEFILE_REFERRAL_TOPIC );
			//				myForm.setReferralList(referrals);
							if( referrals != null && referrals.size() > 0 )
							{
								for( JuvenileCasefileReferralsResponseEvent responseEvent : referrals )
								{
									if( responseEvent != null  &&  responseEvent.isReferralFound() )
									{
										CasefileActivationForm.Refferal myReferral = new CasefileActivationForm.Refferal();
										myReferral.setReferralNumber( responseEvent.getReferralNumber() );
										myReferral.setReferralSeverity(responseEvent.getReferralNumberWithSeverity());
										myReferral.setFinalDisposition(responseEvent.getFinalDisposition());
										myReferral.setRefSequenceNum(responseEvent.getRefSeqNum());
										if ( !filteredReferrals.contains(responseEvent.getReferralNumber()) ){
										    filteredReferrals.add(responseEvent.getReferralNumber());
										    myReferralsList.add( myReferral );
										}
									}
								}
							}
							myForm.setReferrals( myReferralsList );
						}
						//END SET THE RESPONSE EVENT TO THE FORM
						
						HttpSession session = aRequest.getSession();
						JuvenileBriefingDetailsForm juvenileBriefingForm = null;

						juvenileBriefingForm =  UIJuvenileHelper.getJuvBriefingDetailsForm(aRequest);
						
						if( juvenileBriefingForm == null ){
						    
							juvenileBriefingForm = new JuvenileBriefingDetailsForm();
							setProfileDetail(casefileForm.getJuvenileNum(), juvenileBriefingForm);
							session.setAttribute("juvenileBriefingDetailsForm", juvenileBriefingForm);
						}
						
				}
				catch (Exception e){
					ActionMessage myError=new ActionMessage("error.common");
					ActionMessage myError2=new ActionMessage("error.casefileMayNotBeActivated");
							ArrayList coll=new ArrayList();
							coll.add(myError);
							coll.add(myError2);
					sendToErrorPage(aRequest,coll);
					return aMapping.findForward(UIConstants.CANCEL);
				}
								
				if (myForm.getReferrals() != null && casefileForm.getJuvenileNum() != null) {
					getMaxSeverityOffense(myForm.getReferrals(), casefileForm.getJuvenileNum());
					getFinalDisposition(myForm.getReferrals(), casefileForm.getJuvenileNum());
				}
				
				//Restricted Access check for US 31029
				
				casefileForm.setRestrictedAccess(UIJuvenileHelper.checkRestrictedAcces(casefileForm.getJuvenileNum()));
				return aMapping.findForward("displaySuccess");
			  }
	
	/*
	 * Get the max severity level record for each Referral
	 * 
	 * @param referralList
	 * 
	 * @param juvenileNum
	 */
	private void getMaxSeverityOffense(Collection<CasefileActivationForm.Refferal> referrals,
			String juvenileNum) {
		// get petition with max severity level for each referral
		if (referrals != null) {
			for (CasefileActivationForm.Refferal referral : referrals) {
				// get petitions
				GetJuvenileCasefilePetitionsEvent pet = (GetJuvenileCasefilePetitionsEvent) EventFactory
						.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEPETITIONS);

				pet.setJuvenileNum(juvenileNum);
				pet.setReferralNum(referral.getReferralNumber());
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(pet);

				CompositeResponse response = (CompositeResponse) dispatch.getReply();
				Map map = MessageUtil.groupByTopic(response);

				Collection<JJSChargeResponseEvent> petitions = (Collection) map.get(PDJuvenileWarrantConstants.JJS_CHARGE_EVENT_TOPIC);
				int count = 0;
				if (petitions != null) {
					HashMap petitionsMap = new HashMap();
					Iterator<JJSChargeResponseEvent> petIter = petitions.iterator();
					while (petIter.hasNext()) {
						JJSChargeResponseEvent chgResp = petIter.next();
						if (count == 0 && !petIter.hasNext()) { 
							// if this is the while's first iteration and
							// there is *not* another entry in the HashMap
							String referralNum = referral.getReferralNumber();
							if (notNullNotEmptyString(chgResp.getLevelDegree())) {
								referral.setReferralNumber(referralNum + " - " + chgResp.getLevelDegree());
							}
							break;
						}

						if (petitionsMap == null || petitionsMap.isEmpty() || !petitionsMap.containsKey(chgResp.getLevelDegree())) {
							petitionsMap.put(chgResp.getLevelDegree(), chgResp);
						} else {
							JJSChargeResponseEvent tempResp = (JJSChargeResponseEvent) petitionsMap.get(chgResp.getLevelDegree());
							if (chgResp.getPetitionDate().compareTo(tempResp.getPetitionDate()) > 0) {
								petitionsMap.remove(tempResp.getLevelDegree());
								petitionsMap.put(chgResp.getLevelDegree(), chgResp);
							}
						}
						++count;
					} // while

					if (count != 0) { 
						// we're in here because level degree was never
						// appended to the referral number in the previous loop
						String ref = getMaxSeverity(petitionsMap, referral.getReferralNumber());
						referral.setReferralNumber(ref);
					}
				} else { // else petitions collection is null - get the offenses
					count = 0;
					Collection offenses = UIJuvenileCasefileClosingHelper.getOffenses(juvenileNum, referral.getReferralNumber());
					HashMap offenseMap = new HashMap();
					if (offenses != null) {
						Iterator<JJSOffenseResponseEvent> offIter = offenses.iterator();
						while (offIter.hasNext()) {
							JJSOffenseResponseEvent offResp = offIter.next();
							if (count == 0 && !offIter.hasNext()) { 
								// if this is the while's first iteration and
								// there is *not* another entry in the offenses
								// Collection
								String referralNum = referral.getReferralNumber();
								referral.setReferralNumber(referralNum + " - " + offResp.getCatagory());
								break;
							}

							if (offenseMap == null || offenseMap.isEmpty() || !offenseMap.containsKey(offResp.getCatagory())) {
								offenseMap.put(offResp.getCatagory(), offResp);
							} else {
								JJSOffenseResponseEvent tempResp = (JJSOffenseResponseEvent) offenseMap.get(offResp.getCatagory());
								if (offResp.getOffenseDate().compareTo(tempResp.getOffenseDate()) > 0) {
									offenseMap.remove(tempResp.getCatagory());
									offenseMap.put(offResp.getCatagory(),offResp);
								}
							}
							++count;
						} // while
					}

					if (count != 0) { 
						// we're in here because level degree was never
						// appended to the referral number in the previous loop
						String ref = getMaxSeverity(offenseMap, referral.getReferralNumber());
						referral.setReferralNumber(ref);
					}
				}

				checkReferralNumber(referral);
			} // for

			if (referrals.size() > 1) {
				Collections.sort((List) referrals);
			}
		}
	}

	/*
	 * @param offenseMap
	 * 
	 * @param referralNum
	 * 
	 * @return
	 */
	public String getMaxSeverity(HashMap offenseMap, String referralNum) {
		String severity = UIConstants.EMPTY_STRING;

		if (offenseMap != null && offenseMap.size() != 0) {
			if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_CF) != null) {
				severity = referralNum + " - CF";
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_F1) != null) {
				severity = referralNum + " - F1";
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_F2) != null) {
				severity = referralNum + " - F2";
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_F3) != null) {
				severity = referralNum + " - F3";
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_JF) != null) {
				severity = referralNum + " - JF";
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_MA) != null) {
				severity = referralNum + " - MI";
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_MB) != null) {
				severity = referralNum + " - MI";
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_MC) != null) {
				severity = referralNum + " - MI";
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_CO) != null) {
				severity = referralNum + " - MI";
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_SO) != null) {
				severity = referralNum + " - FC";
			} else if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_AC) != null) {
				severity = referralNum + " - AC";
			}
		}

		return severity;
	}

	/*
	 * given a referral number, ensure it's well-formed
	 */
	private void checkReferralNumber(CasefileActivationForm.Refferal referral) {
		String str = referral.getReferralNumber();
		int firstIndex = str.indexOf("-"), lastIndex = str.lastIndexOf("-");

		if (firstIndex != (-1)) { // found a first occurrence
			if (lastIndex != (-1) && (firstIndex != lastIndex)) { 
				// last occurrence found and both char offsets differ,
				// which means the number looks like, "1010 - JF - JF"
				String newNum = str.substring(0, (lastIndex - 1));
				referral.setReferralNumber(newNum);
			}
		}
	}
	
	
	private void getFinalDisposition(Collection<CasefileActivationForm.Refferal> referrals,
			String juvenileNum) {
		
		if (referrals != null) {
			for (CasefileActivationForm.Refferal referral : referrals) {
		
		      GetJuvenileDispositionCodeEvent courtDisp = (GetJuvenileDispositionCodeEvent) EventFactory
		      .getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);

		      if (referral.getFinalDisposition() != null && !referral.getFinalDisposition().equals("")) {
		    	  courtDisp.setCode(referral.getFinalDisposition());
		    	  CompositeResponse resp = postRequestEvent(courtDisp);
               
		    	  int count=0;
		    	  Collection juvDisp = MessageUtil.compositeToCollection(resp, JuvenileDispositionCodeResponseEvent.class  );
		    	  if (juvDisp != null){
		    		  Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvDisp.iterator();
		    		  while (juvCodeIter.hasNext()) {
		    			  JuvenileDispositionCodeResponseEvent dispResp = juvCodeIter.next();
		    			  String referralNum = referral.getReferralNumber();
		    			  String dispDesc = dispResp.getShortDesc();
		    			  referral.setReferralNumber(referralNum + " - " + dispDesc);
		    			  ++count;
		    		  }
		    	  }
               }
			}
		}
	}
	/**
	 * 		  
	 * @param juvenileNum
	 * @param form
	 */
	   private void setProfileDetail(String juvenileNum, JuvenileBriefingDetailsForm form)
	    {
		GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

		reqProfileMain.setJuvenileNum(juvenileNum);
		reqProfileMain.setFromProfile(form.getFromProfile());
		CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
		JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);

		form.setJisInfo(new JuvenileJISResponseEvent());
		if (juvProfileRE != null)
		{
		    form.setProfileDetail(juvProfileRE);
		    form.setProfileDescriptions();

		    if (juvProfileRE.getDateOfBirth() != null)
		    { // Get age based on
		      // year
			int age = UIUtil.getAgeInYears(juvProfileRE.getDateOfBirth());
			if (age > 0)
			{
			    form.setAge(String.valueOf(age));
			}
			else
			{
			    form.setAge(UIConstants.EMPTY_STRING);
			}
		    }
		    Collection jisResps = juvProfileRE.getJisInfo();
		    if (jisResps != null)
		    {
			Collections.sort((List) jisResps);
			Iterator jisIter = jisResps.iterator();
			if (jisIter.hasNext())
			{
			    JuvenileJISResponseEvent jis = (JuvenileJISResponseEvent) jisIter.next();
			    form.setJisInfo(jis);
			}
		    }

		    form.setInMentalHealthServices(juvProfileRE.isMentalHealthServices());
		  //U.S 88526
	    	if ( juvProfileRE.getHispanic() != null )
	    	{
	    	    if ( juvProfileRE.getHispanic().equalsIgnoreCase("Y"))
	    	    {
	    		form.setHispanic(UIConstants.YES_FULL_TEXT);
	    	    }
	    	    else
	    	    {
	    		form.setHispanic(UIConstants.NO_FULL_TEXT);
	    	    }
	    	}
	    	else
	    	{
	    	    form.setHispanic(UIConstants.EMPTY_STRING);
	    	}
		}
	    }
			  
	public ActionForward activate(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
	  {
		CasefileActivationForm myForm=(CasefileActivationForm)aForm;
		JuvenileCasefileForm casefileForm=UIJuvenileHelper.getJuvenileCasefileForm(aRequest,true);
		CasefileForActivationResponseEvent respEvent=null;
		try{
		    	
		    	
			SubmitCasefileForActivationEvent myEvent=new SubmitCasefileForActivationEvent();
			myEvent.setSupervisionNumber(myForm.getCasefileID());
			myEvent.setSupervisionEndDate(myForm.getSupervisionEndDate());
			if ( myForm.getReferrals() !=  null 
		    		&& myForm.getReferrals().size() > 0 ){
			    myEvent.getReferrals().addAll(myForm.getReferrals());
		    	  
		    	}
		//	if (myForm.getCourtOrderedProbationStartDateStr() != null) {
		//		myEvent.setCourtOrderedProbationStartDate(DateUtil.stringToDate(myForm.getCourtOrderedProbationStartDateStr(), DateUtil.DATE_FMT_1));
		//	}
			String controlRef = "";
			if (myForm.getControllingReferral() != null){
			    controlRef = myForm.getControllingReferral();
			    if (controlRef.length() > 4) {
			    	controlRef = controlRef.substring(0,4);					    
			    }
			}
			//task 185084
                	    String supCategory = "";
                	    String juvNum = casefileForm.getJuvenileNum();
                	    String casefileId = casefileForm.getSupervisionNum();
                	    //Getting supervision category from supervision type Id
                	    if(casefileForm.getSupervisionTypeId() != null && !"".equals(casefileForm.getSupervisionTypeId()))
                		    supCategory = UIJuvenileCaseworkHelper.getSupCatFromType(casefileForm.getSupervisionTypeId());                
                	    
                	    Map<Integer, Integer> activeReferralSeverityMap = new HashMap();
                	    if (supCategory != null && (supCategory.equals("AD") || supCategory.equals("PP")))
                	    {
                		List<JuvenileProfileReferralListResponseEvent> openReferralsOnlyList = new ArrayList<JuvenileProfileReferralListResponseEvent>();
                		GetCasefileReferralsEvent disEvent = (GetCasefileReferralsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETCASEFILEREFERRALS);
                		disEvent.setJuvenileNum(juvNum);
                		disEvent.setCasefileId(casefileId);
                		openReferralsOnlyList = (List<JuvenileProfileReferralListResponseEvent>) MessageUtil.postRequestListFilter(disEvent, JuvenileProfileReferralListResponseEvent.class);
                		if (openReferralsOnlyList.size() > 0)
                		{
                		    Iterator refListIter = openReferralsOnlyList.iterator();
                		    JuvenileProfileReferralListResponseEvent evt;
                		    JJSOffense offse;
                		    String offnse = null;
                		    while (refListIter.hasNext())
                		    {
                			evt = (JuvenileProfileReferralListResponseEvent) refListIter.next();
                			if (evt.getOffenses() != null)
                			{
                			    Iterator<JJSOffense> offItr = evt.getOffenses().iterator();
                			    while (offItr.hasNext())
                			    {
                				offse = offItr.next();
                				offnse = offse.getOffenseCodeId();
                				JuvenileOffenseCode offCode = JuvenileOffenseCode.find("offenseCode", offnse);
                				if (offCode.getSeverity() != null && !offCode.getSeverity().isEmpty())
                				{
                				    activeReferralSeverityMap.put(Integer.parseInt(evt.getReferralNumber()), Integer.parseInt(offCode.getSeverity()));
                				}
                			    }
                			}
                		    }
                		    if(activeReferralSeverityMap!=null && activeReferralSeverityMap.size()>0)
                		    {                		
                        		    Comparator<Entry<Integer, Integer>> firstwithvaluessecoundwiththekeys = Entry.<Integer, Integer>comparingByValue().reversed().thenComparing(Entry.comparingByKey());
                        		    Map<Integer, Integer> lhmap =
                        			    activeReferralSeverityMap.entrySet().stream().sorted(firstwithvaluessecoundwiththekeys)
                        			                .collect(Collectors.toMap(Entry::getKey,
                        			                        Entry::getValue,
                        			                        (a, b) -> a,      // merge function
                        			                        LinkedHashMap::new));
                        		    if(lhmap.entrySet()!=null)
                        			controlRef = lhmap.entrySet().stream().findFirst().get().getKey().toString();
                		    }
                		}
                	    }
			//
			myEvent.setCasefileControllingReferralId(controlRef);
			
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(myEvent);
			CompositeResponse compositeResponse = (CompositeResponse) 
			dispatch.getReply();

			MessageUtil.processReturnException(compositeResponse);
			respEvent = (CasefileForActivationResponseEvent) MessageUtil.filterComposite(compositeResponse, CasefileForActivationResponseEvent.class);
			if(respEvent==null){// should never happen but if it does tell user and go back to original page
				ActionMessage myError=new ActionMessage("error.common");
				ArrayList coll=new ArrayList();
				coll.add(myError);
				myError=new ActionMessage("error.casefileMayNotBeActivated");
				coll.add(myError);
				sendToErrorPage(aRequest,coll);
				return aMapping.findForward("displaySuccess");
			}
			
			//add a trait record to T_JCTRAITS table if juvenile associated with this casefile has a RealDOB in MS_Juvenile - US 179660
			if(casefileForm != null && casefileForm.getJuvenileNum() != null && !"".equals(casefileForm.getJuvenileNum()))
			{
			    JuvenileReferralHelper.insertRealDobTraitOnCaseFileActivation(casefileForm.getJuvenileNum());
			}
			
		}
		catch(Exception e){
			ActionMessage myError=new ActionMessage("error.common");
			ArrayList coll=new ArrayList();
			coll.add(myError);
			myError=new ActionMessage("error.casefileMayNotBeActivated");
			coll.add(myError);
			sendToErrorPage(aRequest,coll);
			return aMapping.findForward("displaySuccess");
		}

        GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent) EventFactory
                .getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

        requestEvent.setJuvenileNum(casefileForm.getJuvenileNum());

        CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);

        JuvenileProfileDetailResponseEvent detail = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(replyEvent,
                JuvenileProfileDetailResponseEvent.class);
        
        if(detail!=null){
            //myForm.setJuvMasterStatusId(detail.getStatus());
            //<Kishore>JIMS200056999 : MJCW - Casefile Activation Confirmation missing Juvenile Master Status
        	myForm.setJuvMasterStatusId(detail.getStatusId());
        }
        
		try{	
			
			// SET THE RESPONSE EVENT TO THE FORM
			//myForm.setJuvMasterStatusId(respEvent.getJuvenileMasterStatusId());
			casefileForm.setCaseStatusId(respEvent.getSupervisionStatusId());
			casefileForm.setExpectedSupervisionEndDate(respEvent.getSupervisionEndDate());
			//END SET THE RESPONSE EVENT TO THE FORM
			myForm.setAction(UIConstants.CONFIRM);
				// Adding record in activity table
				UIJuvenileHelper.createActivity(myForm.getCasefileID(), ActivityConstants.ACTIVATED_A_CASEFILE, "");				
				
			/*  code for sending Alerts to CLM & PO based upon supervisionType */
				
				JuvenileCaseworkAlertsHelper helper = new JuvenileCaseworkAlertsHelper();
				String superTypeID = casefileForm.getSupervisionTypeId() ;
				String supCatId = "";
				if (superTypeID != null) {
					supCatId = UIJuvenileCaseworkHelper.getSupCatFromType(superTypeID);
				}
				
				//Creating Task after activating the case file.
				String subject = "";
				//for supervision category of Post Adjudication Community
				if( StringUtils.equalsIgnoreCase( supCatId, UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM ))
				{
					subject = "Schedule Juvenile for Home-Diagnostic visit # " + casefileForm.getJuvenileNum();
					String identityType = "jpo";
					helper.scheduleOverdueInterviewTask(casefileForm.getProbationOfficerLogonId(),PDTaskConstants.MJCW_JPO_OVERDUE_INTERVIEWNOTIFICATION,subject,myForm,48,casefileForm.getJuvenileNum(),respEvent.getActivationDate(),casefileForm.getProbationOfficerId(),identityType);
					identityType = "clm";
					helper.scheduleOverdueInterviewTask(casefileForm.getCaseloadManagerId(),PDTaskConstants.MJCW_CLM_OVERDUE_INTERVIEWNOTIFICATION,subject,myForm,48,casefileForm.getJuvenileNum(),respEvent.getActivationDate(),casefileForm.getProbationOfficerId(),identityType);
				}
				//for supervision category of Post Adjudication Community
				if( StringUtils.equalsIgnoreCase( supCatId, UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM ))
				{
						subject = "Schedule Juvenile for Home-Diagnostic visit # " + casefileForm.getJuvenileNum();
						String identityType = "jpo";
						helper.scheduleOverdueInterviewTask(casefileForm.getProbationOfficerLogonId(),PDTaskConstants.MJCW_JPO_OVERDUE_COMMUNITYSUPERVISION,subject,myForm,480,casefileForm.getJuvenileNum(),respEvent.getActivationDate(),casefileForm.getProbationOfficerId(),identityType);
						identityType = "clm";
						helper.scheduleOverdueInterviewTask(casefileForm.getCaseloadManagerId(),PDTaskConstants.MJCW_CLM_OVERDUE_COMMUNITYSUPERVISION,subject,myForm,480,casefileForm.getJuvenileNum(),respEvent.getActivationDate(),casefileForm.getProbationOfficerId(),identityType);
				}
				
				
				//for supervision category of Post Adjudication Community and Deferred Adjudication
				if( StringUtils.equalsIgnoreCase( supCatId, UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM ) || 
					StringUtils.equalsIgnoreCase( supCatId, UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ ))
				{
					helper.sendPOPostCourtInterviewNotification(respEvent , casefileForm);
				}
				/*
				 * Generating Notices for all supervisionTypes 
				 */
				helper.sendPOMaysi14daysNotification(respEvent , casefileForm);
				helper.sendPOMaysi29daysNotification(respEvent , casefileForm);
				//taken out as per User Story 27325
				//helper.sendPORiskAnalysis14daysNotification(respEvent , casefileForm);
				helper.sendPORiskAnalysis29daysNotification(respEvent , casefileForm);
				helper.sendPOTitleIVAssesmentOverdueNotification(respEvent , casefileForm);
				
				//Caseplan Pending Notice
				//SendCaseplanNotificationEventEvent
				helper.sendPOCaseplanPendingNotification(respEvent , casefileForm);
				
				// Generate notices for casefileclosing to JPO & CLM.
				UpdateJuvenileCasefileEvent updateEvent = new UpdateJuvenileCasefileEvent();
				updateEvent.setSupervisionNumber(myForm.getCasefileID());
				updateEvent.setSupervisionEndDate(myForm.getSupervisionEndDate());

				if( StringUtils.equalsIgnoreCase( supCatId, UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_COM ) || 
						StringUtils.equalsIgnoreCase( supCatId, UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES ) || 
						StringUtils.equalsIgnoreCase( supCatId, UIConstants.CASEFILE_SUPERVISION_CAT_DEFERRED_ADJ ))
				{
					helper.sendPOSupervisionDueExpirePrior30daysNotification( updateEvent, casefileForm );
					helper.sendPOSupervisionDueExpirePrior60daysNotification( updateEvent, casefileForm );
				}
				
				if( myForm.getSupervisionEndDate() != null )
				{
					helper.sendPOSupervisionDueExpireAfter24hoursNotification( updateEvent, casefileForm );
					helper.sendCLMSupervisionDueExpireAfter24hoursNotification( updateEvent, casefileForm );
				}
				//U.S #24194
				if( StringUtils.equalsIgnoreCase( supCatId, UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES )){ //At this point of time, the casefile status should be active.
					
					boolean dateDiffFlg= false; //added for #24194
					if( casefileForm.getExpectedSupervisionEndDate() != null )
					{
						//added for #24194 - calculate the no of days between current system date and expectedEndate 
						Calendar c1=Calendar.getInstance();
					    c1.setTime(DateUtil.getCurrentDate());
					    Calendar c2=Calendar.getInstance();
					    c2.setTime(casefileForm.getExpectedSupervisionEndDate());
					 
					    Date currentDate=c1.getTime(); 
					    Date expectedEndDate=c2.getTime();
								     
					    long diff=currentDate.getTime() - expectedEndDate.getTime(); //currentDate - expectedEndDate
					    int noofdays=Math.abs((int)(diff/(1000*24*60*60))); //Day in millisecs.
					    if(noofdays <= 30){
							dateDiffFlg = true;
						}
						//added for #24194
					}
					if( StringUtils.equalsIgnoreCase( supCatId, UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES) && dateDiffFlg && casefileForm.getCaseStatusId().equalsIgnoreCase(UIConstants.CASEFILE_CASE_STATUS_ACTIVE)){
						helper.sendPostAdjudicationResidential30daysEmailNotification(updateEvent, casefileForm); //30 day notice = Post Adjudication Residential (US #24194)
					}
				}
				// End Sending Alerts
			
				
				// Check if this is the only casefile for this juvenile
				String juvenileNum=casefileForm.getJuvenileNum();
				if(juvenileNum!=null && !juvenileNum.trim().equals("")){
					SearchJuvenileCasefilesEvent search =
						(SearchJuvenileCasefilesEvent) 
						EventFactory.getInstance(JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES);
					search.setSearchType(PDJuvenileCaseConstants.SEARCH_JUVENILE_NUMBER);
					search.setJuvenileNum(juvenileNum);
					IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
					dispatch.postEvent(search);
					
					CompositeResponse response = (CompositeResponse) dispatch.getReply();
					MessageUtil.processReturnException(response);
					int results=0;
					SearchResultsCountResponseEvent count =
						(SearchResultsCountResponseEvent) MessageUtil.filterComposite(response, SearchResultsCountResponseEvent.class);
					if (count != null)
					{
						results = count.getNumberOfResults();
					}
					// if so then proceed
					if(results == 1){
					// get active constellation
						Collection myFamilyMembers= UIJuvenileFamilyHelper.getAllJuvenilesFamilyMembers(juvenileNum);
						if(myFamilyMembers!=null && myFamilyMembers.size()>0){				
							String lastBit=" for Juvenile # " + juvenileNum;
							Iterator myFamMemIter=myFamilyMembers.iterator();
							StringBuffer myBuf=new StringBuffer();
							myBuf.append("Update Family Member Member(s)#: ");
							boolean flag=true;
							boolean currentFlag=true;
							while(myFamMemIter.hasNext()){
								JuvenileFamilyMemberViewResponseEvent myRespEvt=(JuvenileFamilyMemberViewResponseEvent)myFamMemIter.next();
								
								if(myRespEvt!=null && myRespEvt.getMemberNum()!=null && !(myRespEvt.getMemberNum().equals(""))){
									if((currentFlag && flag) || (!(currentFlag) && !(flag))){
										
									}
									else{
										myBuf.append(", ");
									}
									myBuf.append(myRespEvt.getMemberNum());
									
									if(flag)
										flag=false;
									else
										flag=true;
									
								}
							}
							myBuf.append(lastBit);
							CreateTaskEvent createTask = (CreateTaskEvent) EventFactory.getInstance(TaskControllerServiceNames.CREATETASK);	        
						    createTask.setTaskTopic(PDTaskConstants.CASEFILEACTIVATE_FAMILY_UPDATE);
						    createTask.setOwnerId(casefileForm.getProbationOfficerLogonId());
						    createTask.setTaskSubject(myBuf.toString());
							createTask.addTaskStateItem("submitAction","Link");
							createTask.addTaskStateItem("juvnum",casefileForm.getJuvenileNum());
							MessageUtil.postRequest(createTask);
						}
						
						
					}
					List<JuvenileCasefileSearchResponseEvent> casefiles = MessageUtil.compositeToList(response, JuvenileCasefileSearchResponseEvent.class);
					CodeResponseEvent codeResp = UIJuvenileCaseworkHelper.setJPOOfRecord(casefiles);   		 
		    		 //persist to juvenile record
					SaveJuvJPOOfRecEvent saveJPO = (SaveJuvJPOOfRecEvent)EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVJPOOFREC);
					saveJPO.setJuvenileNum(juvenileNum);
					saveJPO.setJpoId(codeResp.getCode());
					IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
					dispatch1.postEvent(saveJPO);
					ErrorResponseEvent errRespEvt = (ErrorResponseEvent)MessageUtil.filterComposite(response, ErrorResponseEvent.class);
					if(errRespEvt != null)
					{
						ActionErrors errors = new ActionErrors();
						errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( errRespEvt.getMessage() ) );
						saveErrors( aRequest, errors );
						return aMapping.findForward( UIConstants.SEARCH_FAILURE );
					}
				}
				

		}
		catch (Exception e){
			
			sendToErrorPage(aRequest,"error.generic","Error: Casefile was activated but notices and tasks may not have been sent.");
		}
		
		return aMapping.findForward(UIConstants.CONFIRM_SUCCESS);
	  }


	    /**
	     * @param aMapping
	     * @param aForm
	     * @param aRequest
	     * @param aResponse
	     * @return ActionForward
	     * @roseuid 447F49960111
	     */
	    public ActionForward casefileDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
	    {
		CasefileActivationForm myForm=(CasefileActivationForm)aForm;
		
		HttpSession session = aRequest.getSession();
		JuvenileCasefileForm casefileForm = new JuvenileCasefileForm();
		if (myForm.getCasefileID() != null && !myForm.getCasefileID().equals(""))
		    casefileForm.setSupervisionNum(myForm.getCasefileID());
		else
		    casefileForm.setSupervisionNum(myForm.getCasefileID());
		session.setAttribute("juvenileCasefileForm", casefileForm);
		return (aMapping.findForward("details"));

	    }
	    
	private void loadJuvPic(HttpServletRequest aRequest,String juvNumber){
			JuvenilePhotoForm myPhotoForm=UIJuvenileHelper.getJuvPhotoForm(aRequest,true);
			myPhotoForm.setJuvenileNumber(juvNumber);
	}

	public ActionForward taskList(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
							  {
									 return aMapping.findForward(UIConstants.CANCEL);
							  }

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
	  {
			 return aMapping.findForward(UIConstants.CANCEL);
	  }
	
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
	  {
			 return aMapping.findForward(UIConstants.BACK);
	  }

    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
	  {
		CasefileActivationForm myForm=(CasefileActivationForm)aForm;
		myForm.setAction(UIConstants.SUMMARY);
		return aMapping.findForward(UIConstants.NEXT);
	  }				  
	/**
			* @param aRequest
			*/
		   private void sendToErrorPage(HttpServletRequest aRequest, Collection aActionErrors)
		   {
				ActionErrors errors = new ActionErrors();
				if(aActionErrors!=null && aActionErrors.size()>0){
					Iterator i=aActionErrors.iterator();
					while(i.hasNext()){
						ActionMessage error=(ActionMessage)i.next();
						errors.add(ActionErrors.GLOBAL_MESSAGE,error);
					}
				   saveErrors(aRequest, errors);
				}
		   }
		   
		   /*
			 * given a String, return true if it's not null and not empty
			 * @param str
			 * @return
			 */
			private boolean notNullNotEmptyString(String str) {
				return (str != null && (str.trim().length() > 0));
			}

			/*
			 * given a Collection, return true if it's null or empty
			 * @param coll
			 * @return
			 */
			private boolean nullOrEmptyCollection(Collection coll) {
				return (coll == null || (coll.size() < 1));
			}

}
