package pd.juvenilecase.transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import messaging.info.reply.CountInfoMessage;
import messaging.juvenile.SearchJuvenileProfilesEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetJuvenileCaseLoadByOfficerEvent;
import messaging.juvenilecase.reply.JuvenileCaseLoadRepsonseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.PDJuvenileCaseConstants;

import org.apache.commons.lang.StringUtils;

import pd.juvenile.Juvenile;
import pd.juvenilecase.JuvenileCaseLoadHelper;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.JuvenileCaseload;

public class GetJuvenileCaseLoadByOfficerCommand implements ICommand{
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetJuvenileCaseLoadByOfficerCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event)
	{
		GetJuvenileCaseLoadByOfficerEvent officerEvent = (GetJuvenileCaseLoadByOfficerEvent) event;
		Iterator files = JuvenileCasefile.findAll(officerEvent);
		ArrayList casefiles = new ArrayList<JuvenileCasefile>();
		HashMap caseloads = new HashMap();
		HashMap activeJuveniles = new HashMap();
		int juvenilesCount = 0;
		int casefilesCount = 0;
		String stDate = officerEvent.getCasefileActivationStDate();
		String endDate = officerEvent.getCasefileActivationEndDate();
		String expStDate = officerEvent.getCasefileExpectedStartDate();
		String expEndDate = officerEvent.getCasefileExpectedEndDate();
		String activationDate = "";
		String expectedEndDate = "";
		Date startDte = null;
		Date endDte = null;
		while (files != null && files.hasNext())
		{
			JuvenileCasefile casefile = (JuvenileCasefile) files.next();
			casefiles.add(casefile);
			boolean actDateGood = false;
			boolean expDateGood = false;
			if(officerEvent.getCaseStatusCd().equalsIgnoreCase(casefile.getCaseStatusId())){
				//<KISHORE>JIMS200060469 : MJCW CF: Add Caseload Search date range(UI)-KK
				// Check Activation Date if present and part of parameters
				if( StringUtils.isNotEmpty(stDate)) {
					activationDate = DateUtil.dateToString(casefile.getActivationDate(), DateUtil.DATE_FMT_1);
					if(StringUtils.isNotEmpty(activationDate) ) {
						if (StringUtils.isNotEmpty(endDate)){
							startDte = DateUtil.stringToDate(officerEvent.getCasefileActivationStDate(), DateUtil.DATE_FMT_1);
							endDte = DateUtil.stringToDate(officerEvent.getCasefileActivationEndDate(), DateUtil.DATE_FMT_1);
							if(DateUtil.compare(casefile.getActivationDate(), startDte, DateUtil.DATE_FMT_1) >= 0 && 
								DateUtil.compare(casefile.getActivationDate(), endDte, DateUtil.DATE_FMT_1) <= 0){
								actDateGood = true;
							}
						}else if(activationDate.equalsIgnoreCase(stDate)){
								actDateGood = true;
						}
					} else {
						actDateGood = false;
					}
				} else {
					actDateGood = true;
				}
				// Check Expected End Date if present and part of parameters 
				if( actDateGood && StringUtils.isNotEmpty(expStDate) ) {
					expectedEndDate = DateUtil.dateToString(casefile.getSupervisionEndDate(), DateUtil.DATE_FMT_1);
					if(StringUtils.isNotEmpty(expectedEndDate) ) {
						if (StringUtils.isNotEmpty(expEndDate)){
							startDte = DateUtil.stringToDate(officerEvent.getCasefileExpectedStartDate(), DateUtil.DATE_FMT_1);
							endDte = DateUtil.stringToDate(officerEvent.getCasefileExpectedEndDate(), DateUtil.DATE_FMT_1);
							if (DateUtil.compare(casefile.getSupervisionEndDate(), startDte, DateUtil.DATE_FMT_1) >= 0 && 
								DateUtil.compare(casefile.getSupervisionEndDate(), endDte, DateUtil.DATE_FMT_1) <= 0){
								expDateGood = true;
							}
						}else if(expectedEndDate.equalsIgnoreCase(expStDate)){
							expDateGood = true;
						}	
					} else {
						expDateGood = false;  // empty supervision end date should not display
					}
				} else{
					expDateGood = true;
				}
				
				if (actDateGood && expDateGood) {
					JuvenileCaseLoadHelper.buildCaseloadResponses(casefile,caseloads);
				}
			}
			if(!PDJuvenileCaseConstants.CASE_STATUS_CLOSE.equalsIgnoreCase(casefile.getCaseStatusId()) && 
				!PDJuvenileCaseConstants.CASESTATUS_PENDING.equalsIgnoreCase(casefile.getCaseStatusId()) ){
				casefilesCount++;
				if(activeJuveniles.isEmpty() || !activeJuveniles.containsKey(casefile.getJuvenileNum())){
					activeJuveniles.put(casefile.getJuvenileNum(), true);
					juvenilesCount++;
				}
			}
		}
		activationDate = null;
		expectedEndDate = null;
		startDte = null;
		endDte = null;
		if(caseloads.keySet().size() > 2000){
	      	CountInfoMessage infoEvent = new CountInfoMessage();
        	infoEvent.setCount(caseloads.keySet().size());  
        	MessageUtil.postReply(infoEvent);
		}else{
			Iterator juveniles = caseloads.keySet().iterator();
			while(juveniles.hasNext()){
				JuvenileCaseLoadRepsonseEvent response= (JuvenileCaseLoadRepsonseEvent)caseloads.get(juveniles.next());
				// If a juvenile has no casefiles matching to the selected case status
				// then that juvenile record should not be displayed on the UI
				if(response.getCasefileAssignments() != null && !response.getCasefileAssignments().isEmpty()){
					JuvenileCaseload juv = JuvenileCaseload.find(response.getJuvenileNum());;
					if(juv != null){
						response.setJuvenileFirstName(juv.getFirstName());
						response.setJuvenileMiddleName(juv.getMiddleName());
						response.setJuvenileLastName(juv.getLastName());
						response.setJuvenileNameSuffix(juv.getNameSuffix());						
						if(StringUtils.isNotEmpty(juv.getFacilityStatus())){
							response.setDetentionStatus(juv.getFacilityStatus());
							//If there is a status then the juvenile is still in the facility.  
							//If there is no status don�t display the facility
							if(StringUtils.isNotEmpty(juv.getFacilityLocation())){
								response.setDetentionFacility(juv.getFacilityLocation());
							}
						}
						if(response.getJuvenileNum()!=null && !response.getJuvenileNum().isEmpty())
						{
        						JuvenileProfileDetailResponseEvent res = null;
        						SearchJuvenileProfilesEvent pEvent = new SearchJuvenileProfilesEvent();
        						pEvent.setJuvenileNum(response.getJuvenileNum());
        						
        						Iterator iter = Juvenile.findAll(pEvent);
        						Juvenile juvenile=null;
        						if (iter != null && iter.hasNext()) 
        						{
        						    	juvenile = (Juvenile) iter.next();
        						    	res = juvenile.getCoreJuvenileProfileResponse();
        						}
        						response.setJuvRectype(res.getRecType());
						}
						//
					} 
					// Each response event will actually carry the total casefiles & juveniles that are active for the selected JPO
					response.setActiveCasefilesCount(casefilesCount);
					response.setActiveJuvenilesCount(juvenilesCount);
					response.setMasterStatus(JuvenileCaseLoadHelper.getJuvenileMasterStatus(response.getJuvenileNum(),casefiles));
					MessageUtil.postReply(response);
				}
			}
		}
	}
	

	public void onRegister(IEvent event)
	{

	}

	public void onUnregister(IEvent event)
	{

	}

	public void update(Object anObject)
	{

	}
}
