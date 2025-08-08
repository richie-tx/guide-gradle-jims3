package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.JuvenileAdmitReasonsResponseEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.juvenilecase.GetRiskAnalysisByJuvenileIdEvent;
import messaging.juvenilecase.reply.RiskAssessmentListResponseEvent;
import messaging.productionsupport.GetProductionSupportFacilityDetentionEvent;
import messaging.productionsupport.reply.ProductionSupportFacilityDetentionResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.ProductionSupportControllerServiceNames;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 * 
 *         Query to Update Facility Detention Fields 
 */

public class UpdateFacilityDetentionQueryAction extends Action {

	private Logger log = Logger.getLogger("UpdateFacilityDetentionQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		
		regform.clear();
		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
			regform.clearAllResults();
			regform.setMsg("");
			return mapping.findForward("error");
		}

		String detentionId = regform.getDetentionId();
		String juvenileId = regform.getJuvenileId();
		String referralNum = regform.getReferralNum();
		String sequenceNum = regform.getSequenceNum();

		if ( (juvenileId == null || juvenileId.equals("")) && (detentionId == null || detentionId.equals("") ) ) {
			regform.setMsg("You must enter a valid Juvenile ID or a valid Detention ID");
			return mapping.findForward("error");
		}
	
		
		// load the static drop down lists
		loadDropDownTables(regform);
		loadAvailableRiskIds(juvenileId,detentionId, regform);
		
		// Redirect if this comes from updateFacilityDetentionQuery2.jsp
		String editFlag = request.getParameter("edit");
		if (editFlag != null && editFlag.equalsIgnoreCase("Y")) {
			if (detentionId != null && detentionId.equals("") == false){
				regform.clearAllResults();
				// retrieve unique detail record
				ArrayList singleDetentionRecordList = retrieveSingleDetentionRecord(detentionId, SecurityUIHelper.getLogonId());
				loadCodeDescriptions(singleDetentionRecordList);
				if(singleDetentionRecordList != null && singleDetentionRecordList.size() > 0){
					regform.setFacilityDetentionList(singleDetentionRecordList);
					ProductionSupportFacilityDetentionResponseEvent resp = (ProductionSupportFacilityDetentionResponseEvent)singleDetentionRecordList.get(0);
					regform.setJuvenileId(resp.getJuvenileId());
					regform.setFacilityStatus(resp.getFacilityStatusCode());					
					regform.setReturnDate(DateUtil.dateToString(resp.getReturnDate(), DateUtil.DATE_FMT_1));					
					regform.setDetainedDate(DateUtil.dateToString(resp.getDetainedDate(), DateUtil.DATE_FMT_1));
					regform.setFloorLocation(resp.getLocationFloorNumber());
					regform.setUnitLocation(resp.getLocationUnit());
					regform.setRoomLocation(resp.getRoomNumber());
					regform.setMultipleOccupancyUnit(resp.getMultipleOccupanyUnit());
					regform.setLocker(resp.getLockerNumber());
					regform.setAdmitAuthority(resp.getAdmittedAuthorizeJPO());
					regform.setDetentionId(resp.getDetentionId());
					resp.setAdmittedTime(JuvenileFacilityHelper.getHoursMinsFromTime(resp.getAdmittedTime()));
					resp.setReleaseTime(JuvenileFacilityHelper.getHoursMinsFromTime(resp.getReleaseTime()));
					resp.setReturnTime(JuvenileFacilityHelper.getHoursMinsFromTime(resp.getReturnTime()));
					regform.setReturnTime(resp.getReturnTime());
					resp.setOriginalAdmittedTime(JuvenileFacilityHelper.getHoursMinsFromTime(resp.getOriginalAdmittedTime()));
					//regform.setReleaseReason(resp.getReleaseReason());
					regform.setMsg("");
					//load the header 
					regform.setHeaderInfo(JuvenileFacilityHelper.getJuvFacilityHeaderDetails(resp.getJuvenileId()));
					//98686
					regform.setBookingReferral(resp.getBookingReferralNum());
					regform.setCurrentReferral(resp.getCurrentReferralNum());
					regform.setBookingSupervisionNum(resp.getBookingSupervisionNum());
					regform.setCurrentSupervisionNum(resp.getCurrentSupervisionNum());
					regform.setFacilitySeqNum(resp.getSequenceNum());
					regform.setCurrentOffense(resp.getCurrentOffense());
					regform.setActiveFacilityCd(resp.getFacilityCode());//  -  "+resp.getFacilityName());
					regform.setNewadmitReasonCd(resp.getAdmittanceReasonCode());//"  -  "+resp.getAdmittanceReasonCodeDesc());
					regform.setNewTransferToFacility(resp.getTransferFacilityCode());
					regform.setUnitLocation(resp.getLocationUnit());
					regform.setFloorLocation(resp.getLocationFloorNumber());
					regform.setRoomLocation(resp.getRoomNumber());
					regform.setMultipleOccupancyUnit(resp.getMultipleOccupanyUnit());
					regform.setLocker(resp.getLockerNumber());
					regform.setValuablesReceipt(resp.getValuablesReceiptNumber());
					if(resp.getDetainedDate()!=null)
					    regform.setDetainedDate(DateUtil.dateToString(resp.getDetainedDate(), DateUtil.DATE_FMT_1));
					regform.setAdmitBy(resp.getAdmittedByAuthority());
					if(resp.getAdmittedDate()!=null)
					    regform.setAdmitDate(DateUtil.dateToString(resp.getAdmittedDate(), DateUtil.DATE_FMT_1));
					regform.setAdmitTime(resp.getAdmittedTime());
					regform.setAdmitAuthority(resp.getAdmittedAuthorizeJPO());
					if(resp.getOriginalAdmittedDate()!=null)
					    regform.setOriginalAdmitDate(DateUtil.dateToString(resp.getOriginalAdmittedDate(), DateUtil.DATE_FMT_1));
					regform.setOriginalAdmitTime(resp.getOriginalAdmittedTime());
					regform.setOutcome(resp.getOutcome());
					if(resp.getReleaseDate()!=null)
					    regform.setReleaseDate(DateUtil.dateToString(resp.getReleaseDate(), DateUtil.DATE_FMT_1));
					regform.setReleaseTime(resp.getReleaseTime());
					regform.setReleasedBy(resp.getReleaseBy());
					regform.setReleasedTo(resp.getReleaseTo());
					regform.setReleaseAuthority(resp.getReleaseAuthorizedBy());
					regform.setReleaseReason(resp.getReleaseReason());
					if(resp.getReturnDate()!=null)
					    regform.setReturnDate(DateUtil.dateToString(resp.getReturnDate(), DateUtil.DATE_FMT_1));
					regform.setReturnTime(resp.getReturnTime());
					regform.setReturnReason(resp.getReturnReason());
					regform.setTempReleaseReason(resp.getTempReleaseReasonCode());
					regform.setFacilityStatus(resp.getFacilityStatusCode());
					regform.setReturnStatus(resp.getReturnStatus());
					regform.setOriginalAdmitOID(resp.getOriginalAdmitOID());
					regform.setAvgCostPerDay(resp.getAvgCostPerDay());
					regform.setDetainedByInd(resp.getDetainedByInd());
					regform.setTjjdFundingSrc(resp.getTjjdFundingSrc());
					regform.setOriginaladmitSEQNUM(resp.getOriginaladmitSEQNUM());
					regform.setPostAdmitOID(resp.getPostAdmitOID());
					regform.setTjjdFacilityId(resp.getTjjdFacilityId());
					regform.setCustodyfirstName(resp.getCustodyfirstName());
					regform.setCustodylastName(resp.getCustodylastName());

					regform.setRiskAnalysisId(resp.getRiskAnalysisId());
					//
					return mapping.findForward("continue");
				}else{
					regform.setMsg("There were no records found for the following: Detention Id:" + detentionId + " JuvenileId: " + 
							juvenileId + " ReferralNum: " + referralNum + " SequenceNum: " + sequenceNum);
					return mapping.findForward("error");
				}
			}
		}

		/** Normal query logic continues here **/
		regform.clearAllResults();
		
		// if this is a search on detention id then retrieve record and forward directly to the single record details page
		if(detentionId != null && detentionId.length() > 0){
			ArrayList<ProductionSupportFacilityDetentionResponseEvent> singleDetentionRecordList = retrieveSingleDetentionRecord(detentionId,SecurityUIHelper.getLogonId());
			loadCodeDescriptions(singleDetentionRecordList);
			if(singleDetentionRecordList != null && singleDetentionRecordList.size() > 0){
			    	regform.setJuvenileId(singleDetentionRecordList.get(0).getJuvenileId());
			    	//loadAvailableRiskIds( singleDetentionRecordList.get(0).getJuvenileId() ,regform);
				regform.setFacilityDetentionList(singleDetentionRecordList);
				regform.setFacilityStatus(singleDetentionRecordList.get(0).getFacilityStatusCode());				
				regform.setHeaderInfo(JuvenileFacilityHelper.getJuvFacilityHeaderDetails(singleDetentionRecordList.get(0).getJuvenileId()));
				regform.setReturnDate(DateUtil.dateToString(singleDetentionRecordList.get(0).getReturnDate(), DateUtil.DATE_FMT_1));				
				regform.setDetainedDate(DateUtil.dateToString(singleDetentionRecordList.get(0).getDetainedDate(), DateUtil.DATE_FMT_1));
				regform.setFloorLocation(singleDetentionRecordList.get(0).getLocationFloorNumber());
				regform.setUnitLocation(singleDetentionRecordList.get(0).getLocationUnit());
				regform.setRoomLocation(singleDetentionRecordList.get(0).getRoomNumber());
				regform.setMultipleOccupancyUnit(singleDetentionRecordList.get(0).getMultipleOccupanyUnit());
				regform.setLocker(singleDetentionRecordList.get(0).getLockerNumber());
				regform.setAdmitAuthority(singleDetentionRecordList.get(0).getAdmittedAuthorizeJPO());
				regform.setDetentionId(singleDetentionRecordList.get(0).getDetentionId());
				singleDetentionRecordList.get(0).setAdmittedTime(JuvenileFacilityHelper.getHoursMinsFromTime(singleDetentionRecordList.get(0).getAdmittedTime()));
				singleDetentionRecordList.get(0).setReleaseTime(JuvenileFacilityHelper.getHoursMinsFromTime(singleDetentionRecordList.get(0).getReleaseTime()));
				singleDetentionRecordList.get(0).setReturnTime(JuvenileFacilityHelper.getHoursMinsFromTime(singleDetentionRecordList.get(0).getReturnTime()));
				regform.setReturnTime(singleDetentionRecordList.get(0).getReturnTime());
				singleDetentionRecordList.get(0).setOriginalAdmittedTime(JuvenileFacilityHelper.getHoursMinsFromTime(singleDetentionRecordList.get(0).getOriginalAdmittedTime()));
				//regform.setReleaseReason(singleDetentionRecordList.get(0).getReleaseReason());
				regform.setMsg("");
				//98686
				regform.setBookingReferral(singleDetentionRecordList.get(0).getBookingReferralNum());
				regform.setCurrentReferral(singleDetentionRecordList.get(0).getCurrentReferralNum());
				regform.setBookingSupervisionNum(singleDetentionRecordList.get(0).getBookingSupervisionNum());
				regform.setCurrentSupervisionNum(singleDetentionRecordList.get(0).getCurrentSupervisionNum());
				regform.setFacilitySeqNum(singleDetentionRecordList.get(0).getSequenceNum());
				regform.setCurrentOffense(singleDetentionRecordList.get(0).getCurrentOffense());
				regform.setActiveFacilityCd(singleDetentionRecordList.get(0).getFacilityCode());//  -  "+resp.getFacilityName());
				regform.setNewadmitReasonCd(singleDetentionRecordList.get(0).getAdmittanceReasonCode());//"  -  "+resp.getAdmittanceReasonCodeDesc());
				regform.setNewTransferToFacility(singleDetentionRecordList.get(0).getTransferFacilityCode());
				regform.setUnitLocation(singleDetentionRecordList.get(0).getLocationUnit());
				regform.setFloorLocation(singleDetentionRecordList.get(0).getLocationFloorNumber());
				regform.setRoomLocation(singleDetentionRecordList.get(0).getRoomNumber());
				regform.setMultipleOccupancyUnit(singleDetentionRecordList.get(0).getMultipleOccupanyUnit());
				regform.setLocker(singleDetentionRecordList.get(0).getLockerNumber());
				regform.setValuablesReceipt(singleDetentionRecordList.get(0).getValuablesReceiptNumber());
				if(singleDetentionRecordList.get(0).getDetainedDate()!=null)
				    regform.setDetainedDate(DateUtil.dateToString(singleDetentionRecordList.get(0).getDetainedDate(), DateUtil.DATE_FMT_1));
				regform.setAdmitBy(singleDetentionRecordList.get(0).getAdmittedByAuthority());
				if(singleDetentionRecordList.get(0).getAdmittedDate()!=null)
				    regform.setAdmitDate(DateUtil.dateToString(singleDetentionRecordList.get(0).getAdmittedDate(), DateUtil.DATE_FMT_1));
				regform.setAdmitTime(singleDetentionRecordList.get(0).getAdmittedTime());
				regform.setAdmitAuthority(singleDetentionRecordList.get(0).getAdmittedAuthorizeJPO());
				if(singleDetentionRecordList.get(0).getOriginalAdmittedDate()!=null)
				    regform.setOriginalAdmitDate(DateUtil.dateToString(singleDetentionRecordList.get(0).getOriginalAdmittedDate(), DateUtil.DATE_FMT_1));
				regform.setOriginalAdmitTime(singleDetentionRecordList.get(0).getOriginalAdmittedTime());
				regform.setOutcome(singleDetentionRecordList.get(0).getOutcome());
				if(singleDetentionRecordList.get(0).getReleaseDate()!=null)
				    regform.setReleaseDate(DateUtil.dateToString(singleDetentionRecordList.get(0).getReleaseDate(), DateUtil.DATE_FMT_1));
				regform.setReleaseTime(singleDetentionRecordList.get(0).getReleaseTime());
				regform.setReleasedBy(singleDetentionRecordList.get(0).getReleaseBy());
				regform.setReleasedTo(singleDetentionRecordList.get(0).getReleaseTo());
				regform.setReleaseAuthority(singleDetentionRecordList.get(0).getReleaseAuthorizedBy());
				regform.setReleaseReason(singleDetentionRecordList.get(0).getReleaseReason());
				if(singleDetentionRecordList.get(0).getReturnDate()!=null)
				    regform.setReturnDate(DateUtil.dateToString(singleDetentionRecordList.get(0).getReturnDate(), DateUtil.DATE_FMT_1));
				regform.setReturnTime(singleDetentionRecordList.get(0).getReturnTime());
				regform.setReturnReason(singleDetentionRecordList.get(0).getReturnReason());
				regform.setTempReleaseReason(singleDetentionRecordList.get(0).getTempReleaseReasonCode());
				regform.setFacilityStatus(singleDetentionRecordList.get(0).getFacilityStatusCode());
				regform.setReturnStatus(singleDetentionRecordList.get(0).getReturnStatus());
				
				regform.setOriginalAdmitOID(singleDetentionRecordList.get(0).getOriginalAdmitOID());
				regform.setAvgCostPerDay(singleDetentionRecordList.get(0).getAvgCostPerDay());
				regform.setDetainedByInd(singleDetentionRecordList.get(0).getDetainedByInd());
				regform.setTjjdFundingSrc(singleDetentionRecordList.get(0).getTjjdFundingSrc());
				regform.setOriginaladmitSEQNUM(singleDetentionRecordList.get(0).getOriginaladmitSEQNUM());
				regform.setPostAdmitOID(singleDetentionRecordList.get(0).getPostAdmitOID());
				regform.setTjjdFacilityId(singleDetentionRecordList.get(0).getTjjdFacilityId());
				regform.setCustodyfirstName(singleDetentionRecordList.get(0).getCustodyfirstName());
				regform.setCustodylastName(singleDetentionRecordList.get(0).getCustodylastName());

				
				regform.setRiskAnalysisId(singleDetentionRecordList.get(0).getRiskAnalysisId());
				//
				regform.setHeaderInfo(JuvenileFacilityHelper.getJuvFacilityHeaderDetails(singleDetentionRecordList.get(0).getJuvenileId()));
				return mapping.findForward("continue");
			}else{
				regform.setMsg("There were no records found for the following: Detention Id:" + detentionId + " JuvenileId: " + 
						juvenileId + " ReferralNum: " + referralNum + " SequenceNum: " + sequenceNum);
				return mapping.findForward("error");
			}
			// if this is a search on juvenile id, seqNum, referralNum then retrieve records and forward either directly to the single record details page (if one record) or the details page if > 1 record
		}else if(juvenileId != null && juvenileId .length() > 0){
			ArrayList<ProductionSupportFacilityDetentionResponseEvent> detentionRecordsList = retrieveDetentionRecords(juvenileId, referralNum, sequenceNum, SecurityUIHelper.getLogonId());
			loadCodeDescriptions(detentionRecordsList);
			regform.setHeaderInfo(JuvenileFacilityHelper.getJuvFacilityHeaderDetails(juvenileId));
			if(detentionRecordsList != null && detentionRecordsList.size() > 1){
				regform.setFacilityDetentionList(detentionRecordsList);
				if(detentionRecordsList.size()==1)
				{
				    regform.setJuvenileId(detentionRecordsList.get(0).getJuvenileId());
				    regform.setDetentionId(detentionRecordsList.get(0).getDetentionId());
				    regform.setReturnDate(DateUtil.dateToString(detentionRecordsList.get(0).getReturnDate(), DateUtil.DATE_FMT_1));					
					regform.setDetainedDate(DateUtil.dateToString(detentionRecordsList.get(0).getDetainedDate(), DateUtil.DATE_FMT_1));
					regform.setFloorLocation(detentionRecordsList.get(0).getLocationFloorNumber());
					regform.setUnitLocation(detentionRecordsList.get(0).getLocationUnit());
					regform.setRoomLocation(detentionRecordsList.get(0).getRoomNumber());
					regform.setMultipleOccupancyUnit(detentionRecordsList.get(0).getMultipleOccupanyUnit());
					regform.setLocker(detentionRecordsList.get(0).getLockerNumber());
					regform.setAdmitAuthority(detentionRecordsList.get(0).getAdmittedAuthorizeJPO());
					//regform.setDetentionId(resp.getDetentionId());
					//98686
					regform.setBookingReferral(detentionRecordsList.get(0).getBookingReferralNum());
					regform.setCurrentReferral(detentionRecordsList.get(0).getCurrentReferralNum());
					regform.setBookingSupervisionNum(detentionRecordsList.get(0).getBookingSupervisionNum());
					regform.setCurrentSupervisionNum(detentionRecordsList.get(0).getCurrentSupervisionNum());
					regform.setFacilitySeqNum(detentionRecordsList.get(0).getSequenceNum());
					regform.setCurrentOffense(detentionRecordsList.get(0).getCurrentOffense());
					regform.setActiveFacilityCd(detentionRecordsList.get(0).getFacilityCode());//  -  "+resp.getFacilityName());
					regform.setNewadmitReasonCd(detentionRecordsList.get(0).getAdmittanceReasonCode());//"  -  "+resp.getAdmittanceReasonCodeDesc());
					regform.setNewTransferToFacility(detentionRecordsList.get(0).getTransferFacilityCode());
					regform.setUnitLocation(detentionRecordsList.get(0).getLocationUnit());
					regform.setFloorLocation(detentionRecordsList.get(0).getLocationFloorNumber());
					regform.setRoomLocation(detentionRecordsList.get(0).getRoomNumber());
					regform.setMultipleOccupancyUnit(detentionRecordsList.get(0).getMultipleOccupanyUnit());
					regform.setLocker(detentionRecordsList.get(0).getLockerNumber());
					regform.setValuablesReceipt(detentionRecordsList.get(0).getValuablesReceiptNumber());
					if(detentionRecordsList.get(0).getDetainedDate()!=null)
					    regform.setDetainedDate(DateUtil.dateToString(detentionRecordsList.get(0).getDetainedDate(), DateUtil.DATE_FMT_1));
					regform.setAdmitBy(detentionRecordsList.get(0).getAdmittedByAuthority());
					if(detentionRecordsList.get(0).getAdmittedDate()!=null)
					    regform.setAdmitDate(DateUtil.dateToString(detentionRecordsList.get(0).getAdmittedDate(), DateUtil.DATE_FMT_1));
					regform.setAdmitTime(detentionRecordsList.get(0).getAdmittedTime());
					regform.setAdmitAuthority(detentionRecordsList.get(0).getAdmittedAuthorizeJPO());
					if(detentionRecordsList.get(0).getOriginalAdmittedDate()!=null)
					    regform.setOriginalAdmitDate(DateUtil.dateToString(detentionRecordsList.get(0).getOriginalAdmittedDate(), DateUtil.DATE_FMT_1));
					regform.setOriginalAdmitTime(detentionRecordsList.get(0).getOriginalAdmittedTime());
					regform.setOutcome(detentionRecordsList.get(0).getOutcome());
					if(detentionRecordsList.get(0).getReleaseDate()!=null)
					    regform.setReleaseDate(DateUtil.dateToString(detentionRecordsList.get(0).getReleaseDate(), DateUtil.DATE_FMT_1));
					regform.setReleaseTime(detentionRecordsList.get(0).getReleaseTime());
					regform.setReleasedBy(detentionRecordsList.get(0).getReleaseBy());
					regform.setReleasedTo(detentionRecordsList.get(0).getReleaseTo());
					regform.setReleaseAuthority(detentionRecordsList.get(0).getReleaseAuthorizedBy());
					regform.setReleaseReason(detentionRecordsList.get(0).getReleaseReason());
					if(detentionRecordsList.get(0).getReturnDate()!=null)
					    regform.setReturnDate(DateUtil.dateToString(detentionRecordsList.get(0).getReturnDate(), DateUtil.DATE_FMT_1));
					regform.setReturnTime(detentionRecordsList.get(0).getReturnTime());
					regform.setReturnReason(detentionRecordsList.get(0).getReturnReason());
					regform.setTempReleaseReason(detentionRecordsList.get(0).getTempReleaseReasonCode());
					regform.setFacilityStatus(detentionRecordsList.get(0).getFacilityStatusCode());
					regform.setReturnStatus(detentionRecordsList.get(0).getReturnStatus());		
					
					regform.setRiskAnalysisId(detentionRecordsList.get(0).getRiskAnalysisId());
					
					regform.setOriginalAdmitOID(detentionRecordsList.get(0).getOriginalAdmitOID());
					regform.setAvgCostPerDay(detentionRecordsList.get(0).getAvgCostPerDay());
					regform.setDetainedByInd(detentionRecordsList.get(0).getDetainedByInd());
					regform.setTjjdFundingSrc(detentionRecordsList.get(0).getTjjdFundingSrc());
					regform.setOriginaladmitSEQNUM(detentionRecordsList.get(0).getOriginaladmitSEQNUM());
					regform.setPostAdmitOID(detentionRecordsList.get(0).getPostAdmitOID());
					regform.setTjjdFacilityId(detentionRecordsList.get(0).getTjjdFacilityId());
					regform.setCustodyfirstName(detentionRecordsList.get(0).getCustodyfirstName());
					regform.setCustodylastName(detentionRecordsList.get(0).getCustodylastName());
					//
					detentionRecordsList.get(0).setAdmittedTime(JuvenileFacilityHelper.getHoursMinsFromTime(detentionRecordsList.get(0).getAdmittedTime()));
					detentionRecordsList.get(0).setReleaseTime(JuvenileFacilityHelper.getHoursMinsFromTime(detentionRecordsList.get(0).getReleaseTime()));
					detentionRecordsList.get(0).setReturnTime(JuvenileFacilityHelper.getHoursMinsFromTime(detentionRecordsList.get(0).getReturnTime()));
					regform.setReturnTime(detentionRecordsList.get(0).getReturnTime());
					detentionRecordsList.get(0).setOriginalAdmittedTime(JuvenileFacilityHelper.getHoursMinsFromTime(detentionRecordsList.get(0).getOriginalAdmittedTime()));
				}
				regform.setMsg("");
				return mapping.findForward("success");
			}else if(detentionRecordsList != null && detentionRecordsList.size() > 0){
				regform.setFacilityDetentionList(detentionRecordsList);
				if(detentionRecordsList.size()==1)
				{
				    regform.setJuvenileId(detentionRecordsList.get(0).getJuvenileId());
				    regform.setDetentionId(detentionRecordsList.get(0).getDetentionId());
				    regform.setFacilityStatus(detentionRecordsList.get(0).getFacilityStatusCode());					
					regform.setReturnDate(DateUtil.dateToString(detentionRecordsList.get(0).getReturnDate(), DateUtil.DATE_FMT_1));					
					regform.setDetainedDate(DateUtil.dateToString(detentionRecordsList.get(0).getDetainedDate(), DateUtil.DATE_FMT_1));
					regform.setFloorLocation(detentionRecordsList.get(0).getLocationFloorNumber());
					regform.setUnitLocation(detentionRecordsList.get(0).getLocationUnit());
					regform.setRoomLocation(detentionRecordsList.get(0).getRoomNumber());
					regform.setMultipleOccupancyUnit(detentionRecordsList.get(0).getMultipleOccupanyUnit());
					regform.setLocker(detentionRecordsList.get(0).getLockerNumber());
					regform.setAdmitAuthority(detentionRecordsList.get(0).getAdmittedAuthorizeJPO());
					//98686
					regform.setBookingReferral(detentionRecordsList.get(0).getBookingReferralNum());
					regform.setCurrentReferral(detentionRecordsList.get(0).getCurrentReferralNum());
					regform.setBookingSupervisionNum(detentionRecordsList.get(0).getBookingSupervisionNum());
					regform.setCurrentSupervisionNum(detentionRecordsList.get(0).getCurrentSupervisionNum());
					regform.setFacilitySeqNum(detentionRecordsList.get(0).getSequenceNum());
					regform.setCurrentOffense(detentionRecordsList.get(0).getCurrentOffense());
					regform.setActiveFacilityCd(detentionRecordsList.get(0).getFacilityCode());//  -  "+resp.getFacilityName());
					regform.setNewadmitReasonCd(detentionRecordsList.get(0).getAdmittanceReasonCode());//"  -  "+resp.getAdmittanceReasonCodeDesc());
					regform.setNewTransferToFacility(detentionRecordsList.get(0).getTransferFacilityCode());
					regform.setUnitLocation(detentionRecordsList.get(0).getLocationUnit());
					regform.setFloorLocation(detentionRecordsList.get(0).getLocationFloorNumber());
					regform.setRoomLocation(detentionRecordsList.get(0).getRoomNumber());
					regform.setMultipleOccupancyUnit(detentionRecordsList.get(0).getMultipleOccupanyUnit());
					regform.setLocker(detentionRecordsList.get(0).getLockerNumber());
					regform.setValuablesReceipt(detentionRecordsList.get(0).getValuablesReceiptNumber());
					if(detentionRecordsList.get(0).getDetainedDate()!=null)
					    regform.setDetainedDate(DateUtil.dateToString(detentionRecordsList.get(0).getDetainedDate(), DateUtil.DATE_FMT_1));
					regform.setAdmitBy(detentionRecordsList.get(0).getAdmittedByAuthority());
					if(detentionRecordsList.get(0).getAdmittedDate()!=null)
					    regform.setAdmitDate(DateUtil.dateToString(detentionRecordsList.get(0).getAdmittedDate(), DateUtil.DATE_FMT_1));
					regform.setAdmitTime(detentionRecordsList.get(0).getAdmittedTime());
					regform.setAdmitAuthority(detentionRecordsList.get(0).getAdmittedAuthorizeJPO());
					if(detentionRecordsList.get(0).getOriginalAdmittedDate()!=null)
					    regform.setOriginalAdmitDate(DateUtil.dateToString(detentionRecordsList.get(0).getOriginalAdmittedDate(), DateUtil.DATE_FMT_1));
					regform.setOriginalAdmitTime(detentionRecordsList.get(0).getOriginalAdmittedTime());
					regform.setOutcome(detentionRecordsList.get(0).getOutcome());
					if(detentionRecordsList.get(0).getReleaseDate()!=null)
					    regform.setReleaseDate(DateUtil.dateToString(detentionRecordsList.get(0).getReleaseDate(), DateUtil.DATE_FMT_1));
					regform.setReleaseTime(detentionRecordsList.get(0).getReleaseTime());
					regform.setReleasedBy(detentionRecordsList.get(0).getReleaseBy());
					regform.setReleasedTo(detentionRecordsList.get(0).getReleaseTo());
					regform.setReleaseAuthority(detentionRecordsList.get(0).getReleaseAuthorizedBy());
					regform.setReleaseReason(detentionRecordsList.get(0).getReleaseReason());
					if(detentionRecordsList.get(0).getReturnDate()!=null)
					    regform.setReturnDate(DateUtil.dateToString(detentionRecordsList.get(0).getReturnDate(), DateUtil.DATE_FMT_1));
					regform.setReturnTime(detentionRecordsList.get(0).getReturnTime());
					regform.setReturnReason(detentionRecordsList.get(0).getReturnReason());
					regform.setTempReleaseReason(detentionRecordsList.get(0).getTempReleaseReasonCode());
					regform.setFacilityStatus(detentionRecordsList.get(0).getFacilityStatusCode());
					regform.setReturnStatus(detentionRecordsList.get(0).getReturnStatus());	
					
					regform.setRiskAnalysisId(detentionRecordsList.get(0).getRiskAnalysisId());
					
					regform.setOriginalAdmitOID(detentionRecordsList.get(0).getOriginalAdmitOID());
					regform.setAvgCostPerDay(detentionRecordsList.get(0).getAvgCostPerDay());
					regform.setDetainedByInd(detentionRecordsList.get(0).getDetainedByInd());
					regform.setTjjdFundingSrc(detentionRecordsList.get(0).getTjjdFundingSrc());
					regform.setOriginaladmitSEQNUM(detentionRecordsList.get(0).getOriginaladmitSEQNUM());
					regform.setPostAdmitOID(detentionRecordsList.get(0).getPostAdmitOID());
					regform.setTjjdFacilityId(detentionRecordsList.get(0).getTjjdFacilityId());
					regform.setCustodyfirstName(detentionRecordsList.get(0).getCustodyfirstName());
					regform.setCustodylastName(detentionRecordsList.get(0).getCustodylastName());
					
					//
					//regform.setDetentionId(resp.getDetentionId());
					detentionRecordsList.get(0).setAdmittedTime(JuvenileFacilityHelper.getHoursMinsFromTime(detentionRecordsList.get(0).getAdmittedTime()));
					detentionRecordsList.get(0).setReleaseTime(JuvenileFacilityHelper.getHoursMinsFromTime(detentionRecordsList.get(0).getReleaseTime()));
					detentionRecordsList.get(0).setReturnTime(JuvenileFacilityHelper.getHoursMinsFromTime(detentionRecordsList.get(0).getReturnTime()));
					regform.setReturnTime(detentionRecordsList.get(0).getReturnTime());
					detentionRecordsList.get(0).setOriginalAdmittedTime(JuvenileFacilityHelper.getHoursMinsFromTime(detentionRecordsList.get(0).getOriginalAdmittedTime()));
				}
				regform.setMsg("");
				return mapping.findForward("continue");
			}else{
				regform.setMsg("There were no records found for the following: Detention Id:" + detentionId + " JuvenileId: " + 
						juvenileId + " ReferralNum: " + referralNum + " SequenceNum: " + sequenceNum);
				return mapping.findForward("error");
			}
		}else{
				regform.setMsg("There were no records found for the following: Detention Id:" + detentionId + " JuvenileId: " + 
						juvenileId + " ReferralNum: " + referralNum + " SequenceNum: " + sequenceNum);
				return mapping.findForward("error");
		}

	}

	/**
	 * Retrieve single detention record based on unique detention record id
	 * @param detentionId
	 * @param logonId
	 * @return
	 */
	private ArrayList retrieveSingleDetentionRecord(String detentionId, String logonId){

		/**
		 * Search for FacilityDetention.
		 */
		log.info("Retrieve Single Detention Record - Facility Detention Detail ID: " + detentionId + " LogonId: " + logonId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetProductionSupportFacilityDetentionEvent getFacilityDetentionEvent = (GetProductionSupportFacilityDetentionEvent)
				EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTFACILITYDETENTION);
		getFacilityDetentionEvent.setHeaderId(detentionId);
		dispatch.postEvent(getFacilityDetentionEvent);		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		Collection<ProductionSupportFacilityDetentionResponseEvent> detentionEventsList =
			MessageUtil.compositeToCollection(compositeResponse, ProductionSupportFacilityDetentionResponseEvent.class);
		ArrayList<ProductionSupportFacilityDetentionResponseEvent> facilityDetentionList =  new ArrayList<ProductionSupportFacilityDetentionResponseEvent>();
		facilityDetentionList.addAll(detentionEventsList);		
		
		return facilityDetentionList;
		
	}
	
	/**
	 * 
	 * Retrieve a collection of the detention records based on given juvenileId and if provided, sequence number and referral number
	 * @param juvenileId
	 * @param referralNum
	 * @param sequenceNum
	 * @param logonId
	 * @return
	 */
	private ArrayList retrieveDetentionRecords(String juvenileId, String referralNum, String sequenceNum, String logonId){

		/**
		 * Search for FacilityDetention Records.
		 */
		log.info("Facility Multiple Detention Detail Records: JuvenileId: " + juvenileId + " referralNum:" + referralNum + " sequenceNum:" + sequenceNum + " LogonId: " + logonId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetProductionSupportFacilityDetentionEvent getFacilityDetentionEvent = (GetProductionSupportFacilityDetentionEvent)
				EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTFACILITYDETENTION);
		getFacilityDetentionEvent.setJuvenileId(juvenileId);
		getFacilityDetentionEvent.setReferralNum(referralNum);
		getFacilityDetentionEvent.setSequenceNum(sequenceNum);

		dispatch.postEvent(getFacilityDetentionEvent);		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		Collection<ProductionSupportFacilityDetentionResponseEvent> detentionEvents =
			MessageUtil.compositeToCollection(compositeResponse, ProductionSupportFacilityDetentionResponseEvent.class);
		ArrayList<ProductionSupportFacilityDetentionResponseEvent> facilityDetentionList =  new ArrayList<ProductionSupportFacilityDetentionResponseEvent>();
		facilityDetentionList.addAll(detentionEvents);
		Collections.sort(facilityDetentionList, ProductionSupportFacilityDetentionResponseEvent.detentionSequenceNumberComparator);
		
		return facilityDetentionList;
		
	}
	
	/**
	 * find and load all the dropdown lists on the form
	 * @param regform
	 */
	private void loadDropDownTables(ProdSupportForm regform){
		// active facility code dropdown
		ArrayList<JuvenileFacilityResponseEvent> activeFacilitiesList = (ArrayList<JuvenileFacilityResponseEvent>)JuvenileFacilityHelper.loadActiveFacilities();
		regform.setActiveFacilitiesList(activeFacilitiesList);
		// admittance reason code dropdown
		ArrayList<JuvenileAdmitReasonsResponseEvent> admitReasonsList = JuvenileFacilityHelper.getAdmitReasons();
		regform.setAdmitReasonsList(admitReasonsList);
		//release To drop down
		regform.setReleaseTo(ComplexCodeTableHelper.getReleasedFromDetentionCodes()); 
		regform.setReturnReasons(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.RETURN_REASON,true));
		regform.setTempReleaseReasons(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.TEMP_RELEASE_REASON, true));
		createStatusList(regform);
	}
	private void createStatusList(ProdSupportForm regForm)
	    {

		ArrayList<CodeResponseEvent> list = new ArrayList<CodeResponseEvent>();
		String[][] obj = { { "D", "Detained in) Detention Facility" }, 
			{ "E", "Escape (a quasi-release)" }, { "N", "In Transfer" }, 
			{ "P", "Placement" }, { "R", "Returned to custody following Escape" }, 
			{ "T", "Temporary Release" }, { "V", "Diversion Program" } };

		    CodeResponseEvent codeObj = new CodeResponseEvent();
		    codeObj.setCode(obj[0][0]);
		    codeObj.setDescription((obj[0][1]));
		    list.add(codeObj);
		    
		    CodeResponseEvent codeObj2 = new CodeResponseEvent();
		    codeObj2.setCode(obj[1][0]);
		    codeObj2.setDescription((obj[1][1]));
		    list.add(codeObj2);
		    
		    CodeResponseEvent codeObj3 = new CodeResponseEvent();
		    codeObj3.setCode(obj[2][0]);
		    codeObj3.setDescription((obj[2][1]));
		    list.add(codeObj3);
		    
		    CodeResponseEvent codeObj4 = new CodeResponseEvent();
		    codeObj4.setCode(obj[3][0]);
		    codeObj4.setDescription((obj[3][1]));
		    list.add(codeObj4);
		    
		    CodeResponseEvent codeObj5 = new CodeResponseEvent();
		    codeObj5.setCode(obj[4][0]);
		    codeObj5.setDescription((obj[4][1]));
		    list.add(codeObj5);
		    
		    CodeResponseEvent codeObj6 = new CodeResponseEvent();
		    codeObj6.setCode(obj[5][0]);
		    codeObj6.setDescription((obj[5][1]));
		    list.add(codeObj6);
		    
		    CodeResponseEvent codeObj7 = new CodeResponseEvent();
		    codeObj7.setCode(obj[6][0]);
		    codeObj7.setDescription((obj[6][1]));
		    list.add(codeObj7);
		    
		    regForm.setStatusCodes(list);

	    }
	
	/**
	 * find and load descriptions for each code shown on the form
	 * @param detentionResponseEventList
	 */
	private void loadCodeDescriptions(ArrayList<ProductionSupportFacilityDetentionResponseEvent> detentionResponseEventList){
		for(ProductionSupportFacilityDetentionResponseEvent responseEvent : detentionResponseEventList){	
			// active facility code description
			JuvenileFacilityResponseEvent activeFacilityResponseEvent =  JuvenileFacilityHelper.getFacilityByCode(responseEvent.getFacilityCode());
			if(activeFacilityResponseEvent != null){
				responseEvent.setFacilityName(activeFacilityResponseEvent.getDescription());
			}else{
				responseEvent.setFacilityName("");
			}
			// admittance reason code description
			JuvenileAdmitReasonsResponseEvent admitResponseEvent =  JuvenileFacilityHelper.getAdmitReasonByCode(responseEvent.getAdmittanceReasonCode());
			if(admitResponseEvent != null){
				responseEvent.setAdmittanceReasonCodeDesc(admitResponseEvent.getDescription());
			}else{
				responseEvent.setAdmittanceReasonCodeDesc("");
			}
		}

	}
	
	/**
	 * 
	 * @param juvenileNum
	 * @param detentionNum
	 * @param form
	 */
	private void loadAvailableRiskIds(String juvenileNum,String detentionNum, ProdSupportForm form){
	    
	    
	    if( detentionNum != null && StringUtils.isNotEmpty(detentionNum) ){
		
		GetProductionSupportFacilityDetentionEvent getFacilityDetentionEvent = (GetProductionSupportFacilityDetentionEvent)
			EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTFACILITYDETENTION);
					getFacilityDetentionEvent.setHeaderId( detentionNum );
			List<ProductionSupportFacilityDetentionResponseEvent> DetRecResp =MessageUtil.postRequestListFilter(getFacilityDetentionEvent,ProductionSupportFacilityDetentionResponseEvent.class);
			
			if(DetRecResp.size() >0){
			    
			    juvenileNum = DetRecResp.get(0).getJuvenileId();
			}
	    }	    
	    
	    GetRiskAnalysisByJuvenileIdEvent entries = (GetRiskAnalysisByJuvenileIdEvent)
			EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETRISKANALYSISBYJUVENILEID);
	    
		entries.setJuvenileNum(juvenileNum);
	    
		List<RiskAssessmentListResponseEvent> resp = MessageUtil.postRequestListFilter(entries, RiskAssessmentListResponseEvent.class);
		
	    Map riskMap = new TreeMap();
	    ArrayList riskIdList = new ArrayList();
	    
	     for(int x=0; x< resp.size();x++){
		 
		 RiskAssessmentListResponseEvent risk = resp.get(x);

		 	riskMap.put(risk.getAssessmentID(), null);
 
		}

		Iterator iter = riskMap.entrySet().iterator();
		while (iter.hasNext())
		{
		    Map.Entry pair = (Map.Entry) iter.next();
		    CodeResponseEvent obj = new CodeResponseEvent();
		    obj.setDescription(pair.getKey().toString());
		    riskIdList.add(obj);
		    iter.remove();
		}
		    
		    form.setRiskAnalysisIds(riskIdList);
		
	    }
	    
	
}
