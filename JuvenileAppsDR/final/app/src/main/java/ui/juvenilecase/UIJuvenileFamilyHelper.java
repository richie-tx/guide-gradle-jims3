/*
 * Created on Feb 22, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import pd.juvenile.JuvenileHelper;
import messaging.family.GetActiveFamilyConstellationEvent;
import messaging.family.GetAllJuvFamilyMembersEvent;
import messaging.family.GetFamilyConstellationDetailsEvent;
import messaging.family.GetFamilyConstellationMemberLatestFinancialEvent;
import messaging.family.GetFamilyConstellationMembersEvent;
import messaging.family.GetFamilyMemberBenefitsEvent;
import messaging.family.GetFamilyMemberEmploymentInfoEvent;
import messaging.family.GetFamilyMembersEvent;
import messaging.family.GetGuardianInfoEvent;
import messaging.family.GetJuvenileAgeAtfamilyMemberDeathEvent;
import messaging.family.SaveFamilyMemberEvent;
import messaging.family.SaveFamilyMemberMaritalStatusEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberFinancialResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberBenefitResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberEmploymentInfoResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberListResponseEvent;
import messaging.juvenilecase.reply.GuardianInfoResponseEvent;
import messaging.juvenilecase.reply.JuvenileFamilyMemberViewResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDConstants;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;
import ui.common.UIUtil;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.form.JuvenileProfileForm;
 
/**
 * @author asrvastava
 *  
 */
public class UIJuvenileFamilyHelper
{
	/* Returns a list of JuvenileFamilyMemberViewResponseEvent responses
	 * 
	 */
  public static List getAllJuvenilesFamilyMembers(String juvNumber)
  {
    // Send PD Request Event
    GetAllJuvFamilyMembersEvent event = (GetAllJuvFamilyMembersEvent) 
    		EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETALLJUVFAMILYMEMBERS);

    event.setJuvenileNum(juvNumber);
    List coll = MessageUtil.postRequestListFilter(event, JuvenileFamilyMemberViewResponseEvent.class);

    Collections.sort(coll);

    return( coll );
  }

  /* Returns a FamilyMemberListResponseEvent
   * 
   */
  public static JuvenileFamilyMemberViewResponseEvent findJuvFamilyMem(
  		String memNum, Collection aJuvenileFamilyMemberViewResponseEvents)
  {
    if( aJuvenileFamilyMemberViewResponseEvents != null && 
    		(aJuvenileFamilyMemberViewResponseEvents.size() > 0) &&
    		memNum != null && (memNum.length() > 0) )
    {
      Iterator<JuvenileFamilyMemberViewResponseEvent> iter = aJuvenileFamilyMemberViewResponseEvents.iterator();
      while (iter.hasNext())
      {
        JuvenileFamilyMemberViewResponseEvent myRespEvt = iter.next();
        if (memNum.equals(myRespEvt.getMemberNum()))
        {
          return myRespEvt;
        }
      }
    }
    
    return null;
  }


  /*
   *  returns true if the guardians are the same
   */
  public static boolean isGuardianTheSame( JuvenileFamilyForm.Guardian currGuardian,
  		JuvenileFamilyForm.Guardian compareGuardian)
  {
    if (currGuardian == null || compareGuardian == null)
    {
      return false;
    }
    
    boolean isTheSame = true;
    
    if( !(currGuardian.getChildSupportPaid().equals(compareGuardian.getChildSupportPaid())))
    {
      isTheSame = false;
    }
    else if ( !(currGuardian.getChildSupportReceived().equals(compareGuardian.getChildSupportReceived())))
    {
      isTheSame = false;
    }
    else if( !(currGuardian.getOtherAdultIncome().equals(compareGuardian.getOtherAdultIncome())))
    {
      isTheSame = false;
    }
    else if( !(currGuardian.getNotes().equals(compareGuardian.getNotes())))
    {
      isTheSame = false;
    }
    else if( !(currGuardian.getChildSupportPayorName().equals(compareGuardian.getChildSupportPayorName())))
    {
      isTheSame = false;
    }
    else if( !(currGuardian.getFinancialId().equals(compareGuardian.getFinancialId())))
    {
      isTheSame = false;
    }
    else if( !(currGuardian.getFoodStamps().equals( compareGuardian.getFoodStamps())))
    {
      isTheSame = false;
    }
    else if (!(currGuardian.getGroceryExpenses().equals(compareGuardian.getGroceryExpenses())))
    {
      isTheSame = false;
    }
    else if (!(currGuardian.getIntangibleValue().equals(compareGuardian.getIntangibleValue())))
    {
      isTheSame = false;
    }
    else if (!(currGuardian.getLifeInsurancePremium().equals(compareGuardian.getLifeInsurancePremium())))
    {
      isTheSame = false;
    }
    else if (!(currGuardian.getMedicalExpenses().equals(compareGuardian.getMedicalExpenses())))
    {
      isTheSame = false;
    }
    else if (!(currGuardian.getNumberInFamily().equals(compareGuardian.getNumberInFamily())))
    {
      isTheSame = false;
    }
    else if (!(currGuardian.getNumberLivingInHome().equals(compareGuardian.getNumberLivingInHome())))
    {
      isTheSame = false;
    }
    else if (!(currGuardian.getNumberOfDependents().equals(compareGuardian.getNumberOfDependents())))
    {
      isTheSame = false;
    }
    else if (!(currGuardian.getOtherIncome().equals(compareGuardian.getOtherIncome())))
    {
      isTheSame = false;
    }
    else if (!(currGuardian.getPropertyValue().equals(compareGuardian.getPropertyValue())))
    {
      isTheSame = false;
    }
    else if (!(currGuardian.getRentExpenses().equals(compareGuardian.getRentExpenses())))
    {
      isTheSame = false;
    }
    else if (!(currGuardian.getSavings().equals(compareGuardian.getSavings())))
    {
      isTheSame = false;
    }
    else if (!(currGuardian.getSchoolExpenses().equals(compareGuardian.getSchoolExpenses())))
    {
      isTheSame = false;
    }
    else if (!(currGuardian.getTanfAfdc().equals(compareGuardian.getTanfAfdc())))
    {
      isTheSame = false;
    }
    else if (!(currGuardian.getTotalExpenses().equals(compareGuardian.getTotalExpenses())))
    {
      isTheSame = false;
    }
    else if (!(currGuardian.getUtilitiesExpenses().equals(compareGuardian.getUtilitiesExpenses())))
    {
      isTheSame = false;
    }
    else if (!(currGuardian.getSsi().equals(compareGuardian.getSsi())))
    {
      isTheSame = false;
    }

    return isTheSame;
  }

    /*
     * 
     */
    public static void getCurrentConstFamilyMembers(JuvenileFamilyForm myFamForm)
    {
      String familyNum = myFamForm.getCurrentConstellation().getFamilyNumber();
      if (familyNum == null || (familyNum.trim().length() == 0) )
      {
        familyNum = myFamForm.getCurrentFamilyNumber();
      }

      myFamForm.clear();
      myFamForm.setJuvenileNumber( myFamForm.getJuvenileNumber() );
      
      // Send PD Request Event
      GetFamilyConstellationMembersEvent event = (GetFamilyConstellationMembersEvent)
      		EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYCONSTELLATIONMEMBERS);
      event.setConetllaionId(familyNum);

      IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
      dispatch.postEvent(event); /**/

      // Get PD Response Event
      CompositeResponse response = (CompositeResponse)dispatch.getReply();
      // Perform Error handling
      MessageUtil.processReturnException(response);
      
      Map dataMap = MessageUtil.groupByTopic(response);
      if( dataMap != null )
      {
        Collection familiesMembers = (Collection) 
        		dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_LIST_TOPIC);
        JuvenileFamilyForm.Constellation newFamily = new JuvenileFamilyForm.Constellation();
        newFamily.setFamilyNumber(familyNum);
        myFamForm.setCurrentConstellation(newFamily);
        UIJuvenileHelper.setJuvFamilyFormFROMMemberListRespEvt(newFamily, familiesMembers);
        myFamForm.setTotalDetentionVisits(UIJuvenileHelper.getTotalJuvDetentionVisits(familiesMembers, myFamForm.getJuvenileNumber()));
        myFamForm.setDaVisit( UIJuvenileHelper.isDetDefenseAttorneyVisit(myFamForm.getJuvenileNumber()) );
        myFamForm.setVisitorCapRemoved( JuvenileHelper.bypassDetentionVisitorCap(myFamForm.getJuvenileNumber()));
      }
    }

    /*
     * 
     */
    public static FamilyMemberDetailResponseEvent saveMember(JuvenileMemberForm myForm, HttpServletRequest aRequest, List suspiciousMatches)
    {
      // Send PD Request Event
      SaveFamilyMemberEvent event = (SaveFamilyMemberEvent) 
      		EventFactory.getInstance(JuvenileFamilyControllerServiceNames.SAVEFAMILYMEMBER);
      
      UIJuvenileHelper.getSaveFamilyMemberEvent(myForm, event);
      event.setSuspiciousMatches(suspiciousMatches);

      boolean saveMarriageInfo = false;
      if( myForm.getMaritalStatusId() != null && 
      		(myForm.getMaritalStatusId().length() > 0) )
      {
        saveMarriageInfo = true;
        if (myForm.getMaritalList() != null && myForm.getMaritalList().size() > 0)
        {
          JuvenileMemberForm.MaritalList myLastMarriage = (JuvenileMemberForm.MaritalList) 
          		((ArrayList)myForm.getMaritalList()).get(0);

          if( myLastMarriage.getMaritalStatusId().equalsIgnoreCase(myForm.getMaritalStatusId()) && 
          		myLastMarriage.getMarriageDate().trim().equalsIgnoreCase(myForm.getMarriageDate()) && 
              myLastMarriage.getNumOfChildren().trim().equalsIgnoreCase(myForm.getNumOfChildren()) && 
              myLastMarriage.getDivorceDate().trim().equalsIgnoreCase(myForm.getDivorceDate()) && 
              myLastMarriage.getRelatedFamMemId().trim().equalsIgnoreCase(myForm.getRelatedFamMemId()))
          {
            saveMarriageInfo = false;
          }
        }
      }

      if( saveMarriageInfo )
      {
        SaveFamilyMemberMaritalStatusEvent martialStatusEvent = 
        		UIJuvenileHelper.getSaveFamilyMemberMaritalStatusEvent(myForm);
        event.addRequest(martialStatusEvent);
      }

      //Added to update juvenile age. Defect#JIMS200047286
      HttpSession session = aRequest.getSession();
	  	JuvenileProfileForm headerForm = (JuvenileProfileForm) session.getAttribute("juvenileProfileHeader");
	  	
      CompositeResponse response = MessageUtil.postRequest(event);
      FamilyMemberDetailResponseEvent returnEvent = (FamilyMemberDetailResponseEvent) 
		  		MessageUtil.filterComposite( response, FamilyMemberDetailResponseEvent.class);
      myForm.setMemberNumber(returnEvent.getMemberId());
	  	
      if( returnEvent != null && 
	  			headerForm != null && 
	  			myForm.isDeceased() == true)
	  	{
	      GetJuvenileAgeAtfamilyMemberDeathEvent reqEvent = (GetJuvenileAgeAtfamilyMemberDeathEvent)
	      		EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETJUVENILEAGEATFAMILYMEMBERDEATH);

				reqEvent.setFamilyMemberId(myForm.getMemberNumber());
				reqEvent.setAction( UIConstants.UPDATE );
				reqEvent.setJuvenileNum(headerForm.getJuvenileNum());
				reqEvent.setJuvenileAge(myForm.getJuvenileAgeAtDeath());

				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(reqEvent);
				returnEvent.setJuvenileAgeAtDeath(reqEvent.getJuvenileAge());
		  }
      //End
      if( !(myForm.getAction().equals("createMemberSummary")) && 
      		UIJuvenileHelper.hasMemberPrimaryInfoChanged(myForm) )
      {
        UIJuvenileHelper.sendOutJPONotificationForMemberUpdate(myForm.getMemberNumber(), myForm.isHasActiveCasefile());
      }
      
      return returnEvent;
    }

    /**
     * Used by another action in DisplayFamilyConstellationDetailsAction as well.
     * 
     * @param aMemberNum
     * @param aGuardian
     */
  public static void getEmploymentMemberInfo(String aMemberNum, JuvenileFamilyForm.Guardian aGuardian)
  {
    if (aMemberNum == null || 
    		aMemberNum.equals( UIConstants.EMPTY_STRING ))
    {
      return;
    }
    
    if( aGuardian == null )
    {
      aGuardian = new JuvenileFamilyForm.Guardian();
    }
    
    GetFamilyMemberEmploymentInfoEvent event = (GetFamilyMemberEmploymentInfoEvent) 
    		EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBEREMPLOYMENTINFO);
    event.setMemberNum(aMemberNum);
    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
    dispatch.postEvent(event);

    // Getting PD Response Event
    CompositeResponse response = (CompositeResponse) dispatch.getReply();
    // Perform Error handling
    MessageUtil.processReturnException(response);

    aGuardian.setEmploymentInfo( null );
    aGuardian.setAnnualNetIncome( UIConstants.EMPTY_STRING );
    
    Map dataMap = MessageUtil.groupByTopic(response);
    if( dataMap != null )
    {
      ArrayList myEmployments = new ArrayList();
      aGuardian.setEmploymentInfoList(myEmployments);
      Collection employments = (Collection) 
      		dataMap.get(PDJuvenileFamilyConstants.FAMILY_MEMBER_EMPLOYMENT_TOPIC);

      if( employments != null && employments.size() > 0 )
      {
        FamilyMemberEmploymentInfoResponseEvent responseEvt ;
        Iterator<FamilyMemberEmploymentInfoResponseEvent> iter = employments.iterator();
        while( iter.hasNext() )
        {
        	responseEvt = iter.next();
          if( responseEvt != null )
          {
            JuvenileMemberForm.MemberEmployment myEmployment = 
            		UIJuvenileHelper.getJuvMemberFormMemberEmploymentInfoFROMEmploymentInfoRespEvt(responseEvt);

            if (myEmployment != null)
            {
              myEmployments.add(myEmployment);
            }
          }
        } // while
      }
      Collections.sort( (List)myEmployments);
    } //dataMap != null
  }

  /*
   * 
   */
  public static Map getActiveFamilyConstellation(String juvenileNum)
  {
    GetActiveFamilyConstellationEvent event = (GetActiveFamilyConstellationEvent) 
    		EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETACTIVEFAMILYCONSTELLATION);
    event.setJuvenileNum(juvenileNum);

    // Get PD Response Event
    CompositeResponse response = UIJuvenileHelper.getCompositeResponse(event);
    // Perform Error handling
    MessageUtil.processReturnException(response);
    Map dataMap = MessageUtil.groupByTopic(response);
    
    return( dataMap );
  }
  
  public static int getDetVisitFamMemberCount(String juvenileNum){
	    
	GetActiveFamilyConstellationEvent getCurrentConstellation = new GetActiveFamilyConstellationEvent();
	getCurrentConstellation.setJuvenileNum(juvenileNum);
	CompositeResponse replyEvent = MessageUtil.postRequest(getCurrentConstellation);
	Collection constellations = MessageUtil.compositeToCollection(replyEvent, FamilyConstellationListResponseEvent.class);
		int detVisitFamilyMemberTotal = 0;
			if (constellations != null && !constellations.isEmpty()) {
			// Only 1 active constellation at a time, therefore get the first in collection
			FamilyConstellationListResponseEvent activeConstellation = (FamilyConstellationListResponseEvent) constellations.iterator().next();
			GetFamilyConstellationDetailsEvent getConstellationDetails = new GetFamilyConstellationDetailsEvent();
			getConstellationDetails.setConstellationNum(activeConstellation.getFamilyNum());
			replyEvent = MessageUtil.postRequest(getConstellationDetails);
			Collection familyMembers = MessageUtil.compositeToCollection(replyEvent, FamilyConstellationMemberListResponseEvent.class);
		
			if (familyMembers != null && familyMembers.size() > 0) {
				
				Iterator iter = familyMembers.iterator();
				while (iter.hasNext()) {
					FamilyConstellationMemberListResponseEvent member = (FamilyConstellationMemberListResponseEvent) iter.next();
					if(member.isDetentionVisitation()){
						detVisitFamilyMemberTotal++;
					}
				}
			}
		}
	    return detVisitFamilyMemberTotal;
	}

  /*
   * 
   */
  public static FamilyConstellationMemberFinancialResponseEvent 
  		getFamilyConstellationFinancial( String constellationMemberNum )
  {
    GetFamilyConstellationMemberLatestFinancialEvent request = (GetFamilyConstellationMemberLatestFinancialEvent) 
    		EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYCONSTELLATIONMEMBERLATESTFINANCIAL);
    request.setConstelltionMemberId(constellationMemberNum);
    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
    dispatch.postEvent(request);
    CompositeResponse response = (CompositeResponse) dispatch.getReply();
    Map dataMap = MessageUtil.groupByTopic(response);

    if( dataMap != null )
    {
      FamilyConstellationMemberFinancialResponseEvent financial = (FamilyConstellationMemberFinancialResponseEvent) 
      		MessageUtil.filterComposite(response, FamilyConstellationMemberFinancialResponseEvent.class);
      return( financial );
    }
    
    return( null );
  }
  
  /*
   * 
   */
  public static List getGuardians(String juvenileNum)
  {
	  	GetGuardianInfoEvent event = new GetGuardianInfoEvent();
		event.setJuvenileId(juvenileNum);
		// Get PD Response Event
		List coll = MessageUtil.postRequestListFilter(event, GuardianInfoResponseEvent.class);
	    return( coll );
  }

  /**
   * Returns a list of family members with matching ssn.
   * @param memberId
   * @param ssn
   * @return
 */
  public static List searchForSimilarMembers(String memberId, String ssn){
		
		List <FamilyMemberListResponseEvent> filteredMemberList = new ArrayList();
		
		if (ssn != null && !ssn.equals(UIConstants.EMPTY_STRING)
                && !ssn.equals(PDConstants.SSN_666666666)
                && !ssn.equals(PDConstants.SSN_777777777)
                && !ssn.equals(PDConstants.SSN_888888888)
                && !ssn.equals(PDConstants.SSN_999999999))
        {
            GetFamilyMembersEvent event = (GetFamilyMembersEvent) EventFactory
                    .getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERS);
            event.setMemberSsn(ssn);

            CompositeResponse response = MessageUtil.postRequest(event);
            
            Collection coll = MessageUtil.compositeToCollection(response, FamilyMemberListResponseEvent.class);
            List <FamilyMemberListResponseEvent> memberList = CollectionUtil.iteratorToList(coll.iterator());

            FamilyMemberListResponseEvent fmre = null;
            
            for (int i = 0; i < memberList.size(); i++) {
				fmre = memberList.get(i);
				if (!fmre.getMemberNum().equals(memberId)){
					filteredMemberList.add(fmre);
				}
			}
            event = null;
            response = null;
            coll = null;
            memberList = null;
            fmre = null;
        }
		
		return filteredMemberList;
	}
  
  public static JuvenileFamilyForm.Guardian getBenefits( JuvenileFamilyForm.Guardian myGuardian, String memberNumber)
  {
	  myGuardian.setChildSupportReceived(UIUtil.formatCurrency( Double.toString( 0 ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
      myGuardian.setChampus(UIUtil.formatCurrency( Double.toString( 0 ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
      myGuardian.setVABenefits(UIUtil.formatCurrency( Double.toString( 0 ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
      myGuardian.setTanfAfdc(UIUtil.formatCurrency( Double.toString( 0 ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
      myGuardian.setSsi(UIUtil.formatCurrency( Double.toString( 0 ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
      myGuardian.setCountyPaidFC(UIUtil.formatCurrency( Double.toString( 0 ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
      
		GetFamilyMemberBenefitsEvent benefitEvent = (GetFamilyMemberBenefitsEvent) 
	    		EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERBENEFITS);
		
		benefitEvent.setMemberId(memberNumber);
		List benefits = MessageUtil.postRequestListFilter(benefitEvent, FamilyMemberBenefitResponseEvent.class);
		 FamilyMemberBenefitResponseEvent benefitRespEvt;
		if(benefits.size()>0)
		{
			 JuvenileMemberForm.MemberBenefit myBenefit;
			 for(Iterator<FamilyMemberBenefitResponseEvent> benefitsIter = benefits.iterator(); benefitsIter.hasNext(); )
			 {
				benefitRespEvt=benefitsIter.next();
				if(benefitRespEvt!=null)
				{
					String eligibilityType = benefitRespEvt.getEligibilityTypeId(); 
					if(eligibilityType!=null)
					{
						if(eligibilityType.equalsIgnoreCase("CHSP"))
							myGuardian.setChildSupportReceived(UIUtil.formatCurrency( Double.toString( benefitRespEvt.getReceivedAmt() ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
					
						if(eligibilityType.equalsIgnoreCase("CHMP"))
							myGuardian.setChampus(UIUtil.formatCurrency( Double.toString( benefitRespEvt.getReceivedAmt() ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
					
						if(eligibilityType.equalsIgnoreCase("VABN"))
							myGuardian.setVABenefits(UIUtil.formatCurrency( Double.toString( benefitRespEvt.getReceivedAmt() ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
					
						if(eligibilityType.equalsIgnoreCase("TANF"))
							myGuardian.setTanfAfdc(UIUtil.formatCurrency( Double.toString( benefitRespEvt.getReceivedAmt() ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
						
						if(eligibilityType.equalsIgnoreCase("SSI"))
							myGuardian.setSsi(UIUtil.formatCurrency( Double.toString( benefitRespEvt.getReceivedAmt() ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
					
						if(eligibilityType.equalsIgnoreCase("COFC"))
							myGuardian.setCountyPaidFC(UIUtil.formatCurrency( Double.toString( benefitRespEvt.getReceivedAmt() ), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, "" ));
					
					}
				}
			 }
		}
		return myGuardian;
  }
}
