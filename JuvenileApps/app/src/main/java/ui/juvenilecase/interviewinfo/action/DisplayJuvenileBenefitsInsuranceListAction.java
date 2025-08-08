package ui.juvenilecase.interviewinfo.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.domintf.IName;
import messaging.interviewinfo.GetJuvenileBenefitsEvent;
import messaging.interviewinfo.GetJuvenileInsuranceEvent;
import messaging.interviewinfo.reply.BenefitsReceivedByResponseEvent;
import messaging.interviewinfo.reply.JuvenileBenefitResponseEvent;
import messaging.interviewinfo.reply.JuvenileInsuranceResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileInterviewInfoHelper;
import ui.juvenilecase.form.JuvenileBenefitsInsuranceForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.form.JuvenileProfileForm;


public class DisplayJuvenileBenefitsInsuranceListAction extends Action
{
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		JuvenileBenefitsInsuranceForm form = (JuvenileBenefitsInsuranceForm)aForm; 
		String forward = UIConstants.SUCCESS; //happy path scenario
		
		form.clear();
		
		//Get Juvenile Number from Header Form 
		String juvenileNumber = "";
	  	JuvenileProfileForm headerForm = UIJuvenileHelper.getHeaderForm(aRequest);

	  	if(headerForm != null && headerForm.getJuvenileNum() != null && headerForm.getJuvenileNum().trim().length() > 0)
	  	{
	  		juvenileNumber = headerForm.getJuvenileNum();
			form.setJuvBenefits(UIJuvenileInterviewInfoHelper.getBenefitsInfoList(juvenileNumber));
			form.setJuvInsurances(getInsuranceInfoList(juvenileNumber));
			form.setJuvNumber(juvenileNumber);
			JuvenileFamilyForm.Constellation currentConstellation = UIJuvenileInterviewInfoHelper.getJuvenileConstellationDetails(juvenileNumber);
			if(currentConstellation != null)
				form.setBenefitsReceivers(getBenefitsReceivedByList(currentConstellation, headerForm.getJuvenileName()));
	  	}
	  	else
	  	{
	  		forward = UIConstants.HANDLE_EXCEPTION;
	  	}
		
		return aMapping.findForward(forward);
		
	}
	
	private Collection getBenefitsReceivedByList(JuvenileFamilyForm.Constellation currentConstellation, String juvName)
	{
		Iterator iter = currentConstellation.getMemberList().iterator();
		ArrayList allBenefitsRecievers = new ArrayList();
		ArrayList receivers = new ArrayList();
		//first one is the Juvenile 
		BenefitsReceivedByResponseEvent receivedBy = new BenefitsReceivedByResponseEvent();		
		IName juvenileName = UIUtil.getNameFromString(juvName);
		receivedBy.setFirstName(juvenileName.getFirstName());	
		receivedBy.setMiddleName(juvenileName.getMiddleName());
		receivedBy.setLastName(juvenileName.getLastName());
		receivedBy.setRelationship( "SELF" );
		receivedBy.setTypeOfPerson(BenefitsReceivedByResponseEvent.PERSON_SELF);
		allBenefitsRecievers.add(receivedBy);
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

	
	private Collection getInsuranceInfoList(String memberNumber)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		// Sending PD Request Event
		GetJuvenileInsuranceEvent event= new GetJuvenileInsuranceEvent();
		event.setJuvenileNum(memberNumber);	   
		dispatch.postEvent(event);	   

		
		//Getting PD Response Event	
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		// Perform Error handling
		MessageUtil.processReturnException(response); 
		
		Collection insurances = MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), JuvenileInsuranceResponseEvent.class );
		
		JuvenileInsuranceResponseEvent responseEvt;
		JuvenileMemberForm.MemberInsurance myInsurance;
		ArrayList myNewList=new ArrayList();
		
		if(insurances!=null && insurances.size()>0)
		{
			Iterator iter=insurances.iterator();
			while(iter.hasNext())
			{
				responseEvt=(JuvenileInsuranceResponseEvent)iter.next();
				if(responseEvt!=null)
				{
					myInsurance = new JuvenileMemberForm.MemberInsurance();
					
					SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

					Date entryDate = responseEvt.getEntryDate();
					if(entryDate != null)
					{
						myInsurance.setEntryDate(format.format(entryDate));
					}
					
					myInsurance.setInsuranceCarrier(responseEvt.getCarrier());
					myInsurance.setInsuranceTypeId(responseEvt.getTypeId());
					myInsurance.setPolicyNumber(responseEvt.getPolicyNum());
					myNewList.add(myInsurance);
				}
			}
		}
		
		Collections.sort(myNewList);
		return myNewList;
		
	}
	
	private String getJuvenileNumber(HttpServletRequest aRequest)
	{
		//Get Juvenile Number from Header Form 
		String juvenileNum = "";
		JuvenileProfileForm headerForm = UIJuvenileHelper.getHeaderForm(aRequest);

		if(headerForm != null)
			juvenileNum = headerForm.getJuvenileNum();
		else
			UIJuvenileHelper.mapCasefileHeaderToJuvHeaderForm(aRequest);

		if(juvenileNum == null || juvenileNum.trim().equals(""))
		{
			JuvenileCasefileForm myCasefileForm=UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
			juvenileNum=myCasefileForm.getJuvenileNum();
		}

		return juvenileNum;
	}
}