/*
 * Created on Oct 3, 2006
 *
 */
package ui.supervision.administercasenotes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import messaging.administercasenotes.GetCasenotesEvent;
import messaging.administercasenotes.domintf.ICasenote;
import messaging.administercasenotes.reply.CasenoteResponseEvent;
import messaging.administercasenotes.to.CasenoteCaseTO;
import messaging.contact.domintf.IAddress;
import messaging.contact.domintf.IName;
import messaging.contact.domintf.IPhoneNumber;
import messaging.contact.domintf.ISocialSecurity;
import messaging.domintf.contact.party.ISupervisee;
import messaging.domintf.contact.user.IUserProfile;
import messaging.supervisionorder.GetPriorSupervisionPeriodsEvent;
import messaging.supervisionorder.reply.SuperviseeListResponseEvent;
import messaging.supervisionorder.reply.SupervisionPeriodResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CasenoteControllerServiceNames;
import naming.PDCommonSupervisionConstants;

import org.apache.commons.lang.StringUtils;

import ui.common.Address;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SocialSecurity;
import ui.common.UIUtil;
import ui.security.SecurityUIHelper;
import ui.supervision.BasicSupervisee;
import ui.supervision.CaseInfo;
import ui.supervision.Casenote;
import ui.supervision.SupervisionPeriodInfo;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administercasenotes.form.CasenoteForm;
import ui.supervision.administercasenotes.form.CasenoteJournalForm;
import ui.supervision.administercasenotes.form.CasenoteSearchForm;
import ui.supervision.administercasenotes.form.SuperviseeInfoHeaderForm;

/**
 * @author jjose
 *
 */
public class UICasenoteHelper {
	
	public static List getPriorSupervisionPeriods(String aSpn, String aUserAgencyId)
	{
		List mySupPeriods=null;
		GetPriorSupervisionPeriodsEvent mySupPeriodEvent = new GetPriorSupervisionPeriodsEvent();
		IDispatch dispatch = EventManager
				.getSharedInstance(EventManager.REQUEST);
		mySupPeriodEvent.setAgencyId(aUserAgencyId);
		mySupPeriodEvent.setSpn(aSpn);
		dispatch.postEvent(mySupPeriodEvent);
		CompositeResponse response1 = (CompositeResponse) EventManager
				.getSharedInstance(EventManager.REQUEST).getReply();
		// check for errors
		MessageUtil.processReturnException(response1);
		Collection periods = MessageUtil.compositeToCollection(response1,
				SupervisionPeriodResponseEvent.class);
		if (periods == null || periods.size() <= 0) {
		} else { // periods to show return to ask user to select periods
			Iterator iterPeriods = periods.iterator();
			mySupPeriods = new ArrayList();
			while (iterPeriods.hasNext()) {
				SupervisionPeriodResponseEvent myRespEvt = (SupervisionPeriodResponseEvent) iterPeriods
						.next();
				SupervisionPeriodInfo mySupPeriod = new SupervisionPeriodInfo();
				mySupPeriod.setSupPeriodBeginDate(myRespEvt.getSupervisionBeginDate());
				mySupPeriod.setSupPeriodEndDate(myRespEvt.getSupervisionEndDate());
				mySupPeriod.setSupPeriodId(myRespEvt.getSupervisionPeriodId());
				mySupPeriods.add(mySupPeriod);
			}
		}
		return mySupPeriods;		
	}
	
	public static List getBasicSupervisessFromSupListRespEvts(Collection supervisees){
		ArrayList mySupervisees=null;
		if(supervisees!=null && supervisees.size()>0){
			mySupervisees=new ArrayList();
			Iterator iter=supervisees.iterator();
			while(iter.hasNext()){
				SuperviseeListResponseEvent myRespEvent=(SuperviseeListResponseEvent)iter.next();
				mySupervisees.add(UICasenoteHelper
						.getBasicSupervisee(myRespEvent));
			}
		}
		return mySupervisees;
	}
	
	public static BasicSupervisee getBasicSupervisee(ISupervisee aResponseEvent){
		if (aResponseEvent == null) {
			return null;
		}
		BasicSupervisee mySupervisee = new BasicSupervisee();
		mySupervisee.setCon(aResponseEvent.getConnectionId());
		mySupervisee.setNextContactDate(aResponseEvent.getNextContactDate());
		mySupervisee.setUnit(aResponseEvent.getUnit());
		mySupervisee.setContactMethodId(aResponseEvent.getContactMethod());
		mySupervisee.setContactReason(aResponseEvent.getContactReason());
		mySupervisee.setInComplianceInd(aResponseEvent.isInComplianceInd());
		mySupervisee.setDateOfBirth(aResponseEvent.getDateOfBirth());
		StringBuffer padFbi = new StringBuffer("");
		padFbi.append(aResponseEvent.getFbiNum());
	    if (padFbi.length() < 9){
	    	while (padFbi.length() < 9){
	    		padFbi.insert(0, "0");
	    	}
	    	aResponseEvent.setSidNum(padFbi.toString());
	    }
		mySupervisee.setFBI(aResponseEvent.getFbiNum());
		mySupervisee.setRaceId(aResponseEvent.getRaceId());
		mySupervisee.setSexId(aResponseEvent.getSexId());
		StringBuffer padSid = new StringBuffer("");
		padSid.append(aResponseEvent.getSidNum());
	    if (padSid.length() < 10){
	    	while (padSid.length() < 10){
	    		padSid.insert(0, "0");
	    	}
	    	aResponseEvent.setSidNum(padSid.toString());
	    }
		mySupervisee.setSID(aResponseEvent.getSidNum());
		StringBuffer padSpn = null;
		padSpn = new StringBuffer(aResponseEvent.getSpn());
	    if (padSpn.length() < 8){
	    	while (padSpn.length() < 8){
	    		padSpn.insert(0, "0");
	    	}
	    	aResponseEvent.setSpn(padSpn.toString());
	    }
		mySupervisee.setSpn(aResponseEvent.getSpn());
		mySupervisee.setOfficerPosition(aResponseEvent.getOfficerPosition());
		ISocialSecurity ssn = new SocialSecurity(aResponseEvent.getSsn());
		mySupervisee.setSsn(ssn);
		mySupervisee.setSuperviseeId(aResponseEvent.getSpn());
		mySupervisee.setSuperviseeName(new Name(aResponseEvent.getFirstName(),
				aResponseEvent.getMiddleName(), aResponseEvent.getLastName()));
		mySupervisee.setSuperviseeNamePtr(aResponseEvent.getNamePtr());
		if (aResponseEvent.getOfficerName() == null || aResponseEvent.getOfficerName().equals("")){
		    IName aName = new Name();
		    aName.setLastName("No Officer Assigned");
		    mySupervisee.setOfficerName(aName);
		} else {
		    mySupervisee.setOfficerName(UIUtil.getNameFromString(aResponseEvent.getOfficerName()));
	    }
		mySupervisee.setProbationOfficerId(aResponseEvent.getProbationOfficerId());
		IAddress myAddress = new Address();
		myAddress.setAddressId(aResponseEvent.getAddressId());
		myAddress.setAptNum(aResponseEvent.getCurrentAddressAptNum());
		myAddress.setCity(aResponseEvent.getCurrentAddressCity());
		myAddress.setStateCode(aResponseEvent.getCurrentAddressStateId());
		myAddress.setStreetName(aResponseEvent.getCurrentAddressStreetName());
		myAddress.setStreetNum(aResponseEvent.getCurrentAddressStreetNum());
		myAddress.setZipCode(aResponseEvent.getCurrentAddressZipCode());
		mySupervisee.setAddress(myAddress);

		IPhoneNumber myHomePhone = new PhoneNumber("");
		if (aResponseEvent.getHomePhoneNum() != null) {
			myHomePhone = new PhoneNumber(aResponseEvent.getHomePhoneNum());
		}
		mySupervisee.setHomePhone(myHomePhone);
		SuperviseeInfoHeaderForm.BasicEmplymentInfo myEmploymentInfo = new SuperviseeInfoHeaderForm.BasicEmplymentInfo();
		myEmploymentInfo.setEmployerName(aResponseEvent.getEmployerName());
		//	myEmploymentInfo.setEmploymentId(aResponseEvent.getEmploy)
		myEmploymentInfo.setTitle(aResponseEvent.getTitle());
		IPhoneNumber myWorkPhone = new PhoneNumber("");
		if (aResponseEvent.getWorkPhoneNum() != null) {
			myWorkPhone = new PhoneNumber(aResponseEvent.getWorkPhoneNum());
		}
		myEmploymentInfo.setPhone(myWorkPhone);
		mySupervisee.setEmploymentInfo(myEmploymentInfo);
		if (aResponseEvent.getCases() != null 
				&& aResponseEvent.getCases().size() > 0) {
			ArrayList myCasesList = new ArrayList();
			mySupervisee.setCases(myCasesList);
			Iterator iter = aResponseEvent.getCases().iterator();
			while (iter.hasNext()) {
				CasenoteCaseTO myCaseRespEvt = (CasenoteCaseTO) iter.next();
					CaseInfo myCase = new CaseInfo();
					StringBuffer padCase = null;
					padCase = new StringBuffer(myCaseRespEvt.getCaseNum());
				    if (padCase.length() < 12){
				    	while (padCase.length() < 12){
				    		padCase.insert(0, "0");
				    	}
				    	myCaseRespEvt.setCaseNum(padCase.toString());
				    }
					myCase.setCaseNum(myCaseRespEvt.getCaseNum());
					StringBuffer padCdi = null;
					padCdi = new StringBuffer(myCaseRespEvt.getCdi());
				    if (padCdi.length() < 3){
				    	while (padCdi.length() < 3){
				    		padCdi.insert(0, "0");
				    	}
				    	myCaseRespEvt.setCourtNum(padCdi.toString());
				    }
					myCase.setCdi(myCaseRespEvt.getCdi());
					StringBuffer padCourt = null;
					padCourt = new StringBuffer(myCaseRespEvt.getCourtNum());
				    if (padCourt.length() < 3){
				    	while (padCourt.length() < 3){
				    		padCourt.insert(0, "0");
				    	}
				    	myCaseRespEvt.setCourtNum(padCourt.toString());
				    }
					myCase.setCourt(myCaseRespEvt.getCourtNum());
					myCase.setCaseSupPeriodBeginDate(myCaseRespEvt
							.getCaseSupervisionPeriodBeginDate());
					myCase.setCaseSupPeriodEndDate(myCaseRespEvt
							.getCaseSupervisionPeriodEndDate());
					myCase.setOffenseCodeId(myCaseRespEvt.getOffenseCodeId());
					myCase.setSupPeriodId(myCaseRespEvt.getSupervisionPeriodId());
					myCase.setOrderId(myCaseRespEvt.getSupOrderId());
					myCase.setSuperviseeName(myCaseRespEvt.getSuperviseeName());
					myCasesList.add(myCase);
			}
			Collections.sort((List) myCasesList);
		} else {
			mySupervisee.setCases(new ArrayList());
		}
		StringBuffer padCourts = null;
		padCourts = new StringBuffer(aResponseEvent.getCourtNums());
	    if (padCourts.length() < 3){
	    	while (padCourts.length() < 3){
	    		padCourts.insert(0, "0");
	    	}
	    }
	    mySupervisee.setCourts(padCourts.toString());
		mySupervisee.setSupervisionPeriodId("");
		return mySupervisee;
	}
	
	public static BasicSupervisee getBasicSupervisee(SuperviseeListResponseEvent listEvent){
		if (listEvent == null)
		{
			return null;
		}
		BasicSupervisee mySupervisee = new BasicSupervisee();
		mySupervisee.setCon(listEvent.getConnectionId());
		mySupervisee.setCourts(listEvent.getCourtNum());
		mySupervisee.setDateOfBirth(listEvent.getDateOfBirth());
		mojo.km.utilities.Name aName = listEvent.getOfficerName();
		IName officerName = new Name();
		officerName.setFirstName(aName.getFirstName());
		officerName.setMiddleName(aName.getMiddleName());
		officerName.setLastName(aName.getLastName());
		mySupervisee.setOfficerName(officerName);
		mySupervisee.setRaceId(listEvent.getRaceId());
		mySupervisee.setSexId(listEvent.getSexId());
		mySupervisee.setSpn(listEvent.getSpn());
		ISocialSecurity ssn = listEvent.getSsn();
		mySupervisee.setSsn(ssn);
		mySupervisee.setSuperviseeId(listEvent.getSpn());
		IName mySuperviseeName = new Name(listEvent.getFirstName(), listEvent.getMiddleName(), listEvent.getLastName());
		mySupervisee.setSuperviseeName(mySuperviseeName);
		mySupervisee.setSupervisionPeriodId(listEvent.getSupervisionPeriodId());
		mySupervisee.setHasPartialInfo(true);
		return mySupervisee;
	}
	
	public static void setCasenoteForm(CasenoteForm aCasenoteForm,Casenote aCasenote, String aSupPeriodId){
		if(aCasenoteForm==null || aCasenote==null){
			return;
		}
		aCasenoteForm.clearAll();
		aCasenoteForm.setCasenoteDate(aCasenote.getCasenoteDate()); //TSV 12/12/2011 JIM200072104 - Set casenote date & time to current date & time the record was updated
		
		Date casenoteDate = aCasenote.getCasenoteDate();
		
		if ( casenoteDate != null )
		{
			SimpleDateFormat formatTime24 = new SimpleDateFormat("HH:mm");			
			String casenoteTime = formatTime24.format(casenoteDate);
			// if casenoteTime = '00:00' set time to current time
			if (StringUtils.isNotEmpty(casenoteTime) && casenoteTime.equals("00:00")){
				Date date = new Date(System.currentTimeMillis());
				casenoteTime = formatTime24.format(date);
			}
			aCasenoteForm.setCasenoteTime( casenoteTime );
		}
		aCasenoteForm.setCasenoteId(aCasenote.getCasenoteId());
		aCasenoteForm.setCasenoteStatusId(aCasenote.getCasenoteStatusId());
		aCasenoteForm.setCasenoteText(aCasenote.getCasenoteText());
		aCasenoteForm.setCasenoteTypeId(aCasenote.getCasenoteTypeId());
		aCasenoteForm.setCollateralId(aCasenote.getCollateralId());
		aCasenoteForm.setContactMethodId(aCasenote.getContactMethodId());
		aCasenoteForm.setContextId(aCasenote.getContextType());
		aCasenoteForm.setCreatedByName(aCasenote.getCreatedByName());
		aCasenoteForm.setGeneratedById(aCasenote.getGeneratedById());
		aCasenoteForm.setSubjectIds(aCasenote.getSubjectIds());
		aCasenoteForm.setAssociateIds(aCasenote.getAssociateIds());
		aCasenoteForm.setSuperviseeId(aCasenote.getSuperviseeId());
		aCasenoteForm.setSupervisionPeriodId(aSupPeriodId);
		aCasenoteForm.setCreateDate(aCasenote.getCreateDate());	
	}
	
	public static Casenote getCasenote(ICasenote aCasenote, String currentUserId){
		if (aCasenote == null)
		{
			return null;
		}
		Casenote myCasenote = new Casenote();
		myCasenote.setCaseId(aCasenote.getCaseId());
		myCasenote.setCasenoteDate(aCasenote.getEntryDate());
		myCasenote.setCasenoteId(aCasenote.getCasenoteId());
		myCasenote.setCasenoteStatusId(aCasenote.getCasenoteStatusId());
		
		// Replacing bad tags for printing
		myCasenote.setCasenoteText(aCasenote.getNotes());
		myCasenote.setAutoSaved(aCasenote.getAutoSaved());
		String timeStr = DateUtil.dateToString(aCasenote.getEntryDate(), "hh:mm a");
		
		if(timeStr!=null){
			if (timeStr.length()==4 || timeStr.length()==7){
				timeStr="0" + timeStr;
			}	
		}
		myCasenote.setCasenoteTime(timeStr);
		//myCasenote.setCasenoteTypeId(aCasenote.getCasenoteTypeId());
		myCasenote.setCollateralId(aCasenote.getCollateralId());
		myCasenote.setCollateralInfo(aCasenote.getCollateralInfo());
		//myCasenote.setContactMethodId(aCasenote.getContactMethodId());
		myCasenote.setContextType(aCasenote.getContextType());
		myCasenote.setCreatedById(aCasenote.getCreatorId());
		myCasenote.setMigrateCreator(aCasenote.getMigrateCreator());
		
		myCasenote.setGeneratedById(aCasenote.getHowGeneratedId());
		myCasenote.setSuperviseeId(aCasenote.getSuperviseeId());
		myCasenote.setCreateDate(aCasenote.getCreateDate());
		myCasenote.setAllowDelete(true);
		//if(UIUtil.getCurrentUserID().equals(aCasenote.getCreatorId())){
		if(currentUserId.equals(aCasenote.getCreatorId())){
			if("D".equalsIgnoreCase(myCasenote.getCasenoteStatusId())){
				myCasenote.setAllowEdit(true);
			}
			else{
				myCasenote.setAllowEdit(false);
			}
		}
		else{
			myCasenote.setAllowEdit(false);
			//If current user is not owner of casenote he/she should be allowed to delete a completed casenote.
			if(myCasenote.getCasenoteStatusId().equals("C")){
				myCasenote.setAllowDelete( true ); //RRY changed this back to True
			}	
		}
// 09-15-2010 #67271 allow compliance casenote to be deleted		
//		if(SupervisionOrderConditionConstants.CONTEXTTYPE.equalsIgnoreCase(aCasenote.getCasenoteTypeCodeId())){
//			myCasenote.setAllowDelete(false);
//		}
		return myCasenote;
	}
	
	public static Casenote getCasenote(CasenoteResponseEvent aCasenote){
		if (aCasenote == null)
		{
			return null;
		}
		Casenote myCasenote = new Casenote();
		myCasenote.setCaseId(aCasenote.getCaseId());
		myCasenote.setCasenoteDate(aCasenote.getEntryDate());
		myCasenote.setCasenoteId(aCasenote.getCasenoteId());
		myCasenote.setCasenoteStatusId(aCasenote.getCasenoteStatusId());
		
		// Replacing bad tags for printing
		myCasenote.setCasenoteText(aCasenote.getNotes());
		myCasenote.setAutoSaved(aCasenote.getAutoSaved());
		String timeStr = DateUtil.dateToString(aCasenote.getEntryDate(), "hh:mm a");
		
		if(timeStr!=null){
			if (timeStr.length()==4 || timeStr.length()==7){
				timeStr="0" + timeStr;
			}	
		}
		myCasenote.setCasenoteTime(timeStr);
		//myCasenote.setCasenoteTypeId(aCasenote.getCasenoteTypeId());
		myCasenote.setCollateralId(aCasenote.getCollateralId());
		myCasenote.setCollateralInfo(aCasenote.getCollateralInfo());
		//myCasenote.setContactMethodId(aCasenote.getContactMethodId());
		myCasenote.setContextType(aCasenote.getContextType());
		myCasenote.setCreatedById(aCasenote.getCreatorId());
		myCasenote.setMigrateCreator(aCasenote.getMigrateCreator());
		
		myCasenote.setGeneratedById(aCasenote.getHowGeneratedId());
		myCasenote.setSuperviseeId(aCasenote.getSuperviseeId());
		myCasenote.setCreateDate(aCasenote.getCreateDate());
		myCasenote.setAllowDelete(true);
		return myCasenote;
	}
	
    /**
     * 
     * @param casenoteList
     * @return
     */
    private static final String DB2ADMIN="DB2ADMIN";
	public static List resolveCreatorNames(List casenoteList){
		IUserProfile user = null;
		Casenote aCasenote = null;
		Map userMap = new HashMap();
		Name name = null;

		for (int i = 0; i < casenoteList.size(); i++) {
			aCasenote = (Casenote) casenoteList.get(i);
			name = (Name) userMap.get(aCasenote.getCreatedById());
			if (StringUtils.isNotEmpty(aCasenote.getMigrateCreator())){
				
				name = new Name(null, null, aCasenote.getMigrateCreator());
			}
			if (name == null && !aCasenote.getCreatedById().equals(DB2ADMIN)){
				user = SecurityUIHelper.getUser(aCasenote.getCreatedById());
				if(user != null) {
					name = new Name(user.getFirstName(), user.getMiddleName(), user.getLastName());
				}
				userMap.put(aCasenote.getCreatedById(), name);
			}
			
			if (name != null)
			{
				aCasenote.setCreatedByName(name);
			}
			casenoteList.set(i, aCasenote);
		}
		return casenoteList;
	}
	
	public static void setAllJournalCaseNotes(String supervisionPeriodId, CasenoteJournalForm myJournalForm){
		GetCasenotesEvent myEvent = (GetCasenotesEvent)EventFactory.getInstance(
				CasenoteControllerServiceNames.GETCASENOTES);
		myEvent.setSupervisionPeriodId(supervisionPeriodId);
		myEvent.setSuperviseeId(myJournalForm.getSuperviseeId());
		myEvent.setSearchBy(PDCommonSupervisionConstants.SEARCH_BY_CASES);
		//myEvent.setCaseIds()
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		
		dispatch.postEvent(myEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		// check for errors
		MessageUtil.processReturnException(response);
		Collection casenotes = MessageUtil.compositeToCollection(response, CasenoteResponseEvent.class);		
		ArrayList myExistingCasenotes = new ArrayList();
		if (!casenotes.isEmpty())
		{
			CasenoteSearchForm searchForm = myJournalForm.getSearchCasenote();
			Collection casenoteSubjectList = searchForm.getCasenoteSubjectList();
			Collection casenoteTypeList = searchForm.getCasenoteTypeList();
			Collection collateralList = searchForm.getCollateralList();
			Collection contactMethodList = searchForm.getContactMethodList();
			String currentUserId = UIUtil.getCurrentUserID();

			Iterator iter = casenotes.iterator();
			while (iter.hasNext())
			{
				ICasenote casenote = (CasenoteResponseEvent)iter.next();
				Casenote newCasenote = UICasenoteHelper.getCasenote(casenote, currentUserId);
				newCasenote.setCasenoteTypeId(casenote.getCasenoteTypeId(), casenoteTypeList);
				String[] associatesArr = UICasenoteHelper.getArrayFromCollection(casenote.getAssociates());
				newCasenote.setAssociateIds(associatesArr, collateralList);
				String[] cnSubjs = UICasenoteHelper.getArrayFromCollection(casenote.getSubjects());
				newCasenote.setSubjectIds(cnSubjs, casenoteSubjectList);
				newCasenote.setAssociateIds(associatesArr, collateralList);
				newCasenote.setContactMethodId(casenote.getContactMethodId(), contactMethodList);
				myExistingCasenotes.add(newCasenote);
			}
			UICasenoteHelper.resolveCreatorNames(myExistingCasenotes);
		}
		Collections.sort(myExistingCasenotes);
		myJournalForm.getSearchCasenote().setCasenoteResults(myExistingCasenotes);
	}

	public static String[] getArrayFromCollection(Collection coll){
		String[] myArr= new String[0];
		if (coll != null && !coll.isEmpty())
		{	
			myArr= new String[coll.size()];
			Collections.sort((List) coll);
			Iterator iter2 = coll.iterator();
			myArr=new String[coll.size()];
			int i=0;
			
			while(iter2.hasNext())
			{
				String aString =(String)iter2.next();
				
				myArr[i] = aString;
				i++;
			}
		}
		return myArr;
	}
	
    /**
     * Create Date from date and time Strings
     * 
     * @return Date
     * @pre strDate != null
     * @pre strTime != null
     * @pre strDate.length() == 8
     * @pre strTime.length() <= 5 (HH:MM)
     */
    public static Date convertToDateTime(String strDate, String strTime)
    {

        Date date = null;
        try
        {
            int year = new Integer(strDate.substring(0, 4)).intValue();
            int month = new Integer(strDate.substring(4, 6)).intValue();
            int day = new Integer(strDate.substring(6, 8)).intValue();
            int hours = new Integer(strTime.substring(0, 2)).intValue();
            int minutes = new Integer(strTime.substring(3, 5)).intValue();
            int seconds = 0;
            Calendar calendar = Calendar.getInstance();
            calendar.setLenient(false);
            calendar.set(year, month - 1, day, hours, minutes, seconds);
            date = calendar.getTime();
        }
        catch (NumberFormatException e)
        {
        }
        catch (IndexOutOfBoundsException e)
        {
        }
        catch (IllegalArgumentException e)
        {
            //This exception is thrown if calendar was created with an invalid
            // date.
        }
        return date;
    }
    
	public static void setCasesList(CasenoteJournalForm myJournalForm,HttpServletRequest aRequest){
		SuperviseeInfoHeaderForm mySuperviseeHeaderForm=UICommonSupervisionHelper.getSuperviseeInfoHeaderForm(aRequest,true);
		CasenoteSearchForm mySearchForm = myJournalForm.getSearchCasenote();
		if(mySearchForm!=null){
			ArrayList myCaseList=new ArrayList();
			mySearchForm.setCasesList(myCaseList);
			if(mySuperviseeHeaderForm!=null && mySuperviseeHeaderForm.getCases()!=null && mySuperviseeHeaderForm.getCases().size()>0){
				mySearchForm.setCasesList(mySuperviseeHeaderForm.getCases());
			}
		}
	}
}// END CLASS
