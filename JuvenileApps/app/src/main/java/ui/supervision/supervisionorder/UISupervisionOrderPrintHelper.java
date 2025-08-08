/*
 * Created on Jan 23, 2006 
 *
 */
package ui.supervision.supervisionorder;

import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.report.SupervisionOrderPrintTemplateRequestEvent;
import messaging.supervisionoptions.reply.ConditionDetailResponseEvent;
import messaging.supervisionorder.GetSupervisionOrderVersionsEvent;
import messaging.supervisionorder.reply.CaseOrderResponseEvent;
import messaging.supervisionorder.reply.SupOrderConditionResponseEvent;
import messaging.supervisionorder.reply.SupervisionOrderDetailResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.messaging.reporting.ReportRequestEvent;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.Name;
import naming.SupervisionConstants;
import ui.common.UIUtil;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;
import ui.supervision.supervisionorder.form.SupervisionOrderSearchForm;

/**
 * @author rcapestani
 *  
 */
public final class UISupervisionOrderPrintHelper {
	

	/**
	 * 
	 */
	
	public static final String FILEEXT = ".pdf";

    public static final String CONTEXTKEYPREFIX = "REPORTING::";
    
    public static final String SPANISH = "_Spanish";
    
    public static final String COMMA = ",";

    public static final String DATEFORMAT = "MMddyy";
    
    public static final String ZERO = "0";

	public void processSignaturePage(ActionForm form, HttpServletRequest aRequest, HttpServletResponse response) throws Exception {
        SupervisionOrderForm supOrderForm = (SupervisionOrderForm) form;

            ReportResponseEvent aRespEvent = null;
            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
            ReportRequestEvent supOrderEvent = new SupervisionOrderPrintTemplateRequestEvent();
            
            SupervisionOrderReportingBean soRb = populateReportingBean(supOrderForm, aRequest);
            supOrderEvent.addDataObject(soRb);
            StringBuffer reportName = new StringBuffer(CONTEXTKEYPREFIX);
            
            //adds code to print misdemeanor report signature page
            if(supOrderForm.getCdi().equalsIgnoreCase("002"))
            {
            	reportName.append("CC/");
            }
            
            reportName.append("SIGNATURE PAGE");

            if(soRb.isPrintSpanish())
                reportName.append(SPANISH);
            
            supOrderEvent.setReportName(reportName.toString());
            
            dispatch.postEvent(supOrderEvent);
            CompositeResponse compResponse = (CompositeResponse) dispatch.getReply();
            aRespEvent = (ReportResponseEvent) MessageUtil.filterComposite(compResponse, ReportResponseEvent.class);

            if (aRespEvent == null) {
                ReturnException returnException = (ReturnException) MessageUtil.filterComposite(compResponse, ReturnException.class);
                throw returnException;
            } else {
                String fileName = "SIGNATURE PAGE" + "_" + soRb.getCause();
                response.setContentType("application/x-file-download");
                response.setHeader("Content-disposition", "attachment; filename=" + fileName + FILEEXT);
                response.setHeader("Cache-Control", "must-revalidate");
                response.setContentLength(aRespEvent.getContent().length);
                response.resetBuffer();
                OutputStream os = response.getOutputStream();
                os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);
                os.flush();
                os.close();
            }
    }

    public void processReport(ActionForm form, HttpServletRequest aRequest, HttpServletResponse response) throws Exception {
        SupervisionOrderForm supOrderForm = (SupervisionOrderForm) form;

        ReportResponseEvent aRespEvent = null;
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        ReportRequestEvent supOrderEvent = new SupervisionOrderPrintTemplateRequestEvent();
        
        SupervisionOrderReportingBean soRb = populateReportingBean(supOrderForm, aRequest);
       
        supOrderEvent.addDataObject(soRb);
        StringBuffer reportName = new StringBuffer(CONTEXTKEYPREFIX)
                                .append(supOrderForm.getCourtCategory()).append("/").append(
                            SupervisionOrderListHelper.getOrderTitleName(supOrderForm.getAllOrderTitleList(),
                                    soRb.getOrderTitleId()).trim());
        if(soRb.isPrintSpanish())
            reportName.append(SPANISH);
        
        supOrderEvent.setReportName(reportName.toString());
        
        dispatch.postEvent(supOrderEvent);
        CompositeResponse compResponse = (CompositeResponse) dispatch.getReply();
        aRespEvent = (ReportResponseEvent) MessageUtil.filterComposite(compResponse, ReportResponseEvent.class);

        if (aRespEvent == null) {
            ReturnException returnException = (ReturnException) MessageUtil.filterComposite(compResponse, ReturnException.class);
            throw returnException;
        } else {
            String fileName = soRb.getOrderTitle() + "_" + soRb.getCause();
            response.setContentType("application/x-file-download");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + FILEEXT);
            response.setHeader("Cache-Control", "must-revalidate");
            response.setContentLength(aRespEvent.getContent().length);
            response.resetBuffer();
            OutputStream os = response.getOutputStream();
            os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);
            os.flush();
            os.close();
        }
    }

    private void setOrderSignedDate(SupervisionOrderReportingBean reqBean, Date signDate) {
        if (signDate != null) {
            String signatureDate = DateFormat.getDateInstance(DateFormat.LONG).format(signDate);
            if( StringUtils.isNotEmpty( signatureDate ))
            {
	            String day = signatureDate.substring(signatureDate.trim().indexOf(" ") + 1, signatureDate.indexOf(COMMA));
	            String month = signatureDate.substring(0, signatureDate.trim().indexOf(" "));
	            String year = signatureDate.substring(signatureDate.indexOf(COMMA) + 1, signatureDate.length());
	            reqBean.setSignedDay(NumberSuffixUtil.getSuffixForString(day));
	            reqBean.setSignedMonth(month);
	            reqBean.setSignedYear(year);
            }
        }
    }
    
    private void setJuvOrderSignedDate(SupervisionOrderReportingBean reqBean, Date signDate) {
        if (signDate != null) {
            String signatureDate = DateFormat.getDateInstance(DateFormat.LONG).format(signDate);
            if( StringUtils.isNotEmpty( signatureDate ))
            {
	            String day = signatureDate.substring(signatureDate.trim().indexOf(" ") + 1, signatureDate.indexOf(COMMA));
	            String month = signatureDate.substring(0, signatureDate.trim().indexOf(" "));
	            String year = signatureDate.substring(signatureDate.indexOf(COMMA) + 1, signatureDate.length());
	            reqBean.setJuvDay(NumberSuffixUtil.getSuffixForString(day));
	            reqBean.setJuvMonth(month);
	            reqBean.setJuvYear(year);
            }
        }
    }

    private void setPrintDOPDate(SupervisionOrderReportingBean reqBean, String probStartDate) 
    {
    	if( StringUtils.isNotEmpty( probStartDate ))
        {
	    	Date pbsDate = DateUtil.stringToDate(probStartDate, DateUtil.DATE_FMT_1);
	    	probStartDate = DateFormat.getDateInstance(DateFormat.LONG).format(pbsDate);
	        String day = probStartDate.substring(probStartDate.trim().indexOf(" ") + 1, probStartDate.indexOf(COMMA));
            String month = probStartDate.substring(0, probStartDate.trim().indexOf(" "));
            String year = probStartDate.substring(probStartDate.indexOf(COMMA) + 1, probStartDate.length());
            reqBean.setDopDayOrdinal(NumberSuffixUtil.getSuffixForString(day));
            reqBean.setDopMonth(month);
            reqBean.setDopYear(year);
        }
    }
    
    private void setPrintDOPEndDate(SupervisionOrderReportingBean reqBean, String date) 
    {
    	if( StringUtils.isNotEmpty( date ))
        {
	    	Date pbsDate = DateUtil.stringToDate(date, DateUtil.DATE_FMT_1);
	    	date = DateFormat.getDateInstance(DateFormat.LONG).format(pbsDate);
	        String day = date.substring(date.trim().indexOf(" ") + 1, date.indexOf(COMMA));
            String month = date.substring(0, date.trim().indexOf(" "));
            String year = date.substring(date.indexOf(COMMA) + 1, date.length());
            reqBean.setProbationEndDay(NumberSuffixUtil.getSuffixForString(day));
            reqBean.setProbationEndMonth(month);
            reqBean.setProbationEndYear(year);
        }
    }
    
    private CaseOrderResponseEvent getCaseOrderResponseEvent(SupervisionOrderForm sof, HttpServletRequest aRequest)
    {
        String primKey = "";
        CaseOrderResponseEvent caseOrder =  null;
        //this is for request from search results page
        if (sof.getPrintAction() == null || sof.getPrintAction().equals("")) 
        {
        	primKey = new StringBuffer(sof.getPrimaryCaseOrderKey()).toString();
            if (primKey != null) 
            {
                // get SupervisionOrderSearchForm from the session to get the list
                // of case orders
                SupervisionOrderSearchForm orderSearchForm = (SupervisionOrderSearchForm) aRequest.getSession().getAttribute("supervisionOrderSearchForm");
                Collection orderList = orderSearchForm.getCaseOrderList();
                Iterator orderIter = orderList.iterator();
                while (orderIter.hasNext()) 
                {
                    caseOrder = (CaseOrderResponseEvent) orderIter.next();
                    if (caseOrder.getPrimaryKey().equals(primKey)) 
                    {
                        break;
                    }
                }

            }
        }
        //this is being done since only from the search results page, the primary key
        //includes the order id appended to cdi+case number
        //other places like confirm update, activate etc the sof has only cdi+casenum
        else
        {
            //primKey = new StringBuffer(sof.getCdi()).append(sof.getCaseNum()).append(sof.getOrderId()).toString();
            caseOrder =  new CaseOrderResponseEvent();
            caseOrder.setOrderId(sof.getOrderId());
            caseOrder.setCaseNum(sof.getCaseNum());
            caseOrder.setCdi(sof.getCdi());
            caseOrder.setOffense(sof.getOffenseDesc());
        }
        
        return caseOrder;
    }
 
    
    private String padCourtNum(String court, String courtCategory){
        //Added this to fix defect JIMS200034771
    	StringBuffer sb = new StringBuffer(court);
    	if (court != null && court.length() < 3) {
            for (int i = 0; i < 3 - court.length(); i++) {
                sb.insert(0, ZERO);
            }
    	}
        if (courtCategory != null && courtCategory.equalsIgnoreCase("CR"))
        {
        	court = (NumberSuffixUtil.getSuffixForString(sb.toString()));
        }
        return court;
    }
    
    private String formatName(String name)
    {
        //Added this to fix defect JIMS200034774
        //Need to do this since the name comes from Criminal case as ENGLAND, STEPHAN ERIK 
        //Its not separately stored in the table
        StringBuffer sb = new StringBuffer(name.substring(name.indexOf(',')+1));
        sb.append(" ").append(name.substring(0,name.indexOf(',')));
        return sb.toString();
    }

    private SupervisionOrderReportingBean populateReportingBean(SupervisionOrderForm sof, HttpServletRequest aRequest) {
        SupervisionOrderReportingBean reqBean = new SupervisionOrderReportingBean();
        CaseOrderResponseEvent caseOrder = getCaseOrderResponseEvent(sof, aRequest); 
        SupervisionOrderDetailResponseEvent orderDetailResponseEvent = null;
        if (sof.getPrintAction() == null || sof.getPrintAction().equals("")) {
            orderDetailResponseEvent = UISupervisionOrderHelper.getSupervisionOrderDetailsForReporting(caseOrder.getOrderId());
            reqBean.setOrderTitleId(caseOrder.getOrderTitleId());
            // or
        } else {
            orderDetailResponseEvent = UISupervisionOrderHelper.getSupervisionOrderDetailsForReporting(sof.getOrderId());
            reqBean.setOrderTitleId(orderDetailResponseEvent.getOrderTitleId());
            
        }

        // get juvenile information for Determinate Sentencing Reports
        String juvCrt = orderDetailResponseEvent.getJuvenileCourtId();
        if (StringUtils.isNotEmpty(juvCrt))
        {
        	String juvCourt = juvCrt.substring(4, 7);
        	reqBean.setJuvCourt(NumberSuffixUtil.getSuffixForString(juvCourt));
        	setJuvOrderSignedDate(reqBean, orderDetailResponseEvent.getJuvSupervisionOrderBeginDate());
         	if (StringUtils.isNotEmpty(orderDetailResponseEvent.getJuvSupervisionLengthString())){
         		reqBean.setJuvTermTimePeriod(orderDetailResponseEvent.getJuvSupervisionLengthString());
         	}else{
         		reqBean.setJuvTermTimePeriod(orderDetailResponseEvent.getJailTime());
         	}
        }
        
        // get SupervisionOrderConditions collections
        Collection conditions = UISupervisionOrderHelper.convert(sof.isVersionTypeChangeAllowed(), sof.isRefreshRefVarsAllowed(), orderDetailResponseEvent.getConditions(),
        		sof.getReferenceVariableMap());

        // sort it by sequence num
        Collections.sort((List) conditions, ConditionDetailResponseEvent.SeqNumComparator);

        List conditionList = null;
        List specialCourts = new ArrayList();
        conditionList = new ArrayList();
        int conditionNumber = 0;
        
        int subConditionFeesIndex = 0;
    	ConditionBean feesCondition = null;
        String feesConditionSeqNumber = null;
        //HashMap conditionNumLettrMap = getConditionNumberLetter();
        boolean isFeesFirstConditionFound = false;
        
        Iterator iter = conditions.iterator();
        while (iter.hasNext()) 
        {
            ConditionDetailResponseEvent condResp = (ConditionDetailResponseEvent) iter.next();
            String resolvedDesc = condResp.getResolvedDescription();
            
            if("FEES".equalsIgnoreCase(condResp.getGroup1Name()))
            {
            	if(!isFeesFirstConditionFound)
            	{
            		isFeesFirstConditionFound = true;
            		
            		ConditionBean condition = new ConditionBean();
            		if(++conditionNumber == condResp.getSequenceNum())
                	{
                		 condition.setConditionNumber("(" + Integer.toString(condResp.getSequenceNum()) + ")");
                		 feesConditionSeqNumber = Integer.toString(condResp.getSequenceNum());
                	}
                	else
                	{
                		condition.setConditionNumber("(" + Integer.toString(conditionNumber) + ")");
                		feesConditionSeqNumber = Integer.toString(conditionNumber);
                	}
                    condition.setConditionNumber("(" + feesConditionSeqNumber + ")");
            		condition.setConditionName(resolvedDesc);
            		conditionList.add(condition);
            		
            		feesCondition = condition;
            		feesCondition.setSubConditions(new ArrayList());
            		subConditionFeesIndex = 0;
            	}
            	else
            	{
            		if(feesCondition!=null)
            		{
                		SubConditionBean subCondition = new SubConditionBean();
                		subCondition.setSubConditionNumber(feesConditionSeqNumber + "." + (++subConditionFeesIndex));
                		subCondition.setSubConditionName(resolvedDesc);
                		
                		feesCondition.getSubConditions().add(subCondition);
            		}
            	}
            }
            else
            {
            	ConditionBean condition = new ConditionBean();
            	++conditionNumber;
           		condition.setConditionNumber("(" + Integer.toString(conditionNumber) + ")");
            	condition.setConditionName(resolvedDesc);
            	conditionList.add(condition);
            }
        }

        reqBean.setConditions(conditionList);

        reqBean.setCause(orderDetailResponseEvent.getCaseNum());
        
        reqBean.setPrintSpanish(orderDetailResponseEvent.isPrintSpanish());
        
        if("M".equals(orderDetailResponseEvent.getVersionTypeId()))
        {
            GetSupervisionOrderVersionsEvent reqEvent = new GetSupervisionOrderVersionsEvent();
            reqEvent.setAgencyId(orderDetailResponseEvent.getAgencyId());
            reqEvent.setCaseNum(orderDetailResponseEvent.getCriminalCaseid());
            reqEvent.setOrderId(orderDetailResponseEvent.getOrderId());
            reqEvent.setOrderChainNum(orderDetailResponseEvent.getOrderChainNum());
        	getSupOrderVersions(reqBean, reqEvent);
        }
        specialCourts = (List) sof.getSpecialCourtCds();
        String specCourtCd = "";
        String cd =  orderDetailResponseEvent.getSpecialCourtCd();
        if ( StringUtils.isNotEmpty( cd ) )
        {
	        if ( specialCourts != null && specialCourts.size()> 0 )
	        {
	        	for ( int i =0; i < specialCourts.size(); i++ )
	            {
	        		CodeResponseEvent specialCourtCodes = (CodeResponseEvent) specialCourts.get(i);
	                if ( cd.equals( specialCourtCodes.getCode() ))
	                {
	                	specCourtCd = specialCourtCodes.getDescription();
	                	break;
	                }
	            }
	        }
        }
        
        StringBuffer specialCourt = new StringBuffer();
        if ( StringUtils.isNotEmpty( specCourtCd ) )
        {
        	specialCourt.append(specCourtCd);
        	specialCourt.append("/");
        }
        String courtNum = orderDetailResponseEvent.getCurrentCourtNum();
        String courtCategory = orderDetailResponseEvent.getCurrentCourtCategory();
        specialCourt.append(padCourtNum(courtNum, courtCategory));
        String court = specialCourt.toString();
        reqBean.setCourt(court);
        sof.setCourtCategory(orderDetailResponseEvent.getOrderCourtCategory());
        
        if (orderDetailResponseEvent.getCdi().equals(SupervisionConstants.FELONY_CDI)){
        	specialCourt = new StringBuffer();
            if ( StringUtils.isNotEmpty( specCourtCd ) )
            {
            	specialCourt.append(specCourtCd);
            	specialCourt.append("/");
            }
            courtNum = orderDetailResponseEvent.getOrderCourtNum();
            courtCategory = orderDetailResponseEvent.getOrderCourtCategory();
            specialCourt.append(padCourtNum(courtNum, courtCategory));
            court = specialCourt.toString();
            reqBean.setSentencingCourt(court);

        } else {
        	reqBean.setSentencingCourt(court);
        }
        
        reqBean.setOffense(orderDetailResponseEvent.getPrintedOffenseDesc());

        reqBean.setDopDay(NumberSuffixUtil.getDateSuffixFormatStringMMDDYY(orderDetailResponseEvent.getProbationStartDate()));

        reqBean.setSignedByDefendantDate(NumberSuffixUtil.getLongDateString(orderDetailResponseEvent.getSignedByDefendantDate()));
        reqBean.setSignedByJudgeDate(NumberSuffixUtil.getLongDateString(orderDetailResponseEvent.getSignedByJudgeDate()));
        setOrderSignedDate(reqBean, orderDetailResponseEvent.getSignedByJudgeDate());
        
        Name signedByName = new Name();
        signedByName = orderDetailResponseEvent.getSignedByName();
        if ( signedByName != null ){
        	
        	reqBean.setSignedByFLName(signedByName.getCompleteFullNameFirst());
        }
        
        Name judgeName = new Name();
        judgeName = orderDetailResponseEvent.getOrigJudgeName();
        if( StringUtils.isNotEmpty( judgeName.getLastName()) )
        {
        	reqBean.setOrigJudgeFLName(judgeName.getCompleteFullNameFirst());
        }else{
        	reqBean.setOrigJudgeFLName(signedByName.getCompleteFullNameFirst());
        }
          
        reqBean.setSignedDate(NumberSuffixUtil.getLongDateString(orderDetailResponseEvent.getOrderSignedDate()));

        reqBean.setCloName(orderDetailResponseEvent.getOrderPresentorName().getCompleteFullNameFirst());

        String printedName = "";
        printedName = replaceBadTags(orderDetailResponseEvent.getPrintedName());
        if( StringUtils.isNotEmpty( printedName ))
        {
        	reqBean.setName(printedName.trim());
        }else{
        	reqBean.setName(formatName(orderDetailResponseEvent.getDefendantName().getCompleteFullNameFirst().trim()));
        }
        
        reqBean.setOrderTitle(SupervisionOrderListHelper.getOrderTitleName(sof.getAllOrderTitleList(), orderDetailResponseEvent.getOrderTitleId()));
        if(StringUtils.isNotEmpty(orderDetailResponseEvent.getPlea()))
        {
        	String plea = orderDetailResponseEvent.getPlea();
        	if(plea.equals("G"))
        	{
        		reqBean.setPlea("GUILTY");
        	}else
    		if(plea.equals("N"))
        	{
        		reqBean.setPlea("NOT GUILTY");
        	}else
        	if(plea.equals("O"))
        	{
        		reqBean.setPlea("NOLO CONTENDERE");
        	}
        }
        reqBean.setSpn(orderDetailResponseEvent.getDefendantId());
        reqBean.setSummaryofChanges( replaceBadTags(orderDetailResponseEvent.getSummaryOfChanges()) );
        
        // RRY changed to get the comments from the form instead of the response
        String orderComments = orderDetailResponseEvent.getComments();
        
        if ( orderComments != null && !"".equals( orderComments )  ){
        	
        	 reqBean.setComments( replaceBadTags( orderComments ) );
        	 
        }else {
        	
            reqBean.setComments( replaceBadTags( sof.getComments() ) );
        }
        reqBean.setProbationEndDate(NumberSuffixUtil.getDateSuffixFormatStringMMDDYY(orderDetailResponseEvent.getProbationEndDate()));

        setPrintDOPDate(reqBean, orderDetailResponseEvent.getProbationStartDate()); 
        setPrintDOPEndDate(reqBean, orderDetailResponseEvent.getProbationEndDate());
        

        reqBean.setSupervisionLengthNum(orderDetailResponseEvent.getSupervisionLengthNum());
        reqBean.setTermHCJ(orderDetailResponseEvent.getJailTime());
        reqBean.setTermSTJ(orderDetailResponseEvent.getDeferredSupervisionLength());
        reqBean.setSupOrderRevisionDate(orderDetailResponseEvent.getSupOrderRevisionDate());
        reqBean.setFineAmountTotal(orderDetailResponseEvent.getFineAmountTotal());

        if (!orderDetailResponseEvent.getVersionTypeId().equals("") && !orderDetailResponseEvent.getVersionTypeId().equals("O")) {
            reqBean.setVersionNumber(NumberSuffixUtil.getSuffix(orderDetailResponseEvent.getVersionNum()));
            reqBean.setVersionType(SupervisionOrderListHelper.getCodeDescription(sof.getVersionTypeList(), orderDetailResponseEvent
                    .getVersionTypeId()));
        }
        return reqBean;
    }
    
    /**
     * Sets the Added and Deleted conditions for a Modified order by comparing with the previous order.
     */    
    private void getSupOrderVersions(SupervisionOrderReportingBean reqBean, GetSupervisionOrderVersionsEvent reqEvent) 
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(reqEvent);
        CompositeResponse compositeResponse = UISupervisionOrderHelper.getCompositeResponse(dispatch);
        // get OrderResposneEvent
        Collection coll = MessageUtil.compositeToCollection(compositeResponse,
                SupervisionOrderDetailResponseEvent.class);
        coll = MessageUtil.processEmptyCollection(coll);
        // set order title names and version types
        //UISupervisionOrderHelper.setOrderDescriptions(coll, sof);

        // set collection in form
        List orderVersions = (List) coll;
        Collections.sort(orderVersions, SupervisionOrderDetailResponseEvent.OrderIdComparator);
        SupervisionOrderDetailResponseEvent orderVersion,previousOrderVersion = null;
        
        if(orderVersions.size() > 0)
        {
	        for(int c =0; c <orderVersions.size(); c++){
	        	SupervisionOrderDetailResponseEvent sodre = (SupervisionOrderDetailResponseEvent) orderVersions.get(c);
	        	if (sodre.getOrderId().equals(reqEvent.getOrderId() ) ){
	        		orderVersion = (SupervisionOrderDetailResponseEvent) orderVersions.get(c);
	        		if (c > 0) {
	        			previousOrderVersion = (SupervisionOrderDetailResponseEvent) orderVersions.get(c -1);
	        		}
	       			getViewVersionConditions(orderVersion, previousOrderVersion, reqBean);
	       			break;
	        	}
	        }
// old code not checking current orderId, changed by defect #65216 on 6-3-2010	        
//            orderVersion = (SupervisionOrderDetailResponseEvent) orderVersions.get(orderVersions.size()-1);
//            if (orderVersions.size() > 1) {
//                previousOrderVersion = (SupervisionOrderDetailResponseEvent) orderVersions.get(orderVersions.size()-2);
//            }
//            getViewVersionConditions(orderVersion, previousOrderVersion, reqBean);            
        }
    }
    
    private void getViewVersionConditions(SupervisionOrderDetailResponseEvent selectedOrder,
            SupervisionOrderDetailResponseEvent previousOrder, SupervisionOrderReportingBean reqBean) {
        //Collection selectedConditions = new ArrayList();
        Collection conditionsAdded = new ArrayList();
        Collection conditionsRemoved = new ArrayList();
        Collection conditionsUpdated = new ArrayList();
        Collection selectedOrderConditions = selectedOrder.getConditions();
        // if there is no previous Order, conditions should be shown as if they
        // exist in both Orders
        if (previousOrder == null) {
            previousOrder = selectedOrder;
        }
        Collection previousOrderConditions = previousOrder.getConditions();
        // create a set of condition ids and a map
        Set prevCondIds = new HashSet();
        // create a map of conditionId and responseEvent
        Map prevCondMap = new HashMap();
        for (Iterator prevIter = previousOrderConditions.iterator(); prevIter.hasNext();) {
            SupOrderConditionResponseEvent previousOrderCondition = (SupOrderConditionResponseEvent) prevIter.next();
            prevCondIds.add(previousOrderCondition.getConditionId());
            prevCondMap.put(previousOrderCondition.getConditionId(), previousOrderCondition);
        }
        // traverse through current Order
        for (Iterator selectedIter = selectedOrderConditions.iterator(); selectedIter.hasNext();) {
            SupOrderConditionResponseEvent selectedOrderCondition = (SupOrderConditionResponseEvent) selectedIter
                    .next();
            // convert into ConditionDetailResponseEvent to add into collection
            ConditionDetailResponseEvent selectedCondition = convert(selectedOrderCondition);

            SupOrderConditionResponseEvent previousOrderCondition = (SupOrderConditionResponseEvent) prevCondMap
                    .get(selectedOrderCondition.getConditionId());
            if (previousOrderCondition != null) { // condition exists in both
                // versions
                // get resolved descriptions
                String selectedResolveDesc = UIUtil.removeXMLtags(selectedOrderCondition.getResolvedDescription(), true);
                String previousResolveDesc = UIUtil.removeXMLtags(previousOrderCondition.getResolvedDescription(), true);
                // check if condition has been updated
                if (selectedResolveDesc == null || previousResolveDesc == null
                        || selectedResolveDesc.equals(previousResolveDesc)) { // unchanged
                    // condition
                    //selectedCondition.setCompareToPreviousVersion("");
                } else { // updated condition
                    conditionsUpdated.add(selectedCondition.getResolvedDescription());
                }
                // remove it from the map
                prevCondMap.remove(selectedOrderCondition.getConditionId());
            } else { // added conditions
                //selectedCondition.setCompareToPreviousVersion("added");
                conditionsAdded.add(selectedCondition.getResolvedDescription());
            }
        }
        // removed conditions
        Collection removedConditions = prevCondMap.values();
        if (removedConditions != null) {
            for (Iterator iter = removedConditions.iterator(); iter.hasNext();) {
                SupOrderConditionResponseEvent removedCondition = (SupOrderConditionResponseEvent) iter.next();
                // convert
                ConditionDetailResponseEvent selectedCondition = convert(removedCondition);
                conditionsRemoved.add(selectedCondition.getResolvedDescription());
            }
        }       
        reqBean.setConditionsAdded(conditionsAdded);
        reqBean.setConditionsRemoved(conditionsRemoved);
        reqBean.setConditionsUpdated(conditionsUpdated);
        //return selectedConditions;
    }

    private ConditionDetailResponseEvent convert(SupOrderConditionResponseEvent orderCondRespEvent) {
        ConditionDetailResponseEvent condDetailRespEvent = new ConditionDetailResponseEvent();
        condDetailRespEvent.setConditionId(orderCondRespEvent.getConditionId());
        condDetailRespEvent.setDescription(orderCondRespEvent.getDescription());
        condDetailRespEvent.setResolvedDescription(orderCondRespEvent.getResolvedDescription());
        condDetailRespEvent.setName(orderCondRespEvent.getName());
        condDetailRespEvent.setLikeConditionInd(orderCondRespEvent.isLikeConditionInd());
        condDetailRespEvent.setSequenceNum(orderCondRespEvent.getSequenceNum());
        return condDetailRespEvent;
    }
    
    /**
     * Replaces all the <p> and &nbsp; tags in the string passed.
     * @param string The string in which the tags needs to be replaced
     * @return The string with replaced tags.
     */
    private String replaceBadTags(String string)
    {
    	String validString = "";
        if( !"".equals( string) && string != null )
        {
        	validString = string.replaceAll("<", "&#60;").replaceAll("\\\\+", "&#92;&#92;");
        }
        return validString;
    }
}