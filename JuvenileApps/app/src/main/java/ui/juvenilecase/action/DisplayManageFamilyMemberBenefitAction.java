package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.domintf.IName;
import messaging.family.GetFamilyMemberBenefitsEvent;
import messaging.interviewinfo.reply.BenefitsReceivedByResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberBenefitResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm;

public class DisplayManageFamilyMemberBenefitAction extends LookupDispatchAction
{
	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
  protected Map getKeyMethodMap()
  {
    Map keyMap = new HashMap();
    keyMap.put("button.next", "next");
    keyMap.put("button.cancel", "cancel");
    keyMap.put("button.back", "back");
    keyMap.put("button.link", "next");
    return keyMap;
  }

  /*
   * 
   */
  public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, 
  		HttpServletRequest aRequest, HttpServletResponse aResponse)
  {
    return aMapping.findForward(UIConstants.CANCEL);
  }

  /*
   * 
   */
  public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
  		HttpServletRequest aRequest, HttpServletResponse aResponse)
  {
    return aMapping.findForward(UIConstants.BACK);
  }

  /*
   * 
   */
  public ActionForward next(ActionMapping aMapping, ActionForm aForm, 
  		HttpServletRequest aRequest, HttpServletResponse aResponse)
  {
    JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
    myForm.clearBenefit();
    String memberNum = myForm.getMemberNumber() ;
    
    // Send PD Request Event
    GetFamilyMemberBenefitsEvent event = (GetFamilyMemberBenefitsEvent) 
    		EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERBENEFITS);

    if( memberNum == null || (memberNum.length() == 0)  )
    {
    	myForm.setAction( "" ) ;
			sendToErrorPage(aRequest, "Member Number is invalid");
			return aMapping.findForward(UIConstants.FAILURE);
    }
    
    event.setMemberId( memberNum );
    
    FamilyMemberBenefitResponseEvent responseEvt;
    List myNewList = new ArrayList();

    List benefits = MessageUtil.postRequestListFilter(event, FamilyMemberBenefitResponseEvent.class);
    if( benefits.size() > 0 )
    {
      JuvenileMemberForm.MemberBenefit myBenefit;
      for( Iterator<FamilyMemberBenefitResponseEvent> iter = benefits.iterator();
      		iter.hasNext(); /*empty*/ )
      {
        if( ((responseEvt = iter.next()) != null) &&
        		(myBenefit = UIJuvenileHelper.getJuvMemberFormMemberBenefitFROMBenefitRespEvt(responseEvt)) != null)
        {
          myNewList.add(myBenefit);
        }
      }
    }

    myForm.setBenefitsInfoList(UIJuvenileHelper.sortMemberBenefitsList(myNewList));
    //added for US 27022
    JuvenileFamilyForm familyForm = UIJuvenileHelper.getFamilyForm(aRequest);
    if(familyForm != null)
    {
    	myForm.setBenefitsReceivers(getBenefitsReceivedByList(familyForm.getCurrentActiveConstellation()));
    }
    
    return aMapping.findForward(UIConstants.SUCCESS);
  }
  
  private Collection getBenefitsReceivedByList(JuvenileFamilyForm.Constellation currentConstellation)
	{
		Iterator iter = currentConstellation.getMemberList().iterator();
		ArrayList allBenefitsRecievers = new ArrayList();
		ArrayList receivers = new ArrayList();
		//first one is the Juvenile 
		BenefitsReceivedByResponseEvent receivedBy = new BenefitsReceivedByResponseEvent();		
	
		while(iter.hasNext())
		{
			JuvenileFamilyForm.MemberList member = (JuvenileFamilyForm.MemberList) iter.next();
			receivedBy = new BenefitsReceivedByResponseEvent();
			receivedBy.setFirstName(member.getMemberName().getFirstName());
			receivedBy.setMiddleName(member.getMemberName().getMiddleName());
			receivedBy.setLastName(member.getMemberName().getLastName());			
			receivedBy.setTypeOfPerson(BenefitsReceivedByResponseEvent.PERSON_FAMILY);
			receivedBy.setRelationship(member.getRelationshipToJuv());
			receivers.add(receivedBy);
		}
		Collections.sort(receivers);
		allBenefitsRecievers.addAll(receivers);
		return allBenefitsRecievers;
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

