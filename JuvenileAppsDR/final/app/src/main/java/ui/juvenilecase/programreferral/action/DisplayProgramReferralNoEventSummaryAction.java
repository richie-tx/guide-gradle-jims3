package ui.juvenilecase.programreferral.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import naming.JuvenileCaseControllerServiceNames;
import naming.ProgramReferralConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.supervision.administerserviceprovider.ProviderProgram;
import pd.supervision.administerserviceprovider.SearchServiceProvider;
import pd.supervision.programreferral.JuvenileProgramReferral;
import messaging.productionsupport.RetrieveJuvPgmRefsByProvPgmIdEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.MessageUtil;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.juvenilecase.programreferral.form.ProgramReferralForm;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;

public class DisplayProgramReferralNoEventSummaryAction extends JIMSBaseAction{
	/**
	 * @roseuid 463BA574000E
	 */
	public DisplayProgramReferralNoEventSummaryAction() {

	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward next( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse ) throws GeneralFeedbackMessageException
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm;
		ActionForward forward = aMapping.findForward( UIConstants.SUCCESS ) ;
		String selectedSPVendorId = "";
		//NEW US 190589 MAX YOUTH
		ProviderProgram selectedProgram = ProviderProgram.find(form.getProgramReferral().getProviderProgramId()); 
		String selectedProgramId = form.getProgramReferral().getProviderProgramId();
		int maxYouthInProgram = 0;
		int maxYouthForSP = 0;
		int juvCount = 0;
		/*Added for bug no 45875*/
		JuvenileServiceProvider juvServiceProvider = JuvenileServiceProvider.find(Integer.valueOf(form.getProgramReferral().getJuvServiceProviderId()));
		if(juvServiceProvider.getInHouse()){
		    form.getProgramReferral().setReferralStatus("ACCEPTED");
		}else{
		    form.getProgramReferral().setReferralStatus("TENTATIVE");
		}
		/*Added for bug no 45875*/	
		form.setAction(UIConstants.SUMMARY);
		form.setSendEmailToContacts( juvServiceProvider.isEmailCheck() );
		
		// US 177340 - Code for MAX YOUTH STARTS 
		JuvenileCasefileForm casefileForm = UIJuvenileHelper.getJuvenileCasefileForm( aRequest ) ;
		String juvNum = casefileForm.getJuvenileNum();
		Set<Integer> distinctJuvenileIds = new HashSet<>(); // Set to hold distinct juvenile IDs
		
		selectedSPVendorId = form.getProgramReferral().getJuvServiceProviderId();
		//get all the providerProgramIds for selectedServProvider/selectedSPVendorId
		Collection spList = form.getServiceProviderList();

		ServiceProviderResponseEvent selectedSP = new ServiceProviderResponseEvent();
		Iterator servProviderItr = spList.iterator();
		while (servProviderItr.hasNext())
		{
		    ServiceProviderResponseEvent sp = (ServiceProviderResponseEvent) servProviderItr.next();
		    if (sp.getJuvServProviderId().equalsIgnoreCase(selectedSPVendorId))
		    {
			selectedSP = sp;
			break;
		    }
		}
		
		//get all the ACTIVE programs for the selected vendor Service Provider Name
		List<ServiceResponseEvent> activeProgramsList = (List<ServiceResponseEvent>) selectedSP.getServiceResponseEvents(); 
		
		for (ServiceResponseEvent program : activeProgramsList)
		{
		    RetrieveJuvPgmRefsByProvPgmIdEvent getJuvProgramReferralEvent = new RetrieveJuvPgmRefsByProvPgmIdEvent();
		    getJuvProgramReferralEvent.setProvProgramId(program.getProgramId());
		    ArrayList<JuvenileProgramReferral> juvenileProgramReferralList = (ArrayList) CollectionUtil.iteratorToList(JuvenileProgramReferral.findAll(getJuvProgramReferralEvent));
		    // call above gets data FROM JIMS2.CSJUVPROGREF WHERE PROVPROGRAM_ID = program.getProgramId() 
		    Iterator juvPgrmRefItr = juvenileProgramReferralList.iterator();
		    while (juvPgrmRefItr.hasNext())
		    {
			JuvenileProgramReferral jpr = (JuvenileProgramReferral) juvPgrmRefItr.next();
			if (jpr.getEndDate() == null) //
			{
			    String casefileID = jpr.getCasefileId();
			    GetJuvenileCasefileEvent getCasefileEvent = (GetJuvenileCasefileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILE);
			    getCasefileEvent.setSupervisionNumber(casefileID); //call to JCCASEFILE
			    CompositeResponse response = MessageUtil.postRequest(getCasefileEvent);
			    JuvenileCasefileResponseEvent casefile = (JuvenileCasefileResponseEvent) MessageUtil.filterComposite(response, JuvenileCasefileResponseEvent.class);
			    //Get all the juveniles across all services under the selected Program/SP
			    if (program.getServiceProviderId() != null && program.getServiceProviderId().equalsIgnoreCase(juvServiceProvider.getOID()) && program.getProgramEndDate() == null)
			    {
				distinctJuvenileIds.add(Integer.parseInt(casefile.getJuvenileNum()));
			    }
			    if (program.getProgramId() != null && selectedProgramId.equalsIgnoreCase(program.getProgramId()))
			    {
				juvCount++;
			    }
			}
		    }
		}
		//get the MAX YOUTH number for the selected vendor from the table: CSJUVSERVPROV
		if (juvServiceProvider.getMaxYouth() != null)
        	{
        	    maxYouthForSP = Integer.parseInt(juvServiceProvider.getMaxYouth());
        	}
		//get the MAX YOUTH number for the selected PROGRAM from the table: [T_CSPROVPROGRAM]
        	if (selectedProgram.getMaxYouth() != null)
        	{
        	    maxYouthInProgram = Integer.parseInt(selectedProgram.getMaxYouth());
        	}
        	if (maxYouthInProgram > 0 && juvCount >= maxYouthInProgram)
        	{
        	    form.getProgramReferral().setJuvServiceProviderId("");
        	    ActionErrors errors = new ActionErrors();
        	    String msg = "Maximum Youth Limit Exceeded for this Program";
        	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", msg));
        	    saveErrors(aRequest, errors);
        	    forward = aMapping.findForward(UIConstants.FAILURE);
        	    return forward;
        	}
        	if (maxYouthForSP > 0 && distinctJuvenileIds.size() >= maxYouthForSP) //check if the total UNIQUE juv count is less than the MAXYOUTH number 
        	{
        	    //check if the juvenileNum is already in the set, if NO, go forward with addition of program ref
        	    if (!distinctJuvenileIds.contains(Integer.parseInt(juvNum)))
        	    {
        		form.getProgramReferral().setJuvServiceProviderId("");
        		ActionErrors errors = new ActionErrors();
        		String msg = "Maximum Youth Limit Exceeded for this Service Provider";
        		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", msg));
        		saveErrors(aRequest, errors);
        		forward = aMapping.findForward(UIConstants.FAILURE);
        		return forward;
        	    }
        	}
	
        	 //US 177340 US 190589 - Code for MAX YOUTH ENDS
		
// check for Active Referrals on all supervisions for current juvenile with Service Provider and Program name 
// matching the selected corresponding values. If match found send error to create page.
		JuvenileCasefileForm jcForm = (JuvenileCasefileForm) getSessionForm(aMapping, aRequest, "juvenileCasefileForm", true);
		String selectedSPId = form.getProgramReferral().getJuvServiceProviderId();
		String selectedPgmpId = form.getProgramReferral().getProviderProgramId();
		
		// get the schedule Type Id using the poviderProgramId.
		Iterator<SearchServiceProvider> serviceProvIter = SearchServiceProvider.findAll("programId", String.valueOf(selectedPgmpId));
		
		
		//prevent the creation of a program referral if the programScheduleTypeId for the program is "C" - calendar - US 170991
		Collection programs = UIServiceProviderHelper.getPrograms(selectedSPId);
		Iterator programsIter = programs.iterator();
		
		while(programsIter.hasNext()){
		    
		    ProviderProgramResponseEvent pg = (ProviderProgramResponseEvent)programsIter.next();
		    
		    if(pg.getProviderProgramId() != null && !"".equals(pg.getProviderProgramId())){
			
			if(pg.getProviderProgramId().equalsIgnoreCase(selectedPgmpId)){
			    String programSchedTypeId = pg.getProgramScheduleTypeId();
			    
			    if(programSchedTypeId != null && programSchedTypeId.equalsIgnoreCase("C")){
				
				form.getProgramReferral().setJuvServiceProviderId("");
				ActionErrors errors = new ActionErrors();
				String msg = "Program requires scheduling and program referral Creation in the calendar tab.";
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",msg));
				saveErrors(aRequest, errors);
				forward = aMapping.findForward(UIConstants.FAILURE);
				
				return forward;
				
			    }
			    
			    break;
			    
			}
		    }
		    
		}		
		
		List<ProgramReferralResponseEvent> referrals = UIProgramReferralHelper.getJuvenileProgramReferrals(jcForm.getJuvenileNum());	
		if(referrals != null && !referrals.isEmpty())
		{
			for(int i=0;i<referrals.size();i++)
			{
  				ProgramReferralResponseEvent resp = (ProgramReferralResponseEvent) referrals.get(i);
  				//added for U.S #11099
  				while(serviceProvIter.hasNext()){
  					SearchServiceProvider serviceProvider = (SearchServiceProvider) serviceProvIter.next();
  					if(serviceProvider.getProgramScheduleTypeId()!=null){
  						resp.setProgramScheduleTypeId(serviceProvider.getProgramScheduleTypeId());
  						if( resp.getProgramScheduleTypeId().equals("C")){
          						form.getProgramReferral().setJuvServiceProviderId("");
  	  						ActionErrors errors = new ActionErrors();
  							String msg = "Program requires scheduling and program referral Creation in the calendar tab.";
  							errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",msg));
  							saveErrors(aRequest, errors);
  							forward = aMapping.findForward(UIConstants.FAILURE);
  							break;
  						}
  					}
  				}
  				//added for U.S #11099
  				if(resp!=null && !ProgramReferralConstants.CLOSED.equals(resp.getReferralStatusCd()))
  				{
  					if (selectedSPId!=null && selectedPgmpId!=null &&  selectedSPId.equals(resp.getJuvServiceProviderId() ) && selectedPgmpId.equals(resp.getProvProgramId()) )
  					{
      					       form.getProgramReferral().setJuvServiceProviderId("");
  						ActionErrors errors = new ActionErrors();
  						String msg = "An Active Referral already exist for this Service Provider and Program on supervision " + resp.getCasefileId();
  						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",msg));
  						saveErrors(aRequest, errors);
  						forward = aMapping.findForward(UIConstants.FAILURE);
  						break;
  					}
  				}
 			}
		}
		return forward;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return( aMapping.findForward( UIConstants.CANCEL ) ) ;

	}
	
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put( "button.next", "next" ) ;
		keyMap.put( "button.cancel", "cancel" ) ;
	}
}