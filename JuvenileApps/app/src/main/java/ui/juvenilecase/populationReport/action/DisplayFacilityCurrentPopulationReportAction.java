package ui.juvenilecase.populationReport.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.to.SocialSecurityBean;
import messaging.family.GetFamilyConstellationDetailsEvent;
import messaging.family.GetFamilyMemberDetailsEvent;
import messaging.juvenile.reply.JuvenileDetentionVisitResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilitiesCurrPopResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.Address;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.form.GuardianBean;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.populationReport.FacilityCurrentPopulationReportBean;
import ui.juvenilecase.populationReport.UIFacilityPopulationHelper;
import ui.juvenilecase.populationReport.form.JuvenilePopulationForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

public class DisplayFacilityCurrentPopulationReportAction extends JIMSBaseAction
{
	/**
	 * @roseuid 42AF409F01B5
	 */
	public DisplayFacilityCurrentPopulationReportAction()
	{
	}

	/**
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected void addButtonMapping(Map buttonMap)
	{
		buttonMap.put("button.submit", "findFacilityPopulations");
		buttonMap.put("button.print", "print");
		buttonMap.put("button.guardianPrint", "guardianDetailPrint");
		buttonMap.put("button.cancel", "cancel");
		return;
	}
	/*
	 * 
	 */
	public ActionForward findFacilityPopulations(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenilePopulationForm jpForm = (JuvenilePopulationForm)aForm;
		UIFacilityPopulationHelper.getFacilityCurrentPopulationReport(jpForm);
		aRequest.getSession().setAttribute("reset", "yes");
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/*
	 * 
	 */
	public ActionForward print(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenilePopulationForm jpForm = (JuvenilePopulationForm)aForm;
		// add code to load data needed for BCO print
		
		FacilityCurrentPopulationReportBean reportingInfo = prepareBean(jpForm, true);
		reportingInfo.setReportName(PDFReport.CURRENT_POPULATION_REPORT_NAME.getReportName());
		aRequest.getSession().setAttribute("reportInfo", reportingInfo);

		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.CURRENT_POPULATION_REPORT_NAME);
		return null;
	}
	
    public ActionForward guardianDetailPrint(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenilePopulationForm jpForm = (JuvenilePopulationForm) aForm;
	// add code to load data needed for BCO print

	FacilityCurrentPopulationReportBean reportingInfo = prepareGuardianDetailsPrintBean(jpForm, true);
	reportingInfo.setReportName(PDFReport.CURRENT_POPULATION_GUARDIANREPORT_NAME.getReportName());
	aRequest.getSession().setAttribute("reportInfo", reportingInfo);

	BFOPdfManager pdfManager = BFOPdfManager.getInstance();
	pdfManager.createPDFReport(aRequest, aResponse, PDFReport.CURRENT_POPULATION_GUARDIANREPORT_NAME);
	return null;
    }
	
	private FacilityCurrentPopulationReportBean prepareBean(JuvenilePopulationForm form, boolean viewReport)
	{
		FacilityCurrentPopulationReportBean bean = new FacilityCurrentPopulationReportBean();
		
		bean.setFacilityName(UIFacilityPopulationHelper.getCodeDescription(form.getFacilities(), form.getFacilityId()));		
		bean.setCurrentPopulations(form.getCurrentPopulations());		
		return bean;
	}
	
	private FacilityCurrentPopulationReportBean prepareGuardianDetailsPrintBean(JuvenilePopulationForm form, boolean viewReport) {
	    FacilityCurrentPopulationReportBean bean = new FacilityCurrentPopulationReportBean();

	    bean.setFacilityName(UIFacilityPopulationHelper.getCodeDescription(form.getFacilities(), form.getFacilityId()));

	    ArrayList<JuvenileFacilitiesCurrPopResponseEvent> currentPopulations = (ArrayList<JuvenileFacilitiesCurrPopResponseEvent>) form.getCurrentPopulations();

	    for (JuvenileFacilitiesCurrPopResponseEvent event : currentPopulations) {
	        if (event.getJuvenileNum() != null && !event.getJuvenileNum().isEmpty()) {
	            Collection<FamilyConstellationListResponseEvent> constellationList = UIJuvenileHelper.getCurrentActiveConstellation(event.getJuvenileNum());

	            if (!constellationList.isEmpty()) {
	                /*
	                 * Only 1 active constellation at a time, therefore it's safe to get
	                 * the first in the collection
	                 */
	                FamilyConstellationListResponseEvent activeConstellation = constellationList.iterator().next();

	                GetFamilyConstellationDetailsEvent getConstellationDetails = new GetFamilyConstellationDetailsEvent();
	                getConstellationDetails.setConstellationNum(activeConstellation.getFamilyNum());

	                CompositeResponse replyEvent = MessageUtil.postRequest(getConstellationDetails);
	                Collection<FamilyConstellationMemberListResponseEvent> familyMembers = MessageUtil.compositeToCollection(replyEvent, FamilyConstellationMemberListResponseEvent.class);

	                // Create a list of guardian(s) for display and residential information - address and phone number
	                if (familyMembers.size() > 0) {
	                    List<GuardianBean> guardians = new ArrayList<>();

	                    for (FamilyConstellationMemberListResponseEvent member : familyMembers) {
	                        GetFamilyMemberDetailsEvent getMemberDetails = (GetFamilyMemberDetailsEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERDETAILS);

	                        getMemberDetails.setMemberNum(member.getMemberNum());
	                        replyEvent = MessageUtil.postRequest(getMemberDetails);
	                        FamilyMemberDetailResponseEvent memberDetail = (FamilyMemberDetailResponseEvent) MessageUtil.filterComposite(replyEvent, FamilyMemberDetailResponseEvent.class);

	                        String rel = member.getRelationToJuvenileId();
	                        if (rel != null && !rel.isEmpty()) {
	                            GuardianBean pbean = new GuardianBean();
	                            pbean.setNameOfPerson(new Name(memberDetail.getFirstName(), memberDetail.getMiddleName(), memberDetail.getLastName()));
	                            pbean.setRelationshipType(member.getRelationToJuvenile());
	                            pbean.setMemberNum(member.getMemberNum());
	                            pbean.setLanguage(CodeHelper.getFastCodeDescription(PDCodeTableConstants.LANGUAGE, memberDetail.getPrimarylanguageId()));
	                            pbean.setInHomeStatus(String.valueOf(member.isInHomeStatus()));
	                            pbean.setPrimaryContact(String.valueOf(member.isPrimaryContact()));
	                            
	                            // If guardian
	                            if (member.isGuardian()) {
	                                guardians.add(pbean);
	                            }
	                            
	                            if (pbean != null
		                                && "true".equalsIgnoreCase(pbean.getPrimaryContact())
		                                && "true".equalsIgnoreCase(pbean.getInHomeStatus())) {
	                        	break;
	                            }	                           
	                        }
	                    }

	                    for (int x = 0; x < guardians.size(); x++) {
	                        GuardianBean gbean = guardians.get(x);
	                        if (gbean != null
	                                && "true".equalsIgnoreCase(gbean.getPrimaryContact())
	                                && "true".equalsIgnoreCase(gbean.getInHomeStatus())) {

	                            // Set primary guardian name
	                            if (gbean.getNameOfPerson() != null) {
	                                event.setPrimaryGuardianName(gbean.getNameOfPerson().getFormattedNameForReport());
	                            }

	                            // Set guardian relationship
	                            if (gbean.getRelationshipType() != null) {
	                                event.setGuardianRelationship(gbean.getRelationshipType());
	                            }

	                            // Set guardian primary language
	                            if (gbean.getLanguage() != null) {
	                                event.setGuardianPrimaryLanguage(gbean.getLanguage());
	                            }

	                            // Use member# to find address and phone number
	                            Address familyAddress = UIJuvenileHelper.getFamilyMemberAddress(gbean.getMemberNum());
	                            if (familyAddress != null && familyAddress.getStreetAddressForReport() != null) {
	                                event.setGuardianAddress(familyAddress.getStreetAddressForReport());
	                            }

	                            JuvenileMemberForm.MemberContact familyPhone = UIJuvenileHelper.getFamilyMemberPhone(gbean.getMemberNum());
	                            if (familyPhone != null
	                                    && familyPhone.getContactPhoneNumber() != null
	                                    && familyPhone.getContactPhoneNumber().getFormattedPhoneNumber() != null) {
	                                event.setGuardianPhoneNumber(familyPhone.getContactPhoneNumber().getFormattedPhoneNumber());
	                            }

	                            Iterator iter = pd.juvenilecase.family.FamilyMemberEmail.findAll("familyMemberId", gbean.getMemberNum());
	                            while (iter != null && iter.hasNext()) {
	                                pd.juvenilecase.family.FamilyMemberEmail email = (pd.juvenilecase.family.FamilyMemberEmail) iter.next();
	                                if (email != null && email.getEmailAddress() != null) {
	                                    event.setGuardianEmailAddress(email.getEmailAddress());
	                                }
	                            }

	                            break;
	                        }
	                    }
	                }
	            }
	        }
	    }

	    bean.setCurrentPopulations(currentPopulations);
	    return bean;
	}

	

	
	/*
	 * 
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenilePopulationForm jpForm = (JuvenilePopulationForm)aForm;
		//jpForm.clear();
		jpForm.setSearchTypeId("");
		jpForm.setFacilityId("");
		return aMapping.findForward("cancel");
	}


}
