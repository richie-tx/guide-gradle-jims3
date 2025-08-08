package ui.supervision.programReferral.action;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerprogramreferrals.GetReferralFormFieldsEvent;
import messaging.administerprogramreferrals.reply.ReferralFormFieldResponseEvent;
import messaging.administerprogramreferrals.to.CSCDDwiReferralFormReportDataTO;
import messaging.administerprogramreferrals.to.CSCDEduReferralFormReportDataTO;
import messaging.administerprogramreferrals.to.CSCDTaipReferralFormDataTO;
import messaging.administerprogramreferrals.to.IReferralFormReportDataTO;
import messaging.administerprogramreferrals.to.NewStartReferralFormReportDataTO;
import messaging.administerprogramreferrals.to.PolygraphReferralFormReportDataTO;
import messaging.administerprogramreferrals.to.ReferralFormReportDataTO;
import messaging.administerprogramreferrals.to.TAIPReferralFormReportDataTO;
import messaging.report.GenericPrintRequestEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.CSAdministerProgramReferralsConstants;
import naming.CSReferralFormConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.administerassessments.form.AssessmentForm;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administerserviceprovider.CSC.AdminServiceProviderHelper;
import ui.supervision.administerserviceprovider.CSC.form.ServiceProviderCSCDForm;
import ui.supervision.programReferral.CSCProgRefUIHelper;
import ui.supervision.programReferral.CSReferralFormUIHelper;
import ui.supervision.programReferral.form.CSCProgRefForm;
import ui.supervision.programReferral.form.CSCProgRefForm.RefTypeCodeNRefTypeNumNRefIdValue;
import ui.supervision.supervisee.form.SuperviseeForm;
import ui.supervision.supervisionorder.form.SupervisionOrderSearchForm;

/**
 * 
 * @author cc_bjangay
 *
 */
public class HandleReferralFormAction extends JIMSBaseAction
{

	@Override
	protected void addButtonMapping(Map keyMap) 
	{
		keyMap.put("button.view", "view");
		keyMap.put("button.next", "next");
		keyMap.put("button.print", "printUJACForm");
	}	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progRefForm = (CSCProgRefForm)aForm;
		progRefForm.setAgencyId(SecurityUIHelper.getUserAgencyId());
		progRefForm.setSecondaryAction(UIConstants.SUMMARY);
		return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
	}
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward view(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progRefForm = (CSCProgRefForm)aForm;
		progRefForm.setReferralTypeDesc("");
		progRefForm.setAgencyId(SecurityUIHelper.getUserAgencyId());
		GetReferralFormFieldsEvent referralFormsFieldEvt = new GetReferralFormFieldsEvent();
		if ((progRefForm.getAction().equals(CSAdministerProgramReferralsConstants.ACTION_CREATE)) ||
				(progRefForm.getAction().equals(CSAdministerProgramReferralsConstants.ACTION_UPDATE_INIT)))
		{
			RefTypeCodeNRefTypeNumNRefIdValue refTypeObj = (RefTypeCodeNRefTypeNumNRefIdValue)progRefForm.getReferralTypeCdNDetailsMap().get(progRefForm.getReferralTypeCode());
			progRefForm.setReferralTypeDesc(refTypeObj.getRefTypeDesc());
			progRefForm.setProgRefId((String)progRefForm.getReferralTypeCdNPgmRefIdMap().get(progRefForm.getReferralTypeCode()));
		}
		
		referralFormsFieldEvt.setProgramReferralId(progRefForm.getProgRefId());
		referralFormsFieldEvt.setReferralFormId(progRefForm.getReferralFormId());
		
		List responseEvtList = postRequestListFilter(referralFormsFieldEvt, ReferralFormFieldResponseEvent.class);
		CSCProgRefUIHelper.populateReferralFormFields(progRefForm, responseEvtList);
		
		return aMapping.findForward(UIConstants.VIEW_SUCCESS);
	}
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	
	public ActionForward printUJACForm(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)throws Exception
	{
		CSCProgRefForm progRefForm = (CSCProgRefForm)aForm;
		CaseAssignmentForm caseAssignForm = (CaseAssignmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CS_CASE_ASSIGNMENT_FORM, true);
		SuperviseeForm superviseeForm = (SuperviseeForm) getSessionForm(aMapping, aRequest, "superviseeForm", true);
		ServiceProviderCSCDForm serviceProviderForm = (ServiceProviderCSCDForm) getSessionForm(aMapping, aRequest, "cscServiceProviderForm", true);
		SupervisionOrderSearchForm searchForm = (SupervisionOrderSearchForm) getSessionForm(aMapping, aRequest,"supervisionOrderSearchForm", true);
		
		searchForm.setSpn(progRefForm.getSpn());
		
		serviceProviderForm.setServiceProviderId(progRefForm.getServiceProviderId());
		String sPId = progRefForm.getServiceProviderId();
		if ( StringUtils.isNotEmpty(sPId) && !sPId.equals("USER_ENTERED"))
		{
			AdminServiceProviderHelper.updateServiceProviderForm( serviceProviderForm );
		}
	
		IReferralFormReportDataTO referralFormDataTo = null;
		
		if(progRefForm.getReferralFormName().equalsIgnoreCase(CSReferralFormConstants.REF_FORM_CSCD_DWI))
		{
			CSCDDwiReferralFormReportDataTO cscdDwiRefFormDataTo = new CSCDDwiReferralFormReportDataTO();
			referralFormDataTo = cscdDwiRefFormDataTo;		
//			populate Supervisee Details
			CSReferralFormUIHelper.populateSuperviseeDetails((ReferralFormReportDataTO)cscdDwiRefFormDataTo, superviseeForm, caseAssignForm);
//			populate Officer Details
			CSReferralFormUIHelper.populateOfficerDetails((ReferralFormReportDataTO)cscdDwiRefFormDataTo, caseAssignForm, progRefForm);
//			populate OOC Details
			CSReferralFormUIHelper.populateOOCDetails((ReferralFormReportDataTO)cscdDwiRefFormDataTo, caseAssignForm);
//			populate Supervision Dates			
			CSReferralFormUIHelper.populateSupervisionDates((ReferralFormReportDataTO)cscdDwiRefFormDataTo, caseAssignForm);
//			populate CSCD DWI Fields
			List fieldsList = progRefForm.getReferralformFieldsList();
			CSReferralFormUIHelper.populateCscdDwiFields(cscdDwiRefFormDataTo, fieldsList);
		}//CSCD DWI
		else if(progRefForm.getReferralFormName().equalsIgnoreCase(CSReferralFormConstants.REF_FORM_CSCD_EDU))
		{
			CSCDEduReferralFormReportDataTO cscdEduRefFormDataTo = new CSCDEduReferralFormReportDataTO();
			referralFormDataTo = cscdEduRefFormDataTo;			
//			populate Supervisee Details
			CSReferralFormUIHelper.populateSuperviseeDetails((ReferralFormReportDataTO)cscdEduRefFormDataTo, superviseeForm, caseAssignForm);
//			populate Officer Details
			CSReferralFormUIHelper.populateOfficerDetails((ReferralFormReportDataTO)cscdEduRefFormDataTo, caseAssignForm, progRefForm);
//			populate Program Details
			CSReferralFormUIHelper.populateProgramDetails((ReferralFormReportDataTO)cscdEduRefFormDataTo, progRefForm);
//			populate CSCD EDU Fields
			List fieldsList = progRefForm.getReferralformFieldsList();
			CSReferralFormUIHelper.populateCscdEduFields(cscdEduRefFormDataTo, fieldsList);	
		}//CSCD EDU
		else if(progRefForm.getReferralFormName().equalsIgnoreCase(CSReferralFormConstants.REF_FORM_CSCD_GENERAL))
		{
			ReferralFormReportDataTO cscdGeneralRefFormDataTo = new ReferralFormReportDataTO();
			referralFormDataTo = cscdGeneralRefFormDataTo;			
//			populate Supervisee Details
			CSReferralFormUIHelper.populateSuperviseeDetails((ReferralFormReportDataTO)cscdGeneralRefFormDataTo, superviseeForm, caseAssignForm);
//			populate Officer Details
			CSReferralFormUIHelper.populateOfficerDetails((ReferralFormReportDataTO)cscdGeneralRefFormDataTo, caseAssignForm, progRefForm);
//			populate Program Details
			CSReferralFormUIHelper.populateProgramDetails((ReferralFormReportDataTO)cscdGeneralRefFormDataTo, progRefForm);
//			populate Service Provider Details
			CSReferralFormUIHelper.populateServiceProviderDetails((ReferralFormReportDataTO)cscdGeneralRefFormDataTo, serviceProviderForm, progRefForm);			
//			populate CSCD General Form Fields
			List fieldsList = progRefForm.getReferralformFieldsList();
			CSReferralFormUIHelper.populateCscdGeneralFields(cscdGeneralRefFormDataTo, fieldsList);	
		}//CSCD General Form
		else if(progRefForm.getReferralFormName().equalsIgnoreCase(CSReferralFormConstants.REF_FORM_CSCD_TAIP))
		{
			CSCDTaipReferralFormDataTO  cscdTaipRefFormDataTo = new CSCDTaipReferralFormDataTO();
			referralFormDataTo = cscdTaipRefFormDataTo;
			AssessmentForm assessForm = (AssessmentForm) getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM, true);
//			populate Supervisee Details
			CSReferralFormUIHelper.populateSuperviseeDetails((ReferralFormReportDataTO)cscdTaipRefFormDataTo, superviseeForm, caseAssignForm);
//			populate Officer Details
			CSReferralFormUIHelper.populateOfficerDetails((ReferralFormReportDataTO)cscdTaipRefFormDataTo, caseAssignForm, progRefForm);
//			populate Prior Offense Details
			CSReferralFormUIHelper.populatePriorOffenseDetails((ReferralFormReportDataTO)cscdTaipRefFormDataTo, caseAssignForm);
//			populate Supervision Dates			
			CSReferralFormUIHelper.populateSupervisionDates((ReferralFormReportDataTO)cscdTaipRefFormDataTo, caseAssignForm);
//			populate CSCD TAIP Fields
			List fieldsList = progRefForm.getReferralformFieldsList();
			CSReferralFormUIHelper.populateCscdTaipFields(cscdTaipRefFormDataTo , fieldsList);	
			CSReferralFormUIHelper.populateCscdTaipReportDetails(cscdTaipRefFormDataTo, assessForm);			
		}//CSCD TAIP	
		else if(progRefForm.getReferralFormName().equalsIgnoreCase(CSReferralFormConstants.REF_FORM_CSCD_TDOEP))
		{
			ReferralFormReportDataTO cscdTDOEPRefFormDataTo = new ReferralFormReportDataTO();
			referralFormDataTo = cscdTDOEPRefFormDataTo;			
//			populate Supervisee Details
			CSReferralFormUIHelper.populateSuperviseeDetails((ReferralFormReportDataTO)cscdTDOEPRefFormDataTo, superviseeForm, caseAssignForm);
//			populate Officer Details
			CSReferralFormUIHelper.populateOfficerDetails((ReferralFormReportDataTO)cscdTDOEPRefFormDataTo, caseAssignForm, progRefForm);
//			populate OOC Details
			CSReferralFormUIHelper.populateOOCDetails((ReferralFormReportDataTO)cscdTDOEPRefFormDataTo, caseAssignForm);
//			populate Program Details
			CSReferralFormUIHelper.populateProgramDetails((ReferralFormReportDataTO)cscdTDOEPRefFormDataTo, progRefForm);
//			populate CSCD TDOEP Fields
			List fieldsList = progRefForm.getReferralformFieldsList();
			CSReferralFormUIHelper.populateCscdTDOEPFields(cscdTDOEPRefFormDataTo, fieldsList);	
		}//CSCD TDOEP
	    else if(progRefForm.getReferralFormName().equalsIgnoreCase(CSReferralFormConstants.REF_FORM_CSCD_VIP))
	    {
			ReferralFormReportDataTO  cscdVipRefFormDataTo = new ReferralFormReportDataTO();	
			referralFormDataTo = cscdVipRefFormDataTo;
//			populate Supervisee Details
			CSReferralFormUIHelper.populateSuperviseeDetails(cscdVipRefFormDataTo, superviseeForm, caseAssignForm);
//			populate Officer Details
			CSReferralFormUIHelper.populateOfficerDetails(cscdVipRefFormDataTo, caseAssignForm, progRefForm);
//			populate VIP Dates
			CSReferralFormUIHelper.populateCscdVIPDates(cscdVipRefFormDataTo);
		}//CSCD VIP				
	    else if(progRefForm.getReferralFormName().equalsIgnoreCase(CSReferralFormConstants.REF_FORM_CSCD_VIP_SPANISH))
		{
			ReferralFormReportDataTO  cscdVipSpanRefFormDataTo = new ReferralFormReportDataTO();	
			referralFormDataTo = cscdVipSpanRefFormDataTo;
//			populate Supervisee Details
			CSReferralFormUIHelper.populateSuperviseeDetails(cscdVipSpanRefFormDataTo, superviseeForm, caseAssignForm);
//			populate Officer Details
			CSReferralFormUIHelper.populateOfficerDetails(cscdVipSpanRefFormDataTo, caseAssignForm, progRefForm);
//			populate VIP Dates
			CSReferralFormUIHelper.populateCscdVIPDates(cscdVipSpanRefFormDataTo);
		}//CSCD VIP SPANISH
	    else if(progRefForm.getReferralFormName().equalsIgnoreCase(CSReferralFormConstants.REF_FORM_DWI))
		{
			CSCDDwiReferralFormReportDataTO dwiRefFormDataTo = new CSCDDwiReferralFormReportDataTO();
			referralFormDataTo = dwiRefFormDataTo;			
//			populate Supervisee Details
			CSReferralFormUIHelper.populateSuperviseeDetails((ReferralFormReportDataTO)dwiRefFormDataTo, superviseeForm, caseAssignForm);
//			populate Officer Details
			CSReferralFormUIHelper.populateOfficerDetails((ReferralFormReportDataTO)dwiRefFormDataTo, caseAssignForm, progRefForm);
//			populate OOC Details
			CSReferralFormUIHelper.populateOOCDetails((ReferralFormReportDataTO)dwiRefFormDataTo, caseAssignForm);
//			populate Program Details
			CSReferralFormUIHelper.populateProgramDetails((ReferralFormReportDataTO)dwiRefFormDataTo, progRefForm);
//			populate Service Provider Details
			CSReferralFormUIHelper.populateServiceProviderDetails((ReferralFormReportDataTO)dwiRefFormDataTo, serviceProviderForm, progRefForm);
//			populate Supervision Dates			
			CSReferralFormUIHelper.populateSupervisionDates((ReferralFormReportDataTO)dwiRefFormDataTo, caseAssignForm);
//			populate DWI Fields
			List fieldsList = progRefForm.getReferralformFieldsList();
			CSReferralFormUIHelper.populateCscdDwiFields(dwiRefFormDataTo, fieldsList);
		}//DWI
	    else if(progRefForm.getReferralFormName().equalsIgnoreCase(CSReferralFormConstants.REF_FORM_GENERAL))
		{
			ReferralFormReportDataTO generalRefFormDataTo = new ReferralFormReportDataTO();
			referralFormDataTo = generalRefFormDataTo;
//			populate Supervisee Details
			CSReferralFormUIHelper.populateSuperviseeDetails((ReferralFormReportDataTO)generalRefFormDataTo, superviseeForm, caseAssignForm);
//			populate Officer Details
			CSReferralFormUIHelper.populateOfficerDetails((ReferralFormReportDataTO)generalRefFormDataTo, caseAssignForm, progRefForm);
//			populate Program Details
			CSReferralFormUIHelper.populateProgramDetails((ReferralFormReportDataTO)generalRefFormDataTo, progRefForm);
//			populate Service Provider Details
			CSReferralFormUIHelper.populateServiceProviderDetails((ReferralFormReportDataTO)generalRefFormDataTo, serviceProviderForm, progRefForm);
//			populate General Fields
			List fieldsList = progRefForm.getReferralformFieldsList();
			CSReferralFormUIHelper.populateGeneralFields(generalRefFormDataTo, fieldsList);	
		}//General Referral Form
	    else if(progRefForm.getReferralFormName().equalsIgnoreCase(CSReferralFormConstants.REF_FORM_GENERIC_POLY))
		{
	    	PolygraphReferralFormReportDataTO genericPolyRefFormDataTo = new PolygraphReferralFormReportDataTO();
			referralFormDataTo = genericPolyRefFormDataTo;
//			populate Supervisee Details
			CSReferralFormUIHelper.populateSuperviseeDetails((ReferralFormReportDataTO)genericPolyRefFormDataTo, superviseeForm, caseAssignForm);
//			populate Officer Details
			CSReferralFormUIHelper.populateOfficerDetails((ReferralFormReportDataTO)genericPolyRefFormDataTo, caseAssignForm, progRefForm);
//			populate Program Details
			CSReferralFormUIHelper.populateProgramDetails((ReferralFormReportDataTO)genericPolyRefFormDataTo, progRefForm);
//			populate Service Provider Details
			CSReferralFormUIHelper.populateServiceProviderDetails((ReferralFormReportDataTO)genericPolyRefFormDataTo, serviceProviderForm, progRefForm);
//			populate Generic Poligraph Fields
			List fieldsList = progRefForm.getReferralformFieldsList();
			CSReferralFormUIHelper.populatePolygraphFields(genericPolyRefFormDataTo, fieldsList);	
		}//Generic Polygraph
	    else if(progRefForm.getReferralFormName().equalsIgnoreCase(CSReferralFormConstants.REF_FORM_NEW_START))
		{
	    	NewStartReferralFormReportDataTO  newStartRefFormDataTo  = new NewStartReferralFormReportDataTO();
			referralFormDataTo = newStartRefFormDataTo;			
//			populate Supervisee Details
			CSReferralFormUIHelper.populateSuperviseeDetails((ReferralFormReportDataTO)newStartRefFormDataTo, superviseeForm, caseAssignForm);
//			populate Supervisee Address
			CSReferralFormUIHelper.populateSuperviseeAddress((ReferralFormReportDataTO)newStartRefFormDataTo, superviseeForm);
//			populate Officer Details
			CSReferralFormUIHelper.populateOfficerDetails((ReferralFormReportDataTO)newStartRefFormDataTo, caseAssignForm, progRefForm);
//			populate Prior Offense Details
			CSReferralFormUIHelper.populatePriorOffenseDetails((ReferralFormReportDataTO)newStartRefFormDataTo, caseAssignForm);
//			populate Supervision Dates			
			CSReferralFormUIHelper.populateSupervisionDates((ReferralFormReportDataTO)newStartRefFormDataTo, caseAssignForm);
//			populate CSCD New Start Fields
			List fieldsList = progRefForm.getReferralformFieldsList();
			CSReferralFormUIHelper.populateNewStartFields(newStartRefFormDataTo , fieldsList);
		}//NEW START
	    else if(progRefForm.getReferralFormName().equalsIgnoreCase(CSReferralFormConstants.REF_FORM_NON_TAIP))
		{
	    	TAIPReferralFormReportDataTO nonTaipTreatmentDataTo = new TAIPReferralFormReportDataTO();
			referralFormDataTo = nonTaipTreatmentDataTo;
//			populate Supervisee Details
			CSReferralFormUIHelper.populateSuperviseeDetails((ReferralFormReportDataTO)nonTaipTreatmentDataTo, superviseeForm, caseAssignForm);
//			populate Officer Details
			CSReferralFormUIHelper.populateOfficerDetails((ReferralFormReportDataTO)nonTaipTreatmentDataTo, caseAssignForm, progRefForm);
//			populate Program Details
			CSReferralFormUIHelper.populateProgramDetails((ReferralFormReportDataTO)nonTaipTreatmentDataTo, progRefForm);
//			populate Service Provider Details
			CSReferralFormUIHelper.populateServiceProviderDetails((ReferralFormReportDataTO)nonTaipTreatmentDataTo, serviceProviderForm, progRefForm);
//			populate Non TAIP Treatment Fields
			List fieldsList = progRefForm.getReferralformFieldsList();
			CSReferralFormUIHelper.populateNonTaipTreatmentFields(nonTaipTreatmentDataTo, fieldsList);	
		}//Non TAIP Treatment
	    else if(progRefForm.getReferralFormName().equalsIgnoreCase(CSReferralFormConstants.REF_FORM_SEX_OFFEN_POLY))
		{
	    	PolygraphReferralFormReportDataTO sexOffenderPolyRefFormDataTo = new PolygraphReferralFormReportDataTO();
			referralFormDataTo = sexOffenderPolyRefFormDataTo;
//			populate Supervisee Details
			CSReferralFormUIHelper.populateSuperviseeDetails((ReferralFormReportDataTO)sexOffenderPolyRefFormDataTo, superviseeForm, caseAssignForm);
//			populate Officer Details
			CSReferralFormUIHelper.populateOfficerDetails((ReferralFormReportDataTO)sexOffenderPolyRefFormDataTo, caseAssignForm, progRefForm);
//			populate Program Details
			CSReferralFormUIHelper.populateProgramDetails((ReferralFormReportDataTO)sexOffenderPolyRefFormDataTo, progRefForm);
//			populate Service Provider Details
			CSReferralFormUIHelper.populateServiceProviderDetails((ReferralFormReportDataTO)sexOffenderPolyRefFormDataTo, serviceProviderForm, progRefForm);
//			populate Sex Offender Poligraph Fields
			List fieldsList = progRefForm.getReferralformFieldsList();
			CSReferralFormUIHelper.populatePolygraphFields(sexOffenderPolyRefFormDataTo, fieldsList);	
		}//Sex Offender Polygraph
	    else if(progRefForm.getReferralFormName().equalsIgnoreCase(CSReferralFormConstants.REF_FORM_SEX_OFFEN_TRTMNT))
		{
	    	PolygraphReferralFormReportDataTO sexOffenderTreatmentRefFormDataTo = new PolygraphReferralFormReportDataTO();
			referralFormDataTo = sexOffenderTreatmentRefFormDataTo;
//			populate Supervisee Details
			CSReferralFormUIHelper.populateSuperviseeDetails((ReferralFormReportDataTO)sexOffenderTreatmentRefFormDataTo, superviseeForm, caseAssignForm);
//			populate Officer Details
			CSReferralFormUIHelper.populateOfficerDetails((ReferralFormReportDataTO)sexOffenderTreatmentRefFormDataTo, caseAssignForm, progRefForm);
//			populate Program Details
			CSReferralFormUIHelper.populateProgramDetails((ReferralFormReportDataTO)sexOffenderTreatmentRefFormDataTo, progRefForm);
//			populate Service Provider Details
			CSReferralFormUIHelper.populateServiceProviderDetails((ReferralFormReportDataTO)sexOffenderTreatmentRefFormDataTo, serviceProviderForm, progRefForm);
//			populate Sex Offender Treatment Fields
			List fieldsList = progRefForm.getReferralformFieldsList();
			CSReferralFormUIHelper.populateSexOffenderTreatmentFields(sexOffenderTreatmentRefFormDataTo, fieldsList);	
		}//Sex Offender Treatment
	    else if(progRefForm.getReferralFormName().equalsIgnoreCase(CSReferralFormConstants.REF_FORM_TAIP_OUTPATNT))
		{
	    	TAIPReferralFormReportDataTO taipOutPatntDataTo = new TAIPReferralFormReportDataTO();
			referralFormDataTo = taipOutPatntDataTo;
//			populate Supervisee Details
			CSReferralFormUIHelper.populateSuperviseeDetails((ReferralFormReportDataTO)taipOutPatntDataTo, superviseeForm, caseAssignForm);
//			populate Officer Details
			CSReferralFormUIHelper.populateOfficerDetails((ReferralFormReportDataTO)taipOutPatntDataTo, caseAssignForm, progRefForm);
//			populate Program Details
			CSReferralFormUIHelper.populateProgramDetails((ReferralFormReportDataTO)taipOutPatntDataTo, progRefForm);
//			populate Service Provider Details
			CSReferralFormUIHelper.populateServiceProviderDetails((ReferralFormReportDataTO)taipOutPatntDataTo, serviceProviderForm, progRefForm);
//			populate TAIP Out-Patient Fields
			List fieldsList = progRefForm.getReferralformFieldsList();
			CSReferralFormUIHelper.populateTaipOutPatientFields(taipOutPatntDataTo, fieldsList);	
		}//TAIP Out-Patient
	    else if(progRefForm.getReferralFormName().equalsIgnoreCase(CSReferralFormConstants.REF_FORM_TAIP_RESIDENTIAL))
		{
	    	TAIPReferralFormReportDataTO taipResidentialDataTo = new TAIPReferralFormReportDataTO();
			referralFormDataTo = taipResidentialDataTo;
//			populate Supervisee Details
			CSReferralFormUIHelper.populateSuperviseeDetails((ReferralFormReportDataTO)taipResidentialDataTo, superviseeForm, caseAssignForm);
//			populate Officer Details
			CSReferralFormUIHelper.populateOfficerDetails((ReferralFormReportDataTO)taipResidentialDataTo, caseAssignForm, progRefForm);
//			populate Program Details
			CSReferralFormUIHelper.populateProgramDetails((ReferralFormReportDataTO)taipResidentialDataTo, progRefForm);
//			populate Service Provider Details
			CSReferralFormUIHelper.populateServiceProviderDetails((ReferralFormReportDataTO)taipResidentialDataTo, serviceProviderForm, progRefForm);
//			populate TAIP Residential Fields
			List fieldsList = progRefForm.getReferralformFieldsList();
			CSReferralFormUIHelper.populateTaipResidentialFields(taipResidentialDataTo, fieldsList);	
		}//TAIP Residential
		else if(progRefForm.getReferralFormName().equalsIgnoreCase(CSReferralFormConstants.REF_FORM_TDOEP))
		{
			ReferralFormReportDataTO tdoepRefFormDataTo = new ReferralFormReportDataTO();
			referralFormDataTo = tdoepRefFormDataTo;			
//			populate Supervisee Details
			CSReferralFormUIHelper.populateSuperviseeDetails((ReferralFormReportDataTO)tdoepRefFormDataTo, superviseeForm, caseAssignForm);
//			populate Officer Details
			CSReferralFormUIHelper.populateOfficerDetails((ReferralFormReportDataTO)tdoepRefFormDataTo, caseAssignForm, progRefForm);
//			populate OOC Details
			CSReferralFormUIHelper.populateOOCDetails((ReferralFormReportDataTO)tdoepRefFormDataTo, caseAssignForm);
//			populate Program Details
			CSReferralFormUIHelper.populateProgramDetails((ReferralFormReportDataTO)tdoepRefFormDataTo, progRefForm);
//			populate Service Provider Details
			CSReferralFormUIHelper.populateServiceProviderDetails((ReferralFormReportDataTO)tdoepRefFormDataTo, serviceProviderForm, progRefForm);
//			populate CSCD TDOEP Fields
			List fieldsList = progRefForm.getReferralformFieldsList();
			CSReferralFormUIHelper.populateCscdTDOEPFields(tdoepRefFormDataTo, fieldsList);	
		}//TDOEP
		// load the report info event  		
     	GenericPrintRequestEvent referralEvent = new GenericPrintRequestEvent();
        referralEvent.addDataObject(referralFormDataTo);   

        String reportNam = progRefForm.getReferralFormName();
        StringBuffer reportName = new StringBuffer();
        if(reportNam != null && !reportNam.equalsIgnoreCase(""))
        {
        	reportName.append("REPORTING::");
        	reportName.append(reportNam);
        }
        referralEvent.setReportName(reportName.toString());

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);   
        dispatch.postEvent(referralEvent);
        CompositeResponse compResponse = (CompositeResponse) dispatch.getReply();
        MessageUtil.processReturnException(compResponse);
        ReportResponseEvent aRespEvent = (ReportResponseEvent) MessageUtil.filterComposite(compResponse, ReportResponseEvent.class);
        aResponse.setContentType("application/x-file-download");
        aResponse.setHeader("Content-disposition", "attachment; filename="
                            + aRespEvent.getFileName().substring(aRespEvent.getFileName().lastIndexOf("/") + 1) + ".pdf");   
        aResponse.setHeader("Cache-Control", "must-revalidate");   
        aResponse.setContentLength(aRespEvent.getContent().length);   
        aResponse.resetBuffer();   
        OutputStream os;
        
        os = aResponse.getOutputStream();
        os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);   
        os.flush();   
        os.close();

        return null;
	}
}