package ui.juvenilecase.populationReport.action;

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

import messaging.administerserviceprovider.GetProgramsByProviderIdEvent;
import messaging.administerserviceprovider.GetServiceProvidersEvent;
import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent;
import messaging.juvenilecase.GetJuvenileFacilityHistoricalReceiptsEvent;
import messaging.programreferral.GetProgramReferralReportEvent;
import messaging.programreferral.ProgramReferralRetrieverAttribute;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileProgramReferralControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.ProgramReferralConstants;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.juvenilecase.populationReport.form.JuvenileFacilityReceiptForm;
import ui.juvenilecase.programreferral.ProgramReferralAttributeFactory;
import ui.juvenilecase.programreferral.form.ProgramReferralForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

public class DisplayProgramReferralSearchAction extends JIMSBaseAction
{
    /**
     * @roseuid 42AF409F01B5
     */
    public DisplayProgramReferralSearchAction()
    {
    }

    /**
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     * @return Map
     */
    protected void addButtonMapping(Map buttonMap)
    {
	buttonMap.put("button.submit", "submitSearch");
	buttonMap.put("button.print", "printReport");
	buttonMap.put("button.link", "link");
	buttonMap.put("button.updateSP", "loadPrograms");
	buttonMap.put("button.cancel", "cancel");
	buttonMap.put("button.refresh", "refresh");
	return;
    }

   /**
    * 
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return
    */
    public ActionForward submitSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	
	ProgramReferralForm form = (ProgramReferralForm) aForm;
	
	List attributeList = new ArrayList();
	
	if(StringUtils.isNotEmpty(form.getSelectedValue())){
	    
	    ProgramReferralRetrieverAttribute spAttribute = ProgramReferralAttributeFactory
			.getServiceProviderAttribute();
		spAttribute.setAttributeValue(form.getSelectedValue()); //sprovider Id
		attributeList.add(spAttribute);

		ProgramReferralRetrieverAttribute programAttribute = null;
		
		if( form.getSelectedPrograms() != null && form.getSelectedPrograms().length > 0){
		   
		    for (String s: form.getSelectedPrograms()) {
			    
			    programAttribute = ProgramReferralAttributeFactory
					.getProgramAttribute();
			    programAttribute.setAttributeValue(s);
			    attributeList.add(programAttribute);
			    
			    System.out.println(s); 
			}
		}
	}
	
	
	if(form.getOfficerLastName() != null && StringUtils.isNotEmpty(form.getOfficerLastName())){
	    
	    ProgramReferralRetrieverAttribute lastNameAttribute = ProgramReferralAttributeFactory
			.getOfficerLastNameProviderAttribute();
	    StringBuffer lName = new StringBuffer(form.getOfficerLastName()).append("%");
		lastNameAttribute.setAttributeValue( lName.toString());
		attributeList.add(lastNameAttribute);
	}
	
	if(form.getOfficerFirstName() != null && StringUtils.isNotEmpty(form.getOfficerFirstName())){
	    
	    ProgramReferralRetrieverAttribute firstNameAttribute = ProgramReferralAttributeFactory
			.getOfficerFirstNameProviderAttribute();
	    StringBuffer fName = new StringBuffer(form.getOfficerFirstName()).append("%");
		firstNameAttribute.setAttributeValue( fName.toString() );
		attributeList.add(firstNameAttribute);
	}
	
	if(form.getOfficerMiddleName() != null && StringUtils.isNotEmpty(form.getOfficerMiddleName())){
	    
	    ProgramReferralRetrieverAttribute middleNameAttribute = ProgramReferralAttributeFactory
			.getOfficerMiddleNameProviderAttribute();
	    StringBuffer mName = new StringBuffer(form.getOfficerMiddleName()).append("%");
		middleNameAttribute.setAttributeValue( mName.toString() );
		attributeList.add(middleNameAttribute);
	}
	

	ProgramReferralRetrieverAttribute statusAttribute1 = ProgramReferralAttributeFactory
			.getStateAttribute();
	statusAttribute1.setAttributeValue(ProgramReferralConstants.ACCEPTED);
	attributeList.add(statusAttribute1);

	/*ProgramReferralRetrieverAttribute statusAttribute2 = ProgramReferralAttributeFactory
		.getStateAttribute();
	statusAttribute2.setAttributeValue(ProgramReferralConstants.TENTATIVE);
	attributeList.add(statusAttribute2);*/ //commented for BUG: 179562 only ACTIVE required in the report
	
	if(form.getSupervisionTypeId() != null && StringUtils.isNotEmpty(form.getSupervisionTypeId())){
	    
	    ProgramReferralRetrieverAttribute sprvisionTypeAttribute = ProgramReferralAttributeFactory
			.getJuvenileSupervisionTypeAttribute();
	    sprvisionTypeAttribute.setAttributeValue( form.getSupervisionTypeId() );
	    attributeList.add( sprvisionTypeAttribute );
	    
	}
	if(form.getLocationId() != null && StringUtils.isNotEmpty(form.getLocationId())){
	    
	    ProgramReferralRetrieverAttribute locationAttribute = ProgramReferralAttributeFactory
			.getJuvenileLocationProviderAttribute();
		locationAttribute.setAttributeValue( form.getLocationId() );
		attributeList.add(locationAttribute);
	}

	GetProgramReferralReportEvent gpre = (GetProgramReferralReportEvent) EventFactory
			.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALREPORT);

	gpre.setReferralAttributes(attributeList);
	gpre.setDetailsNeeded(true);

	List<ProgramReferralResponseEvent> responses = MessageUtil.postRequestListFilter(gpre, ProgramReferralResponseEvent.class);
	
	Collections.sort((List<ProgramReferralResponseEvent>) responses, Collections.reverseOrder(new Comparator<ProgramReferralResponseEvent>() {
	    @Override
	    public int compare(ProgramReferralResponseEvent evt1, ProgramReferralResponseEvent evt2)
	    {
		return Integer.valueOf(evt2.getProvProgramId()).compareTo(Integer.valueOf(evt1.getProvProgramId()));
	    }
	}));
	
	Map<String, String> unique = new HashMap<String, String>();
	Map<String, Integer> groupings = new HashMap<String, Integer>();
	
	Iterator<ProgramReferralResponseEvent> iter = responses.iterator();
	while(iter.hasNext()){
	    
	    ProgramReferralResponseEvent evt = iter.next();
	    unique.put( evt.getProvProgramId(), evt.getProviderProgramName());
	}
	
	int cntr = 0;
	for (String key : unique.keySet()) {
	    //System.out.println("Key = " + key);
		for( ProgramReferralResponseEvent s : responses ) {
		    if( key.equals(s.getProvProgramId()) ) {
		        // increment counter
			cntr ++;
		    }
		   
		}
		 groupings.put(unique.get(key), cntr);
		 cntr =0;
		 // add list of objects to form
	}

	//System.out.println(groupings);
	
	form.setGroupMap(groupings);
	form.setActiveReferralList(responses);
	form.setActiveReferralListSize(responses.size());
	
	return aMapping.findForward(UIConstants.SUCCESS);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    @SuppressWarnings("unchecked")
    public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProgramReferralForm form = (ProgramReferralForm) aForm;
	form.setSelectedValue("");
	form.setSelectedPrograms(null);
	form.setSupervisionTypeId("");
	form.setLocationId("");
	form.setOfficerFirstName("");
	form.setOfficerMiddleName("");
	form.setOfficerLastName("");

	// List spTemp = ComplexCodeTableHelper.getAllServiceProviders();

	GetServiceProvidersEvent getServiceProvidersEvent = (GetServiceProvidersEvent) 
		EventFactory.getInstance(ServiceProviderControllerServiceNames.GETSERVICEPROVIDERS);

	ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	IUserInfo user = securityManager.getIUserInfo();
	getServiceProvidersEvent.setAgencyId(user.getAgencyId());
	getServiceProvidersEvent.setAllServiceProviders(true);
	getServiceProvidersEvent.setStatusId("A");

	CompositeResponse compositeResponse = MessageUtil.postRequest(getServiceProvidersEvent);
	List serviceProviderResponses = MessageUtil.compositeToList(compositeResponse, ServiceProviderResponseEvent.class);
	Collections.sort((List) serviceProviderResponses);

	form.setServiceProviderList(serviceProviderResponses);
	form.setProgramNameList(new ArrayList());
	
	Collection codes = CodeHelper.getActiveCodes(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE,true);
	form.setSupervisionTypes(codes);
	
	List juvLocations = ComplexCodeTableHelper.getActiveJuvenileLocationUnits();
	
	form.setJuvLocations( juvLocations );

	return aMapping.findForward(UIConstants.LIST_SUCCESS);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward printReport(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProgramReferralForm form = (ProgramReferralForm) aForm;
	
	aRequest.getSession().setAttribute("reportInfo", form);
	BFOPdfManager pdfManager = BFOPdfManager.getInstance();
	//let the pdfManager know that the report should be saved in the request during report creation
	aRequest.setAttribute("isPdfSaveNeeded", "false");
	// remove the pdf report attributes from session when finished saving to database
	aRequest.removeAttribute("isPdfSaveNeeded");
	aRequest.removeAttribute("pdfSavedReport");

	pdfManager.createPDFReport(aRequest, aResponse, PDFReport.PROGRAM_REFERRAL_STATISTICAL_SUMMARY);

	return null;
    }

    /**
     * @param receiptsForm
     * @return
     */
    private CompositeResponse retrieveHistoricalReceipts(JuvenileFacilityReceiptForm receiptsForm)
    {
	GetJuvenileFacilityHistoricalReceiptsEvent receiptEvent = new GetJuvenileFacilityHistoricalReceiptsEvent();
	// set search values and post request 
	receiptEvent.setJuvenileNum(receiptsForm.getJuvenileNumber());
	receiptEvent.setFacilityCode(receiptsForm.getFacilityId());
	CompositeResponse response = (CompositeResponse) MessageUtil.postRequest(receiptEvent);
	return response;
    }

    
   
    /*
     * 
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProgramReferralForm form = (ProgramReferralForm) aForm;
	form.clearAll();
	return aMapping.findForward("cancel");
    }
    
    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProgramReferralForm form = (ProgramReferralForm) aForm;
	form.clearAll();
	return aMapping.findForward(UIConstants.LIST_SUCCESS);
    }
    
    public ActionForward loadPrograms(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProgramReferralForm form = (ProgramReferralForm) aForm;
	
	GetProgramsByProviderIdEvent getProgramNames = (GetProgramsByProviderIdEvent) 
		EventFactory.getInstance(ServiceProviderControllerServiceNames.GETPROGRAMSBYPROVIDERID);

	getProgramNames.setJuvServiceProviderId(form.getSelectedValue());

	CompositeResponse compResponse = MessageUtil.postRequest(getProgramNames);
	List programs = MessageUtil.compositeToList(compResponse, ProviderProgramResponseEvent.class);
	for (Iterator<ProviderProgramResponseEvent> iter = programs.iterator(); iter.hasNext();) {

	    ProviderProgramResponseEvent obj = iter.next();
	    if( !"A".equals( obj.getProgramStatusId())){
		
		iter.remove();
	    }
	}
    	
	Collections.sort((List) programs);
	form.setProgramNameList(programs);
	
	return aMapping.findForward(UIConstants.LIST_SUCCESS);
    }

}
