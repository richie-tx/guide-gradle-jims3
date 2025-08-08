package pd.juvenilecase.facility.transactions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.facility.GetJuvenileFacilityDetailsEvent;
import messaging.facility.GetJuvenileFacilityMaxTJPCSeqNumEvent;
import messaging.facility.SaveJuvenileFacilityAdmitDetailsEvent;
import messaging.facility.reply.JuvenileFacilityDetailsResponseEvent;
import messaging.identityaddress.domintf.IAddressable;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.RiskAssessmentListResponseEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.productionsupport.GetTransOffenseReferralsQueryEvent;
import messaging.referral.GetJJSReferralEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.riskanalysis.GetAllRiskAssessmentsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import mojo.naming.NotificationControllerSerivceNames;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.RiskAnalysisConstants;
import pd.codetable.criminal.JuvenileDates;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenilecase.JJSChainNumControl;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSFacilityAdmissionComments;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JJSSplAttnReasonComments;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.referral.JJSTransferredOffenseReferral;
import pd.security.PDSecurityHelper;
import pd.supervision.programreferral.JuvenileProgramReferral;
import ui.common.Name;
import ui.common.UINotificationHelper;

public class SaveJuvenileFacilityAdmitDetailsCommand implements ICommand{
	
	/**
	 * Execute method
	 */
	public void execute(IEvent event)
	{
		SaveJuvenileFacilityAdmitDetailsEvent admitDetails = (SaveJuvenileFacilityAdmitDetailsEvent)event;
		Iterator jjsHeaderItr = JJSHeader.findAll("juvenileNumber",admitDetails.getJuvenileNum());
		int lastSeq=0;
		JJSHeader header;
		boolean newHeader=false;
		if (jjsHeaderItr.hasNext()) {
			header = (JJSHeader) jjsHeaderItr.next();		
			GetJuvenileFacilityDetailsEvent facilityEvent = new GetJuvenileFacilityDetailsEvent();
			facilityEvent.setJuvenileNum(admitDetails.getJuvenileNum());
			Iterator facilityItr = JJSFacility.findAll(facilityEvent);
			
			while (facilityItr.hasNext()) {
				JJSFacility fac = (JJSFacility)facilityItr.next();					
				//get the highest last seq num;
				if(Integer.parseInt(fac.getFacilitySequenceNumber())>lastSeq)
					lastSeq=Integer.parseInt(fac.getFacilitySequenceNumber());
			}
			lastSeq+=20;
			header.setLastSequenceNumber(""+lastSeq);	//check with Regina
		}
		else
		{
			header = new JJSHeader();		
			header.setLastSequenceNumber("20");//check with Regina
			lastSeq=20;
			newHeader=true;
		}
		header.setFacilityStatus(admitDetails.getDetentionType());			
		header.setFacilityCode(admitDetails.getDetainedFacility()); //header facility
		header.setJuvenileNumber(admitDetails.getJuvenileNum());
		header.setReferralNumber(admitDetails.getBookingReferral());
		header.setBookingSupervisionNum(admitDetails.getBookingSupervisionNum());  //waiting for Gordon to add the column
		header.setLcDate(DateUtil.getCurrentDate());
		header.setLcTime(Calendar.getInstance().getTime());
		header.setLcUser(PDSecurityHelper.getLogonId());
		header.setHeaderFacility("DET");
		//decided as per MJCW issues meeting 03/23/2015 not to generate through JIMS2 - OID will be used for TJJD reporting
		//String tjpcSeqNum=getMaxTJPCNum();		
		//header.setTjpcseqnum(Integer.parseInt(tjpcSeqNum)+3 +"");
		//update the JUVENILE_MASTER
		JJSJuvenile juvenile = JJSJuvenile.find( admitDetails.getJuvenileNum() );
		String juvenileName = "";
		if(juvenile!=null)
		{
			juvenile.setDetentionStatusId(admitDetails.getDetentionType());
			juvenile.setDetentionFacilityId(admitDetails.getDetainedFacility());
			Name juvName = new Name(juvenile.getFirstName(), juvenile.getMiddleName(), juvenile.getLastName());
			juvenileName= juvName.getFormattedName();
			
		}	
				
		//get the booking referral and update the admitDate, referralSource and referralOfficers		
		GetJJSReferralEvent jjsEvt = new GetJJSReferralEvent();
		jjsEvt.setJuvenileNum(admitDetails.getJuvenileNum());
		jjsEvt.setReferralNum(admitDetails.getBookingReferral());
		Iterator refIter = JJSReferral.findAll(jjsEvt);
		
		while(refIter.hasNext())
		{
			JJSReferral ref = (JJSReferral)refIter.next();
			if(ref.getReferralNum().equals(admitDetails.getBookingReferral()))
			{
				ref.setAdmitDate(admitDetails.getAdmitDate());
				ref.setReferralSource(admitDetails.getReferralSource());
				ref.setReferralOfficer(admitDetails.getReferralOfficers());
				ref.setTJJDReferralDate(admitDetails.getAdmitDate()); //bug no #86285
				//task 130631 to add indicator PF only if its not TR
				if (!ref.getReferralTypeInd().equalsIgnoreCase("TR"))
				{
				  //task 174215 We need an exception for JUV_OFFENSECATEGORY =AC.  The referraltypeind should always be PA for category ACs.
				    JuvenileOffenseCode offCode = JuvenileOffenseCode.find("offenseCode",admitDetails.getBookingOffense());
				    if(offCode!=null)
				    {
        				    if(offCode.getCategory()!= null && offCode.getCategory().equalsIgnoreCase("AC"))
        				    	ref.setReferralTypeInd("PA");
        				    else
        					 ref.setReferralTypeInd("PF");//U.S #80603
				    }
				    else
					ref.setReferralTypeInd("PF");
				   
				    //task 171521
				    ref.setCountyREFD("101");
				}
				else
				{
					String countyId="";
		    	    		GetTransOffenseReferralsQueryEvent getEvent = new GetTransOffenseReferralsQueryEvent();
		    	    		getEvent.setJuvenileNumber(jjsEvt.getJuvenileNum());
		    	    		getEvent.setReferralNumber(jjsEvt.getReferralNum());
		                	Iterator<JJSTransferredOffenseReferral> transOffenseReferralsIter = JJSTransferredOffenseReferral.findAll(getEvent);   
		                	
		                	while(transOffenseReferralsIter.hasNext()) 
		                	{
		                	    JJSTransferredOffenseReferral transOffenseReferral = (JJSTransferredOffenseReferral) transOffenseReferralsIter.next();
		                	    countyId=transOffenseReferral.getFromCountyCode();
		                	    ref.setCountyREFD(countyId); 
		                	}
		                	  
		    	    	} 
				if(ref.getFinalReleaseDate()!=null){
					ref.setFinalReleaseDate(null);
				}
				//check if the referral has offense RUNWY5
				GetJuvenileCasefileOffensesEvent offEvent = new GetJuvenileCasefileOffensesEvent();
				offEvent.setJuvenileNum(admitDetails.getJuvenileNum());
				offEvent.setReferralNum(admitDetails.getBookingReferral());
				
				
				Iterator<JJSOffense> iter = JJSOffense.findAll(offEvent);
				while (iter.hasNext())
				{
					JJSOffense offense = iter.next();
					if(offense.getOffenseCodeId()!= null && offense.getOffenseCodeId().equalsIgnoreCase("RUNWY5"))
					{
					    ref.setReferralTypeInd("CD");
					    ref.setTJJDReferralDate(DateUtil.getCurrentDate());
					    //task 171521
					    ref.setCountyREFD("756");
					}					
				
				}
				
			}
		}
		this.setAdmitDetails(admitDetails, lastSeq, juvenileName);
	
		//if Hearing.Chain='Y' create a Calendar Detention record		
		if(admitDetails.isNewAdmit() && admitDetails.getGenDetHearingChain()!=null && admitDetails.getGenDetHearingChain().equalsIgnoreCase("Y"))
		{
			JJSCLDetention court = new JJSCLDetention();
			court.setRecType("DETENTION");
			court.setCourtTime("0900");
			court.setReferralNumber(admitDetails.getBookingReferral());
			court.setJuvenileNumber(admitDetails.getJuvenileNum());
			court.setPetitionNumber(admitDetails.getBookingPetitionNum());
			court.setSeqNumber("10");
			court.setHearingType("PC");
			court.setDetentionId(admitDetails.getDetentionId());
			//decided as per MJCW issues meeting 03/23/2015 not to generate through JIMS2
			//court.setTjpcseqnum(Integer.parseInt(tjpcSeqNum)+1+"");
			court.setLcDate(DateUtil.getCurrentDate());
			court.setLcTime(Calendar.getInstance().getTime());
			court.setLcUser(PDSecurityHelper.getLogonId());
			
			Calendar c = Calendar.getInstance();
			c.setTime(admitDetails.getAdmitDate());
			c.add(Calendar.DATE, Integer.parseInt(admitDetails.getDaysToProbableCause()));
			
			//check if courtDate falls on saturday or sunday
			if(c.get(Calendar.DAY_OF_WEEK)== Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK)== Calendar.SUNDAY)
				court.setCourtId("300");
			else
				court.setCourtId(admitDetails.getFacRefereeCourt());	
			
			//US 66847
			if(!court.getCourtId().equals("300"))
			{	
				String cDate1 = DateUtil.dateToString(c.getTime(), "yyyyMMdd");				
				//check if the court date falls on a holiday
				Iterator holiday = JuvenileDates.findAll("code", cDate1);
				if(holiday.hasNext())
				{
					//if it is a holiday set it to 300 court
					court.setCourtId("300");
				}
			}
			String cDate = DateUtil.dateToString(c.getTime(), "yyyyMMdd");
			Date courtDate=DateUtil.stringToDate(cDate, "yyyyMMdd");			
			court.setCourtDate(courtDate);
			
			/*if(court.getCourtId().equals("300"))
			{				
				court.setCourtDate(courtDate);				
			}
			else
			{
				String cDate1 = DateUtil.dateToString(c.getTime(), "MMddyyyy");				
				//check if the court date falls on a holiday
				Iterator holiday = JuvenileDates.findAll("code", cDate1);
				if(holiday.hasNext())
				{
					JuvenileDates juvCourtDate = (JuvenileDates)holiday.next();				
						court.setCourtDate(DateUtil.stringToDate(juvCourtDate.getResetDate(), "yyyyMMdd"));					
				}
				else
					court.setCourtDate(courtDate);
								
			}	*/		
			Calendar cal = Calendar.getInstance();
			cal.setTime(court.getCourtDate());
			cal.set(Calendar.HOUR_OF_DAY, 9);
			cal.set(Calendar.MINUTE,0);
			cal.set(Calendar.SECOND,0);
			cal.set(Calendar.MILLISECOND,0);

			header.setNextHearingDate(cal.getTime());
			//add PCHearing while creating itself as Carla confirmed bug 121710 
			header.setProbableCauseDate(cal.getTime());
			//
			if(newHeader)
			{
				IHome home= new Home();
	   			home.bind(header);
			}
			//set the next chain number
			Iterator chainNumIter = JJSChainNumControl.findAll();
			if(chainNumIter.hasNext())
			{
				JJSChainNumControl chainNumControl = (JJSChainNumControl) chainNumIter.next();
				String nextChainNum = chainNumControl.getNextChainNum();
				if(nextChainNum!=null && !nextChainNum.equals(" "))
				{
					int num = Integer.parseInt(nextChainNum);
					court.setChainNumber(""+num);
					chainNumControl.setNextChainNum(""+(num+1));
				}
			}		
			
 			IHome home2= new Home();
   			home2.bind(court);
			
		}
	}
	private void setAdmitDetails(SaveJuvenileFacilityAdmitDetailsEvent saveAdmit, int lastSeq, String juvenileName)
	{
		JJSFacility fac = new JJSFacility();
		fac.setSpecialAttention(saveAdmit.getSpecialAttentionCd());
		fac.setSaReasonCode(saveAdmit.getSaReason());
		//fac.setSaReasonOtherComments(saveAdmit.getSaReasonOtherComments());		
		fac.setReferralNumber(saveAdmit.getBookingReferral());		
		fac.setBookingSupervisionNum(saveAdmit.getBookingSupervisionNum());		
		fac.setCurrentReferral(saveAdmit.getCurrentReferral());
		fac.setCurrentOffense(saveAdmit.getCurrentOffense());	
		fac.setCurrentSupervisionNum(saveAdmit.getCurrentSupervisionNum());
		fac.setDetainedFacility(saveAdmit.getDetainedFacility());
		fac.setSecureStatus(saveAdmit.getSecureStatus());
		fac.setAdmitReason(saveAdmit.getReasonCode());
		fac.setAdmitDate(saveAdmit.getAdmitDate());
		fac.setAdmitTime(saveAdmit.getAdmitTime());
		fac.setAdmittedByJPO(saveAdmit.getAdmitBy());
		fac.setAdmitAuthority(saveAdmit.getAdmitAuthority());
		fac.setReceiptNumber(saveAdmit.getValuablesReceipt());
		fac.setLockerNumber(saveAdmit.getLocker());		
		fac.setFloorNum(saveAdmit.getFloorLocation());
		fac.setUnit(saveAdmit.getUnitLocation());
		fac.setRoomNum(saveAdmit.getRoomLocation());
		//fac.setComments(saveAdmit.getAdmissionComments()); added for U.S #51737
		fac.setFacilitySequenceNumber(""+lastSeq);
		fac.setJuvenileNumber(saveAdmit.getJuvenileNum());
		fac.setOriginalAdmitDate(saveAdmit.getOriginalAdmitDate());
		fac.setOriginalAdmitTime(saveAdmit.getOriginalAdmitTime());
		fac.setDetentionStatus(saveAdmit.getDetentionType());
		fac.setMultipleOccupyUnit(saveAdmit.getMultipleOccupyUnit());
		fac.setOriginalAdmitOID(saveAdmit.getOriginalAdmitOID()); //copy the old OID.		//added for User-story #44549
		fac.setRecType("DETENTION");
		//Bug #69605
		fac.setDetainedDate(saveAdmit.getDetainedDate());
		fac.setDetainedByInd(saveAdmit.getDetainedByInd());
		fac.setTjjdFacilityId(saveAdmit.getTjjdFacilityId());
		fac.setTjjdFundingSrc(saveAdmit.getTjjdFundingSrc());

		fac.setVendorLocation(saveAdmit.getVendorLocation());
		
		fac.setAvgCostPerDay(saveAdmit.getAvgCostPerDay());
		
		//decided as per MJCW issues meeting 03/23/2015 not to generate through JIMS2 - OID will be used for TJJD reporting
		//fac.setTjpcseqnum(Integer.parseInt(tjpcseqnum)+2+"");
		
		//===================== US 149639 (Bug 160029) =========================
		
		//get all the risk assessments for this juvenile and filter for the same case file
		GetAllRiskAssessmentsEvent event =
			(GetAllRiskAssessmentsEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETALLRISKASSESSMENTS);
		
		event.setJuvenileNumber(fac.getJuvenileNumber());
		//event.setCasefileId(fac.getBookingSupervisionNum());
		
		CompositeResponse composite = MessageUtil.postRequest(event);
		Collection riskAssessments = MessageUtil.compositeToCollection(composite, RiskAssessmentListResponseEvent.class);
		ArrayList<RiskAssessmentListResponseEvent> filteredRiskAssessments = new ArrayList<RiskAssessmentListResponseEvent>();
		
		Calendar twentyFourHoursAgo = Calendar.getInstance();
		twentyFourHoursAgo.add(Calendar.HOUR, -24);
		
		if(fac.getRiskAnalysisId() == null || fac.getRiskAnalysisId().equals("")){ 
		    
		    Iterator<RiskAssessmentListResponseEvent> iter = riskAssessments.iterator();
		    while(iter.hasNext()){
			RiskAssessmentListResponseEvent risk = (RiskAssessmentListResponseEvent)iter.next();
			
			//if(fac.getBookingSupervisionNum().equalsIgnoreCase(risk.getCasefileId()))
			if(RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL.equalsIgnoreCase(risk.getAssessmentType()) 
				&& fac.getDetainedFacility().equalsIgnoreCase("DET")&& risk.getAssessmentDate().after(twentyFourHoursAgo.getTime())
				&& fac.getJuvenileNumber().equalsIgnoreCase(risk.getJuvenileNumber())){
			    filteredRiskAssessments.add(risk);
			}
			
		    }
		}	    		
		
		RiskAssessmentListResponseEvent riskEvent = getLatestRiskAssessmentRecord(filteredRiskAssessments); 
		if(riskEvent != null && riskEvent.getAssessmentDate() != null){
		    fac.setRiskAnalysisId(riskEvent.getAssessmentID());
		}
		
		fac.setOriginaladmitSEQNUM(saveAdmit.getOriginaladmitSEQNUM());
		fac.setPostAdmitOID( saveAdmit.getPostAdmitOID() );
		
		//=================================================================	
		
		IHome home= new Home();
		home.bind(fac);
		saveAdmit.setDetentionId(fac.getOID());
		
	    //US 89589 - if the Juvenile is admitted to DET 
		if(fac.getDetainedFacility().equalsIgnoreCase("DET"))
		{
		    JuvenileProgramReferral programReferral = null ;		
		    //get all the Programs for the Juvenile
		    Iterator<JuvenileProgramReferral> progRefIter = JuvenileProgramReferral.findAll("juvenileId", fac.getJuvenileNumber());
		    while(progRefIter.hasNext())
		    {
			programReferral = progRefIter.next();
			if( programReferral.getReferralStatusCd()!=null &&  programReferral.getReferralStatusCd().equalsIgnoreCase("AC")
				&&
				( programReferral.getProvProgramId().equals("2728") || programReferral.getProvProgramId().equals("1159")
			    || programReferral.getProvProgramId().equals("1161") ||programReferral.getProvProgramId().equals("1156")) )
			{
			   
			        Collection<OfficerProfileResponseEvent>   securityRespEvent =  PDOfficerProfileHelper.getOfficerProfilesInUserGroup("SPECIALTY COURT - EMAIL GROUP");
				if(securityRespEvent!=null){
					 Iterator<OfficerProfileResponseEvent> securityRespIter = securityRespEvent.iterator();
				
				    while(securityRespIter.hasNext())
				    {
					OfficerProfileResponseEvent	securityResEvent =	securityRespIter.next();
					//start send notification for the detention hearing group.
				    	JuvenileFacilityDetailsResponseEvent respEvt = new JuvenileFacilityDetailsResponseEvent();
				    	respEvt.setSubject("Specialty Court Juvenile admitted to DET facility");
				    	respEvt.setIdentity(securityResEvent.getUserId());
				    	respEvt.setJuvenileNum(fac.getJuvenileId());
				
				    	String notifMessage = juvenileName + " " + fac.getJuvenileNumber()+" was admitted to " + fac.getDetainedFacility()+ ", Referral# "+ fac.getReferralNumber()
				    		+ " on " + DateUtil.dateToString(fac.getAdmitDate(), DateUtil.DATE_FMT_1) + ". Juvenile has an accepted program referral for the following program: "
				    		+ programReferral.getProviderProgramName();
				    	respEvt.setNotificationMessage(notifMessage);
				    	
				    	CreateNotificationEvent notificationEvent = (CreateNotificationEvent) 
				    			EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
				    	notificationEvent.setNotificationTopic("JC.FACILITY.ADMIT.SPECIALTY.COURT.NOTIFICATION");
				    	notificationEvent.setSubject(respEvt.getSubject());
				    	notificationEvent.addIdentity("respEvent", (IAddressable)respEvt);
				    	notificationEvent.addContentBean(respEvt);
				    	CompositeResponse response = MessageUtil.postRequest(notificationEvent);
				    	MessageUtil.processReturnException( response ) ;
				    	//end send notification	
				    	
					
					if(securityResEvent.getEmail()!=null && !securityResEvent.getEmail().equals(""))
				    	{
                        			//send email
                        			SendEmailEvent sendEmailEvent = new SendEmailEvent();
                        			StringBuffer message = new StringBuffer(100);
                        			sendEmailEvent.setFromAddress("jims2notification@itc.hctx.net");
				    		UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent,securityResEvent.getEmail());
                        			sendEmailEvent.setSubject("Specialty Court Juvenile admitted to DET facility.");
                        			message.append(juvenileName + " " + fac.getJuvenileNumber()); 
                        			message.append(" was placed in ");
                        			message.append(fac.getDetainedFacility());
                        			message.append(", Referral# ");
                        			message.append(fac.getReferralNumber());
                        			message.append(" on ");
                        			message.append(DateUtil.dateToString(fac.getAdmitDate(), DateUtil.DATE_FMT_1));
                        			message.append(". Juvenile has an accepted program referral for the following program: ");
                        			message.append(programReferral.getProviderProgramName());
                        		    	sendEmailEvent.setMessage(message.toString());
                        		    	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				    		dispatch.postEvent(sendEmailEvent);
				    	}//end if officer email
				    }//end while
				}
			}
		    }
		}
		
	     
	     //US 42660 
	     JuvenileDetentionFacilitiesResponseEvent detResp = new JuvenileDetentionFacilitiesResponseEvent();
	     detResp.setDetentionId(fac.getOID()); // issue for migrated recs sample # juv 393375 traits were not getting saved.
	     detResp.setOriginalAdmitOID(fac.getOriginalAdmitOID());	  
	     IDispatch dispatchDet = EventManager.getSharedInstance(EventManager.REPLY);
	     dispatchDet.postEvent(detResp);
		//added for User-story #44549
		//persist Spl Attention Comments //task #34820 bug fix:40650		
		String saReasonOtherComments = saveAdmit.getSaReasonOtherComments();
		String juvenileNum = saveAdmit.getJuvenileNum();
		String detentionId = fac.getOID();
		if(saReasonOtherComments!=null && !saReasonOtherComments.isEmpty() && juvenileNum!=null && detentionId!=null){
			JJSSplAttnReasonComments splAttentionComments = new JJSSplAttnReasonComments();
			splAttentionComments.setComments(saReasonOtherComments);
			splAttentionComments.setJuvenileNum(juvenileNum);
			splAttentionComments.setDetentionId(detentionId);
			home.bind(splAttentionComments); //insert the comments.
		}	
		//added for U.S #51737
		if(saveAdmit.getAdmissionComments()!=null && !saveAdmit.getAdmissionComments().isEmpty()){
			JJSFacilityAdmissionComments comments = new JJSFacilityAdmissionComments();
			comments.setComments(saveAdmit.getAdmissionComments());
			comments.setJuvenileNum(saveAdmit.getJuvenileNum());
			comments.setDetentionId(detentionId);
			home= new Home();
			home.bind(comments); //insert the comments.
		}
	}
	
	private String getMaxTJPCNum()
	{
		//GENERATE TJPC SEQ NUM	
		String tjpcNum="";
		boolean found=false;
		GetJuvenileFacilityMaxTJPCSeqNumEvent tjpcSeqNumMaxEvt = new GetJuvenileFacilityMaxTJPCSeqNumEvent();
		Iterator headerIter = JJSHeader.findAll(tjpcSeqNumMaxEvt);
		if(headerIter.hasNext())
		{
			found=true;
			JJSHeader header = (JJSHeader)headerIter.next();
			tjpcNum=header.getTjpcseqnum();		
		}
		Iterator detIter = JJSFacility.findAll(tjpcSeqNumMaxEvt);
		if(detIter.hasNext())
		{
			found=true;
			JJSFacility fac = (JJSFacility)detIter.next();
			if(fac.getTjpcseqnum()!=null && Integer.parseInt(fac.getTjpcseqnum())>Integer.parseInt(tjpcNum))
				tjpcNum=fac.getTjpcseqnum();
		}
		//get detention hearing records and get max tjpcSeqNum #81390
		tjpcSeqNumMaxEvt.setLcUser("JIMS2");		
		Iterator clDetIter = JJSCLDetention.findAll(tjpcSeqNumMaxEvt);
		if(clDetIter.hasNext())
		{
			found=true;
			JJSCLDetention clDet = (JJSCLDetention) clDetIter.next();
			if(clDet.getTjpcseqnum()!=null && Integer.parseInt(clDet.getTjpcseqnum())>Integer.parseInt(tjpcNum))
				tjpcNum=clDet.getTjpcseqnum();
		}
		if(!found)
			return "10000000";
		
		return tjpcNum;
	}
	
	public RiskAssessmentListResponseEvent getLatestRiskAssessmentRecord(ArrayList<RiskAssessmentListResponseEvent> list){
	    
	    Date maxDate = this.StringToDate("1900-01-01 00:00:00");

	    RiskAssessmentListResponseEvent riskEvent = new RiskAssessmentListResponseEvent();
	    
	    Iterator riskIter = list.iterator();
	    
	    while(riskIter.hasNext()){
		
		RiskAssessmentListResponseEvent risk = (RiskAssessmentListResponseEvent)riskIter.next();
		
		if(risk.getAssessmentDate() != null && maxDate != null){
		    
		    if(risk.getAssessmentDate().after(maxDate)){
			    maxDate = risk.getAssessmentDate();
			    riskEvent = risk;
		    }
		}
		
	    }

	    return riskEvent;
	}
	
	public Date StringToDate(String s){

	       Date result = null;
	       try{
	           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	           result  = dateFormat.parse(s);
	       }

	       catch(ParseException e){
	           e.printStackTrace();

	       }
	       return result ;
	}

}
