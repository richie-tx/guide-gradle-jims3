/*
 * Created on Oct 17, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administercasenotes;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.administercasenotes.GetCasenoteConditionByOrderIdsEvent;
import messaging.administercasenotes.GetCasenoteNCConditionByOrderIdsEvent;
import messaging.administercasenotes.GetSpnSplitCasenotesByCasenoteIdEvent;
import messaging.spnsplit.GetOrderCaseNotesEvent;
import messaging.spnsplit.ProcessSpnSplitEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import naming.SupervisionConstants;
import pd.commonfunctionality.spnconsolidation.ISpnHandler;
import pd.commonfunctionality.spnsplit.SpnSplitException;
import pd.supervision.administercaseload.CaseAssignment;
import pd.supervision.administercaseload.Supervisee;
import pd.supervision.administercompliance.NonComplianceEventCasenote;
import pd.supervision.administerprogramreferrals.CSProgramReferral;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderPeriod;

/**
 * @author ryoung
 *
 */
public class SpnCasenotesHandler implements ISpnHandler {


	
	/* (non-Javadoc)
	 * @see pd.commonfunctionality.spnconsolidation.ISpnHandler#consolidate(java.lang.String, java.lang.String)
	 */
	public void consolidate(String aliasSpn, String baseSpn) 
	{
        String padded_alias_spn = padSpn(aliasSpn);
        String padded_base_spn = padSpn(baseSpn);        

			//retrieve casenotes for alias SPN
        List<Casenote> alias_casenotes = 
    			CollectionUtil.iteratorToList(Casenote.findAll("superviseeId", padded_alias_spn));

        	//transfer casenotes to base SPN
        try {
			transferCasenotes(alias_casenotes, padded_base_spn, null, null, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}//end of consolidate()
	
	/**
	 * Transfer casenotes to the given SPN
	 * @param fromCasenotes
	 * @param toSpn
	 */
	private void transferCasenotes(List<Casenote> fromCasenotes, 
			String toSpn, Hashtable<String, String> fromOrders, ProcessSpnSplitEvent split_event, boolean transAll) throws Exception
	{
		String target_supervision_period = "";
    	//loop through all alias casenotes and reset supervisee ID to base 
        int num_casenotes = fromCasenotes.size();
        for (int i=0;i<num_casenotes;i++)
        {
        		//reset defendant ID of from casenote to toSpn
        	Casenote from_casenote = fromCasenotes.get(i);
        	from_casenote.setSuperviseeId(toSpn);
        	
        		//check if this order has been given a new supervision period
        	if ( fromOrders != null )
        	{
            	target_supervision_period = fromOrders.get(from_casenote.getSupervisionOrderId());
		    	if (target_supervision_period != null)
		    	{
		    		from_casenote.setSupervisionPeriodId(target_supervision_period);
		    		
		    	} else {
		    		
		    		target_supervision_period = split_event.getErroneousOrderSupervisionPeriodMap()
		    				.get(from_casenote.getSupervisionOrderId());
		    		
		    	}
	
        	}
        	//explicity bind to DB
        	from_casenote.bind();
        }  
        
     // RRY moved this to process all casenotes for the valid spn
    	if ( transAll && target_supervision_period != null ){
    		
    		//retrieve casenotes for alias SPN
            List<Casenote> base_casenotes = 
        			CollectionUtil.iteratorToList(Casenote.findAll("superviseeId", toSpn));
            
            for ( int x =0; x < base_casenotes.size(); x++ ){
            	
            	Casenote old_casenote = base_casenotes.get( x );
            	old_casenote.setSupervisionPeriodId(target_supervision_period);
            }
            
    	}
        		
	}//end of transferCasenotes()

	public static String padSpn(String aSpn)
	{
		String spn = aSpn;
		if (spn != null && !spn.trim().equals(""))
		{
			while (spn.length() < 8)
			{
				spn = "0" + spn;
			}
		}
		return spn;		
	}

    /* (non-Javadoc)
     * @see pd.commonfunctionality.spnconsolidation.ISpnHandler#split(mojo.km.messaging.IEvent)
     */
    public void split(IEvent processSpnSplitEvent) throws Exception
    {
        ProcessSpnSplitEvent spn_split_event = 
        	(ProcessSpnSplitEvent)processSpnSplitEvent;

        //retrieve SPNs of erroneous and valid spins
        String erroneous_defendant_id = padSpn(spn_split_event.getErroneousSpn());
        String valid_defendant_id = padSpn(spn_split_event.getValidSpn()); 
               
        SpnSplitException ex = null;
        String criminalCaseIds = "";
        Map criminalCaseIdsMap = new HashMap();
    	
        if(spn_split_event.getSpnSplitExceptionId() != null && !spn_split_event.getSpnSplitExceptionId().equalsIgnoreCase("")){
        	ex = SpnSplitException.find(spn_split_event.getSpnSplitExceptionId());
        	if(ex != null){
	        	erroneous_defendant_id = padSpn(ex.getErrDefendantId());
	            valid_defendant_id = padSpn(ex.getValidDefendantId()); 	
	            
	            criminalCaseIds = ex.getCriminalCaseIds();
	            String[] crimialCaseIdsArray = criminalCaseIds.split(",");
	            List list = new ArrayList();
	            for(int i=0;i<crimialCaseIdsArray.length;i++){
	            	list.add(crimialCaseIdsArray[i]);
	            }
	            spn_split_event.setCases(list);
        	}else{
        		// return an exception
        	}
        }else{
        	List list = spn_split_event.getCases();
        	StringBuffer st = new StringBuffer();
        	for(int i=0;i<list.size();i++){
        		String criminalCaseId = (String) list.get(i);
        		st.append(criminalCaseId);
            	if(i != list.size()-1){
            		st.append(",");
            	}
            	criminalCaseIdsMap.put(criminalCaseId, criminalCaseId);
        	}
        	criminalCaseIds = st.toString();
        }
        spn_split_event.setValidSpn(valid_defendant_id);
        spn_split_event.setErroneousSpn(erroneous_defendant_id);         
        
    	//retrieve all erroneous orders for selected cases
        List<SupervisionOrder> erroneous_orders = CollectionUtil.iteratorToList(
        		SupervisionOrder.GetOrdersForCaseIds(spn_split_event));

        List<String> order_ids = new ArrayList<String>();
        Map orderMap = new HashMap();
        StringBuffer orderIds = new StringBuffer();
        for (int i=0;i<erroneous_orders.size();i++)
        {
        	//comprise list of all order IDs in selected cases
        	order_ids.add(erroneous_orders.get(i).getOID());
        	orderIds.append(erroneous_orders.get(i).getOID());
        	if(i != erroneous_orders.size() - 1){
        		orderIds.append(",");
        	}
        	if(!orderMap.containsKey(erroneous_orders.get(i).getCriminalCaseId())){
        		orderMap.put(erroneous_orders.get(i).getCriminalCaseId() + erroneous_orders.get(i).getOID(), erroneous_orders.get(i).getCriminalCaseId()+ erroneous_orders.get(i).getOID());
        	}
        }        
                
        //create event for retrieving list of casenotes for given order IDs
        GetOrderCaseNotesEvent order_case_notes_event = new GetOrderCaseNotesEvent();
        order_case_notes_event.setOrderIds(order_ids);
        
        //retrieve all erroneous casenotes for selected cases
        List erroneous_casenotes = CollectionUtil.iteratorToList(Casenote.findAll(order_case_notes_event));
        
  	    Iterator orderIter = SupervisionOrder.findAll(SupervisionConstants.DEFENDANT_ID,erroneous_defendant_id);
        Map notCheckedCases = new HashMap();
        int i=0;
        while(orderIter.hasNext()){
        	SupervisionOrder so = (SupervisionOrder) orderIter.next();        	
        	if(!orderMap.containsKey(so.getCriminalCaseId() + so.getOID())){
        		if(!notCheckedCases.containsKey(so.getCriminalCaseId()+ so.getOID())){
        			notCheckedCases.put(so.getCriminalCaseId() + so.getOID(), so.getCriminalCaseId() + so.getOID());
        		}
        	}
        	i++;
        }
        
       
        if(spn_split_event.getSpnSplitExceptionId() == null || spn_split_event.getSpnSplitExceptionId().equalsIgnoreCase("")){
        	method1(erroneous_casenotes, valid_defendant_id, erroneous_defendant_id, orderIds.toString(), spn_split_event.getValidOrderSupervisionPeriodMap(), spn_split_event , orderMap, notCheckedCases, criminalCaseIds, criminalCaseIdsMap, i);
        }else{
    		if(SupervisionConstants.ERRORCODE_TRCNOTES.equalsIgnoreCase(ex.getErrorCodeId())){
    			method1(erroneous_casenotes, valid_defendant_id, erroneous_defendant_id, orderIds.toString(), spn_split_event.getValidOrderSupervisionPeriodMap(), spn_split_event , orderMap, notCheckedCases, criminalCaseIds, criminalCaseIdsMap, i);
    		}else if(SupervisionConstants.ERRORCODE_CONDCNOTES.equalsIgnoreCase(ex.getErrorCodeId())){
    			method2(valid_defendant_id, erroneous_defendant_id, orderIds.toString(), orderMap, notCheckedCases, criminalCaseIds,criminalCaseIdsMap,i);
    		}else if(SupervisionConstants.ERRORCODE_NCCNOTES.equalsIgnoreCase(ex.getErrorCodeId())){
    			method3(valid_defendant_id, erroneous_defendant_id, orderIds.toString(), orderMap, notCheckedCases, criminalCaseIds,criminalCaseIdsMap,i);
    		}else if(SupervisionConstants.ERRORCODE_CASEASSIGN.equalsIgnoreCase(ex.getErrorCodeId())){
    			method4(valid_defendant_id, erroneous_defendant_id, criminalCaseIds, orderMap,criminalCaseIdsMap);
    		}else if(SupervisionConstants.ERRORCODE_PROGREFREL.equalsIgnoreCase(ex.getErrorCodeId())){
    			method5(valid_defendant_id, erroneous_defendant_id, criminalCaseIds,criminalCaseIdsMap);
    		}
        }
        if(ex != null){
        	ex.setStatusId(SupervisionConstants.INACTIVE_STATUS);
        }        
        
        Supervisee supervisee = Supervisee.findByDefendantId(valid_defendant_id);
        if(supervisee == null){
        	supervisee = new Supervisee();
    		supervisee.setDefendantId(valid_defendant_id);
        }
    }//end of split()
    
    private void method1(List erroneous_casenotes, String validDefendantId, String errDefendantId, String orderIds, Hashtable erroneousOrderSupervisionPeriodMap, ProcessSpnSplitEvent split_event, Map orderMap, Map notCheckedCases, String criminalCaseIds, Map criminalCaseIdsMap, int i) throws Exception{
        String errorCodeId = SupervisionConstants.ERRORCODE_TRCNOTES;
    	try{
            //transfer casenotes to valid SPN
    	    transferCasenotes(erroneous_casenotes, validDefendantId, erroneousOrderSupervisionPeriodMap, split_event, true);
	        
    	    errorCodeId = SupervisionConstants.ERRORCODE_CONDCNOTES;
    	    // Order Condition
			processCasenoteCondition(validDefendantId, errDefendantId, orderIds, orderMap, notCheckedCases, i);		
	
			errorCodeId = SupervisionConstants.ERRORCODE_NCCNOTES;
			// order noncompliance condition
			processCasenoteNCCondition(validDefendantId, errDefendantId, orderIds, orderMap, notCheckedCases, i);  		
	        
			errorCodeId = SupervisionConstants.ERRORCODE_CASEASSIGN;
			//process case Assignment
			processCaseAssignment(errDefendantId, validDefendantId, orderMap);
	
			errorCodeId = SupervisionConstants.ERRORCODE_PROGREFREL;
			// Program Referral Processing
			processProgramReferral(errDefendantId, validDefendantId, criminalCaseIdsMap); 
        }catch(Exception e){
        	SpnSplitException.create(errDefendantId, validDefendantId, e.toString(), errorCodeId, criminalCaseIds, SupervisionConstants.ACTIVE_STATUS);
            throw e;
        }
    }
    
    private void method2(String validDefendantId, String errDefendantId, String orderIds, Map orderMap, Map notCheckedCases, String criminalCaseIds, Map criminalCaseIdsMap, int i) throws Exception{
        String errorCodeId = SupervisionConstants.ERRORCODE_CONDCNOTES;
    	try{
    	    // Order Condition
			processCasenoteCondition(validDefendantId, errDefendantId, orderIds, orderMap, notCheckedCases, i);		
	
			errorCodeId = SupervisionConstants.ERRORCODE_NCCNOTES;
			// order noncompliance condition
			processCasenoteNCCondition(validDefendantId, errDefendantId, orderIds, orderMap, notCheckedCases, i);  		
	        
			errorCodeId = SupervisionConstants.ERRORCODE_CASEASSIGN;
			//process case Assignment
			processCaseAssignment(errDefendantId, validDefendantId, orderMap);
	
			errorCodeId = SupervisionConstants.ERRORCODE_PROGREFREL;
			// Program Referral Processing
			processProgramReferral(errDefendantId, validDefendantId, criminalCaseIdsMap); 
        }catch(Exception e){
        	SpnSplitException.create(errDefendantId, validDefendantId, e.toString(), errorCodeId, criminalCaseIds, SupervisionConstants.ACTIVE_STATUS);
            throw e;
        }
    }
    
    private void method3(String validDefendantId, String errDefendantId, String orderIds, Map orderMap, Map notCheckedCases, String criminalCaseIds, Map criminalCaseIdsMap, int i) throws Exception{
        String errorCodeId = SupervisionConstants.ERRORCODE_NCCNOTES;
    	try{
			// order noncompliance condition
			processCasenoteNCCondition(validDefendantId, errDefendantId, orderIds, orderMap, notCheckedCases, i);  		
	        
			errorCodeId = SupervisionConstants.ERRORCODE_CASEASSIGN;
			//process case Assignment
			processCaseAssignment(errDefendantId, validDefendantId, orderMap);
	
			errorCodeId = SupervisionConstants.ERRORCODE_PROGREFREL;
			// Program Referral Processing
			processProgramReferral(errDefendantId, validDefendantId, criminalCaseIdsMap); 
        }catch(Exception e){
        	SpnSplitException.create(errDefendantId, validDefendantId, e.toString(), errorCodeId, criminalCaseIds, SupervisionConstants.ACTIVE_STATUS);
            throw e;
        }
    }
    
    private void method4(String validDefendantId, String errDefendantId, String criminalCaseIds, Map orderMap, Map criminalCaseIdsMap) throws Exception{
        String errorCodeId = SupervisionConstants.ERRORCODE_CASEASSIGN;
    	try{
			//process case Assignment
			processCaseAssignment(errDefendantId, validDefendantId, orderMap);
	
			errorCodeId = SupervisionConstants.ERRORCODE_PROGREFREL;
			// Program Referral Processing
			processProgramReferral(errDefendantId, validDefendantId, criminalCaseIdsMap); 
        }catch(Exception e){
        	SpnSplitException.create(errDefendantId, validDefendantId, e.toString(), errorCodeId, criminalCaseIds, SupervisionConstants.ACTIVE_STATUS);
            throw e;
        }
    }
    
    private void method5(String validDefendantId, String errDefendantId, String criminalCaseIds, Map criminalCaseIdsMap) throws Exception{
        String errorCodeId = SupervisionConstants.ERRORCODE_PROGREFREL;
    	try{
			// Program Referral Processing
			processProgramReferral(errDefendantId, validDefendantId, criminalCaseIdsMap); 
        }catch(Exception e){
        	SpnSplitException.create(errDefendantId, validDefendantId, e.toString(), errorCodeId, criminalCaseIds, SupervisionConstants.ACTIVE_STATUS);
            throw e;
        }
    }


	private void processProgramReferral(String erroneous_defendant_id, String valid_defendant_id, Map criminalcaseIdsMap) throws Exception{
        Iterator iterPReferral = CSProgramReferral.findAll(SupervisionConstants.DEFENDANT_ID, erroneous_defendant_id);
        while(iterPReferral.hasNext()){
        	CSProgramReferral pr = (CSProgramReferral) iterPReferral.next();
        	if(pr != null && criminalcaseIdsMap.containsKey(pr.getCriminalCaseId())){
	        	pr.setDefendantId(valid_defendant_id);
	        	
	        	SupervisionOrderPeriod spPeriod = null;
	        	Iterator spOrderPeriodIter = SupervisionOrderPeriod.findAll(SupervisionConstants.CRIMINALCASEID, pr.getCriminalCaseId());
	        	while(spOrderPeriodIter.hasNext()){
	        		spPeriod = (SupervisionOrderPeriod) spOrderPeriodIter.next();
	            	if(pr.getReferralDate().after(spPeriod.getSupervisionPeriodBeginDate())){
	            		break;
	            	}
	            }
	        	
	        	Iterator iterCaseNotes = Casenote.findAll(SupervisionConstants.PROGRAMREFERRALID, pr.getOID());
	        	while(iterCaseNotes.hasNext()){
	            	Casenote cNote = (Casenote) iterCaseNotes.next();
	            	if(spPeriod != null){
	            		cNote.setSupervisionPeriodId(spPeriod.getSupervisionPeriodId());
	            	}
	            }
        	}
        } 
	}

	private void processCaseAssignment(String erroneous_defendant_id, String valid_defendant_id, Map orderMap) throws Exception{
		// Case Assignment Processing
        Iterator iterator = CaseAssignment.findAll(SupervisionConstants.DEFENDANT_ID, erroneous_defendant_id);
        while(iterator.hasNext()){
        	CaseAssignment cAssign = (CaseAssignment) iterator.next();
        	String key = cAssign.getCriminalCaseId() + cAssign.getSupervisionOrderId();
        	if(key != null && orderMap.containsValue(key)){        		
		        cAssign.setDefendantId(valid_defendant_id);
	        	cAssign.bind(); 
        	}
        }
    }

	private void processCasenoteNCCondition(String validDefendantId, String errDefendantId, String orderIds, Map orderMap, Map notCheckedCases, int totalOrdersForDefendant) throws Exception{
		Map caseNumberCasenoteMap = new HashMap();
		Map orderMap1 = new HashMap();
		orderMap1.putAll(orderMap);		

		GetCasenoteNCConditionByOrderIdsEvent gcEvent = new GetCasenoteNCConditionByOrderIdsEvent();
   		gcEvent.setOrderIds(orderIds);
   		Iterator iterNCCasenotes = CasenoteNCConditions.findAll(gcEvent);
    		
   		while(iterNCCasenotes.hasNext()){
    		CasenoteNCConditions cn = (CasenoteNCConditions) iterNCCasenotes.next();
			if(orderMap1.containsKey(cn.getCaseNumber() + cn.getSupervisionOrderId())){
				Casenote caseNote = Casenote.find(new StringBuffer("").append(cn.getCasenoteId()).toString());
				if(caseNote != null){
    				caseNote.setSuperviseeId(validDefendantId);
    				caseNote.bind();
    				orderMap1.remove(cn.getCaseNumber()+ cn.getSupervisionOrderId());
				}
			}
    			
			if(orderMap.size() != totalOrdersForDefendant){
				Iterator casenotesIter = CasenoteNCConditions.findAllByNumericParam(SupervisionConstants.CASENOTEID, new StringBuffer("").append(cn.getCasenoteId()).toString());
				while(casenotesIter.hasNext()){
    				CasenoteNCConditions cns = (CasenoteNCConditions) casenotesIter.next();
 			
        			if(notCheckedCases.containsKey(cns.getCaseNumber() + cns.getSupervisionOrderId()) && !caseNumberCasenoteMap.containsKey(cns.getCaseNumber() + cns.getCasenoteId() + cns.getSupervisionOrderId())){
    					Casenote cNote = Casenote.find(new StringBuffer("").append(cns.getCasenoteId()).toString());
        				if(cNote != null){
        				    Casenote casenote1 = cNote.copy();
        				    casenote1.setSuperviseeId(errDefendantId);
        				    casenote1.bind();
        						
        				    Iterator ncecIter = NonComplianceEventCasenote.findAllByNumericParam(SupervisionConstants.NONCOMPLIANCEEVENTID, new StringBuffer("").append(cn.getNonCompEventId()).toString());
        				    if(ncecIter.hasNext()){
        				    	NonComplianceEventCasenote ncec = (NonComplianceEventCasenote) ncecIter.next();
        				    	ncec.setCasenoteId(Integer.parseInt(casenote1.getOID()));
        				    	ncec.bind();
        				    }
        				    caseNumberCasenoteMap.put(cns.getCaseNumber() + cns.getCasenoteId() + cns.getSupervisionOrderId(), cns.getCaseNumber() + cns.getCasenoteId() + cns.getSupervisionOrderId());        				    
             			}        				
    				}
    			}
      		}
        }  		
	}

	private void processCasenoteCondition(String validDefendantId, String errDefendantId, String orderIds, Map orderMap, Map notCheckedCases, int totalOrdersForDefendant)throws Exception {
		Map caseNumberCasenoteMap = new HashMap();
		Map orderMap1 = new HashMap();
		orderMap1.putAll(orderMap);			

		GetCasenoteConditionByOrderIdsEvent gEvent = new GetCasenoteConditionByOrderIdsEvent();
			gEvent.setOrderIds(orderIds);
			Iterator iterCasenotes = CasenoteConditions.findAll(gEvent);
			
			while(iterCasenotes.hasNext()){
				CasenoteConditions cn = (CasenoteConditions) iterCasenotes.next();
				if(orderMap1.containsKey(cn.getCaseNumber() + cn.getSupervisionOrderId())){
					Casenote caseNote = Casenote.find(new StringBuffer("").append(cn.getCasenoteId()).toString());
					caseNote.setSuperviseeId(validDefendantId);
					caseNote.bind();
					orderMap1.remove(cn.getCaseNumber()+ cn.getSupervisionOrderId());
			    }    			
	    			
	    		if(orderMap.size() != totalOrdersForDefendant){
	    			GetSpnSplitCasenotesByCasenoteIdEvent gsEvent = new GetSpnSplitCasenotesByCasenoteIdEvent();
	    			gsEvent.setCasenoteId(cn.getCasenoteId());
	    			Iterator casenotesIter = CasenoteConditions.findAll(gsEvent);
	    			while(casenotesIter.hasNext()){
	    				CasenoteConditions cns = (CasenoteConditions) casenotesIter.next();
	    				if(notCheckedCases.containsKey(cns.getCaseNumber() + cns.getSupervisionOrderId()) && !caseNumberCasenoteMap.containsKey(cns.getCaseNumber() + cn.getCasenoteId() + cn.getSupervisionOrderId())){
	    					Casenote cNote = Casenote.find(new StringBuffer("").append(cns.getCasenoteId()).toString());
	        				if(cNote != null){
	        				    Casenote casenote1 = cNote.copy();
	        				    casenote1.setSuperviseeId(errDefendantId);
	        				    casenote1.bind();
	        						
	        				    Iterator iterConditionCasenote = CasenoteConditions.findAllByNumericParam(SupervisionConstants.SUPERVISIONORDERCONDITIONID, new StringBuffer("").append(cns.getSprOrderConditionId()).toString());
	        					if(iterConditionCasenote.hasNext()){
	        						CasenoteConditions ec = (CasenoteConditions) iterConditionCasenote.next();
	        						ec.setCasenoteId(Integer.parseInt(casenote1.getOID()));
	        						ec.bind();
	        					}
	        				    caseNumberCasenoteMap.put(cns.getCaseNumber() + cns.getCasenoteId() + cns.getSupervisionOrderId(), cns.getCaseNumber() + cns.getCasenoteId() + cns.getSupervisionOrderId());        				    
	        				}
	    				}
	    			}
	    		}
			}
		}
}