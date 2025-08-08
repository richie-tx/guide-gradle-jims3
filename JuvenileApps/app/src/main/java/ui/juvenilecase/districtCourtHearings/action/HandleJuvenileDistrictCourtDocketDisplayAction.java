package ui.juvenilecase.districtCourtHearings.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.facility.GetJuvenileFacilityDetailsEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;

import pd.juvenilecase.JJSFacility;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.districtCourtHearings.StandardDocketReportBean;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

public class HandleJuvenileDistrictCourtDocketDisplayAction extends LookupDispatchAction {

	public HandleJuvenileDistrictCourtDocketDisplayAction() {
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CourtHearingForm courtHearingForm = (CourtHearingForm)aForm;
		if (courtHearingForm.getDisplaying().equalsIgnoreCase("A"))
		{
		    courtHearingForm.setDktSearchResults(courtHearingForm.getAncillaryDockets());
		    return aMapping.findForward(UIConstants.COURT_DOCKET_DISPLAY_PRINT);
		} else if (courtHearingForm.getDisplaying().equalsIgnoreCase("D")) {
		    courtHearingForm.setDktSearchResults(courtHearingForm.getDelinquencyDockets());
		    return aMapping.findForward(UIConstants.COURT_DOCKET_DISPLAY_PRINT);
		} else if (courtHearingForm.getDisplaying().equalsIgnoreCase("B")) {
		    courtHearingForm.setDktSearchResults(courtHearingForm.getAllDockets());
		    return aMapping.findForward(UIConstants.COURT_DOCKET_DISPLAY_PRINT);
		}else
		{
		    courtHearingForm.setCursorPosition("displayingType");
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Displaying Type Is Required, Valid Types Are 'A', 'D', and 'B'")); //Displaying Type Is Required, Valid Types Are Ancillary, Delinquency, And Both
		    saveErrors(aRequest, errors);
		    return aMapping.findForward(UIConstants.FAILURE);
		}
	}

	/**
	 * standardCourtDocket
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
    public ActionForward standardCourtDocket(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	Collection<DocketEventResponseEvent> docketsList = courtHearingForm.getDktSearchResults();
	StandardDocketReportBean reportBean = new StandardDocketReportBean();
	// check if the PDF info is already available, the below if{} reduces redundant processing, if the data is already retrieved
	if (courtHearingForm.getUpdateFlag().equalsIgnoreCase("listReady"))
	{
	    String display = courtHearingForm.getDisplaying();
	    if (display.equalsIgnoreCase("b"))
	    {
		reportBean.setDisplay("");
	    }
	    else
		if (display.equalsIgnoreCase("a"))
		{
		    reportBean.setDisplay("ANCILLARY");
		}
		else
		    if (display.equalsIgnoreCase("d"))
		    {
			reportBean.setDisplay("DELIQUENCY");
		    }
	    //sorts in ascending order by Petition Num. //requirement for Standard Docket PDF
	    Collections.sort((List<DocketEventResponseEvent>) docketsList, new Comparator<DocketEventResponseEvent>() {
		@Override
		public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
		{
		    return new CompareToBuilder().append(evt1.getCourtTime(), evt2.getCourtTime()).append(evt1.getRecType(), evt2.getRecType()).append(evt1.getPetitionNumber(), evt2.getPetitionNumber()).toComparison();
		}
	    });
	    Iterator<DocketEventResponseEvent> itratr = docketsList.iterator();
	    ArrayList<DocketEventResponseEvent> docketsListForPDF = new ArrayList<DocketEventResponseEvent>();
	    DocketEventResponseEvent resp;
	    String currDocketTime = "";
	    String prevDocketTime = "";
	    while (itratr.hasNext())
	    {
		DocketEventResponseEvent docket = (DocketEventResponseEvent) itratr.next();
		resp = new DocketEventResponseEvent();
		currDocketTime = docket.getCourtTime();
		if (currDocketTime != null && !currDocketTime.isEmpty())
		{
		    if (currDocketTime.equals(prevDocketTime))
		    {
			JuvenileDistrictCourtHelper.populateDocketInfo(docket, resp);
			resp.setCourtTime("");
		    }
		    else
		    {
			JuvenileDistrictCourtHelper.populateDocketInfo(docket, resp);
		    }
		}
		else
		{
		    JuvenileDistrictCourtHelper.populateDocketInfo(docket, resp);
		}
		prevDocketTime = currDocketTime;
		docketsListForPDF.add(resp);
	    }
	    reportBean.setDockets(docketsListForPDF);
	    reportBean.setCourtIdWithSuffix(courtHearingForm.getCourtIdWithSuffix());
	    reportBean.setCourtDate(courtHearingForm.getCourtDate());
	    aRequest.getSession().setAttribute("reportInfo", reportBean);
	    BFOPdfManager pdfManager = BFOPdfManager.getInstance();
	    // let the pdfManager know that the report should be saved in the request during report creation
	    aRequest.setAttribute("isPdfSaveNeeded", "false");
	    pdfManager.createPDFReport(aRequest, aResponse, PDFReport.STANDARD_COURT_DOCKET_REPORT);
	    return null;
	}

	Iterator<DocketEventResponseEvent> iterator = docketsList.iterator();
	while (iterator.hasNext())
	{
	    DocketEventResponseEvent docketRespEvt = (DocketEventResponseEvent) iterator.next();
	    String issueFlag = "";
	    // If �minutes� value is less than 5, �minutes� position should display as �00�  
	    if (docketRespEvt.getCourtTime() != null && !docketRespEvt.getCourtTime().isEmpty())
	    {
		String courtTime = docketRespEvt.getCourtTime().substring(3, 5);
		if (Integer.valueOf(courtTime) < 5 && Integer.valueOf(courtTime) > 0)
		{
		    docketRespEvt.setCourtTime(docketRespEvt.getCourtTime().substring(0, 3) + "00");
		}
	    }
	    if (docketRespEvt.getAttorneyConnection() != null && !docketRespEvt.getAttorneyConnection().isEmpty())
	    {
		if (docketRespEvt.getAttorneyConnection().equalsIgnoreCase("AAT"))
		{
		    docketRespEvt.setAttorneyConnection("APPOINTED");
		}
		else
		{
		    if (docketRespEvt.getAttorneyConnection().equalsIgnoreCase("HAT"))
		    {
			docketRespEvt.setAttorneyConnection("HIRED");
		    }
		    if (docketRespEvt.getAttorneyConnection().equalsIgnoreCase("PDO"))
		    {
			docketRespEvt.setAttorneyConnection("PUBLIC DEFENDER");
		    }
		}
	    }
	    /*if (docketRespEvt.getSettingReason() != null && !docketRespEvt.getSettingReason().isEmpty())
	    {
		issueFlag = JuvenileHearingTypeCode.find((docketRespEvt.getSettingReason())).getIssueInd();
		if (issueFlag.equalsIgnoreCase("J"))
		{
		    docketRespEvt.setJuryFlag("(JURY)");
		}
		else
		    if (issueFlag.equalsIgnoreCase("I"))
		    {
			docketRespEvt.setJuryFlag("(TRIAL)");
		    }
	    }*///BUG# 71210 changes //code commented and added the same logic in GetJJSCLAncillaryCourtByReferreeCourtCommand BUG: 84372

	    if (docketRespEvt.getDocketType().equalsIgnoreCase("DETENTION") || docketRespEvt.getDocketType().equalsIgnoreCase("COURT"))
	    {
		JuvenileDistrictCourtHelper.setJuvenileFamilyDetailsForDockets(docketRespEvt.getJuvenileNumber(), docketRespEvt);
		// Get Juvenile Facility/Detention Information
		JuvenileFacilityHeaderResponseEvent facilityHeaderResp = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(docketRespEvt.getJuvenileNumber());
		if (facilityHeaderResp != null) {
			// pull status from jjs header
			if (facilityHeaderResp.getFacilityStatus() != null && facilityHeaderResp.getFacilityStatus().equalsIgnoreCase("D"))
			{
			    JJSFacility facility = null;
			    //BUG 84372 HOT fix changes
			    GetJuvenileFacilityDetailsEvent facilityDetailsEvent = new GetJuvenileFacilityDetailsEvent();
			    facilityDetailsEvent.setJuvenileNum(docketRespEvt.getJuvenileNumber());
			    Iterator<JJSFacility> facilityItr = JJSFacility.findAll(facilityDetailsEvent); //get from JJSDetention
			    while(facilityItr.hasNext()){
				JJSFacility detentionRec = facilityItr.next();
				if(facilityHeaderResp!=null && detentionRec!=null){
				    if(detentionRec.getFacilitySequenceNumber().equals(facilityHeaderResp.getLastSeqNum())){
					facility = detentionRec;
					break;
				    }
				}
			    }
			    if (facility != null){
				docketRespEvt.setFacilityName(facility.getDetainedFacility());
			    }
			}
		} //changes made for performance bug 84351 Richard Y
		JuvenileDistrictCourtHelper helper = new JuvenileDistrictCourtHelper();
		docketRespEvt.setJpoOfRecord( helper.getJpoOfRecordNew( docketRespEvt.getJuvenileNumber() ));
	    }
	    //'Allegation' field on DocketEventResponseEvent is used for the Allegation for the PDF Prints. Bug# 71347

	    if (docketRespEvt.getDocketType().equalsIgnoreCase("COURT"))
	    {
		if (docketRespEvt.getOptionFlag().equalsIgnoreCase("O"))
		{
		    docketRespEvt.setAllegationDesc(docketRespEvt.getHearingTypeDesc());
		}
		else
		    if (!docketRespEvt.getOptionFlag().equalsIgnoreCase("O") && docketRespEvt.getPetitionAllegation() != null && !docketRespEvt.getPetitionAllegation().isEmpty())
		    {
			if (docketRespEvt.getAllegationDesc() != null && docketRespEvt.getAllegationDesc().isEmpty())
			{
			    docketRespEvt.setAllegationDesc(docketRespEvt.getAllegation());
			}//else Allegation Desc will normally print
		    }
		    else
			if (docketRespEvt.getHearingType().equalsIgnoreCase("DS"))
			{
			    docketRespEvt.setAllegationDesc("DISPOSITION");
			}
			else
			    if (docketRespEvt.getHearingType().equalsIgnoreCase("CR"))
			    {
				docketRespEvt.setAllegationDesc("MOTION TO CERTIFY");
			    }
			    else
			    {
				docketRespEvt.setAllegationDesc(docketRespEvt.getHearingType());
			    }
	    }
	    //Changes for 71008
	    if (docketRespEvt.getDocketType().equalsIgnoreCase("DETENTION"))
	    {
		if (docketRespEvt.getHearingType().equalsIgnoreCase("DT"))
		{
		    docketRespEvt.setAllegationDesc("DETENTION HEARING (DT)");
		}
		else
		    if (docketRespEvt.getHearingType().equalsIgnoreCase("PC"))
		    {
			docketRespEvt.setAllegationDesc("DETENTION HEARING (PC)");
		    }
	    }

	    //code below commented for Bug# 71210. See attachment with the bug
	    /*	if (docketRespEvt.getDocketType().equalsIgnoreCase("ANCILLARY") || docketRespEvt.getDocketType().equalsIgnoreCase("COURT")) {
	    		if (docketRespEvt.getHearingType() != null && !docketRespEvt.getHearingType().isEmpty()) {
	    			issueFlag = JuvenileHearingTypeCode.find((docketRespEvt.getHearingType())).getIssueInd();
	    			if (issueFlag.equalsIgnoreCase("J")) {
	    				docketRespEvt.setJuryFlag("(JURY)");
	    			} else if (issueFlag.equalsIgnoreCase("I")) {
	    				docketRespEvt.setJuryFlag("(TRIAL)");
	    			}
	    		}
	    	}*/
	}
	String display = courtHearingForm.getDisplaying();
	if (display.equalsIgnoreCase("b"))
	{
	    reportBean.setDisplay("");
	    courtHearingForm.setUpdateFlag("listReady");
	}
	else
	    if (display.equalsIgnoreCase("a"))
	    {
		reportBean.setDisplay("ANCILLARY");
	    }
	    else
		if (display.equalsIgnoreCase("d"))
		{
		    reportBean.setDisplay("DELIQUENCY");
		}
	//�The time value only needs to appear for the first record listed in that time set�s grouping as a type of sub-header.�
	Iterator<DocketEventResponseEvent> itratr = docketsList.iterator();
	ArrayList<DocketEventResponseEvent> docketsListForPDF = new ArrayList<DocketEventResponseEvent>();
	DocketEventResponseEvent resp;
	String currDocketTime = "";
	String prevDocketTime = "";
	while (itratr.hasNext())
	{
	    DocketEventResponseEvent docket = (DocketEventResponseEvent) itratr.next();
	    resp = new DocketEventResponseEvent();
	    currDocketTime = docket.getCourtTime();
	    if (currDocketTime != null && !currDocketTime.isEmpty())
	    {
		if (currDocketTime.equals(prevDocketTime))
		{
		    JuvenileDistrictCourtHelper.populateDocketInfo(docket, resp);
		    resp.setCourtTime("");
		}
		else
		{
		    JuvenileDistrictCourtHelper.populateDocketInfo(docket, resp);
		}
	    }
	    else
	    {
		JuvenileDistrictCourtHelper.populateDocketInfo(docket, resp);
	    }
	    prevDocketTime = currDocketTime;
	    docketsListForPDF.add(resp);
	}
	reportBean.setDockets(docketsListForPDF);
	reportBean.setCourtIdWithSuffix(courtHearingForm.getCourtIdWithSuffix());
	reportBean.setCourtDate(courtHearingForm.getCourtDate());
	aRequest.getSession().setAttribute("reportInfo", reportBean);
	BFOPdfManager pdfManager = BFOPdfManager.getInstance();
	//let the pdfManager know that the report should be saved in the request during report creation
	aRequest.setAttribute("isPdfSaveNeeded", "false");
	pdfManager.createPDFReport(aRequest, aResponse, PDFReport.STANDARD_COURT_DOCKET_REPORT);
	return null;
    }
	
	
	/** alphabetizedCourtDocket
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
    public ActionForward alphabetizedCourtDocket(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	Collection<DocketEventResponseEvent> docketsList = courtHearingForm.getDktSearchResults();
	StandardDocketReportBean reportBean = new StandardDocketReportBean();

	if (courtHearingForm.getUpdateFlag().equalsIgnoreCase("listReady")) { // check if the PDF info is already available, this reduces a lot of redundant processing
		/*Collections.sort((List<DocketEventResponseEvent>) docketsList, new Comparator<DocketEventResponseEvent>() {
			@Override
			public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2) {
				return new CompareToBuilder().append(evt1.getRecType(), evt2.getRecType())
						.append(evt1.getJuvenileName(), evt2.getJuvenileName()).toComparison();
			}
		});*/
		//The time value only needs to appear for the first record listed in that time set�s grouping as a type of sub-header.
		Iterator<DocketEventResponseEvent> itratr = docketsList.iterator();
		ArrayList<DocketEventResponseEvent> docketsListForPDF = new ArrayList<DocketEventResponseEvent>();
		DocketEventResponseEvent resp; 
		String currDocketTime="";
		String prevDocketTime = "";
		while (itratr.hasNext()) {
			DocketEventResponseEvent docket = (DocketEventResponseEvent) itratr.next();
			resp =  new DocketEventResponseEvent();
			currDocketTime = docket.getCourtTime();
			if (currDocketTime != null && !currDocketTime.isEmpty()) {
				if (currDocketTime.equals(prevDocketTime)) {
					JuvenileDistrictCourtHelper.populateDocketInfo(docket, resp);
					resp.setCourtTime("");
				}
				else{
					JuvenileDistrictCourtHelper.populateDocketInfo(docket, resp);
				}
			}else 
			{
				JuvenileDistrictCourtHelper.populateDocketInfo(docket, resp);
			}
			prevDocketTime = currDocketTime;
			docketsListForPDF.add(resp);
		}
		reportBean.setDockets(sortedDocketResponse(docketsListForPDF));
		reportBean.setDisplay("ALPHABETIZED");
		reportBean.setCourtIdWithSuffix(courtHearingForm.getCourtIdWithSuffix());
		reportBean.setCourtDate(courtHearingForm.getCourtDate());
		aRequest.getSession().setAttribute("reportInfo", reportBean);
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		// let the pdfManager know that the report should be saved in the request during report creation
		aRequest.setAttribute("isPdfSaveNeeded", "false");
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.ALPHABETIZED_COURT_DOCKET_REPORT);
		return null;
	}
	Iterator<DocketEventResponseEvent> iterator = docketsList.iterator();
	while (iterator.hasNext()) {
		DocketEventResponseEvent docketRespEvt = (DocketEventResponseEvent) iterator.next();
		String issueFlag = "";
		if (docketRespEvt.getCourtTime() != null && !docketRespEvt.getCourtTime().isEmpty()) {
			String courtTime = docketRespEvt.getCourtTime().substring(3, 5);
			if (Integer.valueOf(courtTime) < 5 && Integer.valueOf(courtTime) > 0) {
				docketRespEvt.setCourtTime(docketRespEvt.getCourtTime().substring(0, 3) + "00");
			}
		}
		if (docketRespEvt.getAttorneyConnection() != null && !docketRespEvt.getAttorneyConnection().isEmpty()) {
			if (docketRespEvt.getAttorneyConnection().equalsIgnoreCase("AAT")) {
				docketRespEvt.setAttorneyConnection("APPOINTED");
			} 
			else
			{
			    if (docketRespEvt.getAttorneyConnection().equalsIgnoreCase("HAT")) 
				docketRespEvt.setAttorneyConnection("HIRED");
			    if (docketRespEvt.getAttorneyConnection().equalsIgnoreCase("PDO")) 
				docketRespEvt.setAttorneyConnection("PUBLIC DEFENDER");
			    
			}
		}
		/*if (docketRespEvt.getSettingReason() != null && !docketRespEvt.getSettingReason().isEmpty())  {
		    issueFlag = JuvenileHearingTypeCode.find((docketRespEvt.getSettingReason())).getIssueInd();
    			if (issueFlag.equalsIgnoreCase("J")){
    			    docketRespEvt.setJuryFlag("(JURY)");
    			} else if (issueFlag.equalsIgnoreCase("I")){
    				docketRespEvt.setJuryFlag("(TRIAL)");
    			}
		}*///BUG# 71210 //code commented and added the same logic in GetJJSCLAncillaryCourtByReferreeCourtCommand BUG: 84372
		
		/*if (docketRespEvt.getDocketType().equalsIgnoreCase("DETENTION") || docketRespEvt.getDocketType().equalsIgnoreCase("COURT")) {
		    JuvenileCore juvCore = JuvenileCore.findCore(docketRespEvt.getJuvenileNumber());
		    if(juvCore != null){
			docketRespEvt.setDob(DateUtil.dateToString(juvCore.getDateOfBirth(), DateUtil.DATE_FMT_1));
		    }*/ //commented for performance bug 84351
	
			JuvenileDistrictCourtHelper.setJuvenileFamilyDetailsForDockets(docketRespEvt.getJuvenileNumber(), docketRespEvt);
			// Get Juvenile Facility/Detention Information
			JuvenileFacilityHeaderResponseEvent facilityHeaderResp = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(docketRespEvt.getJuvenileNumber());
			if (facilityHeaderResp != null) {
				// pull status from jjs header
				if (facilityHeaderResp.getFacilityStatus() != null && facilityHeaderResp.getFacilityStatus().equalsIgnoreCase("D"))
				{
				    JJSFacility facility = null;
				    //BUG 84372 HOT fix changes
				    GetJuvenileFacilityDetailsEvent facilityDetailsEvent = new GetJuvenileFacilityDetailsEvent();
				    facilityDetailsEvent.setJuvenileNum(docketRespEvt.getJuvenileNumber());
				    Iterator<JJSFacility> facilityItr = JJSFacility.findAll(facilityDetailsEvent); //get from JJSDetention
				    while(facilityItr.hasNext()){
					JJSFacility detentionRec = facilityItr.next();
					if(facilityHeaderResp!=null && detentionRec!=null){
					    if(detentionRec.getFacilitySequenceNumber().equals(facilityHeaderResp.getLastSeqNum())){
						facility = detentionRec;
						break;
					    }
					}
				    }
				    if (facility != null){
				    docketRespEvt.setFacilityName(facility.getDetainedFacility());
				    }
				    /*JJSFacility facility = JuvenileFacilityHelper.getCurrentFacilityRecord(docketRespEvt.getJuvenileNumber());
				    docketRespEvt.setFacilityName(facility.getDetainedFacility());*/ //commented for 84372
				}
			}
			JuvenileDistrictCourtHelper helper = new JuvenileDistrictCourtHelper();
			if( docketRespEvt.getJuvenileNumber()!= null || StringUtils.isNotEmpty( docketRespEvt.getJuvenileNumber() ) ){
			    
			    docketRespEvt.setJpoOfRecord( helper.getJpoOfRecordNew( docketRespEvt.getJuvenileNumber() ));
			}				
		
		//'Allegation' field on DocketEventResponseEvent is used for the Allegation for the PDF Prints. Bug# 71271
		
		if(docketRespEvt.getDocketType().equalsIgnoreCase("COURT")){
		    if(docketRespEvt.getOptionFlag().equalsIgnoreCase("O")){
			docketRespEvt.setAllegationDesc(docketRespEvt.getHearingTypeDesc());
		    } else if(!docketRespEvt.getOptionFlag().equalsIgnoreCase("O") && docketRespEvt.getPetitionAllegation()!= null && !docketRespEvt.getPetitionAllegation().isEmpty()){
			if(docketRespEvt.getAllegationDesc()!= null && docketRespEvt.getAllegationDesc().isEmpty()){
			    docketRespEvt.setAllegationDesc(docketRespEvt.getAllegation());
			}//else Allegation Desc will normally print
		    } else if (docketRespEvt.getHearingType().equalsIgnoreCase("DS")){
			docketRespEvt.setAllegationDesc("DISPOSITION");
		    } else if (docketRespEvt.getHearingType().equalsIgnoreCase("CR")){
			docketRespEvt.setAllegationDesc("MOTION TO CERTIFY");
		    } else {
			docketRespEvt.setAllegationDesc(docketRespEvt.getHearingType());
		    }
		}
		//Changes for 71008
		if(docketRespEvt.getDocketType().equalsIgnoreCase("DETENTION")){
		    if(docketRespEvt.getHearingType().equalsIgnoreCase("DT")){
			docketRespEvt.setAllegationDesc("DETENTION HEARING (DT)");
		    }else  if(docketRespEvt.getHearingType().equalsIgnoreCase("PC")){
			docketRespEvt.setAllegationDesc("DETENTION HEARING (PC)");
		    }
		}

	/*	if (docketRespEvt.getDocketType().equalsIgnoreCase("ANCILLARY") || docketRespEvt.getDocketType().equalsIgnoreCase("COURT")) {
			if (docketRespEvt.getSettingReason() != null && !docketRespEvt.getSettingReason().isEmpty()) {
				issueFlag = JuvenileHearingTypeCode.find((docketRespEvt.getSettingReason())).getIssueInd();
				if (issueFlag.equalsIgnoreCase("J")) {
					docketRespEvt.setJuryFlag("(JURY)");
				} else if (issueFlag.equalsIgnoreCase("I")) {
					docketRespEvt.setJuryFlag("(TRIAL)");
				}
			}
		}*/
	}

	if(courtHearingForm.getDisplaying().equalsIgnoreCase("b")){
		courtHearingForm.setUpdateFlag("listReady");
	}
	courtHearingForm.setDktSearchResults(docketsList);
	/*Collections.sort((List<DocketEventResponseEvent>) docketsList, new Comparator<DocketEventResponseEvent>() {
		@Override
		public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2) {
			return new CompareToBuilder().append(evt1.getRecType(), evt2.getRecType()).append(evt1.getJuvenileName(), evt2.getJuvenileName()).toComparison();
		}
	});*/
	
	//The time value only needs to appear for the first record listed in that time set�s grouping as a type of sub-header.
	Iterator<DocketEventResponseEvent> itratr = docketsList.iterator();
	ArrayList<DocketEventResponseEvent> docketsListForPDF = new ArrayList<DocketEventResponseEvent>();
	DocketEventResponseEvent resp; 
	String currDocketTime="";
	String prevDocketTime = "";
	while (itratr.hasNext()) {
		DocketEventResponseEvent docket = (DocketEventResponseEvent) itratr.next();
		resp =  new DocketEventResponseEvent();
		currDocketTime = docket.getCourtTime();
		if (currDocketTime != null && !currDocketTime.isEmpty()) {
			if (currDocketTime.equals(prevDocketTime)) {
				JuvenileDistrictCourtHelper.populateDocketInfo(docket, resp);
				resp.setCourtTime("");
			}
			else{
			    JuvenileDistrictCourtHelper.populateDocketInfo(docket, resp);
			}
		}else 
		{
			JuvenileDistrictCourtHelper.populateDocketInfo(docket, resp);
		}
		prevDocketTime = currDocketTime;
		docketsListForPDF.add(resp);
	}
	
	reportBean.setDockets(sortedDocketResponse(docketsListForPDF));	
	reportBean.setDisplay("ALPHABETIZED");
	//reportBean.setDockets(docketsList);
	reportBean.setCourtIdWithSuffix(courtHearingForm.getCourtIdWithSuffix());
	reportBean.setCourtDate(courtHearingForm.getCourtDate());
	aRequest.getSession().setAttribute("reportInfo", reportBean);
	BFOPdfManager pdfManager = BFOPdfManager.getInstance();
	// let the pdfManager know that the report should be saved in the
	// request during report creation
	aRequest.setAttribute("isPdfSaveNeeded", "false");
	pdfManager.createPDFReport(aRequest, aResponse, PDFReport.ALPHABETIZED_COURT_DOCKET_REPORT);
	return null;
}

	/**
	 * cancel
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CourtHearingForm form = (CourtHearingForm) aForm;
		form.reset();
		return aMapping.findForward(UIConstants.COURT_MAIN_MENU);
	}

	/**
	 * back
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.BACK);
	}

	@Override
	protected Map getKeyMethodMap() {
		// TODO Auto-generated method stub
		Map<String, String> keyMap = new HashMap<String, String>();
		keyMap.put("button.standardCourtDocket", "standardCourtDocket");
		keyMap.put("button.alphabetizedCourtDocket", "alphabetizedCourtDocket");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.go", "submit");//handles iterativeSearch
		return keyMap;
	}
	
	/**
	 * 
	 * @param docketsList
	 * @return
	 */
	private List<DocketEventResponseEvent> sortedDocketResponse( List<DocketEventResponseEvent> docketsList){
	    
	    Collections.sort((List<DocketEventResponseEvent>) docketsList, new Comparator<DocketEventResponseEvent>() {
		@Override
		public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2) {
			return new CompareToBuilder().append(evt1.getRecType(), evt2.getRecType())
					.append(evt1.getJuvenileName(), evt2.getJuvenileName()).toComparison();
		}
	});
	    
	    return docketsList;
	}

}
