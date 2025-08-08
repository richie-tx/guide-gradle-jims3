/*
 * Created on Sep 20, 2005
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
import messaging.family.GetFamilyConstellationDetailsEvent;
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
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayFamilyConstellationDetailsAction extends LookupDispatchAction
{

	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
			  {
				   return constellationDetails(aMapping,aForm,aRequest,aResponse);
			  }

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.details", "constellationDetails");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
	}
	
	public ActionForward cancel(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
			return forward;
		}
		
	public ActionForward back(
				ActionMapping aMapping,
				ActionForm aForm,
				HttpServletRequest aRequest,
				HttpServletResponse aResponse)
			{
				ActionForward forward = aMapping.findForward(UIConstants.BACK);
				return forward;
			}
			
	public ActionForward constellationDetails(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JuvenileFamilyForm myFamForm=(JuvenileFamilyForm)aForm;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		// Sending PD Request Event
		GetFamilyConstellationDetailsEvent event= 
				   (GetFamilyConstellationDetailsEvent)EventFactory.getInstance(
				   JuvenileFamilyControllerServiceNames.GETFAMILYCONSTELLATIONDETAILS);
		event.setConstellationNum(myFamForm.getSelectedValue());	
		dispatch.postEvent(event);	   

		// Getting PD Response Event	
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		// Perform Error handling
		MessageUtil.processReturnException(response); 
		Map dataMap = MessageUtil.groupByTopic(response);
		if (dataMap != null)
		{
			Collection guardians=new ArrayList();
			Collection familiesMembers  = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_LIST_TOPIC);
			JuvenileFamilyForm.Constellation newFamily=new JuvenileFamilyForm.Constellation();
			newFamily.setFamilyNumber(myFamForm.getSelectedValue());
			myFamForm.setCurrentConstellation(newFamily);
			UIJuvenileHelper.setJuvFamilyFormFROMMemberListRespEvt(newFamily,familiesMembers);
			Collection familiesTraits  = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_TRAIT_LIST_TOPIC);
			UIJuvenileHelper.setJuvFamilyFormCurConstFROMTraitListRespEvt(newFamily,familiesTraits);
			
			Iterator iter =  newFamily.getMemberList().iterator();
			  while(iter.hasNext())
			  {
					JuvenileFamilyForm.MemberList myMember = (JuvenileFamilyForm.MemberList)iter.next();			
					String famConstellationMemberNum = myMember.getFamilyConstellationMemberNum();
					String topic = famConstellationMemberNum
									+ "_"
									+ PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_FINANCIAL_TOPIC;
		
					Collection financials  = (Collection) dataMap.get(topic);
					if(financials != null)
					{
						Iterator finacialIter = financials.iterator();
						while(finacialIter.hasNext())
						{
							FamilyConstellationMemberFinancialResponseEvent financial = 
								(FamilyConstellationMemberFinancialResponseEvent) finacialIter.next();
							JuvenileFamilyForm.Guardian myGuardian=UIJuvenileHelper.getFamilyFormGuardianFROMFamilyConstellationFinancialRespEvt(financial);
							myGuardian.setMemberNumber(myMember.getMemberNumber());
							myGuardian.setConstellationMemberId(myMember.getFamilyConstellationMemberNum());
							myGuardian.setName(myMember.getMemberName());
							myGuardian.setPrimaryCareGiver(myMember.isPrimaryCareGiver());	// 11063 primary caregiver
							myGuardian.setPrimaryContact(myMember.getPrimaryContactAsStr());
							myGuardian.setRelationshipToJuv(myMember.getRelationshipToJuv());
							myGuardian.setDeceased(myMember.getDeceasedYesNo());
							myGuardian.setIncarcerated(myMember.getIncarceratedYesNo());
							UIJuvenileFamilyHelper.getEmploymentMemberInfo(myMember.getMemberNumber(),myGuardian);
							myGuardian.setInHomeStatus(myMember.isInHomeStatus());
							myGuardian.setNumberInFamily(myFamForm.getCurrentActiveConstellation().getFamilyNumber());
							
							 //get the benefits information for the primary guardian - US 27023
					        //myGuardian=UIJuvenileFamilyHelper.getBenefits(myGuardian, myMember.getMemberNumber()); - taken out for Bug #51352
							guardians.add(myGuardian);			
						}
					}
					myFamForm.getCurrentConstellation().setGuardiansList(UIJuvenileHelper.sortGuardianList((ArrayList)guardians));
			  }
		}
		
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
	}
				
	/**
	* @param aRequest
	*/
   private void sendToErrorPage(HttpServletRequest aRequest, String msg)
   {
	   ActionErrors errors = new ActionErrors();
	   errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
	   saveErrors(aRequest, errors);
   }
	}// END CLASS
