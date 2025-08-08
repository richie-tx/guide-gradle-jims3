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
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.family.GetFamilyConstellationGuardianEvent;
import messaging.family.GetFamilyConstellationGuardianFinancialEvent;
import messaging.family.GetFamilyConstellationsEvent;
import messaging.family.GetFamilyTraitsEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileJISResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetJuvenileTraitsByParentTypeEvent;
import messaging.juvenilecase.reply.FamilyConstellationGuardianResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberFinancialResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileLoadCodeTables;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileProfileForm;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayFamilyInfoAction extends LookupDispatchAction
{
	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		return displayFamilyList(aMapping,aForm,aRequest, aResponse);
	}

	/*
	 * 
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return( aMapping.findForward(UIConstants.CANCEL) );
	}

	/*
	 * 
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return( aMapping.findForward(UIConstants.BACK) );
	}

	/*
	 * 
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
	
	
	public ActionForward displayFamilyList( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{	
	    
	   
		JuvenileProfileForm myJuvForm = (JuvenileProfileForm)UIJuvenileHelper.getHeaderForm(aRequest);
		String currentJuvNum = (String)aRequest.getParameter("juvnum");
		
		 HttpSession session = aRequest.getSession();
			JuvenileBriefingDetailsForm juvenileBriefingForm = null;

			juvenileBriefingForm =  UIJuvenileHelper.getJuvBriefingDetailsForm(aRequest);

			if( juvenileBriefingForm == null ){
				juvenileBriefingForm = new JuvenileBriefingDetailsForm();
				setProfileDetail(currentJuvNum, juvenileBriefingForm);
				session.setAttribute("juvenileBriefingDetailsForm", juvenileBriefingForm);
			}
		
		if( currentJuvNum != null && (currentJuvNum.length() > 0) )
		{
			if( myJuvForm == null || 
					myJuvForm.getJuvenileNum() == null || (myJuvForm.getJuvenileNum().length() == 0) || 
					! myJuvForm.getJuvenileNum().equals(currentJuvNum))
			{
				GetJuvenileProfileMainEvent getJuvProfileEvent = new GetJuvenileProfileMainEvent();
				
				getJuvProfileEvent.setJuvenileNum(currentJuvNum);
				CompositeResponse response = MessageUtil.postRequest(getJuvProfileEvent);
				JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent)
						MessageUtil.filterComposite( response, JuvenileProfileDetailResponseEvent.class);
				
				if( juvProfileRE != null )
				{
					UIJuvenileHelper.putHeaderForm( aRequest, juvProfileRE ); 
				}
				myJuvForm = (JuvenileProfileForm)UIJuvenileHelper.getHeaderForm(aRequest);
			}
		}
		
		JuvenileFamilyForm myFamForm = (JuvenileFamilyForm)aForm;
		
		myFamForm.clear();
		myFamForm.setCurrentActiveConstellation(new JuvenileFamilyForm.Constellation());
		myFamForm.setAction("");

		if( ! myFamForm.isListsSet() )
		{
			UIJuvenileLoadCodeTables.getInstance().setJuvenileFamilyForm(myFamForm);
		}

		if( myJuvForm != null)
		{
			myFamForm.setJuvenileNumber(myJuvForm.getJuvenileNum());
		}
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		// Send PD Request Event
		GetFamilyConstellationsEvent event = (GetFamilyConstellationsEvent)
				EventFactory.getInstance( JuvenileFamilyControllerServiceNames.GETFAMILYCONSTELLATIONS);
		event.setJuvenileNum(myFamForm.getJuvenileNumber());
		dispatch.postEvent(event);

		// Get PD Response Event	
		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		// Perform Error handling
		MessageUtil.processReturnException(response);
		myFamForm.setCurrentGuardian(new JuvenileFamilyForm.Guardian());
		Map dataMap = MessageUtil.groupByTopic(response);
		if( dataMap != null )
		{			
			Collection families  = (Collection)dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATIONS_TOPIC);
			UIJuvenileHelper.setJuvFamilyFormConstListFROMConstListRespEvt( myFamForm, families);

			families = myFamForm.getConstellationList();
			if(families != null && families.size() > 0) 
			{
				Iterator<JuvenileFamilyForm.ConstellationList> myIter = families.iterator();
				while( myIter.hasNext() )
				{
					JuvenileFamilyForm.ConstellationList myFamily = myIter.next();
					if( myFamily.isActive() )
					{
						
						Collection currentFamMembers = (Collection)dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_LIST_TOPIC);
						JuvenileFamilyForm.Constellation newFamily = new JuvenileFamilyForm.Constellation();

					

						myFamForm.setHasActiveConstellation(true);
						newFamily.setFamilyNumber(myFamily.getFamilyNumber());
						newFamily.setActive(myFamily.isActive());
						myFamForm.setCurrentConstellation(newFamily);
						myFamForm.setCurrentActiveConstellation(newFamily);
						UIJuvenileHelper.setJuvFamilyFormFROMMemberListRespEvt(newFamily,currentFamMembers);
							/* sort newFamily members */
						
						Collection memberList = newFamily.getMemberList();
						Collection sortedMemberList = getSortedByGuardian(memberList);
						newFamily.setMemberList(sortedMemberList);
						
						if(currentFamMembers != null && currentFamMembers.size() > 0) 
						{
							
							Iterator iter =  newFamily.getMemberList().iterator();
				  			  while(iter.hasNext()) {
				  			  	JuvenileFamilyForm.MemberList myMember = (JuvenileFamilyForm.MemberList)iter.next();
				  			  String lastName = myMember.getMemberName().getLastName();
								if(myMember.isGuardian())
				  			  	{
								    // JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
							        IDispatch disp = EventManager.getSharedInstance(EventManager.REQUEST);

							        // Sending PD Request Event
							        GetFamilyConstellationGuardianEvent mEvent = new GetFamilyConstellationGuardianEvent();

							        mEvent.setMemberNum(convertToInt(myMember.getMemberNumber(),0));
							        disp.postEvent(mEvent);

								    // Getting PD Response Event
							        CompositeResponse resp = (CompositeResponse) disp.getReply();
								    // Perform Error handling
							        MessageUtil.processReturnException(resp);
							        Map dataMap2 = MessageUtil.groupByTopic(resp);
							        FamilyConstellationGuardianResponseEvent responseEvt;

							        JuvenileFamilyForm.Guardian myGuardian;
							        if (dataMap2 != null)
							        {
							            Collection guardianInfo = (Collection) dataMap2
							                    .get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_GUARDIAN_TOPIC);
							            if (guardianInfo != null && guardianInfo.size() > 0)
							            {
						                    Iterator iter2 = guardianInfo.iterator();
							                while (iter2.hasNext())
							                {
							                    responseEvt = (FamilyConstellationGuardianResponseEvent) iter2.next();
							                    if (responseEvt != null)
							                    {
							                    	// Sending PD Request Event
							                        GetFamilyConstellationGuardianFinancialEvent fEvent = new GetFamilyConstellationGuardianFinancialEvent();

							                        fEvent.setFinancialId(responseEvt.getFinancialId());
							                        dispatch.postEvent(fEvent);

							                        // Getting PD Response Event
							                        CompositeResponse fResponse = (CompositeResponse) dispatch.getReply();
							                        // Perform Error handling
							                        MessageUtil.processReturnException(fResponse);
							                        Map dataMap3 = MessageUtil.groupByTopic(fResponse);
							                        FamilyConstellationMemberFinancialResponseEvent fResponseEvt;
							                        
							                        myFamForm.getCurrentGuardian().setAnnualNetIncome("0.00");
							                        if (dataMap3 != null)
							                        {
							                            Collection guardianInfo2 = (Collection) dataMap3
							                                    .get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_GUARDIAN_FINANCIAL_TOPIC);
							                            if (guardianInfo2 != null && guardianInfo2.size() > 0)
							                            {
							                                Iterator iter3 = guardianInfo2.iterator();
							                                if (iter3.hasNext())
							                                {
							                                    fResponseEvt = (FamilyConstellationMemberFinancialResponseEvent) iter3.next();
							                                    if (fResponseEvt != null)
							                                    {
							                                        myGuardian = UIJuvenileHelper
							                                                .getFamilyFormGuardianFROMFamilyConstellationFinancialRespEvt(fResponseEvt);
											                    	myGuardian = UIJuvenileHelper.getFamilyFormGuardianFROMFamilyConstellationFinancialRespEvt(fResponseEvt);
											                    	if (myGuardian != null)
											                        {
											                    		myGuardian.setMemberNumber(myMember.getMemberNumber());
											                    		myGuardian.setConstellationMemberId(myMember.getFamilyConstellationMemberNum());
											                    		myGuardian.setName(myMember.getMemberName());
											                    		String dateString = DateUtil.dateToString(responseEvt.getEntryDate(),
											                                    UIConstants.DATE_FMT_1);
											                            myGuardian.setEntryDate(dateString);
											                    		myGuardian.setRelationshipToJuv(myMember.getRelationshipToJuv());
											                    		myGuardian.setDeceased(myMember.getDeceasedYesNo());
											                    		myGuardian.setInHomeStatus(myMember.isInHomeStatus());
											                    		
											                    		//added for Bug #33954
											                    		myGuardian.setNumberInFamily(myFamForm.getCurrentActiveConstellation().getFamilyNumber());
											                    		myFamForm.setCurrentGuardian(myGuardian);
											                        }
											                    }
											                }
											            }
									  			  	}
							  			    	}    
								           }
							           }
									}
								}
							}
						}
					}
				}
			}
			// extraction program is currently having issues
			String familyNum =	myFamForm.getCurrentConstellation().getFamilyNumber();
			if (familyNum != null && !"".equals(familyNum) )
			{		
				IDispatch dispatch2 = EventManager.getSharedInstance(EventManager.REQUEST);
				// Send PD Request Event
				GetFamilyTraitsEvent tEvent= 
					   (GetFamilyTraitsEvent)EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYTRAITS);
				tEvent.setFamilyNum(myFamForm.getCurrentConstellation().getFamilyNumber());
				dispatch.postEvent(tEvent);
				// Getting PD Response Event
		        CompositeResponse response2 = (CompositeResponse) dispatch2.getReply();
		        // Perform Error handling
		        MessageUtil.processReturnException(response2);
		        Map dataMap4 = MessageUtil.groupByTopic(response2);
		        if (dataMap4 != null)
		        {
		        	Collection familiesTraits  = (Collection) dataMap4.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_TRAIT_LIST_TOPIC);
		        	UIJuvenileHelper.setJuvFamilyFormCurConstFROMTraitListRespEvt(myFamForm.getCurrentConstellation(), familiesTraits);
		        }
			}
			familyNum = null;
		}
		// Filter for former dual trait status
		GetJuvenileTraitsByParentTypeEvent traitByParentEvent = (GetJuvenileTraitsByParentTypeEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITSBYPARENTTYPE);
		traitByParentEvent.setJuvenileNum( currentJuvNum );
		traitByParentEvent.setTraitType("25");

		Collection<JuvenileTraitResponseEvent> juvTraits = MessageUtil.postRequestListFilter(traitByParentEvent, JuvenileTraitResponseEvent.class);

		Collections.sort((List<JuvenileTraitResponseEvent>) juvTraits, new Comparator<JuvenileTraitResponseEvent>() {
		    @Override
		    public int compare(JuvenileTraitResponseEvent evt1, JuvenileTraitResponseEvent evt2)
		    {
			if (evt1.getJuvenileTraitId() != null && evt2.getJuvenileTraitId() != null)
			    return evt2.getJuvenileTraitId().compareTo(evt1.getJuvenileTraitId());
			else
			    return -1;
		    }
		});

		juvenileBriefingForm.setDualStatus("");
		if (juvTraits != null && juvTraits.size() > 0)
		{
		    for (JuvenileTraitResponseEvent juvenileTrait : juvTraits)
		    {
			//filter for current vs former
			if ("DST".equalsIgnoreCase(juvenileTrait.getTraitTypeId()) && "CU".equalsIgnoreCase(juvenileTrait.getStatusId()))
			{
			    juvenileBriefingForm.setDualStatus("DS");
			    break;
			}
			else
			{
			    if ("DST".equalsIgnoreCase(juvenileTrait.getTraitTypeId()) && !"CU".equalsIgnoreCase(juvenileTrait.getStatusId()))
			    {
				juvenileBriefingForm.setDualStatus("FDS");
				break;

			    }
			}
		    }
		}// end of if
		
		if(juvenileBriefingForm.getProfileDetail() != null && juvenileBriefingForm.getProfileDetail().getStatus() != null ){
		    
		    if( juvenileBriefingForm.getProfileDetail().getStatus().toLowerCase().equals("closed")){
			
			myFamForm.setIsClosedJuvStatus(true);
		    }
		}
		
		//get constellation info for youth
		if(myFamForm != null && myFamForm.getJuvenileNumber() != null && !"".equals(myFamForm.getJuvenileNumber()))
		{
		    boolean hasConstellation = UIJuvenileHelper.juvenileHasConstellation(myFamForm.getJuvenileNumber());
		    
		    if(hasConstellation){
			myFamForm.setYouthHasConstellation(true);
		    } else {
			myFamForm.setYouthHasConstellation(false);
		    }
		}
		
		
		return( aMapping.findForward(UIConstants.SUCCESS) );
	}
	
	/*
	 * @param str
	 * @return
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null && (str.trim().length() > 0) ) ;
	}
	
	/*
	 * given a String and an initial value, convert String to int
	 * created this to reduce code clutter, when no error processing
	 * is required in the catch.
	 */
	private int convertToInt( String tStr, int initialValue )
	{
		int value = initialValue ;
		
		if( notNullNotEmptyString( tStr ))
		{
			String str = tStr.trim() ;
			
			try
			{
				value = Integer.parseInt( str ) ;
			}
			catch( NumberFormatException nfe )
			{ /*empty*/
			}
		}
		
		return( value ) ;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.displayFamilyList", "displayFamilyList");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
	}
	/*
	 * This method sorts the collection to have guardian details to come first in the list and alphabetically
	 * added by Sruti
	 *
	 */
	
	
	private Collection getSortedByGuardian( Collection memberList )
	{
		List guardianList = new ArrayList();
		List nonGuardianList = new ArrayList();
		List sortedList = new ArrayList();
		Iterator iter =  memberList.iterator();
		Map guardianMap = new HashMap();
		Map nonGuardianMap = new HashMap();

		while(iter.hasNext()) {
			JuvenileFamilyForm.MemberList myMember = (JuvenileFamilyForm.MemberList)iter.next();
			if(myMember.isGuardian())
			{
				//guardianList.add(myMember);	
				//myMember.getMemberName().getFirstName();		
	            String memerLastName = myMember.getMemberName().getLastName();
	            String memerFirstName = myMember.getMemberName().getFirstName();
				String memberFullName = memerLastName + memerFirstName+myMember.getMemberNumber();
				guardianMap.put(memberFullName, myMember);
				//guardianMap.put(myMember.getMemberNumber(), myMember);
				
			}else{
				//nonGuardianList.add(myMember);	
				
				String memerLastName = myMember.getMemberName().getLastName();
				 String memerFirstName = myMember.getMemberName().getFirstName();
				String memberFullName = memerLastName + memerFirstName+myMember.getMemberNumber();
				nonGuardianMap.put(memberFullName, myMember);
				//nonGuardianMap.put(myMember.getMemberNumber(), myMember);
			}
		}
		/*Sorting both the list seperately and appending*/
		SortedSet<String> guardianKeys = new TreeSet<String>(guardianMap.keySet());
		SortedSet<String> nonGuardianKeys = new TreeSet<String>(nonGuardianMap.keySet());
		for (String key : guardianKeys) { 
			JuvenileFamilyForm.MemberList currMember = (JuvenileFamilyForm.MemberList)guardianMap.get(key);
			guardianList.add(currMember);
		}
		for (String key : nonGuardianKeys) { 
			JuvenileFamilyForm.MemberList currMember = (JuvenileFamilyForm.MemberList)nonGuardianMap.get(key);
			nonGuardianList.add(currMember);
		}
		/*Collections.sort((List) guardianList);
		Collections.sort((List) nonGuardianList);*/
		sortedList.addAll(guardianList);
		sortedList.addAll(nonGuardianList);
		return sortedList;
	}
}// END CLASS
