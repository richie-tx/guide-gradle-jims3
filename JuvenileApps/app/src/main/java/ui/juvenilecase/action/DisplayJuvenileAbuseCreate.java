package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetActiveFamilyConstellationEvent;
import messaging.interviewinfo.reply.InterviewPersonResponseEvent;
import messaging.juvenile.GetJuvenileAbuseListEvent;
import messaging.juvenile.GetJuvenileContactsEvent;
import messaging.juvenile.SaveJuvenileAbuseEvent;
import messaging.juvenile.SaveJuvenileAbusePerpEvent;
import messaging.juvenile.SaveJuvenileAbuserRelationshipEvent;
import messaging.juvenile.SaveJuvenileDSCPSServicesEvent;
import messaging.juvenile.SaveJuvenileDSPlacementsEvent;
import messaging.juvenile.SaveJuvenileDualStatusEvent;
import messaging.juvenile.reply.JuvenileAbuseResponseEvent;
import messaging.juvenile.reply.JuvenileContactResponseEvent;
import messaging.juvenile.reply.JuvenileDSPlacementResponseEvent;
import messaging.juvenile.reply.JuvenileDualStatusResponseEvent;
import messaging.juvenilecase.GetCurrentJuvenileDSTTraitsEvent;
import messaging.juvenilecase.SaveJuvenileTraitsEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.security.IUserInfo;
import mojo.km.security.UserEntityBean;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import pd.juvenile.JuvenileAbuse;
import pd.juvenile.JuvenileAbusePerpatrator;
import pd.security.PDSecurityHelper;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.UINotificationHelper;
import ui.juvenilecase.AbusePerpetrator;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.JuvenileAbuseForm;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.form.TraitsForm;
import ui.security.SecurityUIHelper;

public class DisplayJuvenileAbuseCreate extends LookupDispatchAction
{

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.backToAbuseList","backToAbuseList");
		keyMap.put("button.link", "create");
		keyMap.put("button.addMoreAbuseInfo", "addMoreAbuse");
		keyMap.put("button.finish", "finish");
		keyMap.put("button.next", "summary");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.backToDualStatusList","backToDualStatusList");
		keyMap.put("button.dual", "createDualStatus");
		keyMap.put("button.addPlacement", "addPlacement");
		keyMap.put("button.nextDual", "summaryDual");	
		keyMap.put("button.backDual", "backtoDual");
		keyMap.put( "button.remove", "removePlacements" ) ;
		keyMap.put("button.finishDual", "finishDual");

		return keyMap;
	}

	@SuppressWarnings("unchecked")
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
		throws Exception
	{
		ActionForward forward = aMapping.findForward(UIConstants.CONFIRM);

		JuvenileAbuseForm form = (JuvenileAbuseForm) aForm;
		TraitsForm traitsForm=UIJuvenileHelper.getTraitsForm(aRequest,true);

		JuvenileProfileForm headerForm = UIJuvenileHelper.getHeaderForm(aRequest);
		String juvenileNum = headerForm.getJuvenileNum();
		
		
	//39386 Enabling multiple perpetrators name in Abuse	
		
		SaveJuvenileAbuseEvent saveEvent = (SaveJuvenileAbuseEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEABUSE);
		this.setEvent(juvenileNum, form, saveEvent);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(saveEvent);	
		
		// Now save a trait in the traits table:
		SaveJuvenileTraitsEvent saveTraitEvent =
			(SaveJuvenileTraitsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.SAVEJUVENILETRAITS);
		saveTraitEvent.setJuvenileNum(juvenileNum);
		// TODO Apply available disposition number in future iteration
		//saveTraitEvent.setDispositionNum(SubmitCreateTraitsAction.DISPOSITION_NUM);
		saveTraitEvent.setSupervisionNum(form.getSupervisionNum());
		JuvenileTraitResponseEvent myGenericTrait=new JuvenileTraitResponseEvent();
			//myGenericTrait.setDispositionNum(SubmitCreateTraitsAction.DISPOSITION_NUM);
			myGenericTrait.setEntryDate(new Date());
			myGenericTrait.setJuvenileNum(juvenileNum);
			myGenericTrait.setSupervisionNum(form.getSupervisionNum());
			myGenericTrait.setTraitComments("Auto System Created during the creation of an Abuse Detail Trait Entry");
			myGenericTrait.setTraitTypeId(form.getTraitTypeId());
			myGenericTrait.setStatusId(form.getInformationBasisId());
			myGenericTrait.setInformationSrcCd(form.getInformationSrcCd());
		saveTraitEvent.addRequest(myGenericTrait);

		dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(saveTraitEvent);	
		Collection abuses = UIJuvenileHelper.fetchJuvenileAbuses(juvenileNum);
		
		form.setAbuses(abuses);
        Collection abusesSort= form.getAbuses(); // To sort the Abuses to get the latest abuseI
        String currentAbuseId = null;
        String juvNum = null;
        String supervisionNum = form.getSupervisionNum();;
          for (int i=0;i <= abusesSort.size(); i++)
          {
				JuvenileAbuseResponseEvent abuseResp = (JuvenileAbuseResponseEvent) abusesSort.iterator().next();
				currentAbuseId = abuseResp.getAbuseId();
				juvNum=abuseResp.getJuvenileNumber();
				break;
          }
          
		List<AbusePerpetrator> multiPerp = form.getMultiplePrep();
		if(multiPerp!=null && multiPerp.size()>1){
		
		/*for( Iterator iter =  multiPerp.iterator(); iter.hasNext();)
			{AbusePerpetrator te = (AbusePerpetrator)iter.next();
			if(te!=null)
				{
				Name fullName = te.getName();					
				}
			}*/
			
		AbusePerpetrator multiAbusePrep;
		StringBuffer sbName = new StringBuffer();
		StringBuffer sbRelation = new StringBuffer();
		/* for (Iterator iterator = multiPerp.iterator(); iterator.hasNext();){
			 multiAbusePrep =  multiPerp.iterator().next();
			 String relationShip= multiAbusePrep.getRelationshipToJuv();
			 String firstName= multiAbusePrep.getName().getFirstName();
			 String middleName= multiAbusePrep.getName().getMiddleName();
			 String lastName= multiAbusePrep.getName().getLastName();
			 if(!(firstName==null)){
				 sbName.append(firstName);
			 }
			 if(!(middleName==null)){
				 sbName.append(middleName);
			 }
			 if(!(lastName==null)){
				 sbName.append(lastName);
			 }
			 if(!(relationShip==null)){
			 sbRelation.append(relationShip);
			 }
		 }*/
		
		 
			
		 
		 
		Iterator<AbusePerpetrator> multiPerp1Itr= multiPerp.iterator();
		while(multiPerp1Itr.hasNext()){
			multiAbusePrep = multiPerp1Itr.next();
			//String nameStr += multiAbusePrep.getName().getFirstName()+multiAbusePrep.getName().getMiddleName()+multiAbusePrep.getName().getLastName();
			SaveJuvenileAbusePerpEvent saveAbusePerpEvent =	(SaveJuvenileAbusePerpEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEABUSEPERP);			
			saveAbusePerpEvent.setFirstName(multiAbusePrep.getName().getFirstName());
			saveAbusePerpEvent.setMiddleName(multiAbusePrep.getName().getMiddleName());
			saveAbusePerpEvent.setLastName(multiAbusePrep.getName().getLastName());
			saveAbusePerpEvent.setRelationshipToJuvenileId(multiAbusePrep.getRelationshipToJuvId());
			saveAbusePerpEvent.setAbuseId(currentAbuseId);
			//task 120019
			if(multiAbusePrep.getMemberId().isEmpty())
			    saveAbusePerpEvent.setMemberId(null); 
			else
			    saveAbusePerpEvent.setMemberId(multiAbusePrep.getMemberId());
			if(multiAbusePrep.getContactId().isEmpty())
			    saveAbusePerpEvent.setContactId(null); 
			else
			    saveAbusePerpEvent.setContactId(multiAbusePrep.getContactId());
			//
			IDispatch dispatch2 = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch2.postEvent(saveAbusePerpEvent);
			}
		} 
		else {
			SaveJuvenileAbusePerpEvent saveAbusePrpEvent =	(SaveJuvenileAbusePerpEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEABUSEPERP);
			saveAbusePrpEvent.setFirstName(form.getPerpetratorName().getFirstName());
			saveAbusePrpEvent.setMiddleName(form.getPerpetratorName().getMiddleName());
			saveAbusePrpEvent.setLastName(form.getPerpetratorName().getLastName());
			saveAbusePrpEvent.setRelationshipToJuvenileId(form.getRelationshipToJuvenileId());
			saveAbusePrpEvent.setAbuseId(currentAbuseId);
			//task 120019 check if form. values have
			if(form.getMemberId().isEmpty())
			    saveAbusePrpEvent.setMemberId(null); 
			else
			    saveAbusePrpEvent.setMemberId(form.getMemberId());
			if(form.getContactId().isEmpty())
			    saveAbusePrpEvent.setContactId(null); 
			else
			    saveAbusePrpEvent.setContactId(form.getContactId());
			//
			IDispatch dispatch2 = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch2.postEvent(saveAbusePrpEvent);
		}
		//add code to save multiple relationship to new table like perpetrator 120020
		//String[] multiabuserRelationships = form.getAllegedAbuserRelationship();
		String[] multiabuserRelationships = form.getAllegedAbuserRelationship().split(";");
		//split based on ; and save
		if(multiabuserRelationships!=null)
		{
        		for(int i=0;  i< multiabuserRelationships.length-1; i++)
        		{
        		    	SaveJuvenileAbuserRelationshipEvent saveAbuseRelnEvent =(SaveJuvenileAbuserRelationshipEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEABUSERRELATIONSHIP);	//change it to new name		
        		    	saveAbuseRelnEvent.setAbuseId(currentAbuseId);
        		    	saveAbuseRelnEvent.setJuvenileNumber(juvNum);
        		    	saveAbuseRelnEvent.setSupervisionNumber(supervisionNum);
        		    	saveAbuseRelnEvent.setAllegedabuserRelationship(multiabuserRelationships[i]);
        			IDispatch dispatch2 = EventManager.getSharedInstance(EventManager.REQUEST);
        			dispatch2.postEvent(saveAbuseRelnEvent);
        		}
		
		}
		form.setAction(UIConstants.CONFIRM);
		return forward;
	}
	//109828
	public ActionForward finishDual(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
		throws Exception
	{
		ActionForward forward = aMapping.findForward(UIConstants.CONFIRM_DUAL);

		JuvenileAbuseForm form = (JuvenileAbuseForm) aForm;
		TraitsForm traitsForm=UIJuvenileHelper.getTraitsForm(aRequest,true);

		JuvenileProfileForm headerForm = UIJuvenileHelper.getHeaderForm(aRequest);
		String juvenileNum = headerForm.getJuvenileNum();
		String juvenileName = headerForm.getJuvenileName();
		
		SaveJuvenileDualStatusEvent saveEvent = (SaveJuvenileDualStatusEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEDUALSTATUS);
		this.setDualStatusEvent(juvenileNum, form, saveEvent);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(saveEvent);	
		
		// Now save a trait in the traits table:
		GetCurrentJuvenileDSTTraitsEvent event = (GetCurrentJuvenileDSTTraitsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETCURRENTJUVENILEDSTTRAITS);
		event.setJuvenileNum(juvenileNum);

		IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch1.postEvent(event);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse responses = (CompositeResponse) replyEvent;

		Collection<JuvenileTraitResponseEvent> juvenileTraits = MessageUtil.compositeToCollection(responses, JuvenileTraitResponseEvent.class);
		if (juvenileTraits.size() == 0) 
		{
        		SaveJuvenileTraitsEvent saveTraitEvent =
        			(SaveJuvenileTraitsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.SAVEJUVENILETRAITS);
        		saveTraitEvent.setJuvenileNum(juvenileNum);
        		saveTraitEvent.setSupervisionNum(form.getSupervisionNum());
        		JuvenileTraitResponseEvent myGenericTrait=new JuvenileTraitResponseEvent();
        			myGenericTrait.setEntryDate(new Date());
        			myGenericTrait.setJuvenileNum(juvenileNum);
        			myGenericTrait.setSupervisionNum(form.getSupervisionNum());
        			myGenericTrait.setTraitComments(form.getReferralRegion()+"; "+form.getDualstatusComments());
        			myGenericTrait.setTraitTypeId("DST");
        			myGenericTrait.setStatusId("CU");
        			myGenericTrait.setInformationSrcCd(form.getInformationSrcCd());
        		saveTraitEvent.addRequest(myGenericTrait);
        
        		dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        		dispatch.postEvent(saveTraitEvent);
        		sendDualStatusEmail(juvenileNum, juvenileName, "DST");
		}
		
		Collection dualstatuses = UIJuvenileHelper.fetchJuvenileDualstatuses(juvenileNum);
		
		form.setDualstatuses(dualstatuses);
                Collection dualstatusesSort= form.getDualstatuses(); // To sort the dual to get the latest abuseI
                String currentDualId = null;
                String juvNum = null;
                String supervisionNum = form.getSupervisionNum();;
                 for (int i=0;i <= dualstatusesSort.size(); i++)
                  {
                      JuvenileDualStatusResponseEvent dualResp = (JuvenileDualStatusResponseEvent) dualstatusesSort.iterator().next();
        		currentDualId = dualResp.getDualstatusId();
        		juvNum=dualResp.getJuvenileNumber();
        		break;
                  }          		
		String[] cpsServices = form.getCPSServices().split(";");
		//split based on ; and save
		if(cpsServices!=null)
		{
        		for(int i=0;  i< cpsServices.length-1; i++)
        		{
        		    SaveJuvenileDSCPSServicesEvent savedualcpsservicesEvent =(SaveJuvenileDSCPSServicesEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEDSCPSSERVICES);	//change it to new name		
        		    savedualcpsservicesEvent.setDualID(currentDualId);
        		    savedualcpsservicesEvent.setCPSService(cpsServices[i]);
        		    savedualcpsservicesEvent.setJuvenileNumber(juvenileNum);
        		    savedualcpsservicesEvent.setSupervisionNumber(supervisionNum);        		    	
        		    IDispatch dispatch2 = EventManager.getSharedInstance(EventManager.REQUEST);
        		    dispatch2.postEvent(savedualcpsservicesEvent);
        		}
		
		}
		//placements save
		//String juv = "";
		Collection newPlacements=form.getNewPlacements();
			
		if( newPlacements != null && !newPlacements.isEmpty( ) )
		{
			
			Iterator i = newPlacements.iterator( ) ;

			while( i.hasNext() )
			{
			    JuvenileDSPlacementResponseEvent juvDSplacementresp = (JuvenileDSPlacementResponseEvent)i.next( );
			    SaveJuvenileDSPlacementsEvent saveEvt = (SaveJuvenileDSPlacementsEvent)
				    EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEDSPLACEMENTS);
			    //juvenileNum =  form.get;
			    saveEvt.setJuvenileNumber( juvenileNum ) ;
			    saveEvt.setSupervisionNumber(supervisionNum ) ;
			    saveEvt.setDualID(currentDualId);
			    if(juvDSplacementresp.getPlacementDate()!=null)
			    {
        			    if(juvDSplacementresp.getPlacementDate().isEmpty())
        				saveEvt.setPlacementDate(null);
        			    else
        				saveEvt.setPlacementDate(juvDSplacementresp.getPlacementDate().replace("/", ""));
			    }
			    if(juvDSplacementresp.getPlacementType()!=null)
			    {
        			    if(juvDSplacementresp.getPlacementType().isEmpty())
        				saveEvt.setPlacementType(null);
        			    else
        				saveEvt.setPlacementType(juvDSplacementresp.getPlacementType());
			    }
			    if(juvDSplacementresp.getPlacementtypeotherReason()!=null)
			    {
        			    if(juvDSplacementresp.getPlacementtypeotherReason().isEmpty())
        				saveEvt.setPlacementtypeotherReason(null);
        			    else
        				saveEvt.setPlacementtypeotherReason(juvDSplacementresp.getPlacementtypeotherReason());
			    }
			    if(juvDSplacementresp.getPlacementRemovalReason()!=null)
			    {
        			    if(juvDSplacementresp.getPlacementRemovalReason().isEmpty())
        				saveEvt.setPlacementRemovalReason(null);
        			    else
        				saveEvt.setPlacementRemovalReason(juvDSplacementresp.getPlacementRemovalReason());
			    }
			    if(juvDSplacementresp.getPlacementremovalreasonOther()!=null)
			    {
        			    if(juvDSplacementresp.getPlacementremovalreasonOther().isEmpty())
        				saveEvt.setPlacementremovalreasonOther(null);
        			    else
        				saveEvt.setPlacementremovalreasonOther(juvDSplacementresp.getPlacementremovalreasonOther());
			    }
			    IDispatch dispatch2 = EventManager.getSharedInstance(EventManager.REQUEST);
        		    dispatch2.postEvent(saveEvt);
			}
		}
		//
		form.setAction(UIConstants.CONFIRM_DUAL);
		
		
		return forward;
	}
	//
	private Collection multiPerp(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	private void setEvent(String juvenileNum, JuvenileAbuseForm form, SaveJuvenileAbuseEvent saveEvent )
	{
		saveEvent.setAbuseDetails(form.getAbuseDetails());
		saveEvent.setAbuseEvent(form.getAbuseEvent());
		saveEvent.setAbuseLevelId(form.getAbuseLevelId());
		saveEvent.setAbuseTreatment(form.getAbuseTreatment());
		saveEvent.setInformationBasisId(form.getInformationBasisId());
		saveEvent.setTraitTypeId(form.getTraitTypeId());
		saveEvent.setInformationSrcCd(form.getInformationSrcCd());
		if (PDJuvenileCaseConstants.CPS_INVOLVEMENT_YES.equals(form.getCpsInvolvement()))
		{
			saveEvent.setCpsInvolvement(true);
		}
		else
		{
			saveEvent.setCpsInvolvement(false);
		}
		if (PDJuvenileCaseConstants.CPS_CUSTODY_YES.equals(form.getCpsCustody()))
		{
			saveEvent.setCpsCustody(true);
		}
		else
		{
			saveEvent.setCpsCustody(false);
		}
		
		saveEvent.setCpsCasenumber(form.getCpsCaseNumber());
		/*saveEvent.setFirstName(form.getPerpetratorName().getFirstName());
		saveEvent.setMiddleName(form.getPerpetratorName().getMiddleName());
		saveEvent.setLastName(form.getPerpetratorName().getLastName());
		saveEvent.setRelationshipToJuvenileId(form.getRelationshipToJuvenileId());
		if(form.getContactId()!=null && !form.getContactId().equals(""))
			saveEvent.setContactId(form.getContactId());
		if(form.getMemberId()!=null && !form.getMemberId().equals(""))
			saveEvent.setMemberId(form.getMemberId());*/

		saveEvent.setJuvenileNum(juvenileNum);
		saveEvent.setSupervisionNum(form.getSupervisionNum());
		/*if(form.getAllegedAbuserRelationship()!=null && !form.getAllegedAbuserRelationship().equals(""))
		    saveEvent.setAllegedAbuserRelationship(form.getAllegedAbuserRelationship());
		else
		    saveEvent.setAllegedAbuserRelationship(null);*/
	}
	private void setDualStatusEvent(String juvenileNum, JuvenileAbuseForm form, SaveJuvenileDualStatusEvent saveEvent )
	{
	    	saveEvent.setJuvenileNum(juvenileNum);
		saveEvent.setSupervisionNum(form.getSupervisionNum());
		
		saveEvent.setReferralRegion(form.getReferralRegion());
		saveEvent.setCustodyStatus(form.getCustodyStatus());
		saveEvent.setCpsLevelofcare(form.getCpslevelofCare());
		if(form.getDualstatusComments().isEmpty())
		    saveEvent.setDualstatusComments(null);
		else
		    saveEvent.setDualstatusComments(form.getDualstatusComments());
		saveEvent.setParentalRights(form.getParentalrightsTermination());
				
	}
	
	public ActionForward backToAbuseList(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
			throws Exception
		{
			ActionForward forward = aMapping.findForward("backToAbuseList");
			JuvenileAbuseForm form = (JuvenileAbuseForm) aForm;
			form.setAction(UIConstants.VIEW);
			return forward;
		}
	public ActionForward backToDualStatusList(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
		throws Exception
	{
		ActionForward forward = aMapping.findForward("backToDualStatusList");
		JuvenileAbuseForm form = (JuvenileAbuseForm) aForm;
		form.setAction(UIConstants.VIEW);
		return forward;
	}

	public ActionForward summary(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
		throws Exception
	{
		ActionForward forward = aMapping.findForward(UIConstants.SUMMARY);
		JuvenileAbuseForm form = (JuvenileAbuseForm) aForm;
		form.setTraitTypeId(form.getTraitsForm().getTraitTypeDescriptionId());
		form.setTraitTypeName(form.getTraitsForm().getTraitTypeDescription());
		form.setInformationSrcDesc(CodeHelper.getCodeDescription(PDCodeTableConstants.INFORMATION_SOURCE, form.getInformationSrcCd()));
		/*
		String comments = form.getAbuseEvent();
		if (!comments.equals("") && comments != null) {
			IUserInfo user = SecurityUIHelper.getUser();
			Name userName = new Name(user.getFirstName(),"",user.getLastName());
			form.setAbuseEvent(comments + " [" + DateUtil.getCurrentDateString(UIConstants.DATETIME24_FMT_1) + " - " + userName.getFormattedName() + "]");
		}
		comments = form.getAbuseDetails();
		if (!comments.equals("") && comments != null) {
			IUserInfo user = SecurityUIHelper.getUser();
			Name userName = new Name(user.getFirstName(),"",user.getLastName());
			form.setAbuseDetails(comments + " [" + DateUtil.getCurrentDateString(UIConstants.DATETIME24_FMT_1) + " - " + userName.getFormattedName() + "]");
		}
		*/
//		
		form.setContactId("");
		form.setMemberId("");
		form.setRelationshipToJuvenileId("");
		form.setRelationshipToJuvenile("");
		form.setFirstName("");
		form.setLastName("");
		form.setMiddleName("");
		String firstNames = new String();
		String realtionShips = new String();
		String namewithRealtionships = new String();
		//39386 Enabling multiple perpetrators name in Abuse	
		List<AbusePerpetrator> multiPerp = new ArrayList<AbusePerpetrator>();
		String[] multiPerpIds = aRequest.getParameterValues("perpId");
		if(form.getPerpId()!=null && !form.getPerpId().equals("")){
			if(form.getPerpetrators()!=null && form.getPerpetrators().size()>0){
				Iterator perpIter=form.getPerpetrators().iterator();
				while(perpIter.hasNext()){
					AbusePerpetrator myPerp=(AbusePerpetrator)perpIter.next();
					for(int i=0;  i< multiPerpIds.length; i++){
						if(multiPerpIds[i].toString().equals(myPerp.getPerpetratorId())){
							multiPerp.add(myPerp);
							form.setContactId(myPerp.getContactId());
							form.setMemberId(myPerp.getMemberId());
							form.setPerpetratorName(myPerp.getName());
							form.setRelationshipToJuvenileId(myPerp.getRelationshipToJuvId());
							form.setRelationshipToJuvenile(myPerp.getRelationshipToJuv());
							Name multiName= form.getPerpetratorName();
							String name = multiName.getFormattedName();
							String relation = myPerp.getRelationshipToJuv();
							String namewithRealtionship=name+" - "+relation;
							relation +=" "+"; ";
							name +=";";							
							realtionShips +=relation;
							firstNames +=name;
							namewithRealtionships+=namewithRealtionship+" ; ";
						}
						
					}
					
				}
				form.setRelationShips(realtionShips);
				form.setMultiplePrep(multiPerp);
				form.setMultiNames(firstNames);
				form.setNamewithRelationShips(namewithRealtionships);
			}
		}
		//add code to get multiple relationship to alleged abuser 120020
		//set it in form the multiple relationships
		String[] multiabuserRelationships = aRequest.getParameterValues("allegedAbuserRelationship");
		String relationships="";
		String relationship="";
		if(multiabuserRelationships!=null)
		{		
        		for(int i=0;  i< multiabuserRelationships.length; i++)
        		{
        		    //form.setAllegedAbuserRelationship(multiabuserRelationships);
        		    relationship=multiabuserRelationships[i];
        		    if(!relationships.isEmpty())
        			relationships+=relationship+" ; ";
        		    else
        			relationships=relationship+" ; ";
        		}
		}
		form.setAllegedAbuserRelationship(relationships);
		//
		form.setAction(UIConstants.SUMMARY);
		return forward;
	}
	// dual add
	public ActionForward summaryDual(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
		throws Exception
	{
		ActionForward forward = aMapping.findForward(UIConstants.SUMMARY_DUAL);
		JuvenileAbuseForm form = (JuvenileAbuseForm) aForm;
		form.setTraitTypeId(form.getTraitsForm().getTraitTypeDescriptionId());
		form.setTraitTypeName(form.getTraitsForm().getTraitTypeDescription());
		form.setInformationSrcDesc(CodeHelper.getCodeDescription(PDCodeTableConstants.INFORMATION_SOURCE, form.getInformationSrcCd()));
		
		/*form.setContactId("");
		form.setMemberId("");
		form.setRelationshipToJuvenileId("");
		form.setRelationshipToJuvenile("");
		form.setFirstName("");
		form.setLastName("");
		form.setMiddleName("");
		String firstNames = new String();
		String realtionShips = new String();
		String namewithRealtionships = new String();
		//39386 Enabling multiple perpetrators name in Abuse	
		List<AbusePerpetrator> multiPerp = new ArrayList<AbusePerpetrator>();
		String[] multiPerpIds = aRequest.getParameterValues("perpId");
		if(form.getPerpId()!=null && !form.getPerpId().equals("")){
			if(form.getPerpetrators()!=null && form.getPerpetrators().size()>0){
				Iterator perpIter=form.getPerpetrators().iterator();
				while(perpIter.hasNext()){
					AbusePerpetrator myPerp=(AbusePerpetrator)perpIter.next();
					for(int i=0;  i< multiPerpIds.length; i++){
						if(multiPerpIds[i].toString().equals(myPerp.getPerpetratorId())){
							multiPerp.add(myPerp);
							form.setContactId(myPerp.getContactId());
							form.setMemberId(myPerp.getMemberId());
							form.setPerpetratorName(myPerp.getName());
							form.setRelationshipToJuvenileId(myPerp.getRelationshipToJuvId());
							form.setRelationshipToJuvenile(myPerp.getRelationshipToJuv());
							Name multiName= form.getPerpetratorName();
							String name = multiName.getFormattedName();
							String relation = myPerp.getRelationshipToJuv();
							String namewithRealtionship=name+" - "+relation;
							relation +=" "+"; ";
							name +=";";							
							realtionShips +=relation;
							firstNames +=name;
							namewithRealtionships+=namewithRealtionship+" ; ";
						}
						
					}
					
				}
				form.setRelationShips(realtionShips);
				form.setMultiplePrep(multiPerp);
				form.setMultiNames(firstNames);
				form.setNamewithRelationShips(namewithRealtionships);
			}
		}
		*/
		//set it in form the multiple relationships
		String[] multiCPSServices = aRequest.getParameterValues("CPSServices");
		String services="";
		String service="";
		if(multiCPSServices!=null)
		{		
        		for(int i=0;  i< multiCPSServices.length; i++)
        		{
        		    //form.setAllegedAbuserRelationship(multiabuserRelationships);
        		    service=multiCPSServices[i];
        		    if(!services.isEmpty())
        			services+=service+" ; ";
        		    else
        			services=service+" ; ";
        		}
		}
		form.setCPSServices(services);
		
		form.setAction(UIConstants.SUMMARY);
		return forward;
	}
	//
	
	public ActionForward addMoreAbuse(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			JuvenileAbuseForm form = (JuvenileAbuseForm) aForm;
			// Retrieve all Perpetrators
	
			
			
			
			// Set perpetrators to form
			ActionForward forward = aMapping.findForward("successCasefileSelect");
			return forward;
		}

	private Collection getAbusePerpsFromContacts(Collection contacts){
		ArrayList myAbusePerps=new ArrayList();
		if(contacts!=null && contacts.size()>0){
			Iterator myIter=contacts.iterator();
			while(myIter.hasNext()){
				AbusePerpetrator myAbusePerp=new AbusePerpetrator();
				JuvenileContactResponseEvent myRespEvent=(JuvenileContactResponseEvent)myIter.next();
				myAbusePerp.setContactId(myRespEvent.getContactNum());
				Name myName=new Name();
				myName.setFirstName(myRespEvent.getFirstName());
				myName.setLastName(myRespEvent.getLastName());
				myName.setMiddleName(myRespEvent.getMiddleName());
				myAbusePerp.setName(myName);
				myAbusePerp.setRelationshipToJuvId(myRespEvent.getRelationshipId());
				myAbusePerp.setRelationshipToJuv(myRespEvent.getRelationship());
				myAbusePerps.add(myAbusePerp);
			}
		}
		return myAbusePerps;
	}
	
	public ActionForward create(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
		throws Exception
	{
		ActionForward forward = aMapping.findForward(UIConstants.CREATE);
		JuvenileAbuseForm form = (JuvenileAbuseForm) aForm;
		form.clear();
		GetJuvenileContactsEvent contactsEvent = (GetJuvenileContactsEvent)EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILECONTACTS);
		JuvenileProfileForm headerForm = UIJuvenileHelper.getHeaderForm(aRequest);
		String juvenileNum = headerForm.getJuvenileNum();
		contactsEvent.setJuvenileNum(juvenileNum);
		CompositeResponse response = UIJuvenileHelper.getCompositeResponse(contactsEvent);
		Collection contacts = (Collection) UIJuvenileHelper.fetchCollection(response, PDJuvenileCaseConstants.JUVENILE_CONTACT_TOPIC);
		Collection perps=getAbusePerpsFromContacts(contacts);
		form.setPerpetrators(perps);
		
		// Get family members
		GetActiveFamilyConstellationEvent event = (GetActiveFamilyConstellationEvent) EventFactory
            .getInstance(JuvenileFamilyControllerServiceNames.GETACTIVEFAMILYCONSTELLATION);
    event.setJuvenileNum(juvenileNum);

    // Getting PD Response Event
    response = UIJuvenileHelper.getCompositeResponse(event);
    // Perform Error handling
    MessageUtil.processReturnException(response);
    Map dataMap = MessageUtil.groupByTopic(response);
    if (dataMap != null)
    {
        Collection families = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATIONS_TOPIC);
        if (families != null && families.size() > 0)
        {
            Iterator myIter = families.iterator();
            while (myIter.hasNext())
            {
                FamilyConstellationListResponseEvent myFamily = (FamilyConstellationListResponseEvent) myIter.next();
                if (myFamily.isActive())
                {
                	                  
                    Collection currentFamMembers = (Collection) dataMap
                            .get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_LIST_TOPIC);
                    if(currentFamMembers!=null && currentFamMembers.size()>0){
                    	Iterator currFamMemIter=currentFamMembers.iterator();
                    	while(currFamMemIter.hasNext()){AbusePerpetrator myAbusePerp=new AbusePerpetrator();
                    		FamilyConstellationMemberListResponseEvent myRespEvent1=(FamilyConstellationMemberListResponseEvent)currFamMemIter.next();
		                    AbusePerpetrator myAbusePerp1=new AbusePerpetrator();
		    				myAbusePerp1.setMemberId(myRespEvent1.getMemberNum());
		    				Name myName=new Name();
		    				myName.setFirstName(myRespEvent1.getFirstName());
		    				myName.setLastName(myRespEvent1.getLastName());
		    				myName.setMiddleName(myRespEvent1.getMiddleName());
		    				myAbusePerp1.setName(myName);
		    				myAbusePerp1.setRelationshipToJuvId(myRespEvent1.getRelationToJuvenileId());
		    				myAbusePerp1.setRelationshipToJuv(myRespEvent1.getRelationToJuvenile());
		    				perps.add(myAbusePerp1);
                    	}
                    }
                }
            }
        }
    }
    //add code to add all abused relationship to form or add in form
    //

		 
		return forward;
	}
	//create dual status 120740
	public ActionForward createDualStatus(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
		throws Exception
	{
		ActionForward forward = aMapping.findForward(UIConstants.CREATEDUALSTATUS);
		JuvenileAbuseForm form = (JuvenileAbuseForm) aForm;
		form.clear();
		//add code to check if DST trait already there to make information source need to give or not
		JuvenileProfileForm headerForm = UIJuvenileHelper.getHeaderForm(aRequest);
		String juvenileNum = headerForm.getJuvenileNum();
		GetCurrentJuvenileDSTTraitsEvent event = (GetCurrentJuvenileDSTTraitsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETCURRENTJUVENILEDSTTRAITS);
		event.setJuvenileNum(juvenileNum);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		IEvent replyEvent = dispatch.getReply();
		CompositeResponse responses = (CompositeResponse) replyEvent;

		Collection<JuvenileTraitResponseEvent> juvenileTraits = MessageUtil.compositeToCollection(responses, JuvenileTraitResponseEvent.class);
		if (juvenileTraits.size() == 0)
		{
		    form.setInformationSource(null);
		}
		else if (juvenileTraits != null && juvenileTraits.size() > 0) 
		{
		    for ( JuvenileTraitResponseEvent juvenileTrait : juvenileTraits )
		    {
			if ( juvenileTrait.getTraitTypeId().equals("DST"))
			{
			    form.setInformationSource(juvenileTrait.getInformationSrcCd());
			}	
		    }
		} 
		return forward;
	}
	//
	// task 120740
	public ActionForward addPlacement( ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse )
        {
	    
	    	JuvenileAbuseForm form = (JuvenileAbuseForm)aForm ;
                
	    	JuvenileDSPlacementResponseEvent juvenileDual = this.createJuvenileDualPlacements( form ) ;
                
                form.getNewPlacements().add( juvenileDual ) ;
                
                form.setPlacementDate(null);
                form.setPlacementType(null);
                form.setPlacementtypeotherReason(null);
                form.setPlacementRemovalReason(null); 
                form.setPlacementremovalreasonOther(null);
                form.setPlacementdateMonth(null);
                form.setPlacementdateYear(null);
                	
                return aMapping.findForward( "addPlacement" ) ;
        }
	private JuvenileDSPlacementResponseEvent createJuvenileDualPlacements( JuvenileAbuseForm form )
	{
	    //need to create a response event just for placements
	    JuvenileDSPlacementResponseEvent juvenileDual = new JuvenileDSPlacementResponseEvent( ) ;

		juvenileDual.setPlacementDate(form.getPlacementdateMonth()+"/"+form.getPlacementdateYear());
	    	juvenileDual.setPlacementType(form.getPlacementType());
	    	juvenileDual.setPlacementRemovalReason(form.getPlacementRemovalReason());
	    	if(form.getPlacementRemovalReason().equalsIgnoreCase("OTHER REASON"))
	    	    juvenileDual.setPlacementremovalreasonOther(form.getPlacementremovalreasonOther());
	    	else
	    	    juvenileDual.setPlacementremovalreasonOther(null);
	    	if(form.getPlacementType().equalsIgnoreCase("OTHER"))
	    	    juvenileDual.setPlacementtypeotherReason(form.getPlacementtypeotherReason());
	    	else
	    	    juvenileDual.setPlacementtypeotherReason(null);
		
		return juvenileDual ;
	}

	//
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
		throws Exception
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		JuvenileAbuseForm form = (JuvenileAbuseForm) aForm;
		form.setAction(UIConstants.VIEW);
		form.clear();
		return forward;
	}
	
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
	HttpServletRequest aRequest, HttpServletResponse aResponse)
			{
	    			String forward=null;
	    			JuvenileAbuseForm form = (JuvenileAbuseForm) aForm;
	    			if(form.getAction().equalsIgnoreCase(UIConstants.SUMMARY))
	    			{
	    			    forward=UIConstants.CREATE;
	    			    form.setAction(UIConstants.VIEW);
	    			}
	    			else
	    			    forward="backToAbuseList";
	    			return aMapping.findForward(forward);
				
	}
	public ActionForward backtoDual(ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse)
				{
		    			String forward=null;
		    			JuvenileAbuseForm form = (JuvenileAbuseForm) aForm;
		    			if(form.getAction().equalsIgnoreCase(UIConstants.SUMMARY))
		    			{
		    			    forward=UIConstants.CREATEDUALSTATUS;
		    			    form.setAction(UIConstants.VIEW);
		    			}
		    			else
		    			    forward="backToDualStatusList";
		    			return aMapping.findForward(forward);
					
		}
	public ActionForward removePlacements( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
{
	//ActionForward forward = aMapping.findForward("removePlacements");

	JuvenileAbuseForm form = (JuvenileAbuseForm) aForm;
	if(	  form.getSelectedValue()!= null && 
			!(form.getSelectedValue().equals("")) && 
			  form.getNewPlacements()!= null )
	{
		int offset = Integer.parseInt(form.getSelectedValue() ) ;
		if( form.getNewPlacements().size() > offset )
		{
			((ArrayList)form.getNewPlacements()).remove( offset );							
		}
	}

	return aMapping.findForward( "removePlacements" ) ;
}
	
	private void  sendDualStatusEmail( String juvenileNum,
						String juvenileName,
						String traitDescriptionId){
            boolean isDST = false;
            String headerFacilityStatus = "";
            IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
            
            if ("DST".equals( traitDescriptionId ) ) {
        	isDST = true;
            }
            
            JuvenileFacilityHeaderResponseEvent jjsHeaderInfo = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(juvenileNum);
            if ( jjsHeaderInfo != null ){
        	headerFacilityStatus = jjsHeaderInfo.getFacilityStatus();
            }
            
            if ( isDST && "D".equals( headerFacilityStatus ) ){
                //Collection<OfficerProfileResponseEvent>   securityRespEvent =  PDOfficerProfileHelper.getOfficerProfilesInUserGroup("DUAL STATUS YOUTH DETAINED NOTIFICATION GROUP");
                List<UserEntityBean> userGroup = PDSecurityHelper.getUserGroupByIdOrName("DUAL STATUS YOUTH DETAINED NOTIFICATION GROUP", "");
                if( userGroup!=null){
                
                
                    for ( UserEntityBean user : userGroup )
                    {
                	if( user.getEmail()!=null 
                    	    && ! user.getEmail().equals("")){	
                        
                    		//send email
                    		SendEmailEvent sendEmailEvent = new SendEmailEvent();
                    		sendEmailEvent.setFromAddress("jims2notification@itc.hctx.net");
                        	UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, user.getEmail());
                        	//sendEmailEvent.addToAddress("Dustin.Nguyen@us.hctx.net");
                    		sendEmailEvent.setSubject( "Dual Status - Youth is in Detention, Juvenile: " + juvenileNum + ", " + juvenileName);
                        	sendEmailEvent.setMessage("This email serves as notice of a Dual Status youth who is actively booked in Detention.");
                        	dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
                        	dispatch.postEvent(sendEmailEvent);
                        }
                    }
                }
            }
	}


}
