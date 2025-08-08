//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerconditioncompliance\\transactions\\GetNonCompliantEventsCommand.java

package pd.supervision.posttrial.transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.contact.party.reply.PartyListResponseEvent;
import messaging.criminalcase.GetCaseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.party.GetPartiesEvent;
import messaging.party.GetPartyDataEvent;
import messaging.posttrial.GetActiveSupervisionPeriodForSuperviseeEvent;
import messaging.posttrial.GetSuperviseesEvent;
import messaging.supervisionorder.GetActiveSupervisionPeriodEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.MessageUtil;
import naming.PDConstants;
import naming.PDSecurityConstants;
import pd.contact.party.Party;
import pd.criminalcase.CriminalCase;
import pd.security.PDSecurityHelper;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionPeriod;


/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetSuperviseesCommand implements ICommand 
{
   
   /**
    * @roseuid 473B887E0371
    */
   public GetSuperviseesCommand() 
   {

   }
   
   /**
    * @param event
    * @roseuid 473B75560233
    */
   public void execute(IEvent event) 
   { 
	   GetSuperviseesEvent gEvent = (GetSuperviseesEvent) event;
	   String agencyId = PDSecurityHelper.getUserAgencyId();
	   String spn = gEvent.getSpn();
	   String caseNum = gEvent.getCaseNum();
	   if(spn != null && !spn.equals("")){
		   //Party party = Party.find(spn);
		   GetPartyDataEvent getPartyData = new GetPartyDataEvent();
			getPartyData.setSpn(spn);
			getPartyData.setCurrentNameInd("Y");
			Party party = Party.find(getPartyData);
		   if(party != null){
			   PartyListResponseEvent resp = party.getPartyListResponseEvent();
			   GetActiveSupervisionPeriodEvent pEvent = new GetActiveSupervisionPeriodEvent();
			   pEvent.setSpn(resp.getSpn());
			   pEvent.setAgencyId(agencyId);
			   
			   Iterator iterator = SupervisionPeriod.findAll(pEvent);
			   if(iterator.hasNext()){
				   resp.setActive(PDSecurityConstants.YES);
			   }else{
				   resp.setActive(PDSecurityConstants.NO);
			   }
			   MessageUtil.postReply(resp);
		   }
	   } 
	   else if(caseNum != null && !caseNum.equals("")) {			   
		GetCaseEvent caseEvent = new GetCaseEvent();
		caseEvent.setCaseNum(caseNum);
		caseEvent.setCourtDivisionId(gEvent.getCdi());
		caseEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
		Iterator criminalCase = CriminalCase.findAll(caseEvent);
		if(criminalCase.hasNext()) {
			CriminalCase caseAssignment = (CriminalCase) criminalCase.next();			   
			GetPartyDataEvent getPartyData = new GetPartyDataEvent();
			getPartyData.setSpn(caseAssignment.getDefendantId());
			getPartyData.setCurrentNameInd("Y");
			Party party = Party.find(getPartyData);
			if(party != null){
				PartyListResponseEvent resp = party.getPartyListResponseEvent();
				GetActiveSupervisionPeriodEvent pEvent = new GetActiveSupervisionPeriodEvent();
				pEvent.setSpn(resp.getSpn());
				Iterator iterator = SupervisionPeriod.findAll(pEvent);
				if(iterator.hasNext()){
					resp.setActive(PDSecurityConstants.YES);
				}else{
					resp.setActive(PDSecurityConstants.NO);
				}
				MessageUtil.postReply(resp);
			}
		   }
	   }
	   else{
		   GetPartiesEvent partyEvent = new GetPartiesEvent();
		   partyEvent.setBirthDate(gEvent.getDateOfBirth());
		   partyEvent.setCjisNum(gEvent.getCjisNumber());
		   partyEvent.setDriverLicenseNum(gEvent.getDlNumber());
		   partyEvent.setDriverLicenseStateId(gEvent.getDlStateId());
		   partyEvent.setFbiNum(gEvent.getFbiNumber());
		   partyEvent.setFirstName(gEvent.getFirstName());
		   if(gEvent.getLastName() == null || gEvent.getLastName().equals("")){
			   partyEvent.setLastName("*");
		   } else {
			   partyEvent.setLastName(gEvent.getLastName());
		   }
		   
		   partyEvent.setMiddleName(gEvent.getMiddleName());
		   partyEvent.setRaceId(gEvent.getRaceId());
		   partyEvent.setSexId(gEvent.getSexId());
		   partyEvent.setSidNum(gEvent.getSidNumber());
		   partyEvent.setSsn(gEvent.getSsn());
		   
	       IHome home = new Home();
	       MetaDataResponseEvent metaData = (MetaDataResponseEvent) home.findMeta(partyEvent, Party.class);

	       int totalRecords = metaData.getCount();

	       if (totalRecords > PDConstants.SEARCH_LIMIT)
	       {
	           CountInfoMessage infoEvent = new CountInfoMessage();
	           infoEvent.setMessage("error.max.limit.exceeded");
	           MessageUtil.postReply(infoEvent);
	       }
	       else
	       {
	    	   Iterator partyIter = Party.findAll(partyEvent);
	           if (partyIter != null && partyIter.hasNext())
	           {
	               Party party = null;
	               PartyListResponseEvent resp = null;
	               Map map = new HashMap();
	               StringBuffer buf = new StringBuffer();
	               List list = new ArrayList();
	    		   while (partyIter.hasNext())
	               {
	                   party = (Party) partyIter.next();
	                   resp = party.getPartyListResponseEvent();
	                   if(!map.containsKey(resp.getSpn())){
	                	   map.put(resp.getSpn(), resp);
	                	   buf.append("'");
		    			   buf.append(resp.getSpn());
		    			   buf.append("'");
		                   if(partyIter.hasNext()){
		    				   buf.append(",");
		    			   }		                   
		    		   } 
	                   list.add(resp);
	               }
	    		   
	    		   if(list != null && !list.isEmpty()){
	    			   String spnIds = buf.toString();
	    			   if(spnIds.endsWith(",")){
	    				   spnIds = spnIds.substring(0,spnIds.length() - 1);
	    			   }
	    			   GetActiveSupervisionPeriodForSuperviseeEvent reqEvent = new GetActiveSupervisionPeriodForSuperviseeEvent();
	    	    	   reqEvent.setSpn(spnIds);
	    	    	   Iterator itSP = SupervisionOrder.findAll(reqEvent);
	    	    	   map = new HashMap();
	    	    	   while(itSP.hasNext()){
	    	    		   SupervisionOrder so = (SupervisionOrder) itSP.next();
	    	    		   map.put(so.getDefendantId(),so);
	    	    	   }
	    	    	   
	    	    	   for(int i=0;i<list.size();i++){
	    	    		   PartyListResponseEvent response = (PartyListResponseEvent) list.get(i);
	    	    		   
	    	    		   if(map.containsKey(response.getSpn())){
	    	    			   response.setActive(PDSecurityConstants.YES);
	    				   }else{
	    					   response.setActive(PDSecurityConstants.NO);
	    				   }
	    	    		   MessageUtil.postReply(response);
	    	    	   }	  
	    		   }
	            }            
	        }
	   }
   }
  
   /**
    * @param event
    * @roseuid 473B75560240
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 473B75560242
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 473B75560244
    */
   public void update(Object anObject) 
   {
    
   }
}
