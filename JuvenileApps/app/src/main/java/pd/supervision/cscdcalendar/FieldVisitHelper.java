/*
 * Created on Feb 21, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.cscdcalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.administercasenotes.UpdateCasenoteEvent;
import messaging.cscdcalendar.SaveCSFVEventEvent;
import messaging.cscdcalendar.reply.CSFVItineraryResponseEvent;
import messaging.cscdcalendar.reply.CSFVSuperviseeDetailsResponseEvent;
import messaging.cscdcalendar.reply.CSFieldVisitResponseEvent;
import naming.CasenoteConstants;
import naming.PDCodeTableConstants;
import naming.SupervisionConstants;
import pd.address.Address;
import pd.codetable.criminal.OffenseCode;
import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.contact.party.Party;
import pd.criminalcase.CriminalCase;
import pd.security.PDSecurityHelper;
import pd.supervision.administercasenotes.Casenote;
import pd.supervision.administercasenotes.SpnCasenotesHandler;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionPeriod;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPositionHelper;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FieldVisitHelper {

	/**
	 * method for FieldVisitDetails ResponseEvent
	 * @param fieldVisitDetails
	 * @return
	 */
	public static CSFieldVisitResponseEvent getCSFVEventResponseEvent(FieldVisitEvent fieldVisit) {
		CSFieldVisitResponseEvent fieldVisitResponseEvent = new CSFieldVisitResponseEvent();
		if (fieldVisit.getFvPurpose() != null && (!fieldVisit.getFvPurpose().equals(""))) {
			fieldVisitResponseEvent.setPurpose(fieldVisit.getFvPurpose().getDescription());
		}
		if (fieldVisit.getFvType() != null && (!fieldVisit.getFvType().equals(""))) {
			fieldVisitResponseEvent.setFvType(fieldVisit.getFvType().getDescription());
		}
		if (fieldVisit.getSexOffendarType() != null && (!fieldVisit.getSexOffendarType().equals(""))) {
			fieldVisitResponseEvent.setSexOffendarType(fieldVisit.getSexOffendarType().getDescription());
		}
		if (fieldVisit.getFvMeasureType() != null && (!fieldVisit.getFvMeasureType().equals(""))) {
			fieldVisitResponseEvent.setMeasureType(fieldVisit.getFvMeasureType().getDescription());
		}
		if (fieldVisit.getContactMethod() != null && (!fieldVisit.getContactMethod().equals(""))) {
			fieldVisitResponseEvent.setContactMethod(fieldVisit.getContactMethod().getDescription());
		}
		if (fieldVisit.getFvOutcome() != null && (!fieldVisit.getFvOutcome().equals(""))) {
			fieldVisitResponseEvent.setOutcome(fieldVisit.getFvOutcome().getDescription());
		}
		if (fieldVisit.getStatus() != null && (!fieldVisit.getStatus().equals(""))) {
			fieldVisitResponseEvent.setEventStatus(fieldVisit.getStatus().getDescription());
		}

		//Supervisee alternate addressStuff

		if (fieldVisit.getState() != null && (!fieldVisit.getState().equals(""))) {
			fieldVisitResponseEvent.getAlternateAddress().setState(fieldVisit.getState().getDescription());
		}
		if (fieldVisit.getStreetType() != null && (!fieldVisit.getStreetType().equals(""))) {
			fieldVisitResponseEvent.getAlternateAddress().setStreetType(fieldVisit.getStreetType().getDescription());
		}

		fieldVisitResponseEvent.setPurposeCd(fieldVisit.getFvPurposeCd());
		fieldVisitResponseEvent.setFvTypeCd(fieldVisit.getFvTypeCd());
		fieldVisitResponseEvent.setOutcomeCd(fieldVisit.getFvOutcomeCd());
		fieldVisitResponseEvent.setMeasureTypeCd(fieldVisit.getFvMeasureTypeCd());
		fieldVisitResponseEvent.setContactMethodCd(fieldVisit.getContactMethodCd());
		fieldVisitResponseEvent.setSexOffendarTypeCd(fieldVisit.getSexOffendarTypeCd());
		fieldVisitResponseEvent.setEventType(fieldVisit.getEventTypeId());
		fieldVisitResponseEvent.getAlternateAddress().setAddressTypeCode(fieldVisit.getAddressTypeCd());
		fieldVisitResponseEvent.getAlternateAddress().setCountyCode(fieldVisit.getcountyCd());
		fieldVisitResponseEvent.getAlternateAddress().setStateCode(fieldVisit.getStateCd());
		fieldVisitResponseEvent.getAlternateAddress().setStreetTypeCode(fieldVisit.getStreetTypeCd());
		fieldVisitResponseEvent.setEventStatusCd(fieldVisit.getStatusId());

		fieldVisitResponseEvent.getAlternateAddress().setStreetNum(fieldVisit.getStreetNumber());
		fieldVisitResponseEvent.getAlternateAddress().setStreetName(fieldVisit.getStreetName());
		fieldVisitResponseEvent.getAlternateAddress().setAptNum(fieldVisit.getAptOrSuite());
		fieldVisitResponseEvent.getAlternateAddress().setCity(fieldVisit.getCity());
		fieldVisitResponseEvent.getAlternateAddress().setZipCode(fieldVisit.getZipcode());

		fieldVisitResponseEvent.setComments(fieldVisit.getComments());
		fieldVisitResponseEvent.setConditions(fieldVisit.getNoteWorthyconditions());
		fieldVisitResponseEvent.setStartTime(fieldVisit.getStartDatetime());
		fieldVisitResponseEvent.setEndTime(fieldVisit.getEndDatetime());
		fieldVisitResponseEvent.setAltPhone(fieldVisit.getAlternatePhone());
		fieldVisitResponseEvent.setAddrDesc(fieldVisit.getAddressDescription());
		fieldVisitResponseEvent.setCaution(fieldVisit.getCaution());
		fieldVisitResponseEvent.setNarrative(fieldVisit.getNarrative());
		fieldVisitResponseEvent.setFvIteneraryId(fieldVisit.getFvIteneraryId());
		fieldVisitResponseEvent.setFvEventId(fieldVisit.getOID());
		fieldVisitResponseEvent.setCsEventId(fieldVisit.getCsEventId());
		fieldVisitResponseEvent.setSuperviseeId(fieldVisit.getPartyId());
		fieldVisitResponseEvent.setEventStatusCd(fieldVisit.getStatusId());
		fieldVisitResponseEvent.setKeyMap(fieldVisit.getKeyMap());
		fieldVisitResponseEvent.setMarkedForDeleteOn(fieldVisit.getMarkedForDeleteOn());
		fieldVisitResponseEvent.setEventDate(fieldVisit.getEventDate());
		fieldVisitResponseEvent.setSequenceNum(fieldVisit.getSequenceNum());
		fieldVisitResponseEvent.setOtherPurpose(fieldVisit.getOtherPurpose());
		Party party = fieldVisit.getParty();
		//retrieves supervisee's address from party entity.
		if (party != null) {

			fieldVisitResponseEvent.setSuperviseeName(party.getFullNameWithLastNameFirst());
			fieldVisitResponseEvent.setSuperviseeSSN(party.getSsn());
			fieldVisitResponseEvent.setSuperviseeSpn(party.getSpn());
			fieldVisitResponseEvent.setSuperviseePhone(party.getHomePhoneNum());

			Address partyAddress = party.getCurrentAddress();
			if (partyAddress != null) {
				fieldVisitResponseEvent.getSuperviseeAddress().setStreetNum(partyAddress.getStreetNum());
				fieldVisitResponseEvent.getSuperviseeAddress().setStreetName(partyAddress.getStreetName());
				fieldVisitResponseEvent.getSuperviseeAddress().setCity(partyAddress.getCity());
				fieldVisitResponseEvent.getSuperviseeAddress().setState(partyAddress.getStateId());
				fieldVisitResponseEvent.getSuperviseeAddress().setZipCode(partyAddress.getZipCode());
				fieldVisitResponseEvent.getSuperviseeAddress().setAptNumber(partyAddress.getAptNum());
				fieldVisitResponseEvent.getSuperviseeAddress().setAptNum( partyAddress.getAptNum());
				fieldVisitResponseEvent.getSuperviseeAddress().setCountyCode(partyAddress.getCountyId());
			}
			//Get offenses for the supervisee
			Iterator iter = SupervisionOrder.findAll(SupervisionConstants.DEFENDANT_ID, fieldVisit.getPartyId());

			List offensesList = new ArrayList();
			CriminalCase criminalCase = null;

			while (iter.hasNext()) {
				SupervisionOrder so = (SupervisionOrder) iter.next();
				criminalCase = so.getCriminalCase();
				if (criminalCase != null){
					OffenseCode offense = criminalCase.getOffenseCode();
					if (offense != null) {
						String offenseDesc = offense.getDescription();
						if (!offensesList.contains(offenseDesc)) {
							offensesList.add(offenseDesc);
						}
					}
				}
			}
			fieldVisitResponseEvent.setOffenses(offensesList);
		}

		if (fieldVisit.getContactMethodCd() != null
				&& fieldVisit.getContactMethodCd().equalsIgnoreCase(PDCodeTableConstants.CONTACT_WITH_ASSOCIATES)) {
			ArrayList fvAssocList = new ArrayList();
			Iterator fvAssocIdIter = FieldVisitAssociate.findAll("fvEventId", fieldVisit.getOID());
			while (fvAssocIdIter != null && fvAssocIdIter.hasNext()) {
				fvAssocList.add(((FieldVisitAssociate) fvAssocIdIter.next()).getFvAssociateId());
			}
			String[] assocIds = new String[fvAssocList.size()];
			assocIds = (String[]) fvAssocList.toArray(assocIds);
			fieldVisitResponseEvent.setAssociateId(assocIds);
		}

		return fieldVisitResponseEvent;
	}

	/**
	 * method used to persist FieldVisitEvent
	 * 
	 * @param saveFvEvent
	 * @param csEvent
	 */
	public static void saveFieldVisitEvent(SaveCSFVEventEvent saveFvEvent, CSEvent csEvent, FieldVisitEvent fvEvent) {
		if (saveFvEvent.isCreate())
			fvEvent.setCsEventId(csEvent.getOID());
		else
			fvEvent.setCsEventId(fvEvent.getCsEventId());
		fvEvent.setFvIteneraryId(saveFvEvent.getFvIteneraryId());
		fvEvent.setFvPurposeCd(saveFvEvent.getPurposeCd());
		fvEvent.setFvTypeCd(saveFvEvent.getFvTypeCd());
		fvEvent.setSexOffendarTypeCd(saveFvEvent.getSexOffendarTypeCd());
		fvEvent.setComments(saveFvEvent.getComments());
		fvEvent.setCaution(saveFvEvent.getCaution());
		fvEvent.setAddressDescription(saveFvEvent.getAddrDesc());
		fvEvent.setSequenceNum(saveFvEvent.getSequenceNum());
		fvEvent.setNoteWorthyconditions(saveFvEvent.getConditions());
		fvEvent.setKeymap(saveFvEvent.getKeyMap());
		fvEvent.setStreetNumber(saveFvEvent.getStreetNum());
		fvEvent.setStreetName(saveFvEvent.getStreetName());
		fvEvent.setAptOrSuite(saveFvEvent.getAptNum());
		fvEvent.setCity(saveFvEvent.getCity());
		fvEvent.setZipcode(saveFvEvent.getZipcode());
		fvEvent.setCountyCd(saveFvEvent.getCounty());
		fvEvent.setStateCd(saveFvEvent.getState());
		fvEvent.setAddressTypeCd(saveFvEvent.getAddressTypeCd());
		fvEvent.setStreetTypeCd(saveFvEvent.getStreetType());
		fvEvent.setContactMethodCd(saveFvEvent.getContactMethodCd());
		fvEvent.setFvMeasureTypeCd(saveFvEvent.getMeasureTypeCd());
		if (saveFvEvent.isCreate())
			new mojo.km.persistence.Home().bind(fvEvent);
	}

	/**
	 * Constructs Response Object.
	 * 
	 * @param fieldVisitItinerary
	 * @return
	 */
	public static CSFVItineraryResponseEvent getCDFVItineraryResponseEvent(FieldVisitItinerary fieldVisitItinerary) {
		CSFVItineraryResponseEvent respEvent = new CSFVItineraryResponseEvent();
		respEvent.setPositionId(fieldVisitItinerary.getPositionId());
		String staffPositionId = fieldVisitItinerary.getPositionId();
		if ( StringUtils.isNotEmpty( staffPositionId ) ) {
			GetLightCSCDStaffForUserEvent reqEvt = new GetLightCSCDStaffForUserEvent();
			reqEvt.setStaffPositionId(staffPositionId);
			reqEvt.setOfficerNameNeeded(true);
	    	
	    	LightCSCDStaffResponseEvent staffPosRespEvt = CSCDStaffPositionHelper.getLightCSCDStaffForUser(reqEvt);
			//add logic to find userprofile to get positions phone and email
			if(staffPosRespEvt!=null)
			{
				if ( StringUtils.isNotEmpty( staffPosRespEvt.getOfficerName() ) ) {
					respEvent.setFVOfficer(staffPosRespEvt.getOfficerName());
				} else {
					respEvent.setFVOfficer("NO OFFICER ASSIGNED");						
				}
			}
		}
		if (StringUtils.isNotEmpty(fieldVisitItinerary.getRadioCallNum())) {
			respEvent.setRadioCallNum(fieldVisitItinerary.getRadioCallNum());
		}		
		respEvent.setItineraryDate(fieldVisitItinerary.getItineraryDate());
		respEvent.setInOfficeFrom(fieldVisitItinerary.getInOfficeFrom());
		respEvent.setInOfficeTo(fieldVisitItinerary.getInOfficeTo());
		respEvent.setInFieldFrom(fieldVisitItinerary.getInFieldFrom());
		respEvent.setInFieldTo(fieldVisitItinerary.getInFieldTo());
		respEvent.setMobile(fieldVisitItinerary.getPhoneNum());
		respEvent.setCarTypeCd(fieldVisitItinerary.getCarTypeCd());
		if (fieldVisitItinerary.getCarTypeCd() != null && (!fieldVisitItinerary.getCarTypeCd().equals("")))
			respEvent.setCarType(fieldVisitItinerary.getCarType().getDescription());
		respEvent.setMileageIn( String.valueOf( fieldVisitItinerary.getMileageIn()));
		respEvent.setMileageOut( String.valueOf( fieldVisitItinerary.getMileageOut()));
		respEvent.setAutoLicense(fieldVisitItinerary.getAutoLicenseNum());
		respEvent.setAutoYear(fieldVisitItinerary.getAutoYear());
		respEvent.setQuadrantCd(fieldVisitItinerary.getQuadrantCd());
		if (fieldVisitItinerary.getQuadrantCd() != null && (!fieldVisitItinerary.getQuadrantCd().equals("")))
			respEvent.setQuadrant(fieldVisitItinerary.getQuadrant().getDescription());
		respEvent.setAutoMake(fieldVisitItinerary.getAutoMake());
		respEvent.setAutoModel(fieldVisitItinerary.getAutoModel());
		respEvent.setAutoColor(fieldVisitItinerary.getAutoColor());
		respEvent.setFvItineraryId(fieldVisitItinerary.getOID());
		respEvent.setP1LastName(fieldVisitItinerary.getP1LastName());
		respEvent.setP1MiddleName(fieldVisitItinerary.getP1MiddleName());
		respEvent.setP1FirstName(fieldVisitItinerary.getP1FirstName());
		respEvent.setP2LastName(fieldVisitItinerary.getP2LastName());
		respEvent.setP2MiddleName(fieldVisitItinerary.getP2MiddleName());
		respEvent.setP2FirstName(fieldVisitItinerary.getP2FirstName());
		respEvent.setP3LastName(fieldVisitItinerary.getP3LastName());
		respEvent.setP3MiddleName(fieldVisitItinerary.getP3MiddleName());
		respEvent.setP3FirstName(fieldVisitItinerary.getP3FirstName());
		return respEvent;
	}
	
	
	public static CSFVSuperviseeDetailsResponseEvent getCSFVSuperviseeDeatilsResponseEvent(FieldVisitSuperviseeDetails fvSuperviseeInfo) {
		
		CSFVSuperviseeDetailsResponseEvent respEvent = new CSFVSuperviseeDetailsResponseEvent();		
		respEvent.setAddressDesc(fvSuperviseeInfo.getAddressDesc());
		respEvent.setCaution(fvSuperviseeInfo.getCaution());
		respEvent.setComments(fvSuperviseeInfo.getComments());
		respEvent.setSuperviseeId(fvSuperviseeInfo.getDefendantId());
		return respEvent;
	}

	/**
	 * @param partyId
	 * @param saveFvEvent
	 * Method to create CaseNote
	 */
	public static void createCasenote(String partyId, SaveCSFVEventEvent saveFvEvent, Date casenoteDate) {
		StringBuffer notes = new StringBuffer("");
		String contactMethod = "";
		String[] associateIds = null;
		Collection associateList = null; 
		if (saveFvEvent.getContactMethodCd() != null)
			contactMethod = saveFvEvent.getContactMethodCd();
		notes.append("Field Visit Result Updated to:<br/>");
		notes.append("FIELD VISIT result for EventDate ");
		notes.append(dateToString(saveFvEvent.getEventDate()));
		notes.append(", StartTime ");
		notes.append(dateToTimeString(saveFvEvent.getStartTime()));
		notes.append(" to EndTime ");
		notes.append(dateToTimeString(saveFvEvent.getEndTime()));
		notes.append(", Event Name: FIELD VISIT");
		notes.append("<br/>Method of Contact: ");
		SupervisionCode contactMethodObj = PDSupervisionCodeHelper.getSupervisionCodeByValue(
				PDCodeTableConstants.CSCD_AGENCY, PDCodeTableConstants.CONTACT_METHOD, contactMethod);
		if (contactMethodObj != null)
			notes.append(contactMethodObj.getDescription());
		if (contactMethod.equalsIgnoreCase(PDCodeTableConstants.CONTACT_WITH_ASSOCIATES)
				&& saveFvEvent.getAssociateId() != null) {
			associateList = new ArrayList();			
			associateIds = saveFvEvent.getAssociateId();
			if (associateIds != null) {
				for (int i = 0; i < associateIds.length; i++) {
					//SuperviseeAssociate associate = SuperviseeAssociate.find(associateIds[i]);
					//AssociateResponseEvent associateResponseEvent = PDManageAssociateHelper.getAssociateResponseEvent(associate);
					//notes.append(associateResponseEvent.getFormattedName() + " ");
					associateList.add(associateIds[i]);
				}
			}
		}
		notes.append("<br/>Outcome: ");
		SupervisionCode outcomeObj = PDSupervisionCodeHelper.getSupervisionCodeByValue(
				PDCodeTableConstants.CSCD_AGENCY, PDCodeTableConstants.FV_OUTCOME, saveFvEvent.getFvOutcomeCd());
		if (outcomeObj != null)
			notes.append(outcomeObj.getDescription());
		notes.append("<br/>Narrative: ");
		notes.append(saveFvEvent.getNarrative());
		if (saveFvEvent.getMeasureTypeCd() != null && (!saveFvEvent.getMeasureTypeCd().equals(""))) {
			notes.append("<br/>Measurement Result: ");
			SupervisionCode measureTypeObj = PDSupervisionCodeHelper.getSupervisionCodeByValue(
					PDCodeTableConstants.CSCD_AGENCY, PDCodeTableConstants.FV_MEASUREMENT_TYPES, saveFvEvent
							.getMeasureTypeCd());
			if (measureTypeObj != null)
				notes.append(measureTypeObj.getDescription());
		}
		notes.append("<br/>Comments: ");
		notes.append(saveFvEvent.getComments());

		SupervisionPeriod supPer = SupervisionPeriod.findActiveSupervisionPeriod(SpnCasenotesHandler.padSpn(partyId),
				PDCodeTableConstants.CSCD_AGENCY);

		if (supPer != null) {
			UpdateCasenoteEvent updateEvent = new UpdateCasenoteEvent();
			updateEvent.setAgencyId(PDCodeTableConstants.CSCD_AGENCY);
			updateEvent.setSaveAsDraft(true);
			updateEvent.setCasenoteStatusId(CasenoteConstants.STATUS_DRAFT);
			updateEvent.setCasenoteTypeId(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
			//contact method is decided based upon the contact method of field
			// visit
			if (contactMethod.equalsIgnoreCase(PDCodeTableConstants.CONTACT_WITH_ASSOCIATES)){
				updateEvent.setContactMethodId(PDCodeTableConstants.CASENOTE_FVCONTACTID_COLLATERAL);
				if(associateList != null)
				updateEvent.setAssociates(associateList);
			}
			else if (contactMethod.equalsIgnoreCase(PDCodeTableConstants.CS_FV_CONTACTMETHOD_DIRECTCONTACT))
				updateEvent.setContactMethodId(PDCodeTableConstants.CASENOTE_FVCONTACTID_FACETOFACE);
			else
				updateEvent.setContactMethodId(PDCodeTableConstants.CASENOTE_FVCONTACTID_NOCONTACT);
			updateEvent.setContextType(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
			if (casenoteDate != null){
				updateEvent.setEntryDate(casenoteDate);
			} else {
				updateEvent.setEntryDate(new Date());
			}
			updateEvent.setHowGeneratedId(PDCodeTableConstants.CASENOTE_SYSTEM_GENERATED_ID);
			updateEvent.setNotes(notes.toString());
			Collection subjects = new ArrayList();
			subjects.add((PDSupervisionCodeHelper.getSupervisionCodeByValue(PDCodeTableConstants.CSCD_AGENCY,
					PDCodeTableConstants.CASENOTE_SUBJECT, PDCodeTableConstants.CS_FIELD_VISIT_CATEGORY)).getOID());
			updateEvent.setSubjects(subjects);
			updateEvent.setSupervisionPeriodId(supPer.getOID().toString());
			updateEvent.setSuperviseeId(SpnCasenotesHandler.padSpn(partyId));
			Casenote casenote = new Casenote();
			casenote.updateCasenote(updateEvent);
		}
	}

	/**
	 * @param associateId
	 * @param fvEventid
	 * Method for creating Associates for specific FieldVisitEventId
	 */
	public static void saveFVAssociates(String[] associateId, String fvEventid) {
		Iterator fvAssocIdIter = FieldVisitAssociate.findAll("fvEventId", fvEventid);
		while (fvAssocIdIter != null && fvAssocIdIter.hasNext()) {
			FieldVisitAssociate fvAssoc = (FieldVisitAssociate) fvAssocIdIter.next();
			fvAssoc.delete();
		}
		FieldVisitAssociate fvAssociate = null;
		if (associateId != null) {
			for (int i = 0; i < associateId.length; i++) {
				fvAssociate = new FieldVisitAssociate();
				fvAssociate.setFvEventId(fvEventid);
				fvAssociate.setFvAssociateId(associateId[i]);
			}
		}
	}

	/**
	 * @param saveFvEvent
	 * Method for Rescheduling FieldVisit
	 */
	public static void rescheduleFieldVisit(SaveCSFVEventEvent saveFvEvent) {
		//Creating CSEVENT and FVEVENT for new FvIteneraryId.
		saveFvEvent.setCreate(true);
		createFieldVisit(saveFvEvent);
		//Modifying the existing CSEVENT and FVEVENT for the old fvEvent_Id(rescheduleFVEventId).
		FieldVisitEvent fieldVisitEvent = FieldVisitEvent.find(saveFvEvent.getRescheduleFVEventId());
		if (fieldVisitEvent != null) {
			CSEvent csOldEvent = new CSEvent();
			csOldEvent = CSEvent.find(fieldVisitEvent.getCsEventId());
			if (csOldEvent != null) {
				csOldEvent.setStatusId(PDCodeTableConstants.CS_EVENT_STATUS_CLOSE);
				csOldEvent.setOutCome(PDCodeTableConstants.OTHER_AND_FV_OUTCOME_RESCHEDULED);
			}
		}
	}

	/**
	 * @param saveFvEvent
	 *  Method for Updating FieldVisit
	 */
	public static void updateFieldVisit(SaveCSFVEventEvent saveFvEvent) {
		FieldVisitEvent fieldVisitEvent = FieldVisitEvent.find(saveFvEvent.getFvEventid());
		CSEvent csEvent = null;
		if ( fieldVisitEvent != null ) {
			saveFvEvent.setCreate(false);
			csEvent = CSEvent.find(fieldVisitEvent.getCsEventId());
			if ( csEvent != null ) {
				setCSEvent(csEvent, saveFvEvent);
				String logonId = PDSecurityHelper.getLogonId();
				csEvent.setResultUserId(logonId);
				csEvent.setResultPositionId(saveFvEvent.getResultPositionId());
				if ( !saveFvEvent.getPurposeCd().equalsIgnoreCase("OT") ) {
					csEvent.setPurpose("");
				}
				if ( StringUtils.isNotEmpty(saveFvEvent.getFvOutcomeCd() ) ) {
					csEvent.setOutCome(saveFvEvent.getFvOutcomeCd());
				}
				saveFieldVisitEvent(saveFvEvent, csEvent, fieldVisitEvent);
				
				//Create Associates If the Method of Contact is Associate for the closed status events.
				if ( saveFvEvent.getContactMethodCd() != null
						&& saveFvEvent.getContactMethodCd().equalsIgnoreCase(
								PDCodeTableConstants.CONTACT_WITH_ASSOCIATES )
						&& csEvent.getStatusId().equalsIgnoreCase( PDCodeTableConstants.CS_EVENT_STATUS_CLOSE ) ) {
					saveFVAssociates(saveFvEvent.getAssociateId(), saveFvEvent.getFvEventid());
				}	
			}
		}
		//Create Casenote if the eventStatus is closed.
		if (csEvent != null && csEvent.getStatusId().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_STATUS_CLOSE)) {
			saveFvEvent.setStartTime(csEvent.getStartTime());
			saveFvEvent.setEndTime(csEvent.getEndTime());
			FieldVisitHelper.createCasenote(saveFvEvent.getPartyId(), saveFvEvent, fieldVisitEvent.getEventDate());
		}
	}

	/**
	 * @param saveFvEvent
	 *  Method for Enter Results of FieldVisit
	 */
	public static void resultsFieldVisit(SaveCSFVEventEvent saveFvEvent) {
		FieldVisitEvent fieldVisitEvent = FieldVisitEvent.find(saveFvEvent.getFvEventid());
		CSEvent csEvent = null;
		if (fieldVisitEvent != null) {
			saveFvEvent.setCreate(false);
			csEvent = CSEvent.find(fieldVisitEvent.getCsEventId());
			if (csEvent != null) {
				csEvent.setResultPositionId(saveFvEvent.getResultPositionId());
				String logonId = PDSecurityHelper.getLogonId();
				csEvent.setResultUserId(logonId);// RRY gets the user logged in
				csEvent.setOutCome(saveFvEvent.getFvOutcomeCd());
				csEvent.setStatusId(PDCodeTableConstants.CS_EVENT_STATUS_CLOSE);
				setCSEvent(csEvent, saveFvEvent);
				saveFieldVisitEvent(saveFvEvent, csEvent, fieldVisitEvent);
				//Create Associates If the Method of Contact is Associate for the closed status events.
				if (saveFvEvent.getContactMethodCd() != null
						&& saveFvEvent.getContactMethodCd().equalsIgnoreCase(
								PDCodeTableConstants.CONTACT_WITH_ASSOCIATES)) {
					saveFVAssociates(saveFvEvent.getAssociateId(), saveFvEvent.getFvEventid());
				}
			}
		}
		//Create Casenote.
		if (csEvent != null) {
			saveFvEvent.setStartTime(csEvent.getStartTime());
			saveFvEvent.setEndTime(csEvent.getEndTime());
			FieldVisitHelper.createCasenote(saveFvEvent.getPartyId(), saveFvEvent, csEvent.getEventDate());
		}
	}

	/**
	 * @param saveFvEvent
	 *  Method for CreateFieldVisit
	 */
	public static void createFieldVisit(SaveCSFVEventEvent saveFvEvent) {
		//Reorder itinerary
		Iterator iter = FieldVisitEvent.findAll(saveFvEvent.getFvIteneraryId());
		List fvEvents = new ArrayList();
		int count = 0;
		while (iter != null && iter.hasNext()) {
			fvEvents.add(iter.next());
		}
		count = fvEvents.size() + 1;
		saveFvEvent.setSequenceNum("" + count);
		//End
		CSEvent csEvent = new CSEvent();
		csEvent.setStatusId(PDCodeTableConstants.CS_EVENT_STATUS_OPEN);
		csEvent.setOutCome(PDCodeTableConstants.OTHER_AND_FV_OUTCOME_SCHEDULED);
		csEvent = setCSEvent(csEvent, saveFvEvent);
		csEvent = (CSEvent) new mojo.km.persistence.Home().bind(csEvent);
		if (csEvent != null && csEvent.getOID() != null && (!(csEvent.getOID().equals("")))) {
			FieldVisitEvent fvEvent = new FieldVisitEvent();
			saveFieldVisitEvent(saveFvEvent, csEvent, fvEvent);
		}
	}

	/**
	 * @param csEvent
	 * @param saveFvEvent
	 * @return
	 */
	private static CSEvent setCSEvent(CSEvent csEvent, SaveCSFVEventEvent saveFvEvent) {
		csEvent.setStartTime(saveFvEvent.getStartTime());
		csEvent.setEndTime(saveFvEvent.getEndTime());
		csEvent.setEventDate(saveFvEvent.getEventDate());
		csEvent.setPhoneNumber(saveFvEvent.getAltPhone());
		csEvent.setPositionId(saveFvEvent.getPositionId());
		csEvent.setAssignStaffPos_Id(saveFvEvent.getAssignStaffPos_Id());
		csEvent.setEventTypeId(saveFvEvent.getEventTypeId());
		csEvent.setPartyId(saveFvEvent.getPartyId()); // SuperviseeId
		csEvent.setNarrative(saveFvEvent.getNarrative());
		csEvent.setPurpose(saveFvEvent.getOtherPurpose());
		return csEvent;
	}

	/**
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat dfmt = new SimpleDateFormat("MM/dd/yyyy");
		String dateStr = "";
		try {
			if (date != null) {
				dateStr = dfmt.format(date);
			}
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			throw ex;
		}

		return dateStr;
	}

	/**
	 * @param date
	 * @return
	 */
	public static String dateToTimeString(Date date) {
		SimpleDateFormat dfmt = new SimpleDateFormat("h:mm a");
		String dateStr = "";
		try {
			if (date != null) {
				dateStr = dfmt.format(date);
			}
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			throw ex;
		}

		return dateStr;
	}

}
