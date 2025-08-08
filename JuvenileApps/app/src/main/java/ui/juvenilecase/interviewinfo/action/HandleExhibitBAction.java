package ui.juvenilecase.interviewinfo.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import messaging.administerserviceprovider.GetProgramServicesEvent;
import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.productionsupport.GetProductionSupportCasefilesByJuvEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileWarrantControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;
import net.minidev.json.JSONObject;
import pd.juvenilecase.Assignment;
import pd.juvenilecase.interviewinfo.InterviewDocument;
import ui.action.JIMSBaseAction;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.interviewinfo.form.ExhibitBForm;
import ui.juvenilecase.interviewinfo.form.ProgramService;
import ui.juvenilecase.programreferral.ExhibitBPrintBean;
import ui.util.BFOPdfManagerTest;
import ui.util.PDFReport;

public class HandleExhibitBAction extends JIMSBaseAction
{
    private final String PETITIONS_TAB = "petitions" ;
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.exhibitB", "exhibitB");
	keyMap.put("button.next", "next");
	keyMap.put("button.print", "print");
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.back", "back");
    }
    
    public ActionForward exhibitB (ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ExhibitBForm form = (ExhibitBForm)aForm;
	form.clearAllResults();
	List<PetitionResponseEvent>filteredPetitionList = new ArrayList<>();
	List<PetitionResponseEvent>petitionList = new ArrayList<>();
	form.setCurrentTab( "petitions" );
	JuvenileCasefileForm casefileForm =
        	(JuvenileCasefileForm) aRequest.getSession().getAttribute("juvenileCasefileForm");
	String juvenileNum = casefileForm.getJuvenileNum();
	form.setJuvenileNum(casefileForm.getJuvenileNum());
	List <Assignment> assignmentList = CollectionUtil.iteratorToList(Assignment.findAll("caseFileId", casefileForm.getSupervisionNum()));
	List<String> assignmentReferrals = new ArrayList<>();
	
	if ( assignmentList!= null 
		&& assignmentList.size() > 0 ){
        	for ( Assignment assignment : assignmentList ) {
        	    if ( !assignmentReferrals.contains(assignment.getReferralNumber()) ) {
        		assignmentReferrals.add(assignment.getReferralNumber());
        	    }
        	}
        	
        	
        	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        	for (String referral : assignmentReferrals ) {
        	    GetJJSPetitionsEvent petitionEvent = (GetJJSPetitionsEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.GETJJSPETITIONS);
        	    petitionEvent.setJuvenileNum(juvenileNum);
        	    petitionEvent.setReferralNum(referral);
        	    dispatch.postEvent(petitionEvent);
        	    CompositeResponse compositeResp = MessageUtil.postRequest(petitionEvent);
        	    List<PetitionResponseEvent> petResponses = MessageUtil.compositeToList(compositeResp, PetitionResponseEvent.class);
        	    if ( petResponses != null
        		    && petResponses.size() > 0 ) {
        		Collections.sort( petResponses , new Comparator<PetitionResponseEvent>(){
        		    public int compare(PetitionResponseEvent p1, PetitionResponseEvent p2){
        			return p2.getSequenceNum().compareTo( p1.getSequenceNum() );
        		    }
        		});
        		
        	    }
        	    
        	    if (petResponses!= null 
        		    && petResponses.size() > 0  ) {
        		petitionList.add(petResponses.get(0));
        	    }
        	}
	
	}
	
	if ( petitionList != null
		&& petitionList.size() > 0) {
	    for (PetitionResponseEvent petition : petitionList) {
		
		String juvenileNumber = petition.getJuvenileNum();
		String referralNumber = petition.getReferralNum();
		String petitionNumber = petition.getPetitionNum();
		
		JuvenileProfileReferralListResponseEvent referralRsp = JuvenileFacilityHelper.getJuvReferralDetails(juvenileNumber, referralNumber);
		
		if (referralRsp != null
			&& referralRsp.getCloseDate() == null ){
		    
		    petition.setReferralDate(referralRsp.getReferralDate());
		    
		    GetJJSCourtEvent courtEvt = (GetJJSCourtEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSCOURT);
        	    courtEvt.setJuvenileNumber( juvenileNumber );
        	    courtEvt.setReferralNumber( referralNumber );
        	 
        	    CompositeResponse response = MessageUtil.postRequest(courtEvt);
        	    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(response, DocketEventResponseEvent.class);
        	   
        	    if (docketResponses != null
        		    && docketResponses.size() > 0 ) {
        		Collections.sort(docketResponses, new Comparator<DocketEventResponseEvent>(){
        		    public int compare(DocketEventResponseEvent c1, DocketEventResponseEvent c2){
        			return c2.getSeqNum().compareTo(c1.getSeqNum() );
        		    }
        		});
        		
        		petition.setCourtDate(docketResponses.get(0).getFilingDate());
        		petition.setCourtId(docketResponses.get(0).getCourt());
        	    }
        	    if (petition.getPetitionStatusDescription() != null
        		    && petition.getPetitionStatusDescription().length() > 0  ){
        		petition.setPetitionStatusDescription(capitalize(petition.getPetitionStatusDescription()));
        	    }
        	    
        	    if (petition.getAmend() != null
        		    && petition.getAmend().length() > 0 ){
        		petition.setPetitionAmendment( "-" + ordinal(Integer.parseInt(petition.getAmend())) );
        	    } else {
        		petition.setPetitionAmendment("-");
        	    }
        	    
        	    filteredPetitionList.add(petition);
		}
		
        	   
	    }
	

	    form.setPetitions(filteredPetitionList);
	    
	}

	return( aMapping.findForward("petitionSuccess") );
    }
    
    public ActionForward next (ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ExhibitBForm form = (ExhibitBForm)aForm;
	String currentTab = form.getCurrentTab();
	ArrayList<ProgramReferralResponseEvent> programServicesList = new ArrayList<ProgramReferralResponseEvent>();
	JuvenileCasefileForm casefileForm =
        	(JuvenileCasefileForm) aRequest.getSession().getAttribute("juvenileCasefileForm");
	if ("programServices".equals( currentTab )) {
	 
	   
	   String [] referrals = ( String[] ) aRequest.getParameterValues("selectedReferral");
	   if ( referrals != null 
		   && referrals.length > 0 ) {
	       	List<PetitionResponseEvent>filteredPetitionList = new ArrayList<>();
    	    	List<PetitionResponseEvent>petitions = form.getPetitions();
    	    	for (String referral : referrals ){
    	    	    for ( PetitionResponseEvent petition : petitions ) {
    	    		if ( referral.equals(petition.getReferralNum()) ){
    	    		    filteredPetitionList.add(petition);
    	    		}
    	    	    }
    	    	}
    	    
    	    	form.setPetitions(filteredPetitionList);
    	    	form.setSelectedReferrals(Arrays.asList(referrals));
	   } else {
	       	form.setPetitions( new ArrayList<PetitionResponseEvent>() );
   	    	form.setSelectedReferrals( new ArrayList<String>() );
	   }
	   // String casefileId = casefileForm.getSupervisionNum();
	    
	    ArrayList<JuvenileCasefileResponseEvent>casefiles = new ArrayList<>();
	    List<ProgramService> programServiceList = new ArrayList<>();
	    ProgramService programService = new ProgramService();
	    programServiceList.add(programService);

	    form.setProgramServiceList(programServiceList);
	    GetProductionSupportCasefilesByJuvEvent casefilesEvent = (GetProductionSupportCasefilesByJuvEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCASEFILESBYJUV);
	    casefilesEvent.setJuvenileNumber(form.getJuvenileNum());
	    CompositeResponse casefilesResp = MessageUtil.postRequest( casefilesEvent );
	    casefiles = (ArrayList)  MessageUtil.compositeToCollection(casefilesResp, JuvenileCasefileResponseEvent.class );
	    
	    if ( casefiles != null
		    && casefiles.size() > 0) {
		for (JuvenileCasefileResponseEvent casefile : casefiles ) {
		    
		    GetProgramServicesEvent getProgramServiceEvent = (GetProgramServicesEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETPROGRAMSERVICES);
		    getProgramServiceEvent.setCasefileId( casefile.getSupervisionNum()  );
		    CompositeResponse programServicesResp = MessageUtil.postRequest( getProgramServiceEvent );
		    Collection<ProgramReferralResponseEvent>responses =  MessageUtil.compositeToCollection(programServicesResp, ProgramReferralResponseEvent.class );
		    if (responses != null
			    && responses.size() > 0 ) {
			programServicesList.addAll(responses);
		    }
		}
	    }
	    
	    if ( programServicesList != null
		    && programServicesList.size() > 0 ) {
		form.setProgramServices(programServicesList);
	    }
	    
	    return( aMapping.findForward("programServicesSuccess") );
	    
	} else if ( "circumstances".equals( currentTab ) ){
	    int programSize = form.getProgramServices().size();
	    List<String>selectedProgramServices = new ArrayList<>();
	    HashMap<String, Integer> programs = new HashMap<>();
	    HashMap<String, Integer> services = new HashMap<>();
	    List<ProgramService> psUserInputs = new ArrayList<>();
	    
	    
	    for(int i = 0; i<programSize; i++){
		String programSerivce = aRequest.getParameter("programService_"+i);
		
		if ( programSerivce != null
			&& programSerivce.charAt(0)== 'P' ){
		    String programName = programSerivce.substring(2, programSerivce.length());
		    
		    if ( programs.size() > 0
			    && programs.containsKey(programName) ){
			int duplicates = programs.get(programName) +1;
			programs.put(programName, duplicates);
		    }
		    
		    if ( !programs.containsKey(programName) ){
			programs.put(programName, 1);
		    }
		    
		}
		
		if ( programSerivce != null
			&& programSerivce.charAt(0)== 'S' ){
		    String serviceName = programSerivce.substring(2, programSerivce.length());
		    if ( services.size() > 0
			    && services.containsKey(serviceName) ){
			int duplicates = services.get(serviceName) +1;
			services.put(serviceName, duplicates );
		    }
		    
		    if ( !services.containsKey(serviceName) ){
			services.put(serviceName, 1);
		    }
		    
		}
	    }
	    
	    
	    String [] userInputPrograms= aRequest.getParameterValues("program");
	    //String [] userInputServices= aRequest.getParameterValues("service");
	   // String [] userInputBeginDates= aRequest.getParameterValues("beginDate");
	   // String [] userInputEndDates= aRequest.getParameterValues("endDate");
	    
	    for (int i =1; i < userInputPrograms.length; i++ ) {
		String programService = aRequest.getParameter(Integer.toString(i));
		if ( "P".equals(programService) ) {
		    
		    if ( programs.size() > 0
			    && programs.containsKey( userInputPrograms[i-1]) ){
			
			int duplicates = programs.get(userInputPrograms[i-1]) +1;
			programs.put(userInputPrograms[i-1], duplicates);
		    }
		    
		    if ( !programs.containsKey(userInputPrograms[i-1]) ){
			programs.put(userInputPrograms[i-1], 1);
		    }
		}
		
		if ( "S".equals(programService) ) {
		    
		    if ( services.size() > 0
			    && services.containsKey(userInputPrograms[i-1]) ){
			
			int duplicates = services.get(userInputPrograms[i-1]) +1;
			services.put(userInputPrograms[i-1], duplicates );
		    }
		    
		    if ( !services.containsKey(userInputPrograms[i-1]) ){
			services.put(userInputPrograms[i-1], 1);
		    }
		    
		}
		
		//ProgramService ps = new ProgramService();
		//ps.setSelectedProgramService(programService);
		//ps.setProgram(userInputPrograms[i-1]);
		//ps.setService(userInputServices[i-1]);
		//ps.setBeginDate(userInputBeginDates[i-1]);
		//ps.setEndDate(userInputEndDates[i-1]);
		//psUserInputs.add(ps);
	    }
	    
	    form.setPrograms(programs);
	    form.setServices(services);
	    return( aMapping.findForward("circumstancesSuccess") );
	} else {
	    return( aMapping.findForward("petitionSuccess") );
	}
    }
    
    public ActionForward print (ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
    {
	ExhibitBForm form = (ExhibitBForm)aForm;
	JuvenileCasefileForm casefileForm =
        	(JuvenileCasefileForm) aRequest.getSession().getAttribute("juvenileCasefileForm");
		
	ExhibitBPrintBean printBean = new ExhibitBPrintBean();
	
	printBean.setJuvenileName(casefileForm.getJuvenileFullName());
	printBean.setListServices( replaceSpecialCharacters( form.getFirstCircQuestion()) );
	printBean.setChildRemoval( replaceSpecialCharacters( form.getSecondCircQuestion()) );
	printBean.setListAndExplain( replaceSpecialCharacters( form.getThirdCircQuestion()) );
	printBean.setPrograms(form.getPrograms());
	printBean.setServices(form.getServices());
	
	StringBuffer selectedPetitions = new StringBuffer();
	if (form.getPetitions().size() > 0){
	    printBean.setCourtId( form.getPetitions().get(0).getCourtId() );
	    printBean.setPetitions(form.getPetitions());
	    int size = 0;
	    for(PetitionResponseEvent petition : form.getPetitions() ){
		size++;
		selectedPetitions.append(petition.getPetitionNum() + " " + petition.getPetitionAmendment() + " " + petition.getPetitionStatusDescription() );
		if (size > 0 && size < form.getPetitions().size() ) {
		    selectedPetitions.append(",  ");
		}
	    }
	    
	}
	printBean.setSelectedPetitions(selectedPetitions.toString());
	StringBuffer selectedPrograms = new StringBuffer();
	if (form.getPrograms().size() > 0){
	    int size=0;
	    for (Map.Entry<String, Integer> program : form.getPrograms().entrySet()) {
		    size++;
	            String key = program.getKey();
	            Integer value = program.getValue();
	            selectedPrograms.append(key);
	            if ( value > 1 ) {
	        	selectedPrograms.append( " ( "+  value + " Referrals )");
	            }
	            if (size >= 0
	        	    && size < form.getPrograms().size() ) {
	        	 selectedPrograms.append(";   ");
	            }
	        }
	    
	    printBean.setSelectedPrograms(selectedPrograms.toString());
	}
	
	StringBuffer selectedServices = new StringBuffer();
	if (form.getServices().size() > 0){
	    int size=0;
	    for (Map.Entry<String, Integer> service : form.getServices().entrySet()) {
		    size++;
	            String key = service.getKey();
	            Integer value = service.getValue();
	            selectedServices.append(key);
	            if ( value > 1 ) {
	        	selectedServices.append( " ( " + value + " Referrals )");
	            }
	            if (size >= 0
	        	    && size < form.getServices().size() ) {
	        	selectedServices.append(";   ");
	            }
	        }
	    
	    printBean.setSelectedServices(selectedServices.toString());
	}
	
	
	
	
	aRequest.getSession().setAttribute("reportInfo", printBean);
	try {
	    	BFOPdfManagerTest pdfManager = BFOPdfManagerTest.getInstance();
        	aRequest.setAttribute("isPdfSaveNeeded", "true");
        	pdfManager.createPDFReport(aRequest, aResponse, PDFReport.EXHIBITB_REPORT);
        	byte[] pdfDocument = (byte[]) aRequest.getAttribute("pdfSavedReport");
        	if(pdfDocument!=null){
        		// persist a copy of the BFO pdf document		
        		//SaveInterviewDocumentEvent saveEvt = new SaveInterviewDocumentEvent();
        		//saveEvt.setCasefileId( casefileForm.getSupervisionNum() );
        		//saveEvt.setDocument(pdfDocument);
        		//saveEvt.setDocumentTypeCodeId("EXH");
        		// CODE_TABLE_NAME = INTERVIEW_DOCTYPE);
        
        		//CompositeResponse response = MessageUtil.postRequest(saveEvt);
        		
        		InterviewDocument document = new InterviewDocument();
        		document.setDocument( pdfDocument );
        		document.setCasefileId( casefileForm.getSupervisionNum()  );
        		document.setDocumentTypeCodeId("EXH");	
        		new Home().bind(document);
        		
        		aRequest.removeAttribute("pdfSavedReport");
        		aRequest.removeAttribute("isPdfSaveNeeded");
        	}
	} catch (Exception e){
	    JSONObject error = new JSONObject();
	    error.put("error", e.toString());
	    aResponse.getWriter().println(error);
	}
	return null;
    }
    
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return( aMapping.findForward(UIConstants.CANCEL) );
    }
    
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return( aMapping.findForward(UIConstants.BACK) );
    }
    
    String capitalize(String str) {

	      if (str == null || str.length() == 0) return str;

	      return str.substring(0, 1).toUpperCase() + str.substring(1);

    }
    
    String ordinal(int i) {
	    String[] suffixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
	    switch (i % 100) {
	    case 11:
	    case 12:
	    case 13:
	        return i + "th";
	    default:
	        return i + suffixes[i % 10];

	    }
	}
    
    String replaceSpecialCharacters(String value){
	return value.replaceAll("&", "&amp;")
			.replaceAll("&apos;\'", "&apos;")
			.replaceAll("&quot;\"", "&quot;");
			
    }	

		
}
