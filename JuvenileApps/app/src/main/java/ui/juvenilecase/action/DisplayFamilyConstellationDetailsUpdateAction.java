/*
 * Created on Oct 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetFamilyConstellationMemberLatestFinancialEvent;
import messaging.family.SaveFamilyConstellationEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberFinancialResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileFamilyHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileFamilyForm;

/**
 * @author jjose To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayFamilyConstellationDetailsUpdateAction extends LookupDispatchAction
{
	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put( "button.updateActiveConstellation", "updateConstellation" );
		keyMap.put( "button.updateGuardianInfo", "updateGuardianInfo" );
		keyMap.put( "button.next", "next" );
		keyMap.put( "button.removeSelectedMembers", "removeSelectedMember" );
		keyMap.put( "button.cancel", "cancel" );
		keyMap.put( "button.back", "back" );
		keyMap.put( "button.link", "updateConstellation" );
		// keyMap.put("button.finish", "finish");
		return keyMap;
	}

	/*
	 * 
	 */
	public ActionForward updateGuardianInfo( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		JuvenileFamilyForm myFamForm = (JuvenileFamilyForm)aForm;
		resetActiveConstellation( myFamForm );
		JuvenileFamilyForm.Constellation currentConstellation = myFamForm.getCurrentConstellation();
		
		currentConstellation.setGuardiansList( new ArrayList() );
		Collection members = currentConstellation.getMemberList();
		JuvenileFamilyForm.MemberList myMember = null;
		
		boolean foundMember = false;
		Iterator<JuvenileFamilyForm.MemberList> iter = members.iterator();
		while(iter.hasNext())
		{
			myMember = iter.next();
			/* The delete trigger is used to trip the guardian being checked as
			 * this check box is used as a multi-purpose checkbox on the screen
			 */
			if( myMember.isDelete() )
			{
				myMember.setDelete( false );
				foundMember = true;
				break;
			}
		}
		
		if( !foundMember )
		{
			return aMapping.findForward( UIConstants.FAILURE );
		}

		JuvenileFamilyForm.Guardian myGuardian = new JuvenileFamilyForm.Guardian();
		myGuardian.setMemberNumber( myMember.getMemberNumber() );
		myGuardian.setConstellationMemberId( myMember.getFamilyConstellationMemberNum() );
		myGuardian.setName( myMember.getMemberName() );
		myGuardian.setRelationshipToJuv( myMember.getRelationshipToJuv() );
		myGuardian.setDeceased( myMember.getDeceasedYesNo() );
		
		// Insert new functionality here
		GetFamilyConstellationMemberLatestFinancialEvent request = (GetFamilyConstellationMemberLatestFinancialEvent)
				EventFactory.getInstance( JuvenileFamilyControllerServiceNames.GETFAMILYCONSTELLATIONMEMBERLATESTFINANCIAL );
		
		request.setConstelltionMemberId( myGuardian.getConstellationMemberId() );
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
		dispatch.postEvent( request );
		CompositeResponse response = (CompositeResponse)dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic( response );
		
		if( dataMap != null )
		{
			ArrayList guardList = new ArrayList();
			String topic = myGuardian.getConstellationMemberId() + "_" + PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_FINANCIAL_TOPIC;
			
			Collection guardians = (Collection)dataMap.get( topic );
			Iterator<FamilyConstellationMemberFinancialResponseEvent> iterGuard = guardians.iterator();
			while( iterGuard.hasNext() )
			{
				FamilyConstellationMemberFinancialResponseEvent financial = iterGuard.next();

				JuvenileFamilyForm.Guardian currGuardian = 
						UIJuvenileHelper.getFamilyFormGuardianFROMFamilyConstellationFinancialRespEvt( financial );
				guardList.add( currGuardian );
			}

			if( guardList != null && guardList.size() > 0 )
			{
				currentConstellation.setGuardiansList( UIJuvenileHelper.sortGuardianList( guardList ) );
				JuvenileFamilyForm.Guardian mostRecentGuardian = (JuvenileFamilyForm.Guardian)
						((ArrayList)currentConstellation.getGuardiansList()).get( 0 );

				myGuardian.setChildSupportPayorName( mostRecentGuardian.getChildSupportPayorName() );
				myGuardian.setFinancialId( mostRecentGuardian.getFinancialId() );
				myGuardian.setFoodStamps( mostRecentGuardian.getFoodStamps() );
				myGuardian.setSsi( mostRecentGuardian.getSsi() );
				myGuardian.setGroceryExpenses( mostRecentGuardian.getGroceryExpenses() );
				myGuardian.setIntangibleValue( mostRecentGuardian.getIntangibleValue() );

				myGuardian.setLifeInsurancePremium( mostRecentGuardian.getLifeInsurancePremium() );
				myGuardian.setMedicalExpenses( mostRecentGuardian.getMedicalExpenses() );
				myGuardian.setNumberInFamily( mostRecentGuardian.getNumberInFamily() );
				myGuardian.setNumberLivingInHome( mostRecentGuardian.getNumberLivingInHome() );
				myGuardian.setNumberOfDependents( mostRecentGuardian.getNumberOfDependents() );
				myGuardian.setOtherIncome( mostRecentGuardian.getOtherIncome() );

				myGuardian.setPropertyValue( mostRecentGuardian.getPropertyValue() );
				myGuardian.setRentExpenses( mostRecentGuardian.getRentExpenses() );
				myGuardian.setSavings( mostRecentGuardian.getSavings() );
				myGuardian.setSchoolExpenses( mostRecentGuardian.getSchoolExpenses() );
				myGuardian.setTanfAfdc( mostRecentGuardian.getTanfAfdc() );
				myGuardian.setTotalExpenses( mostRecentGuardian.getTotalExpenses() );
				myGuardian.setUtilitiesExpenses( mostRecentGuardian.getUtilitiesExpenses() );
				myGuardian.setChildSupportPaid( mostRecentGuardian.getChildSupportPaid() );
				myGuardian.setChildSupportReceived( mostRecentGuardian.getChildSupportReceived() );
				myGuardian.setOtherAdultIncome( mostRecentGuardian.getOtherAdultIncome() );
				myGuardian.setNotes( mostRecentGuardian.getNotes() );
			}
		}

		// END of new stuff
		myFamForm.setCurrentGuardian( myGuardian );

		return( aMapping.findForward( "updateGuardianInfo" ) );
	}

	/*
	 * 
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return( aMapping.findForward( UIConstants.CANCEL ) );
	}

	/*
	 * 
	 */
	public ActionForward next( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		JuvenileFamilyForm myFamForm = (JuvenileFamilyForm)aForm;
		JuvenileFamilyForm.Constellation myConstellation = myFamForm.getCurrentConstellation();
		Collection members = myConstellation.getMemberList();
		
		JuvenileFamilyForm.MemberList myMember;
		// Reset all existing family member information that should not exist

		boolean setRelationship = true;
		boolean setInvolvmentLevel = true;

		Iterator<JuvenileFamilyForm.MemberList> iter = members.iterator();
		while(iter.hasNext())
		{
			myMember = iter.next();
			myMember.setDelete( false );
			
			if( myMember.getRelationshipToJuvId() == null || 
					(myMember.getRelationshipToJuvId().trim().length() == 0) )
			{
				setRelationship = false;
			}
			
			if( myMember.getInvolvementLevelId() == null || 
					(myMember.getInvolvementLevelId().trim().length() == 0) )
			{
				setInvolvmentLevel = false;
			}
		} // while

		if( !setRelationship )
		{
			sendToErrorPage( aRequest, "error.mustHaveRelationToJuv" );
			return( aMapping.findForward( UIConstants.FAILURE ) );
		}
		
		if( !setInvolvmentLevel )
		{
			sendToErrorPage( aRequest, "error.mustHaveInvolvementLevel" );
			return( aMapping.findForward( UIConstants.FAILURE ) );
		}
		
		myFamForm.setSecondaryAction( UIConstants.SUMMARY );
		
		return( aMapping.findForward( UIConstants.SUMMARY_SUCCESS ) );
	}

	/*
	 * 
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return( aMapping.findForward( UIConstants.BACK ) );
	}

	/*
	 * 
	 */
	public ActionForward updateConstellation( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		JuvenileFamilyForm myFamForm = (JuvenileFamilyForm)aForm;
		resetActiveConstellation( myFamForm );

		String fNum = myFamForm.getCurrentConstellation().getFamilyNumber() ;
		if( fNum != null && (fNum.trim().length() > 0) )
		{
			UIJuvenileFamilyHelper.getCurrentConstFamilyMembers( myFamForm );
		}
		else
		{
			sendToErrorPage( aRequest, "error.generic", "The Family ID value is missing" );
			return( aMapping.findForward( UIConstants.FAILURE) );
		}

		return( aMapping.findForward( UIConstants.SUCCESS ) );
	}

	/*
	 * 
	 */
	private void resetActiveConstellation( JuvenileFamilyForm myFamForm )
	{
		myFamForm.setCurrentConstellation( myFamForm.getCurrentActiveConstellation() );
	}

	/*
	 * 
	 */
	public ActionForward removeSelectedMembers( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		JuvenileFamilyForm myFamForm = (JuvenileFamilyForm)aForm;
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
		
		// Get to be deleted items
		JuvenileFamilyForm.Constellation currentConstellation = myFamForm.getCurrentConstellation();

		// Send PD Request Event
		SaveFamilyConstellationEvent event = (SaveFamilyConstellationEvent)
				EventFactory.getInstance( JuvenileFamilyControllerServiceNames.SAVEFAMILYCONSTELLATION );
		event.setJuvNum( myFamForm.getJuvenileNumber() );
		event.setConstellationNum( currentConstellation.getFamilyNumber() );

		boolean atLeastOneToRemove = false;
		JuvenileFamilyForm.MemberList myMember;
		Collection members = currentConstellation.getMemberList();
		Iterator<JuvenileFamilyForm.MemberList> iter = members.iterator();
		while( iter.hasNext() )
		{
			myMember = iter.next();
			
			if( myMember.isDelete() )
			{
				if( myMember.getFamilyConstellationMemberNum() == null || 
						(myMember.getFamilyConstellationMemberNum().trim().length() == 0) )
				{
					iter.remove();
				}
				else
				{
					atLeastOneToRemove = true;
					event.addRequest( UIJuvenileHelper.getSaveFamilyConstellationMemberInfoEvent( myMember ) );
				}
			}
		}
		
		if( atLeastOneToRemove )
		{
			dispatch.postEvent( event );

			// Get PD Response Event
			CompositeResponse response = (CompositeResponse)dispatch.getReply();

			MessageUtil.processReturnException( response ); // Perform Error handling
			iter = members.iterator();
			while( iter.hasNext() )
			{
				myMember = iter.next();
				if( (myMember.isDelete()) )
				{
					iter.remove();
				}
			}
		}

		return( aMapping.findForward( "removeMemberSuccess" ) );
	}

	/*
	 * 
	 */
	public ActionForward removeSelectedMember( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		JuvenileFamilyForm myFamForm = (JuvenileFamilyForm)aForm;
		JuvenileFamilyForm.Constellation currentConstellation = myFamForm.getCurrentConstellation();
		String selectedValue = myFamForm.getSelectedValue() ;
		JuvenileFamilyForm.MemberList myMember;

		Collection members = currentConstellation.getMemberList();
		Iterator<JuvenileFamilyForm.MemberList> iter = members.iterator();
		while(iter.hasNext())
		{
			myMember = iter.next();
			if( (myMember.getMemberNumber().equalsIgnoreCase( selectedValue )) )
			{
				myMember.setDelete( true );
			}
		}
		
		return removeSelectedMembers( aMapping, aForm, aRequest, aResponse );
	}

	/**
	 * @param aRequest
	 */
	private void sendToErrorPage( HttpServletRequest aRequest, String msg )
	{
		ActionErrors errors = new ActionErrors();
		errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( msg ) );
		saveErrors( aRequest, errors );
	}

	/**
	 * @param aRequest
	 */
	private void sendToErrorPage( HttpServletRequest aRequest, String msg, String msg2 )
	{
		ActionErrors errors = new ActionErrors();
		errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( msg, msg2 ) );
		saveErrors( aRequest, errors );
	}
}// END CLASS
