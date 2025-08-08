package ui.juvenilecase.prodsupport.action.update;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.productionsupport.UpdateProductionSupportFacilityHeaderEvent;
import messaging.productionsupport.reply.ProductionSupportFacilityHeaderResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import ui.common.StringUtil;
import ui.juvenilecase.form.ProdSupportForm;

/**
 * @author rcarter
 * 	
 * Perform Update of Facility Header Fields
 */

public class PerformUpdateFacilityHeaderAction extends Action {

	private Logger log = Logger.getLogger("PerformUpdateFacilityHeaderAction");
	ArrayList<ProductionSupportFacilityHeaderResponseEvent> facilityHeaderList = null;
	
	/**
	 * 
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Boolean isChange = false;
		ProdSupportForm regform = (ProdSupportForm) form;
		regform.setMsg("");
		Date datePC = null,dateHearing =null,newEndDate=null,newBeginDate=null;
		
		ProductionSupportFacilityHeaderResponseEvent record = retrieveRecord(regform);
				
		if ( record==null ){
			regform.setMsg("PerformUpdateFacilityHeaderAction.java (60) - Could not retrieve record.");
			return (mapping.findForward("error"));
		}
		
		if(record != null && (record.getJuvenileId() == null || "".equals(record.getJuvenileId())))
		{
		    regform.setMsg("Juvenile Id is missing. Please enter a valid juvenile number.");
		    return (mapping.findForward("error"));
		}
	
		// check to see if user updated anything
		if( record.getFacilityStatus() == null ){
		    
		    record.setFacilityStatus("");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		if( record.getProbableCauseHearingDate() != null && StringUtils.isNotEmpty( record.getProbableCauseHearingDate()) ){
		    
		    datePC = sdf.parse(record.getProbableCauseHearingDate());
		    
		}else{
		    
		    datePC = sdf.parse("01/01/1953");
		    
		}
		if( record.getNextHearingDate() != null && StringUtils.isNotEmpty(record.getNextHearingDate() )){
		    
		    dateHearing = sdf.parse( record.getNextHearingDate() );
		    
		}else{
		    dateHearing = sdf.parse("01/01/1953");
		    		    
		}
		if( regform.getNewEndDate() != null && StringUtils.isNotEmpty(regform.getNewEndDate().trim()) ){
		    
		    newEndDate = sdf.parse( regform.getNewEndDate() );
		}else{
		    newEndDate = sdf.parse("01/01/1953" );
		}
		
		if( regform.getNewBeginDate() != null && StringUtils.isNotEmpty(regform.getNewBeginDate().trim()) ){
		    
		    newBeginDate = sdf.parse( regform.getNewBeginDate().trim());
		}else{
		    
		    newBeginDate = sdf.parse("01/01/1953");
		}
		
		String oldJuvNum = "";
		
		if(regform.getFacilityHeaderList().size() > 0)
		{
		    oldJuvNum = ((ProductionSupportFacilityHeaderResponseEvent)regform.getFacilityHeaderList().get(0)).getJuvenileId();
		}

		if( record.getBookingReferralNum().equalsIgnoreCase( regform.getNewReferralNum()) && oldJuvNum.equalsIgnoreCase(regform.getJuvenileId()) 
			&& record.getBookingSupervisionNum().equalsIgnoreCase( regform.getNewcasefileId())
			&& record.getFacilityCode().equalsIgnoreCase(regform.getNewHeaderFacilityCd()) 
			&& record.getFacilityStatusCode().equalsIgnoreCase(regform.getFacilityStatus())
			&& datePC.compareTo(newEndDate) ==0 && dateHearing.compareTo(newBeginDate) ==0
			&& regform.getNewJuvseqnum().equals( record.getLastSequenceNum() ) ){
		    
		    regform.setMsg("No Change detected.");
		    return mapping.findForward("error");
		    
		}
		
		
		// Call to update the Facility Header
		UpdateProductionSupportFacilityHeaderEvent updateHeaderEvent = 
			(UpdateProductionSupportFacilityHeaderEvent)EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTFACILITYHEADER);
		
		
		updateHeaderEvent.setHeaderId(record.getHeaderId());
		updateHeaderEvent.setBookingReferralNum(regform.getNewReferralNum());
		updateHeaderEvent.setBookingSupervisionNum(regform.getNewcasefileId());
		updateHeaderEvent.setLastSequenceNum(regform.getNewJuvseqnum());
		updateHeaderEvent.setFacilityCode(regform.getNewHeaderFacilityCd());
		//bug fix for 113107
		if (StringUtil.isEmpty(regform.getFacilityStatus()))
		    updateHeaderEvent.setFacilityStatusCode(null); 
	        else
	              updateHeaderEvent.setFacilityStatusCode(regform.getFacilityStatus());
		//
		if(StringUtils.isNotEmpty(regform.getNewBeginDate())){
		    
		    updateHeaderEvent.setNextHearingDate(DateUtil.stringToDate(regform.getNewBeginDate(), DateUtil.DATE_FMT_1));
		}else{
		    updateHeaderEvent.setNextHearingDate(DateUtil.stringToDate(null, DateUtil.DATE_FMT_1));
		}
		if(StringUtils.isNotEmpty(regform.getNewEndDate())){
		    updateHeaderEvent.setProbableCauseHearingDate(DateUtil.stringToDate(regform.getNewEndDate(), DateUtil.DATE_FMT_1));
		}else{
		    updateHeaderEvent.setProbableCauseHearingDate(DateUtil.stringToDate(null, DateUtil.DATE_FMT_1));
		}
		
		if (oldJuvNum.equalsIgnoreCase(regform.getJuvenileId()) == false)
		{
		    updateHeaderEvent.setJuvenileId(regform.getJuvenileId());

		    ArrayList<ProductionSupportFacilityHeaderResponseEvent> facHeadList = regform.getFacilityHeaderList();

		    for (ProductionSupportFacilityHeaderResponseEvent event : facHeadList)
		    {
			event.setJuvenileId(regform.getJuvenileId());
		    }

		    regform.setFacilityHeaderList(facHeadList);
		} else {
	    
		    updateHeaderEvent.setJuvenileId(oldJuvNum); //Bug 185595
		}

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent( updateHeaderEvent );
		
		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply( );
 		MessageUtil.processReturnException( compositeResponse );
 		
 		facilityHeaderList = (ArrayList<ProductionSupportFacilityHeaderResponseEvent>) 
 						MessageUtil.compositeToCollection(compositeResponse, ProductionSupportFacilityHeaderResponseEvent.class);
 		regform.setFacilityHeaderList(facilityHeaderList);
		regform.setMsg("");
		return mapping.findForward("success");
	}
	
	/**
	 * 
	 * @param regform
	 * @return
	 */
	private ProductionSupportFacilityHeaderResponseEvent retrieveRecord(ProdSupportForm regform){
		
		ArrayList recs = regform.getFacilityHeaderList();
		ProductionSupportFacilityHeaderResponseEvent record = null;
		
		Iterator iter = recs.iterator();
		if (iter.hasNext())
		{
			record = (ProductionSupportFacilityHeaderResponseEvent)iter.next();
		}
		
		return record;
	}

}
