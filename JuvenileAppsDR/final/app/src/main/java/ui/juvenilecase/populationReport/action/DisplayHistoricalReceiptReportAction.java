package ui.juvenilecase.populationReport.action;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.reply.JuvenileProfilesResponseEvent;
import messaging.juvenilecase.GetJuvenileFacilityHistoricalReceiptsEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHistoricalReceiptsResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.populationReport.form.JuvenileFacilityReceiptForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

public class DisplayHistoricalReceiptReportAction extends JIMSBaseAction
{
	/**
	 * @roseuid 42AF409F01B5
	 */
	public DisplayHistoricalReceiptReportAction()
	{
	}

	/**
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected void addButtonMapping(Map buttonMap)
	{
		buttonMap.put("button.submit", "submitSearch");
		buttonMap.put("button.print", "printHistoricalReceiptReport");
		buttonMap.put("button.cancel", "cancel");
		return;
	}
	/*
	 * 
	 */
	public ActionForward submitSearch(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileFacilityReceiptForm receiptsForm = (JuvenileFacilityReceiptForm)aForm;
		CompositeResponse response = retrieveHistoricalReceipts(receiptsForm);
		// Perform Error handling
		MessageUtil.processReturnException(response);
		// set values for the historical receipt data retrieved
		populateHistoricalReceiptForm(receiptsForm, response);
		// set error message if there are not records
		ActionErrors errors = new ActionErrors();
		if(receiptsForm.getJuvenileName() == null || receiptsForm.getJuvenileName().length()==0){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage( UIConstants.NO_JUVENILE_RECORD_FOUND_ERROR ));
			saveErrors(aRequest, errors);			
		}else if(receiptsForm.getHistoricalReceipts() != null && receiptsForm.getHistoricalReceipts().size() == 0){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage( UIConstants.NO_FACILITY_RECEIPTS_FOUND_ERROR ));
			saveErrors(aRequest, errors);		
		}		
		if(errors.size() > 0){
			return( aMapping.findForward(UIConstants.SEARCH_FAILURE) );
		}
						
		return aMapping.findForward(UIConstants.SUCCESS);
	}	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward printHistoricalReceiptReport(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileFacilityReceiptForm receiptsForm = (JuvenileFacilityReceiptForm)aForm;
		CompositeResponse response = retrieveHistoricalReceipts(receiptsForm);
		// Perform Error handling
		MessageUtil.processReturnException(response);
		// set values for the historical receipt data retrieved
		populateHistoricalReceiptForm(receiptsForm, response);
		// set error message if there are not records
		ActionErrors errors = new ActionErrors();
		if(receiptsForm.getJuvenileName() == null || receiptsForm.getJuvenileName().length()==0){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage( UIConstants.NO_JUVENILE_RECORD_FOUND_ERROR ));
			saveErrors(aRequest, errors);			
		}else if(receiptsForm.getHistoricalReceipts() != null && receiptsForm.getHistoricalReceipts().size() == 0){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage( UIConstants.NO_FACILITY_RECEIPTS_FOUND_ERROR ));
			saveErrors(aRequest, errors);		
		}		
		if(errors.size() > 0){
			return( aMapping.findForward(UIConstants.SEARCH_FAILURE) );
		}
		aRequest.getSession().setAttribute("receiptsForm", receiptsForm);

		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.FACILITY_HISTORICAL_RECEIPT_REPORT_NAME);
		
		return null;
	}
	
	/**
	 * 
	 * @param receiptsForm
	 * @return
	 */
	private CompositeResponse retrieveHistoricalReceipts(JuvenileFacilityReceiptForm receiptsForm){
		GetJuvenileFacilityHistoricalReceiptsEvent receiptEvent = new GetJuvenileFacilityHistoricalReceiptsEvent();
		// set search values and post request 
		receiptEvent.setJuvenileNum(receiptsForm.getJuvenileNumber());
		receiptEvent.setFacilityCode(receiptsForm.getFacilityId());
		CompositeResponse response = (CompositeResponse)MessageUtil.postRequest(receiptEvent);
		return response;
	}
	
	/**
	 * populate the form with results data
	 * @param receiptsForm
	 * @param response
	 */
	private void populateHistoricalReceiptForm(JuvenileFacilityReceiptForm receiptsForm, CompositeResponse response){	
		List receipts = MessageUtil.compositeToList(response, JuvenileFacilityHistoricalReceiptsResponseEvent.class);
		Collections.sort( receipts, JuvenileFacilityHistoricalReceiptsResponseEvent.ReleaseDateComparator );
		receiptsForm.setHistoricalReceipts(receipts);
		receiptsForm.setNumReceipts(receipts.size());
		// set values for the juvenile information
		JuvenileProfilesResponseEvent juvenileProfileResponse = (JuvenileProfilesResponseEvent)MessageUtil.filterComposite(response, JuvenileProfilesResponseEvent.class);
		// populate juvenile number with original search value, since nothing was found 
		if(juvenileProfileResponse.getJuvenileNum() != null ){	
			receiptsForm.setJuvenileNumber(juvenileProfileResponse.getJuvenileNum());
			receiptsForm.setJuvenileName(formatJuvenileFullName(juvenileProfileResponse));
			receiptsForm.setJuvenileRace(juvenileProfileResponse.getRace());
			receiptsForm.setJuvenileSex(juvenileProfileResponse.getSex());
			receiptsForm.setJuvenileDateOfBirth(juvenileProfileResponse.getDateOfBirth());
		}else{
			receiptsForm.setJuvenileName("");
			receiptsForm.setJuvenileRace("");
			receiptsForm.setJuvenileSex("");
			receiptsForm.setJuvenileDateOfBirth(null);
		}
	}
	
	/**
	 * format full name by "lastname, firstname middleName"
	 * @param juvenileProfileResponse
	 * @return
	 */
	private static String formatJuvenileFullName(JuvenileProfilesResponseEvent juvenileProfileResponse){
		StringBuffer fullName = new StringBuffer();
		if(juvenileProfileResponse.getLastName() != null &&  juvenileProfileResponse.getLastName().length() > 0){
			fullName.append(juvenileProfileResponse.getLastName() + ", ");
		}
		if(juvenileProfileResponse.getFirstName() != null && juvenileProfileResponse.getFirstName().length() > 0){
			fullName.append(juvenileProfileResponse.getFirstName() + " ");
		}
		if(juvenileProfileResponse.getMiddleName() != null && juvenileProfileResponse.getMiddleName().length() > 0){
			fullName.append(juvenileProfileResponse.getMiddleName());
		}	

		return fullName.toString();
	}
	
	/*
	 * 
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileFacilityReceiptForm receiptsForm = (JuvenileFacilityReceiptForm)aForm;
		receiptsForm.clear();
		return aMapping.findForward("cancel");
	}

}
