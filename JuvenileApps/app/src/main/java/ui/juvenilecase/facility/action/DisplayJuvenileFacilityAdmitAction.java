package ui.juvenilecase.facility.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;
import org.ujac.util.BeanComparator;

import messaging.codetable.GetJuvenileOffenseCodeEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilewarrant.JJSPetition;
import ui.common.CodeHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.facility.form.AdmitReleaseForm;
import ui.juvenilecase.populationReport.UIFacilityPopulationHelper;
import ui.security.SecurityUIHelper;


public class DisplayJuvenileFacilityAdmitAction extends LookupDispatchAction{
	
	
	
	 // ------------------------------------------------------------------------
    // --- constructor ---
    // ------------------------------------------------------------------------

    /**
     * @roseuid 42A898CE034B
     */
    public DisplayJuvenileFacilityAdmitAction()
    {

    }
    
    /**
     * 
     * @param aMapping
     *            The a mapping.
     * @param aForm
     *            The a form.
     * @param aRequest
     *            The a request.
     * @param aResponse
     *            The a response.
     * @return ActionForward
     * @roseuid 42A46D8E0234
     */
    public ActionForward admit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileBriefingDetailsForm form = (JuvenileBriefingDetailsForm) aForm;   
        JuvenileDetentionFacilitiesResponseEvent detResp = form.getAdmissionInfo();
        form.setHasActiveCasefiles(false);
        form.setHasOpenReferrals(false);
        String primaryLanguageId = null;        
        String currRefSeveritySubType="";
        String bookRefSeveritySubType="";
        Date refDate=null;
    	if(form.getProfileDetail()!=null){
			primaryLanguageId= form.getProfileDetail().getPrimaryLanguage();
		}
		if(primaryLanguageId!=null && !primaryLanguageId.isEmpty()){
			form.setPrimaryLanguageDesc(CodeHelper.getFastCodeDescription(PDCodeTableConstants.LANGUAGE,primaryLanguageId));
		}
        //get the petitions and offense for the referrals
       Collection referrals = UIJuvenileCaseworkHelper.fetchJuvenileProfileReferralsList(form.getJuvenileNum());
       Collection refListForSubsqAdmit= new ArrayList();
       Iterator iter = referrals.iterator();
       
       while(iter.hasNext())
       {
    	   JuvenileProfileReferralListResponseEvent resp = (JuvenileProfileReferralListResponseEvent) iter.next();
    	   
    	   if(resp.getCloseDate()!=null)
    		   iter.remove();
    	   else
    	   {
    	       
    	        resp.setIsEligableForBooking(true);
    		if(resp.getCourtDisposition()!=null && resp.getJuvDispositionCode()!=null && resp.getCourtDisposition().equals(resp.getJuvDispositionCode().getCodeAlpha()))
		{
			if(resp.getJuvDispositionCode()!=null && resp.getJuvDispositionCode().getSubGroupInd()!=null && resp.getJuvDispositionCode().getSubGroupInd().equals("G")){
			    
			    resp.setIsEligableForBooking(false);
			}
		}
				
    	       
    		   form.setHasOpenReferrals(true);
	    	   if(resp.isPetitionsAvailable())
	    	   {
	    		   Iterator iter3 = resp.getPetitions().iterator();
	    		   //int seqNum=0;
	    		   while(iter3.hasNext())
	    		   {
	    			   JJSPetition pet = (JJSPetition)iter3.next();
	    			 
	    			   //if the referral has multiple petitions, pick the one with seq num 1
	    			   if(Integer.parseInt(pet.getSequenceNum())==1)
	    			   {
	    				   resp.setPetitionNumber(pet.getPetitionNum());
	    				   GetJuvenileOffenseCodeEvent jocEvent = new  GetJuvenileOffenseCodeEvent();
	    					jocEvent.setAlphaCode(pet.getOffenseCodeId());
	    					IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    					dispatch.postEvent(jocEvent);
	    					CompositeResponse response = (CompositeResponse)dispatch.getReply();
	    					
	    					JuvenileOffenseCodeResponseEvent juvOffenseCode =(JuvenileOffenseCodeResponseEvent)MessageUtil.filterComposite(response,JuvenileOffenseCodeResponseEvent.class);
	    					
	    				   resp.setPetitionAllegation(juvOffenseCode.getShortDescription());
	    			   }	    			   
	    		   }
	    		 
	    	   }
	    	   JJSOffense tempOffense = new JJSOffense();
	    	   int numericCode=0;
	    	   if(resp.isOffensesAvailable())
	    	   {
		    		   Iterator iter2 = resp.getOffenses().iterator();
		    		   while(iter2.hasNext())
		    		   {
		    			   JJSOffense off = (JJSOffense)iter2.next();
		    			   
		    			   if(off!=null)
		    			   {
		    				   JuvenileOffenseCode offCode = off.getOffenseCode();
		    				   if(offCode != null && !offCode.getNumericCode().equals(""))
		    				   {
			    				   int numCodeFrmOfCode=Integer.parseInt(offCode.getNumericCode());
			    				  if(numericCode==0 || numCodeFrmOfCode<numericCode)
			    				   {
			    					   numericCode = numCodeFrmOfCode;
			    					   tempOffense=off;
			    				   }
		    				   }
		    			   }   		   
		    			}
		    		   
		    		resp.setMostSevereOffense(JuvenileFacilityHelper.fetchOffenseResp(tempOffense, form.getJuvenileNum(), resp.getReferralNumber()));  	        
				 }
		    	  // if(resp.getReferralNumber().equals(detResp.getReferralNumber()))
		    	   if(resp.getReferralNumber().equals(detResp.getCurrentReferral())){
		    		   currRefSeveritySubType=resp.getMostSevereOffense().getSeveritySubtype();
		    	   }
		    	   
		    	   if(resp.getReferralNumber().equals(detResp.getReferralNumber())){
		    		   bookRefSeveritySubType=resp.getMostSevereOffense().getSeveritySubtype();
		    		   refDate=resp.getReferralDate();
		    	   }
    	   
		    	   //for each referral get the casefiles	    	
		   			JuvenileProfileReferralListResponseEvent refCasefileList = JuvenileFacilityHelper.getCasefilesFromReferral(form.getJuvenileNum(), resp.getReferralNumber());
		   		   			
		   			//refCasefileList.setCasefiles(getCasefilesWithStatusdesc(refCasefileList,form));
		   			
		   			//Sort JuvenileCasefileReferral list
		   			ArrayList<JuvenileCasefileReferral> casefileReferrals = new ArrayList<JuvenileCasefileReferral>();
		   			TreeMap filtered = new TreeMap();
		   			
		   			List sortedList = new ArrayList<JuvenileCasefileReferral>( refCasefileList.getCasefileReferrals() );
					ArrayList sortFields = new ArrayList();
					sortFields.add(new ComparatorChain(new BeanComparator("caseFileId")));
					sortFields.add(new ComparatorChain(new BeanComparator("refSeqNum")));
					ComparatorChain multiSort = new ComparatorChain(sortFields);
					Collections.sort(sortedList, multiSort);
					casefileReferrals = (ArrayList<JuvenileCasefileReferral>) sortedList;
		   			 
					for(int x=0; x< casefileReferrals.size();x++) {
					    
					    JuvenileCasefileReferral ref = casefileReferrals.get(x);
					    filtered.put(ref.getCaseFileId(), ref);
					}
		   			
		   			Collection<JuvenileCasefileReferral> filteredList;
		   			filteredList = filtered.values();
		   			//Iterator casefilesIter = refCasefileList.getCasefileReferrals().iterator();
		   			Iterator casefilesIter = filteredList.iterator();
		   			
		   			ArrayList<JuvenileCasefileReferral> activeCasefiles = new ArrayList<JuvenileCasefileReferral>(); //added for Bug #51879 - only active casefiles should be listed
		   			while(casefilesIter.hasNext())
		   	    	{
		   	    		JuvenileCasefileReferral casefile = (JuvenileCasefileReferral)casefilesIter.next();
		   	    		//since it is for display only set the status description on caseStatusCd
		   	    		casefile.setCaseStatusCd(CodeHelper.getCodeDescriptionByCode(CodeHelper.getJuvenileCaseStatuses(), casefile.getCaseStatusCd()));   
		   	    		if(casefile.getCaseStatusCd().equalsIgnoreCase("P"))
		   	    			form.setHasPendingCasefiles(true);
		   	    		if(!form.isHasActiveCasefiles() && casefile.getCaseStatusCd().equalsIgnoreCase(UIConstants.ACTIVE))
		   	    			form.setHasActiveCasefiles(true);
		   	    		if( casefile.getCaseStatusCd().equalsIgnoreCase(UIConstants.ACTIVE))
		   	    				activeCasefiles.add(casefile);
		   	    		casefile.setSupervisionType(CodeHelper.getCodeDescriptionByCode(CodeHelper.getSupervisionTypes(), casefile.getSupervisionTypeCd()));
		   	    		casefile.setSupervisionCat(UIJuvenileCaseworkHelper.getSupCatFromType(casefile.getSupervisionTypeCd()));
		   	    	}
		   			resp.setHasCasefiles(!activeCasefiles.isEmpty());
		   			Collections.sort(activeCasefiles, Collections.reverseOrder());
		   			
		   			resp.setCasefileReferrals(activeCasefiles);   		
		   			if(form.getHeaderInfo().getFacilityStatus()!=null && form.getHeaderInfo().getFacilityStatus().equalsIgnoreCase("N")
		 				   && (resp.getReferralNumber().equals(detResp.getReferralNumber()) || resp.getReferralNumber().equals(detResp.getCurrentReferral())))
		   			{
		     		   refListForSubsqAdmit.add(resp);
		   			}
    	   }
       }
       if(form.getHeaderInfo().getFacilityStatus()!=null && form.getHeaderInfo().getFacilityStatus().equalsIgnoreCase("N")){
    	   form.setReferrals(refListForSubsqAdmit);
       }
       else{
    	   Collections.sort((List)referrals, Collections.reverseOrder());
    	   form.setReferrals(referrals);  
       }
       
        if(form.getHeaderInfo().getFacilityStatus()!=null && form.getHeaderInfo().getFacilityStatus().equalsIgnoreCase("N"))
        {
        	if(detResp.getBookingSupervisionNum()!=null){
        		form.setBookingSupervisionNum(detResp.getBookingSupervisionNum());  
        	}else{
        		form.setBookingSupervisionNum("");        		
        	}
        	
        	if(detResp.getCurrentSupervisionNum()!=null && !detResp.getCurrentSupervisionNum().equals("")){
        		form.setCurrentSupervisionNum(detResp.getCurrentSupervisionNum());
        	}else{
        		form.setCurrentSupervisionNum(form.getBookingSupervisionNum());
        	}
        	form.setBookingReferral(detResp.getReferralNumber()+"|" +form.getBookingSupervisionNum()+ "|"+bookRefSeveritySubType);
        	
        	if(detResp.getCurrentReferral()!=null && !detResp.getCurrentReferral().equals("")){ 
        		if(JuvenileFacilityHelper.referralHasCasefiles(form.getReferrals(), detResp.getCurrentReferral()))
        			form.setCurrentReferral(detResp.getCurrentReferral()+"|" +form.getCurrentSupervisionNum()+ "|"+currRefSeveritySubType);    
        		else
        		{
        			form.setCurrentReferral(detResp.getCurrentReferral()+"||"+currRefSeveritySubType);
        			form.setCurrentSupervisionNum("");
        		}
        	}
        }
        //Bug #69605 - detained date from previous admit should populate
        form.setDetainedDate(detResp.getDetainedDate());
        form.setDetainedByInd(detResp.getDetainedByInd());
        form.setHideFacilityTraitsLink(true);
        if(!form.isHasOpenReferrals())
        	 return aMapping.findForward(UIConstants.FACILITY_REFERRAL_FAILURE);
        return aMapping.findForward(UIConstants.SUCCESS);
    }   
    
   
   
    
    /**
     * 
     * @param aMapping
     *            The a mapping.
     * @param aForm
     *            The a form.
     * @param aRequest
     *            The a request.
     * @param aResponse
     *            The a response.
     * @return ActionForward
     * @roseuid 42A46D8E0234
     */
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileBriefingDetailsForm form = (JuvenileBriefingDetailsForm) aForm;
        AdmitReleaseForm admitForm = new AdmitReleaseForm();
        //added for US 31029
        admitForm.setRestrictedAccessTrait(form.isRestrictedAccessTrait());     
        //load admit reasons
        admitForm.setAdmitReasons(UIFacilityPopulationHelper.loadAdmitReasons());   
        JuvenileDetentionFacilitiesResponseEvent detResp = form.getAdmissionInfo();      
        //check if new admit
        JuvenileFacilityHeaderResponseEvent header = form.getHeaderInfo();
        if(header.getJuvenileNumber()==null || header.getFacilityStatus()==null){
        	admitForm.setNewAdmit(true);
        }
        
        
        //hard release.
        if(header!=null && header.getJuvenileNumber()!=null){
        	if(header.getFacilityStatus()==null || (header.getFacilityStatus()!=null && header.getFacilityStatus().isEmpty())){
        		if(detResp.getReleaseTo()!=null && !detResp.getReleaseTo().equals("TRN")){
        			admitForm.setOriginalAdmitOID(null);
        		}
        	}
        }
       
        //Added for U.S #44549
        //U.S.32320
		List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenseResp = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(form.getJuvenileNum());
    	if(!form.isHasPostAdjCatCasefile()){ //if it is a non post adj casefile.Restrict the facility to fewer ones with tjpc placement type D,E,H.
    		admitForm.setFacilities(JuvenileFacilityHelper.loadNonPostAdjFacilities()); 
		}else{
	    	admitForm.setFacilities(JuvenileFacilityHelper.loadActiveFacilities());
		}
    	//bug fix:#48692 ends.
        if(form.getHeaderInfo().getFacilityStatus()!=null && form.getHeaderInfo().getFacilityStatus().equalsIgnoreCase("N"))
        {        
        	//Bug #69605 
        	admitForm.setDetainedDate(DateUtil.dateToString(form.getDetainedDate(), DateUtil.DATE_FMT_1));
        	admitForm.setDetainedByInd(form.getDetainedByInd());
        	
        	//go through the facilities list n get the codeWithJuvTJPCPlacementType for the TransferToFacility
        	if(detResp.getTransferToFacility()!=null && !detResp.getTransferToFacility().equals(""))        
	        	admitForm.setDetainedFacility(JuvenileFacilityHelper.getFacilityCodeWithJuvTJPCPlacementType(admitForm.getFacilities(), detResp.getTransferToFacility()));   
        	//get the labels for the detained facility
        	JuvenileFacilityResponseEvent facResp = JuvenileFacilityHelper.getFacilityResp(JuvenileFacilityHelper.loadActiveFacilities(), detResp.getTransferToFacility());
        	if(facResp==null)
        	{
        		ActionErrors errors = new ActionErrors();
      	        errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","TransferTo Facility is incorrect. Please contact Data Corrections to fix it"));
      	        saveErrors(aRequest, errors);
      	        return aMapping.findForward(UIConstants.FAILURE);
        	
        	}
        	else
        	{
        		//check if it is valid
        		Collection admitFacilities = admitForm.getFacilities();
        		Iterator admitFacIter = admitFacilities.iterator();
        		boolean found=false;
        		while(admitFacIter.hasNext())
        		{
        			JuvenileFacilityResponseEvent admitFacResp = (JuvenileFacilityResponseEvent)admitFacIter.next();
        			if(admitFacResp.getCode().equalsIgnoreCase(facResp.getCode()))
        				found=true;
        		}
        		if(!found)
        		{
        			 ActionErrors errors = new ActionErrors();
           	        errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","TransferTo Facility is incorrect. Please contact Data Corrections to fix it"));
           	        saveErrors(aRequest, errors);
           	        return aMapping.findForward(UIConstants.FAILURE);
        		}
        		admitForm.setPlacementType(facResp.getJuvTJPCPlacementType());
        	}
        	
        	if(facResp!=null)
        	{
        		admitForm.setLocationOneLabel(facResp.getLocationOneLabel());
        		admitForm.setLocationTwoLabel(facResp.getLocationTwoLabel());
        		admitForm.setLocationThreeLabel(facResp.getLocationThreeLabel());
        	}
        	
        	//go through admit reasons and get codeWithDetType
        	if(detResp.getAdmitReason()!=null && !detResp.getAdmitReason().equals(""))
        		admitForm.setReasonCode(JuvenileFacilityHelper.getAdmitCodeWithDetType(admitForm.getAdmitReasons(),detResp.getAdmitReason()));
        	admitForm.setValuablesReceipt(detResp.getReceiptNumber());            	
        	if(detResp.getAdmittedByJPO() != null && !detResp.getAdmittedByJPO().equals("") && detResp.getAdmittedByJPO().length()<= 3)
        		admitForm.setAdmitBy("UV" + detResp.getAdmittedByJPO());
        	else        
        		admitForm.setAdmitBy(detResp.getAdmittedByJPO());
        		
        	if(detResp.getAdmitAuthority() != null && !detResp.getAdmitAuthority().equals("") && detResp.getAdmitAuthority().length()<= 3)
        		admitForm.setAdmitAuthority("UV" +detResp.getAdmitAuthority());
        	else
        		admitForm.setAdmitAuthority(detResp.getAdmitAuthority());
        	admitForm.setLocker(detResp.getLockerNumber());
        	admitForm.setSecureStatus(detResp.getSecureStatus());        	
        	admitForm.setBookingReferral(detResp.getReferralNumber());        
        	form.setJuvFacList(JuvenileFacilityHelper.getJuvFacilityDetails(form.getJuvenileNum(),null));// get all facilities for the juvenile.
        	JuvenileDetentionFacilitiesResponseEvent origDetResp = JuvenileFacilityHelper.getOriginalAdmitRec(form.getJuvFacList(), admitForm.getBookingReferral());
    		if(origDetResp != null)
    		{
    			//admitForm.setOriginalAdmitDate(origDetResp.getAdmitDate());
    			//admitForm.setOriginalAdmitTime(origDetResp.getAdmitTime());
    			if(origDetResp.getOriginalAdmitOID()!=null){
    				admitForm.setOriginalAdmitOID(origDetResp.getOriginalAdmitOID());
    			}else{
    				admitForm.setOriginalAdmitOID(origDetResp.getDetentionId());
    			}
    		}
           		
    		String[] bookingRef = form.getBookingReferral().split("[|]");    		
    		String[] currRef = form.getCurrentReferral().split("[|]");    
    		if(bookingRef.length>1)
    			admitForm.setBookingSupervisionNum(bookingRef[1]);
    		
    		if(currRef.length>1)
    			admitForm.setCurrentSupervisionNum(currRef[1]);
    	
    		admitForm.setCurrentReferral(currRef[0]);
    		admitForm.setBookingReferral(bookingRef[0]);
    		if(bookingRef.length>2)
    			admitForm.setBookingSeveritySubType(bookingRef[2]);
    		if(currRef.length>2)
    			admitForm.setCurrentSeveritySubType(currRef[2]);    		
		
        	//get offense and petition
        	  Iterator<JuvenileProfileReferralListResponseEvent> refIter = form.getReferrals().iterator();
        	  while(refIter.hasNext())
        	  {
        		  JuvenileProfileReferralListResponseEvent resp = (JuvenileProfileReferralListResponseEvent) refIter.next();
        		  if(resp.getReferralNumber().equalsIgnoreCase(admitForm.getBookingReferral()))
        		  {
        			  admitForm.setBookingPetitionNum(resp.getPetitionNumber());
        			  admitForm.setBookingOffense(resp.getMostSevereOffense().getOffenseDescription());    
        			  admitForm.setBookingOffenseCd(resp.getMostSevereOffense().getOffenseCodeId());
        			  admitForm.setReferralSource(resp.getReferralSource());
        			  admitForm.setReferralOfficers(resp.getReferralOfficer());
        			//set the booking referral PIA status
        				admitForm.setBookingRefPIAStatus(resp.getPiaStatus());
        		  }
        		  //U.S #32320 - facility
        		  if(resp.getReferralNumber().equalsIgnoreCase(admitForm.getCurrentReferral()))
        		  {
        			  boolean isTransferredOffense=false;
        			  if(resp.getMostSevereOffense().getSeveritySubtype().equals("T")){
        				  if(transferredOffenseResp!=null && !transferredOffenseResp.isEmpty()){
        					  Iterator<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenseIter = transferredOffenseResp.iterator();
							  while (transferredOffenseIter.hasNext())
								{
									JuvenileCasefileTransferredOffenseResponseEvent transferredOffense= transferredOffenseIter.next();
									if(transferredOffense.getReferralNum().equals(admitForm.getCurrentReferral())){
										  admitForm.setCurrentPetitionNum(resp.getPetitionNumber());
					        			  admitForm.setCurrentOffense(transferredOffense.getOffenseShortDesc());
					        			  admitForm.setCurrentOffenseCd(transferredOffense.getOffenseCode());
					        			  isTransferredOffense=true;
										break;
									}
								}
        				  }
       					 if(!isTransferredOffense){
							admitForm.setCurrentPetitionNum(resp.getPetitionNumber());
		        			admitForm.setCurrentOffense(resp.getMostSevereOffense().getOffenseDescription());
		        			admitForm.setCurrentOffenseCd(resp.getMostSevereOffense().getOffenseCodeId());
       					 }
					}
        			else
					{
						admitForm.setCurrentPetitionNum(resp.getPetitionNumber());
						admitForm.setCurrentOffense(resp.getMostSevereOffense().getOffenseDescription());
		        		admitForm.setCurrentOffenseCd(resp.getMostSevereOffense().getOffenseCodeId());
					}
        		  }
          	}
        }
        else
        {
	        String[] bookingRef = form.getBookingReferral().split("[|]");
	        String[] currRef = form.getCurrentReferral().split("[|]");
	        if(bookingRef.length>2)
	            admitForm.setBookingSeveritySubType(bookingRef[2]);
	        if(currRef.length>2)
	    		admitForm.setCurrentSeveritySubType(currRef[2]);
	      
	        Collection coll = form.getReferrals();
	        Iterator iter = coll.iterator();
	        while(iter.hasNext())
	        {
	        	JuvenileProfileReferralListResponseEvent resp = (JuvenileProfileReferralListResponseEvent)iter.next();
	        	//go through the referrals and get the associated casefile
	        	
	        	if(resp.getReferralNumber().equals(bookingRef[0].toString()))
	        	{
	        		 boolean isTransferredOffense=false;
	        		 admitForm.setReferralSource(resp.getReferralSource());
	        		 admitForm.setReferralOfficers(resp.getReferralOfficer());
	        		if(resp.getMostSevereOffense().getSeveritySubtype().equals("T")){
	        			if(transferredOffenseResp!=null && !transferredOffenseResp.isEmpty())
	        			{
		        			Iterator<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenseIter = transferredOffenseResp.iterator();
							while (transferredOffenseIter.hasNext())
							{
								JuvenileCasefileTransferredOffenseResponseEvent transferredOffense= transferredOffenseIter.next();
								if(transferredOffense.getReferralNum().equals(resp.getReferralNumber())){
									  admitForm.setBookingReferral(resp.getReferralNumber());
									  admitForm.setBookingPetitionNum(resp.getPetitionNumber());
				        			  admitForm.setBookingOffense(transferredOffense.getOffenseShortDesc());
				        			  admitForm.setBookingOffenseCd(transferredOffense.getOffenseCode());
				        			  isTransferredOffense= true;
									break;
								}
							}
	        			}
	        			 if(!isTransferredOffense){
	        			     //task 146711
							/*admitForm.setCurrentPetitionNum(resp.getPetitionNumber());
			        			admitForm.setCurrentOffense(resp.getMostSevereOffense().getOffenseDescription());
			        			admitForm.setCurrentOffenseCd(resp.getMostSevereOffense().getOffenseCodeId());*/
	        			     	admitForm.setBookingReferral(resp.getReferralNumber());
	        			     	admitForm.setBookingPetitionNum(resp.getPetitionNumber());
		        			admitForm.setBookingOffense(resp.getMostSevereOffense().getOffenseDescription());
		        			admitForm.setBookingOffenseCd(resp.getMostSevereOffense().getOffenseCodeId());
		        			//
	       					}
					}
					else
					{
						admitForm.setBookingReferral(resp.getReferralNumber());
		        		admitForm.setBookingOffense(resp.getMostSevereOffense().getOffenseDescription());
		        		admitForm.setBookingOffenseCd(resp.getMostSevereOffense().getOffenseCodeId());
		        		admitForm.setBookingPetitionNum(resp.getPetitionNumber());
					}
	        		
	        		if(resp.isHasCasefiles()){
	        			admitForm.setBookingSupervisionNum(bookingRef[1].toString());
	        		}
	        	}
	
	        	if(resp.getReferralNumber().equals(currRef[0].toString()))
	        	{
	        		boolean isTransferredOffense=false;
	        		if(resp.getMostSevereOffense().getSeveritySubtype().equals("T")){
						Iterator<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenseIter = transferredOffenseResp.iterator();
						if(transferredOffenseResp!=null && !transferredOffenseResp.isEmpty())
	        			{
							while (transferredOffenseIter.hasNext())
							{
								JuvenileCasefileTransferredOffenseResponseEvent transferredOffense= transferredOffenseIter.next();
								if(transferredOffense.getReferralNum().equals(resp.getReferralNumber())){
									  admitForm.setCurrentReferral(resp.getReferralNumber());
									  admitForm.setCurrentPetitionNum(resp.getPetitionNumber());
				        			  admitForm.setCurrentOffense(transferredOffense.getOffenseShortDesc());
				        			  admitForm.setCurrentOffenseCd(transferredOffense.getOffenseCode());
				        			  isTransferredOffense= true;
									break;
								}
							}
	        			}
						if(!isTransferredOffense){
						    	admitForm.setCurrentReferral(resp.getReferralNumber());
							admitForm.setCurrentPetitionNum(resp.getPetitionNumber());
		        			admitForm.setCurrentOffense(resp.getMostSevereOffense().getOffenseDescription());
		        			admitForm.setCurrentOffenseCd(resp.getMostSevereOffense().getOffenseCodeId());
       					}
					}else
						{
					    		//task 146711
							admitForm.setCurrentReferral(resp.getReferralNumber());
							//
							admitForm.setCurrentPetitionNum(resp.getPetitionNumber());
		        			admitForm.setCurrentOffense(resp.getMostSevereOffense().getOffenseDescription());
		        			admitForm.setCurrentOffenseCd(resp.getMostSevereOffense().getOffenseCodeId());
						}
	        		
	        		if(resp.isHasCasefiles()){
	        			admitForm.setCurrentSupervisionNum(currRef[1].toString());
	        		}
	        	}
	        	admitForm.setAdmitBy(SecurityUIHelper.getLogonId());  
	        	//set the booking referral PIA status
				if(resp.getReferralNumber().equalsIgnoreCase(bookingRef[0]))
				{
					admitForm.setBookingRefPIAStatus(resp.getPiaStatus());
				}
	        
	        	//admitForm.setFacilities(JuvenileFacilityHelper.loadActiveFacilities(form.isHasPostAdjCatCasefile(),true)); 
	        }
        }     
        
    	if(admitForm.getCurrentSupervisionNum()==null || admitForm.getCurrentSupervisionNum().equals(""))
		{
			//US 28666 
    		//if booking supervision number is there then that becomes current supervision number
    		if(admitForm.getBookingSupervisionNum()!=null && !admitForm.getBookingSupervisionNum().equals(""))
    			admitForm.setCurrentSupervisionNum(admitForm.getBookingSupervisionNum());
    		else
    		{    		
	    		//booking supervision id is supervision number of an active casefile with a CreateDate closest to the Referral date of the Current Referral
				String supNum=getSupervisionNumber(form, admitForm.getCurrentReferral());    			
				admitForm.setCurrentSupervisionNum(supNum);
    		}
		}
   
        admitForm.setMostRecentCasefileId(JuvenileFacilityHelper.getMostRecentActiveCasefile(form.getReferrals(), form.getCasefiles(), admitForm.getCurrentReferral()));
//    	admitForm.setAdmitDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
//    	SimpleDateFormat sdf = new SimpleDateFormat("HHmm");   
//    	Date now = new Date();
    	admitForm.setReferrals(form.getReferrals());
//    	admitForm.setAdmitTime(sdf.format(now));
    	admitForm.setFacilityStatus(form.getHeaderInfo().getFacilityStatus());       
        admitForm.setJuvenileNum(form.getJuvenileNum());           
        //load special attention reasons              
        admitForm.setSpecialAttentionReasons(JuvenileFacilityHelper.getSortedSpecialAttnCodes());
        admitForm.setSpecialAttentionCd("NO");        
        admitForm.setHideFacilityTraitsLink(true);
        admitForm.setProfileDetail(form.getProfileDetail());
        admitForm.setAge(form.getAge()); //added for BUG# 40618
        admitForm.setPrimaryLanguageDesc(form.getPrimaryLanguageDesc());
        admitForm.setEthnicity(form.getEthnicity());
        admitForm.setAction(UIConstants.CREATE);
        //added for bug #52120
        admitForm.setDetResp(form.getAdmissionInfo());
        HttpSession session = aRequest.getSession();
        session.setAttribute("admitReleaseForm", admitForm);
        session.setAttribute("juvenileBriefingDetailsForm", form);
        return aMapping.findForward(UIConstants.NEXT);
    }
    
 
    private String getSupervisionNumber(JuvenileBriefingDetailsForm form, String referralNum)
    {
    	Collection casefiles = form.getCasefiles();       		
		Collection referrals = form.getReferrals();
		Iterator refIter = referrals.iterator();
		Date refDate=null;
		while(refIter.hasNext())
		{
			JuvenileProfileReferralListResponseEvent resp = (JuvenileProfileReferralListResponseEvent)refIter.next();
			if(resp.getReferralNumber().equals(referralNum))
				refDate=resp.getReferralDate();
		}
		if(refDate!=null)
		{
			JuvenileCasefileSearchResponseEvent resp = JuvenileFacilityHelper.getCasefileCreatedClosestToRefDt(casefiles, refDate);
			return resp.getSupervisionNum();
		}
		return null;
    }
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return( aMapping.findForward( UIConstants.CANCEL ) ) ;

	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return( aMapping.findForward( UIConstants.BACK ) ) ;

	}
    /**
     * 
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.admit", "admit");
        keyMap.put("button.back", "back");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.next", "next");
        return keyMap;
    }
   
}
