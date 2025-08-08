/*
 * Created on Sep 19, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.casefile.GetJournalEntriesEvent;
import messaging.codetable.criminal.reply.JuvenileCourtDecisionCodeResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByDetRecResultEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.referral.GetJuvenileProfileReferralDetailsEvent;
import messaging.referral.GetJuvenileProfileReferralListEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.PDJuvenileConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.codetable.SimpleCodeTableHelper;
import pd.codetable.criminal.JuvenileActivityTypeCode;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.casefile.Activity;
import pd.juvenilecase.casefile.ActivityHelper;
import pd.juvenilecase.referral.JJSOffense;
import ui.action.JIMSBaseAction;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.AssignedReferralsForm;
import ui.juvenilecase.form.JuvenileMemberForm.MemberContact;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.form.PetitionDetailsForm;
import ui.juvenilecase.form.TransferredOffenseForm;
import ui.juvenilecase.mentalhealth.form.JOTChargeReportBean;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;


/**
 * @author jjose
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayJuvenileProfileReferralListAction extends JIMSBaseAction
{
    public final static String HEADER_FORM = "juvenileProfileHeader";
    private static HashMap<Integer, String> courtDecisions;
    
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.referrals", "displayReferralList");
		keyMap.put("button.facility", "getFacility");
		keyMap.put("button.transfer", "transferred");
		keyMap.put("button.addMoreTransferredOffenses", "addMoreTransOffenses");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.print","print");
		keyMap.put("button.view","displayFacilityAdmitRecord");
		keyMap.put("button.link","link");
		keyMap.put("button.vop","vop");
		keyMap.put("button.vopDetailsAdd","addVOPDetails");
		
	}
	
	/**
	 * displayReferralList
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward displayReferralList(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileProfileForm myJuvForm = (JuvenileProfileForm)
				UIJuvenileHelper.getHeaderForm(aRequest);
		AssignedReferralsForm myRefForm = (AssignedReferralsForm)aForm;
		String juvenileNum = myJuvForm.getJuvenileNum();
		if(juvenileNum == null){
		    juvenileNum = aRequest.getParameter("juvenileNum");
		}

		myRefForm.clear();
		myRefForm.setReferralList(null); //added for VOP BUG # 03/13/2024
		if( myJuvForm != null )
		{
			myRefForm.setJuvenileNum(myJuvForm.getJuvenileNum());
		}

		UIJuvenileHelper.setJuvenileBehaviorHistoryForm(aRequest, juvenileNum);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		// Send PD Request Event
		GetJuvenileProfileReferralDetailsEvent event = (GetJuvenileProfileReferralDetailsEvent)
				EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEPROFILEREFERRALDETAILS);
		event.setJuvenileNum(myRefForm.getJuvenileNum());
		dispatch.postEvent(event);

		// Get PD Response Event
		CompositeResponse response = (CompositeResponse)dispatch.getReply();
		// Perform Error handling
		MessageUtil.processReturnException(response);

		Map dataMap = MessageUtil.groupByTopic(response);
		if( dataMap != null )
		{
			Collection referrals = (Collection)dataMap.get(PDJuvenileConstants.JUVENILE_PROFILE_REFERRAL_LIST_TOPIC);
			if( referrals != null  &&  referrals.size() > 0 ) //changes for HOT Fix Bug 88261
			{
			    if(referrals.size() > 1 ){
				//Collections.sort((List)referrals); 
			    	//Bug #80122
			    Collections.sort((List<JuvenileProfileReferralListResponseEvent>) referrals, Collections.reverseOrder(new Comparator<JuvenileProfileReferralListResponseEvent>() {
				@Override
				public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
				{
				    if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
					return evt1.getReferralNumber().compareTo(evt2.getReferralNumber());
				    else
					return -1;
				}
			    }));
			    }
			    
/*				Iterator<JuvenileProfileReferralListResponseEvent> referralIterator = referrals.iterator();
				while (referralIterator.hasNext())
				{
				    JuvenileProfileReferralListResponseEvent referral = referralIterator.next();
				    Collection<JJSOffense> offenses = referral.getOffenses();
				    if (offenses != null)
				    {
					Iterator<JJSOffense> offenseItr = offenses.iterator();				
					while (offenseItr.hasNext())
					{
					    JJSOffense offense = offenseItr.next();
					    
					    referral.setOffenseCategory(offense.getCatagory());
					    
					    if (offense.getSequenceNum().equalsIgnoreCase("1"))
					    {
						referral.setOffense(offense.getOffenseCodeId());
						referral.setOffenseDesc(offense.getOffenseDescription());
						break;
					    }
					    break;
					}
				    }
				    //for each referral get the casefiles, then find the supervision Category, Supervision Type of the most recent casefile assign /highest seq Num - confirmed with Carla 7/17/2018 [email]
				    JuvenileProfileReferralListResponseEvent refCasefileList = JuvenileFacilityHelper.getAllCasefilesForJuvNumRefNum(juvenileNum, referral.getReferralNumber());
				    Collection<JuvenileCasefileReferral> casefileReferrals = refCasefileList.getCasefileReferrals();

				    Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
					@Override
					public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
					{
					    if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
						return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());
					    else
						return -1;
					}
				    }));
				    Iterator<JuvenileCasefileReferral> caseFileRefItr = casefileReferrals.iterator();
				    while (caseFileRefItr.hasNext())
				    {
					JuvenileCasefileReferral caseFileReferral = caseFileRefItr.next();
					referral.setSupervisionCategoryId(caseFileReferral.getSupervisionCat());
					referral.setSupervisionType(caseFileReferral.getSupervisionType());
					referral.setSupervisionTypeId(caseFileReferral.getSupervisionTypeCd());
					referral.setSupervisionCategory(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, referral.getSupervisionCategoryId()));
					// referral.setJpoId(caseFileReferral.getOfficerID());
					OfficerProfileResponseEvent officerProfileResponse = JuvenileDistrictCourtHelper.getOfficerUserId(caseFileReferral.getOfficerID());
					if (officerProfileResponse != null)
					{
					    referral.setJpoId(officerProfileResponse.getUserId());
					    String officerFullName = officerProfileResponse.getLastName() + ", " + officerProfileResponse.getFirstName();
					    referral.setJpo(officerFullName);
					}
					break;
				    }
				}*/
/*			Iterator<JuvenileProfileReferralListResponseEvent> referralIterator = referrals.iterator();
			while (referralIterator.hasNext())
			{
			    JuvenileProfileReferralListResponseEvent referral = referralIterator.next();
			    Collection<JJSOffense> offenses = referral.getOffenses();
			    
			    if (offenses != null)
			    {
				Iterator<JJSOffense> offenseItr = offenses.iterator();
				List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenses = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(juvenileNum);
				
				while (offenseItr.hasNext())
				{
				    JJSOffense offense = offenseItr.next();
				    JuvenileOffenseCodeResponseEvent offenseCode = JuvenileReferralHelper.getOffenseCd(offense.getOffenseCodeId());
				    if (transferredOffenses !=null
		    				   && transferredOffenses.size() > 0 ){
 		    			   if ( "TRNDSP".equals(offenseCode.getOffenseCode()) 
 		    				   || "TRNSIN".equals(offenseCode.getOffenseCode())
 		    				   || "REGION".equals(offenseCode.getOffenseCode())
 		    			           || "ISCOIN".equals(offenseCode.getOffenseCode())
 		    			       ){
 		    			       for ( JuvenileCasefileTransferredOffenseResponseEvent transferredOffense : transferredOffenses ) {
 		    				   if ( referral.getReferralNumber().equals(transferredOffense.getReferralNum()) ) {
 		    				       offenseCode.setLongDescription(transferredOffense.getOffenseShortDesc() + "-" + transferredOffense.getCategory());
 		    				       offenseCode.setShortDescription(transferredOffense.getOffenseShortDesc() + "-" + transferredOffense.getCategory());
 		    				   }
 		    			       }
 		    			   }
				    }
				    
				    referral.getOffenseCodes().add(offenseCode);
				    
				    if (offense.getSequenceNum().equalsIgnoreCase("1"))
				    {
					referral.setOffense(offense.getOffenseCodeId());
					referral.setOffenseDesc(offense.getOffenseDescription());
					
				    }
				    
				}
			    }
			    //for each referral get the casefiles, then find the supervision Category, Supervision Type of the most recent casefile assign /highest seq Num - confirmed with Carla 7/17/2018 [email]
			    JuvenileProfileReferralListResponseEvent refCasefileList = JuvenileFacilityHelper.getAllCasefilesForJuvNumRefNum(juvenileNum, referral.getReferralNumber());
			    Collection<JuvenileCasefileReferral> casefileReferrals = refCasefileList.getCasefileReferrals();

			    Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
				@Override
				public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
				{
				    if (evt1.getRefSeqNum() != null && evt2.getRefSeqNum() != null)
				    {
					 int seq1 = Integer.parseInt(evt1.getRefSeqNum()); //Bug #91993
					    int seq2 = Integer.parseInt(evt2.getRefSeqNum());
					    Integer seq1Int = new Integer(seq1);
					    Integer seq2Int = new Integer(seq2);
					return seq1Int.compareTo(seq2Int);					
				    }
				    else
					return -1;
				}
			    }));
			    Iterator<JuvenileCasefileReferral> caseFileRefItr = casefileReferrals.iterator();
			    while (caseFileRefItr.hasNext())
			    {
				JuvenileCasefileReferral caseFileReferral = caseFileRefItr.next();
				referral.setSupervisionCategoryId(caseFileReferral.getSupervisionCat());
				referral.setSupervisionType(caseFileReferral.getSupervisionType());
				referral.setSupervisionTypeId(caseFileReferral.getSupervisionTypeCd());
				referral.setSupervisionCategory(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, referral.getSupervisionCategoryId()));
				// referral.setJpoId(caseFileReferral.getOfficerID());
				OfficerProfileResponseEvent officerProfileResponse = JuvenileDistrictCourtHelper.getOfficerUserId(caseFileReferral.getOfficerID());
				if (officerProfileResponse != null)
				{
				    referral.setJpoId(officerProfileResponse.getUserId());
				    String officerFullName = officerProfileResponse.getLastName() + ", " + officerProfileResponse.getFirstName();
				    referral.setJpo(officerFullName);
				}
				break;
			    }
			}*/
			
			//Filter admin referrals from referrals then add them to admin referrals List
			Collection<JuvenileProfileReferralListResponseEvent> children = null;
			HashMap<String,JuvenileProfileReferralListResponseEvent> map = new HashMap<String,JuvenileProfileReferralListResponseEvent>();
			Iterator refsIter = referrals.iterator();
			while( refsIter.hasNext()){
			    
			    JuvenileProfileReferralListResponseEvent obj = (JuvenileProfileReferralListResponseEvent) refsIter.next();
			    
			    if (Integer.valueOf( obj.getReferralNumber()) > 1009 && Integer.valueOf( obj.getReferralNumber()) % 10 != 0 ){
				
				map.put(obj.getReferralNumber(), obj);
				refsIter.remove();
				referrals.remove(obj);				
			    }
			}
			
			for (Map.Entry<String,JuvenileProfileReferralListResponseEvent> mapElement : map.entrySet()) {
		            
			    String key = mapElement.getKey();
       
		            StringBuffer sb = new StringBuffer(key.substring(0, 3)).append(0);
		            String refNum = sb.toString();

		            for (Iterator<JuvenileProfileReferralListResponseEvent> iter = referrals.iterator(); iter.hasNext();) {

		        	JuvenileProfileReferralListResponseEvent obj = iter.next();
		        	
		        	if( refNum.equals( obj.getReferralNumber())){
		        	    
		        	    obj.getAdminReferrals().add(mapElement.getValue());
		        	    break;		        	    
		        	}
		            }		            
			}			
			
			myRefForm.setReferralList(referrals);
			} //parenthesis moved for BUG 88007
		}
		
		aRequest.getSession().setAttribute(
				PDJuvenileCaseConstants.JUVENILE_HISTORY_PAGE,
				PDJuvenileCaseConstants.JUVENILE_PROFILE);
		
		aRequest.getSession().setAttribute(
				PDJuvenileCaseConstants.JUVENILE_HISTORY,
				PDJuvenileCaseConstants.JUVENILE_REFERRAL);

		return( aMapping.findForward(UIConstants.SUCCESS) );
	}
	
	/**
	 * displayReferralList
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward link(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileProfileForm myJuvForm = (JuvenileProfileForm)
				UIJuvenileHelper.getHeaderForm(aRequest);
		
		AssignedReferralsForm myRefForm = (AssignedReferralsForm)aForm;		
		
		String juvenileNum = aRequest.getParameter("juvnum");
		String actionType = aRequest.getParameter("testAction");//for process referral PopUp
		if(juvenileNum==null)
			juvenileNum = myJuvForm.getJuvenileNum();
		else
		{
		    if(myJuvForm==null)
			myJuvForm = new JuvenileProfileForm();
			myJuvForm.setJuvenileNum(juvenileNum);
		}

		//myRefForm.clear();
		myRefForm.setReferralPopList(new ArrayList());
		if( myJuvForm != null )
		{
			myRefForm.setJuvenileNum(juvenileNum);
		}

		//Rebuild header form here
		GetJuvenileProfileMainEvent getJuvProfileEvent = new GetJuvenileProfileMainEvent();
		getJuvProfileEvent.setJuvenileNum(juvenileNum);
		CompositeResponse resp = MessageUtil.postRequest(getJuvProfileEvent);
		JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent)
				MessageUtil.filterComposite( resp, JuvenileProfileDetailResponseEvent.class);
		if (juvProfileRE != null){
			UIJuvenileHelper.populateHeaderFormEvent( myJuvForm, juvProfileRE);
		}
		
		UIJuvenileHelper.setJuvenileBehaviorHistoryForm(aRequest, juvenileNum);

		UIJuvenileHelper.setJuvenileBehaviorHistoryForm(aRequest, juvenileNum);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		// Send PD Request Event
		GetJuvenileProfileReferralListEvent event = (GetJuvenileProfileReferralListEvent)
				EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEPROFILEREFERRALLIST);
		event.setJuvenileNum(myRefForm.getJuvenileNum());
		dispatch.postEvent(event);

		// Get PD Response Event
		CompositeResponse response = (CompositeResponse)dispatch.getReply();
		// Perform Error handling
		MessageUtil.processReturnException(response);

		Map dataMap = MessageUtil.groupByTopic(response);
		if( dataMap != null )
		{
			Collection referrals = (Collection)dataMap.get(PDJuvenileConstants.JUVENILE_PROFILE_REFERRAL_LIST_TOPIC);
			if( referrals != null  &&  referrals.size() > 0 ) //changes for HOT Fix Bug 88261
			{
			    if(referrals.size() > 1 ){  //sort only if multiples
				//Collections.sort((List)referrals);
				//Bug #80122
			    Collections.sort((List<JuvenileProfileReferralListResponseEvent>) referrals, Collections.reverseOrder(new Comparator<JuvenileProfileReferralListResponseEvent>() {
				@Override
				public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
				{
				    if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
					return evt1.getReferralNumber().compareTo(evt2.getReferralNumber());
				    else
					return -1;
				}
			    }));
			    }
			Iterator<JuvenileProfileReferralListResponseEvent> referralIterator = referrals.iterator();
			while (referralIterator.hasNext())
			{
			    JuvenileProfileReferralListResponseEvent referral = referralIterator.next();
			    Collection<JJSOffense> offenses = referral.getOffenses();
			    if (offenses != null)
			    {
				Iterator<JJSOffense> offenseItr = offenses.iterator();				
				while (offenseItr.hasNext())
				{
				    JJSOffense offense = offenseItr.next();
				    
				    referral.setOffenseCategory(offense.getCatagory());
				    
				    if (offense.getSequenceNum().equalsIgnoreCase("1"))
				    {
					referral.setOffense(offense.getOffenseCodeId());
					referral.setOffenseDesc(offense.getOffenseDescription());
					break;
				    }
				    break;
				}
			    }
			    //for each referral get the casefiles, then find the supervision Category, Supervision Type of the most recent casefile assign /highest seq Num - confirmed with Carla 7/17/2018 [email]
			    JuvenileProfileReferralListResponseEvent refCasefileList = JuvenileFacilityHelper.getAllCasefilesForJuvNumRefNum(juvenileNum, referral.getReferralNumber());
			    Collection<JuvenileCasefileReferral> casefileReferrals = refCasefileList.getCasefileReferrals();

			    Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
				@Override
				public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
				{
				    if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
					return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());
				    else
					return -1;
				}
			    }));
			    Iterator<JuvenileCasefileReferral> caseFileRefItr = casefileReferrals.iterator();
			    while (caseFileRefItr.hasNext())
			    {
				JuvenileCasefileReferral caseFileReferral = caseFileRefItr.next();
				referral.setSupervisionCategoryId(caseFileReferral.getSupervisionCat());
				referral.setSupervisionType(caseFileReferral.getSupervisionType());
				referral.setSupervisionTypeId(caseFileReferral.getSupervisionTypeCd());
				referral.setSupervisionCategory(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, referral.getSupervisionCategoryId()));
				// referral.setJpoId(caseFileReferral.getOfficerID());
				OfficerProfileResponseEvent officerProfileResponse = JuvenileDistrictCourtHelper.getOfficerUserId(caseFileReferral.getOfficerID());
				if (officerProfileResponse != null)
				{
				    referral.setJpoId(officerProfileResponse.getUserId());
				    String officerFullName = officerProfileResponse.getLastName() + ", " + officerProfileResponse.getFirstName();
				    referral.setJpo(officerFullName);
				}
				break;
			    }
			}
			myRefForm.setReferralPopList(referrals);
			} //paranthesis moved for BUG 88006
		}
		//code edits to bring the color purple on the ProcessReferralpop-up
		 myRefForm.setActionType(""); //clear field
		if(actionType!=null){
		if(actionType.equalsIgnoreCase("fromProcessReferral")){
		    myRefForm.setActionType("fromProcessReferral");
		}
		}// END code edits to bring the color purple on the ProcessReferralpop-up
		
		aRequest.getSession().setAttribute(HEADER_FORM , myJuvForm);
		aRequest.getSession().setAttribute(
				PDJuvenileCaseConstants.JUVENILE_HISTORY_PAGE,
				PDJuvenileCaseConstants.JUVENILE_PROFILE);
		
		aRequest.getSession().setAttribute(
				PDJuvenileCaseConstants.JUVENILE_HISTORY,
				PDJuvenileCaseConstants.JUVENILE_REFERRAL);

		return( aMapping.findForward("popUpSuccess") );
	}


	/**
	 * getFacility
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward getFacility(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
	{
		
		String juvenileNum = aRequest.getParameter("juvnum");
		String actionType = aRequest.getParameter("actionType");
		/**
		 * Bug Fix #50174: Detention status not refreshing. calling the service again and not pulling from session.
		 * As well updating the session object.
		 */
		GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent)
				EventFactory.getInstance( JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN );

		requestEvent.setJuvenileNum( juvenileNum );
		CompositeResponse replyEvent = MessageUtil.postRequest( requestEvent );
		JuvenileProfileDetailResponseEvent detail = (JuvenileProfileDetailResponseEvent)
				MessageUtil.filterComposite( replyEvent, JuvenileProfileDetailResponseEvent.class );
		
		if( detail == null )
		{
			detail = new JuvenileProfileDetailResponseEvent();
		}

		UIJuvenileHelper.putHeaderForm( aRequest, detail ); //reset the session.
		JuvenileProfileForm myJuvForm = (JuvenileProfileForm)UIJuvenileHelper.getHeaderForm(aRequest);
		

		AssignedReferralsForm myRefForm = (AssignedReferralsForm)aForm;		
		myRefForm.clear();
		if( juvenileNum != null  &&  myRefForm != null )
		{
			myRefForm.setJuvenileNum(juvenileNum);
		}else{
			if(myJuvForm!=null &&  myRefForm != null){
				myRefForm.setJuvenileNum(myJuvForm.getJuvenileNum());
			}
		}
		

		UIJuvenileHelper.setJuvenileBehaviorHistoryForm(aRequest, juvenileNum);
		
		//added for us #14780 // bug fix #51500
		myRefForm.setResidentialCasefile(false);// reset it back;
		SearchJuvenileCasefilesEvent searchEvent = (SearchJuvenileCasefilesEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES);

        searchEvent.setSearchType(PDJuvenileCaseConstants.SEARCH_JUVENILE_NUMBER);
        searchEvent.setJuvenileNum(juvenileNum);

        List<JuvenileCasefileSearchResponseEvent> casefiles = MessageUtil.postRequestListFilter(searchEvent, JuvenileCasefileSearchResponseEvent.class);

        Iterator<JuvenileCasefileSearchResponseEvent> iter = casefiles.iterator();
    		while (iter.hasNext()) //while(1)
    		{
    			JuvenileCasefileSearchResponseEvent casefileResp =  iter.next();
    				//if(casefileResp.getSupervisionCategory().equals("AR")){   //commented the SupervisonCategory check to fix Bug 56669
    					GetJournalEntriesEvent entries = new GetJournalEntriesEvent();
    					entries.setCasefileId(casefileResp.getSupervisionNum());
    					Iterator<Activity> actIter = Activity.findAll(entries);
    					while(actIter.hasNext())
    					{
    						Activity act = actIter.next();
    						if( act != null)
    						{
    						   JuvenileActivityTypeCode  activityTypeCode= ActivityHelper.getActivityTypeCode(act.getActivityCodeId());
    						   if( act != null &&  activityTypeCode !=null && activityTypeCode.getCategoryId()!=null && activityTypeCode.getCategoryId().equals("RES") && activityTypeCode.getTypeId().equalsIgnoreCase("CSM"))
    						   {
    							   myRefForm.setResidentialCasefile(true);
    							   break;
    						   }
    						}
    					} //while(2);
    				//} //residential Casefile.
    			if( myRefForm.isResidentialCasefile()){
    				break;
    			}
    		}	
		//added for us #14780
		
		
		
		//change made for compatability with Facility User Story 11300 - SQL conversion
		// Send PD Request Event
	/*	GetJuvenileDetentionFacilitiesEvent event = (GetJuvenileDetentionFacilitiesEvent)
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILEDETENTIONFACILITIES);
		event.setJuvenileNum(myRefForm.getJuvenileNum());
		dispatch.postEvent(event);

		// Get PD Response Event
		CompositeResponse response = (CompositeResponse)dispatch.getReply();
		// Perform Error handling
		MessageUtil.processReturnException(response);

		Map dataMap = MessageUtil.groupByTopic(response);
		if( dataMap != null )
		{
			Collection facilityHistory = (Collection)dataMap.get(PDJuvenileConstants.JUVENILE_PROFILE_FACILITY_HISTORY_TOPIC);
			if( facilityHistory != null  &&  facilityHistory.size() > 1 )
			{
				List theList = new ArrayList(facilityHistory);

				ArrayList sortFields = new ArrayList();
				sortFields.add(new ReverseComparator(new BeanComparator("admitDate")));
				sortFields.add(new ReverseComparator(new BeanComparator("admitTime")));
				ComparatorChain multiSort = new ComparatorChain(sortFields);
				Collections.sort(theList, multiSort);

				facilityHistory = new ArrayList(theList);

			}
			myRefForm.setFacilityHistoryList(facilityHistory);
		}*/
		
		//added for U.S 14780;
    		boolean isAuthorizedView = false;
		Collection<JuvenileDetentionFacilitiesResponseEvent> facilityHistoryList 	= JuvenileFacilityHelper.getJuvFacilityDetails(myRefForm.getJuvenileNum(), null);
		ArrayList<JuvenileProfileReferralListResponseEvent> referralList	 	= (ArrayList<JuvenileProfileReferralListResponseEvent>) retrieveReferrral(myRefForm.getJuvenileNum());
		ArrayList<JuvenileDetentionFacilitiesResponseEvent> facilityHistoryArrayList	= (ArrayList<JuvenileDetentionFacilitiesResponseEvent>) facilityHistoryList;
		List<String> referralNumbers = new ArrayList<String>();
		if( referralList != null && referralList.size() > 0 ){
		    
		    for (int i = 0; i < referralList.size(); i++ ) {
			    System.out.println( referralList.get(i).getReferralNumber() );
			    referralNumbers.add( referralList.get(i).getReferralNumber());
		    }
		}		
		
		for (int i = 0; i < facilityHistoryArrayList.size(); i++  ){
		    
		    if ( referralNumbers.contains(facilityHistoryArrayList.get(i).getReferralNumber()) ){
			isAuthorizedView = true;
		    }
		}
		
		System.out.println("isAuthorizedView: " + isAuthorizedView);
		
		
		if(facilityHistoryList!=null){
			// re-sort the child list based on facility seq num.
			Collections.sort((List<JuvenileDetentionFacilitiesResponseEvent>)facilityHistoryList,new Comparator<JuvenileDetentionFacilitiesResponseEvent>() {
				@Override
				public int compare(JuvenileDetentionFacilitiesResponseEvent det1,JuvenileDetentionFacilitiesResponseEvent det2) {
					if(det1.getFacilitySequenceNumber()!=null && det2.getFacilitySequenceNumber()!=null)
						return det2.getFacilitySequenceNumber().compareTo(det1.getFacilitySequenceNumber());
					else 
						return -1;
				}
			});
			Collections.sort((List<JuvenileDetentionFacilitiesResponseEvent>)facilityHistoryList, Collections.reverseOrder(JuvenileDetentionFacilitiesResponseEvent.DetentionRespEventComparator));
			if (isAuthorizedView){
			    myRefForm.setFacilityHistoryList(facilityHistoryList);
			} else {
			    facilityHistoryList = null;
			    myRefForm.setFacilityHistoryList(facilityHistoryList);
			}
		} //end of if
		//added for U.S 14780;
		
		aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY_PAGE,PDJuvenileCaseConstants.JUVENILE_PROFILE);
		aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY,PDJuvenileCaseConstants.JUVENILE_FACILITY);
		
		if(actionType != null && actionType.equalsIgnoreCase("popup"))
			return( aMapping.findForward(UIConstants.FACILITY_POP_UP) );

		return( aMapping.findForward(UIConstants.FACILITY) );
	}

	
	/**
	 * Added for U.S #39094
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	@SuppressWarnings("unchecked")
	public ActionForward displayFacilityAdmitRecord(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		AssignedReferralsForm myRefForm = (AssignedReferralsForm) aForm;
		String detentionId = aRequest.getParameter("detentionId");
		String originalAdmitOID = "";
		 myRefForm.setFirstDetainedDate("");
		 myRefForm.setLastDetainedDate("");

		Collection<JuvenileDetentionFacilitiesResponseEvent> detentionResponseList = JuvenileFacilityHelper.getJuvFacilityDetailsByDetentionID(detentionId);
		if (detentionResponseList != null) {
			Iterator<JuvenileDetentionFacilitiesResponseEvent> detentionResponseListItr = detentionResponseList.iterator();
			if(detentionResponseListItr.hasNext()){ //iterate detention recs.
				JuvenileDetentionFacilitiesResponseEvent detRec = detentionResponseListItr.next();
				if( detRec != null ){
					JuvenileFacilityHelper.setBookingOffense(detRec);   //Bug #58186	
					//get some missing referral info such as referralOfficer, ReferralSource etc
					detRec = JuvenileFacilityHelper.getReferralDetail(detRec, myRefForm.getJuvenileNum(), detRec.getReferralNumber());
					
					myRefForm.setAdmissionInfo(detRec);
					if( !StringUtils.isEmpty( detRec.getOriginalAdmitOID())){
					    
					    originalAdmitOID = detRec.getOriginalAdmitOID();
					}else{
					    
					    originalAdmitOID = detentionId;
					}
					
				}
			}
			
			myRefForm.setTraits(JuvenileFacilityHelper.getDetentionTraits(myRefForm.getJuvenileNum()));
			
			JuvenileProfileDetailResponseEvent detailForm = UIJuvenileHelper.getJuvenileDetailForm(aRequest);
			if(detailForm!=null)
				myRefForm.setProfileDetail(detailForm);
			else{
				GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent)EventFactory.getInstance( JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN );
				requestEvent.setJuvenileNum( myRefForm.getJuvenileNum());
				CompositeResponse replyEvent = MessageUtil.postRequest( requestEvent );
				JuvenileProfileDetailResponseEvent detail = (JuvenileProfileDetailResponseEvent)
						MessageUtil.filterComposite( replyEvent, JuvenileProfileDetailResponseEvent.class );
				myRefForm.setProfileDetail(detail);
			}
			
			MemberContact memberContact = UIJuvenileHelper.getPrimaryGuardianPhone(myRefForm.getJuvenileNum());
			if(memberContact!=null){
					myRefForm.setMemberContact(memberContact);
			}
			JuvenileBriefingDetailsForm juvBriefing =  UIJuvenileHelper.getJuvBriefingDetailsForm(aRequest);
			if(juvBriefing!=null){
				myRefForm.setBookingOffenseCd(juvBriefing.getBookingOffenseCode());
				myRefForm.setBookingOffenseCdDesc(juvBriefing.getBookingOffenseCodeDesc());
				//myRefForm.setAdmissionInfo(juvBriefing.getAdmissionInfo());
			}else{
				JuvenileBriefingDetailsForm facilityBriefing = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("FACILITY_BRIEFINGDETAILS_FORM");
				if(facilityBriefing!=null){
					myRefForm.setMemberContact(facilityBriefing.getMemberContact());
					myRefForm.setBookingOffenseCd(facilityBriefing.getBookingOffenseCode());
					myRefForm.setBookingOffenseCdDesc(facilityBriefing.getBookingOffenseCodeDesc());
				}
			}
			
			GetJJSCLDetentionByDetRecResultEvent reqEvent = (GetJJSCLDetentionByDetRecResultEvent) 
							EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYDETRECRESULT);
			
			reqEvent.setDetentionId( originalAdmitOID );
			reqEvent.setHearingResults( getCourtDecisionCodes( courtDecisions) );
			List<DocketEventResponseEvent> detentionList = MessageUtil.postRequestListFilter(reqEvent, DocketEventResponseEvent.class);
			
			if( detentionList.size() > 0 ){
			    
			    Collections.sort((List<DocketEventResponseEvent>) detentionList, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
				@Override
				public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
				{
				    if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
					return Integer.valueOf(evt2.getSeqNum()).compareTo(Integer.valueOf(evt1.getSeqNum()));
				    else
					return -1;
				}
			    }));			 
			    
			    DocketEventResponseEvent firstRecord = (DocketEventResponseEvent) detentionList.get(0);
			    myRefForm.setFirstDetainedDate( firstRecord.getCourtDate() );
			    DocketEventResponseEvent lastRecord = (DocketEventResponseEvent) detentionList.get(detentionList.size() -1);
			    myRefForm.setLastDetainedDate( lastRecord.getCourtDate() );
			}
		}
		return aMapping.findForward(UIConstants.VIEW);
	}
	
	/*
	 * transferred
	 */
	public ActionForward transferred(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		AssignedReferralsForm rForm = (AssignedReferralsForm)aForm;
		String juvenileNum = rForm.getJuvenileNum();
		HttpSession session = aRequest.getSession();
		TransferredOffenseForm tForm = (TransferredOffenseForm)session.getAttribute("transferredOffenseForm" );
		if( tForm == null )
		{
			tForm = new TransferredOffenseForm();
			session.setAttribute( "transferredOffenseForm", tForm );
		}
		tForm.setFromPage(UIConstants.JUVENILE);
		tForm.setJuvenileNumber(juvenileNum);
		tForm.setConfirmMsg(UIConstants.EMPTY_STRING);
		tForm.setExistingTransferredOffensesList(UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(juvenileNum) );

		aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY,
				PDJuvenileCaseConstants.JUVENILE_TRANFERRED);
		return aMapping.findForward(UIConstants.TRANSFER_SUCCESS);
	}	
	
	/**
	 * addMoreTransOffenses
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward addMoreTransOffenses(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		AssignedReferralsForm rForm = (AssignedReferralsForm)aForm;
		String juvenileNum = rForm.getJuvenileNum();
		HttpSession session = aRequest.getSession();
		TransferredOffenseForm tForm = (TransferredOffenseForm)session.getAttribute("transferredOffenseForm" );
		if( tForm == null )
		{
			tForm = new TransferredOffenseForm();
			session.setAttribute( "transferredOffenseForm", tForm );
		}
		tForm.clear();
		if (tForm.getCountiesList() == null || tForm.getCountiesList().isEmpty() )
		{	
			tForm.setCountiesList(UIJuvenileTransferredOffenseReferralHelper.getCounties());
		}
		tForm.setAvailableTransferredOffenseReferralList(UIJuvenileTransferredOffenseReferralHelper.loadAvailableTransferredOffenseReferrals(tForm.getExistingTransferredOffensesList(), juvenileNum) );
		tForm.setFromPage(UIConstants.JUVENILE);
		
		aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY,
				PDJuvenileCaseConstants.JUVENILE_TRANFERRED);

		return aMapping.findForward( UIConstants.ADD_SUCCESS);
	}

	/*
	 * cancel
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return( aMapping.findForward(UIConstants.CANCEL) ) ;
	}

	/*
	 * back
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return( aMapping.findForward(UIConstants.BACK) );
	}
	
	/**
	 * sortOffenses
	 * @param offList
	 * @return
	 */
	public static List sortOffenses(List offList) {
		if (offList.size() > 1) {
			SortedMap map = new TreeMap();
			// sort by referral and seq numbers
			for (int x = 0; x < offList.size(); x++) {
				JuvenileCasefileTransferredOffenseResponseEvent jctore = 
					(JuvenileCasefileTransferredOffenseResponseEvent) offList.get(x);
				map.put(jctore.getReferralNum() + jctore.getSeqNum(), jctore);
			}
			List temp = new ArrayList(map.values());
			offList = new ArrayList(temp);
		}
		return offList;
	}
	
/*	public ActionForward UJACPrint(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		HttpSession aSession = aRequest.getSession();
		JuvenileProfileForm headerForm = (JuvenileProfileForm)aSession.getAttribute( "juvenileProfileHeader" );
		JuvenileProfileDetailResponseEvent mainForm = (JuvenileProfileDetailResponseEvent)aSession.getAttribute("JuvenileDetailForm");			
		PetitionDetailsForm petitionForm = (PetitionDetailsForm)aSession.getAttribute("petitionDetailsForm");
		AssignedReferralsForm assignedRefForm = (AssignedReferralsForm)aForm;		
		JOTChargeReportBean report = new JOTChargeReportBean();
		report.setJuvenileNum(headerForm.getJuvenileNum());
		report.setJuvenileName(headerForm.getJuvenileName());
		report.setReferralNum(assignedRefForm.getReferralNum());
		report.setRaceId(mainForm.getRaceId());
		report.setCurrentAge(headerForm.getAge());
		report.setGender(mainForm.getSex());		
		report.setSidNumber(mainForm.getSID());
		report.setDateOfBirth(DateUtil.dateToString(mainForm.getDateOfBirth(), DateUtil.DATE_FMT_1));
		report.setMultiracial(mainForm.isMultiracial());
		report.setHispanic(mainForm.isHispanic());
		report.setVerifiedDOB(mainForm.isVerifiedDOB());
		if(mainForm.getMultiracial().equalsIgnoreCase("Y"))
			report.setMultiracial(true);
		else
			report.setMultiracial(false);
		if(mainForm.getHispanic().equalsIgnoreCase("Y"))
			report.setHispanic(true);
		else
			report.setHispanic(false);
		if(mainForm.getVerifiedDOB().equalsIgnoreCase("Y"))
			report.setVerifiedDOB(true);
		else
			report.setVerifiedDOB(false);
		report.setArrestDate(petitionForm.getArrestDate());
		report.setArrestTime(petitionForm.getArrestTime());
		report.setPetitionNum(petitionForm.getPetitionNum());
		
		List charges = petitionForm.getJotCharges();
		if(charges!=null)
			report.setJotCharges(charges);
		
		report.setSummaryOfFacts(petitionForm.getSummaryOfFacts());
		
		CompositeResponse compResp = sendPrintRequest("REPORTING::JOT_CHARGE_REPORT",
				report, null);
		
		ReportResponseEvent aReportRespEvt = (ReportResponseEvent) MessageUtil.filterComposite(compResp, ReportResponseEvent.class);
		if (aReportRespEvt.getContent() == null	|| aReportRespEvt.getContent().length < 1) {			
			 ActionErrors errors = new ActionErrors();
			    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.generic","Problems generating report"));
			    saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}
		
		try {
			setPrintContentResp(aResponse, compResp, "JOT_Charge",UIConstants.PRINT_AS_PDF_DOC);
		} catch (GeneralFeedbackMessageException e) {
			sendToErrorPage(aRequest, "");
		}
		ActionForward forward = aMapping.findForward(UIConstants.PRINT_SUCCESS);
		return forward;
	}*/
	
	/**
	 * print
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward print(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		HttpSession aSession = aRequest.getSession();
		JuvenileProfileForm headerForm = (JuvenileProfileForm)aSession.getAttribute( "juvenileProfileHeader" );
		JuvenileProfileDetailResponseEvent mainForm = (JuvenileProfileDetailResponseEvent)aSession.getAttribute("JuvenileDetailForm");			
		PetitionDetailsForm petitionForm = (PetitionDetailsForm)aSession.getAttribute("petitionDetailsForm");
		AssignedReferralsForm assignedRefForm = (AssignedReferralsForm)aForm;		
		JOTChargeReportBean report = new JOTChargeReportBean();
		report.setJuvenileNum(mainForm.getJuvenileNum());
		report.setJuvenileName(mainForm.getFormattedName());
		report.setReferralNum(assignedRefForm.getReferralNum());
		report.setRaceId(mainForm.getRaceId());
		report.setCurrentAge(headerForm.getAge());
		report.setGender(mainForm.getSex());		
		//get the SID number
		GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

		requestEvent.setJuvenileNum(mainForm.getJuvenileNum());

		CompositeResponse replyEvent = postRequestEvent(requestEvent);

		JuvenileProfileDetailResponseEvent juvenile = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(replyEvent,
						JuvenileProfileDetailResponseEvent.class);
		report.setSidNumber(juvenile.getSID());
		report.setDateOfBirth(DateUtil.dateToString(juvenile.getDateOfBirth(), UIConstants.DATE_FMT_1));
		report.setMultiracial(juvenile.isMultiracial());
		//U.S 88526
		if(juvenile.getHispanic()!=null){
		     if(juvenile.getHispanic().equalsIgnoreCase("Y")){  //U.S 88526
			 report.setHispanic(true);
		     }else{
			 report.setHispanic(false);
		     }
		}
		//U.S 88526
		report.setVerifiedDOB(juvenile.isVerifiedDOB());
		report.setArrestDate(petitionForm.getArrestDate());
		report.setArrestTime(petitionForm.getArrestTime());
		report.setPetitionNum(petitionForm.getPetitionNum());
		report.setArrestingAgency(petitionForm.getArrestingAgency());
		
		List charges = petitionForm.getJotCharges();
		if(charges!=null)
			report.setJotCharges(charges);
		List sumOfFactsList = (List) petitionForm.getSummaryOfFacts();
		if (sumOfFactsList != null) {
			int size = sumOfFactsList.size();
			if(size > 0 ) {
				String fact = "";
				StringBuffer factsBuffer = new StringBuffer();
				for (int s = 0; s < size; s++) {
					fact = (String) sumOfFactsList.get(s);
					factsBuffer.append(fact);
				}
				report.setSummaryOF(factsBuffer.toString());
			}
			report.setSummaryOfFacts(petitionForm.getSummaryOfFacts());
		}
		aRequest.getSession().setAttribute("reportInfo", report);
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.JOT_CHARGE);

	    return null;
	}

	private Collection<JuvenileProfileReferralListResponseEvent> retrieveReferrral(String juvenileNum
												) throws Exception
	{
            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

            GetJuvenileProfileReferralListEvent event = (GetJuvenileProfileReferralListEvent)
				EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEPROFILEREFERRALLIST);
            event.setJuvenileNum(juvenileNum);
            dispatch.postEvent(event);
            
            CompositeResponse response = (CompositeResponse)dispatch.getReply();
    	    MessageUtil.processReturnException(response);
    	    Map dataMap = MessageUtil.groupByTopic(response);
            
    	    Collection referrals = (Collection)dataMap.get(PDJuvenileConstants.JUVENILE_PROFILE_REFERRAL_LIST_TOPIC);
            return referrals;
	}
	
	/**
	 * 
	 * @return
	 */
	private static HashMap getCourtDecisionCodes( HashMap map ){
	   
	    map = new HashMap<Integer,String>();
	    List<JuvenileCourtDecisionCodeResponseEvent> codes = JuvenileCaseHelper.getCourtDecisionsNew();
		for( int x=0; x< codes.size();x++){
		    
		    JuvenileCourtDecisionCodeResponseEvent code = codes.get(x);
		    if("Detained".equalsIgnoreCase( code.getAction())){
			map.put(x, code.getCode());
		    }
		}		
		return map;
	}
	
	
	
	
}// END CLASS

