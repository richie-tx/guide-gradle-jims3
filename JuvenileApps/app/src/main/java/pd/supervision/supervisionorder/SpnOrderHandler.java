/*
 * Created on Oct 17, 2006
 *
 */
package pd.supervision.supervisionorder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import messaging.spnsplit.ProcessSpnSplitEvent;
import messaging.spnsplit.reply.SpnSplitErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import pd.commonfunctionality.spnconsolidation.ISpnHandler;

/**
 * @author ryoung
 * 
 */
public class SpnOrderHandler implements ISpnHandler {
    final String ZERO = "0";
    Hashtable<String, String> supervisionPeriodRedirectMap;
    Hashtable<String, String> orderSupervisionPeriodMap;
	
    /*
     * (non-Javadoc)
     * 
     * @see pd.commonfunctionality.spnconsolidation.ISpnHandler#consolidate(java.lang.String,
     *      int)
     */
    public void consolidateOrig(String aliasSpn, String baseSpn) {

        System.out.println("SpnOrderHandler - baseSpn=" + baseSpn);
        System.out.println("SpnOrderHandler - aliasSpn=" + aliasSpn);
        String aliasDefendantId = padSpn(aliasSpn);
        String baseDefendantId = padSpn(baseSpn);
        
        // get supervisionPeriods for alias spn (for all agencies)
        Iterator aliasSupvsnPeriodsIter = SupervisionPeriod.findAll(aliasDefendantId);
        List aliasSupvsnPeriods = convertIterToList(aliasSupvsnPeriodsIter);
        Map aliasAgencyToPeriodsMap = new HashMap();
        Map aliasPeriodIdToPeriodMap = new HashMap();
        createAgencyToPeriodsMap(aliasSupvsnPeriods.iterator(), aliasAgencyToPeriodsMap, aliasPeriodIdToPeriodMap);
        Map aliasOrderToPeriodIdMap = createOrderToPeriodMap(aliasSupvsnPeriods.iterator());

        // get supervisionPeriods for base spn (for all agencies)
        Iterator baseSupvsnPeriodsIter = SupervisionPeriod.findAll(baseDefendantId);
        List baseSupvsnPeriods = convertIterToList(baseSupvsnPeriodsIter);
        Map baseAgencyToPeriodsMap = new HashMap();
        Map basePeriodIdToPeriodMap = new HashMap();
        createAgencyToPeriodsMap(baseSupvsnPeriods.iterator(), baseAgencyToPeriodsMap, basePeriodIdToPeriodMap);
        Map baseOrderToPeriodIdMap = createOrderToPeriodMap(baseSupvsnPeriods.iterator());

        //Find all supervision orders for the given aliasSpn (erronous spn)
        Iterator aliasOrders = SupervisionOrder.findAll("defendantId", aliasDefendantId);
        Map aliasAgencyOrdersMap = createAgencyToOrdersMap(aliasOrders);

        //Find all supervision orders for the given baseSpn (correct spn)
        Iterator baseOrders = SupervisionOrder.findAll("defendantId", baseDefendantId);
        Map baseAgencyOrdersMap = createAgencyToOrdersMap(baseOrders);

        // store processed supervisionPeriod ids here to avoid processing same Period multiple times for multiple Orders
        Set processedSupvsnPeriods = new HashSet();
        List deletedPeriods = new ArrayList();
        //Iterate through aliasSpn orders per agency
        Set agencyIds = aliasAgencyOrdersMap.keySet();
        for(Iterator iter = agencyIds.iterator(); iter.hasNext(); ) {
            String agencyId = (String)iter.next();
            // get all the SupervisionOrders for the current agency
            List aliasAgnecyOrders = (List)aliasAgencyOrdersMap.get(agencyId);
            //iterate through orders for this agency
            for(Iterator orderIter = aliasAgnecyOrders.iterator(); orderIter.hasNext(); ){
                SupervisionOrder aliasOrder = (SupervisionOrder)orderIter.next();
                // update spn 
                aliasOrder.setDefendantId(baseDefendantId);
                //process supervisionPeriod
                //get supervisionPeriodId from this Order
                String aliasPeriodId = (String)aliasOrderToPeriodIdMap.get(aliasOrder.getOID().toString());
                if(aliasPeriodId != null && !processedSupvsnPeriods.contains(aliasPeriodId)){
                    SupervisionPeriod aliasPeriod = (SupervisionPeriod)aliasPeriodIdToPeriodMap.get(aliasPeriodId); 
                    List periodsToBeDeleted = processSupervisionPeriod(aliasOrder, aliasPeriod, (List)baseAgencyToPeriodsMap.get(agencyId));
                    deletedPeriods.addAll(periodsToBeDeleted);
                    processedSupvsnPeriods.add(aliasPeriodId);
                }
            }
        }

        // delete sypervision periods
        for(Iterator iter = deletedPeriods.iterator(); iter.hasNext(); ){
            SupervisionPeriod sp = (SupervisionPeriod)iter.next();
            sp.delete();
        }
    }

    /**
     * Transfer SupervisionOrders from a given SPN to another
     * @param fromSpn
     * @param toSpn
     * @throws Exception 
     */
    private void transferSupervisionOrder(List<SupervisionOrder> fromOrders, String toSpn, 
    											boolean newSupervisionPeriodNeeded) throws Exception
    {    	
    	SupervisionPeriod new_supervision_period = null;

		//initialize map of order to supervision period map entries
    	initOrderSupervisionPeriodMap();
    	
    	//loop through all from orders and reset defendant ID to toSpn 
        int num_from_orders = fromOrders.size();
        for (int i=0;i<num_from_orders;i++)
        {
        		//reset defendant ID of fromSpn order to toSpn
        	SupervisionOrder from_order = fromOrders.get(i);
        	from_order.setDefendantId(toSpn);
        	
        		//determine if a new supervision period needs to be created
        	if (newSupervisionPeriodNeeded)
        	{        			       		        	
        		if (i == 0)
        		{		//create duplicate of the original supervision period
	        		new_supervision_period = 
	        			SupervisionPeriod.copy(from_order.getSupervisionPeriodId());        		
        		}
        		if (new_supervision_period != null)
        		{
        			from_order.setSupervisionPeriodId(new_supervision_period.getOID());
        			
        			//RRY added so the casenotes would get the correct supervision period id
        			//keep track of the new supervision period for this order
        			orderSupervisionPeriodMap.put(from_order.getOID(), 
        					new_supervision_period.getOID());
        		}
        		else
        		{
        			throw new Exception("Error: Order #" + from_order.getOID() 
        					+ " has no supervision period.");
        		}
        		
        	}
       		//explicitly bind to DB
        	from_order.bind();
        }                 
    }//end of transferSupervisionOrder()
    
    /**
     * Redesigned consolidate method using different means of supervision period calculation
     * @throws Exception 
     */
    public void consolidate(String aliasSpn, String baseSpn) throws Exception 
    {    	
    		//retrieve alias and base SPNs
    	String alias_defendantId = padSpn(aliasSpn);
        String base_defendantId = padSpn(baseSpn);
        boolean hasBaseOrders = true;
        
      //retrieve orders for alias SPN
        List<SupervisionOrder> base_orders = 
        	CollectionUtil.iteratorToList(
        			SupervisionOrder.findAll("defendantId", base_defendantId ));
        
        if ( base_orders.size() < 1 ){
        	
        	hasBaseOrders = false;
        }

			//retrieve orders for alias SPN
        List<SupervisionOrder> alias_orders = 
        	CollectionUtil.iteratorToList(
        			SupervisionOrder.findAll("defendantId", alias_defendantId));
        
        //transfer supervision orders from alias to base SPN
        transferSupervisionOrder(alias_orders, base_defendantId, false);
        
        if ( hasBaseOrders ){
        	
        	//recalculate supervision periods for base
            calculateSupervisionPeriod( base_defendantId );
        }

  
    }//end of consolidate()
    
    /**
     * Redirect supervision periods to specified target
     * @param targetSupervisionPeriodId
     * @param redirectList
     */
    public void redirectSupervisionPeriods(List<SupervisionOrderPeriod> redirectList,
    											String targetSupervisionPeriodId)
    {
    	int num_redirects = redirectList.size();
    	for (int i=0;i<num_redirects;i++)
    	{
    			//redirect all supervision periods in list
    		SupervisionOrderPeriod this_period = redirectList.get(i);
    		redirectSupervisionPeriod(this_period.getOriginalSupervisionPeriod(), 
    									targetSupervisionPeriodId,
    									this_period.getSupervisionOrderId());
    	}
    }//end of redirectSupervisionPeriods()

    /**
     * Redirect supervision periods to specified target
     * @param targetSupervisionPeriodId
     * @param redirectList
     */
    public void redirectSupervisionPeriod(String sourceSupervisionPeriodId,
    										String targetSupervisionPeriodId, 
    											String supervisionOrderId)
    {
    	if (sourceSupervisionPeriodId != null)
    	{
        	if (!sourceSupervisionPeriodId.equals(targetSupervisionPeriodId))
        	{
    				//check if this supervision period has been previously redirected
        		if (!supervisionPeriodRedirectMap.contains(sourceSupervisionPeriodId))	
        		{            		
            			//enter redirect supervision period into redirect hashtable
            		supervisionPeriodRedirectMap.put(supervisionOrderId, 
            				targetSupervisionPeriodId);
            		
        				//retrieve source supervision period
            		SupervisionPeriodRedirect update_supervision_period =
            			SupervisionPeriodRedirect.findBySourcePeriod(sourceSupervisionPeriodId);
            		
            			//update supervision period to new period
            		update_supervision_period.setTargetSupervisionPeriodId(
            				targetSupervisionPeriodId);
            		
            			//delete source supervision period
            		SupervisionPeriod source_supervision_period 
            								= SupervisionPeriod.find(sourceSupervisionPeriodId);
            		if (source_supervision_period != null)
            			source_supervision_period.delete();    		
        		}
        		else //this supervision period has been redirected at least once already
        		{
        			SupervisionOrder this_order = SupervisionOrder.find(supervisionOrderId);
        			if (this_order != null)
        				this_order.setSupervisionPeriodId(targetSupervisionPeriodId); 
        			this_order.bind();
        			
	        			
        		}
    		}
    	}
    }//end of redirectSupervisionPeriod()

    /**
     * Initialize and clear map of supervision period redirect entries
     *
     */
    public void initSupervisionPeriodRedirectMap()
    { 
    	if (supervisionPeriodRedirectMap == null)
    		supervisionPeriodRedirectMap = new Hashtable();
    	
    	supervisionPeriodRedirectMap.clear();
    }//end of initSupervisionPeriodRedirectMap()
    
    /**
     * Initialize and clear map of order to supervision periuod map entries
     *
     */
    public void initOrderSupervisionPeriodMap()
    { 
    	if (orderSupervisionPeriodMap == null)
    		orderSupervisionPeriodMap = new Hashtable();
    	
    	orderSupervisionPeriodMap.clear();
    }//end of initOrderSupervisionPeriodMap()    
    
    /**
     * Calculate supervision period for a given defendant
     * @param defendantId
     */
    public void calculateSupervisionPeriod(String defendantId)
    {
    		//initialize map of supervision period redirect entries
    	initSupervisionPeriodRedirectMap();
    	  	
    	//retrieve supervision order periods for given defendant
    	List defendant_spvn_order_periods = 
    			CollectionUtil.iteratorToList(SupervisionOrderPeriod.findAll("defendantId", defendantId));
    	
    		//sort supervision order periods by order activation date
    	Collections.sort(defendant_spvn_order_periods, 
    			SupervisionOrderPeriod.OrderActivationDateComparator);
    	
    		//loop thru all supervision order periods and process accordingly
    	int num_spvn_periods = defendant_spvn_order_periods.size();
    	SupervisionOrderPeriod prev_spvn_order_period = null;
    	SupervisionOrderPeriod this_spvn_order_period = null;
    	
    	
    	for (int i=0;i<num_spvn_periods;i++)
    	{  
    		boolean spvn_period_redirected = false;
    		
    			//retrieve this supervision order period record
    		this_spvn_order_period = (SupervisionOrderPeriod)
    			defendant_spvn_order_periods.get(i);
			
    			//ignore if no supervision period ID found - not likely
    		if (this_spvn_order_period.getSupervisionPeriodId() != null)
    		{
    				//if we've visited at least 1 supervision period already
        		if (prev_spvn_order_period != null)
        		{
        			SupervisionPeriod prev_spvn_period = 
        				SupervisionPeriod.find(prev_spvn_order_period.getSupervisionPeriodId());        			

    					//if this activation date less than previous supervision period end date (overlapping periods)
        			if ( (this_spvn_order_period.getActivationDate() != null)
        					&& (prev_spvn_period.getSupervisionEndDate() != null)
        					&& DateUtil.compare(this_spvn_order_period.getActivationDate(),
        							prev_spvn_period.getSupervisionEndDate(),
        							DateUtil.DATE_FMT_1) <= 0 )
        			{
        				
        					//redirect supervision period to that of previous period
        				redirectSupervisionPeriod(this_spvn_order_period.getOriginalSupervisionPeriod(),
        						prev_spvn_order_period.getSupervisionPeriodId(), 
        							this_spvn_order_period.getSupervisionOrderId());
        				
        					//set this supervision period ID to new supervision period
        				this_spvn_order_period.setSupervisionPeriodId(
        						prev_spvn_order_period.getSupervisionPeriodId());
        				
    						//indicate that this supervision period has been redirected
        				spvn_period_redirected = true;

    						//if this inactivate date greater previous supervision end date
        				if ((this_spvn_order_period.getInactivationDate() != null) 
        						&& (prev_spvn_period.getSupervisionEndDate() != null)
        						&& DateUtil.compare(this_spvn_order_period.getInactivationDate(),
        								prev_spvn_period.getSupervisionEndDate(),
            							DateUtil.DATE_FMT_1) > 0 )
            			{
        						//change end date of supvervision period previous supervision period
        					prev_spvn_period.setSupervisionEndDate(
        							this_spvn_order_period.getInactivationDate());
            			}        				
        			}    								
        		}
        		    		
        			//indicates that all remaining supervision periods should be removed
        		if (this_spvn_order_period.getInactivationDate() == null)
        		{
        				//redirect the remaining supervision periods to point to the 
        				//this open ended supervision period
        			redirectSupervisionPeriods(
        					defendant_spvn_order_periods.subList(i +1, num_spvn_periods),
        						this_spvn_order_period.getSupervisionPeriodId());
        			
        				//find this supervision period and nullify end date
        				// (relevant if this supervision period has been redirected)
        			SupervisionPeriod this_spvn_period = 
        				SupervisionPeriod.find(this_spvn_order_period.getSupervisionPeriodId());        			
        			this_spvn_period.setSupervisionEndDate(null);
        			
        			 break;
        		}
        		
        			//create new supervision period
        		if (!spvn_period_redirected)
        		{
    				//create new supervision period and redirect this supervision period to it
        			SupervisionPeriod new_spvn_period = 
        				SupervisionPeriod.create(this_spvn_order_period);
        			redirectSupervisionPeriod(
        					this_spvn_order_period.getOriginalSupervisionPeriod(),
        						new_spvn_period.getOID(), 
        							this_spvn_order_period.getSupervisionOrderId());  
        			
						//set this supervision period ID to new supervision period
    				this_spvn_order_period.setSupervisionPeriodId(
    						new_spvn_period.getOID());
        			
        		}
    		}

    			//set previous supervision order period using the current 
    		prev_spvn_order_period = this_spvn_order_period;
    	}
    	
    }//end of calculateSupervisionPeriod()
    
    private Map createOrderToPeriodMap(Iterator periods){
        Map orderToPeriodMap = new HashMap();
        List periodIds = new ArrayList(); 
        while(periods.hasNext()){
            SupervisionPeriod period = (SupervisionPeriod)periods.next();
            periodIds.add(period.getOID().toString());
        }
        // retrieve SupervisionPeriods from Order ids
        if(periodIds.size() > 0){
            Iterator supervisionOrders = SupervisionOrder.findAllByPeriodIds(periodIds);
            // create Order to Periods map
            while (supervisionOrders.hasNext()) {
                SupervisionOrder order = (SupervisionOrder)supervisionOrders.next();
                orderToPeriodMap.put(order.getOID().toString(), order.getSupervisionPeriodId());
            }
        }
        
        return orderToPeriodMap;
    }
    
    private String padSpn(String spn){
        if (spn != null && spn.length() < 8) {
            StringBuffer sb = new StringBuffer(spn);
            for (int i = 0; i < 8 - spn.length(); i++) {
                sb.insert(0, ZERO);
            }
            spn = sb.toString();
        }
        return spn;
    }

    private List convertIterToList(Iterator objectIter){
        List objectsList = new ArrayList();
        while(objectIter.hasNext()){
            objectsList.add(objectIter.next());
        }
        return objectsList;
    }

   
    private void createAgencyToPeriodsMap(Iterator supervisionPeriods, Map agencyToPeriodsMap, Map periodIdToPeriodMap) {
        // group them per agency
        while (supervisionPeriods.hasNext()) {
            SupervisionPeriod period = (SupervisionPeriod)supervisionPeriods.next();
            List list = (List) agencyToPeriodsMap.get(period.getAgencyId());
            if (list == null) {
                list = new ArrayList();
                agencyToPeriodsMap.put(period.getAgencyId(), list);
            }
            list.add(period);
            
            // PeriodIdToPeriod Map
            periodIdToPeriodMap.put(period.getOID().toString(), period);
        }
    }
    
    private Map createAgencyToOrdersMap(Iterator supervisionOrders) {
        Map agencyOrdersMap = new HashMap();
        // group them per agency
        while (supervisionOrders.hasNext()) {
            SupervisionOrder order = (SupervisionOrder) supervisionOrders.next();
            List list = (List) agencyOrdersMap.get(order.getAgencyId());
            if (list == null) {
                list = new ArrayList();
                agencyOrdersMap.put(order.getAgencyId(), list);
            }
            list.add(order);
        }

        return agencyOrdersMap;
    }
    
    private boolean checkOverlapPeriods(Date aliasBeginDate, Date aliasEndDate, Date baseBeginDate, Date baseEndDate){
        boolean result = true;
        if((aliasEndDate == null && baseEndDate != null && baseEndDate.before(aliasBeginDate)) || // alias active
                (aliasEndDate != null && baseEndDate == null && baseBeginDate.after(aliasEndDate)) || // base active
                (aliasEndDate != null && baseEndDate != null && // both inactive
 	               !((aliasBeginDate.after(baseBeginDate) && aliasBeginDate.before(baseEndDate)) || 
 	                (aliasEndDate.after(baseBeginDate) && aliasEndDate.before(baseEndDate)) ||
 	                (baseBeginDate.after(aliasBeginDate) && baseBeginDate.before(aliasEndDate)) ||
 	                (baseEndDate.after(aliasBeginDate) && baseEndDate.before(aliasEndDate)))))
        {
            result = false;
        }
        return result;
    }
    
    
    private List processSupervisionPeriod(SupervisionOrder aliasOrder, SupervisionPeriod aliasSupervisionPeriod, List baseAgencyPeriods){
        List deletedPeriods = new ArrayList();
        // check if alias Period overlaps with any base SupervisionPeriod
        Date aliasBeginDate = aliasSupervisionPeriod.getSupervisionBeginDate();
        Date aliasEndDate = aliasSupervisionPeriod.getSupervisionEndDate();
        // traverse through base supervision periods
        for(Iterator iter = baseAgencyPeriods.iterator(); iter.hasNext(); ){
            SupervisionPeriod baseSupervisionPeriod = (SupervisionPeriod)iter.next();
            Date baseBeginDate = baseSupervisionPeriod.getSupervisionBeginDate();
            Date baseEndDate = baseSupervisionPeriod.getSupervisionEndDate();
            //--------- check if periods overlap --------------
            if(!checkOverlapPeriods(aliasBeginDate, aliasEndDate, baseBeginDate, baseEndDate))
            {
                // periods dont overlap.. dont need to do anything
                continue;
            }else //------------periods overlap------------------
            {
	            if(aliasEndDate == null && baseEndDate == null){ // cond1: when both of them active
	                if(aliasBeginDate.before(baseBeginDate)){
	                    // update its begin date to ailas's begin date (earlier one)
	                    baseSupervisionPeriod.setSupervisionBeginDate(aliasBeginDate);
	                }
                    updateRedirectPeriod(aliasSupervisionPeriod, baseSupervisionPeriod);
	            }else if(baseEndDate != null && aliasEndDate == null) // cond2: alias is active and base is not
	            {
	                if(aliasBeginDate.before(baseBeginDate)){
	                    // update its begin date to ailas's begin date (earlier one)
	                    baseSupervisionPeriod.setSupervisionBeginDate(aliasBeginDate);
	                }
                    updateRedirectPeriod(aliasSupervisionPeriod, baseSupervisionPeriod);
	                // activate base supervision period 
                    baseSupervisionPeriod.setSupervisionEndDate(null);
	            }else if(baseEndDate == null && aliasEndDate != null) // cond3: alias is inactive and base is active
	            {
	                if(aliasBeginDate.before(baseBeginDate)){
	                    // update its begin date to ailas's begin date (earlier one)
	                    baseSupervisionPeriod.setSupervisionBeginDate(aliasBeginDate);
	                }
                    updateRedirectPeriod(aliasSupervisionPeriod, baseSupervisionPeriod);
	            }else if(baseEndDate != null && aliasEndDate != null) // cond3: both alias base are inactive
	            {
	                if(aliasBeginDate.before(baseBeginDate)){
	                    // update its begin date to ailas's begin date (earlier one)
	                    baseSupervisionPeriod.setSupervisionBeginDate(aliasBeginDate);
	                }
	                if(aliasEndDate.after(baseEndDate)){
	                    // update its end date to ailas's end date (later one)
	                    baseSupervisionPeriod.setSupervisionEndDate(aliasEndDate);
	                }
                    updateRedirectPeriod(aliasSupervisionPeriod, baseSupervisionPeriod);
	            }
            }
			//delete alias Period
			deletedPeriods.add(aliasSupervisionPeriod);
        }
        return deletedPeriods;
    }
    
    private void updateRedirectPeriod(SupervisionPeriod aliasSupervisionPeriod, SupervisionPeriod baseSupervisionPeriod){
        // get SupervisionPeriodRedirect from alias supervisionPeriod and update its target to base as alias will be deleted
		SupervisionPeriodRedirect periodRedirect = SupervisionPeriodRedirect.findBySourcePeriod(aliasSupervisionPeriod.getOID().toString());
		if(periodRedirect != null){ // will be null in few cases in split
			periodRedirect.setTargetSupervisionPeriod(baseSupervisionPeriod);
		}
		// update all redirects where target is the source redirect being updated
		Iterator redirects = SupervisionPeriodRedirect.findByTargetPeriod(aliasSupervisionPeriod.getOID().toString());
		while(redirects.hasNext()){
		    SupervisionPeriodRedirect redirect = (SupervisionPeriodRedirect)redirects.next();
		    redirect.setTargetSupervisionPeriod(baseSupervisionPeriod);
		}
    }
    
    
    /* (non-Javadoc)
     * @see pd.commonfunctionality.spnconsolidation.ISpnHandler#split(mojo.km.messaging.IEvent)
     */
    public void splitOrig(IEvent processSpnSplitEvent)
    {
        /*
         * steps:
         * 1. get all the cases selected to be split and sort them by the end date
         * 2. pick the first erroneous one and traverse through all the valid periods
         * 3. if valid period overlaps, modify the period
         * 4. if no overlapping valid period, create a new valid peeriod from the erroneous one
         * 5. repeat step #2 for the modified valid periods list (as a result of step# 3 and 4)
         * 6. process unselected cases for erroneous spn  
         */
        ProcessSpnSplitEvent spnSplitEvent = (ProcessSpnSplitEvent)processSpnSplitEvent;
        
        String erroneousDefendantId = padSpn(spnSplitEvent.getErroneousSpn());
        String validDefendantId = padSpn(spnSplitEvent.getValidSpn());
        
        spnSplitEvent.setValidSpn(validDefendantId);
        List caseNums = spnSplitEvent.getCases();

        //---------------erroneous data ---------------------------
        // get all the supervision orders for the given list of case numbers
        Iterator erroneousSupOrders = SupervisionOrder.GetOrdersForCaseIds(spnSplitEvent);
        if(!erroneousSupOrders.hasNext()){
            // If there is no supervision order found for the given case id's then return with an error response event
            returnNoSupOrdersError(erroneousDefendantId);
            return;
        }
        Map errCaseToOrdersMap = createCaseToOrderMap(erroneousSupOrders);
        
        //-------------valid data----------------------------------
        // valid spn's Periods 
        Iterator validSupPer = SupervisionPeriod.findAll(validDefendantId, spnSplitEvent.getAgencyId());
        List validSupPerList = createListFromIterator(validSupPer);
        Collections.sort(validSupPerList, SupervisionPeriod.BeginDateComparator);// sort it by end date
//        Map validPeriodIdToPeriodMap = createIdToPeriodsMap(validSupPer);
        
        //-----------------processing-------------------------------
        List unselectedCases = new ArrayList();
        //Get the case begin and end dates for all the orders with Erroneous spns and selected criminal case id
        Iterator erroneousSupOrderPeriodsIter = SupervisionSplitOrder.findAll("supPeriodId", spnSplitEvent.getErrPeriodId());
        List erroneousSupOrderPeriodsList = createListFromIterator(erroneousSupOrderPeriodsIter);
        Collections.sort(erroneousSupOrderPeriodsList, SupervisionSplitOrder.BeginDateComparator);
        for(Iterator erroneousSupOrderPeriods = erroneousSupOrderPeriodsList.iterator(); erroneousSupOrderPeriods.hasNext(); )
        {
            // erroneous values
            SupervisionSplitOrder errSupOrderPeriod = (SupervisionSplitOrder)erroneousSupOrderPeriods.next();
            // process spn split only if this case is selected
            if(caseNums.contains(errSupOrderPeriod.getCriminalCaseId())){
                Date erroneousCaseBeginDate = errSupOrderPeriod.getCaseBeginDate();
                Date erroneousCaseEndDate = errSupOrderPeriod.getCaseEndDate();
                List errSupOrders = (List)errCaseToOrdersMap.get(errSupOrderPeriod.getCriminalCaseId());
                
                // check if it is overlapping with any valid period
                List validOverlappedPeriods = new ArrayList();
                List validModPeriods = new ArrayList();
                for(Iterator iter = validSupPerList.iterator(); iter.hasNext(); ){
                    SupervisionPeriod validPeriod = (SupervisionPeriod)iter.next();
                    if(this.checkOverlapPeriods(erroneousCaseBeginDate, erroneousCaseEndDate, 
                       validPeriod.getSupervisionBeginDate(), validPeriod.getSupervisionEndDate()))
                    {// period overlapping
                        validOverlappedPeriods.add(validPeriod);
                    }else{
                        // add nonoverlapping period into a new list
                        validModPeriods.add(validPeriod);
                    }
                }
                
                // process overlapped periods
                SupervisionPeriod resultantPeriod = null;
                if(validOverlappedPeriods.size() > 0){
                    resultantPeriod = updateSupOrdersProcessPeriods(errSupOrderPeriod, validOverlappedPeriods, errSupOrders, validDefendantId);
                }else{ // no overlaps
                    resultantPeriod = updateSupOrderCreatePeriod(errSupOrderPeriod, errSupOrders, validDefendantId);
                }
                validModPeriods.add(resultantPeriod);
                validSupPerList = validModPeriods;
            }else{
                // case not selected to split
                unselectedCases.add(errSupOrderPeriod);
            }
        }  

        // process the erroneous period for remaing cases (if any)
        processUnselectedCases(spnSplitEvent.getErrPeriodId(), unselectedCases);
    }

    /**
     * Initiate the process of a SPN split transaction
     * @throws Exception 
     */
    public void split(IEvent processSpnSplitEvent) throws Exception
    {
        ProcessSpnSplitEvent spn_split_event = (ProcessSpnSplitEvent)processSpnSplitEvent;
        
        if(spn_split_event.getSpnSplitExceptionId() == null || spn_split_event.getSpnSplitExceptionId().equalsIgnoreCase("")){
	        //retrieve SPNs of erroneous and valid spins
	        String erroneous_defendant_id = padSpn(spn_split_event.getErroneousSpn());
	        String valid_defendant_id = padSpn(spn_split_event.getValidSpn());        
	        spn_split_event.setValidSpn(valid_defendant_id);
	        spn_split_event.setErroneousSpn(erroneous_defendant_id);
	        
	        	//retrieve all erroneous orders for selected cases
	        List erroneous_orders = CollectionUtil.iteratorToList(
	        		SupervisionOrder.GetOrdersForCaseIds(spn_split_event));
	
	        	//return error if no orders found for selected cases        
	        if(erroneous_orders.size() == 0)
	        {
	            returnNoSupOrdersError(erroneous_defendant_id);
	            return;
	        }
	
	    		//transfer supervision orders from erroneous to valid SPN
	        transferSupervisionOrder(erroneous_orders, valid_defendant_id, true);
	
	    		//recalculate supervision periods for erroneous SPN
	        calculateSupervisionPeriod(erroneous_defendant_id);
	        	//take record of orders that have brand new supervision periods for erroneous SPN
	        Hashtable erroneousOrderSupervisionPeriodMap = 
	        				(Hashtable)orderSupervisionPeriodMap.clone();
	
				//recalculate supervision periods for valid SPN
	        calculateSupervisionPeriod(valid_defendant_id);
	        	//take record of orders that have brand new supervision periods for valid SPN
	        Hashtable validOrderSupervisionPeriodMap = 
				(Hashtable)supervisionPeriodRedirectMap.clone();
	        
	        	//save record of order to new supervision period map for erroneous and valid SPN 
	        	//onto split event for processing by other affected data types
	        spn_split_event.setErroneousOrderSupervisionPeriodMap(
					erroneousOrderSupervisionPeriodMap);
	        spn_split_event.setValidOrderSupervisionPeriodMap(
	        		validOrderSupervisionPeriodMap);
	    }
    }//end of split()
    
    /**
     * @param defendantId
     */
    private void returnNoSupOrdersError(String defendantId)
    {
           IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REPLY);
           SpnSplitErrorResponseEvent spnError =  new SpnSplitErrorResponseEvent();
           spnError.setErroneousSpn(defendantId);
           spnError.setMsg("No Supervision Order Periods found for the given spn");
           dispatch1.postEvent(spnError);
           return;
    }

    private void processUnselectedCases(String supervisionPeriodId, List unselectedCases){
		  SupervisionPeriod supervisionPeriod = SupervisionPeriod.find(supervisionPeriodId);
		  
		  // process unused cases
		  if(unselectedCases.size() == 0){
		      supervisionPeriod.delete();
		  }else{
		      boolean hasFirst = true;
			  for(int i = 0; i < unselectedCases.size(); i++)
			  {
			  	  SupervisionSplitOrder orderPeriod = (SupervisionSplitOrder)unselectedCases.get(i);
			      Date periodBeginDate = orderPeriod.getCaseBeginDate();
			      Date periodEndDate = orderPeriod.getCaseEndDate();
			      if(hasFirst){
			          supervisionPeriod.setSupervisionBeginDate(periodBeginDate);
				      supervisionPeriod.setSupervisionEndDate(orderPeriod.getCaseEndDate());
			          hasFirst = false;
			      }   
			      if(i < (unselectedCases.size() - 1)){
				      SupervisionSplitOrder orderPeriodNext = (SupervisionSplitOrder)unselectedCases.get(i + 1);
					  Date periodBeginDateNext = orderPeriodNext.getCaseBeginDate();
					  Date periodEndDateNext = orderPeriodNext.getCaseEndDate();
		              if(!checkOverlapPeriods(periodBeginDate, periodEndDate, periodBeginDateNext, periodEndDateNext)){
		                  // not overlapping.. create a new period for nonoverlapping case period
		                  supervisionPeriod.setSupervisionEndDate(periodEndDate); // end date for previous period
		                  // get all the orders for the current case
		                  Iterator ordersToBeMoved = SupervisionOrder.findAll("criminalCaseId", orderPeriod.getCriminalCaseId());
		                  SupervisionPeriod newPeriod = SupervisionPeriod.create(orderPeriodNext);
		                  // erroneous orders
		                  while(ordersToBeMoved.hasNext()){
		                      SupervisionOrder order = (SupervisionOrder)ordersToBeMoved.next();
		                      order.setSupervisionPeriod(newPeriod);
		                  }
//		                  updateRedirectPeriod(supervisionPeriod, newPeriod);
		              }else{
		                  //overlapping
		                  // set enddate when it is before last period's end date
		                  if(periodEndDateNext == null || periodEndDate == null){
		                      supervisionPeriod.setSupervisionEndDate(null);
		                  }else if(periodEndDate.after(periodEndDateNext)){
		                      supervisionPeriod.setSupervisionEndDate(periodEndDate);
		                  }else{
		                      supervisionPeriod.setSupervisionEndDate(periodEndDateNext);
		                  }
		              }
			      }
			  }
		  }
    }
    
    private SupervisionPeriod updateSupOrdersProcessPeriods(SupervisionSplitOrder errSupOrderPeriod, List validOverlappedPeriods, List errSupOrders, 
            												String validDefendantId){
        //create SupervisionPeriod first
        SupervisionPeriod resultantPeriod = SupervisionPeriod.create(errSupOrderPeriod);

        // this is a sorted list, so periods will be in sorted by its end date
        // get the first element to set begin date and last element to set the end date of resultant Period
        SupervisionPeriod firstPeriod = (SupervisionPeriod)validOverlappedPeriods.get(0);
        SupervisionPeriod lastPeriod = (SupervisionPeriod)validOverlappedPeriods.get(validOverlappedPeriods.size() - 1);

        // set begindate when it is after first period's begin date
        if(resultantPeriod.getSupervisionBeginDate().after(firstPeriod.getSupervisionBeginDate())){
            resultantPeriod.setSupervisionBeginDate(firstPeriod.getSupervisionBeginDate());
        }
        // set enddate when it is before last period's end date
        if(resultantPeriod.getSupervisionEndDate() != null && lastPeriod.getSupervisionEndDate() == null){
            resultantPeriod.setSupervisionEndDate(null);
        }else if(resultantPeriod.getSupervisionEndDate() != null && lastPeriod.getSupervisionEndDate() != null && resultantPeriod.getSupervisionEndDate().before(lastPeriod.getSupervisionEndDate())){
            resultantPeriod.setSupervisionEndDate(lastPeriod.getSupervisionEndDate());
        }
        
        // traverse thru this list of valid spn's supervisionperiods to delete them. 
        for(Iterator iter = validOverlappedPeriods.iterator(); iter.hasNext(); ){
            SupervisionPeriod validPeriodToBeDeleted = (SupervisionPeriod)iter.next();
            updateRedirectPeriod(validPeriodToBeDeleted, resultantPeriod);
            validPeriodToBeDeleted.delete();
        }
        // erroneous orders
        for(Iterator iter = errSupOrders.iterator(); iter.hasNext(); ){
            SupervisionOrder order = (SupervisionOrder)iter.next();
            order.setDefendantId(validDefendantId);
            order.setSupervisionPeriod(resultantPeriod);
        }
        
        return resultantPeriod;
    }
    
    private SupervisionPeriod updateSupOrderCreatePeriod(SupervisionSplitOrder errSupOrderPeriod, List errSupOrders, String validDefendantId){
        //create SupervisionPeriod first
        SupervisionPeriod resultantPeriod = SupervisionPeriod.create(errSupOrderPeriod);
        
        // erroneous orders
        for(Iterator iter = errSupOrders.iterator(); iter.hasNext(); ){
            SupervisionOrder order = (SupervisionOrder)iter.next();
            order.setDefendantId(validDefendantId);
            order.setSupervisionPeriod(resultantPeriod);
        }
        
        return resultantPeriod;
    }

    private List createListFromIterator(Iterator iter)
    {
        ArrayList list = new ArrayList();
        while (iter.hasNext()) 
        {
            list.add(iter.next());
        }
        return list;
    }
    
    /**
     * @param agencySupOrdersMap
     * @param errSpnAgency
     * @param criminalCaseId
     * @return
     */
    private Map createCaseToOrderMap(Iterator supOrders)
    {
        Map caseToOrderMap = new HashMap();
        while(supOrders.hasNext())
        {
            SupervisionOrder order = (SupervisionOrder)supOrders.next();
            List orders = (List)caseToOrderMap.get(order.getCriminalCaseId());
            if(orders == null){
                orders = new ArrayList();
                caseToOrderMap.put(order.getCriminalCaseId(), orders);
            }
            orders.add(order);
        }
        return caseToOrderMap;
    }

}
