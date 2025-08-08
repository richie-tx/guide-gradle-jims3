
/*
 * Created on Jun 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.caseplan.transactions;

import java.util.Iterator;

import messaging.caseplan.GetCaseplanDetailsEvent;
import messaging.caseplan.GetGenerateCaseplanDetailsEvent;
import messaging.caseplan.reply.CaseplanDetailsResponseEvent;
import messaging.caseplan.reply.SaveCaseplanDataResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.caseplan.CasePlan;
import pd.juvenilecase.caseplan.CaseplanHelper;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetGenerateCaseplanDetailsCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {

		
		
		GetGenerateCaseplanDetailsEvent request = (GetGenerateCaseplanDetailsEvent)event;
		String casefileID = request.getCasefileId();
   		//String casefileID = request.getSupervisionNumber();
		SaveCaseplanDataResponseEvent cpResponse = new SaveCaseplanDataResponseEvent();
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		JuvenileCasefile cf=JuvenileCasefile.find(casefileID);
   		CasePlan cp=null;
   		if(cf==null || cf.getCaseplanId()==null || cf.getCaseplanId().equals("")){
   			
   		}
   		else{
   			cp = CasePlan.find(cf.getCaseplanId());
   		}
   		if(cp == null) {// the caseplan has not been created yet!
   			cpResponse.setCaseplanID(null);
   			dispatch.postEvent(cpResponse);
   			return;
   		}
   		cpResponse.setCaseplanID(cp.getOID().toString());
   		cpResponse.setPriorServices(cp.getPriorServices());
		cpResponse.setContactInfo(cp.getContactInfo());
		cpResponse.setSupLevelId(cp.getSupLevelId());
		//added for user story 11146
		cpResponse.setOthername(cp.getOthername());
		cpResponse.setChildDtNotified(DateUtil.dateToString(cp.getChildDtNotified(),"MM/dd/yyyy"));
		cpResponse.setFamilyDtNotified(DateUtil.dateToString(cp.getFamilyDtNotified(),"MM/dd/yyyy"));
		cpResponse.setCaregiverDtNotified(DateUtil.dateToString(cp.getCaregiverDtNotified(),"MM/dd/yyyy"));
		cpResponse.setOtherDtNotified(DateUtil.dateToString(cp.getOtherDtNotified(),"MM/dd/yyyy"));
		cpResponse.setChildNotifMethod(cp.getChildNotifMethod());
		cpResponse.setFamilyNotifMethod(cp.getFamilyNotifMethod());
		cpResponse.setCaregiverNotifMethod(cp.getCaregiverNotifMethod());
		cpResponse.setOtherNameNotifMethod(cp.getOtherNameNotifMethod());
		cpResponse.setChildDtOfParticipation(DateUtil.dateToString(cp.getChildDtOfParticipation(),"MM/dd/yyyy"));
		cpResponse.setFamilyDtOfParticipation(DateUtil.dateToString(cp.getFamilyDtOfParticipation(),"MM/dd/yyyy"));
		cpResponse.setCaregiverDtOfParticipation(DateUtil.dateToString(cp.getCaregiverDtOfParticipation(),"MM/dd/yyyy"));
		cpResponse.setOtherNameDtOfParticipation(DateUtil.dateToString(cp.getOtherNameDtOfParticipation(),"MM/dd/yyyy"));
		cpResponse.setChildMailedDt(DateUtil.dateToString(cp.getChildMailedDt(),"MM/dd/yyyy"));
		cpResponse.setFamilyMailedDt(DateUtil.dateToString(cp.getFamilyMailedDt(),"MM/dd/yyyy"));
		cpResponse.setCaregiverMailedDt(DateUtil.dateToString(cp.getCaregiverMailedDt(),"MM/dd/yyyy"));
		cpResponse.setOtherNameMailedDt(DateUtil.dateToString(cp.getOtherNameMailedDt(),"MM/dd/yyyy"));
		//added for User story 11191 Add Title IV in caseplan
		cpResponse.setJuvFosterCareCandidate(cp.isJuvFosterCareCandidate());
		cpResponse.setSocialHistDated(DateUtil.dateToString(cp.getSocialHistDated(), "MM/dd/yyyy"));
		cpResponse.setPsychologicalRepDated(DateUtil.dateToString(cp.getPsychologicalRepDated(),"MM/dd/yyyy"));
		cpResponse.setRiskAssesmentDated(DateUtil.dateToString(cp.getRiskAssesmentDated(),"MM/dd/yyyy"));
		cpResponse.setTitleIVEComment(cp.getTitleIVEComment());
		cpResponse.setDtDeterminationMade(DateUtil.dateToString(cp.getDtDeterminationMade(),"MM/dd/yyyy"));
		cpResponse.setOtherDated(DateUtil.dateToString(cp.getOtherDated(),"MM/dd/yyyy"));
		cpResponse.setExplanation(cp.getExplanation());
   		dispatch.postEvent(cpResponse);
		
		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub

	}

}
