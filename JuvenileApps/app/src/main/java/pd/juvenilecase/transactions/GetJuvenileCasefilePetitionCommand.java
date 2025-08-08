//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefilePetitionCommand.java

package pd.juvenilecase.transactions;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import naming.PDCodeTableConstants;
import naming.PDJuvenileWarrantConstants;

import pd.codetable.SimpleCodeTableHelper;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.codetable.criminal.OffenseCode;
import pd.juvenilewarrant.JuvenileOffenderTracking;
import pd.juvenilewarrant.JuvenileOffenderTrackingCharge;
import pd.juvenilewarrant.JuvenileOffenderTrackingCoActor;
import pd.juvenilewarrant.JuvenileOffenderTrackingComplainant;
import pd.juvenilewarrant.JuvenileOffenderTrackingSummaryOfFacts;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;

import messaging.juvenilecase.GetJuvenileCasefilePetitionEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingChargeResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingCoActorResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingComplainantResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingResponseEvent;
import messaging.juvenilewarrant.reply.SummaryOfFactsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.AttributeEvent;
import mojo.km.util.DateUtil;

public class GetJuvenileCasefilePetitionCommand implements ICommand
{

	/**
	 * @roseuid 42A9A3040091
	 */
	public GetJuvenileCasefilePetitionCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42A99B990128
	 */
	public void execute(IEvent event)
	{
		GetJuvenileCasefilePetitionEvent pet = (GetJuvenileCasefilePetitionEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		//<KISHORE>JIMS200059456 : Prog. Referral: Do not display canc. events(PD)-KK
		// There can be multiple charges per daLogNum where as only one charge per petition num
		int count = 0;
		AttributeEvent attEvent = new AttributeEvent();
		if(StringUtils.isNotEmpty(pet.getDaLogNum())){
			attEvent.setAttributeName("daLogNum");
			attEvent.setAttributeValue(pet.getDaLogNum());
		}else if(StringUtils.isNotEmpty(pet.getPetitionNum())){
			attEvent.setAttributeName("petitionNum");
			attEvent.setAttributeValue(pet.getPetitionNum());
		}
		Iterator i = JuvenileOffenderTrackingCharge.findAll(attEvent);
		while (i.hasNext())
		{
			JuvenileOffenderTrackingCharge charge = (JuvenileOffenderTrackingCharge) i.next();
			JuvenileOffenderTrackingChargeResponseEvent resp = PDJuvenileWarrantHelper.getJuvenileOffenderTrackingChargeResponseEvent(charge);			
			getOffenseDetails(charge, resp);
			//Following will always be the same for all the charges in case of daLogNum search
			if(count == 0){
				postSummaryOfFacts(charge.getDaLogNum(), dispatch);
				postDefendantDetails(charge.getDaLogNum(), dispatch);
				postAdultCoActorDetails(charge.getDaLogNum(), dispatch);
				postVictimOrWitnessDetails(charge.getDaLogNum(), dispatch);
				count++;
			}
			dispatch.postEvent(resp);		
		}
	}
	
	/**
	 * Posts the Summary Of Facts by DALOGNUM for a JOT Charge
	 * @param daLogNum
	 * @param dispatch
	 */
	private void postSummaryOfFacts(String daLogNum, IDispatch dispatch)
	{
		AttributeEvent eve = new AttributeEvent();
		eve.setAttributeName("daLogNum");
		eve.setAttributeValue(daLogNum);
		Iterator facts = JuvenileOffenderTrackingSummaryOfFacts.findAll(eve);
		
		SummaryOfFactsResponseEvent fe =  new SummaryOfFactsResponseEvent();
		while (facts.hasNext())
		{
			JuvenileOffenderTrackingSummaryOfFacts f = (JuvenileOffenderTrackingSummaryOfFacts) facts.next();
			fe.addSummaryOfFact(f.getSeqNum(), f.getText().trim());
		}
		dispatch.postEvent(fe);
	}
	
	private JuvenileOffenderTrackingChargeResponseEvent getOffenseDetails(JuvenileOffenderTrackingCharge jtc, JuvenileOffenderTrackingChargeResponseEvent resp)
	{
		//if(jtc.getNcicNum() != null && !jtc.getNcicNum().equalsIgnoreCase(""))
		 if(jtc.getOffenseCodeId()!= null && !jtc.getOffenseCodeId().equalsIgnoreCase(""))
		{ 
			OffenseCode offCode = OffenseCode.find(jtc.getOffenseCodeId());
			if(offCode != null)
			{
				resp.setOffenseDegree(offCode.getDegree());
				resp.setLevelOfOffense(offCode.getLevel());
				JuvenileOffenseCode juvOffCode = JuvenileOffenseCode.find("offenseCode",offCode.getJuvOffenseCode());
				resp.setCharge(juvOffCode.getLongDescription());
			}
		}
		return resp;
		
	}
	
	private void postDefendantDetails(String daLogNum, IDispatch dispatch)
	{
		 JuvenileOffenderTracking jot = JuvenileOffenderTracking.find(daLogNum);
		 JuvenileOffenderTrackingResponseEvent resp = new JuvenileOffenderTrackingResponseEvent();
		 resp.setArrestDate(DateUtil.IntToDate( jot.getArrestDate(), DateUtil.DATE_FMT_2));
		
		 resp.setArrestTime(jot.getArrestTime());
		 resp.setCoDefendants(jot.getCoDefendants());
		 if( jot.getFilingAgency() != null ){
		     
		     resp.setFilingAgencyDesc( SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.STATE_AGENCY, jot.getFilingAgency()));
		     resp.setFilingAgency( jot.getFilingAgency());
		 }
		 StringBuffer buff = new StringBuffer();
		 if("Y".equalsIgnoreCase( jot.getArrestInd() ))
		 	buff.append(PDJuvenileWarrantConstants.DM_ARREST_IND);
		 if("Y".equalsIgnoreCase( jot.getBroughtBack() ))
		 {
		 	if(buff.length()!=0)
		 		buff.append(", ");
		 	buff.append(PDJuvenileWarrantConstants.DM_BROUGHT_BACK);
		 }
		 if("Y".equalsIgnoreCase( jot.getCompKnowsDef() ))
		 {
		 	if(buff.length()!=0)
		 		buff.append(", ");
		 	buff.append(PDJuvenileWarrantConstants.DM_COMP_KNOWS_DEF);
		 }
		 if("Y".equalsIgnoreCase( jot.getAfisNCICId() ))
		 {
		 	if(buff.length()!=0)
		 		buff.append(", ");
		  	buff.append(PDJuvenileWarrantConstants.DM_AFIS_NCIC_ID);
		 }
		 if("Y".equalsIgnoreCase( jot.getLineupInd() ))
		 {
		 	if(buff.length()!=0)
		 		buff.append(", ");
		  	buff.append(PDJuvenileWarrantConstants.DM_LINEUP_IND);
		 }
		 if("Y".equalsIgnoreCase( jot.getOralConfess() ))
		 {
		 	if(buff.length()!=0)
		 		buff.append(", ");
		  	buff.append(PDJuvenileWarrantConstants.DM_ORAL_CONFESS);
		 }
		 if("Y".equalsIgnoreCase( jot.getPhotoArrayId() ))
		 {
		 	if(buff.length()!=0)
		 		buff.append(", ");
		  	buff.append(PDJuvenileWarrantConstants.DM_PHOTO_ARRAY_ID);
		 }
		 if("Y".equalsIgnoreCase( jot.getWrittenConfess() ))
		 {
		 	if(buff.length()!=0)
		 		buff.append(", ");
		  	buff.append(PDJuvenileWarrantConstants.DM_WRITTEN_CONFESS);
		 }
		 if("Y".equalsIgnoreCase( jot.getWitnessKnowsDef() ))
		 {
		 	if(buff.length()!=0)
		 		buff.append(", ");
		  	buff.append(PDJuvenileWarrantConstants.DM_WINTNESS_KNOWS_DEF);
		 }
		 if((jot.getComment1()!= null && !jot.getComment1().equals("")))
		 {
		 	if(buff.length()!=0)
		 		buff.append(", ");
		 	buff.append("Other: "+ jot.getComment1());
		 	if(jot.getComment2()!= null && !jot.getComment2().equals(""))
		 		buff.append(", " + jot.getComment2());
		 	
		 }
		 else
		 {
		 	if(jot.getComment2()!= null && !jot.getComment2().equals(""))
		 	{
		 		if(buff.length()!=0)
			 		buff.append(", ");
		 		buff.append("Other: " + jot.getComment2());
		 	}
		 }
		 resp.setIdentificationType(buff.toString());
		 dispatch.postEvent(resp);
	}
	
	private void postAdultCoActorDetails(String daLogNum, IDispatch dispatch)
	{
		Iterator coActors = JuvenileOffenderTrackingCoActor.findAllByDaLogNum(daLogNum);
		JuvenileOffenderTrackingCoActorResponseEvent coActorResp = new JuvenileOffenderTrackingCoActorResponseEvent();
		while(coActors.hasNext())
		{
			JuvenileOffenderTrackingCoActor actor = (JuvenileOffenderTrackingCoActor)coActors.next();
			coActorResp.addCoActors(actor.getSequenceNum(), actor.getName());
		}
		dispatch.postEvent(coActorResp);
	}
	
	private void postVictimOrWitnessDetails(String daLogNum, IDispatch dispatch)
	{
		Iterator victims = JuvenileOffenderTrackingComplainant.findAllByDaLogNum(daLogNum);
		JuvenileOffenderTrackingComplainantResponseEvent compResp;
		while(victims.hasNext())
		{
			JuvenileOffenderTrackingComplainant comp = (JuvenileOffenderTrackingComplainant)victims.next();
			compResp = new JuvenileOffenderTrackingComplainantResponseEvent();
			compResp.setName(comp.getName());
			compResp.setSequenceNum(comp.getSequenceNum());
			compResp.setDaLogNum(comp.getDaLogNum());
			compResp.setRelationshipToJuvenile(comp.getRelationshipToJuvenile());
			if(comp.getComplainantType()!= null && comp.getComplainantType().equalsIgnoreCase("C"))
				compResp.setAssociationType(PDJuvenileWarrantConstants.DM_ASSOCIATION_TYPE_C);
			else if(comp.getComplainantType()!= null && comp.getComplainantType().equalsIgnoreCase("W"))
				compResp.setAssociationType(PDJuvenileWarrantConstants.DM_ASSOCIATION_TYPE_W);
			dispatch.postEvent(compResp);
		}
	}
	/**
	 * @param event
	 * @roseuid 42A99B990131
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42A99B990133
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42A99B99013A
	 */
	public void update(Object anObject)
	{

	}
}
